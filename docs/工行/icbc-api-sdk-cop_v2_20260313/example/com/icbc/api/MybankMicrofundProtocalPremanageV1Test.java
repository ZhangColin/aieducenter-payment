package com.icbc.api;

import com.icbc.api.request.MybankMicrofundProtocalPremanageRequestV1;
import com.icbc.api.response.MybankMicrofundProtocalPremanageResponseV1;

public class MybankMicrofundProtocalPremanageV1Test {
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
            + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
            + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
            + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    // 2、appid
    protected static final String APP_ID = "10000000000004096921";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY =
            "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTam1" + "+HtjomM8qgwItD3SMxgWjE0Uv9tdhYB5"
                    + "+nyqX54QX4kqXU2WjwpToh5dxdf4j2lgserPgvtTA" + "/3GVg3Gv5zt5KGnVpNEdpciXDuAATqRVkhIgm1fgm8S0"
                    + "/zZwNfbtVfGd417aYJms1c2e" + "+yQyf3E1KY3G8CFP4cN6Dvd2H+X5lyLsKp70rC"
                    + "/AH4JTlNcF6koO5ZrnfDUDlKfBlc9o24rFyif6k0kfkFfYFdNT16EI140" + "+eSY9pgiPRtO1WOHDQOFvPV1U4BzC7k"
                    + "+ptrGcAesLWstBITfADYmN8O7dY4kr5VhoVNIZieiH7/Cn" + "/mXry0oLK6Q6wON2me3uJbFnAgMBAAECggEAKvCraDtwD4DDh6yVq65"
                    + "+0qqz5aatVqVeY5zuBRA0Bf" + "+IOLTP6Xh/eM4rNfiZIpGuL59gY8RyNRshOf6uhVC+gvCmIEqnWXYi" + "+7HY1jedBDnkIJDl+t2FIYX"
                    + "+LILSxqjRfpy0PruGW7Ib0X6N4KtjBdqdjVDHXZWA/pXSrPFDIAMFCAV"
                    + "/9OLO8cBKJiUedg9Z7cIIj0TY5vDw9NQdcq7w1VyjDVn9dHieLKu4VedrmbLmMthD9ohsDB1" + "/RHxquEE+r3b5FJ+lMK718FefMHmSqp"
                    + "/4VadLrRceLn7iXcAJJIkOXH7Mv3OLML3mrz2UBczjCXZKcU7LrhzvK0Id5MppgQKBgQDUabp46" + "+YUdRseucevfJlPENnwRQCzdvYhuE"
                    + "+fVZ7tonlsZVLeEElG3DvE0jcftTv3UoYqoECZwi1MNeW7vu1rlNUox/WwJdIxjar"
                    + "+5aFNlfcs8OXojIiYuUfVaphpOWlg05OzjpW9wTJMPeptVV4sjDnJ3At5jvNjSixbO"
                    + "+HFIQKBgQCxqlOV7yxFWRb7UkEgHsGZbePTIhZbwxsJbV7pKlRwDX22Ep6R11N" + "+nJAg9gyZDWxF1jN4QFg3PXgZryDKA4IxUvRj7vYC1"
                    + "/CfZXcIByalz6srPYQFbr7StuMWUZr/Zau" + "/8II5x8y48AX6JRz9mt4dO"
                    + "+7j7ytdRNTOMPhhLIIdhwKBgHog5C88awheIJ608IQMntUf"
                    + "+j60XefRwUi4AUe6Yet93FSfmVrGgGs2m0LaHwmjGXxlPXDqvNnrFEJaRuGAsF"
                    + "+HrluJRNDnnggRzTxLaUgDrp9vTrE7hBpThWLGrCTr9pPOcoDtvVjh6Pp/Kvd7" + "+u6gHWXbl9lngLcY4ZH"
                    + "/RxshAoGBAJXlvUwdVV7VQdydk7EEAhfvbW7vramSPbeAfbmAK2m3GD"
                    + "+MChnGcP28HqaQsiRX/OgXR6nHsCY7SJ9y8NlAiJIRCX1fWlUf2DbEJt" + "+xGNyBEEwVRNRIkdu6znJAO"
                    + "+QL5hK1W0pg1suaR5C7y2/1QD2uGWpaml3A" + "+0NUcdua79T1AoGASV6DDFKOov0edVPlZs4sPez6eflgcnEknhjL5MI1/u9vhCmg"
                    + "/uHg8UV03t6UlRENx2QtDYEee5lbNrytC0G/QIG0pZwj" + "/8nGymE1u6oXYNN9zUGEiAnNjbWNn3Mwu2bYpVtBdaz9DBSQqh5wfit"
                    + "/uw6K9nsfo0w7Fan2wWLNDe4=";
    
    
    public static void main(String[] args) {
        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankMicrofundProtocalPremanageRequestV1 request = new MybankMicrofundProtocalPremanageRequestV1();
        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/mybank/microfund/protocal/premanage/V1");

        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MybankMicrofundProtocalPremanageRequestV1.MybankMicrofundProtocalPremanageRequestV1Biz bizContent = new MybankMicrofundProtocalPremanageRequestV1.MybankMicrofundProtocalPremanageRequestV1Biz();

        //1-查询
        bizContent.setOperType("1");
        bizContent.setIndustryid("0052235");
        bizContent.setMktacc("0200000209024213165");
        bizContent.setAgteffectdate("1");
        bizContent.setAgtenddate("1");
        bizContent.setBzjDepAccountOrg("1");
        bizContent.setBzjBelongSubject("1");
        bizContent.setContent("1");
        bizContent.setFloatmode("1");
        bizContent.setIntcycle("1");
        bizContent.setIntratemode("1");
        bizContent.setIsextend("1");
        bizContent.setMktname("1");
        bizContent.setOnlyCorefirmOpenacc("1");
        bizContent.setPaymod("1");
        bizContent.setPeriod("1");
        bizContent.setRatecode("1");
        bizContent.setSupportPolymerPay("1");
        bizContent.setTel("1");
        bizContent.setTmpct("1");
        request.setBizContent(bizContent);

        MybankMicrofundProtocalPremanageResponseV1 response;
        try {

            response = (MybankMicrofundProtocalPremanageResponseV1) client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
            } else {
                // 失败
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
