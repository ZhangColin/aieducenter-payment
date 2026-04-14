package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BksAigcFeedbackSaveRequestV1;
import com.icbc.api.request.BksAigcLoginRequestV1;
import com.icbc.api.response.BksAigcFeedbackSaveResponseV1;

public class BksAigcLoginV1Test {
    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY, (String) null, (String) null);
        client.setHeaderParam("X-Request-App","F-BKS"); //调用方应用简称
        BksAigcFeedbackSaveRequestV1 request = new BksAigcFeedbackSaveRequestV1();
        request.setServiceUrl("http://ip:port/api/bks/aigc/login/V1");

        BksAigcLoginRequestV1.BksAigcLoginRequestV1Biz bizContent = new BksAigcLoginRequestV1.BksAigcLoginRequestV1Biz();
        bizContent.setAppScene("123");
        bizContent.setAuthCode("3abcs127893b9e4d245a4e5a32a7b95c9");

        request.setBizContent(bizContent);

        BksAigcFeedbackSaveResponseV1 response;
        try {
            System.out.println("request:"+ JSON.toJSONString(request));
            response = client.execute(request);
            System.out.println("response:" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
