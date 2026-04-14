package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import com.icbc.api.request.ElifeMeituanOrderChangePushRequestV1;
import com.icbc.api.response.ElifeMeituanOrderChangePushResponseV1;

public class ElifeMeituanOrderChangePushTestV1 {
    private  static final Logger logger = Logger.getLogger(ElifeMeituanOrderChangePushTestV1.class.getName());
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //appid
    protected static final String APP_ID = "";//例如 10000000000000002697

    //私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIh0pL84ShSJqeoz01ef2b9PoDZKgEz396kXWa69tjz2jRHvhoD3beQRqTPnDYCn1FqT/vV4tElxXNfEPZcmDgGKUxMu8F+t3vyOKS6RKCWJYTddeljLYnPTLL7rPWEhHLPiTNFfkf5qIh3lca9SV1bnARNIc25DweZAcZCkot/3AgMBAAECgYBiLhsDgsq6cDqTSmq710XiwrXxmjUqLBhDz4s1kUZ4IqVpzD+ot16YBKULtSkf8qF18po1oH2Hgktc69Os1zKnkqBXq9PUGF6XRFqCSfus5EWXLKrg9puB02FqbBl6Vja+R0GrYl3f9zl6O7Fooj2p8y04Y7CV4boXcj8DVa+icQJBAOSkuFIp1ywpdr65KQMnxYXr2VissU78nDPFjSqTjxzNks0po/QXTB4HD1PXaxQHtbhiqjUjYui2eZ2lft5IPv0CQQCYyD0nZQoQUJlkoNYBMdFcYhjlJBjbhcX+yMQI5D7AO/OFsS+tmRCKAqluPWruuFJheLPtAtXNViSc2vVEx58DAkBWAVAEOBDkX8R3AZdXxIIcWhcrr+oSWBs5rjnnphHTN9Dcs6kkjA7pzKYLV6DDrBLDBPoZevIdr2YX0sTiQDLRAkBPm/8pmN9+ktDq+1entbK25bK9K2r8cB80XJ6xV2VPO63v2tzSIzDYCP0KIVyn2Umr2n4+oLyRSzK2vO0SksXLAkEAsaSosxPz80d7QVT8Dp2qcT7Zb2omxxJuL9JJSL9cqroIuTKhET7SNCk5DZ8dQRm/t0YM1qf1+Ghg23dXuFt/ZQ==";

    public static void main(String[] args) throws Exception{
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        ElifeMeituanOrderChangePushRequestV1 request = new ElifeMeituanOrderChangePushRequestV1();
        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("https://ip:port/api/elife/meituan/orderChangePush/V1");

        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        ElifeMeituanOrderChangePushRequestV1.ElifeMeituanOrderChangePushRequestV1Biz bizContent = new ElifeMeituanOrderChangePushRequestV1.ElifeMeituanOrderChangePushRequestV1Biz();
        bizContent.setEntId(617);
        bizContent.setTs(1621222860L);
        bizContent.setMethod("order.change.push");

        Map<String,Object> data = new HashMap<>();
        data.put("sqtBizOrderId",526001045969776640L);
        data.put("origOrderId","694866826080335");
        data.put("sqtType",10);
        data.put("changeType",20);
        data.put("changeStatus","20");
        data.put("changeTime",1621222800000L);
        data.put("extInfoMap","");
        bizContent.setData(data);

        request.setBizContent(bizContent);

        ElifeMeituanOrderChangePushResponseV1 response;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String msgId = dateFormat.format(new Date());
            response = client.execute(request, msgId);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                // 失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
