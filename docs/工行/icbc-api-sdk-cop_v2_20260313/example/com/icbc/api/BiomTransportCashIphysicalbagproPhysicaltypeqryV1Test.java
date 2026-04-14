package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1;
import com.icbc.api.response.BiomTransportCashIphysicalbagproPhysicaltypeqryResponseV1;

public class BiomTransportCashIphysicalbagproPhysicaltypeqryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1 request = new BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1();

        BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1.BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1Biz bizContent = new BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1.BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1Biz();

        BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1.PrivateReqBean();

        BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIphysicalbagproPhysicaltypeqryRequestV1.PublicReqBean();

        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("001192011");
        publicReqBean.setZoneno("200");

        privateReqBean.setTellerno("001192011");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try{
            BiomTransportCashIphysicalbagproPhysicaltypeqryResponseV1 response = client.execute(request);
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
