package com.aieducenter.payment.hsb.domain.aggregate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("HsbPaymentOrder 聚合根测试")
class HsbPaymentOrderTest {

    private HsbPaymentOrder createOrder(LocalDate confirmReceiptDate, String pageReturnUrl) {
        return new HsbPaymentOrder(
            "BIZ001",
            "TestSystem",
            "测试订单",
            "41060860811052",
            "02",
            "01",
            "156",
            10000L,
            10000L,
            null,
            3600L,
            "https://example.com/notify",
            null,
            confirmReceiptDate,
            pageReturnUrl,
            List.of(new HsbSubOrder("BIZ001", "SUB001", "MERCH001", 10000L, 10000L))
        );
    }

    private HsbPaymentOrder createOrder(LocalDate confirmReceiptDate) {
        return createOrder(confirmReceiptDate, null);
    }

    @Test
    @DisplayName("给定确认收货日期，创建订单时应正确保存")
    void given_confirmReceiptDate_when_createOrder_then_fieldStored() {
        LocalDate date = LocalDate.of(2029, 5, 27);

        HsbPaymentOrder order = createOrder(date);

        assertThat(order.getConfirmReceiptDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("给定确认收货日期为空，创建订单时字段应为空")
    void given_nullConfirmReceiptDate_when_createOrder_then_fieldNull() {
        HsbPaymentOrder order = createOrder(null);

        assertThat(order.getConfirmReceiptDate()).isNull();
    }

    @Test
    @DisplayName("给定确认收货日期，resolveClrgDt 应返回 yyyyMMdd 格式字符串")
    void given_confirmReceiptDate_when_resolveClrgDt_then_formattedDate() {
        LocalDate date = LocalDate.of(2029, 5, 27);

        HsbPaymentOrder order = createOrder(date);

        assertThat(order.resolveClrgDt()).isEqualTo("20290527");
    }

    @Test
    @DisplayName("给定确认收货日期为空，resolveClrgDt 应默认当前日期加3年")
    void given_nullConfirmReceiptDate_when_resolveClrgDt_then_defaultNowPlus3Years() {
        HsbPaymentOrder order = createOrder(null);

        String clrgDt = order.resolveClrgDt();
        String expected = LocalDate.now().plusYears(3).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        assertThat(clrgDt).isEqualTo(expected);
    }

    @Test
    @DisplayName("resolveClrgDt 返回值应为8位数字字符串")
    void given_anyConfirmReceiptDate_when_resolveClrgDt_then_8digits() {
        HsbPaymentOrder withDate = createOrder(LocalDate.of(2030, 1, 15));
        HsbPaymentOrder withoutDate = createOrder(null);

        assertThat(withDate.resolveClrgDt()).matches("\\d{8}");
        assertThat(withoutDate.resolveClrgDt()).matches("\\d{8}");
    }

    @Test
    @DisplayName("给定页面返回URL，创建订单时应正确保存")
    void given_pageReturnUrl_when_createOrder_then_fieldStored() {
        String url = "https://example.com/return";
        HsbPaymentOrder order = createOrder(null, url);
        assertThat(order.getPageReturnUrl()).isEqualTo(url);
    }

    @Test
    @DisplayName("给定页面返回URL为空，创建订单时字段应为空")
    void given_nullPageReturnUrl_when_createOrder_then_fieldNull() {
        HsbPaymentOrder order = createOrder(null, null);
        assertThat(order.getPageReturnUrl()).isNull();
    }

    @Test
    @DisplayName("setPaymentResult 应正确设置 cshdkUrl、payUrl、payQrCode、primOrderNo")
    void given_setPaymentResult_then_allFieldsStored() {
        HsbPaymentOrder order = createOrder(null);
        order.setPaymentResult("http://cashier.url", "http://pay.url", "QR123", "PRIM001");
        assertThat(order.getCshdkUrl()).isEqualTo("http://cashier.url");
        assertThat(order.getPayUrl()).isEqualTo("http://pay.url");
        assertThat(order.getPayQrCode()).isEqualTo("QR123");
        assertThat(order.getPrimOrderNo()).isEqualTo("PRIM001");
    }
}
