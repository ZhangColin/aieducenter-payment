package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskmoverQrydonetasklistRequestV1;
import com.icbc.api.response.BiomTransportCashItaskmoverQrydonetasklistResponseV1;

public class BiomTransportCashItaskmoverQrydonetasklistV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashItaskmoverQrydonetasklistRequestV1 request = new BiomTransportCashItaskmoverQrydonetasklistRequestV1();

        BiomTransportCashItaskmoverQrydonetasklistRequestV1.BiomTransportCashItaskmoverQrydonetasklistRequestV1Biz bizContent = new BiomTransportCashItaskmoverQrydonetasklistRequestV1.BiomTransportCashItaskmoverQrydonetasklistRequestV1Biz();

        BiomTransportCashItaskmoverQrydonetasklistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskmoverQrydonetasklistRequestV1.PrivateReqBean();

        BiomTransportCashItaskmoverQrydonetasklistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskmoverQrydonetasklistRequestV1.PublicReqBean();


        privateReqBean.setPageNum(1);
        privateReqBean.setPageSize(10);
        privateReqBean.setTaskId("");
        privateReqBean.setTaskType("");
        privateReqBean.setTaskStatus("");
        privateReqBean.setUserId("");
        privateReqBean.setAllocBrno("");
        privateReqBean.setDevid("");
        privateReqBean.setCassChannel("");
        privateReqBean.setCurr("");
        privateReqBean.setRoutId("");
        privateReqBean.setStruId("");
        privateReqBean.setStartWorkDate("2020-02-02");
        privateReqBean.setEndWorkDate("2020-02-02");


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashItaskmoverQrydonetasklistResponseV1 response = client.execute(request);
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
