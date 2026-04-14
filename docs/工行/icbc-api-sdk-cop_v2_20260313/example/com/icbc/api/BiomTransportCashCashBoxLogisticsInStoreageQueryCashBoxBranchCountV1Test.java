package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1;
import com.icbc.api.response.BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountResponseV1;


public class BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1 request = new BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1();

        BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1.BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1Biz bizContent = new BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1.BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1Biz();

        BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1.PrivateReqBean();

        BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountRequestV1.PublicReqBean();

        privateReqBean.setStatus("1");
        privateReqBean.setAllocDate("2022-08-01");
        privateReqBean.setReqtype("2");
       

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try{
        	BiomTransportCashCashBoxLogisticsInStoreageQueryCashBoxBranchCountResponseV1 response = client.execute(request);
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
