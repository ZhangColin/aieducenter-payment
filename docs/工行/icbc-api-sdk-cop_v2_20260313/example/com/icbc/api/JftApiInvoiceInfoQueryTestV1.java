package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiInvoiceInfoQueryRequestV1;
import com.icbc.api.response.JftApiInvoiceInfoQueryResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftApiInvoiceInfoQueryTestV1 {
	private final Logger log = LoggerFactory.getLogger(getClass());

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
											
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");
		JftApiInvoiceInfoQueryRequestV1 request = new JftApiInvoiceInfoQueryRequestV1();
		request.setServiceUrl("http://[需替换]工行API网关地址:端口/api/jft/api/invoice/queryInvoiceInfo/V1");
		JftApiInvoiceInfoQueryRequestV1.JftApiPayInvoiceInfoQueryBiz bizContent = new JftApiInvoiceInfoQueryRequestV1.JftApiPayInvoiceInfoQueryBiz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setOutUserId("M20180111222326");//用户标识
		bizContent.setOutOrderId("2099876345");//平台对接方ID：平台商户订单ID，确保唯一
		//...省略
		request.setBizContent(bizContent);
		JftApiInvoiceInfoQueryResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 10100000) {
				// 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据

			}
		} catch (IcbcApiException e) {
			log.error(e.getMessage());
		}
	}
}

