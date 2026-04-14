package com.icbc.api;

import org.junit.Assert;
import org.junit.Test;

import com.icbc.api.request.PersonTransactionDetailQueryRequestV1;
import com.icbc.api.response.PersonTransactionDetailQueryResponseV1;


public class PersonTransactionDetailQueryTestV1 {
	 protected static String APP_ID = "10000000000000005006";// "10000000000000004504-6�����";
	 protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJCoQe/O+uqpOtyE/2CUjD7wosZw8jI1AlLNJCllOmlX+obA6h97b0JsEL0SjMCAR3xJyw7MKLqkcy5qQ/bBgw2XSrodmjzOVqfT1OXRii0xw0HiVkHR4cEWnEAAdfo0lDc4iuzCIQrnT5gM0+U+qeSV6JFfwVRjgYzBdHQRPCf1AgMBAAECgYAdQUoEe7GXH5591o/nMmOinvvscg8pRDsyD7bOgGBtyZMrCXzP3SDFKCHCeyvoColqg2oDlhpulK+OpYMVNlGQcO4eubOJp9MUc3m4A9RxkVr3dsVrmygM5czPfWHAPVQ4dECDruBJn9zoo1ci0myRTh4KSCq6SxpCE0Pbf3j7iQJBAOdMPFRfOkSQ1hsN9Mg4jd9on8r7+mF8gFPhkNI6qvEKw/prmU3obUWvZL42vRlLOQyyB92mBF24PStR/B6CjR8CQQCgGz0VoqCplurSCapGdgX3D7bNSDtMUmaLJYJxih8v+zghP0YtVgDeV3NjogVjOlz8/9Rebo0PoFcqyJnNA1RrAkEAul3dBoasZm7ldWsrXuDiv66HgoDB4Cb3J59Kl3oaHpp0CqUEI5gx48JNRE7K00SfNTGF0Pxh7Dn1X6Bxqwu6NQJADrdyPfLc4bnFi9jnleJzWepP2z6wdKt+UXv5KYaQp1BoMGYohTJKkiVnrdjOtfg/Y+IAG03+GVmbqYsW2AleUQJBANYQklohKtsmq8ptX3as6hjcuXcTpc2DSpVeOnCueNv107+dDJ+K14tbiVYzF3tkHBNMRkGtr6EuGKJ+A63sJ00=";
	 protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	

	public void test_cop(){
		//  public UiIcbcClient(String appId, String signType, String privateKey, String charset, String encryptType, String encryptKey)
		UiIcbcClient client = new UiIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY,
					IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES, "xMh0xFsG7G80ziePFdnT8g==");
		PersonTransactionDetailQueryRequestV1  request = new PersonTransactionDetailQueryRequestV1();		
		request.setServiceUrl("http://ip:port/api/person/transaction/detail/query/V1");
		
		PersonTransactionDetailQueryRequestV1.TransactionDetailQueryRequestBizV1 bizContent = new PersonTransactionDetailQueryRequestV1.TransactionDetailQueryRequestBizV1();
		bizContent.setOrderId("222222222222222222");//���ý�����ˮ��
		bizContent.setAmount("123");//���ý��׽��
		bizContent.setName("�ҳ�����");//�ҳ�����
		
		request.setBizContent(bizContent);
		
		PersonTransactionDetailQueryResponseV1 response;
		try {
			response = client.execute(request,"msgId");
			if (response.isSuccess() ) {
				// ҵ��ɹ�����    1-----������ˮ�ţ����׽��ҳ���������У�鲻ͨ����
				Assert.assertEquals(response.getReturnCode(), "1");
				
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
