package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseBillSigcmtbillRequestV1;
import com.icbc.api.request.MybankEnterpriseBillSigcmtbillRequestV1.MybankEnterpriseBillSigcmtbillRequestBizV1;
import com.icbc.api.response.MybankEnterpriseBillSigcmtbillResponseV1;

public class MybankEnterpriseBillSigcmtbillTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/bill/sigcmtbill/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {
            MybankEnterpriseBillSigcmtbillRequestBizV1 bizContent =
                    new MybankEnterpriseBillSigcmtbillRequestBizV1();
            MybankEnterpriseBillSigcmtbillRequestV1 request = new MybankEnterpriseBillSigcmtbillRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("SIGCMTBILL");
            bizContent.setTranDate("20190101");
            bizContent.setTranTime("103231001");
            bizContent.setLanguage("zh_CN");
            bizContent.setfSeqNo("SIGCMTBILL0724320864");
            bizContent.setSignOper("SU00");
            bizContent.setBusinessFlag("0");
            bizContent.setSignAcctNo("0200003309004709145");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseBillSigcmtbillResponseV1 response = client.execute(request);
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
