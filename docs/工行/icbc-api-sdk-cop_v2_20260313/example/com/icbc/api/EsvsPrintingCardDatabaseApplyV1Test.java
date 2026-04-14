package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsPrintingCardDatabaseApplyRequestV1;
import com.icbc.api.response.EsvsPrintingCardDatabaseApplyResponseV1;

public class EsvsPrintingCardDatabaseApplyV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        EsvsPrintingCardDatabaseApplyRequestV1 request = new EsvsPrintingCardDatabaseApplyRequestV1();

        EsvsPrintingCardDatabaseApplyRequestV1.EsvsPrintingCardDatabaseApplyRequestV1Biz bizContent = new EsvsPrintingCardDatabaseApplyRequestV1.EsvsPrintingCardDatabaseApplyRequestV1Biz();

        EsvsPrintingCardDatabaseApplyRequestV1.Approot approot = new EsvsPrintingCardDatabaseApplyRequestV1.Approot();

        EsvsPrintingCardDatabaseApplyRequestV1.PrivateReqBean privateReqBean = new EsvsPrintingCardDatabaseApplyRequestV1.PrivateReqBean();

        EsvsPrintingCardDatabaseApplyRequestV1.PublicReqBean publicReqBean = new EsvsPrintingCardDatabaseApplyRequestV1.PublicReqBean();

        publicReqBean.setTransno("Iestbsealcheck");

        privateReqBean.setAccno("2011004009024566888");
        privateReqBean.setJkspcont("11");
        privateReqBean.setCurrtype("001");
        privateReqBean.setJksptell("91167");
        privateReqBean.setBlkoutflag("0");
        privateReqBean.setSlcdno("80020000000037054");
        privateReqBean.setJksprslt("3");

        approot.setPrivateReqBean(privateReqBean);
        approot.setPublicReqBean(publicReqBean);

        bizContent.setApproot(approot);
        bizContent.setChncode("");
        bizContent.setOapp("F-VIMS");

        request.setBizContent(bizContent);
        try {
            EsvsPrintingCardDatabaseApplyResponseV1 response = client.execute(request);
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
