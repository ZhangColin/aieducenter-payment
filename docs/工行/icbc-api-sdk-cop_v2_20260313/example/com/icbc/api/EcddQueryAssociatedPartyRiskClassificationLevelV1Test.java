package com.icbc.api;

import com.icbc.api.request.EcddQueryAssociatedPartyRiskClassificationLevelRequestV1;
import com.icbc.api.request.EcddQueryAssociatedPartyRiskClassificationLevelRequestV1.EcddQueryAssociatedPartyRiskClassificationLevelRequestV1Biz;
import com.icbc.api.request.EcddQueryAssociatedPartyRiskClassificationLevelRequestV1.PrivateEntity;
import com.icbc.api.response.EcddPublicEntity;
import com.icbc.api.response.EcddQueryAssociatedPartyRiskClassificationLevelResponseV1;

public class EcddQueryAssociatedPartyRiskClassificationLevelV1Test {

    public static void main(String[] args) {

        try {
            String APP_ID = "[需替换]工行API APPID，请向工行联系获取";
            String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
            String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
            String URL = "http://[需替换]地址/ecdd/queryAssociatedPartyRiskClassificationLevel/V1";

            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY,
                    APIGW_PUBLIC_KEY);

            EcddQueryAssociatedPartyRiskClassificationLevelRequestV1Biz bizContent = new EcddQueryAssociatedPartyRiskClassificationLevelRequestV1Biz();
            PrivateEntity privateEntity = new PrivateEntity();
            // 对应参数自行添加
            privateEntity.setAppNo("[需替换]申请编号");
            bizContent.setPrivateEntity(privateEntity);

            EcddPublicEntity publicEntity = new EcddPublicEntity();
            // 对应参数自行添加
            publicEntity.setBrNo("[需替换]机构编号");
            bizContent.setPublicEntity(publicEntity);

            EcddQueryAssociatedPartyRiskClassificationLevelRequestV1 request = new EcddQueryAssociatedPartyRiskClassificationLevelRequestV1();
            request.setServiceUrl(URL);
            request.setBizContent(bizContent);
            EcddQueryAssociatedPartyRiskClassificationLevelResponseV1 response = client.execute(request);

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
