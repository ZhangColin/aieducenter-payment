package com.icbc.api;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.apip.config.Configure;
import com.icbc.apip.context.WebApiContext;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker.HttpMethodType;
import com.icbc.apip.token.SignatureAlgo;

public class CorpidauthInfoQueryTest {

	private static final Logger LOG = Logger.getLogger(CorpidauthInfoQueryTest.class.getName());
	
	public static void main(String[] args) {
		try {
			//调用参数准备 
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("LOGONID", "plliang.c.0200");
			Map<String,Object> chanCommV10 = new HashMap<>();
			chanCommV10.put("serialno", "010202002152001014917875277");
			chanCommV10.put("oapp", "F-MALLB");
			paramMap.put("chanCommV10", chanCommV10);
			String json = JSON.toJSONString(new Object[]{paramMap});
			//通过代码配置
			Configure configure = new Configure(); 
			configure.setAppId("10000000000004097593"); 
			configure.setUserPrivateLoc("D:/api/API_Config/mykey.pri"); 
			configure.setSysPubkeyLoc("D:/api/API_Config/API_GATEWAY.pub");
			configure.setSysBaseUri("https://ip:port/api");
			configure.setAlgo(SignatureAlgo.RSA2048); 
			configure.setSysAuthMaxTimeStep(1000000); 
			WebApiContext context = new WebApiContext(); 
			context.setConfigure(configure); 
			context.init();
			//获取调用对象
			DefaultInvoker invoker = context.getDefaultInvoker();
			invoker.setUri("/corpidauth/info/query/V1");
			invoker.setSendEncoding("utf-8");
			//添加参数
			invoker.addParameter("biz_content", json);
			invoker.setMethod(HttpMethodType.POST);
			//同步调用，获取map对象
			Map<String, Object> resp = invoker.syncInvoke();
			//释放资源 
			context.shutdown();
			if("0".equals(String.valueOf(resp.get("return_code")))){
				// 业务成功处理
				LOG.info("成功");
			}else{
				// 失败
				LOG.info("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
