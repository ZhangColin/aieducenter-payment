package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalwalletMerchantcontractaddRequestV1;
import com.icbc.api.response.MybankPayDigitalwalletMerchantcontractaddResponseV1;
import org.testng.Assert;

public class MybankPayDigitalwalletMerchantcontractaddTestV1 {
    public void test() {
        String APP_ID = "";//APPID，门户获取
        String MY_PRIVATE_KEY = "";//调用方私钥
        String APIGW_PUBLIC_KEY = "";//网关公钥
        String AESKey = "";//调用方加密秘钥
        String MSG_ID = "";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null, null, null, null);
        MybankPayDigitalwalletMerchantcontractaddRequestV1 request = new MybankPayDigitalwalletMerchantcontractaddRequestV1();
        request.setServiceUrl("https://ip:port/api/mybank/pay/digitalwallet/merchantcontractadd/V1");
        MybankPayDigitalwalletMerchantcontractaddRequestV1.MybankPayDigitalwalletMerchantcontractaddRequestV1Biz bizContent = new MybankPayDigitalwalletMerchantcontractaddRequestV1.MybankPayDigitalwalletMerchantcontractaddRequestV1Biz();
        bizContent.setChantype(10);
        bizContent.setInstructionId("");
        bizContent.setMerchantId("");
        bizContent.setSubMerchantId("");
        bizContent.setBusstype("");
        bizContent.setBusscode("");
        bizContent.setCollectType("");
        bizContent.setAccBankcode("");
        bizContent.setAccNo("");
        bizContent.setBak1("");
        bizContent.setBak2("");

        request.setBizContent(bizContent);
        MybankPayDigitalwalletMerchantcontractaddResponseV1 response;

        try {
            response = client.execute(request, MSG_ID);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            Assert.fail();
        }
    }
}
