package com.icbc.api;


import com.icbc.api.request.IcscApipTokenRequestV1;
import com.icbc.api.response.IcscApipTokenResponseV1;

public class IcscApipTokenTestV1 {
    protected static final String MY_PRIVATE_KEY = "此处为私钥串";

    protected static final String APIGW_PUBLIC_KEY = "此处为网关公钥串";

    protected static final String APP_ID = "此处为APP_ID";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
                "json", APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");
        IcscApipTokenRequestV1 request = new IcscApipTokenRequestV1();
        request.setServiceUrl("https://apipcs4.dccnet.com.cn/api/icsc/apipToken/V1");
        IcscApipTokenRequestV1.IcscPaymentBillsApiptokenRequestV1Biz bizContent =
                new IcscApipTokenRequestV1.IcscPaymentBillsApiptokenRequestV1Biz();
        bizContent.setTenantId("0103");//租户编号
        bizContent.setPaymentBillsDate("2023-11-11");//对账日期
        bizContent.setVersion("1.0.0");//版本号
        bizContent.setValidTime("150");//有效时间。单位s，默认5分钟（300s）上限30分钟（1800s）
        request.setBizContent(bizContent);

        IcscApipTokenResponseV1 response;
        try {
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            response = client.execute(request, String.valueOf(System.currentTimeMillis()));
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("ReturnMsg: " + response.getReturnMsg());
                System.out.println("RedirectUrl: " + response.getToken());
                System.out.println("RedirectUrl: " + response.getRandomValue());
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            System.out.println(e.getErrCode());
        }

    }
}
