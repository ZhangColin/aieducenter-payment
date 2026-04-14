package com.icbc.api;

import com.icbc.api.request.JftApiInvoiceAcceptRefundRedOffsetRequestV1;
import com.icbc.api.response.JftApiInvoiceAcceptRefundRedOffsetResponseV1;

import java.util.ArrayList;
import java.util.List;

public class JftApiInvoiceAcceptRefundRedOffsetTestV1 {

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


	public static void main(String[] args) throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");

		JftApiInvoiceAcceptRefundRedOffsetRequestV1 request = new JftApiInvoiceAcceptRefundRedOffsetRequestV1();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/invoice/acceptRefundRedOffset/V1");
		JftApiInvoiceAcceptRefundRedOffsetRequestV1.JftApiInvoiceAcceptRefundRedOffsetBiz bizContent = new JftApiInvoiceAcceptRefundRedOffsetRequestV1.JftApiInvoiceAcceptRefundRedOffsetBiz();
		bizContent.setAppId(APP_ID);//平台商户标识
		bizContent.setVendorId("1224536");//收方商户编号
		bizContent.setUserId("M201801112326");//外部用户编号
		bizContent.setOrderId("20190102_3");//合作方原支付订单号
		bizContent.setBusinessNo("1001202411260001");//业务编号（退款单号或红冲流水号）
		bizContent.setAmount("10");//退款即红冲金额
		bizContent.setNotifyUrl("http://www.xx.com");//退款通知URL
		bizContent.setExtension("1");//商户拓展信息
		bizContent.setFixedCommissionOwn("0.02");//定额佣金退款金额（元）（e支付本行卡支付）
		bizContent.setFixedCommissionOther("0.02");//定额佣金退款金额（元）（e支付它行卡支付）
		bizContent.setFixedCommissionWx("0.02");//定额佣金退款金额（元）（微信支付）
		bizContent.setFixedCommissionZfb("0.02");//定额佣金退款金额（元）（支付宝支付）
		bizContent.setFixedCommissionDigital("0.02");//定额佣金退款金额（元）（数字人民币支付）
		bizContent.setFixedCommission("0.02");//定额佣金退款金额（元）(云闪付)
		bizContent.setOriginalRefundFlag("00");//一案一账户按指定账户退款标志(合并订单不支持一案一账户按指定账户退款,单订单缺省默认为00
		bizContent.setRedOffsetReason("01");//红冲原因
		bizContent.setMac("a3-b4-c5");//mac地址
		bizContent.setCpuId("1546462");//CPU序列号
		bizContent.setMainBoardId("1654874");//主板序列号
		bizContent.setBusinessFlag("1");//退款红冲标记：1退款即红冲；2仅红冲（只能为1或2）

		List<JftApiInvoiceAcceptRefundRedOffsetRequestV1.RedOffsetGoodsInfo> redOffsetList = new ArrayList<JftApiInvoiceAcceptRefundRedOffsetRequestV1.RedOffsetGoodsInfo>();
		JftApiInvoiceAcceptRefundRedOffsetRequestV1.RedOffsetGoodsInfo redOffsetGoodsInfo = new JftApiInvoiceAcceptRefundRedOffsetRequestV1.RedOffsetGoodsInfo();
		redOffsetGoodsInfo.setGoodsSeqno("1");//商品信息子序号
		redOffsetGoodsInfo.setBlueGoodsSeqno("1");// 蓝字发票商品信息子序号
		redOffsetGoodsInfo.setWeight("");//商品规格
		redOffsetGoodsInfo.setUnits("个");//计量单位
		redOffsetGoodsInfo.setPrice("10.00");//含税单价(元)
		redOffsetGoodsInfo.setGoodsNum("1");//商品总数
		redOffsetGoodsInfo.setGoodsAmt("30");//商品金额(元)
		redOffsetGoodsInfo.setProjectName("废轮胎");//项目名称
		redOffsetList.add(redOffsetGoodsInfo);
		bizContent.setRedOffsetList(redOffsetList);

		request.setBizContent(bizContent);
		JftApiInvoiceAcceptRefundRedOffsetResponseV1 response;
		response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
		if (response.isSuccess()) {
			//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
			System.out.println("ReturnCode:" + response.getReturnCode());
			System.out.println("response:" + response);
		} else {
			//失败
			System.out.println("ReturnCode:" + response.getReturnCode());
			System.out.println("ReturnMsg:" + response.getReturnMsg());
		}

	}
}
