package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.AsmsCheckencryptcodeRequestV1;
import com.icbc.api.response.AsmsCheckencryptcodeResponseV1;

public class AsmsCheckencryptcodeV1Test {
    public static void main(String[] args) {
        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTBL1u89Ka0tsMNEqbYPbJjxTn8Xkr8tIHwJZ9odHWkcfTmnOU877cYl+UhdhkfcFEKuvEhX2isPGdaIrqzkm9RBh+K1PQwyViGAhkFiUXDNcp/MdPzGyEjhwd+adKwR3CJfZZoWhZITT0O7fq71ufcSLvCrc6jzOPIDzxLheVall79ZHgVOCDjpTI8JUsj/0kAmHzhRIJ3lUGWyv4mTipwyAT9lzmvXhMcMnzStJ7Jzn1le7VXAd3ERvEz/PKEaXtPJ3kd1Grt0Q2EAns6ozvUg7spEjSBL7K8G77nwuOGWvVqfYQ2o36f5UTczpDaq47zrFVq6tFOIh21LBiYEThAgMBAAECggEAMKeb7bFBnL11gtd3bW6snc39dDllCZdlzOz+oPtBz8oVIMF3yO7V1me0DBozoCZCJMYzmqk3B/WZMKfdeZHls35qwK6kIW3D3G/prv3pLnSwbJPcfls/+qpxQbZSmBnLOGqJ2eXh+8VSmhV9a4Hlh6HQlt2r//ROcuqPYJ4JbgfvW/XIOCzakodAFeGLR2MXa5w+M7/Wc4Ipy1R5Cn10ZElJdAq8Hn8maAzQnQMNfkElZwX+7P8kyv/ueeeTeR+4HHhP5Xd0XLAJ4WVgw/jvzpRyBFdy+Kalvc0G0utIR8+9yUXlQ9yojJFVTzVukq+RHgPQg9PFLtYW9BKMkXgbsQKBgQDhCskBxEgr6DsdjoqpVYHSq8IyTD46+ton1lR6yE6cfuabPqdqVhV1rcANPNI7sCHjdQJUOABJiOIXn5v3soTx2C3dnWVUAck33e5t0fXwlpN5d2lEok6ZYhk1n29S/fDv5FLP860hYIxWD1Hggcd/AqhTPqXAVDgIpAThi9id/QKBgQCnPjrOqsj7Sq0+3b9LF8ScSquxK5QaAJ712SnBT/EbjQJK0QiNHRl/03jFMIUwXHDVd6wYo0JYCBJFDZuZyVndPONz2rlx9Y9nP112RZedo4/lIdXV4ZQl8nsUrSS3zn2dHA9d/kcLTgV+0WQr1CSrCDnxXOgXRGNyu1dNJN8ltQKBgAcygRs9W9GLq9hWJrkjsvM56wwQxCy+uRttD2ad4WW/6UAt0Mzl0+nflLTXrSGbkHnl1B09bUTsy85VnHdfjkDt2QmtbwAwzm6q+mUoweTf6/eGiidXyYu+R3AfmT/eP0Zs1vkvoenfXJ2j/8peXDMDsr520nZEO0aLeFx+TH9BAoGBAIgEp6Vv5zfhe1d2197F/OS0IkFc1P305VPEBB6x2sFyhJu+uHq0YQnCPyX5GUnE1Qv4YaJEHXAlWF6K+7MuvISfwcl26X4Snkf1lBjd7rqJzT0osJZb+baHKK0fSI6pRSZjLOhgJv29veBvgk3XIt9rOFziF+zcdRlZ3l3ikA5FAoGAfI7mPDQby6/BPFMBG3A7UuL29X7Gb4RfOpqS49YsNuCDA0YYirHyOhn49Ai3s9YuegWLOSlWdm2Goz03YuNW7fbVI+WrNc4EeIyRAkd81PgF9gxTNKq+LVMzaD7bGf8an8F95fvgZi4xNuLHukjGW7Exr9ND8W4y8fv8Grw8RkA=";
        //2、appid
        String APP_ID = "10000000000004097687";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        //5、设置请求对象
        AsmsCheckencryptcodeRequestV1 request = new AsmsCheckencryptcodeRequestV1();
        //6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
        request.setServiceUrl("http://ip:port/api/asms/checkencryptcode/generate/V1");
        //7、设置业务参数
        AsmsCheckencryptcodeRequestV1.AsmsCheckencryptcodeGenerateRequestV1Biz bizContent = new AsmsCheckencryptcodeRequestV1.AsmsCheckencryptcodeGenerateRequestV1Biz();

        //8、组装请求参数
        bizContent.setShowFmtData("PENPU1A%2BPGlucHV0ZGF0YT48TUVUSE9EQ09ERT4yMDA8L01FVEhPRENPREU%2BPENBTExGTEFHPjE8L0NBTExGTEFHPjxFTkNSWVBUQ09ERT42QTIxMDMzQTgwMzk8L0VOQ1JZUFRDT0RFPjxNQUlOX1BST1BFUlRZPjwvTUFJTl9QUk9QRVJUWT48Q0hLX1BST1BFUlRZPjwvQ0hLX1BST1BFUlRZPjxDSEtfUFJPUEVSVFlfTk8%2BPC9DSEtfUFJPUEVSVFlfTk8%2BPEVOQ1JZU1JDPjI8L0VOQ1JZU1JDPjwvaW5wdXRkYXRhPjwvQ09TUD4=");

        request.setBizContent(bizContent);
        AsmsCheckencryptcodeResponseV1 response;
        try {
            System.out.println("request:"+ JSON.toJSONString(request));
            response = client.execute(request,"111");
            System.out.println("response:" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
