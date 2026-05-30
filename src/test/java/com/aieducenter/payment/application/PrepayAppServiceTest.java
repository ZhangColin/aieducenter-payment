package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.command.CreatePrepayCommand;
import com.aieducenter.payment.application.dto.response.PrepayOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.CreatePrepayResponse;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("预支付应用服务测试")
class PrepayAppServiceTest {

    @Mock private PaymentOrderRepository paymentOrderRepository;
    @Mock private PaymentGatewayPort paymentGatewayPort;
    @Mock private PaymentLogRepository paymentLogRepository;

    private PrepayAppService service;

    @BeforeEach
    void setUp() {
        service = new PrepayAppService(paymentOrderRepository, paymentGatewayPort, paymentLogRepository);
    }

    private CreatePrepayCommand createWechatCommand() {
        return new CreatePrepayCommand(
            "BIZ001", 10000L, "Python课程", "Python编程课程",
            "课程购买", "https://biz.example.com/notify", 3600L, null,
            9, 7, "oUSDOusdsdISLSDlskdf", null
        );
    }

    private CreatePrepayResponse createSuccessPrepayResponse() {
        return new CreatePrepayResponse(
            true, "0", "success",
            "ICBC_ORDER_123",
            "{\"appid\":\"wx123\",\"prepayid\":\"pre123\"}",
            "JSAPI",
            200L,
            "{\"request\":true}",
            "{\"response\":true}"
        );
    }

    @Test
    @DisplayName("创建微信预支付：成功返回支付参数包")
    void createPrepay_wechat_success_returnsPrepayResponse() {
        // Given
        CreatePrepayCommand command = createWechatCommand();
        CreatePrepayResponse gatewayResponse = createSuccessPrepayResponse();

        when(paymentOrderRepository.save(any(PaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentGatewayPort.createPrepay(any(PaymentOrder.class))).thenReturn(gatewayResponse);

        // When
        PrepayOrderResponse response = service.createPrepay(command, "TestSystem", "192.168.1.1");

        // Then
        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(PaymentStatus.PENDING.getCode());
        assertThat(response.prepayDataPackage()).isEqualTo("{\"appid\":\"wx123\",\"prepayid\":\"pre123\"}");
        assertThat(response.payMode()).isEqualTo(9);
        assertThat(response.payModeName()).isEqualTo("微信");
        assertThat(response.accessType()).isEqualTo(7);
        assertThat(response.accessTypeName()).isEqualTo("微信公众号");

        // Verify order was saved with correct fields
        ArgumentCaptor<PaymentOrder> orderCaptor = ArgumentCaptor.forClass(PaymentOrder.class);
        verify(paymentOrderRepository, atLeastOnce()).save(orderCaptor.capture());
        PaymentOrder savedOrder = orderCaptor.getAllValues().stream()
            .filter(o -> o.getPrepayDataPackage() != null)
            .findFirst().orElse(null);
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getPayMode()).isEqualTo(PayMode.WECHAT);
        assertThat(savedOrder.getAccessType()).isEqualTo(AccessType.WECHAT_OA);
        assertThat(savedOrder.getPrepayDataPackage()).contains("wx123");
        assertThat(savedOrder.getOpenId()).isEqualTo("oUSDOusdsdISLSDlskdf");
    }

    @Test
    @DisplayName("创建预支付：记录日志")
    void createPrepay_success_savesLog() {
        CreatePrepayCommand command = createWechatCommand();
        when(paymentOrderRepository.save(any(PaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentGatewayPort.createPrepay(any(PaymentOrder.class))).thenReturn(createSuccessPrepayResponse());

        service.createPrepay(command, "TestSystem", "192.168.1.1");

        verify(paymentLogRepository).save(any(PaymentLog.class));
    }

    @Test
    @DisplayName("创建预支付：网关失败时抛异常")
    void createPrepay_gatewayFailure_throwsException() {
        CreatePrepayCommand command = createWechatCommand();
        CreatePrepayResponse failureResponse = new CreatePrepayResponse(
            false, "42010031", "参数非法", null, null, null, 100L, "{}", "{}"
        );
        when(paymentOrderRepository.save(any(PaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentGatewayPort.createPrepay(any(PaymentOrder.class))).thenReturn(failureResponse);

        assertThatThrownBy(() -> service.createPrepay(command, "TestSystem", "192.168.1.1"))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("银行网关调用失败");
    }

    @Test
    @DisplayName("创建预支付：未传expiredSeconds时使用默认值")
    void createPrepay_noExpiredSeconds_usesDefault() {
        CreatePrepayCommand command = new CreatePrepayCommand(
            "BIZ002", 5000L, "测试", null,
            null, "https://example.com/notify", null, null,
            9, 9, "openId123", null
        );
        when(paymentOrderRepository.save(any(PaymentOrder.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentGatewayPort.createPrepay(any(PaymentOrder.class))).thenReturn(createSuccessPrepayResponse());

        PrepayOrderResponse response = service.createPrepay(command, "TestSystem", "10.0.0.1");

        assertThat(response).isNotNull();
        // 验证 gateway 被调用了
        verify(paymentGatewayPort).createPrepay(any(PaymentOrder.class));
    }
}
