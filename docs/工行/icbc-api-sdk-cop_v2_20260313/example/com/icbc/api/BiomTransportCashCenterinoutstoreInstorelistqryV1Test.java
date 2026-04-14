package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCenterinoutstoreInstorelistqryRequestV1;
import com.icbc.api.response.BiomTransportCashCenterinoutstoreInstorelistqryResponseV1;

import java.util.ArrayList;

public class BiomTransportCashCenterinoutstoreInstorelistqryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashCenterinoutstoreInstorelistqryRequestV1 request = new BiomTransportCashCenterinoutstoreInstorelistqryRequestV1();

        BiomTransportCashCenterinoutstoreInstorelistqryRequestV1.BiomTransportCashCenterinoutstoreInstorelistqryRequestV1Biz bizContent = new BiomTransportCashCenterinoutstoreInstorelistqryRequestV1.BiomTransportCashCenterinoutstoreInstorelistqryRequestV1Biz();

        BiomTransportCashCenterinoutstoreInstorelistqryRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCenterinoutstoreInstorelistqryRequestV1.PrivateReqBean();

        BiomTransportCashCenterinoutstoreInstorelistqryRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCenterinoutstoreInstorelistqryRequestV1.PublicReqBean();


        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setBranchId("11");
        privateReqBean.setZoneno("11");
        privateReqBean.setRouteNo("11");
        privateReqBean.setArticleId("11");
        privateReqBean.setLoq("11");
        privateReqBean.setEscortPern1("11");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashCenterinoutstoreInstorelistqryResponseV1 response = client.execute(request);
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
