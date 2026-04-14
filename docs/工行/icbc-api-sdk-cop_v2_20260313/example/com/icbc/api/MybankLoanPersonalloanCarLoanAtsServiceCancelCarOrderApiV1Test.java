package com.icbc.api;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.icbc.api.request.MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiV1;
import com.icbc.api.request.MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiRequestV1.MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiV1Biz;
import com.icbc.api.response.MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiResponseV1;

public class MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiV1Test {
		//appid
		protected static final String APP_ID = "10000000000004097490";
		
		//合作方私钥
		protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCrVDMqcC0bMd2NLqkaXLW4Caf9kBXi667h1e8F7Leox7ryyq7uV0d12tItajk7+/OJJQew2WVzcLcAU235cHCEu+1m6skpA7KXxQHdrv6nG/lh0sZdlNZlxPRLZCOY8o1z96TiV2WVClZY/HRBn76yVCHPKscn6T/9V8TD9LKAYbv9VdACUC9iyNyOIjobXC8pEXbPoLkKrvzKPF84Q7Lr2xU0p9D4G44Cg3DA5C3wLZsyMKBG9YLJIeArTiGO4a1vRTjXCeDs6mAZF7EFx9DiedPW1ne6UV9/f9G4lpLOr+qgzEyP7JvoxlHtIm1zIWgyi2kmcqLkxrSsV9HJmsG5AgMBAAECggEBAKpaknWEDJWZw316A9KelGFl2I+bEOgNCFqtaXkuon9D+XMobT+3sOpSpeFaWYZ3QvP+yeLihql+TbgZPKRlVLvmUGug0FvjvVSDIxPVyIdInXLTLUInl/ahG3dLHsoZguToPB6CYBMNsEXS35XQzMBsPyHKmzPoDSROfOuLB2H2KOjT1AfMPGgnET/KJG82tpTWuqVJ76ImDq43aBAN2z+5ryq0S5IRZwNhjMp/Gh0UhngVUlFV+sM1jgv1rR68EvZ7KJl4IpyrjkmJ1o/TFz+A9lATqNGE2tr42q4ux2c3+fJVZB5mP+jvSrAdDXvobiSQqgjbAJXWVyJWwQRrgXECgYEA6kVCoKhjxKF/gR+C/dKA3ExAgnvWYjzHD4DFXfqod+Hbq7pFJZz0YH41THAZ82lIoMSCxtqJfakGPXi4l8WVFAB9hpDhEwK2yv0aFBpLd/kC/Wg66U6Yqxxgwt5wO7BavedEVdfKXaPGVCN4qWo1dSQAdj/UsAljkIw2vRMsbI8CgYEAuzhk6UBvHHciIQblHZrrbHZ26gDFHku1CoOpfFD9eoCi4B5liT85nzzFoccOMKxk1Kox/wxwPytDh2lrf54cpg6RxNR+5vHEaJo46v9HdPupEZuO5yN2Bx+FHYBmdrY+iqu8lLnygpkbAWuJPy7pPREX2QuGi2g43bkli9hfITcCgYEA5pIgT2ssNn0UtwtCq3qhJTFPVJ15lNpouRNuLV7krIj44X3FTI2737SX1JGImQ+IUeLXK8wvAiR3Zc0JMSdf4H3TDbxOKZ3ENukA38Q5fuzwSmRphvK6Q778ZQ70u2qJBNs3YJ2wKJVmP61htqw3zQEQC1IrjM7Q1ZWK0nCqinUCgYASz7rL+Q5xBMNoICI7VEqdblXKc2NbBO5e7RXFiPm/KlezPzqijLjEeD2/YJnbaCcFe0hkwSIyJq21WHFVtFRalxIsfdF2cQp0HDLl4dqTNYoqW8ADF3wPgSpF6ZXiDbPBCTp8kyZ72thszxpOP+9tlF8aDt9o+ILmPGk6YPZGBwKBgCCuEVXL//ubw+SU2tvumSsC3AzUja16Bkd1gX7Pxcr0ZUqP69sEJVKhozn4beGgsR+xU4tBCmtaKEJarBSd9Lzg49e23lMfAsTu1cwgjCQcQtZOHHPMGco347SuVIbj9X+NgOAC+b+vF81p5RvtGNB0Yle6jluhE3PV4NDQkr8z";
		
		//网关公钥
		protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";



	public void test_cop() throws IcbcApiException, UnsupportedEncodingException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiRequestV1 request = new MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiRequestV1();
		MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiV1Biz bizContent = new MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiV1Biz();

		request.setServiceUrl("http://ip:port/api/mybank/loan/personalloan/CarLoanAtsService/cancelCarOrderApi/V1");

		bizContent.setSerialNo("2024100814250001123456614");
		bizContent.setAppNo("F-GCMS");
		bizContent.setAreaCode("0020000072");
		bizContent.setEmployeeCode("888800135");
		bizContent.setLanguage("zh_CN");
		bizContent.setTransNo("PsnProtAcsService");
		bizContent.setVer("1.0");
		bizContent.setTurnPageFlag("");
		bizContent.setBeginRow("");
		bizContent.setRowCount("");
		bizContent.setOperType("1");
		bizContent.setMforderno("SUP20231012001");
		// 将 bizContent 转换为 JSON 字符串
		String bizContentJson = JSON.toJSONString(bizContent);
		bizContent.setBiz_content(bizContentJson);
		request.setBizContent(bizContent);
		try{
			MybankLoanPersonalloanCarLoanAtsServiceCancelCarOrderApiResponseV1 response = client.execute(request, UUID.randomUUID().toString().replaceAll("-", ""));
			if (response.isSuccess() ) {
				System.out.println(response.getReturnCode());
			} else {
				System.out.println(response.getReturnCode());
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
