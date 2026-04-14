package com.icbc.api;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.RealgoldDeliveryPushRouteRequestV1;
import com.icbc.api.request.RealgoldDeliveryPushRouteRequestV1.RealgoldDeliveryPushRouteRequestV1Biz;
import com.icbc.api.request.RealgoldDeliveryPushRouteRequestV1.RealgoldDeliveryPushRouteRequestV1RouteInfo;
import com.icbc.api.response.RealgoldDeliveryPushRouteResponseV1;

public class RealgoldDeliveryPushRouteRequestV1Test {


	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAiTd/WXeD1lWMa+3rMnfeyG/b0rvhO8ZxmmPAjLl+X5EBqUKwObEkMUqH3w6mqdeIoooBTP7r8szVgI+Ghnus7VkJia3m7dzzQAm+KqnRxbYbJW0OKKfOCad3dpg+kgDg1a4xwE7AWruTkT/jVsXm7DEiky86v0V5ifhXxqQ9utM+/c0phdFZmcqeriZvjJgFgJJu9XxSUJlLGbRaN4PzzW1cciKpfCjk6V1Pw2d+FRDxyG0FHDU79LfySniMKtdhupkVzcHgDg+/NDZr6EJ1EIzhf3925HbHsuWDX+og7FbTF5+PwhTEYxQBdyJwgZn3DlLdr3JElpTW/rf5j/V+qQIDAQAB";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "111";//10000000000000003913


	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		RealgoldDeliveryPushRouteRequestV1 request = new RealgoldDeliveryPushRouteRequestV1();
		request.setServiceUrl("http://ip:port/api/realgold/delivery/pushRoute/V1");
		RealgoldDeliveryPushRouteRequestV1Biz bizContent = new RealgoldDeliveryPushRouteRequestV1Biz();
		bizContent.setTid("21873591aaywyyw");
		List<RealgoldDeliveryPushRouteRequestV1RouteInfo> routeList = new ArrayList<RealgoldDeliveryPushRouteRequestV1RouteInfo>();
		RealgoldDeliveryPushRouteRequestV1RouteInfo routeInfo = new RealgoldDeliveryPushRouteRequestV1RouteInfo();
		
		routeInfo.setRouteSeq("1");
		routeInfo.setRouteKeyId("2");
		routeInfo.setRouteMsg("揽收");
		routeInfo.setRouteTime("2020-12-28 17:15:12");
		routeInfo.setRouteAddr("广州天河");
		routeInfo.setRouteName("张三");		
		routeList.add(routeInfo);
		bizContent.setRouteInfoList(routeList);
		request.setBizContent(bizContent);
		RealgoldDeliveryPushRouteResponseV1 response;
		try {
			response = client.execute(request);
//			System.out.println(JSONObject.toJSONString(response));
			// 业务处理成功
			if (response.isSuccess()) {
//				int retCode = response.getReturnCode();
				Assert.assertEquals(response.getReturnCode(), 0);
			} else {
				// //失败
//				System.out.println(response.getReturnCode() + ":"+ response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
