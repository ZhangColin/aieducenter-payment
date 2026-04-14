package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;

import com.icbc.api.request.JftApiSmartStorageQueryTradeRequestV1;
import com.icbc.api.response.JftApiSmartStorageQueryTradeResponseV1;


public class JftApiPaySmartStorageQueryTradeTestV1 {


    public void test() throws IcbcApiException {
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String APP_ID = "[需替换]app_id";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,
                MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,
                IcbcConstants.ENCRYPT_TYPE_AES, AES_Key, "", "");
        JftApiSmartStorageQueryTradeRequestV1 request = new JftApiSmartStorageQueryTradeRequestV1();
        request.setServiceUrl("https://[需替换]工行API网关地址/api/jft/api/pay/smartstorage/querytradelist/V1");
        JftApiSmartStorageQueryTradeRequestV1.JftSmartStorageQueryTradeRequestV1Biz bizContent = new
                JftApiSmartStorageQueryTradeRequestV1.JftSmartStorageQueryTradeRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setIp("955123");
        bizContent.setMac("00");
        bizContent.setOutVendorId("14");
        bizContent.setPage("1");
        bizContent.setPageSize("10");
        bizContent.setStartDate("2024-03-21");
        bizContent.setEndDate("2024-03-21");
        request.setBizContent(bizContent);
        JftApiSmartStorageQueryTradeResponseV1 response = client.execute(request);
        System.out.println(response);
    }
}


