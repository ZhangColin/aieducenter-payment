package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1;
import com.icbc.api.response.EnterpriseSalaryAnnuityInvestQueryAssessQuestionResponseV1;

public class EnterpriseSalaryAnnuityInvestQueryAssessQuestionV1Test {

    public static void main(String[] args) {
        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "";
        //2、appid
        String APP_ID = "";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);
        //5、设置请求对象
        EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1 request = new EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1();

        //6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
        request.setServiceUrl("https://ip:port/api/assess/question/query/V1");
        //7、设置业务参数
        //7.1 渠道要素信息
        EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1.EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1Biz bizContent = new EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1.EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1Biz();
        EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1.EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1Biz.ChanCommV10 chanCommV10 = new EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1.EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1Biz.ChanCommV10();
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
        bizContent.setChanCommV10(chanCommV10);
        //7.2 交易基础信息
        EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1.EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1Biz.InfoCommV10 infoCommV10 = new EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1.EnterpriseSalaryAnnuityInvestQueryAssessQuestionRequestV1Biz.InfoCommV10();
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
        bizContent.setInfoCommV10(infoCommV10);

        bizContent.setCorpNo("corpNo");
        request.setBizContent(bizContent);

        EnterpriseSalaryAnnuityInvestQueryAssessQuestionResponseV1 response;
        try {
            System.out.println("request:" + JSON.toJSONString(request));
            response = client.execute(request);
            System.out.println("response:" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
