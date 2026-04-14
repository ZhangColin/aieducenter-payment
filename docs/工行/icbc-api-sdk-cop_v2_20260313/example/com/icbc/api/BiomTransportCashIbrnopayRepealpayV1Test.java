package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIbrnopayRepealpayRequestV1;
import com.icbc.api.response.BiomTransportCashIbrnopayRepealpayResponseV1;

public class BiomTransportCashIbrnopayRepealpayV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIbrnopayRepealpayRequestV1 request = new BiomTransportCashIbrnopayRepealpayRequestV1();

        BiomTransportCashIbrnopayRepealpayRequestV1.BiomTransportCashIbrnopayRepealPayRequestV1Biz bizContent = new BiomTransportCashIbrnopayRepealpayRequestV1.BiomTransportCashIbrnopayRepealPayRequestV1Biz();

        BiomTransportCashIbrnopayRepealpayRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIbrnopayRepealpayRequestV1.PrivateReqBean();

        BiomTransportCashIbrnopayRepealpayRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIbrnopayRepealpayRequestV1.PublicReqBean();


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setZoneno(200);
        privateReqBean.setBrno(998);
        privateReqBean.setAuthtlnoName("肖文全");
        privateReqBean.setAuthtlno("020000026");
        privateReqBean.setAllocDate("2027-10-01");
        privateReqBean.setAuthAmount("20000");
        privateReqBean.setLstModTell("021214451");
        privateReqBean.setLstModDate("2023-06-20");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIbrnopayRepealpayResponseV1 response = client.execute(request);
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
