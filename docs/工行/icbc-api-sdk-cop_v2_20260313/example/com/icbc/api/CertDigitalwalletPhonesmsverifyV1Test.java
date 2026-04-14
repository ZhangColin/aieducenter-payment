package com.icbc.api;/*
 * @ClaaName CertDigitalwalletPhonesmsverifyV1Test.java
 * @author admin
 * @Description TODO
 * @createTime 15:39年01月15日
 */

import com.icbc.api.request.CertDigitalwalletPhonesmsverifyRequestV1;
import com.icbc.api.response.CertDigitalwalletPhonesmsverifyResponseV1;
import org.junit.Assert;

import java.util.Random;

public class CertDigitalwalletPhonesmsverifyV1Test {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static final String APP_ID = "0200EH0013035";

    public void test_phonesmsverify() {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CertDigitalwalletPhonesmsverifyRequestV1 req = new CertDigitalwalletPhonesmsverifyRequestV1();

        req.setServiceUrl("http://ip:port/api/cert/digitalwallet/phonesmsverify/V1");

        CertDigitalwalletPhonesmsverifyRequestV1.CertDigitalwalletPhonesmsverifyRequestBizV1 biz = new CertDigitalwalletPhonesmsverifyRequestV1.CertDigitalwalletPhonesmsverifyRequestBizV1();


        biz.setCooperator_no("1234567890");
        biz.setAgent_cooperator_no("1122334455");
        biz.setSerial_no("002000000201234567891200001");
        biz.setRelated_serial_no("");
        biz.setOriginal_serial_no("");
        biz.setWork_date("2020-12-12");
        biz.setWork_time("12:12:12");
        biz.setMac("00:AA:00:3F:89:4A");
        biz.setIP("2a31:12a2:bd33:12a3:12f5:12e6:1a3b:1238");
        biz.setOperate_type("99");
        biz.setWallet_id("0022501592860010");

        req.setBizContent(biz);
        CertDigitalwalletPhonesmsverifyResponseV1 response = new CertDigitalwalletPhonesmsverifyResponseV1();

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";

        try {
            response = client.execute(req, msgId);
            if (response.isSuccess()) {
                // 业务成功处理
                Assert.assertEquals(response.getResult_code(), 0);
            } else {
                Assert.assertEquals(response.getResult_code(), 1);
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
