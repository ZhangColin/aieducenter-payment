/**
 * TODO
 *
 * @author kfzx-weiwf
 * 
 */
package com.icbc.api;

import java.net.URLEncoder;

import org.junit.Test;

import com.icbc.api.request.PersonalTransferCollectRequestV1;
import com.icbc.api.request.PersonalTransferCollectRequestV1.PersonalTransferCollectRequestBizV1;


/**
 * TODO
 *
 *
 * @author kfzx-weiwf
 * @since 2017-4-25 下午04:21:30
 */
public class PersonalTransferCollectTestV1{
	 protected static String APP_ID = "10000000000000004504";// "10000000000000004504-6月培帮";
	 protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJCoQe/O+uqpOtyE/2CUjD7wosZw8jI1AlLNJCllOmlX+obA6h97b0JsEL0SjMCAR3xJyw7MKLqkcy5qQ/bBgw2XSrodmjzOVqfT1OXRii0xw0HiVkHR4cEWnEAAdfo0lDc4iuzCIQrnT5gM0+U+qeSV6JFfwVRjgYzBdHQRPCf1AgMBAAECgYAdQUoEe7GXH5591o/nMmOinvvscg8pRDsyD7bOgGBtyZMrCXzP3SDFKCHCeyvoColqg2oDlhpulK+OpYMVNlGQcO4eubOJp9MUc3m4A9RxkVr3dsVrmygM5czPfWHAPVQ4dECDruBJn9zoo1ci0myRTh4KSCq6SxpCE0Pbf3j7iQJBAOdMPFRfOkSQ1hsN9Mg4jd9on8r7+mF8gFPhkNI6qvEKw/prmU3obUWvZL42vRlLOQyyB92mBF24PStR/B6CjR8CQQCgGz0VoqCplurSCapGdgX3D7bNSDtMUmaLJYJxih8v+zghP0YtVgDeV3NjogVjOlz8/9Rebo0PoFcqyJnNA1RrAkEAul3dBoasZm7ldWsrXuDiv66HgoDB4Cb3J59Kl3oaHpp0CqUEI5gx48JNRE7K00SfNTGF0Pxh7Dn1X6Bxqwu6NQJADrdyPfLc4bnFi9jnleJzWepP2z6wdKt+UXv5KYaQp1BoMGYohTJKkiVnrdjOtfg/Y+IAG03+GVmbqYsW2AleUQJBANYQklohKtsmq8ptX3as6hjcuXcTpc2DSpVeOnCueNv107+dDJ+K14tbiVYzF3tkHBNMRkGtr6EuGKJ+A63sJ00=";
	
	
	
	public void check_sign_rsa() throws IcbcApiException {
		
		//  public UiIcbcClient(String appId, String signType, String privateKey, String charset, String encryptType, String encryptKey)
		UiIcbcClient client = new UiIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,
				"-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJCoQe/O+uqpOtyE/2CUjD7wosZw8jI1AlLNJCllOmlX+obA6h97b0JsEL0SjMCAR3xJyw7MKLqkcy5qQ/bBgw2XSrodmjzOVqfT1OXRii0xw0HiVkHR4cEWnEAAdfo0lDc4iuzCIQrnT5gM0+U+qeSV6JFfwVRjgYzBdHQRPCf1AgMBAAECgYAdQUoEe7GXH5591o/nMmOinvvscg8pRDsyD7bOgGBtyZMrCXzP3SDFKCHCeyvoColqg2oDlhpulK+OpYMVNlGQcO4eubOJp9MUc3m4A9RxkVr3dsVrmygM5czPfWHAPVQ4dECDruBJn9zoo1ci0myRTh4KSCq6SxpCE0Pbf3j7iQJBAOdMPFRfOkSQ1hsN9Mg4jd9on8r7+mF8gFPhkNI6qvEKw/prmU3obUWvZL42vRlLOQyyB92mBF24PStR/B6CjR8CQQCgGz0VoqCplurSCapGdgX3D7bNSDtMUmaLJYJxih8v+zghP0YtVgDeV3NjogVjOlz8/9Rebo0PoFcqyJnNA1RrAkEAul3dBoasZm7ldWsrXuDiv66HgoDB4Cb3J59Kl3oaHpp0CqUEI5gx48JNRE7K00SfNTGF0Pxh7Dn1X6Bxqwu6NQJADrdyPfLc4bnFi9jnleJzWepP2z6wdKt+UXv5KYaQp1BoMGYohTJKkiVnrdjOtfg/Y+IAG03+GVmbqYsW2AleUQJBANYQklohKtsmq8ptX3as6hjcuXcTpc2DSpVeOnCueNv107+dDJ+K14tbiVYzF3tkHBNMRkGtr6EuGKJ+A63sJ00=",
				IcbcConstants.CHARSET_UTF8);
		PersonalTransferCollectRequestV1 request= new PersonalTransferCollectRequestV1();		
		request.setServiceUrl("http://ip:port/ui/personal/transfer/collect/V1");
		

		PersonalTransferCollectRequestBizV1 bizContent = new PersonalTransferCollectRequestBizV1();
		bizContent.setOrderID("13412345678");//订单编号，需自行控制每笔订单必须有唯一编号，必输字段，控制不能为空
		bizContent.setOrderDate("20180426164409");//交易日期时间，必输字段，控制不能为空		
		bizContent.setType("1");//交易场景大，按数据字典上送，如1-学费，必输字段，控制不能为空		
		bizContent.setSubType("101");//交易场景小，按数据字典上送，如101-学杂费，非必输，有值就上送
		bizContent.setName(URLEncoder.encode("面包超人"));//家长姓名，非必输，有值就上送
		bizContent.setChildName(URLEncoder.encode("学生"));//学生姓名，非必输，有值就上送
		bizContent.setChildNo("12345");//学号，非必输，有值就上送
		bizContent.setAmount("12");//客户支付的总金额，以分为单位，没有小数点，不可以为零，必须符合金额标准，必输字段，控制不能为空
		bizContent.setCardNo("6EB85223CD538AAC3AE89D2C790000F968895AE6A4F5FD4E");//转账卡号，先将数据加密，然后在这里上送密文，必输字段，控制不能为空
		bizContent.setCino("B38F458E242275A7C15B0C280EB0469E");//客户标识，先将数据加密，然后在这里上送密文，必输字段，控制不能为空
		bizContent.setCollegeNo("00200000612");	//学校编号，必须字段，工银e校园用户信息接口merlogin中返回的“collegeNo”，控制不能为空
		bizContent.setMerID("1");	//商户代码，按数据字典上送，1-培帮，必输字段，控制不能为空	    
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));
	}
}
