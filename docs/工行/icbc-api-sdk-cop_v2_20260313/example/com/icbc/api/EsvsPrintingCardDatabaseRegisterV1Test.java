package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsPrintingCardDatabaseRegisterRequestV1;
import com.icbc.api.response.EsvsPrintingCardDatabaseRegisterResponseV1;

/**
 * @author KFZX-CHENWP5
 * @date 2022-7-28 18:20:42
 */
public class EsvsPrintingCardDatabaseRegisterV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsPrintingCardDatabaseRegisterRequestV1 requestV1 = new EsvsPrintingCardDatabaseRegisterRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/esvs/printing/card/database/register/V1");

        EsvsPrintingCardDatabaseRegisterRequestV1.EsvsPrintingCardDatabaseRegisterRequestV1Biz bizContent = new EsvsPrintingCardDatabaseRegisterRequestV1.EsvsPrintingCardDatabaseRegisterRequestV1Biz();
        bizContent.setChncode("");
        bizContent.setOapp("F-APIP");
        EsvsPrintingCardDatabaseRegisterRequestV1.Approot approot = new EsvsPrintingCardDatabaseRegisterRequestV1.Approot();
        EsvsPrintingCardDatabaseRegisterRequestV1.PrivateReqBean privateResBean = new EsvsPrintingCardDatabaseRegisterRequestV1.PrivateReqBean();
        privateResBean.setAccno("");
        privateResBean.setCurrtype("");
        privateResBean.setSlcdno("");
        privateResBean.setChsid("");
        privateResBean.setJksmimgserno("");
        privateResBean.setQltdtctrslt("");
        privateResBean.setJkzoneno("1");
        privateResBean.setJkbrchno("1");
        privateResBean.setJkuserid("");
        privateResBean.setIntelgtemid("");
        privateResBean.setSealnum("1");
        EsvsPrintingCardDatabaseRegisterRequestV1.Seal seal = new EsvsPrintingCardDatabaseRegisterRequestV1.Seal();
        EsvsPrintingCardDatabaseRegisterRequestV1.SealRow sealRow = new EsvsPrintingCardDatabaseRegisterRequestV1.SealRow();
        sealRow.setSealseq("");
        sealRow.setSealtype("1");
        sealRow.setSealcol("1");
        sealRow.setSealshape("1");
        sealRow.setSealposition("");
        sealRow.setSigflag("");
        sealRow.setSmallcode("");
        sealRow.setSealdatasize("1");
        sealRow.setSealdata("");
        sealRow.setSealbasdbsize("1");
        sealRow.setSealbasdb("");
        seal.setSealRow(sealRow);
        privateResBean.setSeal(seal);
        privateResBean.setSealgronum("1");
        EsvsPrintingCardDatabaseRegisterRequestV1.Sealgro sealgro = new EsvsPrintingCardDatabaseRegisterRequestV1.Sealgro();
        EsvsPrintingCardDatabaseRegisterRequestV1.SealgroRow sealgroRow = new EsvsPrintingCardDatabaseRegisterRequestV1.SealgroRow();
        sealgroRow.setSealgrono("");
        sealgroRow.setSealgrotype("1");
        sealgroRow.setAmount1("1");
        sealgroRow.setAmount2("1");
        sealgroRow.setSealrul("");
        sealgro.setSealgroRow(sealgroRow);
        privateResBean.setSealgro(sealgro);
        approot.setPrivateResBean(privateResBean);
        EsvsPrintingCardDatabaseRegisterRequestV1.PublicReqBean publicResBean = new EsvsPrintingCardDatabaseRegisterRequestV1.PublicReqBean();
        publicResBean.setTransno("transno");
        approot.setPublicResBean(publicResBean);
        bizContent.setApproot(approot);
        requestV1.setBizContent(bizContent);

        EsvsPrintingCardDatabaseRegisterResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));

    }
}
