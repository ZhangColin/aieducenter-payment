package com.icbc.api;

import com.icbc.api.request.MybankPayGyjrB2bMemauthRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bMemauthResponseV1;

import java.util.Random;

public class MybankPayGyjrB2bMemauthTestV1 {
    protected static final String MY_PRIVATE_KEY = "XXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankPayGyjrB2bMemauthRequestV1 request = new MybankPayGyjrB2bMemauthRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/gyjr/b2b/memauth/V1");
        MybankPayGyjrB2bMemauthRequestV1.MemauthBizContentV1Biz bizContent =
                new MybankPayGyjrB2bMemauthRequestV1.MemauthBizContentV1Biz();
        MybankPayGyjrB2bMemauthRequestV1.MemauthTransInfoV1Biz transInfo =
                new MybankPayGyjrB2bMemauthRequestV1.MemauthTransInfoV1Biz();

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        System.out.println(msgId);

        transInfo.setProtocolCode("12345");//聚融协议编号
        transInfo.setBusinessNo("12345");//聚融商户编号
        transInfo.setPlatVendorid("12345");//平台商户编号
        transInfo.setOrganizationCode("12345");//商户统一信用代码
        transInfo.setAgreeCode("AGREECODE20190215");//合作方协议编号
        transInfo.setMemberNo("001");//合作方会员号
        transInfo.setReturnUrl("http://www.XXX.cn");//返回页面地址

        bizContent.setTransInfo(transInfo);

        request.setBizContent(bizContent);

        MybankPayGyjrB2bMemauthResponseV1 response = new MybankPayGyjrB2bMemauthResponseV1();

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
        } finally {
        }

    }
}
