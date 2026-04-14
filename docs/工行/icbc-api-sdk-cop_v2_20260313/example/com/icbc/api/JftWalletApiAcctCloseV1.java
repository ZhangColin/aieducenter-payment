package com.icbc.api;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftWalletApiAcctCloseRequestV1;
import com.icbc.api.request.JftWalletApiAcctCloseRequestV1.CloseAcctSendMsgInBiz;
import com.icbc.api.response.JftWalletApiAcctCloseResponseV1;

/**
 * 开户API接口测试类
 *
 *
 * @author kfzx-wangpeng02
 *
 */
public class JftWalletApiAcctCloseV1 {

	String MY_PRIVATE_KEY2 = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDQMxL2onxMxY3ssAOkF2p7ryzTk6wq++YQxFXqb+gsEzToRDoDcrBa14muwmPWrk8TTg9N9Pn/sy7aLLtmVjBdCLRA69l0+e9DKDS7GBpobp9K9IJ7YZa4La3c/+dg2LTer09fSKGUNsSzNF8elbRz++nx7hscn/daNcn5qUS//GjaMcPVipqxHGJ+G+tEChVNrRlISUJOW0BOeR5oHkTHFUMuWe5VK+OF80APgDxuiHVWRwN3SOGbNeDW1Ic1nQYP/Zt9EvtwDIO49ERxjfr+Ai8Gp0+wYZ9TZUREG8SjR8qwsaIyqz//WLILXhPw0E/K7r9OTnTqYZx9YaDIf2R9AgMBAAECggEBAMv8rqDNVIIXsVVsozSb6K2kVYQ7FJ3Mzn4xD4ix+kVs0BKd/kUwko9+zJxYQ1+syzc77QzRcDxSgB4tY7MB0ULl604MhrPwybb/5qJFHGKjuBjFdi6S0AxGXSnB5R5jVNs14suVqxI/kvuN3YBm5zYYZP+v7gpexQgy2WBuszhxvpOAd0ffmK9ubieG6xdB+1f4tMUpP2sIMsxRSgzbKwZQivJtT3yXElMbUWYpbB9JS6wfdJSY2EICaF6TUAi8otmwRr1ee4CT5U7lvNO2//6ocidl8LqIy1tpLaTfREIAqneWBEoRC4DBZmgOwX7A35cZC0YlbbersMc/JzP3RQECgYEA6PYJGmGkJ31RXdK9BhiUegr+PMx1/9eMxOC1TXxrMtZh7eeyUrbxxPMsiq8jMQWhTEKqsg/AXjCy+AmQeoydMipGUze4+qwN4P69k5m3SVWELnh70QVyhw3rrAa9KHiX5MX4em5p5DM6V0nZ2GutyElpIjRQuZteFZMHPLue6z0CgYEA5Moj9E36S1qEVDYa4YZrUm3E0F064z2sJunQybEjwlAVctax3tInUl2qLCzCIE1UBY9duLcyaOh1HQSKdXlyB2PoS4WH9cH1tJ8I+YBzked7lj4S1u5KMN+azQ2r0qyJClPtLilE2UraZuLXjjq19Thoj1qlrK4zy0lJ0to+8kECgYB5eDBu7RKm9JUEkum00AQ7TpgaI5IzgLUJwxFGAeXzcA1H+1PkBTgzNYi4F3IGEaV1Gh3fEo9/jW0EIBiRjdg5XAmnQprVOflSpAE9gR0sw8ZUZMZKyshY201TOYBQJfEtfHx/eisEriFxejR4wEyw7nY/O4AMB5MbNLrFVB8dEQKBgDF6Wa4y0udK6wU/Mi8Fn94SqRaaouqT4tADjsifi2kxYoIUViEm96QDZ5LoFw9fScVuDMmurLMtWm5tFfhzzCo2+/yhIxcN3DI0ln9GvTeXhQ2n3PEoVVkecl7YDwmfkH9wSQHLoP0RCAxqEE3M+p7OlSYzZ1Lh1YeoENQNqMFBAoGACpZ1SY+eKCa5b5X7j5Z28xsnEjThLqozcNdp/zqWB9z8Qs/yIPpOxRKxRHJ7fIr3ZeD+pcE7fqxtGwVuZwafqmKUVv4zn089iCBuWqTPI0l51u1hhkjWmlBWKHz2y4T8gTuu0bQeDkK5Hi4bhgVWP38vXZkhF6CYGXhiMbA1Xwk=";
	String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEA0DMS9qJ8TMWN7LADpBdqe68s05OsKvvmEMRV6m/oLBM06EQ6A3KwWteJrsJj1q5PE04PTfT5/7Mu2iy7ZlYwXQi0QOvZdPnvQyg0uxgaaG6fSvSCe2GWuC2t3P/nYNi03q9PX0ihlDbEszRfHpW0c/vp8e4bHJ/3WjXJ+alEv/xo2jHD1YqasRxifhvrRAoVTa0ZSElCTltATnkeaB5ExxVDLlnuVSvjhfNAD4A8boh1VkcDd0jhmzXg1tSHNZ0GD/2bfRL7cAyDuPREcY36/gIvBqdPsGGfU2VERBvEo0fKsLGiMqs//1iyC14T8NBPyu6/Tk506mGcfWGgyH9kfQIDAQAB";

	protected static final String APP_ID = "10000000000000012090";// 9月10000000000000008062

	// 销户发短信

	public void test_cop() {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY2, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", "zl+5SxQAx6P7bjpqRu693Q==", "", "");// i5N/TQHG9hq00OpdNzpUUw==

		JftWalletApiAcctCloseRequestV1 request = new JftWalletApiAcctCloseRequestV1();

		request.setServiceUrl("http://ip:port/api/jft/wallet/api/acctclose/V1");
		CloseAcctSendMsgInBiz bizContent = new CloseAcctSendMsgInBiz();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		bizContent.setAppId("10000000000000012090");
		bizContent.setCorpDate("2012-01-01");
		bizContent.setCorpSerno("123456");
		bizContent.setMediumId("654321");
		bizContent.setNotifyUrl("111");
		bizContent.setOutServiceCode("5142");
		bizContent.setTrxAccDate("2012-01-01");
		bizContent.setTrxAccTime("20:33:33");
		bizContent.setPhoneNo("15636598968");
		bizContent.setOutUserId("4645645646456");
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		System.out.println(bizContent);
		request.setBizContent(bizContent);

		JftWalletApiAcctCloseResponseV1 response;

		try {

			response = (JftWalletApiAcctCloseResponseV1) client.execute(request, System.currentTimeMillis() + "");
			System.out.println("~~~~~~~~~");
			System.out.println(JSONObject.toJSONString(response));
			System.out.println("~~~~~~~~~");
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
			}

		} catch (IcbcApiException e) {

			e.printStackTrace();

		}
	}

	public static void main(String[] args) {
		new JftWalletApiAcctCloseV1().test_cop();
	}
}
