package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankAccountFarmFundQueryInfoRequestV1;
import com.icbc.api.response.MybankAccountFarmFundQueryInfoResponseV1;

public class MybankAccountFarmFundQueryInfoTest {

    /*
	protected static String APP_ID = "10000000000000002225";//10000000000000002225
	protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCR8/ZvKPAdZzsyvapySvztQm56s1N59ynKMOWpUbK4c5MNWMl+q3dvsp+UiSAx2TAeGkYfW3W6nO/3Y0hAZki99fmuzpPujEeHhs79HNwGZYQjN71Vck2JeflTq8jpL+9/up0Kz2nbwtZDMKTTEgbfNeO24gV1bmvD2kQ9j66RIyuXSDwQbVbQfl6LiqKoJm3rbtsbwX1Ytc0/Szslyor1VdPZWnHDMm3m78Hqu7X3vL6K7fAW/4FVbeKV7vWjvyiTQfETmuADqMdsqV5YeqUZWE/Dnzg+6JV/3L9nJ8f+/mPlh8k1h1oW035GrADKFKf1M2ujKKeHICnj7qKCdBeNAgMBAAECggEAHkh+U2YtHAy1Tbvox7ojbJ8iCTd4FJBiDV/D5zPaX0crtdM8S5oMOBLZ5ZnmIjGsODK/ZfY2ITg62huxfBs88J0+5zRZoV9d4BLqk74PMQyTNDN2h2omCGZUgzXbg/a8PMZdm0aZ8k0k4+AN8vWEk3+89c9Dzq/QkFyTWCqdz+Mp9NkcOjA255kj5/D1q9Zj0x9VcmKg3oTtrKL/dkspUxVaMKXcpo6J4AszC05tT3N0SNLhcq51I6B9QdbYsYCkP9whPNYIl/y4dN4QnNEivQzA5/ltr5DDQZc7Xke1+SpJN+ylBOBJ8yvNoDnuxx0xEWuNJ5bUzyri/DNjZRKNQQKBgQDKkxn5R4Q3rChI/KVIXHMecTs9fXN5pbnA8UKU6ZeTl+wHffxwRieHZJJp1nDcQ5i8YXCtqEJcJGFn3T0rEjVCJ/RVHclFO+TjYaK9HtyNJLPrvKmAzjCzV1yPfe4qmpgJddzZI7Vii2uFgklixvhkoY74hwSJtvLPTO0p0xrYdQKBgQC4cgfpCMLYlaHQGz+dAEf4IewQpwBGn7XShCpiXGFciGZZEIzvJMzXP7yo8pDMHQbB4kQsTRRG2fcdHGWI5VQGHPqG5O0tUueKGUlSg7j8Y/Pp8ZrBGSrlilAf17I/u9MC7Xe2ZRVGNgPDYAyjMEGmClI0n2+aN6b4CFVBjYmfuQKBgEKO9KDIE7QrF41rnW7aGWTuNVWty2wzvIWdf4/n9EqlRwLrLS9CjahZrhWiRLDKcPusVFZqi2s09OAoe/mT4PXcpNX2lHPwCvN+1/allje10HvrIBJXLP8v/BSVftR2uO+azzZ1GhrHzksulKgk0eZWguA7lI0fFEZycxYj65UlAoGAD9p1RZlkLfuGgf2llRgOF4zK3o+MHYXiuep0PioUkECFE4ixpGh0Vtf6nkbjHTgteYK6O1iQsppPfCgRrheQBkp9WhTZMfkbP6p2u+nof4ET2PrUQ16naj1eL655erLpKypADORZVMSVxDhAPdKLAfuHH1DI5ed8qXsF4PGKb7kCgYEAm/d+daT6YsbHDZlJ/J9Q8rRkKmiqj43NGQSHKg6Z6BEDibm8wRmj3Itu1N6XVChuaH+ekJzvUnZ/q1nyYzGvy6bOHYn3ziF9aH7wuhcRZ4qARmKDnzTBLg2QXBK1+400O3LJ+sAH/yuH/Y4hzRE6YMxBQpdYfnlJcIFVimAsT1w=";
    //protected static String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAkfP2byjwHWc7Mr2qckr87UJuerNTefcpyjDlqVGyuHOTDVjJfqt3b7KflIkgMdkwHhpGH1t1upzv92NIQGZIvfX5rs6T7oxHh4bO/RzcBmWEIze9VXJNiXn5U6vI6S/vf7qdCs9p28LWQzCk0xIG3zXjtuIFdW5rw9pEPY+ukSMrl0g8EG1W0H5ei4qiqCZt627bG8F9WLXNP0s7JcqK9VXT2VpxwzJt5u/B6ru197y+iu3wFv+BVW3ile71o78ok0HxE5rgA6jHbKleWHqlGVhPw584PuiVf9y/ZyfH/v5j5YfJNYdaFtN+RqwAyhSn9TNroyinhyAp4+6ignQXjQIDAQAB";
    protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCe/HF/zUu+R1Y+wxHZvJPTng5+yog067beDjuXSfJdHaq/BBwDviYOtDRirXgrlNnZjRV/QTc+s4iU5KBGOWIJXXDbxypO9L1mX0jV05017F55mfs3N1sAfOkyz2LH0YZeQTvNm08Pnb0Pxy54TDtuJmM3Z43PbTURf9h+DpQI8EDbBdSlxdLw9E5Fc5m30QqLDcf8M0WlwemN8vS1U1z/zuSnS3b8DQ8NwYcGnoQqw1t2JMGouYrjENAUWJkKa7BOQpRYkq2WKmQ94V4rHelDSOriyV2WL3HxcF5xxoqwAySORAU2vSdwUyV0Xv1SJLRPgIT66NkLwT7VgByfPdrRAgMBAAECggEAfv+KrptQmOOlOida2AJyV4WXcKLaYOO1xPW7lmNkbgtnvIRM2JSJFA/RFM4bAz05NDYl6OwwdC63uLBUcyQdcvuoTndvDXkk3mTY6q3KlCuHYD2nVfkrAV3GbmcJMjLl0d7sJZaddgz73GGwdjn280tI0BYxSnOjA6KLTk7Y47W4duC5m5lQcP2NFBvnJogQu86UzJfJd2C6KQd50Yt0sXNy6JA8jzBH2gMEBCV3iG6oPzHSur3502UUtGaGK4CsT659liRG1soMSwNwILNhqDGd/5jQrrKvK9ijpeW43j/ZVBJp7aDQcGjnTaIoveqqW0yrb24Dw2/W0tXHDR4XnQKBgQDt4U7hPl/TSkTSMhwD3StICAOEUtwZLq//QHW51glDpbAHSMz5oYkCRPBQtF269TT5FYWKjeJzbN8n4HEN+Rrc9uZK2LlsVR10UA0Q6aEwAJWgFieP7RG2wQm+Fo6VybuOjo6cDR1dy4UVcW+EE6Pg2UeuhTkFLJS1OnTcZQgDJwKBgQCrGLE7X9V48qIYZycyZdKcN+cTyW+iLtU7U9LXVbN9FyA8sPCgzzQT2qMlyYT9RPV/cxnlzf/E9GM2Z1KKQDjXNmNCUBQHWvktEbqGiZL4w9sDZf2LaY7g1cnw92uVDee+HgxCJ4DNyhmNTBuxUrY+aaUG4/XoAIGyYXDd+MQNRwKBgQCLynjhUxdbAm0ZHQtrleBmbxcp/suLHO2eUdIdCFPfZhHg5f5WwH/UTpDuzF+8i3a63dPhTWoDLLqjI3ksnfQUYB+LYnrs18P8Y0KaasEnhWBoeDx9jvJ29E/2Xikiiy9mIg5aVDaqtPwbUtMWybfnXaiLPYz5pm4pZhXgopqnxwKBgEC+DDqabe3hakLvrZ5dd0+Tu8X6AFsPe7KCapL/I/cerB0rGzLOlTc6EWg4FDG+SWNby53w0GrXyz8H4+zalmehdffe02VhIE7UmY9VOZ8WhaaIjmOVovIjE9Ix3ay+YUXImvzhc9TrN7YG6ae0069UTJUJ58BNiMZWJt5nxs/7AoGAcn4pbsdp0CZK04u6d2PQfocvx/kgRMxY+DEVlzYzyGz00zRyNH0zY5ofFmYNCANj6VKHux/Nvm/GajdQHy5Y4c+eizH+N/eSoG1aoPLy491E3rwMiuoo7kVxJLx6pmkacKyLdylAs4ieSJIKpuiGTYVMjya5oP0EGYWMHgxncgQ=";
    protected static String BASE_URL = "http://ip:port/api";

	public static void main(String[] args) throws Exception {
		String URI = "mybank/account/farm/fundqueryinfo/V1";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
		try {

            MybankAccountFarmFundQueryInfoRequestV1.MybankAccountFarmFundQueryInfoRequestV1Biz bizContent = new MybankAccountFarmFundQueryInfoRequestV1.MybankAccountFarmFundQueryInfoRequestV1Biz();
            MybankAccountFarmFundQueryInfoRequestV1 request = new MybankAccountFarmFundQueryInfoRequestV1();
			bizContent.setTransType("0");
			bizContent.setTransDate("202003");
			bizContent.setInstId("370102");
			bizContent.setAmount("1000");
			bizContent.setSeqNumber("");
			bizContent.setFrequency("");
			bizContent.setCreditAccountNum("");
			bizContent.setDebitAccountNum("");
			request.setServiceUrl(BASE_URL + "/" + URI);
			request.setBizContent(bizContent);
			System.out.println(JSONObject.toJSONString(bizContent));
            MybankAccountFarmFundQueryInfoResponseV1 response = client.execute(request);
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

     */

}
