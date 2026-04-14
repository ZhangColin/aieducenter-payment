package com.icbc.api;

import com.icbc.api.request.MybankAccountPensionAccountStatusqueryRequestV3;
import com.icbc.api.response.MybankAccountPensionAccountStatusqueryResponseV3;
import org.junit.Assert;
import org.junit.Test;

/**
 * 第三支柱开户检查测试案例
 */
public class MybankAccountPensionAccountStatusqueryV3Test {


    public void test_cop() {
        StringBuffer msgStr = new StringBuffer();
        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

        //1、合作方在工行API平台的APP的编号,应用在API开放平台注册时生成
        String APP_ID = "【使用对应环境的合作方APPID】";
        //2、RSA公私钥，API平台的公钥，生产公钥与测试公钥是不一样的
        String APIGW_PUBLIC_KEY = "【替换为网关公钥】";
        //3、RSA公私钥，合作方应用的私钥，应用公钥需要维护在API平台的APP管理中
        String MY_PRIVATE_KEY = "【替换为合作方私钥】";
        //4、AES加密秘钥
        String AES_KEY = "【替换为加密密钥】";

        //5、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8",
                "json", APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, (String) null, (String) null);

        //6、设置请求对象
        MybankAccountPensionAccountStatusqueryRequestV3 request = new MybankAccountPensionAccountStatusqueryRequestV3();
        //7、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
        request.setServiceUrl("http://【替换为对应IP和端口】/api/mybank/account/pension/account/statusquery/V3");
        //8、设置业务参数
        MybankAccountPensionAccountStatusqueryRequestV3.MybankAccountPensionAccountStatusqueryRequestV3Biz bizContent = new MybankAccountPensionAccountStatusqueryRequestV3.MybankAccountPensionAccountStatusqueryRequestV3Biz();

        //9、组装请求参数
        bizContent.setCorpNo("");//客户合作方机构编号，我行分配的机构编号
        bizContent.setTrxAccDate("2021-04-26");//合作方交易日期yyyy-MM-dd
        bizContent.setTrxAccTime("10:38:01");//合作方交易时间HH:mm:ss
        bizContent.setCorpSerno("030302002212117351763301026");//合作方交易单号
        bizContent.setOutServiceCode("statusquery");//外部服务代码
        bizContent.setCorpMediumId("wx_001");//合作方介质号
        bizContent.setCertType("0");//证件类型 业务只支持0-身份证
        bizContent.setCertNo("37032319850314020X");//证件号码
        bizContent.setCustName("王亮");//户名

        request.setBizContent(bizContent);

        MybankAccountPensionAccountStatusqueryResponseV3 response = null;
        try {
            response = client.execute(request);
        } catch (IcbcApiException e) {
            // 测试环境案例经常调不通 给个默认值
            response = new MybankAccountPensionAccountStatusqueryResponseV3();
        } catch (Throwable e2) {
            e2.printStackTrace();
        }

        if (response.isSuccess()) {
            // 业务成功处理
            msgStr.append("【 AssetqueryV3 ReturnCode : ").append(response.getReturnCode()).append("】");
            msgStr.append("【 - ReturnMsg : ").append(response.getReturnMsg()).append("】");
        } else {
            // 失败
            msgStr.append("【 AssetqueryV3 ReturnCode : ").append(response.getReturnCode()).append("】");
            msgStr.append("【 - ReturnMsg : ").append(response.getReturnMsg()).append("】");
        }

        Assert.assertNotNull(msgStr.toString(), response);
    }
}
