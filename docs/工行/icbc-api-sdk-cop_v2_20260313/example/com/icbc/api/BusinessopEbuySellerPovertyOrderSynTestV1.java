package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV1;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV1.OrderPovertyDTORequestV1;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV1.OrderPovertyDTORequestV1.OrderPovertyDTO;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV1.OrderPovertyDTORequestV1.OrderPovertyDTO.PaymentPovertyDTO;
import com.icbc.api.request.BusinessopEbuySellerPovertyOrderSynRequestV1.OrderPovertyDTORequestV1.OrderPovertyDTO.ProdPovertyDTO;
import com.icbc.api.response.BusinessopEbuySellerPovertyOrderSynResponseV1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// E采购扶贫订单同步
public class BusinessopEbuySellerPovertyOrderSynTestV1 {

	// APPID（分行提供）
	protected static final String APP_ID = "[需替换]合作方自己申请";
	// 合作方私钥（合作方自己生成）
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	// APIP网关公钥（分行提供）
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	// 签名秘钥（合作方自己生成）
    private static final String ENCRYPT_KEY = "[需替换]合作方自己生成";
	// 工行API网关地址
	private static final String HOST = "[需替换]工行API网关地址";
	// 外网地址
	private static final String API_URL = HOST+"/api/businessop/ebuy/seller/povertyordersyn/V1";

	public static void main(String[] args) {
		try {
			test();
		} catch (Exception e) {
			
		}
	}
	/**
	 * 
	 * @throws Exception
	 * 
	 */
	public static void test() throws Exception {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
		BusinessopEbuySellerPovertyOrderSynRequestV1 request = new BusinessopEbuySellerPovertyOrderSynRequestV1();
		request.setServiceUrl(API_URL);
		OrderPovertyDTORequestV1 biz = new OrderPovertyDTORequestV1();
		List<OrderPovertyDTO> orderList = new ArrayList<OrderPovertyDTO>();
		OrderPovertyDTO order = new OrderPovertyDTO();
		//根据API文档送入
		order.setAppId("1");
		order.setSellerName("XXXX公司");
		order.setBuyerName("B公司");
		order.setOrderNo("xxxxaaaasss");
		order.setOrderType("1");
		order.setOrderTotalMoney(BigDecimal.valueOf(0.99));
		order.setOrderDate("2016-10-29 20:44:38");
		order.setSellerNationalPoorCounty("1");
		order.setBuyerNationalPoorCounty("1");
		order.setSellerProvincePoorCounty("1");
		order.setBuyerProvincePoorCounty("1");
		order.setFinancingAmount(BigDecimal.valueOf(0.99));
		order.setFinancingType("01");
		order.setContractId("1233211234567");
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
		prodDto.setProdTotalAmt("个");
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
		BusinessopEbuySellerPovertyOrderSynResponseV1 response;
		try {
			System.out.println("Request: " + JSONObject.toJSONString(biz));
			response = client.execute(request, System.currentTimeMillis() + "");
			System.out.println("Response: " + JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				System.out.println("execute success");
			} else {
				System.out.println("execute failue");
			}
		} catch (IcbcApiException e) {
			System.out.println("execute exception" + e);
			e.printStackTrace();
		}
	}

}
