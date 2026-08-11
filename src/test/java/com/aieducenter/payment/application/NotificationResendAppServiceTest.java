package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;
import com.aieducenter.payment.application.dto.command.ResendNotificationCommand;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.NotificationDeliveryResult;
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
        when(businessSystemNotifier.notify(anyString(), any(PaymentOrderResponse.class)))
            .thenReturn(NotificationDeliveryResult.DELIVERED);

        RequestContext.run(adminContext(), () -> service.resendPaymentNotification("PAY001", command()));

        ArgumentCaptor<PaymentOrderResponse> payload = ArgumentCaptor.forClass(PaymentOrderResponse.class);
        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), payload.capture());
        assertThat(payload.getValue()).isNotNull();
        // 不改订单状态（无 save 调用）
        verify(paymentOrderRepository, never()).save(any(PaymentOrder.class));
        // 落 OperationLog(NOTIFY_RESEND, PAYMENT)，含操作者与系统身份；送达成功记 SUCCESS
        ArgumentCaptor<OperationType> op = ArgumentCaptor.forClass(OperationType.class);
        verify(operationLogAppService).record(
            eq(OperationLogTargetType.PAYMENT), eq("PAY001"), op.capture(),
            eq(7L), eq("运营员"), eq("管理后台"), eq("SUCCESS"), eq("客诉补发")
        );
        assertThat(op.getValue()).isEqualTo(OperationType.NOTIFY_RESEND);
    }

    @Test
    @DisplayName("重发支付通知：投递失败（非200/异常）记 DELIVERY_FAILED")
    void givenDeliveryFailed_whenResend_thenLogsDeliveryFailed() {
        PaymentOrder order = createPaidPaymentOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));
        when(businessSystemNotifier.notify(anyString(), any(PaymentOrderResponse.class)))
            .thenReturn(NotificationDeliveryResult.FAILED);

        RequestContext.run(adminContext(), () -> service.resendPaymentNotification("PAY001", command()));

        verify(operationLogAppService).record(
            eq(OperationLogTargetType.PAYMENT), eq("PAY001"), eq(OperationType.NOTIFY_RESEND),
            eq(7L), eq("运营员"), eq("管理后台"), eq("DELIVERY_FAILED"), eq("客诉补发")
        );
    }

    @Test
    @DisplayName("重发支付通知：未投递（blank notifyUrl）记 SKIPPED")
    void givenSkippedDelivery_whenResend_thenLogsSkipped() {
        PaymentOrder order = createPaidPaymentOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));
        when(businessSystemNotifier.notify(anyString(), any(PaymentOrderResponse.class)))
            .thenReturn(NotificationDeliveryResult.SKIPPED);

        RequestContext.run(adminContext(), () -> service.resendPaymentNotification("PAY001", command()));

        verify(operationLogAppService).record(
            eq(OperationLogTargetType.PAYMENT), eq("PAY001"), eq(OperationType.NOTIFY_RESEND),
            eq(7L), eq("运营员"), eq("管理后台"), eq("SKIPPED"), eq("客诉补发")
        );
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
        when(businessSystemNotifier.notify(anyString(), any(RefundNotifyRequest.class)))
            .thenReturn(NotificationDeliveryResult.DELIVERED);

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
