package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.VisaReceiveQueryRequestV1;
import com.icbc.api.request.VisaReceiveQueryRequestV1.VisaReceiveQueryRequestV1Biz;
import com.icbc.api.response.VisaReceiveQueryResponseV1;

public class VisaReceiveQueryTest {
	//应用私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI0HT4G0ZNwZBt6IpQbeyxcgyIPSaHYc4XLhYItX/GbZJNbYWhzSgGWDixR9nFbexuT1MZ68vmHxNkwz35QqsX9gPbOTfNJPlDJkwO3R2UmYphilm8aU7/uiSkCZGf1zqdW/6fcWr88vSJg+3Rgtrje+BhuYd2GUnvSC8uDlaqfPAgMBAAECgYBz0+dG/iRAaQWtS8lOZTmkGzbUncFSENJRii3DBFa3Ge0Zz8PjHDh8J5fZEnmPF5V/gqpoV1h+FKCl4YzlolL4s8lhlROfiP8sH4fbCSr8cjcwERKJ+xK8ES71AtH+jpB0GiG7AI7+5jmmBxdVnH7HAf1DK30BagqPaHEpELcNsQJBAM0boc+JTwQFvq+tgTauiQFDkjlFz5yevfRGVNaqCEnBQVV3Nocg8OH2AhNK/hBecPbYHHIZmt9HcSzT0OfFXB0CQQCwBWJBQiCy7lHGMqPu7kQ5WYQIzrdazXRWO4S3NU8PaZSCFjGaAJ30x/ShtHTcbXJ6VuJCCUkAJTcrAnips1fbAkBeV+Rw8sauofnZEuTJgzSvqSYcQ/lAIGBdePBTITjYvBTSPBiPxV4ngaHov+jnHJm3YSaMcSykoVb0eKWxxj0dAkEAp2vHg+ig73AMaab7FCSe0amaE0KDVaO7ttWPqy7STweOUJlJfoB9vTh/L1tUF+EmFMUVGdh5DeFX8tm76TWDJwJAVuRHH26GNTTieQSZF3NFVDT/kogb+RXGAvjwQ5BJx/uJiBfGBaSFJnV2Y7ocuxU24VySiZZgn62hXVPICyLl8Q==";
		
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//应用ID，暂定，APP申请分配
	protected static final String APP_ID = "10000000000000002135";


	public void test_cop() throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		VisaReceiveQueryRequestV1 request = new VisaReceiveQueryRequestV1();
		request.setServiceUrl("http://ip:port/api/visa/receive/V1/query");
		
		String msgNo = "20161027104045111";

		VisaReceiveQueryRequestV1Biz bizContent = new VisaReceiveQueryRequestV1Biz();
		//bizContent.setUniseqno("msgId");
		bizContent.setVersion("1.0");//版本
		bizContent.setMsgtype(1003);//收款方预查询
		bizContent.setMsgno(msgNo);
		bizContent.setCorpid("0001");//腾讯在银行企业编号
		bizContent.setTimestamp("20161027104345111");//yyyyMMddhhmmssSSS
		bizContent.setCountry("CHN");
		bizContent.setCurrency("RMB");
		bizContent.setUserName("Test");
		bizContent.setCardNo("4380420004567111");

		request.setBizContent(bizContent);
		VisaReceiveQueryResponseV1 response = client.execute(request, msgNo);
		
		if (response.isSuccess()) {
			
			if(0 == response.getReturnCode()){
				System.out.println( response.getCardAvailable());
				System.out.println( response.getRemitSupport());
				System.out.println( response.getKycamlresult());
				System.out.println( response.getKycamlInfo());
				System.out.println( response.getFastSpeed());
			}			
		} else {
			System.out.println(response.getReturnCode());
			System.out.println(response.getReturnMsg());
		}

	}
}
