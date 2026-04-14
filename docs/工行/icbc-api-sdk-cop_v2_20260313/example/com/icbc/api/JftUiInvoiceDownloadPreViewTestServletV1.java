package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiInvoiceDownloadPreViewRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftUiInvoiceDownloadPreViewTestServletV1 extends HttpServlet {
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

			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

			JftUiInvoiceDownloadPreViewRequestV1 request = new JftUiInvoiceDownloadPreViewRequestV1();

			request.setServiceUrl("http://XXX.XX.XX:8081/ui/jft/ui/invoice/download/V1");
			JftUiInvoiceDownloadPreViewRequestV1.JftUiInvoiceDownloadPreViewRequestV1Biz bizContent = new JftUiInvoiceDownloadPreViewRequestV1.JftUiInvoiceDownloadPreViewRequestV1Biz();
			
			bizContent.setAppId(APP_ID);//平台商户标识
            bizContent.setOutOrderId("20200202002");//平台对接方订单ID
            bizContent.setOutRedOffsetId("2024090401");//红冲流水号
            bizContent.setIsRedOrblue("01");//01:蓝票
            bizContent.setOutInvoiceId("20250120001");//开票请求流水号
            bizContent.setPartnerMerTrueAppId("10000000000000121520");//开票企业外部编号
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


