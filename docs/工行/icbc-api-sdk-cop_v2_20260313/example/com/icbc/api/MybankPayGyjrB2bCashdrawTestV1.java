package com.icbc.api.test;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankPayGyjrB2bCashdrawRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bCashdrawResponseV1;

import java.util.Random;


public class MybankPayGyjrB2bCashdrawTestV1 {

    protected static final String MY_PRIVATE_KEY = "XXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankPayGyjrB2bCashdrawRequestV1 request = new MybankPayGyjrB2bCashdrawRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/gyjr/b2b/cashdraw/V1");
        MybankPayGyjrB2bCashdrawRequestV1.MybankPayGyjrB2bCashdrawRequestV1Biz bizContent = new MybankPayGyjrB2bCashdrawRequestV1.MybankPayGyjrB2bCashdrawRequestV1Biz();
        MybankPayGyjrB2bCashdrawRequestV1.MybankPayGyjrB2bCashdrawTransInfoV1 transInfo = new MybankPayGyjrB2bCashdrawRequestV1.MybankPayGyjrB2bCashdrawTransInfoV1();
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

        bizContent.setTrans_info(transInfo);

        request.setBizContent(bizContent);

        MybankPayGyjrB2bCashdrawResponseV1 response;
        try {
            response = client.execute(request, msgId);
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
