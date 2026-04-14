package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsPrintingCardActionDateUpdateRequestV1;
import com.icbc.api.response.EsvsPrintingCardActionDateUpdateResponseV1;

import java.util.ArrayList;
import java.util.List;

public class EsvsPrintingCardActionDateUpdateV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        EsvsPrintingCardActionDateUpdateRequestV1 request = new EsvsPrintingCardActionDateUpdateRequestV1();

        EsvsPrintingCardActionDateUpdateRequestV1.EsvsPrintingCardActionDateUpdateRequestV1Biz bizContent = new EsvsPrintingCardActionDateUpdateRequestV1.EsvsPrintingCardActionDateUpdateRequestV1Biz();

        EsvsPrintingCardActionDateUpdateRequestV1.Approot approot = new EsvsPrintingCardActionDateUpdateRequestV1.Approot();

        EsvsPrintingCardActionDateUpdateRequestV1.PrivateReqBean privateReqBean = new EsvsPrintingCardActionDateUpdateRequestV1.PrivateReqBean();

        EsvsPrintingCardActionDateUpdateRequestV1.PublicReqBean publicReqBean = new EsvsPrintingCardActionDateUpdateRequestV1.PublicReqBean();

        privateReqBean.setEffectdate("2020-06-20");
        privateReqBean.setZoneno("200");
        privateReqBean.setSlcdno("80020000000037054");
        privateReqBean.setAccno("2011004009024566888");
        privateReqBean.setIntelgtem("4252314");
        privateReqBean.setUserid("91167");
        privateReqBean.setCurrtype("001");
        privateReqBean.setBrno("202");

        publicReqBean.setChancode("1001");
        publicReqBean.setOapp("F-VIMS");
        publicReqBean.setOtellerno("1");

        approot.setPrivateReqBean(privateReqBean);
        approot.setPublicReqBean(publicReqBean);

        bizContent.setApproot(approot);

        request.setBizContent(bizContent);
        try {
            EsvsPrintingCardActionDateUpdateResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
