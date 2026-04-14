package com.icbc.api.request;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiInvoiceDownloadRequestV1.JftApiInvoiceDownloadRequestV1Biz;
import com.icbc.api.response.JftApiInvoiceDownloadResponseV1;
public class JftApiInvoiceDownloadTestV1 {

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
											
	public static void main(String[] args) {

		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");
		JftApiInvoiceDownloadRequestV1 request = new JftApiInvoiceDownloadRequestV1();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/invoice/download/V1");
		JftApiInvoiceDownloadRequestV1Biz bizContent = new JftApiInvoiceDownloadRequestV1Biz();
		bizContent.setAppId(APP_ID);//平台商户标识
		bizContent.setOutOrderId("20200202002");//平台对接方订单ID
		bizContent.setOutRedOffsetId("2024090401");//红冲流水号
		bizContent.setIsRedOrblue("02");//02:红票
		bizContent.setOutInvoiceId("20250120001");//开票请求流水号
		request.setBizContent(bizContent);

		JftApiInvoiceDownloadResponseV1 response;
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

