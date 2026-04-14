package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1;
import com.icbc.api.response.BiomServicesIcassdistritaskproQrybagtmpinfoResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomServicesIcassdistritaskproQrybagtmpinfoV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1 request = new BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1();

        BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1.BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1Biz bizContent = new BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1.BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1Biz();

        BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1.PrivateReqBean privateReqBean = new BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1.PrivateReqBean();

        BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1.PublicReqBean publicReqBean = new BiomServicesIcassdistritaskproQrybagtmpinfoRequestV1.PublicReqBean();


        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setStruId("");
        privateReqBean.setPackId("");
        privateReqBean.setServeDate("");
        privateReqBean.setLocSeq("");
        privateReqBean.setLocName("");


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIcassdistritaskproQrybagtmpinfoResponseV1 response = client.execute(request);
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
