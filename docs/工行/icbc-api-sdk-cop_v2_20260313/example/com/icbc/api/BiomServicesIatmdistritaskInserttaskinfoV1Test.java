package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIatmdistritaskInserttaskinfoRequestV1;
import com.icbc.api.response.BiomServicesIatmdistritaskInserttaskinfoResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomServicesIatmdistritaskInserttaskinfoV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesIatmdistritaskInserttaskinfoRequestV1 request = new BiomServicesIatmdistritaskInserttaskinfoRequestV1();

        BiomServicesIatmdistritaskInserttaskinfoRequestV1.BiomServicesIatmdistritaskInserttaskinfoRequestV1Biz bizContent = new BiomServicesIatmdistritaskInserttaskinfoRequestV1.BiomServicesIatmdistritaskInserttaskinfoRequestV1Biz();

        BiomServicesIatmdistritaskInserttaskinfoRequestV1.PrivateReqBean privateReqBean = new BiomServicesIatmdistritaskInserttaskinfoRequestV1.PrivateReqBean();

        BiomServicesIatmdistritaskInserttaskinfoRequestV1.PublicReqBean publicReqBean = new BiomServicesIatmdistritaskInserttaskinfoRequestV1.PublicReqBean();

        List<BiomServicesIatmdistritaskInserttaskinfoRequestV1.aticleIdMap> aticleIdList = new ArrayList<>();
        BiomServicesIatmdistritaskInserttaskinfoRequestV1.aticleIdMap aticleIdMap = new BiomServicesIatmdistritaskInserttaskinfoRequestV1.aticleIdMap();

        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setBrno("998");
        privateReqBean.setBranchId("0020000080");
        privateReqBean.setUserId("160500011");
        privateReqBean.setUserName("高艳玲");
        privateReqBean.setZoneno("200");
        privateReqBean.setAticleIdList(aticleIdList);

        aticleIdList.add(aticleIdMap);
        aticleIdMap.setAddMoneyDate("2027-03-03");
        aticleIdMap.setMoneyBagId("100160001080000089");
        aticleIdMap.setDevidOutStatus("1");
        aticleIdMap.setDevidOutStatusDict("未在途撤销");
        aticleIdMap.setDevReqId("0200004703040004");
        aticleIdMap.setStatus(1);

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIatmdistritaskInserttaskinfoResponseV1 response = client.execute(request);
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
