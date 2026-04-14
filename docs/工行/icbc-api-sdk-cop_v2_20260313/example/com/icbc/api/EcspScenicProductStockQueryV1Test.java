package com.icbc.api;

import com.icbc.api.request.EcspScenicProductStockRequestV1;
import com.icbc.api.response.EcspScenicProductStockResponseV1;

import java.util.List;

import org.junit.Assert;

/**
 * APIP 景区云产品库存查询服务 测试类
 *
 * @author kfzx-liyd
 * @version 1.0
 * @date 2019年12月19日
 */
public class EcspScenicProductStockQueryV1Test {

  public static void main(String[] args) {
    final String APP_ID = "1234567890";
    final String URL = "https://ip:port/api/ecsp/scenic/product/sale/query/V1";

    final String YOUR_PRIVATE_KEY = "YOUR_PRIVATE_KEY";
    final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV"
                                    + "+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX"
                                    + "+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r"
                                    + "/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    EcspScenicProductStockRequestV1.Biz bizContent = new EcspScenicProductStockRequestV1.Biz();
    bizContent.setCorpId("0000000272");
    bizContent.setCcType(3);
    bizContent.setCommId("1000342");
    bizContent.setChannel(1);
    bizContent.setDate("2019-11-12");

    EcspScenicProductStockRequestV1 request = new EcspScenicProductStockRequestV1();
    request.setBizContent(bizContent);
    request.setServiceUrl(URL);

    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, YOUR_PRIVATE_KEY, APIGW_PUBLIC_KEY);
    try {
      EcspScenicProductStockResponseV1 response = client.execute(request);
      if (response.isSuccess()) {
        // 业务成功处理
        Assert.assertEquals(response.getReturnCode(), "0");
        EcspScenicProductStockResponseV1.DayStock dayStock = response.getDayStock();
        List<EcspScenicProductStockResponseV1.PartingStock> partingStock = response.getPartingStocks();
        Assert.assertNotNull(dayStock);
        Assert.assertNotNull(partingStock);
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
