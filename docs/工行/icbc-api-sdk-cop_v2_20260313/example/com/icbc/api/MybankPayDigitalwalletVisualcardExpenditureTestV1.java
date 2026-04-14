package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalwalletVisualcardExpenditureRequestV1;
import com.icbc.api.request.MybankPayDigitalwalletVisualcardExpenditureRequestV1.MybankPayDigitalwalletVisualcardExpenditureRequestV1Biz;
import com.icbc.api.response.MybankPayDigitalwalletVisualcardExpenditureResponseV1;

public class MybankPayDigitalwalletVisualcardExpenditureTestV1 {

	public void testCop() {
		String APP_ID = "";
		String MY_PRIVATE_KEY = new String("");
		String APIGW_PUBLIC_KEY = "";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		MybankPayDigitalwalletVisualcardExpenditureRequestV1 request = new MybankPayDigitalwalletVisualcardExpenditureRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/pay/digitalwallet/visualcard/expenditure/V1");
		MybankPayDigitalwalletVisualcardExpenditureRequestV1Biz bizContent = new MybankPayDigitalwalletVisualcardExpenditureRequestV1Biz();
		bizContent.setPayerWalletId("walletid");
		bizContent.setPayeeWalletId("walletid");
		bizContent.setInstructionId("12345");
		bizContent.setConsumeTermId("12345");
		bizContent.setTradeSign("12345");
		bizContent.setChanType(111);
		bizContent.setAmount(11L);

		request.setBizContent(bizContent);

		MybankPayDigitalwalletVisualcardExpenditureResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				// Assert.assertEquals(response.getReturnCode(), 0);
			} else {
				// 失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}
