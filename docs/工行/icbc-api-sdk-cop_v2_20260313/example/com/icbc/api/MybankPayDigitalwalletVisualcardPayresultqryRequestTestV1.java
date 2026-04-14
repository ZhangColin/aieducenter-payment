package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalwalletVisualcardPayresultqryRequestV1;
import com.icbc.api.request.MybankPayDigitalwalletVisualcardPayresultqryRequestV1.MybankPayDigitalwalletVisualcardPayresultqryRequestV1Biz;
import com.icbc.api.response.MybankPayDigitalwalletVisualcardPayresultqryResponseV1;

public class MybankPayDigitalwalletVisualcardPayresultqryRequestTestV1 {

	public void testCop() {
		String APP_ID = "";
		String MY_PRIVATE_KEY = "";
		String APIGW_PUBLIC_KEY = "";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		MybankPayDigitalwalletVisualcardPayresultqryRequestV1 request = new MybankPayDigitalwalletVisualcardPayresultqryRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/pay/digitalwallet/visualcard/payresultqry/V1");
		MybankPayDigitalwalletVisualcardPayresultqryRequestV1Biz bizContent = new MybankPayDigitalwalletVisualcardPayresultqryRequestV1Biz();
		bizContent.setVisualCardWalletId("walletid");
		bizContent.setFbusiscene(1);
		bizContent.setFexinstructionId("12345");
		bizContent.setChanType(111);

		request.setBizContent(bizContent);

		MybankPayDigitalwalletVisualcardPayresultqryResponseV1 response;
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
