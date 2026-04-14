package com.icbc.api;
import java.util.HashMap;
import java.util.Map;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.apip.config.Configure;
import com.icbc.apip.context.WebApiContext;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker.HttpMethodType;

public class PassUserinfoEncryptOrDecryptSAOpTest {

	//1、mims
	protected static final String APP_ID = "10000000000001234554";

	//	封装查询参数
	public Map<String, Object> packageData() throws Exception{

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("versionid","01");
		map.put("appsenderid","F-MALLC");
		map.put("appreceiverid","F-EPASS");//map
		map.put("structtype","JSON");
		map.put("mesgcharset","UTF-8");
		map.put("msg_id","1");
		map.put("mesgtype", "");
		map.put("mesgstat", "");
		map.put("mesgpriority", "");

		Map<String, String> ChanCommV10 = new HashMap<String,String>();
		ChanCommV10.put("chantype", "");
		ChanCommV10.put("chanlno", "2");
		ChanCommV10.put("serialno", "032409112394393047342477527");

		Map<String, String> InfoCommV10 = new HashMap<String,String>();
		InfoCommV10.put("trxtype", "");
		InfoCommV10.put("trxcode", "1");
		InfoCommV10.put("zoneno", "1");
		InfoCommV10.put("brno", "111");


		Map<String, String> inqCommV10 = new HashMap<String,String>();
		inqCommV10.put("inqtype", "6");
		inqCommV10.put("rowreq", "1");
		inqCommV10.put("initflag", "1");

		Map<String, String> inputV10 = new HashMap<String,String>();
		inputV10.put("CHANNELIDENTIFIER","11111111111111");
		inputV10.put("CHANNELCODE","qw");
		inputV10.put("OPFLAG","0");
		inputV10.put("ENCRYPTEDDATATOAPP","020079000036281");
		inputV10.put("DECRYPTEDDATATOAPP","");
		inputV10.put("SIGNDATATOAPP","");

		map.put("chanCommV10", ChanCommV10);
		map.put("infoCommV10", InfoCommV10);
		map.put("inqCommV10", inqCommV10);
		map.put("inputV10", inputV10);
		return 	map;

	}

	public void GetepassuserinfoEncode(Map<String, Object> packageData) throws Exception{
		//建立返回项
		String json = JSON.toJSONString(new Object[]{packageData});
		String url = "/cert/epass/epassuserinfoencryptordecrypt/V1";
		Map<String, Object> responseBody = doDefaultInvoker(url, json);
		if(responseBody!=null){
			Map<String,Object> appstatv10=(Map<String, Object>)responseBody.get("appstatv10");

			if("1".equals(appstatv10.get("transok"))){
				System.out.println("appstatv10.get('transok')==" +appstatv10.get("transok"));
				System.out.println("appstatv10.get('return_code')==" + appstatv10.get("return_code"));
				System.out.println("appstatv10.get('return_msg')=="+ appstatv10.get("return_msg"));
			}else {
				System.out.println("appstatv10.get('transok')==" +appstatv10.get("transok"));
				System.out.println("appstatv10.get('return_code')==" + appstatv10.get("return_code"));
				System.out.println("appstatv10.get('return_msg')=="+ appstatv10.get("return_msg"));	
			}
			
			Map<String,String> privatev10=(Map<String, String>)responseBody.get("privatev10");
			
			if(privatev10!=null){
				System.out.println("privatev10.get('DECRYPTEDDATATOAPP')==" + privatev10.get("DECRYPTEDDATATOAPP"));
				System.out.println("privatev10.get('ENCRYPTEDDATATOAPP')==" + privatev10.get("ENCRYPTEDDATATOAPP"));
				System.out.println("privatev10.get('SIGNDATATOAPP')==" + privatev10.get("SIGNDATATOAPP"));
			}			
		}else{
			System.out.println("responseBody==" + responseBody);
		}

	}

	public Map<String, Object>  doDefaultInvoker(String url,String json) throws Exception {
		System.out.println("url=="+url);
		System.out.println("json=="+json);

		Configure configure = new Configure();
		configure.setAppId(APP_ID);
		configure.setUserPrivateLoc("D:/epass_app/icbcinbs/pbank/yourname.pri");
		configure.setSysPubkeyLoc("D:/epass_app/icbcinbs/pbank/API_GATEWAY.pub");
		configure.setSysBaseUri("http://ip:port/api");
		configure.setAlgo(com.icbc.apip.token.SignatureAlgo.RSA1024);
		configure.setSysAuthMaxTimeStep(Long.MAX_VALUE);
		WebApiContext context = new WebApiContext();
		context.setConfigure(configure);
		context.init();

		DefaultInvoker invoker = context.getDefaultInvoker();
		// 设置请求路径
		invoker.setUri(url);
		invoker.setSendEncoding("utf-8");
		// 调用参数设置
		invoker.addParameter("biz_content", json);
		System.out.println("invoker:::"+invoker);
		invoker.setMethod(HttpMethodType.POST);
		Map<String, Object> resp = null;
		try {
			// 同步调用，并获取请求结果
			resp = invoker.syncInvoke();

			System.out.println(resp);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return resp;		
	}

	public static void main(String[] args) {
		try {
			PassUserinfoEncryptOrDecryptSAOpTest test = new PassUserinfoEncryptOrDecryptSAOpTest();
			Map<String, Object> packageData = test.packageData();
			test.GetepassuserinfoEncode(packageData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
