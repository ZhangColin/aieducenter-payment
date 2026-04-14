# 退款免审与审核拒绝回调设计

## 背景

当前退款流程要求所有退款都必须经过人工审核（PENDING → APPROVED/REJECTED）。业务上存在不需要审核的小额退款场景，同时审核拒绝时业务端无感知。

## 需求

1. 发起退款时增加 `needAudit` 布尔参数，`false` 时自动审核通过并立即发起银行退款
2. 审核不通过时，复用现有回调机制通知业务端

## 方案

### 1. CreateRefundCommand 增加 needAudit 字段

布尔类型，默认 `true`（需要审核），保持向后兼容。

### 2. 免审退款流程

在 `RefundAppService.createRefund()` 中，当 `needAudit = false` 时：

- 正常创建 PENDING 状态的退款单
- 立即调用已有的审核通过逻辑，自动流转至 APPROVED → 调银行 → REFUNDING
- 复用 `processApprovedRefund()` 方法，不引入新状态或新方法

### 3. 审核拒绝回调

在 `RefundAppService.auditRefund()` 中，当拒绝时：

- 调用 `BusinessSystemNotifier` 发送回调通知
- 复用现有通知格式，status 为 `REJECTED`
- 不修改通知机制本身

## 影响范围

| 文件 | 变更内容 |
|------|----------|
| `CreateRefundCommand` | 增加 `needAudit` 布尔字段 |
| `RefundAppService.createRefund()` | `needAudit=false` 时自动审核通过 |
| `RefundAppService.auditRefund()` | 拒绝时增加回调通知 |
| `RefundApiV1Controller` | 透传 `needAudit` 参数 |

不涉及：RefundOrder 领域模型、RefundStatus 状态机、BusinessSystemNotifier、PaymentGatewayPort。

## 设计原则

- 最小改动，复用现有状态机和回调机制
- 不引入新状态、新接口、新枚举
- 保持向后兼容（needAudit 默认 true）
