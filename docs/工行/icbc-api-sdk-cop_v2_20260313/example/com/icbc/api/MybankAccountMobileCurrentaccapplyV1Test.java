package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.MybankAccountMobileCurrentaccapplyV1Request;
import com.icbc.api.request.MybankAccountMobileCurrentaccapplyV1Request.MybankAccountMobileCurrentaccapplyV1RequestBiz;

public class MybankAccountMobileCurrentaccapplyV1Test {

	
	//api公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	//api私钥
	protected static final String APIGW_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==";
	
	//app id
	protected static final String APP_ID = "10000000000004094584";
	
	public static void main(String[] args) throws IcbcApiException {
		// TODO Auto-generated method stub
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, APIGW_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		MybankAccountMobileCurrentaccapplyV1Request request = new MybankAccountMobileCurrentaccapplyV1Request();
		
		request.setServiceUrl("http://IP:PORT/ui/mybank/account/mobile/currentaccapply/V1");
		
		MybankAccountMobileCurrentaccapplyV1RequestBiz biz_content = new MybankAccountMobileCurrentaccapplyV1RequestBiz();
		
		biz_content.setApiName("MOBILEOPENACC");
		biz_content.setApiVersion("001.001.001.001");
		biz_content.setChlType("1");
		biz_content.setFlowId("H000001");
		biz_content.setNotifyUrl("http://KFZXYIZPQ.Intranet.ICBC.COM.CN:19999/notifyurl");
		biz_content.setQrId("00000000");
		
		request.setBizContent(biz_content);
		

	    System.out.println(client.buildPostForm(request));

	}

}
