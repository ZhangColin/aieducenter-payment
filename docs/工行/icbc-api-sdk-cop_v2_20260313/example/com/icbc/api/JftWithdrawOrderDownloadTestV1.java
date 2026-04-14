package com.icbc.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.icbc.api.IcbcApiException;

public class DownloadTestV1 extends HttpServlet {

	public static void main(String[] args) throws IcbcApiException {
		String appId = "10000000000000045006";		
		String token = "abc";			//  获取token接口返回的token值
		String randomValue = "abc";		//	获取token接口返回的随机数
		String acDate = "2019-07-02";	// 	对账日期，格式："yyyy-MM-dd"
		try {
			URL url = new URL("http://localhost/jft-pay/pay/fileDownload?"
					+ "appId="+appId +"&"
					+ "token="+token + "&"
					+ "randomValue="+randomValue + "&"
					+ "acDate="+acDate);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection)urlConnection;
	
			/**
			 * 通过getHeaderFields方法，获取头文件中的参数map，key = "rets",获取到响应信息的List<String>
			 * List中第一个参数为响应码；第二个参数为响应信息
			 * 响应信息为utf-8编码，所以读取时也需要使用utf-8解码
			 */
			Map<String, List<String>> map = httpUrlConnection.getHeaderFields();
			List<String> retList = map.get("rets");
			String retcode = "";
			String retmsg = "";
			if(retList != null && retList.size() > 0){
				retcode = URLDecoder.decode(retList.get(0), "utf-8");
				retmsg = URLDecoder.decode(retList.get(1), "utf-8");
			}
			System.out.println(retcode);
			if("0000".equals(retcode)){//响应码"0000"为下载成功
				System.out.println(retmsg);
				
				InputStream is = httpUrlConnection.getInputStream();
				byte[] buffer = new byte[1024];
				File file = new File("E:\\aaa.zip");
				FileOutputStream fos = new FileOutputStream(file);
				int i = -1;
				while ((i = is.read(buffer)) != -1) {
					fos.write(buffer, 0, i);
				}
				is.close();
				fos.flush();
				fos.close();
			}
			if("0401".equals(retcode)){
				//"0401"，未找到对账文件
				System.out.println(retmsg);
			}	
			if("0402".equals(retcode)){
				//"0402"，token验证失败
				System.out.println(retmsg);
			}
			if("0403".equals(retcode)){
				//"0403"，上送的日期格式不正确
				System.out.println(retmsg);
			}			
			if("0404".equals(retcode)){
				//"0404"，该结算对账单已过期,不能下载
				System.out.println(retmsg);
			}			
			if("0405".equals(retcode)){
				//"0405"，服务异常
				System.out.println(retmsg);
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


