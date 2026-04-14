package com.icbc.api;

import com.icbc.api.request.AtpEpccFastbindcardRequestV1;
import com.icbc.api.response.AtpEpccFastbindcardResponseV1;

/**
 * @author kfzx-heyj
 * @date 2023-12-29 16:58:08
 * @description 2401B IFastBindCardServInf-网联一键绑卡服务-测试
 */
public class AtpEpccFastbindcardV1Test {

    private static final String APP_ID = "xxx";
    private static final String MY_PRIVATE_KEY = "yyy";
    private static final String APIGW_PUBLIC_KEY = "zzz";
    private static final String BASE_URL = "http://ip:port/api";
    private static final String SERVICE_URI = "/atp/epcc/fastbindcard/V1";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        AtpEpccFastbindcardRequestV1 request = new AtpEpccFastbindcardRequestV1();
        request.setServiceUrl(BASE_URL + SERVICE_URI);
        AtpEpccFastbindcardRequestV1.AtpEpccFastbindcardRequestV1Biz bizContent =
                new AtpEpccFastbindcardRequestV1.AtpEpccFastbindcardRequestV1Biz();
        bizContent.setAppId("F-ATP");
        bizContent.setCorpDate("20240102");
        bizContent.setCorpTime("080000");
        bizContent.setCorpSerno("2024010208000005221430183803651");
        bizContent.setSgnAcctTp("00");
        bizContent.setSgnAcctId("6214**********231");
        bizContent.setCustCINO("23589***787");
        bizContent.setSgnAcctNm("张三");
        bizContent.setCardZoneNo("02000");
        bizContent.setCardBrNo("02023");
        bizContent.setIDTp("0");
        bizContent.setIDNo("33XXXX19XXXXXXXX19");
        bizContent.setMobNo("124****5478");
        bizContent.setInstgId("0147");
        bizContent.setInstgAcct("6762**********782");
        bizContent.setClbckUrl("http://www.xxx.com");
        bizContent.setSaleCode("328432");
        bizContent.setCallChannel("006");
        bizContent.setNote("备注");

        request.setBizContent(bizContent);
        AtpEpccFastbindcardResponseV1 response;

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
