package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillApplyWithDrawRequestV1;
import com.icbc.api.response.GyjrB2bBillApplyWithDrawResponseV1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GyjrB2bBillApplyWithDrawTestV1 {


    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000123456";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillApplyWithDrawRequestV1 request = new GyjrB2bBillApplyWithDrawRequestV1();
        request.setServiceUrl("http://IP:PORT/ui/gyjr/b2b/bill/applywithdraw/V1");
        GyjrB2bBillApplyWithDrawRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillApplyWithDrawRequestV1.BizContentV1Biz();


        bizContent.setTrade_name("applyWithDraw");
        bizContent.setTrade_version("V1");
        bizContent.setProtocolCode("GYJR20220119160206728375706");
        bizContent.setPlatVendorId("14");
        bizContent.setPlatBatSerialNo("9963552352205120520212511");
        bizContent.setTotalAmt("1");
        bizContent.setTotalCount("1");
        bizContent.setCallBackUrl("http://.....");
        bizContent.setBatFlag("1");
        bizContent.setBusinessFlag("1");
        bizContent.setApplyBatSerialNo("4338474783927520");
        List<GyjrB2bBillApplyWithDrawRequestV1.BillBiz> billList = new ArrayList<>();
        GyjrB2bBillApplyWithDrawRequestV1.BillBiz bill = new GyjrB2bBillApplyWithDrawRequestV1.BillBiz();

        bill.setPlatBusiSeq("");
        bill.setPackNo("");
        bill.setRangeBgn("");
        bill.setRangeEnd("");
        bill.setCdTp("");
        bill.setCdTpCn("");
        bill.setRangeAmt("");
        bill.setDueDate("");
        bill.setAcctId("");
        bill.setOrgName("");
        bill.setAccptrName("");
        bill.setDrwrName("");
        bill.setPayeeName("");
        bill.setBusinessType("");
        bill.setBusinessTypeCn("");
        bill.setRespAcctId("");
        bill.setRespBranch("");

        billList.add(bill);
        bizContent.setBillList(billList);
        request.setBizContent(bizContent);

        GyjrB2bBillApplyWithDrawResponseV1 response;
        try {
            response = client.execute(request,new Random().nextInt(99999999) + "msg");
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
