package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsPrintingCardGrantrecoverabandonRequestV1;
import com.icbc.api.response.EsvsPrintingCardGrantrecoverabandonResponseV1;

public class EsvsPrintingCardGrantrecoverabandonV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        EsvsPrintingCardGrantrecoverabandonRequestV1 request = new EsvsPrintingCardGrantrecoverabandonRequestV1();

        EsvsPrintingCardGrantrecoverabandonRequestV1.EsvsPrintingCardGrantRecoverAbandonRequestV1Biz bizContent = new EsvsPrintingCardGrantrecoverabandonRequestV1.EsvsPrintingCardGrantRecoverAbandonRequestV1Biz();

        EsvsPrintingCardGrantrecoverabandonRequestV1.Approot approot = new EsvsPrintingCardGrantrecoverabandonRequestV1.Approot();

        EsvsPrintingCardGrantrecoverabandonRequestV1.PrivateReqBean privateReqBean = new EsvsPrintingCardGrantrecoverabandonRequestV1.PrivateReqBean();

        EsvsPrintingCardGrantrecoverabandonRequestV1.PublicReqBean publicReqBean = new EsvsPrintingCardGrantrecoverabandonRequestV1.PublicReqBean();

        privateReqBean.setAcccashexf("1");
        privateReqBean.setFfdate("2020-04-16");
        privateReqBean.setBrchaddinfo("");
        privateReqBean.setFyslcdno("");
        privateReqBean.setEffectdate("");
        privateReqBean.setAcccrossf("1");
        privateReqBean.setSlcdimgserno("");
        privateReqBean.setGjaccno("");
        privateReqBean.setAcccrosf("1");
        privateReqBean.setSlcdno("2738193847392323");
        privateReqBean.setTermid("38929483193420");
        privateReqBean.setAccorganno("0020000998");
        privateReqBean.setGdscxh("1");
        privateReqBean.setAcccino("394839050193821");
        privateReqBean.setIftrxsernb("434532987");
        privateReqBean.setAccno("2011004009024566888");
        privateReqBean.setBakimgserno("");
        privateReqBean.setAccopendate("2020-04-16");
        privateReqBean.setPoscode("");
        privateReqBean.setGdslcdno("");
        privateReqBean.setLxrmobno("");
        privateReqBean.setAccactcode("0020000998");
        privateReqBean.setAccsealf("1");
        privateReqBean.setLxrtelno("");
        privateReqBean.setAcclstmodfd("2020-04-16");
        privateReqBean.setAccstatus("1");
        privateReqBean.setLxrcerttype("");
        privateReqBean.setBlkoutmode("");
        privateReqBean.setScxh("1");
        privateReqBean.setSealcont("");
        privateReqBean.setAcccurrtype("001");
        privateReqBean.setAccclosdate("2025-04-16");
        privateReqBean.setFycurrtype("");
        privateReqBean.setFyaccno("");
        privateReqBean.setAccbrno("00998");
        privateReqBean.setAcczoneno("00200");
        privateReqBean.setAccatrbt("账1");
        privateReqBean.setBusitype("1");
        privateReqBean.setLxrcertcode("");
        privateReqBean.setSlcdaddcont("");
        privateReqBean.setAccname("0405");
        privateReqBean.setGdslcdaddcont("");
        privateReqBean.setOfficeaddr("");
        privateReqBean.setJktype("");
        privateReqBean.setLxrname("");
        privateReqBean.setAddcond("");
        privateReqBean.setRefytype("");
        privateReqBean.setRefyaccno("");

        publicReqBean.setTransdate("2020-04-16");
        publicReqBean.setTranstell("91167");
        publicReqBean.setTransdevice("770976566898676");
        publicReqBean.setTransstruct("0020000998");
        publicReqBean.setTransno("ESVS10001");
        publicReqBean.setTransbrch("00998");
        publicReqBean.setTranszone("00200");
        publicReqBean.setFuncflag("1");

        approot.setPrivateReqBean(privateReqBean);
        approot.setPublicReqBean(publicReqBean);

        bizContent.setApproot(approot);

        request.setBizContent(bizContent);
        try {
            EsvsPrintingCardGrantrecoverabandonResponseV1 response = client.execute(request);
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
