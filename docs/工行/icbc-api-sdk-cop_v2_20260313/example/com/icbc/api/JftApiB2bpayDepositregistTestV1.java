package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiB2bpayDepositregistRequestV1;
import com.icbc.api.response.JftApiB2bpayDepositregistResponseV1;

public class JftApiB2bpayDepositregistTestV1 {


    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES,AES_Key, "", "");
        JftApiB2bpayDepositregistRequestV1 request=new JftApiB2bpayDepositregistRequestV1();
        request.setServiceUrl("https://ip:port/api/jft/api/b2bpay/depositregist/V1");
        JftApiB2bpayDepositregistRequestV1.JftApiB2bpayDepositregistRequestV1Biz bizContent=new JftApiB2bpayDepositregistRequestV1.JftApiB2bpayDepositregistRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setProjectId("874");
        bizContent.setAmount("10.00");
        bizContent.setSceneType("00");
        bizContent.setOtacctFlag("0");
        bizContent.setImageFile("xddgwwa4484s5g4");
        bizContent.setImageFile("http://wwww.aaaa.com/image.jpg");
        bizContent.setWtBankNo("774455888");
        bizContent.setWtBankName("774455888");
        bizContent.setWtAcctNum("774455888");
        bizContent.setWtAcctName("774455888");
        bizContent.setRemark("ddhg22134342");

        request.setBizContent(bizContent);
        JftApiB2bpayDepositregistResponseV1 response =(JftApiB2bpayDepositregistResponseV1) client.execute(request);
        System.out.println(response);
    }
}
