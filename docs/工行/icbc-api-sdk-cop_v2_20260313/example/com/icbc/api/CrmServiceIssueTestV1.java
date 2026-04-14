package com.icbc.api;

import com.icbc.api.request.CrmServiceIssueRequestV1;
import com.icbc.api.response.CrmServiceIssueResponseV1;

public class CrmServiceIssueTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //2、appid
    protected static final String APP_ID = "10000000000004097499";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsTeNSVZJtlJFoP9Ngg0BsmCT3UjImmafudX5MJBNmjtJ3dXE8oET7OhfteFvjFnwdSG1SfvynruboJM4lAj9PHwfhJU412LEdRFXZVQioufZmOiT8mL4Zv2ufUDTM+dIOCGPwHXnA4iYKqmil/gH/fHfxhmPFlxtlyaGfnZ9lzQmgISelPI3eOjBmEfnLJrfCzZtbL8vJKM4HKigadWA1vRCBc2MJ79taFD+f7NXgBgrfP51mT/8BGVK09XgQRyEGD7ZjPrTj0VXyV/yzg7A8KedhO8CgJIIJqotUy9Xq/d6TWZwakhjg/qN2DBAv6TFCGUyl5FhQeMM51aJ2VFgpAgMBAAECggEAUdjRwbuKSelHxQiEhRTz1mHTNXRjBDj7L2e6bbq5SH9RdJNiDdVnzxrCYPhKVa3JmTNdUtGlfJW1c9J/5c6SeigYWxeYdjWPtIJQcWNWMKfdZqP6C88Whi6KlevImRGWeTn9FwM5YalLPsfB3A+zrUPXye/3ltV3mztTPfnmzLj20RITjejEvzUhQY8rxBmnQCdynL34/e1WdmQWMkgRS7+32RFnEo/1LvjJqw+6/dwcyfhhb/S1XO49YKo7gF/JvxlMqLwJ5sIxKlIGbIRgOUHNbvANoRnZc4sz9dcJVfkOwPMKPUq6b85FwPKRwFiur9nUZri170v9l/IROreDgQKBgQDukqy4/ECNunJu+5exkRDD9a3UdD5TE+reVWjcTs4MOOAakl2e5jJcfEaCiy2qC4I83nrlU52AmydVMmFtALQZT+8QzPy3hFmbOBSJv06/rle8bMF7Dzv0t4BE+zM2ohV5lT1GR/z7L2pNnFvmzazJlbHhEH1paRuX/1R6FWKp+QKBgQC44/xK8Cmowk9z7iEhaR6iHuQaL7Cqb2gmzypT4EJTF8lAsZ81oT6DY8tqE35YrNx2kFZ/pFKdkUsradROmia4SIsTL98sVGgUwJG2YXJrVt1FlY9kjNvNJae5vTrZrbCC19uGl6NWGek4U6uNBKJ22WwhUHcR98lY82AqExArsQKBgQC2DdGmfEBapwvKK03P3852a1VSSb6BsTJKpcyWScCszP4e6NBugGWv89nupnybRZsGDhSkSZFoYX27Yu27u4yzwC7DmFnFRUcHztzFXOqgFU+4ZxmiabasARfuC+9B4qAWns8agbbIi59ISGhNEWiokAYkpGu/c8jtzZdObmFTKQKBgFhUo13x6xUy796E79MUc4wkkflou1BRtUKFSsWTqFwmSwHbWiK3DDSdYrsk4QHgY9tJDeohSU758R2mi8WZo9H65maeQlLdaHQxo+usFovtjf0GfOEe4ovZ5423N/xzHJcGJVzh6SjjbCZ64sSWaD+7lAs+dARjixAyBclUHi+hAoGBAJjrOmlmSHDmvMxO1OBm012zQ3RIEaAR8iOEbs3q9mhAWDi8fvnAMWzXRMgbJZrQSUxWYwTDcdkVYNblJOtLx7CM7FJV497YDvbWeF5uRCJF33BHjDsrqACjuZeYJMwRBlDMJO2k0rZ29lMJpQUIY8m6u1p3oxSOs4yvh1r7bro7";

    public static void main(String[] args) {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CrmServiceIssueRequestV1 request = new CrmServiceIssueRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/crm/service/issue/V1");
//        request.setServiceUrl("http://dsf-2101-gn-nova-novagn-app1.sdc.cs.icbc:16257/icbc/cocoa/apip/com.icbc.pbms.service.ATSExclusiveService/1.3/crmSvcOutterIssue");

        CrmServiceIssueRequestV1.CrmServiceIssueRequestBizPublicV1 publicV1 = new CrmServiceIssueRequestV1.CrmServiceIssueRequestBizPublicV1();
        publicV1.setSerialno("0200123456564234343");
        publicV1.setZoneno("00200");
        publicV1.setBrno("0998");

        CrmServiceIssueRequestV1.CrmServiceIssueRequestBizPrivateV1 privateV1 = new CrmServiceIssueRequestV1.CrmServiceIssueRequestBizPrivateV1();
        privateV1.setCustId("320200074372970");
        privateV1.setServiceId("PBMS20290416000000133");
        privateV1.setCountId("COUNT20201221000000002");
        privateV1.setOrderid("123456789012345");
        privateV1.setBookTime("2020-12-06 13:00:00");
        privateV1.setBookInfo("预约2020-12-06 13:00:00的舒适型礼宾车服务，车牌号码ABCDE，上车地点XXX");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CrmServiceIssueRequestV1.CrmServiceIssueRequestBizV1 bizContent = new CrmServiceIssueRequestV1.CrmServiceIssueRequestBizV1();
        bizContent.setPublicObj(publicV1);
        bizContent.setPrivateObj(privateV1);

        request.setBizContent(bizContent);

        CrmServiceIssueResponseV1 response;
        try {
            response = client.execute(request, "urcnl24ciutr9");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
