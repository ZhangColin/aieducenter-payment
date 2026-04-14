package com.icbc.api;

import com.icbc.api.request.AggregatePaymentRequestV2;
import com.icbc.api.request.AggregatePaymentRequestV2.AggregatePaymentRequestV2Biz;

public class AggregatePaymentRequestTestV2 {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid
	protected static final String APP_ID = "10000000000000002156";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
		
	public static void main(String[] args) throws Exception {
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		String priKey = "TKhSiK9Blwr6+aCq+O0MFg==";
		//prikey为AES秘钥
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,"UTF-8","AES",priKey);

		AggregatePaymentRequestV2 request = new AggregatePaymentRequestV2();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/aggregate/payment/request/V2");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		AggregatePaymentRequestV2Biz bizContent = new AggregatePaymentRequestV2Biz();
		bizContent.setMerId("020001020461");
		bizContent.setChannelId("C020001020121");
		bizContent.setOutTradeNo("TCO001Icbc_w180125144301p532582");
		bizContent.setTranType("OfflinePay");
		bizContent.setOrderDate("20171213102409");
		bizContent.setEndTime("20171213102909");
		bizContent.setGoodsBody("芬达");
		bizContent.setGoodsDetail("{'good_name':'芬达橙味300ml罐装','good_id':'1001','good_num':'1'}");
		bizContent.setAttach("友宝");
		bizContent.setOrderAmount("10000");
		bizContent.setSpbillCreateIp("ip:port");
		bizContent.setNotifyUrl("http://ip:port:11452/servlet/ICBCOFSTEBizServlet?tokenID=1303");
		bizContent.setResultType("0");
		bizContent.setNotifyType("HS");
		bizContent.setBackup1("");
		bizContent.setBackup2("");
		bizContent.setBackup3("");
		bizContent.setBackup4("");
		bizContent.setInterfaceVersion("ip:port");
		request.setBizContent(bizContent);

		System.out.println(client.buildPostForm(request));
		request.setBizContent(bizContent);


		
	}

	
}
