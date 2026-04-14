package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalwalletHdEcpcAccRechargeRequestV1;
import com.icbc.api.response.MybankPayDigitalwalletHdEcpcAccRechargeResponseV1;

import java.util.UUID;

/**
 * 基于大钱包的个人钱包充值
 *
 * @author kfzx-wangc3
 * @date 2022/4/11
 */
public class MybankPayDigitalwalletHdEcpcAccRechargeV1Test {
    public void test() {
        String APP_ID = "";//APPID，门户获取
        String MY_PRIVATE_KEY = "";//调用方私钥
        String APIGW_PUBLIC_KEY = "";//网关公钥        
        String AESKey = "";//调用方加密秘钥        
        String MSG_ID = UUID.randomUUID().toString();
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null, null, null, null);
        MybankPayDigitalwalletHdEcpcAccRechargeRequestV1 request = new MybankPayDigitalwalletHdEcpcAccRechargeRequestV1();
        request.setServiceUrl("https://ip:port/api/mybank/pay/digitalwallet/ecpc/accwalletrecharge/V1");
        MybankPayDigitalwalletHdEcpcAccRechargeRequestV1.MybankPayDigitalwalletHdEcpcAccRechargeRequestV1Biz bizContent = new MybankPayDigitalwalletHdEcpcAccRechargeRequestV1.MybankPayDigitalwalletHdEcpcAccRechargeRequestV1Biz();
        bizContent.setCooperatorNo("1234567890");
        bizContent.setSerialNo("1234567890");
        bizContent.setWorkDate("2022-03-04");
        bizContent.setWorkTime("08:57:10");
        bizContent.setMac("00:AA:00:3F:89:4A");
        bizContent.setIP("ip:port");

        bizContent.setChantype("10");
        bizContent.setAmount("1000");
        bizContent.setCurrtype(1);
        bizContent.setRevtranf(0);
        bizContent.setWalletType("2");
        bizContent.setAccno("100");
        bizContent.setAccname("账户a");

        bizContent.setApduData("13579246800987654321");
        request.setBizContent(bizContent);
        MybankPayDigitalwalletHdEcpcAccRechargeResponseV1 response;
        try {
            response = client.execute(request, MSG_ID);
            if (response.isSuccess()) {
                // 业务成功处理
                // Assert.assertEquals(response.getReturnCode(), 0);
                System.out.println("success");
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
