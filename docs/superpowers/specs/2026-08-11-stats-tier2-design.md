# T10 · 统计二档 设计（StatsAppService 续 + 4 端点）

> 对应 Issue #18（parent #8）。本文是**实现规约**：沿用 T9（Issue #17）确立的统计范式，
> 落地统计二档 4 端点。日期：2026-08-11｜分支：develop。
> 范式源文件：[`2026-08-11-stats-tier1-design.md`](2026-08-11-stats-tier1-design.md)。

## 1. 目标与范围

在既有 `StatsAppService` / `StatsQueryRepository` / `JooqStatsQueryRepository` / `StatsApiV1Controller` 上**追加** 4 个只读统计方法 + 端点，全部 `@RequireSignature`、**银行无关**（CONTEXT.md 不变式 5）：

| 端点 | 参数 | 数据源 |
|---|---|---|
| `GET /api/v1/stats/by-business-system` | `from` `to` | PaymentOrder + RefundOrder |
| `GET /api/v1/stats/by-channel` | `from` `to` | PaymentOrder（pay_mode / access_type） |
| `GET /api/v1/stats/anomalies` | （无；阈值走配置） | PaymentOrder + RefundOrder + PaymentLog |
| `GET /api/v1/stats/operations/activity` | `from` `to` | OperationLog |

**不在本期范围**：CSV 导出、异常单的明细列表（用 T2/T4 列表端点下钻）、多银行通道。

## 2. 关键决策（沿用 T9 + 本期新增）

1. **范式完全沿用 T9**：jOOQ 读侧（`DSLContext.resultQuery` plain SQL）+ AppService Mockito 缝（依赖 `StatsQueryRepository` 接口，测试 mock 投影 record 断言装配）+ 薄委托控制器。列别名双引号、`deleted = FALSE`、`from/to` 走命名绑定防注入、`fetchInto(record)` 按名映射。
2. **比率口径复用 T9**：`BigDecimal` 比率 `[0,1]` 4 位小数（`HALF_UP`），分母为 0 返回 `0.0000`（除零保护）；金额一律 `long`（分）。
3. **退款率口径（本期定义）**：`refundRate = refundedCount / paidCount`（退款成功笔数 / 支付成功笔数），`[0,1]` 4 位小数，分母为 0 返回 `0.0000`。即「每 100 笔成功支付里有多少笔发生成功退款」，是运营评估各业务系统健康度的标准口径。
4. **anomalies「长时」阈值可配置**（AC）：三个阈值经 `@Value` 注入 `StatsAppService` 构造器，默认值：
   - `payment.stats.anomaly.long-pending-payment-hours:24`（支付单 PENDING 超 24h 视为长时）
   - `payment.stats.anomaly.long-refunding-refund-hours:48`（退款单 REFUNDING 超 48h；退款链路更长故阈值更大）
   - `payment.stats.anomaly.failure-window-hours:1`（近 1 小时内的查询/回调失败计入「近期」）
   - 「当前时间」由 SQL 的 `NOW()` 计算（不在 Java 侧取 `LocalDateTime.now()`，保证 AppService 缝测试完全确定性）；阈值以 `long hours` 传入仓储，SQL 写 `created_at < NOW() - INTERVAL '1 hour' * :hours`（PostgreSQL 支持 `interval * integer`，绑定参数为整数，无注入）。
5. **by-channel 双维度**：`pay_mode` 与 `access_type` 各自独立聚合（两路 `GROUP BY`），返回两个列表。两列均可空（预支付才赋值），SQL 过滤 `IS NOT NULL`；缺失枚举值由 AppService 按枚举补零（与 status-distribution 同构），并把 Integer code 映射为枚举 `name`（微信/支付宝/H5/APP/…）。
6. **operations/activity 数据源 = OperationLog（单一）**：按 `operator_id, operator_name, operation` 三元组聚合，AppService 透视成「每个操作员的各操作类型笔数」；通知重发（`operation = NOTIFY_RESEND`）额外按 `operator_system`（来源业务系统）二次聚合。
7. **by-business-system 双源合并**：payment 与 refund 各自按 `business_system_name` 聚合，AppService 取两源名称的并集（保序：payment 顺序优先，refund-only 的追加在后），缺失侧补零。

## 3. 架构（沿用 T9，零新顶层结构）

```
infrastructure/query/
  StatsQueryRepository        ← 接口追加 9 个方法
  JooqStatsQueryRepository    ← 追加 9 个 plain SQL 实现
  projection/                 ← 追加 7 个投影 record
application/
  StatsAppService             ← 追加 4 个 public 方法 + 装配 helper；构造器增 3 个 @Value 阈值
  dto/response/               ← 追加 4 个响应 DTO
endpoints/api/v1/
  StatsApiV1Controller        ← 追加 4 个 GET
```

**分层合规**：接口 + 实现同处 `infrastructure/query/`（规范 §1.2 允许应用层依赖基础设施层接口）；领域层零改动；Mockito 缝保留（AppService 依赖 `StatsQueryRepository` *接口*）；jOOQ 实现不单测（沿用 spec「不引入 @DataJpaTest」）。

## 4. 投影 record（infrastructure/query/projection/，新增 7 个）

| Record | 字段 | 用途 |
|---|---|---|
| `BusinessSystemPaymentRollup` | `String businessSystemName, Long orderCount, Long totalAmount, Long paidCount, Long paidAmount` | by-business-system 支付侧（`paidCount/Amount` 经 `SUM(CASE WHEN status=PAID THEN …)`） |
| `BusinessSystemRefundRollup` | `String businessSystemName, Long orderCount, Long totalAmount, Long refundedCount, Long refundedAmount` | by-business-system 退款侧（`status=SUCCESS`） |
| `ChannelPaymentRollup` | `Integer channelCode, Long orderCount, Long totalAmount, Long paidCount, Long paidAmount` | by-channel 通用（pay_mode 与 access_type 共用此形状，按 Integer code 分组） |
| `StuckOrderCount` | `Long orderCount, Long totalAmount` | anomalies 长时 PENDING 支付 / 长时 REFUNDING 退款 |
| `FailureCountByType` | `String logType, Long failureCount` | anomalies 近期查询/回调失败（按 log_type 归组） |
| `OperatorOperationCount` | `Long operatorId, String operatorName, Integer operation, Long opCount` | operations/activity 按操作员×操作类型 |
| `NotifyResendBySystem` | `String operatorSystem, Long resendCount` | operations/activity 通知重发按来源业务系统 |

> `OperatorOperationCount` 与既有 `AuditorAuditCount` 同形但语义不同（前者覆盖全操作、按操作员；后者仅审核操作、按审核人）。不共用以免命名误导。

## 5. StatsQueryRepository 接口（追加 9 方法）

```java
// by-business-system
List<BusinessSystemPaymentRollup> businessSystemPaymentRollup(LocalDateTime from, LocalDateTime to);
List<BusinessSystemRefundRollup> businessSystemRefundRollup(LocalDateTime from, LocalDateTime to);

// by-channel（pay_mode / access_type 各一）
List<ChannelPaymentRollup> payModeRollup(LocalDateTime from, LocalDateTime to);
List<ChannelPaymentRollup> accessTypeRollup(LocalDateTime from, LocalDateTime to);

// anomalies（hours 由 AppService 传入，SQL 用 NOW() 计算 cutoff）
StuckOrderCount longPendingPayments(long pendingHours);
StuckOrderCount longRefundingRefunds(long refundingHours);
List<FailureCountByType> recentFailureCounts(long windowHours);

// operations/activity
List<OperatorOperationCount> operatorOperationCounts(LocalDateTime from, LocalDateTime to);
List<NotifyResendBySystem> notifyResendBySystem(LocalDateTime from, LocalDateTime to);
```

> `longPendingPayments` / `longRefundingRefunds` 返回单行 `StuckOrderCount`；无数据时 SQL `COALESCE` 保证返回 `{0,0}`（不返回 null），AppService 直接装配。

## 6. 响应 DTO 契约（application/dto/response/，新增 4 个）

### 6.1 `ByBusinessSystemResponse`
```java
record ByBusinessSystemResponse(List<BusinessSystemBreakdown> businessSystems) {
    record BusinessSystemBreakdown(
        String businessSystemName, Summary payment, Summary refund, BigDecimal refundRate
    ) {}
    record Summary(long count, long amount, long successCount, long successAmount, BigDecimal successRate) {}
}
```
- `payment`：`count/amount` 来自 payment rollup；`successCount/Amount` = `paidCount/Amount`；`successRate = paidCount / count`。
- `refund`：`count/amount` 来自 refund rollup；`successCount/Amount` = `refundedCount/Amount`；`successRate = refundedCount / count`。
- `refundRate = refundedCount / paidCount`（跨 payment/refund 两源，分母取 payment 侧）。
- 业务系统集合 = payment ∪ refund 名称并集（保序），缺失侧补零。

### 6.2 `ByChannelResponse`
```java
record ByChannelResponse(List<ChannelBreakdown> byPayMode, List<ChannelBreakdown> byAccessType) {
    record ChannelBreakdown(
        Integer channelCode, String channelName,
        long count, long amount, long successCount, long successAmount, BigDecimal successRate
    ) {}
}
```
- `byPayMode` / `byAccessType`：按 `PayMode.values()` / `AccessType.values()` 枚举顺序全列（补零）；`channelName` 取自枚举 `getName()`；`successRate = paidCount / count`。
- 仅聚合 `pay_mode IS NOT NULL` / `access_type IS NOT NULL` 的支付单（预支付前的单不计入渠道分布）。

### 6.3 `AnomaliesResponse`
```java
record AnomaliesResponse(
    StuckOrders longPendingPayments, StuckOrders longRefundingRefunds, RecentFailures recentFailures
) {
    record StuckOrders(long count, long amount) {}
    record RecentFailures(long totalCount, List<FailureCount> byType) {}
    record FailureCount(String logType, long failureCount) {}
}
```
- `longPendingPayments`：`status=PENDING AND created_at < NOW() - INTERVAL '1 hour' * :pendingHours` 的笔数 + 金额。
- `longRefundingRefunds`：`status=REFUNDING AND created_at < NOW() - INTERVAL '1 hour' * :refundingHours`。
- `recentFailures`：`success=FALSE AND log_type IN (PAYMENT_QUERY, REFUND_QUERY, PAYMENT_CALLBACK) AND created_at > NOW() - INTERVAL '1 hour' * :windowHours`；`totalCount` = 各类型求和，`byType` 透传。

### 6.4 `OperationsActivityResponse`
```java
record OperationsActivityResponse(List<OperatorActivity> byOperator, NotifyResendActivity notifyResend) {
    record OperatorActivity(Long operatorId, String operatorName, long totalCount, List<OperationCount> operations) {}
    record OperationCount(Integer operation, String operationName, long count) {}
    record NotifyResendActivity(long totalCount, List<SystemResendCount> byBusinessSystem) {}
    record SystemResendCount(String businessSystem, long count) {}
}
```
- `byOperator`：按 `operatorId` 透视，`operations` 列出该操作员各 `OperationType`（AUDIT_APPROVE/AUDIT_REJECT/NOTIFY_RESEND）的笔数（`operationName` = `OperationType.getName()`）；`totalCount` = 各操作求和。`operatorId` 为 null 的系统动作单独归为一组（保留 null，前端可显示「系统」）。
- `notifyResend.totalCount` = `NOTIFY_RESEND` 总笔数；`byBusinessSystem` 按 `operator_system` 归组（null 归为 null 组）。

## 7. StatsAppService 改动（构造器 + 4 方法）

**构造器**：去 `@RequiredArgsConstructor`，改显式构造器注入 3 个 `@Value` 阈值。与既有 `PaymentAppService` 的「`@RequiredArgsConstructor` + 字段级 `@Value`」用法**不同**——这里特意放构造器以利缝测试直接传值（`new StatsAppService(repo, 24, 48, 1)`），避免反射设字段：
```java
public StatsAppService(
    StatsQueryRepository statsQueryRepository,
    @Value("${payment.stats.anomaly.long-pending-payment-hours:24}") long longPendingPaymentHours,
    @Value("${payment.stats.anomaly.long-refunding-refund-hours:48}") long longRefundingRefundHours,
    @Value("${payment.stats.anomaly.failure-window-hours:1}") long failureWindowHours
)
```
既有 T9 测试 `setUp()` 同步改为 `new StatsAppService(repo, 24, 48, 1)`。

**4 个 public 方法**：`byBusinessSystem(from, to)` / `byChannel(from, to)` / `anomalies()` / `operationsActivity(from, to)`。前两者与最后一者窗口校验复用 `validateRange`（from 为 null 或 from>to 抛 `STATS_INVALID_RANGE`）；`anomalies()` 无窗口参数。

**装配 helper（pitest 主料，纯逻辑无 IO）**：
- `rate(n, d)` 已存在，复用。
- by-business-system：payment/refund 两源按 name 并集（`LinkedHashMap` 保序），缺失补零 Summary，算 `refundRate = refundedCount / paidCount`。
- by-channel：按 `PayMode`/`AccessType` 枚举顺序补零 + code→name 映射。
- anomalies：三路仓储结果直装（仓储已 COALESCE）；`recentFailures.totalCount` = `byType` 求和；用 `verify(repo).longPendingPayments(24L)` 等断言阈值真的经构造器流入仓储（变异友好，杀「硬编码默认值」变异）。
- operations/activity：按 `operatorId`（含 null 系统动作组）透视 operation 行；`NOTIFY_RESEND` 按 `operator_system` 归组。

## 8. jOOQ SQL 要点（JooqStatsQueryRepository 追加 9 方法）

- **列别名双引号**：投影列 `AS "orderCount"` 等，保留驼峰供 `fetchInto` 按名映射。
- **窗口**：复用既有 `whereDeleted(from, to)` / `windowClause(...)` / `bindWindow(...)`。
- **CASE 聚合成功笔数/金额**：`SUM(CASE WHEN status = <PAID|SUCCESS code> THEN 1 ELSE 0 END)`、`COALESCE(SUM(CASE WHEN status=<code> THEN amount ELSE 0 END), 0)`（与 T9 trend 同构）。
- **anomalies cutoff**：`created_at < NOW() - INTERVAL '1 hour' * :hours`（pending）/ `created_at > NOW() - INTERVAL '1 hour' * :windowHours`（failures）。`hours` 走命名绑定（long）。
- **anomalies 状态/日志类型常量**：`status` 用 `PaymentStatus.PENDING.getCode()` / `RefundStatus.REFUNDING.getCode()` 绑定；`log_type` 三值用 `IN (:q1, :q2, :q3)` 绑定字符串字面量（白名单内联 `"PAYMENT_QUERY" / "REFUND_QUERY" / "PAYMENT_CALLBACK"`，与 T9 审核方法内联 `AUDIT_APPROVE/REJECT` code 同风格）。
- **by-channel 非空过滤**：`WHERE pay_mode IS NOT NULL` / `access_type IS NOT NULL`，再接窗口。
- **银行无关**：所有 SQL 不出现 ICBC 硬编码。

## 9. 测试（沿用 spec Testing Decisions + T9 模式）

**主缝 `StatsAppServiceTest` 追加方法**（Mockito，mock `StatsQueryRepository`，断言装配）：
- by-business-system：双源并集 + 缺失补零 + refundRate（含除零）+ 空表。
- by-channel：枚举补零 + code→name 映射 + 缺失渠道补零 + 空表。
- anomalies：三路仓储结果装配 + `recentFailures.totalCount` 求和 + 空表全零；用 `verify(repo).longPendingPayments(<hours>)` 验配置阈值真的经构造器流入仓储（变异友好，杀「硬编码默认值」变异；附一例自定义阈值 `(2,6,1)` 重构服务验「可配置」AC）。
- operations/activity：操作员透视 + `NOTIFY_RESEND` byBusinessSystem 归组 + 空表。
- 窗口校验：`byBusinessChannel`/`byChannel`/`operationsActivity` 的 from>to 抛 `STATS_INVALID_RANGE`。

**pitest**：`StatsAppService` 已在 `targetClasses`（T9 起纳入），新方法自动覆盖，无需改 pom。阈值 ≥ 70%。

**不引入** `@DataJpaTest`、控制器测试缝（薄委托，签名兜底沿用既有 `SignatureVerificationIntegrationTest`）。`ArchitectureTest` 自动守护分层。

## 10. 错误定义

**无需新增** `PaymentMessage`。窗口校验复用 T9 的 `STATS_INVALID_RANGE`；anomalies 无窗口参数、无新校验。

## 11. 性能与索引

- by-business-system：`GROUP BY business_system_name` + `created_at` 窗口；`idx_payment_orders_system` / `idx_refund_orders_system` + `created_at` 索引已存在（V1）。
- by-channel：`GROUP BY pay_mode` / `access_type`；`pay_mode` / `access_type` 索引已存在（V5）。
- anomalies：`status` + `created_at` 复合条件；两列索引均存在（V1）。`PaymentLog` 的 `log_type` + `created_at` 索引存在（V1/V10）。
- operations/activity：`operator_id` + `created_at`；`idx_operation_logs_operator_id` / `created_at` 存在（V6）。
- **预计无需新增迁移**；若 EXPLAIN 发现缺口再补 V11。

## 12. 验收标准对照

| AC | 满足方式 |
|---|---|
| 4 端点经签名、结构化聚合、银行无关、走索引 | `@RequireSignature` + 4 响应 DTO；SQL 无 ICBC 硬编码；分组/窗口列均有既有索引 |
| anomalies 的「长时」阈值可配置 | 3 个 `payment.stats.anomaly.*` 属性，`@Value` 注入，默认 24/48/1；测试用 ArgumentCaptor 验阈值流到仓储 |
| AppService Mockito 缝测试覆盖，pitest 过 | `StatsAppServiceTest` 追加 4 端点装配用例；StatsAppService 已在 pitest targetClasses |
