package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiInvoiceRegisterConfirmQueryRequestV1;
import com.icbc.api.response.JftApiInvoiceRegisterConfirmQueryResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftApiInvoiceRegisterConfirmQueryTestV1 {
	private final Logger log = LoggerFactory.getLogger(getClass());

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
											
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");
		JftApiInvoiceRegisterConfirmQueryRequestV1 request = new JftApiInvoiceRegisterConfirmQueryRequestV1();
		request.setServiceUrl("http://[需替换]工行API网关地址:端口/api/jft/api/invoice/queryRegisterConfirm/V1");
		JftApiInvoiceRegisterConfirmQueryRequestV1.JftApiPayInvoiceTicketInfoQueryBiz bizContent = new JftApiInvoiceRegisterConfirmQueryRequestV1.JftApiPayInvoiceTicketInfoQueryBiz();
		bizContent.setTaxpayerNo("91510129MtxdsewT");//使用单位纳税人识别号/统一社会信用代码
		bizContent.setProTaxAuthCode("4564646");//省市级税务机关代码
		//...省略
		request.setBizContent(bizContent);
		JftApiInvoiceRegisterConfirmQueryResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				// 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据

			}
		} catch (IcbcApiException e) {
			log.error(e.getMessage());
		}
	}
}

