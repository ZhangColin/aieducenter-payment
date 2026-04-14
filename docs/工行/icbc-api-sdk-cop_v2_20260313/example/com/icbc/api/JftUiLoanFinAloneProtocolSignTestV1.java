package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.JftUiLoanFinAloneProtocolSignRequestV1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//聚富通经营快贷融资独立协议签订服务（页面）


public class JftUiLoanFinAloneProtocolSignTestV1 {


    public static void main(String[] args) {
        String APP_ID = "[需替换]APPID";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        String buildPostForm = "";
        // 设置请求路径

        JftUiLoanFinAloneProtocolSignRequestV1 request = new JftUiLoanFinAloneProtocolSignRequestV1();
        request.setServiceUrl("[需替换]网关地址/ui/jft/ui/loan/finaloneprotocolsign/V1");

        // 设置请求对象request
        JftUiLoanFinAloneProtocolSignRequestV1.JftUiLoanFinAloneProtocolSignRequestV1Biz bizContent = new JftUiLoanFinAloneProtocolSignRequestV1.JftUiLoanFinAloneProtocolSignRequestV1Biz();
        Map<String, String> paramMap = new HashMap();
        paramMap.put("outUserId", "37858562345435");
        paramMap.put("outVendorId", "1111");
        paramMap.put("vendorType", "1");
        paramMap.put("platFormType", "1");
        paramMap.put("noticeUrl", "XXXXXXXXX");
        paramMap.put("signBackUrl", "XXXXXXXXX");
        paramMap.put("signFailUrl", "XXXXXXXXX");
        bizContent.setParams(paramMap);
        bizContent.setAppId(APP_ID);
        request.setBizContent(bizContent);

        try {
            buildPostForm = client.buildPostForm(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(buildPostForm);


    }

}
