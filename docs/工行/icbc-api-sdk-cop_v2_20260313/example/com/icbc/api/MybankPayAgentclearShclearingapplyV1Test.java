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

public class MybankPayAgentclearShclearingapplyV1Test {

	public static void main(String[] args) {
		
		//指定config文件夹的位置，建议此步骤放置于容器初始化过程中完成，设置一次即可（例如：ServletContextListener）
		System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");
		
		//创建调用实例
		DefaultInvoker invoker = Invokers.getDefaultInvoker();
		//设置请求路径
		invoker.setUri("/mybank/pay/agentclear/shclearingapply/V1");
		invoker.setMethod(HttpMethodType.POST);
		
		try {
			String req="{\n" +
	                "\t\"public\":{\n" +
	                "\t\t\"serialNo\":\"111\",\n" +
	                "\t\t\"appNo\":\"F-FMBM\",\n" +
	                "\t\t\"areaCode\":\"0010100637\",\n" +
	                "\t\t\"teller\":\"auto\",\n" +
	                "\t\t\"language\":\"zh_CN\",\n" +
	                "\t\t\"verify\":\"0\",\n" +
	                "\t\t\"transNo\":\"gmo0072\",\n" +
	                "\t\t\"ver\":\"1\",\n" +
	                "\t\t\"turnPage\":\"0\",\n" +
	                "\t\t\"beginRow\":\"0\",\n" +
	                "\t\t\"endRow\":\"0\"\n" +
	                "\t},\n" +
	                "\t\"private\":{\n" +
	                "\t\t\"dealInfo\":{\n" +
	                "\t\t\t\"dealId\":\"SHCH004\",\n" +
	                "\t\t\t\"versionNo\":\"1\",\n" +
	                "\t\t\t\"sourceId\":\"01\",\n" +
	                "\t\t\t\"branchId\":\"0010100637\",\n" +
	                "\t\t\t\"traderId\":\"auto\",\n" +
	                "\t\t\t\"folderId\":\"007002\",\n" +
	                "\t\t\t\"dealAction\":\"3\",\n" +
	                "\t\t\t\"shBusiType\":\"01\",\n" +
	                "\t\t\t\"shTradeType\":\"01\",\n" +
	                "\t\t\t\"shSettleType\":\"01\",\n" +
	                "\t\t\t\"cusShortName\":\"ICBC-SHII\",\n" +
	                "\t\t\t\"tradeDate\":\"20201010\",\n" +
	                "\t\t\t\"settleDate\":\"20201010\",\n" +
	                "\t\t\t\"ccy\":\"CNY\",\n" +
	                "\t\t\t\"amt\":\"1111.123\",\n" +
	                "\t\t\t\"dataClass\":\"03\",\n" +
	                "\t\t\t\"validFlag\":\"1\",\n" +
	                "\t\t\t\"capUserId\":\"1111111\",\n" +
	                "\t\t\t\"capBranchId\":\"010100000\",\n" +
	                "\t\t\t\"capLocId\":\"\",\n" +
	                "\t\t\t\"capWorkDate\":\"20200808\",\n" +
	                "\t\t\t\"capSysTime\":\"\",\n" +
	                "\t\t\t\"updUserId\":\"1111111\",\n" +
	                "\t\t\t\"updBranchId\":\"010100000\",\n" +
	                "\t\t\t\"updLocId\":\"\",\n" +
	                "\t\t\t\"updWorkDate\":\"20200808\",\n" +
	                "\t\t\t\"updSysTime\":\"\",\n" +
	                "\t\t\t\"vrfUserId\":\"\",\n" +
	                "\t\t\t\"vrfBranchId\":\"\",\n" +
	                "\t\t\t\"vrfLocId\":\"\",\n" +
	                "\t\t\t\"vrfWorkDate\":\"20200808\",\n" +
	                "\t\t\t\"vrfSysTime\":\"\",\n" +
	                "\t\t\t}\n" +
	                "\t}\n" +
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
