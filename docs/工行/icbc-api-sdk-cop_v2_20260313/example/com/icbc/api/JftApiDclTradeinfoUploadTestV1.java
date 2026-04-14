package com.icbc.api;


import com.icbc.api.request.JftApiDclTradeinfoUploadRequestV1;
import com.icbc.api.response.JftApiDclTradeinfoUploadResponseV1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 赵双元/产品研发一部/网金创研中心/SDC@SDC
 * @date 2022-04-05 15:16
 */
public class JftApiDclTradeinfoUploadTestV1 {
    public static void main(String[] args) {
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String APP_ID = "10000000000000004207";

        String host = "[需替换]工行API网关地址";
        String apiUrl= host+"/api/jft/api/dcl/tradeinfo/upload/V1";
        String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF‐8",
                "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");
        JftApiDclTradeinfoUploadRequestV1 request = new JftApiDclTradeinfoUploadRequestV1();
        request.setServiceUrl(apiUrl);
        JftApiDclTradeinfoUploadRequestV1.JftApiDclTradeinfoUploadRequestV1Biz bizContent =
                new JftApiDclTradeinfoUploadRequestV1.JftApiDclTradeinfoUploadRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setLoanOutVendorId("999900001");
        bizContent.setOperFlag("1");
        Map<String, Object> tradeMap = new HashMap<>();
        tradeMap.put("serialNo", "123");
        tradeMap.put("applyChannel", "01");
        tradeMap.put("evalType", "1");
        tradeMap.put("custName", "主体名称");
        tradeMap.put("custCerType", "3");
        tradeMap.put("custCerId", "221122");
        tradeMap.put("relation", "01");
        bizContent.setTradeBaseInfo(tradeMap);
        request.setBizContent(bizContent);
        JftApiDclTradeinfoUploadResponseV1 response;
        try {
            response = (JftApiDclTradeinfoUploadResponseV1) client.execute(request,
                    System.currentTimeMillis()+"");
            if (response.isSuccess()) {
                //成功
                System.out.println(response.getReturnCode());
            } else {
                //失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
