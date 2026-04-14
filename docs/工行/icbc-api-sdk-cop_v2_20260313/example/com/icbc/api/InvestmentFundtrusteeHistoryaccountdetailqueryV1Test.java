package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1;
import com.icbc.api.response.InvestmentFundtrusteeHistoryaccountdetailqueryResponseV1;

/**
 * Created by Administrator on 2018/10/16.
 */
public class InvestmentFundtrusteeHistoryaccountdetailqueryV1Test {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDWmOlJokJT6xlOU7oo5B3y6MOn1qQhpJQFTU6KlxK8TTAFJKPhUk3To5Jv49FL5qf6pR5rfjdTa3YnO9SXzj2ElZyQL19DQ1QsXDDi81sqJvejzb+5g4HG69A+jaYY2FrxDzCKqbcYxMq8M0+u/7S0xGeKXEcAOZ3SzvJHc3SHiRChqbxvZHguNjV/Cr4tYyOcMeWvQh7J6BDnYqMhfVtXEl3X49IS/hYPdc95CBSfqGYlG2O5fAXKxYQcEVb8VYF+JDNqBMmm0zUG4bo4LvPpwPhMjK6ssLRTEcPPDK6bNVfI/nMI6J203ABaAnZvEFLB0aNrYkfsn2HEYeJdixV5AgMBAAECggEBAKle5HkOTIVJP57XbE1LxetrFMlKGh7Hc2A/5KZzmADwdt8ITSPQPsa0CiavSwg50wh6++hhJK99WDKdASyTRXB4WKpgCTLgV/XvCVKGOcSqHre08wYrKeljQuesCpT/Zb9ueqRmULGG2G/zm4eGIAywGvxT2mHuF4dDeGs2T5mL/JgcQTBN9TmepavsA2M6z9Zkj4xXyGkAHQ5fa1DuWSW2BhaDf2Gid3NGZDUTgxt9jr4xNDTSw8fqMAqOHh5lQHAe2vvPHsURk2oKYQ/1iGBRR8uo/U/pcfVVAZmixH6ePpR+wM4j8aTJOdeYvhsgpvF013uvKRAtqNeMWHKAMYECgYEA++EGAsbWuJPau1640P6I4wtwuRxGc5eQawCsxxSdVUni9gUyKF+O+NuuzARYXWLe5WNpTXtdWU6ExoMOvK5p1YwZhgPVcn8+V7e3+i0tCJU3SlsH93wIhPcTPL/sNRTBFl4vL0C000+dI59mormQL1UrJ3N0F5sAI+wthYlV1PECgYEA2hu8eFIR3HMEMMzfTOFqY4+Cpoi4vcHl0yyvfIm07hyXYqdBqCvCz3h5Sb9pXHundz7Sp+s0hY+jptAHu1aM3VjYma2VEcZrycvV8u31GBZIawekg8DD+IIGQ3qVSo4OtfVhzj9tEcFP6gsV6BxF3tjY0skfRtOL01h1YEEsKQkCgYBv2hKgQwpxNv9oO30ZGh9Rq+/n1B2ZEVJJoigX1Bi2GjSWieInOaA09OFfqgJPnitbdFT72m+uzAZ8XRC7SzymXyuqbqNn0AewTl5903fvGsxN0bTs+wCRg2LglF3hiJDiTPrpIfPq7qzad9vNIVhdHJYoU8XXAbcDPMtORJmAEQKBgQC4p7TX9Tuz9Rh+LGV9VHbbFbwaPDSUSx3FvNZRzNCGq2QrnMlElNAZELk3C7wrFXGBzolQqxxLW4HFD+kL3UnjeH3hFgnYA7JsJYvcYUQZAXyMSOsqtL/1nDerV5Qfi8e+m6ITmLVBy/VZg8snORCai+J+CtLVBvMhmom9VVAK6QKBgQDC6U36jF0SLhmk1Zk6vU6zDgTO8IF7UnkuAs0jzOLqeyclKVXGraf9YDGjF+WVdOwknFa5P+TpEHtxaXsf5apfUpbXGiCGh04okJX+UFvmWjpnsEmrVD0YX+d1TBE0PsiXw5AEa7OSUf9cq1RUWir/f6CScTyddFBRYhDdISqjug==";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000000014027";


    public void test_cop() throws IcbcApiException {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1 request = new InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1();
        InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1.InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1Biz bizContent = new InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1.InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1Biz();
        InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1.InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1In in = new InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1.InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1In();
        InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1.InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1InRecord record = new InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1.InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1InRecord();
        List<InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1.InvestmentFundtrusteeHistoryaccountdetailqueryRequestV1InRecord> list = new ArrayList<>();

        in.setFileType("1081");
        in.setFundId("202101");
        in.setReportType("01");
        in.setBeginDate("20170412");
        in.setEndDate("20170413");
        in.setDeptCode("0001");
        in.setCertId("");
        in.setSerialNo("20160502000001066382");

        record.setAccntCode("123456");
        record.setAccntName("test(RMB)");
        list.add(record);
        in.setRecordList(list);

        bizContent.setIn(in);
        request.setBizContent(bizContent);

        request.setServiceUrl("http://ip:port/api/investment/fundtrustee/historyaccountdetailquery/V3");
        InvestmentFundtrusteeHistoryaccountdetailqueryResponseV1 response = client.execute(request);

        System.out.println(JSONObject.toJSONString(response));
        if (response.isSuccess()) {
            // 业务成功处理
            System.out.println("success.");
        } else {
            // 失败
            System.out.println("error." + response.getReturnCode() + response.getReturnMsg());
        }

    }

}
