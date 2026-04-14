package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIatsatmcashaddreqproReqaddRequestV1;
import com.icbc.api.response.BiomTransportCashIatsatmcashaddreqproReqaddResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashIatsatmcashaddreqproReqaddV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIatsatmcashaddreqproReqaddRequestV1 request = new BiomTransportCashIatsatmcashaddreqproReqaddRequestV1();

        BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.BiomTransportCashIatsatmcashaddreqproReqaddRequestV1Biz bizContent = new BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.BiomTransportCashIatsatmcashaddreqproReqaddRequestV1Biz();

        BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.PrivateReqBean();

        BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.PublicReqBean();

        List<BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.addMap> addList = new ArrayList<>();
        BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.addMap addMap = new BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.addMap();

        List<BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.deleteMap> deleteList = new ArrayList<>();
        BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.deleteMap deleteMap = new BiomTransportCashIatsatmcashaddreqproReqaddRequestV1.deleteMap();

        publicReqBean.setZoneno("200");
        publicReqBean.setChancode("200");
        publicReqBean.setOtellerno("2012140");

        privateReqBean.setAddList(addList);
        addList.add(addMap);
        addMap.setZoneno("10000");
        addMap.setChksiteno("2");
        addMap.setDevid("10000");
        addMap.setAddnotesno("05154");
        addMap.setPlanadddate("2023-04-21");
        addMap.setDevnotesamt("202321");

        privateReqBean.setDeleteList(deleteList);
        deleteList.add(deleteMap);
        deleteMap.setDevid("10000");
        deleteMap.setAddnotesno("05154");
        deleteMap.setPlanadddate("2023-04-21");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIatsatmcashaddreqproReqaddResponseV1 response = client.execute(request);
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
