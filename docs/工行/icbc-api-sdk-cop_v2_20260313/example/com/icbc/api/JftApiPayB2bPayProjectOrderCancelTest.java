package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiPayB2bPayProjectOrderCancelRequestV1;
import com.icbc.api.response.JftApiPayB2bPayProjectOrderCancelResponseV1;

public class JftApiPayB2bPayProjectOrderCancelTest {
    @org.junit.Test
    public void test() throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String icbcPulicKey = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        String host = "[需替换]工行API网关地址";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON,
                icbcPulicKey, IcbcConstants.ENCRYPT_TYPE_AES, AES_Key, null, null);
        JftApiPayB2bPayProjectOrderCancelRequestV1 request = new JftApiPayB2bPayProjectOrderCancelRequestV1();
        request.setServiceUrl("https://gw.open.icbc.com.cn/api/jft/api/b2bpay/travelBatchRefund/V1");
        JftApiPayB2bPayProjectOrderCancelRequestV1.JftApiPayB2bPayProjectOrderCancelRequestV1Biz bizContent = new  JftApiPayB2bPayProjectOrderCancelRequestV1.JftApiPayB2bPayProjectOrderCancelRequestV1Biz();
        bizContent.setAppId("11000000000000009829");
		bizContent.setOutUserId("11222");
		bizContent.setOutVendorId("123");
		bizContent.setDetailBatchId("123456");
		bizContent.setSceneType("09");
		bizContent.setRefundType("25");
	    request.setBizContent(bizContent);
        JftApiPayB2bPayProjectOrderCancelResponseV1 responseV1 = client.execute(request);
        System.out.println(responseV1);
    }
}
