package com.icbc.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.icbc.api.request.MybankLoanDigitalcreditAddSuppliersRequestV1;
import com.icbc.api.request.MybankLoanDigitalcreditAddSuppliersRequestBizV1;
import com.icbc.api.response.MybankLoanDigitalcreditAddSuppliersResponseV1;

public class MybankLoanDigitalcreditAddSuppliersTestV1 {

	public static void main(String[] args) throws ParseException {

		try {
			String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
			String privateKey = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
			String appId = "10000000000000005509";
			String url = "http://ip:port/api/mybank/loan/digitalcredit/addsuppliers/V1";

			DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
			MybankLoanDigitalcreditAddSuppliersRequestV1 request = new MybankLoanDigitalcreditAddSuppliersRequestV1();
			request.setServiceUrl(url);
			
			MybankLoanDigitalcreditAddSuppliersRequestBizV1 bizContent = new MybankLoanDigitalcreditAddSuppliersRequestBizV1();
			bizContent.setSerialNo("20180205121212000000000");
			bizContent.setAppNo("F-APIP");
			bizContent.setLanguage("zh_CN");
			bizContent.setTransNo("IDigitalCreditCocoaService");
			bizContent.setPlatformNo("PF1001");
			bizContent.setCustomerNumb("494de47b-e658-4dd5-b6a6-e8f5bdfe4adc");
			bizContent.setProjectCode("3602000519200725346");
			bizContent.setProjectName("XX高速公路项目贷款");

			List<Map<String, Object>> suppliers = new ArrayList<>();
			Map<String, Object> supplier1 = new HashMap<>();
			supplier1.put("companyName", "水城XXXX有限公司");
			supplier1.put("companyCertType", "0");
			supplier1.put("companyCertNo", "010XXXX6-9");
			supplier1.put("legalPersonName", "");
			supplier1.put("legalPersonCertType", "");
			supplier1.put("legalPersonCertNo", "");
			supplier1.put("legalPersonPhone", "");
			suppliers.add(supplier1);

			Map<String, Object> supplier2 = new HashMap<>();
			supplier2.put("companyName", "中交XXXX有限公司");
			supplier2.put("companyCertType", "3");
			supplier2.put("companyCertNo", "100XXXXXXXXX931");
			supplier2.put("legalPersonName", "");
			supplier2.put("legalPersonCertType", "");
			supplier2.put("legalPersonCertNo", "");
			supplier2.put("legalPersonPhone", "");
			suppliers.add(supplier2);
			bizContent.setSuppliers(suppliers);

			request.setBizContent(bizContent);
			MybankLoanDigitalcreditAddSuppliersResponseV1 response = client.execute(request);

			if (response.isSuccess()) {
				Map<String, Object> a = response.getReturnContent();
				System.out.println(a);
			} else {

			}

		} catch (IcbcApiException e1) {
			e1.printStackTrace();
		}
	}
}
