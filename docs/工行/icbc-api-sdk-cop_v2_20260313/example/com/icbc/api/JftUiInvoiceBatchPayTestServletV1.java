package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiInvoiceBatchPayRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JftUiInvoiceBatchPayTestServletV1 extends HttpServlet {
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

			JftUiInvoiceBatchPayRequestV1 request = new JftUiInvoiceBatchPayRequestV1();

			request.setServiceUrl("http://[需替换]工行API网关地址:端口/ui/jft/ui/invoice/batchPay/V1");
			JftUiInvoiceBatchPayRequestV1.JftUiInvoiceBatchPayRequestV1Biz bizContent = new JftUiInvoiceBatchPayRequestV1.JftUiInvoiceBatchPayRequestV1Biz();

			bizContent.setAppId("");//平台商户标识：提交支付请求的平台商户编号
			bizContent.setVerifiedCode("");//机构编码，场景支付时必输
			bizContent.setUkeyId("");//U盾ID，场景支付时必输

			//订单信息
			List<JftUiInvoiceBatchPayRequestV1.orderInfo> orderInfoList = new ArrayList<JftUiInvoiceBatchPayRequestV1.orderInfo>();
			JftUiInvoiceBatchPayRequestV1.orderInfo orderInfo1 = new JftUiInvoiceBatchPayRequestV1.orderInfo();

			orderInfo1.setOutOrderId("");
			orderInfo1.setOutVendorId("");
			orderInfo1.setOutUserId("");

			orderInfoList.add(orderInfo1);
			bizContent.setOrderInfo(orderInfoList);

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


