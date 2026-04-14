# 退款免审与审核拒绝回调 实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 支持退款免审直接退款，并在审核拒绝时回调业务端。

**Architecture:** 在现有退款流程中增加 `needAudit` 布尔参数。免审退款复用已有的审核通过链路，审核拒绝复用已有的通知机制。最小改动，不引入新状态或新接口。

**Tech Stack:** Java 21, Spring Boot, JPA, Mockito/JUnit 5

---

### Task 1: CreateRefundCommand 增加 needAudit 字段

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/application/dto/command/CreateRefundCommand.java`
- Test: `src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java`

- [ ] **Step 1: 写失败测试 — needAudit=false 时自动审核通过**

在 `RefundAppServiceTest.java` 中新增测试：

```java
@Test
@DisplayName("创建退款订单：needAudit=false时自动审核通过并退款")
void createRefund_needAuditFalse_autoApproveAndRefund() {
    configureTransactionTemplate();

    PaymentOrder paymentOrder = createPaidPaymentOrder();
    when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(paymentOrder));
    when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
    when(paymentGatewayPort.createRefund(any(), anyString()))
        .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
    when(paymentGatewayPort.queryRefund(any(), any(), any(), any()))
        .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.SUCCESS, 10000L, 10000L, "2026-04-14 10:00:00", "ICBC_REFUND_002", 50L));

    CreateRefundCommand command = new CreateRefundCommand(
        "BIZ001", "PAY001", 10000L, "取消", null, "https://biz.example.com/refund-notify", false
    );

    RefundOrderResponse response = service.createRefund(command, "TestSystem");

    // 验证状态为退款成功（说明自动审核通过了）
    assertThat(response.status()).isEqualTo(5); // SUCCESS
    // 验证回调了业务系统
    verify(businessSystemNotifier).notify(eq("https://biz.example.com/refund-notify"), any(RefundNotifyRequest.class));
}
```

```java
@Test
@DisplayName("创建退款订单：needAudit=true时保持PENDING状态")
void createRefund_needAuditTrue_staysPending() {
    PaymentOrder paymentOrder = createPaidPaymentOrder();
    when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(paymentOrder));
    when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

    CreateRefundCommand command = new CreateRefundCommand(
        "BIZ001", "PAY001", 10000L, "取消", null, "https://biz.example.com/refund-notify", true
    );

    RefundOrderResponse response = service.createRefund(command, "TestSystem");

    assertThat(response.status()).isEqualTo(1); // PENDING
    verify(paymentGatewayPort, never()).createRefund(any(), anyString());
}
```

- [ ] **Step 2: 运行测试确认失败**

Run: `mvn test -pl . -Dtest=RefundAppServiceTest#createRefund_needAuditFalse_autoApproveAndRefund -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 编译失败（CreateRefundCommand record 没有 needAudit 字段）

- [ ] **Step 3: 修改 CreateRefundCommand，增加 needAudit 字段**

修改 `src/main/java/com/aieducenter/payment/application/dto/command/CreateRefundCommand.java`：

```java
public record CreateRefundCommand(
    @NotBlank(message = "业务订单号不能为空")
    @Size(max = 64, message = "业务订单号长度不能超过64")
    String businessOrderNo,

    @NotBlank(message = "支付订单号不能为空")
    @Size(max = 64, message = "支付订单号长度不能超过64")
    String paymentOrderNo,

    @NotNull(message = "退款金额不能为空")
    @Positive(message = "退款金额必须大于0")
    Long refundAmount,

    @Size(max = 512, message = "退款原因长度不能超过512")
    String reason,

    @Size(max = 1000, message = "附加数据长度不能超过1000")
    String attach,

    @Size(max = 512, message = "退款通知地址长度不能超过512")
    String notifyUrl,

    Boolean needAudit
) {
    public boolean needAudit() {
        return needAudit == null || needAudit;
    }
}
```

- [ ] **Step 4: 修改 RefundAppService.createRefund()，needAudit=false 时自动审核通过**

修改 `src/main/java/com/aieducenter/payment/application/RefundAppService.java` 的 `createRefund` 方法：

```java
@Transactional
public RefundOrderResponse createRefund(CreateRefundCommand command, String businessSystemName) {
    // 1. 查找原支付订单
    PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentOrderNo(command.paymentOrderNo())
        .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));

    // 2. 校验原订单支付成功
    if (paymentOrder.getStatus() != PaymentStatus.PAID) {
        throw new ApplicationException(PaymentMessage.ORIGINAL_PAYMENT_NOT_SUCCESS);
    }

    // 3. 创建退款订单
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

    RefundOrder saved = refundOrderRepository.save(refundOrder);

    // 4. 免审：自动审核通过并发起退款
    if (!command.needAudit()) {
        saved.audit(null, "SYSTEM", true, "免审自动通过");
        saved = refundOrderRepository.save(saved);
        executeRefundAfterApproval(saved);
    }

    return toResponse(saved);
}
```

- [ ] **Step 5: 更新现有测试中的 CreateRefundCommand 构造**

现有测试中 `createRefund_success_savesNotifyUrl` 使用了 CreateRefundCommand 的 6 参数构造函数。由于 record 增加了字段，需要更新为 7 参数：

在 `RefundAppServiceTest.java` 的 `createRefund_success_savesNotifyUrl` 测试中：

```java
CreateRefundCommand command = new CreateRefundCommand(
    "BIZ001", "PAY001", 10000L, "取消", null, "https://biz.example.com/refund-notify", true
);
```

- [ ] **Step 6: 运行测试确认全部通过**

Run: `mvn test -pl . -Dtest=RefundAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 所有测试 PASS

- [ ] **Step 7: 提交**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/command/CreateRefundCommand.java \
        src/main/java/com/aieducenter/payment/application/RefundAppService.java \
        src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java
git commit -m "feat: 创建退款支持 needAudit 参数，false 时自动审核通过并发起退款"
```

---

### Task 2: 审核拒绝时回调业务端

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/application/RefundAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java`

- [ ] **Step 1: 写失败测试 — 审核拒绝时回调业务端**

在 `RefundAppServiceTest.java` 中修改现有的 `auditRefund_rejected_noBankCall` 测试：

```java
@Test
@DisplayName("审核拒绝：状态变为REJECTED，不调银行，但回调业务系统")
void auditRefund_rejected_noBankCallButNotify() {
    configureTransactionTemplate();

    RefundOrder order = createPendingRefundOrder();
    when(refundOrderRepository.findByRefundOrderNo("REF001"))
        .thenReturn(Optional.of(order));
    when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

    service.auditRefund("REF001", new AuditRefundCommand(1L, "审核员", false, "不同意"));

    assertThat(order.getStatus()).isEqualTo(RefundStatus.REJECTED);
    verify(paymentGatewayPort, never()).createRefund(any(), anyString());
    // 新增：验证回调了业务系统
    verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), any(RefundNotifyRequest.class));
}
```

- [ ] **Step 2: 运行测试确认失败**

Run: `mvn test -pl . -Dtest=RefundAppServiceTest#auditRefund_rejected_noBankCallButNotify -Dsurefire.failIfNoSpecifiedTests=false`
Expected: FAIL — `businessSystemNotifier.notify` 未被调用

- [ ] **Step 3: 修改 RefundAppService.auditRefund()，拒绝时增加回调**

修改 `src/main/java/com/aieducenter/payment/application/RefundAppService.java` 的 `auditRefund` 方法：

```java
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

    if (command.agreed()) {
        // 审核通过后发起退款
        executeRefundAfterApproval(saved);
    } else {
        // 审核拒绝，回调业务系统
        notifyRejected(saved);
    }

    return toResponse(saved);
}
```

新增私有方法 `notifyRejected`：

```java
private void notifyRejected(RefundOrder refundOrder) {
    RefundNotifyRequest request = new RefundNotifyRequest(
        refundOrder.getRefundOrderNo(),
        refundOrder.getBusinessOrderNo(),
        refundOrder.getPaymentOrderNo(),
        refundOrder.getStatus().getCode(),
        refundOrder.getStatus().getName(),
        refundOrder.getRefundAmount(),
        refundOrder.getBankRefundNo(),
        null,
        refundOrder.getAttach()
    );
    businessSystemNotifier.notify(refundOrder.getNotifyUrl(), request);
}
```

同时修改 `notifyIfTerminal` 方法，移除 `REJECTED` 的排除判断：

```java
private void notifyIfTerminal(RefundOrder refundOrder) {
    if (refundOrder.getStatus().isTerminal()) {
        RefundNotifyRequest request = new RefundNotifyRequest(
            refundOrder.getRefundOrderNo(),
            refundOrder.getBusinessOrderNo(),
            refundOrder.getPaymentOrderNo(),
            refundOrder.getStatus().getCode(),
            refundOrder.getStatus().getName(),
            refundOrder.getRefundAmount(),
            refundOrder.getBankRefundNo(),
            refundOrder.getRefundedAt() != null ? refundOrder.getRefundedAt().toString() : null,
            refundOrder.getAttach()
        );
        businessSystemNotifier.notify(refundOrder.getNotifyUrl(), request);
    }
}
```

- [ ] **Step 4: 运行测试确认全部通过**

Run: `mvn test -pl . -Dtest=RefundAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 所有测试 PASS

- [ ] **Step 5: 提交**

```bash
git add src/main/java/com/aieducenter/payment/application/RefundAppService.java \
        src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java
git commit -m "feat: 审核拒绝时回调业务系统，notifyIfTerminal 统一处理终态通知"
```

---

### Task 3: 全量测试验证

**Files:** 无新增修改

- [ ] **Step 1: 运行全量单元测试**

Run: `mvn test`
Expected: BUILD SUCCESS

- [ ] **Step 2: 运行变异测试确认覆盖率**

Run: `mvn org.pitest:pitest-maven:mutationCoverage`
Expected: 变异测试通过
