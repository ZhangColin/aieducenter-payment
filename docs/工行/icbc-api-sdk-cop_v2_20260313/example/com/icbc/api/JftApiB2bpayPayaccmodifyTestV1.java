package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiB2bpayPayaccmodifyRequestV1;
import com.icbc.api.response.JftApiB2bpayPayaccmodifyResponseV1;

public class JftApiB2bpayPayaccmodifyTestV1 {


    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES,AES_Key, "", "");
        JftApiB2bpayPayaccmodifyRequestV1 request=new JftApiB2bpayPayaccmodifyRequestV1();
        request.setServiceUrl("https://ip:port/api/jft/api/b2bpay/payaccregister/V1");
        JftApiB2bpayPayaccmodifyRequestV1.JftApiB2bpayPayaccmodifyRequestV1Biz bizContent=new JftApiB2bpayPayaccmodifyRequestV1.JftApiB2bpayPayaccmodifyRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutVendorId("955123");
        bizContent.setOutUserId("874");
        bizContent.setBizId("87411");
        bizContent.setOprType("00");
        bizContent.setOpenTime("20220630200035");
        bizContent.setEndTime("20220730200035");
        bizContent.setDestoryMethod("1");
        bizContent.setRemark("ddhg22134342");

        request.setBizContent(bizContent);
        JftApiB2bpayPayaccmodifyResponseV1 response =(JftApiB2bpayPayaccmodifyResponseV1) client.execute(request);
        System.out.println(response);
    }
}
