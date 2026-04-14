package com.icbc.api;


import com.icbc.api.request.JftUiInvoiceAuthorizationRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftUiInvoiceAuthorizationTestServletV1 extends HttpServlet {
	private final Logger log = LoggerFactory.getLogger(getClass());
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

			JftUiInvoiceAuthorizationRequestV1 request = new JftUiInvoiceAuthorizationRequestV1();

			request.setServiceUrl("http://XXX.XX.XX:8081/ui/jft/ui/invoice/authorization/V1");
			JftUiInvoiceAuthorizationRequestV1.JftUiInvoiceAuthorizationRequestV1Biz bizContent = new JftUiInvoiceAuthorizationRequestV1.JftUiInvoiceAuthorizationRequestV1Biz();

			bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号
			bizContent.setOutVendorId("xxxx");//子商户编号
			bizContent.setArchiveType("01");//开票档案类型，00-平台档案；01--子商户档案

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
			log.error(e.getMessage());
		}
	}
}