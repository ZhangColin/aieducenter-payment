package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiB2bpayTransqueryRequestV1;
import com.icbc.api.response.JftApiB2bpayTransqueryResponseV1;

public class JftApiB2bpayTransqueryTestV1 {


    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES,AES_Key, "", "");
        JftApiB2bpayTransqueryRequestV1 request=new JftApiB2bpayTransqueryRequestV1();
        request.setServiceUrl("https://ip:port/api/jft/api/b2bpay/transquery/V1");
        JftApiB2bpayTransqueryRequestV1.JftApiB2bpayTransqueryRequestV1Biz bizContent=new JftApiB2bpayTransqueryRequestV1.JftApiB2bpayTransqueryRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutVendorId("955123");
        bizContent.setOutUserId("874");
        bizContent.setCardNo("94300001000211");
        bizContent.setPayAccount("62510023339444202");
        bizContent.setStartTrxDate("20220308");
        bizContent.setEndTrxDate("20220308");
        bizContent.setStartId("1");
        bizContent.setEndId("50");

        request.setBizContent(bizContent);
        JftApiB2bpayTransqueryResponseV1 response =(JftApiB2bpayTransqueryResponseV1) client.execute(request);
        System.out.println(response);
    }
}
