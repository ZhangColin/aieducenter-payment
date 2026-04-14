package com.icbc;


import com.icbc.ApipParamters;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssDividendOrderInfoRefundQueryRequestV1;
import com.icbc.api.response.BcssDividendOrderInfoRefundQueryResponseV1;

public class BcssDividendOrderInfoRefundQueryTestV1 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/api/bcss/dividend/orderinfo/refundquery/V1");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) {
        try {
            test_refundOrderInfoQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_refundOrderInfoQuery() throws Exception {
        BcssDividendOrderInfoRefundQueryRequestV1 request=new BcssDividendOrderInfoRefundQueryRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssDividendOrderInfoRefundQueryRequestV1.BcssDividendOrderInfoRefundQueryRequestBizV1 bizV1
                  = new BcssDividendOrderInfoRefundQueryRequestV1.BcssDividendOrderInfoRefundQueryRequestBizV1();

        bizV1.setCorpId("2000000138");
        bizV1.setIcbcAppid(APP_ID);
        bizV1.setOriOutTradeNo("mTraderNo20220316014");
        bizV1.setOutTradeNo("mTraderNo20220316014");
        bizV1.setStartIndustry("25");

        request.setBizContent(bizV1);
        DefaultIcbcClient client = getIcbcClient();
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        BcssDividendOrderInfoRefundQueryResponseV1 response = client.execute(request);
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
