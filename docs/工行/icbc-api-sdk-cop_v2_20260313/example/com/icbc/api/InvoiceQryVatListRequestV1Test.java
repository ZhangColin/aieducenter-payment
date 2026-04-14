package com.icbc.api.request;

import com.alibaba.fastjson.JSON;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.IcbcResponse;
import com.icbc.api.request.InvoiceQryVatListRequestV1;
import com.icbc.api.response.InvoiceQryVatListResponseV1;
/**
 * @author kfzx-zhaoc
 * @version 1.0
 * @description TODO
 * @date 2023/1/11 9:12
 */
public class InvoiceQryVatListRequestV1Test {
    protected static String APP_ID = "11000000000000001051";
//    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCiVqi8x6oUbUNjm7/8N53L4Qz7h9FWI/l188CFs8QujWZmLJCiiqNdM0L2h48ynboLFc8U9W7Szg6LUP7HgRIiPOrk+FMMRJbpwzfeg6TMS4PXxuYmaLOq3qHaFiy51ICP3yOGf98x3be9Cd/pADgCV2DdDFXjJbQdQiBi8nOJEjkfWtA56GyfD9ZSf0MdclMX9WQDJ6ZKZ1/a+e2/tw7alBV3SVssRZzkcHehyedJWJbV/MR/yRk4AZV5gT0zcMOuSVI2nFfFdeXjaZuksM3SIpu2HFAbrQ8tW1AqvvHIYr5W5cMUPyMzC4SGor5/KpXVAUuswRcBtdnu4f4DAflTAgMBAAECggEAaccLtc5n7V1wrqPaye43XJRs0CykY3uXXZsRVE1ZRHgQQ/0/1Rl+7wiq1lZoITQS+IwiXea6apOneHGzQa3Bchu9dqqz+mdq6jCw48VliVSfOOc6KcbJY0ojriYKyJ9Fkfl0fXwuo6Bzy7J2eq0Q0b27uO0Dvblgjjv7PF38mE/+dEjFebDIE/oSqho8/kpJI3ZeaxNF56xyOpOCdRmY2ROFT6sIK8/hdC62eA3SOJDEL9UkGMSZkAUUyebavMwzDXpBT74BOttPG3fnshHXp2uPDCCqI3eXCGrgZy30OiUNMPK32IDGiRoQyiXSMZsMqq06+ytyvs6b2+P+s81dCQKBgQD9h5iI1So+BSMcL2FMCrRR03pSSAbvA7tUIsJEIw0dJmZ2ql4oUZWALi3N1j/v63EYVZr/6Pb25ynVDfzClfSOGXYRoS5sBqgMVfnLyWdB6paMackp8FBnwnHMCjQ4ZoafF0kKn3uR2bu1doMtWAVXq4PeQOJvgbhlwQYwxHImRwKBgQCj65idpTLO4FDcqq4hOeSoT45nTKfdbYp0+9wBoyl3xo5sbiMsh9OBIc7NUPQdwXxNCLW4ijo1nZqCSfv0EV3sip67dgnIRjzPPhsy8kS7+FBFFhKeUwOH5wTfNQxcfyxX/wSKlnzMPMpZ5DgSdElM7FMcjD7fdGx8visomg++lQKBgDgO8mhXEfcStbe2tkHVIBfztdlZucbBwXsSEHh2c6Dda8yTUSyDFZZ5H4Gi/RI0shYaX6J0RZ9Eb9h/NkNkWuXMtXGifRw/wIqxvAEWcHF/yHgVr/hI/c5jjTv7iZ5q9RtaT7k8mFsglMPEB69QBiZjc04CdM8JFNfS/WZtGXxjAoGAM0Ty/Qu4coAyinJP+FB1Njd74M9XCajDT4ee7lwsEixmS3+/PKRXAOKkHg+d6tijmiyJOejx/+cFdo7RYSs1Z1CMCTIIKT3esytHso2X9frakGEw/ZgVWKJnIbEj5qJYKkW3E+mxquewmqEI8KyyaH7aF+m9mIJ/lByevxpApmkCgYEAvaBoJoau0k5iOv2ZVRSGmV08ONS9g0MF9bZlXzoD71oJj5YgeTyo1+y0mtKtTSrdamq6oX4u2lyr/Zv9xllTGUEn8wPxgrrXOVRq5MtTCOKOUWlyKZ31IUS08dgV1qu54K68NSSkECyeNjMEPf5q1pa48qcKAg/qRHwhoQhLUSo=";

    // API网关公钥(变量值需替换)
//    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAjP2YbjIzHcJxfka1P7MLWESi0tfADwLRXeMDhLE2HCWT5aH2IdJuYfyqT1Q4Q43Podx6hXagkVteJYcOdXmgfc+jq/V2ArBKzqjCQs010sJAiyyRsGQ2fusW3NpEyu9xfBPd7texFjXi+fnCud68t8fqAeNHHxM5deshlFRMcq+0cBQagYgpFeekY9/i930vVbYncOdf6cIDtpB4DG89AMssLo+crPkNFDdKESxKXqzTtRCAtLBObhMySk5ldE92XamLdHxfHdzeNV7Ox2KfHWHnSwoQK4HsphdNFCS4SolEdHh9cUHr8RR+8QzEvjHOlpj8sDTT6qbzL7T0Xu12BwIDAQAB";
    protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCM/ZhuMjMdwnF+RrU/swtYRKLS18APAtFd4wOEsTYcJZPlofYh0m5h/KpPVDhDjc+h3HqFdqCRW14lhw51eaB9z6Or9XYCsErOqMJCzTXSwkCLLJGwZDZ+6xbc2kTK73F8E93u17EWNeL5+cK53ry3x+oB40cfEzl16yGUVExyr7RwFBqBiCkV56Rj3+L3fS9Vtidw51/pwgO2kHgMbz0Ayywuj5ys+Q0UN0oRLEperNO1EIC0sE5uEzJKTmV0T3ZdqYt0fF8d3N41Xs7HYp8dYedLChArgeymF00UJLhKiUR0eH1xQevxFH7xDMS+Mc6WmPywNNPqpvMvtPRe7XYHAgMBAAECggEBAINW/jexZfLUHxvOTKwBY5EfpzqYp56UNmuqva1P1CNWUjc5sGsDd7aqRBFQWHV91q1nEW/g+WVPYx28eZiv6/Unh8X6DKwmhBmOFiFav0gNk0jTBQj34dhLsemdHzaY5fP7IeSMY7hYDJBnvaGev9jIT9LgYXGuW/ngR1DBUtIGN5FqaGiHDPiRXuI3KdzfxEQxZuPUXguXku//VSTA+F4A2Qg+AzPL5DZ0/AfRs5OUQr9UIXaIyxhaiK1vL87N8M0PTStAPndTwHZupZKWHzx8/olH6rtIdX2B5xSGfk/4dR+L5gTSlldWJDygmVy/21vspI8C5G1aEhouiEuFZRkCgYEA7MfTGIE5pPqGq1ypv2Foau8rmNSCrlMQzszRvKUWRCET1RRSr/IYpEzJOnV2d7ikVsNxWYihhNRp0vB5jI2nu3kI7+WNS8WlYPycGVovWLrPHqaEh6+w3uKXqU1ZBgrTboQGoZPH92ukg0BLTLxJlsEqEyBihJd1ldowLQbcjtUCgYEAmG9OVJUo5HXTNOaQGqeQbtNZIq9Zi2JprTW3cqWYaSsWnCXJMb4jdoTLci7kqvRUXfucRe0yOdXRrF88r+IMQ5iZiXQzTfexNNzh8JM4sX1jVU6fJ1SIpXHq/fMpZPUjnRq4fb7SSeurkg9kop4Q0yJzNVfRuaSW5a8MrtjWN2sCgYAWEGzYXLpdaEcCUukyf0Y2qMDcRflDe1QWoTluJcgUYyyw72rhIGMxdEJQIz/92gPeXeoDGNtMQGr4EBaThWO68YVmrXJkPC+0APYe2quMziOczJGO/00xB9ZwHVgNWxbmtoDTr6yznqE+5c/ef7EvziI4lAz6JA79/s38dCY7XQKBgQCAhjCcPluJLRMUYmYUIz4zQz2/cetlC2ggo871+ktkOPFKXM2jhDZwYo0KzCD/RxyvVUsw5kpX20F7PZ10inpesBacf34WegZOlQJnjinZ9OfA8JZeWfcQz9kbn19CgTIy72molDDIJM23TAVE21NCQaSz0ETDatPT+uENt0P+2wKBgD0ZeeVhJJwv9/uSzTG9Rw3i+AeesUhgsNH2XjpxDCQNdJd+ckErUiqQyYiI2T8aMlZc54AmnadbQHSvYIIYt8xsTs2VQ4Hil1iV9PRNc2FgO09lMspbCZY5nYP0qYITD2fixktMH08CL3HRL1zpR/qRhqIZkadM+noJP+TQ5bi7";
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        try {
            InvoiceQryVatListRequestV1 request = new InvoiceQryVatListRequestV1();
            request.setServiceUrl("http://ip:port/api/gyjr/b2b/invoice/qryVatInvoiceList/V1");
            InvoiceQryVatListRequestV1.InvoiceQryVatListRequestV1Biz bizContent = new InvoiceQryVatListRequestV1.InvoiceQryVatListRequestV1Biz();
            InvoiceQryVatListRequestV1.InvoiceQryVatListRequestV1Biz.TransInfo transInfo = new  InvoiceQryVatListRequestV1.InvoiceQryVatListRequestV1Biz.TransInfo();
            transInfo.setInit_flag("1");
            transInfo.setRow_req("10");
            transInfo.setProtocol_code("GYJR20220415100404714699148");
            transInfo.setAppid(APP_ID);
            transInfo.setChan_serial_no("aweilfj39104710uej13412");
            transInfo.setPrt_flag("1");
            transInfo.setStart_date("2022-10-10");
            transInfo.setEnd_date("2022-12-10");
            transInfo.setAccno("0200062014212394556");
            transInfo.setAcc_type("001");
            transInfo.setZone_no("00200");
            transInfo.setEnd_date("2022-12-10");
            transInfo.setInvpt_pid("awerq235245");
            bizContent.setTransInfo(transInfo);
            request.setBizContent(bizContent);
            System.out.println("入参：" + JSON.toJSONString(request));
            InvoiceQryVatListResponseV1 response =  client.execute(request);
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

