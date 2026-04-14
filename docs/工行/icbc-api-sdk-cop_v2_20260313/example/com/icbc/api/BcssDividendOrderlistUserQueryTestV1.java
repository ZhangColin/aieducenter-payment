package com.icbc;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssDividendOrderlistUserQueryRequestV1;
import com.icbc.api.response.BcssDividendOrderlistUserQueryResponseV1;

public class BcssDividendOrderlistUserQueryTestV1 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/api/bcss/dividend/orderlist/user/query/V1");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) {
        try {
            test_userOrderListQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_userOrderListQuery() throws Exception {
        BcssDividendOrderlistUserQueryRequestV1 request = new BcssDividendOrderlistUserQueryRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssDividendOrderlistUserQueryRequestV1.BcssDividendOrderlistUserQueryRequestBizV1 bizV1
                = new BcssDividendOrderlistUserQueryRequestV1.BcssDividendOrderlistUserQueryRequestBizV1();

        bizV1.setCorpId("2000000138");
        bizV1.setBeginDate("2022-01-22 17:00:00");
        bizV1.setCustId("00000000000000000182");
        bizV1.setEndDate("2022-03-11 17:00:00");
        bizV1.setIcbcAppid("10000000000004097413");
        bizV1.setPageNum("1");
        bizV1.setPageSize("10");
        bizV1.setStatus("0");

        request.setBizContent(bizV1);
        DefaultIcbcClient client = getIcbcClient();
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        BcssDividendOrderlistUserQueryResponseV1 response = client.execute(request);
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
