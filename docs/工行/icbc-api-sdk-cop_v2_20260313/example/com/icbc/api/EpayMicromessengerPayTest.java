package com.icbc.api;
import com.icbc.api.request.EpayMicromessengerPayRequestV1;
import com.icbc.api.request.EpayMicromessengerPayRequestV1.EpayMicromessengerPayRequestV1Biz;

/**
 * ������ά���ѯ�ӿ�
 * 
 * ���̻�����SDK�е�lib�ļ����е�jar���󣬸��ݱ�ע1-6��ʾ���������滻
 */
public class EpayMicromessengerPayTest {

	//1�����ع�Կ
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2��appid
	protected static final String APP_ID = "10000000000000002156";

	//3����Կ����֤��ʽ����Կ��APIƽ̨�Ǽǣ��˴�Ϊ˽Կ
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
		
	public static void main(String[] args) throws Exception {
		
		//ǩ������ΪRSA2ʱ���贫��appid��˽Կ�����ع�Կ��ǩ������ʹ�ö�ֵIcbcConstants.SIGN_TYPE_RSA2����������ʹ��ȱʡֵ
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		EpayMicromessengerPayRequestV1 request = new EpayMicromessengerPayRequestV1();
		
		//4�����ݲ��Ի��������������滻��Ӧip�Ͷ˿�
		request.setServiceUrl("https://ip:port/api/b2c/orderqry/search/V1");

		//5������սӿ��ĵ���bizContent.setxxx()������ҵ���������ݽ��и�ֵ
		EpayMicromessengerPayRequestV1Biz bizContent = new EpayMicromessengerPayRequestV1Biz();
		bizContent.setMerId("020001020461");
		bizContent.setOutTradeNo("20171213102409655518");
		bizContent.setGoodsTag("20171213102409");
		bizContent.setOrderDate("20171213102409");
		bizContent.setEndTime("20171213102909");
		bizContent.setBody("��");
		bizContent.setAttach("ƻ��");
		bizContent.setTotalAmount("10000");
		bizContent.setSpbillCreateIp("ip:port");
		bizContent.setTradeType("704");
		bizContent.setMerUrl("http://ip:port:11452/servlet/ICBCOFSTEBizServlet?tokenID=1303");
		bizContent.setMerFlag("0");
		bizContent.setResultType("0");
		bizContent.setNotifyType("HS");
		bizContent.setBackup1("");
		bizContent.setBackup2("");
		bizContent.setInterfaceVersion("ip:port");
		request.setBizContent(bizContent);

		System.out.println(client.buildPostForm(request));
		request.setBizContent(bizContent);


		
	}

	
}

