package com.icbc.api;

import com.icbc.api.request.MybankPayQrcodeScannedReturnRequestV2;
import com.icbc.api.request.MybankPayQrcodeScannedReturnRequestV2.MybankPayQrcodeScannedReturnRequestV2Biz;
import com.icbc.api.response.MybankPayQrcodeScannedReturnResponseV2;

public class MybankPayQrcodeScannedReturnV2Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	// 2、appid
	protected static final String APP_ID = "10000000*****7354649";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1fMfRfOPOPKDH+1cdiBDRtu6SnTv2sFhQ+pezrcMyoCY+psXhuAvtkoR2yDzC9hnsxWczU33TSaD8Le1Y9uG+zVY1LMmh+fkkAt8jlBxd/7bSYy25rks7WlwN+WvqeyFA8A4CNsNY/GqvIZk+Lyrsd1/8IAdD0NM7JCQIaXi/+Z6VO8bD9Wlacd/HKQrxedImlWJVcTzKnS/5Et3eOyFbuNJAJW2UOBjc00OXlOmTXMnE6QR2gw9yYFICvvV7B6lSpyVQhrNfVjMFVRgEEL23PxWiU0NTjdznXtH1vgivUM8FB0wsfaoPdlMuTkHhbp/xu7PUv/JyM/eaBOZ11ScXAgMBAAECggEATSot55NbZMAAeKcUJjlJNXG7RFlYOo5GLsAtqjbybN8NA8xzDILth2ipq7t9okNzSGmydG9Ga4S9zxtT9GScHT0/L0LIxHb9iGM0LvN1kD3+pLuIoI9L64OC6moniT4rKGoo+qYALyC715TIjFkPvhUPmaqgjCQI+/dk3rLBpZWr/rGgwjupDl0rmzpW+/OxeS6P2ErfCaUXUaAE6nC7y17rbZlrOWp7ckOkNoCNXuPW591iFsnww/Iwq9Q4mudSTc5Q82T5FECiI4ImKdJZK598Ahv4yyaS7BbjAyNC/De/yVUgFVEp14AWzV1zkbLzPuWirV8mhgUvcHsrGi9cWQKBgQDp/tUqXd0t0Xl++y1MdUIDEhceFrXiocWIFtm6eXpEVHlcHf5V4Vk4PGgeT2Is6IPn2BnlMIsjZ7sBY251ljfaID0+YLQGgm5H3AJ5ULZCnbVOQX8L71lYQxCTN2VtSzGpSte3vF7yxPxiJ71ndTj2oFx6D9mRhIW2Y4oozI6E0wKBgQDGjeDsrWt731iDQ2q2uCrJbAJ84psOanlntsrcIkA0UIJFtAJkOq2EIfmWijgfL7NfjYkgLA0cmMMheUMjMbyIZkmvW3Ej3K3MDWswe4vFHvPdwwoxGNuhuz9qhST3Apo8nvS5HkKb1WezxU6iAMUxWno/vaASAAMAgsE6gJA6LQKBgFA1s1pnfIbVNGGLtjZsR2mIDPaacFS0gWKp+o6KT5gWoNcrzOAhdBTr2ADz00JznauejxnaLwe+6gKK0/VovdJ+lJ/KREuq8CSfzRVKzn89UkX1+5favvSbYJYmOhVh3i8a4aEw6Bct4RnAZ5OMQi+APtL0le7kz3T64AQmQoBTAoGBAMCKVNMuWEyHCQ5BEfqeHN+2HbN9C1+vYItydsWBcUFJ0QpGxQrs29aGG8Ifxsx3NTozxgWssgpJGPAc5z35vRmIF2w1AEIGfYKigI9DPwaoPVOPDxMNR+5N5fElq/LcUzT179qTvYPgLe/mJZpzpMV3N5UYDWFnIYIdScOxaCsBAoGABQs8EEOKcin/7maWf0CI5tPNj0pPlb5QPSCV8g56aSrh38ijs3PVUNvNL+ytnRVVLKi7QoJbbaGIFycY/C+n1a0cF71kuUgiUNPydUosijtRBTfMgX1aQQLBp/6uvQoEAlPsGNv8PufnG4ZO2nev+HV9qKv5PngNcI9m/Je8dxc=";
	
	public static void main(String[] args) throws Exception {
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
						
		MybankPayQrcodeScannedReturnRequestV2 request = new MybankPayQrcodeScannedReturnRequestV2();
				
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://*****/api/mybank/pay/qrcode/scanned/return/V2");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		MybankPayQrcodeScannedReturnRequestV2Biz bizContent = new MybankPayQrcodeScannedReturnRequestV2Biz();
		bizContent.setMerId("02000*****60001");
		bizContent.setCustId("123");
		 //该字段非必输项
		 bizContent.setOutTradeNo("2019041*****41706228333");//该字段非必输项,out_trade_no和order_id选一项上送即可
		 bizContent.setOrderId("");//该字段非必输项,out_trade_no和order_id选一项上送即可
		 bizContent.setRejectNo("1233212*****123");
		 bizContent.setRejectAmt("1");
		 bizContent.setOperId("123");//该字段非必输项
		 bizContent.setMerattach("X000000001");//该字段非必输项
		 request.setBizContent(bizContent);
		 MybankPayQrcodeScannedReturnResponseV2 response;
		 try {
				response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
				if (response.getReturnCode() == 0) {
					// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
					//System.out.println("response:" + JSON.toJSONString(response));
					//System.out.println("ReturnCode:"+response.getReturnCode());
				} else {
					// 失败
					//System.out.println("response:" + JSON.toJSONString(response));
					//System.out.println("ReturnCode:"+response.getReturnCode());
					//System.out.println("ReturnMsg:"+response.getReturnMsg());
				}		
		 } catch (IcbcApiException e) {
				e.printStackTrace();
		 }		
	}
}
