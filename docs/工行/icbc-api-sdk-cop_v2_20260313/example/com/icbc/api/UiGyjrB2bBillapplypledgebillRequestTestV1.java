package example.com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UiGyjrB2bBillapplypledgebillRequestTestV1 {

    protected static final String APP_ID = "11000000000000001051";
    // 应用私钥(变量值需替换)
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCM/ZhuMjMdwnF+RrU/swtYRKLS18APAtFd4wOEsTYcJZPlofYh0m5h/KpPVDhDjc+h3HqFdqCRW14lhw51eaB9z6Or9XYCsErOqMJCzTXSwkCLLJGwZDZ+6xbc2kTK73F8E93u17EWNeL5+cK53ry3x+oB40cfEzl16yGUVExyr7RwFBqBiCkV56Rj3+L3fS9Vtidw51/pwgO2kHgMbz0Ayywuj5ys+Q0UN0oRLEperNO1EIC0sE5uEzJKTmV0T3ZdqYt0fF8d3N41Xs7HYp8dYedLChArgeymF00UJLhKiUR0eH1xQevxFH7xDMS+Mc6WmPywNNPqpvMvtPRe7XYHAgMBAAECggEBAINW/jexZfLUHxvOTKwBY5EfpzqYp56UNmuqva1P1CNWUjc5sGsDd7aqRBFQWHV91q1nEW/g+WVPYx28eZiv6/Unh8X6DKwmhBmOFiFav0gNk0jTBQj34dhLsemdHzaY5fP7IeSMY7hYDJBnvaGev9jIT9LgYXGuW/ngR1DBUtIGN5FqaGiHDPiRXuI3KdzfxEQxZuPUXguXku//VSTA+F4A2Qg+AzPL5DZ0/AfRs5OUQr9UIXaIyxhaiK1vL87N8M0PTStAPndTwHZupZKWHzx8/olH6rtIdX2B5xSGfk/4dR+L5gTSlldWJDygmVy/21vspI8C5G1aEhouiEuFZRkCgYEA7MfTGIE5pPqGq1ypv2Foau8rmNSCrlMQzszRvKUWRCET1RRSr/IYpEzJOnV2d7ikVsNxWYihhNRp0vB5jI2nu3kI7+WNS8WlYPycGVovWLrPHqaEh6+w3uKXqU1ZBgrTboQGoZPH92ukg0BLTLxJlsEqEyBihJd1ldowLQbcjtUCgYEAmG9OVJUo5HXTNOaQGqeQbtNZIq9Zi2JprTW3cqWYaSsWnCXJMb4jdoTLci7kqvRUXfucRe0yOdXRrF88r+IMQ5iZiXQzTfexNNzh8JM4sX1jVU6fJ1SIpXHq/fMpZPUjnRq4fb7SSeurkg9kop4Q0yJzNVfRuaSW5a8MrtjWN2sCgYAWEGzYXLpdaEcCUukyf0Y2qMDcRflDe1QWoTluJcgUYyyw72rhIGMxdEJQIz/92gPeXeoDGNtMQGr4EBaThWO68YVmrXJkPC+0APYe2quMziOczJGO/00xB9ZwHVgNWxbmtoDTr6yznqE+5c/ef7EvziI4lAz6JA79/s38dCY7XQKBgQCAhjCcPluJLRMUYmYUIz4zQz2/cetlC2ggo871+ktkOPFKXM2jhDZwYo0KzCD/RxyvVUsw5kpX20F7PZ10inpesBacf34WegZOlQJnjinZ9OfA8JZeWfcQz9kbn19CgTIy72molDDIJM23TAVE21NCQaSz0ETDatPT+uENt0P+2wKBgD0ZeeVhJJwv9/uSzTG9Rw3i+AeesUhgsNH2XjpxDCQNdJd+ckErUiqQyYiI2T8aMlZc54AmnadbQHSvYIIYt8xsTs2VQ4Hil1iV9PRNc2FgO09lMspbCZY5nYP0qYITD2fixktMH08CL3HRL1zpR/qRhqIZkadM+noJP+TQ5bi7";
    // API网关公钥(变量值需替换)
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16" +
            "bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL" +
            "T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP" +
            "3CiNgg0W6O3AGqwIDAQAB";


    public void test_request() {
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8");
            UiGyjrB2bBillapplypledgebillRequestV1 request = new UiGyjrB2bBillapplypledgebillRequestV1();
            // 根据实际环境替换相应的域名
            request.setServiceUrl("https://ip:port/ui/gyjr/b2b/bill/applypledgebill/V1");
            UiGyjrB2bBillapplypledgebillRequestV1.UiGyjrB2bBillapplypledgebillRequestV1Biz biz = new UiGyjrB2bBillapplypledgebillRequestV1.UiGyjrB2bBillapplypledgebillRequestV1Biz();
            UiGyjrB2bBillapplypledgebillRequestV1.UiGyjrB2bBillapplypledgebillRequestV1Biz.PackListInfo bill = new UiGyjrB2bBillapplypledgebillRequestV1.UiGyjrB2bBillapplypledgebillRequestV1Biz.PackListInfo();
            List<UiGyjrB2bBillapplypledgebillRequestV1.UiGyjrB2bBillapplypledgebillRequestV1Biz.PackListInfo> billList = new ArrayList<>();
            billList.add(bill);
            biz.setPackList(billList);
            biz.setProtocolCode("1");
            biz.setPlatVendorId("1");
            biz.setPlatBatSerialNo("1");
            biz.setTradeName("applyissuance");
            biz.setTradeVersion("V1");
            biz.setTotalAmt("1");
            biz.setTotalCount("1");
            biz.setCallBackUrl("");
            request.setBizContent(biz);
            String s = client.buildPostForm(request);
            System.out.println(s);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
