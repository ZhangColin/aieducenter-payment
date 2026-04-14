package com.icbc.api;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.junit.Test;

import com.icbc.api.request.MybankAccountEaccountCardfastbindRequestV1;
import com.icbc.api.request.MybankAccountEaccountCardfastbindRequestV1.MybankAccountEaccountCardfastbindRequestV1Biz;
import com.icbc.api.response.MybankAccountEaccountCardfastbindResponseV1;

public class MybankAccountEaccountCardfastbindTest {
		//appid
		protected static final String APP_ID = "10000000000004097490";
		
		//合作方私钥
		protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCrVDMqcC0bMd2NLqkaXLW4Caf9kBXi667h1e8F7Leox7ryyq7uV0d12tItajk7+/OJJQew2WVzcLcAU235cHCEu+1m6skpA7KXxQHdrv6nG/lh0sZdlNZlxPRLZCOY8o1z96TiV2WVClZY/HRBn76yVCHPKscn6T/9V8TD9LKAYbv9VdACUC9iyNyOIjobXC8pEXbPoLkKrvzKPF84Q7Lr2xU0p9D4G44Cg3DA5C3wLZsyMKBG9YLJIeArTiGO4a1vRTjXCeDs6mAZF7EFx9DiedPW1ne6UV9/f9G4lpLOr+qgzEyP7JvoxlHtIm1zIWgyi2kmcqLkxrSsV9HJmsG5AgMBAAECggEBAKpaknWEDJWZw316A9KelGFl2I+bEOgNCFqtaXkuon9D+XMobT+3sOpSpeFaWYZ3QvP+yeLihql+TbgZPKRlVLvmUGug0FvjvVSDIxPVyIdInXLTLUInl/ahG3dLHsoZguToPB6CYBMNsEXS35XQzMBsPyHKmzPoDSROfOuLB2H2KOjT1AfMPGgnET/KJG82tpTWuqVJ76ImDq43aBAN2z+5ryq0S5IRZwNhjMp/Gh0UhngVUlFV+sM1jgv1rR68EvZ7KJl4IpyrjkmJ1o/TFz+A9lATqNGE2tr42q4ux2c3+fJVZB5mP+jvSrAdDXvobiSQqgjbAJXWVyJWwQRrgXECgYEA6kVCoKhjxKF/gR+C/dKA3ExAgnvWYjzHD4DFXfqod+Hbq7pFJZz0YH41THAZ82lIoMSCxtqJfakGPXi4l8WVFAB9hpDhEwK2yv0aFBpLd/kC/Wg66U6Yqxxgwt5wO7BavedEVdfKXaPGVCN4qWo1dSQAdj/UsAljkIw2vRMsbI8CgYEAuzhk6UBvHHciIQblHZrrbHZ26gDFHku1CoOpfFD9eoCi4B5liT85nzzFoccOMKxk1Kox/wxwPytDh2lrf54cpg6RxNR+5vHEaJo46v9HdPupEZuO5yN2Bx+FHYBmdrY+iqu8lLnygpkbAWuJPy7pPREX2QuGi2g43bkli9hfITcCgYEA5pIgT2ssNn0UtwtCq3qhJTFPVJ15lNpouRNuLV7krIj44X3FTI2737SX1JGImQ+IUeLXK8wvAiR3Zc0JMSdf4H3TDbxOKZ3ENukA38Q5fuzwSmRphvK6Q778ZQ70u2qJBNs3YJ2wKJVmP61htqw3zQEQC1IrjM7Q1ZWK0nCqinUCgYASz7rL+Q5xBMNoICI7VEqdblXKc2NbBO5e7RXFiPm/KlezPzqijLjEeD2/YJnbaCcFe0hkwSIyJq21WHFVtFRalxIsfdF2cQp0HDLl4dqTNYoqW8ADF3wPgSpF6ZXiDbPBCTp8kyZ72thszxpOP+9tlF8aDt9o+ILmPGk6YPZGBwKBgCCuEVXL//ubw+SU2tvumSsC3AzUja16Bkd1gX7Pxcr0ZUqP69sEJVKhozn4beGgsR+xU4tBCmtaKEJarBSd9Lzg49e23lMfAsTu1cwgjCQcQtZOHHPMGco347SuVIbj9X+NgOAC+b+vF81p5RvtGNB0Yle6jluhE3PV4NDQkr8z";
		
		//网关公钥
		protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		
		//AES秘钥
		protected static final String AES_KEY = "KLirK1vCE4mIr5XdWA3DzA==";


	public void test_cop() throws IcbcApiException, UnsupportedEncodingException {
		DefaultIcbcClient client = new DefaultIcbcClient(
				APP_ID,
				IcbcConstants.SIGN_TYPE_RSA2, 
				MY_PRIVATE_KEY,
				IcbcConstants.CHARSET_UTF8, 
				IcbcConstants.FORMAT_JSON,
				APIGW_PUBLIC_KEY, 
				IcbcConstants.ENCRYPT_TYPE_AES,
				AES_KEY, 
				null, null);
		MybankAccountEaccountCardfastbindRequestV1 request = new MybankAccountEaccountCardfastbindRequestV1();
		MybankAccountEaccountCardfastbindRequestV1Biz bizContent = new MybankAccountEaccountCardfastbindRequestV1Biz();

		request.setServiceUrl("http://ip:port/api/mybank/account/eaccount/cardfastbind/V1");
//		request.setServiceUrl("https://ip:port/api/mybank/account/eaccount/cardfastbind/V1");
		bizContent.setAppId(APP_ID); //合作方机构编号
		bizContent.setCorpSerno("2104291234567890123456783"); //交易流水号
		bizContent.setInstgId("0157"); //签约人受理机构标识
		bizContent.setSgnAcctId("6222021202047164000"); //账户号，即卡号 
		bizContent.setSgnAcctNm("zs");//账户名
		bizContent.setMobNo("13030301234");//预留手机号
		bizContent.setIdType("0");//证件类型
		bizContent.setIdNo("33092239990909509X");//证件号 
		bizContent.setNote("memo");//备注或备用字段 
		request.setBizContent(bizContent);
		try{
			MybankAccountEaccountCardfastbindResponseV1 response = client.execute(request, UUID.randomUUID().toString().replaceAll("-", ""));
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
