package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskmoverQrytaskbusiconRequestV1;
import com.icbc.api.response.BiomTransportCashItaskmoverQrytaskbusiconResponseV1;

import java.util.ArrayList;

public class BiomTransportCashItaskmoverQrytaskbusiconV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItaskmoverQrytaskbusiconRequestV1 request = new BiomTransportCashItaskmoverQrytaskbusiconRequestV1();

        BiomTransportCashItaskmoverQrytaskbusiconRequestV1.BiomTransportCashItaskmoverQrytaskbusiconRequestV1Biz bizContent = new BiomTransportCashItaskmoverQrytaskbusiconRequestV1.BiomTransportCashItaskmoverQrytaskbusiconRequestV1Biz();

        BiomTransportCashItaskmoverQrytaskbusiconRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskmoverQrytaskbusiconRequestV1.PrivateReqBean();

        BiomTransportCashItaskmoverQrytaskbusiconRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskmoverQrytaskbusiconRequestV1.PublicReqBean();


        privateReqBean.setTaskId("1111");
        privateReqBean.setTaskType("02001");
        privateReqBean.setTaskSubId("111111111");
        privateReqBean.setStatus("2");

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItaskmoverQrytaskbusiconResponseV1 response = client.execute(request);
            if(response.getReturnCode() == 0){
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            }else{
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        }catch(IcbcApiException e){
            e.printStackTrace();
        }
    }
}
