package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1;
import com.icbc.api.response.BiomTransportCashIapprovebrnoappliproApproveoneapplicationResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashIapprovebrnoappliproApproveoneapplicationV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1 request = new BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1();

        BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1Biz bizContent = new BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1Biz();

        BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.PrivateReqBean();

        BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.PublicReqBean();

        List<BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.approveDetailMap> approveDetails = new ArrayList<>();
        BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.approveDetailMap approveDetailMap = new BiomTransportCashIapprovebrnoappliproApproveoneapplicationRequestV1.approveDetailMap();

        publicReqBean.setZoneno("200");

        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("998");
        privateReqBean.setApproveDetails(approveDetails);


        approveDetails.add(approveDetailMap);
        approveDetailMap.setValuta("10000");
        approveDetailMap.setNotes("");
        approveDetailMap.setDicCode("10000");
        approveDetailMap.setDicName("壹佰元");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIapprovebrnoappliproApproveoneapplicationResponseV1 response = client.execute(request);
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
