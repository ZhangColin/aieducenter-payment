package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberOrderRefundRequestV1;
import com.icbc.api.response.BcssMemberOrderRefundResponseV1;


public class BcssMemberOrderRefundTestV1 {
    protected static final String APIGW_PUBLIC_KEY = "xxxxxxxxxxxxxxxxxx";

    protected static final String MY_PRIVATE_KEY = "xxxxxxxxxxxxxx";


    protected static final String APP_ID = "1000000000000xxxxxxxxxx3";

    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = "http://apip:8081";

    private final static String SIGN_TYPE = "RSA2";
    public final static String URL = "/api/bcss/member/order/refund/V1";


    public static void main(String[] args) throws IcbcApiException {
        BcssMemberOrderRefundRequestV1 request = new BcssMemberOrderRefundRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssMemberOrderRefundRequestV1.BcssMemberOrderRefundRequestV1Biz bizContent = new BcssMemberOrderRefundRequestV1.BcssMemberOrderRefundRequestV1Biz();


        bizContent.setCorpId("2000000067");
        bizContent.setMerNo("");
        bizContent.setClientTransNo("51256366346151151553");
        bizContent.setTimeStamp("2025-05-07 10:44:38");
        bizContent.setMerOrderNo("1111111111311");
        bizContent.setTradeNo("1111111111311");
//        bizContent.setOrderNo("");
        bizContent.setDataSrc("16");
        bizContent.setThirdNo("87765");
        bizContent.setRefundAppNo("2141564204189662594");
        bizContent.setRefundType("2");
        bizContent.setRefundAmt("1");
//        bizContent.setServiceFeeAmt("1");

//        List<BcssMemOrderRefunGoodsDto> goods = new ArrayList<>();
//        BcssMemOrderRefunGoodsDto good1 = new BcssMemOrderRefunGoodsDto();
//        good1.setRefundAmt("500");
//        good1.setGoodsNo("**********");
//        good1.setGoodsNum("10");
//        goods.add(good1);
//        bizContent.setRefunds(goods);

        request.setBizContent(bizContent);

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemberOrderRefundResponseV1 response = client.execute(request);
    }
}
