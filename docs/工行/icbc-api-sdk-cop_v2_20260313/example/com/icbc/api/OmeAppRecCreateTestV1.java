package com.icbc.api.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.OmeAppRecCreateRequestV1;
import com.icbc.api.request.OmeAppRecCreateRequestV1.OmeAppRecCreateRequestBiz;
import com.icbc.api.response.OmeAppRecCreateResponsetV1;

public class OmeAppRecCreateTestV1 {

	public static void main(String[] args) {
		String appId="1000xxxx000xxxx0xxxx";
		String privateKey="-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIljufDHFcWzREiZo/JE4joqgHG2j+hot8YM9Gtuo9sBOA6pB8DRHm5R+kg0VAs0B65NflHd4bEa7hVJhr4xYfnsjtVNxZUdx2eg0PD49GBoFhQtHsgsceVF4F/4+vDim9Bm92CBFncKoJI3Uailh0qDbsfsEFY91zlQomQ6VlKbAgMBAAECgYB9lKpha5h7HWFM+yLgUBZ5FGBTkIv5474XScAgcwFTLcoPnHF5zawDKuubFDm+A9EBgdP1ANtLB1KYIp0HPBxQ9iL8yKdFMf3A6Ty9iABdwC3f+cMRpt5Q93WKgJaQ/S5xWi+pT4yUFcoSoLp9WwOVptiBC9Ho6qScTeIxCeOAAQJBAOt/uMmxmfIzQQcpaXo/e0M5Jlq3Ew/CH2mA9vs8eaR/zC5SO9Bvorn8GzvnZZlL47H2fVlBP7N1zjOU9SG5kSECQQCVWY95h+NEdHryR6MZPtp5mYJwVz+ZeRtf5oNKLxMOP6WNSlMwa9+nwuyt9RLRsrcME3ij9W1BkhKOSigR9eA7AkBnDtXHLjm4jEWA5NaL8Qv4NyJZwJRO6RzmBtIO0EBLhMHN457ZcIE98t3YMUN8+oECeGQSk0NVPdCnMiC0SYkBAkAH3nElyRgbWjvEGiwov1wJfiIWQzKRpoTft6iBNxu863OQjzL04Somj6mivL/iFpV4aGo50UuRYd3ia1vTHm5TAkEArjwh+3XyJMMMBu11JMgNO2r0+2G9vVIBR6oX+9nkbYh8cFtspTAU3Mu/DePiUnr47zAAMI2CGqDMqwN0qSIUpw==";
		String publicKey="-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, publicKey);

		OmeAppRecCreateRequestV1 request = new OmeAppRecCreateRequestV1();
		OmeAppRecCreateRequestBiz bizContent = new OmeAppRecCreateRequestBiz();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		
		bizContent.setVersion("1.0");
		bizContent.setServiceID("10");
		bizContent.setTranID("2019xxxx135xxxx020xxxxxxxx");
		bizContent.setRetryFlag("0");
		bizContent.setAppType("2");
		bizContent.setAppStatus("8");
		bizContent.setFacAppNo("FAC1236546541");
		bizContent.setDevID("0000xxxx0020003");
		bizContent.setDevSN("180438");
		bizContent.setBrandClass("47");
		bizContent.setSmallClass("MAINCLASS10135");
		bizContent.setAppDesc("TEST API CREATE");
		bizContent.setSerType("3");
		bizContent.setCreateTime(sdf1.format(new Date()));
		bizContent.setCreateUser("createUser");
		bizContent.setCreateUserPhone("13906010600");
		bizContent.setNextUser("confirmUser");
		bizContent.setNextUserPhone("13906010601");
		bizContent.setFacAdded("1");

		bizContent.setStaWorkTime("20190221102232");
		bizContent.setEndWorkTime("20190222235412");
		bizContent.setNewDevid("");
		
		request.setBizContent(bizContent);
		//String pc="http://ip:port";
		String pc="http://localhost:8080";
		request.setServiceUrl(pc + "/api/ome/recapp/V1/create");
		request.setTimestamp(sdf.format(new Date()));
		request.setNeedEncrypt(false);
		try {
			OmeAppRecCreateResponsetV1 response = client.execute(request, "msgId");
			if (response.isSuccess() &&  response.getReturnCode()==0 ) {
				// 业务成功处理
				response.getResponseBizContent();//
				/*System.out.println("调用结果："+response.getReturnMsg());*/
			} else {
				// 失败
			/*	System.out.println("调用结果："+response.getReturnCode());
				System.out.println("调用结果："+response.getReturnMsg());*/
			}
		} catch (IcbcApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
