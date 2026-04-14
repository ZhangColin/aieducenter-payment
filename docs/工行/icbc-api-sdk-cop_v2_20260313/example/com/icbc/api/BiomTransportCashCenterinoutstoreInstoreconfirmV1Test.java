package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1;
import com.icbc.api.response.BiomTransportCashCenterinoutstoreInstoreconfirmResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashCenterinoutstoreInstoreconfirmV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1 request = new BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1();

        BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1Biz bizContent = new BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1Biz();

        BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.PrivateReqBean();

        BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.PublicReqBean();


        privateReqBean.setDoneList(new ArrayList<>(){{add(new BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.doneList());}});
        privateReqBean.setUndoneList(new ArrayList<>(){{add(new BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.undoneList());}});
        privateReqBean.setExtraList(new ArrayList<>(){{add(new BiomTransportCashCenterinoutstoreInstoreconfirmRequestV1.extraList());}});
        privateReqBean.setEscortPern1("11");
        privateReqBean.setEscortPernName1("22");
        privateReqBean.setEscortPern2("11");
        privateReqBean.setEscortPernName2("22");
        privateReqBean.setBranchId("111");
        privateReqBean.setRouteNo("2222");
        privateReqBean.setRouteName("1111");
        privateReqBean.setOperPern("11");
        privateReqBean.setOperPernName("111");
        privateReqBean.setBrno("111");
        privateReqBean.setZoneno("11");
        privateReqBean.setForced("1");

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashCenterinoutstoreInstoreconfirmResponseV1 response = client.execute(request);
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
