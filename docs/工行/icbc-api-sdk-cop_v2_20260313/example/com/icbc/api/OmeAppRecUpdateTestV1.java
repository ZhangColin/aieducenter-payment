package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.OmeAppRecUpdateRequestV1;
import com.icbc.api.request.OmeAppRecUpdateRequestV1.OmeAppRecUpdateRequestBiz;
import com.icbc.api.response.OmeAppRecUpdateResponsetV1;

public class OmeAppRecUpdateTestV1 {

	public static void main(String[] args) {
		String appId="1000xxxx00000000xxxx";
		String privateKey="-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIljufDHFcWzREiZo/JE4joqgHG2j+hot8YM9Gtuo9sBOA6pB8DRHm5R+kg0VAs0B65NflHd4bEa7hVJhr4xYfnsjtVNxZUdx2eg0PD49GBoFhQtHsgsceVF4F/4+vDim9Bm92CBFncKoJI3Uailh0qDbsfsEFY91zlQomQ6VlKbAgMBAAECgYB9lKpha5h7HWFM+yLgUBZ5FGBTkIv5474XScAgcwFTLcoPnHF5zawDKuubFDm+A9EBgdP1ANtLB1KYIp0HPBxQ9iL8yKdFMf3A6Ty9iABdwC3f+cMRpt5Q93WKgJaQ/S5xWi+pT4yUFcoSoLp9WwOVptiBC9Ho6qScTeIxCeOAAQJBAOt/uMmxmfIzQQcpaXo/e0M5Jlq3Ew/CH2mA9vs8eaR/zC5SO9Bvorn8GzvnZZlL47H2fVlBP7N1zjOU9SG5kSECQQCVWY95h+NEdHryR6MZPtp5mYJwVz+ZeRtf5oNKLxMOP6WNSlMwa9+nwuyt9RLRsrcME3ij9W1BkhKOSigR9eA7AkBnDtXHLjm4jEWA5NaL8Qv4NyJZwJRO6RzmBtIO0EBLhMHN457ZcIE98t3YMUN8+oECeGQSk0NVPdCnMiC0SYkBAkAH3nElyRgbWjvEGiwov1wJfiIWQzKRpoTft6iBNxu863OQjzL04Somj6mivL/iFpV4aGo50UuRYd3ia1vTHm5TAkEArjwh+3XyJMMMBu11JMgNO2r0+2G9vVIBR6oX+9nkbYh8cFtspTAU3Mu/DePiUnr47zAAMI2CGqDMqwN0qSIUpw==";
		String publicKey="-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, publicKey);

		OmeAppRecUpdateRequestV1 request = new OmeAppRecUpdateRequestV1();
		OmeAppRecUpdateRequestBiz bizContent = new OmeAppRecUpdateRequestBiz();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		bizContent.setVersion("1.0");
		bizContent.setServiceID("10");
		bizContent.setTranID("2018xxxx135425xxxx1000xxxx");
		bizContent.setRetryFlag("0");
		bizContent.setAppType("2");
		bizContent.setActiveType("21");
		bizContent.setAppStatus("6");
		bizContent.setFacAppNo("FAC1236546541");
		bizContent.setDevID("0000xxxx0020003");
		bizContent.setDevSN("180438");
		bizContent.setSmallClass("MAINCLASS10135");
		bizContent.setStaWorkTime("20170512102232");
		bizContent.setEndWorkTime("20170512235412");
		bizContent.setNextUser("nextUser");
		bizContent.setNextUserPhone("13906010601");
		bizContent.setCurrentUser("currentUser");
		bizContent.setActionDesc("TEST API UPDATE");
		bizContent.setEscort("0010xxxx2");
		bizContent.setIsReplaceMedia("1");
		bizContent.setMedName("sdcv-1");
		bizContent.setMedSn("a000100");
		bizContent.setHandoverUser("0010xxxx3");
		
		
		request.setBizContent(bizContent);
		String pc="http://localhost:8080";
		request.setServiceUrl(pc + "/api/ome/recapp/V1/update");
		request.setTimestamp(sdf.format(new Date()));
		request.setNeedEncrypt(false);
		try {
			OmeAppRecUpdateResponsetV1 response = client.execute(request, "msgId");
			if (response.isSuccess() &&  response.getReturnCode()==0 ) {
				response.getResponseBizContent();//
			} else {

			}
		} catch (IcbcApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
