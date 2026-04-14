/**
 * TODO
 *
 * @author kfzx-weiwf
 * @since 2017-4-25 ����04:21:30
 */
package com.icbc.api;

import java.net.URLEncoder;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BabyCardVerifyRequestV1;
import com.icbc.api.request.BabyCardVerifyRequestV1.BabyCardVerifyRequestBizV1;
import com.icbc.api.response.BabyCardVerifyResponseV1;



/**
 * TODO
 *
 *
 * @author kfzx-weiwf
 * @since 2017-4-25 ����04:21:30
 */
public class BabyCardVerifyTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJCoQe/O+uqpOtyE/2CUjD7wosZw8jI1AlLNJCllOmlX+obA6h97b0JsEL0SjMCAR3xJyw7MKLqkcy5qQ/bBgw2XSrodmjzOVqfT1OXRii0xw0HiVkHR4cEWnEAAdfo0lDc4iuzCIQrnT5gM0+U+qeSV6JFfwVRjgYzBdHQRPCf1AgMBAAECgYAdQUoEe7GXH5591o/nMmOinvvscg8pRDsyD7bOgGBtyZMrCXzP3SDFKCHCeyvoColqg2oDlhpulK+OpYMVNlGQcO4eubOJp9MUc3m4A9RxkVr3dsVrmygM5czPfWHAPVQ4dECDruBJn9zoo1ci0myRTh4KSCq6SxpCE0Pbf3j7iQJBAOdMPFRfOkSQ1hsN9Mg4jd9on8r7+mF8gFPhkNI6qvEKw/prmU3obUWvZL42vRlLOQyyB92mBF24PStR/B6CjR8CQQCgGz0VoqCplurSCapGdgX3D7bNSDtMUmaLJYJxih8v+zghP0YtVgDeV3NjogVjOlz8/9Rebo0PoFcqyJnNA1RrAkEAul3dBoasZm7ldWsrXuDiv66HgoDB4Cb3J59Kl3oaHpp0CqUEI5gx48JNRE7K00SfNTGF0Pxh7Dn1X6Bxqwu6NQJADrdyPfLc4bnFi9jnleJzWepP2z6wdKt+UXv5KYaQp1BoMGYohTJKkiVnrdjOtfg/Y+IAG03+GVmbqYsW2AleUQJBANYQklohKtsmq8ptX3as6hjcuXcTpc2DSpVeOnCueNv107+dDJ+K14tbiVYzF3tkHBNMRkGtr6EuGKJ+A63sJ00=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000010534";
	

	@org.junit.Test
	public void test_cop() throws IcbcApiException {
		
		
		//DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
	    		IcbcConstants.CHARSET_UTF8,"json",APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, "xMh0xFsG7G80ziePFdnT8g==","","");
		BabyCardVerifyRequestV1 request = new BabyCardVerifyRequestV1();
		request.setServiceUrl("http://ip:port/api/babycard/customer/verify/V1");

		BabyCardVerifyRequestBizV1 bizContent = new BabyCardVerifyRequestBizV1();
		bizContent.setTranType("1");
		bizContent.setCardNo("6222020200123733988");//������ 6212250200000002509 һ�İ�һ
		bizContent.setName(URLEncoder.encode("һ�İ�һ"));
	    
		request.setBizContent(bizContent);
		BabyCardVerifyResponseV1 response ;
		try {
			response = client.execute(request);
			System.out.println(JSONObject.toJSONString(response));
			if (response.isSuccess() ) {
				
				String corpNum=response.getCorpNum();//�������
				System.out.println("������ţ�"+corpNum);
				if (response.getVerResult() == 1)
				{
					System.out.println("������У��ͨ��");
				}
				else{
					System.out.println(response.getReturnCode());
					System.out.println(response.getReturnMsg());
				}
			} else {
				// ʧ��
				System.out.println(response.getReturnCode());
				System.out.println(response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

