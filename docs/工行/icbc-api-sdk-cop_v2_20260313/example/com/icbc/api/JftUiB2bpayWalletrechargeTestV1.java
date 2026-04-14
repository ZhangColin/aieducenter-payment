package com.icbc.api;

import com.icbc.api.request.JftUiB2bpayWalletrechargeRequestV1;

public class JftUiB2bpayWalletrechargeTestV1 {
    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "10000000000000212516";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        String host = "[需替换]工行API网关地址";
        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.ENCRYPT_TYPE_AES, AES_Key);
        JftUiB2bpayWalletrechargeRequestV1 request = new JftUiB2bpayWalletrechargeRequestV1();
        request.setServiceUrl(host+"/ui/jft/ui/b2bpay/walletrecharge/V1");
        JftUiB2bpayWalletrechargeRequestV1.JftUiB2bpayWalletrechargeRequestV1Biz bizContent = new JftUiB2bpayWalletrechargeRequestV1.JftUiB2bpayWalletrechargeRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutUserId("04221125");
        bizContent.setNotifyUrl("http://ip:port");
        bizContent.setOutOrderId("04221125");
        bizContent.setTradeTime("20180710142122");
        bizContent.setPayAmount("1115.01");

        request.setBizContent(bizContent);
        String form = client.buildPostForm(request);
        System.out.println(form);
    }
}
