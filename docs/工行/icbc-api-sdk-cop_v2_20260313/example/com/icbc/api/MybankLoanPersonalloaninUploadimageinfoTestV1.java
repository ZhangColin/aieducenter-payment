package com.icbc.api;


import com.icbc.api.request.MybankLoanPersonalloaninUploadimageinfoRequestV1;
import com.icbc.api.request.MybankLoanPersonalloaninUploadimageinfoRequestV1.MybankLoanPersonalloaninUploadimageinfoBizContent;
import com.icbc.api.response.MybankLoanPersonalloaninUploadimageinfoResponseV1;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;

public class MybankLoanPersonalloaninUploadimageinfoTestV1 {
    protected static final String appId="10000000000000871208";
    protected static final String privateKey="-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr56i1uTspdZi0zqaerI2EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
    protected static final String apigwPublicKey="MIGfMA1GCSqGSIb4DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj04ctKM7KV+16bWZ6BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    public static void main(String[] args) throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
        MybankLoanPersonalloaninUploadimageinfoRequestV1 request = new MybankLoanPersonalloaninUploadimageinfoRequestV1();
        request.setServiceUrl("http://ip:port/api/mybank/loan/personalloanin/uploadimageinfo/V1");
        MybankLoanPersonalloaninUploadimageinfoBizContent bizContent = new MybankLoanPersonalloaninUploadimageinfoBizContent();

        //公共参数
        bizContent.setLanguage("zh_CN");

        //私有参数
        bizContent.setArea_code("0020000072");
        bizContent.setApplyno("0200200000000104460011");
        bizContent.setCino("02007890007131212");
        bizContent.setImage_no("1000");
        bizContent.setImage_kind_code("P066");
        bizContent.setAutoFlag("0");

        System.out.println("-------分行个人贷款申请影像资料上传服务入参--------"+bizContent);
        request.setBizContent(bizContent);
        System.out.println("-------bizContent--------"+bizContent);
        MybankLoanPersonalloaninUploadimageinfoResponseV1 response=new MybankLoanPersonalloaninUploadimageinfoResponseV1();
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
