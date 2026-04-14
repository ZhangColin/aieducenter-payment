package com.icbc.api;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQueryCancellationPledgeRequestV1;
import com.icbc.api.response.GyjrB2bBillQueryCancellationPledgeResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bBillQueryCancellationPledgeTestV1 {

    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    protected static final String APP_ID = "11000000000000001234";

    static Logger log = Logger.getLogger(GyjrB2bBillQueryCancellationPledgeTestV1.class.getName());

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQueryCancellationPledgeRequestV1 req = new GyjrB2bBillQueryCancellationPledgeRequestV1();

        req.setServiceUrl("https://ip:port/api/gyjr/b2b/bill/querycancellationpledge/V1");
        GyjrB2bBillQueryCancellationPledgeRequestV1.GyjrB2bBillQueryCancellationPledgeRequestV1Biz bizContent = new GyjrB2bBillQueryCancellationPledgeRequestV1.GyjrB2bBillQueryCancellationPledgeRequestV1Biz();
        GyjrB2bBillQueryCancellationPledgeRequestV1.TransinfoBiz transInfo = new GyjrB2bBillQueryCancellationPledgeRequestV1.TransinfoBiz();

        transInfo.setProtocolCode("GYJR20260701105640610539404");
        transInfo.setPlatVendorId("29");
        transInfo.setUkeyId("29");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("1");
        transInfo.setPledgeeAcctId("0200099809212066358");
        transInfo.setPackAmtBgn("0");
        transInfo.setPackAmtEnd("100000000000000000");
        transInfo.setCdTp("0");
        transInfo.setDueDateBgn("20230930");
        transInfo.setDueDateEnd("20240930");
        transInfo.setAccptrClass("102");
        List a=new ArrayList();
        transInfo.setPackNoList(a);

        bizContent.setTransInfoBiz(transInfo);
        req.setBizContent(bizContent);
        GyjrB2bBillQueryCancellationPledgeResponseV1 response;

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        try {
            response = client.execute(req,msgId);
            log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getReturnCode():"+response.getReturnCode());
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
