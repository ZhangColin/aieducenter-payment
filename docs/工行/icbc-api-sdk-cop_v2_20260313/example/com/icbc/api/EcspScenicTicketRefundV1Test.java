package com.icbc.api;

import com.icbc.api.request.EcspScenicTicketRefundRequestV1;
import com.icbc.api.request.EcspScenicTicketRefundRequestV1.ScenicTicketRefundRequestV1Biz;
import com.icbc.api.response.EcspScenicTicketRefundResponseV1;

import java.util.Arrays;
import java.util.List;

/**
 * APIP 景区云票券退票服务
 *
 * @author kfzx-zhangqy
 * @version 1.0
 * @date 2019/12/16
 */
public class EcspScenicTicketRefundV1Test {

  public static void main(String[] args) throws IcbcApiException {
    String corpId = "0000000272";
    String appId = "1234567890";
    String orderNo = "1234567890";
    List<String> tickets = Arrays.asList("1234567890", "1234567890");
    String url = "https://ip:port/api/ecsp/scenic/ticket/refund/V1";

    String yourPrivatekey ="YOUR_PRIVATE_KEY";

    String icbcInternetGatewayPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                          + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                          + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                          + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


    ScenicTicketRefundRequestV1Biz biz = new ScenicTicketRefundRequestV1Biz();
    biz.setCorpId(corpId);
    biz.setOrderNo(orderNo);
    biz.setTickets(tickets);

    EcspScenicTicketRefundRequestV1 requestV1 = new EcspScenicTicketRefundRequestV1();
    requestV1.setBizContent(biz);
    requestV1.setServiceUrl(url);

    DefaultIcbcClient client = new DefaultIcbcClient(appId, IcbcConstants.SIGN_TYPE_RSA2, yourPrivatekey,
                                                     icbcInternetGatewayPublicKey);
    EcspScenicTicketRefundResponseV1 responseV1 = client.execute(requestV1);

    if (responseV1.isSuccess()) {
      // 业务成功处理
    } else {
      // 业务成功失败
    }
  }
}
