package com.icbc.api;

import com.icbc.api.request.EcspScenicProductSaleRequestV1;
import com.icbc.api.request.EcspScenicProductSaleRequestV1.ScenicProductSaleRequestV1Biz;
import com.icbc.api.response.EcspScenicProductSaleResponseV1;

import org.junit.Assert;

/**
 * APIP 景区云产品销售实时查询服务 测试类
 *
 * @author kfzx-luoxw01
 * @version 1.0
 * @date 2019年12月19日
 */
public class EcspScenicProductSaleQueryV1Test {

  public static void main(String[] args) {
    final String APP_ID = "1234567890";
    final String URL = "https://ip:port/api/ecsp/scenic/product/sale/query/V1";

    final String YOUR_PRIVATE_KEY = "YOUR_PRIVATE_KEY";
    final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                    + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                    + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                    + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    ScenicProductSaleRequestV1Biz biz = new ScenicProductSaleRequestV1Biz();
    biz.setCorpId("0000000272");
    biz.setCcType(3);
    biz.setCommId("1000316");
    biz.setChannel(3);

    EcspScenicProductSaleRequestV1 requestV1 = new EcspScenicProductSaleRequestV1();
    requestV1.setBizContent(biz);
    requestV1.setServiceUrl(URL);

    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, YOUR_PRIVATE_KEY, APIGW_PUBLIC_KEY);
    try {
      EcspScenicProductSaleResponseV1 responseV1 = client.execute(requestV1);
      if (responseV1.isSuccess()) {
        // Integer daySaleNum = responseV1.getDaySaleNum();
        Assert.assertEquals(responseV1.getReturnCode(), "0");
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
