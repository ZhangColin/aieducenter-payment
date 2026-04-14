package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1;
import com.icbc.api.response.BiomTransportCashCenterinoutstoreOutstorelistqryResponseV1;

public class BiomTransportCashCenterinoutstoreOutstorelistqryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1 request = new BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1();

        BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1.BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1Biz bizContent = new BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1.BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1Biz();

        BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1.PrivateReqBean();

        BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCenterinoutstoreOutstorelistqryRequestV1.PublicReqBean();


        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setBranchId("11");
        privateReqBean.setRouteNo("01");
        privateReqBean.setZoneno("200");
        privateReqBean.setEscortPern1("77");
        privateReqBean.setSize("10");
        privateReqBean.setPage("1");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashCenterinoutstoreOutstorelistqryResponseV1 response = client.execute(request);
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
