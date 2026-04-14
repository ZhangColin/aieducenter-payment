package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.EntrustprtlMngRequestV1;
import com.icbc.api.response.EntrustprtlMngResponseV1;

public class EntrustprtlMngTest {


	private static final String APIGW_PUBLIC_KEY = null;
	private static final String MY_PRIVATE_KEY = null;

	public static void main(String[] args) {
		String appId = null;
		DefaultIcbcClient client = new DefaultIcbcClient(appId, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		EntrustprtlMngRequestV1 request = new EntrustprtlMngRequestV1();		
		request.setServiceUrl("https://ip:port/api/entrustprtl/mng/V1");
		EntrustprtlMngRequestV1.EntrustprtlMngRequestV1Biz bizContent = new EntrustprtlMngRequestV1.EntrustprtlMngRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setDealFlag("0");
		bizContent.setProjectId("PJ140014011000002153");
		bizContent.setBusiCode("10002");
		bizContent.setBankNo("102");
		bizContent.setAcctNo("6222020200000072500");
		bizContent.setAcctName("XUZZ");
		bizContent.setMobile("15158114810");
		bizContent.setVerifyCode("125336");
		bizContent.setSendNo("0000000026");
		bizContent.setIdType("0");
		bizContent.setIdNo("331082198911178108");
		bizContent.setPrtEnddate("2018-03-02");
		bizContent.setPrtlLmtAmt("20000000");
		bizContent.setQueryItem1("");
		//.........
		bizContent.setQueryItem15("");


		EntrustprtlMngResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());

			} else {
				// 失败
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
