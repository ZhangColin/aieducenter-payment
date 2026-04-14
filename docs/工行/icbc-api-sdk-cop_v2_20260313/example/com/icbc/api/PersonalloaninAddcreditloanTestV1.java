package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.PersonalloaninAddcreditloanRequestV1;
import com.icbc.api.request.PersonalloaninAddcreditloanRequestV1.PersonalloaninAddcreditloanBizContent;
import com.icbc.api.response.PersonalloaninAddcreditloanResponseV1;
import java.util.List;
import java.util.ArrayList;

public class PersonalloaninAddcreditloanTestV1 {
    protected static final String appId="10000000000000871208";
    protected static final String privateKey="-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr56i1uTspdZi0zqaerI2EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
    protected static final String apigwPublicKey="MIGfMA1GCSqGSIb4DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj04ctKM7KV+16bWZ6BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    public static void main(String[] args) throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
        PersonalloaninAddcreditloanRequestV1 request = new PersonalloaninAddcreditloanRequestV1();
        request.setServiceUrl("http://ip:port/api/personalloanin/addCreditLoan/V1");
        PersonalloaninAddcreditloanBizContent bizContent = new PersonalloaninAddcreditloanBizContent();

        PersonalloanObjectInfo param = new PersonalloaninAddcreditloanTestV1();
        param.setPre_approve_flag("0");

        PersonalloanLoanInfo loan_info = new PersonalloanLoanInfo();
        loan_info.setApply_no("12345")
        param.setLoan_info(loan_info);

        PersonalloanCisInfo cis_info = new PersonalloanCisInfo();
        cis_info.setCisname("xxx");
        param.setCis_info(cis_info);

        List<PersonalloanRelaCisInfo> relaCisInfoList = new ArrayList<>();
        PersonalloanRelaCisInfo rela_cis_info = new PersonalloanRelaCisInfo();
        rela_cis_info.setRel_cisname("YYyy");
        relaCisInfoList.add(rela_cis_info);
        param.setRela_cis_info(relaCisInfoList);

        PersonalloanSupplyInfo supply_info = new PersonalloanSupplyInfo();
        supply_info.setDown_payment("11");
        param.setSupply_info(supply_info);

        PersonalloanDywInfo dyw_info = new PersonalloanDywInfo();
        dyw_info.setPlg_dtlkind_code("001");
        param.setDyw_info(dyw_info);

        PersonalloanOtherInfo other_info = new PersonalloanOtherInfo();
        other_info.setDecorate("cc");
        param.setOther_info(other_info);

        bizContent.setParam(param);

        System.out.println("-------个贷信用贷款申请创建入参--------"+bizContent);

        request.setBizContent(bizContent);
        System.out.println("-------bizContent--------"+bizContent);
        PersonalloaninAddcreditloanResponseV1 response=new PersonalloaninAddcreditloanResponseV1();
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
