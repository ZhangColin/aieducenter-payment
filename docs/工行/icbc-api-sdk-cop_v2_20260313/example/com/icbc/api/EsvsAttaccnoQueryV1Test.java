package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsAttaccnoQueryRequestV1;
import com.icbc.api.response.EsvsAttaccnoQueryResponseV1;

/**
 * @author KFZX-CHENWP5
 * @date 2022-7-28 18:20:42
 */
public class EsvsAttaccnoQueryV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsAttaccnoQueryRequestV1 requestV1 = new EsvsAttaccnoQueryRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/esvs/attaccno/query/V1");
        EsvsAttaccnoQueryRequestV1.EsvsAttaccnoQueryRequestV1Biz bizContent = new EsvsAttaccnoQueryRequestV1.EsvsAttaccnoQueryRequestV1Biz();
        EsvsAttaccnoQueryRequestV1.Approot approot = new EsvsAttaccnoQueryRequestV1.Approot();
        EsvsAttaccnoQueryRequestV1.PrivateReqBean privateResBean = new EsvsAttaccnoQueryRequestV1.PrivateReqBean();
        privateResBean.setAccno("accno");
        approot.setPrivateResBean(privateResBean);
        EsvsAttaccnoQueryRequestV1.PublicReqBean publicResBean = new EsvsAttaccnoQueryRequestV1.PublicReqBean();
        publicResBean.setTransno("ESVS10012");
        approot.setPublicResBean(publicResBean);
        bizContent.setApproot(approot);
        requestV1.setBizContent(bizContent);
        EsvsAttaccnoQueryResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));

    }
}
