package com.icbc.api;

import com.icbc.api.request.EcspScenicPosTicketQryRequestV1;
import com.icbc.api.request.EcspScenicPosTicketQryRequestV1.EcspScenicPosTicketQryRequestV1Biz;
import com.icbc.api.response.EcspScenicPosTicketQryResponseV1;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kfzx-pengwl
 * @version V1.0
 * @Description
 * @date 2020/7/24 14:58
 */
public class EcspScenicPosTicketQryV1Test {
  public static final String log =
      "2020-01-20 10:23:42.258 [main] INFO c.i.e.c.l.EcspLog AES: " + "EzYswB5ZwrJY/6EdiPqMwmLnnV7epV4gRfft0eYA+e4=\n"
      + "2020-01-20 10:23:42.270 [main] INFO c.i.e.c.l.EcspLog RSA pub: "
      + "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAk2ptfh7Y6JjPKoMCLQ90jMYFoxNFL"
      + "/bXYWAefp8ql+eEF+JKl1Nlo8KU6IeXcXX+I9pYLHqz4L7UwP9xlYNxr"
      + "+c7eShp1aTRHaXIlw7gAE6kVZISIJtX4JvEtP82cDX27VXxneNe2mCZrNXNnvskMn9xNSmNxvAhT"
      + "+HDeg73dh/l+Zci7Cqe9KwvwB+CU5TXBepKDuWa53w1A5SnwZXPaNuKxcon"
      + "+pNJH5BX2BXTU9ehCNeNPnkmPaYIj0bTtVjhw0Dhbz1dVOAcwu5PqbaxnAHrC1rLQSE3wA2JjfDu3WOJK"
      + "+VYaFTSGYnoh+/wp/5l68tKCyukOsDjdpnt7iWxZwIDAQAB\n"
      + "2020-01-20 10:23:42.270 [main] INFO c.i.e.c.l.EcspLog RSA pri: "
      + "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTam1"
      + "+HtjomM8qgwItD3SMxgWjE0Uv9tdhYB5+nyqX54QX4kqXU2WjwpToh5dxdf4j2lgserPgvtTA"
      + "/3GVg3Gv5zt5KGnVpNEdpciXDuAATqRVkhIgm1fgm8S0/zZwNfbtVfGd417aYJms1c2e"
      + "+yQyf3E1KY3G8CFP4cN6Dvd2H+X5lyLsKp70rC" + "/AH4JTlNcF6koO5ZrnfDUDlKfBlc9o24rFyif6k0kfkFfYFdNT16EI140"
      + "+eSY9pgiPRtO1WOHDQOFvPV1U4BzC7k+ptrGcAesLWstBITfADYmN8O7dY4kr5VhoVNIZieiH7/Cn"
      + "/mXry0oLK6Q6wON2me3uJbFnAgMBAAECggEAKvCraDtwD4DDh6yVq65+0qqz5aatVqVeY5zuBRA0Bf"
      + "+IOLTP6Xh/eM4rNfiZIpGuL59gY8RyNRshOf6uhVC+gvCmIEqnWXYi+7HY1jedBDnkIJDl+t2FIYX"
      + "+LILSxqjRfpy0PruGW7Ib0X6N4KtjBdqdjVDHXZWA/pXSrPFDIAMFCAV"
      + "/9OLO8cBKJiUedg9Z7cIIj0TY5vDw9NQdcq7w1VyjDVn9dHieLKu4VedrmbLmMthD9ohsDB1" + "/RHxquEE+r3b5FJ+lMK718FefMHmSqp"
      + "/4VadLrRceLn7iXcAJJIkOXH7Mv3OLML3mrz2UBczjCXZKcU7LrhzvK0Id5MppgQKBgQDUabp46" + "+YUdRseucevfJlPENnwRQCzdvYhuE"
      + "+fVZ7tonlsZVLeEElG3DvE0jcftTv3UoYqoECZwi1MNeW7vu1rlNUox/WwJdIxjar"
      + "+5aFNlfcs8OXojIiYuUfVaphpOWlg05OzjpW9wTJMPeptVV4sjDnJ3At5jvNjSixbO"
      + "+HFIQKBgQCxqlOV7yxFWRb7UkEgHsGZbePTIhZbwxsJbV7pKlRwDX22Ep6R11N"
      + "+nJAg9gyZDWxF1jN4QFg3PXgZryDKA4IxUvRj7vYC1/CfZXcIByalz6srPYQFbr7StuMWUZr/Zau"
      + "/8II5x8y48AX6JRz9mt4dO+7j7ytdRNTOMPhhLIIdhwKBgHog5C88awheIJ608IQMntUf"
      + "+j60XefRwUi4AUe6Yet93FSfmVrGgGs2m0LaHwmjGXxlPXDqvNnrFEJaRuGAsF"
      + "+HrluJRNDnnggRzTxLaUgDrp9vTrE7hBpThWLGrCTr9pPOcoDtvVjh6Pp/Kvd7"
      + "+u6gHWXbl9lngLcY4ZH/RxshAoGBAJXlvUwdVV7VQdydk7EEAhfvbW7vramSPbeAfbmAK2m3GD"
      + "+MChnGcP28HqaQsiRX/OgXR6nHsCY7SJ9y8NlAiJIRCX1fWlUf2DbEJt+xGNyBEEwVRNRIkdu6znJAO"
      + "+QL5hK1W0pg1suaR5C7y2/1QD2uGWpaml3A" + "+0NUcdua79T1AoGASV6DDFKOov0edVPlZs4sPez6eflgcnEknhjL5MI1/u9vhCmg"
      + "/uHg8UV03t6UlRENx2QtDYEee5lbNrytC0G/QIG0pZwj"
      + "/8nGymE1u6oXYNN9zUGEiAnNjbWNn3Mwu2bYpVtBdaz9DBSQqh5wfit/uw6K9nsfo0w7Fan2wWLNDe4=\n";
  // 1、网关公钥
  protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                                   + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                                   + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                                   + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

  // 2、appid
  protected static final String APP_ID = "appid";

  // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
  protected static final String MY_PRIVATE_KEY =
      "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTam1" + "+HtjomM8qgwItD3SMxgWjE0Uv9tdhYB5"
      + "+nyqX54QX4kqXU2WjwpToh5dxdf4j2lgserPgvtTA" + "/3GVg3Gv5zt5KGnVpNEdpciXDuAATqRVkhIgm1fgm8S0"
      + "/zZwNfbtVfGd417aYJms1c2e" + "+yQyf3E1KY3G8CFP4cN6Dvd2H+X5lyLsKp70rC"
      + "/AH4JTlNcF6koO5ZrnfDUDlKfBlc9o24rFyif6k0kfkFfYFdNT16EI140" + "+eSY9pgiPRtO1WOHDQOFvPV1U4BzC7k"
      + "+ptrGcAesLWstBITfADYmN8O7dY4kr5VhoVNIZieiH7/Cn" + "/mXry0oLK6Q6wON2me3uJbFnAgMBAAECggEAKvCraDtwD4DDh6yVq65"
      + "+0qqz5aatVqVeY5zuBRA0Bf" + "+IOLTP6Xh/eM4rNfiZIpGuL59gY8RyNRshOf6uhVC+gvCmIEqnWXYi" + "+7HY1jedBDnkIJDl+t2FIYX"
      + "+LILSxqjRfpy0PruGW7Ib0X6N4KtjBdqdjVDHXZWA/pXSrPFDIAMFCAV"
      + "/9OLO8cBKJiUedg9Z7cIIj0TY5vDw9NQdcq7w1VyjDVn9dHieLKu4VedrmbLmMthD9ohsDB1" + "/RHxquEE+r3b5FJ+lMK718FefMHmSqp"
      + "/4VadLrRceLn7iXcAJJIkOXH7Mv3OLML3mrz2UBczjCXZKcU7LrhzvK0Id5MppgQKBgQDUabp46" + "+YUdRseucevfJlPENnwRQCzdvYhuE"
      + "+fVZ7tonlsZVLeEElG3DvE0jcftTv3UoYqoECZwi1MNeW7vu1rlNUox/WwJdIxjar"
      + "+5aFNlfcs8OXojIiYuUfVaphpOWlg05OzjpW9wTJMPeptVV4sjDnJ3At5jvNjSixbO"
      + "+HFIQKBgQCxqlOV7yxFWRb7UkEgHsGZbePTIhZbwxsJbV7pKlRwDX22Ep6R11N" + "+nJAg9gyZDWxF1jN4QFg3PXgZryDKA4IxUvRj7vYC1"
      + "/CfZXcIByalz6srPYQFbr7StuMWUZr/Zau" + "/8II5x8y48AX6JRz9mt4dO"
      + "+7j7ytdRNTOMPhhLIIdhwKBgHog5C88awheIJ608IQMntUf"
      + "+j60XefRwUi4AUe6Yet93FSfmVrGgGs2m0LaHwmjGXxlPXDqvNnrFEJaRuGAsF"
      + "+HrluJRNDnnggRzTxLaUgDrp9vTrE7hBpThWLGrCTr9pPOcoDtvVjh6Pp/Kvd7" + "+u6gHWXbl9lngLcY4ZH"
      + "/RxshAoGBAJXlvUwdVV7VQdydk7EEAhfvbW7vramSPbeAfbmAK2m3GD"
      + "+MChnGcP28HqaQsiRX/OgXR6nHsCY7SJ9y8NlAiJIRCX1fWlUf2DbEJt" + "+xGNyBEEwVRNRIkdu6znJAO"
      + "+QL5hK1W0pg1suaR5C7y2/1QD2uGWpaml3A" + "+0NUcdua79T1AoGASV6DDFKOov0edVPlZs4sPez6eflgcnEknhjL5MI1/u9vhCmg"
      + "/uHg8UV03t6UlRENx2QtDYEee5lbNrytC0G/QIG0pZwj" + "/8nGymE1u6oXYNN9zUGEiAnNjbWNn3Mwu2bYpVtBdaz9DBSQqh5wfit"
      + "/uw6K9nsfo0w7Fan2wWLNDe4=";


  public static void main(String[] args) throws Exception {

    // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                                                     APIGW_PUBLIC_KEY);

    EcspScenicPosTicketQryRequestV1 request = new EcspScenicPosTicketQryRequestV1();
    // 4、根据测试环境和生产环境替换相应ip和端口
    request.setServiceUrl("http://appid/api/ecsp/scenic/posticekt/query/V1");

    // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
    EcspScenicPosTicketQryRequestV1Biz bizContent = new EcspScenicPosTicketQryRequestV1Biz();
    bizContent.setCorpId("0000000272").setTimeStamp("2019-05-30 15:03:00").setProtocolType("002")
              .setClientTransNo("1").setUpData("1")
              .setOtherData("0000000005");
    request.setBizContent(bizContent);

    EcspScenicPosTicketQryResponseV1 response;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
      String msgId = dateFormat.format(new Date());
      response = client.execute(request, msgId);
      if (response.isSuccess()) {
        // 业务成功处理
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }

  }
}
