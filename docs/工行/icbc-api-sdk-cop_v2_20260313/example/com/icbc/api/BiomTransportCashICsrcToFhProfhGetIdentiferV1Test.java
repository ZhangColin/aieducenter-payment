package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1;

import com.icbc.api.response.BiomTransportCashICsrcToFhProfhGetIdentiferResponseV1;


public class BiomTransportCashICsrcToFhProfhGetIdentiferV1Test  {


    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1 request = new BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1();

        BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1.BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1Biz bizContent = new BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1.BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1Biz();

        BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1.PrivateReqBean();

        BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1.PublicReqBean publicReqBean = new BiomTransportCashICsrcToFhProfhGetIdentiferRequestV1.PublicReqBean();

        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("998");
        privateReqBean.setIdNum("2");
        privateReqBean.setMacno("2");

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
        	BiomTransportCashICsrcToFhProfhGetIdentiferResponseV1 response = client.execute(request);
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
