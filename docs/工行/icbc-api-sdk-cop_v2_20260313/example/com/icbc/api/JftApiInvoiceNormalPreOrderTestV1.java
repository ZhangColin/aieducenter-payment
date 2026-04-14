package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.JftApiInvoiceNormalPreOrderRequestV1;
import com.icbc.api.request.JftApiInvoiceNormalPreOrderRequestV1.JftApiInvoiceNormalPreOrderBiz;
import com.icbc.api.request.JftApiInvoiceNormalPreOrderRequestV1.InvoiceDetailInfo;
import com.icbc.api.response.JftApiInvoiceNormalPreOrderResponseV1;

public class JftApiInvoiceNormalPreOrderTestV1 {

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");

		JftApiInvoiceNormalPreOrderRequestV1 request = new JftApiInvoiceNormalPreOrderRequestV1();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/invoice/normal/pre/order/V1");
		JftApiInvoiceNormalPreOrderBiz bizContent = new JftApiInvoiceNormalPreOrderBiz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setOutVendorId("1224536");//子商户标识：提交支付请求的子商户编号
		bizContent.setOutUserId("M201801112326");//用户标识
		bizContent.setOutOrderId("20190102_3");//平台对接方ID：平台商户订单ID，确保唯一
		bizContent.setGoodsId("3212");//商品编号（长度单位：字节）
		bizContent.setGoodsName("测试商品");//商品名称（长度单位：字节）
		bizContent.setMac("a3-b4-c5");//手机Mac:支付提交用户手机Mac地址
		bizContent.setTrxIp("xx.xx.xx.xx");//交易IP：支付提交用户IP地址
		bizContent.setTrxIpCity("北京");//交易IP所在地：支付提交用户IP所在地
		bizContent.setTrxChannel("03");//交易渠道:固定取03
		bizContent.setInvoiceNotifyUrl("http://www.xx.com");//商户通知URL：商户接收通知消息URL
		bizContent.setPayNotifyUrl("http://xx.com");//商户通知URL：商户接收支付完成通知消息URL
		bizContent.setInvoiceType("01");
		bizContent.setOrderAmount("0.02");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
		bizContent.setTaxpayerName("");
		bizContent.setSellerAddress("xx市xx区");
		bizContent.setSubMerRateWx("400");//子商户服务费率（微信）,商户档案中支付费率类型为浮动费率，则该字段为必输项
		bizContent.setSubMerRateZfb("400");//子商户服务费率（支付宝）,商户档案中支付费率类型为浮动费率，则该字段为必输项
		bizContent.setTpAppId("wx0f6flal9af93dbe6");//第三方应用ID；商户在微信公众号内或微信小程序内接入时必送，上送微信分配的公众账号ID；商户通过支付宝生活号接入时不送。
		bizContent.setTpOpenId("odg9h43-kX46sAmzGsk3tAvMPbU4");//第三方用户标识；商户在微信公众号内或微信小程序内接入时必送，上送用户在商户appid下的唯一标识；商户通过支付宝生活号接入时不送。
		bizContent.setUnionId("");//第三方用户标识；商户在支付宝生活号接入时必送，上送用户的唯一标识;商户在微信公众号内或微信小程序内选送，上送用户唯一标识。
		bizContent.setPayMode("01");//支付模式：微信小程序: 01，支付宝生活号: 02
		bizContent.setOutBusinessCode("10000000000000000001");//外部业务编码

		//发票明细信息
		List<JftApiInvoiceNormalPreOrderRequestV1.InvoiceDetailInfo> detailInfoList = new ArrayList<JftApiInvoiceNormalPreOrderRequestV1.InvoiceDetailInfo>();
		JftApiInvoiceNormalPreOrderRequestV1.InvoiceDetailInfo detail1 = new JftApiInvoiceNormalPreOrderRequestV1.InvoiceDetailInfo();
		detail1.setGoodsSeqno("1");
		detail1.setProjectName("xx商品");
		detail1.setGoodsNum("10");
		detail1.setGoodsAmt("50");
		detail1.setUnits("个");
		detail1.setTaxRate("0.03");
		detail1.setMergedCode("1001");
		detailInfoList.add(detail1);

		JftApiInvoiceNormalPreOrderRequestV1.InvoiceDetailInfo detail2 = new JftApiInvoiceNormalPreOrderRequestV1.InvoiceDetailInfo();
		detail2.setGoodsSeqno("2");
		detail2.setProjectName("xx商品2");
		detail2.setGoodsNum("10");
		detail2.setGoodsAmt("50");
		detail2.setUnits("个");
		detail2.setTaxRate("0.03");
		detail2.setMergedCode("1001");
		detailInfoList.add(detail2);

		bizContent.setInvoiceDetailInfo(detailInfoList);

		List<JftApiInvoiceNormalPreOrderRequestV1.LicenseNumberInfo> licenseNumberInfoList = new ArrayList<JftApiInvoiceNormalPreOrderRequestV1.LicenseNumberInfo>();
		JftApiInvoiceNormalPreOrderRequestV1.LicenseNumberInfo licenseNumberInfo1 = new JftApiInvoiceNormalPreOrderRequestV1.LicenseNumberInfo();
		licenseNumberInfo1.setLicenseNumber("XXX");
		licenseNumberInfoList.add(licenseNumberInfo1);

		bizContent.setLicenseNumberInfo(licenseNumberInfoList);

		request.setBizContent(bizContent);

		JftApiInvoiceNormalPreOrderResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 10100000) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
