package com.icbc.api;

import com.icbc.api.request.GyjrB2bBillQrymultidiscountpriceRequestV1;
import com.icbc.api.request.GyjrB2bBillQrymultidiscountpriceRequestV1.GyjrB2bBillQrymultidiscountpriceRequestV1Biz;
import com.icbc.api.response.GyjrB2bBillQrymultidiscountpriceResponseV1;

public class GyjrB2bBillQrymultidiscountpriceTest {

    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    protected static final String APP_ID = "11000000000000002142";

    static Logger log = Logger.getLogger(GyjrB2bBillQrymultidiscountpriceTest.class.getName());
    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        GyjrB2bBillQrymultidiscountpriceRequestV1 req = new GyjrB2bBillQrymultidiscountpriceRequestV1();

        req.setServiceUrl("http://xxx.xxx.xxx.xxx:8081/api/gyjr/b2b/bill/qrymultidiscountprice/V1");

        GyjrB2bBillQrymultidiscountpriceRequestV1.GyjrB2bBillQrymultidiscountpriceRequestV1Biz bizContent = new GyjrB2bBillQrymultidiscountpriceRequestV1.GyjrB2bBillQrymultidiscountpriceRequestV1Biz();

        GyjrB2bBillQrymultidiscountpriceRequestV1.TransinfoBiz transInfo = new GyjrB2bBillQrymultidiscountpriceRequestV1.TransinfoBiz();

        GyjrB2bBillQrymultidiscountpriceRequestV1.QueryInfo queryInfo = new GyjrB2bBillQrymultidiscountpriceRequestV1.QueryInfo();
        List<GyjrB2bBillQrymultidiscountpriceRequestV1.QueryListInfo> queryList = new ArrayList<>();
        GyjrB2bBillQrymultidiscountpriceRequestV1.QueryListInfo queryListInfo = new GyjrB2bBillQrymultidiscountpriceRequestV1.QueryListInfo();
        queryListInfo.setCdTp("AC01");
        queryListInfo.setPrice("1000000");
        queryListInfo.setValidEndDate("20251231");
        queryListInfo.setAccepterType("");
        queryListInfo.setAccepterCode("");
        List<String> accepterNameList = new ArrayList<>();
        accepterNameList.add("中国工商银行");
        accepterNameList.add("中国建设银行");
        accepterNameList.add("中国天地银行");
        queryListInfo.setAccepterNameList(accepterNameList);
        queryListInfo.setAccRsc("0");
        queryListInfo.setBeginNum("1");
        queryListInfo.setFetchRow("10");
        queryList.add(queryListInfo);
        queryInfo.setQueryList(queryList);

        queryInfo.setOperType("match");
        queryList.add(queryListInfo);
        queryInfo.setQueryList(queryList);

        transInfo.setSerialNo("0231545150");
        transInfo.setProtocolCode("GYJR1000000000001234");
        transInfo.setOrgCode("");
        transInfo.setOrgName("");
        transInfo.setOrgCreditType("");
        transInfo.setCustNo("15202312321023");
        transInfo.setQueryInfo(queryInfo);

        bizContent.setTransInfoBiz(transInfo);
        req.setBizContent(bizContent);

        GyjrB2bBillQrymultidiscountpriceResponseV1 response;

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        try {
            response = client.execute(req,msgId);

            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getReturnCode():"+response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():"+response.getReturnCode());
                log.fine("response.getReturnMsg():"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
