package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankPayFiancialFinrefundRequestV3;
import com.icbc.api.request.MybankPayFiancialFinrefundRequestV3.MybankPayFiancialFinrefundRequestV3Biz;
import com.icbc.api.response.MybankPayFiancialFinrefundResponseV3;

public class MybankPayFiancialFinrefundV3Test {

    //合作方私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //appid
    protected static final String APP_ID = "10000000000000010422";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8",
                "json", APIGW_PUBLIC_KEY, "AES", "Nttz4Bs/k8eqWGn1c6pOMg==", "", "");

        MybankPayFiancialFinrefundRequestV3 request = new MybankPayFiancialFinrefundRequestV3();

        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/mybank/pay/fiancial/finclearpay/V1");

        MybankPayFiancialFinrefundRequestV3Biz bizContent = new MybankPayFiancialFinrefundRequestV3Biz();

        bizContent.setAdmdivCode("310321");
        bizContent.setAmt("102");
        bizContent.setAppCode("8800");
        bizContent.setBackSerno("949132027062757280185");
        bizContent.setBrno("00620");
        bizContent.setChannlNo("102");
        bizContent.setInnerAcctName("2013年8月版本200地区2网点——HUI");
        bizContent.setInnerAcctno("0200000209207295184");
        bizContent.setOperType("202");
        bizContent.setPayAcctName("2013年8月版本200地区2网点——HUI");
        bizContent.setPayAcctNo("0200000209207295184");
        bizContent.setSerno("57280181");
        bizContent.setStYear("2027");
        bizContent.setTellNo("01920");
        bizContent.setTradeSerNoB("80181");
        bizContent.setTradeSerNoS("572");
        bizContent.setTradeType("01");
        bizContent.setTrxCode("9498");
        bizContent.setVoucherChanId("102");
        bizContent.setVoucherNo("VCH000000685833");
        bizContent.setVoucherType("1202");
        bizContent.setWorkdate("2027-06-27");
        bizContent.setZoneNo("00200");

        MybankPayFiancialFinrefundResponseV3 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("返回:" + JSON.toJSONString(response));
            } else {
                // 失败
                System.out.println("返回:" + JSON.toJSONString(response));
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
