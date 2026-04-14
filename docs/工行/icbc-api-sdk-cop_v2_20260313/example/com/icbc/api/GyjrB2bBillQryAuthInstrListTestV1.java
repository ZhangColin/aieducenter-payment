package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQryAuthInstrListRequestV1;
import com.icbc.api.response.GyjrB2bBillQryAuthInstrListResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GyjrB2bBillQryAuthInstrListTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000123456";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQryAuthInstrListRequestV1 request = new GyjrB2bBillQryAuthInstrListRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/querypresentationbill/V1");
        GyjrB2bBillQryAuthInstrListRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQryAuthInstrListRequestV1.BizContentV1Biz();
        GyjrB2bBillQryAuthInstrListRequestV1.TransInfoV1Biz transInfo
                = new GyjrB2bBillQryAuthInstrListRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorid("14");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("100");
        transInfo.setTrade_name("queryAuthInstrListByBatNo");
        transInfo.setTrade_version("V1");
        transInfo.setBatSerialNo("KYA203138141262848");

        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        GyjrB2bBillQryAuthInstrListResponseV1 response;
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
