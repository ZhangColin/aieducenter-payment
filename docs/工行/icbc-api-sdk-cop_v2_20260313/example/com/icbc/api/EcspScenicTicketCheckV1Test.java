package com.icbc.api;

import com.icbc.api.request.EcspScenicTicketCheckRequestV1;
import com.icbc.api.request.EcspScenicTicketCheckRequestV1.ScenicTicketCheckRequestV1Biz;
import com.icbc.api.response.EcspScenicTicketCheckResponseV1;

import java.util.List;

import org.junit.Assert;

/**
 * APIP 景区云票券检验服务 测试类
 *
 * @author kfzx-luoxw01
 * @version 1.0
 * @date 2019年12月19日
 */
public class EcspScenicTicketCheckV1Test {

  public static void main(String[] args) {
    final String APP_ID = "1234567890";
    final String URL = "https://ip:port/api/ecsp/scenic/ticket/check/V1";

    final String YOUR_PRIVATE_KEY = "YOUR_PRIVATE_KEY";
    final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                    + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                    + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                    + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    ScenicTicketCheckRequestV1Biz biz = new ScenicTicketCheckRequestV1Biz();
    biz.setCorpId("0000000272");
    biz.setSpotId("1");
    biz.setTimestamp("2019-12-19 20:29:00");
    biz.setCientTransNo("1234567890");
    biz.setProtocolType("002");
    biz.setUpData("1234567890");

    EcspScenicTicketCheckRequestV1 requestV1 = new EcspScenicTicketCheckRequestV1();
    requestV1.setBizContent(biz);
    requestV1.setServiceUrl(URL);

    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, YOUR_PRIVATE_KEY, APIGW_PUBLIC_KEY);
    try {
      EcspScenicTicketCheckResponseV1 responseV1 = client.execute(requestV1);
      if (responseV1.isSuccess()) {
        // List<String> list = responseV1.getTicketNoList();
        Assert.assertEquals(responseV1.getReturnCode(), "0");
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
