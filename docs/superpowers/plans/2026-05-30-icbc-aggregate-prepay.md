# ICBC 聚合支付预支付接口 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 新增工行聚合支付预支付接口 `POST /api/v1/payments/prepay`，支持微信/支付宝/云闪付的应用内支付。

**Architecture:** 在现有支付限界上下文中扩展。新增独立的预支付 API 端点，内部复用 PaymentOrder 实体、回调处理、查询退款。只新增 Domain 枚举/字段、Port 方法、Infrastructure 适配器方法、Application 服务和 Endpoint。

**Tech Stack:** Java 21, Spring Boot, JPA/Hibernate, ICBC SDK (`icbc-api-sdk-cop_v2_20260313`), JUnit 5, Mockito, AssertJ

---

## File Structure

### New Files

| File | Responsibility |
|------|---------------|
| `src/main/java/.../domain/enums/PayMode.java` | 支付方式枚举（微信/支付宝/云闪付） |
| `src/main/java/.../domain/enums/AccessType.java` | 接入方式枚举（H5/APP/公众号/生活号/小程序） |
| `src/main/java/.../domain/port/response/CreatePrepayResponse.java` | 预支付网关响应 record |
| `src/main/java/.../application/dto/command/CreatePrepayCommand.java` | 预支付请求 command record |
| `src/main/java/.../application/dto/response/PrepayOrderResponse.java` | 预支付响应 record |
| `src/main/java/.../application/PrepayAppService.java` | 预支付应用服务 |
| `src/main/java/.../endpoints/api/v1/PrepayApiV1Controller.java` | 预支付 REST 控制器 |
| `src/main/resources/db/migration/V5__add_aggregate_payment_fields.sql` | 数据库迁移 |
| `src/test/java/.../domain/enums/PayModeTest.java` | PayMode 枚举测试 |
| `src/test/java/.../domain/enums/AccessTypeTest.java` | AccessType 枚举测试 |
| `src/test/java/.../domain/aggregate/PaymentOrderPrepayTest.java` | PaymentOrder 预支付字段测试 |
| `src/test/java/.../application/PrepayAppServiceTest.java` | PrepayAppService 测试 |
| `src/test/java/.../application/PaymentCallbackAppServiceBackfillTest.java` | 回调回填测试 |

### Modified Files

| File | Change |
|------|--------|
| `src/main/java/.../domain/aggregate/PaymentOrder.java` | 新增 6 个字段 + setter |
| `src/main/java/.../domain/port/PaymentGatewayPort.java` | 新增 `createPrepay()` 方法 |
| `src/main/java/.../infrastructure/icbc/IcbcConfig.java` | 新增 `prepayUrl`, `shopAppid` |
| `src/main/java/.../infrastructure/icbc/IcbcPaymentGatewayAdapter.java` | 新增 `createPrepay()` 实现 |
| `src/main/java/.../application/PaymentCallbackAppService.java` | 新增 payMode/accessType 回填 |
| `src/main/java/.../application/PaymentAppService.java` | 现有订单设置 accessType=H5 |
| `src/main/resources/application-local.yml` | 新增 prepay-url, shop-appid |
| `src/main/resources/application-test.yml` | 新增 prepay-url, shop-appid |
| `src/main/resources/application-prod.yml` | 新增 prepay-url, shop-appid |

---

### Task 1: Domain Enums — PayMode

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/enums/PayMode.java`
- Test: `src/test/java/com/aieducenter/payment/domain/enums/PayModeTest.java`

- [ ] **Step 1: Write the failing test**

```java
// src/test/java/com/aieducenter/payment/domain/enums/PayModeTest.java
package com.aieducenter.payment.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PayMode 枚举测试")
class PayModeTest {

    @Test
    @DisplayName("给定微信支付方式，验证code和name")
    void given_wechat_then_code9AndNameWechat() {
        assertThat(PayMode.WECHAT.getCode()).isEqualTo(9);
        assertThat(PayMode.WECHAT.getName()).isEqualTo("微信");
    }

    @Test
    @DisplayName("给定支付宝支付方式，验证code和name")
    void given_alipay_then_code10AndNameAlipay() {
        assertThat(PayMode.ALIPAY.getCode()).isEqualTo(10);
        assertThat(PayMode.ALIPAY.getName()).isEqualTo("支付宝");
    }

    @Test
    @DisplayName("给定云闪付支付方式，验证code和name")
    void given_unionpay_then_code13AndNameUnionpay() {
        assertThat(PayMode.UNIONPAY.getCode()).isEqualTo(13);
        assertThat(PayMode.UNIONPAY.getName()).isEqualTo("云闪付");
    }

    @Test
    @DisplayName("根据code查找对应的枚举值")
    void given_code9_then_returnsWechat() {
        assertThat(PayMode.WECHAT.getCode()).isEqualTo(9);
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn test -pl . -Dtest=PayModeTest -Dsurefire.failIfNoSpecifiedTests=false -q 2>&1 | tail -5`
Expected: FAIL — class not found

- [ ] **Step 3: Write minimal implementation**

```java
// src/main/java/com/aieducenter/payment/domain/enums/PayMode.java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum PayMode implements BaseEnum<PayMode> {
    WECHAT(9, "微信"),
    ALIPAY(10, "支付宝"),
    UNIONPAY(13, "云闪付");

    private final Integer code;
    private final String name;

    PayMode(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<PayMode> {
        public JpaConverter() {
            super(PayMode.class);
        }
    }
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `mvn test -pl . -Dtest=PayModeTest -q 2>&1 | tail -5`
Expected: Tests run: 4, Failures: 0

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/enums/PayMode.java src/test/java/com/aieducenter/payment/domain/enums/PayModeTest.java
git commit -m "feat(prepay): add PayMode enum for aggregate payment"
```

---

### Task 2: Domain Enums — AccessType

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/enums/AccessType.java`
- Test: `src/test/java/com/aieducenter/payment/domain/enums/AccessTypeTest.java`

- [ ] **Step 1: Write the failing test**

```java
// src/test/java/com/aieducenter/payment/domain/enums/AccessTypeTest.java
package com.aieducenter.payment.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AccessType 枚举测试")
class AccessTypeTest {

    @Test
    @DisplayName("H5接入方式")
    void given_h5_then_code4() {
        assertThat(AccessType.H5.getCode()).isEqualTo(4);
        assertThat(AccessType.H5.getName()).isEqualTo("H5");
    }

    @Test
    @DisplayName("APP接入方式")
    void given_app_then_code5() {
        assertThat(AccessType.APP.getCode()).isEqualTo(5);
        assertThat(AccessType.APP.getName()).isEqualTo("APP");
    }

    @Test
    @DisplayName("微信公众号接入方式")
    void given_wechatOa_then_code7() {
        assertThat(AccessType.WECHAT_OA.getCode()).isEqualTo(7);
        assertThat(AccessType.WECHAT_OA.getName()).isEqualTo("微信公众号");
    }

    @Test
    @DisplayName("支付宝生活号接入方式")
    void given_alipayLife_then_code8() {
        assertThat(AccessType.ALIPAY_LIFE.getCode()).isEqualTo(8);
        assertThat(AccessType.ALIPAY_LIFE.getName()).isEqualTo("支付宝生活号");
    }

    @Test
    @DisplayName("小程序接入方式")
    void given_miniProgram_then_code9() {
        assertThat(AccessType.MINI_PROGRAM.getCode()).isEqualTo(9);
        assertThat(AccessType.MINI_PROGRAM.getName()).isEqualTo("小程序");
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn test -pl . -Dtest=AccessTypeTest -Dsurefire.failIfNoSpecifiedTests=false -q 2>&1 | tail -5`
Expected: FAIL — class not found

- [ ] **Step 3: Write minimal implementation**

```java
// src/main/java/com/aieducenter/payment/domain/enums/AccessType.java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum AccessType implements BaseEnum<AccessType> {
    H5(4, "H5"),
    APP(5, "APP"),
    WECHAT_OA(7, "微信公众号"),
    ALIPAY_LIFE(8, "支付宝生活号"),
    MINI_PROGRAM(9, "小程序");

    private final Integer code;
    private final String name;

    AccessType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<AccessType> {
        public JpaConverter() {
            super(AccessType.class);
        }
    }
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `mvn test -pl . -Dtest=AccessTypeTest -q 2>&1 | tail -5`
Expected: Tests run: 5, Failures: 0

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/enums/AccessType.java src/test/java/com/aieducenter/payment/domain/enums/AccessTypeTest.java
git commit -m "feat(prepay): add AccessType enum for aggregate payment"
```

---

### Task 3: Domain Entity Extension — PaymentOrder

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/domain/aggregate/PaymentOrder.java`
- Test: `src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderPrepayTest.java`

- [ ] **Step 1: Write the failing test**

```java
// src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderPrepayTest.java
package com.aieducenter.payment.domain.aggregate;

import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PaymentOrder 预支付字段测试")
class PaymentOrderPrepayTest {

    @Test
    @DisplayName("给定预支付参数，设置payMode后可以获取")
    void given_order_when_setPayMode_then_canGet() {
        PaymentOrder order = createOrder();
        order.setPayMode(PayMode.WECHAT);
        assertThat(order.getPayMode()).isEqualTo(PayMode.WECHAT);
    }

    @Test
    @DisplayName("给定预支付参数，设置accessType后可以获取")
    void given_order_when_setAccessType_then_canGet() {
        PaymentOrder order = createOrder();
        order.setAccessType(AccessType.MINI_PROGRAM);
        assertThat(order.getAccessType()).isEqualTo(AccessType.MINI_PROGRAM);
    }

    @Test
    @DisplayName("设置shopAppid后可以获取")
    void given_order_when_setShopAppid_then_canGet() {
        PaymentOrder order = createOrder();
        order.setShopAppid("wx1234567890");
        assertThat(order.getShopAppid()).isEqualTo("wx1234567890");
    }

    @Test
    @DisplayName("设置openId后可以获取")
    void given_order_when_setOpenId_then_canGet() {
        PaymentOrder order = createOrder();
        order.setOpenId("oUSDOusdsdISLSDlskdf");
        assertThat(order.getOpenId()).isEqualTo("oUSDOusdsdISLSDlskdf");
    }

    @Test
    @DisplayName("设置prepayDataPackage后可以获取")
    void given_order_when_setPrepayDataPackage_then_canGet() {
        PaymentOrder order = createOrder();
        String dataPackage = "{\"appid\":\"wx123\",\"prepayid\":\"pre123\"}";
        order.setPrepayDataPackage(dataPackage);
        assertThat(order.getPrepayDataPackage()).isEqualTo(dataPackage);
    }

    @Test
    @DisplayName("设置tradeType后可以获取")
    void given_order_when_setTradeType_then_canGet() {
        PaymentOrder order = createOrder();
        order.setTradeType("JSAPI");
        assertThat(order.getTradeType()).isEqualTo("JSAPI");
    }

    @Test
    @DisplayName("新创建的订单预支付字段应为null")
    void given_newOrder_then_prepayFieldsAreNull() {
        PaymentOrder order = createOrder();
        assertThat(order.getPayMode()).isNull();
        assertThat(order.getAccessType()).isNull();
        assertThat(order.getShopAppid()).isNull();
        assertThat(order.getOpenId()).isNull();
        assertThat(order.getPrepayDataPackage()).isNull();
        assertThat(order.getTradeType()).isNull();
    }

    private PaymentOrder createOrder() {
        return new PaymentOrder(
            "BIZ001", "TestSystem", "课程购买",
            10000L, "Python课程", "Python编程课程",
            "https://example.com/notify", null, 3600L
        );
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn test -pl . -Dtest=PaymentOrderPrepayTest -Dsurefire.failIfNoSpecifiedTests=false -q 2>&1 | tail -5`
Expected: FAIL — cannot find symbols (getPayMode, setPayMode, etc.)

- [ ] **Step 3: Write minimal implementation**

在 `PaymentOrder.java` 的现有字段区域（`actualAmount` 之后）新增 6 个字段和对应 setter：

```java
// --- 新增预支付字段 ---

@Getter
@Column(name = "pay_mode")
private PayMode payMode;

@Getter
@Column(name = "access_type")
private AccessType accessType;

@Getter
@Column(name = "shop_appid", length = 32)
private String shopAppid;

@Getter
@Column(name = "open_id", length = 128)
private String openId;

@Getter
@Column(name = "prepay_data_package", columnDefinition = "TEXT")
private String prepayDataPackage;

@Getter
@Column(name = "trade_type", length = 16)
private String tradeType;
```

在 `setQrCodeUrl()` 方法后面新增 setter 方法：

```java
public void setPayMode(PayMode payMode) {
    this.payMode = payMode;
}

public void setAccessType(AccessType accessType) {
    this.accessType = accessType;
}

public void setShopAppid(String shopAppid) {
    this.shopAppid = shopAppid;
}

public void setOpenId(String openId) {
    this.openId = openId;
}

public void setPrepayDataPackage(String prepayDataPackage) {
    this.prepayDataPackage = prepayDataPackage;
}

public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
}
```

同时添加 import：
```java
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
```

- [ ] **Step 4: Run test to verify it passes**

Run: `mvn test -pl . -Dtest=PaymentOrderPrepayTest -q 2>&1 | tail -5`
Expected: Tests run: 7, Failures: 0

- [ ] **Step 5: Run existing tests to verify no regression**

Run: `mvn test -pl . -Dtest=PaymentOrderTest -q 2>&1 | tail -5`
Expected: Tests run: 8, Failures: 0

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/aggregate/PaymentOrder.java src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderPrepayTest.java
git commit -m "feat(prepay): extend PaymentOrder with prepay fields (payMode, accessType, etc.)"
```

---

### Task 4: Domain Port — CreatePrepayResponse and PaymentGatewayPort

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/port/response/CreatePrepayResponse.java`
- Modify: `src/main/java/com/aieducenter/payment/domain/port/PaymentGatewayPort.java`

- [ ] **Step 1: Create CreatePrepayResponse record**

```java
// src/main/java/com/aieducenter/payment/domain/port/response/CreatePrepayResponse.java
package com.aieducenter.payment.domain.port.response;

/**
 * 创建预支付响应
 *
 * <p>聚合支付网关返回的创建预支付结果</p>
 */
public record CreatePrepayResponse(
    /**
     * 是否成功
     */
    boolean success,

    /**
     * 返回码
     */
    String returnCode,

    /**
     * 返回消息
     */
    String returnMsg,

    /**
     * 工行订单号
     */
    String bankOrderNo,

    /**
     * 支付参数包JSON
     * <p>根据支付方式不同，包含 wx_data_package / zfb_data_package / union_data_package</p>
     */
    String dataPackage,

    /**
     * 交易类型
     * <p>JSAPI（公众号/小程序）、APP 等</p>
     */
    String tradeType,

    /**
     * 执行耗时（毫秒）
     */
    Long executionTime,

    /**
     * 请求参数（JSON格式，用于日志记录）
     */
    String requestParams,

    /**
     * 响应体（JSON格式，用于日志记录）
     */
    String responseBody
) {
}
```

- [ ] **Step 2: Add createPrepay to PaymentGatewayPort**

在 `PaymentGatewayPort.java` 的 `createPayment` 方法后面新增：

```java
/**
 * 创建预支付
 *
 * @param paymentOrder 支付订单聚合根（需包含 payMode, accessType, openId 等）
 * @return 创建预支付响应
 */
CreatePrepayResponse createPrepay(PaymentOrder paymentOrder);
```

添加 import：
```java
import com.aieducenter.payment.domain.port.response.CreatePrepayResponse;
```

- [ ] **Step 3: Compile to verify**

Run: `mvn compile -pl . -q 2>&1 | tail -10`
Expected: BUILD SUCCESS（IcbcPaymentGatewayAdapter 会编译失败因为未实现新接口方法，先忽略，下个 Task 实现）

注意：此步会编译报错因为 `IcbcPaymentGatewayAdapter` 未实现 `createPrepay()`。这是预期行为，将在 Task 6 中修复。

- [ ] **Step 4: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/port/response/CreatePrepayResponse.java src/main/java/com/aieducenter/payment/domain/port/PaymentGatewayPort.java
git commit -m "feat(prepay): add CreatePrepayResponse and PaymentGatewayPort.createPrepay()"
```

---

### Task 5: Database Migration

**Files:**
- Create: `src/main/resources/db/migration/V5__add_aggregate_payment_fields.sql`

- [ ] **Step 1: Create migration script**

```sql
-- src/main/resources/db/migration/V5__add_aggregate_payment_fields.sql

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

- [ ] **Step 2: Commit**

```bash
git add src/main/resources/db/migration/V5__add_aggregate_payment_fields.sql
git commit -m "feat(prepay): add database migration for aggregate payment fields"
```

---

### Task 6: Infrastructure — IcbcConfig Extension

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcConfig.java`

- [ ] **Step 1: Add new config fields to IcbcConfig**

在 `IcbcConfig.java` 的 `notifyBaseUrl` 字段后面新增：

```java
/**
 * 预支付接口地址（聚合消费下单）
 */
private String prepayUrl;

/**
 * 商户在微信开放平台注册的APPID
 * <p>工行侧绑定，用于微信/支付宝/云闪付支付</p>
 */
private String shopAppid;
```

- [ ] **Step 2: Update config files**

在 `application-local.yml` 的 `refund-query-url` 行后面新增：

```yaml
    prepay-url: ${ICBC_PREPAY_URL:https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/consumepurchase/V1}
    shop-appid: ${ICBC_SHOP_APPID:}
```

同样更新 `application-test.yml` 和 `application-prod.yml`，添加相同的两行。

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcConfig.java src/main/resources/application-local.yml src/main/resources/application-test.yml src/main/resources/application-prod.yml
git commit -m "feat(prepay): add prepayUrl and shopAppid to IcbcConfig"
```

---

### Task 7: Infrastructure — IcbcPaymentGatewayAdapter.createPrepay()

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java`

- [ ] **Step 1: Add createPrepay implementation**

在 `IcbcPaymentGatewayAdapter.java` 中：

**1a. 添加 import：**

```java
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1.CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineConsumepurchaseResponseV1;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.port.response.CreatePrepayResponse;
```

**1b. 在 `createPayment()` 方法后添加 `createPrepay()` 方法：**

```java
@Override
public CreatePrepayResponse createPrepay(PaymentOrder paymentOrder) {
    DefaultIcbcClient client = clientFactory.createClient();

    CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1 request =
        new CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1();
    request.setServiceUrl(icbcConfig.getPrepayUrl());

    CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1Biz bizContent =
        new CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV1Biz();

    // 设置业务参数
    bizContent.setMer_id(icbcConfig.getMerId());
    bizContent.setOut_trade_no(paymentOrder.getPaymentOrderNo());
    bizContent.setPay_mode(String.valueOf(paymentOrder.getPayMode().getCode()));
    bizContent.setAccess_type(String.valueOf(paymentOrder.getAccessType().getCode()));
    bizContent.setMer_prtcl_no(icbcConfig.getMerPrtclNo());
    bizContent.setOrig_date_time(java.time.LocalDateTime.now().format(
        java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
    bizContent.setDecive_info(cn.hutool.core.util.IdUtil.fastSimpleUUID().substring(0, 32));
    bizContent.setBody(paymentOrder.getSubject());
    bizContent.setFee_type("001"); // 人民币
    bizContent.setSpbill_create_ip(paymentOrder.getClientIp() != null ? paymentOrder.getClientIp() : "127.0.0.1");
    bizContent.setTotal_fee(paymentOrder.getAmount().toString());
    bizContent.setMer_url(icbcConfig.getNotifyBaseUrl() + "/api/v1/payment/callbacks/icbc");
    bizContent.setIcbc_appid(icbcConfig.getAppId());
    bizContent.setNotify_type("HS");
    bizContent.setResult_type("0");
    bizContent.setExpire_time(paymentOrder.getExpiredSeconds().toString());

    // 微信支付需要 shop_appid 和 open_id
    if (paymentOrder.getPayMode() == PayMode.WECHAT) {
        bizContent.setShop_appid(icbcConfig.getShopAppid());
        bizContent.setOpen_id(paymentOrder.getOpenId() != null ? paymentOrder.getOpenId() : "");
    }

    // 支付宝生活号需要 union_id
    if (paymentOrder.getOpenId() != null && !paymentOrder.getOpenId().isEmpty()) {
        bizContent.setOpen_id(paymentOrder.getOpenId());
    }

    // 附加数据
    if (paymentOrder.getAttach() != null) {
        bizContent.setAttach(paymentOrder.getAttach());
    }

    request.setBizContent(bizContent);

    // 记录请求参数
    String requestParams = com.alibaba.fastjson2.JSON.toJSONString(bizContent);

    long startTime = System.currentTimeMillis();
    CardbusinessAggregatepayB2cOnlineConsumepurchaseResponseV1 response;
    try {
        response = client.execute(request, clientFactory.generateMsgId());
    } catch (Exception e) {
        log.error("ICBC prepay request failed", e);
        return new CreatePrepayResponse(
            false,
            "SYSTEM_ERROR",
            e.getMessage(),
            null,
            null,
            null,
            System.currentTimeMillis() - startTime,
            requestParams,
            null
        );
    }
    long executionTime = System.currentTimeMillis() - startTime;

    // 解析响应
    boolean success = response.getReturnCode() == 0;
    String responseBody = com.alibaba.fastjson2.JSON.toJSONString(response);

    // 根据支付方式提取对应的 data_package
    String dataPackage = null;
    String tradeType = null;
    if (success) {
        if (paymentOrder.getPayMode() == PayMode.WECHAT) {
            dataPackage = response.getWx_data_package();
        } else if (paymentOrder.getPayMode() == PayMode.ALIPAY) {
            dataPackage = response.getZfb_data_package();
        } else if (paymentOrder.getPayMode() == PayMode.UNIONPAY) {
            dataPackage = response.getUnion_data_package();
        }
        tradeType = response.getTrade_type();
    }

    return new CreatePrepayResponse(
        success,
        String.valueOf(response.getReturnCode()),
        response.getReturnMsg(),
        success ? response.getOrder_id() : null,
        dataPackage,
        tradeType,
        executionTime,
        requestParams,
        responseBody
    );
}
```

- [ ] **Step 2: Compile to verify**

Run: `mvn compile -pl . -q 2>&1 | tail -10`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java
git commit -m "feat(prepay): implement IcbcPaymentGatewayAdapter.createPrepay()"
```

---

### Task 8: Application DTOs

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/dto/command/CreatePrepayCommand.java`
- Create: `src/main/java/com/aieducenter/payment/application/dto/response/PrepayOrderResponse.java`

- [ ] **Step 1: Create CreatePrepayCommand**

```java
// src/main/java/com/aieducenter/payment/application/dto/command/CreatePrepayCommand.java
package com.aieducenter.payment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * 创建预支付命令
 *
 * <p>用于接收业务系统发送的聚合支付请求</p>
 */
public record CreatePrepayCommand(
    /**
     * 业务订单号
     */
    @NotBlank(message = "业务订单号不能为空")
    @Size(max = 64, message = "业务订单号长度不能超过64")
    String businessOrderNo,

    /**
     * 支付金额（分）
     */
    @NotNull(message = "金额不能为空")
    @Positive(message = "金额必须大于0")
    Long amount,

    /**
     * 支付标题
     */
    @NotBlank(message = "支付标题不能为空")
    @Size(max = 255, message = "支付标题长度不能超过255")
    String subject,

    /**
     * 支付描述
     */
    @Size(max = 1000, message = "支付描述长度不能超过1000")
    String body,

    /**
     * 业务名称
     */
    @Size(max = 128, message = "业务名称长度不能超过128")
    String businessName,

    /**
     * 异步通知地址
     */
    @NotBlank(message = "异步通知地址不能为空")
    @Size(max = 512, message = "异步通知地址长度不能超过512")
    String notifyUrl,

    /**
     * 过期时长（秒）
     * <p>可选，不传则使用服务端默认配置</p>
     */
    Long expiredSeconds,

    /**
     * 附加数据
     * <p>自定义数据，原样返回</p>
     */
    @Size(max = 1000, message = "附加数据长度不能超过1000")
    String attach,

    /**
     * 支付方式
     * <p>9=微信, 10=支付宝, 13=云闪付</p>
     */
    @NotNull(message = "支付方式不能为空")
    Integer payMode,

    /**
     * 接入方式
     * <p>5=APP, 7=公众号, 8=生活号, 9=小程序</p>
     */
    @NotNull(message = "接入方式不能为空")
    Integer accessType,

    /**
     * 微信用户标识
     * <p>微信支付时必填（payMode=9 且 accessType=7或9）</p>
     */
    @Size(max = 128, message = "openId长度不能超过128")
    String openId,

    /**
     * 支付宝用户标识
     * <p>支付宝生活号时必填（payMode=10 且 accessType=8）</p>
     */
    @Size(max = 128, message = "unionId长度不能超过128")
    String unionId
) {
}
```

- [ ] **Step 2: Create PrepayOrderResponse**

```java
// src/main/java/com/aieducenter/payment/application/dto/response/PrepayOrderResponse.java
package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 预支付订单响应
 *
 * <p>返回给业务系统的预支付订单详情，包含前端调起支付所需的参数包</p>
 */
public record PrepayOrderResponse(
    /**
     * 订单 ID
     */
    Long id,

    /**
     * 业务订单号
     */
    String businessOrderNo,

    /**
     * 支付订单号
     */
    String paymentOrderNo,

    /**
     * 业务系统名称
     */
    String businessSystemName,

    /**
     * 支付状态码
     */
    Integer status,

    /**
     * 支付状态名称
     */
    String statusName,

    /**
     * 支付金额（分）
     */
    Long amount,

    /**
     * 支付参数包JSON
     * <p>前端用于调起微信/支付宝/云闪付支付</p>
     */
    String prepayDataPackage,

    /**
     * 支付方式码
     */
    Integer payMode,

    /**
     * 支付方式名称
     */
    String payModeName,

    /**
     * 接入方式码
     */
    Integer accessType,

    /**
     * 接入方式名称
     */
    String accessTypeName,

    /**
     * 过期时间
     */
    LocalDateTime expiredAt,

    /**
     * 创建时间
     */
    LocalDateTime createdAt
) {
}
```

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/command/CreatePrepayCommand.java src/main/java/com/aieducenter/payment/application/dto/response/PrepayOrderResponse.java
git commit -m "feat(prepay): add CreatePrepayCommand and PrepayOrderResponse DTOs"
```

---

### Task 9: Application Service — PrepayAppService

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/PrepayAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/PrepayAppServiceTest.java`

- [ ] **Step 1: Write the failing test**

```java
// src/test/java/com/aieducenter/payment/application/PrepayAppServiceTest.java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.command.CreatePrepayCommand;
import com.aieducenter.payment.application.dto.response.PrepayOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.CreatePrepayResponse;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("预支付应用服务测试")
class PrepayAppServiceTest {

    @Mock private PaymentOrderRepository paymentOrderRepository;
    @Mock private PaymentGatewayPort paymentGatewayPort;
    @Mock private PaymentLogRepository paymentLogRepository;

    private PrepayAppService service;

    @BeforeEach
    void setUp() {
        service = new PrepayAppService(paymentOrderRepository, paymentGatewayPort, paymentLogRepository);
    }

    private CreatePrepayCommand createWechatCommand() {
        return new CreatePrepayCommand(
            "BIZ001", 10000L, "Python课程", "Python编程课程",
            "课程购买", "https://biz.example.com/notify", 3600L, null,
            9, 7, "oUSDOusdsdISLSDlskdf", null
        );
    }

    private CreatePrepayResponse createSuccessPrepayResponse() {
        return new CreatePrepayResponse(
            true, "0", "success",
            "ICBC_ORDER_123",
            "{\"appid\":\"wx123\",\"prepayid\":\"pre123\"}",
            "JSAPI",
            200L,
            "{\"request\":true}",
            "{\"response\":true}"
        );
    }

    @Test
    @DisplayName("创建微信预支付：成功返回支付参数包")
    void createPrepay_wechat_success_returnsPrepayResponse() {
        // Given
        CreatePrepayCommand command = createWechatCommand();
        CreatePrepayResponse gatewayResponse = createSuccessPrepayResponse();

        when(paymentOrderRepository.save(any(PaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentGatewayPort.createPrepay(any(PaymentOrder.class))).thenReturn(gatewayResponse);

        // When
        PrepayOrderResponse response = service.createPrepay(command, "TestSystem", "192.168.1.1");

        // Then
        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(PaymentStatus.PENDING.getCode());
        assertThat(response.prepayDataPackage()).isEqualTo("{\"appid\":\"wx123\",\"prepayid\":\"pre123\"}");
        assertThat(response.payMode()).isEqualTo(9);
        assertThat(response.payModeName()).isEqualTo("微信");
        assertThat(response.accessType()).isEqualTo(7);
        assertThat(response.accessTypeName()).isEqualTo("微信公众号");

        // Verify order was saved with correct fields
        ArgumentCaptor<PaymentOrder> orderCaptor = ArgumentCaptor.forClass(PaymentOrder.class);
        verify(paymentOrderRepository, atLeastOnce()).save(orderCaptor.capture());
        PaymentOrder savedOrder = orderCaptor.getAllValues().stream()
            .filter(o -> o.getPrepayDataPackage() != null)
            .findFirst().orElse(null);
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getPayMode()).isEqualTo(PayMode.WECHAT);
        assertThat(savedOrder.getAccessType()).isEqualTo(AccessType.WECHAT_OA);
        assertThat(savedOrder.getPrepayDataPackage()).contains("wx123");
        assertThat(savedOrder.getOpenId()).isEqualTo("oUSDOusdsdISLSDlskdf");
    }

    @Test
    @DisplayName("创建预支付：记录日志")
    void createPrepay_success_savesLog() {
        CreatePrepayCommand command = createWechatCommand();
        when(paymentOrderRepository.save(any(PaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentGatewayPort.createPrepay(any(PaymentOrder.class))).thenReturn(createSuccessPrepayResponse());

        service.createPrepay(command, "TestSystem", "192.168.1.1");

        verify(paymentLogRepository).save(any(PaymentLog.class));
    }

    @Test
    @DisplayName("创建预支付：网关失败时抛异常")
    void createPrepay_gatewayFailure_throwsException() {
        CreatePrepayCommand command = createWechatCommand();
        CreatePrepayResponse failureResponse = new CreatePrepayResponse(
            false, "42010031", "参数非法", null, null, null, 100L, "{}", "{}"
        );
        when(paymentOrderRepository.save(any(PaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentGatewayPort.createPrepay(any(PaymentOrder.class))).thenReturn(failureResponse);

        assertThatThrownBy(() -> service.createPrepay(command, "TestSystem", "192.168.1.1"))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("银行网关调用失败");
    }

    @Test
    @DisplayName("创建预支付：未传expiredSeconds时使用默认值")
    void createPrepay_noExpiredSeconds_usesDefault() {
        CreatePrepayCommand command = new CreatePrepayCommand(
            "BIZ002", 5000L, "测试", null,
            null, "https://example.com/notify", null, null,
            9, 9, "openId123", null
        );
        when(paymentOrderRepository.save(any(PaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentGatewayPort.createPrepay(any(PaymentOrder.class))).thenReturn(createSuccessPrepayResponse());

        PrepayOrderResponse response = service.createPrepay(command, "TestSystem", "10.0.0.1");

        assertThat(response).isNotNull();
        // 验证 gateway 被调用了
        verify(paymentGatewayPort).createPrepay(any(PaymentOrder.class));
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn test -pl . -Dtest=PrepayAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false -q 2>&1 | tail -5`
Expected: FAIL — class not found

- [ ] **Step 3: Write minimal implementation**

```java
// src/main/java/com/aieducenter/payment/application/PrepayAppService.java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.command.CreatePrepayCommand;
import com.aieducenter.payment.application.dto.response.PrepayOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.CreatePrepayResponse;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrepayAppService {

    private static final Logger log = LoggerFactory.getLogger(PrepayAppService.class);

    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PaymentLogRepository paymentLogRepository;

    @Value("${payment.default-expired-seconds:3600}")
    private Long defaultExpiredSeconds;

    @Transactional
    public PrepayOrderResponse createPrepay(CreatePrepayCommand command, String businessSystemName, String clientIp) {
        Long expiredSeconds = command.expiredSeconds() != null ? command.expiredSeconds() : defaultExpiredSeconds;

        // 1. 创建支付订单
        PaymentOrder paymentOrder = new PaymentOrder(
            command.businessOrderNo(),
            businessSystemName,
            command.businessName(),
            command.amount(),
            command.subject(),
            command.body(),
            command.notifyUrl(),
            command.attach(),
            expiredSeconds
        );

        // 设置客户端 IP
        paymentOrder.setClientIp(clientIp);

        // 设置预支付特有字段
        paymentOrder.setPayMode(PayMode.values()[0]); // 先设置，后面根据code重新赋值
        for (PayMode pm : PayMode.values()) {
            if (pm.getCode().equals(command.payMode())) {
                paymentOrder.setPayMode(pm);
                break;
            }
        }
        for (AccessType at : AccessType.values()) {
            if (at.getCode().equals(command.accessType())) {
                paymentOrder.setAccessType(at);
                break;
            }
        }
        paymentOrder.setOpenId(command.openId());

        // 2. 保存订单
        PaymentOrder saved = paymentOrderRepository.save(paymentOrder);

        // 3. 调用银行网关创建预支付
        CreatePrepayResponse gatewayResponse = paymentGatewayPort.createPrepay(paymentOrder);

        // 4. 记录调用日志
        PaymentLog logEntry = new PaymentLog(
            paymentOrder.getPaymentOrderNo(),
            null,
            "PREPAY_REQUEST",
            "ICBC",
            "aggregatepay/b2c/online/consumepurchase",
            null,
            gatewayResponse.requestParams(),
            gatewayResponse.responseBody(),
            200,
            gatewayResponse.returnCode(),
            gatewayResponse.returnMsg(),
            gatewayResponse.executionTime(),
            gatewayResponse.success(),
            gatewayResponse.success() ? null : "银行调用失败"
        );
        paymentLogRepository.save(logEntry);

        // 5. 银行调用失败，抛异常
        if (!gatewayResponse.success()) {
            throw new RuntimeException("银行网关调用失败: " + gatewayResponse.returnMsg());
        }

        // 6. 保存预支付参数包
        if (gatewayResponse.dataPackage() != null) {
            paymentOrder.setPrepayDataPackage(gatewayResponse.dataPackage());
        }
        if (gatewayResponse.bankOrderNo() != null) {
            // bankOrderNo 暂存，回调时更新
        }
        if (gatewayResponse.tradeType() != null) {
            paymentOrder.setTradeType(gatewayResponse.tradeType());
        }
        paymentOrderRepository.save(paymentOrder);

        // 7. 返回响应
        return new PrepayOrderResponse(
            paymentOrder.getId(),
            paymentOrder.getBusinessOrderNo(),
            paymentOrder.getPaymentOrderNo(),
            paymentOrder.getBusinessSystemName(),
            paymentOrder.getStatus().getCode(),
            paymentOrder.getStatus().getName(),
            paymentOrder.getAmount(),
            paymentOrder.getPrepayDataPackage(),
            paymentOrder.getPayMode() != null ? paymentOrder.getPayMode().getCode() : null,
            paymentOrder.getPayMode() != null ? paymentOrder.getPayMode().getName() : null,
            paymentOrder.getAccessType() != null ? paymentOrder.getAccessType().getCode() : null,
            paymentOrder.getAccessType() != null ? paymentOrder.getAccessType().getName() : null,
            paymentOrder.getExpiredAt(),
            paymentOrder.getCreatedAt()
        );
    }
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `mvn test -pl . -Dtest=PrepayAppServiceTest -q 2>&1 | tail -10`
Expected: Tests run: 4, Failures: 0

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/PrepayAppService.java src/test/java/com/aieducenter/payment/application/PrepayAppServiceTest.java
git commit -m "feat(prepay): implement PrepayAppService with tests"
```

---

### Task 10: Endpoint — PrepayApiV1Controller

**Files:**
- Create: `src/main/java/com/aieducenter/payment/endpoints/api/v1/PrepayApiV1Controller.java`

- [ ] **Step 1: Create controller**

```java
// src/main/java/com/aieducenter/payment/endpoints/api/v1/PrepayApiV1Controller.java
package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.PrepayAppService;
import com.aieducenter.payment.application.dto.command.CreatePrepayCommand;
import com.aieducenter.payment.application.dto.response.PrepayOrderResponse;
import com.cartisan.core.context.RequestContext;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import com.cartisan.web.util.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Validated
@Tag(name = "Prepay API v1", description = "聚合支付预支付接口 v1")
public class PrepayApiV1Controller {

    private final PrepayAppService prepayAppService;

    @PostMapping("/prepay")
    @RequireSignature
    @Operation(summary = "创建预支付订单")
    public ApiResponse<PrepayOrderResponse> createPrepay(
            @Valid @RequestBody CreatePrepayCommand command,
            HttpServletRequest request
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        String clientIp = IpUtil.getClientIp(request);
        PrepayOrderResponse response = prepayAppService.createPrepay(command, businessSystemName, clientIp);
        return ApiResponse.ok(response);
    }
}
```

- [ ] **Step 2: Compile to verify**

Run: `mvn compile -pl . -q 2>&1 | tail -5`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/endpoints/api/v1/PrepayApiV1Controller.java
git commit -m "feat(prepay): add PrepayApiV1Controller with POST /api/v1/payments/prepay"
```

---

### Task 11: Callback Enhancement — payMode/accessType Backfill

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/application/PaymentCallbackAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/PaymentCallbackAppServiceBackfillTest.java`

- [ ] **Step 1: Write the failing test**

```java
// src/test/java/com/aieducenter/payment/application/PaymentCallbackAppServiceBackfillTest.java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.IcbcCallbackParam;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.aieducenter.payment.infrastructure.icbc.IcbcCallbackVerifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("回调回填 payMode/accessType 测试")
class PaymentCallbackAppServiceBackfillTest {

    @Mock private PaymentOrderRepository paymentOrderRepository;
    @Mock private PaymentLogRepository paymentLogRepository;
    @Mock private IcbcCallbackVerifier icbcCallbackVerifier;
    @Mock private BusinessSystemNotifier businessSystemNotifier;

    private PaymentCallbackAppService service;

    @BeforeEach
    void setUp() {
        service = new PaymentCallbackAppService(
            paymentOrderRepository, paymentLogRepository, icbcCallbackVerifier, businessSystemNotifier
        );
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.initSynchronization();
        }
    }

    @AfterEach
    void tearDown() {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.clearSynchronization();
        }
    }

    @Test
    @DisplayName("回调时从 pay_type 回填 payMode")
    void handleCallback_success_backfillsPayMode() {
        PaymentOrder order = new PaymentOrder(
            "BIZ001", "TestSystem", "课程购买",
            10000L, "Python", "Course",
            "https://example.com/notify", null, 3600L
        );

        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));
        when(paymentOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(icbcCallbackVerifier.generateResponseMsgId()).thenReturn("msg-id");
        when(icbcCallbackVerifier.buildCallbackResponse(any())).thenReturn("{}");

        // pay_type=9 表示微信支付
        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "0", "success", "0", "msg-001",
            "PAY001", "ICBC_ORDER", "10000", "10000",
            "20240112121212", "9", "7",
            "", "1", "2", "openId123",
            "THIRD_456", "0", "0", "0", "0", "0", "0",
            "", "020001021935"
        );

        service.handleCallback("/api/v1/payment/callbacks/icbc", Map.of("biz_content", "{}"), "sign", callbackParam);

        assertThat(order.getPayMode()).isEqualTo(PayMode.WECHAT);
        assertThat(order.getAccessType()).isEqualTo(AccessType.WECHAT_OA);
    }

    @Test
    @DisplayName("回调时支付宝 pay_type=10 回填 payMode")
    void handleCallback_alipay_backfillsPayMode() {
        PaymentOrder order = new PaymentOrder(
            "BIZ002", "TestSystem", "课程购买",
            10000L, "Python", "Course",
            "https://example.com/notify", null, 3600L
        );

        when(paymentOrderRepository.findByPaymentOrderNo("PAY002")).thenReturn(Optional.of(order));
        when(paymentOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(icbcCallbackVerifier.generateResponseMsgId()).thenReturn("msg-id");
        when(icbcCallbackVerifier.buildCallbackResponse(any())).thenReturn("{}");

        // pay_type=10 支付宝
        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "0", "success", "0", "msg-002",
            "PAY002", "ICBC_ORDER", "10000", "10000",
            "20240112121212", "10", "8",
            "", "1", "2", "",
            "THIRD_789", "0", "0", "0", "0", "0", "0",
            "", "020001021935"
        );

        service.handleCallback("/api/v1/payment/callbacks/icbc", Map.of(), "sign", callbackParam);

        assertThat(order.getPayMode()).isEqualTo(PayMode.ALIPAY);
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn test -pl . -Dtest=PaymentCallbackAppServiceBackfillTest -Dsurefire.failIfNoSpecifiedTests=false -q 2>&1 | tail -10`
Expected: FAIL — payMode is null (not backfilled yet)

- [ ] **Step 3: Modify PaymentCallbackAppService to backfill payMode/accessType**

在 `PaymentCallbackAppService.java` 中：

**3a. 添加 import：**
```java
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.AccessType;
```

**3b. 在 `handleCallback()` 方法中，`markAsPaid` 调用之后、`paymentOrderRepository.save(order)` 之前，添加回填逻辑：**

找到这段代码：
```java
        if (callback.isPaymentSuccess()) {
            Long actualAmount = parseAmount(callback.paymentAmt());
            PaymentChannel paymentChannel = mapPaymentChannel(callback.payType());
            order.markAsPaid(callback.orderId(), callback.thirdTradeNo(), paymentChannel, actualAmount);
        } else {
```

替换为：
```java
        if (callback.isPaymentSuccess()) {
            Long actualAmount = parseAmount(callback.paymentAmt());
            PaymentChannel paymentChannel = mapPaymentChannel(callback.payType());
            order.markAsPaid(callback.orderId(), callback.thirdTradeNo(), paymentChannel, actualAmount);

            // 回填 payMode 和 accessType
            if (order.getPayMode() == null) {
                PayMode payMode = mapPayMode(callback.payType());
                if (payMode != null) {
                    order.setPayMode(payMode);
                }
            }
            if (order.getAccessType() == null) {
                AccessType accessType = mapAccessType(callback.accessType());
                if (accessType != null) {
                    order.setAccessType(accessType);
                }
            }
        } else {
```

**3c. 在 `mapPaymentChannel()` 方法后面新增两个映射方法：**

```java
    private PayMode mapPayMode(String payType) {
        if (payType == null) return null;
        for (PayMode pm : PayMode.values()) {
            if (pm.getCode().toString().equals(payType)) {
                return pm;
            }
        }
        return null;
    }

    private AccessType mapAccessType(String accessType) {
        if (accessType == null) return null;
        for (AccessType at : AccessType.values()) {
            if (at.getCode().toString().equals(accessType)) {
                return at;
            }
        }
        return null;
    }
```

- [ ] **Step 4: Run test to verify it passes**

Run: `mvn test -pl . -Dtest=PaymentCallbackAppServiceBackfillTest -q 2>&1 | tail -5`
Expected: Tests run: 2, Failures: 0

- [ ] **Step 5: Run existing callback tests to verify no regression**

Run: `mvn test -pl . -Dtest=PaymentCallbackAppServiceTest -q 2>&1 | tail -5`
Expected: Tests run: 5, Failures: 0

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/PaymentCallbackAppService.java src/test/java/com/aieducenter/payment/application/PaymentCallbackAppServiceBackfillTest.java
git commit -m "feat(prepay): backfill payMode/accessType from callback pay_type/access_type"
```

---

### Task 12: Existing Payment Enhancement — Set accessType=H5

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/application/PaymentAppService.java`

- [ ] **Step 1: Modify PaymentAppService.createPayment() to set accessType**

在 `PaymentAppService.java` 中：

**1a. 添加 import：**
```java
import com.aieducenter.payment.domain.enums.AccessType;
```

**1b. 在 `createPayment()` 方法中，`paymentOrder.setClientIp(clientIp);` 之后添加：**

```java
        // 二维码支付设置 accessType = H5
        paymentOrder.setAccessType(AccessType.H5);
```

- [ ] **Step 2: Run existing payment tests to verify no regression**

Run: `mvn test -pl . -Dtest=PaymentOrderTest,PaymentCallbackAppServiceTest -q 2>&1 | tail -5`
Expected: All pass

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/PaymentAppService.java
git commit -m "feat(prepay): set accessType=H5 for existing QR code payments"
```

---

### Task 13: Full Compile & Test Verification

- [ ] **Step 1: Full compile**

Run: `mvn compile -pl . -q 2>&1 | tail -10`
Expected: BUILD SUCCESS

- [ ] **Step 2: Run all tests**

Run: `mvn test -pl . -q 2>&1 | tail -15`
Expected: All tests pass, no failures

- [ ] **Step 3: Run architecture test**

Run: `mvn test -pl . -Dtest=ArchitectureTest -q 2>&1 | tail -5`
Expected: Pass (verify new files don't violate layering rules)

- [ ] **Step 4: Final commit if any fixes needed**

---

### Task 14: Package Verification

- [ ] **Step 1: Package the application**

Run: `mvn package -DskipTests -q 2>&1 | tail -5`
Expected: BUILD SUCCESS

- [ ] **Step 2: Verify the JAR is created**

Run: `ls -la target/*.jar 2>&1 | tail -3`
Expected: JAR file exists

---

## Summary

| Task | Component | New/Modified | TDD |
|------|-----------|-------------|-----|
| 1 | PayMode enum | New | Yes |
| 2 | AccessType enum | New | Yes |
| 3 | PaymentOrder entity | Modified | Yes |
| 4 | CreatePrepayResponse + Port | New/Modified | No (record + interface) |
| 5 | Database migration | New | No (SQL) |
| 6 | IcbcConfig | Modified | No (config) |
| 7 | IcbcPaymentGatewayAdapter | Modified | No (adapter impl) |
| 8 | Application DTOs | New | No (records) |
| 9 | PrepayAppService | New | Yes |
| 10 | PrepayApiV1Controller | New | No (controller) |
| 11 | Callback backfill | Modified | Yes |
| 12 | Existing payment H5 | Modified | No (1 line) |
| 13 | Full verification | - | - |
| 14 | Package verification | - | - |
