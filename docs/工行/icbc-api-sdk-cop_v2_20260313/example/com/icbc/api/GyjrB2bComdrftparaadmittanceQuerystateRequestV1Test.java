package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.GyjrB2bComdrftparaadmittanceQuerystateRequestV1;
import com.icbc.api.response.GyjrB2bComdrftparaadmittanceQuerystateResponseV1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GyjrB2bComdrftparaadmittanceQuerystateRequestV1Test {

    protected static String APP_ID = "11000000000000001051";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCM/ZhuMjMdwnF+RrU/swtYRKLS18APAtFd4wOEsTYcJZPlofYh0m5h/KpPVDhDjc+h3HqFdqCRW14lhw51eaB9z6Or9XYCsErOqMJCzTXSwkCLLJGwZDZ+6xbc2kTK73F8E93u17EWNeL5+cK53ry3x+oB40cfEzl16yGUVExyr7RwFBqBiCkV56Rj3+L3fS9Vtidw51/pwgO2kHgMbz0Ayywuj5ys+Q0UN0oRLEperNO1EIC0sE5uEzJKTmV0T3ZdqYt0fF8d3N41Xs7HYp8dYedLChArgeymF00UJLhKiUR0eH1xQevxFH7xDMS+Mc6WmPywNNPqpvMvtPRe7XYHAgMBAAECggEBAINW/jexZfLUHxvOTKwBY5EfpzqYp56UNmuqva1P1CNWUjc5sGsDd7aqRBFQWHV91q1nEW/g+WVPYx28eZiv6/Unh8X6DKwmhBmOFiFav0gNk0jTBQj34dhLsemdHzaY5fP7IeSMY7hYDJBnvaGev9jIT9LgYXGuW/ngR1DBUtIGN5FqaGiHDPiRXuI3KdzfxEQxZuPUXguXku//VSTA+F4A2Qg+AzPL5DZ0/AfRs5OUQr9UIXaIyxhaiK1vL87N8M0PTStAPndTwHZupZKWHzx8/olH6rtIdX2B5xSGfk/4dR+L5gTSlldWJDygmVy/21vspI8C5G1aEhouiEuFZRkCgYEA7MfTGIE5pPqGq1ypv2Foau8rmNSCrlMQzszRvKUWRCET1RRSr/IYpEzJOnV2d7ikVsNxWYihhNRp0vB5jI2nu3kI7+WNS8WlYPycGVovWLrPHqaEh6+w3uKXqU1ZBgrTboQGoZPH92ukg0BLTLxJlsEqEyBihJd1ldowLQbcjtUCgYEAmG9OVJUo5HXTNOaQGqeQbtNZIq9Zi2JprTW3cqWYaSsWnCXJMb4jdoTLci7kqvRUXfucRe0yOdXRrF88r+IMQ5iZiXQzTfexNNzh8JM4sX1jVU6fJ1SIpXHq/fMpZPUjnRq4fb7SSeurkg9kop4Q0yJzNVfRuaSW5a8MrtjWN2sCgYAWEGzYXLpdaEcCUukyf0Y2qMDcRflDe1QWoTluJcgUYyyw72rhIGMxdEJQIz/92gPeXeoDGNtMQGr4EBaThWO68YVmrXJkPC+0APYe2quMziOczJGO/00xB9ZwHVgNWxbmtoDTr6yznqE+5c/ef7EvziI4lAz6JA79/s38dCY7XQKBgQCAhjCcPluJLRMUYmYUIz4zQz2/cetlC2ggo871+ktkOPFKXM2jhDZwYo0KzCD/RxyvVUsw5kpX20F7PZ10inpesBacf34WegZOlQJnjinZ9OfA8JZeWfcQz9kbn19CgTIy72molDDIJM23TAVE21NCQaSz0ETDatPT+uENt0P+2wKBgD0ZeeVhJJwv9/uSzTG9Rw3i+AeesUhgsNH2XjpxDCQNdJd+ckErUiqQyYiI2T8aMlZc54AmnadbQHSvYIIYt8xsTs2VQ4Hil1iV9PRNc2FgO09lMspbCZY5nYP0qYITD2fixktMH08CL3HRL1zpR/qRhqIZkadM+noJP+TQ5bi7";
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        try {
            String nowDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
            String nowTime = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
            GyjrB2bComdrftparaadmittanceQuerystateRequestV1 request = new GyjrB2bComdrftparaadmittanceQuerystateRequestV1();
            request.setServiceUrl("http://ip:port/api/gyjr/b2b/comdrftParaAdmittance/queryState/V1");
            GyjrB2bComdrftparaadmittanceQuerystateRequestV1.GyjrB2bComdrftparaadmittanceQuerystateRequestV1Biz bizContent = new GyjrB2bComdrftparaadmittanceQuerystateRequestV1.GyjrB2bComdrftparaadmittanceQuerystateRequestV1Biz();
            GyjrB2bComdrftparaadmittanceQuerystateRequestV1.GyjrB2bComdrftparaadmittanceQuerystateRequestV1Biz.TransInfo transInfo = new  GyjrB2bComdrftparaadmittanceQuerystateRequestV1.GyjrB2bComdrftparaadmittanceQuerystateRequestV1Biz.TransInfo();
            transInfo.setSerialNo("12345678");
            transInfo.setTeller("020006145");
            transInfo.setSocialCode("0000000011068751X0");
            transInfo.setProtocolCode("GYJR20221129162630399341979");
            transInfo.setCustName("测试企业名称");
            bizContent.setTransInfo(transInfo);
            request.setBizContent(bizContent);
            System.out.println("入参：" + JSON.toJSONString(request));

            GyjrB2bComdrftparaadmittanceQuerystateResponseV1 response  = new GyjrB2bComdrftparaadmittanceQuerystateResponseV1();

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
