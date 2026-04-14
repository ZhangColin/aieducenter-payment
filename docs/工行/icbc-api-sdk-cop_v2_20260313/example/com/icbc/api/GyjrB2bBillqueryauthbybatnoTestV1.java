package com.icbc.api;
import com.alibaba.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQueryauthbatnoRequestV1;
import com.icbc.api.response.GyjrB2bBillQueryauthbatnoResponseV1;
import java.util.Random;
public class GyjrB2bBillqueryauthbybatnoTestV1 {
    protected static final String APP_ID = "******";
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQueryauthbatnoRequestV1 request = new GyjrB2bBillQueryauthbatnoRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/queryauthbybatno/V1");
        GyjrB2bBillQueryauthbatnoRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQueryauthbatnoRequestV1.BizContentV1Biz();
        GyjrB2bBillQueryauthbatnoRequestV1.TransInfoV1Biz transInfo = new GyjrB2bBillQueryauthbatnoRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorid("14");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("100");
        transInfo.setUkeyId("1111");
        transInfo.setBeginDate("20231221");
        transInfo.setEndDate("20231225");
        transInfo.setOperationType("sign");
        transInfo.setBusinessType("discount");
        transInfo.setBatSerialNo("KYA1262848");
        transInfo.setAcctId("");
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);
        GyjrB2bBillQueryauthbatnoResponseV1 response;
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
