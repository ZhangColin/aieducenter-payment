package com.icbc.api;

import com.icbc.api.request.DcepMerchantReceiptReceiptRequestV1.DcepMerchantReceiptReceiptRequestV1Biz;
import com.icbc.api.request.DcepMerchantReceiptReceiptRequestV1;
import com.icbc.api.response.DcepMerchantReceiptReceiptResponseV1;

public class DcepMerchantReceiptReceiptRequestTestV1 {
	public void testCop() {
		String APP_ID = "";
		String MY_PRIVATE_KEY = new String("");
		String APIGW_PUBLIC_KEY = "";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		DcepMerchantReceiptReceiptRequestV1 request = new DcepMerchantReceiptReceiptRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/pay/digitalwallet/visualcard/expenditure/V1");
		DcepMerchantReceiptReceiptRequestV1Biz bizContent = new DcepMerchantReceiptReceiptRequestV1Biz();
		bizContent.setBusiscene(1);
		bizContent.setChantype(1);
    	bizContent.setAcctype(1);
   		bizContent.setOpertype(1);
    	bizContent.setInstructionId("0021500049");
        bizContent.setPpseqno("12324465");
		bizContent.setQrcode("12324465");
		bizContent.setPayer_wallet_id("12324");
    	bizContent.setPayer_wallet_name("12324");
    	bizContent.setPayer_bankcode("12324");
    	bizContent.setToken("12324");
        bizContent.setMerchant_wallet_id("12324");
		bizContent.setMerchant_wallet_name("12324");
		bizContent.setMerchant_id(12345L);
    	bizContent.setMerchant_name("123");
        bizContent.setCommodity("123");
    	bizContent.setBusstype("2");
    	bizContent.setBusscode("2");
		bizContent.setMerchant_order("12324");
		bizContent.setMerchant_order_notes("12345");
    	bizContent.setExpire_minutes(10);
    	bizContent.setTrandate("12324");
		bizContent.setTrantime("12324");
    	bizContent.setAmount(12213L);
		bizContent.setCurrtype(1);
    	bizContent.setTranplace("1");
    	bizContent.setSummary("12324");
		bizContent.setTerm_id("12324");
		bizContent.setSign("12324");
        bizContent.setBak1("12324");
		bizContent.setBak2("12324");
		
		request.setBizContent(bizContent);

		DcepMerchantReceiptReceiptResponseV1 response;
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
