package com.icbc.api;

import com.icbc.api.request.MybankLoanPersonalloaninCustomerRiskcreditEntryRequestV1;
import com.icbc.api.request.MybankLoanPersonalloaninCustomerRiskcreditEntryRequestV1.MybankLoanPersonalloaninCustomerRiskcreditEntryBizContent;
import com.icbc.api.response.MybankLoanPersonalloaninCustomerRiskcreditEntryResponseV1;

public class MybankLoanPersonalloaninCustomerRiskcreditEntryTestV1 {
	protected static final String appId="10000000000000084001";
	protected static final String privateKey="-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr56i1uTspdZi0zqaerI2EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
	protected static final String apigwPublicKey="MIGfMA1GCSqGSIb4DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj04ctKM7KV+16bWZ6BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) throws IcbcApiException{
		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
		MybankLoanPersonalloaninCustomerRiskcreditEntryRequestV1 request = new MybankLoanPersonalloaninCustomerRiskcreditEntryRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/loan/personalloanin/customer/riskcredit/entry/V1");
		MybankLoanPersonalloaninCustomerRiskcreditEntryBizContent bizContent = new MybankLoanPersonalloaninCustomerRiskcreditEntryBizContent();
		bizContent.setTradeType("7");
		bizContent.setBusiType("15");
		bizContent.setSerNum("00200202303101100013");
		bizContent.setApplyDate("2023-03-10 11:11:46");
		bizContent.setDataSrc("16");
		bizContent.setZoneno("00200");
		bizContent.setBlZoneno("00200");
		bizContent.setBrno("00500");
		bizContent.setTellerNo("123456789");
		
       System.out.println("-------房贷客户风筛授信录入入参--------"+bizContent);
		
		request.setBizContent(bizContent);
		System.out.println("-------bizContent--------"+bizContent);
		MybankLoanPersonalloaninCustomerRiskcreditEntryResponseV1 response=new MybankLoanPersonalloaninCustomerRiskcreditEntryResponseV1();
		try{
			response = client.execute(request);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(response.isSuccess()){
			System.out.println("success!");
		}else{
			System.out.println("fail"+response.getReturnMsg());
		}
	}
}
