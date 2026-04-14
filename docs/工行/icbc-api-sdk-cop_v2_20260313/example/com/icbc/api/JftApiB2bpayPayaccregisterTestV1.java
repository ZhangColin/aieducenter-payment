package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiB2bpayPayaccregisterRequestV1;
import com.icbc.api.response.JftApiB2bpayPayaccregisterResponseV1;
import com.icbc.api.response.JftApiB2bpayTransqueryResponseV1;

public class JftApiB2bpayPayaccregisterTestV1 {


    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES,AES_Key, "", "");
        JftApiB2bpayPayaccregisterRequestV1 request=new JftApiB2bpayPayaccregisterRequestV1();
        request.setServiceUrl("https://ip:port/api/jft/api/b2bpay/payaccregister/V1");
        JftApiB2bpayPayaccregisterRequestV1.JftApiB2bpayPayaccregisterRequestV1Biz bizContent=new JftApiB2bpayPayaccregisterRequestV1.JftApiB2bpayPayaccregisterRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutUserId("874");
        bizContent.setOutVendorId("955123");
        bizContent.setRegisterTime("20220630200135");
        bizContent.setPayAccount("62510023339444202");
        bizContent.setBizId("87411");
        bizContent.setDiscernCode("94300001000211");
        bizContent.setTopAccount("98876222554265");
        bizContent.setMcardType("01");
        bizContent.setRegistType("01");
        bizContent.setBizId("01");
        bizContent.setOpenTime("20220630200135");
        bizContent.setEndTime("20220730200135");
        bizContent.setDestoryDate("20220730200135");
        bizContent.setOpenRight("00");
        bizContent.setPayAccount("98876222554265");
        bizContent.setPayAccountName("榔右淏福胯囊住卤化僵侦柱");
        bizContent.setInterestFlag("1");
        bizContent.setDestoryMethod("1");
        bizContent.setAccountCountType("00");

        request.setBizContent(bizContent);
        JftApiB2bpayPayaccregisterResponseV1 response =(JftApiB2bpayPayaccregisterResponseV1) client.execute(request);
        System.out.println(response);
    }
}
