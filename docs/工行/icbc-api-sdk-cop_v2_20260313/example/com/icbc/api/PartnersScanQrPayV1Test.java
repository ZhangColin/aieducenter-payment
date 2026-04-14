package com.icbc.api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.BizContent;
import com.icbc.api.IcbcApiException;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.PartnersScanQrPayRequestV1;

public class PartnersScanQrPayV1Test extends HttpServlet {
	private static final long serialVersionUID = -6906580622995519046L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//如下密钥获取方式，请合作方自行修改，本demo只为测试用
		String charset = "utf-8";
		String appid = req.getParameter("appid");
		String priAPP = req.getParameter("pri");//签名RSA2私钥
		String aesKey = req.getParameter("aesKey");//AES密钥
		String interfaceName = req.getParameter("interfaceName");
		String interfaceVersion = req.getParameter("interfaceVersion");
		String service_url = req.getParameter("service_url");
		
		String qr_code = req.getParameter("qr_code"); 
		String order_date = req.getParameter("order_date"); 
		String expire_time = req.getParameter("expire_time"); 
		String notify_url = req.getParameter("notify_url"); 
		String mer_reference = req.getParameter("mer_reference"); 
		String corp_appid = req.getParameter("corp_appid"); 
		String wallet_flag = req.getParameter("wallet_flag"); 
		String cust_phone = req.getParameter("cust_phone"); 
		String cust_id = req.getParameter("cust_id"); 
		
		//组装biz_content,请合作方按需组装
		String biz_content="{\"order_info\":{\"qr_code\":\""+ qr_code 
				+ "\",\"order_date\":\"" + order_date 
				+"\",\"expire_time\":\"" + expire_time 
				+"\",\"credit_type\":\"2\",\"notify_url\":\""+ notify_url 
				+"\",\"notify_type\":\"HS\",\"result_type\":\"1\","
				+"\"mer_reference\":\""+ mer_reference + "\",\"return_url\":\"\",\"mer_var\":\"\",\"backup1\":\"\"},"
				+ "\"corp_info\":"
				+ "{\"corp_appid\":\""+ corp_appid +"\",\"wallet_flag\":\""+ wallet_flag +"\"},"
				+ "\"cust_info\":{\"cust_phone\":\""+ cust_phone +"\""
				+ ",\"cust_id\":\""+ cust_id +"\"}}";
		
		System.out.println(biz_content);
		UiIcbcClient client = null;
		if (req.getParameter("signtype").equals("rsa")) {
			client = new UiIcbcClient(appid, priAPP, charset);
		}
		if (req.getParameter("signtype").equals("rsa2")) {
			client = new UiIcbcClient(appid, "RSA2", priAPP, "UTF-8", "AES", aesKey);
		}

		resp.setHeader("Content-Type", "text/html; charset=" + charset);
		PrintWriter out = resp.getWriter();
		try {
			PartnersScanQrPayRequestV1 request = new PartnersScanQrPayRequestV1();
			BizContent bizContent = (BizContent) JSON.parseObject(biz_content, request.getBizContentClass());
			request.setBizContent(bizContent);
			request.setServiceUrl(service_url);
			request.setInterfaceName(interfaceName);
			request.setInterfaceVersion(interfaceVersion);
			
			String reqString = client.buildPostForm(request);
			System.out.println(reqString);

			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\">");
			out.write("</head>");
			out.write("<body>");

			out.write(reqString);

			out.write("</body>");
			out.write("</html>");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IcbcApiException e) {
			e.printStackTrace(out);
		} 
	}
	
}
