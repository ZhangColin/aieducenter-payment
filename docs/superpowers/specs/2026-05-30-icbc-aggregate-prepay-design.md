# ICBC 聚合支付预支付接口设计

## 背景

现有支付服务对接工行二维码消费接口（`qrcode/consumption/V1`），生成二维码链接供用户扫码支付。
现需新增工行"线上POS聚合消费下单接口（无界面）"（`aggregatepay/b2c/online/consumepurchase/V1`），
支持微信公众号、小程序、APP 内支付，以及支付宝、云闪付等聚合支付场景。

## 核心差异分析

### 现有二维码支付 vs 新增预支付

| 维度 | 二维码支付 | 预支付 |
|------|-----------|--------|
| 工行下单API | `qrcode/consumption/V1` | `aggregatepay/b2c/online/consumepurchase/V1` |
| 工行查询API | 相同 (`aggregatepay/b2c/online/orderqry/V1`) | 相同 |
| 工行退款API | 相同 (`aggregatepay/b2c/online/merrefund/V1`) | 相同 |
| 回调机制 | 相同 | 相同 |
| 入参差异 | 仅基础订单信息 | 额外需要 payMode, accessType, openId, shopAppid |
| 出参差异 | 返回 qrCodeUrl | 返回 wx/zfb/union_data_package |
| 前端处理 | 展示二维码图片 | 用数据包调起支付SDK |

### 设计原则

1. **对外 API 分开**：二维码支付和预支付的入参/出参完全不同，分开更清晰
2. **内部代码复用**：共享 PaymentOrder 实体、回调处理、查询、退款
3. **对齐工行接口**：工行只有下单不同，查询/退款/回调完全共享，我们也对应

## API 接口设计

### 新增：预支付接口

```
POST /api/v1/payments/prepay
```

**请求体 `CreatePrepayCommand`：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| businessOrderNo | String | 是 | 业务订单号，最大64字符 |
| amount | Long | 是 | 支付金额（分） |
| subject | String | 是 | 支付标题 |
| body | String | 否 | 支付描述 |
| businessName | String | 否 | 业务名称 |
| notifyUrl | String | 是 | 异步通知地址 |
| expiredSeconds | Long | 否 | 过期时长（秒），默认3600 |
| attach | String | 否 | 附加数据，原样返回 |
| payMode | Integer | 是 | 支付方式：9=微信, 10=支付宝, 13=云闪付 |
| accessType | Integer | 是 | 接入方式：5=APP, 7=公众号, 8=生活号, 9=小程序 |
| openId | String | 条件必填 | 微信用户标识（payMode=9 且 accessType=7或9时必填） |
| unionId | String | 条件必填 | 支付宝用户标识（payMode=10 且 accessType=8时必填） |

**响应体 `PrepayOrderResponse`：**

| 字段 | 类型 | 说明 |
|------|------|------|
| id | Long | 订单ID |
| paymentOrderNo | String | 支付订单号 |
| status | Integer | 状态码 |
| statusName | String | 状态名称 |
| amount | Long | 金额（分） |
| prepayDataPackage | String | 支付参数包JSON（前端用于调起支付） |
| payMode | Integer | 支付方式码：9=微信, 10=支付宝, 13=云闪付 |
| payModeName | String | 支付方式描述 |
| accessType | Integer | 接入方式码：5=APP, 7=公众号, 8=生活号, 9=小程序 |
| accessTypeName | String | 接入方式描述 |
| expiredAt | LocalDateTime | 过期时间 |
| createdAt | LocalDateTime | 创建时间 |

### 复用接口（不变）

- `GET /api/v1/payments/{paymentOrderNo}` — 查询订单
- `POST /api/v1/payments/{paymentOrderNo}/query` — 主动查询银行状态
- `POST /api/v1/refunds` — 退款
- `POST /api/v1/payment/callbacks/icbc` — 回调

## 数据库设计

### 迁移脚本 V5

```sql
-- 新增聚合支付字段
ALTER TABLE pay_payment_orders ADD COLUMN pay_mode INTEGER;
ALTER TABLE pay_payment_orders ADD COLUMN access_type INTEGER;
ALTER TABLE pay_payment_orders ADD COLUMN shop_appid VARCHAR(32);
ALTER TABLE pay_payment_orders ADD COLUMN open_id VARCHAR(128);
ALTER TABLE pay_payment_orders ADD COLUMN prepay_data_package TEXT;
ALTER TABLE pay_payment_orders ADD COLUMN trade_type VARCHAR(16);

-- 索引
CREATE INDEX idx_payment_orders_pay_mode ON pay_payment_orders(pay_mode) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_access_type ON pay_payment_orders(access_type) WHERE deleted = FALSE;
```

### 字段说明

| 列 | 类型 | 说明 |
|---|---|---|
| pay_mode | INTEGER | 支付方式：NULL=未确定, 9=微信, 10=支付宝, 13=云闪付。二维码订单创建时为NULL，回调时从 pay_type 回填。预支付订单创建时设置。 |
| access_type | INTEGER | 接入方式：4=H5(二维码), 5=APP, 7=公众号, 8=生活号, 9=小程序。对齐工行 access_type 概念。 |
| shop_appid | VARCHAR(32) | 商户在微信/支付宝开放平台注册的APPID |
| open_id | VARCHAR(128) | 用户在微信/支付宝的唯一标识 |
| prepay_data_package | TEXT | 工行返回的支付参数包JSON（wx_data_package/zfb_data_package/union_data_package） |
| trade_type | VARCHAR(16) | 交易类型：JSAPI（公众号/小程序）、APP 等。工行回调时返回。 |

### 现有字段复用

- `qr_code_url` — 二维码支付专用，预支付为 NULL
- `amount` — 通用
- `notify_url` — 通用
- `client_ip` — 映射到工行的 spbill_create_ip
- `bank_order_no`, `third_party_order_no`, `actual_amount` — 通用

### 订单类型区分

通过字段组合区分：
- 二维码订单：`access_type = 4`（H5），`qr_code_url` 有值，`prepay_data_package` 为 NULL
- 预支付订单：`access_type = 5/7/8/9`，`prepay_data_package` 有值，`qr_code_url` 为 NULL

## 代码架构变更

### Domain 层

#### 新增枚举

**PayMode：**
```java
public enum PayMode implements BaseEnum<Integer> {
    WECHAT(9, "微信"),
    ALIPAY(10, "支付宝"),
    UNIONPAY(13, "云闪付");
}
```

**AccessType：**
```java
public enum AccessType implements BaseEnum<Integer> {
    H5(4, "H5"),
    APP(5, "APP"),
    WECHAT_OA(7, "微信公众号"),
    ALIPAY_LIFE(8, "支付宝生活号"),
    MINI_PROGRAM(9, "小程序");
}
```

#### PaymentOrder 实体扩展

新增字段及对应 setter：
- `payMode` (PayMode)
- `accessType` (AccessType)
- `shopAppid` (String)
- `openId` (String)
- `prepayDataPackage` (String)
- `tradeType` (String)

现有构造函数增加 `accessType` 参数（二维码订单传 `AccessType.H5`），或通过 setter 设置。

### Domain Port 层

#### PaymentGatewayPort 新增方法

```java
CreatePrepayResponse createPrepay(PaymentOrder order);
```

#### 新增 CreatePrepayResponse

```java
public record CreatePrepayResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    String bankOrderNo,
    String dataPackage,
    String tradeType,
    Long executionTime,
    String requestParams,
    String responseBody
) {}
```

### Infrastructure 层

#### IcbcConfig 新增配置

```yaml
gateway:
  icbc:
    prepay-url: https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/consumepurchase/V1
    shop-appid: wx8888888888888888
```

#### IcbcPaymentGatewayAdapter 新增 createPrepay()

使用 ICBC SDK 的 `CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1`：

```java
@Override
public CreatePrepayResponse createPrepay(PaymentOrder paymentOrder) {
    // 1. 创建 ICBC SDK 请求对象
    // 2. 设置 biz_content 参数：
    //    - mer_id, mer_prtcl_no, icbc_appid (从配置)
    //    - out_trade_no = paymentOrder.getPaymentOrderNo()
    //    - pay_mode = paymentOrder.getPayMode().getCode()
    //    - access_type = paymentOrder.getAccessType().getCode()
    //    - shop_appid = icbcConfig.getShopAppid() (从配置)
    //    - open_id = paymentOrder.getOpenId()
    //    - total_fee = paymentOrder.getAmount().toString()
    //    - fee_type = "001"
    //    - spbill_create_ip = paymentOrder.getClientIp()
    //    - body = paymentOrder.getBody() 或 paymentOrder.getSubject()
    //    - mer_url = 回调地址
    //    - notify_type = "HS", result_type = "0"
    //    - orig_date_time = 当前时间
    //    - decive_info = 生成的设备标识
    //    - expire_time = 过期秒数
    // 3. 调用 client.execute()
    // 4. 解析响应，提取 data_package
    //    - pay_mode=9 → wx_data_package
    //    - pay_mode=10 → zfb_data_package
    //    - pay_mode=13 → union_data_package
    // 5. 返回 CreatePrepayResponse
}
```

#### PaymentCallbackAppService 扩展

回调处理逻辑扩展：
- 新增 `payMode` 和 `accessType` 的回填逻辑
- 从 callback 的 `pay_type` 映射到 `PayMode` 枚举
- 从 callback 的 `access_type` 映射到 `AccessType` 枚举
- 在 `markAsPaid()` 时一并设置

#### IcbcCallbackParam 扩展

确保已包含所有回调字段（现有已基本覆盖，新增 `promotion_detail`、`discount_info` 等可选字段可不存储）。

### Application 层

#### 新增 PrepayAppService

```java
@Service
public class PrepayAppService {
    @Transactional
    public PrepayOrderResponse createPrepay(CreatePrepayCommand command, String businessSystemName, String clientIp) {
        // 1. 创建 PaymentOrder（带 payMode, accessType, openId, shopAppid）
        // 2. 保存订单
        // 3. 调用 paymentGatewayPort.createPrepay()
        // 4. 记录日志
        // 5. 保存 prepayDataPackage 和 bankOrderNo 到订单
        // 6. 返回 PrepayOrderResponse
    }
}
```

### Endpoints 层

#### 新增 PrepayApiV1Controller

```java
@RestController
@RequestMapping("/api/v1/payments")
public class PrepayApiV1Controller {
    @PostMapping("/prepay")
    @RequireSignature
    public ApiResponse<PrepayOrderResponse> createPrepay(
        @Valid @RequestBody CreatePrepayCommand command,
        HttpServletRequest request) {
        // 获取 businessSystemName 和 clientIp
        // 调用 PrepayAppService.createPrepay()
    }
}
```

## 完整支付流程

### 微信公众号/小程序支付流程

```
1. 用户在微信中打开页面
   ↓
2. 前端通过微信 OAuth 获取 open_id
   ↓
3. 前端调用 POST /api/v1/payments/prepay
   参数: payMode=9, accessType=7/9, openId=xxx, ...
   ↓
4. 支付服务创建 PaymentOrder，调用工行聚合支付API
   ↓
5. 工行返回 wx_data_package（含 appid, partnerid, prepayid, sign 等）
   ↓
6. 支付服务保存 prepayDataPackage，返回给前端
   ↓
7. 前端用 wx_data_package 调用 wx.requestPayment() 唤起微信支付
   ↓
8. 用户在微信中完成支付
   ↓
9. 工行回调 POST /api/v1/payment/callbacks/icbc
   ↓
10. 支付服务验签 → 更新订单状态 → 回填 payMode → 通知业务系统
```

### 支付宝生活号/小程序支付流程

与微信类似，区别：
- `payMode=10`, `accessType=8/5`
- 使用 `unionId` 替代 `openId`
- 工行返回 `zfb_data_package`
- 前端调用支付宝 SDK

### 云闪付支付流程

- `payMode=13`, `accessType=5/9`
- 工行返回 `union_data_package`
- 前端调用银联 SDK

## 现有二维码支付的增强

### 回调回填 payMode

在 PaymentCallbackAppService 中，回调处理时从 `pay_type` 映射并设置 `payMode`：
- 二维码订单创建时 `payMode = null`
- 回调时从 `pay_type` 回填

### accessType 自动设置

现有二维码支付创建订单时设置 `accessType = H5(4)`。

## 测试策略

### 单元测试

- `PayMode`、`AccessType` 枚举映射测试
- `IcbcPaymentGatewayAdapter.createPrepay()` mock 测试
- `PrepayAppService.createPrepay()` 业务逻辑测试
- 回调处理新增字段回填测试

### 集成测试

- 调用工行测试环境，验证返回 `wx_data_package` 格式正确
- 模拟工行回调，验证订单状态更新和字段回填

### 端到端测试

完整的微信支付流程需要在微信环境内测试，建议：
1. 提供一个简单的 HTML 测试页面
2. 在微信中打开，可走完整个支付流程
3. 需要配合前端开发进行联调

## 工行 SDK 确认

ICBC SDK (`icbc-api-sdk-cop_v2_20260313`) 已包含所需类：
- `CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1`
- `CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1Biz`
- `CardbusinessAggregatepayB2cOnlineConsumepurchaseResponseV1`

SDK 示例代码位于 `docs/工行/icbc-api-sdk-cop_v2_20260313/example/`。

## 配置变更

```yaml
gateway:
  icbc:
    # 现有配置保持不变
    env: local
    app-id: xxx
    mer-id: xxx
    mer-prtcl-no: xxx
    private-key: xxx
    public-key: xxx
    notify-base-url: xxx
    payment-url: xxx
    payment-query-url: xxx
    refund-url: xxx
    refund-query-url: xxx
    # 新增配置
    prepay-url: https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/consumepurchase/V1
    shop-appid: wx8888888888888888  # 商户微信APPID（工行绑定），支付宝/云闪付时可为空
```
