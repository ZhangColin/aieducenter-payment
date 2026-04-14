package com.icbc.ndf.jft.b2bpay.impl;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiPayB2bPayFinancingPayRequestV1;
import com.icbc.api.response.JftApiPayB2bPayFinancingPayResponseV1;

import java.util.Collections;

public class JftApiPayB2bPayFinancingPayTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000000086505";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8",
                "json",APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");
        JftApiPayB2bPayFinancingPayRequestV1 request=new JftApiPayB2bPayFinancingPayRequestV1();
        request.setServiceUrl("http://ip:port/api/jft/api/pay/b2bpay/financingpay/V1");
        JftApiPayB2bPayFinancingPayRequestV1.JftApiPayB2bPayFinancingpayRequestV1Biz bizContent=new JftApiPayB2bPayFinancingPayRequestV1.JftApiPayB2bPayFinancingpayRequestV1Biz();



        bizContent.setAppId("10000000000000012146");
        bizContent.setOutVendorId("71810240113");
        bizContent.setOutUserId("71810240115");
        bizContent.setNotifyUrl("http://www.baidu.com");
        bizContent.setJumpUrl("http://www.xxx.com");
        bizContent.setOutOrderId("20181128531212019");
        bizContent.setTradeTime("20181205111111");
        bizContent.setTrxIp("ip:port");
        bizContent.setTrxChannel("01");
        bizContent.setPayAmount("0.11");
        bizContent.setPayMethod("03");
        bizContent.setFloatRate("1");
        bizContent.setProvince("北京");
        bizContent.setCounty("北京");
        bizContent.setCity("北京");
        bizContent.setEmail("123456@icbc.com");
        bizContent.setPhone("1388888888");
        bizContent.setAddress("北京市北京西三旗建材城东路16号");
        bizContent.setPost("5168845");
        bizContent.setPayRem("试一下");
        bizContent.setOrderRem("食品");
        bizContent.setPayerName("xx集团");
        bizContent.setPayerAcctNum("0210062001111108811");
        bizContent.setAcctOrgcode("456789");
        bizContent.setPayPhoneno("1388888888");
        bizContent.setLoanCurrType("001");
        bizContent.setRecOrgCode("456789");
        bizContent.setBuyerOtherId("123456789012345678");
        bizContent.setBuyerStatus("1");
        JftApiPayB2bPayFinancingPayRequestV1.JftApiPayB2bPayFinancingpayRequestV1Biz.GoodsInfo goodsInfo = new JftApiPayB2bPayFinancingPayRequestV1.JftApiPayB2bPayFinancingpayRequestV1Biz.GoodsInfo();
        goodsInfo.setGoodsName("牛肉干");
        goodsInfo.setGoodsNum("10");
        goodsInfo.setGoodsAmt("3.01");
        goodsInfo.setWeight("10");
        goodsInfo.setPrice("50");
        goodsInfo.setSellerAddress("13866668");
        goodsInfo.setSellerName("牛大");
        goodsInfo.setUnits("斤");


        bizContent.setGoodsInfo(Collections.singletonList(goodsInfo));

        request.setBizContent(bizContent);

        JftApiPayB2bPayFinancingPayResponseV1 response;
        try {
            response =(JftApiPayB2bPayFinancingPayResponseV1) client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
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
