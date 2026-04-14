package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.JftApiInvoiceNormalPreOrderRequestV1;
import com.icbc.api.request.JftApiInvoiceNormalPreOrderRequestV1.JftApiInvoiceNormalPreOrderBiz;
import com.icbc.api.request.JftApiInvoiceNormalPreOrderRequestV1.InvoiceDetailInfo;
import com.icbc.api.response.JftApiInvoiceNormalPreOrderResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JftApiInvoiceRefinedOilPreOrderTestV1 {
	private final Logger log = LoggerFactory.getLogger(getClass());
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");

		JftApiInvoiceRefinedOilPreOrderRequestV1 request = new JftApiInvoiceRefinedOilPreOrderRequestV1();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/invoice/refinedOil/pre/order/V1");
		JftApiInvoiceRefinedOilPreOrderBiz bizContent = new JftApiInvoiceRefinedOilPreOrderBiz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setOutVendorId("1224536");//子商户标识：提交支付请求的子商户编号
		bizContent.setOutUserId("M201801112326");//用户标识
		bizContent.setOutOrderId("20190102_3");//平台对接方ID：平台商户订单ID，确保唯一
		bizContent.setMac("a3-b4-c5");//手机Mac:支付提交用户手机Mac地址
		bizContent.setTrxIp("xx.xx.xx.xx");//交易IP：支付提交用户IP地址
		bizContent.setPayChannel("99");//交易渠道:固定取99
		bizContent.setInvoiceNotifyUrl("");//商户通知URL：商户接收通知消息URL
		bizContent.setInvoiceType("01");
		bizContent.setOrderAmount("0.02");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
		bizContent.setBuyerPersonType("N");
		bizContent.setTaxpayerNo("9xxT");
		bizContent.setTaxpayerName("xx公司");
		bizContent.setSellerAddress("xx市xx区");
		bizContent.setPayMode("01");//支付模式
		bizContent.setOutInvoiceId("KPLSH25110513481");
		bizContent.setSpecificElements("01");
		bizContent.setAreaCode("11100000000");
		bizContent.setShouldTaxLocation("应税行为发生地");
		bizContent.setPayeeAccountNo("收款银行账号");
		bizContent.setPayeeBankName("收款银行名称");
		bizContent.setDrawerName("开票人");
		bizContent.setDrawerCardNumber("140234xxxxxxxx");
		bizContent.setDrawerCardType("201");
		bizContent.setNotes("notes");
		bizContent.setConnectType("02");
		bizContent.setPartnerType("01");
		bizContent.setPartnerMerTrueAppId("11000000000000006808");
		bizContent.setPartnerTrxChannel("06");
		bizContent.setIcbcOrderId("2025110051234567890");


		//发票明细信息
		List<JftApiInvoiceRefinedOilPreOrderRequestV1.InvoiceDetailInfo> detailInfoList = new ArrayList<JftApiInvoiceRefinedOilPreOrderRequestV1.InvoiceDetailInfo>();
		JftApiInvoiceRefinedOilPreOrderRequestV1.InvoiceDetailInfo detail1 = new JftApiInvoiceRefinedOilPreOrderRequestV1.InvoiceDetailInfo();
		detail1.setGoodsSeqno("1");
		detail1.setProjectName("xx商品");
		detail1.setGoodsNum("10");
		detail1.setGoodsAmt("50");
		detail1.setUnits("吨");
		detail1.setTaxRate("0.03");
		detail1.setMergedCode("1001");
		detail1.setInvoiceBankNature("00");
		detail1.setPrice("5");
		detail1.setWeight("订单商品规格");
		detailInfoList.add(detail1);

		JftApiInvoiceRefinedOilPreOrderRequestV1.InvoiceDetailInfo detail2 = new JftApiInvoiceRefinedOilPreOrderRequestV1.InvoiceDetailInfo();
		detail2.setGoodsSeqno("2");
		detail2.setProjectName("xx商品2");
		detail2.setGoodsNum("10");
		detail2.setGoodsAmt("50");
		detail2.setUnits("吨");
		detail2.setTaxRate("0.03");
		detail2.setMergedCode("1001");
		detail1.setInvoiceBankNature("00");
		detail1.setPrice("5");
		detail1.setWeight("订单商品规格");
		detailInfoList.add(detail2);

		bizContent.setInvoiceDetailInfo(detailInfoList);
		request.setBizContent(bizContent);

		JftApiInvoiceRefinedOilPreOrderResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
			
			} else {
				//失败
				
			}
		} catch (IcbcApiException e) {
			log.error(e.getMessage());
		}
	}
}
