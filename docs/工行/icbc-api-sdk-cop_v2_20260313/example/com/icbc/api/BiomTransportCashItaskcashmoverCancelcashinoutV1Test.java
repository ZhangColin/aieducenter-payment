package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskcashmoverCancelcashinoutRequestV1;
import com.icbc.api.response.BiomTransportCashItaskcashmoverCancelcashinoutResponseV1;

public class BiomTransportCashItaskcashmoverCancelcashinoutV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashItaskcashmoverCancelcashinoutRequestV1 request = new BiomTransportCashItaskcashmoverCancelcashinoutRequestV1();

        BiomTransportCashItaskcashmoverCancelcashinoutRequestV1.BiomTransportCashItaskcashmoverCancelcashinoutRequestV1Biz bizContent = new BiomTransportCashItaskcashmoverCancelcashinoutRequestV1.BiomTransportCashItaskcashmoverCancelcashinoutRequestV1Biz();

        BiomTransportCashItaskcashmoverCancelcashinoutRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskcashmoverCancelcashinoutRequestV1.PrivateReqBean();

        BiomTransportCashItaskcashmoverCancelcashinoutRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskcashmoverCancelcashinoutRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setZoneno("200");
        privateReqBean.setOtellerno("465475424");
        privateReqBean.setBrno("998");
        privateReqBean.setBranchId("0020001242");
        privateReqBean.setTellerName("姓名");
        privateReqBean.setTaskType("03004");
        privateReqBean.setTaskId("00200009982709304896");
        privateReqBean.setStatus(2);

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashItaskcashmoverCancelcashinoutResponseV1 response = client.execute(request);
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
