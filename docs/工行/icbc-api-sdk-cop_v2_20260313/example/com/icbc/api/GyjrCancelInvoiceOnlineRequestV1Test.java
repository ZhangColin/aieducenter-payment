package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.GyjrCancelInvoiceOnlineRequestV1;
import com.icbc.api.response.GyjrCancelInvoiceOnlineResponseV1;

public class GyjrCancelInvoiceOnlineRequestV1Test {
    public static void main() throws IcbcApiException {
        String apigwPublicKey = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAjP2YbjIzHcJxfka1P7MLWESi0tfADwLRXeMDhLE2HCWT5aH2IdJuYfyqT1Q4Q43Podx6hXagkVteJYcOdXmgfc+jq/V2ArBKzqjCQs010sJAiyyRsGQ2fusW3NpEyu9xfBPd7texFjXi+fnCud68t8fqAeNHHxM5deshlFRMcq+0cBQagYgpFeekY9/i930vVbYncOdf6cIDtpB4DG89AMssLo+crPkNFDdKESxKXqzTtRCAtLBObhMySk5ldE92XamLdHxfHdzeNV7Ox2KfHWHnSwoQK4HsphdNFCS4SolEdHh9cUHr8RR+8QzEvjHOlpj8sDTT6qbzL7T0Xu12BwIDAQAB"; //api网关公钥
        String privateKey = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCM/ZhuMjMdwnF+RrU/swtYRKLS18APAtFd4wOEsTYcJZPlofYh0m5h/KpPVDhDjc+h3HqFdqCRW14lhw51eaB9z6Or9XYCsErOqMJCzTXSwkCLLJGwZDZ+6xbc2kTK73F8E93u17EWNeL5+cK53ry3x+oB40cfEzl16yGUVExyr7RwFBqBiCkV56Rj3+L3fS9Vtidw51/pwgO2kHgMbz0Ayywuj5ys+Q0UN0oRLEperNO1EIC0sE5uEzJKTmV0T3ZdqYt0fF8d3N41Xs7HYp8dYedLChArgeymF00UJLhKiUR0eH1xQevxFH7xDMS+Mc6WmPywNNPqpvMvtPRe7XYHAgMBAAECggEBAINW/jexZfLUHxvOTKwBY5EfpzqYp56UNmuqva1P1CNWUjc5sGsDd7aqRBFQWHV91q1nEW/g+WVPYx28eZiv6/Unh8X6DKwmhBmOFiFav0gNk0jTBQj34dhLsemdHzaY5fP7IeSMY7hYDJBnvaGev9jIT9LgYXGuW/ngR1DBUtIGN5FqaGiHDPiRXuI3KdzfxEQxZuPUXguXku//VSTA+F4A2Qg+AzPL5DZ0/AfRs5OUQr9UIXaIyxhaiK1vL87N8M0PTStAPndTwHZupZKWHzx8/olH6rtIdX2B5xSGfk/4dR+L5gTSlldWJDygmVy/21vspI8C5G1aEhouiEuFZRkCgYEA7MfTGIE5pPqGq1ypv2Foau8rmNSCrlMQzszRvKUWRCET1RRSr/IYpEzJOnV2d7ikVsNxWYihhNRp0vB5jI2nu3kI7+WNS8WlYPycGVovWLrPHqaEh6+w3uKXqU1ZBgrTboQGoZPH92ukg0BLTLxJlsEqEyBihJd1ldowLQbcjtUCgYEAmG9OVJUo5HXTNOaQGqeQbtNZIq9Zi2JprTW3cqWYaSsWnCXJMb4jdoTLci7kqvRUXfucRe0yOdXRrF88r+IMQ5iZiXQzTfexNNzh8JM4sX1jVU6fJ1SIpXHq/fMpZPUjnRq4fb7SSeurkg9kop4Q0yJzNVfRuaSW5a8MrtjWN2sCgYAWEGzYXLpdaEcCUukyf0Y2qMDcRflDe1QWoTluJcgUYyyw72rhIGMxdEJQIz/92gPeXeoDGNtMQGr4EBaThWO68YVmrXJkPC+0APYe2quMziOczJGO/00xB9ZwHVgNWxbmtoDTr6yznqE+5c/ef7EvziI4lAz6JA79/s38dCY7XQKBgQCAhjCcPluJLRMUYmYUIz4zQz2/cetlC2ggo871+ktkOPFKXM2jhDZwYo0KzCD/RxyvVUsw5kpX20F7PZ10inpesBacf34WegZOlQJnjinZ9OfA8JZeWfcQz9kbn19CgTIy72molDDIJM23TAVE21NCQaSz0ETDatPT+uENt0P+2wKBgD0ZeeVhJJwv9/uSzTG9Rw3i+AeesUhgsNH2XjpxDCQNdJd+ckErUiqQyYiI2T8aMlZc54AmnadbQHSvYIIYt8xsTs2VQ4Hil1iV9PRNc2FgO09lMspbCZY5nYP0qYITD2fixktMH08CL3HRL1zpR/qRhqIZkadM+noJP+TQ5bi7";  //私钥
        String appId = "11000000000000001006";
        String url = "http://ip:port/api/gyjr/b2b/invoice/onlineCancel/V1";

        DefaultIcbcClient client = new DefaultIcbcClient(appId, IcbcConstants.SIGN_TYPE_RSA2, privateKey, apigwPublicKey);
        GyjrCancelInvoiceOnlineRequestV1 request = new GyjrCancelInvoiceOnlineRequestV1();
        GyjrCancelInvoiceOnlineRequestV1.RequestV1Biz bizContent = new GyjrCancelInvoiceOnlineRequestV1.RequestV1Biz();
        GyjrCancelInvoiceOnlineRequestV1.TransInfo transInfo = new GyjrCancelInvoiceOnlineRequestV1.TransInfo();
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        request.setServiceUrl(url);
        transInfo.setProtocolCode("聚融协议号");
        transInfo.setFlowId("0123456789");
        transInfo.setAccNo("特征值");
        transInfo.setAppid(appId);
        transInfo.setEvent_no("1334454555");

        GyjrCancelInvoiceOnlineResponseV1 response = client.execute(request);
        System.out.println(response.isSuccess());
        System.out.println(response.getReturnCode());
        System.out.println(response.getEventNo());
    }
}
