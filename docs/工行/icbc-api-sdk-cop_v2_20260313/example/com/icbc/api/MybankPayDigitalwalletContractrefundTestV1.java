package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalwalletContractmgrCancelRequestV1;
import com.icbc.api.request.MybankPayDigitalwalletContractrefundRequestV1;
import com.icbc.api.response.MybankPayDigitalwalletContractrefundResponseV1;
import org.testng.Assert;

public class MybankPayDigitalwalletContractrefundTestV1 {
    public void test() {
        String APP_ID = "";//APPID，门户获取
        String MY_PRIVATE_KEY = "";//调用方私钥
        String APIGW_PUBLIC_KEY = "";//网关公钥
        String AESKey = "";//调用方加密秘钥
        String MSG_ID = "";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null, null, null, null);
        MybankPayDigitalwalletContractrefundRequestV1 request = new MybankPayDigitalwalletContractrefundRequestV1();
        request.setServiceUrl("https://ip:port/api/mybank/pay/digitalwallet/contractrefund/V2");
        MybankPayDigitalwalletContractrefundRequestV1.MybankPayDigitalwalletContractrefundRequestV1Biz bizContent = new MybankPayDigitalwalletContractrefundRequestV1.MybankPayDigitalwalletContractrefundRequestV1Biz();
        bizContent.setChantype(10);
        bizContent.setInstructionId("");
        bizContent.setMerchantId("");
        bizContent.setSubMerchantId("");
        bizContent.setOutTradeNo("");
        bizContent.setOrderId("");
        bizContent.setOutTradeDate("");
        bizContent.setOutTradeNo("");
        bizContent.setBusstype("");
        bizContent.setBankCode("");
        bizContent.setTrandate("");
        bizContent.setTrantime("");
        bizContent.setTotalAmount("");
        bizContent.setAmount("");
        bizContent.setCurrtype("");
        bizContent.setRefundDesc("");
        bizContent.setBak1("");
        bizContent.setBak2("");

        request.setBizContent(bizContent);
        MybankPayDigitalwalletContractrefundResponseV1 response;

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
