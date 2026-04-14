package com.icbc.ndf.jft.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.ndf.jft.pay.controller.JftUiPayH5QueryRequestV1.JftUiPayH5QueryRequestV1Biz;


public class JftUiPayH5QueryTestServletV1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		try {
			//商户向api平台申请的平台商户编号
			String APP_ID = "[需替换]APP的编号";
			//商户申请平台编号后获取的私钥
			String MY_PRIVATE_KEY = "[需替换]应用方私钥";
			String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
					
			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key);
			
			JftUiPayH5QueryRequestV1 request = new JftUiPayH5QueryRequestV1();
			
			request.setServiceUrl("http://ip:port/ui/jft/ui/pay/queryRefundOrOrderByPC/V1");
			JftUiPayH5QueryRequestV1Biz bizContent = new JftUiPayH5QueryRequestV1Biz();
			
			bizContent.setAppId("10000000000000045510");//平台商户标识：提交支付请求的平台商户编号
			
			request.setBizContent(bizContent);

			resp.setHeader("Content-Type", "text/html;charset="
					+ IcbcConstants.CHARSET_UTF8);
			PrintWriter out = resp.getWriter();
			System.out.println(client.buildPostForm(request));

			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
					+ IcbcConstants.CHARSET_UTF8 + "\">");
			out.write("</head>");
			out.write("<body>");
			out.write(client.buildPostForm(request));
			out.write("</body>");
			out.write("</html>");

		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}


