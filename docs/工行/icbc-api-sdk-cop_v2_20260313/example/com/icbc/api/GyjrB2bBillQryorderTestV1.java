package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;


import java.util.Arrays;
import java.util.Random;

public class GyjrB2bBillQryorderTestV1 {


    protected static final String MY_PRIVATE_KEY = "XXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQryorderRequestV1 request = new GyjrB2bBillQryorderRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/qryorder/V1");
        GyjrB2bBillQryorderRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQryorderRequestV1.BizContentV1Biz();
        GyjrB2bBillQryorderRequestV1.TransInfoV1Biz transInfo
                = new GyjrB2bBillQryorderRequestV1.TransInfoV1Biz();

        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorid("14");
        transInfo.setTransactionRqSeq("9963552352205120520212511");
        transInfo.setOrderNo("2022083010001");

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
