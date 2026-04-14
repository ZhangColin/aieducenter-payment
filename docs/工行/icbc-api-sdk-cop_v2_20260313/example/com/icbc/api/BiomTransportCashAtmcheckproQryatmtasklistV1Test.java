package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashAtmcheckproQryatmtasklistRequestV1;
import com.icbc.api.response.BiomTransportCashAtmcheckproQryatmtasklistResponseV1;

public class BiomTransportCashAtmcheckproQryatmtasklistV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashAtmcheckproQryatmtasklistRequestV1 request = new BiomTransportCashAtmcheckproQryatmtasklistRequestV1();

        BiomTransportCashAtmcheckproQryatmtasklistRequestV1.BiomTransportCashAtmcheckproQryatmtasklistRequestV1Biz bizContent = new BiomTransportCashAtmcheckproQryatmtasklistRequestV1.BiomTransportCashAtmcheckproQryatmtasklistRequestV1Biz();

        BiomTransportCashAtmcheckproQryatmtasklistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashAtmcheckproQryatmtasklistRequestV1.PrivateReqBean();

        BiomTransportCashAtmcheckproQryatmtasklistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashAtmcheckproQryatmtasklistRequestV1.PublicReqBean();

        privateReqBean.setDate("2012-01-01");

        privateReqBean.setBrno("2");

        privateReqBean.setStatus("1");

        privateReqBean.setZoneno("200");


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
            BiomTransportCashAtmcheckproQryatmtasklistResponseV1 response = client.execute(request);
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
