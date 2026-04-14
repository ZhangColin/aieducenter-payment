package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiB2bpayBudgetRegistRequestV1;
import com.icbc.api.response.JftApiB2bpayDepositregistResponseV1;

import java.util.ArrayList;
import java.util.List;

public class JftApiB2bpayBudgetRegisterTestV1 {
    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,
                MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,
                IcbcConstants.ENCRYPT_TYPE_AES, AES_Key, "", "");
        JftApiB2bpayBudgetRegistRequestV1 request = new JftApiB2bpayBudgetRegistRequestV1();
        String host = "[需替换]工行API网关地址";
        request.setServiceUrl(host + "/api/jft/api/pay/b2bpay/preOrderRegister/V1");
        JftApiB2bpayBudgetRegistRequestV1.JftApiB2bpayBudgetRegistRequestV1Biz
                bizContent = new JftApiB2bpayBudgetRegistRequestV1.JftApiB2bpayBudgetRegistRequestV1Biz();
        bizContent.setAppId("[需替换]app_id");
        bizContent.setOutUserId("55555");
        bizContent.setProjectId("9999");
        bizContent.setAmount("66666");
        bizContent.setOutVendorId("66666");
        bizContent.setSceneType("03");
        bizContent.setTradeTime("2019‐04‐22 11:11:11");
        bizContent.setPayerAcctNum("555555");
        bizContent.setPayerName("5555");
        bizContent.setWtAcctNum("666611");
        bizContent.setWtAcctName("666611111");
        bizContent.setWtBankName("66661111");
        bizContent.setWtBankNo("6666");
        bizContent.setProjectName("6666");
        bizContent.setNotifyUrl("6666");
        List<JftApiB2bpayBudgetRegistRequestV1.OriTrx> oriTrxList = new ArrayList<>();
        JftApiB2bpayBudgetRegistRequestV1.OriTrx oriTrx = new JftApiB2bpayBudgetRegistRequestV1.OriTrx();
        oriTrx.setOriSerialNo("1223334444");
        oriTrx.setOriAmount("0.01");
        oriTrx.setDetailNo("12312");
        oriTrxList.add(oriTrx);
        bizContent.setOriTrxList(oriTrxList);
        request.setBizContent(bizContent);
        JftApiB2bpayDepositregistResponseV1 response;
        try {
            response = (JftApiB2bpayDepositregistResponseV1) client.execute(request, System.currentTimeMillis() + "11");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //成功
            } else {
                //失败
            }
        } catch (IcbcApiException e) {
           //异常
        }
    }
}
