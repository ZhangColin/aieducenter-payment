package com.icbc.api.test;

import com.icbc.api.*;
import com.icbc.api.request.JftApiB2bpayTransqueryRequestV1;
import com.icbc.api.request.JftApiPayB2bPayDepositrefundRequestV1;
import com.icbc.api.response.JftApiB2bpayTransqueryResponseV1;
import com.icbc.api.response.JftApiPayB2bPayDepositrefundResponseV1;

public class JftApiPayB2bPayDepositrefundTestV1 {

    public static void main(String[] args) throws IcbcApiException {
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES,AES_Key, "", "");
        JftApiPayB2bPayDepositrefundRequestV1 request=new JftApiPayB2bPayDepositrefundRequestV1();
        String host = "[需替换]工行API网关地址";
        request.setServiceUrl(host+"/api/jft/api/pay/b2bpay/refund/V1");
        JftApiPayB2bPayDepositrefundRequestV1.JftApiPayB2bPayDepositrefundRequestV1Biz bizContent=new JftApiPayB2bPayDepositrefundRequestV1.JftApiPayB2bPayDepositrefundRequestV1Biz();

        bizContent.setAppId("10000000000007354589");

        bizContent.setOutUserId("71810240115");
        bizContent.setNotifyUrl("http://www.ceshi.com");
        bizContent.setOutRefundId("20190422131212222");
        bizContent.setRefundAmount("11.11");
        bizContent.setTradeTime("2019-04-22 11:11:11");
        bizContent.setPayBankNo("");
        bizContent.setPayBankName("");
        bizContent.setPayAcctNum("");
        bizContent.setPayAcctName("");
        bizContent.setRecAcctNum("");
        bizContent.setRefundReason("");
        bizContent.setRemark("");



        request.setBizContent(bizContent);

        JftApiPayB2bPayDepositrefundResponseV1 response;
        try {
            response =(JftApiPayB2bPayDepositrefundResponseV1) client.execute(request, System.currentTimeMillis()+"11");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
