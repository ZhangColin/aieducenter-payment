package com.icbc.api;

import com.icbc.api.request.EnterpriseSalaryDigitalWalletSalaryPayRequestV1;
import com.icbc.api.request.EnterpriseSalaryDigitalWalletSalaryPayRequestV1.EnterpriseSalaryDigitalWalletSalaryPayV1Biz;
import com.icbc.api.response.EnterpriseSalaryDigitalWalletSalaryPayResponseV1;

public class EnterpriseSalaryDigitalWalletSalaryPayTestV1 {
	
	public void test() {
		String APP_ID = "1111111";
		String MY_PRIVATE_KEY = new String("11");
		String APIGW_PUBLIC_KEY = "222";
		String AESKey = "11";

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
				IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null,null,null,null);

		EnterpriseSalaryDigitalWalletSalaryPayRequestV1 request = new EnterpriseSalaryDigitalWalletSalaryPayRequestV1();
		request.setServiceUrl("https://ip:port/api/enterprise/salary/digitalwallet/salarypay/V1");
		EnterpriseSalaryDigitalWalletSalaryPayV1Biz bizContent = new EnterpriseSalaryDigitalWalletSalaryPayV1Biz();
		bizContent.setChannelType(23);
		bizContent.setAccountType(21);
		bizContent.setInstructionId("123");
		bizContent.setProtocalNo("123");
		bizContent.setPayerWalletId("123");
		bizContent.setPayerWalletName("123");
		bizContent.setPayerBankcode("123");
		bizContent.setPayerName("123");
		bizContent.setPayeeWalletId("123");
		bizContent.setPayeeWalletName("123");
		bizContent.setPayeeBankcode(123);
		bizContent.setPayeeName("123");
		bizContent.setPayeeTelno("123");
		bizContent.setTransactionDate("2020-07-26");
		bizContent.setTransactionTime("00-00-00");
		bizContent.setAmount(123);
		bizContent.setCurrencyType(1);
		bizContent.setNots("123");
		bizContent.setBak1("123");
		bizContent.setBak2("123");
		
		request.setBizContent(bizContent);

		EnterpriseSalaryDigitalWalletSalaryPayResponseV1 response;
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
