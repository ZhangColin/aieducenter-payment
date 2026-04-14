package com.icbc.api;
import com.alibaba.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQueryguaranteeRequestV1;
import com.icbc.api.response.GyjrB2bBillQueryguaranteeResponseV1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class GyjrB2bBillqueryguaranteeTestV1 {
    protected static final String APP_ID = "******";
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillQueryguaranteeRequestV1 request = new GyjrB2bBillQueryguaranteeRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/queryguaranteebill/V1");
        GyjrB2bBillQueryguaranteeRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQueryguaranteeRequestV1.BizContentV1Biz();
        GyjrB2bBillQueryguaranteeRequestV1.TransInfoV1Biz transInfo = new GyjrB2bBillQueryguaranteeRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorid("14");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("100");
        transInfo.setUkeyId("1111");
        List<String> appAcctIds = new ArrayList<>();
        appAcctIds.add("12345667");
        transInfo.setAppAcctIds(appAcctIds);
        transInfo.setCdTp("0");
        transInfo.setGuarType("1");
        transInfo.setPackAmtBgn("10000");
        transInfo.setPackAmtEnd("100000");
        transInfo.setDueDateBgn("XXXX-XX-XX");
        transInfo.setDueDateEnd("XXXX-XX-XX");
        List<String> packNoList = new ArrayList<>();
        packNoList.add("123445980");
        transInfo.setPackNoList(packNoList);
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);
        GyjrB2bBillQueryguaranteeResponseV1 response;
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
