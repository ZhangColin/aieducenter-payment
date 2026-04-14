package com.icbc.api;


import com.icbc.api.request.MybankLoanPersonalloaninAddcreditapplyRequestV1;
import com.icbc.api.request.MybankLoanPersonalloaninAddcreditapplyRequestV1.MybankLoanPersonalloaninAddcreditapplyBizContent;
import com.icbc.api.response.MybankLoanPersonalloaninAddcreditapplyResponseV1;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;

public class MybankLoanPersonalloaninAddcreditapplyTestV1 {
    protected static final String appId="10000000000000871208";
    protected static final String privateKey="-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr56i1uTspdZi0zqaerI2EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
    protected static final String apigwPublicKey="MIGfMA1GCSqGSIb4DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj04ctKM7KV+16bWZ6BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    public static void main(String[] args) throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
        MybankLoanPersonalloaninAddcreditapplyRequestV1 request = new MybankLoanPersonalloaninAddcreditapplyRequestV1();
        request.setServiceUrl("http://ip:port/api/mybank/loan/personalloanin/addcreditapply/V1");
        MybankLoanPersonalloaninAddcreditapplyBizContent bizContent = new MybankLoanPersonalloaninAddcreditapplyBizContent();

        bizContent.setApplyEmployee("888800135");
        bizContent.setUnitNo("8388383");
        bizContent.setHousePurcDuration("8");
        bizContent.setAppNo("F-GCMS");
        bizContent.setCino("020078900071312");
        bizContent.setSettleZone("710000");
        bizContent.setSettleAddress("taiwan");
        bizContent.setLanguage("zh_CN");
        bizContent.setVerifIncome("300000");
        bizContent.setProvidFundContri("8484");
        bizContent.setEntpayflag("");
        bizContent.setCellphohoneNo("13501010102");
        bizContent.setHouseTotalPrice("83830000");
        bizContent.setOtherBasis("");
        bizContent.setPersonHier("03");
        bizContent.setEmployeeCode("888800135");
        bizContent.setZoneno("0200");
        bizContent.setDataSources("02");
        bizContent.setRejActNo("460000000666");
        bizContent.setFinancAssetBalance("84848");
        bizContent.setApplyArea("0020000072");
        bizContent.setCardType("0");
        bizContent.setInterviewFlag("1");
        bizContent.setTransNo("PerLoanCreditLimitService");
        bizContent.setValidPeriod("34");
        bizContent.setPlanCode("916");
        bizContent.setApplyLimit("900000");
        bizContent.setSerialNo("PA1822354712777");
        bizContent.setCusCateCode("83838");
        bizContent.setAreaCode("0020000072");
        bizContent.setVerifBasis("01,03,04,02,05");
        bizContent.setCusGroupCode("06");
        bizContent.setCompanyAddress("address");
        bizContent.setProdKindCode("030100000661");
        bizContent.setDealArea("0020000072");
        bizContent.setCompanyZone("820000");
        bizContent.setApplyStatus("01");
        bizContent.setBrno("0059");

        System.out.println("-------个贷融e借申请创建入参--------"+bizContent);


        request.setBizContent(bizContent);
        System.out.println("-------bizContent--------"+bizContent);
        MybankLoanPersonalloaninAddcreditapplyResponseV1 response=new MybankLoanPersonalloaninAddcreditapplyResponseV1();
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
