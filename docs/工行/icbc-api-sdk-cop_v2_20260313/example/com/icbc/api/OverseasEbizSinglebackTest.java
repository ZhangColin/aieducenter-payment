package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.OverseasEbizSinglebackRequestV1;
import com.icbc.api.request.OverseasEbizSinglebackRequestV1.OverseasEbizSinglebackRequestV1Biz;
import com.icbc.api.response.OverseasEbizSinglebackResponseV1;

public class OverseasEbizSinglebackTest {

	//证书私钥  
	protected static final String MY_PRIVATE_KEY ="";  
	//网关公钥  
	protected static final String APIGW_PUBLIC_KEY = "";  

	protected static final String APP_ID = "Oikeclo001";  

	//证书公钥  
	protected static final  String MY_PUB_KEY = "";  

//
	public static void test_cop() {
		
		// 构造client对象  

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY,MY_PUB_KEY);

		OverseasEbizSinglebackRequestV1 request = new OverseasEbizSinglebackRequestV1();		
		request.setServiceUrl("http://ip:port/api/overseas/ebiz/singleback/V1");
		OverseasEbizSinglebackRequestV1Biz bizContent = new OverseasEbizSinglebackRequestV1Biz();
		bizContent.setOrderNo("334957328573");
		bizContent.setRefundAmt("200");
		
		request.setBizContent(bizContent);

		OverseasEbizSinglebackResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess() ) {
				 // 业务成功处理 
				  System.out.println("success");
			} else {
				 // 失败 
				  System.out.println("error");
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
