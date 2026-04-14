package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIroutehandleproQryroutelistRequestV1;
import com.icbc.api.response.BiomTransportCashIroutehandleproQryroutelistResponseV1;

public class BiomTransportCashIroutehandleproQryroutelistV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashIroutehandleproQryroutelistRequestV1 request = new BiomTransportCashIroutehandleproQryroutelistRequestV1();

        BiomTransportCashIroutehandleproQryroutelistRequestV1.BiomTransportCashIroutehandleproQryroutelistRequestV1Biz bizContent = new BiomTransportCashIroutehandleproQryroutelistRequestV1.BiomTransportCashIroutehandleproQryroutelistRequestV1Biz();

        BiomTransportCashIroutehandleproQryroutelistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIroutehandleproQryroutelistRequestV1.PrivateReqBean();

        BiomTransportCashIroutehandleproQryroutelistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIroutehandleproQryroutelistRequestV1.PublicReqBean();


        privateReqBean.setBranchId("");
        privateReqBean.setBrno("");
        privateReqBean.setRouteNo("");
        privateReqBean.setOtellerno("");
        privateReqBean.setZoneno("");
        privateReqBean.setEscortPern("");
        privateReqBean.setLocSeq("");
        privateReqBean.setPageIndex(1);
        privateReqBean.setPageSize(1);

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashIroutehandleproQryroutelistResponseV1 response = client.execute(request);
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
