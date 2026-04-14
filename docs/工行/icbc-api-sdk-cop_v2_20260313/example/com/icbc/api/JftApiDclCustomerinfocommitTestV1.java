package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiDclCustomerinfocommitRequestV1;
import com.icbc.api.response.JftApiDclCustomerinfocommitResponseV1;

import java.util.HashMap;
import java.util.Map;

public class JftApiDclCustomerinfocommitTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAPaPye3IZxbwUR6w9PrRP8ZBwBBOD/8PLv4PSxDTOVWHF7zAeqJ7L/Lvg/BTZTz9wnPNmmTpJTqGkjYBBNJbmADW9Vou83n3zsc56n37mHqYx98nlLaYk2Jv6VM45WhB2uLBImB2obHeNz";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000004095545";
    protected static final String encryptKey = "p001";
    private static final String API_URL = "/api/jft/api/dcl/customerinfocommit/V1";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"",
                "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

        JftApiDclCustomerinfocommitRequestV1 request = new JftApiDclCustomerinfocommitRequestV1();
        JftApiDclCustomerinfocommitRequestV1.JftApiDclCustomerinfocommitRequestV1Biz biz = new JftApiDclCustomerinfocommitRequestV1.JftApiDclCustomerinfocommitRequestV1Biz();
        Map<String, Object> params = new HashMap<>();
        request.setServiceUrl(API_URL);
        biz.setParams(params);
        request.setBizContent(biz);

        JftApiDclCustomerinfocommitResponseV1 response = null;
        try {
            response = client.execute(request, System.currentTimeMillis()+"");
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            Map<String, Object> return_content = response.getReturn_content();
            System.out.println(return_content);
        } else {
            
        }
    }
}
