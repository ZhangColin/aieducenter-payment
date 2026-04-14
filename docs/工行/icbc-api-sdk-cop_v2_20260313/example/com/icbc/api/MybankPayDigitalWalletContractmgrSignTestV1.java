package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalWalletContractmgrSignReqeustV1;
import com.icbc.api.response.MybankPayDigitalWalletContractmgrSignResponseV1;
import org.testng.Assert;

public class MybankPayDigitalWalletContractmgrSignTestV1 {
    public void test() {
        String APP_ID = "";//APPID，门户获取
        String MY_PRIVATE_KEY = "";//调用方私钥
        String APIGW_PUBLIC_KEY = "";//网关公钥
        String AESKey = "";//调用方加密秘钥
        String MSG_ID = "";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null, null, null, null);
        MybankPayDigitalWalletContractmgrSignReqeustV1 request = new MybankPayDigitalWalletContractmgrSignReqeustV1();
        request.setServiceUrl("https://ip:port/api/mybank/pay/digitalwallet/contractmgr/sign/V1");
        MybankPayDigitalWalletContractmgrSignReqeustV1.MybankPayDigitalWalletContractmgrSignReqeustV1Biz bizContent = new MybankPayDigitalWalletContractmgrSignReqeustV1.MybankPayDigitalWalletContractmgrSignReqeustV1Biz();
        bizContent.setChannel(10);
        bizContent.setInstructionId("");
        bizContent.setOrderId("");
        bizContent.setMerchantId("");
        bizContent.setSubMerchantId("");
        bizContent.setWalletToken("");
        bizContent.setPhoneNo("");
        bizContent.setVerifyInfo("");
        bizContent.setAuthCode("");
        bizContent.setBak1("");
        bizContent.setBak2("");

        request.setBizContent(bizContent);
        MybankPayDigitalWalletContractmgrSignResponseV1 response;

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
