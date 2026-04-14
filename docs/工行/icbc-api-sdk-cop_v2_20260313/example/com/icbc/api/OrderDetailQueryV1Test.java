package com.icbc.api;

import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.OrderDetailQueryRequestV1;
import com.icbc.api.request.OrderDetailQueryRequestV1.OrderDetailQueryRequestV1Biz;
import com.icbc.api.response.OrderDetailQueryResponseV1;

public class OrderDetailQueryV1Test {
	
	//appid
	protected static final String APP_ID = "xxxxxx";
	
	//合作方私钥
	protected static final String MY_PRIVATE_KEY = "xxxxxx";
	
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxxxxx";
	

	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		OrderDetailQueryRequestV1 request = new OrderDetailQueryRequestV1();
		
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/order/detail/query/V1");

		OrderDetailQueryRequestV1Biz bizContent = new OrderDetailQueryRequestV1Biz();
		bizContent.put("vendorId","AESU2023062900000018");
		bizContent.put("orderId","202408290740298398");
		
		request.setBizContent(bizContent);

		OrderDetailQueryResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				if("0".equals(response.getCode())){
					String retMsg = response.getMsg();
				}else{
					String retMsg = response.getMsg();
				}
				
			} else {
				//失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
}
