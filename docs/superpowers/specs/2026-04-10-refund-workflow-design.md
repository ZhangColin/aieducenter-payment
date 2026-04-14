# 退款流程完善设计

> 日期：2026-04-10
> 状态：已审批

## 背景

当前退款订单审核通过后，状态变为 APPROVED 但没有向工行发起退款请求。需要完善整个退款流程，包括：审核后发起退款、定时任务查询退款状态、业务系统查询退款订单时自动同步工行状态。

## 状态流转

```
PENDING → (审核拒绝) → REJECTED（终态）
PENDING → (审核通过) → 调工行退货 → REFUNDING（保存 bankRefundNo）→ 等2秒 → 调工行退货查询
  → 成功 → SUCCESS（终态，回调业务系统）
  → 失败 → FAILED（终态，回调业务系统）
  → 退款中 → REFUNDING（不变，等定时任务或业务查询）
```

审核通过后如果调工行退货失败（网络异常等），状态保持 APPROVED，定时任务兜底重试。

## 核心场景

### 场景一：审核通过后发起退款

`RefundAppService.auditRefund()` 审核通过后：

1. **事务1**：审核状态更新 → PENDING → APPROVED，保存
2. **事务2**（独立事务，银行调用不持有数据库连接）：
   a. 查找原支付订单获取 `bankOrderNo`
   b. 调 `PaymentGatewayPort.createRefund()` 向工行发起退货
   c. 记录退款请求日志（PaymentLog，logType = REFUND_REQUEST）
   d. 成功 → `refundOrder.startRefund(bankRefundNo)`（保存银行流水号），保存
   e. 等 2 秒（`Thread.sleep(2000)`）
   f. 调 `PaymentGatewayPort.queryRefund()` 查询退货状态
   g. 记录退款查询日志（PaymentLog，logType = REFUND_QUERY）
   h. 根据查询结果更新状态（SUCCESS / FAILED / 保持 REFUNDING）
   i. 如果达到终态，通过 `BusinessSystemNotifier` 回调业务系统

失败（工行调用异常）→ 状态保持 APPROVED，记录日志，不回调。定时任务后续兜底重试。

**事务拆分说明**：银行调用 + 2秒等待必须在事务之外执行，避免长时间持有数据库连接（预计耗时 3-5 秒）。

### 场景二：定时任务查询退款状态

`RefundQueryScheduler` 每 5 分钟执行：

1. 分页查询 APPROVED 状态的订单（每次最多处理 100 条） — 走场景一的退款+查询+回调逻辑
2. 分页查询 REFUNDING 状态的订单（每次最多处理 100 条） — 调工行退货查询，更新状态，终态则回调

**并发保护**：当前服务为单实例部署，不需要分布式锁。如果未来多实例部署，需要引入 ShedLock 或数据库锁。

### 场景三：业务系统查询退款订单

`RefundAppService.queryRefund()` 根据状态分支：

| 状态 | 处理 | 回调 | 预计耗时 |
|------|------|------|----------|
| REJECTED / SUCCESS / FAILED | 直接返回 | 不回调 | <100ms |
| APPROVED | 调工行退货 → 等2秒 → 查询 → 更新 → 返回 | 不回调 | 3-5秒 |
| REFUNDING | 调工行退货查询 → 更新 → 返回 | 不回调 | 1-2秒 |

## 数据模型变更

### RefundOrder 变更

| 变更项 | 类型 | 说明 |
|--------|------|------|
| notifyUrl | 新增字段 (varchar(512)) | 退款结果回调地址 |
| startRefund(String bankRefundNo) | 方法签名修改 | 从无参改为接受银行退款流水号 |

### Flyway 迁移脚本

新建 `V2__add_notify_url_to_refund_orders.sql`（不修改已有脚本），为 `pay_refund_orders` 表添加 `notify_url` 列。

### 退款回调通知格式（RefundNotifyRequest）

```json
{
  "refundOrderNo": "REF20260410143000000123",
  "businessOrderNo": "BIZ001",
  "paymentOrderNo": "PAY20260410140000000456",
  "status": "SUCCESS",
  "refundAmount": "1000",
  "bankRefundNo": "020001030558...",
  "refundTime": "2026-04-10 14:30:05",
  "attach": "原始附加数据"
}
```

## 代码变更清单

### 基础设施层

**IcbcPaymentGatewayAdapter.queryRefund()** — 完整重写（当前为 UnsupportedOperationException 占位）
- 使用 `CardbusinessAggregatepayB2cOnlineRefundqryRequestV1` SDK 类
- 请求参数：`mer_id`、`out_trade_no`（支付订单号）、`outtrx_serial_no`（退款订单号）、`order_id`（工行订单号）、`mer_prtcl_no`
- 根据 `pay_status` 映射：0→SUCCESS, 1→FAILED, 2→REFUNDING
- 返回 `QueryRefundResponse`：包含状态、退款金额、实退金额、工行流水号、退款时间

**PaymentGatewayPort.queryRefund()** — 修改接口签名
- 当前签名：`queryRefund(String refundOrderNo, String bankRefundNo)`
- 新签名：`queryRefund(String refundOrderNo, String paymentOrderNo, String bankOrderNo, String bankRefundNo)`
- 工行退货查询需要原支付订单号和工行订单号，当前参数不足以构建完整请求

### 领域层

**RefundOrder**
- 新增 `notifyUrl` 字段（JPA `@Column(name = "notify_url", length = 512)`）
- 构造函数新增 `notifyUrl` 参数（第9个参数，破坏性变更，需同步修改 `RefundAppService.createRefund()` 调用点）
- 修改 `startRefund()` 签名：从无参改为 `startRefund(String bankRefundNo)`，在 APPROVED→REFUNDING 转换时保存银行退款流水号

**RefundOrderRepository**
- 新增 `findByStatusIn(List<RefundStatus> statuses, Pageable pageable)` 方法（带分页，每次最多 100 条）

### 应用层

**RefundAppService** — 新增依赖
- `PaymentGatewayPort paymentGatewayPort`（新增）
- `PaymentLogRepository paymentLogRepository`（新增）
- `BusinessSystemNotifier businessSystemNotifier`（新增）

**RefundAppService 方法变更**
- 修改 `createRefund()`：传入 `notifyUrl` 到 RefundOrder 构造函数
- 修改 `auditRefund()`：审核通过后拆分为两个事务：
  - 事务1（`@Transactional`）：审核状态更新
  - 事务2（独立方法，无 `@Transactional`）：调工行退货 + 2秒等待 + 查询 + 更新状态 + 回调
- 修改 `getRefund()` → 重命名为 `queryRefund()`：
  - APPROVED：发起退款 + 查询 + 更新（不回调）
  - REFUNDING：查询 + 更新（不回调）
  - 其他：直接返回
- 新增私有方法：
  - `executeRefundAndQuery(RefundOrder, PaymentOrder)` — 调工行退货 + 2秒等待 + 查询 + 更新状态
  - `queryAndUpdateRefundStatus(RefundOrder, PaymentOrder)` — 查询工行退货状态，更新订单
  - `notifyRefundResult(RefundOrder)` — 构造 RefundNotifyRequest 并回调业务系统

**BusinessSystemNotifier**
- 新增重载方法 `notify(String notifyUrl, RefundNotifyRequest request)` 接受退款通知 DTO

**RefundQueryScheduler**（新建）
- 参考 `PaymentExpirationScheduler` 模式
- `@Scheduled(cron = "${scheduler.refund-query:0 */5 * * * *}")`
- 扫描 APPROVED + REFUNDING 状态的退款订单（带分页限制）
- APPROVED → 走退款+查询+回调逻辑
- REFUNDING → 走查询+回调逻辑
- 每个订单独立 try-catch，避免单笔失败影响整批

**RefundNotifyRequest**（新建 DTO）
- 退款回调通知 record，包含：refundOrderNo、businessOrderNo、paymentOrderNo、status、refundAmount、bankRefundNo、refundTime、attach

### 接口层

**CreateRefundCommand**
- 新增 `notifyUrl` 字段（`@Size(max = 512)`）

**RefundOrderResponse**
- 新增 `notifyUrl` 字段
- 新增 `failedAt` 字段（已有但未在 Response 中返回）

**RefundApiV1Controller**
- `getRefund` 端点改为调用 `queryRefund()`

### 数据库

**新建 Flyway 迁移脚本 `V2__add_notify_url_to_refund_orders.sql`**
- `ALTER TABLE pay_refund_orders ADD COLUMN notify_url VARCHAR(512)`

## 工行退货查询接口参数

### 请求

| 参数 | 必输 | 说明 |
|------|------|------|
| mer_id | 是 | 商户编号 |
| out_trade_no | 否 | 商户消费订单号（paymentOrderNo） |
| order_id | 否 | 工行消费订单号（二者必输其一）|
| outtrx_serial_no | 是 | 退款流水号（refundOrderNo）|
| mer_prtcl_no | 否 | 协议编号 |

### 响应关键字段

| 参数 | 说明 |
|------|------|
| return_code | 0=成功 |
| pay_status | 0=退货成功, 1=退货失败, 2=状态未知 |
| intrx_serial_no | 工行退货流水号 |
| reject_amt | 退款总金额（分）|
| real_reject_amt | 实退金额（分）|
| refund_time | 退货时间 |

## 等待策略

审核通过发起退款后固定等待 2 秒再查询。如果此时退款仍未完成，状态保持 REFUNDING，由定时任务后续轮询。
