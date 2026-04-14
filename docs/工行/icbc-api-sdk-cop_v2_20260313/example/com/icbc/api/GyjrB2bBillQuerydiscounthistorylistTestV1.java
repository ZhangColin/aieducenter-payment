package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQuerydiscounthistorylistRequestV1;
import com.icbc.api.response.GyjrB2bBillQuerydiscounthistorylistResponseV1;

import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bBillQuerydiscounthistorylistTestV1 {

    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000012345";

    static Logger log = Logger.getLogger(GyjrB2bBillQuerydiscounthistorylistTestV1.class.getName());


    public static void mainTest() {
        try {
            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
            GyjrB2bBillQuerydiscounthistorylistRequestV1 req = new GyjrB2bBillQuerydiscounthistorylistRequestV1();
            req.setServiceUrl("https://ip:port/api/gyjr/b2b/bill/queryDiscountHistoryList/V1");
            GyjrB2bBillQuerydiscounthistorylistRequestV1.GyjrB2bBillQuerydiscounthistorylistRequestV1Biz bizContent = new GyjrB2bBillQuerydiscounthistorylistRequestV1.GyjrB2bBillQuerydiscounthistorylistRequestV1Biz();
            GyjrB2bBillQuerydiscounthistorylistRequestV1.TransinfoBiz transInfo = new GyjrB2bBillQuerydiscounthistorylistRequestV1.TransinfoBiz();

            transInfo.setProtocolCode("GYJR20260701105640610539404");
            transInfo.setPlatVendorId("29");
            transInfo.setUkeyId("29");
            transInfo.setPageIndex("1");
            transInfo.setPageSize("10");
            transInfo.setReg_type("123");
            transInfo.setReg_no("123456789");
            transInfo.setName("客户名称");
            transInfo.setPartyABhCode("123456");
            transInfo.setProtocolType("PR01");

            bizContent.setTransInfoBiz(transInfo);
            req.setBizContent(bizContent);
            GyjrB2bBillQuerydiscounthistorylistResponseV1 response;

            Random rand = new Random();
            String msgId = rand.nextInt(99999) + "msg";

            response = client.execute(req, msgId);
            log.fine("JSONObject.toJSONString(response):" + JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getStatus():" + response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():" + response.getReturnCode());
                log.fine("response.getReturnMsg():" + response.getReturnMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
