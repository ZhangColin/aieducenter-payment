package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsSealinfoQueryRequestV1;
import com.icbc.api.response.EsvsSealinfoQueryResponseV1;

/**
 * @author KFZX-CHENWP5
 * @date 2022-7-28 18:20:42
 */
public class EsvsSealinfoQueryV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsSealinfoQueryRequestV1 requestV1 = new EsvsSealinfoQueryRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/esvs/sealinfo/query/V1");
        EsvsSealinfoQueryRequestV1.EsvsSealinfoQueryRequestV1Biz bizContent = new EsvsSealinfoQueryRequestV1.EsvsSealinfoQueryRequestV1Biz();
        EsvsSealinfoQueryRequestV1.Approot approot = new EsvsSealinfoQueryRequestV1.Approot();
        EsvsSealinfoQueryRequestV1.PrivateReqBean privateResBean = new EsvsSealinfoQueryRequestV1.PrivateReqBean();
        privateResBean.setAccno("1");
        privateResBean.setAmount("1");
        privateResBean.setBakchar1("");
        privateResBean.setBakchar2("");
        privateResBean.setCtflag("1");
        privateResBean.setCurrtype("");
        privateResBean.setVoudate("");
        privateResBean.setVouhno("");
        privateResBean.setVouhtype("1");
        approot.setPrivateResBean(privateResBean);
        EsvsSealinfoQueryRequestV1.PublicReqBean publicResBean = new EsvsSealinfoQueryRequestV1.PublicReqBean();
        publicResBean.setBusseqno("");
        publicResBean.setIftrxsernb("1");
        publicResBean.setOrganno("1");
        publicResBean.setPtrxseq("");
        publicResBean.setSerface("1");
        publicResBean.setTellerno("");
        publicResBean.setTransno("");
        publicResBean.setTransorg("");
        approot.setPublicResBean(publicResBean);
        bizContent.setApproot(approot);
        requestV1.setBizContent(bizContent);
        EsvsSealinfoQueryResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));

    }
}
