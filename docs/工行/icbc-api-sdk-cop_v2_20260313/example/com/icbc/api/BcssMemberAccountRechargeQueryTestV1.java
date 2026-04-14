package com.icbc;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberAccountRechargeQueryRequestV1;
import com.icbc.api.response.BcssMemberAccountRechargeQueryResponseV1;

/**
 * @author kfzx-ouhz
 */
public class BcssMemberAccountRechargeQueryTestV1 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/api/bcss/member/account/recharge/query/V1");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) {

        try {
            test_rechargeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 充值查询测试类
     */
    public static void test_rechargeQuery() throws Exception {
        BcssMemberAccountRechargeQueryRequestV1 request = new BcssMemberAccountRechargeQueryRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssMemberAccountRechargeQueryRequestV1.BcssRechargeQueryRequestBizV1 bizContent = new BcssMemberAccountRechargeQueryRequestV1.BcssRechargeQueryRequestBizV1();
        //设置参数
        bizContent.setCorpId("2000000067");
        bizContent.setTimeStamp("2021-07-08 17:06:45");
        bizContent.setMerNo("00000000000000000000");
        bizContent.setImeiNo("1");
        bizContent.setManufacturerId("1");
        bizContent.setClientType("3");
        bizContent.setClientTransNo("1");
        bizContent.setApplyNo("3");
        bizContent.setQueryType("1");
        request.setBizContent(bizContent);
        DefaultIcbcClient client = getIcbcClient();
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        BcssMemberAccountRechargeQueryResponseV1 response = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }

    /**
     * api
     *
     * @return
     */
    private static DefaultIcbcClient getIcbcClient() {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        return client;
    }

    /**
     * UI
     *
     * @return
     */
    private static UiIcbcClient getUiClinet() {
        UiIcbcClient client = new UiIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        return client;
    }
}

