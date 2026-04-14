# 待办

## 支付响应 DTO 按场景裁剪

当前创建订单、查询状态、异步回调三个接口返回的 `PaymentOrderResponse` 结构完全相同，但各场景侧重不同：

- **创建订单**：侧重 `paymentOrderNo` + `qrCodeUrl`
- **查询状态 / 异步回调**：侧重 `status` / `paidAt` / `bankOrderNo` 等状态信息，`qrCodeUrl` 不需要

后续应按场景拆分响应 DTO，去掉查询和回调中不需要的字段。
