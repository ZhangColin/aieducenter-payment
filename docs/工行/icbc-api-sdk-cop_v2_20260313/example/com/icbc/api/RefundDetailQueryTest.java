package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.RefundDetailQueryRequestV1;
import com.icbc.api.response.RefundDetailQueryResponseV1;

public class RefundDetailQueryTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String APPID = "yourAppId";
	
	public static void main(String[] args) {
		
		
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		RefundDetailQueryRequestV1 request = new RefundDetailQueryRequestV1();		
		request.setServiceUrl("https://ip:port/api/refund/detail/query/V1");
		RefundDetailQueryRequestV1.RefundDetailQueryRequestV1Biz bizContent = new RefundDetailQueryRequestV1.RefundDetailQueryRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setCorpCis("020090003171737");
		bizContent.setProjectId("PJ140014021000000769");
		bizContent.setRefundDate("2017-10-01");
		bizContent.setQueryFlag("1");
		bizContent.setCurPage("0");
		bizContent.setFetchNum("100");

		RefundDetailQueryResponseV1 response;
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
