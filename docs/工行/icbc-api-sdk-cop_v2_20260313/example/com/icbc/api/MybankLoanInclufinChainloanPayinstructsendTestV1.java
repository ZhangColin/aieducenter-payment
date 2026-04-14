package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinChainloanPayinstructsendRequestV1;
import com.icbc.api.request.MybankLoanInclufinChainloanPayinstructsendRequestV1.MybankLoanInclufinChainloanPayinstructsendV1Biz;
import com.icbc.api.response.MybankLoanInclufinChainloanPayinstructsendResponseV1;

public class MybankLoanInclufinChainloanPayinstructsendTestV1 {
	
	//APP_ID,APIGW_PUBLIC_KEY,PRI_KEY,URL  根据实际的应用场景进行修改
		protected static final String APP_ID = "xxxxxxx";
		protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
		protected static final String PRI_KEY = "xxxxxxx";
		protected static final String URL = "http://xxxxxxx/api/mybank/loan/inclufin/chainloan/payinstructsend/V1";
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			MybankLoanInclufinChainloanPayinstructsendRequestV1 request = new MybankLoanInclufinChainloanPayinstructsendRequestV1();
			MybankLoanInclufinChainloanPayinstructsendResponseV1 response = new MybankLoanInclufinChainloanPayinstructsendResponseV1();
			MybankLoanInclufinChainloanPayinstructsendV1Biz bizContent = new MybankLoanInclufinChainloanPayinstructsendV1Biz();
			
			//公共参数
			bizContent.put("serialNo", "12334455");
			bizContent.put("appNo", "F-GCMS");
			bizContent.put("areaCode", "");
			bizContent.put("employeeCode", "");
			bizContent.put("language", "zh_CN");
			bizContent.put("transNo", "IPayCoreCompanyCocoaService");
			bizContent.put("ver", "1.0");
			bizContent.put("turnPageFlag", "");
			bizContent.put("beginRow", "");
			bizContent.put("rowCount", "");
			//私有参数
			bizContent.put("type", "01");
			bizContent.put("appid", APP_ID);
			bizContent.put("cooperUnit", "WS3602073");
			bizContent.put("modelID", "123");
			bizContent.put("supChainNo", "123123123123");
			bizContent.put("payerName", "");
			bizContent.put("payerCertType", "");
			bizContent.put("peyerCertCode", "0");
			bizContent.put("payerAccountBank", "测试开户支行");
			bizContent.put("payerAccountNum", "000111101101");
			bizContent.put("orderNo", "1111000119");
			bizContent.put("currType", "001");
			bizContent.put("payAmount", "1110");
			bizContent.put("suppName", "测试供应商");
			bizContent.put("suppCertType", "0");
			bizContent.put("suppCertCode", "1222344");
			bizContent.put("suppAccountNum", "123464322345654");
			

			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2" ,PRI_KEY,"UTF-8","json", APIGW_PUBLIC_KEY,null,null,null,null);

			request.setServiceUrl(URL);
			request.setBizContent(bizContent);
			
			 try {
		            response = client.execute(request);
		        } catch (Exception e) {
		        	//System.out.println(e.getMessage());
		        }
		        if (response.isSuccess()) {
		        	//System.out.println(e.getMessage());
		        } else {
		        	//System.out.println(e.getMessage());
		        }
		    //  System.out.println(response);
		}

}
