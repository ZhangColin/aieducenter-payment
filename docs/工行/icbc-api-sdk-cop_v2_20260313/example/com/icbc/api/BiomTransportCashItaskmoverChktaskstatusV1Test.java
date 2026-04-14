package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskmoverChktaskstatusRequestV1;
import com.icbc.api.response.BiomTransportCashItaskmoverChktaskstatusResponseV1;

import java.util.ArrayList;

public class BiomTransportCashItaskmoverChktaskstatusV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashItaskmoverChktaskstatusRequestV1 request = new BiomTransportCashItaskmoverChktaskstatusRequestV1();

        BiomTransportCashItaskmoverChktaskstatusRequestV1.BiomTransportCashItaskmoverChktaskstatusRequestV1Biz bizContent = new BiomTransportCashItaskmoverChktaskstatusRequestV1.BiomTransportCashItaskmoverChktaskstatusRequestV1Biz();

        BiomTransportCashItaskmoverChktaskstatusRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskmoverChktaskstatusRequestV1.PrivateReqBean();

        BiomTransportCashItaskmoverChktaskstatusRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskmoverChktaskstatusRequestV1.PublicReqBean();


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setStatus(2);
        privateReqBean.setTaskId("001");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashItaskmoverChktaskstatusResponseV1 response = client.execute(request);
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
