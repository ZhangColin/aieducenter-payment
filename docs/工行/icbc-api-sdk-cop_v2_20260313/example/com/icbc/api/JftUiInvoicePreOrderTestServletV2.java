package com.icbc.api;

import com.icbc.api.request.JftUiInvoicePreOrderRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JftUiInvoicePreOrderTestServletV2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		try {
			String APP_ID = "10000000000000045510";
			
			String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";

			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8);

			JftUiInvoicePreOrderRequestV1 request = new JftUiInvoicePreOrderRequestV1();

			request.setServiceUrl("http://ip:port/ui/jft/ui/invoice/pre/order/V1");
			JftUiInvoicePreOrderRequestV1.JftUiInvoicePreOrderRequestV1Biz bizContent = new JftUiInvoicePreOrderRequestV1.JftUiInvoicePreOrderRequestV1Biz();
			
			bizContent.setAppId("10000000000000045510");//平台商户标识：提交支付请求的平台商户编号	10000000000000001
			bizContent.setOutVendorId("20180426");//子商户标识：提交支付请求的子商户编号
			bizContent.setOutOrderId("201804102908"); //平台对接方订单ID
			bizContent.setOutUserId("M201801112326"); //用户标识
			bizContent.setTrxChannel("02");//交易渠道
			bizContent.setAsynFlag("0");
			bizContent.setPayPhoneno("18888888888");
			bizContent.setCurrency("001");

			bizContent.setInvoiceJumpUrl("http://www.xx.com");//商户跳转URL:受理完成后回调地址（跳转商户页面），不支持传参
			bizContent.setInvoiceNotifyUrl("http://www.xx.com");//商户通知URL：商户接收通知消息URL
			bizContent.setPayJumpUrl("http://www.xx.com");//商户跳转URL:支付完成后回调地址（跳转商户页面），不支持传参
			bizContent.setInvoiceType("01");
			bizContent.setOrderAmount("0.02");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
			bizContent.setNaturalPersonName("姓名");
			bizContent.setCardType("");
			bizContent.setCardNumber("0111010101012101");
			bizContent.setSellerAddress("xx市xx区");
			bizContent.setTaxpayerNo("121");
			bizContent.setTaxpayerName("xx公司");
			bizContent.setMac("a3-b4-c5");//PC mac:支付提交用户PC的Mac地址	        
			bizContent.setTrxIp("ip:port");//交易IP：支付提交用户IP地址

			//子订单信息
			List<JftUiInvoicePreOrderRequestV1.GoodsInfo> goodsInfoList = new ArrayList<JftUiInvoicePreOrderRequestV1.GoodsInfo>();
			JftUiInvoicePreOrderRequestV1.GoodsInfo goods1 = new JftUiInvoicePreOrderRequestV1.GoodsInfo();
			goods1.setGoodsSeqno("1");
			goods1.setProjectName("xx商品");
			goods1.setGoodsNum("10");
			goods1.setGoodsAmt("50");
			goods1.setUnits("个");
			goods1.setTaxRate("0.03");
			goods1.setMergedCode("1001");
			goodsInfoList.add(goods1);

			JftUiInvoicePreOrderRequestV1.GoodsInfo goods2 = new JftUiInvoicePreOrderRequestV1.GoodsInfo();
			goods2.setGoodsSeqno("2");
			goods2.setProjectName("xx商品2");
			goods2.setGoodsNum("10");
			goods2.setGoodsAmt("50");
			goods2.setUnits("个");
			goods2.setTaxRate("0.03");
			goods2.setMergedCode("1001");
			goodsInfoList.add(goods2);
			
			bizContent.setGoodsInfo(goodsInfoList);
			
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


