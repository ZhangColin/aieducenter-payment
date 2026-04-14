package com.icbc.api;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQrymultidiscountpriceRequestV1;
import com.icbc.api.response.GyjrB2bBillQrymultidiscountpriceResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
/**
 * @Title :
 * @Description :
 * @Author : 付海军
 * @Date : 2023/11/27
 * @Version : 1.0
 */

public class GyjrB2bBillQrymultidiscountpriceRequestTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCpqf7bEyV4Rszt+VkMt05214iZfhJrUf51vA5aLEhWPWa6JJpqJEL0wBsMIZrTwF1Y7CWsnUXgxfX//CBtQTIe4As6WQFBCdglKyf2XgDqeFBoDQu+IAx5PyZSzLDbFSLtlrVNYLbeqxYvOtHJx5BCR15uGJVnxWvH17NHGxY1T4+subdHcT2Q+1upMSPw08D6oHTuyRJS/EQvqNC1S5Xxye4hfft5fWQMbwYU87JxMGaRQm5UjRIKvydqYsrenTw6vneIRzAmcJvTSfQ7G3e+QUnmPnsOMHZvAGCeEgjHan1Zp84y/YDCQB/juMjPtTFZztA/D1tyN5ftB/sUOWpVAgMBAAECggEAbMEMHS+LsCpqCAzyWOVTRXhpFkKq2eIoIx3VcVW7VJRtOdpmpKzzwmE1tTQBQ8Sl0W8lwxzZAvjB9EGQI5hL4WCpsdAFrsP5qfGc3x5opeNTXy8lnMx8Umf5T8/nebd1uSgGtT/OWLMAOGwSt63VObb3v8jAVFFHAkWwpF6ngKme7sjig7HjQmLkALNWXoYlTPq9Yy3kPYQPGRKTuMPzWPqALs0hBaaXyoFv1Ixd+ZqEHIxJrVnr5ycZZldC3Tz0cs1worSE6rudEeFXgJZkcu7OmiT1/ajenj1IC+7z8nllzzh7F2AjVPzXiva7pyUwqnVL0UzlBAyYTrUjR1ZjTQKBgQDrwjXVp1Tj/9xrooaI56yOl32db8Na+g4iRiJFRKg/qoFyVgTss6UJgG6DDcg/6+V0Ftyq+iMJ9wYK2VQ0pgDF/fS53tOWR6aKFsREbdcptTnay+xtweUU8Uvx4AGSezrqTJBXi64j7JsvnNI5g3lU59DypVkhSXCjQ44kyr5iCwKBgQC4OxP7lQFRRXhI0r56PTLAgllWa8zk0VzeH1yA/75sgnGnN+Tr380xaYiITjF7teONnGfCsn4DEs8Z6cAI5lmsnXZ+vb5QocFf24zNVuxc8fSyr1BQeUfc6SjkWRHPQRShpHBUU8qQwQu1ot/p4oRqp8oESxPViu88A5ccxcyBHwKBgQCk/tSCZItqzeRLiAEzyz/ew3LsPg2wnBRMNF1DsRs3A3N4u27S4Z79GV8+ZOTip4t8RYvGNS1Uigc2jIb8Gbms12DwvFvEbvzE484UPfM5g1WsJM+guPREReNqseUnyQqgc1kJi/4hsjOhjNUr3zGJFHwNdV79htJH0L2oTiQTpQKBgElWs1C+c2TP+985jbwJqq959Ghzx3/7XDXMVD3CESMPFoFTvdysHfoiochpIEe4E5rm/WNDPOiqTylopRql4GLX2DkB/cYJbnpjYvzHMpaZdyi+o4E1lob5hl7Imk9f//+M/26CYKwZPTkUWoC3vLDclI36+S1T/oJsh3Zbw1pPAoGABc5jN0aBXsvUt8AXnUfx9wNPHHnmY55QrvIVqRZZwLT7adGwcfIplx0O+z7W4epa559I4c+DS/6UwvTRiEIy0UY19vAVlgCfpWqcfS/ZP3OywYK0jHy+1CmBkbsU35HU9rI5uz4nBpoahO40qmHFRO9WK6lQojGrWFLBl0o+aPM=";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16" +
            "bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL" +
            "T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP" +
            "3CiNgg0W6O3AGqwIDAQAB";
    protected static final String APP_ID = "11000000000000002142";

    static Logger log = Logger.getLogger(GyjrB2bBillQrymultidiscountpriceRequestTestV1.class.getName());
    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        GyjrB2bBillQrymultidiscountpriceRequestV1 req = new GyjrB2bBillQrymultidiscountpriceRequestV1();

        req.setServiceUrl("http://ip:port/api/gyjr/b2b/bill/qrymultidiscountprice/V1");

        GyjrB2bBillQrymultidiscountpriceRequestV1.GyjrB2bBillQrymultidiscountpriceRequestV1Biz bizContent = new GyjrB2bBillQrymultidiscountpriceRequestV1.GyjrB2bBillQrymultidiscountpriceRequestV1Biz();

        GyjrB2bBillQrymultidiscountpriceRequestV1.TransinfoBiz transInfo = new GyjrB2bBillQrymultidiscountpriceRequestV1.TransinfoBiz();

        GyjrB2bBillQrymultidiscountpriceRequestV1.QueryInfo queryInfo = new GyjrB2bBillQrymultidiscountpriceRequestV1.QueryInfo();
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

        queryInfo.setQueryList(queryListInfo);

        queryInfo.setOperType("match");
        queryInfo.setQueryList(queryListInfo);

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
