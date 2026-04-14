package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIdistritaskproUpddistritaskRequestV1;
import com.icbc.api.response.BiomServicesIdistritaskproUpddistritaskResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomServicesIdistritaskproUpddistritaskV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesIdistritaskproUpddistritaskRequestV1 request = new BiomServicesIdistritaskproUpddistritaskRequestV1();

        BiomServicesIdistritaskproUpddistritaskRequestV1.BiomServicesIdistritaskproUpddistritaskRequestV1Biz bizContent = new BiomServicesIdistritaskproUpddistritaskRequestV1.BiomServicesIdistritaskproUpddistritaskRequestV1Biz();

        BiomServicesIdistritaskproUpddistritaskRequestV1.PrivateReqBean privateReqBean = new BiomServicesIdistritaskproUpddistritaskRequestV1.PrivateReqBean();

        BiomServicesIdistritaskproUpddistritaskRequestV1.PublicReqBean publicReqBean = new BiomServicesIdistritaskproUpddistritaskRequestV1.PublicReqBean();

        List<BiomServicesIdistritaskproUpddistritaskRequestV1.cMap> cList = new ArrayList<>();
        BiomServicesIdistritaskproUpddistritaskRequestV1.cMap cMap = new BiomServicesIdistritaskproUpddistritaskRequestV1.cMap();

        publicReqBean.setChncode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setDoneAmount("200000");
        privateReqBean.setAuthuserid("020000159");
        privateReqBean.setBrno("998");
        privateReqBean.setMoneyBagId("0416546445454");
        privateReqBean.setcList(cList);

        cList.add(cMap);
        cMap.setCashType("001");
        cMap.setCurrtype("001");
        cMap.setBoxType("001");
        cMap.setAmount("10000");
        cMap.setId("1");
        cMap.setCashTypeDict("纸币");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIdistritaskproUpddistritaskResponseV1 response = client.execute(request);
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
