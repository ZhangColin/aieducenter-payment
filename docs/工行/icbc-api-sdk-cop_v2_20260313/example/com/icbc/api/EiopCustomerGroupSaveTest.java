package com.icbc.api;


import com.icbc.api.request.EiopCustomerGroupSaveRequestV1;
import com.icbc.api.request.EiopCustomerGroupSaveRequestV1.EiopCustomerGroupSaveRequestV1Biz;
import com.icbc.api.response.EiopCustomerGroupSaveResponseV1;
/*
 * @author:  kfzx-Haozuoyu
 * @description:
 * @param: 
 * @return: 
 * @date: 2023/12/25  15:28
 */
public class EiopCustomerGroupSaveTest {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //2、appid
    protected static final String APP_ID = "10000000000000014035";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥 行外访问
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCO/Q2D4B77lM9GbN5n7NTkqIHloL5RJh8hh4Hbt8dWGj19pTmtDucMn7alRQk99DHW230So/OgH6OiX5GVGNKfH6I9ONEuJ69Oq7lXg9KvUg4LrsI6HhiMdqVhUx0/5YXCaNBP3yj0GJH2VEHtD9iJkqbT5K42SM0alwEvqZ3GQ30ADFNT73xif+/qK/FHqM9CaS1m1EzgXhITeHZ1sgCoXWz3oJjtv5+K3XFXg44h3sVDwUKGMOMMg+EyZMU8pg+pDxoAnD9W3vKAhWN2myVedzP2Ua8xJMPQwB3IaBHhTtD07j7Lskg995D+3HLEOg4w2F3n/V9a0o2U6A/4r93bAgMBAAECggEALof88PBrRLWdai6dhbZVZX/Mnbxckq3ULaYaRTUeIk/xuMSe60lRORN0bX30zvt7iI7uubUu3+nL51gwWRO13uB9fGzT7bAuZWyOqQMV+cW7GmQpgp4uoO8vTGeuug9oIubW+1cEzcJ9JWsv9QRWFBbsQUJGyxWP4VpIoYkR9eWdARrnDN4Oc3tQo6Mn+cE6rykm7eLxtGq8BK5B+ktUYx12nEMtVwOvkNbdY+ryIa/Zqh8+vNj7MCEOZXDFMhNCEFH0SlNppcF6JN6tj47RIgumAH7hiAcVJREg3oz3ScI3QmDWG0wZfjDMNwlTzpRX7rLr9THRzxIGcikFz+p6wQKBgQDYK+wQsPsNUZ8ZYjSRcUJdrDuyJGEWe0AqOpJ8f4Q9jc/XIPmrgBbQ9lWwcT4NLzsQ4Ls0zCG/pPbikrJ2x5HjtjhhD6hqd6T5jK4RpokDdQU4LY3ib7H4aaLFr9/p7az2DVYfndWeuEiopCouponResultQuery4YblBcqZzPOy4KshdgCl7WyKj0iTewKBgQCpVVVfy8QB9YZjw/+jMMjX+OvCydDVSzhIxWOlVHN4e/a6UhNDrwS8O4G2hN7cUsa2/e5DpUvdKId0ZZXTjgE3ZJ7NcKA9MWOsjsLfUeip8y7Jeb8tmfw+yo+VMqQ/D9Wmd0mpIm6dsjfwsuRfvD+46wTjx7FrASmPi7yUrlYhIQKBgEsG44XfhyLWQ1vyA8C/CIOudYGcsr20xU5iydjAAUvD3nESFtV5PXIFLTRx+VFmJR/0f8URXF1BihaVR6+GEJJx+htl7dQV9oJOW1Pw8vjqMoeO+AGxNpm7QC1Qn2x3e2JiupN6mlvfIT49Qi5W1jyPL73T0FFqvUrZnqRwmoXlAoGAVOM4d1uZGTpScrUQ9GhCZzKcTfwKOsMIpkJ936XkdSP/3gvDKI0WnJ5gOnWFm4HLqHTH+xVkYKwuq4U5hOKNbY19ppOtYL/freOrgXR4ZKpKbCoiUuP89iVpsWu/tvVjL55hSO6/8UIklP+5SN0APN1kiCPVp0Tf5twwX4C+IMECgYEAzdo0jQjqz/ijnQDAFHw/pgAnbUIxp0nXnjoPjBRM9vonD0z/3BrvvRyldglo6urWRx1v+/ONkbXGSEpmWESOaRKRCiIZpcf294fAgKHDubMKpSJqu5WMfJx6Ffx5s+WmTHGcboAXEabMM87iE1nUF6eRnRTeT6SWtL5DKEgGYUs=";

    /**
     * @param args
     */
    public static void main(String[] args) {
        //签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        EiopCustomerGroupSaveRequestV1 request = new EiopCustomerGroupSaveRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        //行外流程
        request.setServiceUrl("http://ip:port/api/eiop/customer/group/save/v1");

        //5、请对照接口文档用bizContent.setEiopCouponResultQuery()方法对业务上送数据进行赋值
        EiopCustomerGroupSaveRequestV1Biz bizContent = new  EiopCustomerGroupSaveRequestV1Biz();

        EiopCustomerGroupSaveRequestV1.Common common = new EiopCustomerGroupSaveRequestV1.Common();
        EiopCustomerGroupSaveRequestV1.Parameter parameter = new EiopCustomerGroupSaveRequestV1.Parameter();
        common.setSenderid("F-EIOP-ODA");//调用方应用名称或分行名称
        common.setSerialno("235423452456465346");//渠道号

        parameter.setCgFileType("1");
        parameter.setCgCreateWay("");
        parameter.setOrganization("斗罗大陆");
        parameter.setOrganizationId("斗罗大陆001");
        parameter.setDomain("1");
        parameter.setExecSubFlag("5");
        parameter.setCgCustflag("1");
        parameter.setCgName("我是一个大客群");
        parameter.setAccendant("萧炎");
        parameter.setAccendantId("斗皇");
        //备用字段，
        parameter.setBak1("");
        parameter.setBak2("");
        parameter.setBak3("");
        parameter.setBak4("");
        parameter.setBak5("");
        parameter.setBak5("");
        parameter.setBak6("");
        parameter.setBak7("");
        parameter.setBak8("");
        parameter.setBak9("");
        parameter.setBak10("");
        parameter.setBak11("");
        parameter.setBak12("");
        parameter.setBak13("");
        parameter.setBak14("");
        parameter.setBak15("");
        parameter.setBak16("");
        parameter.setBak17("");
        parameter.setBak18("");
        parameter.setBak19("");
        parameter.setBak20("");
        parameter.setBak21("");
        parameter.setBak22("");
        parameter.setBak23("");
        parameter.setBak24("");
        parameter.setBak25("");
        parameter.setBak26("");
        parameter.setBak27("");
        parameter.setBak28("");
        parameter.setBak29("");
        parameter.setBak30("");
        parameter.setBak31("");
        parameter.setBak32("");
        parameter.setBak33("");
        parameter.setBak34("");
        parameter.setBak35("");
        parameter.setBak36("");
        parameter.setBak37("");
        parameter.setBak38("");
        parameter.setBak39("");
        parameter.setBak40("");
        parameter.setBak41("");
        parameter.setBak42("");
        parameter.setBak43("");
        parameter.setBak44("");
        parameter.setBak45("");
        parameter.setBak46("");
        parameter.setBak47("");
        parameter.setBak48("");
        parameter.setBak49("");
        parameter.setBak50("");


        bizContent.setCommon(common);
        bizContent.setParameter(parameter);


        request.setBizContent(bizContent);

        EiopCustomerGroupSaveResponseV1 response = new EiopCustomerGroupSaveResponseV1();
        try {
            response = client.execute(request, "msgId");
            if (response.isSuccess()) {
                EiopCustomerGroupSaveResponseV1.AppStatV10 result  = response.getAppStatV10();
                EiopCustomerGroupSaveResponseV1.Data data  = response.getData();
                // 业务成功处理
            } else {
                // 失败
                int returnCode = response.getReturnCode();
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
