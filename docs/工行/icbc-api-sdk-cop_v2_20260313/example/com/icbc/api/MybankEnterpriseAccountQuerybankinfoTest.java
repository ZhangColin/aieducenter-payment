package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseAccountQuerybankinfoRequestV2;
import com.icbc.api.request.MybankEnterpriseAccountQuerybankinfoRequestV2.MybankEnterpriseAccountQuerybankinfoRequestBizV2;
import com.icbc.api.response.MybankEnterpriseAccountQuerybankinfoResponseV2;

public class MybankEnterpriseAccountQuerybankinfoTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/account/querybankinfo/V2";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {
            MybankEnterpriseAccountQuerybankinfoRequestBizV2 bizContent =
                    new MybankEnterpriseAccountQuerybankinfoRequestBizV2();
            MybankEnterpriseAccountQuerybankinfoRequestV2 request = new MybankEnterpriseAccountQuerybankinfoRequestV2();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("QBKNCNEW");
            bizContent.setTranDate("20190101");
            bizContent.setTranTime("103231001");
            bizContent.setLanguage("zh_CN");
            bizContent.setfSeqNo("QBKNCNEW0100711313");
            bizContent.setCardNo("4580601025885653");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseAccountQuerybankinfoResponseV2 response = client.execute(request);
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
