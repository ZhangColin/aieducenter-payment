package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;

import com.icbc.api.request.MybankLoanInclufinPersloanQuickloanphoneverifyRequestV1;
import com.icbc.api.request.MybankLoanInclufinPersloanQuickloanphoneverifyRequestV1.MybankLoanInclufinPersloanQuickloanphoneverifyRequestV1BizContent;
import com.icbc.api.response.MybankLoanInclufinPersloanQuickloanphoneverifyResponseV1;

public class MybankLoanInclufinPersloanQuickloanphoneverifyTestV1{
	//appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
		protected static final String APP_ID = "xxxxxxx";
		protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
		protected static final String PRI_KEY = "xxxxxxx";
		protected static final String URL = "https://xxxxxxx/api/mybank/loan/inclufin/persloan/quickloanphoneverify/V1";

	public static void main(String[] args){
		MybankLoanInclufinPersloanQuickloanphoneverifyRequestV1 request = new MybankLoanInclufinPersloanQuickloanphoneverifyRequestV1();
		MybankLoanInclufinPersloanQuickloanphoneverifyResponseV1 response = new MybankLoanInclufinPersloanQuickloanphoneverifyResponseV1();
		MybankLoanInclufinPersloanQuickloanphoneverifyRequestV1BizContent bizContent = new MybankLoanInclufinPersloanQuickloanphoneverifyRequestV1BizContent();
		
		//公共参数		
		bizContent.setSerialNo("12334455");
		bizContent.setAppNo("F-APIP");
		bizContent.setAreaCode("");
		bizContent.setEmployeeCode("");
		bizContent.setLanguage("zh_CN");
		bizContent.setTransNo("ILoanMessageCheckCocoaService");
		bizContent.setVer("1.0");
		
		//私有参数
		bizContent.setTransDate("20200423");
		bizContent.setPhoneNo("11111111111");
		bizContent.setVerifyCode("826719");
		bizContent.setType("01");
		bizContent.setCustID("1231231231");
		bizContent.setModelID("ZH1107");
		bizContent.setRemark1("01");
		bizContent.setRemark2("01");
		bizContent.setCooperUnit("DW0101045");
		bizContent.setBusiCode("cWPHQlKDebDOXHqhyXGryHadJXTeyGSrhmNoSAGZ");
		
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
