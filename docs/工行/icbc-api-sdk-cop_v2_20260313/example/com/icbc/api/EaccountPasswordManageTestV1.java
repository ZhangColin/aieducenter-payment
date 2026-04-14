/**
 * TODO
 *
 * @author kfzx-panjf01
 * 
 */
package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.EaccountPasswordManageRequestV1;
import com.icbc.api.request.EaccountPasswordManageRequestV1.EaccountPasswordManageRequestBizV1;

/**
 * TODO
 *
 *
 * @author kfzx-panjf01
 */
public class EaccountPasswordManageTestV1 {

	protected static final String APP_ID = "xxxxxxxxxxxxxxxxxxxx";
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJCoQe/O+uqpOtyE/2CUjD7wosZw8jI1AlLNJCllOmlX+obA6h97b0JsEL0SjMCAR3xJyw7MKLqkcy5qQ/bBgw2XSrodmjzOVqfT1OXRii0xw0HiVkHR4cEWnEAAdfo0lDc4iuzCIQrnT5gM0+U+qeSV6JFfwVRjgYzBdHQRPCf1AgMBAAECgYAdQUoEe7GXH5591o/nMmOinvvscg8pRDsyD7bOgGBtyZMrCXzP3SDFKCHCeyvoColqg2oDlhpulK+OpYMVNlGQcO4eubOJp9MUc3m4A9RxkVr3dsVrmygM5czPfWHAPVQ4dECDruBJn9zoo1ci0myRTh4KSCq6SxpCE0Pbf3j7iQJBAOdMPFRfOkSQ1hsN9Mg4jd9on8r7+mF8gFPhkNI6qvEKw/prmU3obUWvZL42vRlLOQyyB92mBF24PStR/B6CjR8CQQCgGz0VoqCplurSCapGdgX3D7bNSDtMUmaLJYJxih8v+zghP0YtVgDeV3NjogVjOlz8/9Rebo0PoFcqyJnNA1RrAkEAul3dBoasZm7ldWsrXuDiv66HgoDB4Cb3J59Kl3oaHpp0CqUEI5gx48JNRE7K00SfNTGF0Pxh7Dn1X6Bxqwu6NQJADrdyPfLc4bnFi9jnleJzWepP2z6wdKt+UXv5KYaQp1BoMGYohTJKkiVnrdjOtfg/Y+IAG03+GVmbqYsW2AleUQJBANYQklohKtsmq8ptX3as6hjcuXcTpc2DSpVeOnCueNv107+dDJ+K14tbiVYzF3tkHBNMRkGtr6EuGKJ+A63sJ00=";
	private static final String AES_KEY = "xMh0xFsG7G80ziePFdnT8g==";	
	private static Logger logger = Logger.getLogger(EaccountPasswordManageTestV1.class.getName());

	public static void main(String[] args) throws IcbcApiException {

		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY);
		EaccountPasswordManageRequestV1 request = new EaccountPasswordManageRequestV1();
		request.setServiceUrl("http://ip:port/ui/eaccount/password/manage/V1");///
		EaccountPasswordManageRequestBizV1 bizContent = new EaccountPasswordManageRequestBizV1();
		bizContent.setUserId("xxxxxxxxxxxx");// 必输，需控制不能为空，用户唯一标识,送身份证号
		bizContent.setBacAddr("xxxxxxxxxxxx");// 必输，回调地址
		bizContent.setCorpAppid(APP_ID);// 外公司合作方送APP_ID的值，行内应用如e支付等送外公司合作方的APPID
		long systime = System.currentTimeMillis(); // 现时间戳
		String orderTimeStamp = new Long(systime).toString();

		logger.info("orderTimeStamp:" + orderTimeStamp);
		bizContent.setOrderTimeStamp(orderTimeStamp);// 生成请求的时间,必输

		request.setBizContent(bizContent);
		logger.info(client.buildPostForm(request));// 实际调用时的相关返回结果及异常处理，请自行添加
	}
}
