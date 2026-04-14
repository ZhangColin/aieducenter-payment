package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCenterinoutstoreInstorescanupdRequestV1;
import com.icbc.api.response.BiomTransportCashCenterinoutstoreInstorescanupdResponseV1;


import java.util.ArrayList;

public class BiomTransportCashCenterinoutstoreInstorescanupdV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashCenterinoutstoreInstorescanupdRequestV1 request = new BiomTransportCashCenterinoutstoreInstorescanupdRequestV1();

        BiomTransportCashCenterinoutstoreInstorescanupdRequestV1.BiomTransportCashCenterinoutstoreInstorescanupdRequestV1Biz bizContent = new BiomTransportCashCenterinoutstoreInstorescanupdRequestV1.BiomTransportCashCenterinoutstoreInstorescanupdRequestV1Biz();

        BiomTransportCashCenterinoutstoreInstorescanupdRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCenterinoutstoreInstorescanupdRequestV1.PrivateReqBean();

        BiomTransportCashCenterinoutstoreInstorescanupdRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCenterinoutstoreInstorescanupdRequestV1.PublicReqBean();


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setArticleIdList(new ArrayList());
        privateReqBean.setDoneList(new ArrayList());
        privateReqBean.setExtraList(new ArrayList());
        privateReqBean.setUndoneList(new ArrayList());
        privateReqBean.setBranchId("0020001242");
        privateReqBean.setOperPern("020000119");
        privateReqBean.setOperPernName("����");
        privateReqBean.setOperBranchName("��������ҵ��������");
        privateReqBean.setRouteNo("189");
        privateReqBean.setRouteName("�����Ͻ���·");
        privateReqBean.setForced("");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashCenterinoutstoreInstorescanupdResponseV1 response = client.execute(request);
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
