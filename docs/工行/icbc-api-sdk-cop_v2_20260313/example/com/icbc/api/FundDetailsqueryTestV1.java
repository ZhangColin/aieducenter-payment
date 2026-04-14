package com.icbc.api;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.FundDetailsqueryRequestV1;
import com.icbc.api.request.FundDetailsqueryRequestV1.FundDetailsqueryRequestV1Biz;
import com.icbc.api.response.FundDetailsqueryResponseV1;

public class FundDetailsqueryTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002166";


	public void test_cop() throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		FundDetailsqueryRequestV1 request = new FundDetailsqueryRequestV1();
		request.setServiceUrl("http://ip:port/api/fund/V1/detailsquery");

		FundDetailsqueryRequestV1Biz bizContent = new FundDetailsqueryRequestV1Biz();
		bizContent.setCorpNo("123");
		bizContent.setCustNo("456");
		bizContent.setCardNo("6222080200000021603");//客户卡号
		bizContent.setFundId("000848");//基金代码
		bizContent.setBeginDate("2016-12-31");//开始日期
		bizContent.setEndDate("2016-12-31");//截止日期
		bizContent.setQueryFlag(1);//查询标志1-首次查询 2-上一页 3-下一页          
		bizContent.setQueryRecordNum(5);//每次查询记录数  
		bizContent.setWorkTimestamp("9999-12-31-24.00.00.000000");//记录时间戳（翻页条件，查询标志为2或3时必输）
	    
		request.setBizContent(bizContent);

		FundDetailsqueryResponseV1 response = client.execute(request);

		System.out.println(JSONObject.toJSONString(response));
		if (response.isSuccess()) {
			// 业务成功处理
			System.out.println(response.getReturnCode());
		} else {
			// 失败
		}
	}
}

