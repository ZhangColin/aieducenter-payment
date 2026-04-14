package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1;
import com.icbc.api.response.BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyResponseV1;

public class BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1 request = new BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1();

        BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1.BiomTransportCashCentreStorageSubmitCentrePutInStorageSimpplifyRequestV1Biz bizContent = new BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1.BiomTransportCashCentreStorageSubmitCentrePutInStorageSimpplifyRequestV1Biz();

        BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1.PrivateReqBean();

        BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setTaskId("00200009982709304896");
        privateReqBean.setDistriTaskId("00200009982709304896");
        privateReqBean.setSimpplify("1");
        privateReqBean.setUserId("555017484");
        privateReqBean.setUserName("姓名");
        privateReqBean.setBranchId("0020001242");
        privateReqBean.setBrno("998");
        privateReqBean.setoTaskStatus("4");
        privateReqBean.setnTaskStatus("5");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashCentrestorageSubmitcentreputinstoragesimpplifyResponseV1 response = client.execute(request);
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
