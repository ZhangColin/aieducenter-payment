package com.icbc.api;

import com.icbc.api.request.EcspScenicOrderCreatePayRequestV1;
import com.icbc.api.request.EcspScenicOrderCreatePayRequestV1.ScenicOrderCreateRequestV1Biz;
import com.icbc.api.response.EcspScenicOrderCreatePayResponseV1;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

/**
 * APIP 景区云订单新订出票服务 测试类
 *
 * @author kfzx-luoxw01
 * @version 1.0
 * @date 2019年12月19日
 */
public class EcspScenicOrderCreatepayV1Test {

  public static void main(String[] args) {
    final String APP_ID = "1234567890";
    final String URL = "https://ip:port/api/ecsp/scenic/order/createpay/V1";

    final String YOUR_PRIVATE_KEY = "YOUR_PRIVATE_KEY";
    final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                    + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                    + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                    + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    ScenicOrderCreateRequestV1Biz biz = new ScenicOrderCreateRequestV1Biz();
    biz.setCorpId("0000000272");
    biz.setChannel(3);
    biz.setTravelDate("2019-12-19");
    biz.setBuyerName("name");
    biz.setBuyerMobile("18611111111");
    biz.setPayWay(1);
    ScenicOrderCreateRequestV1Biz.Ticket ticket = new ScenicOrderCreateRequestV1Biz.Ticket();
    ticket.setCcType(3);
    ticket.setCommId("1000316");
    ticket.setTtypeId("27");
    ticket.setPrice(1);
    ticket.setTicketNum(1);
    ticket.setTimeId("124");
    ticket.setTimeBeg("13:00:00");
    ticket.setTimeEnd("23:00:00");
    ScenicOrderCreateRequestV1Biz.User user = new ScenicOrderCreateRequestV1Biz.User();
    user.setUserName("name");
    user.setUserMobile("18611111111");
    user.setUserIdType(7);
    user.setUserIdNo("123456");
    ticket.setUserList(Arrays.asList(user));
    biz.setTicketList(Arrays.asList(ticket));

    EcspScenicOrderCreatePayRequestV1 requestV1 = new EcspScenicOrderCreatePayRequestV1();
    requestV1.setBizContent(biz);
    requestV1.setServiceUrl(URL);

    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, YOUR_PRIVATE_KEY, APIGW_PUBLIC_KEY);
    try {
      EcspScenicOrderCreatePayResponseV1 responseV1 = client.execute(requestV1);
      if (responseV1.isSuccess()) {
        // String orderNo = responseV1.getOrderNo();
        // List<EcspScenicOrderCreatePayResponseV1.Ticket> list = responseV1.getTicketList();
        Assert.assertEquals(responseV1.getReturnCode(), "0");
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
