package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsJkSealcardUpdRequestV1;
import com.icbc.api.response.EsvsJkSealcardUpdResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kfzx-luqy
 * @date 2024-7-26 10:50:34
 */
public class EsvsJkSealcardUpdV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsJkSealcardUpdRequestV1 requestV1 = new EsvsJkSealcardUpdRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/esvs/jk/sealcard/upd/V1");
        EsvsJkSealcardUpdRequestV1.EsvsJkSealcardUpdRequestV1Biz bizContent = new EsvsJkSealcardUpdRequestV1.EsvsJkSealcardUpdRequestV1Biz();
        EsvsJkSealcardUpdRequestV1.PrivateReqBean privateResBean = new EsvsJkSealcardUpdRequestV1.PrivateReqBean();
        privateResBean.setSlcdno("");
        privateResBean.setAccno("");
        privateResBean.setCurrtype("");
        privateResBean.setJksmimgserno("");
        privateResBean.setJkzoneno("");
        privateResBean.setJkbrchno("");
        privateResBean.setJkuserid("");
        privateResBean.setSealcont("");
        List<EsvsJkSealcardUpdRequestV1.SealInfo> sealList = new ArrayList<>();
        EsvsJkSealcardUpdRequestV1.SealInfo sealInfo = new EsvsJkSealcardUpdRequestV1.SealInfo();
        sealInfo.setSealNo("");
        sealInfo.setSealType("");
        sealInfo.setSealColor("");
        sealInfo.setSealShape("");
        sealInfo.setSealEapid("");
        sealInfo.setSealposition("");
        sealInfo.setSealEapname("");
        sealInfo.setSignFlag("");
        sealInfo.setSmallcode("");
        sealList.add(sealInfo);
        privateResBean.setSealList(sealList);
        privateResBean.setRuleCout("");
        List<EsvsJkSealcardUpdRequestV1.RuleInfo> ruleList = new ArrayList<>();
        EsvsJkSealcardUpdRequestV1.RuleInfo ruleInfo = new EsvsJkSealcardUpdRequestV1.RuleInfo();
        ruleInfo.setGrpNo("");
        ruleInfo.setGroType("");
        ruleInfo.setAmount1("");
        ruleInfo.setAmount2("");
        ruleInfo.setSealrul("");
        privateResBean.setRuleList(ruleList);
        EsvsJkSealcardUpdRequestV1.PublicReqBean publicResBean = new EsvsJkSealcardUpdRequestV1.PublicReqBean();
        publicResBean.setAppid("");
        publicResBean.setZoneno("");
        publicResBean.setBrno("");
        bizContent.setPrivateResBean(privateResBean);
        bizContent.setPublicResBean(publicResBean);
        requestV1.setBizContent(bizContent);
        EsvsJkSealcardUpdResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));
    }
}
