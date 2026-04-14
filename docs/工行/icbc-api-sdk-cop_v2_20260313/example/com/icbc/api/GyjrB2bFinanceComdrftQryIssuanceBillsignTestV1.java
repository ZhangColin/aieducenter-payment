package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bFinanceComdrftQryIssuanceBillsignRequestV1;
import com.icbc.api.response.GyjrB2bFinanceComdrftQryIssuanceBillsignResponseV1;

import java.util.*;
import java.util.logging.Logger;

public class GyjrB2bFinanceComdrftQryIssuanceBillsignTestV1 {

    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000012345";

    static Logger log = Logger.getLogger(GyjrB2bFinanceComdrftQryIssuanceBillsignTestV1.class.getName());
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        GyjrB2bFinanceComdrftQryIssuanceBillsignRequestV1 req = new GyjrB2bFinanceComdrftQryIssuanceBillsignRequestV1();

        req.setServiceUrl("https://ip:port/api/gyjr/b2b/bill/qrysignissuancebill/V1");
        GyjrB2bFinanceComdrftQryIssuanceBillsignRequestV1.GyjrB2bBillQrySignIssuanceBillRequestV1Biz bizContent = new GyjrB2bFinanceComdrftQryIssuanceBillsignRequestV1.GyjrB2bBillQrySignIssuanceBillRequestV1Biz();
        GyjrB2bFinanceComdrftQryIssuanceBillsignRequestV1.TransinfoBiz transInfo = new GyjrB2bFinanceComdrftQryIssuanceBillsignRequestV1.TransinfoBiz();

        List<String> accountNumList = new ArrayList<>();
        accountNumList.add("12345");
        accountNumList.add("33333");
        List<String> billNosList = new ArrayList<>();

        billNosList.add("131342108090220220513155935001");
        billNosList.add("691342101013120240731122158204");

        transInfo.setProtocolCode("GYJR20260701105640610539404");
        transInfo.setPlatVendorId("29");
        transInfo.setAccountNumList(accountNumList);
        transInfo.setUkeyId("29");
        transInfo.setBillAmtFrom("");
        transInfo.setBillAmtTo("");
        transInfo.setCdType("AC00");
        transInfo.setBillDeadDateFrom("");
        transInfo.setBillDeadDateTo("");
        transInfo.setBillNoList(billNosList);

        bizContent.setTransInfoBiz(transInfo);
        req.setBizContent(bizContent);
        GyjrB2bFinanceComdrftQryIssuanceBillsignResponseV1 response;

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        try {
            response = client.execute(req,msgId);
            log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getStatus():"+response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():"+response.getReturnCode());
                log.fine("response.getReturnMsg():"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
