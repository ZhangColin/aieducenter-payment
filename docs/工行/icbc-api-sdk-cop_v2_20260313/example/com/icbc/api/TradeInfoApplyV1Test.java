package com.icbc.api;

import java.util.Map;

import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker.HttpMethodType;
import com.icbc.apip.invoker.Invokers;
import com.icbc.apip.invoker.JsonBuilder;

public class TradeInfoApplyV1Test {

	public static void main(String[] args) {
		System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");
		DefaultInvoker invoker = Invokers.getDefaultInvoker();
		invoker.setUri("/trade/info/apply/V1/");
		invoker.setMethod(HttpMethodType.POST);

		try {
		    String req_json = JsonBuilder.create()
		        .add("projectId", "PJ12001201B0xxxxxxxx")
		        .add("corpCis", "04099000xxxxxxx")
		        .add("startDate", "2016-08-24")
		        .add("endDate", "2019-08-24")
		        .build();
		    invoker.addParameter("biz_content", req_json);
			invoker.addParameter("msg_id","urcnl24ciutr9");
		    //Map<String, Object> resp = invoker.syncInvoke();
		    invoker.syncInvoke();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
