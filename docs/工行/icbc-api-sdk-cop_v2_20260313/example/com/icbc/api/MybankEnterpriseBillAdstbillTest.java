package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseBillAdstbillRequestV1;
import com.icbc.api.request.MybankEnterpriseBillAdstbillRequestV1.MybankEnterpriseBillAdstbillRequestBizV1;
import com.icbc.api.response.MybankEnterpriseBillAdstbillResponseV1;

public class MybankEnterpriseBillAdstbillTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/ADSTBILL/adstbill/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {
            MybankEnterpriseBillAdstbillRequestBizV1 bizContent =
                    new MybankEnterpriseBillAdstbillRequestBizV1();
            MybankEnterpriseBillAdstbillRequestV1 request = new MybankEnterpriseBillAdstbillRequestV1();

            MybankEnterpriseBillAdstbillRequestRdV1 rd = new MybankEnterpriseBillAdstbillRequestRdV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("ADSTBILL");
            bizContent.setTranDate("20190101");
            bizContent.setTranTime("103231001");
            bizContent.setLanguage("zh_CN");
            bizContent.setfSeqNo("ADSTBILL2142580683");
            bizContent.setRemark("null");
            rd.setDrwrAcctId("0200003309004709145");
            rd.setRangeBgn("000000000001");
            rd.setRangeEnd("000000006660");
            rd.setPackNo("100000296020260301000000072202");
            request.setServiceUrl(BASE_URL + "/" + URI);
            bizContent.setRd(rd);
            request.setBizContent(bizContent);
            MybankEnterpriseBillAdstbillResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");
            } else {
                // 失败
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
