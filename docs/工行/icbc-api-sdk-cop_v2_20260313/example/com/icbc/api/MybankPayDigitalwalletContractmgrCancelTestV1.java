package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalWalletContractmgrSignReqeustV1;
import com.icbc.api.request.MybankPayDigitalwalletContractmgrCancelRequestV1;
import com.icbc.api.response.MybankPayDigitalWalletContractmgrSignResponseV1;
import com.icbc.api.response.MybankPayDigitalwalletContractmgrCancelResponseV1;
import org.testng.Assert;

public class MybankPayDigitalwalletContractmgrCancelTestV1 {

    public void test() {
        String APP_ID = "";//APPID，门户获取
        String MY_PRIVATE_KEY = "";//调用方私钥
        String APIGW_PUBLIC_KEY = "";//网关公钥
        String AESKey = "";//调用方加密秘钥
        String MSG_ID = "";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null, null, null, null);
        MybankPayDigitalwalletContractmgrCancelRequestV1 request = new MybankPayDigitalwalletContractmgrCancelRequestV1();
        request.setServiceUrl("https://ip:port/api/mybank/pay/digitalwallet/contractmgr/cancel/V1");
        MybankPayDigitalwalletContractmgrCancelRequestV1.MybankPayDigitalwalletContractmgrCancelRequestV1Biz bizContent = new MybankPayDigitalwalletContractmgrCancelRequestV1.MybankPayDigitalwalletContractmgrCancelRequestV1Biz();
        bizContent.setChannel(10);
        bizContent.setInstructionId("");
        bizContent.setPayToken("");
        bizContent.setBak1("");
        bizContent.setBak2("");

        request.setBizContent(bizContent);
        MybankPayDigitalwalletContractmgrCancelResponseV1 response;

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
