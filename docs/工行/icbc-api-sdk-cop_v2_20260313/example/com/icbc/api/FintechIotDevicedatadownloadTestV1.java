package com.icbc.api;

import java.util.Random;

import com.icbc.api.request.FintechIotDevicedatadownloadRequestV1;
import com.icbc.api.request.FintechIotDevicedatadownloadRequestV1.FintechIotDevicedatadownloadRequestV1Biz;
import com.icbc.api.response.FintechIotDevicedatadownloadResponseV1;

public class FintechIotDevicedatadownloadTestV1 {
	
	//应用私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----Ev....";
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//APID
	protected static final String APP_ID = "10000000000002XXXXXX";
	
	

	public void sendtest(){
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		FintechIotDevicedatadownloadRequestV1 request = new FintechIotDevicedatadownloadRequestV1();
		request.setServiceUrl("https://ip:port/api/fintech/iot/devicedatadownload/V1");

		FintechIotDevicedatadownloadRequestV1Biz bizContent = new FintechIotDevicedatadownloadRequestV1Biz();
		
		//#############  根据实际情况设置好参数  ##############
		bizContent.setAPP_ID("F-XXXX");
		bizContent.setAPP_KEY("APP_KEY01");
		bizContent.setEVENT_ID("19900115");
		bizContent.setEVENT_BUSTYPE("0001");
		bizContent.setEVENT_TYPE("01");
		bizContent.setEVENT_ZONENO("015");
		bizContent.setEVENT_KEYS("ioth/iot/gdxchanneltopic12");
		bizContent.setDATA("{\"privateInfo\":{\"serviceId\":\"qc.order\",\"parkCode\":\"123435\",\"carNo\":\"carno01\"...}}");

		
		request.setBizContent(bizContent);
		String msgId = bizContent.getAPP_ID()+Math.random()*1000;    //确保证MSGID的唯一性msgId

		FintechIotDevicedatadownloadResponseV1 response;
		try {
			response = (FintechIotDevicedatadownloadResponseV1) client.execute(request, msgId);
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("返回代码为："+response.getReturnCode());
				System.out.println("返回消息为："+response.getReturnMsg());
				System.out.println("返回成功失败标志为："+response.getOPPER_FLAG());
				//System.out.println("返回成功失败标志为："+response.getDATA());
			} else {
				// 失败
				System.out.println("返回代码为："+response.getReturnCode());
				System.out.println("返回消息为："+response.getReturnMsg());
				System.out.println("返回成功失败标志为："+response.getOPPER_FLAG());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		new FintechIotDevicedatadownloadTestV1().sendtest();
	}


}
