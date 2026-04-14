package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.CardbusinessEtcAgreementcardRequestV1;
import com.icbc.api.request.CardbusinessEtcAgreementcardRequestV1.CardbusinessEtcAgreementcardRequestV1Biz;
import com.icbc.api.response.CardbusinessEtcAgreementcardResponseV1;

public class CardbusinessEtcAgreementcardV1Test {
	public static void main(String[] args) {
		String APP_ID = "xxxxxxxxxxxx";
		String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvsDaqwqupg1kWak1iCFUWfHpDpdc4y2Z2hrj2dDnPzWgtkJ8qB+LlLSi44ZXvSJuUNkPZ843498ecQ/a72aeJ86vVzgI6cJYydCgcnT8RLTAeoYED9XBvkcbYKLrA8BEo4DEv9I2FJBU4AVgrZfeltSuqQqPhNQHiyWX+LfDzE8j48a0ZGAOaypnELgBNKroL7vf4IumKWDkrnc4otAfVQ60e6OFdm4JdTch4tj0xwPhSf7bqLQYlzbH+9qXkCD7hJ/5hFzT36eKIkXLVcXTBZhX7UrRq7332v4RfBf57HxMKqbrH1AHaMHC/W8pavOjVWRDn05ctwNqdnpaY4gUbAgMBAAECggEAHATqcwlg/0PnbV++v6e8ij2H8kn8rR4jVTvfO88FeVK1Dq9F8xoW4Z32JKY1l1fpGBYfdB8XwWEjAJUGQKdRxPqLqSmVyWQzUBCeZRHfAQpH5lqzjMIUEHcbU1YLksG2uuImdhN2jolWqPvt5933RQJD2H+QS1Z82oJsNcF18A4vtrokSSz4KLUVKfqASbkcPjYWLO4HZj4POrW1hxVaTKSuhQ45jcaqSVn1SWOwMKmIOX+HMIHJWJGaNeUNIc8SZsHx0Zf26G4asDMI1yCnzy2zV1u76jH7ZXkVukoy8Y0P2UHiQpdQjaWMQmsQ0xHRRuUjpwRmmpOiSaebgUA5oQKBgQDrfr5pDHpIXBoyNgNJUXbKElyA9s91imU0rWHdGTLFztjmEGAwDi3nK9J/OhLQ1ihsU4jK/fRygVbENXWDN0doQGUN5paCTLF3zQy9JFl8P9+iPiMKgHDV5kDcxiAK1laSUsHlZ+IzQDgMhMELCPhghNhpuQvNsZ4zNyO/0OutBwKBgQC+/Fvyv9+iOJWcxuaRpof/eJWXKfquxrK2SvjTQfzdQuP9MUuwuPAzcNz/SsNSx1KSx53ZNZPwXSq8nh793YnF7ZAT9KE2VUZApW69FayFltiWsF/iM9Q+qQZdcrDrOWf3ZzuDXGEGniGwWo9NnrVvtMQw7VWDKpFwTwSoICS2TQKBgCMxY79LMBMUQI30aHVSiVAVgmF7gPFt50YCCzgBhgbtrJhlJhrIY6/Casi8owc2mf/mU/ESFq/uCEt2OcOMRU2dIfz70s5SK8tdh2PUxNK+sOsMIix1mWc967+SlENKWNGxMGzaI4HJPRcR18+QXZ3+/GCAWPU6cAvzV5JY5H+NAoGBALFzEIuvsz2qjXgpZjmWHW5Ev9i/7GdPvuSZLDA9c5CxgdvSXZ1JeASTrQKWmHzlLderGQKkQVxndDnkQK9nk4cPf3rLJK6y9J9EFmwhIzbMPVllbvFhbqb5rSxOUAt+PxckF6upvYzEiFCvE87u022C63b6TSWd+5v6Dz2XAAVRAoGAX/P5tKMte8UQXbW8eo8sqUYHJ0FkjOmJm1NpvOHlI3LZ/CJ+TbRpxg0TiOKw5H9PIahq3LGFLfKIIc9lnHuhGkp3jyIGgH38xp6K63UFnMoFjO+n5Apm1V/5q/00SQBK/oxLTtqxBttFvmr46SQOls1U4XKRKzJpx+0OG9AbCJ4=";

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY); // 加载APPID，合作方签名私钥，API网关公钥
		String ICBCAPI_URL = "http://ip:port/api/cardbusiness/etc/agreementcard/V1";

		CardbusinessEtcAgreementcardRequestV1 request = new CardbusinessEtcAgreementcardRequestV1();
		CardbusinessEtcAgreementcardRequestV1Biz bizContent = new CardbusinessEtcAgreementcardRequestV1Biz();

		// 以下bizContent为业务数据字段，需要set具体业务数据
		bizContent.setMsgId ("xxxxxxxxxxxx"); //交易流水号
		bizContent.setOrgCode("xxxxxxxxxxxx"); //渠道代码
		bizContent.setAgreementNum("xxxxxxxxxxxx"); //签约协议号
		bizContent.setVehicleId("xxxxxxxxxxxx"); //车辆信息唯一标识
		bizContent.setOperflag("xxxxxxxxxxxx"); //操作类型 1-更换卡号 2-更换手机号
		bizContent.setCard_no("xxxxxxxxxxxx"); //银行卡号 更换卡号时必输；白名单渠道若没有客户三要素，则必输卡号以查询客户信息；
		bizContent.setMobile_no("xxxxxxxxxxxx"); //手机号 更换手机号时必输
//		bizContent.setId_type("0"); //证件类型 0-身份证
		bizContent.setId_no("xxxxxxxxxxxx"); //证件号码
		bizContent.setHolder_name("xxxxxxxxxxxx"); //持卡人姓名

		request.setBizContent(bizContent);
		request.setServiceUrl(ICBCAPI_URL);

		String msg_id = String.valueOf(System.currentTimeMillis());
		try {
			CardbusinessEtcAgreementcardResponseV1 response = client.execute(request, msg_id);// 第二个参数需要用随机数，APi网关会做防重复检查

			if (response.isSuccess() && response.getReturnCode() == 0) {
//				System.out.println("response.isSuccess():" + response.isSuccess());
//				System.out.println("Success:" + response.getReturnCode());
//				System.out.println("RetMsg:" + response.getReturnMsg());
//				System.out.println("cardType:" + response.getCardType());
//			} else {
//				System.out.println("response.isSuccess():" + response.isSuccess());
//				System.out.println("err_ReturnCode " + response.getReturnCode());
//				System.out.println("err_ReturnMsg " + response.getReturnMsg());
			}
//			System.out.println("调用结束：！" + '\n');
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
