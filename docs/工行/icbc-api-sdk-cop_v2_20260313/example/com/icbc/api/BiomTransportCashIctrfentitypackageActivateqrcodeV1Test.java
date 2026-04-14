package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1;
import com.icbc.api.response.BiomTransportCashIctrfentitypackageActivateqrcodeResponseV1;

public class BiomTransportCashIctrfentitypackageActivateqrcodeV1Test {


    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1 request = new BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1();

        BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1.BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1Biz bizContent = new BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1.BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1Biz();

        BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1.PrivateReqBean();

        BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIctrfentitypackageActivateqrcodeRequestV1.PublicReqBean();

        privateReqBean.setAmount("1000000");
        privateReqBean.setBranchId("0020001242");
        privateReqBean.setCashType("0");
        privateReqBean.setCategory("3");
        privateReqBean.setCurr("1");
        privateReqBean.setQrcode("4793802719239");
        privateReqBean.setTagType("1");
        privateReqBean.setTicketNum("100");
        privateReqBean.setTickets("10000");

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashIctrfentitypackageActivateqrcodeResponseV1 response = client.execute(request);
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
