package com.icbc.api;
import com.icbc.api.request.AtpEpccCfsTransRequestV1;
import com.icbc.api.response.AtpEpccCfsTransResponseV1;
/**
 * @author kfzx-chengx1
 * @date 2024-11-25 11:19:08
 * @description 2412C ICfsTransServInf-银行主动发起付款服务-测试
 */
public class AtpEpccCfsTransV1Test {
    private static final String APP_ID = "xxx";
    private static final String MY_PRIVATE_KEY = "yyy";
    private static final String APIGW_PUBLIC_KEY = "zzz";
    private static final String BASE_URL = "http://ip:port/api";
    private static final String SERVICE_URI = "/atp/epcc/cfstrans/V1";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        AtpEpccCfsTransRequestV1 request = new AtpEpccCfsTransRequestV1();
        request.setServiceUrl(BASE_URL + SERVICE_URI);
        AtpEpccCfsTransRequestV1.AtpEpccCfsTransRequestV1Biz bizContent =
                new AtpEpccCfsTransRequestV1.AtpEpccCfsTransRequestV1Biz();
        bizContent.setAppId("F-SAES");
        bizContent.setCorpDate("20240102");
        bizContent.setCorpTime("080000");
        bizContent.setCorpSerno("202401020800003000725645729");
        bizContent.setTrxDevcInf("");
        bizContent.setPyerMrchntShrtNm("商户**");
        bizContent.setTrxPrps("0001");
        bizContent.setPyeeBkId("");
        bizContent.setTrxDtTm("20241031041645");
        bizContent.setTrxUstrd("123");
        bizContent.setTrxTrmTp("99");
        bizContent.setTrxSmmry("交易摘要");
        bizContent.setPayment("1234567");
        bizContent.setTrxTrmNo("");
        bizContent.setPyerMrchntNm("商户**");
        bizContent.setClearBankNo("123456");
        bizContent.setPyerAcctTp("05");
        bizContent.setPyerMrchntNo("12345477");
        bizContent.setTrxScene("1000");
        bizContent.setPyerAcctNo("1234544486786");
        bizContent.setPyerAcctNm("**账户");
        bizContent.setTrxCtgy("0120");
        bizContent.setPyeeBkNm("**公司");
        bizContent.setPyeeBkNo("1234");
        bizContent.setUseData("1-1234");
        bizContent.setBizTp("300001");
        bizContent.setCurrtype("001");
        request.setBizContent(bizContent);
        AtpEpccCfsTransResponseV1 response;

        try {
            response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response);
            } else {
                // 业务失败处理
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
