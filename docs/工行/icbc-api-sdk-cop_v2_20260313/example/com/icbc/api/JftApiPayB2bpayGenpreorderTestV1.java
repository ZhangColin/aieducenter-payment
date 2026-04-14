package com.icbc.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiPayB2bpayGenpreorderRequestV1;
import com.icbc.api.response.JftApiPayB2bpayGenpreorderResponseV1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JftApiPayB2bpayGenpreorderTestV1 {


    public void test() throws IcbcApiException {

        String APP_ID = "10000000000000212516";
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";
        String icbcPulicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        String AES_Key = "5xGJdh7qb+B95SUoxDlatg==";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON,
                icbcPulicKey, IcbcConstants.ENCRYPT_TYPE_AES, AES_Key, null, null);

        JftApiPayB2bpayGenpreorderRequestV1 request = new JftApiPayB2bpayGenpreorderRequestV1();
        request.setServiceUrl("https://apipcs4.dccnet.com.cn/api/jft/api/pay/b2bpay/genpreorder/V1");

        JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz bizContent = new JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz();

        bizContent.setAppId("10000000000000205515");
        bizContent.setOutVendorId("04221125");
        bizContent.setOutUserId("202004221117");
        bizContent.setNotifyUrl("http://www.xxx.com");
        bizContent.setJumpUrl("http://www.xxx.com");
        bizContent.setOutOrderId("20201128531215026");
        bizContent.setTradeTime("2020-07-30 08:41:56");
        bizContent.setTrxChannel("01");
        bizContent.setTrxIp("ip:port");
        bizContent.setPayAmount("2.00");
        bizContent.setOrderAmount("2.00");
        bizContent.setPayMethod("02");
        bizContent.setFloatRate("888");
        bizContent.setProvince("北京");
        bizContent.setCounty("北京");
        bizContent.setCity("北京");
        bizContent.setEmail("123456@icbc.com");
        bizContent.setPhone("1388888888");
        bizContent.setAddress("北京市北京西三旗建材城东路16号");
        bizContent.setPost("5168845");
        bizContent.setPayRem("试一下");
        bizContent.setOrderRem("食品");
        bizContent.setAddRemark("食品");
        bizContent.setPayerType("3");
        bizContent.setPayerAcctId("1001765234");
        bizContent.setPayerName("万众集团");
        bizContent.setPayerAcctNum("万众集团");
        bizContent.setAcctOrgcode("456789");
        bizContent.setPayPhoneno("1388888888");
        bizContent.setCurrency("001");
        bizContent.setRecOrgCode("456789");
        bizContent.setRecAcctId("123654123");
        bizContent.setRecName("123654123");
        bizContent.setRecAcctNum("123654123");
        bizContent.setAsynFlag("0");
        bizContent.setStlFlag("0");
        bizContent.setPayModeTool("1");
        bizContent.setMultiRecFlag("1");

        JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.Good goodsInfo = new JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.Good();
        goodsInfo.setGoodsSeqno("1");
        goodsInfo.setGoodsName("牛肉干");
        goodsInfo.setGoodsNum("10");
        goodsInfo.setGoodsAmt("10");
        goodsInfo.setWeight("肉干");
        goodsInfo.setPrice("50");
        goodsInfo.setSellerAddress("13866668");
        goodsInfo.setSellerName("牛大");
        goodsInfo.setUnits("斤");
        goodsInfo.setOutVendorId("04221125");
        JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.Good goodsInfo2 = new JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.Good();
        goodsInfo2.setGoodsSeqno("2");
        goodsInfo2.setGoodsName("牛肉干");
        goodsInfo2.setGoodsNum("10");
        goodsInfo2.setGoodsAmt("10");
        goodsInfo2.setWeight("肉干");
        goodsInfo2.setPrice("50");
        goodsInfo2.setSellerAddress("13866668");
        goodsInfo2.setSellerName("牛大");
        goodsInfo2.setUnits("斤");
        goodsInfo2.setOutVendorId("04221120");

        List<JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.Good> goodList = new ArrayList<>();
        goodList.add(goodsInfo);
        goodList.add(goodsInfo2);
        bizContent.setGoodsInfo(goodList);

        JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.RecInfo recInfo1 = new JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.RecInfo();
        recInfo1.setOutVendorId("04221125");
        recInfo1.setRecAcctId("123654123");
        recInfo1.setRecAcctNum("123654123");
        recInfo1.setRecName("123654123");
        recInfo1.setRecOrgCode("123");
        recInfo1.setTrxAmount("1.00");
        recInfo1.setOrderAmount("1.00");
        recInfo1.setFixedFee("100");
        recInfo1.setFloatRate("1");
        JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.RecInfo recInfo2 = new JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.RecInfo();
        recInfo2.setOutVendorId("04221120");
        recInfo2.setRecAcctId("04187335");
        recInfo2.setRecAcctNum("123654123");
        recInfo2.setRecName("123654123");
        recInfo2.setRecOrgCode("456");
        recInfo2.setTrxAmount("1.00");
        recInfo2.setOrderAmount("1.00");
        recInfo2.setFixedFee("100");
        recInfo2.setFloatRate("1");

        List<JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.RecInfo> recList = new ArrayList<>();
        recList.add(recInfo1);
        recList.add(recInfo2);
        bizContent.setRecList(recList);

        JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.OriTrxSerialNo oriTrxSerialNo1 = new JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.OriTrxSerialNo();
        oriTrxSerialNo1.setDetailNo("1");
        oriTrxSerialNo1.setOriTrxSerialNo("123654123");
        oriTrxSerialNo1.setTrxAmount("1.00");
        JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.OriTrxSerialNo oriTrxSerialNo2 = new JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.OriTrxSerialNo();
        oriTrxSerialNo2.setDetailNo("2");
        oriTrxSerialNo2.setOriTrxSerialNo("04187335");
        oriTrxSerialNo2.setTrxAmount("1.00");

        List<JftApiPayB2bpayGenpreorderRequestV1.JftApiPayB2bpayGenpreorderRequestV1Biz.OriTrxSerialNo> oriTrxSerialNoList = new ArrayList<>();
        oriTrxSerialNoList.add(oriTrxSerialNo1);
        oriTrxSerialNoList.add(oriTrxSerialNo2);
        bizContent.setOriTrxSerialNoList(oriTrxSerialNoList);

        bizContent.setSummary("测试一下");
        bizContent.setPurpose("测试一下");
        bizContent.setIsPromotion("0");
        bizContent.setBatchNo("1000001");
        bizContent.setBatchAmount("5.00");
        request.setBizContent(bizContent);

        JftApiPayB2bpayGenpreorderResponseV1 responseV1 = client.execute(request);
        System.out.println(responseV1);

    }
}
