package com.icbc.api;

import java.util.HashMap;
import java.util.Map;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.apip.config.Configure;
import com.icbc.apip.context.WebApiContext;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker.HttpMethodType;
import com.icbc.apip.token.SignatureAlgo;

public class CorptelBranchQueryCustinfoTest {

	public static void main(String[] args) {
		try {
			// 调用参数准备
			Map<String, Object> paramMap = new HashMap<>();
			Map<String, Object> channelMap = new HashMap<>();
			channelMap.put("chan_type", "10");
			channelMap.put("oapp", "F-CCIS");
			channelMap.put("chan_serialno", "030102001328001190844618627");
			Map<String, Object> infoCommMap = new HashMap<>();
			infoCommMap.put("trx_code", "42136");
			infoCommMap.put("zone_no", "0200");
			infoCommMap.put("br_no", "00620");
			infoCommMap.put("teller_no", "13");
			infoCommMap.put("workdate", "2026-02-20");
			infoCommMap.put("worktime", "12:12:12");
			infoCommMap.put("servface", "11");
			infoCommMap.put("term_id", "10");
			Map<String, Object> inqWorkMap = new HashMap<>();
			inqWorkMap.put("init_flag", "1");
			Map<String, Object> inqRowMap = new HashMap<>();
			inqRowMap.put("reg_card_no", "9558830200001945864");
			inqRowMap.put("srvcode", "1");
			inqRowMap.put("cis_no", "1");
			Map<String, Object> icomMsgMap = new HashMap<>();
			icomMsgMap.put("search_type", "1");
			icomMsgMap.put("reg_card_no", "9558830200001945864");
			icomMsgMap.put("cust_no", "11");
			icomMsgMap.put("unit_chn_name", "111");
			paramMap.put("channel", channelMap);
			paramMap.put("info_comm", infoCommMap);
			paramMap.put("inq_work", inqWorkMap);
			paramMap.put("inq_row", inqRowMap);
			paramMap.put("icom_msg", icomMsgMap);
			String json = JSON.toJSONString(new Object[] { paramMap });
			// 通过代码配置
			Configure configure = new Configure();
			configure.setAppId("10000000000004097717");
			configure.setUserPrivateLoc("D:/key/mykey.pri");
			configure.setSysPubkeyLoc("D:/key/API_GATEWAY.pub");
			configure.setSysBaseUri("http://ip:port/api");
			configure.setAlgo(SignatureAlgo.RSA2048);
			configure.setSysAuthMaxTimeStep(1000000);
			WebApiContext context = new WebApiContext();
			context.setConfigure(configure);
			context.init();
			// 获取调用对象
			DefaultInvoker invoker = context.getDefaultInvoker();
			invoker.setUri("/corptel/branch/query/custinfo/V1");
			invoker.setSendEncoding("utf-8");
			// 添加参数
			invoker.addParameter("biz_content", json);
			invoker.setMethod(HttpMethodType.POST);
			// 同步调用，获取map对象
			Map<String, Object> resp = invoker.syncInvoke();
			// 释放资源
			context.shutdown();
			System.out.println("return_code：" + String.valueOf(resp.get("return_code")));
			System.out.println("return_msg：" + String.valueOf(resp.get("return_msg")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
