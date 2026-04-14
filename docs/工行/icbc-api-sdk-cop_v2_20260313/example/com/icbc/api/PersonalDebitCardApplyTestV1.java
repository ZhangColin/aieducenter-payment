package com.icbc.api;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.PersonalDebitCardApplyRequestV1;
import com.icbc.api.request.PersonalDebitCardApplyRequestV1.PersonalDebitCardApplyRequestBizV1;
import com.icbc.api.response.PersonalDebitCardApplyResponseV1;


public class PersonalDebitCardApplyTestV1 {
	 protected static String APP_ID = "10000000000000008109";// "10000000000000004504-6月培帮";
	 protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJCoQe/O+uqpOtyE/2CUjD7wosZw8jI1AlLNJCllOmlX+obA6h97b0JsEL0SjMCAR3xJyw7MKLqkcy5qQ/bBgw2XSrodmjzOVqfT1OXRii0xw0HiVkHR4cEWnEAAdfo0lDc4iuzCIQrnT5gM0+U+qeSV6JFfwVRjgYzBdHQRPCf1AgMBAAECgYAdQUoEe7GXH5591o/nMmOinvvscg8pRDsyD7bOgGBtyZMrCXzP3SDFKCHCeyvoColqg2oDlhpulK+OpYMVNlGQcO4eubOJp9MUc3m4A9RxkVr3dsVrmygM5czPfWHAPVQ4dECDruBJn9zoo1ci0myRTh4KSCq6SxpCE0Pbf3j7iQJBAOdMPFRfOkSQ1hsN9Mg4jd9on8r7+mF8gFPhkNI6qvEKw/prmU3obUWvZL42vRlLOQyyB92mBF24PStR/B6CjR8CQQCgGz0VoqCplurSCapGdgX3D7bNSDtMUmaLJYJxih8v+zghP0YtVgDeV3NjogVjOlz8/9Rebo0PoFcqyJnNA1RrAkEAul3dBoasZm7ldWsrXuDiv66HgoDB4Cb3J59Kl3oaHpp0CqUEI5gx48JNRE7K00SfNTGF0Pxh7Dn1X6Bxqwu6NQJADrdyPfLc4bnFi9jnleJzWepP2z6wdKt+UXv5KYaQp1BoMGYohTJKkiVnrdjOtfg/Y+IAG03+GVmbqYsW2AleUQJBANYQklohKtsmq8ptX3as6hjcuXcTpc2DSpVeOnCueNv107+dDJ+K14tbiVYzF3tkHBNMRkGtr6EuGKJ+A63sJ00=";
	 protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	
	 public static void main(String[] args) throws IcbcApiException, UnsupportedEncodingException {
		//  public UiIcbcClient(String appId, String signType, String privateKey, String charset, String encryptType, String encryptKey)
		 DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
		    		IcbcConstants.CHARSET_UTF8,"json",APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, "xMh0xFsG7G80ziePFdnT8g==","","");
		PersonalDebitCardApplyRequestV1  request = new PersonalDebitCardApplyRequestV1();		
		request.setServiceUrl("http://ip:port/api/personal/debitcard/apply/V1"); //1809版本 行外网关ip:port  DMZ ip:port:8083  http://gw.dccnet.com.cn:8083
	//	request.setServiceUrl("http://ip:port:8083/api/personal/debitcard/apply/V1"); 
		PersonalDebitCardApplyRequestV1.PersonalDebitCardApplyRequestBizV1 bizContent = new PersonalDebitCardApplyRequestV1.PersonalDebitCardApplyRequestBizV1();
		bizContent.setOrderId("031417022005103042981711435");//031417021293103042935610538-审核失败 031417021293103042936251860审核成功
		bizContent.setStatus("4");//		
		bizContent.setAppId("10000000000001894554");//
		bizContent.setMessage("审核通过");
		request.setBizContent(bizContent);
		
		PersonalDebitCardApplyResponseV1 response;
		try {
			response = client.execute(request);
			if (response.isSuccess() ) {			
				System.out.println("retCode:"+response.getReturnCode());				
			} else {
				// 失败				
				System.out.println("retCode:"+response.getReturnCode());
				System.out.println("retMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
}
