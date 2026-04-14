package com.icbc.api;

import com.icbc.api.request.MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryRequestV1;
import com.icbc.api.request.MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryRequestV1.MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryRequesBizV1;
import com.icbc.api.response.MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryResponseV1;

/**
 * 高级批量扣个人缴费协议删除指令查询接口QENDIDEL
 * 
 * @author kfzx-yinfc
 *
 */

public class MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryTest {
    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/pay/batchpersonaldeduct/mepbprotocoldelinstrqry/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryRequesBizV1 bizContent =
                    new MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryRequesBizV1();
            MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryRequestV1 request =
                    new MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryRequestV1();
            bizContent.setTransCode("");
            bizContent.setBankCode("102");
            bizContent.setCis("");
            bizContent.setLoginId("");
            bizContent.setTranDate("");
            bizContent.setTranTime("");
            bizContent.setfSeqno("");
            bizContent.setQryfSeqno("");
            bizContent.setQrySerialNo("");


            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterprisePayBatchpersonaldeductMepbprotocoldelinstrqryResponseV1 response =
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
