# 退款流程完善 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 完善退款流程，实现审核通过后自动发起退款、定时任务查询退款状态、业务查询接口自动同步工行状态。

**Architecture:** 在现有 DDD 分层结构上扩展。审核通过后同步调用工行退款接口，2秒后查询一次。定时任务每5分钟扫描 APPROVED/REFUNDING 订单。业务查询接口根据状态自动调工行同步。关键设计：银行调用 + Thread.sleep 必须在数据库事务之外执行，使用 Spring `TransactionTemplate` 在 `auditRefund` 中先提交审核状态事务，再执行银行调用。

**Tech Stack:** Spring Boot, JPA/Hibernate, ICBC SDK, Spring @Scheduled, Flyway, JUnit 5 + Mockito

**Spec:** `docs/superpowers/specs/2026-04-10-refund-workflow-design.md`

---

## File Structure

### New Files
- `src/main/resources/db/migration/V2__add_notify_url_to_refund_orders.sql` — Flyway 迁移脚本
- `src/main/java/com/aieducenter/payment/application/dto/callback/RefundNotifyRequest.java` — 退款回调通知 DTO
- `src/main/java/com/aieducenter/payment/application/RefundQueryScheduler.java` — 退款查询定时任务
- `src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java` — 退款服务测试

### Modified Files
- `src/main/java/com/aieducenter/payment/domain/aggregate/RefundOrder.java` — 新增 notifyUrl 字段，修改 startRefund 签名
- `src/main/java/com/aieducenter/payment/domain/repository/RefundOrderRepository.java` — 新增 findByStatusIn
- `src/main/java/com/aieducenter/payment/domain/port/PaymentGatewayPort.java` — 修改 queryRefund 签名
- `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java` — 实现 queryRefund
- `src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java` — 新增退款通知重载
- `src/main/java/com/aieducenter/payment/application/RefundAppService.java` — 核心退款流程变更
- `src/main/java/com/aieducenter/payment/application/dto/command/CreateRefundCommand.java` — 新增 notifyUrl
- `src/main/java/com/aieducenter/payment/application/dto/response/RefundOrderResponse.java` — 新增 notifyUrl, failedAt
- `src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java` — getRefund → queryRefund
- `src/main/resources/application-local.yml` — 添加 refund-query-url 和 scheduler 配置
- `src/main/resources/application-prod.yml` — 添加 refund-query-url
- `src/test/java/com/aieducenter/payment/domain/aggregate/RefundOrderTest.java` — 更新 startRefund 测试

---

### Task 1: Flyway 迁移 + RefundOrder 领域模型变更

**Files:**
- Create: `src/main/resources/db/migration/V2__add_notify_url_to_refund_orders.sql`
- Modify: `src/main/java/com/aieducenter/payment/domain/aggregate/RefundOrder.java`
- Modify: `src/test/java/com/aieducenter/payment/domain/aggregate/RefundOrderTest.java`

- [ ] **Step 1: 创建 Flyway 迁移脚本**

```sql
-- V2__add_notify_url_to_refund_orders.sql
ALTER TABLE pay_refund_orders ADD COLUMN notify_url VARCHAR(512);
```

- [ ] **Step 2: 修改 RefundOrder — 新增 notifyUrl 字段，修改 startRefund 签名**

在 `RefundOrder.java` 中：
1. 新增字段（放在 `attach` 字段之后）：
```java
@Getter
@Column(name = "notify_url", length = 512)
private String notifyUrl;
```

2. 构造函数新增 `notifyUrl` 参数（在第8个参数 `attach` 之后）：
```java
public RefundOrder(
        String businessOrderNo,
        String paymentOrderNo,
        String businessSystemName,
        String businessName,
        Long refundAmount,
        Long refundableAmount,
        String reason,
        String attach,
        String notifyUrl
) {
    // ... 原有赋值保持不变 ...
    this.notifyUrl = notifyUrl;
}
```

3. 修改 `startRefund()` 方法，接受 `bankRefundNo` 并保存：
```java
public void startRefund(String bankRefundNo) {
    Assertions.require(this.status == RefundStatus.APPROVED,
        PaymentMessage.REFUND_ORDER_NOT_APPROVED);
    this.status = RefundStatus.REFUNDING;
    this.bankRefundNo = bankRefundNo;
}
```

- [ ] **Step 3: 更新 RefundOrderTest**

1. 所有 `new RefundOrder(...)` 调用点增加第9个参数 `notifyUrl`（大部分传 `null`）
2. 所有 `order.startRefund()` 调用改为 `order.startRefund("ICBC_REFUND_123")`
3. 新增测试：
```java
@Test
@DisplayName("给定已批准退款，开始退款时应该保存银行退款流水号")
void given_approvedRefund_when_startRefund_then_savesBankRefundNo() {
    RefundOrder order = new RefundOrder(
        "ORDER001", "PAY001", "TestSystem",
        "课程购买", 10000L, 10000L, "Reason", null, "https://biz.example.com/notify"
    );
    order.audit(123L, "张三", true, "同意退款");

    order.startRefund("ICBC_REFUND_456");

    assertThat(order.getStatus()).isEqualTo(RefundStatus.REFUNDING);
    assertThat(order.getBankRefundNo()).isEqualTo("ICBC_REFUND_456");
    assertThat(order.getNotifyUrl()).isEqualTo("https://biz.example.com/notify");
}
```

- [ ] **Step 4: 运行测试验证**

Run: `mvn test -Dtest=RefundOrderTest -DfailIfNoTests=false`
Expected: All tests pass

- [ ] **Step 5: Commit**

```bash
git add src/main/resources/db/migration/V2__add_notify_url_to_refund_orders.sql \
  src/main/java/com/aieducenter/payment/domain/aggregate/RefundOrder.java \
  src/test/java/com/aieducenter/payment/domain/aggregate/RefundOrderTest.java
git commit -m "feat(refund): add notifyUrl to RefundOrder, update startRefund to accept bankRefundNo"
```

---

### Task 2: PaymentGatewayPort 签名变更 + ICBC queryRefund 实现

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/domain/port/PaymentGatewayPort.java`
- Modify: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java`
- Modify: `src/main/resources/application-local.yml`
- Modify: `src/main/resources/application-prod.yml`

- [ ] **Step 1: 修改 PaymentGatewayPort.queryRefund 签名**

```java
/**
 * 查询退款
 *
 * @param refundOrderNo 退款订单号 (对应 outtrx_serial_no)
 * @param paymentOrderNo 原支付订单号 (对应 out_trade_no)
 * @param bankOrderNo 原工行订单号 (对应 order_id)
 * @param bankRefundNo 银行退款流水号 (对应 intrx_serial_no，可为空)
 * @return 查询退款响应
 */
QueryRefundResponse queryRefund(String refundOrderNo, String paymentOrderNo, String bankOrderNo, String bankRefundNo);
```

- [ ] **Step 2: 实现 IcbcPaymentGatewayAdapter.queryRefund**

完整替换 `throw new UnsupportedOperationException(...)` 为实际实现。**注意**：SDK response 的 getter 名称需编译验证，以下基于 ICBC SDK 命名惯例：

```java
@Override
public QueryRefundResponse queryRefund(String refundOrderNo, String paymentOrderNo, String bankOrderNo, String bankRefundNo) {
    DefaultIcbcClient client = clientFactory.createClient();

    CardbusinessAggregatepayB2cOnlineRefundqryRequestV1 request =
        new CardbusinessAggregatepayB2cOnlineRefundqryRequestV1();
    request.setServiceUrl(icbcConfig.getRefundQueryUrl());

    var bizContent = new CardbusinessAggregatepayB2cOnlineRefundqryRequestV1Biz();

    bizContent.setMer_id(icbcConfig.getMerId());
    bizContent.setOuttrx_serial_no(refundOrderNo);
    if (bankOrderNo != null && !bankOrderNo.isEmpty()) {
        bizContent.setOrder_id(bankOrderNo);
    }
    if (paymentOrderNo != null && !paymentOrderNo.isEmpty()) {
        bizContent.setOut_trade_no(paymentOrderNo);
    }
    bizContent.setMer_prtcl_no(icbcConfig.getMerPrtclNo());

    request.setBizContent(bizContent);

    long startTime = System.currentTimeMillis();
    CardbusinessAggregatepayB2cOnlineRefundqryResponseV1 response;
    try {
        response = client.execute(request, clientFactory.generateMsgId());
    } catch (Exception e) {
        log.error("ICBC refund query failed", e);
        return new QueryRefundResponse(
            false, "SYSTEM_ERROR", e.getMessage(),
            RefundStatus.REFUNDING, null, null, null, null,
            System.currentTimeMillis() - startTime
        );
    }
    long executionTime = System.currentTimeMillis() - startTime;

    boolean success = response.getReturnCode() == 0;
    RefundStatus refundStatus = mapRefundStatus(response.getPay_status());

    return new QueryRefundResponse(
        success,
        String.valueOf(response.getReturnCode()),
        response.getReturnMsg(),
        refundStatus,
        parseAmount(response.getReject_amt()),
        parseAmount(response.getReal_reject_amt()),
        response.getRefund_time(),
        response.getIntrx_serial_no(),
        executionTime
    );
}

private RefundStatus mapRefundStatus(String payStatus) {
    if ("0".equals(payStatus)) return RefundStatus.SUCCESS;
    if ("1".equals(payStatus)) return RefundStatus.FAILED;
    return RefundStatus.REFUNDING;
}
```

如果编译时 SDK response getter 名称不匹配（如 `getReject_amt()` vs `getRejectAmt()`），根据编译错误调整。

- [ ] **Step 3: 添加配置**

在 `application-local.yml` 的 `gateway.icbc` 节点下（`refund-url` 之后）添加：
```yaml
    refund-query-url: ${ICBC_REFUND_QUERY_URL:https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/refundqry/V1}
```

在 `application-prod.yml` 的 `gateway.icbc` 节点下添加：
```yaml
    refund-query-url: https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/refundqry/V1
```

在 `scheduler` 节点下添加：
```yaml
  refund-query: "0 */5 * * * *"
```

- [ ] **Step 4: 编译验证**

Run: `mvn compile -DskipTests`
Expected: BUILD SUCCESS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/port/PaymentGatewayPort.java \
  src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java \
  src/main/resources/application-local.yml \
  src/main/resources/application-prod.yml
git commit -m "feat(refund): implement ICBC refund query, update PaymentGatewayPort signature"
```

---

### Task 3: Repository + DTO + Notifier 变更

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/domain/repository/RefundOrderRepository.java`
- Modify: `src/main/java/com/aieducenter/payment/application/dto/command/CreateRefundCommand.java`
- Modify: `src/main/java/com/aieducenter/payment/application/dto/response/RefundOrderResponse.java`
- Create: `src/main/java/com/aieducenter/payment/application/dto/callback/RefundNotifyRequest.java`
- Modify: `src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java`

- [ ] **Step 1: RefundOrderRepository 新增 findByStatusIn**

```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

Page<RefundOrder> findByStatusIn(List<RefundStatus> statuses, Pageable pageable);
```

- [ ] **Step 2: CreateRefundCommand 新增 notifyUrl**

在 `attach` 字段之后添加：
```java
@Size(max = 512, message = "退款通知地址长度不能超过512")
String notifyUrl
```

- [ ] **Step 3: RefundOrderResponse 新增 failedAt 和 notifyUrl**

在 `refundedAt` 之后添加 `failedAt`，在 `bankRefundNo` 之后添加 `notifyUrl`：
```java
public record RefundOrderResponse(
    Long id,
    String businessOrderNo,
    String refundOrderNo,
    String paymentOrderNo,
    String businessSystemName,
    String businessName,
    String status,
    Long refundAmount,
    Long refundableAmount,
    String reason,
    String auditorName,
    Boolean auditAgreed,
    String auditRemark,
    LocalDateTime createdAt,
    LocalDateTime approvedAt,
    LocalDateTime refundedAt,
    LocalDateTime failedAt,
    String bankRefundNo,
    String notifyUrl
) {}
```

- [ ] **Step 4: 创建 RefundNotifyRequest**

```java
package com.aieducenter.payment.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;

public record RefundNotifyRequest(
    @JSONField(name = "refundOrderNo")
    String refundOrderNo,

    @JSONField(name = "businessOrderNo")
    String businessOrderNo,

    @JSONField(name = "paymentOrderNo")
    String paymentOrderNo,

    @JSONField(name = "status")
    String status,

    @JSONField(name = "refundAmount")
    String refundAmount,

    @JSONField(name = "bankRefundNo")
    String bankRefundNo,

    @JSONField(name = "refundTime")
    String refundTime,

    @JSONField(name = "attach")
    String attach
) {}
```

- [ ] **Step 5: 重构 BusinessSystemNotifier**

提取公共 HTTP 发送逻辑到 `sendNotification` 私有方法，两个公开方法都委托给它：

```java
/**
 * 通知业务系统（支付结果）
 */
public void notify(String notifyUrl, BusinessNotifyRequest request) {
    if (notifyUrl == null || notifyUrl.isBlank()) {
        log.debug("notifyUrl is empty, skip notification. orderId={}", request.orderId());
        return;
    }
    sendNotification(notifyUrl, JSON.toJSONString(request), request.orderId());
}

/**
 * 通知业务系统（退款结果）
 */
public void notify(String notifyUrl, RefundNotifyRequest request) {
    if (notifyUrl == null || notifyUrl.isBlank()) {
        log.debug("notifyUrl is empty, skip refund notification. refundOrderNo={}", request.refundOrderNo());
        return;
    }
    sendNotification(notifyUrl, JSON.toJSONString(request), request.refundOrderNo());
}

private void sendNotification(String notifyUrl, String body, String orderId) {
    try {
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(notifyUrl))
            .timeout(Duration.ofSeconds(10))
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        log.info("Notifying business system: url={}, orderId={}", notifyUrl, orderId);

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            log.info("Business system notification succeeded: orderId={}, status={}", orderId, response.statusCode());
        } else {
            log.warn("Business system notification returned non-200: orderId={}, status={}, body={}",
                orderId, response.statusCode(), response.body());
        }
    } catch (Exception e) {
        log.warn("Business system notification failed: orderId={}, url={}, error={}",
            orderId, notifyUrl, e.getMessage());
    }
}
```

需要添加 `import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;`

- [ ] **Step 6: 编译验证**

Run: `mvn compile -DskipTests`
Expected: BUILD SUCCESS

- [ ] **Step 7: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/repository/RefundOrderRepository.java \
  src/main/java/com/aieducenter/payment/application/dto/command/CreateRefundCommand.java \
  src/main/java/com/aieducenter/payment/application/dto/response/RefundOrderResponse.java \
  src/main/java/com/aieducenter/payment/application/dto/callback/RefundNotifyRequest.java \
  src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java
git commit -m "feat(refund): add repository query, DTOs, and notifier for refund workflow"
```

---

### Task 4: RefundAppService 核心流程重构

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/application/RefundAppService.java`
- Modify: `src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java`

这是最核心的变更。

- [ ] **Step 1: 添加新依赖**

RefundAppService 新增三个依赖（`@RequiredArgsConstructor` 自动注入）：
```java
private final PaymentGatewayPort paymentGatewayPort;
private final PaymentLogRepository paymentLogRepository;
private final BusinessSystemNotifier businessSystemNotifier;
```

同时注入 `TransactionTemplate` 用于编程式事务控制：
```java
private final TransactionTemplate transactionTemplate;
```

需要添加 import：
```java
import org.springframework.transaction.support.TransactionTemplate;
import com.aieducenter.payment.domain.port.response.CreateRefundResponse;
import com.aieducenter.payment.domain.port.response.QueryRefundResponse;
```

- [ ] **Step 2: 修改 createRefund — 传入 notifyUrl**

RefundOrder 构造增加 `command.notifyUrl()` 参数：
```java
RefundOrder refundOrder = new RefundOrder(
    command.businessOrderNo(),
    command.paymentOrderNo(),
    businessSystemName,
    paymentOrder.getBusinessName(),
    command.refundAmount(),
    paymentOrder.getAmount(),
    command.reason(),
    command.attach(),
    command.notifyUrl()
);
```

- [ ] **Step 3: 修改 auditRefund — 事务拆分**

**关键设计**：审核状态更新在事务内，银行调用 + 2秒等待在事务外。使用 `TransactionTemplate` 编程式控制：

```java
/**
 * 审核退款
 */
public RefundOrderResponse auditRefund(String refundOrderNo, AuditRefundCommand command) {
    // 事务1：审核状态更新
    RefundOrder saved = transactionTemplate.execute(status -> {
        RefundOrder refundOrder = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
            .orElseThrow(() -> new ApplicationException(PaymentMessage.REFUND_ORDER_NOT_FOUND));

        refundOrder.audit(
            command.auditorId(),
            command.auditorName(),
            command.agreed(),
            command.remark()
        );

        return refundOrderRepository.save(refundOrder);
    });

    // 事务外：审核通过后发起退款
    if (command.agreed()) {
        executeRefundAfterApproval(saved);
    }

    return toResponse(saved);
}
```

- [ ] **Step 4: 提取核心私有方法**

```java
/**
 * 审核通过后发起退款（事务外执行）
 */
private void executeRefundAfterApproval(RefundOrder refundOrder) {
    try {
        PaymentOrder paymentOrder = paymentOrderRepository
            .findByPaymentOrderNo(refundOrder.getPaymentOrderNo())
            .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));

        executeRefundAndQuery(refundOrder, paymentOrder);
    } catch (Exception e) {
        log.error("Failed to execute refund after approval: refundOrderNo={}, error={}",
            refundOrder.getRefundOrderNo(), e.getMessage());
    }
}

/**
 * 执行退款请求 + 2秒等待 + 查询状态
 */
private void executeRefundAndQuery(RefundOrder refundOrder, PaymentOrder paymentOrder) {
    // 1. 调工行退货
    CreateRefundResponse refundResponse = paymentGatewayPort.createRefund(
        refundOrder, paymentOrder.getBankOrderNo()
    );

    // 2. 记录退款请求日志
    saveRefundRequestLog(refundOrder, refundResponse);

    if (!refundResponse.success()) {
        log.warn("Refund request failed: refundOrderNo={}, returnCode={}, returnMsg={}",
            refundOrder.getRefundOrderNo(), refundResponse.returnCode(), refundResponse.returnMsg());
        return; // 保持 APPROVED，等定时任务重试
    }

    // 3. 更新为 REFUNDING（独立事务）
    transactionTemplate.executeWithoutResult(status -> {
        refundOrder.startRefund(refundResponse.bankRefundNo());
        refundOrderRepository.save(refundOrder);
    });

    // 4. 等 2 秒
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
    }

    // 5. 查询退款状态
    queryAndUpdateRefundStatus(refundOrder, paymentOrder);

    // 6. 如果达到终态，回调业务系统
    notifyIfTerminal(refundOrder);
}

/**
 * 查询工行退款状态并更新订单
 */
private void queryAndUpdateRefundStatus(RefundOrder refundOrder, PaymentOrder paymentOrder) {
    QueryRefundResponse queryResponse = paymentGatewayPort.queryRefund(
        refundOrder.getRefundOrderNo(),
        refundOrder.getPaymentOrderNo(),
        paymentOrder.getBankOrderNo(),
        refundOrder.getBankRefundNo()
    );

    saveRefundQueryLog(refundOrder, queryResponse);

    if (!queryResponse.success()) {
        log.warn("Refund query failed: refundOrderNo={}, returnCode={}, returnMsg={}",
            refundOrder.getRefundOrderNo(), queryResponse.returnCode(), queryResponse.returnMsg());
        return;
    }

    // 根据查询结果更新状态（独立事务）
    if (queryResponse.refundStatus() == RefundStatus.SUCCESS) {
        transactionTemplate.executeWithoutResult(status -> {
            refundOrder.refundSuccess(
                queryResponse.bankRefundNo() != null ? queryResponse.bankRefundNo() : refundOrder.getBankRefundNo()
            );
            refundOrderRepository.save(refundOrder);
        });
    } else if (queryResponse.refundStatus() == RefundStatus.FAILED) {
        transactionTemplate.executeWithoutResult(status -> {
            refundOrder.refundFailed(queryResponse.returnMsg());
            refundOrderRepository.save(refundOrder);
        });
    }
    // REFUNDING 状态不变
}
```

- [ ] **Step 5: 提取辅助方法（使用不同方法名避免重载歧义）**

```java
/**
 * 记录退款请求日志
 */
private void saveRefundRequestLog(RefundOrder refundOrder, CreateRefundResponse refundResponse) {
    PaymentLog logEntry = new PaymentLog(
        refundOrder.getPaymentOrderNo(),
        refundOrder.getRefundOrderNo(),
        "REFUND_REQUEST",
        "ICBC",
        "aggregatepay/b2c/online/merrefund",
        null,
        null,
        null,
        200,
        refundResponse.returnCode(),
        refundResponse.returnMsg(),
        refundResponse.executionTime(),
        refundResponse.success(),
        refundResponse.success() ? null : "银行调用失败"
    );
    paymentLogRepository.save(logEntry);
}

/**
 * 记录退款查询日志
 */
private void saveRefundQueryLog(RefundOrder refundOrder, QueryRefundResponse queryResponse) {
    PaymentLog logEntry = new PaymentLog(
        refundOrder.getPaymentOrderNo(),
        refundOrder.getRefundOrderNo(),
        "REFUND_QUERY",
        "ICBC",
        "aggregatepay/b2c/online/refundqry",
        null,
        null,
        com.alibaba.fastjson2.JSON.toJSONString(queryResponse),
        200,
        queryResponse.returnCode(),
        queryResponse.returnMsg(),
        queryResponse.executionTime(),
        queryResponse.success(),
        null
    );
    paymentLogRepository.save(logEntry);
}

/**
 * 如果达到终态（SUCCESS/FAILED），回调业务系统
 */
private void notifyIfTerminal(RefundOrder refundOrder) {
    if (refundOrder.getStatus().isTerminal() && refundOrder.getStatus() != RefundStatus.REJECTED) {
        RefundNotifyRequest request = new RefundNotifyRequest(
            refundOrder.getRefundOrderNo(),
            refundOrder.getBusinessOrderNo(),
            refundOrder.getPaymentOrderNo(),
            refundOrder.getStatus().name(),
            String.valueOf(refundOrder.getRefundAmount()),
            refundOrder.getBankRefundNo(),
            refundOrder.getRefundedAt() != null ? refundOrder.getRefundedAt().toString() : null,
            refundOrder.getAttach()
        );
        businessSystemNotifier.notify(refundOrder.getNotifyUrl(), request);
    }
}
```

- [ ] **Step 6: 暴露定时任务需要的方法**

```java
/**
 * 处理已批准的退款订单（发起退款 + 查询 + 回调）
 * 供定时任务调用
 */
public void processApprovedRefund(RefundOrder refundOrder, PaymentOrder paymentOrder) {
    executeRefundAndQuery(refundOrder, paymentOrder);
}

/**
 * 处理退款中的订单（查询 + 回调）
 * 供定时任务调用
 */
public void processRefundingOrder(RefundOrder refundOrder, PaymentOrder paymentOrder) {
    queryAndUpdateRefundStatus(refundOrder, paymentOrder);
    notifyIfTerminal(refundOrder);
}
```

- [ ] **Step 7: 修改 getRefund → queryRefund**

**重要**：移除原方法上的 `@Transactional(readOnly = true)` 注解，因为该方法现在包含写操作。

```java
/**
 * 查询退款订单（根据状态自动同步工行）
 */
public RefundOrderResponse queryRefund(String refundOrderNo) {
    RefundOrder refundOrder = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
        .orElseThrow(() -> new ApplicationException(PaymentMessage.REFUND_ORDER_NOT_FOUND));

    if (refundOrder.getStatus() == RefundStatus.APPROVED) {
        try {
            PaymentOrder paymentOrder = paymentOrderRepository
                .findByPaymentOrderNo(refundOrder.getPaymentOrderNo())
                .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));
            executeRefundAndQuery(refundOrder, paymentOrder);
        } catch (Exception e) {
            log.error("Failed to execute refund in query: refundOrderNo={}", refundOrderNo, e);
        }
    } else if (refundOrder.getStatus() == RefundStatus.REFUNDING) {
        try {
            PaymentOrder paymentOrder = paymentOrderRepository
                .findByPaymentOrderNo(refundOrder.getPaymentOrderNo())
                .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));
            queryAndUpdateRefundStatus(refundOrder, paymentOrder);
        } catch (Exception e) {
            log.error("Failed to query refund status: refundOrderNo={}", refundOrderNo, e);
        }
    }

    return toResponse(refundOrder);
}
```

- [ ] **Step 8: 更新 toResponse — 添加新字段**

```java
private RefundOrderResponse toResponse(RefundOrder order) {
    return new RefundOrderResponse(
        order.getId(),
        order.getBusinessOrderNo(),
        order.getRefundOrderNo(),
        order.getPaymentOrderNo(),
        order.getBusinessSystemName(),
        order.getBusinessName(),
        order.getStatus().getName(),
        order.getRefundAmount(),
        order.getRefundableAmount(),
        order.getReason(),
        order.getAuditorName(),
        order.getAuditAgreed(),
        order.getAuditRemark(),
        order.getCreatedAt(),
        order.getApprovedAt(),
        order.getRefundedAt(),
        order.getFailedAt(),
        order.getBankRefundNo(),
        order.getNotifyUrl()
    );
}
```

- [ ] **Step 9: 更新 RefundApiV1Controller**

```java
@GetMapping("/{refundOrderNo}")
@RequireSignature
@Operation(summary = "查询退款订单")
public ApiResponse<RefundOrderResponse> getRefund(
        @PathVariable String refundOrderNo
) {
    RefundOrderResponse response = refundAppService.queryRefund(refundOrderNo);
    return ApiResponse.ok(response);
}
```

- [ ] **Step 10: 编译验证**

Run: `mvn compile -DskipTests`
Expected: BUILD SUCCESS

- [ ] **Step 11: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/RefundAppService.java \
  src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java
git commit -m "feat(refund): implement core refund workflow with TransactionTemplate"
```

---

### Task 5: RefundQueryScheduler 定时任务

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/RefundQueryScheduler.java`

- [ ] **Step 1: 创建 RefundQueryScheduler**

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.cartisan.core.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RefundQueryScheduler {

    private static final Logger log = LoggerFactory.getLogger(RefundQueryScheduler.class);
    private static final int BATCH_SIZE = 100;

    private final RefundOrderRepository refundOrderRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final RefundAppService refundAppService;

    @Scheduled(cron = "${scheduler.refund-query:0 */5 * * * *}")
    public void processRefundOrders() {
        log.info("Starting refund order processing...");

        processOrdersByStatus(RefundStatus.APPROVED, true);
        processOrdersByStatus(RefundStatus.REFUNDING, false);

        log.info("Refund order processing completed.");
    }

    private void processOrdersByStatus(RefundStatus status, boolean executeRefund) {
        int page = 0;
        Page<RefundOrder> orders;
        do {
            orders = refundOrderRepository.findByStatusIn(
                List.of(status), PageRequest.of(page, BATCH_SIZE)
            );

            for (RefundOrder refundOrder : orders.getContent()) {
                try {
                    PaymentOrder paymentOrder = paymentOrderRepository
                        .findByPaymentOrderNo(refundOrder.getPaymentOrderNo())
                        .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));

                    if (executeRefund) {
                        refundAppService.processApprovedRefund(refundOrder, paymentOrder);
                    } else {
                        refundAppService.processRefundingOrder(refundOrder, paymentOrder);
                    }
                } catch (Exception e) {
                    log.error("Failed to process refund order {}: {}",
                        refundOrder.getRefundOrderNo(), e.getMessage());
                }
            }
            page++;
        } while (orders.hasNext());
    }
}
```

- [ ] **Step 2: 编译验证**

Run: `mvn compile -DskipTests`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/RefundQueryScheduler.java
git commit -m "feat(refund): add scheduled task for refund status polling"
```

---

### Task 6: RefundAppServiceTest 单元测试

**Files:**
- Create: `src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java`

- [ ] **Step 1: 编写完整的 RefundAppServiceTest**

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;
import com.aieducenter.payment.application.dto.command.AuditRefundCommand;
import com.aieducenter.payment.application.dto.command.CreateRefundCommand;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.CreateRefundResponse;
import com.aieducenter.payment.domain.port.response.QueryRefundResponse;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("退款应用服务测试")
class RefundAppServiceTest {

    @Mock private RefundOrderRepository refundOrderRepository;
    @Mock private PaymentOrderRepository paymentOrderRepository;
    @Mock private PaymentGatewayPort paymentGatewayPort;
    @Mock private PaymentLogRepository paymentLogRepository;
    @Mock private BusinessSystemNotifier businessSystemNotifier;
    @Mock private TransactionTemplate transactionTemplate;

    private RefundAppService service;

    @BeforeEach
    void setUp() {
        service = new RefundAppService(
            refundOrderRepository,
            paymentOrderRepository,
            paymentGatewayPort,
            paymentLogRepository,
            businessSystemNotifier,
            transactionTemplate
        );

        // 模拟 TransactionTemplate 直接执行（不真正开启事务）
        lenient().when(transactionTemplate.execute(any())).thenAnswer(inv -> {
            org.springframework.transaction.support.TransactionCallback<?> callback = inv.getArgument(0);
            return callback.doInTransaction(null);
        });
        lenient().doAnswer(inv -> {
            org.springframework.transaction.support.TransactionCallbackWithoutResult callback = inv.getArgument(0);
            callback.doInTransaction(null);
            return null;
        }).when(transactionTemplate).executeWithoutResult(any());
    }

    // ========== 辅助方法 ==========

    private RefundOrder createPendingRefundOrder() {
        return new RefundOrder(
            "BIZ001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "取消", null,
            "https://biz.example.com/notify"
        );
    }

    private RefundOrder createApprovedRefundOrder() {
        RefundOrder order = createPendingRefundOrder();
        order.audit(1L, "审核员", true, "同意");
        return order;
    }

    private RefundOrder createRefundingRefundOrder() {
        RefundOrder order = createApprovedRefundOrder();
        order.startRefund("ICBC_REFUND_001");
        return order;
    }

    private PaymentOrder createPaidPaymentOrder() {
        PaymentOrder order = new PaymentOrder(
            "BIZ001", "TestSystem", "课程购买",
            10000L, "Python课程", "描述",
            "https://biz.example.com/notify", "attach", 3600L
        );
        order.markAsPaid("ICBC_ORDER_001", "THIRD_001", PaymentChannel.ICBC, 10000L);
        return order;
    }

    // ========== 测试用例 ==========

    @Test
    @DisplayName("创建退款订单：成功并保存 notifyUrl")
    void createRefund_success_savesNotifyUrl() {
        PaymentOrder paymentOrder = createPaidPaymentOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(paymentOrder));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        CreateRefundCommand command = new CreateRefundCommand(
            "BIZ001", "PAY001", 10000L, "取消", null, "https://biz.example.com/refund-notify"
        );

        RefundOrderResponse response = service.createRefund(command, "TestSystem");

        assertThat(response).isNotNull();
        assertThat(response.notifyUrl()).isEqualTo("https://biz.example.com/refund-notify");
    }

    @Test
    @DisplayName("审核通过：工行退款成功，状态变为REFUNDING，保存bankRefundNo")
    void auditRefund_approved_refundSuccess() {
        RefundOrder order = createApprovedRefundOrder();
        // 重新 mock 返回 APPROVED 状态的订单
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.REFUNDING, 10000L, 10000L, null, null, 50L));

        AuditRefundCommand command = new AuditRefundCommand(1L, "审核员", true, "同意");

        RefundOrderResponse response = service.auditRefund("REF001", command);

        assertThat(response.status()).isEqualTo("退款中");
        // 不回调（REFUNDING 不是终态）
        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("审核通过：工行退款失败，状态保持APPROVED")
    void auditRefund_approved_refundFailed_staysApproved() {
        RefundOrder order = createPendingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        AuditRefundCommand command = new AuditRefundCommand(1L, "审核员", true, "同意");

        RefundOrderResponse response = service.auditRefund("REF001", command);

        // 退款未执行（因为 paymentGatewayPort 未 mock），但不影响审核
        assertThat(order.getStatus()).isEqualTo(RefundStatus.APPROVED);
    }

    @Test
    @DisplayName("审核通过：退款后查询成功，回调业务系统")
    void auditRefund_approved_querySuccess_notifiesBusiness() {
        RefundOrder order = createApprovedRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.SUCCESS, 10000L, 10000L, "2026-04-10 14:30:05", "ICBC_REFUND_002", 50L));

        service.auditRefund("REF001", new AuditRefundCommand(1L, "审核员", true, "同意"));

        // 验证回调
        ArgumentCaptor<RefundNotifyRequest> captor = ArgumentCaptor.forClass(RefundNotifyRequest.class);
        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), captor.capture());
        assertThat(captor.getValue().status()).isEqualTo("SUCCESS");
        assertThat(captor.getValue().refundAmount()).isEqualTo("10000");
    }

    @Test
    @DisplayName("审核通过：退款后查询失败，状态变FAILED，回调业务系统")
    void auditRefund_approved_queryFailed_notifiesBusiness() {
        RefundOrder order = createApprovedRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.FAILED, null, null, null, null, 50L));

        service.auditRefund("REF001", new AuditRefundCommand(1L, "审核员", true, "同意"));

        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("审核通过：退款后查询仍退款中，不回调")
    void auditRefund_approved_queryRefunding_noCallback() {
        RefundOrder order = createApprovedRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.REFUNDING, null, null, null, null, 50L));

        service.auditRefund("REF001", new AuditRefundCommand(1L, "审核员", true, "同意"));

        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("审核拒绝：状态变为REJECTED，不调银行")
    void auditRefund_rejected_noBankCall() {
        RefundOrder order = createPendingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        service.auditRefund("REF001", new AuditRefundCommand(1L, "审核员", false, "不同意"));

        assertThat(order.getStatus()).isEqualTo(RefundStatus.REJECTED);
        verify(paymentGatewayPort, never()).createRefund(any(), anyString());
        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("查询退款订单：APPROVED状态触发退款，不回调")
    void queryRefund_approved_triggersRefundNoCallback() {
        RefundOrder order = createApprovedRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.SUCCESS, 10000L, 10000L, null, null, 50L));

        RefundOrderResponse response = service.queryRefund("REF001");

        assertThat(response).isNotNull();
        // 查询接口不回调
        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("查询退款订单：REFUNDING状态只查询，不回调")
    void queryRefund_refunding_onlyQueryNoCallback() {
        RefundOrder order = createRefundingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.queryRefund(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.SUCCESS, 10000L, 10000L, null, null, 50L));

        RefundOrderResponse response = service.queryRefund("REF001");

        // 不调 createRefund
        verify(paymentGatewayPort, never()).createRefund(any(), anyString());
        // 不回调
        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("查询退款订单：SUCCESS状态直接返回，不调银行")
    void queryRefund_success_returnsDirectly() {
        RefundOrder order = createRefundingRefundOrder();
        order.refundSuccess("ICBC_REFUND_001");
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));

        RefundOrderResponse response = service.queryRefund("REF001");

        assertThat(response.status()).isEqualTo("退款成功");
        verify(paymentGatewayPort, never()).queryRefund(anyString(), anyString(), anyString(), anyString());
        verify(paymentGatewayPort, never()).createRefund(any(), anyString());
    }
}
```

**注意**：`RefundAppService` 构造函数参数顺序必须与上述 `setUp()` 中的一致：`refundOrderRepository, paymentOrderRepository, paymentGatewayPort, paymentLogRepository, businessSystemNotifier, transactionTemplate`。

- [ ] **Step 2: 运行测试验证**

Run: `mvn test -Dtest=RefundAppServiceTest -DfailIfNoTests=false`
Expected: All tests pass

- [ ] **Step 3: Commit**

```bash
git add src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java
git commit -m "test(refund): add RefundAppServiceTest covering core refund workflow"
```

---

### Task 7: 集成验证 + 最终清理

- [ ] **Step 1: 全量编译**

Run: `mvn compile`
Expected: BUILD SUCCESS

- [ ] **Step 2: 全量测试**

Run: `mvn test`
Expected: All tests pass

- [ ] **Step 3: 检查遗漏**

确认以下配置文件都包含 `refund-query-url`：
- `application-local.yml`
- `application-prod.yml`

确认 `application-local.yml` 包含 `scheduler.refund-query`。

- [ ] **Step 4: Final commit (if any fixes needed)**

```bash
git add -A
git commit -m "chore(refund): final cleanup and configuration"
```
