package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.JftApiInvoiceUniversalPreOrderRequestV1;
import com.icbc.api.request.JftApiInvoiceUniversalPreOrderRequestV1.JftApiInvoiceUniversalPreOrderBiz;
import com.icbc.api.response.JftApiInvoiceUniversalPreOrderResponseV1;

public class JftApiInvoiceUniversalPreOrderTestV1 {

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_KEY = "[需替换]应用方加密串，与工行API开放平台一致";


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_KEY, "", "");

		JftApiInvoiceUniversalPreOrderRequestV1 request = new JftApiInvoiceUniversalPreOrderRequestV1();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/invoice/normal/pre/order/V1");
		JftApiInvoiceUniversalPreOrderBiz bizContent = new JftApiInvoiceUniversalPreOrderBiz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setOutOrderId("20190102_3");//平台对接方ID：平台商户订单ID，确保唯一
		bizContent.setMac("a3-b4-c5");//手机Mac:支付提交用户手机Mac地址
		bizContent.setTrxIp("xx.xx.xx.xx");//交易IP：支付提交用户IP地址
		bizContent.setPayChannel("99");//交易渠道:固定取99
		bizContent.setInvoiceNotifyUrl("xx");//商户通知URL：商户接收通知消息URL
		bizContent.setInvoiceType("01");
		bizContent.setOrderAmount("0.02");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
		bizContent.setTaxpayerName("");
		bizContent.setSellerAddress("xx市xx区");
		bizContent.setPayMode("01");//支付模式

		//发票明细信息
		List<JftApiInvoiceUniversalPreOrderRequestV1.InvoiceDetailInfo> detailInfoList = new ArrayList<JftApiInvoiceUniversalPreOrderRequestV1.InvoiceDetailInfo>();
		JftApiInvoiceUniversalPreOrderRequestV1.InvoiceDetailInfo detail1 = new JftApiInvoiceUniversalPreOrderRequestV1.InvoiceDetailInfo();
		detail1.setGoodsSeqno("1");
		detail1.setProjectName("xx商品");
		detail1.setGoodsNum("10");
		detail1.setGoodsAmt("50");
		detail1.setUnits("个");
		detail1.setTaxRate("0.03");
		detail1.setMergedCode("1001");
		detailInfoList.add(detail1);

		JftApiInvoiceUniversalPreOrderRequestV1.InvoiceDetailInfo detail2 = new JftApiInvoiceUniversalPreOrderRequestV1.InvoiceDetailInfo();
		detail2.setGoodsSeqno("2");
		detail2.setProjectName("xx商品2");
		detail2.setGoodsNum("10");
		detail2.setGoodsAmt("50");
		detail2.setUnits("个");
		detail2.setTaxRate("0.03");
		detail2.setMergedCode("1001");
		detailInfoList.add(detail2);

		bizContent.setInvoiceDetailInfo(detailInfoList);

		request.setBizContent(bizContent);

		JftApiInvoiceUniversalPreOrderResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				//ReturnCode: response.getReturnCode()
			} else {
				//失败
				//ReturnCode: response.getReturnCode()
				//ReturnMsg: response.getReturnMsg()
			}
		} catch (IcbcApiException e) {
			//异常处理
		}
	}
}
