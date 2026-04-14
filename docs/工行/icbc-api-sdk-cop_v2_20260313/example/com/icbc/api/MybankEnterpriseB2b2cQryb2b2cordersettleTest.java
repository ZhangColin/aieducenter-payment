package com.icbc.fbase.mngcard.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.MybankEnterpriseB2b2cQryb2b2cordersettleRequestV1;
import com.icbc.api.request.MybankEnterpriseB2b2cQryb2b2cordersettleRequestV1.MybankEnterpriseB2b2cQryb2b2cordersettleRequesBizV1;
import com.icbc.api.response.MybankEnterpriseB2b2cQryb2b2cordersettleResponseV1;

/**
 * B2B、B2C汇总记账清单查询
 * @author kfzx-yangjiang
 *
 */
public class MybankEnterpriseB2b2cQryb2b2cordersettleTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "/mybank/enterprise/b2b2c/qryb2b2cordersettle/V1"; 
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterpriseB2b2cQryb2b2cordersettleRequesBizV1 bizContent = new MybankEnterpriseB2b2cQryb2b2cordersettleRequesBizV1();
            MybankEnterpriseB2b2cQryb2b2cordersettleRequestV1 request = new MybankEnterpriseB2b2cQryb2b2cordersettleRequestV1();
            bizContent.setBankCode("102");
            
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseB2b2cQryb2b2cordersettleResponseV1 response = client.execute(request);
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
