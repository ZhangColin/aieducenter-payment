package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request;
import com.icbc.api.response.BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Response;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Test {


    public static void main(String[] args) {
        String MY_PRIVATE_KEY = "****";

        String APP_ID = "1234";

        String APIGW_PUBLIC_KEY = "****";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request request = new BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request();

        BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request.BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1RequestBiz bizContent = new BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request.BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1RequestBiz();

        BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request.PrivateReqBean privateReqBean = new BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request.PrivateReqBean();

        BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request.PublicReqBean publicReqBean = new BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request.PublicReqBean();

        BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request.PrivateReqBean.qrcode qrcodeMap = new BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Request.PrivateReqBean.qrcode();

        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");


        qrcodeMap.setQrcode("010000001");
        qrcodeMap.setCurrtype(1);
        qrcodeMap.setBoxtype(1);
        qrcodeMap.setPiece(1);
        qrcodeMap.setValuta(1);
        List qrcodeList = new ArrayList();
        qrcodeList.add(qrcodeMap);
        privateReqBean.setAllocDate("2022-07-07");
        privateReqBean.setBrno("998");
        privateReqBean.setAticleId("0020000202");
        privateReqBean.setTaskType(1);
        privateReqBean.setDevid("1");
        privateReqBean.setZoneno(200);
        privateReqBean.setCurrtype(1);
        privateReqBean.setQrcodeList(qrcodeList);


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItotaloutcashboxproCreatecashboxrelationV1Response response = client.execute(request);
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
