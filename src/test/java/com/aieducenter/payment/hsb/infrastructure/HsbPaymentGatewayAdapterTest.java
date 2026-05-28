package com.aieducenter.payment.hsb.infrastructure;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.port.response.CreateHsbPaymentResponse;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("HsbPaymentGatewayAdapter 测试")
class HsbPaymentGatewayAdapterTest {

    private HsbConfig hsbConfig;
    private HsbHttpClient hsbHttpClient;
    private HsbPaymentGatewayAdapter adapter;

    @BeforeEach
    void setUp() {
        hsbConfig = new HsbConfig();
        hsbConfig.setMktId("41060860811052");
        hsbConfig.setPrivateKey("test-private-key");
        hsbHttpClient = mock(HsbHttpClient.class);
        adapter = new HsbPaymentGatewayAdapter(hsbConfig, hsbHttpClient);
    }

    @Test
    @DisplayName("createPayment 请求中应包含 Clrg_Dt（确认收货日期）")
    void given_orderWithConfirmReceiptDate_when_createPayment_then_requestContainsClrgDt() {
        LocalDate confirmDate = LocalDate.of(2029, 5, 27);
        HsbPaymentOrder order = createTestOrder(confirmDate);
        List<HsbSubOrder> subOrders = List.of(
            new HsbSubOrder("BIZ001", "SUB001", "MERCH001", 10000L, 10000L)
        );

        String mockResponse = """
            {"Svc_Rsp_St":"00","Svc_Rsp_Cd":"SUCCESS","Cshdk_Url":"http://pay.url","Pay_Qr_Code":"QR123","Prim_Ordr_No":"PRIM001"}
            """;

        try (MockedStatic<HsbSignUtil> signUtilMock = mockStatic(HsbSignUtil.class)) {
            signUtilMock.when(() -> HsbSignUtil.sign(anyString(), anyString())).thenReturn("mock-signature");
            when(hsbHttpClient.postJson(anyString(), anyString())).thenReturn(mockResponse);

            CreateHsbPaymentResponse response = adapter.createPayment(order, subOrders);

            ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
            verify(hsbHttpClient).postJson(anyString(), bodyCaptor.capture());

            JSONObject requestJson = JSONObject.parseObject(bodyCaptor.getValue());
            assertThat(requestJson.getString("Clrg_Dt")).isEqualTo("20290527");
            assertThat(response.success()).isTrue();
        }
    }

    @Test
    @DisplayName("createPayment 确认收货日期为空时，Clrg_Dt 应默认当前日期加3年")
    void given_orderWithoutConfirmReceiptDate_when_createPayment_then_clrgDtDefaultsTo3Years() {
        HsbPaymentOrder order = createTestOrder(null);
        List<HsbSubOrder> subOrders = List.of(
            new HsbSubOrder("BIZ001", "SUB001", "MERCH001", 10000L, 10000L)
        );

        String mockResponse = """
            {"Svc_Rsp_St":"00","Svc_Rsp_Cd":"SUCCESS","Cshdk_Url":"http://pay.url","Pay_Qr_Code":"QR123","Prim_Ordr_No":"PRIM001"}
            """;

        try (MockedStatic<HsbSignUtil> signUtilMock = mockStatic(HsbSignUtil.class)) {
            signUtilMock.when(() -> HsbSignUtil.sign(anyString(), anyString())).thenReturn("mock-signature");
            when(hsbHttpClient.postJson(anyString(), anyString())).thenReturn(mockResponse);

            adapter.createPayment(order, subOrders);

            ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
            verify(hsbHttpClient).postJson(anyString(), bodyCaptor.capture());

            JSONObject requestJson = JSONObject.parseObject(bodyCaptor.getValue());
            String expectedClrgDt = LocalDate.now().plusYears(3).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            assertThat(requestJson.getString("Clrg_Dt")).isEqualTo(expectedClrgDt);
        }
    }

    private HsbPaymentOrder createTestOrder(LocalDate confirmReceiptDate) {
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
            null,
            List.of(new HsbSubOrder("BIZ001", "SUB001", "MERCH001", 10000L, 10000L))
        );
    }
}
