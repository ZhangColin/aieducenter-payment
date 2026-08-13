package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PaymentOrderMapper.convert 单元测试（static convert 是列表与详情共用的转换真源）。
 *
 * <p>聚焦枚举出口规范：凡暴露的枚举字段须同时给 code + 中文名，且 null 安全。
 * 覆盖 payMode / accessType / paymentChannel 三组的 null 与非 null 两类用例。</p>
 */
@DisplayName("PaymentOrderMapper.convert 枚举出口映射测试")
class PaymentOrderMapperTest {

    private static PaymentOrder newOrder() {
        return new PaymentOrder(
            "BIZ001", "course-system", "课程购买",
            10000L, "Python 课程", "描述",
            "https://biz.example.com/notify", "attach", 3600L
        );
    }

    @Nested
    @DisplayName("枚举字段已回填（非 null）")
    class EnumFieldsPresent {

        @Test
        @DisplayName("payMode / accessType / paymentChannel 均输出 code + 中文名")
        void given_enumsPresent_when_convert_then_codeAndNameExposed() {
            PaymentOrder order = newOrder();
            order.setPayMode(PayMode.WECHAT);
            order.setAccessType(AccessType.H5);
            order.markAsPaid("ICBC_ORDER_001", "THIRD_001", PaymentChannel.ICBC, 10000L);

            PaymentOrderResponse response = PaymentOrderMapper.convert(order);

            assertThat(response.payMode()).isEqualTo(PayMode.WECHAT.getCode());        // 9
            assertThat(response.payModeName()).isEqualTo("微信");
            assertThat(response.accessType()).isEqualTo(AccessType.H5.getCode());      // 4
            assertThat(response.accessTypeName()).isEqualTo("H5");
            assertThat(response.paymentChannel()).isEqualTo(PaymentChannel.ICBC.getCode()); // 1
            assertThat(response.paymentChannelName()).isEqualTo("工商银行");
        }
    }

    @Nested
    @DisplayName("枚举字段未回填（null）")
    class EnumFieldsAbsent {

        @Test
        @DisplayName("payMode / accessType / paymentChannel 均未回填时，code 与 name 全部输出 null（不抛 NPE）")
        void given_enumsAbsent_when_convert_then_allFieldsNullAndNoNpe() {
            PaymentOrder order = newOrder();   // payMode / accessType / paymentChannel 均 null

            PaymentOrderResponse response = PaymentOrderMapper.convert(order);

            assertThat(response.payMode()).isNull();
            assertThat(response.payModeName()).isNull();
            assertThat(response.accessType()).isNull();
            assertThat(response.accessTypeName()).isNull();
            assertThat(response.paymentChannel()).isNull();
            assertThat(response.paymentChannelName()).isNull();
        }
    }
}
