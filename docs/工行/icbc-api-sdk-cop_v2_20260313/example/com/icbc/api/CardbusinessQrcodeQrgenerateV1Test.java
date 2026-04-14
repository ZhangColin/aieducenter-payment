package com.icbc.api;
import java.util.logging.Logger;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessQrcodeQrgenerateRequestV1;
import com.icbc.api.response.CardbusinessQrcodeQrgenerateResponseV1;

public class CardbusinessQrcodeQrgenerateV1Test {

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1fMfRfOPOPKDH+1cdiBDRtu6SnTv2sFhQ+pezrcMyoCY+psXhuAvtkoR2yDzC9hnsxWczU33TSaD8Le1Y9uG+zVY1LMmh+fkkAt8jlBxd/7bSYy25rks7WlwN+WvqeyFA8A4CNsNY/GqvIZk+Lyrsd1/8IAdD0NM7JCQIaXi/+Z6VO8bD9Wlacd/HKQrxedImlWJVcTzKnS/5Et3eOyFbuNJAJW2UOBjc00OXlOmTXMnE6QR2gw9yYFICvvV7B6lSpyVQhrNfVjMFVRgEEL23PxWiU0NTjdznXtH1vgivUM8FB0wsfaoPdlMuTkHhbp/xu7PUv/JyM/eaBOZ11ScXAgMBAAECggEATSot55NbZMAAeKcUJjlJNXG7RFlYOo5GLsAtqjbybN8NA8xzDILth2ipq7t9okNzSGmydG9Ga4S9zxtT9GScHT0/L0LIxHb9iGM0LvN1kD3+pLuIoI9L64OC6moniT4rKGoo+qYALyC715TIjFkPvhUPmaqgjCQI+/dk3rLBpZWr/rGgwjupDl0rmzpW+/OxeS6P2ErfCaUXUaAE6nC7y17rbZlrOWp7ckOkNoCNXuPW591iFsnww/Iwq9Q4mudSTc5Q82T5FECiI4ImKdJZK598Ahv4yyaS7BbjAyNC/De/yVUgFVEp14AWzV1zkbLzPuWirV8mhgUvcHsrGi9cWQKBgQDp/tUqXd0t0Xl++y1MdUIDEhceFrXiocWIFtm6eXpEVHlcHf5V4Vk4PGgeT2Is6IPn2BnlMIsjZ7sBY251ljfaID0+YLQGgm5H3AJ5ULZCnbVOQX8L71lYQxCTN2VtSzGpSte3vF7yxPxiJ71ndTj2oFx6D9mRhIW2Y4oozI6E0wKBgQDGjeDsrWt731iDQ2q2uCrJbAJ84psOanlntsrcIkA0UIJFtAJkOq2EIfmWijgfL7NfjYkgLA0cmMMheUMjMbyIZkmvW3Ej3K3MDWswe4vFHvPdwwoxGNuhuz9qhST3Apo8nvS5HkKb1WezxU6iAMUxWno/vaASAAMAgsE6gJA6LQKBgFA1s1pnfIbVNGGLtjZsR2mIDPaacFS0gWKp+o6KT5gWoNcrzOAhdBTr2ADz00JznauejxnaLwe+6gKK0/VovdJ+lJ/KREuq8CSfzRVKzn89UkX1+5favvSbYJYmOhVh3i8a4aEw6Bct4RnAZ5OMQi+APtL0le7kz3T64AQmQoBTAoGBAMCKVNMuWEyHCQ5BEfqeHN+2HbN9C1+vYItydsWBcUFJ0QpGxQrs29aGG8Ifxsx3NTozxgWssgpJGPAc5z35vRmIF2w1AEIGfYKigI9DPwaoPVOPDxMNR+5N5fElq/LcUzT179qTvYPgLe/mJZpzpMV3N5UYDWFnIYIdScOxaCsBAoGABQs8EEOKcin/7maWf0CI5tPNj0pPlb5QPSCV8g56aSrh38ijs3PVUNvNL+ytnRVVLKi7QoJbbaGIFycY/C+n1a0cF71kuUgiUNPydUosijtRBTfMgX1aQQLBp/6uvQoEAlPsGNv8PufnG4ZO2nev+HV9qKv5PngNcI9m/Je8dxc=";

	protected static final String APP_ID = "1000XXXX000007354680";
	
	private static Logger logger = Logger.getLogger(CardbusinessQrcodeQrgenerateV1Test.class.getName());

	public static void main(String[] args) throws Exception {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessQrcodeQrgenerateRequestV1 request = new CardbusinessQrcodeQrgenerateRequestV1();
		request.setServiceUrl("http://ip:port/api/cardbusiness/qrcode/qrgenerate/V1");
		CardbusinessQrcodeQrgenerateRequestV1.CardbusinessQrcodeQrgenerateRequestV1Biz bizContent = new CardbusinessQrcodeQrgenerateRequestV1.CardbusinessQrcodeQrgenerateRequestV1Biz();
		request.setBizContent(bizContent);

		bizContent.setMerId("020002040095");
		bizContent.setStoreCode("02000015087");
		bizContent.setOutTradeNo("ZHL777O15002039");
		bizContent.setOrderAmt(7370L);
		bizContent.setTradeDate("20171210");
		bizContent.setTradeTime("160346");
		bizContent.setTporderCreateIp("localip");
		bizContent.setNotifyFlag("0");
		bizContent.setPayExpire("1200");
		

		CardbusinessQrcodeQrgenerateResponseV1 response;

		try {
			response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.fine("ReturnCode:" + response.getReturnCode());
				logger.fine("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				logger.fine("response:" + JSON.toJSONString(response));
				logger.fine("ReturnCode:" + response.getReturnCode());
				logger.fine("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
