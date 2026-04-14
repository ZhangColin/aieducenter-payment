package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinChainLoanQueryrepaylistRequestV1;
import com.icbc.api.request.MybankLoanInclufinChainLoanQueryrepaylistRequestV1.MybankLoanInclufinChainLoanQueryrepaylistRequestV1Biz;
import com.icbc.api.response.MybankLoanInclufinChainLoanQueryrepaylistResponseV1;


public class MybankLoanInclufinChainLoanQueryrepaylistV1Test {

	public static void main(String[] args) {
		try {
			 String APP_ID = "xxxxxxx";
			 String APIGW_PUBLIC_KEY = "xxxxx";
			 String PRI_KEY =   "xxx";               
			 String URL = "http://xxxxx/api/mybank/loan/inclufin/chainloan/queryrepaylist/V1";
			
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        

        MybankLoanInclufinChainLoanQueryrepaylistRequestV1Biz bizContent =
                    new MybankLoanInclufinChainLoanQueryrepaylistRequestV1Biz();
        	
        	
        	bizContent.setSerialNo("43892367846328");
        	bizContent.setAppNo("F-GCMS");
        	bizContent.setLanguage("zh_CN");
        	bizContent.setTransNo("SupplyChainFinanceCocoaService");
        	bizContent.setVer("1.0");
        	
        	bizContent.setSupplyChainCode("GY-2015000000788");
        	bizContent.setApplyCode("0000000000000022131");
        	bizContent.setCustName("小曦");
        	bizContent.setCertCode("JA0035");
        	bizContent.setCertType("1");
        	bizContent.setOrderCode("订单_AAAAA002");
        	bizContent.setQueryMode("1");

        	
     
        	MybankLoanInclufinChainLoanQueryrepaylistRequestV1 request =
                    new MybankLoanInclufinChainLoanQueryrepaylistRequestV1();
            request.setServiceUrl(URL);
            request.setBizContent(bizContent);
            MybankLoanInclufinChainLoanQueryrepaylistResponseV1 response = client.execute(request);
          
            if (response.isSuccess()) {
            	//System.out.println(response);
            	
                
            } else {
            	//System.out.println("");
            	
            }

        } catch (Exception e) {
        	//System.out.println(e);
        	
        }
	}
}
