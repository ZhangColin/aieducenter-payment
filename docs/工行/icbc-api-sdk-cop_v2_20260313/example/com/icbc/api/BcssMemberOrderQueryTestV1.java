package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberOrderQueryRequestV1;
import com.icbc.api.response.BcssMemberOrderQueryResponseV1;

public class BcssMemberOrderQueryTestV1 {
    protected static final String MY_PRIVATE_KEY = "xxxxxxxxxxxxxxxx";

    protected static final String APIGW_PUBLIC_KEY = "xxxxxxxxxxxxxxxxx";

    protected static final String APP_ID = "1000000000000xxxxxxx";
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = "http://apip:8081";

    private final static String SIGN_TYPE = "RSA2";
    public final static String URL = "/api/bcss/member/order/query/V1";

    public static void main(String[] args) throws Exception {
        testCop();
    }

    public static void testCop() throws IcbcApiException {
        BcssMemberOrderQueryRequestV1 request = new BcssMemberOrderQueryRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssMemberOrderQueryRequestV1.BcssMemberOrderQueryRequestV1Biz bizContent = new BcssMemberOrderQueryRequestV1.BcssMemberOrderQueryRequestV1Biz();

        bizContent.setCorpId("2000000067");
        bizContent.setMerNo("");
        bizContent.setClientTransNo("101541251250116547");
        bizContent.setTimeStamp("2023-10-29 20:44:38");
        bizContent.setMerOrderNo("1962858322222858245");
        bizContent.setTradeNo("1962858322222858245");
        bizContent.setOrderNo("");
        bizContent.setDataSrc("16");
        bizContent.setThirdNo("87765");
        bizContent.setIndustry("");

        request.setBizContent(bizContent);

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemberOrderQueryResponseV1 response = client.execute(request);
    }
}
