package com.icbc.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.exception.ConfigException;
import com.icbc.apip.exception.InvokerException;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker.HttpMethodType;
import com.icbc.apip.invoker.Invokers;
import com.icbc.apip.invoker.JsonBuilder;

public class PaymentMngCardManageServiceV1Test {

	public static void main(String[] args) {

		System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");

		DefaultInvoker invoker = Invokers.getDefaultInvoker();
		invoker.setMethod(HttpMethodType.POST);
		invoker.setUri("/payment/mngcard/manage/V1");
		String req_json;
		try {
			req_json = JsonBuilder.create().add("project_id", "PJ140014021XXXXXXXXXX").add("busi_code", "2018010108")
					.add("busi_name", "111").add("busi_address", "").add("upper_busi_code", "").add("operate_flag", "0")
					.build();
			invoker.addParameter("biz_content", req_json);
			//System.out.println(req_json);

			Map<String, Object> resp = invoker.syncInvoke();
			//System.out.println(resp);
			resp.get("");

		} catch (InvokerException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ConfigException e) {
			e.printStackTrace();
		} finally {
		}

		try {
			Invokers.shutdown();
		} catch (InvokerException e) {
			e.printStackTrace();
		} catch (ConfigException e) {
			e.printStackTrace();
		}

		try {
			Invokers.shutdown();
		} catch (InvokerException e) {
			e.printStackTrace();
		} catch (ConfigException e) {
			e.printStackTrace();
		}
	}
}
