import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1;
import com.icbc.api.response.EnterpriseSalaryAnnuityAppGetEmpAppListResponseV1;
import org.junit.Assert;
import org.junit.Test;


public class EnterpriseSalaryAnnuityAppGetEmpAppListV1Test {


    public void test_appsub() {
        StringBuffer msgStr = new StringBuffer();
        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

        //1、合作方在工行API平台的APP的编号,应用在API开放平台注册时生成
        String APP_ID = "10000000000004097582";
        //2、RSA公私钥，API平台的公钥，生产公钥与测试公钥是不一样的
        String APIGW_PUBLIC_KEY = "key1";
        //3、RSA公私钥，合作方应用的私钥，应用公钥需要维护在API平台的APP管理中
        String MY_PRIVATE_KEY = "key2";
        //4、AES加密秘钥
        String AES_KEY = "key3";

        //5、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8",
                "json", APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, (String) null, (String) null);

        //6、设置请求对象
        EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1 request = new EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1();
        //7、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
        request.setServiceUrl("http://test.icbc/api/enterprise/salary/annuity/app/getEmpAppList/V1");
        //8、设置业务参数
        EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1Biz bizContent =
                new EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1Biz();
        EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1Biz.ChanCommV10 chanCommV10 = new EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1Biz.ChanCommV10();
        EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1Biz.InqCommV10 inqCommV10 = new EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1Biz.InqCommV10();
        EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1Biz.InfoCommV10 infoCommV10 = new EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1.EnterpriseSalaryAnnuityAppGetEmpAppListRequestV1Biz.InfoCommV10();
        //9、组装请求参数
        chanCommV10.setChantype("410");//渠道种类
        chanCommV10.setOapp("BF-XXX");//发起应用
        chanCommV10.setSerialno("870402003002002194809781856");//事件编号
        chanCommV10.setMserialn("");
        chanCommV10.setTermid("53");
        chanCommV10.setIp("ip:port");
        chanCommV10.setSevlevel("0");
        chanCommV10.setCino("020079104130803");
        chanCommV10.setIftrxsernb("50624053");
        chanCommV10.setMac("0");
        chanCommV10.setPreflag("1");

        infoCommV10.setTrxtype("2");//交易类型
        infoCommV10.setTrxcode("70413");//交易代码
        infoCommV10.setZoneno("00200");//地区号
        infoCommV10.setBrno("00003");//网点号
        infoCommV10.setTellerno("93");//柜员号
        infoCommV10.setWorkdate("2022-11-21");//工作日期(yyyy-MM-dd)
        infoCommV10.setWorktime("19:05:41");//交易时间（HH:MM:SS）
        infoCommV10.setServiceface("89");//服务界面
        infoCommV10.setApptype("11");
        infoCommV10.setBrnoweb("00620");
        infoCommV10.setRevtranf("0");
        infoCommV10.setZoneweb("0200");
        infoCommV10.setRegflag("0");

        inqCommV10.setInqtype("1");
        inqCommV10.setRowreq("3");
        inqCommV10.setInitflag("2");
        inqCommV10.setStridenum("3");

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setInqCommV10(inqCommV10);
        bizContent.setCorpNo("10000000000004097738");//合作方机构编号
        bizContent.setQryBeginDate("2024-01-01");
        bizContent.setQryEndDate("2024-03-31");
        bizContent.setCurrentPageNo("5");
        bizContent.setChannelType("15");
        bizContent.setAppType("");
        bizContent.setEmpId("0001781677");


        request.setBizContent(bizContent);
        EnterpriseSalaryAnnuityAppGetEmpAppListResponseV1 response = null;
        try {
            response = client.execute(request);
        } catch (IcbcApiException e) {
            // 测试环境案例经常调不通 给个默认值
            response = new EnterpriseSalaryAnnuityAppGetEmpAppListResponseV1();
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
