package com.icbc.api;

import com.icbc.api.request.CardbusinessOverseasEbizPosPostraderefundRequestV2;
import com.icbc.api.request.CardbusinessOverseasEbizPosPostraderefundRequestV2.CardbusinessOverseasEbizPosPostraderefundRequestV2Biz;
import com.icbc.api.response.CardbusinessOverseasEbizPosPostraderefundResponseV2;

public class CardbusinessOverseasEbizPosPostraderefundTestV2 {

	//证书私钥  
	protected static final String MY_PRIVATE_KEY ="";  
	//网关公钥  
	protected static final String APIGW_PUBLIC_KEY = "";  

	protected static final String APP_ID = "Oikeclo001";  

	//证书公钥  
	protected static final  String MY_PUB_KEY = "";  

	public static void test_cop() {
		
		// 构造client对象  

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY,MY_PUB_KEY);
		
		client.setFovaRegion("00119"); // header里上送5位地区号

		CardbusinessOverseasEbizPosPostraderefundRequestV2 request = new CardbusinessOverseasEbizPosPostraderefundRequestV2();		
		request.setServiceUrl("http://ip:port/api/cardbusiness/overseas/ebiz/pos/postraderefund/V2");
		CardbusinessOverseasEbizPosPostraderefundRequestV2Biz bizContent = new CardbusinessOverseasEbizPosPostraderefundRequestV2Biz();
		bizContent.setOrderNo("334957328573");
		bizContent.setRefundAmt("200");
		bizContent.setShopCode("011944444");
		
		request.setBizContent(bizContent);

		CardbusinessOverseasEbizPosPostraderefundResponseV2 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess() ) {
				 // 业务成功处理 
				  
			} else {
				 // 失败 
				 
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO 方法的描述：。
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		test_cop();
	}
}
