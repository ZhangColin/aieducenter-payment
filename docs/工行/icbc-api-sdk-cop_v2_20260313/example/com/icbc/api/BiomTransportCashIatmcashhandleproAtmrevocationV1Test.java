package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIatmcashhandleproAtmrevocationRequestV1;
import com.icbc.api.response.BiomTransportCashIatmcashhandleproAtmrevocationResponseV1;

public class BiomTransportCashIatmcashhandleproAtmrevocationV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIatmcashhandleproAtmrevocationRequestV1 request = new BiomTransportCashIatmcashhandleproAtmrevocationRequestV1();

        BiomTransportCashIatmcashhandleproAtmrevocationRequestV1.BiomTransportCashIatmcashhandleproAtmrevocationRequestV1Biz bizContent = new BiomTransportCashIatmcashhandleproAtmrevocationRequestV1.BiomTransportCashIatmcashhandleproAtmrevocationRequestV1Biz();

        BiomTransportCashIatmcashhandleproAtmrevocationRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIatmcashhandleproAtmrevocationRequestV1.PrivateReqBean();

        BiomTransportCashIatmcashhandleproAtmrevocationRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIatmcashhandleproAtmrevocationRequestV1.PublicReqBean();



        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setZoneno("200");
        privateReqBean.setTaskId("1111111");
        privateReqBean.setTaskType("02002");
        privateReqBean.setAddcode("0012441");
        privateReqBean.setInputdate("20230101");
        privateReqBean.setDevId("111111");
        privateReqBean.setChksiteno("2");
        privateReqBean.setAuthtlnoName("Alice");
        privateReqBean.setAuthtlno("11111111111111");
        privateReqBean.setTellerName("Alice");
        privateReqBean.setOtellerno("11111");
        privateReqBean.setBranchId("11111111");
        privateReqBean.setBrno("2");


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIatmcashhandleproAtmrevocationResponseV1 response = client.execute(request);
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
