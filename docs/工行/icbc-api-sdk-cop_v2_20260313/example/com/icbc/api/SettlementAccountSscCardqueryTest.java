package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.SettlementAccountSscCardqueryRequestV1.SettlementAccountSscCardqueryRequestV1Biz;
import com.icbc.api.request.SettlementAccountSscCardqueryRequestV1;
import com.icbc.api.response.SettlementAccountSscCardqueryResponseV1;
import org.junit.Test;

import java.util.UUID;

// 社保卡卡片信息查询接口：
// /settlement/account/ssc/cardquery/V1
// http://ip:port/icbc/cocoa/apip/com.icbc.prsa.app.service.corp.ATSCorpSocialService/1.0/sSCardQuery
public class SettlementAccountSscCardqueryTest {

	// MY_PRIVATE_KEY：签名私钥、APIGW_PUBLIC_KEY：API网关公钥（测试环境是固定的）、SIGN_TYPE：密钥类型
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCK+1L6ay9J/BX2N1mb6qTpmMK+G2m+GXLQ7zje7wdHOUyyoNLPZ476sbdFwvRYPvVvS+twsTibjhFriR2tJdtYWtqfgqR2QsjHltuIIMaFJyR2Tzzyb4gG4M8DcwsGRb7v24bYVlm8STFvVCx6sATTEO1WW6EQuBvXCsqDKWYcPdVlj6EGTaGILWydblvuTfIVp4HL4k2pClWkzbEw/JHkSDJfxrMSX3+ShsfRYcC+AfQJmcT0fouDQPVhQaIv7sWrGWGZZCwbo/+TpYfKLo8Hvdgf5nJNGm1q7Z6Ibh2CwaV5wEfWsg2EPsuNvzlmF4Su1u7eA5hSp1EdMaHaze9AgMBAAECggEAZy3AYvqu1CRTKp6YJ3j9oRkyymBOMhoUPcDMeRf77KuZtN+2oC4QPXgD9v0sfeM82pehsj5+5ufsrLay6nCM0sN0bC3J3P9IB6cpW6pjoImT7TtfR/GyRSrpo/Ff09hzUkrTUNqiDam4txAvZUcoxmMeDCH+KtIDqeFJyYPKwoAnvvHF1y7w5eHUiHt25mZyFgjzYKGYWLWNWWl5XwBVB5zqoeZ8kV0o/iliW6MR7U5k2exOBzKUWuu8e+diSvTNmL5pQkgQJAdd0o21gOdL2VkB6i/KQoNIk1vFE6iJNG2qiPY7f/r2RxEOXFBZugUQvuYaPnhj/Bd6wGRrnPWxwQKBgQDkI8DchTPoK6MVw7oPnnABMv0yPWGkKlGHKSU1Ctn1/ZFVQLerfWcjxLE5m0knmoZkC/h8Vjpf+OASSPp668L+C8AcHZG8eiHuncVkW5zfdzBBqZGOhs7vmQ1zdW7KERSceuuJWUsuJgsFmVlMMOfHTAl2AKH4IRe+IvaTsRdwcQKBgQCSEW8DLycbDENugxQnxUPAH9GhUcLFqNl+32s0JzN1WR0rlXfjVggBnHpHoC1o0FnvTXw9TvVSfxi7Pjv9kGK5BgjGralchx9i39iIFhuVdRGBftb1ZFLc9hz+3tfbr/+Zww+ruYMAeSmi+tRs291CJ+rObo+aXabUn+/y7sOiDQKBgQCAG3eUHRRNvhtBAHx9gsmAUuKNEJzAs6a+UCN9io9kK7QtfD7nBG8jTbL7FzDYFVR736D0KPN0qusgOWy2E6eWQoDpCM2qWaauOFf8h9kqPuKRLKf4sciIEJjWCc5hl3/MctXKdaLa0jAEP90+BIRSjGcPpUFOdT7A3R87RglUcQKBgDmxMxSh4iM3w8Z5dVOEAY93wdeisHhbiZQn8HLH7PYzfWcCgTjEeSMRx+ZUaLsWV/9BromUjybHVmBWY2FyTtvfe0Xlv5fxMr9dxGtzt+9fLsvj6klAF854UtmD8OQvbpgvyBe+jPR55q0dvxtD9+8yTva8CnIg/UzL4v0juxddAoGAJW34dkumF7unNNghVZLXq3UzFbbgQHwLmcZzi/PDohy9LvTx7JZul4lfWpxbvJ0q0PN5DCCA0u9wcWmZqzlaEkGZ1uMTenQ566DU6+dIcieq8Swy47IN7kDEsV1fgbdeA94WZf8xWwrLkwQeUTIxiboS0cU84AnrMUX/VBz+saA=";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String SIGN_TYPE = "RSA2";

	// APPID
	protected static final String APP_ID = "10000000000004097698";


	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        SettlementAccountSscCardqueryRequestV1 request = new SettlementAccountSscCardqueryRequestV1();
        SettlementAccountSscCardqueryRequestV1Biz bizContent = new SettlementAccountSscCardqueryRequestV1Biz();

		// ip:port是8月版功能环境的API网关地址，实际在生产使用时不需要手动设置，因为已经在new SettlementAccountSscOpenprecheckRequestV1()的过程中设置了正式的生产环境地址。
		request.setServiceUrl("http://ip:port/api/settlement/account/ssc/cardquery/V1");

		// 封装请求报文
        bizContent.setCorpNo("10000000000004095100");		 	//合作方机构编号
		bizContent.setCorpSerno(UUID.randomUUID().toString());  // 合作方交易单号
		bizContent.setCustName("九九九九九九九九九九九九九九九");
		bizContent.setCertType("0");
		bizContent.setCertNo("999999199009099991");
		bizContent.setMediumId("6222020000000000001"); 	//介质号
		bizContent.setCorpType("001"); 					//合作方种类

        request.setBizContent(bizContent);

		// 发起请求
		long msgId = System.currentTimeMillis();        // msgId用于交易防重，生产使用时，请传入对应的唯一ID
		System.out.println("request: "+ JSON.toJSONString(request));
        SettlementAccountSscCardqueryResponseV1 response = client.execute(request, String.valueOf(msgId));
		System.out.println("resonse: "+ JSON.toJSONString(response));
        
        if (response.isSuccess() ) {
            // 业务成功处理
        } else {
			// 业务失败处理
        }
	}

}
