package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallNfpInvoiceinfouploadRequestV1;
import com.icbc.api.request.MallNfpInvoiceinfouploadRequestV1.*;
import com.icbc.api.response.MallNfpInvoiceinfouploadResponseV1;

import java.util.ArrayList;
import java.util.List;

// 网络融资发票信息上传数据服务
public class MallNfpInvoiceinfouploadTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXY3LeSkYCtpk2mmyeLKYA2OrFyp36vW7OOkfU0w6OkAnGnOFY5qn92ig49Psn2sw96yRbdbq2466SM9Vy13RCYLg2WnGDNmx2+QZr1j5gSmCusk76fVvFv5c0/WCdg/9IQt/NPLR3Pg9vyVGTrw1zZwP5MRtizOTTBAMMgfbPOD8JrS2+my1CwX7P9vrBxvQZMLGMHJsMneaAuoG9Kfo7UpEUnIBiKLq/9DbDMQQ1fSS0l0xA64IJLuLG+nwiwzgyWX7AblQtCtdPTHmBCPk4o8Y5hpNAyQ4dtcWk1F90i/YxexyDwbDBnPJ9uP+HFp895Je8XoPy3VqMEQToEdF/AgMBAAECggEAYoeJVYAFvPJXGaC8HN52VIrZkk3jMDRmj7aC1dNf4A2DL08fo5rM/LkmAJwBctkGoS4Pha9EDJB3KBSwUYRE2IbWnhmTU5DyWySMt1bDVixSbKEEoidAuZ2OriBS53HnjoJn4s4lro4nwdvHSlVebcOYZHE/n7g3Hde4nEVh+09hb6JXXFC3EK/7p80zt0wjp1yv9/iLPgXSvyXRW9qM8ED5JbDpsO4nDvF5ZwXWjQ3iauQjsKjVgYcNg31PNq6tl39I3sivx2AP8IYoYz/J5IukKrc6Yeb9KLyjrhMzeoJr3gzpp5T/VPb2vgeH+z8ytU91inMiS7WqmD+xNq33QQKBgQDSph1K/fq8+ZyckERklNudVdtsIKHtNL5kFvPFZUSF94oUgg6y6IkWO1wtDUIUAxi9B88/l34TBeYqmvCfDe/MN7npAjNbDhh6m6VyPZqgUkCdLCUqPpoI3vEP7N8iLPCXZoVs6nvrOqh0tbsLUplQpDc3KOzQqYhhGGyoqZNfUQKBgQC3+zRUH0Y8TUdtkD+VWB74+FzfmgalNZQZMjVbhkpmPWLCj69IxtHIFwWtKQQdeAvYii8PgHrstkQOSEcpXTItIJXcHpwRCypyZpO1i/ScvflhdHJdWKYsaho7k/peGWTP09mD7UHv3SzEC0taU6GzvUE7ZsZbZCwQf8OPkbIPzwKBgQCROH4HXjMHJtpmQTs1UD5bKH6d+51Gh88d94jVNCxaYz5pRwRVKOShVAkG8hX07/GVGBYz0//Ro4Z7Jtft/1CrLFvZnG/FPWF43Wbg7EF2d14ePPneNsxAg1oTTYzC5dJzvfUMEled4pGR7Ftnwej6ZEvAllE0xTvFHG6ElGuKEQKBgDuIBzipHfrpLpGfOVPDvdHl23dmyXuvyIoWmvlQjMZkWo6GG7u6yaqq+GU9fl1iuMKKhAvKEPyDRaYxcQVLaK79ekBNj0ZcjJGT63u1G6OUPRtxqIfX5X09n9tjWIIWSW6OKDXuEYXh1VhiJzHFGe+k73SfHtP3p+J21PqhtDI1AoGBAMQZUXTDb6wSA76b2Lc5/pqYYvoO1GQYJvWm8o4ZTlno7iBdFTAOEz7ekKFmyuW/NiZq58hOA5PQevRmXKggLix5qSlvjLJc8y93gC6kqW/Znuaf2fYHR7fl7jR1neltETtEWvrW0Ei7YugeBb4sim1lagbPLI87Ab8hwSJahwHh";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static final String APP_ID = "10000000000000114513";
    protected static final String ENCRYPT_KEY = "mQbJILokBccRHUkS+XBk7A==";

    public static void main(String[] args) {
        upload();
    }

    private static void upload() {

        String serviceUrl = "http://ip:port/api/mall/nfp/invoiceinfoupload/V1";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8",
                "json", APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");

        MallNfpInvoiceinfouploadRequestV1Biz bizContent = new MallNfpInvoiceinfouploadRequestV1Biz();
        bizContent.setAppid("020090000018907");
        bizContent.setOutVendorId("020090000018907");
        bizContent.setOutCoreVendorId("020090000018907");
        bizContent.setInvoiceInfoList(generatorTestInvoiceInfo());

        MallNfpInvoiceinfouploadRequestV1 request = new MallNfpInvoiceinfouploadRequestV1();
        request.setServiceUrl(serviceUrl);
        request.setBizContent(bizContent);

        MallNfpInvoiceinfouploadResponseV1 response;
        try {
            System.out.println("请求参数:" + JSON.toJSONString(bizContent));
            response = client.execute(request, System.currentTimeMillis() + "");
            System.out.println("请求参数:" + JSON.toJSONString(response));
            if (response.isSuccess()) {
                System.out.println("业务处理成功！");
            } else {
                System.out.println("业务处理失败！");
            }
            System.out.println(response.getReturnCode() + "  " + response.getReturnMsg());
            System.out.println(JSONObject.toJSONString(response.getResdata()));
        } catch (IcbcApiException e) {
            System.out.println("业务处理失败！" + e);
        }

    }

    private static List<InvoiceInfo> generatorTestInvoiceInfo() {

        List<InvoiceCompany> invoiceCompanyList = new ArrayList<InvoiceCompany>();
        InvoiceCompany invoiceCompany = new InvoiceCompany();
        invoiceCompany.setAddress("北京市海淀区");
        invoiceCompany.setTaxpayerRegistrationNumber("00000001");
        invoiceCompany.setCompanyType("0");
        invoiceCompany.setCompanyName("北京中北博健科贸有限公司");
        invoiceCompany.setPhone("12345678901");
        invoiceCompany.setBankName("ICBC");
        invoiceCompany.setBankAccount("0000000000001");
        invoiceCompanyList.add(invoiceCompany);

        List<InvoiceProduct> invoiceProductList = new ArrayList<InvoiceProduct>();
        InvoiceProduct invoiceProduct = new InvoiceProduct();
        invoiceProduct.setProductName("弹力绷带10CM*450CM");
        invoiceProduct.setProductNumber("600");
        invoiceProduct.setSizeOrModel("10CM*450CM");
        invoiceProduct.setUnitPrice("4.80");
        invoiceProduct.setUnit("卷");
        invoiceProduct.setAmout("2880.00");
        invoiceProduct.setTaxRate("10");
        invoiceProduct.setTaxAmount("288.00");
        invoiceProduct.setProducerCompany("北京中北博健科贸有限公司");
        invoiceProduct.setOutsideOrderNo("2018052511209");
        invoiceProduct.setInsideOrderNo("4500420761");

        List<InvoiceInfo> invoiceInfoList = new ArrayList<InvoiceInfo>();
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setInvoiceCode("1100162350");
        invoiceInfo.setInvoiceNo("09171176");
        invoiceInfo.setInvoiceAmount("91030.00");
        invoiceInfo.setInvoiceDate("2019-03-20");
        invoiceInfo.setInvoiceRemark("invoice upload test");
        invoiceInfo.setInvoiceKey("180608086333");
        invoiceInfo.setInvoiceCompanyList(invoiceCompanyList);
        invoiceInfo.setInvoiceProductList(invoiceProductList);
        invoiceInfoList.add(invoiceInfo);

        return invoiceInfoList;
    }

}
