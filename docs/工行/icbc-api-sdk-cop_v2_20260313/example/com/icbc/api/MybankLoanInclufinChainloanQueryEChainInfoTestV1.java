package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinChainloanQueryEChainInfoRequestV1;
import com.icbc.api.request.MybankLoanInclufinChainloanQueryEChainInfoRequestV1.MybankLoanInclufinChainloanQueryEChainInfoV1Biz;
import com.icbc.api.response.MybankLoanInclufinChainloanQueryEChainInfoResponseV1;

public class MybankLoanInclufinChainloanQueryEChainInfoTestV1 {
	
	//APP_ID,APIGW_PUBLIC_KEY,PRI_KEY,URL  根据实际的应用场景进行修改
		protected static final String APP_ID = "xxxxxxx";
		protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
		protected static final String PRI_KEY = "xxxxxxx";
		protected static final String URL = "http://xxxxxxx/api/mybank/loan/inclufin/chainloan/queryechaininfo/V1";
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			MybankLoanInclufinChainloanQueryEChainInfoRequestV1 request = new MybankLoanInclufinChainloanQueryEChainInfoRequestV1();
			MybankLoanInclufinChainloanQueryEChainInfoResponseV1 response = new MybankLoanInclufinChainloanQueryEChainInfoResponseV1();
			MybankLoanInclufinChainloanQueryEChainInfoV1Biz bizContent = new MybankLoanInclufinChainloanQueryEChainInfoV1Biz();
			
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
			bizContent.put("appid", APP_ID);
			bizContent.put("type", "01");
			bizContent.put("cooperUnit", "");
			bizContent.put("modelID", "");
			bizContent.put("supChainNo", "123123123123");
			bizContent.put("coreName", "123");
			bizContent.put("coreCertType", "");
			bizContent.put("coreCertCode", "");
			bizContent.put("cusCertType", "0");
			bizContent.put("cusCertCode", "000111101101");
			bizContent.put("cusName", "1111000119");
			bizContent.put("orderNo", "0001");
			bizContent.put("applyNo", "");
			bizContent.put("serachType", "01");

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
