package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIdistritaskproQrydistritaskRequestV1;
import com.icbc.api.response.BiomServicesIdistritaskproQrydistritaskResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomServicesIdistritaskproQrydistritaskV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesIdistritaskproQrydistritaskRequestV1 request = new BiomServicesIdistritaskproQrydistritaskRequestV1();

        BiomServicesIdistritaskproQrydistritaskRequestV1.BiomServicesIdistritaskproQrydistritaskRequestV1Biz bizContent = new BiomServicesIdistritaskproQrydistritaskRequestV1.BiomServicesIdistritaskproQrydistritaskRequestV1Biz();

        BiomServicesIdistritaskproQrydistritaskRequestV1.PrivateReqBean privateReqBean = new BiomServicesIdistritaskproQrydistritaskRequestV1.PrivateReqBean();

        BiomServicesIdistritaskproQrydistritaskRequestV1.PublicReqBean publicReqBean = new BiomServicesIdistritaskproQrydistritaskRequestV1.PublicReqBean();

        List<BiomServicesIdistritaskproQrydistritaskRequestV1.retMap> retList = new ArrayList<>();
        BiomServicesIdistritaskproQrydistritaskRequestV1.retMap retMap = new BiomServicesIdistritaskproQrydistritaskRequestV1.retMap();

        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setSimpplify(1);
        privateReqBean.setTaskId("00200009982507010058");
        privateReqBean.setZoneno("200");


        retList.add(retMap);
        retMap.setCurr("10000");
        retMap.setTagType("3");
        retMap.setTickets("10000");
        retMap.setTicketNum("2");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIdistritaskproQrydistritaskResponseV1 response = client.execute(request);
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
