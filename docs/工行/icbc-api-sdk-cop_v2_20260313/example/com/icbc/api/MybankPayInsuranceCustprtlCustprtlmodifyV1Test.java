package com.icbc.api;

import com.icbc.api.request.MybankPayInsuranceCustprtlCustprtlmodifyRequestV1;
import com.icbc.api.response.MybankPayInsuranceCustprtlCustprtlmodifyResponseV1;
import java.util.logging.Logger;

public class MybankPayInsuranceCustprtlCustprtlmodifyV1Test {
    static Logger log = Logger.getLogger(MybankPayInsuranceCustprtlCustprtlmodifyV1Test.class.getName());
    //估值表
    protected static String APP_ID = "xxxxxxxxxxxxxxxxxx";
    protected static String BASE_URL = "http://ip:port/api"; // 行外

    //网关公钥
    protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //应用私钥（与内管平台注册app时的“签名公钥”配对）
    protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCO9n8V4mpho7TRjkHzSPMQLm8axVgHXIQ+nT56DsntQ/LOTZyN0hVTb9JUVJaX7BF0QsDNFQyD9/S/ZWXQLCAfKVmmaKU1ftdYFPIJVHQcDzjhrVHh66nf028Yy+vbNTmNyY4EJqBy/caNuLsHM6ZQhhPgVFgRIWKmmMeGAMixBVN0QANwIZJS6bw3uMm90xON5ik58a80augElrFEgl1AO0nbTVjwPO8Fhg6RNjQyWTYCOWj3oi12jQqkDngBIP5KhwwtwzH62x1GFoqGP4hJxNqyhU8EDRhTEbB2qGNvoCryqGMi/3T2TH3gHkm8AVezCqeia4REdYIQgkRbf4z7AgMBAAECggEARunGSBizJBOMEZY66G57Ar/FpMXW0G/3uQdOgkTrrKFTwzmEOmTG50hEUEhVGq6iScUC1bdnKd7IrFejlQsOM+bS7VRi/pIYRzy8ghw+K9zxHiBWyXWYIcpDBU3BhE52afpcU3rFg8SVxJ6pRv07th2Lpm6v8G9a+8A02CdzZTq73BSw/uyAQL8n1iqN8Tllv7tGs+L7BUChm7+T+QoalbPkFz6G7idGZRbiS4elnkltZRqdRFuJOk1EQJcWAteU40WVQWhJQbX0gmx6Sy37Z5FHYf74SCLGW70zpQAGyOzghpOc0v0x97K6fxsQNWCCOSZ54F7Ptns256HePpHCIQKBgQDYXRXpSYWg31od/fFACA2XTJX1FALtpepI/ZRIOuV5jaEnD771oxG40dlyAO+AK4fth8ggi6eC1QL7nsvigJRpjeQhkvzHeyVDRbY2pqZQFMvWHwuEMhzdcY72BVQXD3ui03bfHukttreb50sKIPz440lXcJPr0sgSmyYHavouvwKBgQCpJxleATT58lJIePMR6uKxo32TcSw4l9RDM7rgSpzEMwICMxNtL+NTFZz+EYFQyV5ftI9QrtDSdsY0FNOGtqdLij+f3PnNjOo//ssjZdFM9AO0Ygiode873n14//6/ByBk0JGIP3rhr37QwjEnuaEeAMuG4z6RcN3vMyQvU/9sxQKBgC3PZ7hBLN3m7Hm1LsNT8Sy7tnRxjD0tB/H0XtT8Q2H9LsXKe7Aemr37EIMm147XqKwON71MVGdLGpqUAK8wcjv3IvpI2OSTmkwoEnB30HbcMFxhrlqLEbmBNbe5YRiQ2qibBWwwa0aWjUGhMy4t1eIMnZa561ffBSWLW/3uFJKZAoGAPOWQomE420s8CE9S4v53ApDjYrN5wh4XEuKKwrjZ22CptnUA52w4IO9FB8rxjU4Rpc7xUZC+KScNkC53QKZ9cBqjcUAyxqeWpddq9qFvSo8GzmgEyfE7YYhmD6vJ4KgGJ4VW/4ArExkFRM7Et5KCyLRskXE6U04Y+EanHkrVSCkCgYBVXHAdvSEeNj/bC2m9OYghgmzUyZMVVqKLOIP+7s59nHpjK18WYGHtMGqgv+NeFFRnNAs+tW1Triq3P+OPLsJUk5Mjs2NN92hV+OOKXqpkgYqhhKSZQ8ll05imGd/snrjZ4JrKJSJvZUO6SJHKbnb5c2FwV1ztfAF8442JxRooOg==";
    public static void main(String[] args) throws Exception {
        String URI = "mybank/pay/insurance/custprtl/custprtlmodify/V1"; // api地址

        //DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", PRI_KEY, APIGW_PUBLIC_KEY);
        DefaultIcbcClient client =  new DefaultIcbcClient(APP_ID, "RSA2", PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", "EZNm07FGm6wtbdP7kKWfjA==", (String)null, (String)null);
        try {

            // 请求实体
            MybankPayInsuranceCustprtlCustprtlmodifyRequestV1 request = new MybankPayInsuranceCustprtlCustprtlmodifyRequestV1();
            // bizContent实体
            MybankPayInsuranceCustprtlCustprtlmodifyRequestV1.ApiInvestmentInsuranceCustprtlProtocolModifyRequestV1Biz bizContent
                    = new MybankPayInsuranceCustprtlCustprtlmodifyRequestV1.ApiInvestmentInsuranceCustprtlProtocolModifyRequestV1Biz();
            MybankPayInsuranceCustprtlCustprtlmodifyRequestV1.CommonReqInfo commonReqInfoContent
                    = new MybankPayInsuranceCustprtlCustprtlmodifyRequestV1.CommonReqInfo();
            MybankPayInsuranceCustprtlCustprtlmodifyRequestV1.TransReqInfo transReqInfoContent
                    = new MybankPayInsuranceCustprtlCustprtlmodifyRequestV1.TransReqInfo();
            MybankPayInsuranceCustprtlCustprtlmodifyRequestV1.BizContentInfo bizContentInfo =
                    new MybankPayInsuranceCustprtlCustprtlmodifyRequestV1.BizContentInfo();


            commonReqInfoContent.setChanNo("15");
            commonReqInfoContent.setInsuId("028");
            commonReqInfoContent.setTransDate("20190723");
            commonReqInfoContent.setTransTime("191616");
            commonReqInfoContent.setZoneNo("01202");
            commonReqInfoContent.setBrNo("00230");

            transReqInfoContent.setCustName("张三");
            transReqInfoContent.setCustIdType("0");
            transReqInfoContent.setCustIdNo("xxxxxxxxxxx");
            transReqInfoContent.setPrtlNo("xxxxxxxxxxxxxxxxxx");
            transReqInfoContent.setCustAccno("xxxxxxxxxxxx");
            transReqInfoContent.setLmtAmt("xxxxxxxxxxxxx");
            transReqInfoContent.setPrtlEndDate("2019-08-01");

            bizContentInfo.setCommonReqInfo(commonReqInfoContent);
            bizContentInfo.setTransReqInfo(transReqInfoContent);

            bizContent.setBizContentInfo(bizContentInfo);


            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);

            MybankPayInsuranceCustprtlCustprtlmodifyResponseV1 response = client.execute(request);

            log.info(response.toString());
            boolean flag=response.isSuccess();
            if (flag) {
                // 业务成功处理
                log.info("success");//
            } else {
                // 失败
                log.info("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
