package com.icbc.api;

import java.util.Random;
import java.util.logging.Logger;

import com.icbc.api.request.CorpacctInfoQueryRequestV1;
import com.icbc.api.request.CorpacctInfoQueryRequestV1.CorpacctInfoQueryRequestV1Biz;
import com.icbc.api.request.CorpacctInfoQueryRequestV1.CorpacctInfoQueryRequestV1Biz.ChanCommV10;
import com.icbc.api.response.CorpacctInfoQueryResponseV1;


public class CorpacctInfoQueryTest {

	protected static final String APP_ID = "10000000000000007533";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";

	public static void main(String[] args) {

		// 构造client对象
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		CorpacctInfoQueryRequestV1 request = new CorpacctInfoQueryRequestV1();

		// 设置请求路径
		request.setServiceUrl("https://ip:port/api/corpacct/info/query/V1");
		CorpacctInfoQueryRequestV1Biz bizContent = new CorpacctInfoQueryRequestV1Biz();
		bizContent.setGROUPID("102054341AAAAAA");
		bizContent.setACCTNUM("3602078209000868801");
		ChanCommV10 chanCommV10 = bizContent.new ChanCommV10();
		chanCommV10.setSerialno("050202002263503000019600019");
		bizContent.setChanCommV10(chanCommV10);
		request.setBizContent(bizContent);
		CorpacctInfoQueryResponseV1 response;

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";

		try {
			Logger log = Logger.getLogger(CorpacctInfoQueryTest.class.getName());
			log.info("msgId:"+ msgId);
			response = client.execute(request, msgId);
			if (response.isSuccess()) {
				log.info("ReturnCode:"+ response.getReturnCode()+"\n Response:"+response.getReturnMsg());
			} else {
				log.info("ReturnCode:"+ response.getReturnCode()+"\n Response:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
