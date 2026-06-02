package com.aieducenter.payment.hsb.infrastructure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "hsb")
public class HsbConfig {
    private String baseUrl = "http://marketpaypl4.dev.jh:8035/online/direct/";
    private String mktId;
    private String platformMerchantId;
    private String privateKey;
    private String platformPublicKey;
    private String initiatorSystemId = "00000";
    private String initiatorChannelCode = "0000000000000000000000000";
    private String reconciliationStoragePath = "data/reconciliation";
    private Version version = new Version();

    @Data
    public static class Version {
        private String placeOrder = "5";
        private String queryOrder = "5";
        private String refundOrder = "3";
        private String queryRefund = "4";
        private String confirmSettlement = "4";
    }

    public String getPlaceOrderUrl() { return baseUrl + "gatherPlaceorder"; }
    public String getQueryOrderUrl() { return baseUrl + "gatherEnquireOrder"; }
    public String getRefundOrderUrl() { return baseUrl + "refundOrder"; }
    public String getQueryRefundUrl() { return baseUrl + "enquireRefundOrder"; }
    public String getConfirmSettlementUrl() { return baseUrl + "mergeNoticeArrival"; }
}
