/**
 * 
 */
package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinPersloanQuickloancustverifyRequestV1;
import com.icbc.api.request.MybankLoanInclufinPersloanQuickloancustverifyRequestV1.MybankLoanInclufinPersloanQuickloancustverifyV1Biz;
import com.icbc.api.response.MybankLoanInclufinPersloanQuickloancustverifyResponseV1;

/**
 * @author Administrator
 *
 */
public class MybankLoanInclufinPersloanQuickloancustverifyTestV1 {

	//APP_ID,APIGW_PUBLIC_KEY,PRI_KEY,URL  根据实际的应用场景进行修改
	protected static final String APP_ID = "xxxxxxx";
	protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
	protected static final String PRI_KEY = "xxxxxxx";
	protected static final String URL = "http://xxxxxxx/api/mybank/loan/inclufin/persloan/quickloancustverify/V1";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MybankLoanInclufinPersloanQuickloancustverifyRequestV1 request = new MybankLoanInclufinPersloanQuickloancustverifyRequestV1();
		MybankLoanInclufinPersloanQuickloancustverifyResponseV1 response = new MybankLoanInclufinPersloanQuickloancustverifyResponseV1();
		MybankLoanInclufinPersloanQuickloancustverifyV1Biz bizContent = new MybankLoanInclufinPersloanQuickloancustverifyV1Biz();
		
		//公共参数
		bizContent.put("serialNo", "55550012334455");
		bizContent.put("appNo", "F-GCMS");
		bizContent.put("areaCode", "");
		bizContent.put("employeeCode", "");
		bizContent.put("language", "zh_CN");
		bizContent.put("transNo", "IPerLoanCustIDInfoCocoaService");
		bizContent.put("ver", "1.0");
		//私有参数
		bizContent.put("type", "01");
		bizContent.put("appid", APP_ID);
		bizContent.put("cooperUnit", "DW0101045");
		bizContent.put("modelID", "ZH1107");
		bizContent.put("custID", "");
		bizContent.put("name", "二十一");
		bizContent.put("idtype", "0");
		bizContent.put("idNo", "110101198506020206");
		bizContent.put("cardNo", "6222021001064235549");
		bizContent.put("phoneNo", "15171002201");
		bizContent.put("CName", "二十一测试公司");
		bizContent.put("creditCode", "T12345678901234567");
		bizContent.put("remark1", "");
		bizContent.put("remark2", "");
		bizContent.put("verifyFlag", "");
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
