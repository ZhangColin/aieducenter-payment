package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BillSingleQueryRequestV1;
import com.icbc.api.response.BillSingleQueryResponseV1;

public class BillSingleQueryServiceTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String APPID = "yourAppId";
	
	public static void main(String[] args) {
		
		
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

  	BillSingleQueryRequestV1 request = new BillSingleQueryRequestV1();		
		request.setServiceUrl("https://ip:port/api/bill/single/query/V1");
		BillSingleQueryRequestV1.BillSingleQueryRequestV1Biz bizContent = new BillSingleQueryRequestV1.BillSingleQueryRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setCorpCis("020090003171737");
		bizContent.setProjectId("PJ1111111");
		bizContent.setBatchNo("0123233");
		bizContent.setBillNo("1");

		BillSingleQueryResponseV1 response;
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
