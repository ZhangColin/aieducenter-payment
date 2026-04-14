package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bRmbforexpriceexposeQuerypriceRequestV1;
import com.icbc.api.response.GyjrB2bRmbforexpriceexposeQuerypriceResponseV1;

import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bRmbforexpriceexposeQuerypriceRequestTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDqcOarLGi7/Z8w2xjqRBhFqrEp2toT338Uk2hvEzJWDT4g1660VEFJzzRMDn89ITVZViZX4NMIEYX8srlEUcSNGXE2Qn6c3qTllJ/qCaHXjDUqHkJrOFEVj5p5RWXR0IcAosM2PBRc/WvRwnYT03b+pw4mBTLF01OG2S4yHl61he+nZLHtam2bSx+y0YcdP+QDDB899wy80EfijuOrZvwpbVbCbpVzLbWx68FhGF92+RtW3U/1/JTeREf9UfAyMAuTwEEV2DQl9A98GZijtUV6CjdmKs5C06NMKhxqY6wJoP546uvCmyiEP5PTrMYHbNSHUICI5aSCKgsfkwKisjqPAgMBAAECggEAG8uK7Zx0RmWJbCdtysiMACZ+nrG9zUFgNbZMWW7zNtSXGxm3MtYSb4fLcYD+gObhBlh6b0zOeFRriExAPuNa5Eex7nbvv/V9o2J9myfNMlsDKp0vzrp8kovWCq65paiNePvTHSj+WRAKqnHdwff+Ua7ItKVFIcMuUfyJx5NmMysV57MnxQlQ9rXY6cYfCmwF3Z1fPkCtOent42Y7rGz6o8RrqJmIa9IAl754VSvGTLWEH7E749d/0EqMlIFD/qVgJ7qJnthPcIqBCoV2we2caIlENWk/of0/suqfF1LMqnF3UxtYHucQ6Kg5nCMwZftwq3d0yKWz9xn6OEqnEJTDYQKBgQD42TtQRm9dcjF7VYQi4Vkk+WyMEUOQMQnspjYTrzyjd2r5wfHHHCjrjv+I70EHOWQGE5AFqC+NbV3aE7kUwqAmPq37581E28mEGnO8FHoMcGRc8moWliiuxfPeu1XCHis7TGQzaUybvOzEwxWypcq3wRi4TmKyrcA9jV+HGiHP9wKBgQDxLax2iNTHR8e/ZMe7EKO42v7DnpHEBaQ6xZnhfcIh2Lhh+ey6IbI10PscMBMxvgRJFP/P5waQ+DxC2UKml2ISoZ4vhVrr1cxlIP0hLPh161jdMokAlDEgZ492ORtbSH+neriP0MzgNcqHwUrzL7DJDvYTvX5gtCB8Hd5ePFR0KQKBgQDjXlh7eF+4meXsMq6Q0CWLSvqK4BtDXAsdVqCCS9Kz6SCtIy+uJWSeFgIVM8XOu/lQM/+bq5gHOK5P20V4WnvJdeuyBpHfIJJ3SVQFvQ/m3apV4ohcKsbxKZ0xHSm2O6p0e8/xyXxdr7kF7tmcaS9Xxe64N6ama2sC0nE+F/pc8QKBgCrk/K94Vp+63IPl4MAcjYHrMTSJl1NNzY/GZT/9pfFvIhwK31ebdp6rtH2m70MBkLGO0pCffkN6Yv1gtsH6Wq8Fn6TECw+2zsuNuneUD/ypBv0QZ916feJNiO516a+qjUm0Qe8SK94UsxjCABqQeNjJmGqaYUfBwaVRiqMoB71BAoGBAPRn65eo8GV6E1YQsGp8EAdBHQf6RuZ+RUak6Rp7jPnvt5UtXt+87u+vHjp0QWiG/MmxOxl0iDvhkryLLEAX2yGCZy4lsD7QciiFoWgF/fBRvdbH1Ag1kdqLiXmUWz1bbyWLxkH1H0fHWPU/tXOHLb0q6NZ1sXUeKLPMX8KvF+vC";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16" +
            "bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL" +
            "T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP" +
            "3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000004098067";

    static Logger log = Logger.getLogger(GyjrB2bRmbforexpriceexposeQuerypriceRequestTestV1.class.getName());
    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        GyjrB2bRmbforexpriceexposeQuerypriceRequestV1 req = new GyjrB2bRmbforexpriceexposeQuerypriceRequestV1();

        req.setServiceUrl("http://ip:port/api/gyjr/b2b/rmbforexpriceexpose/queryprice/V1");

        GyjrB2bRmbforexpriceexposeQuerypriceRequestV1.GyjrB2bRmbforexpriceexposeQuerypriceRequestV1Biz bizContent = new GyjrB2bRmbforexpriceexposeQuerypriceRequestV1.GyjrB2bRmbforexpriceexposeQuerypriceRequestV1Biz();

        GyjrB2bRmbforexpriceexposeQuerypriceRequestV1.TransinfoBiz transInfo = new GyjrB2bRmbforexpriceexposeQuerypriceRequestV1.TransinfoBiz();

        transInfo.setSerialNo("64854126126589");
        transInfo.setAppId("451221524242");
        transInfo.setProtocolCode("GYJR20211204102832000000002");
        transInfo.setCurrType1("14");
        bizContent.setTransInfoBiz(transInfo);
        req.setBizContent(bizContent);

        System.out.println(JSONObject.toJSONString(bizContent));

        GyjrB2bRmbforexpriceexposeQuerypriceResponseV1 response;

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        try {
            response = client.execute(req,msgId);
            log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getReturnCode():"+response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():"+response.getReturnCode());
                log.fine("response.getReturnMsg():"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
