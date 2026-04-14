package com.icbc.api;

import org.junit.Test;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CardbusinessAggregatepayApopenidqueryRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayApopenidqueryRequestV1.CardbusinessAggregatepayApopenidqueryRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayApopenidqueryResponseV1;

public class CardbusinessAggregatepayApopenidqueryTest {
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000012177";
	
	
	public void test_cop() throws IcbcApiException{
		
	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessAggregatepayApopenidqueryRequestV1 request = new CardbusinessAggregatepayApopenidqueryRequestV1();
		request.setServiceUrl("http://ip:port/api/cardbusiness/aggregatepay/apopenidquery/V1");
		CardbusinessAggregatepayApopenidqueryRequestV1Biz bizContent = new CardbusinessAggregatepayApopenidqueryRequestV1Biz();
		request.setBizContent(bizContent);
		
		bizContent.setMersepNo("020001020461");
		bizContent.setSubAppId("wx9435043c361123");
		bizContent.setOauthCode("289858740300888013");
		
		CardbusinessAggregatepayApopenidqueryResponseV1 response;
		
		try {
			response = client.execute(request);
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnMsg());
			} else {
				// 失败
				System.out.println(response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
		}

}
