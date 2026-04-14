package com.icbc.api;

import com.icbc.api.request.BusinessopElinkMerchantimportUiimportbuyeraddRequestV1;

/**
 * @author wbx
 * @create 2019-12-18 20:32
 */
public class BusinessopElinkMerchantimportUiimportbuyeraddTestV1 {
    protected static final String APP_ID = "10000000000000222505";
    protected static final String MY_PRIVATE_KEY ="-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXY3LeSkYCtpk2mmyeLKYA2OrFyp36vW7OOkfU0w6OkAnGnOFY5qn92ig49Psn2sw96yRbdbq2466SM9Vy13RCYLg2WnGDNmx2+QZr1j5gSmCusk76fVvFv5c0/WCdg/9IQt/NPLR3Pg9vyVGTrw1zZwP5MRtizOTTBAMMgfbPOD8JrS2+my1CwX7P9vrBxvQZMLGMHJsMneaAuoG9Kfo7UpEUnIBiKLq/9DbDMQQ1fSS0l0xA64IJLuLG+nwiwzgyWX7AblQtCtdPTHmBCPk4o8Y5hpNAyQ4dtcWk1F90i/YxexyDwbDBnPJ9uP+HFp895Je8XoPy3VqMEQToEdF/AgMBAAECggEAYoeJVYAFvPJXGaC8HN52VIrZkk3jMDRmj7aC1dNf4A2DL08fo5rM/LkmAJwBctkGoS4Pha9EDJB3KBSwUYRE2IbWnhmTU5DyWySMt1bDVixSbKEEoidAuZ2OriBS53HnjoJn4s4lro4nwdvHSlVebcOYZHE/n7g3Hde4nEVh+09hb6JXXFC3EK/7p80zt0wjp1yv9/iLPgXSvyXRW9qM8ED5JbDpsO4nDvF5ZwXWjQ3iauQjsKjVgYcNg31PNq6tl39I3sivx2AP8IYoYz/J5IukKrc6Yeb9KLyjrhMzeoJr3gzpp5T/VPb2vgeH+z8ytU91inMiS7WqmD+xNq33QQKBgQDSph1K/fq8+ZyckERklNudVdtsIKHtNL5kFvPFZUSF94oUgg6y6IkWO1wtDUIUAxi9B88/l34TBeYqmvCfDe/MN7npAjNbDhh6m6VyPZqgUkCdLCUqPpoI3vEP7N8iLPCXZoVs6nvrOqh0tbsLUplQpDc3KOzQqYhhGGyoqZNfUQKBgQC3+zRUH0Y8TUdtkD+VWB74+FzfmgalNZQZMjVbhkpmPWLCj69IxtHIFwWtKQQdeAvYii8PgHrstkQOSEcpXTItIJXcHpwRCypyZpO1i/ScvflhdHJdWKYsaho7k/peGWTP09mD7UHv3SzEC0taU6GzvUE7ZsZbZCwQf8OPkbIPzwKBgQCROH4HXjMHJtpmQTs1UD5bKH6d+51Gh88d94jVNCxaYz5pRwRVKOShVAkG8hX07/GVGBYz0//Ro4Z7Jtft/1CrLFvZnG/FPWF43Wbg7EF2d14ePPneNsxAg1oTTYzC5dJzvfUMEled4pGR7Ftnwej6ZEvAllE0xTvFHG6ElGuKEQKBgDuIBzipHfrpLpGfOVPDvdHl23dmyXuvyIoWmvlQjMZkWo6GG7u6yaqq+GU9fl1iuMKKhAvKEPyDRaYxcQVLaK79ekBNj0ZcjJGT63u1G6OUPRtxqIfX5X09n9tjWIIWSW6OKDXuEYXh1VhiJzHFGe+k73SfHtP3p+J21PqhtDI1AoGBAMQZUXTDb6wSA76b2Lc5/pqYYvoO1GQYJvWm8o4ZTlno7iBdFTAOEz7ekKFmyuW/NiZq58hOA5PQevRmXKggLix5qSlvjLJc8y93gC6kqW/Znuaf2fYHR7fl7jR1neltETtEWvrW0Ei7YugeBb4sim1lagbPLI87Ab8hwSJahwHh";
    protected static final String SERVICE_URL = "http://ip:port/ui/businessop/elink/merchantimport/uiimportbuyeradd/V1";
    public static void main(String[] args) {
        String buildPostForm = "";

        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        BusinessopElinkMerchantimportUiimportbuyeraddRequestV1 request = new BusinessopElinkMerchantimportUiimportbuyeraddRequestV1();
        request.setServiceUrl(SERVICE_URL);
        BusinessopElinkMerchantimportUiimportbuyeraddRequestV1.BusinessopElinkMerchantimportUiimportbuyeraddRequestV1Biz bizContent = new BusinessopElinkMerchantimportUiimportbuyeraddRequestV1.BusinessopElinkMerchantimportUiimportbuyeraddRequestV1Biz();
        /**
         * 进口采购商建档页面接口-新增：必输参数(parentVendorId、operationType)
         * 进口采购商建档页面接口-回显--修改:必输参数(parentVendorId、vendorId、payId、orderRemark)
         */
        bizContent.setParentVendorId("10002778");// 平台方编号
        bizContent.setOperationType("1");// 1-新增，2-修改
        bizContent.setVendorId("10100127");// 商户编号
        bizContent.setPayId("REG201709050115008");// 付款单号
        bizContent.setOrderRemark("这是订单备注");// 订单备注

        request.setBizContent(bizContent);
        try {
            buildPostForm = client.buildPostForm(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(buildPostForm);
    }

}
