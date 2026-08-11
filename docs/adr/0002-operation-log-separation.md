# OperationLog 与 PaymentLog 职责分离；订单生命周期以读模型合并

## 背景

服务需同时留痕两类本质不同的事件：① 本服务与银行网关的每次交互（请求/响应/返回码/耗时）——机器↔机器；② 行为者对订单的操作（审核、通知重发等）——人/系统↔本服务。曾考虑合并进单张 `PaymentLog` 表以"一眼看全订单生命周期"，但二者字段结构、查询模式、保留与合规要求差异大。

## 决策

**不合表，合视图。**

- `PaymentLog`（表 `pay_payment_logs`）保持单一职责：只记网关交互（`logType` ∈ PAYMENT_REQUEST / PAYMENT_QUERY / PAYMENT_CANCEL / REFUND_REQUEST / REFUND_QUERY / PAYMENT_CALLBACK）。
- 新增 `OperationLog`（表 `pay_operation_logs`）：记行为者操作，字段 `targetType` / `targetNo` / `operation` / `operatorId` / `operatorName` / `operatorSystem` / `result` / `remark` / `createdAt`。
- **订单生命周期**是一个**读模型**（`OrderLifecycleAppService`）：按 `orderNo` 把两表记录 union 后按时间排序返回。

## 理由

合表会让网关字段（`returnCode` / `executionTime` / `bankInterface`）对人工操作恒为 null，`logType` 跨"网关调用类型"与"操作类型"两个维度而语义崩坏，且两者保留期/合规要求不同。两表分离保住各自聚合的单一职责，读模型仍满足"完整生命周期"的查看需求。

## 后果

- 生命周期查询读两表后合并（应用层 union 或 DB 视图），非单表扫描。
- 统计端点按数据源天然分流：网关健康度走 PaymentLog，审核/操作活跃度走 OperationLog。
