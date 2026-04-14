package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EsvsAsyncSealRequestV1;
import com.icbc.api.response.EsvsAsyncSealResponseV1;
import com.icbc.api.response.EsvsZyFlagQueryResponseV1;

/**
 * @author KFZX-CHENWP5
 * @date 2023-7-11 10:50:34
 */
public class EsvsAsyncSealV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EsvsAsyncSealRequestV1 requestV1 = new EsvsAsyncSealRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/esvs/zy/flag/query/V1");
        EsvsAsyncSealRequestV1.EsvsAsyncSealRequestV1Biz bizContent = new EsvsAsyncSealRequestV1.EsvsAsyncSealRequestV1Biz();
        EsvsAsyncSealRequestV1.PrivateReqBean privateResBean = new EsvsAsyncSealRequestV1.PrivateReqBean();
        privateResBean.setAccnocont("");
        EsvsAsyncSealRequestV1.AccnoInfo accnoinfo = new EsvsAsyncSealRequestV1.AccnoInfo();
        accnoinfo.setAccno("");
        accnoinfo.setVouhtype("");
        accnoinfo.setVouhno("");
        accnoinfo.setDate("");
        accnoinfo.setCtflag("");
        accnoinfo.setCurrent("");
        accnoinfo.setAmount("");
        accnoinfo.setPicflag("");
        accnoinfo.setAddinfo("");
        accnoinfo.setPicadd("");
        accnoinfo.setPicchildseq("");
        privateResBean.setAccnoinfo(accnoinfo);
        EsvsAsyncSealRequestV1.PublicReqBean publicResBean = new EsvsAsyncSealRequestV1.PublicReqBean();
        publicResBean.setTransno("");
        publicResBean.setOrganno("");
        publicResBean.setOrganlvl("");
        publicResBean.setZoneno("");
        publicResBean.setTransorg("");
        publicResBean.setSerface("");
        publicResBean.setEventseq("");
        publicResBean.setPtrxseq("");
        publicResBean.setBusseqno("");
        publicResBean.setTellerno("");
        publicResBean.setTransdate("");
        bizContent.setPrivateResBean(privateResBean);
        bizContent.setPublicResBean(publicResBean);
        requestV1.setBizContent(bizContent);
        EsvsAsyncSealResponseV1 responseV1 = client.execute(requestV1);
        System.out.println(JSON.toJSONString(responseV1));

    }
}
