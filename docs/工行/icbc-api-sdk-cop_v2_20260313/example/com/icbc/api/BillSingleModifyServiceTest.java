package com.icbc.api;

import java.math.BigDecimal;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BillSingleModifyRequestV1;
import com.icbc.api.response.BillSingleModifyResponseV1;

public class BillSingleModifyServiceTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String APPID = "yourAppId";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		BillSingleModifyRequestV1 request = new BillSingleModifyRequestV1();		
		request.setServiceUrl("https://ip:port/api/bill/single/V1/modify");
		BillSingleModifyRequestV1.BillSingleModifyRequestV1Biz bizContent = new BillSingleModifyRequestV1.BillSingleModifyRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setCorpCis("020090003171737");
		bizContent.setProjectId("PJ140014021000000769");
		bizContent.setBatchNo("1000342342342");
		bizContent.setBillNo("1");		
		bizContent.setBillCaeseDate("2017-07-01");
		bizContent.setBillAmount(new BigDecimal("4566"));

		BillSingleModifyResponseV1 response;
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
