package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalwalletContractpayRequestV1;
import com.icbc.api.response.MybankPayDigitalwalletContractpayResponseV1;
import org.testng.Assert;

public class MybankPayDigitalwalletContractpayTestV1 {
    public void test() {
        String APP_ID = "";//APPID，门户获取
        String MY_PRIVATE_KEY = "";//调用方私钥
        String APIGW_PUBLIC_KEY = "";//网关公钥
        String AESKey = "";//调用方加密秘钥
        String MSG_ID = "";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null, null, null, null);
        MybankPayDigitalwalletContractpayRequestV1 request = new MybankPayDigitalwalletContractpayRequestV1();
        request.setServiceUrl("https://ip:port/api/mybank/pay/digitalwallet/contractpay/V1");
        MybankPayDigitalwalletContractpayRequestV1.MybankPayDigitalwalletContractpayRequestV1Biz bizContent = new MybankPayDigitalwalletContractpayRequestV1.MybankPayDigitalwalletContractpayRequestV1Biz();
        bizContent.setChantype(1);
        bizContent.setInstructionId("1");
        bizContent.setBankCode("1");
        bizContent.setToken("1");
        bizContent.setMerchantId("1");
        bizContent.setSubMerchantId("1");
        bizContent.setAttach("1");
        bizContent.setCommodity("1");
        bizContent.setDetail("1");
        bizContent.setBusstype("1");
        bizContent.setBusscode("1");
        bizContent.setOutTradeNo("1");
        bizContent.setExpireMinutes(1);
        bizContent.setTrandate("1");
        bizContent.setTrantime("1");
        bizContent.setAmount("1");
        bizContent.setCurrtype("1");
        bizContent.setGoodsTag("1");
        bizContent.setTermId("1");
        bizContent.setTermIp("1");
        bizContent.setReceipt("1");
        bizContent.setSceneInfo("1");
        bizContent.setSign("1");
        bizContent.setBak1("1");
        bizContent.setBak2("1");

        request.setBizContent(bizContent);
        MybankPayDigitalwalletContractpayResponseV1 response;

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
