package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bLogisticsReceiveRequestV1;
import com.icbc.api.request.MallB2bLogisticsReceiveRequestV1.MallB2bLogisticsReceiveRequestV1Biz;
import com.icbc.api.response.MallB2bLogisticsReceiveResponseV1;

public class MallB2bLogisticsReceiveTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDTcEGKilMhHiwQZ9XBou8WH+D90TFsQ5EJS4Tj2WSqYG/C3Z5RIiQTaMyenqwSszlO0m8RLdgtT3wdVorNt7U/y/mocohZa/buyJiQ0cVjqKC7bTn+HN+mSM2+mRKiOx+3BIuBhYFy2X+uGyo/nYnqfjJ/jCrRTH6HoZ+wxrIWGwByosGHSBvu2ArqlGuj4TQvLYwGKm7Zcus3w0iqhJ4OlxnGrdycDQ9KkWc/HhVgk/QbLdaA6RXXq4eG75tcff1uWa0YAfaFijnkF0PTYV81scfaiPiQkuifSw2/cH96T36Rr8Hnu7T2iaEkfu4shm8KRqIWBMf6ugIBV7AqI/ZVAgMBAAECggEAUVpf3W5bjeKOEcHh/R8z3s9lQkO7QGy+4VStTP3YPbXdSpz6zTzVpKIuGNn0Wz2aTzmognOhOc3bc25Y+c41btaMuMNYsw9Xc4i7Kv2LdUzsizD7n0p8wv5AK5W6FbGqAkE9bBhyNj55YgQRvWhgKFlmFK1ZzC4igQ0u5FWPL/F/5RJ+pAAFn8XVtFjGTuoytIaMspLV+xY3PiRltkaK5KLX4GeT9ivdAFix3tEjJoeoiREXmG5GyW+YgB6mk/lJ0wzZh6w1c1x724vXo1t4fRDpGW1uYyTsm/MEenNsNK8meL+3/yNbr2XAXNxoLZxTX6lnp05nN5EG7WoW/WauAQKBgQD/spRPOWL07/EWlWoTHHakNGLmOKdvDSI+cP0A3G1J6VGapxJIFca6xGRZhI5gmhGmEFwyQhmijGA6WunIdy/38KKwAMYprib5R7Qtf0S+/m8DzvPGgE02F03Yffnb5OQnmfhrBRS76qpo5BpCiopa4MpYTcPBFcBjGKpRPseE6wKBgQDTsEacomAueEnSmt264zARnfJFD3c3iPFNDp2h2VZXmw+aYTzV2MtVX/IREEwc0cUTZTLFnFBu1Hz8xM2NgjhG/XwPE0bgA2NsOYwmvia9NJD/ZF6JnGvfWtXk4BVvMmFRXVaunNoHzNT9GR3MBLNMFLnwmOxpmGI+wyThHhChvwKBgQD2h2Qs+kRpqgnFX2u1X2UYLpYIKClujbgtgdy9fsk0UOtn85tRuwUvP3kNQhvQEdvgQ7fQkSCMM1H4EX2D1T5/jBgfH6/vscRL5hFXKHTMvR2mC1vIUB2hgFiC2fsUQxyi/fWgoXZ7M+b3WK3k6BQ7SK5u8xNAz8r9AGP+D0xo3wKBgDj3Islb2M4oiJUoH6r/IBxdiKiU9Nv4+hZmbHz1Y4rHq3c724cgg/dBjn6RCCppOMNzZCpRWkO44rmSrE38UPCpngzgfx4hS0wAYHq8iOg5kRtpAxe9BDpi01R4CMyXniCrIXxzsSiQMnC5pY8VZLqAiVj5jgUMgMOq9+2sjYU5AoGBAJpEfGTbWn9FUQjWCjmg7ikbYIGhBpUeFPfr2uXsNxyvIW9fRXFFBvyxstDTSPByTvsgVChRr5aytG4OUT8QWrwMR2TTQ7uxUZQf69fvvxbws3QGeT+hPzLMgtNvCfuDejCof14Tq52ozu/vpUQFP6r5J4mj/P3xWyIza0hM13Jj";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002870";

	public void test_cop() throws Exception{
	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8", 
				"json",APIGW_PUBLIC_KEY,"AES","AxWTVCpL0yyeAzwOPlsSVw==", "", "");
		
		MallB2bLogisticsReceiveRequestV1 request = new MallB2bLogisticsReceiveRequestV1();
		request.setServiceUrl("https://ip:port/api/mall/b2b/logistics/receive/V1");
		//获取报文
		String xml = getXml();
		
		MallB2bLogisticsReceiveRequestV1Biz biz = new MallB2bLogisticsReceiveRequestV1Biz();
		biz.setReqxmldata(xml);
		request.setBizContent(biz);

		MallB2bLogisticsReceiveResponseV1 response;
		try {
			response = (MallB2bLogisticsReceiveResponseV1) client.execute(request, System.currentTimeMillis()+"");
		
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
		new MallB2bLogisticsReceiveTestV1().test_cop();
		
	}
	
	public static String getXml() throws Exception{
		String xml ="<DELIVERY_ID>F20190725000006601</DELIVERY_ID><CURRENT_STATUS>5</CURRENT_STATUS><VENDOR_ID>10002778</VENDOR_ID><LOGISTIC_LIST><LOGISTIC_INFO><PROGRESS_TYPE>1</PROGRESS_TYPE><CREATE_TIME>20170925123521</CREATE_TIME><RESULT_MESSAGE>{'起始港(地)':'SHENZHEN','目的港(地)':'LOS ANGELES','发布日期':'2017-09-25 12:35:21'}</RESULT_MESSAGE></LOGISTIC_INFO><LOGISTIC_INFO><PROGRESS_TYPE>2</PROGRESS_TYPE><CREATE_TIME>20170927100201</CREATE_TIME><RESULT_MESSAGE>{'运输方式':'海运','船公司':'PSL','订舱号':'T123456','起运日期':'201-709-27 10:02:01','预计到达日期':'2017-09-27 10:02:01'}</RESULT_MESSAGE></LOGISTIC_INFO><LOGISTIC_INFO><PROGRESS_TYPE>3</PROGRESS_TYPE><CREATE_TIME>20170927152528</CREATE_TIME><RESULT_MESSAGE>{'操作时间':'2017-09-27 15:25:28','报关单号':'65423465133241354'}</RESULT_MESSAGE></LOGISTIC_INFO><LOGISTIC_INFO><PROGRESS_TYPE>4</PROGRESS_TYPE><CREATE_TIME>20170928082343</CREATE_TIME><RESULT_MESSAGE>{'当前进度':'运输中'}</RESULT_MESSAGE></LOGISTIC_INFO><LOGISTIC_INFO><PROGRESS_TYPE>5</PROGRESS_TYPE><CREATE_TIME>20170929173542</CREATE_TIME><RESULT_MESSAGE>{'快递公司':'DHL','快递单号':'1234564444','派送日期':'2017-09-29 17:35:42'}</RESULT_MESSAGE></LOGISTIC_INFO></LOGISTIC_LIST>";
		return xml;
	}
	
}

