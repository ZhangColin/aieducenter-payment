package com.icbc.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.request.MybankLoanDigitalcreditAcceptDistributionRequestV1;
import com.icbc.api.request.MybankLoanDigitalcreditAcceptDistributionRequestV1Biz;
import com.icbc.api.response.MybankLoanDigitalcreditAcceptDistributionResponseV1;

public class MybankLoanDigitalcreditAcceptDistributionTestV1 {

    public static void main(String[] args) throws ParseException {

        try {
            String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
            String privateKey = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
            String appId = "10000000000000005509";
            String url = "http://ip:port/api/mybank/loan/digitalcredit/acceptdistribution/V1";

            DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
            MybankLoanDigitalcreditAcceptDistributionRequestV1 request = new MybankLoanDigitalcreditAcceptDistributionRequestV1();
            request.setServiceUrl(url);
            MybankLoanDigitalcreditAcceptDistributionRequestV1Biz bizContent = new MybankLoanDigitalcreditAcceptDistributionRequestV1Biz();
            HashMap<String, Object> bizParaMap = new HashMap<String, Object>();

            bizParaMap.put("SerialNo", "201805131234567");
            bizParaMap.put("AppNo", "F-APIP");
            bizParaMap.put("AreaCode", "");
            bizParaMap.put("Teller", "");
            bizParaMap.put("Language", "zh_CN");
            bizParaMap.put("Verify", "");
            bizParaMap.put("TransNo", "IAcceptDistributionService");
            bizParaMap.put("VER", "1.0");
            bizParaMap.put("TurnPage", "0");
            bizParaMap.put("BeginRow", "");
            bizParaMap.put("EndRow", "");
            bizParaMap.put("PlatformNo", "12345");
            bizParaMap.put("DistribNumb", "12");
            bizParaMap.put("DCType", "01");
            bizParaMap.put("IssueDCNumb", "fp001");
            bizParaMap.put("IssueDCMoney", "1000.00");
            bizParaMap.put("Currency", "001");
            bizParaMap.put("ConfirmType", "0");
            bizParaMap.put("PayerReceivedMoney", "100.00");
            bizParaMap.put("PayerDistribMoney", "900.00");
            bizParaMap.put("PayerEntNumb", "1243234532");
            bizParaMap.put("PayerEntName", "XX高速公路有限公司");
            bizParaMap.put("PayerCertiType", "SZ");
            bizParaMap.put("PayerCertiNumb", "124578899");
            bizParaMap.put("PayerAccName", "�˻�����");
            bizParaMap.put("PayerAcc", "4654575687698");
            bizParaMap.put("PayerBankName", "XX高速公路有限公司");
            bizParaMap.put("AccBankCode", "1890711XXX");
            bizParaMap.put("PayDate", "20190101");
            bizParaMap.put("notifyUrl", "http://www.xxxx.com/notify.do");
            bizParaMap.put("DealFlag", "0");


            List<HashMap<String, Object>> DCInfoList = (List) new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> DCInfo1 = new HashMap<String, Object>();
            DCInfo1.put("SerialNo", "1");
            DCInfo1.put("DCNumb", "fp012");
            DCInfo1.put("DCTotalMoney", "1000.00");
            DCInfo1.put("DCReceivedMoney", "100.00");
            DCInfo1.put("DCDistribMoney", "343.00");
            DCInfo1.put("DCEntNumb", "234535");
            DCInfo1.put("DCEntName", "水城XXXX有限公司");
            DCInfo1.put("CertiType", "SZ");
            DCInfo1.put("CertiNumb", "235436456");
            DCInfo1.put("TaxNo", "76868");
            DCInfo1.put("AccountName", "34645677");
            DCInfo1.put("Account", "46576587658");
            DCInfo1.put("AccountFlag", "76876945645");
            DCInfo1.put("AccBankName", "长安支行");
            DCInfo1.put("AccBankCode", "0020000067");
            DCInfo1.put("Mobile", "15066979869");


            HashMap<String, Object> DCInfo2 = new HashMap<String, Object>();
            DCInfo2.put("SerialNo", "2");
            DCInfo2.put("DCNumb", "fp013");
            DCInfo2.put("DCTotalMoney", "1000.00");
            DCInfo2.put("DCReceivedMoney", "100.00");
            DCInfo2.put("DCDistribMoney", "343.00");
            DCInfo2.put("DCEntNumb", "234535");
            DCInfo2.put("DCEntName", "中交XXXX有限公司");
            DCInfo2.put("CertiType", "SZ");
            DCInfo2.put("CertiNumb", "235436456");
            DCInfo2.put("TaxNo", "76868");
            DCInfo2.put("AccountName", "34645677");
            DCInfo2.put("Account", "46576587658");
            DCInfo2.put("AccountFlag", "76876945645");
            DCInfo2.put("AccBankName", "长安支行");
            DCInfo2.put("AccBankCode", "0020000067");
            DCInfo2.put("Mobile", "15066979869");


            DCInfoList.add(DCInfo1);
            DCInfoList.add(DCInfo2);

            JSONArray jsonDCInfoList = (JSONArray) JSONArray.toJSON(DCInfoList);
            bizParaMap.put("DCInfoList", jsonDCInfoList);

            bizContent.setParam(bizParaMap);
            request.setBizContent(bizContent);
            MybankLoanDigitalcreditAcceptDistributionResponseV1 response;
            response = client.execute(request);
            if (response.isSuccess()) {
                String a = response.getReturnContent();
                System.out.println(a);

            } else {

            }

        } catch (IcbcApiException e1) {
            e1.printStackTrace();
        }
    }
}
