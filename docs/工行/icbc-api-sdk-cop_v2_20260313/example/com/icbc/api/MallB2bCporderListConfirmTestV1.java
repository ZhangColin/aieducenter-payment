package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bCporderListConfirmRequestV1;
import com.icbc.api.request.MallB2bCporderListConfirmRequestV1.MallB2bCprderListConfirmRequestV1Biz;
import com.icbc.api.request.MallB2bCporderListConfirmRequestV1.OrderProdCPInput;
import com.icbc.api.response.MallB2bCporderListConfirmResponseV1;

public class MallB2bCporderListConfirmTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCa4ZHW0f6AocKGJuKnN0WrZe4x9FWRNKBadbPXu++uHwX6pVuhW0XN10k8Va6c4VOxLbNq5K1HqHU0lytvE8WITnTY1oHmcl3XW0+sFwmJeh6KgDth2Z/qeEcc2xKz4FKx5ByzU6Nr+TqBViBKM/Q3ZVbNG0+iRpxpO56lKmzIp/WAdd3WW5M9IzckqdNE+Ze7YzUHqxaLOZDlvRpFTcIyLGzPwlYy/zVWIpqteAjDpClRX29sNq9/EKzJXC3+wxBHYp2HmC5pO2YwBf0D39zbfIccGjZdSxg7NzqZBKQyuIN6ZeoyK3/7d2w3ucK4SQ16EH0ndduvPyy07+rMRquDAgMBAAECggEAJ2vsu/n9Rm5l3dF99UPbRtIN+8cOFISJoBy/ZtTeH7qjwfVeykY6ETeP23yPkjzEGZ/hP/7uoAOfuYAUvRQSPiyCD0MiEy7e9da3A71lXwmnRmd3NeO3kW2F5DFapFAfnEnMiFRsjyoDmzH5zAlHSuZPXqtp7Ir+esaDDdUrlTLKyFsrHTlSMAXHrtWyTxFNw4CjWcblXNlwwOIw/MObrK89Kan2eemA+HZ0dDkDp8yT8wvSh8BDaRE4EX2+xqhtsXk59sEc0n5BYcJXn+cy8BG1CCbl1uiWrKwZ4yP2YPKVVyRV8eKaVoBQrm3iA4Fyn6RmmHccDX2Ob2zOHbPwaQKBgQD8qENmm9u9ukkgn+1mynqu3zO3uzosz0Cm1ezONxVmJi3Dby0q2XHHRLM1DownQLq6KORIxbMO12kIDdTtF2Bv2bNrBC9DvLCEEDpu/kTZFe4qfsjZY0KvkRd8geGeWSvaIjWAWpItFdfntHphI4v5g5Kj/Cbt5yh3fidfnEuYDwKBgQCc7iTQscCu7Iu7UYCpPXerI2N0uozzEawAkhAUk9nY43sE5El6DNWH8X3ugPzq7+qmhRJihl2kSXorOJOyGH3sWcAGqvUV5YoSY8FIKNRCwDiKeRHbtqtOYwaUnp7k7qrQRKhY7TbTGkCr7Vyv0COTyPUPAf/O4Rg6dIkD1hAhTQKBgDWdMyhL/JZHOItexI7ieqQvIxdi+JZXqH1+udCMqdLSWTW2mtwT5mwFrQ2eN6mAt98XHFJA+Z01oskdvkhNhJ5XkRYtfiYenUp48x1ogY2bdL25x78xrA37eQNnwEvM2lxgk//XuFsu3nEBwFJqq6JYBD8eG9iyC8lHkO1eCkCLAoGAJQsZ6aFr6F6yBT+R7WEzFFTAL1ex2da8EU5pOVJFRCs+aMMyjPrmlLZukZQlH5gOfcw/sL4i8JrfmcA7LPkXEGmHKIvGWqraUsj1YQEySadyMU2u53xRJ6m8beybeVLVQ2BRlNw56s7xoSPgkyfAniv7nNVN+xmrTHEhvqQTHqkCgYEAvgDLJ2RbQCyY5oBWvjNorYeCzEzBaODjZx32oWjwebBXU60V3TDZ2G1PfGzuxcPeHwD4lPjvm9kqDSZazS1JJUICnVtONftq9Lo2ZKgEqjr308EE4Ibyk+X1HcOMyXrR37hbLaGnvPy69s1+fZEDdGeGNvdP7vpwLUyleKrsEEc=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000003939";//4月功能

	public static void main(String[] args) throws Exception{

	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8", 
				"json",APIGW_PUBLIC_KEY,"AES","hdZdNozJr5oATedE9UmuSA==", "", "");
		MallB2bCporderListConfirmRequestV1 request = new MallB2bCporderListConfirmRequestV1();

		request.setServiceUrl("http://ip:port/api/mall/b2b/cporder/list/confirm/V1");
		MallB2bCprderListConfirmRequestV1Biz biz = new MallB2bCprderListConfirmRequestV1Biz();
		List<OrderProdCPInput> orderProdList = new ArrayList<OrderProdCPInput>();
		OrderProdCPInput orderProd1 = new OrderProdCPInput();
		orderProd1.setOrderProdId("202110300000006107");
		orderProd1.setVendorProdId("10720239");
		orderProdList.add(orderProd1);
		OrderProdCPInput orderProd2 = new OrderProdCPInput();
		orderProd2.setOrderProdId("202110300000006108");;
		orderProd2.setVendorProdId("10031594");
		orderProdList.add(orderProd2);
		OrderProdCPInput orderProd3 = new OrderProdCPInput();
		orderProd3.setOrderProdId("202110300000006109");;
		orderProd3.setVendorProdId("B000012");
		orderProdList.add(orderProd3);
		biz.setOrderId("202110300000026933");
		biz.setVendorId("10001309");
		biz.setVendorConName("test1");
		biz.setVendorConMobile("18712311131");
		biz.setOrderProdList(orderProdList);
		biz.setRetStatus("01");
		biz.setRetMessage("test");
		request.setBizContent(biz);
		MallB2bCporderListConfirmResponseV1 response;
		try {
			
			System.out.println(JSONObject.toJSONString(request));
			response = (MallB2bCporderListConfirmResponseV1) client.execute(request, System.currentTimeMillis()+"");
			System.out.println(JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
				System.out.println(response.getReturnCode());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}
	
}

