package com.icbc.api;

import com.icbc.api.request.JftApiB2bpayRatetrialRequestV1;
import com.icbc.api.request.JftApiB2bpayTransqueryRequestV1;
import com.icbc.api.response.JftApiB2bpayRatetrialResponseV1;
import com.icbc.api.response.JftApiB2bpayTransqueryResponseV1;

public class JftApiB2bpayRatetrialTestV1 {


    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES,AES_Key, "", "");
        JftApiB2bpayRatetrialRequestV1 request=new JftApiB2bpayRatetrialRequestV1();
        request.setServiceUrl("https://ip:port/api/jft/api/b2bpay/ratetrial/V1");
        JftApiB2bpayRatetrialRequestV1.JftApiB2bpayRatetrialRequestV1Biz bizContent=new JftApiB2bpayRatetrialRequestV1.JftApiB2bpayRatetrialRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutVendorId("955123");
        bizContent.setOutUserId("874");
        bizContent.setCardNo("943000010002");
        bizContent.setTrailAmount("10");
        bizContent.setRefundId("100000020");
        bizContent.setRefundAccno("1000020003");
        bizContent.setTrialEndDate("20200302");

        request.setBizContent(bizContent);
        JftApiB2bpayRatetrialResponseV1 response =(JftApiB2bpayRatetrialResponseV1) client.execute(request);
        System.out.println(response);
    }
}
