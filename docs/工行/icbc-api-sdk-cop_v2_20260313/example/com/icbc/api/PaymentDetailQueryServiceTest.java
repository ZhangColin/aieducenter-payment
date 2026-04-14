package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.PaymentDetailQueryRequestV1;
import com.icbc.api.response.PaymentDetailQueryResponseV1;

public class PaymentDetailQueryServiceTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String APPID = "yourAppId";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		PaymentDetailQueryRequestV1 request = new PaymentDetailQueryRequestV1();		
		request.setServiceUrl("https://ip:port/api/payment/detail/query/V1");
		PaymentDetailQueryRequestV1.PaymentDetailQueryRequestV1Biz bizContent = new PaymentDetailQueryRequestV1.PaymentDetailQueryRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setCorpCis("020090003171737");
		bizContent.setProjectId("PJ140014021000000769");
		bizContent.setTrxDate("2017-10-01");
		bizContent.setCurPage("0");
		bizContent.setFetchNum("100");

		PaymentDetailQueryResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

}
