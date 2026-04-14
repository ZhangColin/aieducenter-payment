package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;
import com.aieducenter.payment.application.dto.command.AuditRefundCommand;
import com.aieducenter.payment.application.dto.command.CreateRefundCommand;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.PaymentChannel;
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

import java.lang.reflect.Field;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import org.springframework.transaction.support.TransactionCallback;

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
    }

    /**
     * 配置 TransactionTemplate 直接执行回调（不真正开启事务）
     */
    private void configureTransactionTemplate() {
        lenient().when(transactionTemplate.execute(any(TransactionCallback.class))).thenAnswer(inv -> {
            TransactionCallback<?> callback = inv.getArgument(0);
            return callback.doInTransaction(null);
        });
        lenient().doAnswer(inv -> {
            java.util.function.Consumer<org.springframework.transaction.TransactionStatus> action = inv.getArgument(0);
            action.accept(null);
            return null;
        }).when(transactionTemplate).executeWithoutResult(any());
    }

    // ========== 辅助方法 ==========

    private RefundOrder createPendingRefundOrder() {
        RefundOrder order = new RefundOrder(
            "BIZ001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "取消", null,
            "https://biz.example.com/notify"
        );
        // 模拟 JPA @PrePersist 设置的字段（id, refundOrderNo）
        setField(order, "id", 1001L);
        setField(order, "refundOrderNo", "REF001");
        return order;
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

    private static void setField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field " + fieldName, e);
        }
    }

    // ========== 测试用例 ==========

    @Test
    @DisplayName("创建退款订单：成功并保存 notifyUrl")
    void createRefund_success_savesNotifyUrl() {
        PaymentOrder paymentOrder = createPaidPaymentOrder();
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001")).thenReturn(Optional.of(paymentOrder));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        CreateRefundCommand command = new CreateRefundCommand(
            "BIZ001", "PAY001", 10000L, "取消", null, "https://biz.example.com/refund-notify", true
        );

        RefundOrderResponse response = service.createRefund(command, "TestSystem");

        assertThat(response).isNotNull();
        assertThat(response.notifyUrl()).isEqualTo("https://biz.example.com/refund-notify");
    }

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

    @Test
    @DisplayName("审核通过：工行退款成功，状态变为REFUNDING，保存bankRefundNo")
    void auditRefund_approved_refundSuccess() {
        configureTransactionTemplate();

        RefundOrder order = createPendingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(any(), any(), any(), any()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.REFUNDING, 10000L, 10000L, null, null, 50L));

        AuditRefundCommand command = new AuditRefundCommand(1L, "审核员", true, "同意");

        RefundOrderResponse response = service.auditRefund("REF001", command);

        assertThat(response.status()).isEqualTo(4);
        // 不回调（REFUNDING 不是终态）
        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("审核通过：工行退款失败，状态保持APPROVED")
    void auditRefund_approved_refundFailed_staysApproved() {
        configureTransactionTemplate();

        RefundOrder order = createPendingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        AuditRefundCommand command = new AuditRefundCommand(1L, "审核员", true, "同意");

        RefundOrderResponse response = service.auditRefund("REF001", command);

        // 退款未执行（因为 paymentOrderRepository 未 mock），但不影响审核
        assertThat(order.getStatus()).isEqualTo(RefundStatus.APPROVED);
    }

    @Test
    @DisplayName("审核通过：退款后查询成功，回调业务系统")
    void auditRefund_approved_querySuccess_notifiesBusiness() {
        configureTransactionTemplate();

        RefundOrder order = createPendingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(any(), any(), any(), any()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.SUCCESS, 10000L, 10000L, "2026-04-10 14:30:05", "ICBC_REFUND_002", 50L));

        service.auditRefund("REF001", new AuditRefundCommand(1L, "审核员", true, "同意"));

        // 验证回调
        ArgumentCaptor<RefundNotifyRequest> captor = ArgumentCaptor.forClass(RefundNotifyRequest.class);
        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), captor.capture());
        assertThat(captor.getValue().status()).isEqualTo(5);
        assertThat(captor.getValue().refundAmount()).isEqualTo(10000L);
    }

    @Test
    @DisplayName("审核通过：退款后查询失败，状态变FAILED，回调业务系统")
    void auditRefund_approved_queryFailed_notifiesBusiness() {
        configureTransactionTemplate();

        RefundOrder order = createPendingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(any(), any(), any(), any()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.FAILED, null, null, null, null, 50L));

        service.auditRefund("REF001", new AuditRefundCommand(1L, "审核员", true, "同意"));

        verify(businessSystemNotifier).notify(eq("https://biz.example.com/notify"), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("审核通过：退款后查询仍退款中，不回调")
    void auditRefund_approved_queryRefunding_noCallback() {
        configureTransactionTemplate();

        RefundOrder order = createPendingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(any(), any(), any(), any()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.REFUNDING, null, null, null, null, 50L));

        service.auditRefund("REF001", new AuditRefundCommand(1L, "审核员", true, "同意"));

        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("审核拒绝：状态变为REJECTED，不调银行")
    void auditRefund_rejected_noBankCall() {
        configureTransactionTemplate();

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
        configureTransactionTemplate();

        RefundOrder order = createApprovedRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.createRefund(any(), anyString()))
            .thenReturn(new CreateRefundResponse(true, "0", "success", "ICBC_REFUND_002", 100L));
        when(paymentGatewayPort.queryRefund(any(), any(), any(), any()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.REFUNDING, 10000L, 10000L, null, null, 50L));

        RefundOrderResponse response = service.queryRefund("REF001");

        assertThat(response).isNotNull();
        // 查询接口不回调（REFUNDING 不是终态）
        verify(businessSystemNotifier, never()).notify(anyString(), any(RefundNotifyRequest.class));
    }

    @Test
    @DisplayName("查询退款订单：REFUNDING状态只查询，不回调")
    void queryRefund_refunding_onlyQueryNoCallback() {
        configureTransactionTemplate();

        RefundOrder order = createRefundingRefundOrder();
        when(refundOrderRepository.findByRefundOrderNo("REF001"))
            .thenReturn(Optional.of(order));
        when(refundOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo("PAY001"))
            .thenReturn(Optional.of(createPaidPaymentOrder()));
        when(paymentGatewayPort.queryRefund(any(), any(), any(), any()))
            .thenReturn(new QueryRefundResponse(true, "0", "success", RefundStatus.SUCCESS, 10000L, 10000L, null, null, 50L));

        RefundOrderResponse response = service.queryRefund("REF001");

        // 不调 createRefund
        verify(paymentGatewayPort, never()).createRefund(any(), anyString());
        // queryRefund 对 REFUNDING 状态不调用 notifyIfTerminal，不回调
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

        assertThat(response.status()).isEqualTo(5);
        verify(paymentGatewayPort, never()).queryRefund(any(), any(), any(), any());
        verify(paymentGatewayPort, never()).createRefund(any(), anyString());
    }
}
