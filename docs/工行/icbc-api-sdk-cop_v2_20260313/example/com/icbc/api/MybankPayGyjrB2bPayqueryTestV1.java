package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayGyjrB2bPayqueryRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bPayqueryResponseV1;

import java.util.Random;

public class MybankPayGyjrB2bPayqueryTestV1 {


    protected static final String MY_PRIVATE_KEY = "XXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankPayGyjrB2bPayqueryRequestV1 request = new MybankPayGyjrB2bPayqueryRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/gyjr/b2b/payquery/V1");
        MybankPayGyjrB2bPayqueryRequestV1.PayqueryBizContentV1Biz bizContent = new MybankPayGyjrB2bPayqueryRequestV1.PayqueryBizContentV1Biz();
        MybankPayGyjrB2bPayqueryRequestV1.PayqueryTransInfoV1Biz transInfo
                = new MybankPayGyjrB2bPayqueryRequestV1.PayqueryTransInfoV1Biz();


        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        System.out.println(msgId);

        transInfo.setPartnerSeq("MQOY2522012051021081");
        transInfo.setAgreeCode("0020000998140010137438000000097012");
        transInfo.setOrderCode("2019062730001");
        transInfo.setProtocolCode("GYJR20211204102832000000002");
        transInfo.setBusinessNo("12345");
        transInfo.setPlatVendorid("12345");
        transInfo.setOrganizationCode("12345");

        bizContent.setTransInfo(transInfo);

        request.setBizContent(bizContent);

        MybankPayGyjrB2bPayqueryResponseV1 response;
        try {
            response = client.execute(request,msgId);
            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
            } else {
                // 失败
                System.out.println(response.getReturnCode());
                System.out.println(response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
