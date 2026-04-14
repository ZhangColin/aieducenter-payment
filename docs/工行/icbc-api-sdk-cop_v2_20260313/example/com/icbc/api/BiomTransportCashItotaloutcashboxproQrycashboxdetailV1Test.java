package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request;
import com.icbc.api.response.BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Response;

public class BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Test {


    public static void main(String[] args) {
        String MY_PRIVATE_KEY = "****";

        String APP_ID = "1234";

        String APIGW_PUBLIC_KEY = "****";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request request = new BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request();

        BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request.BiomTransportCashItotaloutcashboxproQrycashboxdetailV1RequestBiz bizContent = new BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request.BiomTransportCashItotaloutcashboxproQrycashboxdetailV1RequestBiz();

        BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request.PrivateReqBean privateReqBean = new BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request.PrivateReqBean();

        BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request.PublicReqBean publicReqBean = new BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Request.PublicReqBean();


        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");


        privateReqBean.setAllocDate("2022-07-07");
        privateReqBean.setBrno("998");
        privateReqBean.setAticleId("0020000202");
        privateReqBean.setTaskType(1);
        privateReqBean.setDevid("1");
        privateReqBean.setCashType(1);
        privateReqBean.setQrcode("1");
        privateReqBean.setBoxtype(1);
        privateReqBean.setCurrtype(1);
        privateReqBean.setValuta(1);
        privateReqBean.setPiece(1);


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItotaloutcashboxproQrycashboxdetailV1Response response = client.execute(request);
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
