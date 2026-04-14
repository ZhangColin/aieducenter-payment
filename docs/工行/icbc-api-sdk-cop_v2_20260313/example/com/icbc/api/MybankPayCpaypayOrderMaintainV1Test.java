package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankPayCpaypayOrderMaintainRequestV1;
import com.icbc.api.response.MybankPayCpaypayOrderMaintainResponseV1;


import java.util.ArrayList;
import java.util.List;

public class MybankPayCpaypayOrderMaintainV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankPayCpaypayOrderMaintainRequestV1 request = new MybankPayCpaypayOrderMaintainRequestV1();

        MybankPayCpaypayOrderMaintainRequestV1.MybankPayCpaypayOrderMaintainRequestV1Biz bizContent = new MybankPayCpaypayOrderMaintainRequestV1.MybankPayCpaypayOrderMaintainRequestV1Biz();

        MybankPayCpaypayOrderMaintainRequestV1.PrivateReqBean privateReqBean = new MybankPayCpaypayOrderMaintainRequestV1.PrivateReqBean();

        MybankPayCpaypayOrderMaintainRequestV1.PublicReqBean publicReqBean = new MybankPayCpaypayOrderMaintainRequestV1.PublicReqBean();

        publicReqBean.setGroupID("020090002394852");
        publicReqBean.setEventNo("030202009010001536991872093");

        privateReqBean.setAgreeCode("0020000998060410006041000000830888");
        privateReqBean.setPartnerSeq("010140002182000773299912210");
        privateReqBean.setOrderCode("20220523091022");
        privateReqBean.setOperFlag("1");
        privateReqBean.setOrderExpireDate("2024-12-12");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            MybankPayCpaypayOrderMaintainResponseV1 response = client.execute(request);
            if (response.getTransok() == 0) {
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("return_code:" + response.getReturnCode());
                System.out.println("return_msg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
