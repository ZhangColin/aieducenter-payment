package com.icbc.api;

import java.util.HashMap;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankLoanDigitalcredentialsUploadtrdinformationRequestV1;
import com.icbc.api.request.MybankLoanDigitalcredentialsUploadtrdinformationRequestV1Biz;
import com.icbc.api.response.MybankLoanDigitalcredentialsUploadtrdinformationResponseV1;

public class MybankLoanDigitalcredentialsUploadtrdinformationTestV1 {
public static void main(String[] args) {
		
		try {

			String apigwPublicKey =  "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
			String privateKey = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
			String appId = "XXXXXXXXXXXXXXXXXX";
			String url = "http://ip/api/mybank/loan/digitalcredentials/uploadtrdinformation/V1";
			
			DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
			MybankLoanDigitalcredentialsUploadtrdinformationRequestV1 request = new MybankLoanDigitalcredentialsUploadtrdinformationRequestV1();
			request.setServiceUrl(url);
			MybankLoanDigitalcredentialsUploadtrdinformationRequestV1Biz bizContent = new MybankLoanDigitalcredentialsUploadtrdinformationRequestV1Biz();
			HashMap<String,Object> bizParaMap = new HashMap<String,Object>();
			
			bizParaMap.put("serialNo", "XXXXXXXXXXXXXXXXXX");
			bizParaMap.put("appNo", "F-DCS");
			bizParaMap.put("areaCode", "");
			bizParaMap.put("employeeCode", "");
			bizParaMap.put("language", "zh_CN");
			bizParaMap.put("transNo", "IImportTradeInfoCocoaService");
			bizParaMap.put("ver", "1.0");
			
			HashMap<String,Object> modelMap = new HashMap<String,Object>();
			modelMap.put("appName", "appname");
			modelMap.put("tradeInfoType", "1");
			modelMap.put("contractNo", "11111111");

			JSONArray jsonArr = new JSONArray();
			JSONObject json = new JSONObject();
			//第一个JSONObject为贸易信息具体内容  
			json.put("fied1","合同时凭证类型,凭证时上送合同编号");
			json.put("fied2","合同时上送凭证号码,凭证时上送合同名称");
			json.put("fied3","合同时上送凭证代码,凭证时上送供应商名称");
			json.put("fied4","合同时上送凭证金额,凭证时上送购货方名称");
			json.put("fied5","合同时上送凭证日期,凭证时上送“合同金额");
			json.put("fied6","币种");
			json.put("fied7","供应商证件类型");
			json.put("fied8","供应商证件号码");
			json.put("fied9","购货方证件类型");
			json.put("fied10","合同时上送购货方证件号码,凭证时上送购货方证件号码");
			json.put("fied11","合同时上送空,凭证时上送购货方名称");
			json.put("fied12","合同时上送空,凭证时上送购货方证件类型");
			jsonArr.add(json);
			
			//第二个JSONObject之后为贸易信息具体内容    
			JSONObject json1 = new JSONObject();
			json.put("fied1","合同时凭证类型,凭证时上送合同编号");
			json.put("fied2","合同时上送凭证号码,凭证时上送合同名称");
			json.put("fied3","合同时上送凭证代码,凭证时上送供应商名称");
			json.put("fied4","合同时上送凭证金额,凭证时上送购货方名称");
			json.put("fied5","合同时上送凭证日期,凭证时上送“合同金额");
			json.put("fied6","币种");
			json.put("fied7","供应商证件类型");
			json.put("fied8","供应商证件号码");
			json.put("fied9","购货方证件类型");
			json.put("fied10","合同时上送购货方证件号码,凭证时上送购货方证件号码");
			json.put("fied11","合同时上送空,凭证时上送购货方名称");
			json.put("fied12","合同时上送空,凭证时上送购货方证件类型");
			//......           
			jsonArr.add(json1);
			
			modelMap.put("tradeInfoList", jsonArr);
			modelMap.put("class","com.icbc.hz1.service.dcs.tradeinfo.dto.ImportTradeInfoInModel");

			bizParaMap.put("model", modelMap);

			bizContent.setParam(bizParaMap);
			request.setBizContent(bizContent);
			
			MybankLoanDigitalcredentialsUploadtrdinformationResponseV1 response;		
			response = client.execute(request);
			if (response.isSuccess()) {
				String a = response.getReturnContent();
				log.debug(a);
				
			} else {
				
			}
		}  catch (IcbcApiException e1) {
			e1.printStackTrace();
		} 
	}
}
