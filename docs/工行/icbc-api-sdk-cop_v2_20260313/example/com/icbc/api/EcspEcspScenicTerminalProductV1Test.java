package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.EcspScenicTerminalProductRequestV1;
import com.icbc.api.request.EcspScenicTerminalProductRequestV1.EcspScenicTerminalProductRequestV1Biz;
import com.icbc.api.response.EcspScenicTerminalProductResponseV1;

/**
 * @author kfzx-lixm04
 * @version V1.0
 * 景区云终端销售产品基础查询
 * @Description
 * @date 2020/2/17 10:54
 */
public class EcspEcspScenicTerminalProductV1Test {
  public static void main(String[] args) {
    final String APP_ID = "app_id";
    final String URL = "http://ip:port/api/ecsp/scenic/terminal/product/base/query/V1";
    final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                    + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                    + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                    + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    final String YOUR_PRIVATE_KEY =
        "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTam1" + "+HtjomM8qgwItD3SMxgWjE0Uv9tdhYB5"
        + "+nyqX54QX4kqXU2WjwpToh5dxdf4j2lgserPgvtTA" + "/3GVg3Gv5zt5KGnVpNEdpciXDuAATqRVkhIgm1fgm8S0"
        + "/zZwNfbtVfGd417aYJms1c2e" + "+yQyf3E1KY3G8CFP4cN6Dvd2H+X5lyLsKp70rC"
        + "/AH4JTlNcF6koO5ZrnfDUDlKfBlc9o24rFyif6k0kfkFfYFdNT16EI140" + "+eSY9pgiPRtO1WOHDQOFvPV1U4BzC7k"
        + "+ptrGcAesLWstBITfADYmN8O7dY4kr5VhoVNIZieiH7/Cn" + "/mXry0oLK6Q6wON2me3uJbFnAgMBAAECggEAKvCraDtwD4DDh6yVq65"
        + "+0qqz5aatVqVeY5zuBRA0Bf" + "+IOLTP6Xh/eM4rNfiZIpGuL59gY8RyNRshOf6uhVC+gvCmIEqnWXYi"
        + "+7HY1jedBDnkIJDl+t2FIYX" + "+LILSxqjRfpy0PruGW7Ib0X6N4KtjBdqdjVDHXZWA/pXSrPFDIAMFCAV"
        + "/9OLO8cBKJiUedg9Z7cIIj0TY5vDw9NQdcq7w1VyjDVn9dHieLKu4VedrmbLmMthD9ohsDB1" + "/RHxquEE+r3b5FJ+lMK718FefMHmSqp"
        + "/4VadLrRceLn7iXcAJJIkOXH7Mv3OLML3mrz2UBczjCXZKcU7LrhzvK0Id5MppgQKBgQDUabp46"
        + "+YUdRseucevfJlPENnwRQCzdvYhuE" + "+fVZ7tonlsZVLeEElG3DvE0jcftTv3UoYqoECZwi1MNeW7vu1rlNUox/WwJdIxjar"
        + "+5aFNlfcs8OXojIiYuUfVaphpOWlg05OzjpW9wTJMPeptVV4sjDnJ3At5jvNjSixbO"
        + "+HFIQKBgQCxqlOV7yxFWRb7UkEgHsGZbePTIhZbwxsJbV7pKlRwDX22Ep6R11N"
        + "+nJAg9gyZDWxF1jN4QFg3PXgZryDKA4IxUvRj7vYC1" + "/CfZXcIByalz6srPYQFbr7StuMWUZr/Zau" + "/8II5x8y48AX6JRz9mt4dO"
        + "+7j7ytdRNTOMPhhLIIdhwKBgHog5C88awheIJ608IQMntUf"
        + "+j60XefRwUi4AUe6Yet93FSfmVrGgGs2m0LaHwmjGXxlPXDqvNnrFEJaRuGAsF"
        + "+HrluJRNDnnggRzTxLaUgDrp9vTrE7hBpThWLGrCTr9pPOcoDtvVjh6Pp/Kvd7" + "+u6gHWXbl9lngLcY4ZH"
        + "/RxshAoGBAJXlvUwdVV7VQdydk7EEAhfvbW7vramSPbeAfbmAK2m3GD"
        + "+MChnGcP28HqaQsiRX/OgXR6nHsCY7SJ9y8NlAiJIRCX1fWlUf2DbEJt" + "+xGNyBEEwVRNRIkdu6znJAO"
        + "+QL5hK1W0pg1suaR5C7y2/1QD2uGWpaml3A" + "+0NUcdua79T1AoGASV6DDFKOov0edVPlZs4sPez6eflgcnEknhjL5MI1/u9vhCmg"
        + "/uHg8UV03t6UlRENx2QtDYEee5lbNrytC0G/QIG0pZwj" + "/8nGymE1u6oXYNN9zUGEiAnNjbWNn3Mwu2bYpVtBdaz9DBSQqh5wfit"
        + "/uw6K9nsfo0w7Fan2wWLNDe4=";

    EcspScenicTerminalProductRequestV1Biz biz = new EcspScenicTerminalProductRequestV1Biz();
    biz.setCorpId("0000000272");
    biz.setCcType(1);
    biz.setTerminalNo("ECSPVanstone0100021030019");
    biz.setTerminalType(7);
    biz.setCurrentPage(1);
    biz.setPageSize(10);

    EcspScenicTerminalProductRequestV1 requestV1 = new EcspScenicTerminalProductRequestV1();
    requestV1.setBizContent(biz);
    requestV1.setServiceUrl(URL);

    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, YOUR_PRIVATE_KEY,
                                                     APIGW_PUBLIC_KEY);
    try {
      EcspScenicTerminalProductResponseV1 responseV1 = client.execute(requestV1);
      if (responseV1.isSuccess()) {
      } else {
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
