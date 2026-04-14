package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1;
import com.icbc.api.response.BiomTransportCashCenterinoutstoreOutstoreconfirmResponseV1;

import java.util.ArrayList;

public class BiomTransportCashCenterinoutstoreOutstoreconfirmV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1 request = new BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1();

        BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1.BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1Biz bizContent = new BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1.BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1Biz();

        BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1.PrivateReqBean();

        BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCenterinoutstoreOutstoreconfirmRequestV1.PublicReqBean();


        privateReqBean.setTaskIdList(new ArrayList<>());
        privateReqBean.setEscortMode("1");
        privateReqBean.setBranchId("00102368");
        privateReqBean.setOutUser("0001569");
        privateReqBean.setOutUserName("ellen");
        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("e");
        privateReqBean.setOperPern("0125369");
        privateReqBean.setOperPernName("lilly");
        privateReqBean.setEscortPern1("0125669");
        privateReqBean.setEscortName1("ellen2");
        privateReqBean.setEscortPern2("012536");
        privateReqBean.setEscortName2("ellen3");
        privateReqBean.setOperBranchName("center");

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashCenterinoutstoreOutstoreconfirmResponseV1 response = client.execute(request);
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
