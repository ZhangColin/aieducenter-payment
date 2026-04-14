package com.icbc.fbase.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.MybankEnterpriseBillBilltodrawRequestV1;
import com.icbc.api.request.MybankEnterpriseBillBilltodrawRequestV1.MybankEnterpriseBillBilltodrawRequestBizV1;
import com.icbc.api.response.MybankEnterpriseBillBilltodrawResponseV1;

public class MybankEnterpriseBillBilltodrawTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/bill/billtodraw/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterpriseBillBilltodrawRequestBizV1 bizContent =
                    new MybankEnterpriseBillBilltodrawRequestBizV1();
            MybankEnterpriseBillBilltodrawRequestV1 request =
                    new MybankEnterpriseBillBilltodrawRequestV1();
            bizContent.setfSeqno("123");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseBillBilltodrawResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println("success");//
            } else {
                System.out.println("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
