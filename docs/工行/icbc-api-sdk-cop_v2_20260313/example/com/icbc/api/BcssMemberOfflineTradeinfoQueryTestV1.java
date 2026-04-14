package com.icbc.api;

import java.util.ArrayList;

import com.icbc.api.request.BcssMemberOfflineTradeinfoQueryRequestV1;
import com.icbc.api.request.BcssMemberOfflineTradeinfoQueryRequestV1.BcssMemberOfflineTradeInfoQueryRequestBizV1;
import com.icbc.api.response.BcssMemberOfflineTradeinfoQueryResponseV1;

public class BcssMemberOfflineTradeinfoQueryTestV1 {

	 // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "";
   
    // APP_ID
    protected static final String APP_ID = "";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
    	BcssMemberOfflineTradeinfoQueryTestV1 a = new BcssMemberOfflineTradeinfoQueryTestV1();
        a.testCop();
    }

    public static void testCop() throws IcbcApiException {
    	BcssMemberOfflineTradeinfoQueryRequestV1 request = new BcssMemberOfflineTradeinfoQueryRequestV1();
        request.setServiceUrl("http://**.**.**.**:**/api/bcss/member/offline/tradeinfo/query/V1");
        BcssMemberOfflineTradeInfoQueryRequestBizV1 bizContent = new BcssMemberOfflineTradeInfoQueryRequestBizV1();
        
        bizContent.setCorpId("0000000273");
        bizContent.setTimeStamp("2019-06-05 13:47:49");
        bizContent.setOperType("11");
        bizContent.setClientTransNo("02020190422102628165");
        bizContent.setStartDate("2020-02-02");
        bizContent.setEndDate("2020-0-02");
        bizContent.setStartNum("1");
        bizContent.setEndNum("10");
       
       
       
        ArrayList<BcssMemberOfflineTradeinfoQueryRequestV1.ProductInfoDto> busiList = new ArrayList<BcssMemberOfflineTradeinfoQueryRequestV1.ProductInfoDto>();
        BcssMemberOfflineTradeinfoQueryRequestV1.ProductInfoDto busi = new BcssMemberOfflineTradeinfoQueryRequestV1.ProductInfoDto();
        busi.setBusiNo("201904230000001149");
        busi.setMerNo("201904230000001149");
        busi.setDocType("1");
      
        busiList.add(busi);
       
        bizContent.setBusiList(busiList);
        
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemberOfflineTradeinfoQueryResponseV1 response = client.execute(request);
        //System.out.println(JSONObject.toJSONString(response));
    }
}
