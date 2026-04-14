package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberCustAuthinfoModifyRequestV1;
import com.icbc.api.response.BcssMemberCustAuthinfoModifyResponseV1;

import java.io.IOException;

public class BcssMemberCustAuthinfoModifyTestV1 {
	protected static final String MY_PRIVATE_KEY = "";

	// APIGW_PUBLIC_KEY
	protected static final String APIGW_PUBLIC_KEY = "";

	// APP_ID
	protected static final String APP_ID = "100000000000040xxxxx";

	protected static final String RSA = "RSA";
	protected static final String RSA2 = "RSA2";

	public static String format(int number) {
		return String.format("%09d", number);
	}

	public static void main(String[] args)  {

		try {
			testApi();
		} catch (IcbcApiException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}



	public static void testApi() throws IcbcApiException, IOException {
		JSONObject chanelJson=new JSONObject();
		chanelJson.put("clientType","3");
//		chanelJson.put("bcssBasChannelInfo",本地存储的auth人脸 渠道信息);
//		chanelJson.put("bcssBasEncryptionVersion",bas加密构件的版本号，即BASApi类的getVersion());

		BcssMemberCustAuthinfoModifyRequestV1 request = new BcssMemberCustAuthinfoModifyRequestV1();
		request.setServiceUrl("http://***.****.***.***:**/api/bcss/member/cust/authinfo/modify/V1");

		BcssMemberCustAuthinfoModifyRequestV1.BcssMemberCustAuthinfoModifyRequestBizV1 bizContent = new BcssMemberCustAuthinfoModifyRequestV1.BcssMemberCustAuthinfoModifyRequestBizV1();
		bizContent.setClientTransNo("12345678912345678922");
		bizContent.setTimeStamp("2021-10-25 10:10:12");
		bizContent.setCorpId("2000000005");
		bizContent.setOperType("4");//厂商编号
		bizContent.setStartDate("2025-10-10 12:00:00");//硬件序列号
		//2000000005
		bizContent.setEndDate("2025-12-10 12:00:00");//硬件序列号
		bizContent.setMerNo("20000000050000000001");//默认送空字符串
		bizContent.setUserType("25");
		bizContent.setUserInfo("assdfghjlzc");
		request.setBizContent(bizContent);
		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		BcssMemberCustAuthinfoModifyResponseV1 response = client.execute(request);



	}

}