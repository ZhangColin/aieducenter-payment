package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsSealinfoRegisterRequestV1;
import com.icbc.api.response.EsvsSealinfoRegisterResponseV1;

import java.util.ArrayList;

/**
 * @author KFZX-CHENWP5
 * @date 2022-7-28 18:20:42
 */
public class EsvsSealinfoRegisterV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsSealinfoRegisterRequestV1 requestV1 = new EsvsSealinfoRegisterRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/invoice/phone/title/update/V1");
        EsvsSealinfoRegisterRequestV1.EsvsSealinfoRegisterRequestV1Biz bizContent = new EsvsSealinfoRegisterRequestV1.EsvsSealinfoRegisterRequestV1Biz();
        EsvsSealinfoRegisterRequestV1.Approot approot = new EsvsSealinfoRegisterRequestV1.Approot();
        EsvsSealinfoRegisterRequestV1.PrivateReqBean privateResBean = new EsvsSealinfoRegisterRequestV1.PrivateReqBean();
        privateResBean.setPostinfo("");
        privateResBean.setAmountflag("1");
        privateResBean.setBillres1("1");
        privateResBean.setBillres2("1");
        privateResBean.setBillsimilarity("1");
        privateResBean.setMaccode("");
        privateResBean.setCtflag("1");
        privateResBean.setSealcount("1");
        privateResBean.setVouhno("1");
        privateResBean.setPicchildseq("");
        privateResBean.setBusiid("");
        privateResBean.setTransocrflag("");
        privateResBean.setAccno("");
        privateResBean.setDate("");
        privateResBean.setNoteflag("1");
        privateResBean.setCurrtype("");
        privateResBean.setAmount("1");
        privateResBean.setBillresf("1");
        privateResBean.setSealrul("");
        privateResBean.setSealcardno("1");
        privateResBean.setBillmodf("1");
        privateResBean.setVouhtype("1");
        privateResBean.setSealgrono("1");
        EsvsSealinfoRegisterRequestV1.Seal seal = new EsvsSealinfoRegisterRequestV1.Seal();
        ArrayList<EsvsSealinfoRegisterRequestV1.Seal> list = new ArrayList<>();
        seal.setSealres2("1");
        seal.setSealres1("1");
        seal.setAssisttype("");
        seal.setSealtype("1");
        seal.setSmallcode("");
        seal.setSealmodf("");
        seal.setSealresf("1");
        seal.setSealcol("1");
        seal.setSealseq("");
        seal.setSealmaccode("");
        seal.setSimilarity("1");
        seal.setSealshape("1");
        seal.setSigflag("1");
        seal.setRefreason("");
        seal.setAssimgno("");
        list.add(seal);
        privateResBean.setSeal(list);
        approot.setPrivateResBean(privateResBean);
        EsvsSealinfoRegisterRequestV1.PublicReqBean publicResBean = new EsvsSealinfoRegisterRequestV1.PublicReqBean();
        publicResBean.setBusseqno("");
        publicResBean.setEventseq("1");
        publicResBean.setOrganno("");
        publicResBean.setPtrxseq("1");
        publicResBean.setSerface("1");
        publicResBean.setTellerno("1");
        publicResBean.setTransno("");
        publicResBean.setTransorg("1");
        approot.setPublicResBean(publicResBean);
        bizContent.setApproot(approot);
        requestV1.setBizContent(bizContent);
        EsvsSealinfoRegisterResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));
    }
}
