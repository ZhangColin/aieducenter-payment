package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinChainloanAccountspaystatusqryRequestV1;
import com.icbc.api.request.MybankLoanInclufinChainloanAccountspaystatusqryRequestV1.MybankLoanInclufinChainloanAccountspaystatusqryRequestV1Biz;
import com.icbc.api.response.MybankLoanInclufinChainloanAccountspaystatusqryResponseV1;

public class MybankLoanInclufinChainloanAccountspaystatusqryRequestV1Test {

	public static void main(String[] args) {
		try {
			String APP_ID = "xxxxxxx";
			String APIGW_PUBLIC_KEY = "xxxxx";
			String PRI_KEY = "xxx";
			String URL = "http://xxxxx/api/mybank/loan/inclufin/chainloan/accountspaystatusqry/V1";

			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY,
					APIGW_PUBLIC_KEY);

			MybankLoanInclufinChainloanAccountspaystatusqryRequestV1Biz bizContent = new MybankLoanInclufinChainloanAccountspaystatusqryRequestV1Biz();

			bizContent.setSerialNo("***");
			bizContent.setAppNo("***");
			bizContent.setLanguage("zh_CN");
			bizContent.setTransNo("IQueryOrderPayInfoCocoaService");
			bizContent.setAppid("***");
			bizContent.setPayBatchNo("***");
			bizContent.setSupplyChainNo("***");

			MybankLoanInclufinChainloanAccountspaystatusqryRequestV1 request = new MybankLoanInclufinChainloanAccountspaystatusqryRequestV1();
			request.setServiceUrl(URL);
			request.setBizContent(bizContent);
			MybankLoanInclufinChainloanAccountspaystatusqryResponseV1 response = client
					.execute(request);
			  
            if (response.isSuccess()) {
            	//业务处理
                
            } else {
            	//业务处理
            }

        } catch (Exception e) {
        	//异常处理
        }
	}
}
