package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1;
import com.icbc.api.response.BiomTransportCashCenterinoutstoreInstoredetailqryResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashCenterinoutstoreInstoredetailqryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1 request = new BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1();

        BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1.BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1Biz bizContent = new BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1.BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1Biz();

        BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1.PrivateReqBean();

        BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCenterinoutstoreInstoredetailqryRequestV1.PublicReqBean();


        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setBranchId("11");
        privateReqBean.setZoneno("11");
        privateReqBean.setRouteNo("11");
        privateReqBean.setQryTypeList(new ArrayList());

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashCenterinoutstoreInstoredetailqryResponseV1 response = client.execute(request);
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
