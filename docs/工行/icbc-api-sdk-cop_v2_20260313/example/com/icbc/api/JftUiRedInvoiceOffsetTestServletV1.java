package com.icbc.api.request;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JftUiRedInvoiceOffsetTestServletV1 extends HttpServlet {

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
			String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key);

			JftUiRedInvoiceOffsetRequestV1 request = new JftUiRedInvoiceOffsetRequestV1();
			request.setServiceUrl("[需替换]工行API网关地址/ui/jft/ui/red/invoice/offset/V1");//生产环境需替换为生产地址
			JftUiRedInvoiceOffsetRequestV1.JftUiRedInvoiceOffsetRequestV1Biz bizContent = new JftUiRedInvoiceOffsetRequestV1.JftUiRedInvoiceOffsetRequestV1Biz();

			bizContent.setAppId("10000000000000045510");//平台商户标识：提交支付请求的平台商户编号	10000000000000001
			bizContent.setOutRedOffsetId("202408300123");//红冲流水号
			bizContent.setOutOrderId("20240830001"); //平台对接方订单ID
			bizContent.setRedOffsetReason("02"); //红冲原因
			bizContent.setRedOffsetAmount("10.00");//红冲含税金额
			bizContent.setIsRedo("N");
			bizContent.setChannel("01");
            bizContent.setJumpUrl("http://www.xxx.com");
            bizContent.setMac("3c:28:6e");
            bizContent.setMainBoardId("");
            bizContent.setCpuId("");

			//红冲商品列表
			List<JftUiRedInvoiceOffsetRequestV1.RedOffsetGoodsInfo> goodsInfoList = new ArrayList<JftUiRedInvoiceOffsetRequestV1.RedOffsetGoodsInfo>();
			JftUiRedInvoiceOffsetRequestV1.RedOffsetGoodsInfo goods1 = new JftUiRedInvoiceOffsetRequestV1.RedOffsetGoodsInfo();
			goods1.setGoodsSeqno("1");//红冲商品信息子序号
			goods1.setBlueGoodsSeqno("1");//蓝字发票商品信息子序号
			goods1.setWeight("");//商品规格
			goods1.setUnits("个");//计量单位
			goods1.setPrice("10.00");//含税单价(元)
			goods1.setGoodsNum("-5");//商品数量
			goods1.setGoodsAmt("-50.00");//红冲含税金额(元)
			goods1.setProjectName("xx商品");//项目名称
			goodsInfoList.add(goods1);

			JftUiRedInvoiceOffsetRequestV1.RedOffsetGoodsInfo goods2 = new JftUiRedInvoiceOffsetRequestV1.RedOffsetGoodsInfo();
			goods2.setGoodsSeqno("1");//红冲商品信息子序号
			goods2.setBlueGoodsSeqno("1");//蓝字发票商品信息子序号
			goods2.setWeight("");//商品规格
			goods2.setUnits("件");//计量单位
			goods2.setPrice("3.00");//含税单价(元)
			goods2.setGoodsNum("-2");//商品数量
			goods2.setGoodsAmt("-6.00");//红冲含税金额(元)
			goods2.setProjectName("xx商品");//项目名称
			goodsInfoList.add(goods2);
			
			bizContent.setRedOffsetList(goodsInfoList);
			
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
			e.printStackTrace();
		}
	}
}


