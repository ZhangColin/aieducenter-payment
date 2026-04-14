package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQuerydiscountbillRequestV1;
import com.icbc.api.response.GyjrB2bBillQuerydiscountbillResponseV1;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GyjrB2bBillQuerydiscountbillTestV1 {

    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000012345";


    public void test() throws IcbcApiException, UnsupportedEncodingException {
        // 构造client对象
        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

        // 设置请求对象request
        GyjrB2bBillQuerydiscountbillRequestV1 request = new GyjrB2bBillQuerydiscountbillRequestV1();
        // 设置请求路径
        request.setServiceUrl("http://ip:port/api/gyjr/b2b/bill/querydiscountbill/V1");

        GyjrB2bBillQuerydiscountbillRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillQuerydiscountbillRequestV1.BizContentV1Biz();
        GyjrB2bBillQuerydiscountbillRequestV1.TransInfoV1Biz  transInfo= new GyjrB2bBillQuerydiscountbillRequestV1.TransInfoV1Biz();

        GyjrB2bBillQuerydiscountbillRequestV1.PackBodyV1Biz  packBody= new GyjrB2bBillQuerydiscountbillRequestV1.PackBodyV1Biz();
        List packBodyList = new ArrayList();
        packBody.setPackNo("555500089892021121606000750002");
        packBody.setRangeBgn("000000000001");
        packBody.setRangeEnd("000000001675");
        packBodyList.add(packBody);

        transInfo.setUkeyId("12345");
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorid("14");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("10");
        transInfo.setHolderAcctId("");
        transInfo.setCdAmtBgn("0");
        transInfo.setCdAmtEnd("100000000000");
        transInfo.setCdTp("AC01");
        transInfo.setIsECDS("0");
        transInfo.setDueDateBgn("20220620");
        transInfo.setDueDateEnd("20230620");
        transInfo.setAccptrBank("");
        transInfo.setSmartFlag("0");
        transInfo.setSmartAmtBgn("");
        transInfo.setSmartAmtEnd("");
        transInfo.setSmtRequiredBills(Arrays.asList("111","222","333"));
        transInfo.setSmtForecloseBills(Arrays.asList("555","666","777"));
        transInfo.setDscntBkAcctsrcv("");
        transInfo.setOrderRule("1");
        transInfo.setPackBodyList(packBodyList);


        bizContent.setTransInfoV1Biz(transInfo);
        request.setBizContent(bizContent);

        GyjrB2bBillQuerydiscountbillResponseV1 response;
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
