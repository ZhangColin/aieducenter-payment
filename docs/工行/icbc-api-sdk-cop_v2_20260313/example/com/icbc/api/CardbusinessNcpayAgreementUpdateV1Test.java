package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayAgreementUpdateRequestV1;
import com.icbc.api.request.CardbusinessNcpayAgreementUpdateRequestV1.CardbusinessNcpayAgreementUpdateRequestV1Biz;
import com.icbc.api.response.CardbusinessNcpayAgreementUpdateResponseV1;

public class CardbusinessNcpayAgreementUpdateV1Test {

	protected static final String APIGW_PUBLIC_KEY = "****";

	protected static final String APP_ID = "******";

	protected static final String MY_PRIVATE_KEY = "***";
	
	public static void main(String[] args) throws Exception {
	
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessNcpayAgreementUpdateRequestV1 request = new CardbusinessNcpayAgreementUpdateRequestV1();
		
		request.setServiceUrl("http://*****/api/cardbusiness/ncpay/agreement/update/V1");
		CardbusinessNcpayAgreementUpdateRequestV1Biz bizContent = new CardbusinessNcpayAgreementUpdateRequestV1Biz();
		bizContent.setTrxSerno("202***054");
		bizContent.setTrxDate("2020-04-20");
		bizContent.setTrxTime("16:06:42");
		bizContent.setMerSerPrtclno("02000****70201");
		bizContent.setSignNo("20250******687759635");
		bizContent.setCardNo("621226*****119635");
		bizContent.setOperFlag("1");
		bizContent.setMerSerFlag("1");
		bizContent.setMerSerId("020****0621"); 
			
		request.setBizContent(bizContent);
		CardbusinessNcpayAgreementUpdateResponseV1 response;
		try {
			response = client.execute(request, String.valueOf(System.currentTimeMillis()+(int)(Math.random() * 100000000)));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯丄1�7			
			
			if (response.isSuccess()) {
//				System.out.println("ReturnCode:"+response.getReturnCode());
//				System.out.println("response:" + response);
				
			} else {
//				System.out.println("ReturnCode:"+response.getReturnCode());
//				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
