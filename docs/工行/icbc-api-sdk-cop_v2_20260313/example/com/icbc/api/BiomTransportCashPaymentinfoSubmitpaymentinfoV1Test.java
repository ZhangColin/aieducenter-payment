package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1;
import com.icbc.api.response.BiomTransportCashPaymentinfoSubmitpaymentinfoResponseV1;
import java.util.ArrayList;


public class BiomTransportCashPaymentinfoSubmitpaymentinfoV1Test {

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
        BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1 request = new BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1();
        BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1Biz biz = new BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1Biz();

// 设置 public 字段
        BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.PublicReqBean publicBean = new BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.PublicReqBean();
        publicBean.setChancode("702");
        publicBean.setOapp("F-BIOM");
        publicBean.setZoneno("200");
        publicBean.setOtellerno("020000139");

// 设置 private 字段
        BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.PrivateReqBean privateBean = new BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.PrivateReqBean();
        privateBean.setLinkStatus("1");
        privateBean.setBranchId("0020001242");
        privateBean.setZoneno("200");
        privateBean.setBrno("998");
        privateBean.setTellerno("020000139");
        privateBean.setUserName("李三");
        privateBean.setTaskId("00200009982510010046");
        privateBean.setTaskType("03002");
        privateBean.setHmdecolpayMode("3");
        privateBean.setoTaskStatus(8);
        privateBean.setHdAmount("0");
        privateBean.setThAmount("100000");
        privateBean.setTotalAmount("100000");
        privateBean.setCurrtype("001");
        privateBean.setSecondgoodsMode("1");
        privateBean.setStatus(8);
        privateBean.setAuthtlno("020000025");
        privateBean.setAuthtlnoName("旺旺1");

// 设置 thDetailList
        BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.ThDetailList thDetail = new BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.ThDetailList();
        thDetail.setBoxtype("1");
        thDetail.setBoxtypeDict("完整券");
        thDetail.setTotalShowMoneytemp(0);

// 设置 tableData
        BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.TableData tableData = new BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.TableData();
        tableData.setCashType("0");
        tableData.setCashTypeDict("纸币");
        tableData.setTotalShowMoneytemp(0);

// 设置 children
        BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.Children child = new BiomTransportCashPaymentinfoSubmitpaymentinfoRequestV1.Children();
        child.setNotes("");
        child.setDicCode("10000");
        child.setValuta("100.00");
        child.setPiece("");
        child.setCurrkind("0");
        child.setDicSort(1);
        child.setLocale("zh_CN");
        child.setDicName("壹佰元");
        child.setCurrtype("001");
        child.setClabel("壹佰元");
        child.setDicType("VALUTA");
        child.setValuta2("10000");
        ArrayList arrayList1 = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList1.add(child);
        arrayList2.add(tableData);
        arrayList3.add(thDetail);
        tableData.setChildren(arrayList1);
        thDetail.setTableData(arrayList2);
        privateBean.setThDetailList(arrayList3);
// 设置 pkDetailList（空）
        privateBean.setPkDetailList(new ArrayList());

// 组装 biz
        biz.setPublicReqBean(publicBean);
        biz.setPrivateReqBean(privateBean);
        request.setBizContent(biz);

        // 发送请求
        try {
            BiomTransportCashPaymentinfoSubmitpaymentinfoResponseV1 response = client.execute(request);
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
