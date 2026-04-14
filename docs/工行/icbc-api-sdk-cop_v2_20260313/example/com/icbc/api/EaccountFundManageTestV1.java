package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.EaccountFundManageRequestV1;
import com.icbc.api.request.EaccountFundManageRequestV1.EaccountFundManageRequestV1Biz;

public class EaccountFundManageTestV1 {
	
	 protected static String APP_ID = "";
	 protected static String MY_PRIVATE_KEY = "";//私钥
	 private static String AES_KEY = "";//	AES_KEY
	 
	public static void main(String[] args) throws IcbcApiException {
		// TODO Auto-generated method stub
		//UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		UiIcbcClient client = new UiIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY);
		EaccountFundManageRequestV1 request = new EaccountFundManageRequestV1();
		request.setServiceUrl("https://ip:port/ui/eaccount/fund/manage/V1");
		
		EaccountFundManageRequestV1Biz bizContent = new EaccountFundManageRequestV1Biz();
		bizContent.setCorpAppId(APP_ID);
		bizContent.setCorpMediumId("");//卡号
		bizContent.setCustName("");//用户名
		bizContent.setCertNo("");//身份证号
		bizContent.setPhoneNo("");//手机号
		bizContent.setBackAddr("");//回调地址
		
		long systime = System.currentTimeMillis(); // 
		String orderTimeStamp=new Long(systime).toString();		
		System.out.println("orderTimeStamp:"+orderTimeStamp);
		bizContent.setReqTimestamp(orderTimeStamp);
		
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request)); 
	}
}
