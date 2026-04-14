package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQryinquireRequestV1;
import com.icbc.api.response.GyjrB2bBillQryinquireResponseV1;

import java.util.Arrays;
import java.util.Random;

public class GyjrB2bBillQryinquireTestV1 {


    protected static final String MY_PRIVATE_KEY = "XXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQryinquireRequestV1 request = new GyjrB2bBillQryinquireRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/qryinquire/V1");
        GyjrB2bBillQryinquireRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQryinquireRequestV1.BizContentV1Biz();
        GyjrB2bBillQryinquireRequestV1.TransInfoV1Biz transInfo
                = new GyjrB2bBillQryinquireRequestV1.TransInfoV1Biz();
        GyjrB2bBillQryinquireRequestV1.Bill bill = new GyjrB2bBillQryinquireRequestV1.Bill();

        bill.setCdNo("1234567890");
        bill.setRangeBgn("11111");
        bill.setRangeEnd("22222");
        bill.setAmount("300");
        bill.setDueDate("20220510");
        bill.setAccptrAcctsvcr("102100099889");
        bill.setCdTp("AC01");
        bill.setCdMdm("ME02");

        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorid("14");
        transInfo.setTransactionRqSeq("9963552352205120520212511");
        transInfo.setInquiryType("2");
        transInfo.setQuoteAcctsvcr("102100099889");
        transInfo.setCreditCode("0000000011068751X0");
        transInfo.setDealType("01");
        transInfo.setCdTp("");
        transInfo.setCdMdm("");
        transInfo.setQuoteCdList(Arrays.asList(bill));

        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        GyjrB2bBillQryinquireResponseV1 response;
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
