package com.icbc.api;

import com.icbc.api.request.JftUiInvoiceBackTaxRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftUiInvoiceBackTaxTestServletV1 extends HttpServlet {

	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	protected static final String APP_ID = "[需替换]APP的编号";
	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		try {

			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key);

			JftUiInvoiceBackTaxRequestV1 request = new JftUiInvoiceBackTaxRequestV1();

			request.setServiceUrl("[需替换]工行API网关地址/ui/jft/ui/invoice/back/tax/V1");
			JftUiInvoiceBackTaxRequestV1.JftUiInvoiceBackTaxRequestV1Biz bizContent = new JftUiInvoiceBackTaxRequestV1.JftUiInvoiceBackTaxRequestV1Biz();
			
			bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
			bizContent.setOutVendorId("20180426");//子商户标识：提交支付请求的子商户编号
			bizContent.setOutUserId("20250530");
			bizContent.setMerBusiModel("01");
			bizContent.setApplyReason("申请退税");
			bizContent.setTaxpayerNo("20250531");
			
			request.setBizContent(bizContent);

			resp.setHeader("Content-Type", "text/html;charset="
					+ IcbcConstants.CHARSET_UTF8);
			PrintWriter out = resp.getWriter();
			

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


