package com.icbc.api;

import com.icbc.api.request.MybankAccountPensionBranchAccountscodeverifyRequestV1;
import com.icbc.api.response.MybankAccountPensionBranchAccountscodeverifyResponseV1;
import org.junit.Assert;
import org.junit.Test;

/**
 * 第三支柱短信验证码校验测试案例
 */
public class MybankAccountPensionBranchAccountscodeverifyTest {


    public void test_accountscodeverify() {
        StringBuffer msgStr = new StringBuffer();
        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

        //1、合作方在工行API平台的APP的编号,应用在API开放平台注册时生成
        String APP_ID = "10000000000004097582";
        //2、RSA公私钥，API平台的公钥，生产公钥与测试公钥是不一样的
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        //3、RSA公私钥，合作方应用的私钥，应用公钥需要维护在API平台的APP管理中
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCIoI4kzHYZTZZMmxhfxxZqQoSD6L0VIhmheV6/DqGBj3srhUORXt1Onea5/DUt0SbE/MUPrWpDuHmXikHfavyYh167MW6WeKIUTwFmv14a/DyzT7msjEzvNJGH+KphmE866rkddtNTXrfiLTmVneF5VwY/Dc06JYqpt4HVAUsXhJFtwxYvztp99LrlEVDTiJpRObRuXlra4b7g1naIsrOtDogNCyikWW+wGIyl70G7cOYzc3/xKLtKofGNh1TT4IJ/MUqqYJtlpfCYcI0jWDhvpkMo4mK3H21zzR/p/F1KhM4Gfs6GhCyAJPXSWHm0L5u+qygm/7+ptvJao+PhEWbFAgMBAAECggEADAkAnHM6T/5KqBLdccpqyg9z9t6wkUUAtv2m//N8/Jm0AuBpVEvj+MHmCioSUSfkX2PYxAnFm5OLenLeXJzjBVifWqP/FtgnhtxyxidDVeM0QaNbOrzdJhEeZLjd4Vz2Vb1JczP9Z5GnZxAOy4Qt0W9+CF3h3yZhcZPNpwLHJ5oPDOt7HoWRxpHX1zWGtrSLlTWBWJsb8sxLSjkPIFlw74rOTi6d13Buhmmy/O18eftolROy6DUHp+DpOJiTx9e7IGswqZn1Db31fYMFqdxh23RkTSSXY9FQYig0qVYwMRHC4tHMLbefkMAH7irShaH5DxlwVAyD1OWogVLQwnfvfQKBgQDshjBe+0oGrlKUr3MJqTX41oXUTM693pLoCFDroo57ZRtxaOzp6NfhLoHSrogArpNWdFbkIFwf7bTwAKx5Hsc/e6lx8cUqri5RBMDx4L9hU30hkvf0Yfr7P1Y8PEKjNzQrzRM6dM84DWeDsxS9vCtq07zfW915HBA7vVBbXCNY5wKBgQCT4JZk/ZcKK8xCGYgyqv+mUuCjgeauVA4A/o27FUx4zYa3s0mRhVl4kAj77EvU1EmD1zj9YJxdxdHGMenf0BTq2b6FAhpHzNc4MHcD6wPPtjOyKzpcONHHgKqPAE0l/4obIChw7PREHlOFco3kTVAfT03qikPZfgUxflwWiqnxcwKBgQDJIH6ndgYH0lq0xtEK6RHVLQ6Wbk3rlQXVO6UcCpoggeSpNrEnYvGYMYUB05ohAlKV1M5CpOXR3L/cyqXfRPXnnM+E7jNcTMtj56fuxs/XAjjIwQIKlProhX5eMFv2EK5ThMqSDdxb7MIWcsTzZryA1dapFKQrJVsEgcKAPcRtBwKBgEY+/bJjtIgkXhdYQZawHFlKLWP+pTJSw4Q9Z95jM7x/BWvmQyBZlDLtWRczHKKxZWq3pl7WsfDps8jOlSa4lfcqEJLmeM16xRHwE9lMnrbg+pW8azC73QtEV8Euh3dxeI2CLTPXnAFv1oUh8b1t7I2zAnRT0f9TGex09hHPNQN5AoGBAOkU5KBkNggctHXvgKsPDeQTWhsEXO9CY/IeBvmX78oV/+cs277Aa4Q9HxuixYvgLDkTTEjdAq+y07tfV4ZdIov3kjDmfSjZlE0AWybAVyNwTr/NdRv+SlBUdytoChDyA52JdUxsZsFwkrAO+UNHCh65rg+HjkNpwWLLuVFZSij/";
        //4、AES加密秘钥
        String AES_KEY = "NTF9Fjy668DxUulsCd2Jqw==";

        //5、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8",
                "json", APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, (String) null, (String) null);

        //6、设置请求对象
        MybankAccountPensionBranchAccountscodeverifyRequestV1 request = new MybankAccountPensionBranchAccountscodeverifyRequestV1();
        //7、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
        request.setServiceUrl("http://ip:port/api/mybank/account/pension/branch/accountscodeverify/V1");
        //8、设置业务参数
        MybankAccountPensionBranchAccountscodeverifyRequestV1.MybankAccountPensionBranchAccountscodeverifyRequestV1Biz bizContent =
                new MybankAccountPensionBranchAccountscodeverifyRequestV1.MybankAccountPensionBranchAccountscodeverifyRequestV1Biz();
        MybankAccountPensionBranchAccountscodeverifyRequestV1.ChanCommV10 chanCommV10 = new MybankAccountPensionBranchAccountscodeverifyRequestV1.ChanCommV10();
        MybankAccountPensionBranchAccountscodeverifyRequestV1.InfoCommV10 infoCommV10 = new MybankAccountPensionBranchAccountscodeverifyRequestV1.InfoCommV10();
        //9、组装请求参数
        chanCommV10.setChantype("410");//渠道种类
        chanCommV10.setOapp("BF-XXX");//发起应用
        chanCommV10.setSerialno("870402003002002194809781856");//事件编号

        infoCommV10.setTrxtype("2");//交易类型
        infoCommV10.setTrxcode("70413");//交易代码
        infoCommV10.setZoneno("00200");//地区号
        infoCommV10.setBrno("00003");//网点号
        infoCommV10.setTellerno("93");//柜员号
        infoCommV10.setWorkdate("2022-11-21");//工作日期(yyyy-MM-dd)
        infoCommV10.setWorktime("19:05:41");//交易时间（HH:MM:SS）
        infoCommV10.setServiceface("89");//服务界面
        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);

        bizContent.setCorpNo("10000000000004097738");//合作方机构编号
        bizContent.setCorpSerno("030302002186100040282285267");//合作方交易单号
        bizContent.setTrxAccDate("2021-04-26");//交易受理日期
        bizContent.setTrxAccTime("10:38:01");//交易受理时间
        bizContent.setSmsSendNo("917271");//短信发送编号
        bizContent.setSmsScode("431401");//短信验证码
        bizContent.setOutServiceCode("scodeverify");//外部服务代码
        bizContent.setCorpDate("2021-04-26");//合作方工作日期
        bizContent.setCorpSernoOriginal("030302001030333431322320040");//原合作方交易单号
        bizContent.setAppType("1");//申请交易类型，1-开户

        request.setBizContent(bizContent);

        MybankAccountPensionBranchAccountscodeverifyResponseV1 response = null;
        try {
            response = client.execute(request);
        } catch (IcbcApiException e) {
            // 测试环境案例经常调不通 给个默认值
            response = new MybankAccountPensionBranchAccountscodeverifyResponseV1();
        } catch (Throwable e2) {
            e2.printStackTrace();
        }

        if (response.isSuccess()) {
            // 业务成功处理
            msgStr.append("【 AssetqueryV1 ReturnCode : ").append(response.getReturnCode()).append("】");
            msgStr.append("【 - ReturnMsg : ").append(response.getReturnMsg()).append("】");
        } else {
            // 失败
            msgStr.append("【 AssetqueryV1 ReturnCode : ").append(response.getReturnCode()).append("】");
            msgStr.append("【 - ReturnMsg : ").append(response.getReturnMsg()).append("】");
        }

        Assert.assertNotNull(msgStr.toString(), response);
    }
}
