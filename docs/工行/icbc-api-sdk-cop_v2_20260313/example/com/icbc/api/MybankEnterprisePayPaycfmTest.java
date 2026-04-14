package com.icbc.api;

import com.icbc.api.request.MybankEnterprisePayPaycfmRequestV1;
import com.icbc.api.request.MybankEnterprisePayPaycfmRequestV1.MybankEnterprisePayPaycfmRequestBizV1;
import com.icbc.api.response.MybankEnterprisePayPaycfmResponseV1;

public class MybankEnterprisePayPaycfmTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "https://gw.open.icbc.com.cn"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "/mybank/enterprise/pay/paycfm/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {
            MybankEnterprisePayPaycfmRequestBizV1 bizContent =
                    new MybankEnterprisePayPaycfmRequestBizV1();
            MybankEnterprisePayPaycfmRequestV1 request = new MybankEnterprisePayPaycfmRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("PAYCFM");
            bizContent.setTranDate("20251201");
            bizContent.setTranTime("103231001");
            bizContent.setLoginId("43634634");
            bizContent.setGroupId("6543634643643");
            bizContent.setLanguage("zh_CN");
            bizContent.setfSeqno("PAYCFM0077022947");
            bizContent.setAuditSerialNo("KRJ942157455486655");
            bizContent.setAuditFSeqno("PAYENT4556432154561");
            bizContent.setOperationType("1");
            bizContent.setTranType("1");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterprisePayPaycfmResponseV1 response = client.execute(request);
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