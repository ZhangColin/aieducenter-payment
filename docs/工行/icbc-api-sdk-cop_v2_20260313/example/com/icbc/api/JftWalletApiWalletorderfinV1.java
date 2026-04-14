package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.JftWalletApiVaorderfinRequestV1;
import com.icbc.api.request.JftWalletApiVaorderfinRequestV1.JftWalletApiVaorderfinRequestV1Biz;
import com.icbc.api.response.JftWalletApiOrderfinprcResponseV1;

public class JftWalletApiWalletorderfinV1 {
	String MY_PRIVATE_KEY2 = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";
	String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJ h0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000012145";


	public void test_WalletOrderFinApi() {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY2, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", "5xGJdh7qb+B95SUoxDlatg==", "", "");
		JftWalletApiVaorderfinRequestV1 request = new JftWalletApiVaorderfinRequestV1();
		request.setServiceUrl("http://ip:port/api/jft/wallet/api/walletorderfin/V1");
		JftWalletApiVaorderfinRequestV1Biz jftWalletApiVaorderfinprcRequestV1Biz = new JftWalletApiVaorderfinRequestV1Biz();
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		jftWalletApiVaorderfinprcRequestV1Biz.setTrxAccDate("20190311");
		jftWalletApiVaorderfinprcRequestV1Biz.setTrxAccTime("12:00:00");
		jftWalletApiVaorderfinprcRequestV1Biz.setCorpDate("20190301");//
		jftWalletApiVaorderfinprcRequestV1Biz.setCorpSerno("123456879156123");// 娴佹按鍙�
		jftWalletApiVaorderfinprcRequestV1Biz.setOrderId("2018122702");// 璁㈠崟鍙�
		jftWalletApiVaorderfinprcRequestV1Biz.setOrderName("741128");// 璁㈠崟鍚嶇О
		jftWalletApiVaorderfinprcRequestV1Biz.setOrderNote("");// 璁㈠崟闄勮█
		jftWalletApiVaorderfinprcRequestV1Biz.setBaseAcctNo("1201902210003492020");// 浼氬憳鍗″彿
		jftWalletApiVaorderfinprcRequestV1Biz.setTranAmt("20");// 浜ゆ槗閲戦
		jftWalletApiVaorderfinprcRequestV1Biz.setAppId("10000000000001754554");
		jftWalletApiVaorderfinprcRequestV1Biz.setOutUserId("175455401");// 澶栭儴鐢ㄦ埛id
		jftWalletApiVaorderfinprcRequestV1Biz.setNotifyUrl("www.baidu.com");// 閫氱煡url
		jftWalletApiVaorderfinprcRequestV1Biz.setPayMode("01");// 鏀粯鏂瑰紡
		jftWalletApiVaorderfinprcRequestV1Biz.setTrxIp("asdf");// 鐢ㄦ埛ip
		jftWalletApiVaorderfinprcRequestV1Biz.setSubMerRateWx("");// 瀛愬晢鎴锋湇鍔¤垂鐜�--寰俊
		jftWalletApiVaorderfinprcRequestV1Biz.setSubMerRateZfb("");// 瀛愬晢鎴锋湇鍔¤垂鐜�--鏀粯瀹�
		jftWalletApiVaorderfinprcRequestV1Biz.setTpAppId("");// 寰俊鍒嗛厤鐨勫叕浼楄处鍙稩D
		jftWalletApiVaorderfinprcRequestV1Biz.setTpOpenId("");// 鐢ㄦ埛鍦ㄥ晢鎴穉ppid涓嬬殑鍞竴鏍囪瘑
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		request.setBizContent(jftWalletApiVaorderfinprcRequestV1Biz);

		JftWalletApiOrderfinprcResponseV1 response;
		try {
			response = (JftWalletApiOrderfinprcResponseV1) client.execute(request, System.currentTimeMillis() + "");
			if (response.isSuccess()) {
				// 涓氬姟鎴愬姛澶勭悊
				System.out.println(response.getReturnCode());
			} else {
				// 澶辫触
			}

		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
