package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessQrcodeQrgenerateRequestV3;
import com.icbc.api.response.CardbusinessQrcodeQrgenerateResponseV3;

import java.util.HashMap;

public class CardbusinessQrcodeQrgenerateV3Test {

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1fMfRfOPOPKDH+1cdiBDRtu6SnTv2sFhQ+pezrcMyoCY+psXhuAvtkoR2yDzC9hnsxWczU33TSaD8Le1Y9uG+zVY1LMmh+fkkAt8jlBxd/7bSYy25rks7WlwN+WvqeyFA8A4CNsNY/GqvIZk+Lyrsd1/8IAdD0NM7JCQIaXi/+Z6VO8bD9Wlacd/HKQrxedImlWJVcTzKnS/5Et3eOyFbuNJAJW2UOBjc00OXlOmTXMnE6QR2gw9yYFICvvV7B6lSpyVQhrNfVjMFVRgEEL23PxWiU0NTjdznXtH1vgivUM8FB0wsfaoPdlMuTkHhbp/xu7PUv/JyM/eaBOZ11ScXAgMBAAECggEATSot55NbZMAAeKcUJjlJNXG7RFlYOo5GLsAtqjbybN8NA8xzDILth2ipq7t9okNzSGmydG9Ga4S9zxtT9GScHT0/L0LIxHb9iGM0LvN1kD3+pLuIoI9L64OC6moniT4rKGoo+qYALyC715TIjFkPvhUPmaqgjCQI+/dk3rLBpZWr/rGgwjupDl0rmzpW+/OxeS6P2ErfCaUXUaAE6nC7y17rbZlrOWp7ckOkNoCNXuPW591iFsnww/Iwq9Q4mudSTc5Q82T5FECiI4ImKdJZK598Ahv4yyaS7BbjAyNC/De/yVUgFVEp14AWzV1zkbLzPuWirV8mhgUvcHsrGi9cWQKBgQDp/tUqXd0t0Xl++y1MdUIDEhceFrXiocWIFtm6eXpEVHlcHf5V4Vk4PGgeT2Is6IPn2BnlMIsjZ7sBY251ljfaID0+YLQGgm5H3AJ5ULZCnbVOQX8L71lYQxCTN2VtSzGpSte3vF7yxPxiJ71ndTj2oFx6D9mRhIW2Y4oozI6E0wKBgQDGjeDsrWt731iDQ2q2uCrJbAJ84psOanlntsrcIkA0UIJFtAJkOq2EIfmWijgfL7NfjYkgLA0cmMMheUMjMbyIZkmvW3Ej3K3MDWswe4vFHvPdwwoxGNuhuz9qhST3Apo8nvS5HkKb1WezxU6iAMUxWno/vaASAAMAgsE6gJA6LQKBgFA1s1pnfIbVNGGLtjZsR2mIDPaacFS0gWKp+o6KT5gWoNcrzOAhdBTr2ADz00JznauejxnaLwe+6gKK0/VovdJ+lJ/KREuq8CSfzRVKzn89UkX1+5favvSbYJYmOhVh3i8a4aEw6Bct4RnAZ5OMQi+APtL0le7kz3T64AQmQoBTAoGBAMCKVNMuWEyHCQ5BEfqeHN+2HbN9C1+vYItydsWBcUFJ0QpGxQrs29aGG8Ifxsx3NTozxgWssgpJGPAc5z35vRmIF2w1AEIGfYKigI9DPwaoPVOPDxMNR+5N5fElq/LcUzT179qTvYPgLe/mJZpzpMV3N5UYDWFnIYIdScOxaCsBAoGABQs8EEOKcin/7maWf0CI5tPNj0pPlb5QPSCV8g56aSrh38ijs3PVUNvNL+ytnRVVLKi7QoJbbaGIFycY/C+n1a0cF71kuUgiUNPydUosijtRBTfMgX1aQQLBp/6uvQoEAlPsGNv8PufnG4ZO2nev+HV9qKv5PngNcI9m/Je8dxc=";

	protected static final String APP_ID = "1000XXXX000007354680";
	
	private static Logger logger = Logger.getLogger(CardbusinessQrcodeQrgenerateV3Test.class.getName());

	public static void main(String[] args) throws Exception {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessQrcodeQrgenerateRequestV3 request = new CardbusinessQrcodeQrgenerateRequestV3();
		request.setServiceUrl("http://ip:port/api/cardbusiness/qrcode/qrgenerate/V3");
		CardbusinessQrcodeQrgenerateRequestV3.CardbusinessQrcodeQrgenerateRequestV3Biz bizContent = new CardbusinessQrcodeQrgenerateRequestV3.CardbusinessQrcodeQrgenerateRequestV3Biz();


		bizContent.setMerId("020002040095");
		bizContent.setStoreCode("02000015087");
		bizContent.setOutTradeNo("ZHL777O15002039");
		bizContent.setOrderAmt("7370");
		bizContent.setTradeDate("20171210");
		bizContent.setTradeTime("160346");
		bizContent.setTporderCreateIp("localip");
		bizContent.setNotifyFlag("0");
		bizContent.setPayExpire("1200");
		bizContent.setNotifyUrl("http://ywqiyf.cn");
		bizContent.setSpFlag("0");
		bizContent.setCustName("cc");
		bizContent.setCustCertNo("0");
		bizContent.setCustCertType("0");
		bizContent.setGoodsName("");

    	HashMap<String,Object> terminalInfo = new HashMap<String,Object>();
		terminalInfo.put("location", "+37.12/-121.213");//终 端 实 时 经 纬度信息
		terminalInfo.put("network_license", "P3100");//终端入网认证编号
		terminalInfo.put("device_type", "01");//设备类型
		terminalInfo.put("serial_num", "");//终端序列号
		terminalInfo.put("device_id", "Sxxxxxx");//终端设备号
		terminalInfo.put("encrypt_rand_num", "xxxxxx");//加密随机因子
		terminalInfo.put("secret_text", "");//密文数据
		terminalInfo.put("app_version", "");//应用程序版本号
		terminalInfo.put("device_ip", "");//商户端设备 IP
		terminalInfo.put("mobile_country_cd", "");//移动国家代码
		terminalInfo.put("mobile_net_num", "");//移动网络号码
		terminalInfo.put("icc_id", "");//ICCID
		terminalInfo.put("location_cd1", "");//位置区域码 1
		terminalInfo.put("lbs_num1", "");//基站编号 1
		terminalInfo.put("lbs_signal1", "");//基站信号 1 
		terminalInfo.put("location_cd2", "");//位置区域码 2 
		terminalInfo.put("lbs_num2", "");//基站编号 2
		terminalInfo.put("lbs_signal2", "");//基站信号 2
		terminalInfo.put("location_cd3", "");//位置区域码 3
		terminalInfo.put("lbs_num3", "");//基站编号 3
		terminalInfo.put("lbs_signal3", "");//基站信号 3
		terminalInfo.put("telecom_sys_id", "");//电信系统识别码
		terminalInfo.put("telecom_net_id", "");//电信网络识别码
		terminalInfo.put("telecom_lbs", "");//电信基站
		terminalInfo.put("telecom_lbs_signal", "");//电信基站信号
		
		bizContent.setTerminalInfo(terminalInfo);//终端信息

		request.setBizContent(bizContent);
		CardbusinessQrcodeQrgenerateResponseV3 response;

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
