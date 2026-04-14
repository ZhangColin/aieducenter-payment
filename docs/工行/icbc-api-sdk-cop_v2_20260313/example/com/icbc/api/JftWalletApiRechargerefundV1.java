package com.icbc.api;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftWalletApiRechargerefundRequestV1;
import com.icbc.api.request.JftWalletApiRechargerefundRequestV1.JftWalletApiRechargerefundRequestV1Biz;
import com.icbc.api.response.JftWalletApiRechargerefundResponseV1;


public class JftWalletApiRechargerefundV1 {
	/**
	 * 充值退款测试类
	 *
	 * @author kfzx-gaona
	 *
	 */
	String MY_PRIVATE_KEY2 = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDQMxL2onxMxY3ssAOkF2p7ryzTk6wq++YQxFXqb+gsEzToRDoDcrBa14muwmPWrk8TTg9N9Pn/sy7aLLtmVjBdCLRA69l0+e9DKDS7GBpobp9K9IJ7YZa4La3c/+dg2LTer09fSKGUNsSzNF8elbRz++nx7hscn/daNcn5qUS//GjaMcPVipqxHGJ+G+tEChVNrRlISUJOW0BOeR5oHkTHFUMuWe5VK+OF80APgDxuiHVWRwN3SOGbNeDW1Ic1nQYP/Zt9EvtwDIO49ERxjfr+Ai8Gp0+wYZ9TZUREG8SjR8qwsaIyqz//WLILXhPw0E/K7r9OTnTqYZx9YaDIf2R9AgMBAAECggEBAMv8rqDNVIIXsVVsozSb6K2kVYQ7FJ3Mzn4xD4ix+kVs0BKd/kUwko9+zJxYQ1+syzc77QzRcDxSgB4tY7MB0ULl604MhrPwybb/5qJFHGKjuBjFdi6S0AxGXSnB5R5jVNs14suVqxI/kvuN3YBm5zYYZP+v7gpexQgy2WBuszhxvpOAd0ffmK9ubieG6xdB+1f4tMUpP2sIMsxRSgzbKwZQivJtT3yXElMbUWYpbB9JS6wfdJSY2EICaF6TUAi8otmwRr1ee4CT5U7lvNO2//6ocidl8LqIy1tpLaTfREIAqneWBEoRC4DBZmgOwX7A35cZC0YlbbersMc/JzP3RQECgYEA6PYJGmGkJ31RXdK9BhiUegr+PMx1/9eMxOC1TXxrMtZh7eeyUrbxxPMsiq8jMQWhTEKqsg/AXjCy+AmQeoydMipGUze4+qwN4P69k5m3SVWELnh70QVyhw3rrAa9KHiX5MX4em5p5DM6V0nZ2GutyElpIjRQuZteFZMHPLue6z0CgYEA5Moj9E36S1qEVDYa4YZrUm3E0F064z2sJunQybEjwlAVctax3tInUl2qLCzCIE1UBY9duLcyaOh1HQSKdXlyB2PoS4WH9cH1tJ8I+YBzked7lj4S1u5KMN+azQ2r0qyJClPtLilE2UraZuLXjjq19Thoj1qlrK4zy0lJ0to+8kECgYB5eDBu7RKm9JUEkum00AQ7TpgaI5IzgLUJwxFGAeXzcA1H+1PkBTgzNYi4F3IGEaV1Gh3fEo9/jW0EIBiRjdg5XAmnQprVOflSpAE9gR0sw8ZUZMZKyshY201TOYBQJfEtfHx/eisEriFxejR4wEyw7nY/O4AMB5MbNLrFVB8dEQKBgDF6Wa4y0udK6wU/Mi8Fn94SqRaaouqT4tADjsifi2kxYoIUViEm96QDZ5LoFw9fScVuDMmurLMtWm5tFfhzzCo2+/yhIxcN3DI0ln9GvTeXhQ2n3PEoVVkecl7YDwmfkH9wSQHLoP0RCAxqEE3M+p7OlSYzZ1Lh1YeoENQNqMFBAoGACpZ1SY+eKCa5b5X7j5Z28xsnEjThLqozcNdp/zqWB9z8Qs/yIPpOxRKxRHJ7fIr3ZeD+pcE7fqxtGwVuZwafqmKUVv4zn089iCBuWqTPI0l51u1hhkjWmlBWKHz2y4T8gTuu0bQeDkK5Hi4bhgVWP38vXZkhF6CYGXhiMbA1Xwk=";
	String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000012090";

	// 充值退款测试

	public void test_cop1() {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY2, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", "zl+5SxQAx6P7bjpqRu693Q==", "", "");

		JftWalletApiRechargerefundRequestV1 request = new JftWalletApiRechargerefundRequestV1();
		request.setServiceUrl("http://ip:port/api/jft/wallet/api/rechargerefund/V1");
		JftWalletApiRechargerefundRequestV1Biz bizContent = new JftWalletApiRechargerefundRequestV1Biz();
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		bizContent.setAppId("10000000000000012090");// 商户appid
		bizContent.setTrxAccDate("2017-03-15");//合作方交易日期
		bizContent.setTrxAccTime("13:30:01");//合作方交易时间
		bizContent.setCorpDate("2017-03-15");//合作方工作日期
		bizContent.setCorpSerno("AasD10999988889876");//合作方交易单号
		bizContent.setOutUserId("123132132");//外部USER_ID
		bizContent.setBaseAcctNo("6556668880000000026");//账号
		bizContent.setOrigOrderId("201901181415");//原充值预订单号
		bizContent.setOutRefundId("9898989867677676");//充值退款流水号
//		bizContent.setReversalReason("充值退款接口测试数据");//退款原因
		bizContent.setNotifyUrl("www.com.dicts");//通知地址
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println(bizContent);
		request.setBizContent(bizContent);

		JftWalletApiRechargerefundResponseV1 response;
		try {
			response = (JftWalletApiRechargerefundResponseV1) client.execute(request, System.currentTimeMillis() + "");
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

}
