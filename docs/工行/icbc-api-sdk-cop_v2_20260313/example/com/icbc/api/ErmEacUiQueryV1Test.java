package com.icbc.api;

import com.icbc.api.request.ErmEacUiQueryRequestV1;
import com.icbc.api.request.ErmEacUiQueryRequestV1.ErmEacUiQueryRequestV1Biz;
import com.icbc.api.response.ErmEacUiQueryResponseV1;

public class ErmEacUiQueryV1Test {

    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static final String APP_ID = "10000000000000005452";

    public void test_cop() {
        String SIGN_TYPE= "RSA2";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        ErmEacUiQueryRequestV1 request = new ErmEacUiQueryRequestV1();

        request.setServiceUrl("https://ip:port/api/erm/eac/ui/query/V1");
        ErmEacUiQueryRequestV1Biz bizContent = new ErmEacUiQueryRequestV1Biz();
        bizContent.setAppId("F-XXXX");
        bizContent.setVerifyCode("1");
        bizContent.setOperType("1");
        bizContent.setPage("newsBoard");
        bizContent.setUserId("000011111");
        bizContent.setBranchId("0000011111");
        request.setBizContent(bizContent);

        ErmEacUiQueryResponseV1 response;
        try {
            response = client.execute(request, "msgId");
            if (response.isSuccess() ) {
                // 业务成功处理
                System.out.println("response:" + response);
            } else {
                // 失败
                System.out.println("response:" + response);
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
