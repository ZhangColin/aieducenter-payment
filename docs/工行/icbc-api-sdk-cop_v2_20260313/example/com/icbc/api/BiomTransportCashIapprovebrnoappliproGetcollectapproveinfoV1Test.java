package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1;
import com.icbc.api.response.BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoResponseV1;

public class BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1 request = new BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1();

        BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1.BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1Biz bizContent = new BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1.BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1Biz();

        BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1.PrivateReqBean();

        BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoRequestV1.PublicReqBean();


        publicReqBean.setZoneno("200");

        privateReqBean.setCentZoneNo("200");
        privateReqBean.setCentBrno("998");
        privateReqBean.setCurrType("001");
        privateReqBean.setReqIds("00200000022710010681");
        privateReqBean.setIsQueryStore("0");
        privateReqBean.setQueryType("0");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIapprovebrnoappliproGetcollectapproveinfoResponseV1 response = client.execute(request);
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
