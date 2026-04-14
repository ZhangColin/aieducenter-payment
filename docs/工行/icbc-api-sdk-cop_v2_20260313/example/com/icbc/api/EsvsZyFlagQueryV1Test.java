package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsZyFlagQueryRequestV1;
import com.icbc.api.response.EsvsZyFlagQueryResponseV1;

/**
 * @author KFZX-CHENWP5
 * @date 2023-7-11 10:50:34
 */
public class EsvsZyFlagQueryV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsZyFlagQueryRequestV1 requestV1 = new EsvsZyFlagQueryRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/esvs/zy/flag/query/V1");
        EsvsZyFlagQueryRequestV1.EsvsZyFlagQueryRequestV1Biz bizContent = new EsvsZyFlagQueryRequestV1.EsvsZyFlagQueryRequestV1Biz();
        EsvsZyFlagQueryRequestV1.PrivateReqBean privateResBean = new EsvsZyFlagQueryRequestV1.PrivateReqBean();
        privateResBean.setBrNo("0");
        privateResBean.setZoneNo("0");
        privateResBean.setSwitchType("ZY_JK");
        EsvsZyFlagQueryRequestV1.PublicReqBean publicResBean = new EsvsZyFlagQueryRequestV1.PublicReqBean();
        publicResBean.setAppName("调用方应用简称");
        bizContent.setPrivateResBean(privateResBean);
        bizContent.setPublicResBean(publicResBean);
        requestV1.setBizContent(bizContent);
        EsvsZyFlagQueryResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));

    }
}
