package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesICsrcToFhProfhGetPckgRequestV1;
import com.icbc.api.response.BiomServicesICsrcToFhProfhGetPckgResponseV1;

public class BiomServicesICsrcToFhProfhGetPckgV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BiomServicesICsrcToFhProfhGetPckgRequestV1 request = new BiomServicesICsrcToFhProfhGetPckgRequestV1();
        request.setServiceUrl("https://ip:port/api/services/ICsrcToFhPro/fhGetPckg/V1");
        BiomServicesICsrcToFhProfhGetPckgRequestV1.BiomServicesICsrcToFhProfhGetPckgRequestV1Biz bizContent = new BiomServicesICsrcToFhProfhGetPckgRequestV1.BiomServicesICsrcToFhProfhGetPckgRequestV1Biz();
        BiomServicesICsrcToFhProfhGetPckgRequestV1.PrivateReqBean privateReqBean = new BiomServicesICsrcToFhProfhGetPckgRequestV1.PrivateReqBean();
        BiomServicesICsrcToFhProfhGetPckgRequestV1.PublicReqBean publicReqBean = new BiomServicesICsrcToFhProfhGetPckgRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");

        privateReqBean.setCtrZoneno("200");
        privateReqBean.setCtrBrno("998");
        privateReqBean.setBusitype("6");
        privateReqBean.setBeginDate("2024-01-01");
        privateReqBean.setEndDate("2024-02-01");
        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("2");
        privateReqBean.setCurrType("001");
        privateReqBean.setApplyno("1");
        privateReqBean.setOrderStatus("1");
        privateReqBean.setBegNum("1");
        privateReqBean.setFetchNum("20");
        privateReqBean.setMacno("02000000099801INSERT8888");


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesICsrcToFhProfhGetPckgResponseV1 response = client.execute(request);
            System.out.println(JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
