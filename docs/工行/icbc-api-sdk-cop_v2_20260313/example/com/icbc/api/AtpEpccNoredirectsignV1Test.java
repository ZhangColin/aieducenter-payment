package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.AtpEpccNoredirectsignRequestV1;
import com.icbc.api.response.AtpEpccNoredirectsignResponseV1;

/**
 * @author kfzx-qianw
 * @date 2023/7/12 14:12
 * @description
 */
public class AtpEpccNoredirectsignV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "zzz";

    public static void main(String[] args) {

        String URI = "atp/epcc/noredirectsign/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            AtpEpccNoredirectsignRequestV1.AtpEpccNoredirectsignRequestBizV1 bizContent = new AtpEpccNoredirectsignRequestV1.AtpEpccNoredirectsignRequestBizV1();
            AtpEpccNoredirectsignRequestV1 request = new AtpEpccNoredirectsignRequestV1();

		    bizContent.setAppId("F-ATP");
            bizContent.setCorpDate("20200629");
            bizContent.setCorpTime("162900");
            bizContent.setCorpSerno("2023062914301805221430183803651");
            bizContent.setCorpNo("0147");
            bizContent.setCustAcc("6214**********231");
            bizContent.setCardType("1");
            bizContent.setCustName("张**");
            bizContent.setCustIdType("0");
            bizContent.setCustIdNo("310101********0466");
            bizContent.setCustPhone("186****8230");
            bizContent.setSilentScene("posSign");
            bizContent.setInstgAcct("testInstgAcct");
            bizContent.setTrxId("202306281098234531478765467111");

            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            AtpEpccNoredirectsignResponseV1 response = client.execute(request);
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
