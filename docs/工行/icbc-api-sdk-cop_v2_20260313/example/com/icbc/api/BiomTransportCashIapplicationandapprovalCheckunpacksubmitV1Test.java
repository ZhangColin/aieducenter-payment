package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1;
import com.icbc.api.response.BiomTransportCashIapplicationandapprovalCheckunpacksubmitResponseV1;

public class BiomTransportCashIapplicationandapprovalCheckunpacksubmitV1Test {

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
        BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1 request = new BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1();
        BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1Biz biz = new BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1Biz();

        // 设置 public 字段
        BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.PublicReqBean publicBean = new BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.PublicReqBean();
        publicBean.setOapp("F-BIOM");
        publicBean.setChancode("702");
        publicBean.setZoneno("200");
        publicBean.setOtellerno("020000139");

        // 设置 private 字段
        BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.PrivateReqBean privateBean = new BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.PrivateReqBean();
        privateBean.setSimpplify("1");
        privateBean.setType("1");
        privateBean.setTaskType("03001");
        privateBean.setTaskId("00200009982510010048");
        privateBean.setTaskSubId("00200009982510010048");
        privateBean.setVerTell("020000139");
        privateBean.setVerTellName("李三");
        privateBean.setVauthtlno("020000025");
        privateBean.setVauthtlnoName("旺旺1");
        privateBean.setStruId("0020001242");
        privateBean.setZoneno("200");
        privateBean.setBrno("998");
        privateBean.setReqType("4");
        privateBean.setAccountType("1");
        privateBean.setoTaskStatus(4);
        privateBean.setStatus(4);

// 设置 aticleList
        BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.AticleList aticleList = new BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.AticleList();
        aticleList.setCashType("0");
        aticleList.setCashTypeDict("纸币");
        aticleList.setAticleId("");
        aticleList.set_expanded(true);
        aticleList.set_level(1);
        aticleList.set_show(true);

// 设置 children
        BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.Children child = new BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1.Children();
        child.setCashType("0");
        child.setAmount("0");
        child.setNotes("");
        child.setDicCode("10000");
        child.setValuta("10000");
        child.setBoxPiece("0");
        child.setCurrkind("0");
        child.setDicSort(1);
        child.setLocale("zh_CN");
        child.setDicType("VALUTA");
        child.setPiece("1");
        child.setBoxtype("1");
        child.setApplyPiece("1");
        child.setDicName("壹佰元");
        child.setAuthPiece("1");
        child.setCurrtype("001");
        child.setClabel("壹佰元");
        child.setValutaDict("壹佰元");
        child.set_expanded(true);
        child.set_level(2);
        child.setParent("~private~aticleList~0");
        child.set_show(true);

        aticleList.setChildren(List.of(child));
        privateBean.setAticleList(List.of(aticleList));

        // 组装 biz
        biz.setPublicReqBean(publicBean);
        biz.setPrivateReqBean(privateBean);
        request.setBizContent(biz);

        // 发送请求
        try {
            BiomTransportCashIapplicationandapprovalCheckunpacksubmitResponseV1 response = client.execute(request);
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
