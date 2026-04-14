package com.icbc.api;

import com.icbc.api.request.MybankPayGyjrB2bCashdetailqueryRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bCashdetailqueryResponseV1;

import java.util.Random;

public class MybankPayGyjrB2bCashdetailqueryTestV1 {
    protected static final String MY_PRIVATE_KEY = "XXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankPayGyjrB2bCashdetailqueryRequestV1 request = new MybankPayGyjrB2bCashdetailqueryRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/gyjr/b2b/memauth/V1");
        MybankPayGyjrB2bCashdetailqueryRequestV1.CashdetailqueryBizContentV1Biz bizContent =
                new MybankPayGyjrB2bCashdetailqueryRequestV1.CashdetailqueryBizContentV1Biz();
        MybankPayGyjrB2bCashdetailqueryRequestV1.CashdetailqueryTransInfoV1Biz transInfo =
                new MybankPayGyjrB2bCashdetailqueryRequestV1.CashdetailqueryTransInfoV1Biz();

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        System.out.println(msgId);

        transInfo.setProtocolCode("12345");
        transInfo.setBusinessNo("12345");
        transInfo.setPlatVendorid("12345");
        transInfo.setOrganizationCode("12345");
        transInfo.setQueryType("001");
        transInfo.setAgreeCode("0020000998140010137438000000097012");
        transInfo.setOrderNo("2019062730001");
        transInfo.setBaginDate("20210506000000");
        transInfo.setEndDate("20210506235959");
        transInfo.setBegNum("1");
        transInfo.setEndDate("10");

        bizContent.setTransInfo(transInfo);

        request.setBizContent(bizContent);

        MybankPayGyjrB2bCashdetailqueryResponseV1 response = new MybankPayGyjrB2bCashdetailqueryResponseV1();

        try {
            response = client.execute(request, msgId);
            System.out.println("response:" + response.toString());
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");//
            } else {
                // 失败
                System.out.println("error");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
