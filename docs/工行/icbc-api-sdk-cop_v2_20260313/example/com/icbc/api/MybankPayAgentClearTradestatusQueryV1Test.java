package com.icbc.api;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.logging.LogFactory;

import com.icbc.api.internal.apache.http.client.ClientProtocolException;
import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.exception.ConfigException;
import com.icbc.apip.exception.InvokerException;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invokers;
import com.icbc.apip.invoker.JsonBuilder;
import com.icbc.apip.invoker.Invoker.HttpMethodType;

public class MybankPayAgentClearTradestatusQueryV1Test {

	public static void main(String[] args) {
		
		//指定config文件夹的位置，建议此步骤放置于容器初始化过程中完成，设置一次即可（例如：ServletContextListener）
		System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");
		
		//创建调用实例
		DefaultInvoker invoker = Invokers.getDefaultInvoker();
		//设置请求路径
		invoker.setUri("/mybank/pay/agentclear/tradestatusquery/V1");
		invoker.setMethod(HttpMethodType.POST);
		
		try {
			String req="{\n" +
                	"\t\"boTradeId\":\"171222B45068\"\n" +
	                "}";
			invoker.addParameter("biz_content", req);

			//同步调用，并获取请求结果
			invoker.syncInvoke();
			

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvokerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ConfigException e) {
			e.printStackTrace();
		}
		sleep5Sec();

		try {
			//关闭连接池、线程池，释放资源。在容器销毁前调用一次即可。注意每次调用后释放。
			Invokers.shutdown();
		} catch (InvokerException e) {
			e.printStackTrace();
		} catch (ConfigException e) {
			e.printStackTrace();
		}
	}

	public static void sleep5Sec() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
