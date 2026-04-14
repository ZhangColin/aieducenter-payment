# ICBC Payment Callback Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Implement the two-layer callback mechanism — receive ICBC payment result callbacks and forward results to business systems.

**Architecture:** DDD layers — a new controller receives ICBC POST callbacks, an application service orchestrates verification/order-update/notification, ICBC-specific signature logic lives in infrastructure, and a business notifier sends HTTP POST JSON to business systems.

**Tech Stack:** Spring Boot, JPA/Hibernate, Hutool (crypto), FastJSON2, PostgreSQL, Flyway, JUnit 5, AssertJ, MockMvc

**Design Spec:** `docs/superpowers/specs/2026-04-09-icbc-callback-design.md`

---

## File Map

### New Files

| File | Responsibility |
|---|---|
| `src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentCallbackController.java` | REST endpoint: `POST /api/v1/payment/callbacks/icbc` |
| `src/main/java/com/aieducenter/payment/application/PaymentCallbackAppService.java` | Orchestrate callback: verify → update → log → notify |
| `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcCallbackVerifier.java` | RSA signature verification + RSA2 response signing |
| `src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java` | HTTP POST JSON to business system notifyUrl |
| `src/main/java/com/aieducenter/payment/application/dto/callback/IcbcCallbackParam.java` | Parsed ICBC callback data (from biz_content) |
| `src/main/java/com/aieducenter/payment/application/dto/callback/BusinessNotifyRequest.java` | Request body sent to business system |
| `src/main/resources/db/migration/V3__add_actual_amount.sql` | Add actual_amount column to payment orders table |
| `src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderCallbackTest.java` | Tests for actualAmount + markAsPaid changes |
| `src/test/java/com/aieducenter/payment/infrastructure/icbc/IcbcCallbackVerifierTest.java` | Tests for signature verify + response sign |
| `src/test/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifierTest.java` | Tests for business notification |
| `src/test/java/com/aieducenter/payment/application/PaymentCallbackAppServiceTest.java` | Tests for callback orchestration |

### Modified Files

| File | Change |
|---|---|
| `src/main/java/com/aieducenter/payment/domain/aggregate/PaymentOrder.java` | Add `actualAmount` field, update `markAsPaid()` signature |
| `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcConfig.java` | Add `notifyBaseUrl` field |
| `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java` | Change `mer_url` to use config, change `result_type` to `"0"` |
| `src/main/resources/application-local.yml` | Add `gateway.icbc.*` config section |
| `src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderTest.java` | Update existing markAsPaid test for new signature |

---

## Task 1: Add `actualAmount` to PaymentOrder Domain Model

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/domain/aggregate/PaymentOrder.java`
- Create: `src/main/resources/db/migration/V3__add_actual_amount.sql`
- Create: `src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderCallbackTest.java`
- Modify: `src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderTest.java`

- [ ] **Step 1: Write failing test for markAsPaid with actualAmount**

Create `src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderCallbackTest.java`:

```java
package com.aieducenter.payment.domain.aggregate;

import com.aieducenter.payment.domain.enums.PaymentMethod;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("支付订单回调相关测试")
class PaymentOrderCallbackTest {

    private PaymentOrder createPendingOrder() {
        return new PaymentOrder(
            "ORDER001", "TestSystem", "课程购买",
            10000L, "Python课程", "Python编程课程",
            "127.0.0.1", "https://example.com/notify",
            "https://example.com/return", null, null
        );
    }

    @Test
    @DisplayName("支付成功时应记录实际支付金额")
    void given_pending_order_when_markAsPaid_then_actualAmount_recorded() {
        PaymentOrder order = createPendingOrder();

        order.markAsPaid("ICBC_ORDER_123", "THIRD_456", PaymentMethod.WECHAT, 9800L);

        assertThat(order.getStatus()).isEqualTo(PaymentStatus.PAID);
        assertThat(order.getActualAmount()).isEqualTo(9800L);
        assertThat(order.getBankOrderNo()).isEqualTo("ICBC_ORDER_123");
        assertThat(order.getThirdPartyOrderNo()).isEqualTo("THIRD_456");
        assertThat(order.getPaymentMethod()).isEqualTo(PaymentMethod.WECHAT);
        assertThat(order.getPaidAt()).isNotNull();
    }

    @Test
    @DisplayName("标记支付成功时实际支付金额可以为空")
    void given_pending_order_when_markAsPaid_without_actualAmount_then_success() {
        PaymentOrder order = createPendingOrder();

        order.markAsPaid("ICBC_ORDER_123", "THIRD_456", PaymentMethod.WECHAT, null);

        assertThat(order.getStatus()).isEqualTo(PaymentStatus.PAID);
        assertThat(order.getActualAmount()).isNull();
    }

    @Test
    @DisplayName("已支付的订单不能再次标记为已支付")
    void given_paid_order_when_markAsPaid_then_throw() {
        PaymentOrder order = createPendingOrder();
        order.markAsPaid("ICBC_1", null, PaymentMethod.ICBC, 10000L);

        assertThatThrownBy(() ->
            order.markAsPaid("ICBC_2", null, PaymentMethod.WECHAT, 5000L))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test -pl . -Dtest=PaymentOrderCallbackTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: FAIL — `markAsPaid(String, String, PaymentMethod, Long)` method does not exist

- [ ] **Step 3: Update existing markAsPaid test**

In `src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderTest.java`, find the existing `markAsPaid` test and update the call from:
```java
paymentOrder.markAsPaid("ICBC123", "THIRD456", PaymentMethod.WECHAT);
```
to:
```java
paymentOrder.markAsPaid("ICBC123", "THIRD456", PaymentMethod.WECHAT, 10000L);
```

Search for all occurrences of `markAsPaid(` in the file and update them to include the 4th parameter.

- [ ] **Step 4: Implement actualAmount in PaymentOrder entity**

In `src/main/java/com/aieducenter/payment/domain/aggregate/PaymentOrder.java`:

Add field after `thirdPartyOrderNo` (around line 95):
```java
    @Getter
    @Column(name = "actual_amount")
    private Long actualAmount;
```

Change `markAsPaid` method signature (around line 155):
```java
    /**
     * 支付成功
     */
    public void markAsPaid(String bankOrderNo, String thirdPartyOrderNo, PaymentMethod paymentMethod, Long actualAmount) {
        Assertions.require(this.status == PaymentStatus.PENDING,
            PaymentMessage.PAYMENT_ORDER_NOT_PENDING);

        this.status = PaymentStatus.PAID;
        this.paidAt = LocalDateTime.now();
        this.bankOrderNo = bankOrderNo;
        this.thirdPartyOrderNo = thirdPartyOrderNo;
        this.paymentMethod = paymentMethod;
        this.actualAmount = actualAmount;
    }
```

- [ ] **Step 5: Run all PaymentOrder tests to verify they pass**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test -pl . -Dtest="PaymentOrderTest,PaymentOrderCallbackTest"`
Expected: ALL PASS

- [ ] **Step 6: Create Flyway migration**

Create `src/main/resources/db/migration/V3__add_actual_amount.sql`:

> **Note:** V2 migration creates `payment_payment_orders` but the entity `@Table` annotation says `pay_payment_orders`.
> Use the entity's `@Table` name in the ALTER TABLE since JPA queries against that name.
> If this is the first time running V2, you may need to reconcile the table names first.

```sql
-- Add actual_amount column for recording the actual amount paid by the user
-- (may differ from order amount due to discounts, coupons, etc.)
ALTER TABLE pay_payment_orders ADD COLUMN actual_amount BIGINT;
```

- [ ] **Step 7: Fix other usages of markAsPaid**

Search the entire codebase for `markAsPaid(` calls and update them to the new 4-parameter signature. Key file to check:
- `src/main/java/com/aieducenter/payment/application/PaymentAppService.java` — the `queryPaymentStatus` method calls `markAsPaid`. Update to pass `queryResponse.paidAmount()` as the 4th arg. Also check `QueryPaymentResponse` — it has `paidAmount` which maps correctly.

Update `PaymentAppService.queryPaymentStatus()`:
```java
paymentOrder.markAsPaid(
    queryResponse.bankOrderNo(),
    queryResponse.thirdPartyOrderNo(),
    queryResponse.paymentMethod(),
    queryResponse.paidAmount()
);
```

- [ ] **Step 8: Run all tests**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test`
Expected: ALL PASS

- [ ] **Step 9: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/aggregate/PaymentOrder.java \
  src/main/resources/db/migration/V3__add_actual_amount.sql \
  src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderCallbackTest.java \
  src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderTest.java \
  src/main/java/com/aieducenter/payment/application/PaymentAppService.java
git commit -m "feat(payment): add actualAmount field to PaymentOrder for callback support"
```

---

## Task 2: Update IcbcConfig and Fix Adapter

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcConfig.java`
- Modify: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java`
- Modify: `src/main/resources/application-local.yml`

- [ ] **Step 1: Add notifyBaseUrl to IcbcConfig**

In `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcConfig.java`, add after the existing fields:

```java
    /**
     * 支付回调基础地址
     * <p>用于拼接工行异步通知地址: {notifyBaseUrl}/api/v1/payment/callbacks/icbc</p>
     */
    private String notifyBaseUrl;
```

- [ ] **Step 2: Fix IcbcPaymentGatewayAdapter**

In `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java`, make two changes:

**Change 1** — Replace `mer_url` line (around line 58):
```java
        // OLD: bizContent.setMer_url(paymentOrder.getNotifyUrl());
        bizContent.setMer_url(icbcConfig.getNotifyBaseUrl() + "/api/v1/payment/callbacks/icbc");
```

**Change 2** — Replace `result_type` line (around line 61):
```java
        // OLD: bizContent.setResult_type("1");
        bizContent.setResult_type("0");
```

- [ ] **Step 3: Add ICBC config to application-local.yml**

In `src/main/resources/application-local.yml`, append at the end:

```yaml
# 工行支付网关配置
gateway:
  icbc:
    env: local
    app-id: ${ICBC_APP_ID:dummy-app-id}
    mer-id: ${ICBC_MER_ID:dummy-mer-id}
    mer-prtcl-no: ${ICBC_MER_PRTL_NO:dummy-prtcl-no}
    private-key: ${ICBC_PRIVATE_KEY:dummy-private-key}
    public-key: ${ICBC_PUBLIC_KEY:dummy-public-key}
    notify-base-url: ${ICBC_NOTIFY_BASE_URL:http://localhost:8081}
    payment-url: ${ICBC_PAYMENT_URL:https://gw.open.icbc.com.cn/api/cardbusiness/qrcode/consumption/V1}
    payment-query-url: ${ICBC_PAYMENT_QUERY_URL:https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/orderqry/V1}
    refund-url: ${ICBC_REFUND_URL:https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/merrefund/V1}
```

- [ ] **Step 4: Run compilation to verify**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcConfig.java \
  src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java \
  src/main/resources/application-local.yml
git commit -m "feat(payment): add notifyBaseUrl config and fix result_type to receive all callbacks"
```

---

## Task 3: ICBC Callback Verifier (Signature Verification + Response Signing)

**Files:**
- Create: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcCallbackVerifier.java`
- Create: `src/test/java/com/aieducenter/payment/infrastructure/icbc/IcbcCallbackVerifierTest.java`

- [ ] **Step 1: Write failing tests for IcbcCallbackVerifier**

Create `src/test/java/com/aieducenter/payment/infrastructure/icbc/IcbcCallbackVerifierTest.java`:

```java
package com.aieducenter.payment.infrastructure.icbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("工行回调验签和响应签名测试")
class IcbcCallbackVerifierTest {

    private IcbcCallbackVerifier verifier;
    private String testPublicKey;
    private String testPrivateKey;

    @BeforeEach
    void setUp() throws Exception {
        // Generate RSA key pair for testing
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();

        // Public key simulates ICBC gateway public key (for verification)
        testPublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        // Private key simulates our merchant private key (for response signing)
        testPrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        IcbcConfig config = new IcbcConfig();
        config.setPublicKey(testPublicKey);
        config.setPrivateKey(testPrivateKey);

        verifier = new IcbcCallbackVerifier(config);
    }

    @Test
    @DisplayName("验签通过：正确的RSA签名")
    void verifySignature_withValidSign_returnsTrue() throws Exception {
        // Given: parameters from ICBC callback
        Map<String, String> params = new LinkedHashMap<>();
        params.put("api", "/api/cardbusiness/qrcode/consumption/V1");
        params.put("app_id", "10000000000004095781");
        params.put("biz_content", "{\"return_code\":\"0\",\"out_trade_no\":\"PAY001\"}");
        params.put("charset", "utf-8");
        params.put("format", "json");
        params.put("from", "icbc-api");
        params.put("sign_type", "RSA");
        params.put("timestamp", "2024-01-12 17:33:56");

        String path = "/api/v1/payment/callbacks/icbc";

        // Build plaintext and sign with private key (simulating ICBC)
        String plaintext = verifier.buildSignaturePlaintext(path, params);
        String sign = signWithSha1Rsa(plaintext, testPrivateKey);

        // When & Then
        assertThat(verifier.verifySignature(path, params, sign)).isTrue();
    }

    @Test
    @DisplayName("验签失败：篡改参数")
    void verifySignature_withTamperedParams_returnsFalse() throws Exception {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("api", "/api/cardbusiness/qrcode/consumption/V1");
        params.put("app_id", "10000000000004095781");
        params.put("biz_content", "{\"return_code\":\"0\"}");
        params.put("charset", "utf-8");
        params.put("format", "json");
        params.put("from", "icbc-api");
        params.put("sign_type", "RSA");
        params.put("timestamp", "2024-01-12 17:33:56");

        String path = "/api/v1/payment/callbacks/icbc";
        String plaintext = verifier.buildSignaturePlaintext(path, params);
        String sign = signWithSha1Rsa(plaintext, testPrivateKey);

        // Tamper with params
        params.put("biz_content", "{\"return_code\":\"1\"}");

        assertThat(verifier.verifySignature(path, params, sign)).isFalse();
    }

    @Test
    @DisplayName("构建签名原文：参数按ASCII升序拼接")
    void buildSignaturePlaintext_sortsParamsByAscii() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("timestamp", "2024-01-12 17:33:56");
        params.put("api", "/test");
        params.put("charset", "utf-8");

        String result = verifier.buildSignaturePlaintext("/callback", params);

        // api < charset < timestamp (ASCII order)
        assertThat(result).isEqualTo("/callback?api=/test&charset=utf-8&timestamp=2024-01-12 17:33:56");
    }

    @Test
    @DisplayName("构建工行应答：包含returnCode字段且格式紧凑")
    void buildResponse_containsReturnCodeAndIsCompact() {
        String response = verifier.buildCallbackResponse("msg-123");

        assertThat(response).contains("\"return_code\":0");
        assertThat(response).contains("\"returnCode\":\"0\"");
        assertThat(response).contains("\"msg_id\":\"msg-123\"");
        assertThat(response).contains("\"sign_type\":\"RSA2\"");
        assertThat(response).contains("\"sign\":");
        // No spaces or newlines in the signed content
        assertThat(response).doesNotContain("\n");
        assertThat(response).doesNotContain("\r");
    }

    private String signWithSha1Rsa(String plaintext, String privateKeyBase64) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyBase64);
        java.security.spec.PKCS8EncodedKeySpec keySpec = new java.security.spec.PKCS8EncodedKeySpec(keyBytes);
        java.security.PrivateKey privateKey = java.security.KeyFactory.getInstance("RSA").generatePrivate(keySpec);

        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(privateKey);
        signature.update(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(signature.sign());
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test -pl . -Dtest=IcbcCallbackVerifierTest`
Expected: FAIL — class does not exist

- [ ] **Step 3: Implement IcbcCallbackVerifier**

Create `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcCallbackVerifier.java`:

```java
package com.aieducenter.payment.infrastructure.icbc;

import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 工行回调验签和响应签名
 *
 * <p>处理两件事：
 * <ul>
 *   <li>验签：用工行网关公钥验证回调报文的RSA签名（SHA1WithRSA）</li>
 *   <li>签名：用商户私钥对响应报文签名（SHA256WithRSA，即RSA2）</li>
 * </ul>
 */
@Component
@Adapter(PortType.CLIENT)
@RequiredArgsConstructor
public class IcbcCallbackVerifier {

    private static final Logger log = LoggerFactory.getLogger(IcbcCallbackVerifier.class);

    private final IcbcConfig icbcConfig;

    /**
     * 构建签名原文
     *
     * <p>格式：请求路径path + "?" + 参数按ASCII升序拼接（排除sign字段）</p>
     */
    public String buildSignaturePlaintext(String path, Map<String, String> params) {
        TreeMap<String, String> sortedParams = new TreeMap<>(params);
        sortedParams.remove("sign");

        String queryString = sortedParams.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining("&"));

        return path + "?" + queryString;
    }

    /**
     * 验证工行回调签名
     *
     * <p>使用工行网关公钥验证SHA1WithRSA签名</p>
     *
     * @param path   请求路径
     * @param params 所有请求参数（包含sign）
     * @param sign   待验证的签名值
     * @return 验签是否通过
     */
    public boolean verifySignature(String path, Map<String, String> params, String sign) {
        try {
            String plaintext = buildSignaturePlaintext(path, params);
            PublicKey publicKey = loadPublicKey(icbcConfig.getPublicKey());

            Signature verifier = Signature.getInstance("SHA1WithRSA");
            verifier.initVerify(publicKey);
            verifier.update(plaintext.getBytes(StandardCharsets.UTF_8));

            return verifier.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            log.error("ICBC callback signature verification failed", e);
            return false;
        }
    }

    /**
     * 构建工行回调应答
     *
     * <p>返回完整的应答JSON，包含签名</p>
     * <p>格式要求：response_biz_content, sign_type, sign 顺序，无空格换行</p>
     *
     * @param msgId 消息ID（必须唯一）
     * @return 完整的应答JSON字符串
     */
    public String buildCallbackResponse(String msgId) {
        String responseBizContent = "{\"return_code\":0,\"return_msg\":\"success\",\"returnCode\":\"0\",\"msg_id\":\"" + msgId + "\"}";
        String signType = "RSA2";

        // 签名字符串 = "response_biz_content":<value>,"sign_type":"<value>" (不含空格换行)
        String signContent = "\"response_biz_content\":" + responseBizContent + ",\"sign_type\":\"" + signType + "\"";

        String signature = signWithRsa2(signContent);

        return "{\"response_biz_content\":" + responseBizContent + ",\"sign_type\":\"" + signType + "\",\"sign\":\"" + signature + "\"}";
    }

    /**
     * 生成唯一的消息ID
     */
    public String generateResponseMsgId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String signWithRsa2(String content) {
        try {
            PrivateKey privateKey = loadPrivateKey(icbcConfig.getPrivateKey());

            Signature signer = Signature.getInstance("SHA256WithRSA");
            signer.initSign(privateKey);
            signer.update(content.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(signer.sign());
        } catch (Exception e) {
            log.error("Failed to sign response", e);
            throw new RuntimeException("签名失败", e);
        }
    }

    private PublicKey loadPublicKey(String publicKeyBase64) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

    private PrivateKey loadPrivateKey(String privateKeyBase64) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }
}
```

- [ ] **Step 4: Run tests to verify they pass**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test -pl . -Dtest=IcbcCallbackVerifierTest`
Expected: ALL PASS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcCallbackVerifier.java \
  src/test/java/com/aieducenter/payment/infrastructure/icbc/IcbcCallbackVerifierTest.java
git commit -m "feat(payment): add ICBC callback signature verifier with RSA verify and RSA2 sign"
```

---

## Task 4: Business System Notifier

**Files:**
- Create: `src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java`
- Create: `src/main/java/com/aieducenter/payment/application/dto/callback/BusinessNotifyRequest.java`
- Create: `src/test/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifierTest.java`

- [ ] **Step 1: Create BusinessNotifyRequest DTO**

Create `src/main/java/com/aieducenter/payment/application/dto/callback/BusinessNotifyRequest.java`:

```java
package com.aieducenter.payment.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * 通知业务系统的请求体
 */
public record BusinessNotifyRequest(
    @JSONField(name = "orderId")
    String orderId,

    @JSONField(name = "outTradeNo")
    String outTradeNo,

    @JSONField(name = "status")
    String status,

    @JSONField(name = "amount")
    String amount,

    @JSONField(name = "actualAmount")
    String actualAmount,

    @JSONField(name = "bankOrderNo")
    String bankOrderNo,

    @JSONField(name = "payTime")
    String payTime,

    @JSONField(name = "payType")
    String payType,

    @JSONField(name = "attach")
    String attach
) {}
```

- [ ] **Step 2: Write failing test for BusinessSystemNotifier**

Create `src/test/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifierTest.java`:

```java
package com.aieducenter.payment.infrastructure;

import com.aieducenter.payment.application.dto.callback.BusinessNotifyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("业务系统通知测试")
class BusinessSystemNotifierTest {

    private BusinessSystemNotifier notifier;

    @BeforeEach
    void setUp() {
        notifier = new BusinessSystemNotifier();
    }

    @Test
    @DisplayName("构建通知请求体：正确映射字段")
    void buildRequest_mapsFieldsCorrectly() {
        BusinessNotifyRequest request = new BusinessNotifyRequest(
            "PAY001",
            "BIZ001",
            "PAID",
            "10000",
            "9800",
            "ICBC_ORDER_123",
            "2024-01-12 12:12:12",
            "9",
            "custom-attach-data"
        );

        assertThat(request.orderId()).isEqualTo("PAY001");
        assertThat(request.outTradeNo()).isEqualTo("BIZ001");
        assertThat(request.status()).isEqualTo("PAID");
        assertThat(request.amount()).isEqualTo("10000");
        assertThat(request.actualAmount()).isEqualTo("9800");
    }

    @Test
    @DisplayName("notifyUrl为空时不发送通知且不抛异常")
    void notify_withNullUrl_doesNotThrow() {
        BusinessNotifyRequest request = new BusinessNotifyRequest(
            "PAY001", "BIZ001", "PAID", "10000", "9800",
            "ICBC_123", "2024-01-12 12:12:12", "9", null
        );

        assertThatNoException().isThrownBy(() -> notifier.notify(null, request));
        assertThatNoException().isThrownBy(() -> notifier.notify("", request));
    }
}
```

- [ ] **Step 3: Run test to verify it fails**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test -pl . -Dtest=BusinessSystemNotifierTest`
Expected: FAIL — class does not exist

- [ ] **Step 4: Implement BusinessSystemNotifier**

Create `src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java`:

```java
package com.aieducenter.payment.infrastructure;

import com.aieducenter.payment.application.dto.callback.BusinessNotifyRequest;
import com.alibaba.fastjson2.JSON;
import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * 业务系统通知器
 *
 * <p>支付结果异步通知业务系统。POST JSON 到业务系统提供的 notifyUrl。</p>
 * <p>不重试。业务系统可通过查询接口主动获取结果。</p>
 */
@Component
@Adapter(PortType.CLIENT)
public class BusinessSystemNotifier {

    private static final Logger log = LoggerFactory.getLogger(BusinessSystemNotifier.class);

    private final HttpClient httpClient;

    public BusinessSystemNotifier() {
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    }

    /**
     * 通知业务系统
     *
     * @param notifyUrl 业务系统回调地址
     * @param request   通知内容
     */
    public void notify(String notifyUrl, BusinessNotifyRequest request) {
        if (notifyUrl == null || notifyUrl.isBlank()) {
            log.debug("notifyUrl is empty, skip notification. orderId={}", request.orderId());
            return;
        }

        try {
            String body = JSON.toJSONString(request);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(notifyUrl))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

            log.info("Notifying business system: url={}, orderId={}", notifyUrl, request.orderId());

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                log.info("Business system notification succeeded: orderId={}, status={}", request.orderId(), response.statusCode());
            } else {
                log.warn("Business system notification returned non-200: orderId={}, status={}, body={}",
                    request.orderId(), response.statusCode(), response.body());
            }
        } catch (Exception e) {
            log.warn("Business system notification failed: orderId={}, url={}, error={}",
                request.orderId(), notifyUrl, e.getMessage());
        }
    }
}
```

- [ ] **Step 5: Run tests**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test -pl . -Dtest=BusinessSystemNotifierTest`
Expected: ALL PASS

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java \
  src/main/java/com/aieducenter/payment/application/dto/callback/BusinessNotifyRequest.java \
  src/test/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifierTest.java
git commit -m "feat(payment): add business system notifier for payment result forwarding"
```

---

## Task 5: Create IcbcCallbackParam DTO + PaymentCallbackAppService

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/dto/callback/IcbcCallbackParam.java`
- Create: `src/main/java/com/aieducenter/payment/application/PaymentCallbackAppService.java`
- Create: `src/test/java/com/aieducenter/payment/application/PaymentCallbackAppServiceTest.java`

- [ ] **Step 1: Create IcbcCallbackParam DTO**

Create `src/main/java/com/aieducenter/payment/application/dto/callback/IcbcCallbackParam.java`:

```java
package com.aieducenter.payment.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * 工行回调解析后的业务数据
 *
 * <p>对应工行回调 biz_content 中的字段（snake_case JSON → camelCase Java）</p>
 */
public record IcbcCallbackParam(
    @JSONField(name = "return_code") String returnCode,
    @JSONField(name = "return_msg") String returnMsg,
    @JSONField(name = "returnCode") String returnCode2,
    @JSONField(name = "msg_id") String msgId,
    @JSONField(name = "out_trade_no") String outTradeNo,
    @JSONField(name = "order_id") String orderId,
    @JSONField(name = "total_amt") String totalAmt,
    @JSONField(name = "payment_amt") String paymentAmt,
    @JSONField(name = "pay_time") String payTime,
    @JSONField(name = "pay_type") String payType,
    @JSONField(name = "access_type") String accessType,
    @JSONField(name = "card_no") String cardNo,
    @JSONField(name = "card_flag") String cardFlag,
    @JSONField(name = "decr_flag") String decrFlag,
    @JSONField(name = "open_id") String openId,
    @JSONField(name = "third_trade_no") String thirdTradeNo,
    @JSONField(name = "total_disc_amt") String totalDiscAmt,
    @JSONField(name = "point_amt") String pointAmt,
    @JSONField(name = "ecoupon_amt") String ecouponAmt,
    @JSONField(name = "mer_disc_amt") String merDiscAmt,
    @JSONField(name = "coupon_amt") String couponAmt,
    @JSONField(name = "bank_disc_amt") String bankDiscAmt,
    @JSONField(name = "attach") String attach,
    @JSONField(name = "mer_id") String merId
) {
    /**
     * 判断支付是否成功
     */
    public boolean isPaymentSuccess() {
        return "0".equals(returnCode);
    }
}
```

- [ ] **Step 2: Write failing test for PaymentCallbackAppService**

Create `src/test/java/com/aieducenter/payment/application/PaymentCallbackAppServiceTest.java`:

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.BusinessNotifyRequest;
import com.aieducenter.payment.application.dto.callback.IcbcCallbackParam;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.PaymentMethod;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.aieducenter.payment.infrastructure.icbc.IcbcCallbackVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("支付回调应用服务测试")
class PaymentCallbackAppServiceTest {

    @Mock private PaymentOrderRepository paymentOrderRepository;
    @Mock private PaymentLogRepository paymentLogRepository;
    @Mock private IcbcCallbackVerifier icbcCallbackVerifier;
    @Mock private BusinessSystemNotifier businessSystemNotifier;

    private PaymentCallbackAppService service;

    @BeforeEach
    void setUp() {
        service = new PaymentCallbackAppService(
            paymentOrderRepository,
            paymentLogRepository,
            icbcCallbackVerifier,
            businessSystemNotifier
        );
    }

    private PaymentOrder createPaidOrder() {
        PaymentOrder order = new PaymentOrder(
            "BIZ001", "TestSystem", "课程购买",
            10000L, "Python课程", "Python编程课程",
            "127.0.0.1", "https://biz.example.com/notify",
            "https://biz.example.com/return", "attach-data", 3600L
        );
        return order;
    }

    @Test
    @DisplayName("支付成功回调：更新订单为PAID并通知业务系统")
    void handleCallback_paymentSuccess_updatesOrderAndNotifies() {
        // Given
        PaymentOrder order = createPaidOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));
        when(paymentOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(icbcCallbackVerifier.buildCallbackResponse(anyString())).thenReturn("{\"response_biz_content\":{\"return_code\":0},\"sign_type\":\"RSA2\",\"sign\":\"xxx\"}");

        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "0", "success", "0", "msg-001",
            "PAY001", "ICBC_ORDER_123", "10000", "9800",
            "20240112121212", "9", "4",
            "", "1", "2", "",
            "THIRD_456", "200", "0", "0", "0", "0", "0",
            "attach-data", "020001021935"
        );

        Map<String, String> params = Map.of("biz_content", "{}");

        // When
        String response = service.handleCallback("/api/v1/payment/callbacks/icbc", params, "test-sign", callbackParam);

        // Then
        assertThat(order.getStatus()).isEqualTo(PaymentStatus.PAID);
        assertThat(order.getBankOrderNo()).isEqualTo("ICBC_ORDER_123");
        assertThat(order.getActualAmount()).isEqualTo(9800L);
        assertThat(response).isNotNull();

        // Verify log was written
        verify(paymentLogRepository).save(any(PaymentLog.class));

        // Verify business system was notified
        ArgumentCaptor<BusinessNotifyRequest> notifyCaptor = ArgumentCaptor.forClass(BusinessNotifyRequest.class);
        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), notifyCaptor.capture());
        BusinessNotifyRequest notifyRequest = notifyCaptor.getValue();
        assertThat(notifyRequest.status()).isEqualTo("PAID");
        assertThat(notifyRequest.outTradeNo()).isEqualTo("BIZ001");
    }

    @Test
    @DisplayName("支付失败回调：更新订单为FAILED")
    void handleCallback_paymentFailed_updatesOrderToFailed() {
        PaymentOrder order = createPaidOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY002")).thenReturn(Optional.of(order));
        when(paymentOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(icbcCallbackVerifier.buildCallbackResponse(anyString())).thenReturn("{}");

        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "21003116", "查询验证不通过", "21003116", "msg-002",
            "PAY002", "", "", "",
            "", "", "", "", "", "", "",
            "", "", "", "", "", "", "",
            "", ""
        );

        String response = service.handleCallback("/api/v1/payment/callbacks/icbc", Map.of(), "sign", callbackParam);

        assertThat(order.getStatus()).isEqualTo(PaymentStatus.FAILED);
        verify(businessSystemNotifier).notify(anyString(), any());
    }

    @Test
    @DisplayName("签名验证失败：返回错误响应，不更新订单")
    void handleCallback_signatureInvalid_returnsErrorWithoutUpdate() {
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(false);

        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "0", "success", "0", "msg-003", "PAY003", "", "10000", "10000",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
        );

        String response = service.handleCallback("/api/v1/payment/callbacks/icbc", Map.of(), "bad-sign", callbackParam);

        verify(paymentOrderRepository, never()).save(any());
        verify(businessSystemNotifier, never()).notify(anyString(), any());
    }

    @Test
    @DisplayName("订单不存在：记录日志但不抛异常")
    void handleCallback_orderNotFound_returnsResponse() {
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(paymentOrderRepository.findByPaymentOrderNo("NOTFOUND")).thenReturn(Optional.empty());
        when(icbcCallbackVerifier.buildCallbackResponse(anyString())).thenReturn("{}");

        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "0", "success", "0", "msg-004", "NOTFOUND", "", "10000", "10000",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
        );

        String response = service.handleCallback("/api/v1/payment/callbacks/icbc", Map.of(), "sign", callbackParam);

        assertThat(response).isNotNull();
        verify(paymentOrderRepository, never()).save(any());
    }

    @Test
    @DisplayName("已支付订单收到重复回调：忽略不重复处理")
    void handleCallback_alreadyPaid_ignoresDuplicate() {
        PaymentOrder order = createPaidOrder();
        order.markAsPaid("EXISTING_ICBC", null, PaymentMethod.WECHAT, 10000L);
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));
        when(icbcCallbackVerifier.buildCallbackResponse(anyString())).thenReturn("{}");

        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "0", "success", "0", "msg-005", "PAY001", "ICBC_NEW", "10000", "10000",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
        );

        String response = service.handleCallback("/api/v1/payment/callbacks/icbc", Map.of(), "sign", callbackParam);

        // Should NOT update (already PAID)
        verify(paymentOrderRepository, never()).save(any());
        // Should still notify (idempotent, business system can handle duplicates)
    }
}
```

- [ ] **Step 3: Run test to verify it fails**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test -pl . -Dtest=PaymentCallbackAppServiceTest`
Expected: FAIL — class does not exist

- [ ] **Step 4: Implement PaymentCallbackAppService**

Create `src/main/java/com/aieducenter/payment/application/PaymentCallbackAppService.java`:

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.BusinessNotifyRequest;
import com.aieducenter.payment.application.dto.callback.IcbcCallbackParam;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.PaymentMethod;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.aieducenter.payment.infrastructure.icbc.IcbcCallbackVerifier;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 支付回调应用服务
 *
 * <p>处理工行支付结果回调，编排：验签 → 更新订单 → 记录日志 → 通知业务系统 → 返回应答</p>
 */
@Service
@RequiredArgsConstructor
public class PaymentCallbackAppService {

    private static final Logger log = LoggerFactory.getLogger(PaymentCallbackAppService.class);
    private static final DateTimeFormatter ICBC_PAY_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter DISPLAY_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentLogRepository paymentLogRepository;
    private final IcbcCallbackVerifier icbcCallbackVerifier;
    private final BusinessSystemNotifier businessSystemNotifier;

    /**
     * 处理工行回调
     *
     * @param path     请求路径
     * @param params   原始请求参数
     * @param sign     签名
     * @param callback 解析后的回调数据
     * @return 工行要求的应答JSON
     */
    @Transactional
    public String handleCallback(String path, Map<String, String> params, String sign, IcbcCallbackParam callback) {
        log.info("Received ICBC callback: outTradeNo={}, returnCode={}", callback.outTradeNo(), callback.returnCode());

        // 1. 验签
        if (!icbcCallbackVerifier.verifySignature(path, params, sign)) {
            log.warn("ICBC callback signature verification failed: outTradeNo={}", callback.outTradeNo());
            return buildErrorResponse("签名验证失败");
        }

        // 2. 查找订单
        PaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(callback.outTradeNo()).orElse(null);
        if (order == null) {
            log.warn("ICBC callback: order not found, outTradeNo={}", callback.outTradeNo());
            return icbcCallbackVerifier.buildCallbackResponse(icbcCallbackVerifier.generateResponseMsgId());
        }

        // 3. 记录日志（无论是否成功都记录）
        saveCallbackLog(order.getPaymentOrderNo(), callback);

        // 4. 幂等检查：只有 PENDING 状态才处理
        if (order.getStatus() != PaymentStatus.PENDING) {
            log.info("ICBC callback: order already in terminal state, orderId={}, status={}",
                order.getPaymentOrderNo(), order.getStatus());
            return icbcCallbackVerifier.buildCallbackResponse(icbcCallbackVerifier.generateResponseMsgId());
        }

        // 5. 更新订单状态
        if (callback.isPaymentSuccess()) {
            Long actualAmount = parseAmount(callback.paymentAmt());
            PaymentMethod paymentMethod = mapPaymentMethod(callback.payType());
            order.markAsPaid(callback.orderId(), callback.thirdTradeNo(), paymentMethod, actualAmount);
        } else {
            order.markAsFailed(callback.returnMsg());
        }
        paymentOrderRepository.save(order);

        // 6. 通知业务系统
        notifyBusinessSystem(order, callback);

        // 7. 返回工行应答
        return icbcCallbackVerifier.buildCallbackResponse(icbcCallbackVerifier.generateResponseMsgId());
    }

    private void saveCallbackLog(String paymentOrderNo, IcbcCallbackParam callback) {
        PaymentLog logEntry = new PaymentLog(
            paymentOrderNo,
            null,
            "PAYMENT_CALLBACK",
            "ICBC",
            "qrcode/consumption/callback",
            null,
            null,
            JSON.toJSONString(callback),
            200,
            callback.returnCode(),
            callback.returnMsg(),
            null,
            callback.isPaymentSuccess(),
            null
        );
        paymentLogRepository.save(logEntry);
    }

    private void notifyBusinessSystem(PaymentOrder order, IcbcCallbackParam callback) {
        String payTime = formatPayTime(callback.payTime());

        BusinessNotifyRequest request = new BusinessNotifyRequest(
            order.getPaymentOrderNo(),
            order.getBusinessOrderNo(),
            order.getStatus().name(),
            String.valueOf(order.getAmount()),
            order.getActualAmount() != null ? String.valueOf(order.getActualAmount()) : null,
            callback.orderId(),
            payTime,
            callback.payType(),
            order.getAttach()
        );

        businessSystemNotifier.notify(order.getNotifyUrl(), request);
    }

    private String formatPayTime(String icbcPayTime) {
        if (icbcPayTime == null || icbcPayTime.isEmpty()) {
            return null;
        }
        try {
            LocalDateTime time = LocalDateTime.parse(icbcPayTime, ICBC_PAY_TIME_FORMAT);
            return time.format(DISPLAY_TIME_FORMAT);
        } catch (Exception e) {
            return icbcPayTime;
        }
    }

    private Long parseAmount(String amountStr) {
        if (amountStr == null || amountStr.isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(amountStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private PaymentMethod mapPaymentMethod(String payType) {
        if (payType == null) return null;
        return switch (payType) {
            case "9" -> PaymentMethod.WECHAT;
            case "10" -> PaymentMethod.ALIPAY;
            default -> PaymentMethod.ICBC;
        };
    }

    private String buildErrorResponse(String message) {
        // 即使验签失败也返回工行要求的格式，避免重试
        return icbcCallbackVerifier.buildCallbackResponse(icbcCallbackVerifier.generateResponseMsgId());
    }
}
```

- [ ] **Step 5: Run tests**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test -pl . -Dtest=PaymentCallbackAppServiceTest`
Expected: ALL PASS

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/callback/IcbcCallbackParam.java \
  src/main/java/com/aieducenter/payment/application/PaymentCallbackAppService.java \
  src/test/java/com/aieducenter/payment/application/PaymentCallbackAppServiceTest.java
git commit -m "feat(payment): add callback app service with verification, order update, and notification"
```

---

## Task 6: Create PaymentCallbackController

**Files:**
- Create: `src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentCallbackController.java`

- [ ] **Step 1: Implement controller**

Create `src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentCallbackController.java`:

```java
package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.PaymentCallbackAppService;
import com.aieducenter.payment.application.dto.callback.IcbcCallbackParam;
import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 支付回调控制器
 *
 * <p>接收工行支付结果异步通知</p>
 * <p>此路径已在 WebConfig 中排除签名验证</p>
 */
@RestController
@RequestMapping("/api/v1/payment/callbacks")
@RequiredArgsConstructor
@Tag(name = "Payment Callback API", description = "支付回调接口")
public class PaymentCallbackController {

    private static final Logger log = LoggerFactory.getLogger(PaymentCallbackController.class);

    private final PaymentCallbackAppService paymentCallbackAppService;

    @PostMapping(value = "/icbc", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Operation(summary = "接收工行支付结果回调")
    public ResponseEntity<String> handleIcbcCallback(@RequestParam Map<String, String> params) {
        log.info("Received ICBC callback: params keys={}", params.keySet());

        String sign = params.get("sign");
        String bizContent = params.get("biz_content");

        if (sign == null || bizContent == null) {
            log.warn("ICBC callback missing required parameters");
            return ResponseEntity.badRequest().body("missing parameters");
        }

        // 解析 biz_content
        IcbcCallbackParam callbackParam = JSON.parseObject(bizContent, IcbcCallbackParam.class);

        // 处理回调
        String response = paymentCallbackAppService.handleCallback(
            "/api/v1/payment/callbacks/icbc",
            params,
            sign,
            callbackParam
        );

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
}
```

- [ ] **Step 2: Verify compilation**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentCallbackController.java
git commit -m "feat(payment): add ICBC callback controller endpoint POST /api/v1/payment/callbacks/icbc"
```

---

## Task 7: Full Integration Test Run

**Files:** None (verification only)

- [ ] **Step 1: Run all tests**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw test`
Expected: ALL PASS

- [ ] **Step 2: Verify application starts**

Run: `cd /Users/zhangcolin/workspace/hcy_payment && ./mvnw spring-boot:run -Dspring-boot.run.profiles=local` (then Ctrl+C after startup)

Expected: Application starts without errors. The ICBC callback endpoint is registered.

- [ ] **Step 3: Final verification checklist**

- [ ] `PaymentOrder.actualAmount` field exists and `markAsPaid()` accepts 4 params
- [ ] `IcbcConfig.notifyBaseUrl` configured in yml
- [ ] `IcbcPaymentGatewayAdapter` uses `notifyBaseUrl` for `mer_url` and `result_type="0"`
- [ ] `IcbcCallbackVerifier` handles RSA verify + RSA2 sign
- [ ] `BusinessSystemNotifier` POSTs JSON to business systems
- [ ] `PaymentCallbackAppService` orchestrates the full flow
- [ ] `PaymentCallbackController` receives form POST at `/api/v1/payment/callbacks/icbc`
- [ ] WebConfig already excludes `/api/v1/payment/callbacks/**` from signature verification
- [ ] Flyway migration V3 adds `actual_amount` column
