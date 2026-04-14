package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiInvoiceDownloadRequestV1.JftApiQueryPersonInvoiceInfoRequestV1Biz;
import com.icbc.api.response.JftApiQueryPersonInvoiceInfoResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftApiInvoiceDownloadTestV1 {

	private final Logger log = LoggerFactory.getLogger(getClass());

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	protected static final String APP_ID = "[需替换]APP的编号";

	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
											
	public static void main(String[] args) {

		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");
		JftApiInvoiceDownloadRequestV1 request = new JftApiInvoiceDownloadRequestV1();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/invoice/queryPersonInvoiceInfo/V1");
		JftApiQueryPersonInvoiceInfoRequestV1Biz bizContent = new JftApiQueryPersonInvoiceInfoRequestV1Biz();
		bizContent.setAppId("11000000000000010951");//平台外部系统编号
		bizContent.setTaxpayerNo("91990000000028628B");//统一社会信用代码/纳税人识别号
        bizContent.setOutUserId("20220418946727137");//外部用户编号
		bizContent.setMerBusiModel("01");//平台经营模式
        bizContent.setOutVendorId("45613897654");//付方编号
		
		request.setBizContent(bizContent);

		JftApiQueryPersonInvoiceInfoResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				
			}	
		} catch (IcbcApiException e) {
			log.error(e.getMessage());
		}
	}
}

