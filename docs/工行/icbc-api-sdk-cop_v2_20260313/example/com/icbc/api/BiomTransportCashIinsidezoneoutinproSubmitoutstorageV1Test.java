package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request;
import com.icbc.api.response.BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Response;

import java.util.Collections;

public class BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Test {


    public static void main(String[] args) {
        String MY_PRIVATE_KEY = "****";

        String APP_ID = "1234";

        String APIGW_PUBLIC_KEY = "****";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request request = new BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request();

        BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request.BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1RequestBiz bizContent = new BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request.BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1RequestBiz();

        BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request.PrivateReqBean privateReqBean = new BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request.PrivateReqBean();

        BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request.PublicReqBean publicReqBean = new BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Request.PublicReqBean();


        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        privateReqBean.setLogId("0020000202");
        privateReqBean.setInputType("1");
        privateReqBean.setCategory("1");
        privateReqBean.setStatus("1");
        privateReqBean.setAuthUserId("1");
        privateReqBean.setAuthUserIdName("1");
        privateReqBean.setAticleList(Collections.singletonList(""));


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashIinsidezoneoutinproSubmitoutstorageV1Response response = client.execute(request);
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
