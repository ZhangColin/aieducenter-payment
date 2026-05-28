package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.command.CreateHsbPaymentCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.CreateHsbPaymentResponse;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.infrastructure.HsbConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("HsbPaymentAppService 测试")
class HsbPaymentAppServiceTest {

    private HsbPaymentOrderRepository paymentOrderRepository;
    private HsbPaymentLogRepository paymentLogRepository;
    private HsbPaymentGatewayPort gatewayPort;
    private HsbConfig hsbConfig;
    private TransactionTemplate transactionTemplate;
    private HsbPaymentAppService service;

    @BeforeEach
    void setUp() {
        paymentOrderRepository = mock(HsbPaymentOrderRepository.class);
        paymentLogRepository = mock(HsbPaymentLogRepository.class);
        gatewayPort = mock(HsbPaymentGatewayPort.class);
        hsbConfig = new HsbConfig();
        hsbConfig.setMktId("41060860811052");
        transactionTemplate = mock(TransactionTemplate.class);
        // 让 TransactionTemplate 直接执行 callback，模拟独立事务
        when(transactionTemplate.execute(any())).thenAnswer(inv -> {
            org.springframework.transaction.support.TransactionCallback<?> callback = inv.getArgument(0);
            return callback.doInTransaction(null);
        });
        doAnswer(inv -> {
            java.util.function.Consumer<org.springframework.transaction.TransactionStatus> action = inv.getArgument(0);
            action.accept(null);
            return null;
        }).when(transactionTemplate).executeWithoutResult(any());

        service = new HsbPaymentAppService(
            paymentOrderRepository, paymentLogRepository, gatewayPort, hsbConfig, transactionTemplate
        );
    }

    private CreateHsbPaymentCommand createCommand() {
        return CreateHsbPaymentCommand.builder()
            .businessMainOrderNo("BIZ001")
            .paymentMethod("03")
            .orderType("04")
            .totalAmount(10000L)
            .txnTotalAmount(10000L)
            .subOrders(List.of(
                CreateHsbPaymentCommand.HsbSubOrderCommand.builder()
                    .businessSubOrderNo("SUB001")
                    .mktMrchId("MERCH001")
                    .orderAmount(10000L)
                    .txnAmount(10000L)
                    .build()
            ))
            .build();
    }

    @Test
    @DisplayName("建行接口返回成功时，订单保存后更新支付结果")
    void given_gatewaySuccess_when_createPayment_then_orderSavedAndUpdated() {
        CreateHsbPaymentCommand command = createCommand();
        when(paymentOrderRepository.save(any(HsbPaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo(any())).thenAnswer(inv -> {
            HsbPaymentOrder order = new HsbPaymentOrder(
                "BIZ001", "TestSystem", null, "41060860811052", "03", "04", "156",
                10000L, 10000L, null, 3600L, null, null, null, null, List.<HsbSubOrder>of());
            return java.util.Optional.of(order);
        });

        CreateHsbPaymentResponse gatewayResponse = new CreateHsbPaymentResponse(
            true, "00", "SUCCESS", "http://cashier.url", "http://pay.url", "QR123", "PRIM001",
            100L, "{}", "{}", null);
        when(gatewayPort.createPayment(any(), any())).thenReturn(gatewayResponse);

        HsbPaymentOrderResponse response = service.createPayment(command, "TestSystem");

        assertThat(response).isNotNull();
        // 验证 save 被调用了至少两次：一次创建，一次更新支付结果
        verify(paymentOrderRepository, atLeast(2)).save(any(HsbPaymentOrder.class));
        verify(gatewayPort).createPayment(any(), any());
    }

    @Test
    @DisplayName("建行接口调用失败时，异常向上抛出")
    void given_gatewayFailure_when_createPayment_then_throwsException() {
        CreateHsbPaymentCommand command = createCommand();
        when(paymentOrderRepository.save(any(HsbPaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(gatewayPort.createPayment(any(), any())).thenReturn(
            new CreateHsbPaymentResponse(false, "01", "FAIL", null, null, null, null, 50L, "{}", "{}", null));

        assertThatThrownBy(() -> service.createPayment(command, "TestSystem"))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("建行网关调用失败");
    }

    @Test
    @DisplayName("订单先保存再调用建行接口（save 在 gateway 之前）")
    void given_createPayment_when_orderCreated_then_saveBeforeGateway() {
        CreateHsbPaymentCommand command = createCommand();
        when(paymentOrderRepository.save(any(HsbPaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo(any())).thenAnswer(inv -> {
            HsbPaymentOrder order = new HsbPaymentOrder(
                "BIZ001", "TestSystem", null, "41060860811052", "03", "04", "156",
                10000L, 10000L, null, 3600L, null, null, null, null, List.<HsbSubOrder>of());
            return java.util.Optional.of(order);
        });
        when(gatewayPort.createPayment(any(), any())).thenReturn(
            new CreateHsbPaymentResponse(true, "00", "SUCCESS", "http://cashier.url", "http://pay.url", null, "PRIM001", 100L, "{}", "{}", null));

        service.createPayment(command, "TestSystem");

        var inOrder = inOrder(paymentOrderRepository, gatewayPort);
        inOrder.verify(paymentOrderRepository).save(any(HsbPaymentOrder.class));
        inOrder.verify(gatewayPort).createPayment(any(), any());
    }

    @Test
    @DisplayName("建行返回子订单ID时，通过business_sub_order_no匹配回填Sub_Ordr_Id")
    void given_gatewayReturnsSubOrderIds_when_createPayment_then_subOrderIdsMappedToSubOrders() {
        CreateHsbPaymentCommand command = createCommand();
        when(paymentOrderRepository.save(any(HsbPaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentOrderRepository.findByPaymentOrderNo(any())).thenAnswer(inv -> {
            HsbPaymentOrder order = new HsbPaymentOrder(
                "BIZ001", "TestSystem", null, "41060860811052", "03", "04", "156",
                10000L, 10000L, null, 3600L, null, null, null, null, List.<HsbSubOrder>of());
            return java.util.Optional.of(order);
        });

        Map<String, String> subOrderIds = Map.of("SUB001", "105000007630317032310103433719001");
        CreateHsbPaymentResponse gatewayResponse = new CreateHsbPaymentResponse(
            true, "00", "SUCCESS", "http://cashier.url", "http://pay.url", "QR123", "PRIM001",
            100L, "{}", "{}", subOrderIds);
        when(gatewayPort.createPayment(any(), any())).thenReturn(gatewayResponse);

        HsbPaymentOrderResponse response = service.createPayment(command, "TestSystem");

        assertThat(response).isNotNull();
        assertThat(response.subOrders()).hasSize(1);
        assertThat(response.subOrders().get(0).subOrderId())
            .isEqualTo("105000007630317032310103433719001");
    }
}
