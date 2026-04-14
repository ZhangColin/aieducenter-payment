package com.icbc.api;

import com.icbc.api.request.MybankPayDigitalwalletHdEcpcCashRechargeQueryRequestV1;
import com.icbc.api.response.MybankPayDigitalwalletHdEcpcCashRechargeQueryResponseV1;

import java.util.UUID;

/**
 * @author kfzx-laigq
 * @date 2022/3/3
 */
public class MybankPayDigitalwalletHdEcpcCashRechargeQueryV1Test {
    public void test() {
        String APP_ID = "";//APPID，门户获取
        String MY_PRIVATE_KEY = "";//调用方私钥
        String APIGW_PUBLIC_KEY = "";//网关公钥
        String AESKey = "";//调用方加密秘钥
        String MSG_ID = UUID.randomUUID().toString();
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null,null,null,null);
        MybankPayDigitalwalletHdEcpcCashRechargeQueryRequestV1 request = new MybankPayDigitalwalletHdEcpcCashRechargeQueryRequestV1();
        request.setServiceUrl("https://ip:port/api/mybank/pay/digitalwallet/ecpc/hdcashrechargequery/V1");
        MybankPayDigitalwalletHdEcpcCashRechargeQueryRequestV1.MybankPayDigitalwalletHdEcpcCashRechargeQueryRequestV1Biz bizContent = new MybankPayDigitalwalletHdEcpcCashRechargeQueryRequestV1.MybankPayDigitalwalletHdEcpcCashRechargeQueryRequestV1Biz();
        bizContent.setCooperatorNo("1234567890");
        bizContent.setSerialNo("1234567890");
        bizContent.setWorkDate("2022-03-04");
        bizContent.setWorkTime("08:57:10");
        bizContent.setMac("00:AA:00:3F:89:4A");
        bizContent.setIP("ip:port");
        //
        //bizContent.setCooperatorNo("1234567890");
        //bizContent.setContextId("13579246800987654321");
        //bizContent.setWalletId("10001");
        //bizContent.setWalletType("1234567890");
        request.setBizContent(bizContent);
        MybankPayDigitalwalletHdEcpcCashRechargeQueryResponseV1 response;
        try {
            response = client.execute(request,MSG_ID);
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
