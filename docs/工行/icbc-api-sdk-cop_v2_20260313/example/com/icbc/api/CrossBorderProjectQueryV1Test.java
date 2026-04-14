package com.icbc.api;

import java.util.Map;

import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker.HttpMethodType;
import com.icbc.apip.invoker.Invokers;
import com.icbc.apip.invoker.JsonBuilder;

public class CrossBorderProjectQueryV1Test {

	public static void main(String[] args) {
		System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");
		DefaultInvoker invoker = Invokers.getDefaultInvoker();
		invoker.setUri("/cross/border/project/query/V1/");
		invoker.setMethod(HttpMethodType.POST);

		try {
		    String req_json = JsonBuilder.create()
		        .add("recvCustAcct", "702520018692")
		        .add("paySubClass", "1901")
		        .build();
		    invoker.addParameter("biz_content", req_json);
				invoker.addParameter("msg_id","urcnl24ciutr9");
		    Map<String, Object> resp = invoker.syncInvoke();
		    resp.get("");
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
