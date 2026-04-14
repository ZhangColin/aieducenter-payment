package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.CborderNoticeRmbtradedetailExtractRequestV1;
import com.icbc.api.request.CborderNoticeRmbtradedetailExtractRequestV1.CborderNoticeRmbtradedetailExtractRequestV1Biz;
import com.icbc.api.response.CborderNoticeRmbtradedetailExtractResponseV1;

public class CborderNoticeRmbtradedetailExtractTest {

	//应用私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQoszs+J4p7BpfegRxVzh7j5AhZZ2MEDggoQESGDOQBNN7u5jfNP0kJhUlUFkhGSPeQehqoA386ipxv7dQN57fGe1hENg19KZlDxFvFB6r06VaF6tlhWdnJ8J7GdlNE225MHArsYCgUjYhvt5orD3xEaVZ4gYL8Hg5aeHCkstbkEavh+OecTZqj8Fjq/N0v6Tig4KMIbZCSGTDzrDy1IdxeWNJcHClsOXsn6S1L7eXYTt6/Vz94EmslrEtAw4NWbKMz9CXv8APtI630c5t1VfyDh9VNlHIQLPBKhZrv608ILOMv5eSEpMrngwCs4kaXOynqMl4QEeE2g9fx6VN/14BAgMBAAECggEAHnL3m2fXvFGlV7ZTWx0WmgSmPjGrUkJT3qKmnA2WRVqOvzzGOS9pn4OY7gsVSH+HapILgO8DItyVZO8Mg4nCG4vUypuGOQ2xPhNHA1CECH59EBNtlOjZ1QCs08ekVLKjZdpJsxkiUB4eoUoSfozMWYTyf5Ky3S6Z0NR2n8TRpQvOYC3L+J9FCJZpCD86g6lc79OMipZe+7GsptSqCcgQv9waN+xAzUTviG1raocfHLHYrLGidsAco8Acx3dGphIeciQjn2sxz9j6bJ7VTtB+/KlckJlaRdmeM+265Vx/bwVnbciyXt8EtVA2A8ETvR1tdhg6HPJItm+5hf3YOXXkwQKBgQDzc6L13Y3ayTafFKcZQfHf804wW/gwM/J2Xac4Rru/kx/TrcDwC+Z9aXj9i8/aJd+64sZcPf5PHyOnOsjxS9ScUnJaCef8J5+8WhgAwNiOYtdUxHo7PTlgnbSGYQLZIQxX4iIRcyZ7ZJ/nLsOx0DzZDNyUwvkR4L7k7COVQAPGeQKBgQCYF0hS4z8/Wv+1BwpLFm+TAAKHpFENomizxnKQnkDGYrQk0zYRCjNDzePzlvLrDCRZv9xSqWI40yoI2/qUJj1J9C4doRsm4HpPrIEoNf/aQXtPTo0qWxXL5rsScW5PALwacWifFb+8e1P0Go35Wj8NZc+ieY/D1NKi0lsoNQ6RyQKBgQCDqDuu3zxB36MscMzVOfaGeFwGhxY3a0BqhsdSBd6vAe+qWEMJDkZ9+DY4WA32GZlexhkj9Vq8i0iKvZ3B8JJwX53713OfyAX4pHhqn9/9ULLG4uJ/euK2v1LLgVc6+UZlmKN38GAVRxu6uG2Ykdb2QqBfyvlLk9Fol9O+eToRcQKBgEGxrNfAuM0Q7WqwQ83zPvZsBqu4WhpdkIQ2r72v3aLTd6SAZtYtZb9f65qIFR1Z/UmXCf/akkahT4LMIgxkCA0tn83EjQnZ5HsNtCw+RulrZ2v2LhuhhGSyoRNyU4dkddumKN6uckdjrfu+jJysUpfi5jDz+RlJkEIsQpu3bzehAoGBAMnOz2Jnntj5S6AxDM/l0a4rtFznw131bNoIVdNA31bX4fsW8AcL3t13JZHmJ0bYyIQqmawIOkLpXUEDPZXEpgMAOV6dQZQQvaHOJ8pcyOoRTY7Q3eraXOuPSfjjDvqVd7gmwIiMzig0IArzZHrrU6SVB/otJ/ktQFHH+2gys/B8";
		
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//应用ID，暂定，APP申请分配
	protected static final String APP_ID = "10000000000000003904";


	public void test_cop() throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CborderNoticeRmbtradedetailExtractRequestV1 request = new CborderNoticeRmbtradedetailExtractRequestV1();
		request.setServiceUrl("http://ip:port/api/cborder/notice/rmbtradedetail/extract/V1");

		CborderNoticeRmbtradedetailExtractRequestV1Biz bizContent = new CborderNoticeRmbtradedetailExtractRequestV1Biz();
		bizContent.setVersion("1.0");
		bizContent.setFunction("tradeReg");
		bizContent.setReqTime("20150101010000");
		bizContent.setReqTimeZone("UTC+8");
		bizContent.setReqMsgId("1234567asdfasdf1123fda");
		bizContent.setReserve("");
		bizContent.setClientId("123456789012345");
		bizContent.setBatchSerialNo("1234567890123452015102110000100001");
		bizContent.setTransTime("20151021100000");
		bizContent.setDetailFileName("TRADEDTL_1234567890123452015102110000100001.zip");
		bizContent.setDetailFileDigest("c01f4da1768bcdc39f8eba023b611eb4");
		

		request.setBizContent(bizContent);

		CborderNoticeRmbtradedetailExtractResponseV1 response = client.execute(request);//请求通讯唯一编号
		if (response.isSuccess()) {
			System.out.println(response.getResultStatus());
		} else {
			System.out.println(response.getReturnCode());
			System.out.println(response.getReturnMsg());
		}

	}
}
