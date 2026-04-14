package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1;
import com.icbc.api.response.BiomTransportCashIbrnopayUpdtotalsysboxquotaverResponseV1;

public class BiomTransportCashIbrnopayUpdtotalsysboxquotaverV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1 request = new BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1();

        BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1.BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1Biz bizContent = new BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1.BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1Biz();

        BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1.PrivateReqBean();

        BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIbrnopayUpdtotalsysboxquotaverRequestV1.PublicReqBean();


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setTaskType("01001");
        privateReqBean.setTaskId("00200000022709301024");
        privateReqBean.setTaskSubId("00200000022709301024");
        privateReqBean.setSimpplify("1");
        privateReqBean.setBranchId("0020000002");
        privateReqBean.setBrno("2");
        privateReqBean.setType("1");
        privateReqBean.setUserId("020016998");
        privateReqBean.setUserName("陈丽民");
        privateReqBean.setAuthtlno("020000316");
        privateReqBean.setAuthtlnoName("网点通测试");
        privateReqBean.setCurr("001");
        privateReqBean.setZoneno("200");
        privateReqBean.setStatus(3);
        privateReqBean.setTransIncomplete("1");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIbrnopayUpdtotalsysboxquotaverResponseV1 response = client.execute(request);
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
