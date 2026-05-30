package com.aieducenter.payment.domain.aggregate;

import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PaymentOrder 预支付字段测试")
class PaymentOrderPrepayTest {

    @Test
    @DisplayName("给定预支付参数，设置payMode后可以获取")
    void given_order_when_setPayMode_then_canGet() {
        PaymentOrder order = createOrder();
        order.setPayMode(PayMode.WECHAT);
        assertThat(order.getPayMode()).isEqualTo(PayMode.WECHAT);
    }

    @Test
    @DisplayName("给定预支付参数，设置accessType后可以获取")
    void given_order_when_setAccessType_then_canGet() {
        PaymentOrder order = createOrder();
        order.setAccessType(AccessType.MINI_PROGRAM);
        assertThat(order.getAccessType()).isEqualTo(AccessType.MINI_PROGRAM);
    }

    @Test
    @DisplayName("设置shopAppid后可以获取")
    void given_order_when_setShopAppid_then_canGet() {
        PaymentOrder order = createOrder();
        order.setShopAppid("wx1234567890");
        assertThat(order.getShopAppid()).isEqualTo("wx1234567890");
    }

    @Test
    @DisplayName("设置openId后可以获取")
    void given_order_when_setOpenId_then_canGet() {
        PaymentOrder order = createOrder();
        order.setOpenId("oUSDOusdsdISLSDlskdf");
        assertThat(order.getOpenId()).isEqualTo("oUSDOusdsdISLSDlskdf");
    }

    @Test
    @DisplayName("设置prepayDataPackage后可以获取")
    void given_order_when_setPrepayDataPackage_then_canGet() {
        PaymentOrder order = createOrder();
        String dataPackage = "{\"appid\":\"wx123\",\"prepayid\":\"pre123\"}";
        order.setPrepayDataPackage(dataPackage);
        assertThat(order.getPrepayDataPackage()).isEqualTo(dataPackage);
    }

    @Test
    @DisplayName("设置tradeType后可以获取")
    void given_order_when_setTradeType_then_canGet() {
        PaymentOrder order = createOrder();
        order.setTradeType("JSAPI");
        assertThat(order.getTradeType()).isEqualTo("JSAPI");
    }

    @Test
    @DisplayName("新创建的订单预支付字段应为null")
    void given_newOrder_then_prepayFieldsAreNull() {
        PaymentOrder order = createOrder();
        assertThat(order.getPayMode()).isNull();
        assertThat(order.getAccessType()).isNull();
        assertThat(order.getShopAppid()).isNull();
        assertThat(order.getOpenId()).isNull();
        assertThat(order.getPrepayDataPackage()).isNull();
        assertThat(order.getTradeType()).isNull();
    }

    private PaymentOrder createOrder() {
        return new PaymentOrder(
            "BIZ001", "TestSystem", "课程购买",
            10000L, "Python课程", "Python编程课程",
            "https://example.com/notify", null, 3600L
        );
    }
}
