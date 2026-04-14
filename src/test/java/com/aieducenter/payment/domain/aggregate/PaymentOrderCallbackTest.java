package com.aieducenter.payment.domain.aggregate;

import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("支付订单回调相关测试")
class PaymentOrderCallbackTest {

    private PaymentOrder createPendingOrder() {
        return new PaymentOrder(
            "ORDER001", "TestSystem", "课程购买",
            10000L, "Python课程", "Python编程课程",
            "https://example.com/notify", null, null
        );
    }

    @Test
    @DisplayName("支付成功时应记录实际支付金额")
    void given_pending_order_when_markAsPaid_then_actualAmount_recorded() {
        PaymentOrder order = createPendingOrder();

        order.markAsPaid("ICBC_ORDER_123", "THIRD_456", PaymentChannel.ICBC, 9800L);

        assertThat(order.getStatus()).isEqualTo(PaymentStatus.PAID);
        assertThat(order.getActualAmount()).isEqualTo(9800L);
        assertThat(order.getBankOrderNo()).isEqualTo("ICBC_ORDER_123");
        assertThat(order.getThirdPartyOrderNo()).isEqualTo("THIRD_456");
        assertThat(order.getPaymentChannel()).isEqualTo(PaymentChannel.ICBC);
        assertThat(order.getPaidAt()).isNotNull();
    }

    @Test
    @DisplayName("标记支付成功时实际支付金额可以为空")
    void given_pending_order_when_markAsPaid_without_actualAmount_then_success() {
        PaymentOrder order = createPendingOrder();

        order.markAsPaid("ICBC_ORDER_123", "THIRD_456", PaymentChannel.ICBC, null);

        assertThat(order.getStatus()).isEqualTo(PaymentStatus.PAID);
        assertThat(order.getActualAmount()).isNull();
    }

    @Test
    @DisplayName("已支付的订单不能再次标记为已支付")
    void given_paid_order_when_markAsPaid_then_throw() {
        PaymentOrder order = createPendingOrder();
        order.markAsPaid("ICBC_1", null, PaymentChannel.ICBC, 10000L);

        assertThatThrownBy(() ->
            order.markAsPaid("ICBC_2", null, PaymentChannel.ICBC, 5000L))
            .isInstanceOf(com.cartisan.core.exception.DomainException.class)
            .hasMessageContaining("支付订单不是待支付状态");
    }
}
