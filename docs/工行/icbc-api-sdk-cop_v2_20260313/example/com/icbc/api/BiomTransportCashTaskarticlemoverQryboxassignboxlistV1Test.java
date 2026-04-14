package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1;
import com.icbc.api.response.BiomTransportCashTaskarticlemoverQryboxassignboxlistResponseV1;

public class BiomTransportCashTaskarticlemoverQryboxassignboxlistV1Test {

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
        BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1 request = new BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1();

        // 创建 BizContent 对象
        BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1.BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1Biz bizContent = new BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1.BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1Biz();

        // 创建 publicReqBean
        BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1.PublicReqBean();
        publicReqBean.setChancode("702");
        publicReqBean.setZoneno("200");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("020000119");

        // 创建 privateReqBean
        BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashTaskarticlemoverQryboxassignboxlistRequestV1.PrivateReqBean();
        privateReqBean.setStruId("0020001242");
        privateReqBean.setAllotDate("20250405");
        privateReqBean.setType("0");
        privateReqBean.setLocSeq("LOC001");
        privateReqBean.setAssignDate("20250405");
        privateReqBean.setTaskStatus("0");
        privateReqBean.setInitflag("1");
        privateReqBean.setPageNum(1);
        privateReqBean.setPageSize(10);

        // 设置 bizContent
        bizContent.setPublicReqBean(publicReqBean);
        bizContent.setPrivateReqBean(privateReqBean);

        request.setBizContent(bizContent);

        // 发送请求
        try {
            BiomTransportCashTaskarticlemoverQryboxassignboxlistResponseV1 response = client.execute(request);
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
