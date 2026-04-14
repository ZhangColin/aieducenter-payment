package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskmoverQrytaskconRequestV1;
import com.icbc.api.response.BiomTransportCashItaskmoverQrytaskconResponseV1;

public class BiomTransportCashItaskmoverQrytaskconV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItaskmoverQrytaskconRequestV1 request = new BiomTransportCashItaskmoverQrytaskconRequestV1();

        BiomTransportCashItaskmoverQrytaskconRequestV1.BiomTransportCashItaskmoverQrytaskconRequestV1Biz bizContent = new BiomTransportCashItaskmoverQrytaskconRequestV1.BiomTransportCashItaskmoverQrytaskconRequestV1Biz();

        BiomTransportCashItaskmoverQrytaskconRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskmoverQrytaskconRequestV1.PrivateReqBean();

        BiomTransportCashItaskmoverQrytaskconRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskmoverQrytaskconRequestV1.PublicReqBean();


        privateReqBean.setTaskId("");
        privateReqBean.setBrno("");


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItaskmoverQrytaskconResponseV1 response = client.execute(request);
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
