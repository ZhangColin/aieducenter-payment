package com.icbc.api;

import com.icbc.api.request.EcspScenicCorpAddCorpInfoRequestV1;
import com.icbc.api.request.EcspScenicCorpAddCorpInfoRequestV1.EcspScenicAddCorpInfoRequestV1Biz;
import com.icbc.api.request.EcspScenicModifyCorpInfoRequestV1;
import com.icbc.api.request.EcspScenicModifyCorpInfoRequestV1.EcspScenicModifyCorpInfoRequestV1Biz;
import com.icbc.api.response.EcspScenicCorpAddCorpInfoResponseV1;
import com.icbc.api.response.EcspScenicModifyCorpInfoResponseV1;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kfzx-pengwl
 * @version V1.0
 * @Description
 * @date 2019/9/25 8:44
 */
public class EcspScenicCorpAddCorpInfoTestV1 {
  // 1、网关公钥
  protected static final String APIGW_PUBLIC_KEY =
      "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAsCa3BqbXMuepuWARHjm8QTYfoJ5pEIOYo0uFPUXz6nJDx+aCWQX2ZiWLC0A"
      + "+aIuMeGDeQ9O9AjoCeawZAVlssSYXyjMIixwLYgQf86Zf0r+WWZGW7tMuxwr8zWxfbfe9ob3s1q"
      + "+fsDCaCejLtvR8htsZnQBQKDAywmRwY3MmZv227X9LZ31w542p6N642C4hTVB6pW57O7jjIDPiYB0LKsTPvAOh7L7ReB10NhMQjPuX1rl"
      + "M4amAPXXK4/oxU2qFrQ1tbeKZ2VQw2gh0Bb0qRWTltQ9qGQ1PQDf7vWwgKeLvKF69PcWJ60xpCGVSy6/FaQtAWsXdLJINJ0tWBVaizwIDAQAB";

  // 2、appid
  protected static final String APP_ID = "appid";

  // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
  protected static final String MY_PRIVATE_KEY =
      "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwJrcGptcy56m5YBEeObxBNh"
      + "+gnmkQg5ijS4U9RfPqckPH5oJZBfZmJYsLQD5oi4x4YN5D070COgJ5rBkBWWyxJhfKMwiLHAtiBB/zpl"
      + "/Sv5ZZkZbu0y7HCvzNbF9t972hvezWr5+wMJoJ6Mu29HyG2xmdAFAoMDLCZHBjcyZm/bbtf0tnfXDnjano3rjYLiFNUHqlbns7uOMgM"
      + "+JgHQsqxM+8A6HsvtF4HXQ2ExCM+5fWuUzhqYA9dcrj+jFTaoWtDW1t4pnZVDDaCHQFvSpFZOW1D2oZDU9AN"
      +
      "/u9bCAp4u8oXr09xYnrTGkIZVLLr8VpC0Baxd0skg0nS1YFVqLPAgMBAAECggEAPSczozuIukNyyMhxKO0iTgFcOYbFMtZ8xzQQrZT7eQjyPxP"
      + "nbMCFKjEedGl5QRIxUhlPEH0RwRQih9sXU0CYW2e+GpRdAu5ZhU2pSg0YdzH-----请使用正确的APP私钥-----xKud7W/oLyAN7+wk6fpucosd+GsMQutgH7tyhpkue9n"
      + "KPfMBSLirET03LGgjo+e0KolivjcFODfEK7SwRwD92ikCNFrVnwXHj/Tyh5I7Da69ifaUFcR7mKASONfPFiSdUOh0/Db9A6NpwxZ61NkOba6N2SfPV"
      + "bIzZ3Uzpq/J4ecu85J/3ZaoVzuSQot3XZ2hyYdxpjWs/t40E1CvG2M/TW+RjUIvFpZcbsJKwqAQKBgQDqg1bcKYY5nbTPkDoavbYZoOcDO5m49q8gKOH"
      + "jRv769Qc6U/XCPblJk0KMIyvjC0mopKknKZDQCL5orIreMZGIhpN858oBHGr1k6cFmbU4p6hWwIExYbT8PMGe5X5hrQdJt19AEjOAZLK2h8l+U9wBg"
      + "2GC1KSJrvBn1OSWAqAWSQKBgQDASnXNqE/cmM0bsvJ8GnjOMjLj/tQnQil45wMxDd49cK1ljlw9RvTn7UA+L+pA8a9sMFw0hbUxHF8Om0dEr7lsybZL"
      + "zRycCjwiJ5Pmklt/OBkl/K7ZwpujkmrcUbgTJnXqZIDmce3p9lxjy1SgH4mUqn1Ulzwuc9j0oiZ1WTaQVwKBgAouK24hS1di9j9wdmVEyQvxxTWJt9Gk"
      + "u9N8ktnH8je4vViV3nxRK6L7Bo+I01Xm3oq4ozM1BblSN9nZa6nbowK3PN3WPhmwPyBsCXsfwhM0jAVAFButryqoBIu+UK8y0Lctg7AHWj7/oBZ2bJPUVh+"
      + "msWv5iS8E7iDDyCyO7p4pAoGBAK1o+P/0j8Di0+Xs5RziSpu5TYerIewdkIUF4Cz20rsZyGXTkn94ZBhcLxll4J2c15R7sEt1YUfezCLCCe15g4EGD64gVX"
      + "KrEDvE8W54AqeiEZveWljrVayXuGLkF1q9zo0JAe3QRLw/fmZI0hJtHofBkLEeihliQ/+p04D0aW6VAoGANOyW9QzsSmmP026oMAKM1FtNUfpCi9KFevvzBq"
      + "TFo6O/jzvQvuX5lhmep5djKNpaV8mMv+H666ZNPZ7UZnkiqz92l1MqRfcqel0AftRnybZEAhDwrV9qBG8CVgSDIDfM4ZVguWonw4FrycXRBlFy7IRx1LBIV1xcu"
      + "p+kjPHd5Og=";

  public static void main(String[] args) throws Exception {

    // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                                                     APIGW_PUBLIC_KEY);

    EcspScenicCorpAddCorpInfoRequestV1 request = new EcspScenicCorpAddCorpInfoRequestV1();
    // 4、根据测试环境和生产环境替换相应ip和端口
    request.setServiceUrl("http://ip:port/api/ecsp/scenic/corp/addCorpInfo/V1/addCorpInfo");

    // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
    EcspScenicAddCorpInfoRequestV1Biz bizContent = new EcspScenicAddCorpInfoRequestV1Biz();
    bizContent.setAccessType((byte) 1).setAccountNo("000142").setAddress("香洲区南方软件园").setAdminCellphone("13333333333")
              .setAdminCertNo("114432").setAdminName("王菲").setCity("珠海市").setCorpName("工软").setBrchId("1111")
              .setCustId("00099356");
    request.setBizContent(bizContent);

    EcspScenicCorpAddCorpInfoResponseV1 response;
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
     * 测试修改信息
     */
    EcspScenicModifyCorpInfoRequestV1 modifyCorpInfoRequestV1 = new EcspScenicModifyCorpInfoRequestV1();
    modifyCorpInfoRequestV1.setServiceUrl("https://ip:port/api/ecoupon/send/V1");
    EcspScenicModifyCorpInfoRequestV1Biz modifyCOfpInfoRequestV1Biz = new EcspScenicModifyCorpInfoRequestV1Biz();
    modifyCOfpInfoRequestV1Biz.setCorpId("9000000001").setAccessType((byte) 1).setAccountNo("000142")
                              .setAddress("香洲区南方软件园").setAdminCellphone("13333333333").setAdminCertNo("114432")
                              .setAdminName("王菲").setCity("珠海市").setCorpName("工软").setBrchId("1111")
                              .setCustId("00099356");
    modifyCorpInfoRequestV1.setBizContent(modifyCOfpInfoRequestV1Biz);

    EcspScenicModifyCorpInfoResponseV1 ecspScenicModifyCorpInfoResponseV1;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
      String msgId = dateFormat.format(new Date());
      ecspScenicModifyCorpInfoResponseV1 = client.execute(modifyCorpInfoRequestV1, msgId);
      if (ecspScenicModifyCorpInfoResponseV1.isSuccess()) {
        // 业务成功处理
      } else {
        // 失败
      }
    } catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
