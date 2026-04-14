package com.icbc.api;

import com.icbc.api.request.MybankAccountFarmCardreimburseapproveRequestV1;

public class MybankAccountFarmCardreimburseapproveRequestV1Test {
	protected static String APP_ID = "APPID";
	protected static String APIGW_PUBLIC_KEY = "APIGW_PUBLIC_KEY";
	protected static String PRI_KEY = "PRI_KEY";

	
	
	public static void main(String[] args) throws IcbcApiException {
		UiIcbcClient client = new UiIcbcClient(APP_ID, PRI_KEY, "UTF-8");

		MybankAccountFarmCardreimburseapproveRequestV1.MybankAccountFarmCardreimburseapproveRequestV1Biz biz = new MybankAccountFarmCardreimburseapproveRequestV1.MybankAccountFarmCardreimburseapproveRequestV1Biz();
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
		biz.setAccountNum("accountnum111");
		biz.setAccountName("accountname111");
		biz.setBatchNum("batchnum111");

		MybankAccountFarmCardreimburseapproveRequestV1 request = new MybankAccountFarmCardreimburseapproveRequestV1();
		request.setBizContent(biz);
		request.setServiceUrl("https://gw.icloud.icbc.com.cn/ui/mybank/account/farm/cardreimburseapprove/V1");

		client.buildPostForm(request);

	}

}
