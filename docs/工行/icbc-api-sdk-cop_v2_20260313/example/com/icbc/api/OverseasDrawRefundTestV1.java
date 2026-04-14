package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.OverseasDrawRefundRequestV1;
import com.icbc.api.request.OverseasDrawRefundRequestV1.OverseasDrawRefundRequestV1Biz;
import com.icbc.api.response.OverseasDrawRefundResponseV1;

public class OverseasDrawRefundTestV1 {

	// 调用方自己的AppId
	private static final String APP_ID = new String("10000000000000014025");
	// 调用方私钥
	private static final String MY_PRIVATE_KEY = new String(
			"-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC3l/fHuD6N8WqLIqepv4X7fZyT+D24U7UaH0AhDgDEVKBIq2jOHjJrTg7p/zmsaExK/6hDT6W4sEHvBYjRtBnlzcAcNi43oH02/tKR46VLvDNCaxFFtPsgENoiYs6fhwqfyiauHWNp0ae6TPnAbzvmyZ9nv0JDb55OG3m9XxFHHX/bm164VG9cgGvxSx0hpwmrBjg8HXZ09eWMY1NiUm+cRx+pwkV3u0qvo0O0rqu3qtSOOspYIOKbgYWZGw9zY7sdF/++9+nXKdYYKhq7fUshuTo0unUh42wctrzRIq/aznGlQQv5kevkMV/luVRFmVBbSCe4hq3wMoacBsn24Yc1AgMBAAECggEBALWN4akuaSXjLBJlddomnO9je3jUjux+UuR/LOLDPzzn1w2l/GGAeSmmKpd6HmWBSpkMEzBlTiPCoaEmHvQJn2aivizn43J9EfZVrpQ4w5iOhz5SNOVzILfIEn7YqS4W1AnKVc/vHgViH/Ta9Ew5c8o5bpqhB306nxjskMoVfjeMoQI6SfnOdDTHFmDsUNwJQ5rIc9hdSuZWd3ve6NXKVAs1HNFof1oCCU1YSMOBzTjraRwDZJgRZW6jXhIIw0J0GtP5s/APVkd5UH9hQF/+bDgJOeKqncLhAztDvJ4u0oJY0pKzlVj+Ca5S3qoIGrkNRZK25azEjmt4hvzVEE403cECgYEA/0/VqKlGd/rQm0IZPUsIDJ3t3OHYc70r48txwOpo1s6sZke4hGH6mfKUk3ir3+1/ox28CDxFGWPzB5V0tsztHOHbFcjPBd3PchCf5/OH32OzjeM/7DTuMY5sKeL2tUxk8bB2FetLwfuSTPa0Y5LYvqEL/iuckbacJSfWuDtGV/ECgYEAuBalzDnpPhQ5iqZL6P5EdE63qBxvRXe3r+gslltBDFEcBYrMMH2fRokTe74mz1WDRo0GRljvPfgUbanQZMOmIuTmMcdYrFSFtA8d96rW7fBNAvy2Tz+rV56Mxw5HuKYTXnAU7Q4jZH2fqqDoWL4Qs6qhYJiR+sKGUF5O4hoSR4UCgYEAtAUUwAHrgDnH0VF//mnjWRIaX/6W4rTmN46BvOcL2TqM8OltP1remqIjBQC3IAOUVe1nYd8tUcPZ3uURELvaDN1r3yZiYt6NoP374k8i5O6l8kvkDKcdtK+ZsMcp8ZfWLVyhRr9MAZFjDQV/GB642WEw1Zzmnw/FdaVrjyesZ+ECgYEAp56nlQ/eG1KbGm5VjG+JsUXD4OUTRHO/5AJKJxYsYqSwGTPKvRSFpNpHzVLVT9CTWfgrEH8GSKzNHRWU1Ut4ySkXmZfkW/YSUGO7a7o5PiqiVOf6sKpOv9d0ZAPStoUx+bmgtEE9ZNEmQ12EctmNp6rkimoh8eySFDfrJ+GJZ2kCgYAeyPEYGTip3LwjNlnBvNbNNce/3vcN5iKVdA7fSAsZj+TTb6jWy7AV+TIorWfjE5xMkGa0xbWd3CkP/Ac5VcsXus8mys/qGc5AL2GCUGf8NmZD66QpgN+9fn1SawMrtntsaqcXwUusaG3JOZmAsbeJ5dORNsV5se360Xx7szR99g==");
	// API网关公钥
	private static final String APIGW_PUBLIC_KEY = new String(
			"-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB");
	// API请求地址
	private static final String API_SERVICE_URL = new String("http://ip:port/api");
	// AES加密密钥
	private static final String AES_KEY = "U92s0I009oZk2V+vhT4xDA==";

	public static void main(String[] args) {

		
		// 签名类型为SHA256WithRSA时，传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, null, null);
		
		
		OverseasDrawRefundRequestV1 request = new OverseasDrawRefundRequestV1();
		
		//分区标识，固定 上送onv
		client.setZoneNo("onv");

		//访问工行API平台URL
		request.setServiceUrl(API_SERVICE_URL + "/overseas/draw/refund/V1");

		OverseasDrawRefundRequestV1Biz bizContent = new OverseasDrawRefundRequestV1Biz();

		//交易日期
		bizContent.setTransdate("2021-11-11 12:00:00");

		//工行交易唯一标识串
		bizContent.setIcbcrefnum("21111112000019000201");
		
		//socash交易唯一标识串
		bizContent.setSocashrefnum("12345678900001");
		
		//交易金额
		bizContent.setTransamt("16.00");
				
		//APPID
		bizContent.setAppid("10000000000000014025");

		request.setBizContent(bizContent);

		
		OverseasDrawRefundResponseV1 response;

		try {
			response = client.execute(request);
			
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(JSONObject.toJSONString(response));
			} else {
				// 失败
				System.out.println(JSONObject.toJSONString(response));
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

}
