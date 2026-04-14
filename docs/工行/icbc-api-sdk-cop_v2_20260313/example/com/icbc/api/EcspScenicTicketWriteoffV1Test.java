package com.icbc.api;

import com.icbc.api.request.EcspScenicTicketWriteOffRequestV1;
import com.icbc.api.request.EcspScenicTicketWriteOffRequestV1.ScenicTicketWriteOffRequestV1Biz;
import com.icbc.api.response.EcspScenicTicketWriteOffResponseV1;

import org.junit.Assert;

/**
 * APIP 景区云票券核销服务 测试类
 *
 * @author kfzx-luoxw01
 * @version 1.0
 * @date 2019年12月19日
 */
public class EcspScenicTicketWriteoffV1Test {

  public static void main(String[] args) {
    final String APP_ID = "1234567890";
    final String URL = "https://ip:port/api/ecsp/scenic/ticket/writeoff/V1";

    final String YOUR_PRIVATE_KEY = "YOUR_PRIVATE_KEY";
    final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                    + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                    + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                    + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    ScenicTicketWriteOffRequestV1Biz biz = new ScenicTicketWriteOffRequestV1Biz();
    biz.setCorpId("0000000272");
    biz.setSpotId("1");
    biz.setTicketNo("1234567890");
    biz.setCientTransNo("1234567890");
    biz.setInOutDirect("0");
    biz.setEnterType("10");
    biz.setTicketType("0");

    EcspScenicTicketWriteOffRequestV1 requestV1 = new EcspScenicTicketWriteOffRequestV1();
    requestV1.setBizContent(biz);
    requestV1.setServiceUrl(URL);

    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, YOUR_PRIVATE_KEY, APIGW_PUBLIC_KEY);
    try {
      EcspScenicTicketWriteOffResponseV1 responseV1 = client.execute(requestV1);
      if (responseV1.isSuccess()) {
        // String orderNo = responseV1.getTicketNo();
        Assert.assertEquals(responseV1.getReturnCode(), "0");
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
