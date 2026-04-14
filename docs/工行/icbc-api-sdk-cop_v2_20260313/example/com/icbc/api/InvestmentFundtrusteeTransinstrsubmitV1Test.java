package com.icbc.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.InvestmentFundtrusteeTransinstrsubmitRequestV1;
import com.icbc.api.response.InvestmentFundtrusteeTransinstrsubmitResponseV1;

/**
 * Created by Administrator on 2018/10/16.
 */
public class InvestmentFundtrusteeTransinstrsubmitV1Test {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDWmOlJokJT6xlOU7oo5B3y6MOn1qQhpJQFTU6KlxK8TTAFJKPhUk3To5Jv49FL5qf6pR5rfjdTa3YnO9SXzj2ElZyQL19DQ1QsXDDi81sqJvejzb+5g4HG69A+jaYY2FrxDzCKqbcYxMq8M0+u/7S0xGeKXEcAOZ3SzvJHc3SHiRChqbxvZHguNjV/Cr4tYyOcMeWvQh7J6BDnYqMhfVtXEl3X49IS/hYPdc95CBSfqGYlG2O5fAXKxYQcEVb8VYF+JDNqBMmm0zUG4bo4LvPpwPhMjK6ssLRTEcPPDK6bNVfI/nMI6J203ABaAnZvEFLB0aNrYkfsn2HEYeJdixV5AgMBAAECggEBAKle5HkOTIVJP57XbE1LxetrFMlKGh7Hc2A/5KZzmADwdt8ITSPQPsa0CiavSwg50wh6++hhJK99WDKdASyTRXB4WKpgCTLgV/XvCVKGOcSqHre08wYrKeljQuesCpT/Zb9ueqRmULGG2G/zm4eGIAywGvxT2mHuF4dDeGs2T5mL/JgcQTBN9TmepavsA2M6z9Zkj4xXyGkAHQ5fa1DuWSW2BhaDf2Gid3NGZDUTgxt9jr4xNDTSw8fqMAqOHh5lQHAe2vvPHsURk2oKYQ/1iGBRR8uo/U/pcfVVAZmixH6ePpR+wM4j8aTJOdeYvhsgpvF013uvKRAtqNeMWHKAMYECgYEA++EGAsbWuJPau1640P6I4wtwuRxGc5eQawCsxxSdVUni9gUyKF+O+NuuzARYXWLe5WNpTXtdWU6ExoMOvK5p1YwZhgPVcn8+V7e3+i0tCJU3SlsH93wIhPcTPL/sNRTBFl4vL0C000+dI59mormQL1UrJ3N0F5sAI+wthYlV1PECgYEA2hu8eFIR3HMEMMzfTOFqY4+Cpoi4vcHl0yyvfIm07hyXYqdBqCvCz3h5Sb9pXHundz7Sp+s0hY+jptAHu1aM3VjYma2VEcZrycvV8u31GBZIawekg8DD+IIGQ3qVSo4OtfVhzj9tEcFP6gsV6BxF3tjY0skfRtOL01h1YEEsKQkCgYBv2hKgQwpxNv9oO30ZGh9Rq+/n1B2ZEVJJoigX1Bi2GjSWieInOaA09OFfqgJPnitbdFT72m+uzAZ8XRC7SzymXyuqbqNn0AewTl5903fvGsxN0bTs+wCRg2LglF3hiJDiTPrpIfPq7qzad9vNIVhdHJYoU8XXAbcDPMtORJmAEQKBgQC4p7TX9Tuz9Rh+LGV9VHbbFbwaPDSUSx3FvNZRzNCGq2QrnMlElNAZELk3C7wrFXGBzolQqxxLW4HFD+kL3UnjeH3hFgnYA7JsJYvcYUQZAXyMSOsqtL/1nDerV5Qfi8e+m6ITmLVBy/VZg8snORCai+J+CtLVBvMhmom9VVAK6QKBgQDC6U36jF0SLhmk1Zk6vU6zDgTO8IF7UnkuAs0jzOLqeyclKVXGraf9YDGjF+WVdOwknFa5P+TpEHtxaXsf5apfUpbXGiCGh04okJX+UFvmWjpnsEmrVD0YX+d1TBE0PsiXw5AEa7OSUf9cq1RUWir/f6CScTyddFBRYhDdISqjug==";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000000014027";

    
    public void test_cop() throws IcbcApiException {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        InvestmentFundtrusteeTransinstrsubmitRequestV1 request = new InvestmentFundtrusteeTransinstrsubmitRequestV1();
        InvestmentFundtrusteeTransinstrsubmitRequestV1.InvestmentFundtrusteeTransinstrsubmitRequestV1Biz bizContent = new InvestmentFundtrusteeTransinstrsubmitRequestV1.InvestmentFundtrusteeTransinstrsubmitRequestV1Biz();
        InvestmentFundtrusteeTransinstrsubmitRequestV1.InvestmentFundtrusteeTransinstrsubmitRequestV1In in = new InvestmentFundtrusteeTransinstrsubmitRequestV1.InvestmentFundtrusteeTransinstrsubmitRequestV1In();
        InvestmentFundtrusteeTransinstrsubmitRequestV1.InvestmentFundtrusteeTransinstrsubmitRequestV1InRecord record = new InvestmentFundtrusteeTransinstrsubmitRequestV1.InvestmentFundtrusteeTransinstrsubmitRequestV1InRecord();
        List<InvestmentFundtrusteeTransinstrsubmitRequestV1.InvestmentFundtrusteeTransinstrsubmitRequestV1InRecord> list = new ArrayList<>();

        in.setFileType("1241");
        in.setFundId("040003");
        in.setReportType("01");
        in.setBeginDate("20120613");
        in.setEndDate("");
        in.setDeptCode("0002");
        in.setCertId("");
        in.setSerialNo("DZ2012061300002");

        // 娴嬭瘯闄勪欢
        /*StringBuilder sb = new StringBuilder();
        String encodeStr = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\administrator\\Desktop\\test.txt")));

            String s = null;
            while ((s = br.readLine()) != null) {
                sb.append(s + "\n");
            }
            br.close();
            encodeStr = Base64.encode(sb.toString().getBytes("GBK"));
            System.out.println((double)(encodeStr.length())/1024/1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        record.setTimestmp("20120613 15:17:36562");
        record.setCash(new BigDecimal("100.212345"));
        record.setOutDeptAccn("");
        record.setOutDeptAccnName("");
        record.setOutAcntBank("");
        record.setInDeptAccn("000000100332001");
        record.setInDeptAccnName("姹囨坊瀵岀悊璐�30澶╁�哄埜鍨嬭瘉鍒告姇璧勫熀閲�");
        record.setInAcntBank("涓ぎ缁撶畻鍏徃");
        record.setTradeDate("20120613");
        record.setRemark("2012-06-13鏃VP浜ゆ槗鍒掓缂栧彿锛欳R20120613001257");
        record.setVoucherDesc("2012-06-13鏃VP浜ゆ槗鍒掓缂栧彿锛欳R20120613001257");
        record.setOperationType("1002204001");
        record.setSeqNo("ZLS47003020120613170");
        record.setIncorCode("闄堥洩");
        record.setInAcntBankPsn("");
        record.setInAcntBankCode("");
        record.setTradeNo("");
        record.setOutAcntCur("001");
        record.setInAcntCur("001");
        record.setFileClob(/*encodeStr*/"");
        record.setFileName("");
        record.setRemark1("");
        record.setRemark2("");
        record.setRemark3("");
        record.setRemark4("");
        record.setRemark5("");
        record.setNum1(new BigDecimal("0.123456"));
        record.setNum2(new BigDecimal("0.123456"));
        record.setNum3(new BigDecimal("0.123456"));
        record.setNum4(new BigDecimal("0.123456"));
        record.setNum5(new BigDecimal("0.123456"));

        list.add(record);
        in.setRecordList(list);

        bizContent.setIn(in);
        request.setBizContent(bizContent);

        request.setServiceUrl("http://ip:port/api/investment/fundtrustee/transinstrsubmit/V3");
        InvestmentFundtrusteeTransinstrsubmitResponseV1 response = client.execute(request);

        System.out.println(JSONObject.toJSONString(response));
        if (response.isSuccess()) {
            // 涓氬姟鎴愬姛澶勭悊
            System.out.println("success.");
        } else {
            // 澶辫触
            System.out.println("error." + response.getReturnCode() + response.getReturnMsg());
        }

    }

}
