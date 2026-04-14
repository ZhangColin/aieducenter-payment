package com.icbc.api.crypt;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.EcspReligionSendAlarmInfoRequestV1;
import com.icbc.api.request.EcspReligionSendAlarmInfoRequestV1.EcspReligionSendAlarmInfoRequestV1Biz;
import com.icbc.api.response.EcspReligionSendAlarmInfoResponseV1;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kfzx-pengwl
 * @version V1.0
 * @Description
 * @date 2019/11/25 9:09
 */
public class EcspReligonAlarmInfoTestV1 {
  // 1、网关公钥
  protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                                   + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                                   + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                                   + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

  // 2、appid
  protected static final String APP_ID = "*****";

  // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
  protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQ0"
                                                 + "/AmpTdCL8JRTvh9WLYeY0hE645nr7qE+hNCxlebfQWZ9ckcuhHhUx3HeJyanY6"
                                                 + "+7nJWhQQlcoSbcch5ZrqiVPDR2mfpw4/ac6JaAxv6eliaRUgRKIyD2Yi7GKQJ/91"
                                                 + "+US"
                                                 +
                                                 "+wxv06sWuruDcWZ32jK9Q1bR3Cjkghqlovm0tqK68iPQCRSSZsMHJttasjisejGjUEFy80z2OqJlCcVZYJ2D3nTpSu"
                                                 + "/j4M+vg7E0J6vYjpsxwMH+BvWDgG1vPcrBDPQUtEewPZsBzzq4sjL3DXs"
                                                 + "+a5hhJxe6nh4GVi9wk2jApARN6NNX0g6bc8rIgR6K" + "M+8EdXMrqwZDH9VEcCj"
                                                 + "/ndAgMBAAECggEAXnnjL1KXuZVuLZxWfW5hX3Fks5D2VK1N"
                                                 + "/3EjH2xDuirUfJrZluPKxjJyOq1ze2lMtUDXVZ" + "lwGv2Zg821"
                                                 + "+XwKQ9slPcA8CUKJlPfZ8N4WYk9tfTW0zXymLVy61VI9sEg5NS3A7pDgoGkc0"
                                                 + "+hYB0V7r/XnePK1dzVZ2JxIOMYVbCwSx+u9"
                                                 +
                                                 "yWGLUETzjRr6LEzxzbiIJtxleU4wBhXLcCBmuStPhLl7gY8cbzDnu2Hw3vPUs170vcmi+y/X244lBvOaL1MMZcAXhQs9qoXF5pSUa4slZXymt"
                                                 + "A7VBMrnfP3B2VhZqBRCFdWfiyQvIpoOpBYqJTB"
                                                 +
                                                 "/pa3YI5rD4SjjvK3gAQKBgQDrVz62veQDT5hMUsKA7OqASB93dxwb5Zl5U1IK35WG642YxmB"
                                                 + "O6kaNO3Y3hoVy/IJdGyy+3CbCvEK779A3J3uEvdINVRFKjoyM"
                                                 + "/r2QajoZYj2eRYmZWL7rFDDJzRo1zeqnTuHhw+5qdUMxrPXa/X2BZAPi18tSrgL"
                                                 + "DAp4jjMNtaQKBgQCdip5yIWMY2W5agAlZTrooiXr"
                                                 +
                                                 "+IRLDFWqN28bBGmFDd9w4xOabQlrEwbnijEGqUGgVWC366FFyXYKXnVBQgnSQdTAItXhNkvq"
                                                 + "KRQE7"
                                                 +
                                                 "/WYKz7dUzv3u10RbC5QiDZd3rXSgQflYgp7lotBUFWgzmxim3XJNwQw1s4NOC7BcVkS2VQKBgQDejaro9bpixyqnQrxdX66DkbDwMNuMJhx0965mSfoFlUxaZX8+IZ6GgOzSpYwdqV0+3yv5cgfVsGMLh0XIYrYUWc4CfafUcenIjD6IC2zWBGlw+yGcuqgRdw6MSEG5D/oQLjC+Z6NXWX0pxP7bEU1Wx1ZFdvz4O0axVBBoXf2RwQKBgBndkHYclTCXQk8Jhn9rNZVCPTdMJtBwy7XOx6scsatqGnYXneUP1+dGB0by0DUuTmhyudngGr80KyTJZ05QnOK62tATO1VBNHFPMwyuZfqQcAE0muyD2203UXLl2GCF0ggdzNqDV6NMP/gOK7WNWVbMNOAfOen3Ny+xK5t9bnqhAoGAQ3PnPhSFYprxSvYNjzvgi1L/72QqsHQ896uak6GG04UPm9WiQ24H4PKVwFebl1fqmWgH4w/7ih2Dx8WuxTupEYgytcZ/eeM0zzjMxZec0ZVz5cGIIOQD/agTDFi1Uwul+z6LKbJr6d+NF3LZbz4MnRRgomSVlNLBeAyi4o9DXFg=";
  public static void main(String[] args) throws Exception {

    // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                                                     APIGW_PUBLIC_KEY);

    EcspReligionSendAlarmInfoRequestV1 request = new EcspReligionSendAlarmInfoRequestV1();
    // 4、根据测试环境和生产环境替换相应ip和端口
    request.setServiceUrl("http://****/api/ecsp/religion/alarm/send/V1");

    // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
    EcspReligionSendAlarmInfoRequestV1Biz bizContent = new EcspReligionSendAlarmInfoRequestV1Biz();
    bizContent.setCategory("1").setCheckCode("2").setCorpId("3").setData("4").setDataType("5").setDeviceSN("6")
              .setStrTESN("1").setTimeStamp("1");
    request.setBizContent(bizContent);

    EcspReligionSendAlarmInfoResponseV1 response;
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

  public static final String key =
      "2019-11-25 18:59:27.522 [main] INFO c.i.e.c.l.EcspLog AES: JsP3vaCgYUOsl8w6KSbE4/CSZOgPuCqUUItydJPmGTY=\n"
      + "2019-11-25 18:59:27.542 [main] INFO c.i.e.c.l.EcspLog RSA pub: "
      + "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAkNPwJqU3Qi/CUU74fVi2HmNIROuOZ6"
      + "+6hPoTQsZXm30FmfXJHLoR4VMdx3icmp2Ovu5yVoUEJXKEm3HIeWa6olTw0dpn6cOP2nOiWgMb+npYmkVIESiMg9mIuxikCf"
      + "/dflEvsMb9OrFrq7g3Fmd9oyvUNW0dwo5IIapaL5tLaiuvIj0AkUkmbDBybbWrI4rHoxo1BBcvNM9jqiZQnFWWCdg9506Urv4"
      + "+DPr4OxNCer2I6bMcDB"
      + "/gb1g4Btbz3KwQz0FLRHsD2bAc86uLIy9w17PmuYYScXup4eBlYvcJNowKQETejTV9IOm3PKyIEeijPvBHVzK6sGQx/VRHAo/53QIDAQAB\n"
      + "2019-11-25 18:59:27.542 [main] INFO c.i.e.c.l.EcspLog RSA pri: "
      + "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQ0/AmpTdCL8JRTvh9WLYeY0hE645nr7qE"
      + "+hNCxlebfQWZ9ckcuhHhUx3HeJyanY6+7nJWhQQlcoSbcch5ZrqiVPDR2mfpw4/ac6JaAxv6eliaRUgRKIyD2Yi7GKQJ/91+US"
      + "+wxv06sWuruDcWZ32jK9Q1bR3Cjkghqlovm0tqK68iPQCRSSZsMHJttasjisejGjUEFy80z2OqJlCcVZYJ2D3nTpSu/j4M"
      + "+vg7E0J6vYjpsxwMH+BvWDgG1vPcrBDPQUtEewPZsBzzq4sjL3DXs+a5hhJxe6nh4GVi9wk2jApARN6NNX0g6bc8rIgR6KM"
      + "+8EdXMrqwZDH9VEcCj/ndAgMBAAECggEAXnnjL1KXuZVuLZxWfW5hX3Fks5D2VK1N"
      + "/3EjH2xDuirUfJrZluPKxjJyOq1ze2lMtUDXVZlwGv2Zg821"
      + "+XwKQ9slPcA8CUKJlPfZ8N4WYk9tfTW0zXymLVy61VI9sEg5NS3A7pDgoGkc0+hYB0V7r/XnePK1dzVZ2JxIOMYVbCwSx"
      + "+u9yWGLUETzjRr6LEzxzbiIJtxleU4wBhXLcCBmuStPhLl7gY8cbzDnu2Hw3vPUs170vcmi+y"
      + "/X244lBvOaL1MMZcAXhQs9qoXF5pSUa4slZXymtA7VBMrnfP3B2VhZqBRCFdWfiyQvIpoOpBYqJTB"
      + "/pa3YI5rD4SjjvK3gAQKBgQDrVz62veQDT5hMUsKA7OqASB93dxwb5Zl5U1IK35WG642YxmBO6kaNO3Y3hoVy/IJdGyy"
      + "+3CbCvEK779A3J3uEvdINVRFKjoyM/r2QajoZYj2eRYmZWL7rFDDJzRo1zeqnTuHhw+5qdUMxrPXa"
      + "/X2BZAPi18tSrgLDAp4jjMNtaQKBgQCdip5yIWMY2W5agAlZTrooiXr"
      + "+IRLDFWqN28bBGmFDd9w4xOabQlrEwbnijEGqUGgVWC366FFyXYKXnVBQgnSQdTAItXhNkvqKRQE7"
      +
      "/WYKz7dUzv3u10RbC5QiDZd3rXSgQflYgp7lotBUFWgzmxim3XJNwQw1s4NOC7BcVkS2VQKBgQDejaro9bpixyqnQrxdX66DkbDwMNuMJhx0965mSfoFlUxaZX8+IZ6GgOzSpYwdqV0+3yv5cgfVsGMLh0XIYrYUWc4CfafUcenIjD6IC2zWBGlw+yGcuqgRdw6MSEG5D/oQLjC+Z6NXWX0pxP7bEU1Wx1ZFdvz4O0axVBBoXf2RwQKBgBndkHYclTCXQk8Jhn9rNZVCPTdMJtBwy7XOx6scsatqGnYXneUP1+dGB0by0DUuTmhyudngGr80KyTJZ05QnOK62tATO1VBNHFPMwyuZfqQcAE0muyD2203UXLl2GCF0ggdzNqDV6NMP/gOK7WNWVbMNOAfOen3Ny+xK5t9bnqhAoGAQ3PnPhSFYprxSvYNjzvgi1L/72QqsHQ896uak6GG04UPm9WiQ24H4PKVwFebl1fqmWgH4w/7ih2Dx8WuxTupEYgytcZ/eeM0zzjMxZec0ZVz5cGIIOQD/agTDFi1Uwul+z6LKbJr6d+NF3LZbz4MnRRgomSVlNLBeAyi4o9DXFg=\n"
      + "\n" + "Process finished with exit code 0";
}
