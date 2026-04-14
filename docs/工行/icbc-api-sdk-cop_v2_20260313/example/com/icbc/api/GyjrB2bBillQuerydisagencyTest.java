package com.icbc.api;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQuerydisagencyRequestV1;
import com.icbc.api.response.GyjrB2bBillQuerydisagencyResponseV1;

import java.util.Random;

public class GyjrB2bBillQuerydisagencyTest {
    protected static final String APP_ID = "******";
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQuerydisagencyRequestV1 request = new GyjrB2bBillQuerydisagencyRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/querydisagency/V1");
        GyjrB2bBillQuerydisagencyRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQuerydisagencyRequestV1.BizContentV1Biz();
        GyjrB2bBillQuerydisagencyRequestV1.TransInfoV1Biz transInfo = new GyjrB2bBillQuerydisagencyRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("0521745434");
        transInfo.setPageSize(10);
        transInfo.setPageIndex(1);
        transInfo.setQueryType("1");
        transInfo.setCustName("醇滋沃婚平频识");
        transInfo.setCreditNo("78945612");
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);
        GyjrB2bBillQuerydisagencyResponseV1 response;
        try {
            response = client.execute(request,new Random().nextInt(99999) + "msg");
            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                System.out.println(response.getReturnCode());
            } else {
                System.out.println(response.getReturnCode());
                System.out.println(response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
