package com.icbc.api;
import com.alibaba.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQueryagreedetailRequestV1;
import com.icbc.api.response.GyjrB2bBillQueryagreedetailResponseV1;
import java.util.Random;
public class GyjrB2bBillQueryagreedetailTest {
    protected static final String APP_ID = "******";
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQueryagreedetailRequestV1 request = new GyjrB2bBillQueryagreedetailRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/queryagreedetail/V1");
        GyjrB2bBillQueryagreedetailRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQueryagreedetailRequestV1.BizContentV1Biz();
        GyjrB2bBillQueryagreedetailRequestV1.TransInfoV1Biz transInfo = new GyjrB2bBillQueryagreedetailRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("11");
        transInfo.setPlatVendorid("22");
        transInfo.setPageSize("10");
        transInfo.setPageIndex("1");
        transInfo.setUkeyId("3344");
        transInfo.setPayAccount("55");
        transInfo.setQueryType("1");
        transInfo.setPlatBatSerialNo("77");
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);
        GyjrB2bBillQueryagreedetailResponseV1 response;
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
