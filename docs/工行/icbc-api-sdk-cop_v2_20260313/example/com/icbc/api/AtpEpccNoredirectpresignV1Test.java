package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.AtpEpccNoredirectpresignRequestV1;
import com.icbc.api.response.AtpEpccNoredirectpresignResponseV1;

/**
 * @author kfzx-qianw
 * @date 2023/7/12 14:12
 * @description
 */
public class AtpEpccNoredirectpresignV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "zzz";

    public static void main(String[] args) {

        String URI = "atp/epcc/noredirectpresign/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            AtpEpccNoredirectpresignRequestV1.AtpEpccNoredirectpresignRequestBizV1 bizContent = new AtpEpccNoredirectpresignRequestV1.AtpEpccNoredirectpresignRequestBizV1();
            AtpEpccNoredirectpresignRequestV1 request = new AtpEpccNoredirectpresignRequestV1();

		    bizContent.setAppId("F-ATP");
            bizContent.setCorpDate("20200629");
            bizContent.setCorpTime("162900");
            bizContent.setCorpSerno("2019052214301805221430183803651");
            bizContent.setCorpNo("0147");
            bizContent.setCustAcc("6214**********231");
            bizContent.setCardType("1");
            bizContent.setCustName("张**");
            bizContent.setCustIdType("0");
            bizContent.setCustIdNo("310101********0466");
            bizContent.setCustPhone("186****8230");
            bizContent.setSilentScene("posSign");

            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            AtpEpccNoredirectpresignResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                //业务成功处理
                System.out.println(JSON.toJSONString(responseV1));
            } else {
                //失败
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
