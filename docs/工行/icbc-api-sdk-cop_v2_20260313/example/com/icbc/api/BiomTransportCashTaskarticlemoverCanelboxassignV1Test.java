package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashTaskarticlemoverCanelboxassignRequestV1;
import com.icbc.api.response.BiomTransportCashTaskarticlemoverCanelboxassignResponseV1;

public class BiomTransportCashTaskarticlemoverCanelboxassignV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        // 初始化客户端（请替换为你的实际参数）
        DefaultIcbcClient client = new DefaultIcbcClient(
                "APP_ID",
                IcbcConstants.SIGN_TYPE_RSA2,
                "MY_PRIVATE_KEY",
                "UTF-8",
                "json",
                "APIGW_PUBLIC_KEY",
                null, null, null, null
        );

        // 创建请求对象
        BiomTransportCashTaskarticlemoverCanelboxassignRequestV1 request = new BiomTransportCashTaskarticlemoverCanelboxassignRequestV1();

        // 创建 BizContent 对象
        BiomTransportCashTaskarticlemoverCanelboxassignRequestV1.BiomTransportCashTaskarticlemoverCanelboxassignRequestV1Biz bizContent = new BiomTransportCashTaskarticlemoverCanelboxassignRequestV1.BiomTransportCashTaskarticlemoverCanelboxassignRequestV1Biz();

        // 创建 publicReqBean
        BiomTransportCashTaskarticlemoverCanelboxassignRequestV1.PublicReqBean publicReqBean = new BiomTransportCashTaskarticlemoverCanelboxassignRequestV1.PublicReqBean();
        publicReqBean.setChancode("702");
        publicReqBean.setZoneno("200");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("020000119");

        // 创建 privateReqBean
        BiomTransportCashTaskarticlemoverCanelboxassignRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashTaskarticlemoverCanelboxassignRequestV1.PrivateReqBean();
        privateReqBean.setStruId("STRU123456");
        privateReqBean.setAllotDate("20250405");
        privateReqBean.setArticleId("ARTICLE001");
        privateReqBean.setBrno(998);
        privateReqBean.setOtellerno("020000119");

        // 设置 bizContent
        bizContent.setPublicReqBean(publicReqBean);
        bizContent.setPrivateReqBean(privateReqBean);

        request.setBizContent(bizContent);

        // 发送请求
        try {
            BiomTransportCashTaskarticlemoverCanelboxassignResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("Response: " + JSON.toJSONString(response));
            } else {
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("ReturnMsg: " + response.getReturnMsg());
                System.out.println("Response: " + JSON.toJSONString(response));
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
