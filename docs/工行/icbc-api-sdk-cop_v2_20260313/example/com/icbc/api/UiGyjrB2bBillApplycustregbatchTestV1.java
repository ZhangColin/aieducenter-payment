package com.icbc.api;
import com.icbc.api.request.UiGyjrB2bBillApplycustregbatchRequestV1;
import java.util.ArrayList;
import java.util.List;
public class UiGyjrB2bBillApplycustregbatchTestV1 {
    protected static final String APP_ID = "******";
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";


    public static void main(String[] args) {
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8");
            UiGyjrB2bBillApplycustregbatchRequestV1 request = new UiGyjrB2bBillApplycustregbatchRequestV1();
            // 根据实际环境替换相应的域名
            request.setServiceUrl("https://ip:port/ui/gyjr/b2b/bill/applycustregbatch/V1");
            UiGyjrB2bBillApplycustregbatchRequestV1.UiGyjrB2bBillApplycustregbatchRequestV1Biz biz = new UiGyjrB2bBillApplycustregbatchRequestV1.UiGyjrB2bBillApplycustregbatchRequestV1Biz();
            UiGyjrB2bBillApplycustregbatchRequestV1.UiGyjrB2bBillApplycustregbatchRequestV1Biz.AcctListInfo acctListInfo = new UiGyjrB2bBillApplycustregbatchRequestV1.UiGyjrB2bBillApplycustregbatchRequestV1Biz.AcctListInfo();
            List<UiGyjrB2bBillApplycustregbatchRequestV1.UiGyjrB2bBillApplycustregbatchRequestV1Biz.AcctListInfo> acctList = new ArrayList<>();
            acctListInfo.setOperAcctId("123456**");
            acctListInfo.setOperType("OT01");
            acctList.add(acctListInfo);
            biz.setProtocolCode("GYJR002002023050903");
            biz.setTrade_name("applyCustRegBatch");
            biz.setTrade_version("V1");
            biz.setPlatVendorId("230001");
            biz.setPlatBatSerialNo("44zy765991");
            biz.setBatchFlag("1");
            biz.setPlatBusiSeq("55sx238991");
            biz.setCustType("120");
            biz.setCustSoccode("100015525000000");
            biz.setCustName("委耽芒垮蹲氛栓殷切绥");
            biz.setDealType("1");
            biz.setCustPriAcc("123456**789");
            biz.setEmail("2fffg@qq.com");
            biz.setIsArg("0");
            biz.setIsGreen("1");
            biz.setIsTech("1");
            biz.setAcctList(acctList);
            biz.setCallBackUrl("http://test.com");
            request.setBizContent(biz);
            String s = client.buildPostForm(request);
            System.out.println(s);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
