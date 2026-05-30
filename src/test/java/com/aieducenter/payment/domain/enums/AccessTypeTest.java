package com.aieducenter.payment.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AccessType 枚举测试")
class AccessTypeTest {

    @Test
    @DisplayName("H5接入方式")
    void given_h5_then_code4() {
        assertThat(AccessType.H5.getCode()).isEqualTo(4);
        assertThat(AccessType.H5.getName()).isEqualTo("H5");
    }

    @Test
    @DisplayName("APP接入方式")
    void given_app_then_code5() {
        assertThat(AccessType.APP.getCode()).isEqualTo(5);
        assertThat(AccessType.APP.getName()).isEqualTo("APP");
    }

    @Test
    @DisplayName("微信公众号接入方式")
    void given_wechatOa_then_code7() {
        assertThat(AccessType.WECHAT_OA.getCode()).isEqualTo(7);
        assertThat(AccessType.WECHAT_OA.getName()).isEqualTo("微信公众号");
    }

    @Test
    @DisplayName("支付宝生活号接入方式")
    void given_alipayLife_then_code8() {
        assertThat(AccessType.ALIPAY_LIFE.getCode()).isEqualTo(8);
        assertThat(AccessType.ALIPAY_LIFE.getName()).isEqualTo("支付宝生活号");
    }

    @Test
    @DisplayName("小程序接入方式")
    void given_miniProgram_then_code9() {
        assertThat(AccessType.MINI_PROGRAM.getCode()).isEqualTo(9);
        assertThat(AccessType.MINI_PROGRAM.getName()).isEqualTo("小程序");
    }
}
