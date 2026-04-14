package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesICsrcToFhProfhGetTaskRequestV1;
import com.icbc.api.response.BiomServicesICsrcToFhProfhGetTaskResponseV1;


import java.util.ArrayList;
import java.util.List;

public class BiomServicesICsrcToFhProfhGetTaskV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesICsrcToFhProfhGetTaskRequestV1 request = new BiomServicesICsrcToFhProfhGetTaskRequestV1();

        BiomServicesICsrcToFhProfhGetTaskRequestV1.BiomServicesICsrcToFhProfhGetTaskRequestV1Biz bizContent = new BiomServicesICsrcToFhProfhGetTaskRequestV1.BiomServicesICsrcToFhProfhGetTaskRequestV1Biz();

        BiomServicesICsrcToFhProfhGetTaskRequestV1.PrivateReqBean privateReqBean = new BiomServicesICsrcToFhProfhGetTaskRequestV1.PrivateReqBean();

        BiomServicesICsrcToFhProfhGetTaskRequestV1.PublicReqBean publicReqBean = new BiomServicesICsrcToFhProfhGetTaskRequestV1.PublicReqBean();

        publicReqBean.setChncode("703");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1");

        privateReqBean.setZoneno("00200");
        privateReqBean.setBrno("00002");
        privateReqBean.setCurrType("001");
        privateReqBean.setApplyno("050201213164864104117000153");
        privateReqBean.setMacno("200");


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesICsrcToFhProfhGetTaskResponseV1 response = client.execute(request);
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
