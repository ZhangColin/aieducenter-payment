package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashTaskarticlemoverQryboxassignbrnolistRequestV1;
import com.icbc.api.request.BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1;
import com.icbc.api.response.BiomTransportCashTaskarticlemoverQryboxassignbrnolistResponseV1;
import com.icbc.api.response.BiomTransportCashTaskarticlemoverSumbitboxassignResponseV1;

import java.util.ArrayList;

public class BiomTransportCashTaskarticlemoverSumbitBoxAssignV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1 request = new BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1();

        BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1.BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1Biz bizContent = new BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1.BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1Biz();

        BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1.PrivateReqBean();

        BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1.PublicReqBean publicReqBean = new BiomTransportCashTaskarticlemoverSumbitboxassignRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("998");
        privateReqBean.setStruId("0020001242");
        privateReqBean.setAllotDate("2025-05-21");
        privateReqBean.setInStruId("0020001242");
        privateReqBean.setBrtype("1");
        privateReqBean.setArticleIdList(new ArrayList<>());
        privateReqBean.setRouteId("185");
        privateReqBean.setRouteName("自动化测试");
        privateReqBean.setOtellerno("020000119");
        privateReqBean.setTellerName("荣荣");



        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashTaskarticlemoverSumbitboxassignResponseV1 response = client.execute(request);
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
