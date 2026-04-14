package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.request.MybankLoanInclusivefinanceOnlinesignRequestV1;
import com.icbc.api.response.MybankLoanInclusivefinanceOnlinesignResponseV1;

import java.util.ArrayList;
import java.util.List;

public class MybankLoanInclusivefinanceOnlinesignTestV1  {

    // appid,privateKey,apigwPublicKey 根据实际的应用场景进行修改
    protected static final String appId = "123";
    protected static final String privateKey = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
    protected static final String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
        MybankLoanInclusivefinanceOnlinesignRequestV1 request = new MybankLoanInclusivefinanceOnlinesignRequestV1();

        request.setServiceUrl("https://ip:port/api/mybank/loan/inclusivefinance/onlinesign/V1");
        MybankLoanInclusivefinanceOnlinesignRequestV1.MybankLoanInclusivefinanceOnlinesignV1Biz param = new MybankLoanInclusivefinanceOnlinesignRequestV1.MybankLoanInclusivefinanceOnlinesignV1Biz();
        MybankLoanInclusivefinanceOnlinesignRequestV1.ApplyInfo olApplyInfoDto =new MybankLoanInclusivefinanceOnlinesignRequestV1.ApplyInfo();
        olApplyInfoDto.setApproveResult("11");
        olApplyInfoDto.setApplyDate("20220122");
        olApplyInfoDto.setApplyStage("1");
        olApplyInfoDto.setApplyTime("111");
        olApplyInfoDto.setBusiClassify("1");
        olApplyInfoDto.setCino("111");
        olApplyInfoDto.setCardNo("1");
        olApplyInfoDto.setCardType("11");
        olApplyInfoDto.setCustName("11");
        olApplyInfoDto.setCnlType("1");
        olApplyInfoDto.setCreateEmp("1");
        olApplyInfoDto.setSealType("1");
        JSONArray custPositionArray=JSONArray.parseArray("[{\"sealPositionType\":\"1\",\"keyWord\":\"签章3||签章4\",\"keyWordOffset\":\"C\",\"keyWordPostitionIndex\":\"1-2\",\"xcoordinate\":\"10\",\"ycoordinate\":\"20\"}]");
        olApplyInfoDto.setSealPositionSymbol(custPositionArray.toJSONString());
        olApplyInfoDto.setSealAreaCode("11");
        olApplyInfoDto.setPhoneNo("11");
        olApplyInfoDto.setMaterialSource("11");
        olApplyInfoDto.setMaterialData("q");
        olApplyInfoDto.setLoanProtocolCode("11");
        olApplyInfoDto.setIsPush("1");
        olApplyInfoDto.setIsNeedCustomerConfirm("11");
        olApplyInfoDto.setEapBatchId("11");
        olApplyInfoDto.setDataArea("11");
        MybankLoanInclusivefinanceOnlinesignRequestV1.MaterailInfo materailInfo= new MybankLoanInclusivefinanceOnlinesignRequestV1.MaterailInfo();
        materailInfo.setMaterialAreaCode("11");
        materailInfo.setMaterialTypeCode("11");


        List custList =new ArrayList<>();
        MybankLoanInclusivefinanceOnlinesignRequestV1.CustInf custInfoDto=new MybankLoanInclusivefinanceOnlinesignRequestV1.CustInf();
        custInfoDto.setCardNo("1");
        custInfoDto.setCardType("11");
        custInfoDto.setCino("1");
        custInfoDto.setRole("1");
        custList.add(custInfoDto);

        // 公共参数
        param.setSerialNo("12334455");
        param.setAppNo("F-APIP");
        param.setAreaCode("");
        param.setEmployeeCode("");
        param.setLanguage("zh_CN");
        param.setTransNo("mybank/loan/inclusivefinance/onlinesign/V1");
        param.setVer("1.0");
        param.setTurnPageFlag("");
        param.setBeginRow("");
        param.setRowCount("");
        // 私有参数
        param.setApplyInfo(olApplyInfoDto);
        param.setMaterailInfo(materailInfo);
        param.setCustInfList(custList);

        request.setBizContent(param);
        MybankLoanInclusivefinanceOnlinesignResponseV1 response = new MybankLoanInclusivefinanceOnlinesignResponseV1();
        try {
            response = client.execute(request);
        } catch (Exception e) {
         }
        if (response.isSuccess()) {
         } else {
         }
    }
}
