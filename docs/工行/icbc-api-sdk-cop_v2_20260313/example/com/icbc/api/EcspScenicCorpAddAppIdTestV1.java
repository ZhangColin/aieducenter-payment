package com.icbc.api;

import com.icbc.api.request.EcspScenicCorpAddAppIdRequestV1;
import com.icbc.api.request.EcspScenicCorpAddAppIdRequestV1.EcspScenicAddAppIdRequestV1Biz;
import com.icbc.api.request.EcspScenicCorpGenKeyRequestV1;
import com.icbc.api.request.EcspScenicCorpGenKeyRequestV1.EcspScenicGenKeyRequestV1Biz;
import com.icbc.api.response.EcspScenicCorpAddAppIdResponseV1;
import com.icbc.api.response.EcspScenicCorpGenKeyResponseV1;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kfzx-pengwl
 * @version V1.0
 * @Description
 * @date 2019/9/25 9:13
 */
public class EcspScenicCorpAddAppIdTestV1 {
  // 1、网关公钥
  protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                                   + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                                   + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                                   + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

  // 2、appid
  protected static final String APP_ID = "appid";

  // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
  protected static final String MY_PRIVATE_KEY =
      "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCcYH+VSrIU" + "/NVF11SO0/wvA4qub87Yi5f0"
      + "+nLXfWp8FeBj3IHY9CmBK3fQkpCy8kwyO6ZBwwD6YLZmU78QEIXZUSRFQiLkn2Tf3t1d57EHhkpxtbTbvGhtz"
      + "/xQ1Ei17F6PSB5aMkocavRYc9ALYk3jnPWRZsDNe49at28sBT7qwCOwj4JE/EAke9H+zesaYd3TBdMnluqWSD1wk1FA9DCjUdv"
      + "+1mmwiw6Z6NoUSWsW/EmCC0YsHOfR2akmGE2ddNGT6SHDUIcIetW1zX"
      + "/BGa34LUJdXUMGO8XkY60KmNqp6rfZy8U0SbN5qIsIIuUArBrVdqYZZ8U6gFaG9HrMtJ1ZAgMBAAECggEAep74rFp8NhTkL"
      + "/Xf6nez4jHGx24JJqifqYvSDcPcghytbeD1OOiMuysYssabDJcOxL0da6e71pmLrcWxpqkuK/CCnoRz1A5hDWM29nG90YauTWgMv9mHkcRrt"
      + "/4eXflRVM1ab6PLlWkQ3WNDGgolxXvI3BFU0kw3Zw078arTYUO4cURSikc"
      + "+nr0CmnWrZWlKEfkSLEjbg4uYjidmXNWqCaiv4TGLNZuHdAl6AK1SXEzJ28JG4eTywsUH0ToFI/7fCw6BbIz9bDVksId0"
      + "+oclC3mYqwsPpELGzbRXOvksGg1G2DWX3mzfwY2ng66v1bVTAGzFswLJM8ND+Ynh08L8mQKBgQDlr19CFx83K8emY2tpE3mTPEtqAM"
      + "+X4EEgn7AW7GLUbhvjcRPLe3uJeeJuBMBE7mUyflQopadI9gbtsFG1m9gw3P1EH/OV/Ixwp"
      + "/1zUEjnarZm7IriawJFxKW2DJFuP44XXhLkfKhnYj8qat1fR3VcBdP1ChPevkrG/ZlWAOm2jwKBgQCuSwK9lK7U/pV1JY+IFEEiXyie"
      + "/CRE6QXi+My6kDTURpKhHmqSnVHto7EbxyC4rT3/7W1lTEvP"
      + "+lIvtRdzrlc9dpUV3Tx44vMAzm1bd63v4EswQ0MgI5OXQXieE2C08LJI11HwfRqBq6T9p6bsXt"
      +
      "+QuS4S408Ec4TlyFAoXVahlwKBgQDYM95dHdq6WjGSxB8FR9m26JisbZr7gZKMnQRqb5UQFZ2W2IgjkFbXL64elcZOnIERqKybnmL7YOtgrgocA1jblgmpKNJnqtSf7uG6vCYEAAU2mQ6a1tPKzLKXzPjQaG7ePCVYgqcaufZvfSxoVaF5P9sm25ecok9NRMYjmLRmrQKBgFGtZUNigevewjt7tp+Wnk91OJwInRaN/Ow4m/hxy9AOonhOwLKkdthedYkG8BabWQquQS3Pa1ezfwBMZQkZElkDUSpF2yjrceVW13idxS08dv/PBY3yHiAr1lJ90jv48BOilAuXFOzfexpDjr4GV86CeUzwqP71A7qK/MVtuENRAoGAdkGlxBE9FW3Tyk2w0HaFA4rQjo8+dLuzUR2yFKYKGOJ+md+kFHEKCY0ugdHXOY1qrsmXfIv1izwHrmNoZHQKKE1WlrzreL7cCZk1sW04UO2Tzdz1SltxeYKeCzjY/w4utGLLvV3h8hGAsDKuaTtcHOqhu5as9SzCeKRaw0D8NWY=";

  public static void main(String[] args) throws Exception {

    // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                                                     APIGW_PUBLIC_KEY);

    EcspScenicCorpAddAppIdRequestV1 request = new EcspScenicCorpAddAppIdRequestV1();
    // 4、根据测试环境和生产环境替换相应ip和端口
    request.setServiceUrl("https://ip:port/api/ecoupon/send/V1");

    // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
    EcspScenicAddAppIdRequestV1Biz bizContent = new EcspScenicAddAppIdRequestV1Biz();
    bizContent.setAppId("appid").setCustId("00099356");
    request.setBizContent(bizContent);

    EcspScenicCorpAddAppIdResponseV1 response;
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

    /**
     * 生产Key·
     */
    EcspScenicCorpGenKeyRequestV1 genKeyRequestV1 = new EcspScenicCorpGenKeyRequestV1();
    genKeyRequestV1.setServiceUrl("https://ip:port/api/ecoupon/send/V1");
    EcspScenicGenKeyRequestV1Biz genKeyRequestV1Biz = new EcspScenicGenKeyRequestV1Biz();
    genKeyRequestV1Biz.setCustId("00099356");
    genKeyRequestV1.setBizContent(genKeyRequestV1Biz);

    EcspScenicCorpGenKeyResponseV1 genKeyResponseV1;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
      String msgId = dateFormat.format(new Date());
      genKeyResponseV1 = client.execute(genKeyRequestV1, msgId);
      if (genKeyResponseV1.isSuccess()) {
        // 业务成功处理
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
