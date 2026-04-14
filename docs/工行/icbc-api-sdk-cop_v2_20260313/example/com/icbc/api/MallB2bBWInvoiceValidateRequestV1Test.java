package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcResponse;
import java.util.logging.Logger;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

public class MallB2bBWInvoiceValidateRequestV1Test {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCaPvnrn66dx0qy68v7uGAYN2is3RHaTO0tdNHqwElmz49yx4Ectuh/DW5vStMPA4h0cPZkQR5858k8g6TdRP58HQ6qIn1PkyMbYyb2qWw6iqMVBBpZ76OUStmzptNPaF++iO4HkN7q5H6YpBsaIVU1rNgZIp416UxiN33A6XZ8QEtauCaBbTYrKwwcWTldm4c4uu4JVlHlNzV0yhqMq90FFKZo0yF2vIgulc79i+U/aRlyl1HyEZOdHFHt+jAJwvGqrU2KXsuD10V5c7p6//kSgHX6S245HSkNGoOCPxRfLco2KHQV0TtE0MOTvl2iv4gc2MxQVpex+mOh2H4fQiRlAgMBAAECggEAWwupQaezznYAZbRBZ4fhUOoD42VqUEzwAtDdx49QxFrn9XxFfcmJWtV2xkKIamhPd0vmZC777AZ1Bj8+ZU2AWYpdSZeqF/pl0pioOxiuRa/nq1QMqeGF0NaNwjlKqaiWO0VgGx8sNcJB55+WT8o1HcW0mMGSWYgoCiFvHRuxUI1IVpNu2PILUWWHzqYV733PmEIbRWmr1q5FvUZrK+eSnNojbFNG1vyP3ZxcDgFY/TV4bdJV+uH7RIzoIK5PnpKGIrIDXafgkhb1iDa3LgW6tA7XOjI8ASo/KHroVD5W2NjTY+9eVfqBr5RxA8zQAbUj1yaLKJy733jxCCL6t/rpAQKBgQDG0ODRLhADmwYhj49osFB4uRox3wtmdY9icP1t0UZNAKgXZArj15tpyJ0SAlbVnRE9ReKON6AYKn71WQ3VbTLLlPqsx5CDEw/hslNY8hzXFDcQTdWGcsOenYD+/KxnYhS5BG/mo6yeOwgKG9O7ldVxBRqm/DrC0fku7FmII2HfsQKBgQDGnFiHP0sQLlroq1mFkY2p169t8mgHhPolXmiISu4ivhzGCQ13tdGiXilGWXzAOyS1G7hQHzEXPRbikR8tE2c4V3NSiqqBjTz52MfrVlkpZLE/SLVk/HD96Q+ZVWDWSeRjdfHSDKytENZ1fVS6yLfCQGmpc0rJpmYOgRwnEVwQ9QKBgD9ijKpkAxsDK5s7h78apy015lIMCDhsNoBuAX4ngEBAe1apwIcPUC8TlHAPatvDraFZTHeG6HWL5toVwEFRBSa+ooS/QyVmyGNuVQBAgE1Z1oxOJEsu/4rsMBobOGPlczHhZkOj3v67Vz7RzFk71caol+yw8ZL3GniJRaoPjb6hAoGALJNolypQeZg91RyoIf9SET2UZRPT32QsFHkE8xoGKowKvzPG7whCvbNuhhiNlENx8KgBDRKDHRkb6svxeFdzgkS2YF8494ywk87gx+QAbPbphS+XbsvtvD8zvhFYg5/f/6a4GJG5q7ost//iJW3pOFLmpB55OZr8KuM1Y1tVE4UCgYA51iUwPvdy7cB89xQFQJppcJ3h4KZElDXejQlQFjhvr6eq0NnJLS+j1iQl4scGDmZEgutO01ppM/8hIefBmkr8k/Own5t0R414wsrpO6ZWQAc9yX83HiQGRo7KYD//bZZENiYweVAWA26/97ZShe6QRJC8HY52MisAcz4llp7Mkw==";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static final String APP_ID = "9*****************3";

    public static void test(){
        Logger log = Logger.getLogger(MallB2bBWInvoiceValidateRequestV1Test.class.getName());
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
                APIGW_PUBLIC_KEY, "AES", "9BPwvvA+55wGeqfKGAE3Ew==", "", "");
        MallB2bBWInvoiceValidateRequestV1 request = new MallB2bBWInvoiceValidateRequestV1();
        request.setServiceUrl("http://*.*.*.*:*/api/BWInvoiceValidateApi/invoke/V1");
        MallB2bBWInvoiceValidateRequestV1.InvoiceValidateRequestV1 biz = new MallB2bBWInvoiceValidateRequestV1.InvoiceValidateRequestV1();
        MallB2bBWInvoiceValidateRequestV1.InvoiceValidateRequestV1.Invoice invoice = new MallB2bBWInvoiceValidateRequestV1.InvoiceValidateRequestV1.Invoice();
        invoice.setTaxNo("***********");
        invoice.setPlatformId("1******5");
        invoice.setBuyerId("0******7");
        invoice.setSellerId("0******2");
        invoice.setInvoiceNumber("5******5");
        invoice.setInvoiceCode("6******6");
        invoice.setBillingDate("2020-06-18");
        invoice.setCheckCode("7******7");
        invoice.setTotalAmount("1****0");
        invoice.setCurrencyAmount("*");
        invoice.setCurrencyType("*");

        MallB2bBWInvoiceValidateRequestV1.InvoiceValidateRequestV1.Order order = new MallB2bBWInvoiceValidateRequestV1.InvoiceValidateRequestV1.Order();
        order.setOrderId("111");
        order.setPickId("222");
        order.setBuyerFullName("companyName");
        order.setReceiptAmount("200");
        order.setReceiptCurrency("001");
        order.setPlaceTime("2020-06-18");
        order.setSailTime("2020-05-05");
        order.setOriginPort("qingdao");
        order.setTransferPort("beijing");
        order.setDestinationPort("dalian");
        order.setFreighter("yuantong");
        order.setLaneCode("333");
        order.setLaneName("yuantong");
        biz.setInvoice(invoice);
        biz.setOrder(order);
        request.setBizContent(biz);

        IcbcResponse response;
        try {
            log.info("Request: " + JSONObject.toJSONString(biz));
            response = client.execute(request, System.currentTimeMillis() + "");
            log.info("Response: " + JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                log.info("execute success");
            } else {
                log.info("execute failue");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        test();
    }
}
