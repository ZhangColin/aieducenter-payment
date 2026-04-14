package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsBatchPrintingCardCheckRequestV1;
import com.icbc.api.response.EsvsBatchPrintingCardCheckResponseV1;

import java.util.ArrayList;
import java.util.List;

public class EsvsBatchPrintingCardCheckV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        EsvsBatchPrintingCardCheckRequestV1 request = new EsvsBatchPrintingCardCheckRequestV1();

        EsvsBatchPrintingCardCheckRequestV1.EsvsBatchPrintingCardCheckRequestV1Biz bizContent = new EsvsBatchPrintingCardCheckRequestV1.EsvsBatchPrintingCardCheckRequestV1Biz();

        EsvsBatchPrintingCardCheckRequestV1.Approot approot = new EsvsBatchPrintingCardCheckRequestV1.Approot();

        EsvsBatchPrintingCardCheckRequestV1.PrivateReqBean privateReqBean = new EsvsBatchPrintingCardCheckRequestV1.PrivateReqBean();

        EsvsBatchPrintingCardCheckRequestV1.PublicReqBean publicReqBean = new EsvsBatchPrintingCardCheckRequestV1.PublicReqBean();
        EsvsBatchPrintingCardCheckRequestV1.Accnoinfo accnoinfo = new EsvsBatchPrintingCardCheckRequestV1.Accnoinfo();
        List<EsvsBatchPrintingCardCheckRequestV1.Accnoinfo> accnoinfos = new ArrayList<>();

        accnoinfo.setPicadd("0020001242");
        accnoinfo.setAddinfo("0020001242");
        accnoinfo.setAccno("0020001242");
        accnoinfo.setDate("0020001242");
        accnoinfo.setPicflag("0020001242");
        accnoinfo.setAmount("0020001242");
        accnoinfo.setCtflag("0020001242");
        accnoinfo.setVouhno("0020001242");
        accnoinfo.setVouhtype("0020001242");
        accnoinfo.setCurrent("0020001242");
        accnoinfo.setPicchildseq("0020001242");

        accnoinfos.add(accnoinfo);
        privateReqBean.setAccnoinfo(accnoinfos);
        privateReqBean.setAccnocont("0020001242");
        privateReqBean.setPriority("0020001242");

        publicReqBean.setOrganno("0020001242");
        publicReqBean.setTransorg("0020001242");
        publicReqBean.setPtrxseq("0020001242");
        publicReqBean.setBusseqno("0020001242");
        publicReqBean.setTransdate("0020001242");
        publicReqBean.setTransno("0020001242");
        publicReqBean.setSerface("0020001242");
        publicReqBean.setZoneno("0020001242");
        publicReqBean.setEventseq("0020001242");
        publicReqBean.setOrganlvl("0020001242");
        publicReqBean.setTellerno("0020001242");

        approot.setPrivateReqBean(privateReqBean);
        approot.setPublicReqBean(publicReqBean);

        bizContent.setApproot(approot);

        request.setBizContent(bizContent);
        try {
            EsvsBatchPrintingCardCheckResponseV1 response = client.execute(request);
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
