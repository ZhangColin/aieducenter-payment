package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIcassdistritaskproAdddistritaskRequestV1;
import com.icbc.api.response.BiomServicesIcassdistritaskproAdddistritaskResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomServicesIcassdistritaskproAdddistritaskV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesIcassdistritaskproAdddistritaskRequestV1 request = new BiomServicesIcassdistritaskproAdddistritaskRequestV1();

        BiomServicesIcassdistritaskproAdddistritaskRequestV1.BiomServicesIcassdistritaskproAdddistritaskRequestV1Biz bizContent = new BiomServicesIcassdistritaskproAdddistritaskRequestV1.BiomServicesIcassdistritaskproAdddistritaskRequestV1Biz();

        BiomServicesIcassdistritaskproAdddistritaskRequestV1.PrivateReqBean privateReqBean = new BiomServicesIcassdistritaskproAdddistritaskRequestV1.PrivateReqBean();

        BiomServicesIcassdistritaskproAdddistritaskRequestV1.PublicReqBean publicReqBean = new BiomServicesIcassdistritaskproAdddistritaskRequestV1.PublicReqBean();


        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setBrno("2");
        privateReqBean.setPackIdList("");
        privateReqBean.setTellerName("22");
        privateReqBean.setStruId("2222");
        privateReqBean.setLocName("2222");


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIcassdistritaskproAdddistritaskResponseV1 response = client.execute(request);
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
