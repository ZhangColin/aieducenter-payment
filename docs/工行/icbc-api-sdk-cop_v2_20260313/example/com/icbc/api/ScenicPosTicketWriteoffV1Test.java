package com.icbc.api;

import com.icbc.api.request.ScenicPosTicketWriteoffRequestV1;
import com.icbc.api.request.ScenicPosTicketWriteoffRequestV1.ScenicPosTicketWriteoffRequestV1Biz;
import com.icbc.api.response.ScenicPosTicketWriteoffResponseV1;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kfzx-pengwl
 * @version V1.0
 * @Description
 * @date 2020/8/10 9:26
 */
public class ScenicPosTicketWriteoffV1Test {
  // 1、网关公钥
  protected static final String APIGW_PUBLIC_KEY = "11111";

  // 2、appid
  protected static final String APP_ID = "11111";

  // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
  protected static final String MY_PRIVATE_KEY =
      "11111111";

  public static void main(String[] args) throws Exception {

    // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                                                     APIGW_PUBLIC_KEY);

    ScenicPosTicketWriteoffRequestV1 request = new ScenicPosTicketWriteoffRequestV1();
    // 4、根据测试环境和生产环境替换相应ip和端口
    request.setServiceUrl("http://ip/api/scenic/pos/ticket/writeoff/V1");

    // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
    ScenicPosTicketWriteoffRequestV1Biz bizContent = new ScenicPosTicketWriteoffRequestV1Biz();
    bizContent.setCorpId("1111").setTimeStamp("2019-05-30 15:03:00").setProtocolType("111")
              .setClientId("1111").setUpData("111").setOtherData("11")
              .setClientTransNo("111").setClientType("1112").setCientTransNo("1111");
    request.setBizContent(bizContent);

    ScenicPosTicketWriteoffResponseV1 response;
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
