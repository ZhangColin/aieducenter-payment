package com.icbc.api;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiPayB2bPaySyncPayRequestV1;

public class JftUiPayB2bPaySyncPayTestV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String APP_ID = "10000000000002554554";
            String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

            UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);

            JftUiPayB2bPaySyncPayRequestV1 request = new JftUiPayB2bPaySyncPayRequestV1();
            request.setServiceUrl("http://ip:port/ui/jft/ui/pay/b2bpay/syncpay/V1");

            JftUiPayB2bPaySyncPayRequestV1.JftUiPayB2bPaySyncPayRequestV1Biz bizContent = new JftUiPayB2bPaySyncPayRequestV1.JftUiPayB2bPaySyncPayRequestV1Biz();

            bizContent.setAppId("10000000000002554554");
            bizContent.setOutVendorId("123654123");
            bizContent.setOutUserId("1001765234");
            bizContent.setNotifyUrl("http://www.baidu.com");
            bizContent.setJumpUrl("http://www.baidu.com");
            bizContent.setOutOrderId("20181128531212019");
            bizContent.setTradeTime("201904101111111");
            bizContent.setTrxChannel("01");
            bizContent.setPayAmount("0.11");
            bizContent.setFloatRate("888");
            bizContent.setPayMethod("01");
            bizContent.setProvince("北京");
            bizContent.setCounty("北京");
            bizContent.setCity("北京");
            bizContent.setEmail("123456@icbc.com");
            bizContent.setPhone("1388888888");
            bizContent.setAddress("北京市北京西三旗建材城东路16号");
            bizContent.setPost("5168845");
            bizContent.setOrderRem("食品");
            bizContent.setPayRem("试一下");
            bizContent.setPayerName("食品");
            bizContent.setPayerAcctNum("万众集团");
            bizContent.setAcctOrgcode("456789");
            bizContent.setPayPhoneno("1388888888");
            bizContent.setCurrType("001");
            bizContent.setRecOrgCode("456789");
            JftUiPayB2bPaySyncPayRequestV1.JftUiPayB2bPaySyncPayRequestV1Biz.GoodsInfo goodsInfo = new JftUiPayB2bPaySyncPayRequestV1.JftUiPayB2bPaySyncPayRequestV1Biz.GoodsInfo();
            goodsInfo.setGoodsSeqno("1");
            goodsInfo.setGoodsName("牛肉干");
            goodsInfo.setGoodsNum("10");
            goodsInfo.setGoodsAmt("10");
            goodsInfo.setWeight("肉干");
            goodsInfo.setPrice("50");
            goodsInfo.setSellerAddress("13866668");
            goodsInfo.setSellerName("牛大");
            goodsInfo.setUnits("斤");

            bizContent.setGoodsInfo(Collections.singletonList(goodsInfo));



            request.setBizContent(bizContent);
            resp.setHeader("Content-Type", "text/html;charset="
                    + IcbcConstants.CHARSET_UTF8);
            PrintWriter out = resp.getWriter();
            System.out.println(client.buildPostForm(request));

            out.write("<html>");
            out.write("<head>");
            out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
                    + IcbcConstants.CHARSET_UTF8 + "\">");
            out.write("</head>");
            out.write("<body>");
            out.write(client.buildPostForm(request));
            out.write("</body>");
            out.write("</html>");

        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
