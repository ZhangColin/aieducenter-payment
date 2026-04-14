package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1;
import com.icbc.api.response.BiomTransportCashTaskarticlemoverQryboxassignroutelistResponseV1;

public class BiomTransportCashTaskarticlemoverQryboxassignroutelistV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1 request = new BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1();

        BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1.BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1Biz bizContent = new BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1.BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1Biz();

        BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1.PrivateReqBean();

        BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashTaskarticlemoverQryboxassignroutelistRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setLocSeq("00");
        privateReqBean.setPageNum(1);
        privateReqBean.setPageSize(1);
        privateReqBean.setRouteId("");
        privateReqBean.setStruId("0001");
        privateReqBean.setSrvWorkDate("2025-03-09");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashTaskarticlemoverQryboxassignroutelistResponseV1 response = client.execute(request);
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
