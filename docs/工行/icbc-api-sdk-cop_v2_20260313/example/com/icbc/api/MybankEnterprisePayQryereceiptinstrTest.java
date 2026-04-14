package com.icbc.fbase.mngcard.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.MybankEnterprisePayQryereceiptinstrRequestV1;
import com.icbc.api.request.MybankEnterprisePayQryereceiptinstrRequestV1.MybankEnterprisePayQryereceiptinstrRequestBizV1;
import com.icbc.api.response.MybankEnterprisePayQryereceiptinstrResponseV1;

/**
 * 高级新查询批量电子回单指令
 * @author kfzx-yangjiang
 *
 */
public class MybankEnterprisePayQryereceiptinstrTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "/mybank/enterprise/pay/qryereceiptinstr/V1"; 
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterprisePayQryereceiptinstrRequestBizV1 bizContent = new MybankEnterprisePayQryereceiptinstrRequestBizV1();
            MybankEnterprisePayQryereceiptinstrRequestV1 request = new MybankEnterprisePayQryereceiptinstrRequestV1();
            bizContent.setBankCode("102");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterprisePayQryereceiptinstrResponseV1 response = client.execute(request);
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
