package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.PosOrderAddRequestV2;
import com.icbc.api.request.PosOrderAddRequestV2.*;
import com.icbc.api.response.PosOrderAddResponseV2;


public class PosOrderAddTestV2 {
	
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String AES_KEY = "4+boqULIzNa7SWbYKZtHZA==";

	protected static final String APP_ID = "10000000000000008026";
	
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_KEY, "", "");
		
		PosOrderAddRequestV2 request = new PosOrderAddRequestV2();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/pay/pos/order/add/V2");
		
		PosOrderAddRequestV2Biz bizContent = new PosOrderAddRequestV2Biz();
		bizContent.setAppId("10000000000000008026");
		bizContent.setOutVendorId("020001020461");
		bizContent.setOutUserId("120180126456987458");
		bizContent.setGoodsId("123");
		bizContent.setGoodsName("大商品");
		bizContent.setOutOrderId("20180720100002");
		bizContent.setMac("a3-b4-c5");
		bizContent.setTrxIp("ip:port");
		bizContent.setTrxIpCity("北京");
		bizContent.setTrxChannel("05");
		bizContent.setPayAmount("22.00");
		bizContent.setOutBusinessCode("10000000000000000001");//外部业务编码
		bizContent.setAppName("聚富通App");	//对接商户的应用app的名称
		bizContent.setSubMerRateOwn("200");
		bizContent.setSubMerRateOther("200");
		bizContent.setSubMerRateWx("400");
		bizContent.setSubMerRateZfb("500");
		
		List<SubOrderInfo> subOrders = new ArrayList<SubOrderInfo>();
			SubOrderInfo sub1 = new SubOrderInfo();
			sub1.setOV("1129102"); //子商户标识
			sub1.setGI("good1"); //子商品编号（长度单位：字节）
			sub1.setGN("子订单商品1"); //子商品名称（长度单位：字节）
			sub1.setOI("154529277181301"); //外部订单id
			sub1.setPA("1.23"); //子订单金额
			sub1.setRW("400"); //子商户服务费率（微信/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub1.setRZ("400"); //子商户服务费率（支付宝/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub1.setROW("400"); //子商户服务费率（e支付本行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub1.setROT("400"); //子商户服务费率（e支付它行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			subOrders.add(sub1);
	
		SubOrderInfo sub2 = new SubOrderInfo();
			sub2.setOV("1129102"); //子商户标识
			sub2.setGI("good1"); //子商品编号（长度单位：字节）
			sub2.setGN("子订单商品1"); //子商品名称（长度单位：字节）
			sub2.setOI("154529277181301"); //外部订单id
			sub2.setPA("1.23"); //子订单金额
			sub2.setRW("400"); //子商户服务费率（微信/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub2.setRZ("400"); //子商户服务费率（支付宝/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub2.setROW("400"); //子商户服务费率（e支付本行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub2.setROT("400"); //子商户服务费率（e支付它行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			subOrders.add(sub2);
	
		bizContent.setSubOrders(subOrders);
		request.setBizContent(bizContent);
		
		PosOrderAddResponseV2 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
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
