package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIdistritaskproInserttaskinfoRequestV1;
import com.icbc.api.response.BiomServicesIdistritaskproInserttaskinfoResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomServicesIdistritaskproInserttaskinfoV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesIdistritaskproInserttaskinfoRequestV1 request = new BiomServicesIdistritaskproInserttaskinfoRequestV1();

        BiomServicesIdistritaskproInserttaskinfoRequestV1.BiomServicesIdistritaskproInserttaskinfoRequestV1Biz bizContent = new BiomServicesIdistritaskproInserttaskinfoRequestV1.BiomServicesIdistritaskproInserttaskinfoRequestV1Biz();

        BiomServicesIdistritaskproInserttaskinfoRequestV1.PrivateReqBean privateReqBean = new BiomServicesIdistritaskproInserttaskinfoRequestV1.PrivateReqBean();

        BiomServicesIdistritaskproInserttaskinfoRequestV1.PublicReqBean publicReqBean = new BiomServicesIdistritaskproInserttaskinfoRequestV1.PublicReqBean();

        List<BiomServicesIdistritaskproInserttaskinfoRequestV1.retMap> retList = new ArrayList<>();
        BiomServicesIdistritaskproInserttaskinfoRequestV1.retMap retMap = new BiomServicesIdistritaskproInserttaskinfoRequestV1.retMap();

        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");
        publicReqBean.setOtellernoName("200");
        publicReqBean.setBrno("998");

        privateReqBean.setAMOUNT("200");
        privateReqBean.setAuthUserId("998");
        privateReqBean.setBRANCH_ID("肖文全");
        privateReqBean.setCashType("020000026");
        privateReqBean.setRetList(retList);

        retList.add(retMap);
        retMap.setCurr("10000");
        retMap.setTagType("3");
        retMap.setTickets("10000");
        retMap.setTicketNum("2");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIdistritaskproInserttaskinfoResponseV1 response = client.execute(request);
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
