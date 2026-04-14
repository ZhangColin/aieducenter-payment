package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashAtmcheckproCashamountcheckRequestV1;
import com.icbc.api.response.BiomTransportCashAtmcheckproCashamountcheckResponseV1;

public class BiomTransportCashAtmcheckproCashamountcheckV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashAtmcheckproCashamountcheckRequestV1 request = new BiomTransportCashAtmcheckproCashamountcheckRequestV1();

        BiomTransportCashAtmcheckproCashamountcheckRequestV1.BiomTransportCashAtmcheckproCashamountcheckRequestV1Biz bizContent = new BiomTransportCashAtmcheckproCashamountcheckRequestV1.BiomTransportCashAtmcheckproCashamountcheckRequestV1Biz();

        BiomTransportCashAtmcheckproCashamountcheckRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashAtmcheckproCashamountcheckRequestV1.PrivateReqBean();

        BiomTransportCashAtmcheckproCashamountcheckRequestV1.PublicReqBean publicReqBean = new BiomTransportCashAtmcheckproCashamountcheckRequestV1.PublicReqBean();


        publicReqBean.setZoneno("200");
        publicReqBean.setChancode("5");
        publicReqBean.setOapp("F-X");
        publicReqBean.setOtellerno("0200045");

        privateReqBean.setAticle_id("11111");
        privateReqBean.setMoney_amount("1000000000");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashAtmcheckproCashamountcheckResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
