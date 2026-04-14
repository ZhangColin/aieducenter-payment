package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1;
import com.icbc.api.response.BiomTransportCashItaskcashmoverChkboxcashvaluteResponseV1;

import java.util.ArrayList;

public class BiomTransportCashItaskcashmoverChkboxcashvaluteV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1 request = new BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1();

        BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1.BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1Biz bizContent = new BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1.BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1Biz();

        BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1.PrivateReqBean();

        BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskcashmoverChkboxcashvaluteRequestV1.PublicReqBean();

        privateReqBean.setBrno("2");
        privateReqBean.setZoneno("200");
        privateReqBean.setAllAmount(1);
        privateReqBean.setBoxType("1");
        privateReqBean.setBranchId("11111");
        privateReqBean.setCurrType("001");
        privateReqBean.setFlag("1");
        privateReqBean.setInitType("1");
        privateReqBean.setInRole("");
        privateReqBean.setOtellerno("00020000");
        privateReqBean.setQutoList(new ArrayList<>());
        privateReqBean.setTaskId("1111");
        privateReqBean.setTaskType("02001");
        privateReqBean.setTellerName("leo");
        privateReqBean.setType("3");

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItaskcashmoverChkboxcashvaluteResponseV1 response = client.execute(request);
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
