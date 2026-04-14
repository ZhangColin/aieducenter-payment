package com.icbc.ndf.jft.b2bpayroll.api.impl;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;

public class JftApiPayrollQueryDetaiTest {
    @org.junit.Test
    public void test() throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String icbcPulicKey = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        String host = "[需替换]工行API网关地址";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON,
                icbcPulicKey, IcbcConstants.ENCRYPT_TYPE_AES, AES_Key, null, null);
        JftApiPayrollQueryDetailRequestV1 request = new JftApiPayrollQueryDetailRequestV1();
        request.setServiceUrl(host+"/api/jft/api/bill/billdiscountinfo/V1");
        JftApiPayrollQueryDetailRequestV1.JftApiPayrollQueryDetailRequestV1Biz bizContent = new  JftApiPayrollQueryDetailRequestV1.JftApiPayrollQueryDetailRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setType("2");
        bizContent.setAppBatserialno("222");

        bizContent.setStartId("1");
        bizContent.setEndId("50");

        request.setBizContent(bizContent);



        JftApiPayrollQueryDetailResponseV1 responseV1 = client.execute(request);
        System.out.println(responseV1);
    }
}
