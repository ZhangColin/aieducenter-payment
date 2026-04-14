package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayGyjrB2bPaycancelRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bPaycancelResponseV1;

import java.util.Random;

/**
 * <br>
 * <B>功能描述：  </B><br>
 * <p>
 * </p>
 * <br>
 * <B>使用方法和示例：</B><br>
 * <p>
 * </p>
 *
 * @author kfzx-quhy <br>
 * 中国工商银行软件开发中心
 * @version 1.00 2022/5/20
 */
public class MybankPayGyjrB2bPaycancelTestV1 {

    protected static final String MY_PRIVATE_KEY = "XXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankPayGyjrB2bPaycancelRequestV1 request = new MybankPayGyjrB2bPaycancelRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/gyjr/b2b/paycancel/V1");
        MybankPayGyjrB2bPaycancelRequestV1.MybankPayGyjrB2bPaycancelRequestV1Biz bizContent = new MybankPayGyjrB2bPaycancelRequestV1.MybankPayGyjrB2bPaycancelRequestV1Biz();
        MybankPayGyjrB2bPaycancelRequestV1.MybankPayGyjrB2bPaycancelTransInfoV1 transInfo = new MybankPayGyjrB2bPaycancelRequestV1.MybankPayGyjrB2bPaycancelTransInfoV1();
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
        MybankPayGyjrB2bPaycancelResponseV1 response;
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
