package com.icbc.api;

import com.icbc.api.request.MybankAccountCorporatewalletApplyProgressQueryRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletApplyProgressQueryResponseV1;

import java.util.UUID;

public class MybankAccountCorporatewalletApplyProgressQueryTestV1 {

    public static void main(String[] args) {
        String APP_ID = "";//APPID，门户获取
        String MY_PRIVATE_KEY = "";//调用方私钥
        String APIGW_PUBLIC_KEY = "";//网关公钥
        String AESKey = "";//调用方加密秘钥
        String MSG_ID = UUID.randomUUID().toString();
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null,null,null,null);
        MybankAccountCorporatewalletApplyProgressQueryRequestV1 request = new MybankAccountCorporatewalletApplyProgressQueryRequestV1();
        request.setServiceUrl("https://ip:port/api/mybank/account/corporatewallet/applyprogressquery/V1");

        MybankAccountCorporatewalletApplyProgressQueryRequestV1.MybankAccountCorporatewalletApplyProgressQueryRequestV1Biz bizContent= new MybankAccountCorporatewalletApplyProgressQueryRequestV1.MybankAccountCorporatewalletApplyProgressQueryRequestV1Biz();
        bizContent.setAgr_no("PTxxxxxx13");
        bizContent.setWork_date("2021-05-25");
        bizContent.setWork_time("10:13:12");
        bizContent.setMac("xx:xx:xx:xx:xx:xx");
        bizContent.setOriginal_serial_no("111xxxxx11");
        bizContent.setBus_serialno("111xxxxx11");
        bizContent.setIp("xxx.xxx.xxx.xxx");
        bizContent.setApplyno("00xxxxxxxxxxxx99");
        bizContent.setApply_serial_no("111xxxxx11");
        request.setBizContent(bizContent);

        MybankAccountCorporatewalletApplyProgressQueryResponseV1 response;
        try {
            response = client.execute(request, MSG_ID);
            if ("0".equals(response.getResult_code())) {
                // 业务成功处理
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response1:" + response.getReturnMsg());
            } else {
                // 失败
                System.out.println("returnCode:"+response.getReturnCode());
                System.out.println("response2:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
