package com.icbc.api;

import java.util.HashMap;

import com.icbc.api.request.MybankDepositQrcodePayRequestV2;
import com.icbc.api.request.MybankDepositQrcodePayRequestV2.MybankDepositQrcodePayRequestV2Biz;
import com.icbc.api.response.MybankDepositQrcodePayResponseV2;

/**
 * 预收押金接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankDepositQrcodePayV2Test {
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	//2、appid
	protected static final String APP_ID = "100000000****7354649";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1fMfRfOPOPKDH+1cdiBDRtu6SnTv2sFhQ+pezrcMyoCY+psXhuAvtkoR2yDzC9hnsxWczU33TSaD8Le1Y9uG+zVY1LMmh+fkkAt8jlBxd/7bSYy25rks7WlwN+WvqeyFA8A4CNsNY/GqvIZk+Lyrsd1/8IAdD0NM7JCQIaXi/+Z6VO8bD9Wlacd/HKQrxedImlWJVcTzKnS/5Et3eOyFbuNJAJW2UOBjc00OXlOmTXMnE6QR2gw9yYFICvvV7B6lSpyVQhrNfVjMFVRgEEL23PxWiU0NTjdznXtH1vgivUM8FB0wsfaoPdlMuTkHhbp/xu7PUv/JyM/eaBOZ11ScXAgMBAAECggEATSot55NbZMAAeKcUJjlJNXG7RFlYOo5GLsAtqjbybN8NA8xzDILth2ipq7t9okNzSGmydG9Ga4S9zxtT9GScHT0/L0LIxHb9iGM0LvN1kD3+pLuIoI9L64OC6moniT4rKGoo+qYALyC715TIjFkPvhUPmaqgjCQI+/dk3rLBpZWr/rGgwjupDl0rmzpW+/OxeS6P2ErfCaUXUaAE6nC7y17rbZlrOWp7ckOkNoCNXuPW591iFsnww/Iwq9Q4mudSTc5Q82T5FECiI4ImKdJZK598Ahv4yyaS7BbjAyNC/De/yVUgFVEp14AWzV1zkbLzPuWirV8mhgUvcHsrGi9cWQKBgQDp/tUqXd0t0Xl++y1MdUIDEhceFrXiocWIFtm6eXpEVHlcHf5V4Vk4PGgeT2Is6IPn2BnlMIsjZ7sBY251ljfaID0+YLQGgm5H3AJ5ULZCnbVOQX8L71lYQxCTN2VtSzGpSte3vF7yxPxiJ71ndTj2oFx6D9mRhIW2Y4oozI6E0wKBgQDGjeDsrWt731iDQ2q2uCrJbAJ84psOanlntsrcIkA0UIJFtAJkOq2EIfmWijgfL7NfjYkgLA0cmMMheUMjMbyIZkmvW3Ej3K3MDWswe4vFHvPdwwoxGNuhuz9qhST3Apo8nvS5HkKb1WezxU6iAMUxWno/vaASAAMAgsE6gJA6LQKBgFA1s1pnfIbVNGGLtjZsR2mIDPaacFS0gWKp+o6KT5gWoNcrzOAhdBTr2ADz00JznauejxnaLwe+6gKK0/VovdJ+lJ/KREuq8CSfzRVKzn89UkX1+5favvSbYJYmOhVh3i8a4aEw6Bct4RnAZ5OMQi+APtL0le7kz3T64AQmQoBTAoGBAMCKVNMuWEyHCQ5BEfqeHN+2HbN9C1+vYItydsWBcUFJ0QpGxQrs29aGG8Ifxsx3NTozxgWssgpJGPAc5z35vRmIF2w1AEIGfYKigI9DPwaoPVOPDxMNR+5N5fElq/LcUzT179qTvYPgLe/mJZpzpMV3N5UYDWFnIYIdScOxaCsBAoGABQs8EEOKcin/7maWf0CI5tPNj0pPlb5QPSCV8g56aSrh38ijs3PVUNvNL+ytnRVVLKi7QoJbbaGIFycY/C+n1a0cF71kuUgiUNPydUosijtRBTfMgX1aQQLBp/6uvQoEAlPsGNv8PufnG4ZO2nev+HV9qKv5PngNcI9m/Je8dxc=";	
	public static void main(String[] args) throws Exception {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
				
		MybankDepositQrcodePayRequestV2 request = new MybankDepositQrcodePayRequestV2();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://****/api/mybank/deposit/qrcode/scanned/pay/V2");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		MybankDepositQrcodePayRequestV2Biz bizContent = new MybankDepositQrcodePayRequestV2Biz();
		
		bizContent.setMerId("020*****460001");//商户编号
		bizContent.setQrCode("134688****540238");  //付款码
		bizContent.setOutTradeNo("1111116*****666666555"); //外部订单号
		bizContent.setTradeDate("20230515"); //交易日期  格式:YYYYMMDD
		bizContent.setTradeTime("091001"); //交易时间hhmmss
		bizContent.setAttach("1231"); //商户附加信息
		bizContent.setOrderAmt("1"); //交易金额
		bizContent.setSubAppId("wxfb72f1a7d061d631"); //子商户应用号
		
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
		MybankDepositQrcodePayResponseV2 response;
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

