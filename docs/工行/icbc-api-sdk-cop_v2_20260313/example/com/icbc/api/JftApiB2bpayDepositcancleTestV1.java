package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiB2bpayDepositcancleRequestV1;
import com.icbc.api.response.JftApiB2bpayDepositcancleResponseV1;

public class JftApiB2bpayDepositcancleTestV1 {


    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES,AES_Key, "", "");
        JftApiB2bpayDepositcancleRequestV1 request=new JftApiB2bpayDepositcancleRequestV1();
        request.setServiceUrl("https://ip:port/api/jft/api/b2bpay/depositcancle/V1");
        JftApiB2bpayDepositcancleRequestV1.JftApiB2bpayDepositcancleRequestV1Biz bizContent=new JftApiB2bpayDepositcancleRequestV1.JftApiB2bpayDepositcancleRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutUserId("874");
        bizContent.setRemark("ddhg22134342");
        bizContent.setCancelDate("20240411");
        bizContent.setCancelType("01");

        request.setBizContent(bizContent);
        JftApiB2bpayDepositcancleResponseV1 response =(JftApiB2bpayDepositcancleResponseV1) client.execute(request);
        System.out.println(response);
    }
}
