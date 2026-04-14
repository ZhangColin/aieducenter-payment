package com.icbc.api;

import com.alibaba.fastjson.JSON;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.GyjrB2bEbankinfoQryEbankinfoRequestV1;
import com.icbc.api.response.GyjrB2bEbankinfoQryEbankinfoResponseV1;

/**
 * @author kfzx-zhengld
 * @version 1.0
 * @description TODO
 * @date 2023/3/15 9:25
 */
public class GyjrB2bEbankinfoQryEbankinfoRequestV1Test {
    protected static String APP_ID = "10000000000004098067";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDqcOarLGi7/Z8w2xjqRBhFqrEp2toT338Uk2hvEzJWDT4g1660VEFJzzRMDn89ITVZViZX4NMIEYX8srlEUcSNGXE2Qn6c3qTllJ/qCaHXjDUqHkJrOFEVj5p5RWXR0IcAosM2PBRc/WvRwnYT03b+pw4mBTLF01OG2S4yHl61he+nZLHtam2bSx+y0YcdP+QDDB899wy80EfijuOrZvwpbVbCbpVzLbWx68FhGF92+RtW3U/1/JTeREf9UfAyMAuTwEEV2DQl9A98GZijtUV6CjdmKs5C06NMKhxqY6wJoP546uvCmyiEP5PTrMYHbNSHUICI5aSCKgsfkwKisjqPAgMBAAECggEAG8uK7Zx0RmWJbCdtysiMACZ+nrG9zUFgNbZMWW7zNtSXGxm3MtYSb4fLcYD+gObhBlh6b0zOeFRriExAPuNa5Eex7nbvv/V9o2J9myfNMlsDKp0vzrp8kovWCq65paiNePvTHSj+WRAKqnHdwff+Ua7ItKVFIcMuUfyJx5NmMysV57MnxQlQ9rXY6cYfCmwF3Z1fPkCtOent42Y7rGz6o8RrqJmIa9IAl754VSvGTLWEH7E749d/0EqMlIFD/qVgJ7qJnthPcIqBCoV2we2caIlENWk/of0/suqfF1LMqnF3UxtYHucQ6Kg5nCMwZftwq3d0yKWz9xn6OEqnEJTDYQKBgQD42TtQRm9dcjF7VYQi4Vkk+WyMEUOQMQnspjYTrzyjd2r5wfHHHCjrjv+I70EHOWQGE5AFqC+NbV3aE7kUwqAmPq37581E28mEGnO8FHoMcGRc8moWliiuxfPeu1XCHis7TGQzaUybvOzEwxWypcq3wRi4TmKyrcA9jV+HGiHP9wKBgQDxLax2iNTHR8e/ZMe7EKO42v7DnpHEBaQ6xZnhfcIh2Lhh+ey6IbI10PscMBMxvgRJFP/P5waQ+DxC2UKml2ISoZ4vhVrr1cxlIP0hLPh161jdMokAlDEgZ492ORtbSH+neriP0MzgNcqHwUrzL7DJDvYTvX5gtCB8Hd5ePFR0KQKBgQDjXlh7eF+4meXsMq6Q0CWLSvqK4BtDXAsdVqCCS9Kz6SCtIy+uJWSeFgIVM8XOu/lQM/+bq5gHOK5P20V4WnvJdeuyBpHfIJJ3SVQFvQ/m3apV4ohcKsbxKZ0xHSm2O6p0e8/xyXxdr7kF7tmcaS9Xxe64N6ama2sC0nE+F/pc8QKBgCrk/K94Vp+63IPl4MAcjYHrMTSJl1NNzY/GZT/9pfFvIhwK31ebdp6rtH2m70MBkLGO0pCffkN6Yv1gtsH6Wq8Fn6TECw+2zsuNuneUD/ypBv0QZ916feJNiO516a+qjUm0Qe8SK94UsxjCABqQeNjJmGqaYUfBwaVRiqMoB71BAoGBAPRn65eo8GV6E1YQsGp8EAdBHQf6RuZ+RUak6Rp7jPnvt5UtXt+87u+vHjp0QWiG/MmxOxl0iDvhkryLLEAX2yGCZy4lsD7QciiFoWgF/fBRvdbH1Ag1kdqLiXmUWz1bbyWLxkH1H0fHWPU/tXOHLb0q6NZ1sXUeKLPMX8KvF+vC";
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        try {
            GyjrB2bEbankinfoQryEbankinfoRequestV1 request = new GyjrB2bEbankinfoQryEbankinfoRequestV1();
            request.setServiceUrl("http://ip:port/api/gyjr/b2b/ebankinfo/qryebankinfo/V1");
            GyjrB2bEbankinfoQryEbankinfoRequestV1.GyjrQryEbankInfoRequestV1Biz bizcontent = new GyjrB2bEbankinfoQryEbankinfoRequestV1.GyjrQryEbankInfoRequestV1Biz();
            GyjrB2bEbankinfoQryEbankinfoRequestV1.GyjrQryEbankInfoRequestV1Biz.TransInfo transInfo = new GyjrB2bEbankinfoQryEbankinfoRequestV1.GyjrQryEbankInfoRequestV1Biz.TransInfo();
            transInfo.setProtocolCode("GYJR20211204102832000000001");
            transInfo.setAppId(APP_ID);
            transInfo.setCreditNo("91511921MA6766QGX8");
            transInfo.setCreditType("101");
            bizcontent.setTransInfo(transInfo);
            request.setBizContent(bizcontent );
            System.out.println("入参：" + JSON.toJSONString(request));
            GyjrB2bEbankinfoQryEbankinfoResponseV1 response = new GyjrB2bEbankinfoQryEbankinfoResponseV1();
            response =  client.execute(request);
            System.out.println("出参：" + JSON.toJSONString(response));
            if (response.isSuccess()) {
                System.out.println("success");// 成功
            } else {
                System.out.println("error");// 失败
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
