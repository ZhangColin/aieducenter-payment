package com.icbc.api;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftApiVacctNovelAcctOnlinePayRechargeRequestV1;
import com.icbc.api.request.JftApiVacctNovelAcctOnlinePayRechargeRequestV1.JftApiVacctNovelAcctOnlinePayRechargeRequestV1Biz;
import com.icbc.api.response.JftApiVacctNovelAcctOnlinePayRechargeResponseV1;
import org.junit.Test;

/**
 * 新型登记簿线上支付充值接口
 *
 * @author zuo shuai
 * @date 2021/5/25 9:52
 */
public class JftApiVacctNovelAcctOnlinePayRechargeTestV1 {
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000000205520";

    @SuppressWarnings("unchecked")

    public void test_cop() {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");
        JftApiVacctNovelAcctOnlinePayRechargeRequestV1 request = new JftApiVacctNovelAcctOnlinePayRechargeRequestV1();
        request.setServiceUrl("http://ip:port/api/jft/api/vacct/novel/acct/online/pay/recharge/V1");
        JftApiVacctNovelAcctOnlinePayRechargeRequestV1Biz bizContent = new JftApiVacctNovelAcctOnlinePayRechargeRequestV1Biz();
        bizContent.setAppId("10000000000000205520");
        bizContent.setOutSubVendorId("10000000000000205520");
        bizContent.setBusiNo("202108010000457000010100");
        bizContent.setSerialNo("202108010000457000010100");
        bizContent.setAcctNo("0002000020000000138286");
        bizContent.setAmount("3068.88");
		bizContent.setRealAmount("3068.88");
        bizContent.setVersion("1.0.0");//设置接口访问版本号
        request.setBizContent(bizContent);
        JftApiVacctNovelAcctOnlinePayRechargeResponseV1 response;
        try {
            response = (JftApiVacctNovelAcctOnlinePayRechargeResponseV1) client.execute(request, System.currentTimeMillis()+"");

            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
