package com.icbc.api;

import com.icbc.api.request.MybankAccountDigitalwalletDetailqueryRequestV1;
import com.icbc.api.response.MybankAccountDigitalwalletDetailqueryResponseV1;

import java.util.Random;

public class MybankAccountDigitalwalletDetailqueryTestV1 {

	
	protected static final String MY_PRIVATE_KEY = "xxx";
	protected static final String APIGW_PUBLIC_KEY = "xxx";
	protected static final String APP_ID = "xxx";

	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankAccountDigitalwalletDetailqueryRequestV1 request = new MybankAccountDigitalwalletDetailqueryRequestV1();
		request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/detailquery/V1");
		MybankAccountDigitalwalletDetailqueryRequestV1.QueryAccountDigitalwalletDetailRequestV1Biz bizContent = new MybankAccountDigitalwalletDetailqueryRequestV1.QueryAccountDigitalwalletDetailRequestV1Biz();


		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";

		bizContent.setCooperatorNo("xxx");
		bizContent.setAgentCooperatorNo("xxx");
		bizContent.setSerialNo("xxx");
		bizContent.setRelatedSerialNo("");
		bizContent.setOriginalSerialNo("");
		bizContent.setWorkDate("2020-12-12");
		bizContent.setWorkTime("12:12:12");
		bizContent.setMac("xx:xx:xx:xx:xx:xx");
		bizContent.setIP("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");
		bizContent.setWalletId("xxx");	
		bizContent.setStrartDate("2000-12-22");
		bizContent.setStrartTime("20:56:00");
		bizContent.setEndDate("3020-12-22");
		bizContent.setEndTime("20:57:00");
		bizContent.setLoanSign("1");
		bizContent.setPageSize(10);
		bizContent.setPageNum(1);
		bizContent.setIsEncrypt("true");
		bizContent.getPostStatus("1");

		request.setBizContent(bizContent);

		MybankAccountDigitalwalletDetailqueryResponseV1 response;
		try {
			response = client.execute(request,msgId);
		if (response.isSuccess()) {
			System.out.println("ReturnCode:"+response.getReturnCode());
			System.out.println("ReturnMsg:"+response.getReturnMsg());
            System.out.println("response:" + response);
			} else {
				// ʧ��
				System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} 
	}
}
