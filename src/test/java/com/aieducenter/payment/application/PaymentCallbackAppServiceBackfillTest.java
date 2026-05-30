package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.IcbcCallbackParam;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
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
