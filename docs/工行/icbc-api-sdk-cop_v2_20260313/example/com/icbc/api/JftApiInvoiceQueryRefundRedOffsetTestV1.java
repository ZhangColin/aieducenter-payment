package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiInvoiceQueryRefundRedOffsetRequestV1;
import com.icbc.api.response.JftApiInvoiceQueryRefundRedOffsetResponseV1;

public class JftApiInvoiceQueryRefundRedOffsetTestV1 {

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
											
	
	public static void main(String[] args) throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");
		JftApiInvoiceQueryRefundRedOffsetRequestV1 request = new JftApiInvoiceQueryRefundRedOffsetRequestV1();
		request.setServiceUrl("http://[需替换]工行API网关地址:端口/api/jft/api/invoice/queryRefundRedOffset/V1");
		JftApiInvoiceQueryRefundRedOffsetRequestV1.JftApiInvoiceQueryRefundRedOffsetBiz bizContent = new JftApiInvoiceQueryRefundRedOffsetRequestV1.JftApiInvoiceQueryRefundRedOffsetBiz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setBusinessNo("M20180111222326");//业务编号
		//...省略
		request.setBizContent(bizContent);
		JftApiInvoiceQueryRefundRedOffsetResponseV1 response;
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

	}
}

