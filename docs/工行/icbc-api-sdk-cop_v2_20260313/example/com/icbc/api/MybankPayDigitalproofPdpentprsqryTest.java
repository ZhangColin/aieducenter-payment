package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.MybankPayDigitalproofPdpentprsqryRequestV1;

public class MybankPayDigitalproofPdpentprsqryTest {

	/**
	 * @param args
	 */

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000001534554";
	public static void main(String[] args) throws Exception  {

		// 构造 client对象
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

		// 设置 请求对象request
		 MybankPayDigitalproofPdpentprsqryRequestV1 request = new  MybankPayDigitalproofPdpentprsqryRequestV1();
		// 设置 请求路径
		request.setServiceUrl("http://ip:port/mybank/pay/digitalproof/pdpentprsqry/V1");

		//构造 biz_content
		 MybankPayDigitalproofPdpentprsqryRequestV1.MybankPayDigitalproofPdpentprsqryRequestV1Biz bizContent = new  MybankPayDigitalproofPdpentprsqryRequestV1.MybankPayDigitalproofPdpentprsqryRequestV1Biz();

		  bizContent.setApiName("DIGITAL_PROOF");
			bizContent.setApiVersion("001.001.008.001");
			bizContent.setChainid("1");
			bizContent.setEnterid("20210920162925000000000600000002");
			bizContent.setCorpuserid("11");
		  bizContent.setTranswitch("0");
		  request.setBizContent(bizContent);
		
		// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
		System.out.println(client.buildPostForm(request));
	}

}
