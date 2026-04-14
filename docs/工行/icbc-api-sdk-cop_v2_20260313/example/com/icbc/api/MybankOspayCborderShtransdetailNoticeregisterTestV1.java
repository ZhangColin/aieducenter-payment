package com.icbc.api;

import com.icbc.api.request.MybankOspayCborderShtransdetailNoticeregisterRequestV1;
import com.icbc.api.request.MybankOspayCborderShtransdetailNoticeregisterRequestV1.MybankOspayCborderShtransdetailNoticeregisterRequestV1Biz;
import com.icbc.api.response.MybankOspayCborderShtransdetailNoticeregisterResponseV1;


public class MybankOspayCborderShtransdetailNoticeregisterTestV1 {


    //应用私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfRzz0xHkghBQJEoIr1VhiIsF84Ik4sm5lvkOKOBOEJsD4MUAZtSpqT4oTIRF6I48xO/vDWvGEovBqlfNZ8LHYAL4qmq8KE5XyXG0hhRfBVI0h/PT096VEFzWT9QJE2i8usZDn2S+L72zqcZHM+0s3SnvffUiLgvQtAXW6JxrOVVC/PpDz+aw8g6hoJKQxuTR9bFRGtz3cPRJFj8O59EVpYBd3+7FDKAT5FKVTh0otSY7VNZVUlUJZT3r5c2TMj6A1JM+eZ5205cIWH1mvIDJEqK6V9EaR0oM2Eup08Y0MKIL2dHuYyhkmjGjLP6DxsSeDzoUcI8/NiXW+7R4Ys8Z7AgMBAAECggEAOC67rKcFBtgv7QdPTUDi/TK/R1o4bPxbfp3/Wuju0WWOGb3dm3PQ81D9JkQ60vdqEfbTlpQkryF+ZkpgXXBOex+/9G09ovNHqAa8I9+7gmFdqwh8K1CTRhJdvsPkuf6wN+tAbteoMtcQdx36TPCzFly6lxmtFjDqCloCedhDV1YoQNZ16VLZTcfzcHnUlwV3a/HlVH+vyLoMHNB7Pqq6V+lnZHkc/wc3Gcs1oE8Qo78ZuwyNqF+9IjhIi6MGNNkniQoIimyfY77zCl7q2gMnpIwRfu5nGjzB8gv+uD11nVvfqI/VveU/YlqI+f1dIDLbGWkpNmJEmWBZbuaPGOzUqQKBgQDLVDtoQvS9aW3Y9k8mlcAdNDOV4V88bgayjytiVkxEfkXJdc54c6vARLLjTruFv+np+zl7TwmqYqmIuXSAWSJtDGFpqqModtnFDR6FmNV1ExJ50hrKjV5E7n3/5oKE1UXJP51/jFd7zrlSs8epO8vJ3d8oJj6ccFbbXEJqQW8FZwKBgQDIiceEQSmUy9QuybcMFx3L9JcA4WJ01xjaI2B4eiXnqY7ViOMyin3OZl9Zo7whmIKWqfbd6YjUneVQnQAwljTRPWkBSJfdWI3yA0zwNHrZR+pTEynToOvuHcnojKNCWChs90Nw4iUPQXStCXTEE6vxfAcZXod4OgyN9/T0Ue0VzQKBgQCPAP4LVVQmR5lPh+ShQZ9tqsJBPtIpgUTKnvEnZ4Sag6j3iRifFNOOFYMvZbdz2UdeHigOdsdYg62zEKREhOw2gAefNrksA3MkKRb3ZUdz4OcUsNXQ8d6EYqxRp8DSReGgKlLOW1lIJVuoM/NQLlMk9hw2c7eDdWtROrEnZD/+LwKBgD0CPaKsuFu+TwUU9Q1GkxgTh0ULTIrDTZo/nP/KGXILEZtBE+2yWGfMmUGKIJgP79qqRqJog+o7RyJpQT+G0va9Og7b6Oe8IyL3ghapwJ4N1OoFMHpjwKMmiOCouJpce5/68ZeMFT446agi8lUcrvi+hOJg8dHPxzYBs5IIwpoZAoGBAK8ULpHFBsyOTF/kT88E1702rdecRxJNIkzKqEHRQ3wM/mU6Ijjzm8CRB8uRCKoUrwm0DtkcJluLgZiqsmtZ4YcPuuJ2LFLe6d0Cwog2aoJW/yF4zVo27xuyoK1Hd1z7uOgUsYEN/zdoqr30lQuX6jxLYA7wXVBRGh88P+KZJLY+";
        
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //应用ID，暂定，APP申请分配
    protected static final String APP_ID = "APP_ID";
    
    public static void main(String[] args){

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankOspayCborderShtransdetailNoticeregisterRequestV1 request = new MybankOspayCborderShtransdetailNoticeregisterRequestV1();
        request.setServiceUrl("http://ip:port/api/mybank/ospay/cborder/shtransdetail/noticeregister/V1");

        MybankOspayCborderShtransdetailNoticeregisterRequestV1Biz bizContent = new MybankOspayCborderShtransdetailNoticeregisterRequestV1Biz();
      
        bizContent.setClientId("201906200000021"); 
        bizContent.setBatchSerialNo("123456781");
        bizContent.setDetailFileName("kjsh.zip");
        bizContent.setDetailFileDigest("b3c85a51fa813315d2ce788164c0b7bc");
        bizContent.setKeyMsg(123468900);
        bizContent.setRecordDate("2020-04-04");
        bizContent.setZoneNo(200);
        bizContent.setBrNo(998);
        bizContent.setKeymak(6);

        request.setBizContent(bizContent);
        try {
            client.execute(request);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
   

}
