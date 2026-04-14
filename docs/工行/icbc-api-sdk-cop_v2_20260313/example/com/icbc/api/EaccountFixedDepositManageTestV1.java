/**
 * TODO
 *
 * @author kfzx-weiwf
 * 
 */
package com.icbc.api;

import java.net.URLEncoder;
import java.net.URLDecoder;

import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.EaccountFixedDepositManageRequestV1;
import com.icbc.api.request.EaccountFixedDepositManageRequestV1.EaccountFixedDepositManageRequestBizV1;

/**
 * TODO
 *
 *
 * @author kfzx-weiwf
 */
public class EaccountFixedDepositManageTestV1{
	 
	 protected static String APP_ID = "";
	 protected static String MY_PRIVATE_KEY = "";//私钥
	 private static String AES_KEY = "";//	AES_KEY
	
	 
	public static void main(String[] args) throws IcbcApiException {
		
		UiIcbcClient client = new UiIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY);
		EaccountFixedDepositManageRequestV1 request = new EaccountFixedDepositManageRequestV1();		
		request.setServiceUrl("https://ip:port/ui/eaccount/fixedDeposit/manage/V1");
		EaccountFixedDepositManageRequestBizV1 bizContent = new EaccountFixedDepositManageRequestBizV1();
		bizContent.setPhoneNo("");//手机号
		bizContent.setCertNo("");//身份证号
		bizContent.setCustName(URLEncoder.encode(""));//姓名
		bizContent.setCorpMediumId("");//卡号
		bizContent.setBacAddr(URLEncoder.encode(""));//回调地址
				
		bizContent.setCorpAppid(APP_ID);//appid
		
		long systime = System.currentTimeMillis();
		String orderTimeStamp=new Long(systime).toString();		
		System.out.println("orderTimeStamp:"+orderTimeStamp);
		bizContent.setOrderTimeStamp(orderTimeStamp);//	
	    
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));//
	}
}
