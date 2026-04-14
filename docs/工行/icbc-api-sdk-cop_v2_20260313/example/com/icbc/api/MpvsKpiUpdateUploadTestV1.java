package com.icbc.mpvs.bean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.icbc.api.request.MpvsKpiUpdateUploadRequestV1;
import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.exception.ConfigException;
import com.icbc.apip.exception.InvokerException;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invokers;
import com.icbc.apip.invoker.Invoker.HttpMethodType;


public class MpvsKpiUpdateUploadTestV1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");

		// 获取调用对象；设置服务对应的uri、调用方式、调用参数； 
		DefaultInvoker invoker = Invokers.getDefaultInvoker();
		//权限相关
//		BaseAuth baseAuth = new BaseAuth();
//		baseAuth.setName("xxxxxx");//咨询F-MPVS应用获取
//		baseAuth.setPass("xxxxxxxxx");//咨询F-MPVS应用获取
//		baseAuth.setMsg_id("000000000003");
//		baseAuth.setOp("1");
//		baseAuth.setSubware("002");
		
		List<String> list = new ArrayList<String>();
	
		//list.add(" 0120100000 1201 0000001000009016300100000020181231 0120100000          987654321.99");
		//list.add(" 0120100000 1201 0000001000009016300100000020181131 0120100000          333333333.99");
		
		MpvsKpiUpdateUploadRequestV1 bean = new MpvsKpiUpdateUploadRequestV1();
//		bean.setApp_code(JSON.toJSONString(baseAuth));--按这个调用
		bean.setApp_code("xxxxx");
		bean.setKpis(list);
		String req_json = JSON.toJSONString(bean);
        //String req_json= "{\"app_code\":\"{\\\"name\\\":\\\"FHZJ\\\",\\\"op\\\":\\\"1\\\",\\\"pass\\\":\\\"FHZJ\\\",\\\"msg_id\\\":\\\"000000000001\\\",\\\"subware\\\":\\\"001\\\"}\",\"kpis\":[\" 0120100000001000001531200100000020181231 0120100000          987654321.99\",\" 0120100000001000001531200100000020181231 0120100000          987654321.99\"]}";
		invoker.setUri("/mpvs/kpi/update/upload/V1");
		invoker.setMethod(HttpMethodType.POST); 
		invoker.addParameter("biz_content", req_json);
		try {
			//System.out.println("invoker.getJsonStr()-->"+req_json);
			//Object obj = invoker.syncInvokeObject();
			invoker.syncInvokeObject();
			//System.out.println(obj.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ConfigException e) {
			e.printStackTrace();
		} catch (InvokerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
