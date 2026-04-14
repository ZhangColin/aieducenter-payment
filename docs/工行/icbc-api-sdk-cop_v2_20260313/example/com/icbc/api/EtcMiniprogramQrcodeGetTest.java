package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Test;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.EtcMiniprogramQrcodeGetRequestV1;
import com.icbc.api.request.EtcMiniprogramQrcodeGetRequestV1.EtcMiniprogramQrcodeGetRequestV1Biz;
import com.icbc.api.response.EtcMiniprogramQrcodeGetResponseV1;

/**
*@author : kfzx-zhangyy02
*@date : 2019年7月22日 下午7:00:29
*
*/

public class EtcMiniprogramQrcodeGetTest {
		// 1、网关公钥
		protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

		// 2、appid
		protected static final String APP_ID = "xxxxxxxxx";

		// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==/yZCGk+quiMCw84/gDflYdg4cJ4hg7cemYSZJjoKliowSKk1kqdYtUDf/64FfJn3OJIoBW9lqLBRNnuwdWFDyULt/pNjCXeOnNpO3d0EoQ0s5+pIXcniNIATbhhnfBlSjH6Wg0Pu/as5qyEwfcSb7kbPlW8fqUS6l+3u+vAQcwX/6jaWgsDUXwQd5hX9HBzlbg==";
		

		public void test_cop() throws IcbcApiException {

			// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
					IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

			EtcMiniprogramQrcodeGetRequestV1 request = new EtcMiniprogramQrcodeGetRequestV1();
			// 4、根据测试环境和生产环境替换相应ip和端口
			request.setServiceUrl("http://ip:port/api/cardbusiness/etc/etcminiprogramqrcodeget/V1");

			// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
			EtcMiniprogramQrcodeGetRequestV1Biz bizContent = new EtcMiniprogramQrcodeGetRequestV1Biz();
			bizContent.setScene("EKX123456");
			bizContent.setWidth("100");;
			
			request.setBizContent(bizContent);
			boolean testFlag = true;
			EtcMiniprogramQrcodeGetResponseV1 response;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
				String msgId = dateFormat.format(new Date());
				response = client.execute(request, msgId);
				if (response.isSuccess()) {
					// 业务成功处理
					testFlag = true;
				} else {
					// 失败
					testFlag = false;
				}
				assertEquals(true, testFlag);
			} catch (IcbcApiException e) {
				e.printStackTrace();
			}
		}
}
