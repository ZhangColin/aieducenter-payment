package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskmoverQrydotasklistRequestV1;
import com.icbc.api.response.BiomTransportCashItaskmoverQrydotasklistResponseV1;

import java.util.ArrayList;

public class BiomTransportCashItaskmoverQrydotasklistV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItaskmoverQrydotasklistRequestV1 request = new BiomTransportCashItaskmoverQrydotasklistRequestV1();

        BiomTransportCashItaskmoverQrydotasklistRequestV1.BiomTransportCashItaskmoverQrydotasklistRequestV1Biz bizContent = new BiomTransportCashItaskmoverQrydotasklistRequestV1.BiomTransportCashItaskmoverQrydotasklistRequestV1Biz();

        BiomTransportCashItaskmoverQrydotasklistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskmoverQrydotasklistRequestV1.PrivateReqBean();

        BiomTransportCashItaskmoverQrydotasklistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskmoverQrydotasklistRequestV1.PublicReqBean();


        privateReqBean.setcStartDate("2020-02-02");
        privateReqBean.setcEndDate("2020-02-02");
        privateReqBean.setStruId("211");
        privateReqBean.setPageNum(1);
        privateReqBean.setPageSize(10);
        privateReqBean.setTaskId("1");
        privateReqBean.setTaskType("05001");
        privateReqBean.setTaskStatus("1");
        privateReqBean.setStartWorkDate("");
        privateReqBean.setEndWorkDate("");
        privateReqBean.setPostIdList(new ArrayList<>());
        privateReqBean.setAllocBrno("");
        privateReqBean.setDevid("");
        privateReqBean.setScanAticleId("");
        privateReqBean.setCassChannel("");
        privateReqBean.setCurr("");
        privateReqBean.setRoutId("");

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItaskmoverQrydotasklistResponseV1 response = client.execute(request);
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
