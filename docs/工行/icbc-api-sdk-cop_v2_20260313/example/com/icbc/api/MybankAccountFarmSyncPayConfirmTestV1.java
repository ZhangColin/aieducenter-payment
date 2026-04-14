package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankAccountFarmSyncPayConfirmRequestV1;
import com.icbc.api.response.MybankAccountFarmSyncPayConfirmResponseV1;

public class MybankAccountFarmSyncPayConfirmTestV1 {
protected static String APP_ID = "10000000000004096993";
	

	private  static String BASE_URL = "http://ip:port/api";
	

	protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCR8/ZvKPAdZzsyvapySvztQm56s1N59ynKMOWpUbK4c5MNWMl+q3dvsp+UiSAx2TAeGkYfW3W6nO/3Y0hAZki99fmuzpPujEeHhs79HNwGZYQjN71Vck2JeflTq8jpL+9/up0Kz2nbwtZDMKTTEgbfNeO24gV1bmvD2kQ9j66RIyuXSDwQbVbQfl6LiqKoJm3rbtsbwX1Ytc0/Szslyor1VdPZWnHDMm3m78Hqu7X3vL6K7fAW/4FVbeKV7vWjvyiTQfETmuADqMdsqV5YeqUZWE/Dnzg+6JV/3L9nJ8f+/mPlh8k1h1oW035GrADKFKf1M2ujKKeHICnj7qKCdBeNAgMBAAECggEAHkh+U2YtHAy1Tbvox7ojbJ8iCTd4FJBiDV/D5zPaX0crtdM8S5oMOBLZ5ZnmIjGsODK/ZfY2ITg62huxfBs88J0+5zRZoV9d4BLqk74PMQyTNDN2h2omCGZUgzXbg/a8PMZdm0aZ8k0k4+AN8vWEk3+89c9Dzq/QkFyTWCqdz+Mp9NkcOjA255kj5/D1q9Zj0x9VcmKg3oTtrKL/dkspUxVaMKXcpo6J4AszC05tT3N0SNLhcq51I6B9QdbYsYCkP9whPNYIl/y4dN4QnNEivQzA5/ltr5DDQZc7Xke1+SpJN+ylBOBJ8yvNoDnuxx0xEWuNJ5bUzyri/DNjZRKNQQKBgQDKkxn5R4Q3rChI/KVIXHMecTs9fXN5pbnA8UKU6ZeTl+wHffxwRieHZJJp1nDcQ5i8YXCtqEJcJGFn3T0rEjVCJ/RVHclFO+TjYaK9HtyNJLPrvKmAzjCzV1yPfe4qmpgJddzZI7Vii2uFgklixvhkoY74hwSJtvLPTO0p0xrYdQKBgQC4cgfpCMLYlaHQGz+dAEf4IewQpwBGn7XShCpiXGFciGZZEIzvJMzXP7yo8pDMHQbB4kQsTRRG2fcdHGWI5VQGHPqG5O0tUueKGUlSg7j8Y/Pp8ZrBGSrlilAf17I/u9MC7Xe2ZRVGNgPDYAyjMEGmClI0n2+aN6b4CFVBjYmfuQKBgEKO9KDIE7QrF41rnW7aGWTuNVWty2wzvIWdf4/n9EqlRwLrLS9CjahZrhWiRLDKcPusVFZqi2s09OAoe/mT4PXcpNX2lHPwCvN+1/allje10HvrIBJXLP8v/BSVftR2uO+azzZ1GhrHzksulKgk0eZWguA7lI0fFEZycxYj65UlAoGAD9p1RZlkLfuGgf2llRgOF4zK3o+MHYXiuep0PioUkECFE4ixpGh0Vtf6nkbjHTgteYK6O1iQsppPfCgRrheQBkp9WhTZMfkbP6p2u+nof4ET2PrUQ16naj1eL655erLpKypADORZVMSVxDhAPdKLAfuHH1DI5ed8qXsF4PGKb7kCgYEAm/d+daT6YsbHDZlJ/J9Q8rRkKmiqj43NGQSHKg6Z6BEDibm8wRmj3Itu1N6XVChuaH+ekJzvUnZ/q1nyYzGvy6bOHYn3ziF9aH7wuhcRZ4qARmKDnzTBLg2QXBK1+400O3LJ+sAH/yuH/Y4hzRE6YMxBQpdYfnlJcIFVimAsT1w=";
     





	public static void main(String[] args) throws Exception {
		String URI = "mybank/account/farm/syncPayConfirm/V1";

		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", PRI_KEY, APIGW_PUBLIC_KEY);
		try {
			MybankAccountFarmSyncPayConfirmRequestV1 request = new MybankAccountFarmSyncPayConfirmRequestV1();
			MybankAccountFarmSyncPayConfirmRequestV1.MybankAccountFarmSyncPayConfirmRequestBizV1 requestBizV1 = new MybankAccountFarmSyncPayConfirmRequestV1.MybankAccountFarmSyncPayConfirmRequestBizV1();

			requestBizV1.setfSeqNo("1234567890");
			requestBizV1.setTransDate("20220202");
			requestBizV1.setCorpCode("icbc");
			requestBizV1.setParamType("1");
			requestBizV1.setDetail("{\"busiNo\":\"ZZ20220125000000027304\"}");

			request.setServiceUrl(BASE_URL +"/"+ URI);
			request.setBizContent(requestBizV1);
			
	        System.out.println(JSON.toJSONString(request));

	        MybankAccountFarmSyncPayConfirmResponseV1 response = client.execute(request);
			if (response.isSuccess()) {
				System.out.println("success");
				System.out.println(JSONObject.toJSONString(response));
			} else {
				System.out.println("error");
				System.out.println(JSONObject.toJSONString(response));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
