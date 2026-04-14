package com.icbc.api;

import com.icbc.api.request.CertCampusCampuserregisterRequestV1;
import com.icbc.api.request.CertCampusCampuserregisterRequestV1.CertCampusCampuserregisterRequestV1Biz;
import com.icbc.api.response.CertCampusCampuserregisterResponseV1;



public class CertCampusCampuserregisterTestV1 {
	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQ...";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "100000000000000XXXXX";
	

	public void sendtest(){
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CertCampusCampuserregisterRequestV1 request = new CertCampusCampuserregisterRequestV1();
		request.setServiceUrl("https://ip:port/api/cert/campus/campuserregister/V1");

		CertCampusCampuserregisterRequestV1Biz bizContent = new CertCampusCampuserregisterRequestV1Biz();
		
		//#############  根据实际情况设置好参数  ##############
		bizContent.setAppInfo("appinfo01");
		bizContent.setAppVersion("AppVersion01");
		bizContent.setBthDate("19900101");
		bizContent.setCustId("custId01");
		bizContent.setEpDate("20301212");
		bizContent.setGender("0");
		bizContent.setIdNum("idNUM01");
		bizContent.setIdType("idTYPE01");
		bizContent.setImg("imbdetail...");
		bizContent.setName("xiaoming");
		bizContent.setNation("China");
		bizContent.setSchoolId("school01");
		bizContent.setStudentId("student01");

		request.setBizContent(bizContent);

		CertCampusCampuserregisterResponseV1 response;
		try {
			response = (CertCampusCampuserregisterResponseV1) client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("返回代码为："+response.getReturnCode());
				System.out.println("返回消息为："+response.getReturnMsg());
			} else {
				// 失败
				System.out.println("返回代码为："+response.getReturnCode());
				System.out.println("返回消息为："+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		new CertCampusCampuserregisterTestV1().sendtest();
	}


}
