package com.icbc.api;

import com.icbc.api.request.EcspScenicProductRequestV1;
import com.icbc.api.response.EcspScenicProductResponseV1;

import java.util.List;

import org.junit.Assert;

/**
 * APIP 景区云产品查询服务 测试类
 *
 * @author kfzx-liyd
 * @version 1.0
 * @date 2019年12月19日
 */
public class EcspScenicProductQueryV1Test {

  public static void main(String[] args) {
    final String APP_ID = "1234567890";
    final String URL = "https://ip:port/api/ecsp/scenic/product/sale/query/V1";

    final String YOUR_PRIVATE_KEY = "YOUR_PRIVATE_KEY";
    final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                    + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                    + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                    + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    EcspScenicProductRequestV1.Biz bizContent = new EcspScenicProductRequestV1.Biz();
    bizContent.setCorpId("0000000001");
    bizContent.setCcType(1);
    bizContent.setCurrentPage(1);
    bizContent.setPageSize(10);

    EcspScenicProductRequestV1 request = new EcspScenicProductRequestV1();
    request.setBizContent(bizContent);
    request.setServiceUrl(URL);

    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, YOUR_PRIVATE_KEY, APIGW_PUBLIC_KEY);
    try {
      EcspScenicProductResponseV1 response = client.execute(request);
      if (response.isSuccess()) {
        // 业务成功处理
        Assert.assertEquals(response.getReturnCode(), 0);
        int totalNum = response.getTotalNum();
        List<EcspScenicProductResponseV1.Product> list = response.getList();
        Assert.assertTrue(totalNum > 0);
        Assert.assertTrue(list != null && list.size() > 0);
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
