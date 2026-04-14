package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request;
import com.icbc.api.response.BiomTransportCashItotaloutcashboxproQrysumreqcountV1Response;

public class BiomTransportCashItotaloutcashboxproQrysumreqcountV1Test {


    public static void main(String[] args) {
        String MY_PRIVATE_KEY = "****";

        String APP_ID = "1234";

        String APIGW_PUBLIC_KEY = "****";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request request = new BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request();

        BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request.BiomTransportCashItotaloutcashboxproQrysumreqcountV1RequestBiz bizContent = new BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request.BiomTransportCashItotaloutcashboxproQrysumreqcountV1RequestBiz();

        BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request.PrivateReqBean privateReqBean = new BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request.PrivateReqBean();

        BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request.PublicReqBean publicReqBean = new BiomTransportCashItotaloutcashboxproQrysumreqcountV1Request.PublicReqBean();


        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");


        privateReqBean.setAllocDate("2022-07-07");
        privateReqBean.setBrno("998");



        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItotaloutcashboxproQrysumreqcountV1Response response = client.execute(request);
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
