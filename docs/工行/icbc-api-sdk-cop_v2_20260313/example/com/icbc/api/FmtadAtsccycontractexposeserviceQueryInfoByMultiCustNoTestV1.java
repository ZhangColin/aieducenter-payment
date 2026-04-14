package com.icbc.api;

import com.icbc.api.request.FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestV1;
import com.icbc.api.request.FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestV1.FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestBizV1;
import com.icbc.api.request.FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestV1.ChanCommV10;
import com.icbc.api.request.FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestV1.InfoCommV10;
import com.icbc.api.request.FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestV1.AppLogComm;
import com.icbc.api.request.FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestV1.InqCommV10;
import com.icbc.api.response.FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoResponseV1;

import java.util.Collections;

public class FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

    //2、appid
    protected static final String APP_ID = "10000000000000260001";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";

    public static void main(String[] args) throws Exception {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestV1 request = new FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestV1();
        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("https://ip:port/api/account/bill/info/query/V2");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestBizV1 bizContent = new FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoRequestBizV1();
        ChanCommV10 chanCommV10 = new ChanCommV10();
        chanCommV10.setChanlno("0");
        InfoCommV10 infoCommV10 = new InfoCommV10();
        infoCommV10.setTrxcode("00116");
        AppLogComm appLogComm = new AppLogComm();
        appLogComm.setServFace("165");

        InqCommV10 inqCommV10 = new InqCommV10();
        inqCommV10.setInqtype("1");
        inqCommV10.setRowreq("5");

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setAppLogComm(appLogComm);
        bizContent.setInqCommV10(inqCommV10);

        bizContent.setBusiType("12");
        bizContent.setServFace("0");
        bizContent.setCptNo("0");
        bizContent.setNfFlag("0");
        bizContent.setAgmtNo("");
        bizContent.setStatus("0");
        bizContent.setBegDate("2032-01-01");
        bizContent.setEndDate("2032-12-31");
        bizContent.setCustNoList(Collections.singletonList("020090283940000"));

        request.setBizContent(bizContent);


        FmtadAtsccycontractexposeserviceQueryInfoByMultiCustNoResponseV1 response;
        try {
            response = client.execute(request, "msgId");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据

            } else {
                //失败

            }
        } catch (IcbcApiException e) {
            //异常处理
        }
    }
}
