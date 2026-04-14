package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.EpassUserinfoRequestV1;
import com.icbc.api.request.EpassUserinfoRequestV1.EpassUserinfoRequestV1Biz;
import com.icbc.api.response.EpassUserinfoResponseV1;



public class EpassUserinfoTest {

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
//	protected static final String APP_ID = "10000000000000004523";//6月全链路监控OAUTH行外RSA
	protected static final String APP_ID = "10000000000000004524";//6月全链路监控OAUTH行外RSA2
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCdi7NGXeRzDEndTZlRaSJOOmNtQzuPR/lr2BxHVKSmxLP9N2+LHI8hLZcps+qjNaMWnJTvUJ5L6sxxt4i0fsuf5rrifdQHmKZE21Pra5QxzrvXOvzD7iS58eONDNm5HZt5OEh0sA5WfwTtTa1Id3nU0LPqSOeYhKey8pAD+YPx0umoCIQJdDapSCjScCOWGGZ9qKTZ2QZzdMYVHVA3ghoNgfZ4uRw/y7aq6b2yrLy1PaleiAVZmzgnSfqQdlT9qF8PwMi9E4qiZVhtQ3+m6mevDsnml+XtdI+XK0NGULXGBc6tXfH2aeJDIXigdxP35ay+VjKAO3xaLq6onVHX6EwXAgMBAAECggEBAIIxBg9qW/Uu/INjMwXxyaW33p5WZwu6wMV8K7JOolomR09D1muxSB503Gxb/9Sr6IAxGWrCxm2aNSTsNI5kEcWsGFg+/07fuNdCIJF1r1uRqeztFCCd1b/Lwu225t1xOB/ugQHc05xrDxlDjaVLBmT1qxg68xg415SrmpOOSJk3E6Vs790VNqHK1KWpD6EpomZ8NrlJQQHX+ov9y81tIcpuk1LIqeyL9Ch7o3eVN4hgMr32d/OJu687XONXmLtSMmdIQyEJr/YDsemEFSCEJnBJ+v5excqxBuqDObjUdEEFUe/UNGBn227NnevmcqmRU404cbDwUXNCBZTbrbNFOOECgYEA1y6sPb3lb8v6OYdKc1lhfx3IWgDsShtbE7puma7oZSKoII5N2ufregV/x6xlLQH75LJmOaCOUZ8zpvu1BAKNo3aifIYIMEPoufrmSqEsoIaOjBCuym1DzUMRlAHA14C2diTB8QpsSLPjn2tl81fMSqQpQ02Pkyo1964Ybnt6u08CgYEAu24sf5NkZ/jCyClvr9RgSjuIYpHBqaiAHhcRL1tqi5jH08XQV3M1HpJMm26n6zl+M4SGNImOTt5si3WS+5ldzxgn2BjYUQohyXcg+7Xl1EwEV9nQ7H1LMZ7+KdiF3GkrmK6MAbtjLpFJwcEwjwlW0XwNCtWZP0kLqmt1Ot7eELkCgYEAr4hGPzNqN1GItGcVp5TxAHX2Gt2H40f/es9pK3rP3rhvt+b9waXQpEBaJTMPcbCdn8ibtUSw9ApPY1cPKc8/ZWoAUF4xzdWHwjbWp2hk9CnvnHh7bWvGlGmyrC7l9aXsseF4R7296Wy9MS22A9TKNNVHR7y8c0tKrNna2j1mN+cCgYEAip1MY31KAGrFAutCIjNakwu8LcnGBAcN2TMODov5HAx9nGzrTAF0A4wHA9yuaxQ19TvJVQl2KXXtqKXnchNWgVZIAVU+hZCMtEZaKk24D224uI/qJzvyZFxFdAUh2oQdLenIuW8wRrxEY/rQQriRcJPhwWV+1ILdEcgvBr7UVGECgYEAkR5ZoZplBfiGnX3S1CuNJM2UIIoorIKlT8lj6RUYdQoMNBXt7nkv3illXU88kUrW7IiQoKEnd5LiCKkYVNLWoobgZvURyF30jB5+AcSXNGxvCSMtMGcN1VGzeIBpz8JkCw+u9YnyvHvQoYrvd8RkV3EnLtJk5j5VoyJHTNeFb+w=";
	
	public static void main(String[] args) throws Exception {
		
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		EpassUserinfoRequestV1 request = new EpassUserinfoRequestV1();
		
//		request.setServiceUrl("http://ip:port/api/epass/userinfo/CisNum/V1");//api调用方式
		request.setServiceUrl("http://ip:port/oauth/api/epass/userinfo/CisNum/V1");//oauth调用方式

		EpassUserinfoRequestV1Biz bizContent = new EpassUserinfoRequestV1Biz();
		bizContent.setPRIVATE("{\"msg_id\":\"1234567890\",\"APIGW_APPID\":\"10000000000000004440\","
				+ "\"USERID\":\"201312090001104053\",\"currentMAC\":\"68-F7-28-C8-C2-AD\","
				+ "\"clientIp\":\"ip:port\",\"timestamp\":\"2017-12-04 14:20:00\"}");
		bizContent.setPUBLIC("{\"FCODE\":\"passGetUserInfo\",\"SERVER_IP\":\"ip:port\","
				+ "\"DT\":\"2017-05-18\",\"TM\":\"10:56:55\","
				+ "\"CHANNELIDENTIFIER\":\"1234567890\",\"CHANNELCODE\":\"303\"}");
		request.setBizContent(bizContent);
		
		request.getExtraParameters().put("oauth_content","{"
				+ "\"access_token\":\"bff2547a-a6a8-4506-bcf6-e4d1020c6531GIYDCOBQGQYTMMJXGQZTEMRQGAYA====\","
				+ "\"open_id\":\"DR89mx9GtAu3e8wiwcptKWM3/VaG89Y/vTfHE6+DjR/1Jj2zPeobZg==\","
				+ "\"union_id\":\"oLMzHjfBe6hPK8V96tXfuGM3/VaG89Y/vTfHE6+DjR/1Jj2zPeobZg==\","
				+ "\"timestamp\":\"2018-04-16 17:40:00\"}");
		
		EpassUserinfoResponseV1 response;
		try {
			response = client.execute(request);
			if (response.isSuccess()) {
				System.out.println("********************业务处理成功********************");
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				System.out.println("********************业务处理失败********************");
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}

