package com.icbc.api;

import com.icbc.api.request.MybankCardbusinessServiceMerchantqueryRequestV1;
import com.icbc.api.response.MybankCardbusinessServiceMerchantqueryResponseV1;

import java.util.HashMap;
import java.util.Map;

public class MybankCardbusinessServiceMerchantqueryTest {

    public static void main(String[] args) throws Exception {
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCzYUaZG/h8xTBwy"
                + "4FiUFwKrTnyBqbQNNa4zfWzMWK+cQ/eEHuPxKcWrv5lwcav+NXE1I3IMUuXIX7zee"
                + "vaIgNs0GnCgcxBk4il/NMoVxlfrUSU2I/cBSMLGcOoD95IeA1kSGO1gQGnKq+g76r"
                + "A0dt87+Jdh4Zjfc9wlryDnDhRRb4XF5t7KBehe8ESMR4K9oLcD2MXfxoXyJE7HiWY"
                + "lhHA3M2A1EDaIZKOcW817kZbJSL+3IeidfItGIVt4cG/GYWWbGnkwmoP2YvHRZcTq"
                + "ncNfcn2VieyWQB6IGyId1eSLDqvAQAHwe63dTO3ca5THlnJyM+4yTbYABBkTZJGhR"
                + "SLAgMBAAECggEAGFiom1lXhZ/B7l1Ap1Ze17ghOD883t99EUIrCfI3A8AWtj4odx/"
                + "qycJglvERBYtRgFCVGcnPB6ktMOCoaUfajSdQY2z4iyH6FwVUp5ICyKCcwVMPW356"
                + "/iO2EZZtxgo9TxIgLl9fGRBLHB3LYDZbgv6QC7+8Nr65q/9ZgMdwQV9qr5bfKyk1Q"
                + "fOdS2aOihSMqm6rB0L4UyMzLXe66QOwevXr1aje2t63yRDyzILoHhpx7BFTmAD43X"
                + "IVBYusbRS6rYI6NSt79Fh73sDFRqDTaNeJUFm4VqrN09vZv3TRasvgWoziYN3Qu4y"
                + "qrSbhxdyNUa/a5aCdfbrSXg5PDMflGQKBgQDfhLFWDssJCiRysmCpOZGjkyd7fivY"
                + "hOZedota7rw/rXjyAi+2Im2oZSq+v0gs4wSMKAEX5mpFrqXd+yEfkfpzczErcw8FA"
                + "UaWMXP47Gw8tl6FAbWuHUh5TzTYuiOa0ZJ3Nde9lr2/68s13DSULtp+nqtKa0URcR"
                + "xQBZcIFsl8vQKBgQDNco1ysZGvGAVj/N9HpLl+S8Y39I78q7wOMqGwXZ+kPSrZhVt"
                + "lgyI13AJIWio2GvMWQ2izEhxmHUI0G51g6OhlOCDrDnJZD3t/Sq8cBsasfgF6M8UI"
                + "5Yep8aDJ9iSDO1b9OQ+PlGGzhPfkpnCQNJpV4WAZ8E+lx8/6BS/nJNr+5wKBgGA3K"
                + "2sjFQ1AG25wEKHn0SR7rMxBBLjAhLX3C4fKKqiykNSEUmtxqIiLe/n+NyBzXLrEYa"
                + "wbGHYEdwv2FU0Utp3J4UmVzspVwT/X69ckVndP/aH9mWpo2pC1oscEYV08h3Lfb44"
                + "B/I5H/wKhVtTO0mkc/hkb0fBvI0PjFB98R6F1AoGBAMsWQdzuyyRainmcrtsTaon+"
                + "PaNyjSYYXX1rb4n4AqdID+OWgBKI3nh/U4JSHwf8++82E3lwncITii4YXio+Efx8z"
                + "42Maly/gUUDfrYlvJ8msC8sncl9m+3SfS8nslBHiGYo0ckM1Mew2ZudLAhg92+Nqo"
                + "H5m8I1EtID7qZ/ODxhAoGBAIcf/IMADuxiCtIs6/MqXJIN7i/fSAR6+mVXzgmu7tE"
                + "759oVqLRiB6m0r7TYPeAtJ958fujjT2uPm8AGHs3xBe+75aLLmGVSl01C4fgX81JD"
                + "DMvGiKacYbLc8nOr6ZEiNfKdTK+0UjbpFOE4CS6d9l/BUUnhjLoNX5jhXVsfNel4";
        String APP_ID = "10000000000004097556";
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致


        MybankCardbusinessServiceMerchantqueryRequestV1 request = new MybankCardbusinessServiceMerchantqueryRequestV1();

        request.setServiceUrl("http://ip:port:16257/api/mybank/cardbusiness/shop/queryElcMerInfo/V1");
        MybankCardbusinessServiceMerchantqueryRequestV1.MybankCardbusinessServiceMerchantqueryRequestV1Biz bizRes = new MybankCardbusinessServiceMerchantqueryRequestV1.MybankCardbusinessServiceMerchantqueryRequestV1Biz();

        Map<String, Object> bwg_ci16070_comm = new HashMap<>();

        bizRes.setSerid(10L);
        bizRes.setSerialno("x");

        request.setBizContent(bizRes);

        MybankCardbusinessServiceMerchantqueryResponseV1 response;

        try {
            response = client.execute(request, "msgId");
            // 判断调用是否成功，进行后续业务处理
            if (response.isSuccess()) {
                // TODO 业务成功处理
                System.out.println(" success:" + response.getReturn_code() + response.getReturn_msg());
            } else {
                // TODO 失败
                System.out.println(" fail:" + response.getReturn_code() + response.getReturn_msg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
