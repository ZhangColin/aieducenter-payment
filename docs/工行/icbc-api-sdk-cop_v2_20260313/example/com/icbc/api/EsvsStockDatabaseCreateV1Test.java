package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsStockDatabaseCreateRequestV1;
import com.icbc.api.response.EsvsStockDatabaseCreateResponseV1;

import java.util.ArrayList;
import java.util.List;

public class EsvsStockDatabaseCreateV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        EsvsStockDatabaseCreateRequestV1 request = new EsvsStockDatabaseCreateRequestV1();

        EsvsStockDatabaseCreateRequestV1.EsvsStockDatabaseCreateRequestV1Biz bizContent = new EsvsStockDatabaseCreateRequestV1.EsvsStockDatabaseCreateRequestV1Biz();

        EsvsStockDatabaseCreateRequestV1.Approot approot = new EsvsStockDatabaseCreateRequestV1.Approot();

        EsvsStockDatabaseCreateRequestV1.PrivateReqBean privateReqBean = new EsvsStockDatabaseCreateRequestV1.PrivateReqBean();

        EsvsStockDatabaseCreateRequestV1.PublicReqBean publicReqBean = new EsvsStockDatabaseCreateRequestV1.PublicReqBean();
        EsvsStockDatabaseCreateRequestV1.Rulelst rulelst = new EsvsStockDatabaseCreateRequestV1.Rulelst();
        List<EsvsStockDatabaseCreateRequestV1.Rulelst> rulelsts = new ArrayList<>();
        EsvsStockDatabaseCreateRequestV1.Seallst seallst = new EsvsStockDatabaseCreateRequestV1.Seallst();
        List<EsvsStockDatabaseCreateRequestV1.Seallst> seallsts =  new ArrayList<>();

        seallst.setSealdatasize("0020001242");
        seallst.setSealbasdbsize("0020001242");
        seallst.setSealbasdb("0020001242");
        seallst.setSealtype("0020001242");
        seallst.setSealcol("0020001242");
        seallst.setSealseq("0020001242");
        seallst.setSmallcode("0020001242");
        seallst.setSealshape("0020001242");
        seallst.setSigflag("0020001242");
        seallst.setSealdata("0020001242");

        rulelst.setAmount2("0020001242");
        rulelst.setSealgrotype("0020001242");
        rulelst.setAmount1("0020001242");
        rulelst.setSealrul("0020001242");
        rulelst.setSealgrono("0020001242");

        rulelsts.add(rulelst);
        seallsts.add(seallst);
        privateReqBean.setRulecont("0020001242");
        privateReqBean.setFfdate("0020001242");
        privateReqBean.setEffectdate("0020001242");

        privateReqBean.setSeallst(seallsts);
        privateReqBean.setPoscode("0020001242");
        privateReqBean.setCurrtype("0020001242");
        privateReqBean.setLxrmobno("0020001242");
        privateReqBean.setJksmimgserno("0020001242");
        privateReqBean.setSealcardno("0020001242");
        privateReqBean.setFkzoneid("0020001242");
        privateReqBean.setFkstructid("0020001242");
        privateReqBean.setFkbrchid("0020001242");
        privateReqBean.setLxrtelno("0020001242");
        privateReqBean.setLxrcerttype("0020001242");
        privateReqBean.setSealcont("0020001242");
        privateReqBean.setRulelst(rulelsts);
        privateReqBean.setBusitype("0020001242");
        privateReqBean.setAcctno("0020001242");
        privateReqBean.setJkuserid("0020001242");
        privateReqBean.setLxrcertcode("0020001242");
        privateReqBean.setSlcdaddcont("0020001242");
        privateReqBean.setJkzoneno("0020001242");
        privateReqBean.setOfficeaddr("0020001242");
        privateReqBean.setQltdtctrslt("0020001242");
        privateReqBean.setJkbrchno("0020001242");
        privateReqBean.setFktellno("0020001242");
        privateReqBean.setChsid("0020001242");
        privateReqBean.setLxrname("0020001242");
        privateReqBean.setJkstructid("0020001242");
        privateReqBean.setAddcond("0020001242");

        publicReqBean.setTransdate("0020001242");
        publicReqBean.setTransstruct("0020001242");
        publicReqBean.setTransno("0020001242");
        publicReqBean.setTranstime("0020001242");

        approot.setPrivateReqBean(privateReqBean);
        approot.setPublicReqBean(publicReqBean);

        bizContent.setApproot(approot);
        bizContent.setApprootfrom("0020001242");

        request.setBizContent(bizContent);
        try {

            EsvsStockDatabaseCreateResponseV1 response = client.execute(request);
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
