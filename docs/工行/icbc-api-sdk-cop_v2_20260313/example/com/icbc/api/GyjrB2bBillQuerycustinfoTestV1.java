package com.icbc.api;
import com.alibaba.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQuerycustinfoRequestV1;
import com.icbc.api.response.GyjrB2bBillQuerycustinfoResponseV1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GyjrB2bBillQuerycustinfoTestV1 {
    protected static final String APP_ID = "******";
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQuerycustinfoRequestV1 request = new GyjrB2bBillQuerycustinfoRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/querycustinfo/V1");
        GyjrB2bBillQuerycustinfoRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQuerycustinfoRequestV1.BizContentV1Biz();
        GyjrB2bBillQuerycustinfoRequestV1.TransInfoV1Biz transInfo = new GyjrB2bBillQuerycustinfoRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorid("14");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("100");
        transInfo.setUkeyId("1111");
        transInfo.setCustPriAcc("112457");
        transInfo.setOperType("1");
        List<String> corpAcctIdList = new ArrayList<>();
        corpAcctIdList.add("12345667");
        transInfo.setCorpAcctIdList(corpAcctIdList);
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);
        GyjrB2bBillQuerycustinfoResponseV1 response;
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
