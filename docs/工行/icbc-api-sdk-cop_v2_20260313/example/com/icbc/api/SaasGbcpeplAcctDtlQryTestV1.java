package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.SaasGbcpeplAcctDtlQryRequestV1;
import com.icbc.api.response.SaasGbcpeplAcctDtlQryResponseV1;

/**
 * All rights Reserved, Designed By ICBC
 *
 * @author kfzx-chenl1
 * @version V1.0
 * @Title: SaasGbcpeplAcctDtlQryTestV1
 * @Description: 类的概述.
 * <p>类的详细说明,阐述该类的具体作用和使用场景<br>
 * @Copyright: 2020 ICBC All rights reserved.
 * @see
 * @since 2020/6/19 16:46
 */
public class SaasGbcpeplAcctDtlQryTestV1 {

    protected static String appId = "XXXX";
    protected static String APIGW_PUBLIC_KEY = "XXXX";
    protected static String MY_PRIVATE_KEY = "XXX";
    protected static String BASE_URL = "https://ip:port/api";

    private static String URL = "/saas/gbcpepl/account/acctdtlqry/V1";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        SaasGbcpeplAcctDtlQryRequestV1 requestV1 = new SaasGbcpeplAcctDtlQryRequestV1();
        SaasGbcpeplAcctDtlQryRequestV1.SaasGbcpeplAcctDtlQryRequestBizV1 requestBizV1 = new SaasGbcpeplAcctDtlQryRequestV1.SaasGbcpeplAcctDtlQryRequestBizV1();

        requestV1.setServiceUrl(BASE_URL + URL);
        requestV1.setBizContent(requestBizV1);
        requestBizV1.setMsgId("XXXXXX");
        requestBizV1.setInstId("inst_001");
        requestBizV1.setInstAcct("XXXXXX");
        requestBizV1.setSubAccount("XXXXXX");
        requestBizV1.setWorkDate("2020-12-30");

        try {
            client.execute(requestV1);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
