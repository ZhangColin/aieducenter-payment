package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQuerysignbillRequestV1;
import com.icbc.api.response.GyjrB2bBillQuerysignbillResponseV1;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class GyjrB2bBillQuerysignbillTestV1 {


    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000012345";


    public void GyjrB2bBillQuerysignbillTestV1() {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQuerysignbillRequestV1 request = new GyjrB2bBillQuerysignbillRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/querysignbill/V1");
        GyjrB2bBillQuerysignbillRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQuerysignbillRequestV1.BizContentV1Biz();
        GyjrB2bBillQuerysignbillRequestV1.TransInfoV1Biz transInfo
                = new GyjrB2bBillQuerysignbillRequestV1.TransInfoV1Biz();

        transInfo.setUkeyId("12345");
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorid("14");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("100");
        transInfo.setSignAcctId("");
        transInfo.setCdTp("AC01");
        transInfo.setSignType("");
        transInfo.setAccptrBank("1");
        transInfo.setDueDateBgn("20230101");
        transInfo.setDueDateEnd("20230105");
        transInfo.setPackAmtBgn("");
        transInfo.setPackAmtEnd("");
        transInfo.setPackNoList(Arrays.asList("111","222"));
        transInfo.setOrderRule("0");

        bizContent.setTransInfoV1Biz(transInfo);
        request.setBizContent(bizContent);

        GyjrB2bBillQuerysignbillResponseV1 response;
        try {
            response = client.execute(request,new Random().nextInt(99999) + "msg");
            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
            } else {
                // 失败
                System.out.println(response.getReturnCode());
                System.out.println(response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
