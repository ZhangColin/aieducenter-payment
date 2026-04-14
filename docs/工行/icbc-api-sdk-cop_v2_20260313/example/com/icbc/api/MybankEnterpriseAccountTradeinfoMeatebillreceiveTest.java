package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseAccountTradeinfoMeatebillreceiveRequestV1;
import com.icbc.api.request.MybankEnterpriseAccountTradeinfoMeatebillreceiveRequestV1.MybankEnterpriseAccountDownebillRequesBizV1;
import com.icbc.api.response.MybankEnterpriseAccountTradeinfoMeatebillreceiveResponseV1;

/**
 * 电子回单下载DownEBill
 * 
 * @author kfzx-yinfc
 *
 */

public class MybankEnterpriseAccountTradeinfoMeatebillreceiveTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址
    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/account/tradeinfo/meatebillreceive/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterpriseAccountDownebillRequesBizV1 bizContent =
                    new MybankEnterpriseAccountDownebillRequesBizV1();
            MybankEnterpriseAccountTradeinfoMeatebillreceiveRequestV1 request =
                    new MybankEnterpriseAccountTradeinfoMeatebillreceiveRequestV1();
            bizContent.setTransCode("");
            bizContent.setBankCode("102");
            bizContent.setCis("");
            bizContent.setLoginId("");
            bizContent.setTranDate("");
            bizContent.setTranTime("");
            bizContent.setfSeqno("");

            bizContent.setAreaCode("");
            bizContent.setNetCode("");
            bizContent.setTellerNo("");
            bizContent.setWorkDate("");
            bizContent.setTranSerialNo("");
            bizContent.setAcctNo("");
            bizContent.setCurrencyType("");

            bizContent.setReqReserved1("");
            bizContent.setReqReserved2("");
            bizContent.setReqReserved3("");
            bizContent.setReqReserved4("");
            bizContent.setfSeqno("");


            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseAccountTradeinfoMeatebillreceiveResponseV1 response =
                    client.execute(request);
            if (response.isSuccess()) {
                System.out.println("success");//
            } else {
                System.out.println("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
