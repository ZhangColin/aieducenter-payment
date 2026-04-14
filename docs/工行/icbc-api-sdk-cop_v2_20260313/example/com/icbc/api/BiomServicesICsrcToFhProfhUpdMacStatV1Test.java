package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesICsrcToFhProfhUpdMacStatRequestV1;
import com.icbc.api.response.BiomServicesICsrcToFhProfhUpdMacStatResponseV1;

public class BiomServicesICsrcToFhProfhUpdMacStatV1Test {


    protected static final String MY_PRIVATE_KEY = "****";BiomServicesICsrcToFhProfhUpdMacStatRequestV1

    protected static final String APP_ID = "1234";BiomServicesICsrcToFhProfhUpdMacStatRequestV1

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesICsrcToFhProfhUpdMacStatRequestV1 request = new BiomTransportCashICsrcToFhProfhUpdMacStatRequestV1();

        BiomServicesICsrcToFhProfhUpdMacStatRequestV1.BiomServicesICsrcToFhProfhUpdMacStatRequestV1Biz bizContent = new BiomServicesICsrcToFhProfhUpdMacStatRequestV1.BiomServicesICsrcToFhProfhUpdMacStatRequestV1Biz();

        BiomServicesICsrcToFhProfhUpdMacStatRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashICsrcToFhProfhUpdMacStatRequestV1.PrivateReqBean();

        BiomServicesICsrcToFhProfhUpdMacStatRequestV1.PublicReqBean publicReqBean = new BiomTransportCashICsrcToFhProfhUpdMacStatRequestV1.PublicReqBean();

        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("998");
        privateReqBean.setMacstat("001");
        privateReqBean.setMacno("1");





        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try {
            BiomServicesICsrcToFhProfhUpdMacStatResponseV1 response = client.execute(request);
            if (response.getReturn_code() == 0) {
                System.out.println("ReturnCode:" + response.getReturn_code());
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
