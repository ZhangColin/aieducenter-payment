package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV2;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV2.OrderPovertyDTORequestV2;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV2.OrderPovertyDTORequestV2.OrderPovertyDTO;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV2.OrderPovertyDTORequestV2.OrderPovertyDTO.PaymentPovertyDTO;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV2.OrderPovertyDTORequestV2.OrderPovertyDTO.ProdPovertyDTO;
import com.icbc.api.response.BusinessopEbuySellerPovertyOrderSynResponseV2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * E采购扶贫订单同步
 */
public class BusinessopEbuySellerPovertyOrderSynTestV2 {

	// 合作方私钥（合作方自己生成）
	private static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXY3LeSkYCtpk2mmyeLKYA2OrFyp36vW7OOkfU0w6OkAnGnOFY5qn92ig49Psn2sw96yRbdbq2466SM9Vy13RCYLg2WnGDNmx2+QZr1j5gSmCusk76fVvFv5c0/WCdg/9IQt/NPLR3Pg9vyVGTrw1zZwP5MRtizOTTBAMMgfbPOD8JrS2+my1CwX7P9vrBxvQZMLGMHJsMneaAuoG9Kfo7UpEUnIBiKLq/9DbDMQQ1fSS0l0xA64IJLuLG+nwiwzgyWX7AblQtCtdPTHmBCPk4o8Y5hpNAyQ4dtcWk1F90i/YxexyDwbDBnPJ9uP+HFp895Je8XoPy3VqMEQToEdF/AgMBAAECggEAYoeJVYAFvPJXGaC8HN52VIrZkk3jMDRmj7aC1dNf4A2DL08fo5rM/LkmAJwBctkGoS4Pha9EDJB3KBSwUYRE2IbWnhmTU5DyWySMt1bDVixSbKEEoidAuZ2OriBS53HnjoJn4s4lro4nwdvHSlVebcOYZHE/n7g3Hde4nEVh+09hb6JXXFC3EK/7p80zt0wjp1yv9/iLPgXSvyXRW9qM8ED5JbDpsO4nDvF5ZwXWjQ3iauQjsKjVgYcNg31PNq6tl39I3sivx2AP8IYoYz/J5IukKrc6Yeb9KLyjrhMzeoJr3gzpp5T/VPb2vgeH+z8ytU91inMiS7WqmD+xNq33QQKBgQDSph1K/fq8+ZyckERklNudVdtsIKHtNL5kFvPFZUSF94oUgg6y6IkWO1wtDUIUAxi9B88/l34TBeYqmvCfDe/MN7npAjNbDhh6m6VyPZqgUkCdLCUqPpoI3vEP7N8iLPCXZoVs6nvrOqh0tbsLUplQpDc3KOzQqYhhGGyoqZNfUQKBgQC3+zRUH0Y8TUdtkD+VWB74+FzfmgalNZQZMjVbhkpmPWLCj69IxtHIFwWtKQQdeAvYii8PgHrstkQOSEcpXTItIJXcHpwRCypyZpO1i/ScvflhdHJdWKYsaho7k/peGWTP09mD7UHv3SzEC0taU6GzvUE7ZsZbZCwQf8OPkbIPzwKBgQCROH4HXjMHJtpmQTs1UD5bKH6d+51Gh88d94jVNCxaYz5pRwRVKOShVAkG8hX07/GVGBYz0//Ro4Z7Jtft/1CrLFvZnG/FPWF43Wbg7EF2d14ePPneNsxAg1oTTYzC5dJzvfUMEled4pGR7Ftnwej6ZEvAllE0xTvFHG6ElGuKEQKBgDuIBzipHfrpLpGfOVPDvdHl23dmyXuvyIoWmvlQjMZkWo6GG7u6yaqq+GU9fl1iuMKKhAvKEPyDRaYxcQVLaK79ekBNj0ZcjJGT63u1G6OUPRtxqIfX5X09n9tjWIIWSW6OKDXuEYXh1VhiJzHFGe+k73SfHtP3p+J21PqhtDI1AoGBAMQZUXTDb6wSA76b2Lc5/pqYYvoO1GQYJvWm8o4ZTlno7iBdFTAOEz7ekKFmyuW/NiZq58hOA5PQevRmXKggLix5qSlvjLJc8y93gC6kqW/Znuaf2fYHR7fl7jR1neltETtEWvrW0Ei7YugeBb4sim1lagbPLI87Ab8hwSJahwHh";
	// APIP网关公钥（分行提供）
	private static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 签名秘钥（合作方自己生成）
	private static final String ENCRYPT_KEY = "mQbJILokBccRHUkS+XBk7A==";
	// APPID（分行提供）
	private static final String APP_ID = "1000XXXXXX02509";
	// 内网地址
	private static final String API_URL = "http://IP/api/businessop/ebuy/seller/povertyordersyn/V2";
	// 外网地址
//	private static final String API_URL = "https://apipcs4.dccnet.com.cn/api/businessop/ebuy/seller/povertyordersyn/V2";
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
		BusinessopEbuySellerPovertyOrderSynRequestV2 request = new BusinessopEbuySellerPovertyOrderSynRequestV2();
		request.setServiceUrl(API_URL);
		OrderPovertyDTORequestV2 biz = new OrderPovertyDTORequestV2();
		List<OrderPovertyDTO> orderList = new ArrayList<OrderPovertyDTO>();
		OrderPovertyDTO order = new OrderPovertyDTO();
		//根据API文档送入
		biz.setAppId("1000xxxxxx509");
		order.setSellerName("XXXX公司");
		order.setBuyerName("B公司");
		order.setOrderNo("xxxxaaaasss");
		order.setOrderType("1");
		order.setOrderTotalMoney(BigDecimal.valueOf(0.99));
		order.setOrderDate("2016-10-29 20:44:38");
		order.setOutBuyerId("100");
		order.setOutSellerId("200");
		order.setSellerNationalPoorCounty("1");
		order.setBuyerNationalPoorCounty("1");
		order.setBuyerType("3300xxxx110");
		order.setSellerProvincePoorCounty("1");
		order.setBuyerProvincePoorCounty("1");
		List<PaymentPovertyDTO> paymentList = new ArrayList<PaymentPovertyDTO>();
		List<ProdPovertyDTO> prodList = new ArrayList<ProdPovertyDTO>();
		//支付信息集合可为空
		PaymentPovertyDTO dto = new PaymentPovertyDTO();
		dto.setPaymentTime("2020-05-29 20:44:38");
		dto.setPayAmt(BigDecimal.valueOf(0.99));
		paymentList.add(dto);
		// 拼装商品信息
		ProdPovertyDTO prodDto = new ProdPovertyDTO();
		prodDto.setProdBuyAmt(BigDecimal.valueOf(0.99));
		prodDto.setProdId("xxxaaadssdfa");
		prodDto.setProdName("商品名称1");
		prodDto.setProdType("具体的商品类型");
		prodDto.setProdTotalAmt("个");
		prodDto.setProdNumber(1L);
		prodDto.setTaxTotalPrice(BigDecimal.valueOf(0.99));
		prodDto.setProdBuyAmt(BigDecimal.valueOf(0.99));
		prodDto.setTaxTotalPrice(BigDecimal.valueOf(0.99));
		ProdPovertyDTO prodDto2 = new ProdPovertyDTO();
		prodDto2.setProdBuyAmt(BigDecimal.valueOf(0.99));
		prodDto2.setProdId("xxxaaadssdfa");
		prodDto2.setProdName("商品名称2");
		prodDto2.setProdType("具体的商品类型");
		prodDto2.setProdTotalAmt("个");
		prodDto2.setProdNumber(1L);
		prodDto2.setTaxTotalPrice(BigDecimal.valueOf(0.99));
		prodDto2.setProdBuyAmt(BigDecimal.valueOf(0.99));
		prodDto2.setTaxTotalPrice(BigDecimal.valueOf(0.99));
		prodList.add(prodDto2);
		// 放入业务 DTO
		order.setPaymentList(paymentList);
		order.setProdList(prodList);
		orderList.add(order);
		biz.setOrderList(orderList);
		request.setBizContent(biz);
		BusinessopEbuySellerPovertyOrderSynResponseV2 response;
		try {
			//System.out.println("Request: " + JSONObject.toJSONString(biz));
			response = client.execute(request, System.currentTimeMillis() + "");
			//System.out.println("Response: " + JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				//System.out.println("execute success");
			} else {
				//System.out.println("execute failue");
			}
		} catch (IcbcApiException e) {
			//System.out.println("execute exception" + e);
			e.printStackTrace();
		}
	}

}
