package com.icbc.api;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQuerybasicbillRequestV1;
import com.icbc.api.response.GyjrB2bBillQuerybasicbillResponseV1;
import java.util.*;
public class GyjrB2bBillQuerybasicbillTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000123456";


    public static void main(String[] args) {

            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
            GyjrB2bBillQuerybasicbillRequestV1 request = new GyjrB2bBillQuerybasicbillRequestV1();
            request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/bill/querybasicbill/V1");
            GyjrB2bBillQuerybasicbillRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQuerybasicbillRequestV1.BizContentV1Biz();
            GyjrB2bBillQuerybasicbillRequestV1.TransInfoV1Biz transInfo
                    = new GyjrB2bBillQuerybasicbillRequestV1.TransInfoV1Biz();
            transInfo.setProtocolCode("GYJR20220119160206728375706");
            transInfo.setPlatVendorid("14");
            transInfo.setPageIndex("1");
            transInfo.setPageSize("100");
            transInfo.setUkeyId("123456");
            List<Map> packNoList = new ArrayList<>();
            Map map = new HashMap();
            map.put("acctId","123");
            map.put("rangeBgn","456");
            map.put("packNo","789");
            map.put("rangeEnd","321");
            packNoList.add(map);
            transInfo.setPackNoList(packNoList);
            bizContent.setTransInfo(transInfo);
            request.setBizContent(bizContent);
            GyjrB2bBillQuerybasicbillResponseV1 response;
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
