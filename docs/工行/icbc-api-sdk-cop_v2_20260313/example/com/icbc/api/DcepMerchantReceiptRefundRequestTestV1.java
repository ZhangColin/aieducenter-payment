package com.icbc.api;

import com.icbc.api.request.DcepMerchantReceiptRefundRequestV1;
import com.icbc.api.request.DcepMerchantReceiptRefundRequestV1.DcepMerchantReceiptRefundRequestV1Biz;
import com.icbc.api.response.DcepMerchantReceiptRefundResponseV1;

public class DcepMerchantReceiptRefundRequestTestV1 {
	
	public void testCop() {
		String APP_ID = "";
		String MY_PRIVATE_KEY = new String("");
		String APIGW_PUBLIC_KEY = "";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		DcepMerchantReceiptRefundRequestV1 request = new DcepMerchantReceiptRefundRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/pay/digitalwallet/visualcard/expenditure/V1");
		DcepMerchantReceiptRefundRequestV1Biz bizContent = new DcepMerchantReceiptRefundRequestV1Biz();
		bizContent.setFex_merchant_order("12324");
		bizContent.setBusiscene(1);
		bizContent.setChantype(1);
    	bizContent.setInstructionId("0021500049");
        bizContent.setMerchant_wallet_id("12324");
		bizContent.setMerchant_wallet_name("12324");
		bizContent.setMerchant_id(12345L);
    	bizContent.setMerchant_name("12324");
        bizContent.setMerchant_order("12324");
		bizContent.setTrandate("12324");
		bizContent.setTrantime("12324");
    	bizContent.setAmount(111L);
        bizContent.setCurrtype(1);
		bizContent.setSummary("12324");
		bizContent.setTerm_id("12324");
    	bizContent.setSign("12324");
        bizContent.setBak1("12324");
		bizContent.setBak2("12324");
		
		request.setBizContent(bizContent);

		DcepMerchantReceiptRefundResponseV1 response;
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
