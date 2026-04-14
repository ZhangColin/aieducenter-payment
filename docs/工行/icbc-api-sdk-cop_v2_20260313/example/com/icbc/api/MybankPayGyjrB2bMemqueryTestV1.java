package com.icbc.api;

import com.icbc.api.request.MybankPayGyjrB2bMemqueryRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bMemqueryResponseV1;

import java.util.Random;

public class MybankPayGyjrB2bMemqueryTestV1 {
    protected static final String MY_PRIVATE_KEY = "XXXXXXXX";

    protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";

    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        // 设置请求对象request
        MybankPayGyjrB2bMemqueryRequestV1 req = new MybankPayGyjrB2bMemqueryRequestV1();

        // 设置请求路径
        req.setServiceUrl("http://IP:PORT/api/mybank/pay/gyjr/b2b/memquery/V1");

        MybankPayGyjrB2bMemqueryRequestV1.BizContentV1Biz biz = new MybankPayGyjrB2bMemqueryRequestV1.BizContentV1Biz();
        MybankPayGyjrB2bMemqueryRequestV1.TransInfoV1Biz transInfo = new MybankPayGyjrB2bMemqueryRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("1");
        transInfo.setBusinessNo("1");
        transInfo.setPlatVendorid("1");
        transInfo.setOrganizationCode("1");
        transInfo.setAgreeCode("AGREECODE20190215");
        transInfo.setMemberNo("001");

        biz.setTransInfo(transInfo);
        req.setBizContent(biz);
        MybankPayGyjrB2bMemqueryResponseV1 response = new MybankPayGyjrB2bMemqueryResponseV1();

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        System.out.println(msgId);

        try {
            response = client.execute(req, msgId);
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
