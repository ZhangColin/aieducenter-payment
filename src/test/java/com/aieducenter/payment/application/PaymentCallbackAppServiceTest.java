package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.IcbcCallbackParam;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.aieducenter.payment.infrastructure.icbc.IcbcCallbackVerifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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

        // Initialize transaction synchronization for unit tests
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.initSynchronization();
        }
    }

    @AfterEach
    void tearDown() {
        // Manually trigger afterCommit callbacks so verify() assertions work
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.getSynchronizations().forEach(sync -> {
                if (sync instanceof TransactionSynchronization ts) {
                    ts.afterCommit();
                }
            });
            TransactionSynchronizationManager.clearSynchronization();
        }
    }

    private PaymentOrder createPendingOrder() {
        PaymentOrder order = new PaymentOrder(
            "BIZ001", "TestSystem", "课程购买",
            10000L, "Python课程", "Python编程课程",
            "https://biz.example.com/notify", "attach-data", 3600L
        );
        return order;
    }

    /**
     * Trigger registered TransactionSynchronization.afterCommit() callbacks.
     * In unit tests without a real Spring transaction, we must invoke these manually.
     */
    private void triggerAfterCommit() {
        TransactionSynchronizationManager.getSynchronizations().forEach(sync -> {
            if (sync instanceof TransactionSynchronization ts) {
                ts.afterCommit();
            }
        });
    }

    @Test
    @DisplayName("支付成功回调：更新订单为PAID并通知业务系统")
    void handleCallback_paymentSuccess_updatesOrderAndNotifies() {
        // Given
        PaymentOrder order = createPendingOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));
        when(paymentOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(icbcCallbackVerifier.generateResponseMsgId()).thenReturn("msg-id-success");
        when(icbcCallbackVerifier.buildCallbackResponse(any())).thenReturn("{\"response_biz_content\":{\"return_code\":0},\"sign_type\":\"RSA2\",\"sign\":\"xxx\"}");

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

        // Trigger afterCommit callbacks (normally done by Spring transaction manager)
        triggerAfterCommit();

        // Verify business system was notified
        ArgumentCaptor<PaymentOrderResponse> notifyCaptor = ArgumentCaptor.forClass(PaymentOrderResponse.class);
        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), notifyCaptor.capture());
        PaymentOrderResponse notifyResponse = notifyCaptor.getValue();
        assertThat(notifyResponse.status()).isEqualTo(2);
        assertThat(notifyResponse.businessOrderNo()).isEqualTo("BIZ001");
    }

    @Test
    @DisplayName("支付失败回调：更新订单为FAILED")
    void handleCallback_paymentFailed_updatesOrderToFailed() {
        PaymentOrder order = createPendingOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY002")).thenReturn(Optional.of(order));
        when(paymentOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(icbcCallbackVerifier.generateResponseMsgId()).thenReturn("msg-id-failed");
        when(icbcCallbackVerifier.buildCallbackResponse(any())).thenReturn("{}");

        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "21003116", "查询验证不通过", "21003116", "msg-002",
            "PAY002", "", "", "",
            "", "", "", "", "", "", "",
            "", "", "", "", "", "", "",
            "", ""
        );

        String response = service.handleCallback("/api/v1/payment/callbacks/icbc", Map.of(), "sign", callbackParam);

        assertThat(order.getStatus()).isEqualTo(PaymentStatus.FAILED);
        triggerAfterCommit();
        verify(businessSystemNotifier).notify(anyString(), any(PaymentOrderResponse.class));
    }

    @Test
    @DisplayName("签名验证失败：返回错误响应，不更新订单")
    void handleCallback_signatureInvalid_returnsErrorWithoutUpdate() {
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(false);
        when(icbcCallbackVerifier.generateResponseMsgId()).thenReturn("msg-id-invalid");
        when(icbcCallbackVerifier.buildCallbackResponse(any())).thenReturn("{}");

        IcbcCallbackParam callbackParam = new IcbcCallbackParam(
            "0", "success", "0", "msg-003", "PAY003", "", "10000", "10000",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
        );

        String response = service.handleCallback("/api/v1/payment/callbacks/icbc", Map.of(), "bad-sign", callbackParam);

        verify(paymentOrderRepository, never()).save(any());
        verify(businessSystemNotifier, never()).notify(anyString(), any(PaymentOrderResponse.class));
    }

    @Test
    @DisplayName("订单不存在：记录日志但不抛异常")
    void handleCallback_orderNotFound_returnsResponse() {
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(paymentOrderRepository.findByPaymentOrderNo("NOTFOUND")).thenReturn(Optional.empty());
        when(icbcCallbackVerifier.generateResponseMsgId()).thenReturn("msg-id-notfound");
        when(icbcCallbackVerifier.buildCallbackResponse(any())).thenReturn("{}");

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
        PaymentOrder order = createPendingOrder();
        order.markAsPaid("EXISTING_ICBC", null, PaymentChannel.ICBC, 10000L);
        when(icbcCallbackVerifier.verifySignature(anyString(), any(), anyString())).thenReturn(true);
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(order));
        when(icbcCallbackVerifier.generateResponseMsgId()).thenReturn("msg-id-dup");
        when(icbcCallbackVerifier.buildCallbackResponse(any())).thenReturn("{}");

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
