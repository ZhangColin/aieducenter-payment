package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashTaskarticlemoverScanboxassignRequestV1;
import com.icbc.api.response.BiomTransportCashTaskarticlemoverScanboxassignResponseV1;

import java.util.ArrayList;

public class BiomTransportCashTaskarticlemoverScanboxassignV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashTaskarticlemoverScanboxassignRequestV1 request = new BiomTransportCashTaskarticlemoverScanboxassignRequestV1();

        BiomTransportCashTaskarticlemoverScanboxassignRequestV1.BiomTransportCashTaskarticlemoverScanboxassignRequestV1Biz bizContent = new BiomTransportCashTaskarticlemoverScanboxassignRequestV1.BiomTransportCashTaskarticlemoverScanboxassignRequestV1Biz();

        BiomTransportCashTaskarticlemoverScanboxassignRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashTaskarticlemoverScanboxassignRequestV1.PrivateReqBean();

        BiomTransportCashTaskarticlemoverScanboxassignRequestV1.PublicReqBean publicReqBean = new BiomTransportCashTaskarticlemoverScanboxassignRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setStruId("001");
        privateReqBean.setZoneno("200");
        privateReqBean.setBrtype("1");
        privateReqBean.setArticleIdList(new ArrayList<String>() {{
            add("3");
        }});
        privateReqBean.setInStruId("001");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashTaskarticlemoverScanboxassignResponseV1 response = client.execute(request);
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
