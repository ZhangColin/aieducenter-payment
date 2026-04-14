package com.icbc.api;

import com.icbc.api.request.EiopTaskcenterActivityQueryactlistRequestV1;
import com.icbc.api.response.EiopTaskcenterActivityQueryactlistResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ibm.java.diagnostics.utils.Context.logger;

public class EiopTaskcenterActivityQueryactlistTestV1 {
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    // 2、appid
    protected static final String APP_ID = "";//例如 10000000000000002697

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIh0pL84ShSJqeoz01ef2b9PoDZKgEz396kXWa69tjz2jRHvhoD3beQRqTPnDYCn1FqT/vV4tElxXNfEPZcmDgGKUxMu8F+t3vyOKS6RKCWJYTddeljLYnPTLL7rPWEhHLPiTNFfkf5qIh3lca9SV1bnARNIc25DweZAcZCkot/3AgMBAAECgYBiLhsDgsq6cDqTSmq710XiwrXxmjUqLBhDz4s1kUZ4IqVpzD+ot16YBKULtSkf8qF18po1oH2Hgktc69Os1zKnkqBXq9PUGF6XRFqCSfus5EWXLKrg9puB02FqbBl6Vja+R0GrYl3f9zl6O7Fooj2p8y04Y7CV4boXcj8DVa+icQJBAOSkuFIp1ywpdr65KQMnxYXr2VissU78nDPFjSqTjxzNks0po/QXTB4HD1PXaxQHtbhiqjUjYui2eZ2lft5IPv0CQQCYyD0nZQoQUJlkoNYBMdFcYhjlJBjbhcX+yMQI5D7AO/OFsS+tmRCKAqluPWruuFJheLPtAtXNViSc2vVEx58DAkBWAVAEOBDkX8R3AZdXxIIcWhcrr+oSWBs5rjnnphHTN9Dcs6kkjA7pzKYLV6DDrBLDBPoZevIdr2YX0sTiQDLRAkBPm/8pmN9+ktDq+1entbK25bK9K2r8cB80XJ6xV2VPO63v2tzSIzDYCP0KIVyn2Umr2n4+oLyRSzK2vO0SksXLAkEAsaSosxPz80d7QVT8Dp2qcT7Zb2omxxJuL9JJSL9cqroIuTKhET7SNCk5DZ8dQRm/t0YM1qf1+Ghg23dXuFt/ZQ==";

    public static void main(String[] args) throws Exception {

        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                APIGW_PUBLIC_KEY);

        EiopTaskcenterActivityQueryactlistRequestV1 request = new EiopTaskcenterActivityQueryactlistRequestV1();
        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("https://ip:port/api/eiop/taskcenter/activity/queryactlist/V1");
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        EiopTaskcenterActivityQueryactlistRequestV1.EiopTaskcenterActivityQueryactlistRequestV1Biz bizContent = new EiopTaskcenterActivityQueryactlistRequestV1.EiopTaskcenterActivityQueryactlistRequestV1Biz();


        Map<String, Object> chanCommV10 = new HashMap<>();
        chanCommV10.put("cino","xxxxxxxxxxxxx");
        // other para
        Map<String, Object> infoCommV10 = new HashMap<>();
        infoCommV10.put("trxcode","10009");
        // other para
        Map<String, Object> icom = new HashMap<>();
        icom.put("userid","A20234234xxxxxxxx");
        // other para
        bizContent.setChanCommV10(chanCommV10);
        bizContent.setIcom(icom);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setStructtype("JSON");
        bizContent.setAppsenderid("F-EIOP");
        bizContent.setAppreceiverid("F-EIOP");
        bizContent.setVersionid("1.0");
        bizContent.setMesgtype("");
        bizContent.setMesgstat("9");
        bizContent.setOrigsendtime("15:15:00");
        bizContent.setMsg_id("sdfsdfds");
        bizContent.setMesgpriority("");
        bizContent.setMesgcharset("UTF-8");
        bizContent.setOrigsenddate("2022-04-28");
        request.setBizContent(bizContent);
        EiopTaskcenterActivityQueryactlistResponseV1 response;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String msgId = dateFormat.format(new Date());
            response = (EiopTaskcenterActivityQueryactlistResponseV1) client.execute(request, msgId);
            if (response.isSuccess()) {

                Map appStatV10 = response.getAppStatV10();
                Map result = response.getResult();
                String transok = (String) appStatV10.get("transok");
                String returnMsg = (String) appStatV10.get("return_msg");
                String returnCode = (String) appStatV10.get("return_code");
                List list = (List) result.get("list");
                // 业务成功处理
                logger.info("ReturnCode:" + response.getReturnCode());
                logger.info("response:" + response);
//				System.out.println("ReturnCode:" + response.getReturnCode());
//				System.out.println("response:" + response);
            } else {
                // 失败
                logger.info("ReturnCode:" + response.getReturnCode());
                logger.info("ReturnMsg:" + response.getReturnMsg());
//				System.out.println("ReturnCode:" + response.getReturnCode());
//				System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
