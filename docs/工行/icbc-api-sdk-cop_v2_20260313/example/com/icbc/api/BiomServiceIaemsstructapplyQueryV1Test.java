package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServiceIaemsstructapplyQueryRequestV1;
import com.icbc.api.response.BiomServiceIaemsstructapplyQueryResponseV1;

public class BiomServiceIaemsstructapplyQueryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServiceIaemsstructapplyQueryRequestV1 request = new BiomServiceIaemsstructapplyQueryRequestV1();

        BiomServiceIaemsstructapplyQueryRequestV1.BiomServicesIaemsstructapplyQueryRequestV1Biz bizContent = new BiomServiceIaemsstructapplyQueryRequestV1.BiomServicesIaemsstructapplyQueryRequestV1Biz();

        BiomServiceIaemsstructapplyQueryRequestV1.PrivateReqBean privateReqBean = new BiomServiceIaemsstructapplyQueryRequestV1.PrivateReqBean();

        BiomServiceIaemsstructapplyQueryRequestV1.PublicReqBean publicReqBean = new BiomServiceIaemsstructapplyQueryRequestV1.PublicReqBean();


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setStatus("1");
        privateReqBean.setQrytype("1");
        privateReqBean.setStruct("0020001444");
        privateReqBean.setBegNum("1");
        privateReqBean.setFetchNum("60");
        privateReqBean.setReqblno("001000");
        privateReqBean.setReqdate("2024-10-01");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServiceIaemsstructapplyQueryResponseV1 response = client.execute(request);
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
