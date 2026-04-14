package com.icbc.api;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1;
import com.icbc.api.response.GyjrB2bFinanceComdrftQryendorsementbillsignResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bFinanceComdrftQryendorsementbillsignTestV1 {

    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    protected static final String APP_ID = "11000000000000001234";

    static Logger log = Logger.getLogger(GyjrB2bFinanceComdrftQryendorsementbillsignTestV1.class.getName());

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1 req = new GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1();

        req.setServiceUrl("https://ip:port/api/gyjr/b2b/finance/comdrft/qryendorsementbillsign/V1");
        GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1.GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1Biz bizContent = new GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1.GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1Biz();
        GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1.TransinfoBiz transInfo = new GyjrB2bFinanceComdrftQryendorsementbillsignRequestV1.TransinfoBiz();

        transInfo.setProtocolCode("GYJR20260701105640610539404");
        transInfo.setPlatVendorId("29");
        transInfo.setUkeyId("29");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("29");
        List list=new ArrayList<>();
        transInfo.setBillNos(list);
        transInfo.setEndrseeAcctId("1");
        transInfo.setAccRcs("29");

        bizContent.setTransInfoBiz(transInfo);
        req.setBizContent(bizContent);
        GyjrB2bFinanceComdrftQryendorsementbillsignResponseV1 response;

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
