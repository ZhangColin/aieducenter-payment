package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsSealcardQueryRequestV1;
import com.icbc.api.response.EsvsSealcardQueryResponseV1;

/**
 * @author KFZX-CHENWP5
 * @date 2022-7-28 18:20:42
 */
public class EsvsSealcardQueryV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsSealcardQueryRequestV1 requestV1 = new EsvsSealcardQueryRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/esvs/sealcard/query/V1");
        EsvsSealcardQueryRequestV1.EsvsSealcardQueryRequestV1Biz biz = new EsvsSealcardQueryRequestV1.EsvsSealcardQueryRequestV1Biz();
        EsvsSealcardQueryRequestV1.Approot approot = new EsvsSealcardQueryRequestV1.Approot();
        EsvsSealcardQueryRequestV1.PrivateReqBean privateResBean = new EsvsSealcardQueryRequestV1.PrivateReqBean();
        privateResBean.setAccno("1");
        privateResBean.setBegpos("1");
        privateResBean.setCurrtype("1");
        privateResBean.setQrytype("1");
        privateResBean.setRetnum("1");
        privateResBean.setSlcdno("");
        approot.setPrivateResBean(privateResBean);
        EsvsSealcardQueryRequestV1.PublicReqBean publicResBean = new EsvsSealcardQueryRequestV1.PublicReqBean();
        publicResBean.setTransbrch("");
        publicResBean.setTransdate("");
        publicResBean.setTransno("");
        publicResBean.setTransstruct("");
        publicResBean.setTranstell("");
        publicResBean.setTranszone("");
        approot.setPublicResBean(publicResBean);
        biz.setApproot(approot);
        requestV1.setBizContent(biz);
        EsvsSealcardQueryResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));

    }
}
