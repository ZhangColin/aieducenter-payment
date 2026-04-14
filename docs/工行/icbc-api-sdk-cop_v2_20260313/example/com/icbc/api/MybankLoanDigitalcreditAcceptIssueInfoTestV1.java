package com.icbc.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankLoanDigitalcreditAcceptIssueInfoRequestV1;
import com.icbc.api.request.MybankLoanDigitalcreditAcceptIssueInfoRequestV1Biz;
import com.icbc.api.response.MybankLoanDigitalcreditAcceptIssueInfoResponseV1;

public class MybankLoanDigitalcreditAcceptIssueInfoTestV1 {

	private static Log log = LogFactory.getLog(MybankLoanDigitalcreditAcceptIssueInfoTestV1.class);

	public static void main(String[] args) throws ParseException {

		try {
			String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
			String privateKey = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
			String appId = "XXXXXXXXXXXXXXXXXX";
			String url = "http://ip/api/mybank/loan/digitalcredit/acceptIssueInfo/V1";

			DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
			MybankLoanDigitalcreditAcceptIssueInfoRequestV1 request = new MybankLoanDigitalcreditAcceptIssueInfoRequestV1();
			request.setServiceUrl(url);
			MybankLoanDigitalcreditAcceptIssueInfoRequestV1Biz bizContent = new MybankLoanDigitalcreditAcceptIssueInfoRequestV1Biz();
			HashMap<String, Object> bizParaMap = new HashMap<String, Object>();

			bizParaMap.put("SerialNo", "201805131234567");
			bizParaMap.put("AppNo", "F-EBANKC");
			bizParaMap.put("AreaCode", "");
			bizParaMap.put("Teller", "");
			bizParaMap.put("Language", "zh_CN");
			bizParaMap.put("Verify", "");
			bizParaMap.put("TransNo", "IAcceptIssueInfoService");
			bizParaMap.put("VER", "1.0");
			bizParaMap.put("PlatformNo", "02");
			bizParaMap.put("CustomerNumb", "1");
			bizParaMap.put("ECreditNumb", "1234");
			bizParaMap.put("ECreditMoney", "12");
			bizParaMap.put("Currency", "001");
			bizParaMap.put("EffectDate", "20180101");
			bizParaMap.put("PayDate", "20190101");
			bizParaMap.put("PayerBankName", "134");
			bizParaMap.put("PayerBankAccount", "134");
			bizParaMap.put("CertificateType", "01");
			bizParaMap.put("modeType", "02");
			bizParaMap.put("isSecret", "1");
			bizParaMap.put("secretFileNo", "20190610001");
			bizParaMap.put("hasCertificate", "1");

			HashMap<String, Object> certificate = new HashMap<String, Object>();
			certificate.put("name", "12345");
			certificate.put("seqNo", "1");
			certificate.put("certificationBody", "鍙戣瘉涓讳綋");
			certificate.put("certificateNo", "璇佸彿/鏂囧彿");
			certificate.put("certificateNo", "璇佸彿/鏂囧彿");
			certificate.put("obligee", "鏉冨埄浜�");
			certificate.put("endDate", "20191212");
			certificate.put("certificateImageNO", "20190610002");

			List<Map<String, Object>> certificateList = new ArrayList<Map<String, Object>>();
			certificateList.add(certificate);
			bizParaMap.put("certificateList", certificateList);

			HashMap<String, Object> contract = new HashMap<String, Object>();
			contract.put("contractNum", "201906101532");
			contract.put("contractAmt", "123");
			contract.put("contractCur", "001");
			contract.put("tradeContent", "浜ゆ槗鍐呭");
			contract.put("payDate", "20191213");
			contract.put("contractImageNo", "璐告槗鍚堝悓褰卞儚鏂囦欢");

			List<Map<String, Object>> contractList = new ArrayList<Map<String, Object>>();
			contractList.add(contract);
			bizParaMap.put("contractList", contractList);

			HashMap<String, Object> iBillInfo = new HashMap<String, Object>();
			iBillInfo.put("contractNum", "201906101529");
			iBillInfo.put("billSerialNo", "1");
			iBillInfo.put("billMoney", "123.33");
			iBillInfo.put("billSerialNo", "1");
			iBillInfo.put("billCode", "123");
			iBillInfo.put("billNum", "123");
			iBillInfo.put("billMoney", "1230.00");
			iBillInfo.put("billCurrency", "001");
			iBillInfo.put("billKind", "01");
			iBillInfo.put("billImageNo", "123");

			List<Map<String, Object>> iBillInfoList = new ArrayList<Map<String, Object>>();
			iBillInfoList.add(iBillInfo);
			bizParaMap.put("iBillInfoList", iBillInfoList);

			bizContent.setParam(bizParaMap);
			request.setBizContent(bizContent);

			MybankLoanDigitalcreditAcceptIssueInfoResponseV1 response;
			response = client.execute(request);
			if (response.isSuccess()) {
				String a = response.getReturnContent();
				log.info(a);
			}

		} catch (IcbcApiException e1) {
			e1.printStackTrace();
		}

	}
}
