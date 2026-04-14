package com.icbc.api;

import com.icbc.api.request.MybankCardbusinessEnterpriseFeepayMcefpayRequestV1;
import com.icbc.api.request.MybankCardbusinessEnterpriseFeepayMcefpayRequestV1.MybankCardbusinessEnterpriseFeepayMcefpayRequestBizV1;
import com.icbc.api.response.MybankCardbusinessEnterpriseFeepayMcefpayResponseV1;

public class MybankCardbusinessEnterpriseFeepayMcefpayTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/cardbusiness/feepay/pay/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {
            MybankCardbusinessEnterpriseFeepayMcefpayRequestBizV1 bizContent =
                    new MybankCardbusinessEnterpriseFeepayMcefpayRequestBizV1();
            MybankCardbusinessEnterpriseFeepayMcefpayRequestV1 request = new MybankCardbusinessEnterpriseFeepayMcefpayRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("PAY");
            bizContent.setTranDate("20190101");
            bizContent.setTranTime("082301000");
            bizContent.setfSeqno("123");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankCardbusinessEnterpriseFeepayMcefpayResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");
            } else {
                // 失败
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
