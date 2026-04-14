package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.SaesServmerOrderrefundRequestV1;
import com.icbc.api.response.SaesServmerOrderrefundResponseV1;

import java.util.ArrayList;
import java.util.List;


/**
 * @author kfzx-yangk01
 * @create 2019-09-19-14:55
 * @desc
 */
public class SaesServmerOrderrefundTestV1 {

    public static void main(String[] args) {
        //1、网关公钥`
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        //2、appid
        String APP_ID = "1111111";
        //3、密钥对认证方式，公钥在API平台登记，此处为私钥
        String PRI_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTBL1u89Ka0tsMNEqbYPbJjxTn8Xkr8tIHwJZ9odHWkcfTmnOU877cYl+UhdhkfcFEKuvEhX2isPGdaIrqzkm9RBh+K1PQwyViGAhkFiUXDNcp/MdPzGyEjhwd+adKwR3CJfZZoWhZITT0O7fq71ufcSLvCrc6jzOPIDzxLheVall79ZHgVOCDjpTI8JUsj/0kAmHzhRIJ3lUGWyv4mTipwyAT9lzmvXhMcMnzStJ7Jzn1le7VXAd3ERvEz/PKEaXtPJ3kd1Grt0Q2EAns6ozvUg7spEjSBL7K8G77nwuOGWvVqfYQ2o36f5UTczpDaq47zrFVq6tFOIh21LBiYEThAgMBAAECggEAMKeb7bFBnL11gtd3bW6snc39dDllCZdlzOz+oPtBz8oVIMF3yO7V1me0DBozoCZCJMYzmqk3B/WZMKfdeZHls35qwK6kIW3D3G/prv3pLnSwbJPcfls/+qpxQbZSmBnLOGqJ2eXh+8VSmhV9a4Hlh6HQlt2r//ROcuqPYJ4JbgfvW/XIOCzakodAFeGLR2MXa5w+M7/Wc4Ipy1R5Cn10ZElJdAq8Hn8maAzQnQMNfkElZwX+7P8kyv/ueeeTeR+4HHhP5Xd0XLAJ4WVgw/jvzpRyBFdy+Kalvc0G0utIR8+9yUXlQ9yojJFVTzVukq+RHgPQg9PFLtYW9BKMkXgbsQKBgQDhCskBxEgr6DsdjoqpVYHSq8IyTD46+ton1lR6yE6cfuabPqdqVhV1rcANPNI7sCHjdQJUOABJiOIXn5v3soTx2C3dnWVUAck33e5t0fXwlpN5d2lEok6ZYhk1n29S/fDv5FLP860hYIxWD1Hggcd/AqhTPqXAVDgIpAThi9id/QKBgQCnPjrOqsj7Sq0+3b9LF8ScSquxK5QaAJ712SnBT/EbjQJK0QiNHRl/03jFMIUwXHDVd6wYo0JYCBJFDZuZyVndPONz2rlx9Y9nP112RZedo4/lIdXV4ZQl8nsUrSS3zn2dHA9d/kcLTgV+0WQr1CSrCDnxXOgXRGNyu1dNJN8ltQKBgAcygRs9W9GLq9hWJrkjsvM56wwQxCy+uRttD2ad4WW/6UAt0Mzl0+nflLTXrSGbkHnl1B09bUTsy85VnHdfjkDt2QmtbwAwzm6q+mUoweTf6/eGiidXyYu+R3AfmT/eP0Zs1vkvoenfXJ2j/8peXDMDsr520nZEO0aLeFx+TH9BAoGBAIgEp6Vv5zfhe1d2197F/OS0IkFc1P305VPEBB6x2sFyhJu+uHq0YQnCPyX5GUnE1Qv4YaJEHXAlWF6K+7MuvISfwcl26X4Snkf1lBjd7rqJzT0osJZb+baHKK0fSI6pRSZjLOhgJv29veBvgk3XIt9rOFziF+zcdRlZ3l3ikA5FAoGAfI7mPDQby6/BPFMBG3A7UuL29X7Gb4RfOpqS49YsNuCDA0YYirHyOhn49Ai3s9YuegWLOSlWdm2Goz03YuNW7fbVI+WrNc4EeIyRAkd81PgF9gxTNKq+LVMzaD7bGf8an8F95fvgZi4xNuLHukjGW7Exr9ND8W4y8fv8Grw8RkA=";
        //4、接口消费方，创建与F-APIP外网网关的连接，默认RSA。
        String apiGatewayUrl = "http://ip:port/api/saes/servmer/orderrefund/V1";
        //使用RSA2方式
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);
        //设置请求对象
        SaesServmerOrderrefundRequestV1 request = new SaesServmerOrderrefundRequestV1();
        //设置请求路径
        request.setServiceUrl(apiGatewayUrl);
        //设置业务参数
        SaesServmerOrderrefundRequestV1.SaesServiceRequestV1Biz biz = new SaesServmerOrderrefundRequestV1.SaesServiceRequestV1Biz();

        SaesServmerOrderrefundRequestV1.OrderSubmitActInput orderSubmitActInput = new SaesServmerOrderrefundRequestV1.OrderSubmitActInput();
        orderSubmitActInput.setRecNum("xx");
        orderSubmitActInput.setSubOrderNo("xx");
        orderSubmitActInput.setSeqNo("xx");
        orderSubmitActInput.setBusiType("xx");
        orderSubmitActInput.setOriTrxDate("xx");
        orderSubmitActInput.setOriShopCode("xx");
        orderSubmitActInput.setShopCode("xx");
        orderSubmitActInput.setClassifyAmt("xx");
        orderSubmitActInput.setTermId("xx");
        orderSubmitActInput.setRcnclElmnt1("xx");
        orderSubmitActInput.setRcnclElmnt2("xx");
        orderSubmitActInput.setRcnclElmnt3("xx");
        orderSubmitActInput.setRcnclElmnt4("xx");
        orderSubmitActInput.setRcnclElmnt5("xx");
        orderSubmitActInput.setRcnclElmnt6("xx");
        orderSubmitActInput.setRcnclElmnt7("xx");
        orderSubmitActInput.setAmount1("xx");
        orderSubmitActInput.setAmount2("xx");
        orderSubmitActInput.setAmount3("xx");
        orderSubmitActInput.setMerSpInfor("xx");
        orderSubmitActInput.setRetSubOrderNo("xx");

        List<SaesServmerOrderrefundRequestV1.OrderSubmitActInput> orderSubmitActInputs = new ArrayList<>();
        orderSubmitActInputs.add(orderSubmitActInput);

        biz.setMerType("1");
        biz.setMerNo("xxxx");
        biz.setPayDate("20180101");
        biz.setOrderId("12345");
        biz.setRejectId("12345");
        biz.setRejectReason("误拍");
        biz.setRejectAmt("xxxx");
        biz.setOrderIdType("0");
        biz.setSubOrderCount("1");
        biz.setOrderSubmitActInput(orderSubmitActInputs);
        biz.setChannel("1");
        biz.setSerMerNo("sadewq");

        request.setBizContent(biz);

        SaesServmerOrderrefundResponseV1 response;
        try {
            //测试时，避免apip识别成重放，请修改msgId,每次手工触发测试执行时，递加1
            response = client.execute(request, "ur811");
            response.isSuccess();
            System.out.println("response:" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            System.out.println(e);
        }
    }
}
