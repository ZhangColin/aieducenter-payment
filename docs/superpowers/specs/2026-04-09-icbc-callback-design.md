# ICBC Payment Callback Design

## Overview

Two-layer callback mechanism:

1. **ICBC -> Payment Service**: ICBC sends payment result to our callback endpoint
2. **Payment Service -> Business System**: We notify the business system of the payment result

## 1. ICBC Callback Endpoint

### Endpoint

`POST /api/v1/payment/callbacks/icbc`

Already excluded from API signature verification in `WebConfig`.

### Notify URL Configuration

Configured in `application.yml` per environment:

```yaml
gateway:
  icbc:
    notify-base-url: https://api.example.com
```

The full notify URL sent to ICBC as `mer_url`: `{notify-base-url}/api/v1/payment/callbacks/icbc`

**Implementation change**: `IcbcPaymentGatewayAdapter` currently uses `paymentOrder.getNotifyUrl()` for `mer_url`. This must change to use `IcbcConfig.notifyBaseUrl` + fixed path. The `paymentOrder.notifyUrl` field is reserved for the business system callback target.

Add `notifyBaseUrl` field to `IcbcConfig` class and add corresponding yml configuration.

### result_type Configuration

**Must change** `result_type` from `"1"` to `"0"` in `IcbcPaymentGatewayAdapter` (currently line 61).

This ensures ICBC sends callbacks for both payment success AND failure, allowing us to update order status in all cases without relying on active queries.

### Callback Handling Flow

1. Receive POST request (`Content-Type: application/x-www-form-urlencoded`)
2. Extract parameters: `api`, `app_id`, `biz_content`, `charset`, `format`, `from`, `sign_type`, `timestamp`, `sign`
3. **Verify signature** using ICBC gateway public key:
   - Sign type is always `RSA` for callbacks (not RSA2)
   - Signature plaintext = request path + "?" + parameters sorted by ASCII ascending (excluding `sign`)
4. Parse `biz_content` JSON, extract key fields
5. Find `PaymentOrder` by `out_trade_no`
6. Update order status and details
7. Write `PaymentLog` with full callback content
8. Notify business system
9. Return ICBC-required response format

### ICBC Callback Scenarios

With `result_type=0` (to be configured in our order creation):
- **Payment success**: `return_code=0` -> update order to PAID
- **Payment failure**: `return_code` non-zero -> update order to FAILED

Note: `return_code` (communication layer) and `returnCode` (interface layer) are two separate fields in the callback. We check `return_code` as the primary success indicator: `return_code=0` means the transaction was communicated successfully.

ICBC will attempt notification up to 3 times total (1 initial + 2 retries) within 10 minutes if we don't return a valid response.

### Data Recording (PaymentOrder update)

| Field | Source (ICBC callback biz_content) |
|---|---|
| status | PAID if return_code=0, else FAILED |
| bankOrderNo | order_id |
| thirdPartyOrderNo | third_trade_no |
| paidAt / failedAt | Current timestamp |
| actualAmount | payment_amt (actual paid amount in fen) |

**Note**: `actualAmount` field needs to be added to `PaymentOrder` entity and `markAsPaid()` method signature.

Additional fields stored in payment log or extension fields: pay_type, access_type, card_no, card_flag, decr_flag, total_amt, total_disc_amt, attach, pay_time, promotion_detail, etc.

### Response to ICBC

```json
{
  "response_biz_content":{"return_code":0,"return_msg":"success","returnCode":"0","msg_id":"unique-msg-id"},
  "sign_type":"RSA2",
  "sign":"signature-using-merchant-private-key"
}
```

Key requirements:
- `return_code`, `return_msg`, `returnCode`, and `msg_id` are all required fields in the response
- `msg_id` must be unique per response, generated each time (not reused)
- Sign string is the exact content: `"response_biz_content":{"return_code":0,"return_msg":"success","returnCode":"0","msg_id":"xxx"},"sign_type":"RSA2"` (no spaces or newlines)
- Field order must be: response_biz_content, sign_type, sign
- sign_type matches the type registered with ICBC (RSA2)
- Use merchant private key with RSA2 algorithm to sign

## 2. Business System Notification

After processing ICBC callback, we POST JSON to the business system's `notifyUrl` (provided when they created the payment order).

### Request

```
POST {notifyUrl}
Content-Type: application/json
```

### Request Body

```json
{
  "orderId": "payment-order-uuid",
  "outTradeNo": "business-system-order-no",
  "status": "PAID",
  "amount": "100",
  "actualAmount": "100",
  "bankOrderNo": "icbc-order-id",
  "payTime": "2024-01-12 12:12:12",
  "payType": "9",
  "attach": "business-attached-data"
}
```

| Field | Type | Description |
|---|---|---|
| orderId | String | Our payment order ID |
| outTradeNo | String | Business system's order number |
| status | String | PAID or FAILED |
| amount | String | Order total amount (fen) |
| actualAmount | String | Actual paid amount (fen) |
| bankOrderNo | String | ICBC order ID |
| payTime | String | Payment completion time (yyyy-MM-dd HH:mm:ss) |
| payType | String | Payment method (9=WeChat, 10=Alipay, etc.) |
| attach | String | Business system's attached data, passed through |

### Business System Response

- HTTP 200 = success
- No retry on failure. Business systems can query order status via the query API.

## 3. Implementation Notes

### Changes to Existing Code

1. **`IcbcConfig`**: Add `notifyBaseUrl` field with `@Value` or `@ConfigurationProperties` binding
2. **`IcbcPaymentGatewayAdapter`**:
   - Change `mer_url` from `paymentOrder.getNotifyUrl()` to `icbcConfig.getNotifyBaseUrl() + "/api/v1/payment/callbacks/icbc"`
   - Change `result_type` from `"1"` to `"0"`
3. **`PaymentOrder` entity**: Add `actualAmount` field, update `markAsPaid()` to accept it
4. **`application.yml`**: Add `gateway.icbc.notify-base-url` property per environment

### Signature Verification for ICBC Callback

ICBC callback uses `RSA` sign_type (not RSA2). Must use ICBC gateway public key (`IcbcConfig.publicKey`).

The key difference from outbound signing:
- **Callback verification**: RSA (RSAWITHSHA1) + ICBC gateway public key
- **Response signing**: RSA2 (RSAWITHSHA256) + merchant private key

Signature plaintext construction:
```
/api/v1/payment/callbacks/icbc?api=/api/cardbusiness/qrcode/consumption/V1&app_id=xxx&biz_content={...}&charset=utf-8&format=json&from=icbc-api&sign_type=RSA&timestamp=2017-01-12 17:33:56
```

Parameters sorted by ASCII, joined with `&`, preceded by request path + `?`.

### Response Signing to ICBC

Sign the response using our merchant private key with RSA2 algorithm:
- Input: the exact JSON string of `"response_biz_content":{"return_code":0,"return_msg":"success","returnCode":"0","msg_id":"xxx"},"sign_type":"RSA2"` (no spaces/newlines)
- Algorithm: RSA2 (RSAWITHSHA256)
- Output: Base64 encoded signature
- **Critical**: JSON serialization must NOT add any formatting (no spaces, no newlines). Use explicit compact serialization.

### pay_time Format Conversion

ICBC sends `pay_time` in `yyyyMMddHHmmss` format (e.g., `20190112121212`). Business system notification uses `yyyy-MM-dd HH:mm:ss` format. Conversion is needed.
