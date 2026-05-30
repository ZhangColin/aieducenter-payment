package com.aieducenter.payment.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PayMode 枚举测试")
class PayModeTest {

    @Test
    @DisplayName("给定微信支付方式，验证code和name")
    void given_wechat_then_code9AndNameWechat() {
        assertThat(PayMode.WECHAT.getCode()).isEqualTo(9);
        assertThat(PayMode.WECHAT.getName()).isEqualTo("微信");
    }

    @Test
    @DisplayName("给定支付宝支付方式，验证code和name")
    void given_alipay_then_code10AndNameAlipay() {
        assertThat(PayMode.ALIPAY.getCode()).isEqualTo(10);
        assertThat(PayMode.ALIPAY.getName()).isEqualTo("支付宝");
    }

    @Test
    @DisplayName("给定云闪付支付方式，验证code和name")
    void given_unionpay_then_code13AndNameUnionpay() {
        assertThat(PayMode.UNIONPAY.getCode()).isEqualTo(13);
        assertThat(PayMode.UNIONPAY.getName()).isEqualTo("云闪付");
    }

    @Test
    @DisplayName("根据code查找对应的枚举值")
    void given_code9_then_returnsWechat() {
        assertThat(PayMode.WECHAT.getCode()).isEqualTo(9);
    }
}
