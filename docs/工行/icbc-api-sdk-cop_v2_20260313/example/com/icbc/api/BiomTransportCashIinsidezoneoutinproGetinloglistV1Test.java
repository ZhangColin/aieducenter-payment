package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIinsidezoneoutinproGetinloglistV1Request;
import com.icbc.api.response.BiomTransportCashIinsidezoneoutinproGetinloglistV1Response;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashIinsidezoneoutinproGetinloglistV1Test {


    public static void main(String[] args) {
        String MY_PRIVATE_KEY = "****";

        String APP_ID = "1234";

        String APIGW_PUBLIC_KEY = "****";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashIinsidezoneoutinproGetinloglistV1Request request = new BiomTransportCashIinsidezoneoutinproGetinloglistV1Request();

        BiomTransportCashIinsidezoneoutinproGetinloglistV1Request.BiomTransportCashIinsidezoneoutinproGetinloglistV1RequestBiz bizContent = new BiomTransportCashIinsidezoneoutinproGetinloglistV1Request.BiomTransportCashIinsidezoneoutinproGetinloglistV1RequestBiz();

        BiomTransportCashIinsidezoneoutinproGetinloglistV1Request.PrivateReqBean privateReqBean = new BiomTransportCashIinsidezoneoutinproGetinloglistV1Request.PrivateReqBean();

        BiomTransportCashIinsidezoneoutinproGetinloglistV1Request.PublicReqBean publicReqBean = new BiomTransportCashIinsidezoneoutinproGetinloglistV1Request.PublicReqBean();


        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        privateReqBean.setBranchId("0020000202");
        privateReqBean.setType("1");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashIinsidezoneoutinproGetinloglistV1Response response = client.execute(request);
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
