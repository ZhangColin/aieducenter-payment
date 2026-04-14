package com.icbc.api;

import org.junit.Test;
import com.icbc.api.request.B2cPassfreeAgreementSignRequestV1;
import com.icbc.api.request.B2cPassfreeAgreementSignRequestV1.B2cPassfreeAgreementSignRequestV1Biz;

public class B2cPassfreeAgreementSignV1Test {
	
	//appid
	protected static final String APP_ID = "10000000000000002156";
	
	//合作方私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==";
	
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	

	public void test_cop() throws IcbcApiException {
		
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		B2cPassfreeAgreementSignRequestV1 request = new B2cPassfreeAgreementSignRequestV1();
		
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/b2c/passfree/agreement/sign/V1");
		
		B2cPassfreeAgreementSignRequestV1Biz bizContent = new B2cPassfreeAgreementSignRequestV1Biz();
		bizContent.setExternalId("123456789005266238");
		bizContent.setSignValidityPeriod("12m");
		bizContent.setMerchantId("1234567890");
		bizContent.setMerchantAcct("6222020200106190966");
		bizContent.setExternalAgreementNo("201802201234567890000001");
		bizContent.setNotifyUrl("https://ip:port/notify");
		bizContent.setReturnUrl("https://ip:port/return");
		bizContent.setSupportCardType("0");
		
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));

	}
	
}
