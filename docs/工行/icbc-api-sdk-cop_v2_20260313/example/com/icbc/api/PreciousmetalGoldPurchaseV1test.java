package com.icbc.api;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.PreciousmetalGoldPurchaseRequestV1;
import com.icbc.api.request.PreciousmetalGoldPurchaseRequestV1.PreciousmetalGoldPurchaseRequestV1Biz;
import com.icbc.api.response.PreciousmetalGoldPurchaseResponseV1;

public class PreciousmetalGoldPurchaseV1test {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002166";


	public void test_cop() throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		PreciousmetalGoldPurchaseRequestV1 request = new PreciousmetalGoldPurchaseRequestV1();
		request.setServiceUrl("http://ip:port/api/preciousmetal/gold/V1/purchase");

		PreciousmetalGoldPurchaseRequestV1Biz bizContent = new PreciousmetalGoldPurchaseRequestV1Biz();
		bizContent.setCustNo("ABC123456");		
		bizContent.setCustName("小肥羊");
		bizContent.setCertType(0);
		bizContent.setCertNo("44528111111111");
		bizContent.setMobileNo("15915822105");
		bizContent.setTrxSeqNo("8888811");
		bizContent.setPurchaseQuantity(10);
		bizContent.setPurchasePrice(222);
		bizContent.setPurchaseAmt(2220);
		bizContent.setLockPriceTs("2016-04-ip:port.762126");		
		bizContent.setFeeRate(1000000);
		bizContent.setFee(22);
		bizContent.setTrxReqTs("2016-04-ip:port.762126");
	    
		request.setBizContent(bizContent);

		PreciousmetalGoldPurchaseResponseV1 response = client.execute(request, "msgId");

		System.out.println(JSONObject.toJSONString(response));
		if (response.isSuccess() && response.getSubFlag() == 1) {
			// 业务成功处理
			System.out.println(response.getCustNo());
		} else {
			// 失败
		}

	}
}

