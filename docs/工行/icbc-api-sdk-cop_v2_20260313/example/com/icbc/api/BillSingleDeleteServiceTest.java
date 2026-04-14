package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BillSingleDeleteRequestV1;
import com.icbc.api.response.BillSingleDeleteResponseV1;

public class BillSingleDeleteServiceTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String APPID = "yourAppId";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		BillSingleDeleteRequestV1 request = new BillSingleDeleteRequestV1();		
		request.setServiceUrl("https://ip:port/api/bill/single/V1/delete");
		BillSingleDeleteRequestV1.BillSingleDeleteRequestV1Biz bizContent = new BillSingleDeleteRequestV1.BillSingleDeleteRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setCorpCis("020090003171737");
		bizContent.setBatchNo("0123233");
		bizContent.setBillNo("1");

		BillSingleDeleteResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
//				Assert.assertEquals(response.getPurchasePrice(), 100);
				System.out.println(response.getReturnCode());
				System.out.println(response.getReturnMsg());
				System.out.println(response.getMsgId());
			} else {
				// 失败
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

}
