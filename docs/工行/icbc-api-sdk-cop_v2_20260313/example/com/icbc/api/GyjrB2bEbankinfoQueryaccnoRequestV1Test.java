package com.icbc.api;

import com.alibaba.fastjson.JSON;
import com.icbc.api.request.GyjrB2bEbankInfoQueryAccnoRequestV1;
import com.icbc.api.response.GyjrB2bEbankInfoQueryAccnoResponseV1;
public class GyjrB2bEbankInfoQueryAccnoRequestV1Test {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        try {
            GyjrB2bEbankInfoQueryAccnoRequestV1 request = new GyjrB2bEbankInfoQueryAccnoRequestV1();
            request.setServiceUrl("[需替换]工行API地址"+"/gyjr/b2b/ebankinfo/queryaccno/v1");
            GyjrB2bEbankInfoQueryAccnoRequestV1.GyjrB2bEbankinfoQueryaccnoRequestV1V1Biz bizContent = new GyjrB2bEbankInfoQueryAccnoRequestV1.GyjrB2bEbankinfoQueryaccnoRequestV1V1Biz();
            GyjrB2bEbankInfoQueryAccnoRequestV1.GyjrB2bEbankinfoQueryaccnoRequestV1V1Biz.TransInfo transInfo = new GyjrB2bEbankInfoQueryAccnoRequestV1.GyjrB2bEbankinfoQueryaccnoRequestV1V1Biz.TransInfo();
            transInfo.setSerialNo("12345678");
            transInfo.setAppId(APP_ID);
            transInfo.setIdNo("841588");
            transInfo.setIdType("101");
            transInfo.setProtocolCode("GYJR20230128085638443404143");
            transInfo.setPageIndex("1");
            transInfo.setIsAbroad("1");
            transInfo.setPageSize("100");
            bizContent.setTransInfo(transInfo);
            request.setBizContent(bizContent);
            System.out.println("入参：" + JSON.toJSONString(request));

            GyjrB2bEbankInfoQueryAccnoResponseV1 response  = new GyjrB2bEbankInfoQueryAccnoResponseV1();

            response =  client.execute(request);

            System.out.println("出参：" + JSON.toJSONString(response));
            if (response.getReturnCode() == 0) {
                System.out.println("success");// 成功
            } else {
                System.out.println("error");// 失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}