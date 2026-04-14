package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;

import com.icbc.api.request.MybankLoanInclufinPersloanOverduedtlqryRequestV1;
import com.icbc.api.request.MybankLoanInclufinPersloanOverduedtlqryRequestV1.MybankLoanInclufinPersloanOverduedtlqryRequestV1BizContent;
import com.icbc.api.response.MybankLoanInclufinPersloanOverduedtlqryResponseV1;

public class MybankLoanInclufinPersloanOverduedtlqryTestV1{
	//appid,privateKey,apigwPublicKey  根据实际的应用场景进行修攄1�7
		protected static final String APP_ID = "xxxxxxx";
		protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
		protected static final String PRI_KEY = "xxxxxxx";
		protected static final String URL = "https://xxxxxxx/api/mybank/loan/inclufin/persloan/overduedtlqry/V1";

	public static void main(String[] args){
		MybankLoanInclufinPersloanOverduedtlqryRequestV1 request = new MybankLoanInclufinPersloanOverduedtlqryRequestV1();
		MybankLoanInclufinPersloanOverduedtlqryResponseV1 response = new MybankLoanInclufinPersloanOverduedtlqryResponseV1();
		MybankLoanInclufinPersloanOverduedtlqryRequestV1BizContent bizContent = new MybankLoanInclufinPersloanOverduedtlqryRequestV1BizContent();
		
		//公共参数		
		bizContent.setSerialNo("12334455");
		bizContent.setAppNo("F-APIP");
		bizContent.setAreaCode("");
		bizContent.setEmployeeCode("");
		bizContent.setLanguage("zh_CN");
		bizContent.setTransNo("language");
		bizContent.setVer("2.0");
		
		//私有参数

		bizContent.setCooperUnit("DW0101045");
		bizContent.setApplyNo("123123123");
		bizContent.setEbankLoanApplyNo("123");
		bizContent.setLnno("123");
		bizContent.setRtnTimes("1");
		bizContent.setAppid("123");
		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);

		request.setServiceUrl(URL);
		request.setBizContent(bizContent);
		 try {
	            response = client.execute(request);
	            
	        } catch (IcbcApiException e) {
//	            System.out.println(e.getMessage());
	        }
	        if (response.isSuccess()) {
//	            System.out.println("success!");
	        } else {
//	            System.out.println("fail" + response.getReturnMsg());
	        }
//	        System.out.println(response);
		}
}
