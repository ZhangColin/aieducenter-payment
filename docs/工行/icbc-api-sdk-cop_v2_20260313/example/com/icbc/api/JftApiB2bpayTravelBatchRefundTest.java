package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiB2bPayTravelBatchRefundRequestV1;
import com.icbc.api.response.JftApiB2bPayTravelBatchRefundResponseV1;

public class JftApiB2bPayTravelBatchRefundTest {
    @org.junit.Test
    public void test() throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String icbcPulicKey = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        String host = "[需替换]工行API网关地址";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON,
                icbcPulicKey, IcbcConstants.ENCRYPT_TYPE_AES, AES_Key, null, null);
        JftApiB2bPayTravelBatchRefundRequestV1 request = new JftApiB2bPayTravelBatchRefundRequestV1();
        request.setServiceUrl("https://gw.open.icbc.com.cn/api/jft/api/b2bpay/travelBatchRefund/V1");
        JftApiB2bPayTravelBatchRefundRequestV1.JftApiB2bPayTravelBatchRefundRequestV1Biz bizContent = new  JftApiB2bPayTravelBatchRefundRequestV1.JftApiB2bPayTravelBatchRefundRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutVendorId("100110101202102");
        bizContent.setOutUserId("L112120550");
        bizContent.setNotifyUrl("xxx.com");
        bizContent.setOriOutOrderId("50215602302302050");
        bizContent.setOutRefundId("0020312302312023");
        bizContent.setRefundAmount("100.00");
        bizContent.setTradeTime("2025-02-27");
        bizContent.setAcAcctNum("622154121515152415");
        bizContent.setAcAcctName("发明考虑离开");
        bizContent.setAcBankNo("56121231");
        bizContent.setAcBankName("发明考虑离开");
        request.setBizContent(bizContent);
        JftApiB2bPayTravelBatchRefundResponseV1 responseV1 = client.execute(request);
        System.out.println(responseV1);
    }
}
