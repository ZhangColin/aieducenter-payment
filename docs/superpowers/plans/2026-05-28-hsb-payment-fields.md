# HSB Payment Field Additions Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 补充建行支付下单请求中的页面返回URL（Pgfc_Ret_Url_Adr）和下单响应中的收银台URL（Cshdk_Url）、支付URL（Pay_Url）、支付二维码串（Pay_Qr_Code）字段，全部落库。

**Architecture:** 在现有 DDD 六边形架构中，从领域层（聚合根）→ 应用层（Command/AppService/Mapper）→ 基础设施层（GatewayAdapter）逐层添加字段。现有 `pay_url` 列实际存的是 `Cshdk_Url`，需重命名为 `cshdk_url`，再新增 `pay_url` 存储 `Pay_Url`。

**Tech Stack:** Java 21, JPA/Hibernate, Flyway, JUnit 5, Mockito, AssertJ

---

### Task 1: Flyway 迁移脚本

**Files:**
- Create: `src/main/resources/db/migration/V4__add_payment_url_fields.sql`

- [ ] **Step 1: 编写迁移脚本**

```sql
-- 重命名现有 pay_url 为 cshdk_url（实际存储的是收银台URL）
ALTER TABLE hsb_payment_orders RENAME COLUMN pay_url TO cshdk_url;

-- 新增 pay_url 列（存储建行 Pay_Url）
ALTER TABLE hsb_payment_orders ADD COLUMN pay_url VARCHAR(512);

-- 新增页面返回URL列（建行字段: Pgfc_Ret_Url_Adr）
ALTER TABLE hsb_payment_orders ADD COLUMN page_return_url VARCHAR(512);
```

- [ ] **Step 2: Commit**

```bash
git add src/main/resources/db/migration/V4__add_payment_url_fields.sql
git commit -m "feat(hsb): add Flyway migration for payment URL fields"
```

---

### Task 2: 领域层 — HsbPaymentOrder 聚合根

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbPaymentOrder.java`
- Modify: `src/test/java/com/aieducenter/payment/hsb/domain/aggregate/HsbPaymentOrderTest.java`

- [ ] **Step 1: 编写失败测试 — pageReturnUrl 存储**

在 `HsbPaymentOrderTest.java` 中，更新 `createOrder` 方法签名（增加 `pageReturnUrl` 参数），并添加新测试：

```java
// 更新 createOrder helper
private HsbPaymentOrder createOrder(LocalDate confirmReceiptDate, String pageReturnUrl) {
    return new HsbPaymentOrder(
        "BIZ001",
        "TestSystem",
        "测试订单",
        "41060860811052",
        "02",
        "01",
        "156",
        10000L,
        10000L,
        null,
        3600L,
        "https://example.com/notify",
        null,
        confirmReceiptDate,
        pageReturnUrl,
        List.of(new HsbSubOrder("BIZ001", "SUB001", "MERCH001", 10000L, 10000L))
    );
}

// 保留旧方法兼容
private HsbPaymentOrder createOrder(LocalDate confirmReceiptDate) {
    return createOrder(confirmReceiptDate, null);
}
```

添加新测试：

```java
@Test
@DisplayName("给定页面返回URL，创建订单时应正确保存")
void given_pageReturnUrl_when_createOrder_then_fieldStored() {
    String url = "https://example.com/return";
    HsbPaymentOrder order = createOrder(null, url);

    assertThat(order.getPageReturnUrl()).isEqualTo(url);
}

@Test
@DisplayName("给定页面返回URL为空，创建订单时字段应为空")
void given_nullPageReturnUrl_when_createOrder_then_fieldNull() {
    HsbPaymentOrder order = createOrder(null, null);

    assertThat(order.getPageReturnUrl()).isNull();
}

@Test
@DisplayName("setPaymentResult 应正确设置 cshdkUrl、payUrl、payQrCode、primOrderNo")
void given_setPaymentResult_then_allFieldsStored() {
    HsbPaymentOrder order = createOrder(null);

    order.setPaymentResult("http://cashier.url", "http://pay.url", "QR123", "PRIM001");

    assertThat(order.getCshdkUrl()).isEqualTo("http://cashier.url");
    assertThat(order.getPayUrl()).isEqualTo("http://pay.url");
    assertThat(order.getPayQrCode()).isEqualTo("QR123");
    assertThat(order.getPrimOrderNo()).isEqualTo("PRIM001");
}
```

- [ ] **Step 2: 运行测试确认失败**

Run: `mvn test -pl . -Dtest=HsbPaymentOrderTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 编译失败（构造函数签名不匹配）

- [ ] **Step 3: 修改 HsbPaymentOrder.java**

变更清单：
1. `payUrl` 字段 → 重命名为 `cshdkUrl`，列名 `pay_url` → `cshdk_url`
2. 新增 `payUrl` 字段，列名 `pay_url`
3. 新增 `pageReturnUrl` 字段，列名 `page_return_url`
4. 构造函数新增 `pageReturnUrl` 参数
5. `setPaymentResult` 签名变更：新增 `cshdkUrl` 和 `payUrl` 参数

```java
// === 字段变更 ===

// 原 payUrl 改为 cshdkUrl（收银台URL）
@Getter
@Column(name = "cshdk_url", length = 512)
private String cshdkUrl;

// 新增 payUrl（支付URL，建行字段 Pay_Url）
@Getter
@Column(name = "pay_url", length = 512)
private String payUrl;

// 新增 pageReturnUrl（页面返回URL，建行字段 Pgfc_Ret_Url_Adr）
@Getter
@Column(name = "page_return_url", length = 512)
private String pageReturnUrl;

// === 构造函数变更 ===
public HsbPaymentOrder(
        String businessMainOrderNo,
        String businessSystemName,
        String businessName,
        String mktId,
        String paymentMethod,
        String orderType,
        String currency,
        Long totalAmount,
        Long txnTotalAmount,
        String feeBearerId,
        Long expiredSeconds,
        String notifyUrl,
        String attach,
        LocalDate confirmReceiptDate,
        String pageReturnUrl,
        List<HsbSubOrder> subOrders
) {
    // ... 原有校验和赋值不变 ...
    this.pageReturnUrl = pageReturnUrl;
    // ... 其余不变 ...
}

// === setPaymentResult 变更 ===
public void setPaymentResult(String cshdkUrl, String payUrl, String payQrCode, String primOrderNo) {
    this.cshdkUrl = cshdkUrl;
    this.payUrl = payUrl;
    this.payQrCode = payQrCode;
    this.primOrderNo = primOrderNo;
}
```

- [ ] **Step 4: 运行测试确认通过**

Run: `mvn test -pl . -Dtest=HsbPaymentOrderTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbPaymentOrder.java src/test/java/com/aieducenter/payment/hsb/domain/aggregate/HsbPaymentOrderTest.java
git commit -m "feat(hsb): add pageReturnUrl, cshdkUrl, payUrl fields to HsbPaymentOrder"
```

---

### Task 3: 应用层 — Command、Response、Mapper

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/hsb/application/dto/command/CreateHsbPaymentCommand.java`
- Modify: `src/main/java/com/aieducenter/payment/hsb/application/dto/response/HsbPaymentOrderResponse.java`
- Modify: `src/main/java/com/aieducenter/payment/hsb/application/mapper/HsbPaymentOrderMapper.java`

- [ ] **Step 1: 修改 CreateHsbPaymentCommand — 新增 pageReturnUrl**

在 `attach` 和 `confirmReceiptDate` 之间添加：

```java
@Builder
public record CreateHsbPaymentCommand(
    @NotBlank String businessMainOrderNo,
    String businessName,
    @NotBlank String paymentMethod,
    @NotBlank String orderType,
    String currency,
    @NotNull @Positive Long totalAmount,
    @NotNull @Positive Long txnTotalAmount,
    String feeBearerId,
    Long expiredSeconds,
    String notifyUrl,
    String attach,
    String pageReturnUrl,
    LocalDate confirmReceiptDate,
    @NotEmpty @Valid List<HsbSubOrderCommand> subOrders
) {
    // HsbSubOrderCommand 不变
}
```

- [ ] **Step 2: 修改 HsbPaymentOrderResponse — 新增字段 + 重命名**

```java
@Builder
public record HsbPaymentOrderResponse(
    String paymentOrderNo,
    String businessMainOrderNo,
    String businessSystemName,
    String businessName,
    String status,
    String mktId,
    String paymentMethod,
    String orderType,
    String currency,
    Long totalAmount,
    Long txnTotalAmount,
    String feeBearerId,
    String cshdkUrl,
    String payUrl,
    String payQrCode,
    String primOrderNo,
    String pyTrnNo,
    Long actualAmount,
    LocalDateTime paidAt,
    LocalDateTime failedAt,
    LocalDateTime expiredAt,
    String notifyUrl,
    String attach,
    String pageReturnUrl,
    LocalDate confirmReceiptDate,
    List<HsbSubOrderResponse> subOrders
) {
    // HsbSubOrderResponse 不变
}
```

- [ ] **Step 3: 修改 HsbPaymentOrderMapper — 适配新字段**

```java
// 在 convert 方法中：
.cshdkUrl(order.getCshdkUrl())
.payUrl(order.getPayUrl())
.payQrCode(order.getPayQrCode())
// ... 其余不变 ...
.pageReturnUrl(order.getPageReturnUrl())
```

- [ ] **Step 4: 编译验证**

Run: `mvn compile -pl .`
Expected: BUILD SUCCESS（注意：HsbPaymentAppService 和其他依赖构造函数的地方还没改，会编译失败，因此需要同时修改 Task 4 的文件。将 Task 3 和 Task 4 合并提交）

- [ ] **Step 5: Commit（与 Task 4 合并）**

---

### Task 4: 应用层 — AppService + 网关响应 DTO + 适配器

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/hsb/domain/port/response/CreateHsbPaymentResponse.java`
- Modify: `src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbPaymentGatewayAdapter.java`
- Modify: `src/main/java/com/aieducenter/payment/hsb/application/HsbPaymentAppService.java`
- Modify: `src/test/java/com/aieducenter/payment/hsb/infrastructure/HsbPaymentGatewayAdapterTest.java`
- Modify: `src/test/java/com/aieducenter/payment/hsb/application/HsbPaymentAppServiceTest.java`

- [ ] **Step 1: 修改 CreateHsbPaymentResponse — 字段调整**

现有 `payUrl` 实际接收的是 `Cshdk_Url`，需拆分为两个字段：

```java
public record CreateHsbPaymentResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    String cshdkUrl,
    String payUrl,
    String payQrCode,
    String primOrderNo,
    long executionTime,
    String requestParams,
    String responseParams,
    Map<String, String> subOrderIds
) {}
```

- [ ] **Step 2: 修改 HsbPaymentGatewayAdapter — 发送 Pgfc_Ret_Url_Adr + 提取新字段**

在 `createPayment` 方法中：

**请求侧**（在 `json.put("Clrg_Dt", ...)` 之后添加）：

```java
if (cn.hutool.core.util.StrUtil.isNotBlank(order.getPageReturnUrl())) {
    json.put("Pgfc_Ret_Url_Adr", order.getPageReturnUrl());
}
```

**响应侧**（修改字段提取）：

```java
String cshdkUrl = success ? response.getString("Cshdk_Url") : null;
String payUrl = success ? response.getString("Pay_Url") : null;
String payQrCode = success ? response.getString("Pay_Qr_Code") : null;
String primOrderNo = success ? response.getString("Prim_Ordr_No") : null;
```

**构造返回值**（更新所有 `new CreateHsbPaymentResponse(...)` 调用）：

成功返回：
```java
return new CreateHsbPaymentResponse(success, returnCode, returnMsg,
    cshdkUrl, payUrl, payQrCode, primOrderNo, executionTime, requestParams, responseBody, subOrderIdMap);
```

异常返回：
```java
return new CreateHsbPaymentResponse(false, "SYSTEM_ERROR", e.getMessage(),
    null, null, null, null, executionTime, requestParams, null, null);
```

- [ ] **Step 3: 修改 HsbPaymentAppService — 透传 pageReturnUrl + 适配新字段**

**构造函数调用**（增加 `command.pageReturnUrl()`）：

```java
HsbPaymentOrder paymentOrder = new HsbPaymentOrder(
    command.businessMainOrderNo(),
    businessSystemName,
    command.businessName(),
    hsbConfig.getMktId(),
    command.paymentMethod(),
    command.orderType(),
    command.currency(),
    command.totalAmount(),
    command.txnTotalAmount(),
    command.feeBearerId(),
    command.expiredSeconds(),
    command.notifyUrl(),
    command.attach(),
    command.confirmReceiptDate(),
    command.pageReturnUrl(),
    subOrders
);
```

**setPaymentResult 调用**（适配新签名）：

```java
// 事务2中的两处 setPaymentResult 调用
toUpdate.setPaymentResult(
    gatewayResponse.cshdkUrl(), gatewayResponse.payUrl(),
    gatewayResponse.payQrCode(), gatewayResponse.primOrderNo());

order.setPaymentResult(
    gatewayResponse.cshdkUrl(), gatewayResponse.payUrl(),
    gatewayResponse.payQrCode(), gatewayResponse.primOrderNo());
```

- [ ] **Step 4: 更新测试文件**

**HsbPaymentGatewayAdapterTest.java**:

更新 `createTestOrder` helper（添加 `pageReturnUrl` 参数）：

```java
private HsbPaymentOrder createTestOrder(LocalDate confirmReceiptDate) {
    return new HsbPaymentOrder(
        "BIZ001", "TestSystem", "测试订单", "41060860811052",
        "02", "01", "156", 10000L, 10000L, null, 3600L,
        "https://example.com/notify", null, confirmReceiptDate, null,
        List.of(new HsbSubOrder("BIZ001", "SUB001", "MERCH001", 10000L, 10000L))
    );
}
```

添加新测试：

```java
@Test
@DisplayName("createPayment 请求中应包含 Pgfc_Ret_Url_Adr（页面返回URL）")
void given_orderWithPageReturnUrl_when_createPayment_then_requestContainsPgfcRetUrlAdr() {
    HsbPaymentOrder order = new HsbPaymentOrder(
        "BIZ001", "TestSystem", "测试订单", "41060860811052",
        "02", "01", "156", 10000L, 10000L, null, 3600L,
        "https://example.com/notify", null, null, "https://example.com/return",
        List.of(new HsbSubOrder("BIZ001", "SUB001", "MERCH001", 10000L, 10000L))
    );

    String mockResponse = """
        {"Svc_Rsp_St":"00","Svc_Rsp_Cd":"SUCCESS","Cshdk_Url":"http://cashier.url","Pay_Url":"http://pay.url","Pay_Qr_Code":"QR123","Prim_Ordr_No":"PRIM001"}
        """;

    try (MockedStatic<HsbSignUtil> signUtilMock = mockStatic(HsbSignUtil.class)) {
        signUtilMock.when(() -> HsbSignUtil.sign(anyString(), anyString())).thenReturn("mock-signature");
        when(hsbHttpClient.postJson(anyString(), anyString())).thenReturn(mockResponse);

        CreateHsbPaymentResponse response = adapter.createPayment(order, order.getSubOrders());

        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
        verify(hsbHttpClient).postJson(anyString(), bodyCaptor.capture());

        JSONObject requestJson = JSONObject.parseObject(bodyCaptor.getValue());
        assertThat(requestJson.getString("Pgfc_Ret_Url_Adr")).isEqualTo("https://example.com/return");
        assertThat(response.cshdkUrl()).isEqualTo("http://cashier.url");
        assertThat(response.payUrl()).isEqualTo("http://pay.url");
        assertThat(response.payQrCode()).isEqualTo("QR123");
    }
}

@Test
@DisplayName("createPayment 页面返回URL为空时，请求中不应包含 Pgfc_Ret_Url_Adr")
void given_orderWithoutPageReturnUrl_when_createPayment_then_requestOmitsPgfcRetUrlAdr() {
    HsbPaymentOrder order = createTestOrder(null);

    String mockResponse = """
        {"Svc_Rsp_St":"00","Svc_Rsp_Cd":"SUCCESS","Cshdk_Url":"http://cashier.url","Pay_Qr_Code":"QR123","Prim_Ordr_No":"PRIM001"}
        """;

    try (MockedStatic<HsbSignUtil> signUtilMock = mockStatic(HsbSignUtil.class)) {
        signUtilMock.when(() -> HsbSignUtil.sign(anyString(), anyString())).thenReturn("mock-signature");
        when(hsbHttpClient.postJson(anyString(), anyString())).thenReturn(mockResponse);

        adapter.createPayment(order, order.getSubOrders());

        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
        verify(hsbHttpClient).postJson(anyString(), bodyCaptor.capture());

        JSONObject requestJson = JSONObject.parseObject(bodyCaptor.getValue());
        assertThat(requestJson.containsKey("Pgfc_Ret_Url_Adr")).isFalse();
    }
}
```

**HsbPaymentAppServiceTest.java**:

更新 `CreateHsbPaymentResponse` 构造（字段顺序变化）：

```java
// 所有 new CreateHsbPaymentResponse(...) 调用需更新
// 旧: (success, returnCode, returnMsg, payUrl, payQrCode, primOrderNo, ...)
// 新: (success, returnCode, returnMsg, cshdkUrl, payUrl, payQrCode, primOrderNo, ...)

// 例：
new CreateHsbPaymentResponse(
    true, "00", "SUCCESS", "http://cashier.url", "http://pay.url", "QR123", "PRIM001",
    100L, "{}", "{}", null);

// 失败场景：
new CreateHsbPaymentResponse(false, "01", "FAIL", null, null, null, null, 50L, "{}", "{}", null);
```

更新 `HsbPaymentOrder` 构造（添加 `pageReturnUrl` 参数）：

```java
new HsbPaymentOrder(
    "BIZ001", "TestSystem", null, "41060860811052", "03", "04", "156",
    10000L, 10000L, null, 3600L, null, null, null, null, List.of());
```

- [ ] **Step 5: 运行全部测试**

Run: `mvn test -pl . -Dtest="HsbPaymentOrderTest,HsbPaymentGatewayAdapterTest,HsbPaymentAppServiceTest"`
Expected: ALL PASS

- [ ] **Step 6: 与 Task 3 合并 Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/application/dto/command/CreateHsbPaymentCommand.java \
  src/main/java/com/aieducenter/payment/hsb/application/dto/response/HsbPaymentOrderResponse.java \
  src/main/java/com/aieducenter/payment/hsb/application/mapper/HsbPaymentOrderMapper.java \
  src/main/java/com/aieducenter/payment/hsb/domain/port/response/CreateHsbPaymentResponse.java \
  src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbPaymentGatewayAdapter.java \
  src/main/java/com/aieducenter/payment/hsb/application/HsbPaymentAppService.java \
  src/test/java/com/aieducenter/payment/hsb/infrastructure/HsbPaymentGatewayAdapterTest.java \
  src/test/java/com/aieducenter/payment/hsb/application/HsbPaymentAppServiceTest.java
git commit -m "feat(hsb): add pageReturnUrl request field and cshdkUrl/payUrl response fields"
```

---

### Task 5: 编译验证 + 全量测试

- [ ] **Step 1: 全量编译**

Run: `mvn compile`
Expected: BUILD SUCCESS

- [ ] **Step 2: 运行全量单元测试**

Run: `mvn test`
Expected: ALL TESTS PASS

- [ ] **Step 3: 最终 Commit（如有遗漏修复）**

```bash
git add -A
git commit -m "fix(hsb): address remaining compilation issues from field additions"
```
