package com.icbc.api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EpayProtocolQueryRequestV1;
import com.icbc.api.request.EpayProtocolQueryRequestV1.*;
import com.icbc.api.response.EpayProtocolQueryResponseV1;

public class EpayProtocolQueryTest  extends HttpServlet {
	public EpayProtocolQueryTest() {
		super();
	}

	public void init() throws ServletException {
	}
	
	
	protected void service(HttpServletRequest request,HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {

		request.setCharacterEncoding("GBK");
		
		String service_url = request.getParameter("service_url");//API测试环境 交易对应api url
		
		//公共请求通讯区
		String app_id = request.getParameter("app_id");//APPID
		
		String charset = request.getParameter("charset");//字符集
		
		String public_key = request.getParameter("public_key");//公钥
		
		String private_key = request.getParameter("private_key");//私钥
		
		String mobile = request.getParameter("mobile");//手机号
		
		String cardno = request.getParameter("cardno");//合作方二三类户
		 

		//private_key = private_key.replaceAll(" ", "+");
			//String api_public_key = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(app_id, private_key, public_key);					

		EpayProtocolQueryRequestV1 requestApi = new EpayProtocolQueryRequestV1();
		EpayProtocolQueryRequestV1Biz bizContent = new EpayProtocolQueryRequestV1Biz();
		bizContent.setMobile(mobile);
		bizContent.setCardno(cardno);
		requestApi.setBizContent(bizContent);	
		requestApi.setServiceUrl(service_url);
		
		EpayProtocolQueryResponseV1 responseApi;
		response.setHeader("Content-Type", "text/html; charset=" + charset);
		PrintWriter out = response.getWriter();
		try{
		responseApi = (EpayProtocolQueryResponseV1) client.execute(requestApi, "msgId");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
		out.write("<html>");
		out.write("<head>");
		out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\">");
		out.write("</head>");
		out.write("<body>");
		if (responseApi.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				out.write(JSON.toJSONString(responseApi));
				System.out.println("ReturnCode:"+responseApi.getReturnCode());
				System.out.println("responseApi:" + responseApi);
			} else {
				//失败
				out.write(JSON.toJSONString(responseApi));
				System.out.println("ReturnCode:"+responseApi.getReturnCode());
				System.out.println("ReturnMsg:"+responseApi.getReturnMsg());
			}		
		
		out.write("</body>");
		out.write("</html>");
		
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}			
		}	
}
