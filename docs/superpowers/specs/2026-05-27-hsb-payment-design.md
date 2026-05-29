# 建行惠市宝支付接入设计

## 概述

本文档描述建行惠市宝（对公专业结算综合服务平台）支付接入的设计方案。惠市宝与现有工行支付是完全不同的业务模型（多方分账），因此采用**完全独立的限界上下文**，不与工行代码混合。

### 业务场景

适用于接单吧平台的多方分账场景：
1. 接单吧在建行创建市场，用户下单时产生主订单和子订单（定义分账方案）
2. 调用支付服务生成惠市宝支付订单，用户完成支付
3. 支付成功后，可多次确认分账，直到所有子订单分账完成
4. 支持退款（按子订单粒度退款）

### 核心约束

- 金额单位：内部统一使用**分**（Long），调建行时转换为**元**
- 不涉及消费券、附加项等场景（以支付、退款、分账为主）
- 签名使用建行提供的 `mktpaysign.jar`（SHA256WithRSA）
- 参数签名处理参考工行接口模式（`@RequireSignature`）

---

## 架构方案：完全独立的限界上下文

新建 `hsb` 包作为惠市宝专属限界上下文，拥有独立的聚合根、数据库表、API、适配器。

理由：惠市宝的多方分账模型（主订单+子订单）与工行单一订单模型差异太大，独立上下文保持模型清晰。

---

## 数据库设计

### 1. hsb_payment_orders（主支付订单）

| 字段 | 类型 | 说明 | 建行对应 |
|------|------|------|----------|
| id | BIGINT PK | TSID | |
| payment_order_no | VARCHAR(64) UNIQUE NOT NULL | 支付订单号（我们生成） | → Ittparty_Jrnl_No 发起方流水号 |
| business_main_order_no | VARCHAR(64) NOT NULL | 业务系统主订单号 | → Main_Ordr_No |
| business_system_name | VARCHAR(128) NOT NULL | 业务系统名（从 RequestContext 获取） | |
| business_name | VARCHAR(128) | 业务名称 | |
| status | INTEGER NOT NULL | 订单状态 | |
| mkt_id | VARCHAR(32) NOT NULL | 市场编号（配置） | → Mkt_Id |
| payment_method | VARCHAR(8) NOT NULL | 支付方式（01-07） | → Pymd_Cd |
| order_type | VARCHAR(8) NOT NULL | 订单类型（02-05） | → Py_Ordr_Tpcd |
| currency | VARCHAR(8) DEFAULT '156' | 币种 | → Ccy |
| total_amount | BIGINT NOT NULL | 订单总金额（分） | → Ordr_Tamt |
| txn_total_amount | BIGINT NOT NULL | 交易总金额（分） | → Txn_Tamt |
| fee_bearer_id | VARCHAR(32) | 手续费承担方编号 | → Hdcg_Brs_Id |
| expired_seconds | BIGINT DEFAULT 3600 | 订单超时时间（秒） | |
| notify_url | VARCHAR(512) | 业务系统回调 URL | |
| attach | TEXT | 附加数据 | |
| pay_url | VARCHAR(512) | 建行返回的支付链接 | → Cshdk_Url |
| pay_qr_code | VARCHAR(512) | 建行返回的二维码 | → Pay_Qr_Code |
| prim_order_no | VARCHAR(64) | 建行返回的主订单编号 | → Prim_Ordr_No |
| py_trn_no | VARCHAR(64) | 建行支付流水号 | → Py_Trn_No |
| actual_amount | BIGINT | 实际支付金额（分） | |
| paid_at | TIMESTAMP | 支付时间 | |
| failed_at | TIMESTAMP | 失败时间 | |
| expired_at | TIMESTAMP | 过期时间 | |
| + 审计字段 | | created_at, updated_at, created_by, updated_by, deleted | |

索引：`business_main_order_no`、`business_system_name`、`status`、`created_at`（WHERE deleted = FALSE）

### 2. hsb_payment_sub_orders（子订单）

| 字段 | 类型 | 说明 | 建行对应 |
|------|------|------|----------|
| id | BIGINT PK | TSID | |
| payment_order_id | BIGINT NOT NULL FK | 关联主支付订单 ID → hsb_payment_orders.id | |
| payment_order_no | VARCHAR(64) | 关联主支付订单号（冗余） | |
| business_main_order_no | VARCHAR(64) NOT NULL | 业务主订单号 | |
| business_sub_order_no | VARCHAR(64) NOT NULL | 业务子订单号 | → Cmdty_Ordr_No 商品订单号 |
| mkt_mrch_id | VARCHAR(32) NOT NULL | 商家编号 | → Mkt_Mrch_Id |
| order_amount | BIGINT NOT NULL | 订单金额（分） | → Ordr_Amt |
| txn_amount | BIGINT NOT NULL | 交易金额（分） | → Txnamt |
| sub_order_id | VARCHAR(64) | 惠市宝子订单编号（建行返回） | → Sub_Ordr_Id |
| confirmed | BOOLEAN DEFAULT FALSE | 是否已确认分账 | |
| confirmed_at | TIMESTAMP | 确认分账时间 | |
| + 审计字段 | | | |

索引：`payment_order_id`、`business_sub_order_no`、`mkt_mrch_id`（WHERE deleted = FALSE）

### 3. hsb_refund_orders（退款订单）

| 字段 | 类型 | 说明 | 建行对应 |
|------|------|------|----------|
| id | BIGINT PK | TSID | |
| refund_order_no | VARCHAR(64) UNIQUE NOT NULL | 退款订单号（我们生成） | → Cust_Rfnd_Trcno 客户退款流水号 |
| payment_order_id | BIGINT NOT NULL FK | 关联主支付订单 ID → hsb_payment_orders.id | |
| payment_order_no | VARCHAR(64) NOT NULL | 关联主支付订单号 | |
| business_main_order_no | VARCHAR(64) NOT NULL | 业务退款订单号 | |
| business_system_name | VARCHAR(128) NOT NULL | 业务系统名 | |
| business_name | VARCHAR(128) | 业务名称 | |
| refund_type | VARCHAR(16) DEFAULT 'ASYNC' | 退款类型 | → Rfnd_Type（00实时/01异步） |
| status | INTEGER NOT NULL | 退款状态 | |
| refund_amount | BIGINT NOT NULL | 退款总金额（分） | → Rfnd_Amt |
| reason | VARCHAR(512) | 退款原因 | |
| notify_url | VARCHAR(512) | 业务系统回调 URL | |
| attach | TEXT | 附加数据 | |
| super_refund_no | VARCHAR(64) | 建行退款流水号 | → Super_Refund_No |
| refunded_at | TIMESTAMP | 退款成功时间 | |
| failed_at | TIMESTAMP | 退款失败时间 | |
| + 审计字段 | | | |

索引：`payment_order_id`、`business_main_order_no`、`status`（WHERE deleted = FALSE）

### 4. hsb_refund_sub_orders（退款子订单）

| 字段 | 类型 | 说明 | 建行对应 |
|------|------|------|----------|
| id | BIGINT PK | TSID | |
| refund_order_id | BIGINT NOT NULL FK | 关联退款订单 ID → hsb_refund_orders.id | |
| refund_order_no | VARCHAR(64) | 关联退款订单号（冗余） | |
| business_main_order_no | VARCHAR(64) NOT NULL | 业务主订单号 | |
| business_sub_order_no | VARCHAR(64) NOT NULL | 业务子订单号 | |
| sub_order_id | VARCHAR(64) | 惠市宝子订单编号 | → Sub_Ordr_Id |
| refund_amount | BIGINT NOT NULL | 子订单退款金额（分） | → Rfnd_Amt |
| + 审计字段 | | | |

索引：`refund_order_id`、`business_sub_order_no`（WHERE deleted = FALSE）

### 5. hsb_settlement_confirms（分账确认记录）

| 字段 | 类型 | 说明 | 建行对应 |
|------|------|------|----------|
| id | BIGINT PK | TSID | |
| payment_order_id | BIGINT NOT NULL FK | 关联主支付订单 ID → hsb_payment_orders.id | |
| payment_order_no | VARCHAR(64) NOT NULL | 关联主支付订单号 | → Ittparty_Jrnl_No |
| business_main_order_no | VARCHAR(64) NOT NULL | 业务主订单号 | |
| status | INTEGER NOT NULL | 确认状态 | |
| business_sub_order_nos | JSON | 本次确认的业务子订单号列表 | |
| sub_order_ids | JSON | 对应的惠市宝子订单编号列表 | → Sub_Ordr_Id |
| confirmed_at | TIMESTAMP | 确认时间 | |
| + 审计字段 | | | |

索引：`payment_order_id`、`status`（WHERE deleted = FALSE）

### 6. hsb_payment_logs（操作日志）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT PK | TSID |
| payment_order_no | VARCHAR(64) | |
| refund_order_no | VARCHAR(64) | |
| log_type | VARCHAR(32) | |
| bank_interface | VARCHAR(64) | 建行接口名 |
| request_url | VARCHAR(512) | |
| request_params | TEXT | |
| response_params | TEXT | |
| http_status | INTEGER | |
| return_code | VARCHAR(16) | |
| return_msg | TEXT | |
| execution_time | BIGINT | |
| success | BOOLEAN | |
| error_message | TEXT | |
| + 审计字段 | | |

---

## API 接口设计

### 一、对外接口（业务系统调用我们，@RequireSignature）

#### 1. 创建支付订单

`POST /api/v1/hsb/payments`

请求体：
```json
{
  "businessMainOrderNo": "BJD2026001",
  "businessName": "AI课程订单",
  "paymentMethod": "03",
  "orderType": "04",
  "totalAmount": 10000,
  "txnTotalAmount": 10000,
  "feeBearerId": "41060860811052000010",
  "expiredSeconds": 3600,
  "notifyUrl": "https://xxx/callback",
  "attach": "...",
  "subOrders": [
    {
      "businessSubOrderNo": "SUB001",
      "mktMrchId": "41060860811052000010",
      "orderAmount": 6000,
      "txnAmount": 6000
    },
    {
      "businessSubOrderNo": "SUB002",
      "mktMrchId": "41060860811052000011",
      "orderAmount": 4000,
      "txnAmount": 4000
    }
  ]
}
```

- `businessSystemName` 从 `RequestContext.getCallerAppName()` 获取
- `mktId` 从配置获取

响应体：
```json
{
  "paymentOrderNo": "HSB20260527...",
  "payUrl": "https://...",
  "payQrCode": "..."
}
```

#### 2. 查询支付结果

`GET /api/v1/hsb/payments/{paymentOrderNo}`

响应体包含完整支付订单信息（含子订单列表、状态、建行流水号等）。

#### 3. 主动查询支付状态（调建行）

`POST /api/v1/hsb/payments/{paymentOrderNo}/query`

主动调建行 gatherEnquireOrder 接口查询并更新状态。

#### 4. 创建退款订单

`POST /api/v1/hsb/refunds`

请求体：
```json
{
  "paymentOrderNo": "HSB20260527...",
  "businessMainOrderNo": "REF_BJD2026001",
  "refundType": "ASYNC",
  "refundAmount": 3000,
  "reason": "商品退款",
  "notifyUrl": "https://xxx/refund-callback",
  "subOrders": [
    {
      "businessSubOrderNo": "SUB001",
      "refundAmount": 2000
    },
    {
      "businessSubOrderNo": "SUB002",
      "refundAmount": 1000
    }
  ]
}
```

- `py_trn_no` 从关联的支付订单中查询

#### 5. 查询退款结果

`GET /api/v1/hsb/refunds/{refundOrderNo}`

#### 6. 确认分账

`POST /api/v1/hsb/settlements`

请求体：
```json
{
  "paymentOrderNo": "HSB20260527...",
  "businessSubOrderNos": ["SUB001", "SUB002"]
}
```

处理流程：
1. 根据支付订单号查到建行返回的 `prim_order_no`（建行生成的订单编号）
2. 根据 businessSubOrderNos 查到对应的惠市宝子订单编号（sub_order_id）
3. 调用建行 mergeNoticeArrival 接口，传入 Prim_Ordr_No 和 Sub_Ordr_Id（多个子订单用逗号分隔）
4. 落库分账确认记录

注意：建行确认收货接口的 Prim_Ordr_No 是建行生成返回的订单编号（存在 hsb_payment_orders.prim_order_no），不是我们的 business_main_order_no

### 二、建行回调接口（建行调用我们，需验签）

#### 7. 支付结果通知

`POST /api/v1/hsb/callback/payment`

建行发送（通知接口验签规则：仅 Sign_Inf 不参与拼接，空值不参与签名）：
```json
{
  "Main_Ordr_No": "...",
  "Py_Trn_No": "...",
  "Ordr_Amt": "...",
  "Txnamt": "...",
  "Pay_Time": "...",
  "Ordr_Stcd": "2",
  "Sign_Inf": "..."
}
```

订单状态代码：`2`=成功，`3`=失败，`4`=失效

处理流程：验签 → 根据 Main_Ordr_No（即我们的 business_main_order_no）找到订单 → 更新状态/金额/pyTrnNo → 通知业务系统

响应（需返回以下字段，建行才能确认通知成功）：
```json
{
  "Svc_Rsp_St": "00",
  "Rcv_Tm": "20260527123456"
}
```
通知频率：5分钟一次，最多6次，收到成功响应则不再推送。

#### 8. 退款结果通知

`POST /api/v1/hsb/callback/refund`

建行发送：
```json
{
  "Ittparty_Tms": "...",
  "Ittparty_Jrnl_No": "...",
  "Py_Trn_No": "...",
  "Cust_Rfnd_Trcno": "...",
  "Super_Refund_No": "...",
  "Rfnd_Amt": "...",
  "Refund_Rsp_St": "00",
  "Refund_Rsp_Inf": "...",
  "Refund_Funds_Source": "...",
  "Sign_Inf": "..."
}
```

退款响应状态：`00`=退款成功，`01`=退款失败
退款资金来源：`01`=分账前退款，`02`=分账后退款-退款专户，`03`=分账后退款-待分账余额，`04`=从商家退款专户退款

处理流程：验签 → 根据 Cust_Rfnd_Trcno（即 refund_order_no）或 Py_Trn_No 找到退款订单 → 更新状态 → 通知业务系统

响应：
```json
{
  "Svc_Rsp_St": "00",
  "Rcv_Tm": "20260527123456",
  "Ittparty_Tms": "..."
}
```
通知频率同支付通知。

---

## 代码结构与包组织

```
com.aieducenter.payment.
├── hsb/                                        # 惠市宝限界上下文
│   ├── domain/
│   │   ├── aggregate/
│   │   │   ├── HsbPaymentOrder.java            # 主支付订单聚合根
│   │   │   ├── HsbSubOrder.java                # 子订单（@OneToMany 嵌入聚合根）
│   │   │   ├── HsbRefundOrder.java             # 退款订单聚合根
│   │   │   ├── HsbRefundSubOrder.java          # 退款子订单（@OneToMany 嵌入聚合根）
│   │   │   └── HsbSettlementConfirm.java       # 分账确认聚合根
│   │   ├── enums/
│   │   │   ├── HsbPaymentStatus.java           # PENDING/PAID/FAILED/EXPIRED
│   │   │   ├── HsbRefundStatus.java            # PENDING/REFUNDING/SUCCESS/FAILED
│   │   │   └── HsbSettlementStatus.java        # PENDING/CONFIRMED/FAILED
│   │   ├── repository/
│   │   │   ├── HsbPaymentOrderRepository.java
│   │   │   ├── HsbRefundOrderRepository.java
│   │   │   └── HsbSettlementConfirmRepository.java
│   │   ├── port/
│   │   │   └── HsbPaymentGatewayPort.java      # 南向网关端口接口
│   │   └── error/
│   │       └── HsbMessage.java
│   ├── application/
│   │   ├── HsbPaymentAppService.java           # 支付应用服务
│   │   ├── HsbRefundAppService.java            # 退款应用服务
│   │   ├── HsbSettlementAppService.java        # 分账确认应用服务
│   │   ├── HsbCallbackAppService.java          # 回调处理应用服务
│   │   ├── HsbPaymentQueryScheduler.java       # 定时查询支付结果
│   │   ├── HsbRefundQueryScheduler.java        # 定时查询退款结果
│   │   ├── dto/
│   │   │   ├── command/
│   │   │   │   ├── CreateHsbPaymentCommand.java
│   │   │   │   ├── CreateHsbRefundCommand.java
│   │   │   │   └── ConfirmHsbSettlementCommand.java
│   │   │   ├── callback/
│   │   │   │   ├── HsbPaymentCallbackParam.java
│   │   │   │   └── HsbRefundCallbackParam.java
│   │   │   └── response/
│   │   │       ├── HsbPaymentOrderResponse.java
│   │   │       └── HsbRefundOrderResponse.java
│   │   └── mapper/
│   │       └── HsbPaymentOrderMapper.java
│   ├── infrastructure/
│   │   ├── HsbConfig.java                      # 配置
│   │   ├── HsbPaymentGatewayAdapter.java       # 建行网关适配器
│   │   ├── HsbSignUtil.java                    # 签名/验签（封装 mktpaysign.jar）
│   │   ├── HsbSplicingUtil.java                # 签名字符串拼接
│   │   └── HsbHttpClient.java                  # HTTP 客户端
│   └── endpoints/
│       └── api/v1/
│           ├── HsbPaymentController.java       # 支付 API（@RequireSignature）
│           ├── HsbRefundController.java        # 退款 API（@RequireSignature）
│           ├── HsbSettlementController.java    # 分账确认 API（@RequireSignature）
│           └── HsbCallbackController.java      # 建行回调（验签）
```

### 聚合根关系

- `HsbPaymentOrder` 通过 `@OneToMany(cascade = ALL)` 管理 `HsbSubOrder` 列表
- `HsbRefundOrder` 通过 `@OneToMany(cascade = ALL)` 管理 `HsbRefundSubOrder` 列表

### HsbPaymentGatewayPort 端口接口

```java
public interface HsbPaymentGatewayPort {
    CreateHsbPaymentResponse createPayment(HsbPaymentOrder order, List<HsbSubOrder> subOrders);
    QueryHsbPaymentResponse queryPayment(String mktId, String mainOrderNo, String pyTrnNo);
    CreateHsbRefundResponse createRefund(HsbRefundOrder order, String pyTrnNo, List<HsbRefundSubOrder> subOrders);
    QueryHsbRefundResponse queryRefund(String mktId, String custRfndTrcno, String rfndTrcno);
    ConfirmSettlementResponse confirmSettlement(String mktId, String primOrderNo, String subOrderId);
}
```

---

## 建行接口版本号（Vno）

从建行接口文档中确认的版本号：

| 接口 | 路径 | Vno | 备注 |
|------|------|-----|------|
| 生成支付订单 | gatherPlaceorder | 5 | 默认填写版本5 |
| 查询支付结果 | gatherEnquireOrder | 5 | 填写版本为5 |
| 订单退款 | refundOrder | 3 | 默认版本3，非必输 |
| 查询退款结果 | enquireRefundOrder | 4 | 填写版本为4 |
| 确认收货 | mergeNoticeArrival | 4 | 默认版本4 |

---

## HsbConfig 配置

```yaml
hsb:
  base-url: http://marketpaypl4.dev.jh:8035/online/direct/
  mkt-id: "41060860811052"
  private-key: "MIIEvgIBADANBgk..."  # 私钥（请求报文加密）
  platform-public-key: "MIIBIjANBgk..."  # 平台公钥（响应报文验签）
  version:
    place-order: "5"
    query-order: "5"
    refund-order: "3"
    query-refund: "4"
    confirm-settlement: "4"
  initiator-system-id: "00000"       # Ittparty_Stm_Id
  initiator-channel-code: "0000000000000000000000000"  # Py_Chnl_Cd
```

### 环境配置

**PL4 测试环境**：
- Host：`42.240.3.133 marketpaypl4.dev.jh`
- URL：`http://marketpaypl4.dev.jh:8035/`
- 市场编号：`41060860811052`
- 商家编号：`41060860811052000000`、`41060860811052000010`、`41060860811052000011`
- 支付方式仅支持 `03`（H5 支付）

**生产环境**：
- URL：`https://marketpay.ccb.com/online/direct/`

---

## 签名流程

### 请求签名

1. 组装请求 JSON（不含 Sign_Inf）
2. `HsbSplicingUtil.createSign()` 拼接签名字符串（TreeMap 按 key 字典排序，排除 Sign_Inf/Svc_Rsp_St/Svc_Rsp_Cd/Rsp_Inf，空值不参与签名）
3. `RSASignUtil.sign(privateKey, signStr)` 用我们的私钥签名
4. 将签名放入 Sign_Inf 字段

### 响应验签

1. 对响应 JSON 拼接（排除公共字段 Sign_Inf/Svc_Rsp_St/Svc_Rsp_Cd/Rsp_Inf，空值不参与签名）
2. `RSASignUtil.verifySign(platformPublicKey, signStr, signInf)` 验证

### 通知接口验签（建行回调我们）

通知接口输入字段中，仅 Sign_Inf 不参与拼接验签。空值不参与签名。

---

## 状态机

### 支付状态（HsbPaymentStatus）

```
PENDING → PAID
PENDING → FAILED
PENDING → EXPIRED
```

### 退款状态（HsbRefundStatus）

```
PENDING → REFUNDING → SUCCESS
                   → FAILED
异步退款场景的中间状态：
- REFUNDING = 建行返回 02-退款延时等待 或 03-退款结果不确定
- 最终通过退款通知或主动查询确定 SUCCESS 或 FAILED
```

### 分账确认状态（HsbSettlementStatus）

```
PENDING → CONFIRMED
PENDING → FAILED
```

---

## 回调处理流程

### 支付结果回调

1. 收到建行通知 → 验签
2. 根据 Main_Ordr_No（business_main_order_no）找到支付订单
3. 更新状态为 PAID，写入 py_trn_no、actual_amount、paid_at
4. 同步更新子订单的 sub_order_id（建行返回）
5. 通过 notify_url 通知业务系统

### 退款结果回调

1. 收到建行通知 → 验签
2. 根据 Py_Trn_No 找到退款订单
3. 更新状态为 SUCCESS/FAILED
4. 通过 notify_url 通知业务系统

### 定时查询补偿

- `HsbPaymentQueryScheduler`：定时查询 PENDING 状态超过一定时间的支付订单
- `HsbRefundQueryScheduler`：定时查询 REFUNDING 状态的退款订单

---

## 金额转换

适配器层负责分→元的转换：
- 发送请求：`BigDecimal.valueOf(amountInFen).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)`
- 接收响应：`new BigDecimal(amountStr).multiply(BigDecimal.valueOf(100)).longValue()`

---

## 依赖

- `mktpaysign.jar`：建行提供的签名工具包（`com.ccb.mktpay.sign.RSASignUtil`）
- 位置：`docs/建行/TestPay/WebContent/WEB-INF/lib/mktpaysign.jar`
- 需要安装到本地 Maven 仓库或放入项目 lib 目录
