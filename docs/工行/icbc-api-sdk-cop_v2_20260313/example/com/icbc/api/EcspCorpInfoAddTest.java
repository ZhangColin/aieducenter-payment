package com.icbc.api;

import com.icbc.api.request.EcspCorpInfoAddRequestV1;
import com.icbc.api.response.EcspCorpInfoAddResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kfzx-linfeng
 */
public class EcspCorpInfoAddTest {
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

    EcspCorpInfoAddRequestV1 request = new EcspCorpInfoAddRequestV1();
    // 4、根据测试环境和生产环境替换相应ip和端口
    request.setServiceUrl("http://****/api/ecsp/assn/class/meeting/sign/V1");

    // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
    EcspCorpInfoAddRequestV1.EcspCorpInfoAddRequestV1Biz bizContent = new EcspCorpInfoAddRequestV1.EcspCorpInfoAddRequestV1Biz();
    bizContent.setCorpName("corpName").setCorpSname("corpSName").setCorpEmail("accountNo").setSource("source")
        .setAdminName("adminName").setAdminCellphone("139456789456").setAdminCertNo("adminCertNo").setEcis("ecis")
        .setTradeBigtype("tradeBigType").setTradeSmalltype("tradeSmallType").setCorpBranchNumber("corpBranchNum")
        .setAdminGender("structType").setCustType("custType").setStatus((byte)1).setAreaCode("areaCode")
        .setIsUnderSupervise((byte)1).setIsReligionPlace((byte)1).setChannelId("channelId");
    request.setBizContent(bizContent);

    EcspCorpInfoAddResponseV1 response;
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
