package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIbrnopayAddpayRequestV1;
import com.icbc.api.response.BiomTransportCashIbrnopayAddpayResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashIbrnopayAddpayV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIbrnopayAddpayRequestV1 request = new BiomTransportCashIbrnopayAddpayRequestV1();

        BiomTransportCashIbrnopayAddpayRequestV1.BiomTransportCashIbrnopayAddpayRequestV1Biz bizContent = new BiomTransportCashIbrnopayAddpayRequestV1.BiomTransportCashIbrnopayAddpayRequestV1Biz();

        BiomTransportCashIbrnopayAddpayRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIbrnopayAddpayRequestV1.PrivateReqBean();

        BiomTransportCashIbrnopayAddpayRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIbrnopayAddpayRequestV1.PublicReqBean();

        List<BiomTransportCashIbrnopayAddpayRequestV1.payMap> table01 = new ArrayList<>();
        BiomTransportCashIbrnopayAddpayRequestV1.payMap payMap = new BiomTransportCashIbrnopayAddpayRequestV1.payMap();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("998");
        privateReqBean.setAuthtlnoName("肖文全");
        privateReqBean.setAuthtlno("020000026");
        privateReqBean.setTable01(table01);

        table01.add(payMap);
        payMap.setValuta("10000");
        payMap.setNotes("");
        payMap.setDicCode("10000");
        payMap.setDicName("壹佰元");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIbrnopayAddpayResponseV1 response = client.execute(request);
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
