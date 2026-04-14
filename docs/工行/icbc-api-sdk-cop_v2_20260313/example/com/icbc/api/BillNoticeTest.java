package com.icbc.api;

import java.math.BigDecimal;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BillNoticeRequestV1;
import com.icbc.api.request.BillNoticeRequestV1Biz;
import com.icbc.api.response.BillNoticeResponse;

public class BillNoticeTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String APPID = "yourAppId";
	
	public static void main(String[] args) {
		
		
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		BillNoticeRequestV1 request = new BillNoticeRequestV1();		
		request.setServiceUrl("http://ip:port/api/bill/notice/V1/add");
		BillNoticeRequestV1Biz bizContent = new BillNoticeRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setNoticeType("01");
		bizContent.setNoticeChan("1");
		bizContent.setCustomerNo("123456789012345678901234567890");
		bizContent.setMobile("15826471089");
		bizContent.setProjectId("PJ140014021000000769");
		bizContent.setBillDate("2017-07-01");
		bizContent.setBillAmount(new BigDecimal("4566"));
		bizContent.setBusiCode("009");
		bizContent.setItem1("item1");
		bizContent.setQueryItem1("queryItem1");

		BillNoticeResponse response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
			
			} else {
				// 失败
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

}
