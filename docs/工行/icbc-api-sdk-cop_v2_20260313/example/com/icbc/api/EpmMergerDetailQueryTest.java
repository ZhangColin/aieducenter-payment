package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.EpmMergerQueryRequestV1;
import com.icbc.api.response.EpmMergerQueryResponseV1;

public class EpmMergerDetailQueryTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String APPID = "yourAppId";
	
	public static void main(String[] args) {
		
		
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		EpmMergerQueryRequestV1 request = new EpmMergerQueryRequestV1();		
		request.setServiceUrl("http://ip:port/api/epm/merger/detail/query/V1");
		EpmMergerQueryRequestV1.EpmMergerQueryRequestV1Biz bizContent = new EpmMergerQueryRequestV1.EpmMergerQueryRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setTrxDate("2017-07-01");
		bizContent.setAcctno("12234234523");
		bizContent.setCurPage("0");
		bizContent.setFetchNum("100");

		EpmMergerQueryResponseV1 response;
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
