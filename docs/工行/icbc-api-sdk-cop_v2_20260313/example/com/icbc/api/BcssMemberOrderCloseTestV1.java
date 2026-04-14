package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberOrderCloseRequestV1;
import com.icbc.api.response.BcssMemberOrderCloseResponseV1;


public class BcssMemberOrderCloseTestV1 {
    protected static final String MY_PRIVATE_KEY = "xxx";

    protected static final String APIGW_PUBLIC_KEY = "xxxxx";

    protected static final String APP_ID = "10000000000004xxxxxx";
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = "http://xx";

    private final static String SIGN_TYPE = "RSA2";
    public final static String URL = "/api/bcss/member/order/close/V1";

    public static void main(String[] args) throws Exception {
        testCop();
    }

    public static void testCop() throws IcbcApiException {
        BcssMemberOrderCloseRequestV1 request = new BcssMemberOrderCloseRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssMemberOrderCloseRequestV1.BcssMemberOrderCloseRequestV1Biz bizContent = new BcssMemberOrderCloseRequestV1.BcssMemberOrderCloseRequestV1Biz();


        bizContent.setCorpId("2000000067");
        bizContent.setMerOrderNo("1111111111133");
        bizContent.setClientTransNo("100116517123415551514");
        bizContent.setTimeStamp("2023-10-29 20:44:38");
        bizContent.setTradeNo("1111111111133");
        bizContent.setOrderNo("");
        bizContent.setDataSrc("16");
        bizContent.setThirdNo("16");
        bizContent.setMerNo("");

        request.setBizContent(bizContent);

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemberOrderCloseResponseV1 response = client.execute(request);
    }
}
