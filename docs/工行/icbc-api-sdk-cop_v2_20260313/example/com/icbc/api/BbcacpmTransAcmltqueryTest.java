package com.icbc.api;

import com.alibaba.fastjson.JSON;
import com.icbc.api.request.BbcacpmTransAcmltqueryRequestV1;
import com.icbc.api.response.BbcacpmTransAcmltqueryResponseV1;

public class BbcacpmTransAcmltqueryTest {
    protected static String APP_ID = "xxx";
    protected static String APIGW_PUBLIC_KEY = "xxx";
    protected static String PRI_KEY = "xxx";
    protected static String BASE_URL = "http://ip:port/api";

    public static void main(String[] args) throws IcbcApiException {
        String URI = "bbcacpm/trans/acmltquery/V1"; // api地址
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1 bizContent =
                    new BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1();
            BbcacpmTransAcmltqueryRequestV1 request = new BbcacpmTransAcmltqueryRequestV1();


            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1.GrpHdr grpHdr = new BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1.GrpHdr();

            grpHdr.setRefrence("0118011800003042");
            grpHdr.setBusinessCode("10081");
            grpHdr.setVersion("1.0.0");
            grpHdr.setTradeSource("C");
            grpHdr.setTradeDate("20301001");
            grpHdr.setTradeTime("125959");
            bizContent.setGrpHdr(grpHdr);

            BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1.GrpHdr.Institution sender = new BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1.GrpHdr.Institution();
            sender.setBrchId("402100000018");
            sender.setBrchNm("北京银行");
            sender.setInstId("402100000018");
            sender.setInstNm("北京银行");
            sender.setSubBrchId("260");
            sender.setSubBrchNm("200");
            grpHdr.setSender(sender);

            BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1.GrpHdr.Institution recver = new BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1.GrpHdr.Institution();
            recver.setBrchId("402100000018");
            sender.setBrchNm("北京银行");
            recver.setInstId("402100000018");
            recver.setInstNm("北京银行");
            recver.setSubBrchId("260");
            recver.setSubBrchNm("260");
            grpHdr.setRecver(recver);

            BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1.AcpmAcmltQueryInBusitext busiText = new BbcacpmTransAcmltqueryRequestV1.BbcacpmTransAcmltqueryRequestBizV1.AcpmAcmltQueryInBusitext();
            busiText.setCurrtype("RMB");
            busiText.setOgcode("402100000018");
            busiText.setTrxserno("4021230023754");
            busiText.setProdacc("1111111111");
            busiText.setOgtrxserno("12345678");
            bizContent.setBusiText(busiText);
            System.out.println(JSON.toJSONString(bizContent));
            // 通讯区参数设置
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            // 输出支付指令提交响应对象，需根据实际调用服务更换（BbcacpmTransAcmltqueryResponseV1）
            BbcacpmTransAcmltqueryResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");//
                System.out.println(response);//
            } else {
                // 失败
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
