package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bCrborderpayImporttradepayRequestV1;
import com.icbc.api.request.MallB2bCrborderpayImporttradepayRequestV1.MallB2bCrborderpayImporttradepayRequestV1Biz;
import com.icbc.api.request.MallB2bCrborderpayImporttradepayRequestV1.MallB2bCrborderpayImporttradepayRequestV1Biz.RecvMallInfo;
import com.icbc.api.response.MallB2bCrborderpayImporttradepayResponseV1;

// e链通进口业务支付接口
public class MallB2bCrborderpayImporttradepayTestV1 {

    protected static final String	MY_PRIVATE_KEY		= "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXY3LeSkYCtpk2mmyeLKYA2OrFyp36vW7OOkfU0w6OkAnGnOFY5qn92ig49Psn2sw96yRbdbq2466SM9Vy13RCYLg2WnGDNmx2+QZr1j5gSmCusk76fVvFv5c0/WCdg/9IQt/NPLR3Pg9vyVGTrw1zZwP5MRtizOTTBAMMgfbPOD8JrS2+my1CwX7P9vrBxvQZMLGMHJsMneaAuoG9Kfo7UpEUnIBiKLq/9DbDMQQ1fSS0l0xA64IJLuLG+nwiwzgyWX7AblQtCtdPTHmBCPk4o8Y5hpNAyQ4dtcWk1F90i/YxexyDwbDBnPJ9uP+HFp895Je8XoPy3VqMEQToEdF/AgMBAAECggEAYoeJVYAFvPJXGaC8HN52VIrZkk3jMDRmj7aC1dNf4A2DL08fo5rM/LkmAJwBctkGoS4Pha9EDJB3KBSwUYRE2IbWnhmTU5DyWySMt1bDVixSbKEEoidAuZ2OriBS53HnjoJn4s4lro4nwdvHSlVebcOYZHE/n7g3Hde4nEVh+09hb6JXXFC3EK/7p80zt0wjp1yv9/iLPgXSvyXRW9qM8ED5JbDpsO4nDvF5ZwXWjQ3iauQjsKjVgYcNg31PNq6tl39I3sivx2AP8IYoYz/J5IukKrc6Yeb9KLyjrhMzeoJr3gzpp5T/VPb2vgeH+z8ytU91inMiS7WqmD+xNq33QQKBgQDSph1K/fq8+ZyckERklNudVdtsIKHtNL5kFvPFZUSF94oUgg6y6IkWO1wtDUIUAxi9B88/l34TBeYqmvCfDe/MN7npAjNbDhh6m6VyPZqgUkCdLCUqPpoI3vEP7N8iLPCXZoVs6nvrOqh0tbsLUplQpDc3KOzQqYhhGGyoqZNfUQKBgQC3+zRUH0Y8TUdtkD+VWB74+FzfmgalNZQZMjVbhkpmPWLCj69IxtHIFwWtKQQdeAvYii8PgHrstkQOSEcpXTItIJXcHpwRCypyZpO1i/ScvflhdHJdWKYsaho7k/peGWTP09mD7UHv3SzEC0taU6GzvUE7ZsZbZCwQf8OPkbIPzwKBgQCROH4HXjMHJtpmQTs1UD5bKH6d+51Gh88d94jVNCxaYz5pRwRVKOShVAkG8hX07/GVGBYz0//Ro4Z7Jtft/1CrLFvZnG/FPWF43Wbg7EF2d14ePPneNsxAg1oTTYzC5dJzvfUMEled4pGR7Ftnwej6ZEvAllE0xTvFHG6ElGuKEQKBgDuIBzipHfrpLpGfOVPDvdHl23dmyXuvyIoWmvlQjMZkWo6GG7u6yaqq+GU9fl1iuMKKhAvKEPyDRaYxcQVLaK79ekBNj0ZcjJGT63u1G6OUPRtxqIfX5X09n9tjWIIWSW6OKDXuEYXh1VhiJzHFGe+k73SfHtP3p+J21PqhtDI1AoGBAMQZUXTDb6wSA76b2Lc5/pqYYvoO1GQYJvWm8o4ZTlno7iBdFTAOEz7ekKFmyuW/NiZq58hOA5PQevRmXKggLix5qSlvjLJc8y93gC6kqW/Znuaf2fYHR7fl7jR1neltETtEWvrW0Ei7YugeBb4sim1lagbPLI87Ab8hwSJahwHh";
    protected static final String	APIGW_PUBLIC_KEY	= "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static final String	APP_ID				= "10000000000000085002";
    protected static final String	ENCRYPT_KEY			= "mQbJILokBccRHUkS+XBk7A==";

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8","json", APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
        MallB2bCrborderpayImporttradepayRequestV1 request = new MallB2bCrborderpayImporttradepayRequestV1();
//        request.setServiceUrl("http://gw.dccnet.com.cn:8085/api/mall/b2b/crborderpay/importtradepay/V1");
        request.setServiceUrl("http://ip:port/api/mall/b2b/crborderpay/importtradepay/V1");
        MallB2bCrborderpayImporttradepayRequestV1Biz b2BPayBiz = new MallB2bCrborderpayImporttradepayRequestV1Biz();
        //2.设置参数
        b2BPayBiz.setOutVendorId("10017659");
        b2BPayBiz.setSumPayamt("1765234");
        b2BPayBiz.setPayId("768428");
        b2BPayBiz.setReturnUrl("http://www.baidu.com");
        b2BPayBiz.setAgreeCode("123");
        b2BPayBiz.setPayMethod("1");
        b2BPayBiz.setPayMemno("会员号");
        b2BPayBiz.setPayName("付款账户户名11111");
        b2BPayBiz.setPayAccno("付款账户222");
        b2BPayBiz.setBuyerVendorId("001");
        b2BPayBiz.setOrderId("10017659");
        b2BPayBiz.setOrderRemark("这是订单备注"); 
		b2BPayBiz.setEstimatedTime("201903311530"); 
        List<RecvMallInfo> recvList = new ArrayList<RecvMallInfo>();
        RecvMallInfo recvMallInfo = new RecvMallInfo();
        recvMallInfo.setPayeeSysflag("3");//境外
        recvMallInfo.setPayeeAcc("123465");
        recvMallInfo.setPayAmount("200");
        recvMallInfo.setPayeeBankCountry("USA");
        recvMallInfo.setRbankname("美国洛杉矶分行");
        recvMallInfo.setPayeeCountry("USA");
        recvMallInfo.setPayeeAddress("张三美国洛杉矶某街道");
        recvMallInfo.setMallCode("11134");
        recvMallInfo.setPayeeAccName("户名");
        recvList.add(recvMallInfo);
        b2BPayBiz.setRecvList(recvList);
        request.setBizContent(b2BPayBiz);

        MallB2bCrborderpayImporttradepayResponseV1 response;
        try {
            System.out.println("Request: " + JSONObject.toJSONString(b2BPayBiz));
            response = client.execute(request, System.currentTimeMillis() + "");
            System.out.println("Response: " + JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                System.out.println("execute success");
            } else {
                System.out.println("execute failue");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }


}
