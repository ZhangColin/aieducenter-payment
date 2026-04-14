/**
 * TODO
 *
 * @author kfzx-weiwf
 * 
 */
package com.icbc.api;

import java.net.URLEncoder;

import org.junit.Test;

import com.icbc.api.request.ThirdPartyIIAccountRequestV1;
import com.icbc.api.request.ThirdPartyIIAccountRequestV1.ThirdPartyIIAccountRequestBizV1;


/**
 * TODO
 *
 *
 * @author kfzx-weiwf
 * @since 2017-4-25 下午04:21:30
 */
public class ThirdPartyIIAccountHomePageTestV1{
	 
	 protected static String APP_ID = "10000000000000004950";// "10000000000000002826";
	 protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJCoQe/O+uqpOtyE/2CUjD7wosZw8jI1AlLNJCllOmlX+obA6h97b0JsEL0SjMCAR3xJyw7MKLqkcy5qQ/bBgw2XSrodmjzOVqfT1OXRii0xw0HiVkHR4cEWnEAAdfo0lDc4iuzCIQrnT5gM0+U+qeSV6JFfwVRjgYzBdHQRPCf1AgMBAAECgYAdQUoEe7GXH5591o/nMmOinvvscg8pRDsyD7bOgGBtyZMrCXzP3SDFKCHCeyvoColqg2oDlhpulK+OpYMVNlGQcO4eubOJp9MUc3m4A9RxkVr3dsVrmygM5czPfWHAPVQ4dECDruBJn9zoo1ci0myRTh4KSCq6SxpCE0Pbf3j7iQJBAOdMPFRfOkSQ1hsN9Mg4jd9on8r7+mF8gFPhkNI6qvEKw/prmU3obUWvZL42vRlLOQyyB92mBF24PStR/B6CjR8CQQCgGz0VoqCplurSCapGdgX3D7bNSDtMUmaLJYJxih8v+zghP0YtVgDeV3NjogVjOlz8/9Rebo0PoFcqyJnNA1RrAkEAul3dBoasZm7ldWsrXuDiv66HgoDB4Cb3J59Kl3oaHpp0CqUEI5gx48JNRE7K00SfNTGF0Pxh7Dn1X6Bxqwu6NQJADrdyPfLc4bnFi9jnleJzWepP2z6wdKt+UXv5KYaQp1BoMGYohTJKkiVnrdjOtfg/Y+IAG03+GVmbqYsW2AleUQJBANYQklohKtsmq8ptX3as6hjcuXcTpc2DSpVeOnCueNv107+dDJ+K14tbiVYzF3tkHBNMRkGtr6EuGKJ+A63sJ00=";
	 private String AES_KEY = "xMh0xFsG7G80ziePFdnT8g==";
	
	
	public void check_sign_rsa() throws IcbcApiException {
		
		UiIcbcClient client = new UiIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,
				"-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJCoQe/O+uqpOtyE/2CUjD7wosZw8jI1AlLNJCllOmlX+obA6h97b0JsEL0SjMCAR3xJyw7MKLqkcy5qQ/bBgw2XSrodmjzOVqfT1OXRii0xw0HiVkHR4cEWnEAAdfo0lDc4iuzCIQrnT5gM0+U+qeSV6JFfwVRjgYzBdHQRPCf1AgMBAAECgYAdQUoEe7GXH5591o/nMmOinvvscg8pRDsyD7bOgGBtyZMrCXzP3SDFKCHCeyvoColqg2oDlhpulK+OpYMVNlGQcO4eubOJp9MUc3m4A9RxkVr3dsVrmygM5czPfWHAPVQ4dECDruBJn9zoo1ci0myRTh4KSCq6SxpCE0Pbf3j7iQJBAOdMPFRfOkSQ1hsN9Mg4jd9on8r7+mF8gFPhkNI6qvEKw/prmU3obUWvZL42vRlLOQyyB92mBF24PStR/B6CjR8CQQCgGz0VoqCplurSCapGdgX3D7bNSDtMUmaLJYJxih8v+zghP0YtVgDeV3NjogVjOlz8/9Rebo0PoFcqyJnNA1RrAkEAul3dBoasZm7ldWsrXuDiv66HgoDB4Cb3J59Kl3oaHpp0CqUEI5gx48JNRE7K00SfNTGF0Pxh7Dn1X6Bxqwu6NQJADrdyPfLc4bnFi9jnleJzWepP2z6wdKt+UXv5KYaQp1BoMGYohTJKkiVnrdjOtfg/Y+IAG03+GVmbqYsW2AleUQJBANYQklohKtsmq8ptX3as6hjcuXcTpc2DSpVeOnCueNv107+dDJ+K14tbiVYzF3tkHBNMRkGtr6EuGKJ+A63sJ00=",
				IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES, "xMh0xFsG7G80ziePFdnT8g==");
		ThirdPartyIIAccountRequestV1 request = new ThirdPartyIIAccountRequestV1();		
		request.setServiceUrl("http://ip:port/ui/thirdParty/IIAccount/servlet/homePage/V1");///thirdParty/IIAccount/homePage/V1   thirdParty/IIAccount/V1/homePage


		ThirdPartyIIAccountRequestBizV1 bizContent = new ThirdPartyIIAccountRequestBizV1();
		bizContent.setMobileNo("13412345678");//实际调用时需控制手机号不能为空,请自行添加
		bizContent.setIdcode("12345");//实际调用时需控制身份证号不能为空，请自行添加		
		bizContent.setName(URLEncoder.encode("测试"));//实际调用时需控制姓名不能为空，请自行添加		
	    
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));// 实际调用时的相关返回结果及异常处理，请自行添加
	}
}
