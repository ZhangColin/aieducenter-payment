package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftApiLoanQryLoanTradeInstRequestV1;
import com.icbc.api.request.JftUiLoanAuthRequestV1;
import com.icbc.api.response.JftApiLoanQryLoanTradeInstResponseV1;

import java.io.PrintWriter;

/**
 * @author
 */
public class JftUiLoanAuthTestV1 {



    private static final String API_URL = "[需替换]APIP网关地址/ui/jft/ui/loan/auth/V1";


    public static void main(String[] args) {
        String APP_ID = "[需替换]APPID";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        JftUiLoanAuthRequestV1 request = new JftUiLoanAuthRequestV1();
        request.setServiceUrl(API_URL);
        JftUiLoanAuthRequestV1.JftUiLoanAuthRequestV1Biz bizContent = new JftUiLoanAuthRequestV1.JftUiLoanAuthRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutUserId("201802021008999991");
        bizContent.setOutVendorId("201802021008999991");
        bizContent.setSiteType("1");
        bizContent.setUserType("1");
        request.setBizContent(bizContent);
        bizContent.setAppId(APP_ID);
        request.setBizContent(bizContent);
        String buildPostForm = "";
        try {
            buildPostForm = client.buildPostForm(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(buildPostForm);

    }

}
