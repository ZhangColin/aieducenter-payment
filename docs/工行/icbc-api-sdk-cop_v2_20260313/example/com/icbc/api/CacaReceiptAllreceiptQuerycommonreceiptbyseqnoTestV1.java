package com.icbc.api;

import com.icbc.api.request.CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1;
import com.icbc.api.response.CacaReceiptAllreceiptQuerycommonreceiptbyseqnoResponseV1;

import java.util.*;

public class CacaReceiptAllreceiptQuerycommonreceiptbyseqnoTestV1 {
    // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTBL1u89Ka0tsMNEqbYPbJjxTn8Xkr8tIHwJZ9odHWkcfTmnOU877cYl+UhdhkfcFEKuvEhX2isPGdaIrqzkm9RBh+K1PQwyViGAhkFiUXDNcp/MdPzGyEjhwd+adKwR3CJfZZoWhZITT0O7fq71ufcSLvCrc6jzOPIDzxLheVall79ZHgVOCDjpTI8JUsj/0kAmHzhRIJ3lUGWyv4mTipwyAT9lzmvXhMcMnzStJ7Jzn1le7VXAd3ERvEz/PKEaXtPJ3kd1Grt0Q2EAns6ozvUg7spEjSBL7K8G77nwuOGWvVqfYQ2o36f5UTczpDaq47zrFVq6tFOIh21LBiYEThAgMBAAECggEAMKeb7bFBnL11gtd3bW6snc39dDllCZdlzOz+oPtBz8oVIMF3yO7V1me0DBozoCZCJMYzmqk3B/WZMKfdeZHls35qwK6kIW3D3G/prv3pLnSwbJPcfls/+qpxQbZSmBnLOGqJ2eXh+8VSmhV9a4Hlh6HQlt2r//ROcuqPYJ4JbgfvW/XIOCzakodAFeGLR2MXa5w+M7/Wc4Ipy1R5Cn10ZElJdAq8Hn8maAzQnQMNfkElZwX+7P8kyv/ueeeTeR+4HHhP5Xd0XLAJ4WVgw/jvzpRyBFdy+Kalvc0G0utIR8+9yUXlQ9yojJFVTzVukq+RHgPQg9PFLtYW9BKMkXgbsQKBgQDhCskBxEgr6DsdjoqpVYHSq8IyTD46+ton1lR6yE6cfuabPqdqVhV1rcANPNI7sCHjdQJUOABJiOIXn5v3soTx2C3dnWVUAck33e5t0fXwlpN5d2lEok6ZYhk1n29S/fDv5FLP860hYIxWD1Hggcd/AqhTPqXAVDgIpAThi9id/QKBgQCnPjrOqsj7Sq0+3b9LF8ScSquxK5QaAJ712SnBT/EbjQJK0QiNHRl/03jFMIUwXHDVd6wYo0JYCBJFDZuZyVndPONz2rlx9Y9nP112RZedo4/lIdXV4ZQl8nsUrSS3zn2dHA9d/kcLTgV+0WQr1CSrCDnxXOgXRGNyu1dNJN8ltQKBgAcygRs9W9GLq9hWJrkjsvM56wwQxCy+uRttD2ad4WW/6UAt0Mzl0+nflLTXrSGbkHnl1B09bUTsy85VnHdfjkDt2QmtbwAwzm6q+mUoweTf6/eGiidXyYu+R3AfmT/eP0Zs1vkvoenfXJ2j/8peXDMDsr520nZEO0aLeFx+TH9BAoGBAIgEp6Vv5zfhe1d2197F/OS0IkFc1P305VPEBB6x2sFyhJu+uHq0YQnCPyX5GUnE1Qv4YaJEHXAlWF6K+7MuvISfwcl26X4Snkf1lBjd7rqJzT0osJZb+baHKK0fSI6pRSZjLOhgJv29veBvgk3XIt9rOFziF+zcdRlZ3l3ikA5FAoGAfI7mPDQby6/BPFMBG3A7UuL29X7Gb4RfOpqS49YsNuCDA0YYirHyOhn49Ai3s9YuegWLOSlWdm2Goz03YuNW7fbVI+WrNc4EeIyRAkd81PgF9gxTNKq+LVMzaD7bGf8an8F95fvgZi4xNuLHukjGW7Exr9ND8W4y8fv8Grw8RkA=";
        //2、appid
        String APP_ID = "1";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1 request = new CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1();
        request.setServiceUrl("https://ip:port/api/caca/receipt/allreceipt/querycommonreceiptbyseqno/V1");
        CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoRequestV1Biz bizContent = new CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoRequestV1Biz();

        // public 通讯区定义赋值
        CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoRequestV1Pub pubObj = new CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoRequestV1Pub();
        pubObj.setCode("29056");
        pubObj.setZone("0200");
        pubObj.setFr("0");
        pubObj.setDt("2017-07-06");
        // private 通讯区定义赋值
        CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoRequestV1In inObj = new CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoRequestV1In();

        inObj.setZone("0200");
        inObj.setBrno("");
        inObj.setTelno("");
        inObj.setChannel("");
        CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoCondRequestV1In qcReceiptBySeqnoCondRequestV1In = new CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoCondRequestV1In();
        qcReceiptBySeqnoCondRequestV1In.setAccno("");
        qcReceiptBySeqnoCondRequestV1In.setCurrtype("");
        qcReceiptBySeqnoCondRequestV1In.setMgpayacc("");
        qcReceiptBySeqnoCondRequestV1In.setSerialno("");
        qcReceiptBySeqnoCondRequestV1In.setSadlindate("");
        ArrayList<CacaReceiptAllreceiptQuerycommonreceiptbyseqnoRequestV1.QCReceiptBySeqnoCondRequestV1In> qcReceiptBySeqnoCondRequestV1Ins = new ArrayList<>();
        qcReceiptBySeqnoCondRequestV1Ins.add(qcReceiptBySeqnoCondRequestV1In);
        inObj.setCond(qcReceiptBySeqnoCondRequestV1Ins);
        bizContent.setIn(inObj);
        bizContent.setPub(pubObj);
        request.setBizContent(bizContent);
        try {
            CacaReceiptAllreceiptQuerycommonreceiptbyseqnoResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

