package com.icbc.api;

import com.icbc.api.request.DcepMerchantReceiptQueryRequestV1;
import com.icbc.api.request.DcepMerchantReceiptQueryRequestV1.DcepMerchantReceiptQueryRequestV1Biz;
import com.icbc.api.response.DcepMerchantReceiptQueryResponseV1;

public class DcepMerchantReceiptQueryRequestTestV1 {

	public void testCop() {
		String APP_ID = "";
		String MY_PRIVATE_KEY = new String("");
		String APIGW_PUBLIC_KEY = "";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		DcepMerchantReceiptQueryRequestV1 request = new DcepMerchantReceiptQueryRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/pay/digitalwallet/visualcard/expenditure/V1");
		DcepMerchantReceiptQueryRequestV1Biz bizContent = new DcepMerchantReceiptQueryRequestV1Biz();
		bizContent.setBusitype(1);
		bizContent.setBusiscene(1);
		bizContent.setChantype(1);
    	bizContent.setInstructionId("0021500049");
        bizContent.setMerchant_wallet_id("1234567");
		bizContent.setMerchant_id(12345L);
    	bizContent.setMerchant_order("123456");
    	bizContent.setTrandate("123456");
        bizContent.setBak1("123456");
		bizContent.setBak2("123456");
		request.setBizContent(bizContent);

		DcepMerchantReceiptQueryResponseV1 response;
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
