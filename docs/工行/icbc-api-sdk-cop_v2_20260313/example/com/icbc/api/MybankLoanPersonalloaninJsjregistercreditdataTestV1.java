package com.icbc.api;


import com.icbc.api.request.MybankLoanPersonalloaninJsjregistercreditdataRequestV1;
import com.icbc.api.request.MybankLoanPersonalloaninJsjregistercreditdataRequestV1.MybankLoanPersonalloaninJsjregistercreditdataRequestV1;
import com.icbc.api.response.MybankLoanPersonalloaninJsjregistercreditdataResponseV1;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;

public class MybankLoanPersonalloaninJsjregistercreditdataTestV1 {
	protected static final String appId="10000000000000871208";
	protected static final String privateKey="-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr56i1uTspdZi0zqaerI2EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
	protected static final String apigwPublicKey="MIGfMA1GCSqGSIb4DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj04ctKM7KV+16bWZ6BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) throws IcbcApiException{
		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
		MybankLoanPersonalloaninJsjregistercreditdataV1 request = new MybankLoanPersonalloaninJsjregistercreditdataV1();
		request.setServiceUrl("http://ip:port/api/mybank/loan/personalloanin/jsjregistercreditdata/V1");
		MybankLoanPersonalloaninJsjregistercreditdataBizContent bizContent = new MybankLoanPersonalloaninJsjregistercreditdataBizContent();
		                                                                               
		//公共参数
		bizContent.setSerialNo("123456789012345678901234567");
		bizContent.setAppNo("F-APIP");
		bizContent.setAreaCode("0010100000");
		bizContent.setLanguage("zh_CN");
		bizContent.setTransNo("AuthorizeCreditService");
		bizContent.setVer("1.0");
		bizContent.setTurnPageFlag("");
		bizContent.setBeginRow("");
		bizContent.setRowCount("");
		
		//私有参数
		bizContent.setCISNAME("zhangsan");
		bizContent.setCERT_TYPE("0");
		bizContent.setCERT_CODE("123456789012345678");
		bizContent.setCELLPHONE("13112345678");
		bizContent.setBUSI_AREA("0001234567");
		bizContent.setSCENE_FLAG("2");
		bizContent.setGRANT_TYPE("99");
		bizContent.setFACE_EAP_BATCH("09843q980120931209375");
		bizContent.setBUSI_TYPE("0001");
		bizContent.setGRANT_RANGE("01");
		bizContent.setCERT_EAP_BATCH("asdf92341089887501131");
			
        System.out.println("-------金闪借分行回调入参--------"+bizContent);
		
		
		request.setBizContent(bizContent);
		System.out.println("-------bizContent--------"+bizContent);
		MybankLoanPersonalloaninJsjregistercreditdataResponseV1 response=new MybankLoanPersonalloaninJsjregistercreditdataResponseV1();
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
