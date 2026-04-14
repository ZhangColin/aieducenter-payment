package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIapplicationandapprovalCheckunpacksubmitRequestV1;
import com.icbc.api.response.BiomTransportCashIapplicationandapprovalCheckunpacksubmitResponseV1;

public class BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverV1Test {

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
        BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1 request = new BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1();
        BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1Biz biz = new BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1Biz();

// 设置 public 字段
        BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.PublicReqBean publicBean = new BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.PublicReqBean();
        publicBean.setOapp("F-BIOM");
        publicBean.setChancode("702");
        publicBean.setZoneno("200");
        publicBean.setOtellerno("020000139");

// 设置 private 字段
        BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.PrivateReqBean privateBean = new BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.PrivateReqBean();
        privateBean.setSimpplify("1");
        privateBean.setType("1");
        privateBean.setTaskType("03002");
        privateBean.setTaskId("00200009982510010047");
        privateBean.setTaskSubId("00200009982510010047");
        privateBean.setQuotaTell("020000139");
        privateBean.setQuotaTellName("李三");
        privateBean.setAuthtlno("020000025");
        privateBean.setAuthtlnoName("旺旺1");
        privateBean.setStruId("0020001242");
        privateBean.setZoneno("200");
        privateBean.setBrno("998");
        privateBean.setReqType("1");
        privateBean.setStatus(4);

// 设置 aticleList
        BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.AticleList aticleList = new BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.AticleList();
        aticleList.setAticleId("020009981010000006");
        aticleList.setAticleIdDict("款箱0006");

// 设置 applyList
        BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.ApplyList applyList = new BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.ApplyList();
        applyList.setCashType("0");
        applyList.setCashTypeDict("纸币");

// 设置 children
        BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.Children child = new BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverRequestV1.Children();
        child.setCashType("0");
        child.setAmount("10000");
        child.setValuta("10000");
        child.setTodoAmount("10000");
        child.setDoneAmount("0");
        child.setReqId("00200009982510010047");
        child.setDonePiece("1");
        child.setTaskType("10");
        child.setCurrDict("人民币");
        child.setAllAmount("10000");
        child.setPiece("1");
        child.setBoxtype("1");
        child.setAllPiece("1");
        child.setId("2");
        child.setApplyPiece("1");
        child.setAuthPiece("1");
        child.setTaskId("00200009982510010047-01");
        child.setCurrtype("001");
        child.setValutaDict("壹佰元");
        child.setTodoPiece("0");
        child.setCopyDonePiece("0");
        child.set_expanded(true);
        child.set_level(2);
        child.set_show(true);

        applyList.setChildren(List.of(child));
        aticleList.setApplyList(List.of(applyList));
        privateBean.setAticleList(List.of(aticleList));

// 组装 biz
        biz.setPublicReqBean(publicBean);
        biz.setPrivateReqBean(privateBean);
        request.setBizContent(biz);

        // 发送请求
        try {
            BiomTransportCashIapplicationandapprovalUpdtotalsysquotaverResponseV1 response = client.execute(request);
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
