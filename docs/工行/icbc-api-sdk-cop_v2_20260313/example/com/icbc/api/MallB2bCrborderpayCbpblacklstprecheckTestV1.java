package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bBlackPreCheckServiceRequestV1;
import com.icbc.api.request.MallB2bBlackPreCheckServiceRequestV1.MallB2bBlackPreCheckServiceRequestV1Biz;
import com.icbc.api.response.MallB2bBlackPreCheckServiceResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * 黑名单预检查服务
 * @author kfzx-jinsx01
 * @version 2019年4月版
 * @date 2019/4/10 20:03
 */
public class MallB2bCrborderpayCbpblacklstprecheckTestV1 {

    protected static final String	MY_PRIVATE_KEY		= "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXY3LeSkYCtpk2mmyeLKYA2OrFyp36vW7OOkfU0w6OkAnGnOFY5qn92ig49Psn2sw96yRbdbq2466SM9Vy13RCYLg2WnGDNmx2+QZr1j5gSmCusk76fVvFv5c0/WCdg/9IQt/NPLR3Pg9vyVGTrw1zZwP5MRtizOTTBAMMgfbPOD8JrS2+my1CwX7P9vrBxvQZMLGMHJsMneaAuoG9Kfo7UpEUnIBiKLq/9DbDMQQ1fSS0l0xA64IJLuLG+nwiwzgyWX7AblQtCtdPTHmBCPk4o8Y5hpNAyQ4dtcWk1F90i/YxexyDwbDBnPJ9uP+HFp895Je8XoPy3VqMEQToEdF/AgMBAAECggEAYoeJVYAFvPJXGaC8HN52VIrZkk3jMDRmj7aC1dNf4A2DL08fo5rM/LkmAJwBctkGoS4Pha9EDJB3KBSwUYRE2IbWnhmTU5DyWySMt1bDVixSbKEEoidAuZ2OriBS53HnjoJn4s4lro4nwdvHSlVebcOYZHE/n7g3Hde4nEVh+09hb6JXXFC3EK/7p80zt0wjp1yv9/iLPgXSvyXRW9qM8ED5JbDpsO4nDvF5ZwXWjQ3iauQjsKjVgYcNg31PNq6tl39I3sivx2AP8IYoYz/J5IukKrc6Yeb9KLyjrhMzeoJr3gzpp5T/VPb2vgeH+z8ytU91inMiS7WqmD+xNq33QQKBgQDSph1K/fq8+ZyckERklNudVdtsIKHtNL5kFvPFZUSF94oUgg6y6IkWO1wtDUIUAxi9B88/l34TBeYqmvCfDe/MN7npAjNbDhh6m6VyPZqgUkCdLCUqPpoI3vEP7N8iLPCXZoVs6nvrOqh0tbsLUplQpDc3KOzQqYhhGGyoqZNfUQKBgQC3+zRUH0Y8TUdtkD+VWB74+FzfmgalNZQZMjVbhkpmPWLCj69IxtHIFwWtKQQdeAvYii8PgHrstkQOSEcpXTItIJXcHpwRCypyZpO1i/ScvflhdHJdWKYsaho7k/peGWTP09mD7UHv3SzEC0taU6GzvUE7ZsZbZCwQf8OPkbIPzwKBgQCROH4HXjMHJtpmQTs1UD5bKH6d+51Gh88d94jVNCxaYz5pRwRVKOShVAkG8hX07/GVGBYz0//Ro4Z7Jtft/1CrLFvZnG/FPWF43Wbg7EF2d14ePPneNsxAg1oTTYzC5dJzvfUMEled4pGR7Ftnwej6ZEvAllE0xTvFHG6ElGuKEQKBgDuIBzipHfrpLpGfOVPDvdHl23dmyXuvyIoWmvlQjMZkWo6GG7u6yaqq+GU9fl1iuMKKhAvKEPyDRaYxcQVLaK79ekBNj0ZcjJGT63u1G6OUPRtxqIfX5X09n9tjWIIWSW6OKDXuEYXh1VhiJzHFGe+k73SfHtP3p+J21PqhtDI1AoGBAMQZUXTDb6wSA76b2Lc5/pqYYvoO1GQYJvWm8o4ZTlno7iBdFTAOEz7ekKFmyuW/NiZq58hOA5PQevRmXKggLix5qSlvjLJc8y93gC6kqW/Znuaf2fYHR7fl7jR1neltETtEWvrW0Ei7YugeBb4sim1lagbPLI87Ab8hwSJahwHh";
    protected static final String	APIGW_PUBLIC_KEY	= "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static final String	APP_ID				= "10000000000007354574";
    protected static final String	ENCRYPT_KEY			= "mQbJILokBccRHUkS+XBk7A==";


    public static void main(String[] args) throws Exception {
        new MallB2bCrborderpayCbpblacklstprecheckTestV1().test_cop();
    }

    public void test_cop() throws Exception {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
        MallB2bBlackPreCheckServiceRequestV1 request = new MallB2bBlackPreCheckServiceRequestV1();
        request.setServiceUrl("http://ip:port/api/mall/b2b/crborderpay/cbpblacklstprecheck/V1");
        MallB2bBlackPreCheckServiceRequestV1Biz biz = new MallB2bBlackPreCheckServiceRequestV1Biz();
        String json = getJson(); // 获取Json
        biz.setReqJsonData(json);
        request.setBizContent(biz);

        MallB2bBlackPreCheckServiceResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("成功");
            } else {
                // 失败
                System.out.println("失败");
            }
            System.out.println(response.getReturnCode() + "  " + response.getReturnMsg());
            System.out.println(JSONObject.toJSONString(response.getResdata()));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }

    public String getJson() throws Exception {
        List<OrderList> orderList = new ArrayList<OrderList>();
        orderList.add(new OrderList("201809191936380001", "申请提款1", "45678.45", "166", "美国", "加州省", "加州市", "美国 加州省 加州市", "张", "三", "ddccbb"));
        orderList.add(new OrderList("201809191936380002", "申请提款2", "454378.65", "166", "TWD", "TWD省", "TWD市", "TWD TWD省 TWD市", "曾", "凡", "456"));
        BizContent bizContent = new BizContent("201809191936380000", "提款", "e00000011", "9001326", orderList);
        String data = JSON.toJSONString(bizContent);
        return data;
    }
    /**
     * 请求数据项
     * @author	kfzx-jinsx01
     * @date	2018年9月27日上午11:04:44
     * @version	2018年11月版
     */
    class BizContent {
        private String applyNo;
        private String applyName;
        private String parentVendorId;
        private String vendorId;
        private List<OrderList> orderList;
        public BizContent() {
            super();
        }
        /**
         * @param applyNo 申请编号
         * @param applyName 申请名称
         * @param parentVendorId 平台供应商编号
         * @param vendorId 供应商编号
         * @param orderList 订单信息集合
         */
        public BizContent(String applyNo, String applyName, String parentVendorId, String vendorId, List<OrderList> orderList) {
            super();
            this.applyNo = applyNo;
            this.applyName = applyName;
            this.parentVendorId = parentVendorId;
            this.vendorId = vendorId;
            this.orderList = orderList;
        }
        public String getApplyNo() {
            return applyNo;
        }
        public void setApplyNo(String applyNo) {
            this.applyNo = applyNo;
        }
        public String getApplyName() {
            return applyName;
        }
        public void setApplyName(String applyName) {
            this.applyName = applyName;
        }
        public String getParentVendorId() {
            return parentVendorId;
        }
        public void setParentVendorId(String parentVendorId) {
            this.parentVendorId = parentVendorId;
        }
        public String getVendorId() {
            return vendorId;
        }
        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
        }
        public List<OrderList> getOrderList() {
            return orderList;
        }
        public void setOrderList(List<OrderList> orderList) {
            this.orderList = orderList;
        }

    }
    /**
     * 订单信息集合
     * @author	kfzx-jinsx01
     * @date	2018年9月27日上午11:04:30
     * @version	2018年11月版
     */
    class OrderList {
        private String orderNo;
        private String orderName;
        private String orderAmount;
        private String orderCurrent;
        private String payors_Country;
        private String payors_Province;
        private String payors_City;
        private String payors_Street;
        private String payors_LastName;
        private String payors_FirstName;
        private String payors_Account;
        public OrderList() {
            super();
        }
        /**
         * @param orderNo 订单编号
         * @param orderName 订单名称
         * @param orderAmount 订单金额
         * @param orderCurrent 订单币种
         * @param payors_Country 付款人国家
         * @param payors_Province 付款人省份
         * @param payors_City 付款人市区
         * @param payors_Street 付款人详细地址
         * @param payors_LastName 付款人姓
         * @param payors_FirstName 付款人名
         * @param payors_Account 付款人账号
         */
        public OrderList(String orderNo, String orderName, String orderAmount, String orderCurrent, String payors_Country, String payors_Province, String payors_City, String payors_Street, String payors_LastName, String payors_FirstName, String payors_Account) {
            super();
            this.orderNo = orderNo;
            this.orderName = orderName;
            this.orderAmount = orderAmount;
            this.orderCurrent = orderCurrent;
            this.payors_Country = payors_Country;
            this.payors_Province = payors_Province;
            this.payors_City = payors_City;
            this.payors_Street = payors_Street;
            this.payors_LastName = payors_LastName;
            this.payors_FirstName = payors_FirstName;
            this.payors_Account = payors_Account;
        }
        public String getOrderNo() {
            return orderNo;
        }
        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
        public String getOrderName() {
            return orderName;
        }
        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }
        public String getOrderAmount() {
            return orderAmount;
        }
        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }
        public String getOrderCurrent() {
            return orderCurrent;
        }
        public void setOrderCurrent(String orderCurrent) {
            this.orderCurrent = orderCurrent;
        }
        public String getPayors_Country() {
            return payors_Country;
        }
        public void setPayors_Country(String payors_Country) {
            this.payors_Country = payors_Country;
        }
        public String getPayors_Province() {
            return payors_Province;
        }
        public void setPayors_Province(String payors_Province) {
            this.payors_Province = payors_Province;
        }
        public String getPayors_City() {
            return payors_City;
        }
        public void setPayors_City(String payors_City) {
            this.payors_City = payors_City;
        }
        public String getPayors_Street() {
            return payors_Street;
        }
        public void setPayors_Street(String payors_Street) {
            this.payors_Street = payors_Street;
        }
        public String getPayors_LastName() {
            return payors_LastName;
        }
        public void setPayors_LastName(String payors_LastName) {
            this.payors_LastName = payors_LastName;
        }
        public String getPayors_FirstName() {
            return payors_FirstName;
        }
        public void setPayors_FirstName(String payors_FirstName) {
            this.payors_FirstName = payors_FirstName;
        }
        public String getPayors_Account() {
            return payors_Account;
        }
        public void setPayors_Account(String payors_Account) {
            this.payors_Account = payors_Account;
        }
    }
}
