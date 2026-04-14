package com.icbc.api;

import com.icbc.api.request.MybankLoanPersonalloaninWhitelistinvalidRequestV1;
import com.icbc.api.response.MybankLoanPersonalloaninWhitelistinvalidResponseV1;


public class MybankLoanPersonalloaninWhitelistinvalidTestV1 {

    // appid,privateKey,apigwPublicKey 根据实际的应用场景进行修改
    protected static final String appId = "123";
    protected static final String privateKey = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
    protected static final String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
        MybankLoanPersonalloaninWhitelistinvalidRequestV1 request = new MybankLoanPersonalloaninWhitelistinvalidRequestV1();

        request.setServiceUrl("https://ip:port/api/mybank/loan/personalloanin/whitelistinvalid/V1");
        MybankLoanPersonalloaninWhitelistinvalidRequestV1.MybankLoanPersonalloaninWhitelistinvalidV1Biz param = new MybankLoanPersonalloaninWhitelistinvalidRequestV1.MybankLoanPersonalloaninWhitelistinvalidV1Biz();

        // 公共参数
        param.setSerialNo("12334455");
        param.setAppNo("F-APIP");
        param.setAreaCode("");
        param.setEmployeeCode("");
        param.setLanguage("zh_CN");
        param.setTransNo("mybank/loan/personalloanin/whitelistinvalid/V1");
        param.setVer("1.0");

        // 私有参数
        param.setCooperUnit("11");
        param.setAppId("111");
        param.setOutId("11");
        param.setModelType("1");
        param.setStatus("0|1|4");



        request.setBizContent(param);
        MybankLoanPersonalloaninWhitelistinvalidResponseV1 response = new MybankLoanPersonalloaninWhitelistinvalidResponseV1();
        try {
            response = client.execute(request);
        } catch (Exception e) {
        }
        if (response.isSuccess()) {
            System.out.println("return_code             :       " + response.getReturnCode());
            System.out.println("return_msg              :       " + response.getReturnMsg());
            System.out.println("msg_id                  :       " + response.getMsgId());
        } else {
        }
    }
}
