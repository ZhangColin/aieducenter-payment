package com.icbc.api;

import java.io.UnsupportedEncodingException;

import com.icbc.api.request.CertUmspVerifymsgsendforsaasRequestV1;
import com.icbc.api.request.CertUmspVerifymsgsendforsaasRequestV1.CertUmspVerifymsgsendforsaasRequestV1Address;
import com.icbc.api.request.CertUmspVerifymsgsendforsaasRequestV1.CertUmspVerifymsgsendforsaasRequestV1Biz;
import com.icbc.api.response.CertUmspVerifymsgsendforsaasResponseV1;

public class CertUmspVerifymsgsendforsaasRequestV1Test {
	//appid
	protected static final String APP_ID = "10000000000000007533";
	//合作方私钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//网关公钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
	//签名类型
	protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";
    
	public static void main(String[] args) throws Exception {
		CertUmspVerifymsgsendforsaasRequestV1Test a = new CertUmspVerifymsgsendforsaasRequestV1Test();
	    a.test_cop();
	}
	
	public void test_cop() throws UnsupportedEncodingException{
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CertUmspVerifymsgsendforsaasRequestV1 request = new CertUmspVerifymsgsendforsaasRequestV1();
		request.setServiceUrl("https://ip:port/api/cert/umsp/verifymsgsendforsaas/V1");
		CertUmspVerifymsgsendforsaasRequestV1Biz requestBiz = new CertUmspVerifymsgsendforsaasRequestV1Biz();
		requestBiz.setBusstype("BSMSYYZC");
		requestBiz.setClienttrace("1234343");
		requestBiz.setData(new String("123455      122333    CXY转账".getBytes(), "GBK"));
		requestBiz.setUser("F-UMSP");
		requestBiz.setPolicyno("PIPGW001");
		CertUmspVerifymsgsendforsaasRequestV1Address phonemap = new CertUmspVerifymsgsendforsaasRequestV1Address();
		phonemap.setPhone("13141275404");
		phonemap.setIsLongSMS("Y");
		requestBiz.setPhonemap(phonemap);

		request.setBizContent(requestBiz);
		CertUmspVerifymsgsendforsaasResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			int returncode = response.getReturnCode();
			String returnmsg = response.getReturnMsg(); 
			String serverinfo = response.getServerinfo();
			String sign = response.getSign();
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(returncode);
			} else {
				System.out.println(returncode);
				System.out.println(returnmsg);
				System.out.println(serverinfo);
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
