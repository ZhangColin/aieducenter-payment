package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bEnquiryApprovalRequestV1;
import com.icbc.api.response.GyjrB2bEnquiryApprovalResponseV1;

import java.util.Arrays;
import java.util.Random;

public class GyjrB2bEnquiryApprovalTestV1 {

    protected static final String MY_PRIVATE_KEY = "XXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bEnquiryApprovalRequestV1 request = new GyjrB2bEnquiryApprovalRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/enquiry/approval/V1");
        GyjrB2bEnquiryApprovalRequestV1.BizContentV1Biz bizContent = new GyjrB2bEnquiryApprovalRequestV1.BizContentV1Biz();
        GyjrB2bEnquiryApprovalRequestV1.TransInfoV1Biz transInfo = new GyjrB2bEnquiryApprovalRequestV1.TransInfoV1Biz();

        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setAppno("12345678901234");
        transInfo.setAppId("1111111");
        transInfo.setFlag("2");
        transInfo.setReason("不通过");

        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        GyjrB2bEnquiryApprovalResponseV1 response;
        try {
            response = client.execute(request,new Random().nextInt(99999) + "msg");
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
