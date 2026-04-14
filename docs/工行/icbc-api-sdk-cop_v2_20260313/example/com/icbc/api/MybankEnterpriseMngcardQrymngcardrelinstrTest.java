package com.icbc.fbase.mngcard.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.MybankEnterpriseMngcardQrymngcardrelinstrRequestV1;
import com.icbc.api.request.MybankEnterpriseMngcardQrymngcardrelinstrRequestV1.MybankEnterpriseMngcardQrymngcardrelinstrRequestBizV1;
import com.icbc.api.response.MybankEnterpriseMngcardQrymngcardrelinstrResponseV1;
/**
 * 管家卡对照关系维护指令查询
 * @author kfzx-yangjiang
 *
 */
public class MybankEnterpriseMngcardQrymngcardrelinstrTest {
    
    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "/mybank/enterprise/mngcard/qrymngcardrelinstr/V1"; 
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterpriseMngcardQrymngcardrelinstrRequestBizV1 bizContent = new MybankEnterpriseMngcardQrymngcardrelinstrRequestBizV1();
            MybankEnterpriseMngcardQrymngcardrelinstrRequestV1 request = new MybankEnterpriseMngcardQrymngcardrelinstrRequestV1();
            bizContent.setBankCode("102");
            
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseMngcardQrymngcardrelinstrResponseV1 response = client.execute(request);
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
