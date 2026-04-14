package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1;
import com.icbc.api.response.BiomTransportCashItotaloutcashboxproCreateopencashboxrelationResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashItotaloutcashboxproCreateopencashboxrelationTestV1 {
    private static final String MY_PRIVATE_KEY = "****";

    private static final String APP_ID = "1234";

    private static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1 request = new BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1();

        BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1Biz bizContent = new BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1Biz();

        BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.PrivateReqBean();

        BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.PublicReqBean();

        BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.PrivateReqBean.Qrcode qrcode = new BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.PrivateReqBean.Qrcode();

        List<BiomTransportCashItotaloutcashboxproCreateopencashboxrelationRequestV1.PrivateReqBean.Qrcode> qrcodeList = new ArrayList<>();

        qrcode.setBoxtype("0");
        qrcode.setCashType("1");
        qrcode.setPiece("100000");
        qrcode.setQrCodeField("000001");
        qrcode.setValuta("0");

        qrcodeList.add(qrcode);

        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        privateReqBean.setAllocDate("2022-07-21");
        privateReqBean.setAticleId("11111");
        privateReqBean.setBrno("30005");
        privateReqBean.setCurrtype("1");
        privateReqBean.setDevid("00001111");
        privateReqBean.setQrcodeList(qrcodeList);
        privateReqBean.setTaskType("1");
        privateReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItotaloutcashboxproCreateopencashboxrelationResponseV1 response = client.execute(request);
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
