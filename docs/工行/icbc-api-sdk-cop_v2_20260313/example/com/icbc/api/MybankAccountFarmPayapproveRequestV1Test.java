package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.MybankAccountFarmPayapproveRequestV1;

public class MybankAccountFarmPayapproveRequestV1Test {
	protected static String APP_ID = "APPID";
	protected static String APIGW_PUBLIC_KEY = "APIGW_PUBLIC_KEY";
	protected static String PRI_KEY = "PRI_KEY";

	
	public static void main(String[] args) throws IcbcApiException {


		UiIcbcClient client = new UiIcbcClient(APP_ID, PRI_KEY, "UTF-8");

		MybankAccountFarmPayapproveRequestV1.MybankAccountFarmPayapproveRequestV1Biz biz = new MybankAccountFarmPayapproveRequestV1.MybankAccountFarmPayapproveRequestV1Biz();
		biz.setCorporId("corporid111");
		biz.setCorporName("corporName111");
		biz.setToken("token111");
		biz.setExpirationTime("20200304101010");
		biz.setUserId("userid111");
		biz.setUserName("username111");
		biz.setInstId("instid111");
		biz.setInstName("instname111");
		biz.setMenuId("menuid111");
		biz.setMenuName("menuname111");
		biz.setBatchNum("batchnum111");

		MybankAccountFarmPayapproveRequestV1 request = new MybankAccountFarmPayapproveRequestV1();
		request.setBizContent(biz);
		request.setServiceUrl("https://gw.icloud.icbc.com.cn/ui/mybank/account/farm/payapprove/V1");

		client.buildPostForm(request);


	}
	
}
