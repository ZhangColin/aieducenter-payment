package com.icbc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayProtocolqueryRequestV1;
import com.icbc.api.request.MybankPayCpayProtocolqueryRequestV1.MybankPayCpayProtocolqueryRequestV1Biz;
import com.icbc.api.response.MybankPayCpayProtocolqueryResponseV1;

public class MybankPayCpayProtocolqueryRequestTestV1 {
    protected static final String MY_PRIVATE_KEY = "XXXX";

    protected static final String APIGW_PUBLIC_KEY = "XXXXX";

    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankPayCpayProtocolqueryRequestV1 req = new MybankPayCpayProtocolqueryRequestV1();
        req.setServiceUrl("http://IP:PROT/api/mybank/pay/cpay/protocolquery/V1");

        MybankPayCpayProtocolqueryRequestV1.MybankPayCpayProtocolqueryRequestV1Biz bizContent = new MybankPayCpayProtocolqueryRequestV1.MybankPayCpayProtocolqueryRequestV1Biz();

        MybankPayCpayProtocolqueryResponseV1 response = new MybankPayCpayProtocolqueryResponseV1();

        bizContent.put("upstreamEventSerialno", String.valueOf(System.currentTimeMillis()));
        bizContent.put("relEventNo", "");
        bizContent.put("busSerialno", "123456789012345671");
        bizContent.put("trxID", "1234567890");
        bizContent.put("channelType","101");
        bizContent.put("terminalId", "dfasf");
        bizContent.put("channelTerminalType", "1");
        bizContent.put("macAddress", "dfasf");
        bizContent.put("ipAddress", "dfasf");
        bizContent.put("channelDate", "2017-08-21");
        bizContent.put("channelTime", "10:10:10");
        bizContent.put("startApp", "F-EBANK");
        bizContent.put("startProdCode", "");
        bizContent.put("servface", "1");
        bizContent.put("zoneNo", "200");
        bizContent.put("brNo", "260");
        bizContent.put("operTeller", "12");
        bizContent.put("trxType", "3");
        bizContent.put("trxCode", "1234");
        bizContent.put("functionCode", "");
        bizContent.put("programName", "6666");
        bizContent.put("trxDate", "2024-09-21");
        bizContent.put("checkWorkDateFlag", "1");
        bizContent.put("operationType", "1");
        bizContent.put("trxTimestamp", "2017-08-21 10:10:10.000000");

        bizContent.put("initFlag","1");//查询标志
        bizContent.put("rowReq","10");//一次查询的最大记录数
        bizContent.put("qryType","1");//查询方式
        bizContent.put("parProno","0020000002140010136437000000257002");//合作方协议编号
        bizContent.put("memberNo","");//会员编号
        bizContent.put("begMemberNo","");//翻页会员编号

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        log.fine("msgId:"+msgId);

        request.setBizContent(bizContent);

        try {
            response = client.execute(request,msgId);
            log.fine("JSONObject.toJSONString(response):"+JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getReturnCode():"+response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():"+response.getReturnCode());
                log.fine("response.getReturnMsg():"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
