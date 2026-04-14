package com.icbc.api;


import com.alibaba.fastjson.JSON;
import com.icbc.api.request.MybankOspayCborderVendorOrderdetailapplyRequestV1;
import com.icbc.api.response.MybankOspayCborderVendorOrderdetailapplyResponseV1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MybankOspayCborderVendorOrderdetailapplyTestV1 {

    //应用私钥
    protected static final String MY_PRIVATE_KEY = "";
    protected static final String APIGW_PUBLIC_KEY = "";
    protected static final String APP_ID = "";

    protected static final String SIGN_TYPE = "RSA2";
    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankOspayCborderVendorOrderdetailapplyRequestV1 request = new MybankOspayCborderVendorOrderdetailapplyRequestV1();
        request.setServiceUrl("http://ip:port/api/mybank/ospay/cborder/vendor/orderdetailapply/V1");

        MybankOspayCborderVendorOrderdetailapplyRequestV1.MybankOspayCborderVendorOrderdetailapplyRequestV1Biz biz = new MybankOspayCborderVendorOrderdetailapplyRequestV1.MybankOspayCborderVendorOrderdetailapplyRequestV1Biz(); //订单1
        List<MybankOspayCborderVendorOrderdetailapplyRequestV1.MybankOspayCborderVendorOrderdetailapplyRequestV1Biz.VendorOrderInfoRequestV1Biz.VendorOrderProdRequestV1Biz> orderProdList = new ArrayList<>();
        MybankOspayCborderVendorOrderdetailapplyRequestV1.MybankOspayCborderVendorOrderdetailapplyRequestV1Biz.VendorOrderInfoRequestV1Biz.VendorOrderProdRequestV1Biz orderProd = new MybankOspayCborderVendorOrderdetailapplyRequestV1.MybankOspayCborderVendorOrderdetailapplyRequestV1Biz.VendorOrderInfoRequestV1Biz.VendorOrderProdRequestV1Biz();
        orderProd.setProdName("苹果");
        orderProd.setProdBrandName("123");
        orderProd.setProdModel("456");
        orderProd.setProdNorms("789");
        orderProd.setProdAmount(new BigDecimal(10.00));
        orderProd.setProdCurrent("001");
        orderProd.setProdNumber((long) 10);
        orderProdList.add(orderProd);
        List<MybankOspayCborderVendorOrderdetailapplyRequestV1.MybankOspayCborderVendorOrderdetailapplyRequestV1Biz.VendorOrderInfoRequestV1Biz> orderList = new ArrayList<>();
        MybankOspayCborderVendorOrderdetailapplyRequestV1.MybankOspayCborderVendorOrderdetailapplyRequestV1Biz.VendorOrderInfoRequestV1Biz order = new MybankOspayCborderVendorOrderdetailapplyRequestV1.MybankOspayCborderVendorOrderdetailapplyRequestV1Biz.VendorOrderInfoRequestV1Biz();
        order.setOrderNo("201809280000000752");
        order.setOrderName("123");
        order.setOrderAmount(new BigDecimal(100.00));
        order.setOrderCurrent("001");
        order.setPayorsCountry("英国");
        order.setPayorsProvince("夏威夷州");
        order.setPayorsCity("曼彻斯特市");
        order.setPayorsStreet("456");
        order.setPayorsType("C");
        order.setPayorsName("李四");
        order.setPayorsAccount("111111111111");
        order.setBuyerCountry("英国");
        order.setBuyerProvince("夏威夷州");
        order.setBuyerCity("曼彻斯特市");
        order.setBuyerStreet("456");
        order.setBuyerName("阿道夫");
        order.setBuyerEmail("90548764@163.com");
        order.setPayorsCountryCode("1111");
        order.setTradecode("111");
        order.setComments("111");
        order.setProdType("001");
        order.setOrderTime("2023-03-10 18:00:00");
        order.setTradePlatform("Amazon");
        order.setOrderProdList(orderProdList);
        orderList.add(order);
        biz.setApplyNo("201809280000000100");
        biz.setApplyName("科创申请");
        biz.setVendorId("000546549");
        biz.setParentVendorId("10001431");
        biz.setApplyCurrent("001");
        biz.setApplyAmount(new BigDecimal(100.00));
        biz.setCurrentBatchNo("1");
        biz.setTotalBatchNo("1");
        biz.setOrderList(orderList);
        request.setBizContent(biz);

        try {
            MybankOspayCborderVendorOrderdetailapplyResponseV1 response = client.execute(request);
            if(response.getReturnCode()==0){
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                // 失败
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
