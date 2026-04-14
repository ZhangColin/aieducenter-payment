package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankLoanPersonalloanImageuploadnewRequestV1;
import com.icbc.api.request.MybankLoanPersonalloanImageuploadnewRequestV1.MybankLoanPersonalloanImageuploadnew;
import com.icbc.api.request.MybankLoanPersonalloanImageuploadnewRequestV1.MybankLoanPersonalloanImageuploadnewRequestV1Biz;
import com.icbc.api.response.MybankLoanPersonalloanImageuploadnewResponseV1;

public class MybankLoanPersonalloanImageuploadnewTestV1 {
	
	 static Logger log = Logger.getLogger(MybankLoanPersonalloanImageuploadnewTestV1.class.getName());
	    //估�1�7�表
	    protected static String APP_ID = "xxxxxx";
	 
	    //网关公钥
	    protected static String APIGW_PUBLIC_KEY = "xxxxxx";
	    //应用私钥（与内管平台注册app时的“签名公钥�1�7�配对）
	    protected static String PRI_KEY = "xxxxxx";
	    public static void main(String[] args) throws Exception {
	       
	        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, null, (String)null, (String)null, (String)null);               	       
	        String url = "http://ip:port/api/mybank/loan/personalloan/imageuploadNew/V1";
	        try {
	            // 请求实体
	        	MybankLoanPersonalloanImageuploadnewRequestV1 request = new  MybankLoanPersonalloanImageuploadnewRequestV1();
	            // bizContent实体
	        	MybankLoanPersonalloanImageuploadnewRequestV1Biz bizContent  = new  MybankLoanPersonalloanImageuploadnewRequestV1Biz();
	        	MybankLoanPersonalloanImageuploadnew param = new MybankLoanPersonalloanImageuploadnew();	        	
	        	// 公共参数
	    		param.setSerialNo("12334455");
	    		param.setAppNo("F-APIP");
	    		param.setAreaCode("");
	    		param.setEmployeeCode("");
	    		param.setLanguage("zh_CN");
	    		param.setTransNo("MybankLoanPersonalloanImageupload");
	    		param.setVer("1.0");
	    		param.setTurnPageFlag("");
	    		param.setBeginRow("");
	    		param.setRowCount("");	    		
	    		//私有入参
	        	param.setCooperCode("666666");	        
	        	param.setBankNo("325555");
	        	param.setFileId("12233");
	        	param.setFileName("解压.doc");
	        	param.setFileClob("1233");	                	
	            bizContent.setParam(param);                                                                 	           	            
	            request.setServiceUrl(url);
	            request.setBizContent(bizContent);	            	     
	            log.info("response:" + JSON.toJSONString(request));
	            MybankLoanPersonalloanImageuploadnewResponseV1 response = client.execute(request);
	            log.info("response:" + JSON.toJSONString(response));
	            boolean flag=response.isSuccess();
	            if (flag) {
	                // 业务成功处理
	                log.info("success");//
	            } else {
	                // 失败
	                log.info("error");
	            }	                    
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	            	            	        
	            	            
	            
	         //http://122.70.13.147/api
//	        String req_json = JSON.toJSONString(param);
//	        Configure configure = new Configure();
//	  		configure.setAppId(APP_ID);
//	  		configure.setUserPrivateLoc("D:\\apikey\\icbc_sign_service(sdk).pri");
//	  		configure.setSysPubkeyLoc("D:\\apikey\\API_GATEWAY.pub");
//	  		configure.setSysBaseUri("http://122.70.13.147/api");
//	  		configure.setAlgo(com.icbc.apip.token.SignatureAlgo.RSA2048);
//	  		//configure.setSysAuthMaxTimeStep(Long.MAX_VALUE);
//	  		WebApiContext context = new WebApiContext();
//	  		context.setConfigure(configure);
//	  		context.init();

//	  		DefaultInvoker invoker = context.getDefaultInvoker();
//	  		invoker.setUri("/mybank/loan/personalloan/imageupload/V1");
//	  		invoker.setSendEncoding("utf-8");
//	  		invoker.addParameter("biz_content", req_json);
//	  		System.out.println("invoker:::"+invoker);
//	  		invoker.setMethod(HttpMethodType.POST);
//	  		Map<String, Object> resp = null;
//	  		try {
//	  			resp = invoker.syncInvoke();
//	  			System.out.println(resp);
//	  		} catch (Exception e) {
//	  			e.getMessage();
//	  			e.printStackTrace();
//	  		}/
	          	            	            	            	            
	       
	    }

}
