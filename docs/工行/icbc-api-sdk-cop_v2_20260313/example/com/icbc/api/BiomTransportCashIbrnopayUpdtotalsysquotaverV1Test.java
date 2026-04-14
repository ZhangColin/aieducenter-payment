package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1;
import com.icbc.api.response.BiomTransportCashIbrnopayUpdtotalsysquotaverResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashIbrnopayUpdtotalsysquotaverV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1 request = new BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1();

        BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1Biz bizContent = new BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1Biz();

        BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.PrivateReqBean();

        BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.PublicReqBean();

        List<BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.aticleMap> aticleList = new ArrayList<>();
        BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.aticleMap aticleMap = new BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.aticleMap();
        List<BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.applyMap> applyList = new ArrayList<>();
        BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.applyMap applyMap = new BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.applyMap();
        List<BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.childrenMap> children = new ArrayList<>();
        BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.childrenMap childrenMap = new BiomTransportCashIbrnopayUpdtotalsysquotaverRequestV1.childrenMap();


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("998");
        privateReqBean.setTaskId("00200009982709304896");
        privateReqBean.setType("1");
        privateReqBean.setAuthtlnoName("肖文全");
        privateReqBean.setAuthtlno("020000026");
        privateReqBean.setAticleList(aticleList);

        aticleList.add(aticleMap);
        aticleMap.setAticleId("1");
        aticleMap.setAticleIdDict("款箱0001");
        aticleMap.setTotalShowMoneytemp(10000);
        aticleMap.setApplyList(applyList);

        applyList.add(applyMap);
        applyMap.setCashType("0");
        applyMap.setCashTypeDict("纸币");
        applyMap.setChildren(children);
        children.add(childrenMap);

        childrenMap.setAmount(10000);
        childrenMap.setPiece("1");
        childrenMap.setCurrtype("001");
        childrenMap.setValutaDict("壹佰元");
        childrenMap.set_expanded(true);
        childrenMap.set_level(2);
        childrenMap.set_show(true);
        childrenMap.setHalf_piece("1");
        childrenMap.setFull_piece("1");
        childrenMap.setUnfull_piece("1");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIbrnopayUpdtotalsysquotaverResponseV1 response = client.execute(request);
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
