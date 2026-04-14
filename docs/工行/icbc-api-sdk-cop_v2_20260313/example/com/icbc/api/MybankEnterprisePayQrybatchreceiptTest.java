package com.icbc.fbase.mngcard.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.MybankEnterprisePayQrybatchreceiptRequestV1;
import com.icbc.api.request.MybankEnterprisePayQrybatchreceiptRequestV1.MybankEnterprisePayQrybatchreceiptRequesBizV1;
import com.icbc.api.response.MybankEnterprisePayQrybatchreceiptResponseV1;

/**
 * 高级新电子回单批量查询
 * @author kfzx-yangjiang
 *
 */
public class MybankEnterprisePayQrybatchreceiptTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "/mybank/enterprise/pay/qrybatchreceipt/V1"; 
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterprisePayQrybatchreceiptRequesBizV1 bizContent = new MybankEnterprisePayQrybatchreceiptRequesBizV1();
            MybankEnterprisePayQrybatchreceiptRequestV1 request = new MybankEnterprisePayQrybatchreceiptRequestV1();
            bizContent.setBankCode("102");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterprisePayQrybatchreceiptResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println("success");
            } else {
                System.out.println("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
