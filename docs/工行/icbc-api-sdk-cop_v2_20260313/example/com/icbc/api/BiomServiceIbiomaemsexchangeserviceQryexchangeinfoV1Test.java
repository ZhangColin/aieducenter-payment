package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1;
import com.icbc.api.response.BiomServiceIbiomaemsexchangeserviceQryexchangeinfoResponseV1;

public class BiomServiceIbiomaemsexchangeserviceQryexchangeinfoV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1 request = new BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1();

        BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1.BiomServicesIbiomaemsexchangeserviceQryexchangeinfoRequestV1Biz bizContent = new BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1.BiomServicesIbiomaemsexchangeserviceQryexchangeinfoRequestV1Biz();

        BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1.PrivateReqBean privateReqBean = new BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1.PrivateReqBean();

        BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1.PublicReqBean publicReqBean = new BiomServiceIbiomaemsexchangeserviceQryexchangeinfoRequestV1.PublicReqBean();


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setStatus(1);
        privateReqBean.setFlag(0);
        privateReqBean.setBranch("0020001444");
        privateReqBean.setQryBegDate("2024-10-01");
        privateReqBean.setQryEndDate("2024-10-01");
        privateReqBean.setUserId("001");
        privateReqBean.setCcnblno("001");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServiceIbiomaemsexchangeserviceQryexchangeinfoResponseV1 response = client.execute(request);
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
