package com.icbc.api;

import com.icbc.api.request.EcddMaintainHisDueInfoRequestV1;
import com.icbc.api.request.EcddMaintainHisDueInfoRequestV1.EcddMaintainHisDueInfoRequestV1Biz;
import com.icbc.api.request.EcddMaintainHisDueInfoRequestV1.PrivateEntity;
import com.icbc.api.response.EcddPublicEntity;
import com.icbc.api.response.EcddMaintainHisDueInfoResponseV1;

public class EcddMaintainHisDueInfoV1Test {

    public static void main(String[] args) {

        try {
            String APP_ID = "[需替换]工行API APPID，请向工行联系获取";
            String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
            String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
            String URL = "http://[需替换]地址/ecdd/queryCheckResult/V1";

            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY,
                    APIGW_PUBLIC_KEY);

            EcddMaintainHisDueInfoRequestV1Biz bizContent = new EcddMaintainHisDueInfoRequestV1Biz();
            PrivateEntity privateEntity = new PrivateEntity();
            // 对应参数自行添加
            privateEntity.seAppNo("[需替换]");
            bizContent.setPrivateEntity(privateEntity);

            EcddPublicEntity publicEntity = new EcddPublicEntity();
            // 对应参数自行添加
            publicEntity.setBrNo("[需替换]机构编号");
            bizContent.setPublicEntity(publicEntity);

            EcddMaintainHisDueInfoRequestV1 request = new EcddMaintainHisDueInfoRequestV1();
            request.setServiceUrl(URL);
            request.setBizContent(bizContent);
            EcddMaintainHisDueInfoResponseV1 response = client.execute(request);

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