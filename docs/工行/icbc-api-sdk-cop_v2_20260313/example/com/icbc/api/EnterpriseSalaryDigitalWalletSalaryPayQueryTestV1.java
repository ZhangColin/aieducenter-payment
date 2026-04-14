package com.icbc.api;

import com.icbc.api.request.EnterpriseSalaryDigitalWalletSalaryPayQueryRequestV1;
import com.icbc.api.request.EnterpriseSalaryDigitalWalletSalaryPayQueryRequestV1.EnterpriseSalaryDigitalWalletSalaryPayQueryV1Biz;
import com.icbc.api.response.EnterpriseSalaryDigitalWalletSalaryPayQueryResponseV1;

public class EnterpriseSalaryDigitalWalletSalaryPayQueryTestV1 {
	
	public void test() {
		String APP_ID = "11111111";
		String MY_PRIVATE_KEY = new String("11");
		String APIGW_PUBLIC_KEY = "222";
		String AESKey = "11";

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
				IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null,null,null,null);

		EnterpriseSalaryDigitalWalletSalaryPayQueryRequestV1 request = new EnterpriseSalaryDigitalWalletSalaryPayQueryRequestV1();
		request.setServiceUrl("https://ip:port/api/enterprise/salary/digitalwallet/salarypayquery/V1");
		EnterpriseSalaryDigitalWalletSalaryPayQueryV1Biz bizContent = new EnterpriseSalaryDigitalWalletSalaryPayQueryV1Biz();
		bizContent.setBusinessScene(1);
		bizContent.setChannelType(23);
		bizContent.setInstructionId("123");
		bizContent.setTransactionDate("2020-07-26");
		bizContent.setPayerWalletId("123");
		bizContent.setBak1("123");
		bizContent.setBak2("123");
		
		request.setBizContent(bizContent);

		EnterpriseSalaryDigitalWalletSalaryPayQueryResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				//
			} 
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
