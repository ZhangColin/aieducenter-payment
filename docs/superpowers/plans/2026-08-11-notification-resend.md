# 通知重发（支付 + 退款）实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 新增 `NotificationResendAppService`：`resendPaymentNotification(paymentOrderNo, command)` / `resendRefundNotification(refundOrderNo, command)`——仅当订单处于**终态**时，把当前结果重 POST 到其 `notifyUrl`（复用 `BusinessSystemNotifier`），**不改订单状态、不重置时间线**（ADR-0001）；每次重发落 `OperationLog(NOTIFY_RESEND)`，含操作者（请求体）与系统身份（签名上下文）。端点 `POST /api/v1/payments/{paymentOrderNo}/notifications/resend`、`POST /api/v1/refunds/{refundOrderNo}/notifications/resend`。

**Architecture:** DDD 六边形**写应用服务**，薄端点委托。复用既有基础设施：`PaymentStatus.isTerminal()` / `RefundStatus.isTerminal()`（终态判定）、`BusinessSystemNotifier.notify(...)`（重 POST）、`OperationLogAppService.record(...)`（留痕，#9 已落地）、`PaymentOrderMapper.convert(...)`（支付 payload）。退款 payload 构造当前内联在 `RefundAppService.notifyIfTerminal`——**提取 `RefundNotifyRequest.from(RefundOrder)` 静态工厂**，让原方法与重发服务共用同一构造真源（DRY）。不新建表、不新增聚合、不新增仓储方法、不新增迁移。一条测试缝：AppService Mockito 缝（mock 仓储 / 通知器 / `OperationLogAppService`，验证"仅终态才发 + 不改状态 + 落日志 + 身份透传"，变异友好）。

**Tech Stack:** Java 21，Spring Boot，cartisan-data-jpa（`BaseRepository`），cartisan-openapi（`@RequireSignature`），cartisan-web（`ApiResponse` / `RequestContext`），JUnit 5 + Mockito + AssertJ，pitest。

## 关联文档

- 父 spec：Issue #8（实现规约，User Story 14/15/16「通知重发不改状态」、25/26「留痕含系统+操作者身份」）。
- 本 issue：#16（T8 · 通知重发）。
- 依赖：#9（T1 · OperationLog 能力）——**代码层已落地**（`OperationLog` 聚合 / `OperationLogAppService.record` / `OperationType.NOTIFY_RESEND` / `OperationLogTargetType.PAYMENT|REFUND` 均在库），本计划直接消费。
- 决策：`docs/adr/0001-no-admin-state-mutation.md`（运营不施加状态——重发不改状态）、`docs/adr/0002-operation-log-separation.md`（OperationLog 记行为者写操作）。
- 术语：`CONTEXT.md`。
- 编码规范：`docs/guide/限界上下文代码编写规范.md`（6.1 控制器注入字段归组至顶部）。
- 先验：`RefundAppServiceTest`（AppService Mockito 缝 + `ArgumentCaptor` 验 `record(...)`）、`OperationLogAppService`（record 8 参签名）。

## Global Constraints

- **不改订单状态**（ADR-0001）：重发方法全程不调用任何仓储 `save` / 聚合状态迁移方法；只读订单 → 重 POST → 落日志。
- **仅终态重发**：`PaymentStatus.isTerminal()`（PAID/FAILED/CANCELLED/EXPIRED）、`RefundStatus.isTerminal()`（REJECTED/SUCCESS/FAILED）；非终态拒绝并抛 `ApplicationException`（明确错误码）。
- **银行无关**（CONTEXT.md 不变式 5）：不出现 ICBC 硬编码。
- **身份双写**：操作者（`command.operatorId/operatorName`，来自请求体）+ 系统身份（`RequestContext.getCallerAppName()`，来自签名上下文）均写入 OperationLog。
- **DTO 只用 Record**；端点响应 `ApiResponse<Void>`（动作型，无自然实体返回）；不暴露聚合。
- **构造函数注入**（`@RequiredArgsConstructor`），禁止字段注入。
- **导入顺序**：Java 标准库 → Jakarta/Spring → cartisan → 项目内部。
- **测试命名**：`given_X_when_Y_then_Z` 语义（中文 `@DisplayName`），AssertJ 断言；Mockito 严格存根（`MockitoExtension` 默认 STRICT_STUBS）。
- **签名校验**：端点 `@RequireSignature`；POST 请求体参与 bodyDigest 签名（框架已支持，既有 `auditRefund` 同构）。
- **变异测试**：`mvn org.pitest:pitest-maven:mutationCoverage` 须过（阈值 70%）；新增 `NotificationResendAppService` 纳入 pitest `targetClasses`。

## 关键设计决策（锁定）

| 决策点 | 取值 | 理由 |
|--------|------|------|
| 服务形态 | 新增独立 `NotificationResendAppService`（两方法） | spec 明示「通知重发 AppService」为独立应用服务；与查询/审核服务职责分离，单一职责。 |
| 方法签名 | `resendPaymentNotification(String, ResendNotificationCommand)` / `resendRefundNotification(String, ResendNotificationCommand)` | issue 文面只给单号，但落 OperationLog 需操作者身份（spec US 26「操作者身份取自请求体字段传入」）；故增 `command` 入参携带 `operatorId/operatorName/remark`，与 `auditRefund(refundOrderNo, AuditRefundCommand)` 同构。 |
| 命令复用 | 支付/退款共用一个 `ResendNotificationCommand` | 两端点 body 字段同构（operatorId/operatorName/remark），DRY。 |
| 操作者身份是否必填 | `@NotNull operatorId/operatorName` | 与 `AuditRefundCommand` 一致；确保每次重发可归属到人，不留匿名写操作。 |
| 系统身份来源 | `RequestContext.getCallerAppName()` | 与 `auditRefund` 一致（既有先验）；admin-bff 作为可信 caller 经 `@RequireSignature` 注入。 |
| OperationLog `result` 取值 | 固定 `"SUCCESS"` | 重发动作本身已执行（已 POST）；投递成败是通知器内部 best-effort 日志（既有行为）。与 `auditRefund` 固定记 SUCCESS（决策已落库）同构，不引入 result=SKIPPED/FAILED（YAGNI，AC 未要求）。 |
| notifyUrl 为空 | 通知器内部 skip（既有行为），仍记 SUCCESS | 既有 `BusinessSystemNotifier.notify` 对空 URL 直接 return；重发不额外判空，保持与首次通知一致语义。 |
| 事务边界 | 重发方法**不加** `@Transactional`、不注入 `TransactionTemplate` | 无状态变更；`OperationLogAppService.record` 自身 `@Transactional`；HTTP 通知不应在事务中（与 `PaymentCallbackAppService` afterCommit 通知同原则）。 |
| 退款 payload 构造 | 提取 `RefundNotifyRequest.from(RefundOrder)` 静态工厂，`RefundAppService.notifyIfTerminal` 与重发服务共用 | 当前构造内联在 `notifyIfTerminal`；重发需产出**与首次通知完全一致**的 payload，单一真源避免分叉。行为保持等价（既有 `RefundAppServiceTest` 守护）。 |
| 端点响应 | `ApiResponse<Void>`（`ApiResponse.ok()`） | 动作型端点无自然实体返回；调用方关心"是否已重发 + 是否终态"，由成功/错误码表达。 |
| pitest 范围 | 追加 `com.aieducenter.payment.application.NotificationResendAppService` | 沿用 pom 既有策略：仅精确覆盖有 Mockito 缝的应用服务；Controller（薄委托）不进评分。 |

## File Structure

```
src/main/java/com/aieducenter/payment/
├── application/
│   ├── dto/
│   │   ├── callback/
│   │   │   └── RefundNotifyRequest.java           [改] +static from(RefundOrder)
│   │   └── command/
│   │       └── ResendNotificationCommand.java      [新] operatorId/operatorName/remark
│   ├── NotificationResendAppService.java           [新] resendPaymentNotification / resendRefundNotification
│   └── RefundAppService.java                        [改] notifyIfTerminal 复用 RefundNotifyRequest.from
├── domain/error/
│   └── PaymentMessage.java                          [改] +PAYMENT_ORDER_NOT_TERMINAL +REFUND_ORDER_NOT_TERMINAL
└── endpoints/api/v1/
    ├── PaymentApiV1Controller.java                  [改] +POST /{paymentOrderNo}/notifications/resend
    └── RefundApiV1Controller.java                   [改] +POST /{refundOrderNo}/notifications/resend

src/test/java/com/aieducenter/payment/
└── application/
    └── NotificationResendAppServiceTest.java        [新] Mockito 缝：终态判定 + 不改状态 + 落日志 + 身份透传

pom.xml                                              [改] pitest targetClasses 追加 NotificationResendAppService
```

**不新增**：迁移（无新表/列）、聚合、仓储方法、枚举。全部复用 #9（OperationLog）与既有（通知器 / 终态判定 / Mapper）成果。

---

### Task 1: DRY 前置——提取 `RefundNotifyRequest.from(RefundOrder)` 并复用于 `notifyIfTerminal`

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/application/dto/callback/RefundNotifyRequest.java`
- Modify: `src/main/java/com/aieducenter/payment/application/RefundAppService.java`（`notifyIfTerminal`，行 303-318）

**Interfaces:**
- Produces（供 Task 2 消费）：`RefundNotifyRequest.from(RefundOrder) → RefundNotifyRequest`（static）

**说明：** 行为保持等价的重构。既有 `RefundAppServiceTest`（含 `auditRefund_approved_querySuccess_notifiesBusiness` 的 `ArgumentCaptor` 校验 `status==5 / refundAmount==10000L`）作为安全网，无需新增测试。

- [ ] **Step 1: 给 `RefundNotifyRequest` 增加 `from` 静态工厂**

在 `RefundNotifyRequest.java` 末尾（record 的 `) {}` 之前）加入：

```java
    /**
     * 由退款聚合构造通知 payload（首次通知与重发共用同一构造真源）。
     */
    public static RefundNotifyRequest from(com.aieducenter.payment.domain.aggregate.RefundOrder order) {
        return new RefundNotifyRequest(
            order.getRefundOrderNo(),
            order.getBusinessOrderNo(),
            order.getPaymentOrderNo(),
            order.getStatus().getCode(),
            order.getStatus().getName(),
            order.getRefundAmount(),
            order.getBankRefundNo(),
            order.getRefundedAt() != null ? order.getRefundedAt().toString() : null,
            order.getAttach()
        );
    }
```

字段映射与 `RefundAppService.notifyIfTerminal` 当前内联构造**逐字段一致**（行 305-315）。

- [ ] **Step 2: `RefundAppService.notifyIfTerminal` 改为调用工厂**

把 `notifyIfTerminal` 内联构造替换为 `RefundNotifyRequest.from(refundOrder)`：

```java
    private void notifyIfTerminal(RefundOrder refundOrder) {
        if (refundOrder.getStatus().isTerminal()) {
            businessSystemNotifier.notify(refundOrder.getNotifyUrl(), RefundNotifyRequest.from(refundOrder));
        }
    }
```

（删去原 9 参 `new RefundNotifyRequest(...)` 内联块。）

- [ ] **Step 3: 编译 + 跑既有 `RefundAppServiceTest` 确认绿**

Run: `mvn -q test -Dtest=RefundAppServiceTest`
Expected: PASS（9 个测试全绿；`ArgumentCaptor` 仍校验到 `status==5 / refundAmount==10000L`，证明 payload 字段不变）。

- [ ] **Step 4: 提交**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/callback/RefundNotifyRequest.java \
        src/main/java/com/aieducenter/payment/application/RefundAppService.java
git commit -m "refactor(payment): 提取 RefundNotifyRequest.from，notifyIfTerminal 与重发服务共用

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 2: `ResendNotificationCommand` + 错误码 + `NotificationResendAppService` + Mockito 缝测试（TDD）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/dto/command/ResendNotificationCommand.java`
- Modify: `src/main/java/com/aieducenter/payment/domain/error/PaymentMessage.java`
- Create: `src/main/java/com/aieducenter/payment/application/NotificationResendAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/NotificationResendAppServiceTest.java`

**Interfaces:**
- Consumes（既有）：
  - `PaymentOrderRepository.findByPaymentOrderNo(String) → Optional<PaymentOrder>`
  - `RefundOrderRepository.findByRefundOrderNo(String) → Optional<RefundOrder>`
  - `BusinessSystemNotifier.notify(String notifyUrl, PaymentOrderResponse)` / `.notify(String, RefundNotifyRequest)`
  - `OperationLogAppService.record(OperationLogTargetType, String, OperationType, Long, String, String, String, String)`
  - `PaymentOrderMapper.convert(PaymentOrder) → PaymentOrderResponse`（static）
  - `RefundNotifyRequest.from(RefundOrder) → RefundNotifyRequest`（static，Task 1 产出）
  - `RequestContext.getCallerAppName() → String`
- Produces（供 Task 3 消费）：
  - `record ResendNotificationCommand(Long operatorId, String operatorName, String remark)`
  - `NotificationResendAppService.resendPaymentNotification(String paymentOrderNo, ResendNotificationCommand)`
  - `NotificationResendAppService.resendRefundNotification(String refundOrderNo, ResendNotificationCommand)`

- [ ] **Step 1: 新建 `ResendNotificationCommand`（setup）**

```java
package com.aieducenter.payment.application.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 通知重发命令。
 *
 * <p>携带操作者身份（来自 admin-bff 请求体），系统身份取自签名上下文（{@code RequestContext.getCallerAppName()}）。
 * 与 {@code AuditRefundCommand} 同构。</p>
 */
public record ResendNotificationCommand(
    @NotNull(message = "操作人ID不能为空")
    Long operatorId,

    @NotNull(message = "操作人姓名不能为空")
    @Size(max = 64, message = "操作人姓名长度不能超过64")
    String operatorName,

    @Size(max = 512, message = "备注长度不能超过512")
    String remark
) {
}
```

- [ ] **Step 2: `PaymentMessage` 增加两个终态校验错误码（setup）**

在 `PaymentMessage.java` 的 `REFUND_ALREADY_REFUNDED`（行 36）之后追加两个枚举项（业务规则错误 400 段）：

```java
    PAYMENT_ORDER_NOT_TERMINAL(400, "PAY_060", "支付订单未处于终态，无法重发通知"),
    REFUND_ORDER_NOT_TERMINAL(400, "PAY_061", "退款订单未处于终态，无法重发通知");
```

（把原 `REFUND_ALREADY_REFUNDED(403, "PAY_042", "已退款，不能重复退款");` 末尾的分号改为逗号。）

- [ ] **Step 3: 写失败测试 `NotificationResendAppServiceTest`**

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;
import com.aieducenter.payment.application.dto.command.ResendNotificationCommand;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.cartisan.core.context.RequestContext;
import com.cartisan.core.exception.ApplicationException;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("通知重发应用服务测试")
class NotificationResendAppServiceTest {

    @Mock private PaymentOrderRepository paymentOrderRepository;
    @Mock private RefundOrderRepository refundOrderRepository;
    @Mock private BusinessSystemNotifier businessSystemNotifier;
    @Mock private OperationLogAppService operationLogAppService;

    private NotificationResendAppService service;

    @BeforeEach
    void setUp() {
        service = new NotificationResendAppService(
            paymentOrderRepository,
            refundOrderRepository,
            businessSystemNotifier,
            operationLogAppService
        );
    }

    // ========== 辅助方法 ==========

    private static ResendNotificationCommand command() {
        return new ResendNotificationCommand(7L, "运营员", "客诉补发");
    }

    private static RequestContext adminContext() {
        return new RequestContext("req-1", "127.0.0.1", "admin-app", "管理后台", null, null, null, null);
    }

    private static PaymentOrder createPaidPaymentOrder() {
        PaymentOrder order = new PaymentOrder(
            "BIZ001", "TestSystem", "课程购买",
            10000L, "Python课程", "描述",
            "https://biz.example.com/notify", "attach", 3600L
        );
        order.markAsPaid("ICBC_ORDER_001", "THIRD_001", PaymentChannel.ICBC, 10000L);
        return order;
    }

    private static PaymentOrder createPendingPaymentOrder() {
        return new PaymentOrder(
            "BIZ001", "TestSystem", "课程购买",
            10000L, "Python课程", "描述",
            "https://biz.example.com/notify", "attach", 3600L
        );
    }

    private static RefundOrder createSuccessRefundOrder() {
        RefundOrder order = new RefundOrder(
            "BIZ001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "取消", null,
            "https://biz.example.com/notify"
        );
        order.audit(1L, "审核员", true, "同意");
        order.startRefund("ICBC_REFUND_001");
        order.refundSuccess("ICBC_REFUND_001");
        return order;
    }

    private static RefundOrder createPendingRefundOrder() {
        return new RefundOrder(
            "BIZ001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "取消", null,
            "https://biz.example.com/notify"
        );
    }

    // ========== 支付通知重发 ==========

    @Test
    @DisplayName("重发支付通知：终态 PAID 重发当前结果并落 OperationLog，不改订单状态")
    void givenPaidPayment_whenResend_thenNotifiesAndLogsNoStateChange() {
        PaymentOrder order = createPaidPaymentOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));

        RequestContext.run(adminContext(), () -> service.resendPaymentNotification("PAY001", command()));

        ArgumentCaptor<PaymentOrderResponse> payload = ArgumentCaptor.forClass(PaymentOrderResponse.class);
        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), payload.capture());
        assertThat(payload.getValue()).isNotNull();
        // 不改订单状态（无 save 调用）
        verify(paymentOrderRepository, never()).save(any(PaymentOrder.class));
        // 落 OperationLog(NOTIFY_RESEND, PAYMENT)，含操作者与系统身份
        ArgumentCaptor<OperationType> op = ArgumentCaptor.forClass(OperationType.class);
        verify(operationLogAppService).record(
            eq(OperationLogTargetType.PAYMENT), eq("PAY001"), op.capture(),
            eq(7L), eq("运营员"), eq("管理后台"), eq("SUCCESS"), eq("客诉补发")
        );
        assertThat(op.getValue()).isEqualTo(OperationType.NOTIFY_RESEND);
    }

    @Test
    @DisplayName("重发支付通知：非终态 PENDING 拒绝并返回明确错误，不重发不留痕")
    void givenPendingPayment_whenResend_thenRejectedNoNotifyNoLog() {
        PaymentOrder order = createPendingPaymentOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> service.resendPaymentNotification("PAY001", command()))
            .isInstanceOf(ApplicationException.class)
            .hasMessage(PaymentMessage.PAYMENT_ORDER_NOT_TERMINAL.message());

        verify(businessSystemNotifier, never()).notify(anyString(), any(PaymentOrderResponse.class));
        verify(operationLogAppService, never())
            .record(any(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("重发支付通知：订单不存在抛 404")
    void givenMissingPayment_whenResend_thenNotFound() {
        when(paymentOrderRepository.findByPaymentOrderNo("NOPE")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.resendPaymentNotification("NOPE", command()))
            .isInstanceOf(ApplicationException.class)
            .hasMessage(PaymentMessage.PAYMENT_ORDER_NOT_FOUND.message());
    }

    // ========== 退款通知重发 ==========

    @Test
    @DisplayName("重发退款通知：终态 SUCCESS 重发当前结果并落 OperationLog，不改订单状态")
    void givenSuccessRefund_whenResend_thenNotifiesAndLogsNoStateChange() {
        RefundOrder order = createSuccessRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001")).thenReturn(Optional.of(order));

        RequestContext.run(adminContext(), () -> service.resendRefundNotification("REF001", command()));

        ArgumentCaptor<RefundNotifyRequest> payload = ArgumentCaptor.forClass(RefundNotifyRequest.class);
        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), payload.capture());
        assertThat(payload.getValue().refundAmount()).isEqualTo(10000L);
        assertThat(payload.getValue().status()).isEqualTo(RefundStatus.SUCCESS.getCode());
        // 不改订单状态
        verify(refundOrderRepository, never()).save(any(RefundOrder.class));
        // 落 OperationLog(NOTIFY_RESEND, REFUND)
        ArgumentCaptor<OperationType> op = ArgumentCaptor.forClass(OperationType.class);
        verify(operationLogAppService).record(
            eq(OperationLogTargetType.REFUND), eq("REF001"), op.capture(),
            eq(7L), eq("运营员"), eq("管理后台"), eq("SUCCESS"), eq("客诉补发")
        );
        assertThat(op.getValue()).isEqualTo(OperationType.NOTIFY_RESEND);
    }

    @Test
    @DisplayName("重发退款通知：非终态 PENDING 拒绝并返回明确错误")
    void givenPendingRefund_whenResend_thenRejectedNoNotifyNoLog() {
        RefundOrder order = createPendingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001")).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> service.resendRefundNotification("REF001", command()))
            .isInstanceOf(ApplicationException.class)
            .hasMessage(PaymentMessage.REFUND_ORDER_NOT_TERMINAL.message());

        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
        verify(operationLogAppService, never())
            .record(any(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("重发退款通知：订单不存在抛 404")
    void givenMissingRefund_whenResend_thenNotFound() {
        when(refundOrderRepository.findByRefundOrderNo("NOPE")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.resendRefundNotification("NOPE", command()))
            .isInstanceOf(ApplicationException.class)
            .hasMessage(PaymentMessage.REFUND_ORDER_NOT_FOUND.message());
    }
}
```

- [ ] **Step 4: 跑测试确认失败（类不存在）**

Run: `mvn -q test -Dtest=NotificationResendAppServiceTest`
Expected: 编译失败——`NotificationResendAppService` 符号不存在。

- [ ] **Step 5: 实现 `NotificationResendAppService`**

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;
import com.aieducenter.payment.application.dto.command.ResendNotificationCommand;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.cartisan.core.context.RequestContext;
import com.cartisan.core.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 通知重发应用服务。
 *
 * <p>运营补发业务系统漏收的支付/退款结果通知：仅当订单处于<b>终态</b>时，把当前结果重 POST 到其
 * {@code notifyUrl}（复用 {@link BusinessSystemNotifier}），<b>不改订单状态、不重置时间线</b>（ADR-0001）。
 * 每次重发落 {@code OperationLog(NOTIFY_RESEND)}，含操作者（请求体）与系统身份（签名上下文）。</p>
 */
@Service
@RequiredArgsConstructor
public class NotificationResendAppService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final RefundOrderRepository refundOrderRepository;
    private final BusinessSystemNotifier businessSystemNotifier;
    private final OperationLogAppService operationLogAppService;

    /**
     * 重发支付结果通知。
     *
     * @param paymentOrderNo 支付订单号
     * @param command        操作者身份（来自请求体）
     */
    public void resendPaymentNotification(String paymentOrderNo, ResendNotificationCommand command) {
        PaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));

        if (!order.getStatus().isTerminal()) {
            throw new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_TERMINAL);
        }

        businessSystemNotifier.notify(order.getNotifyUrl(), PaymentOrderMapper.convert(order));

        operationLogAppService.record(
            OperationLogTargetType.PAYMENT,
            paymentOrderNo,
            OperationType.NOTIFY_RESEND,
            command.operatorId(),
            command.operatorName(),
            RequestContext.getCallerAppName(),
            "SUCCESS",
            command.remark()
        );
    }

    /**
     * 重发退款结果通知。
     *
     * @param refundOrderNo 退款订单号
     * @param command       操作者身份（来自请求体）
     */
    public void resendRefundNotification(String refundOrderNo, ResendNotificationCommand command) {
        RefundOrder order = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
            .orElseThrow(() -> new ApplicationException(PaymentMessage.REFUND_ORDER_NOT_FOUND));

        if (!order.getStatus().isTerminal()) {
            throw new ApplicationException(PaymentMessage.REFUND_ORDER_NOT_TERMINAL);
        }

        businessSystemNotifier.notify(order.getNotifyUrl(), RefundNotifyRequest.from(order));

        operationLogAppService.record(
            OperationLogTargetType.REFUND,
            refundOrderNo,
            OperationType.NOTIFY_RESEND,
            command.operatorId(),
            command.operatorName(),
            RequestContext.getCallerAppName(),
            "SUCCESS",
            command.remark()
        );
    }
}
```

- [ ] **Step 6: 跑测试确认通过**

Run: `mvn -q test -Dtest=NotificationResendAppServiceTest`
Expected: PASS（6 个测试全绿）。

- [ ] **Step 7: 提交**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/command/ResendNotificationCommand.java \
        src/main/java/com/aieducenter/payment/domain/error/PaymentMessage.java \
        src/main/java/com/aieducenter/payment/application/NotificationResendAppService.java \
        src/test/java/com/aieducenter/payment/application/NotificationResendAppServiceTest.java
git commit -m "feat(payment): NotificationResendAppService（仅终态重发 + 不改状态 + 落 OperationLog）

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 3: 两个重发端点（薄委托，`@RequireSignature`）

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentApiV1Controller.java`
- Modify: `src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java`

**Interfaces:**
- Consumes（Task 2 产出）：`NotificationResendAppService.resendPaymentNotification` / `resendRefundNotification`

**说明：** 薄委托，无新测试缝（行为由 Task 2 的 AppService 缝覆盖，签名校验兜底沿用既有 `SignatureVerificationIntegrationTest`）。

- [ ] **Step 1: `PaymentApiV1Controller` 注入服务并加端点**

在顶部注入字段组（`paymentAppService` / `paymentOrderQueryAppService` 同处）加入：

```java
    private final NotificationResendAppService notificationResendAppService;
```

并补 import：

```java
import com.aieducenter.payment.application.NotificationResendAppService;
import com.aieducenter.payment.application.dto.command.ResendNotificationCommand;
```

在 `cancelPayment` 方法之后追加端点：

```java
    @PostMapping("/{paymentOrderNo}/notifications/resend")
    @RequireSignature
    @Operation(summary = "重发支付结果通知")
    public ApiResponse<Void> resendPaymentNotification(
            @PathVariable String paymentOrderNo,
            @Valid @RequestBody ResendNotificationCommand command
    ) {
        notificationResendAppService.resendPaymentNotification(paymentOrderNo, command);
        return ApiResponse.ok();
    }
```

- [ ] **Step 2: `RefundApiV1Controller` 注入服务并加端点**

在顶部注入字段组（`refundAppService` / `refundOrderQueryAppService` 同处）加入：

```java
    private final NotificationResendAppService notificationResendAppService;
```

并补 import：

```java
import com.aieducenter.payment.application.NotificationResendAppService;
import com.aieducenter.payment.application.dto.command.ResendNotificationCommand;
```

在 `auditRefund` 方法之后（或 `list` 之前）追加端点：

```java
    @PostMapping("/{refundOrderNo}/notifications/resend")
    @RequireSignature
    @Operation(summary = "重发退款结果通知")
    public ApiResponse<Void> resendRefundNotification(
            @PathVariable String refundOrderNo,
            @Valid @RequestBody ResendNotificationCommand command
    ) {
        notificationResendAppService.resendRefundNotification(refundOrderNo, command);
        return ApiResponse.ok();
    }
```

- [ ] **Step 3: 编译 + 跑全量测试确认无回归**

Run: `mvn -q test`
Expected: 全绿（含既有 `SignatureVerificationIntegrationTest` / `ArchitectureTest`）。

- [ ] **Step 4: 提交**

```bash
git add src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentApiV1Controller.java \
        src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java
git commit -m "feat(payment): POST payments/refunds/{no}/notifications/resend 端点（@RequireSignature）

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 4: pitest 覆盖 + 全量验证

**Files:**
- Modify: `pom.xml`（pitest `targetClasses`）

- [ ] **Step 1: `targetClasses` 追加 `NotificationResendAppService`**

在 `pom.xml` pitest `<targetClasses>` 块末尾（`OrderLifecycleAppService` 之后）加一行，并在上方注释块补一行 issue #16 说明：

```xml
                        <param>com.aieducenter.payment.application.OrderLifecycleAppService</param>
                        <param>com.aieducenter.payment.application.NotificationResendAppService</param>
```

注释块追加（与既有 issue #10..#14 同构）：

```
                        issue #16 起追加 NotificationResendAppService（通知重发应用服务缝）。
```

- [ ] **Step 2: 跑变异测试**

Run: `mvn org.pitest:pitest-maven:mutationCoverage`
Expected: BUILD SUCCESS；`NotificationResendAppService` 变异分 ≥ 70%（终态判定 / 不改状态 / 落日志 / 身份透传分支均被缝测试杀死）。

- [ ] **Step 3: 跑全量测试 + 编译兜底**

Run: `mvn -q test && mvn -q compile`
Expected: 全绿。

- [ ] **Step 4: 提交**

```bash
git add pom.xml
git commit -m "chore(build): pitest 覆盖 NotificationResendAppService

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## AC 对照

| Issue #16 验收项 | 覆盖 |
|------------------|------|
| 仅终态单重发；非终态拒绝并返回明确错误 | Task 2 测试 `*_nonTerminal*_rejected` + `PAYMENT_ORDER_NOT_TERMINAL` / `REFUND_ORDER_NOT_TERMINAL` |
| 重发不改订单状态、不重置时间线 | Task 2 实现（无 `save` / 状态迁移调用）+ 测试 `verify(..., never()).save(any())` |
| 落 OperationLog(NOTIFY_RESEND)，含操作者与系统身份 | Task 2 实现 `record(...NOTIFY_RESEND..., operatorId, operatorName, getCallerAppName(), ...)` + `ArgumentCaptor` 验证 |
| AppService Mockito 缝测试覆盖终态判定与"不改状态"，pitest 过 | Task 2 缝测试 + Task 4 pitest |

## Self-Review

- **Spec 覆盖**：spec US 14/15（重发支付/退款通知）→ Task 2+3；US 16（不改状态）→ Task 2 实现 + never().save 断言；US 25/26（留痕 + 系统/操作者身份）→ Task 2 record 调用 + captor；ADR-0001（不施加状态）→ 全程无 save。端点两条 → Task 3。无遗漏。
- **Placeholder 扫描**：每步含完整可编译代码 / 精确命令 / 预期输出，无 TBD。
- **类型一致**：`ResendNotificationCommand(operatorId, operatorName, remark)` 在 Task 2 定义、Task 3 端点引用一致；`RefundNotifyRequest.from(RefundOrder)` 在 Task 1 定义、Task 2 消费一致；`record(...)` 8 参签名与 `OperationLogAppService` 既有定义一致（Task 2 captor 顺序：targetType, targetNo, operation, operatorId, operatorName, operatorSystem, result, remark）。
