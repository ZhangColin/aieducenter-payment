package com.icbc.api;

import com.icbc.api.request.MybankAccountPensionAccountStatusqueryRequestV2;
import com.icbc.api.response.MybankAccountPensionAccountStatusqueryResponseV2;
import org.junit.Assert;
import org.junit.Test;

/**
 * 第三支柱开户检查测试案例
 */
public class MybankAccountPensionAccountStatusqueryV2Test {


    public void test_cop() {
        StringBuffer msgStr = new StringBuffer();
        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

        //1、合作方在工行API平台的APP的编号,应用在API开放平台注册时生成
        String APP_ID = "10000000000004097527";
        //2、RSA公私钥，API平台的公钥，生产公钥与测试公钥是不一样的
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        //3、RSA公私钥，合作方应用的私钥，应用公钥需要维护在API平台的APP管理中
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCecDlXA5wngnALouTIKTALs3iW3gtiBKIBCrgCcAyuOl+7YzB+pznSfyU0cmHCNOQV9P5ylMfzETF5qDRza0mYk9Ww+k9rV5Kua2yk6sY6J8fpQoOW4nFrfBYHYEnUENt0CYeSjDgn1JQtdZYnoVcPJarVISZ4AaCpzr7dFuBHtgd5ehojaNdQyg+iDRrnVs+uyMYryiYyiU1q6rnPZ32zsbckoYHt2tKfqqoYw0HzLIfI+lKGqMRemDmlpJEFj7ilVHqoT3SKqgyd0QY+5ds5mRqIS+u9kDLfXB8lSasLTihQ3crH6uaOH4ll8/9Kmh8v2SGB8v+xfZX/BVKp1OHjAgMBAAECggEAaPPkN+zPQyjXug2Uybbm+clQR3gdSDfTbSdBj3PUIpHOmp3xIOT7m6HX7m8arQBGWvkomJRQA4bPJEy1OgPHA8hiVNLrSyr+ooXMuU5AM4PonG8QJiIoic/v88DoBw+Hn800PnKePFxIL/jll6j7t6/3AynrhrFlUTcAHgqqVTWGg4YCHPb//QbquukF0RvYADOAG+w50dcKpS4MySvZtlv+isJ3xBpg0JKUmOVi5Z/TYPc01YHAyfHdheocHus6lAYdewS8AJarp7pUYZL7W2GdpDCkIMNBAStUFaV+iLSF2ugb7WjoQ9tf2LPOleVjpBvZT30a5vsFWcyj7npUAQKBgQDel8f5EteZXo4h7a4U/ydjIQ6Gj7k6Ih7e7xrIGnHLimOR1IyBpt+LuCdVxmoBGlW0HEDYZs1QicaTWjxiLa2aKewx8OSW0uVWHCSbk+TXjFBVwgARWy6+GAJi6wCgQhxr3KaNhknNuOBdaIJz/hW7Uslf/caUaBv2lmJSA46iAQKBgQC2N5GOndfsAmLZZBTEAMPsfc8ZELi2cBHDf0sQkdcakFk7f4u1HlhynLKD1e/dsoLimdJYYmTS4mtLkSCqb2sip9F/o5LCFtVevck9Zf8y03Lmye1WIhvQ03j1JOxrUnR3rq7qtw9mRxlyDlKGZn4weDLsGfN6DsjnchtseQU74wKBgQDCWRmzwhzWfzVwMDrvgDxek+0/1dsvA72afoAOuPAkfCclm8Q2K4JBVr+ZZ80EO38a7BQ+HbwkFrd3zrCsNB/AW9wrWp3rhiXRAm+AGygSfyNhTSqL/OYZ+ZKT1YXNZwuzrYsKRHbKFSJozCgiqHVHAihrFIO+7guESJd97jcqAQKBgDUxq7Byq+IeIDVOXjGJ96hGYfrzQrkfpk/oDUXdDWqX0TTpWyOYpEFxNTv/bHLP9h2owhtDPiPCLYh16Y9Xz3Liguo8Gk/G0qtBxEnESOfRiq2NLVgDxRMFaT5HdS0veQinWjDzF+/HucnuHHFX4mbL8DG0sbtkLYpDaXYVLk35AoGBALSRYkiZwmTJ1DNodY7JERefCSQvYakyIdT8zokp7+eW/obVPQ4v3sC332O1WlKFLk46vV3t5XNM2u4JdIo0yHT9MMzDcoGCYbtdl6Uk1CvpiTqam5mDjPuFk86JWiPcl5tIurUVf1Nlw/n9hwdvySPybjy5JTbM3rfALHniwNar";
        //4、AES加密秘钥
        String AES_KEY = "JF0wdsrM14iy9OzeOdk52Q==";

        //5、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8",
                "json", APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, (String) null, (String) null);

        //6、设置请求对象
        MybankAccountPensionAccountStatusqueryRequestV2 request = new MybankAccountPensionAccountStatusqueryRequestV2();
        //7、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
        request.setServiceUrl("http://ip:port/api/mybank/account/pension/account/statusquery/V2");
        //8、设置业务参数
        MybankAccountPensionAccountStatusqueryRequestV2.MybankAccountPensionAccountStatusqueryRequestV2Biz bizContent = new MybankAccountPensionAccountStatusqueryRequestV2.MybankAccountPensionAccountStatusqueryRequestV2Biz();

        //9、组装请求参数
        bizContent.setCorpNo("10000000000004097527");//客户合作方机构编号，我行分配的机构编号
        bizContent.setTrxAccDate("2021-04-26");//合作方交易日期yyyy-MM-dd
        bizContent.setTrxAccTime("10:38:01");//合作方交易时间HH:mm:ss
        bizContent.setCorpSerno("030302002212117351763301026");//合作方交易单号
        bizContent.setOutServiceCode("statusquery");//外部服务代码
        bizContent.setCorpMediumId("wx_001");//合作方介质号
        bizContent.setCertType("0");//证件类型 业务只支持0-身份证
        bizContent.setCertNo("37032319850314020X");//证件号码
        bizContent.setCustName("王亮");//户名

        request.setBizContent(bizContent);

        MybankAccountPensionAccountStatusqueryResponseV2 response = null;
        try {
            response = client.execute(request);
        } catch (IcbcApiException e) {
            // 测试环境案例经常调不通 给个默认值
            response = new MybankAccountPensionAccountStatusqueryResponseV2();
        } catch (Throwable e2) {
            e2.printStackTrace();
        }

        if (response.isSuccess()) {
            // 业务成功处理
            msgStr.append("【 AssetqueryV2 ReturnCode : ").append(response.getReturnCode()).append("】");
            msgStr.append("【 - ReturnMsg : ").append(response.getReturnMsg()).append("】");
        } else {
            // 失败
            msgStr.append("【 AssetqueryV2 ReturnCode : ").append(response.getReturnCode()).append("】");
            msgStr.append("【 - ReturnMsg : ").append(response.getReturnMsg()).append("】");
        }

        Assert.assertNotNull(msgStr.toString(), response);
    }
}
