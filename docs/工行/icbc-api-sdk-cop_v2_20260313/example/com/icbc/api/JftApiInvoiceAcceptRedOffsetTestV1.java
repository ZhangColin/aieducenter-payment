package com.icbc.api;
import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.JftApiInvoiceAcceptRedOffsetRequestV1;

import com.icbc.api.request.JftApiInvoiceAcceptRedOffsetRequestV1.JftApiInvoiceAcceptRedOffsetV1Biz;
import com.icbc.api.request.JftApiInvoiceAcceptRedOffsetRequestV1.RedOffsetGoodsInfo;
import com.icbc.api.response.JftApiInvoiceAcceptRedOffsetResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JftApiInvoiceAcceptRedOffsetTestV1 {
	private final Logger log = LoggerFactory.getLogger(getClass());
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_KEY = "[需替换]应用方加密串，与工行API开放平台一致";


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_KEY, "", "");

		JftApiInvoiceAcceptRedOffsetRequestV1 request = new JftApiInvoiceAcceptRedOffsetRequestV1();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/invoice/acceptRedOffset/V1");
		JftApiInvoiceAcceptRedOffsetV1Biz bizContent = new JftApiInvoiceAcceptRedOffsetV1Biz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setOutVendorId("010020200513111111");//平台子商户编号
        bizContent.setOutOrderId("5525555412");//合作方订单ID
        bizContent.setOutRedOffsetId("552544411");//红冲流水号
		bizContent.setAmount("10");//红冲金额
		bizContent.setRedOffsetReason("01");//红冲原因
		bizContent.setMac("3c:28:6e");
		bizContent.setCpuId("");
		bizContent.setMainBoardId("");
		bizContent.setPartnerMerTrueAppId("5444455");
		bizContent.setIntrxSerialNo("55441441");
		//红冲的商品信息
		List<RedOffsetGoodsInfo> goodsInfoList = new ArrayList<RedOffsetGoodsInfo>();
		RedOffsetGoodsInfo goods1 = new RedOffsetGoodsInfo();
		goods1.setGoodsSeqno("1");//红冲商品信息子序号
		goods1.setBlueGoodsSeqno("1");//蓝字发票商品信息子序号
		goods1.setWeight("");//商品规格
		goods1.setUnits("个");//计量单位
		goods1.setPrice("10.00");//含税单价(元)
		goods1.setGoodsNum("1");//商品数量
		goods1.setGoodsAmt("30");//红冲含税金额(元)
		goods1.setProjectName("xx商品");//项目名称
		goodsInfoList.add(goods1);
		bizContent.setRedOffsetList(goodsInfoList);
		request.setBizContent(bizContent);
		JftApiInvoiceAcceptRedOffsetResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				
			} else {
				//失败
				
			}
		} catch (IcbcApiException e) {
			//异常处理
			log.error(e.getMessage());
		}
	}
}
