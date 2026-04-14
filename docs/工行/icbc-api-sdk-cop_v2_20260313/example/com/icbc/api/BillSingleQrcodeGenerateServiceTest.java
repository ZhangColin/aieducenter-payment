package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BillSingleQrcodeGenerateRequestV1;
import com.icbc.api.response.BillSingleQrcodeGenerateResponseV1;

public class BillSingleQrcodeGenerateServiceTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String URL = "https://ip:port/api/coupon/apply/V1";//api地址
	private static final String APPID = "yourAppId";
	
	public static void main(String[] args){
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		BillSingleQrcodeGenerateRequestV1 request = new BillSingleQrcodeGenerateRequestV1();		
		request.setServiceUrl("https://ip:port/api/bill/single/qrcode/generate/V1");
		BillSingleQrcodeGenerateRequestV1.BillSingleQrcodeGenerateRequestV1Biz bizContent = new BillSingleQrcodeGenerateRequestV1.BillSingleQrcodeGenerateRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setCorpCis("020090003171737");
		bizContent.setProjectId("PJ110011041000000596");
		bizContent.setBusiCode("330291");
		bizContent.setQueryItem1("item1");
		bizContent.setQueryItem2("");
		bizContent.setQueryItem3("");
		bizContent.setQueryItem4("");
		bizContent.setQueryItem5("");
		bizContent.setQueryItem6("");
		bizContent.setQueryItem7("");
		bizContent.setQueryItem8("");
		bizContent.setQueryItem9("");
		bizContent.setQueryItem10("");
		bizContent.setQueryItem12("");
		bizContent.setQueryItem13("");
		bizContent.setQueryItem14("");
		bizContent.setQueryItem15("");
		;
		
		BillSingleQrcodeGenerateResponseV1 respones;
		
		try {
			respones = client.execute(request, "msgId");
			if (respones.isSuccess()) {
				// 业务成功处理
				System.out.println(respones.getReturnCode());

			} else {
				// 失败
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
