package com.icbc.api;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.icbc.apip.config.Configure;
import com.icbc.apip.context.WebApiContext;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker.HttpMethodType;

public class PassQueryPhotoSAOpTest {

	//1、mims
	protected static final String APP_ID = "";//10000000000004100275

	//	封装查询参数
	public Map<String, Object> packageData() throws Exception{

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("versionid","01");
		map.put("appsenderid","F-MALLC");
		map.put("appreceiverid","F-EPASS");//map
		map.put("structtype","JSON");
		map.put("mesgcharset","UTF-8");
		map.put("msg_id","");
		map.put("mesgtype", "");
		map.put("mesgstat", "");
		map.put("mesgpriority", "");

		Map<String, String> ChanCommV10 = new HashMap<String,String>();
		ChanCommV10.put("serialno", "");//032409112394393047342477520

		Map<String, String> InfoCommV10 = new HashMap<String,String>();
		InfoCommV10.put("trxtype", "001");
		InfoCommV10.put("trxcode", "PassQueryPhotoAPI");
		InfoCommV10.put("zoneno", "0101");
		InfoCommV10.put("brno", "0001");


		Map<String, String> inqCommV10 = new HashMap<String,String>();

		Map<String, String> inputV10 = new HashMap<String,String>();
		inputV10.put("fcode","PassQueryPhotoAPI");
		inputV10.put("server_ip","");//ip:port
		inputV10.put("dt","2022-03-21");
		inputV10.put("tm","092957");
		inputV10.put("channelidentifier","");//ip:port.20230120092957.450
		inputV10.put("channelcode","303");
		inputV10.put("querytype","2");
		inputV10.put("ciscode","020000283947377");

		map.put("chanCommV10", ChanCommV10);
		map.put("infoCommV10", InfoCommV10);
		map.put("inqCommV10", inqCommV10);
		map.put("inputV10", inputV10);
		return 	map;

	}

	public void GetepassuserinfoEncode(Map<String, Object> packageData) throws Exception{
		//建立返回项
		String json = JSON.toJSONString(new Object[]{packageData});
		String url = "/cert/epass/mediaprocess/epassphotoquery/V1";
		Map<String, Object> responseBody = doDefaultInvoker(url, json);
		if(responseBody!=null){
			Map<String,Object> appstatv10=(Map<String, Object>)responseBody.get("appstatv10");

			if("1".equals(appstatv10.get("transok"))){
				//System.out.println("appstatv10.get('transok')==" +appstatv10.get("transok"));
				//System.out.println("appstatv10.get('return_code')==" + appstatv10.get("return_code"));
				//System.out.println("appstatv10.get('return_msg')=="+ appstatv10.get("return_msg"));
			}else {
				//System.out.println("appstatv10.get('transok')==" +appstatv10.get("transok"));
				//System.out.println("appstatv10.get('return_code')==" + appstatv10.get("return_code"));
				//System.out.println("appstatv10.get('return_msg')=="+ appstatv10.get("return_msg"));	
			}
			
			Map<String,String> privatev10=(Map<String, String>)responseBody.get("privatev10");
			
			if(privatev10!=null){
				//System.out.println("privatev10.get('nickname')==" + privatev10.get("nickname"));
				//System.out.println("privatev10.get('filepath')==" + privatev10.get("filepath"));
			}			
		}else{
			//System.out.println("responseBody==" + responseBody);
		}

	}

	public Map<String, Object>  doDefaultInvoker(String url,String json) throws Exception {
		//System.out.println("url=="+url);
		//System.out.println("json=="+json);

		Configure configure = new Configure();
		configure.setAppId(APP_ID);
		configure.setUserPrivateLoc("D:/epass_app/icbcinbs/pbank/yourname.pri");//app管理里面的签名公钥  对应  这个私钥   
		configure.setSysPubkeyLoc("D:/epass_app/icbcinbs/pbank/API_GATEWAY.pub");//网关公钥
		configure.setSysBaseUri("");//http://ip:port/api
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
		invoker.setMethod(HttpMethodType.POST);
		Map<String, Object> resp = null;
		try {
			// 同步调用，并获取请求结果
			resp = invoker.syncInvoke();

			//System.out.println(resp);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return resp;		
	}

	public static void main(String[] args) {
		try {
			PassQueryPhotoSAOpTest test = new PassQueryPhotoSAOpTest();
			Map<String, Object> packageData = test.packageData();
			test.GetepassuserinfoEncode(packageData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
