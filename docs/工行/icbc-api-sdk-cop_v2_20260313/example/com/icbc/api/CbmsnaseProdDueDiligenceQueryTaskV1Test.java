package com.icbc.api;

import com.icbc.api.request.CbmsnaseProdDueDiligenceQueryTaskRequestV1;
import com.icbc.api.request.CbmsnaseProdDueDiligenceQueryTaskRequestV1.CbmsnaseProdDueDiligenceQueryTaskRequestV1Biz;
import com.icbc.api.response.CbmsnaseProdDueDiligenceCreateTaskResponseV1;

public class CbmsnaseProdDueDiligenceGreateTaskV1Test {

    public static void main(String[] args) {

        try {
            String APP_ID = "[需替换]工行API APPID，请向工行联系获取";
            String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
            String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
            String URL = "http://[需替换]地址/ecdd/queryCheckResult/V1";

            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY,
                    APIGW_PUBLIC_KEY);

            CbmsnaseProdDueDiligenceQueryTaskRequestV1Biz bizContent = new CbmsnaseProdDueDiligenceQueryTaskRequestV1Biz();

            // 对应参数自行添加
            bizContent.setCustno("[需替换]");

            CbmsnaseProdDueDiligenceQueryTaskRequestV1 request = new CbmsnaseProdDueDiligenceQueryTaskRequestV1();
            request.setServiceUrl(URL);
            request.setBizContent(bizContent);
            CbmsnaseProdDueDiligenceQueryTaskResponseV1 response = client.execute(request);

            if (response.isSuccess()) {
                // 业务处理

            } else {
                // 业务处理
            }

        } catch (Exception e) {
            // 异常处理
        }

    }


}