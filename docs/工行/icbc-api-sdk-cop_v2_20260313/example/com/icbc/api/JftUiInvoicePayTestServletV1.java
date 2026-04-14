package com.icbc.api;

import com.icbc.api.request.JftUiInvoicePayRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JftUiInvoicePayTestServletV1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		try {
			String APP_ID = "[需替换]APP的编号";
			
			String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8);

			JftUiInvoicePayRequestV1 request = new JftUiInvoicePayRequestV1();

			request.setServiceUrl("http://[需替换]工行API网关地址:端口/ui/jft/ui/invoice/pay/V1");
			JftUiInvoicePayRequestV1.JftUiInvoicePayRequestV1Biz bizContent = new JftUiInvoicePayRequestV1.JftUiInvoicePayRequestV1Biz();
			
			bizContent.setAppId("10000000000000045510");//平台商户标识：提交支付请求的平台商户编号	10000000000000001
			bizContent.setOutVendorId("20180426");//子商户标识：提交支付请求的子商户编号
			bizContent.setOutOrderId("201804102908"); //平台对接方订单ID
			bizContent.setOutUserId("M201801112326"); //用户标识
			bizContent.setVerifiedCode("");//机构编码，场景支付时必输
			bizContent.setUkeyId("");//U盾ID，场景支付时必输
			
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


