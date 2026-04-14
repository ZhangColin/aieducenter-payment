package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1;
import com.icbc.api.response.BiomTransportCashCentrestorageQrypendingdistrigoodsResponseV1;

public class BiomTransportCashCentrestorageQrypendingdistrigoodsV1Test {


    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1 request = new BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1();

        BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1.BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1Biz bizContent = new BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1.BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1Biz();

        BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1.PrivateReqBean();

        BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCentrestorageQrypendingdistrigoodsRequestV1.PublicReqBean();

        privateReqBean.setUserId("1");
        privateReqBean.setBranch_Id("0020001242");

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashCentrestorageQrypendingdistrigoodsResponseV1 response = client.execute(request);
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
