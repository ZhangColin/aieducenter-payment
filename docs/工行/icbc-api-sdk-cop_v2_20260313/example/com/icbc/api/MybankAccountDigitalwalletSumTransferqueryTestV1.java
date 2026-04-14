package com.icbc.api;

import com.icbc.api.request.MybankAccountDigitalwalletSumTransferqueryRequestV1;
import com.icbc.api.request.MybankAccountDigitalwalletSumTransferqueryRequestV1.QueryAccountDigitalwalletSumTransferRequestV1Biz;
import com.icbc.api.response.MybankAccountDigitalwalletSumTransferqueryResponseV1;

import java.util.Random;

public class MybankAccountDigitalwalletSumTransferqueryTestV1 {

	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCt78Efg1ML46Z7OP2FLS8IWMfWgimCrNK1XTugiUlJOdm009IfzRQIitg8+hQp0EEQ9EekKN0FHo0NZyuFji+h8zs0wk2tEjpuKfvf+T9dfTqiyzoRux6Lp7eM4VsU5L+VvVcXE5JiTptl86cCtT3YqXK0ZrPFHgTBfudnLKGb+orwsFHk6SMXzzRZLk2qv7cg0QfN4njvLZwIOsZ0SLbTsJW7oypuL7lRT90ndcopaRWe22C1gO5jK8RrLteycheeep8YZo9m5jWJR9TFzrPWTequtsw0KE/9iX5BMt3ygwbofNbsG/mqRTIgRmTTbbrd8A4ovrddGYzRxSZ/44C3AgMBAAECggEBAIUaDlg2H4vellx/PQn3jKE/hxUwn+FerROal/F+fTVq7p1A5u/s+GKdFWJ8IIfq1I/LzzLhDllHirrx800NhIfS4MC728MQSuRYfmVjj8jT9tXy3KoK3hISsxQ8s31mmvFUZdvD32YZLzOyY2BgH0a4TI2edfzmvs7vdNbwQW9g5I3Hy/XCNS6N4TWWbiwAlWfeWnC0wqTXrdZG3UZG6hHpxZZqLSUIk1AdZe9/gbuAecGmi93E7DzomGT2c9UAlFj6pT2Uk5I3gpbFsE088ZqQE1FisuVBH/c2kTqQuQEtK9Ig8ykUia9DSepVBsW1YIBUlPNjLw7i4A9r+iFAZukCgYEA1VgFgSX847/Prridj7gPE1e2Uv3cphvpTPwO2gKkLFUrqIq7FeIDjhGFMDcexzqdEbNGKtkTlOg9mWUXtE4+YqcyfYaK+KdT5FH/YGgoKY16+frYXN1hSEX7miMW4ngEkc+Dp5lhedshWRsVXPC5t6oeR4UzqkFyGtdu0GlH7o0CgYEA0Las4QV/erDbwV+9MFbrdTQ0b9q9yS373bx4WgsxWWOVSg18r+oqJ+18outHa77ET6lS5OP5CVQxSBYhOImSDuk1XZVMl+csKQLEAWqDSem/ZTj8dBtdd1o//N57HRKAoxfq+4BS/qtWLESMx7JfSRnaHDuP5fXqjjMZm/1EDVMCgYBxyVKWQTaSGhmgrKKMprSTsnJx7SgkCDr0bnNVX5dEdeClMej6kOMogLuyrZNi2yYu7Jj9/W1uPDeuerab2id34okRXhL87/HUks9MEgFpuCXg8iM31HMwYvMAkLqB61U0l6M2Xp4YqgEtTz6jpVjIVplzt92IbwN2wXQ/AAf3eQKBgDR/zsqnXGKbPBvPHkKbioynvgzow9ZoB7PF5awYpqgqgXHoJJYHW+VBOpmsfwGeMbksCVrj8U+kauUME/0ptaCkjrTkiiinkJccR/tnNHESJ/+pLu9xtrf/7pqHyAYPEmZz7Lqok7fVl055S8QrnQZBR3jUjqS0j/SG7Akt0sFbAoGBAIRrHqazDr4AHNXI1cflSU1rcmk4Pzy0nDeAZO09xfTbmTAWZaqdY+Nlx8No/6nXzoshpaVHiwFIs9SM06Vk4idlQqMg9g+/DcjbT1GB1Y6YiOnfJ0HpX2kW2JYaZa+IMKDbK3nG+x+RHTxsD9eCaGTfEaZqUjcBnzC1fhB8Mobu";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String APP_ID = "XXXXXXXXXXXXXXXXXXXX";

	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankAccountDigitalwalletSumTransferqueryRequestV1 request = new MybankAccountDigitalwalletSumTransferqueryRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/account/digitalwallet/sumtransferquery/V1");
		MybankAccountDigitalwalletSumTransferqueryRequestV1.QueryAccountDigitalwalletSumTransferRequestV1Biz bizContent = new MybankAccountDigitalwalletSumTransferqueryRequestV1.QueryAccountDigitalwalletSumTransferRequestV1Biz();


		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";

		bizContent.setCooperatorNo("XXXXXXXXXXXX");
		bizContent.setAgentCooperatorNo("XXXXXXXXXXXX");
		bizContent.setSerialNo("XXXXXXXXXXXX");
		bizContent.setRelatedSerialNo("");
		bizContent.setOriginalSerialNo("");
		bizContent.setWorkDate("2020-12-12");
		bizContent.setWorkTime("12:12:12");
		bizContent.setMac("XXXXXXXXXXXX");
		bizContent.setIP("XXXXXXXXXXXX");	
		bizContent.setWalletId("0022000134981491");	
		bizContent.setStartDate("2000-12-22");
		bizContent.setStartTime("00:00:00");
		bizContent.setEndDate("3020-12-22");
		bizContent.setEndTime("23:59:59");
		bizContent.setPageSize(10);
		bizContent.setPageNum(1);

		request.setBizContent(bizContent);

		MybankAccountDigitalwalletSumTransferqueryResponseV1 response;
		try {
			response = client.execute(request,msgId);
		if (response.isSuccess()) {
			System.out.println("ReturnCode:"+response.getReturnCode());
			System.out.println("ReturnMsg:"+response.getReturnMsg());
            System.out.println("response:" + response);
			} else {
				System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} 
	}
}
