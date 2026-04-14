package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIatmdistritaskInserttaskinfoRequestV1;
import com.icbc.api.response.BiomServicesIatmdistritaskInserttaskinfoResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomServicesICsrcToFhProfhBackToBiomV1Test {


    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesICsrcToFhProfhBackToBiomRequestV1 request = new BiomServicesICsrcToFhProfhBackToBiomRequestV1();

        BiomServicesICsrcToFhProfhBackToBiomRequestV1.BiomServicesICsrcToFhProfhBackToBiomRequestV1Biz bizContent = new BiomServicesICsrcToFhProfhBackToBiomRequestV1.BiomServicesICsrcToFhProfhBackToBiomRequestV1Biz();

        BiomServicesICsrcToFhProfhBackToBiomRequestV1.PrivateReqBean privateReqBean = new BiomServicesICsrcToFhProfhBackToBiomRequestV1.PrivateReqBean();

        BiomServicesICsrcToFhProfhBackToBiomRequestV1.PublicReqBean publicReqBean = new BiomServicesICsrcToFhProfhBackToBiomRequestV1.PublicReqBean();

        List<BiomServicesICsrcToFhProfhBackToBiomRequestV1.aticleIdMap> taskList = new ArrayList<>();
        BiomServicesICsrcToFhProfhBackToBiomRequestV1.aticleIdMap aticleIdMap = new BiomServicesICsrcToFhProfhBackToBiomRequestV1.aticleIdMap();

        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");


        privateReqBean.setBrno("998");
        privateReqBean.setZoneno("200");
        privateReqBean.setMacno("200");
        privateReqBean.setTaskList(taskList);

        taskList.add(aticleIdMap);
        aticleIdMap.setApplynoe("050200953083864000312002515");
        aticleIdMap.setRemark("050200953083864000312002515");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesICsrcToFhProfhBackToBiomRequestV1 response = client.execute(request);
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
