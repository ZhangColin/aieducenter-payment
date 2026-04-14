package com.icbc.api.example;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MacauOnlinepayPayrequestTest {
    //证书公钥
    protected static final  String MY_PUB_KEY = "";
    //证书私钥
    protected static final String MY_PRIVATE_KEY ="";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "";

    protected static final String APP_ID = "appid";

    public static void test_cop() {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY, MY_PUB_KEY);
        MacauOnlinepayPayrequestRequestV1 request = new MacauOnlinepayPayrequestRequestV1();
        request.setServiceUrl("http://ip:port/api/macau/onlinepay/payrequest/V1");
        MacauOnlinepayPayrequestRequestV1.MacauOnlinepayPayrequestRequestV1Biz biz_content = new MacauOnlinepayPayrequestRequestV1.MacauOnlinepayPayrequestRequestV1Biz();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = sdf.format(new Date());
        biz_content.setTransType("00");
        biz_content.setReqTime(now);
        biz_content.setChannelSerialNo(now);
        biz_content.setMerchantId("1234567");
        request.setBizContent(biz_content);
        MacauOnlinepayPayrequestResponseV1 response;
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
