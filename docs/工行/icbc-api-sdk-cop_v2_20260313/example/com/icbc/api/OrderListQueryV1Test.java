package com.icbc.api;

import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.OrderListQueryRequestV1;
import com.icbc.api.request.OrderListQueryRequestV1.OrderListQueryRequestV1Biz;
import com.icbc.api.response.OrderListQueryResponseV1;

public class OrderListQueryV1Test {
	
	//appid
	protected static final String APP_ID = "xxxxxx";
	
	//合作方私钥
	protected static final String MY_PRIVATE_KEY = "xxxxxx";
	
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxxxxx";
	

	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		OrderListQueryRequestV1 request = new OrderListQueryRequestV1();
		
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/order/list/query/V1");

		OrderListQueryRequestV1Biz bizContent = new OrderListQueryRequestV1Biz();
		bizContent.put("vendorId","AESU2023062900000018");
		bizContent.put("orderId","202408290740298398");
		bizContent.put("orgId","0010100000");
		bizContent.put("contractId","ICBCCONT202500200012250021");
		bizContent.put("orderStatusList",["03","13","27"]);
		bizContent.put("orderTitle","北京分行科技混合标准订单");
		bizContent.put("procurementType","0");
		bizContent.put("orderProdName","打火机");
		bizContent.put("deliveryStatusList",["0","1","2"]);
		bizContent.put("startMoney","1234.56");
		bizContent.put("endMoney","2234.56");
		bizContent.put("startTime","2025-11-01");
		bizContent.put("endTime","2025-11-30");
		bizContent.put("offset",1);
		bizContent.put("fetchNum",10);
		bizContent.put("orderType","5");
		
		request.setBizContent(bizContent);

		OrderListQueryResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				if("0".equals(response.getCode())){
					int totalNum = response.getTotalnum();
					String resultList = response.getResultList();
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
