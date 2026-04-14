package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BasFaceH5FaceprequeryRequestV1;
import com.icbc.api.response.BasFaceH5FaceprequeryResponseV1;


public class BasFaceH5FaceprequeryTestV1 {


	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

	// 2、appid
	protected static final String APP_ID = "1234";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";

	public static void main(String[] args) throws Exception {
		// 初始化客户端（请替换为你的实际参数）
        DefaultIcbcClient client = new DefaultIcbcClient(
                "APP_ID",
                IcbcConstants.SIGN_TYPE_RSA2,
                "MY_PRIVATE_KEY",
                "UTF-8",
                "json",
                "APIGW_PUBLIC_KEY",
                null, null, null, null
        );

        // 创建请求对象
        BasFaceH5FaceprequeryRequestV1 request = new BasFaceH5FaceprequeryRequestV1();
        request.setServiceUrl("http://gw.inner.icbc.com.cn:8081/bas/face/h5face/faceprequery/V1");
        BasFaceH5FaceprequeryRequestV1.BasFaceH5FaceprequeryRequestV1Biz biz = new BasFaceH5FaceprequeryRequestV1.BasFaceH5FaceprequeryRequestV1Biz();
        biz.setAppInfo("生物识别");
        biz.setAppName("F-BAS");
        biz.setAppVersion("1.0");
        biz.setChannel("BAS01");
        biz.setCustomerAgreement("1");
        biz.setProgramName("test");
        biz.setRedirectUrl("https://bas.com.icbc");
        biz.setTrCode("test123");
        biz.setTrxZone(1000);
        biz.setUserId("000982138");
        request.setBizContent(biz);
        // 发送请求
        try {
        	BasFaceH5FaceprequeryResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("Response: " + JSON.toJSONString(response));
            } else {
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("ReturnMsg: " + response.getReturnMsg());
                System.out.println("Response: " + JSON.toJSONString(response));
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
	}
}
