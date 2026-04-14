package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIswitchproCtrfswitchRequestV1;
import com.icbc.api.response.BiomTransportCashIswitchproCtrfswitchResponseV1;

public class BiomTransportCashIswitchproCtrfswitchV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashIswitchproCtrfswitchRequestV1 request = new BiomTransportCashIswitchproCtrfswitchRequestV1();

        BiomTransportCashIswitchproCtrfswitchRequestV1.BiomTransportCashIswitchproCtrfswitchRequestV1Biz bizContent = new BiomTransportCashIswitchproCtrfswitchRequestV1.BiomTransportCashIswitchproCtrfswitchRequestV1Biz();

        BiomTransportCashIswitchproCtrfswitchRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIswitchproCtrfswitchRequestV1.PrivateReqBean();

        BiomTransportCashIswitchproCtrfswitchRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIswitchproCtrfswitchRequestV1.PublicReqBean();

        privateReqBean.setSTATUS("1");
        privateReqBean.setBRANCH_ID("0020001242");
        privateReqBean.setCATEGORY(1);
        privateReqBean.setDISTRI_DATE("2022-09-09");
        privateReqBean.setDISTRI_PERSION(1);
        privateReqBean.setDISTRI_TYPE(1);
        privateReqBean.setTASK_ID("0000000001");
        privateReqBean.setTYPE(1);

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");
        publicReqBean.setOtellerno_name("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashIswitchproCtrfswitchResponseV1 response = client.execute(request);
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
