package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.response.BiomTransportCashIbrnopayQryvalutainfoResponseV1;
import com.icbc.api.request.BiomTransportCashIbrnopayQryvalutainfoRequestV1;

public class BiomTransportCashIbrnopayQryvalutainfoV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIbrnopayQryvalutainfoRequestV1 request = new BiomTransportCashIbrnopayQryvalutainfoRequestV1();

        BiomTransportCashIbrnopayQryvalutainfoRequestV1.BiomTransportCashIbrnopayQryvalutainfoRequestV1Biz bizContent = new BiomTransportCashIbrnopayQryvalutainfoRequestV1.BiomTransportCashIbrnopayQryvalutainfoRequestV1Biz();

        BiomTransportCashIbrnopayQryvalutainfoRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIbrnopayQryvalutainfoRequestV1.PrivateReqBean();

        BiomTransportCashIbrnopayQryvalutainfoRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIbrnopayQryvalutainfoRequestV1.PublicReqBean();


        publicReqBean.setZoneno("200");

        privateReqBean.setCashType("001");
        privateReqBean.setCurrType("1");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIbrnopayQryvalutainfoResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
