package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufininsCorploanQryLoanAuthorityRequestV1;
import com.icbc.api.request.MybankLoanInclufininsCorploanQryLoanAuthorityRequestV1.MybankLoanInclufininsCorploanQryLoanAuthorityRequestBizV1;
import com.icbc.api.response.MybankLoanInclufininsCorploanQryLoanAuthorityReponseV1;

public class MybankLoanInclufininsCorploanQryLoanAuthorityTest {

    protected static String APP_ID = "xxxxxxx";
    protected static String APIGW_PUBLIC_KEY = "xxxxx";
    protected static String PRI_KEY = "xxx";
    protected static String BASE_URL = "http://domain:port/api"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "mybank/loan/inclusivefinance/corploan/qryLoanAuthority/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            MybankLoanInclufininsCorploanQryLoanAuthorityRequestBizV1 bizContent =
                    new MybankLoanInclufininsCorploanQryLoanAuthorityRequestBizV1();
            MybankLoanInclufininsCorploanQryLoanAuthorityRequestV1 request = new MybankLoanInclufininsCorploanQryLoanAuthorityRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setGroupID("020090001926823");
            bizContent.setLogonID("xyy000.c.0200");
            bizContent.setBusiType("13");
            bizContent.setTransDate("20220510");
            bizContent.setSerialNum("2022051010174411100100001");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankLoanInclufininsCorploanQryLoanAuthorityReponseV1 response = client.execute(request);
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
