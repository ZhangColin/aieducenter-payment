package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIatmdistritaskQryatmmoneybagRequestV1;
import com.icbc.api.response.BiomServicesIatmdistritaskQryatmmoneybagResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomServicesIatmdistritaskQryatmmoneybagV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesIatmdistritaskQryatmmoneybagRequestV1 request = new BiomServicesIatmdistritaskQryatmmoneybagRequestV1();

        BiomServicesIatmdistritaskQryatmmoneybagRequestV1.BiomServicesIatmdistritaskQryatmmoneybagRequestV1Biz bizContent = new BiomServicesIatmdistritaskQryatmmoneybagRequestV1.BiomServicesIatmdistritaskQryatmmoneybagRequestV1Biz();

        BiomServicesIatmdistritaskQryatmmoneybagRequestV1.PrivateReqBean privateReqBean = new BiomServicesIatmdistritaskQryatmmoneybagRequestV1.PrivateReqBean();

        BiomServicesIatmdistritaskQryatmmoneybagRequestV1.PublicReqBean publicReqBean = new BiomServicesIatmdistritaskQryatmmoneybagRequestV1.PublicReqBean();



        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setAddMoneyDate("2023-01-01");
        privateReqBean.setStatus("2");
        privateReqBean.setLocId("9999");
        privateReqBean.setBranchId("9999");
        privateReqBean.setDevId("9999");
        privateReqBean.setRoutNo("9999");
        privateReqBean.setMoneyBagId("9999");




        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIatmdistritaskQryatmmoneybagResponseV1 response = client.execute(request);
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
