package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQueryWithdrawalBillRequestV1;
import com.icbc.api.response.GyjrB2bBillQueryWithdrawalBillResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GyjrB2bBillQueryWithdrawalBillTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000123456";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQueryWithdrawalBillRequestV1 request = new GyjrB2bBillQueryWithdrawalBillRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/querywithdrawalbill/V1");
        GyjrB2bBillQueryWithdrawalBillRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQueryWithdrawalBillRequestV1.BizContentV1Biz();
        GyjrB2bBillQueryWithdrawalBillRequestV1.TransInfoV1Biz transInfo
                = new GyjrB2bBillQueryWithdrawalBillRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorId("14");
        transInfo.setUkeyId("1");
        transInfo.setTransactionRqSeq("9963552352205120520212511");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("10");
        transInfo.setOrgAcctid("1");
        transInfo.setBusinessType("NES_RC_01");
        transInfo.setPackAmtBgn("10");
        transInfo.setPackAmtEnd("1000");
        transInfo.setApplyDateBgn("20260420");
        transInfo.setApplyDateEnd("20260620");
        transInfo.setCdTp("AC01");
        transInfo.setDueDateBgn("20260620");
        transInfo.setDueDateEnd("20270620");
        transInfo.setApplyBatSerialNo("00000000000000000000000000");
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        transInfo.setPackNoList(list);
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        GyjrB2bBillQueryWithdrawalBillResponseV1 response;
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
