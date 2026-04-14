package com.icbc.api;

import com.icbc.api.request.JftApiVendorSeparateAccountsBusinessQueryRequestV1;
import com.icbc.api.request.JftApiVendorSeparateAccountsBusinessQueryRequestV1.JftApiVendorSeparateAccountsBusinessQueryRequestV1Biz;
import com.icbc.api.response.JftApiVendorSeparateAccountsBusinessQueryResponseV1;

/**
 * @Author wblizl
 * @Date 2019/4/22 11:09
 * @Description:
 */
public class JftApiVendorSeparateAccountsBusinessQueryTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //2、appid
    protected static final String APP_ID = "10000000000007354590";
    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";


    public static void main(String[] args) throws Exception {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES","nezfRV32iH9deJq4IrcCpw==", "", "");
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", "nezfRV32iH9deJq4IrcCpw==", "", "");

        JftApiVendorSeparateAccountsBusinessQueryRequestV1 request = new JftApiVendorSeparateAccountsBusinessQueryRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("https://ip:port/api/jft/api/vendor/separate/accounts/business/query/V1");
 
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值

        JftApiVendorSeparateAccountsBusinessQueryRequestV1Biz bizContent = new JftApiVendorSeparateAccountsBusinessQueryRequestV1Biz();

        bizContent.setAppId("10000000000007354590");//必须
        bizContent.setOutVendorId("10000000000007354590");//必须
        bizContent.setBusinessType("02");//必须 开通业务类型 01 内部机构开通业务 02 企业会员开通业务
        bizContent.setSubBusinessType("02");//使用业务类型 非必须上送
        /*
        使用业务类型 01 开通绑定收款管家卡; 02 开通绑定电费付款管家卡; 03 开通绑定水费付款管家卡;
        此字段非必填
        当开通业务类型为 01 时  此字段不用上送
                                         |~ 不上送时会查询该上送会员的全部开通成功业务
        当开通业务类型为 02 时  此字段  <
                                         |_ 上送时会查询对应使用业务类型的业务
        */

        request.setBizContent(bizContent);
        System.out.println("request:" + request.toString());

        JftApiVendorSeparateAccountsBusinessQueryResponseV1 response;

        try {
            response = (JftApiVendorSeparateAccountsBusinessQueryResponseV1) client.execute(request, System.currentTimeMillis() + "");
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
