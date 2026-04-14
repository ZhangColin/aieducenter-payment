package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.VisaRemitQueryRequestV1;
import com.icbc.api.request.VisaRemitQueryRequestV1.VisaRemitQueryRequestV1Biz;
import com.icbc.api.response.VisaRemitQueryResponseV1;

public class VisaRemitQueryTest {

	// 应用私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI0HT4G0ZNwZBt6IpQbeyxcgyIPSaHYc4XLhYItX/GbZJNbYWhzSgGWDixR9nFbexuT1MZ68vmHxNkwz35QqsX9gPbOTfNJPlDJkwO3R2UmYphilm8aU7/uiSkCZGf1zqdW/6fcWr88vSJg+3Rgtrje+BhuYd2GUnvSC8uDlaqfPAgMBAAECgYBz0+dG/iRAaQWtS8lOZTmkGzbUncFSENJRii3DBFa3Ge0Zz8PjHDh8J5fZEnmPF5V/gqpoV1h+FKCl4YzlolL4s8lhlROfiP8sH4fbCSr8cjcwERKJ+xK8ES71AtH+jpB0GiG7AI7+5jmmBxdVnH7HAf1DK30BagqPaHEpELcNsQJBAM0boc+JTwQFvq+tgTauiQFDkjlFz5yevfRGVNaqCEnBQVV3Nocg8OH2AhNK/hBecPbYHHIZmt9HcSzT0OfFXB0CQQCwBWJBQiCy7lHGMqPu7kQ5WYQIzrdazXRWO4S3NU8PaZSCFjGaAJ30x/ShtHTcbXJ6VuJCCUkAJTcrAnips1fbAkBeV+Rw8sauofnZEuTJgzSvqSYcQ/lAIGBdePBTITjYvBTSPBiPxV4ngaHov+jnHJm3YSaMcSykoVb0eKWxxj0dAkEAp2vHg+ig73AMaab7FCSe0amaE0KDVaO7ttWPqy7STweOUJlJfoB9vTh/L1tUF+EmFMUVGdh5DeFX8tm76TWDJwJAVuRHH26GNTTieQSZF3NFVDT/kogb+RXGAvjwQ5BJx/uJiBfGBaSFJnV2Y7ocuxU24VySiZZgn62hXVPICyLl8Q==";

	// 网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	// 应用ID，暂定，APP申请分配
	protected static final String APP_ID = "10000000000000002130";


	public void test_cop() throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		VisaRemitQueryRequestV1 request = new VisaRemitQueryRequestV1();
		request.setServiceUrl("http://ip:port/api/visa/remit/V1/query");

		VisaRemitQueryRequestV1Biz bizContent = new VisaRemitQueryRequestV1Biz();
		bizContent.setVersion("1.0");// 版本
		bizContent.setMsgtype(1002);// 汇款方预查询
		String msgno = "2934723742399924";
		bizContent.setMsgno(msgno);
		bizContent.setTimestamp("20170105161223256");// yyyyMMddhhmmssSSS
		bizContent.setCorpid("201608220012004");// 腾讯在银行企业编号
		bizContent.setUsername("3o49238");// 汇款人姓名
		bizContent.setIdtype(0);// 0-身份证
		bizContent.setUserid("310227198312244216");// 身份证号

		request.setBizContent(bizContent);

		VisaRemitQueryResponseV1 response = client.execute(request, msgno);// 请求通讯唯一编号
		if (response.isSuccess()) {
			if (0 == response.getReturnCode()) {
				System.out.println("available_quota:"
						+ response.getAvailablequota());
				System.out
						.println("sender_status" + response.getSenderstatus());
				System.out.println("kyc_aml_result"
						+ response.getKycamlresult());
			}
		} else {

			System.out.println(response.getReturnMsg());
		}
	}
}
