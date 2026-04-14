package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalwalletVisualcardRechargeRequestV1;
import com.icbc.api.request.MybankPayDigitalwalletVisualcardRechargeRequestV1.MybankPayDigitalwalletVisualcardRechargeRequestV1Biz;
import com.icbc.api.response.MybankPayDigitalwalletVisualcardRechargeResponseV1;

public class MybankPayDigitalwalletVisualcardRechargeRequestTestV1 {

	public void test_cop() {
		String APP_ID = "";
		String MY_PRIVATE_KEY = "";
		String APIGW_PUBLIC_KEY = "";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		MybankPayDigitalwalletVisualcardRechargeRequestV1 request = new MybankPayDigitalwalletVisualcardRechargeRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/pay/digitalwallet/visualcard/recharge/V1");
		MybankPayDigitalwalletVisualcardRechargeRequestV1Biz bizContent = new MybankPayDigitalwalletVisualcardRechargeRequestV1Biz();
		bizContent.setPayerWalletId("walletid");
		bizContent.setPayeeWalletId("walletid");
		bizContent.setInstructionId("12345");
		bizContent.setRechargeTermId("12345");
		bizContent.setTradeSign("12345");
		bizContent.setChanType(111);
		bizContent.setAmount(11L);

		request.setBizContent(bizContent);

		MybankPayDigitalwalletVisualcardRechargeResponseV1 response;
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
