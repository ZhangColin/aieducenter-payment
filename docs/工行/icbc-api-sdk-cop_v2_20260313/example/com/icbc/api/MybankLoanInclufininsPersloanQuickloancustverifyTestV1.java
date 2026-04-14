/**
 * 
 */
package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufininsPersloanQuickloancustverifyRequestV1;
import com.icbc.api.request.MybankLoanInclufininsPersloanQuickloancustverifyRequestV1.MybankLoanInclufininsPersloanQuickloancustverifyV1Biz;
import com.icbc.api.response.MybankLoanInclufininsPersloanQuickloancustverifyResponseV1;

/**
 * @author kfzx-siad
 *
 */
public class MybankLoanInclufininsPersloanQuickloancustverifyTestV1 {

	//APP_ID,APIGW_PUBLIC_KEY,PRI_KEY,URL  根据实际的应用场景进行修改
	protected static final String APP_ID = "xxxxxxx";
	protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
	protected static final String PRI_KEY = "xxxxxxx";
	protected static final String URL = "http://xxxxxxx/api/mybank/loan/inclufinins/persloan/quickloancustverify/V1";

	/**
	 * @param kfzx-siad
	 */
	public static void main(String[] args) {
		MybankLoanInclufininsPersloanQuickloancustverifyRequestV1 request = new MybankLoanInclufininsPersloanQuickloancustverifyRequestV1();
		MybankLoanInclufininsPersloanQuickloancustverifyResponseV1 response = new MybankLoanInclufininsPersloanQuickloancustverifyResponseV1();
		MybankLoanInclufininsPersloanQuickloancustverifyV1Biz bizContent = new MybankLoanInclufininsPersloanQuickloancustverifyV1Biz();
		
		//公共参数
		bizContent.put("serialNo", "12334455");
		bizContent.put("appNo", "F-GCMS");
		bizContent.put("areaCode", "");
		bizContent.put("employeeCode", "");
		bizContent.put("language", "zh_CN");
		bizContent.put("transNo", "IPerLoanCustIDInfoBranchCocoaService");
		bizContent.put("ver", "1.0");
		bizContent.put("turnPageFlag", "");
		bizContent.put("beginRow", "");
		bizContent.put("rowCount", "");
		//私有参数
		bizContent.put("type", "01");
		bizContent.put("appid", APP_ID);
		bizContent.put("cooperUnit", "HT4301056");
		bizContent.put("modelID", "123");
		bizContent.put("custID", "123");
		bizContent.put("name", "测试一号");
		bizContent.put("idtype", "0");
		bizContent.put("idNo", "1111111111");
		bizContent.put("cardNo", "");
		bizContent.put("phoneNo", "");
		bizContent.put("CName", "测试公司");
		bizContent.put("creditCode", "11");
		bizContent.put("remark1", "");
		bizContent.put("remark2", "");
		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);

		request.setServiceUrl(URL);
		request.setBizContent(bizContent);
		
		 try {
	            response = client.execute(request);
	        } catch (Exception e) {
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
