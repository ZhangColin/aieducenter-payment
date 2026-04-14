package com.icbc.api;

import com.icbc.api.request.AtpEpccDealStateQryRequestV1;
import com.icbc.api.response.AtpEpccDealStateQryResponseV1;

/**
 * @author kfzx-shiwb1
 * @date 2024-11-25 09:34:30
 * @description 2412C IDealStateQryServInf-银行主动发起交易状态查询-测试
 */
public class AtpEpccDealStateQryV1Test {

    private static final String APP_ID = "xxx";
    private static final String MY_PRIVATE_KEY = "yyy";
    private static final String APIGW_PUBLIC_KEY = "zzz";
    private static final String BASE_URL = "http://ip:port/api";
    private static final String SERVICE_URI = "/atp/epcc/dealstateqry/V1";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        AtpEpccDealStateQryRequestV1 request = new AtpEpccDealStateQryRequestV1();
        request.setServiceUrl(BASE_URL + SERVICE_URI);
        AtpEpccDealStateQryRequestV1.AtpEpccDealStateQryRequestV1Biz bizContent =
                new AtpEpccDealStateQryRequestV1.AtpEpccDealStateQryRequestV1Biz();
        bizContent.setAppId("F-ATP");
        bizContent.setCorpDate("20241125");
        bizContent.setCorpTime("080000");
        bizContent.setCorpSerno("202411254330802607931593137");
        bizContent.setOriTrxCtgy("0120");
        bizContent.setOriTrxDtTm("20241123094100");
        bizContent.setOriTrxDate("20241123");
        bizContent.setOriCorpSerno("202411234330802607942493236");
        bizContent.setOriTrxScene("0016");

        request.setBizContent(bizContent);
        AtpEpccDealStateQryResponseV1 response;

        try {
            response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response);
            } else {
                // 业务失败处理
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
