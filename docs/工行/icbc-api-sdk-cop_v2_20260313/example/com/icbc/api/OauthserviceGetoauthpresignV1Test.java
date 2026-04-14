package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.OauthserviceGetoauthpresignRequestV1;
import com.icbc.api.request.OauthserviceGetoauthpresignRequestV1.OauthserviceGetoauthpresignRequestV1Biz;
import com.icbc.api.response.OauthserviceGetoauthpresignResponseV1;
import com.icbc.api.response.OauthserviceGetoauthpresignResponseV1.OauthserviceGetoauthpresignResponseV1oauthInfo;
import com.icbc.api.utils.IcbcSignature;

public class OauthserviceGetoauthpresignV1Test {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaXN+0wqwPGjeJwL4b7pf5KVnC3ObKEkvl7N1flLpMVWRVU5CZTdwsuzyxD3Licr//J3q1dyhj27DKKtHRARFr1DjFKwnT9Umiln2bUKPmxQmIJDuH12gi3BmcAsLa2wmFlMOfSYf8dHv0Qqg139TSsPMxHVfts8ialynWy1+I2W6DUw1WxXYcS61uRAIsOaI/V4FWV3kiI5KCKPWHffspN8BJy6IgVtYJlTvZGPxxeSqRYcS3i8jOGiqBGn2KSxvoEFON4wFz0O87XUJJdWq6qlk5WQty5jU1YVRCKNfGdbQ5ZFZORaq//0A5AfU+sTor9nYpiECZshFKmCOL0Yk3AgMBAAECggEAdY918DyyvW3CDm90WeGEbIZJjlaejhcRJrMWhczHdYszNqjF33uky4xt1Itne37ZKoHuunVdlW+jXNTvprWhhvXBpqf4fCy7P9BNKRRpPz+g2sZ9cB9ld+cYufCAZh2bStjZg16E6ofsP0LkyOMHOs7459bpOGdLyDCH9MY+pAF8zxIarVuFgwXDRGYmrLPIxvQoORcEsm83BlPzx2ZWho2ycDyBtZr4CdSJMGY/nR4PqKr4ZxPpetl+RjNleUR/tX89Si5HH7fIAur8CmZ7DB5TKCz42lnhEdF/36jxT2wLF7mzowu4GATW56245cPAYKZrgIQvAwPAF+W6DHx2gQKBgQD8wpzNuc+yFZhIBBtzlEQP3ex7/cP6jOLtcTpvxj8KlcAA7GhwIO4XNO3iU02wc25oiioLuIGqndhxd/0l90GTcrfxAg1CK9WbWrlq93acgwa3PxedN3dT8yPYN/1uoDpe+2KOaWzrGvSw3Y0Zy5XC+tUQQszq+900ytKZlDx1HwKBgQCcV2NFusy4At4zlynasZd/c11nBEzEKox2/+NZ1Uy8ui42c21cfwlRC32FuuSIFlhTSewtRWM8ronkiATJNa3HrcUbJJRgTUfQJnYi9MHYQcRhUYfLqn5KXURpCS48qyYQvXguvMLxxFLSwACxKydqXgafrhviuv3BJyc6BmcQ6QKBgQC2L3hieNoR3KrkzcETr/zXCvkX7jPCdqZiy0h5pIVwFbxNbhlhKPs0UvxcM/s9ghZE18GyrQ0Zc6i9MOWQ5gWoR/n5h6cS2RGgZuZbCX+/FFpU3u8gbQhfxFND2CeeA3PPXpcNW0QculNA4JCB1VJfNfk5PU0C2wP4t3DRLh3dtQKBgAX2Ixfulo3Rti1bb0IikXdPq9kYxeCZCsAi48VyJ7nuk4KKBjqHsZBAkum9hadWyslqQTI8uhr/QMzRDvWpMVYy3xg43duKNtnODaUplhar81QOqtHuT2CAi/JBYlz33by3rNSDaB+V796PabdXwOoCadg+6FtN7A0odAt3YQi5AoGBAK8hgOogeusHyk8XCvf/gBL8VPKVSTLYoos62dqhtfRf4+nK79g9Ix3v3hQcUypNzKX2i1nckngn/L6O4/eNkZQbC1ksQV+vce/MgL4Hda24lwwf5kNhRkHk42ND0SIUQjuI5j63DsedRyzIl/04nihpWF5I/IsSeFwf2mJpt90E";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000003909";
	
	protected static final String APP_PRIVATE_KEY = "-----请使用正确的APP私钥-----EwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCdi7NGXeRzDEndTZlRaSJOOmNtQzuPR/lr2BxHVKSmxLP9N2+LHI8hLZcps+qjNaMWnJTvUJ5L6sxxt4i0fsuf5rrifdQHmKZE21Pra5QxzrvXOvzD7iS58eONDNm5HZt5OEh0sA5WfwTtTa1Id3nU0LPqSOeYhKey8pAD+YPx0umoCIQJdDapSCjScCOWGGZ9qKTZ2QZzdMYVHVA3ghoNgfZ4uRw/y7aq6b2yrLy1PaleiAVZmzgnSfqQdlT9qF8PwMi9E4qiZVhtQ3+m6mevDsnml+XtdI+XK0NGULXGBc6tXfH2aeJDIXigdxP35ay+VjKAO3xaLq6onVHX6EwXAgMBAAECggEBAIIxBg9qW/Uu/INjMwXxyaW33p5WZwu6wMV8K7JOolomR09D1muxSB503Gxb/9Sr6IAxGWrCxm2aNSTsNI5kEcWsGFg+/07fuNdCIJF1r1uRqeztFCCd1b/Lwu225t1xOB/ugQHc05xrDxlDjaVLBmT1qxg68xg415SrmpOOSJk3E6Vs790VNqHK1KWpD6EpomZ8NrlJQQHX+ov9y81tIcpuk1LIqeyL9Ch7o3eVN4hgMr32d/OJu687XONXmLtSMmdIQyEJr/YDsemEFSCEJnBJ+v5excqxBuqDObjUdEEFUe/UNGBn227NnevmcqmRU404cbDwUXNCBZTbrbNFOOECgYEA1y6sPb3lb8v6OYdKc1lhfx3IWgDsShtbE7puma7oZSKoII5N2ufregV/x6xlLQH75LJmOaCOUZ8zpvu1BAKNo3aifIYIMEPoufrmSqEsoIaOjBCuym1DzUMRlAHA14C2diTB8QpsSLPjn2tl81fMSqQpQ02Pkyo1964Ybnt6u08CgYEAu24sf5NkZ/jCyClvr9RgSjuIYpHBqaiAHhcRL1tqi5jH08XQV3M1HpJMm26n6zl+M4SGNImOTt5si3WS+5ldzxgn2BjYUQohyXcg+7Xl1EwEV9nQ7H1LMZ7+KdiF3GkrmK6MAbtjLpFJwcEwjwlW0XwNCtWZP0kLqmt1Ot7eELkCgYEAr4hGPzNqN1GItGcVp5TxAHX2Gt2H40f/es9pK3rP3rhvt+b9waXQpEBaJTMPcbCdn8ibtUSw9ApPY1cPKc8/ZWoAUF4xzdWHwjbWp2hk9CnvnHh7bWvGlGmyrC7l9aXsseF4R7296Wy9MS22A9TKNNVHR7y8c0tKrNna2j1mN+cCgYEAip1MY31KAGrFAutCIjNakwu8LcnGBAcN2TMODov5HAx9nGzrTAF0A4wHA9yuaxQ19TvJVQl2KXXtqKXnchNWgVZIAVU+hZCMtEZaKk24D224uI/qJzvyZFxFdAUh2oQdLenIuW8wRrxEY/rQQriRcJPhwWV+1ILdEcgvBr7UVGECgYEAkR5ZoZplBfiGnX3S1CuNJM2UIIoorIKlT8lj6RUYdQoMNBXt7nkv3illXU88kUrW7IiQoKEnd5LiCKkYVNLWoobgZvURyF30jB5+AcSXNGxvCSMtMGcN1VGzeIBpz8JkCw+u9YnyvHvQoYrvd8RkV3EnLtJk5j5VoyJHTNeFb+w=";
	

	public void test() throws IcbcApiException {
		//RSA调用
		//DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,APP_PRIVATE_KEY,APIGW_PUBLIC_KEY);
		
		//RSA2调用
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA2,APP_PRIVATE_KEY,APIGW_PUBLIC_KEY);
		
		OauthserviceGetoauthpresignRequestV1 request = new OauthserviceGetoauthpresignRequestV1();
		request.setServiceUrl("http://ip:port/api/oauthservice/getoauthpresign/V1");
		
		OauthserviceGetoauthpresignRequestV1Biz bizContent = new OauthserviceGetoauthpresignRequestV1Biz();
		bizContent.setResponseType("code");
		bizContent.setRedirectUrl("http://www.icbc.com/api");
		bizContent.setScope("00006");
		bizContent.setState("3d6be0a4035d839573b04816624a415e");
		bizContent.setTimeStamp("2017-12-19 16:22:00");
		
		request.setBizContent(bizContent);
		
		OauthserviceGetoauthpresignResponseV1 response = client.execute(request,"123");
		
		if (response.isSuccess()) {
			// 接收成功
			System.out.println("return_code="+response.getReturnCode());
			System.out.println("return_msg="+response.getReturnMsg());
			System.out.println("state="+response.getState());
			System.out.println("sign="+response.getSign());
			System.out.println("timestamp="+response.getTimeStamp());
			
			OauthserviceGetoauthpresignResponseV1oauthInfo oauthinfo = response.getOauthInfo();
			System.out.println("app_id="+oauthinfo.getAppId());
			System.out.println("msg_id="+oauthinfo.getMsgId());
			System.out.println("redirect_url="+oauthinfo.getRedirectUrl());
			System.out.println("response_type="+oauthinfo.getResponseType());
			System.out.println("scope="+oauthinfo.getScope());
			System.out.println("request_timestamp="+oauthinfo.getTimeStamp());
			
			
//			String strToSignValue = "{\"app_id\":\"10000000000000003909\",\"response_type\":\"code\",\"scope\":\"00006\",\"msg_id\":\"123\",\"redirect_url\":\"http://www.icbc.com/api\",\"timestamp\":\"2017-12-19 16:22:00\"}";
//			boolean passed = IcbcSignature.verify(strToSignValue, IcbcConstants.SIGN_TYPE_RSA, APIGW_PUBLIC_KEY, "UTF-8", response.getSign()); 
//			System.out.println("passed= " + passed);
		}else {
			// 失败
			System.out.println("return_code="+response.getReturnCode());
			System.out.println("return_msg="+response.getReturnMsg());
		}
	}
}
