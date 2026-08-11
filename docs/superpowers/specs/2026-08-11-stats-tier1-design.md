# T9 · 统计一档 设计（StatsAppService + 4 端点）

> 对应 Issue #17（parent #8）。本文是**实现规约**：确立 T9 的统计范式，供 T10 沿用。
> 日期：2026-08-11｜分支：develop

## 1. 目标与范围

新增 `StatsAppService`（只读）+ 4 个 `@RequireSignature` 端点，全部**银行无关**（CONTEXT.md 不变式 5）：

| 端点 | 参数 | 数据源 |
|---|---|---|
| `GET /api/v1/stats/payments/overview` | `from` `to` `granularity`(DAY\|HOUR) | PaymentOrder + RefundOrder |
| `GET /api/v1/stats/orders/status-distribution` | （无，全局快照） | PaymentOrder + RefundOrder |
| `GET /api/v1/stats/gateway/health` | `from` `to` | PaymentLog |
| `GET /api/v1/stats/operations/audit` | `from` `to` | OperationLog + RefundOrder |

**Out of scope（T10 / 另案）**：按业务系统维度、按渠道/接入类型、异常监控、操作活跃度、CSV 导出。

## 2. 关键决策（brainstorming 已确认）

1. **聚合走框架读侧 `cartisan-data-query`（jOOQ + DSLContext）**，不自己造 JPQL 投影范式。jOOQ 原生支持 `date_trunc` 分桶、跨表 JOIN、`fetchInto(record)` 直装。确立「stats = jOOQ 读侧」范式给 T10 沿用。
2. **趋势序列补零**：AppService 按 `from→to + granularity` 生成完整连续桶序列，SQL 只返回有数据的桶，缺失桶填 0。
3. **status-distribution = 全局快照**：无时间窗、无 businessSystem 过滤；所有状态枚举值都出现（补零）；退款 PENDING 积压单列。
4. **时间参数**：`from`+`to`（LocalDateTime，必填，`from <= to`）+ `granularity`（DAY\|HOUR，缺省 DAY，仅 overview 用）。gateway / audit 不带 granularity。
5. **比率口径**：`BigDecimal` 比率 `[0,1]` 4 位小数（`HALF_UP`）；分母为 0 时返回 `0.0000`（除零保护）。金额一律 `Long`（分，架构规则禁 float/double）。

## 3. 架构（CQRS 读侧 + Mockito 缝）

```
infrastructure/query/
  StatsQueryRepository        ← 接口（Mockito 缝：AppService 依赖它，测试 mock 它）
  JooqStatsQueryRepository    ← @Repository 实现，注入 DSLContext，写 SQL
  projection/                 ← 投影 record（读模型，fetchInto 目标）
application/
  StatsAppService             ← 依赖 StatsQueryRepository；装配 投影→响应 DTO
  dto/response/               ← 4 个统计响应 DTO（admin-bff 契约）
endpoints/api/v1/
  StatsApiV1Controller        ← 4 GET，@RequireSignature，返回 ApiResponse<…>
```

**分层合规性**：
- 接口 + 实现同处 `infrastructure/query/`。编码规范 §1.2 明确允许「应用层依赖基础设施层接口」的写法（接口与适配器同包分开文件）。
- **领域层零改动**：统计是查询读模型，非领域概念；不在 `domain/` 加聚合投影，避免污染。
- **Mockito 缝保留**：`StatsAppService` 依赖 `StatsQueryRepository` *接口*；测试 mock 接口返回投影 record，断言装配（成功率、补零、净额、按人归组）。jOOQ 实现不单测（沿用 spec「不引入 @DataJpaTest」——查询正确性靠显式可读 SQL + 框架）。

## 4. jOOQ 使用约定

- **跳过 jOOQ 代码生成插件**（避免 build 期依赖运行中的 Postgres，manual QUERY-001）。改用 **plain SQL via `DSLContext`**：
  ```java
  dsl.resultQuery("""
      SELECT status AS status,
             COUNT(*) AS orderCount,
             COALESCE(SUM(amount), 0) AS totalAmount,
             COALESCE(SUM(actual_amount), 0) AS paidAmount
      FROM pay_payment_orders
      WHERE deleted = FALSE AND created_at BETWEEN :from AND :to
      GROUP BY status
      """)
     .bind("from", from).bind("to", to)
     .fetchInto(PaymentStatusCount.class);
  ```
- **列别名 = 投影 record 组件名**（驼峰），`fetchInto` 按名映射。
- **`granularity` 白名单内联**：枚举校验后取 `'day'`/`'hour'` 字面量拼入 `date_trunc(<lit>, created_at)`，不作为绑定参数（`date_trunc` 首参为 text literal）；`from`/`to` 走绑定参数防注入。
- **软删除**：所有查询 `deleted = FALSE`（四个聚合均继承 `AuditableSoftDeletable`）。
- **方言**：`SQLDialect.POSTGRES`（框架 `JooqAutoConfiguration` 固定）。

## 5. 投影 record（infrastructure/query/projection/）

| Record | 字段 | 用途 |
|---|---|---|
| `PaymentStatusCount` | `Integer status, Long orderCount, Long totalAmount, Long paidAmount` | overview 摘要 + distribution（paidAmount=SUM(actual_amount)，PAID 行即成功金额） |
| `RefundStatusCount` | `Integer status, Long orderCount, Long totalAmount` | overview 退款摘要 + distribution（SUCCESS 行 totalAmount 即成功金额） |
| `PaymentTrendBucket` | `LocalDateTime bucketStart, Long orderCount, Long totalAmount, Long paidCount, Long paidAmount` | overview 支付趋势（paidCount/Amount 经 `SUM(CASE WHEN status=2 THEN …)`) |
| `RefundTrendBucket` | `LocalDateTime bucketStart, Long orderCount, Long totalAmount, Long refundedCount, Long refundedAmount` | overview 退款趋势（status=5 为 SUCCESS） |
| `GatewayInterfaceRollup` | `String bankCode, String bankInterface, Long totalCount, Long successCount, BigDecimal avgExecutionTime` | gateway-health 各接口汇总 |
| `GatewayReturnCodeCount` | `String bankInterface, String returnCode, Long codeCount` | gateway-health returnCode 分布（按 bankInterface 归组） |
| `AuditOperationCount` | `Integer operation, Long opCount` | operations-audit 总览（AUDIT_APPROVE/AUDIT_REJECT） |
| `AuditorAuditCount` | `Long auditorId, String auditorName, Integer operation, Long opCount` | operations-audit 按审核人 |
| `AvgAuditDuration` | `BigDecimal avgDurationMinutes` | operations-audit 平均审核时长（源自 RefundOrder） |

## 6. 响应 DTO 契约（application/dto/response/）

### 6.1 `PaymentOverviewResponse`
```java
record PaymentOverviewResponse(
    Summary payment, Summary refund, long netAmount, List<TrendBucket> trend
) {
    record Summary(long count, long amount, long successCount, long successAmount, BigDecimal successRate) {}
    record TrendBucket(LocalDateTime bucket,
        long paymentCount, long paymentAmount, long paidCount, long paidAmount,
        long refundCount, long refundAmount, long refundedCount, long refundedAmount) {}
}
```
- `payment`：`count`=窗口内支付单数，`amount`=SUM(amount)，`successCount`=PAID 行 orderCount，`successAmount`=PAID 行 paidAmount，`successRate`。
- `refund`：`count`=窗口内退款单数，`amount`=SUM(refund_amount)，`successCount`=SUCCESS 行 orderCount，`successAmount`=SUCCESS 行 totalAmount，`successRate`。
- `netAmount` = `payment.successAmount - refund.successAmount`。
- `trend`：按 `granularity` 补零的连续序列，每桶含支付/退款各 4 字段。

### 6.2 `StatusDistributionResponse`
```java
record StatusDistributionResponse(
    List<PaymentStatusBucket> paymentStatuses,   // 5 个 PaymentStatus 全列（补零）
    List<RefundStatusBucket> refundStatuses,     // 6 个 RefundStatus 全列（补零）
    Backlog refundBacklog                        // PENDING 笔数+金额
) {
    record PaymentStatusBucket(Integer status, String statusName, long count, long amount) {}
    record RefundStatusBucket(Integer status, String statusName, long count, long amount) {}
    record Backlog(long pendingCount, long pendingAmount) {}
}
```
- 全局快照（无时间窗）：`countByStatus` 不过滤时间。
- 枚举顺序固定（`PaymentStatus.values()` / `RefundStatus.values()`），缺失状态补零，前端坐标轴稳定。

### 6.3 `GatewayHealthResponse`
```java
record GatewayHealthResponse(List<InterfaceHealth> interfaces) {
    record InterfaceHealth(
        String bankCode, String bankInterface,
        long totalCount, long successCount, BigDecimal successRate, BigDecimal avgExecutionTimeMs,
        List<ReturnCodeCount> returnCodes
    ) {}
    record ReturnCodeCount(String returnCode, long count) {}
}
```
- `avgExecutionTimeMs` = `AVG(execution_time)`，BigDecimal 2 位小数。
- `successCount` = `SUM(CASE WHEN success THEN 1 ELSE 0 END)`。
- returnCodes 按 bankInterface 归组到对应接口。

### 6.4 `OperationsAuditResponse`
```java
record OperationsAuditResponse(
    long totalAudits, long approvedCount, long rejectedCount, BigDecimal approvalRate,
    BigDecimal avgAuditDurationMinutes,
    List<AuditorBreakdown> byAuditor
) {
    record AuditorBreakdown(
        Long auditorId, String auditorName,
        long count, long approvedCount, long rejectedCount, BigDecimal approvalRate
    ) {}
}
```
- **数据源拆分**（issue 写「数据源 OperationLog」，但平均审核时长缺退款单创建时间）：
  - 笔数/通过率/按审核人 ← `OperationLog`（记 operatorId/Name + operation + result + createdAt）。
  - 平均审核时长 ← `RefundOrder`（`audited_at - created_at`，`audit_type=MANUAL AND audited_at BETWEEN from AND to`）。
- `byAuditor` 不含人均时长（避免跨聚合归属歧义）；auditorId/Name 取自 OperationLog.operatorId/Name（与 RefundOrder.auditorId 同源——`RefundAppService.auditRefund` 同时写两处）。

## 7. StatsAppService 装配逻辑（pitest 主料）

纯逻辑、无 IO，可测：
- **比率**：`rate(n, d) = d==0 ? ZERO(4dp) : n/d.setScale(4, HALF_UP)`，复用。
- **补零桶序列**：`generateBuckets(from, to, granularity)` → 按 DAY（`from.toLocalDate().atStartOfDay()`，+1 day）或 HOUR（`from` 截到整点，+1 hour）枚举至 `> to`；**上限 400 桶**（防御，超出抛业务异常 `STATS_RANGE_TOO_LARGE`）。SQL 返回的稀疏桶按 `bucketStart` 覆盖到全序列，缺失填 0。
- **状态补零**：按枚举值枚举，缺失填 0 桶。
- **returnCode 归组**：按 bankInterface 分桶 returnCode 行。
- **byAuditor 透视**：按 auditorId 聚合 operation 行为 approved/rejected。
- **netAmount**：支付成功金额 − 退款成功金额。

## 8. 错误定义

`PaymentMessage` 增（沿用既有 `PAY_xxx` 编码，统计校验占 `PAY_07x` 段）：
- `STATS_RANGE_TOO_LARGE(400, "PAY_070", "统计区间过大，请缩小时间范围或粒度")`
- `STATS_INVALID_RANGE(400, "PAY_071", "统计时间区间无效（from 须 ≤ to）")`

## 9. 测试（沿用 spec Testing Decisions）

- **主缝 `StatsAppServiceTest`**（Mockito，`@ExtendWith(MockitoExtension.class)`）：mock `StatsQueryRepository`，断言装配。覆盖：
  - overview 正常（含 netAmount、successRate）、空窗口全零、缺桶补零、除零（count=0→rate=0）、granularity=HOUR。
  - distribution 全状态补零、PENDING backlog 提取、空表全零。
  - gateway returnCode 归组、空接口。
  - audit byAuditor 透视、approvalRate、avgDurationMinutes、空。
  - bucket 上限超限抛 `STATS_RANGE_TOO_LARGE`、from>to 抛 `STATS_INVALID_RANGE`。
  - 用 `ArgumentCaptor` 验传给仓储的 from/to/granularity（变异友好）。
- **pitest**：pom.xml `targetClasses` 加 `com.aieducenter.payment.application.StatsAppService`（threshold 70%）。
- **不引入** `@DataJpaTest`、不引入控制器测试缝（薄委托，签名兜底沿用既有 `SignatureVerificationIntegrationTest`）。
- `ArchitectureTest` 自动守护新代码分层（应用→基础设施依赖、命名、禁字段注入等）。

## 10. pom.xml 变更

- 加依赖 `com.cartisan:cartisan-data-query:0.1.0-SNAPSHOT`（jOOQ 版本由 BOM 管）。
- pitest `targetClasses` 增 `com.aieducenter.payment.application.StatsAppService`。

## 11. 性能与索引

- 窗口查询走 `created_at` 索引范围扫描 + 桶聚合；分组字段（`status`/`bank_interface`/`operator_id`/`auditor_id`）均已有索引（V7/V9/V10/V6）。
- status-distribution 是全局快照、按设计无时间窗，必然聚合全部存活行（`idx_payment_orders_status` / `idx_refund_orders_status` 辅助分组）——这是快照本质，AC「不全表扫描」针对窗口/趋势查询。
- **预计无需新增迁移**；若 EXPLAIN 发现某查询缺索引，再补 V11。

## 12. 验收标准对照

| AC | 满足方式 |
|---|---|
| 4 端点经签名、返回结构化聚合、银行无关 | `@RequireSignature` + 4 响应 DTO；SQL 不出现 ICBC 硬编码 |
| 聚合走索引/分桶、不全表扫描；趋势按日/小时分桶 | jOOQ `date_trunc('day'/'hour')` GROUP BY + created_at 索引范围 |
| AppService Mockito 缝测试覆盖聚合组装，pitest 过 | `StatsAppServiceTest` mock `StatsQueryRepository` + pitest targetClasses 含 StatsAppService |
