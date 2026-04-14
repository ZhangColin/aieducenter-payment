package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsJkSealcardQryRequestV1;
import com.icbc.api.response.EsvsJkSealcardQryResponseV1;

/**
 * @author kfzx-luqy
 * @date 2024-7-26 10:50:34
 */
public class EsvsJkSealcardQryV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsJkSealcardQryRequestV1 requestV1 = new EsvsJkSealcardQryRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/esvs/jk/sealcard/qry/V1");
        EsvsJkSealcardQryRequestV1.EsvsJkSealcardQryRequestV1Biz bizContent = new EsvsJkSealcardQryRequestV1.EsvsJkSealcardQryRequestV1Biz();
        EsvsJkSealcardQryRequestV1.PrivateReqBean privateResBean = new EsvsJkSealcardQryRequestV1.PrivateReqBean();
        privateResBean.setSlcdno("");
        privateResBean.setAccno("");
        privateResBean.setCurrtype("");
        EsvsJkSealcardQryRequestV1.PublicReqBean publicResBean = new EsvsJkSealcardQryRequestV1.PublicReqBean();
        publicResBean.setAppid("");
        publicResBean.setZoneno("");
        publicResBean.setBrno("");
        bizContent.setPrivateResBean(privateResBean);
        bizContent.setPublicResBean(publicResBean);
        requestV1.setBizContent(bizContent);
        EsvsJkSealcardQryResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));
    }
}
