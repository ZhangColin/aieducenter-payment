package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bOrderDetailQueryRequestV1;
import com.icbc.api.request.MallB2bOrderDetailQueryRequestV1.MallB2bOrderDetailQueryRequestV1Biz;
import com.icbc.api.response.MallB2bOrderDetailQueryResponseV1;

public class MallB2bOrderDetailQueryTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDTcEGKilMhHiwQZ9XBou8WH+D90TFsQ5EJS4Tj2WSqYG/C3Z5RIiQTaMyenqwSszlO0m8RLdgtT3wdVorNt7U/y/mocohZa/buyJiQ0cVjqKC7bTn+HN+mSM2+mRKiOx+3BIuBhYFy2X+uGyo/nYnqfjJ/jCrRTH6HoZ+wxrIWGwByosGHSBvu2ArqlGuj4TQvLYwGKm7Zcus3w0iqhJ4OlxnGrdycDQ9KkWc/HhVgk/QbLdaA6RXXq4eG75tcff1uWa0YAfaFijnkF0PTYV81scfaiPiQkuifSw2/cH96T36Rr8Hnu7T2iaEkfu4shm8KRqIWBMf6ugIBV7AqI/ZVAgMBAAECggEAUVpf3W5bjeKOEcHh/R8z3s9lQkO7QGy+4VStTP3YPbXdSpz6zTzVpKIuGNn0Wz2aTzmognOhOc3bc25Y+c41btaMuMNYsw9Xc4i7Kv2LdUzsizD7n0p8wv5AK5W6FbGqAkE9bBhyNj55YgQRvWhgKFlmFK1ZzC4igQ0u5FWPL/F/5RJ+pAAFn8XVtFjGTuoytIaMspLV+xY3PiRltkaK5KLX4GeT9ivdAFix3tEjJoeoiREXmG5GyW+YgB6mk/lJ0wzZh6w1c1x724vXo1t4fRDpGW1uYyTsm/MEenNsNK8meL+3/yNbr2XAXNxoLZxTX6lnp05nN5EG7WoW/WauAQKBgQD/spRPOWL07/EWlWoTHHakNGLmOKdvDSI+cP0A3G1J6VGapxJIFca6xGRZhI5gmhGmEFwyQhmijGA6WunIdy/38KKwAMYprib5R7Qtf0S+/m8DzvPGgE02F03Yffnb5OQnmfhrBRS76qpo5BpCiopa4MpYTcPBFcBjGKpRPseE6wKBgQDTsEacomAueEnSmt264zARnfJFD3c3iPFNDp2h2VZXmw+aYTzV2MtVX/IREEwc0cUTZTLFnFBu1Hz8xM2NgjhG/XwPE0bgA2NsOYwmvia9NJD/ZF6JnGvfWtXk4BVvMmFRXVaunNoHzNT9GR3MBLNMFLnwmOxpmGI+wyThHhChvwKBgQD2h2Qs+kRpqgnFX2u1X2UYLpYIKClujbgtgdy9fsk0UOtn85tRuwUvP3kNQhvQEdvgQ7fQkSCMM1H4EX2D1T5/jBgfH6/vscRL5hFXKHTMvR2mC1vIUB2hgFiC2fsUQxyi/fWgoXZ7M+b3WK3k6BQ7SK5u8xNAz8r9AGP+D0xo3wKBgDj3Islb2M4oiJUoH6r/IBxdiKiU9Nv4+hZmbHz1Y4rHq3c724cgg/dBjn6RCCppOMNzZCpRWkO44rmSrE38UPCpngzgfx4hS0wAYHq8iOg5kRtpAxe9BDpi01R4CMyXniCrIXxzsSiQMnC5pY8VZLqAiVj5jgUMgMOq9+2sjYU5AoGBAJpEfGTbWn9FUQjWCjmg7ikbYIGhBpUeFPfr2uXsNxyvIW9fRXFFBvyxstDTSPByTvsgVChRr5aytG4OUT8QWrwMR2TTQ7uxUZQf69fvvxbws3QGeT+hPzLMgtNvCfuDejCof14Tq52ozu/vpUQFP6r5J4mj/P3xWyIza0hM13Jj";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002870";

	public void test_cop() throws Exception{

	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8", 
				"json",APIGW_PUBLIC_KEY,"AES","AxWTVCpL0yyeAzwOPlsSVw==", "", "");
		
		

		
		MallB2bOrderDetailQueryRequestV1 request = new MallB2bOrderDetailQueryRequestV1();

		

		//详情
		request.setServiceUrl("https://ip:port/api/mall/b2b/order/detail/query/V1");
		//列表

		
		

		
		//详情
		String xml="<ORDER_ID>201907200000025412</ORDER_ID><VENDOR_ID>10002778</VENDOR_ID>";
		MallB2bOrderDetailQueryRequestV1Biz biz = new MallB2bOrderDetailQueryRequestV1Biz();
		biz.setReqxmldata(xml);
		request.setBizContent(biz);

		MallB2bOrderDetailQueryResponseV1 response;
		try {
			response = (MallB2bOrderDetailQueryResponseV1) client.execute(request, System.currentTimeMillis()+"");
		
		System.out.println(JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}


	}
	
	public static void main(String[] args) throws Exception{
		new MallB2bOrderDetailQueryTestV1().test_cop();
		
	}
	

	
}

