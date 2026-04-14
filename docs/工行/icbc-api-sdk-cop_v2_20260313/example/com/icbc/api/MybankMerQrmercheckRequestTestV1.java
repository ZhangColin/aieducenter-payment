import java.util.logging.Logger;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.MybankMerQrmercheckRequestV1;
import com.icbc.api.request.MybankMerQrmercheckRequestV1.MybankMerQrmercheckRequestV1Biz;
import com.icbc.api.response.MybankMerQrmercheckResponseV1;

public class MybankMerQrmercheckRequestTestV1 {

	public static void main(String[] args) {
		String APP_ID = "10000000000BCSS004097419";

		String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

		String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDGLL9wyxvM9c8RFLOYI1AXZXtRUMESZiqcRlJQeKKb1wnvN9c4l6sSZCaWoLzagddHhND5FRfEVNVzexq5KUVex9Z6ELO4aFPSUyuZWyQcFbPBBbnZrY4JLB1S/ScUcg0wSGdLzaPTYH5ID5bqZc8Ln2z10zuJo6RILsHpptUwlBEWjLFY6u3WjjeGuhpjcSaKZFIhAdBwI4zM6QZU5ZZwpJwCrZ0sJzBdmZVs5aMgyvHAy3lgJ9GepsPx5QsCfLzoQBo09MsBaH6X5a+eF85W8wLYthVvP5e8EUjK37lxCqhFAgyem/oZ4qIKRtRaQ9cgUsYTdDM+uJa5jxzMS4QfAgMBAAECggEAWV4AjurpBIqs8DT3sFnsWkCnVcom5xcvi/2iTSu5mLFmh3wVEC5FNSv4wuCFibwXyua4HjtG1oCzKTqQmINmBxHzRGSXx0c01ZowBR42sHuQ7jCY2PMNJFHCf5QttWihTe64tlRr1nyjNffhxJ5YuhMQ3eJ97Nng0bRUOfvEE/1GdbkNPNrZ2UQ8F6i5T+9Y6MQPiaLKHT9sIBUIjczehUGA7Oki/ir1qXGiKZVv922Y4KX7r5GRDIj5SQ7093aJt0xnMRVb8MC//YymCrzJskpViBIdf1TkREbDqcjlK/GiSzmv4i8wOcwh2Yu3H5GL+6tZi+vJlm1FTQb2uGs2QQKBgQDkFXiUieFfTooJOfUGw+FRqNeb788ink5O9h1yir49uBQJvoJJl2sNhcBEKEdmXkOt3BCdAL+txlJEZ948skyWpESh0fvxzwgarI32dAadA6gZXwltfkcNBvWFm6ZHTub07SKq+RFakoqQDDxtT07E8Dvt8Krb7+PC+tLuf9w1cQKBgQDebiOF1Dds2h12YwqtTP0oS9NJteYOXgHbWurBYTnUJ6osy3QbOziYQ2LtWUWlKlswkyj4fmgDbjgSGg7ciGRjnK5oawpWRtSYQNCZEV96ITVCHqxh8Fx0PsH9JYSbep/lVWDIo+dbLRTstqJLpF/ysv8vqgZuNW02nJ8UDsdKjwKBgGlhojy/F+o5F3370UI27rwUDsp1mnacPgItpJ1vjUZm7psx8nCeDVBKI/NZyON7WjnSG7ruxaE8qHYCp6PTnZ9cdnlt08J9rXjlkRH1ubiCIADY8DouxhU2yenZaJI52mv/xKzKN0Jpwqube6/lnLdRZ98f+1FzHQDRcvPPD6eBAoGBAKP4HUeywYkE4WM8b8qBTr4952wXI5jDxCqoQxZ7Td4Pv2t2MAlDBt14D51YifbP2plcYn+M77BXuA3gF8SK5zZnckqXXe9sF5yFa0Drha7KfDBZ3En2nrEy8l5jIfZb1zOAVPCToyGbgileGRYZ/oEzYtsIQ+VKjVAM9Vgb7N4RAoGAenReOdJ1j9lzHb6xeknTzNldKCf0i1VVjBvC6j+QbKCi0EXeJJxt5ysPpFtmhxF/HeXoj9qtb4a3qi3oBesc1HEQYuZAMtAduN1uTd6UFdRQKw1nrf5aap1fpI523vOryq9rzYFY2yBmfFvNV9iWreGqw294Jkj4thl1g36v2Tc=";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);

		MybankMerQrmercheckRequestV1 request = new MybankMerQrmercheckRequestV1();

		// 请根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/mybank/mer/qrmercheck/V1");

		MybankMerQrmercheckRequestV1Biz bizContent = new MybankMerQrmercheckRequestV1Biz();

		// set request params
		bizContent.setServiceMerNo("020001010007");
		bizContent.setMerchantNo("020006024127");
		bizContent.setPhoneNo("12345678911");

		// if use dept_no to query, set the items below instead
		// bizContent.setServiceMerNo("020001010007");
		// bizContent.setDeptNo("020099990079156");
		// bizContent.setPhoneNo("13713841296");

		request.setBizContent(bizContent);
		request.isNeedEncrypt();

		String msg_id = String.valueOf(System.currentTimeMillis());
		Logger log = Logger.getLogger(MybankMerQrmercheckRequestTestV1.class.getName());

		MybankMerQrmercheckResponseV1 response;
		try {
			response = client.execute(request, msg_id);
			log.info("return_code:" + response.getReturnCode() + " | return_msg:" + response.getReturnMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
