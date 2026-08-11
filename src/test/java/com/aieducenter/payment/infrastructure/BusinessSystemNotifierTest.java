package com.aieducenter.payment.infrastructure;

import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.enums.NotificationDeliveryResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("业务系统通知测试")
class BusinessSystemNotifierTest {

    private BusinessSystemNotifier notifier;

    @BeforeEach
    void setUp() {
        notifier = new BusinessSystemNotifier();
    }

    @Test
    @DisplayName("构建通知请求体：使用 PaymentOrderResponse")
    void buildRequest_mapsFieldsCorrectly() {
        PaymentOrderResponse response = new PaymentOrderResponse(
            1001L,
            "BIZ001",
            "PAY001",
            "TestSystem",
            "课程购买",
            2,
            "已支付",
            10000L,
            "Python课程",
            "Python编程课程",
            "工商银行",
            "https://qr.example.com/pay",
            "192.168.1.1",
            LocalDateTime.of(2024, 1, 12, 12, 0),
            LocalDateTime.of(2024, 1, 12, 13, 0),
            LocalDateTime.of(2024, 1, 12, 12, 12, 12),
            "ICBC_ORDER_123",
            "THIRD_456"
        );

        assertThat(response.status()).isEqualTo(2);
        assertThat(response.statusName()).isEqualTo("已支付");
        assertThat(response.amount()).isEqualTo(10000L);
        assertThat(response.businessOrderNo()).isEqualTo("BIZ001");
    }

    @Test
    @DisplayName("notifyUrl为空时不发送通知、不抛异常，返回 SKIPPED")
    void notify_withBlankUrl_returnsSkippedAndDoesNotThrow() {
        PaymentOrderResponse response = new PaymentOrderResponse(
            1001L, "BIZ001", "PAY001", "TestSystem", "课程购买",
            2, "已支付", 10000L, "Python课程", "Python编程课程",
            "工商银行", "https://qr.example.com/pay", "192.168.1.1",
            null, null, null, null, null
        );

        assertThatNoException().isThrownBy(() -> notifier.notify(null, response));
        assertThat(notifier.notify(null, response)).isEqualTo(NotificationDeliveryResult.SKIPPED);
        assertThat(notifier.notify("", response)).isEqualTo(NotificationDeliveryResult.SKIPPED);
        assertThat(notifier.notify("   ", response)).isEqualTo(NotificationDeliveryResult.SKIPPED);
    }
}
