package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskcashmoverAddcashinoutconRequestV1;
import com.icbc.api.response.BiomTransportCashItaskcashmoverAddcashinoutconResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashItaskcashmoverAddcashinoutconV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashItaskcashmoverAddcashinoutconRequestV1 request = new BiomTransportCashItaskcashmoverAddcashinoutconRequestV1();

        BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.BiomTransportCashItaskcashmoverAddcashinoutconRequestV1Biz bizContent = new BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.BiomTransportCashItaskcashmoverAddcashinoutconRequestV1Biz();

        BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.PrivateReqBean();

        BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.PublicReqBean();

        List<BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.qutoMap> qutoMapList = new ArrayList<>();
        BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.qutoMap qutoMap = new BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.qutoMap();
        List<BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.valuteMap> valuteMapList = new ArrayList<>();
        BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.valuteMap valuteMap = new BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.valuteMap();
        List<BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.cashTypeMap> cashTypeMapList = new ArrayList<>();
        BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.cashTypeMap cashTypeMap = new BiomTransportCashItaskcashmoverAddcashinoutconRequestV1.cashTypeMap();


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setZoneno("200");
        privateReqBean.setOtellerno("465475424");
        privateReqBean.setBrno("998");
        privateReqBean.setBranchId("0020001242");
        privateReqBean.setTellerName("姓名");
        privateReqBean.setTaskId("00200009982709304896");
        privateReqBean.setType("1");
        privateReqBean.setInRole("XJ1008");
        privateReqBean.setBoxType("1");
        privateReqBean.setCurrType("001");
        privateReqBean.setBoxtype("1");
        privateReqBean.setCurrtype("001");
        privateReqBean.setAllAmount(10000);
        privateReqBean.setNotes("附言");
        privateReqBean.setAuthtlnoName("肖文全");
        privateReqBean.setAuthtlno("020000026");
        privateReqBean.setOutTelNo("80858");
        privateReqBean.setInTelNo("20690");
        privateReqBean.setHostOpertype("1");
        privateReqBean.setQutoList(qutoMapList);

        qutoMapList.add(qutoMap);
        qutoMap.setBoxType("1");
        qutoMap.setBoxTypeDict("完整券");
        qutoMap.setAmount(10000);
        qutoMap.setValuteList(valuteMapList);

        valuteMapList.add(valuteMap);
        valuteMap.setCashType("0");
        valuteMap.setCashTypeDict("纸币");
        valuteMap.setCashTypeList(cashTypeMapList);
        cashTypeMapList.add(cashTypeMap);

        cashTypeMap.setAmount(10000);
        cashTypeMap.setNotes("附言");
        cashTypeMap.setDicCode("10000");
        cashTypeMap.setValuta("10000");
        cashTypeMap.setCurrkind("0");
        cashTypeMap.setCurrType("001");
        cashTypeMap.setDicSort(1);
        cashTypeMap.setBoxType("1");
        cashTypeMap.setLocale("zh_CN");
        cashTypeMap.setDicType("VALUTA");
        cashTypeMap.setPiece("1");
        cashTypeMap.setDicName("壹佰元");
        cashTypeMap.setCurrtype("001");
        cashTypeMap.setClabel("壹佰元");
        cashTypeMap.setValutaDict("壹佰元");
        cashTypeMap.setValuta2("10000");
        cashTypeMap.setCashTypeDict("纸币");
        cashTypeMap.setIdx(0);
        cashTypeMap.set_expanded(true);
        cashTypeMap.set_level(2);
        cashTypeMap.set_show(true);

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashItaskcashmoverAddcashinoutconResponseV1 response = client.execute(request);
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
