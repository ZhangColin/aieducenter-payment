# HSB Payment Field Additions Design

## Overview

补充建行支付下单和响应中缺失的字段：页面返回URL（请求侧）和收银台URL/支付URL（响应侧）。

## Requirements

### 1. 下单请求 — 新增页面返回URL

建行字段名：`Pgfc_Ret_Url_Adr`，用户支付完成后页面跳转地址。非必填，部分场景使用。

- `CreateHsbPaymentCommand` 新增 `pageReturnUrl` 字段
- `HsbPaymentOrder` 新增 `pageReturnUrl` 字段，对应数据库列 `page_return_url VARCHAR(512)`
- `HsbPaymentAppService.createPayment()` 透传该字段到聚合根和网关
- `HsbPaymentGatewayAdapter.createPayment()` 在请求 JSON 中加入 `Pgfc_Ret_Url_Adr`（仅当非空时）
- `HsbPaymentOrderResponse` 返回该字段

### 2. 响应字段重命名 — pay_url → cshdk_url

现有 `pay_url` 列实际存储的是建行 `Cshdk_Url`（收银台URL），需修正命名为 `cshdk_url`。

- 数据库：`pay_url` 重命名为 `cshdk_url`
- `HsbPaymentOrder.java`：字段 `payUrl` → `cshdkUrl`，列名 `pay_url` → `cshdk_url`
- `CreateHsbPaymentResponse.java`：字段 `payUrl` → `cshdkUrl`
- `HsbPaymentOrderResponse.java`：字段 `payUrl` → `cshdkUrl`
- `HsbPaymentOrderMapper.java`：映射调整
- `HsbPaymentAppService`：`setPaymentResult()` 调用适配

### 3. 响应字段新增 — Pay_Url（支付URL）

建行响应中的 `Pay_Url` 字段，部分支付方式会使用。目前未提取，需新增。

- `HsbPaymentOrder` 新增 `payUrl` 字段，对应数据库列 `pay_url VARCHAR(512)`
- `CreateHsbPaymentResponse` 新增 `payUrl` 字段
- `HsbPaymentGatewayAdapter.createPayment()` 从响应中提取 `Pay_Url`
- `HsbPaymentOrderResponse` 返回该字段
- `HsbPaymentOrderMapper` 映射新字段

### 4. 数据库迁移

Flyway 脚本 `V4__add_payment_url_fields.sql`：

```sql
-- 重命名现有 pay_url 为 cshdk_url（实际存储的是收银台URL）
ALTER TABLE hsb_payment_orders RENAME COLUMN pay_url TO cshdk_url;

-- 新增 pay_url 列（存储建行 Pay_Url）
ALTER TABLE hsb_payment_orders ADD COLUMN pay_url VARCHAR(512);

-- 新增页面返回URL列
ALTER TABLE hsb_payment_orders ADD COLUMN page_return_url VARCHAR(512);
```

## Impact on setPaymentResult()

现有签名：`setPaymentResult(String payUrl, String payQrCode, String primOrderNo)`
新签名：`setPaymentResult(String cshdkUrl, String payUrl, String payQrCode, String primOrderNo)`

## Affected Files

| File | Change |
|------|--------|
| `HsbPaymentOrder.java` | 重命名 payUrl→cshdkUrl, 新增 payUrl, 新增 pageReturnUrl |
| `CreateHsbPaymentCommand.java` | 新增 pageReturnUrl |
| `HsbPaymentOrderResponse.java` | 重命名 payUrl→cshdkUrl, 新增 payUrl, 新增 pageReturnUrl |
| `CreateHsbPaymentResponse.java` | 重命名 payUrl→cshdkUrl, 新增 payUrl |
| `HsbPaymentOrderMapper.java` | 映射调整 |
| `HsbPaymentAppService.java` | 透传 pageReturnUrl, 适配 setPaymentResult |
| `HsbPaymentGatewayAdapter.java` | 发送 Pgfc_Ret_Url_Adr, 提取 Pay_Url, 适配新字段 |
| `V4__add_payment_url_fields.sql` | 数据库迁移 |
