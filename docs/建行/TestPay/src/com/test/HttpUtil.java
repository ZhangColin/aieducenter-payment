package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 *  
 *  @author aoliu
 *  @lastModified       
 *  @history            
 */
public class HttpUtil {
	

    
    /** 
     * post请求（用于请求json格式的参数） 
     * @param urlPath
     * @param Json
     * @return 
     */  
    public static String doJsonPost(String urlPath, String Json) {
        
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            
            conn.setRequestProperty("userid", "8a80c4d7648dcd0401648dcd316c0000");
            conn.setRequestProperty("serviceid", "4028bcd26565cc9901658e01b9f40000");
            //conn.setRequestProperty("Authorization", "Basic 8a80c4d7648dcd0401648dcd316c00004028bcd26565cc9901658e01b9f40000");
            //conn.addRequestProperty(key, value);
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返�?415;
            conn.setRequestProperty("accept","application/json");
            // �?服务器里面发送数�?
            if (Json != null) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
            }
            System.out.println("http返回码"+conn.getResponseCode());
          
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static String urlDoce(String data) {
    	new JSONObject();
    	JSONObject  json  = JSON.parseObject(data);
		String s = json.getString("Py_URL");
    	
		String result = "";
		try {
			result = URLDecoder.decode(s,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
   
    public static void trustAllHosts() {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
    		
    		public X509Certificate[] getAcceptedIssuers() {
    			// TODO Auto-generated method stub
    			return null;
    		}
    		
    		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    			// TODO Auto-generated method stub
    			
    		}
    		
    		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    			// TODO Auto-generated method stub
    			
    		}
    	}

        };

     

        try {

            SSLContext sc = SSLContext.getInstance("TLS");

            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
    public static void main(String[] args) {
    	String result = "";
    	
    	 JSONObject  json = new JSONObject();
 		json.put("tradeNo", "123");
 		json.put("payType", "1");
 		json.put("cardType", "2");
 		json.put("appsId", "123");
 		
    	  String url = "http://127.0.0.1:9082/cip-rest/busCard/test";
    	 String  ss  = "{\"tradeNo\":\"123\",\"appsId\":\"123\",\"payType\":\"1\",\"cardType\":\"2\"}";
    	
    	 try {
    		 result = HttpUtil.doJsonPost(url,json.toString() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println(result);
	}


}
