package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MenonlineRefundOnlinerefundRequestV1;
import com.icbc.api.response.MenonlineRefundOnlinerefundResponseV1;


/**
 * @Auther kfzx-wangsj04
 * @Description
 * @Date 2020/4/28
 */

public class MenonlineRefundOnlinerefundTestV1 {
    protected static final String MY_PRIVATE_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    protected static final String APIGW_PUBLIC_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    protected static final String APP_ID = "xxxxxxxxxxxxxx";
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        MenonlineRefundOnlinerefundTestV1 a = new MenonlineRefundOnlinerefundTestV1();
        a.test_orderQuery();

    }

    private void test_orderQuery() throws IcbcApiException {
        MenonlineRefundOnlinerefundRequestV1 requestV1 = new MenonlineRefundOnlinerefundRequestV1();
        requestV1.setServiceUrl("http://xxxxxxxx:xxxx/api/menonline/refund/onlineRefund/V1");
        MenonlineRefundOnlinerefundRequestV1.MenonlineRefundOnlinerefundRequestBizV1 biz = new MenonlineRefundOnlinerefundRequestV1.MenonlineRefundOnlinerefundRequestBizV1();
        biz.setCorpId("xxxxxxxxxxx");
        biz.setMerNo("xxxxxxxxxxx");
        requestV1.setBizContent(biz);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MenonlineRefundOnlinerefundResponseV1 responseV1=client.execute(requestV1);
        System.out.println(JSONObject.toJSONString(responseV1));
    }

}
