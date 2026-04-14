package com.icbc.api.example;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

public class MacauAggregateOrderrequestTest {
    //证书公钥
    protected static final  String MY_PUB_KEY = "";
    //证书私钥
    protected static final String MY_PRIVATE_KEY ="";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "";

    protected static final String APP_ID = "Oikeclo001";


    public static void test_cop() {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY, MY_PUB_KEY);
        MacauAggregateOrderrequestRequestV1 request = new MacauAggregateOrderrequestRequestV1();
        request.setServiceUrl("http://ip:port/api/cardbussiness/macau/aggregate/orderrequest/V1");
        MacauAggregateOrderrequestRequestV1.MacauAggregateOrderrequestRequestV1Biz bizContent = new MacauAggregateOrderrequestRequestV1.MacauAggregateOrderrequestRequestV1Biz();
        bizContent.setPath("query");
        bizContent.setRequest(new JSONObject() {{
            put("merchantId1", "1234567");
            put("icbcOrderId", "001AL20190725142821522920112");
        }});
        request.setBizContent(bizContent);

        MacauAggregateOrderrequestResponseV1 response;
        try {
            response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println(response.getReturnMsg());
            } else {
                System.out.println("error");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test_cop();
    }
}
