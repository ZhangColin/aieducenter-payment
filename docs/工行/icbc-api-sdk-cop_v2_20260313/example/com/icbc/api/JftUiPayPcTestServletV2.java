package com.icbc.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.request.JftUiPayPcRequestV2;
import com.icbc.api.request.JftUiPayPcRequestV2.JftUiPayPcRequestV2Biz;
import com.icbc.api.request.JftUiPayPcRequestV2.SubOrderInfo;
public class JftUiPayPcTestServletV2 extends HttpServlet {

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
			
			String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
			
			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key);

			JftUiPayPcRequestV2 request = new JftUiPayPcRequestV2();
			
			request.setServiceUrl("http://ip:port/ui/jft/ui/pay/pc/V2");
			JftUiPayPcRequestV2Biz bizContent = new JftUiPayPcRequestV2Biz();
			
			bizContent.setAppId("10000000000000045510");//平台商户标识：提交支付请求的平台商户编号	10000000000000001
			bizContent.setOutVendorId("20180426");//子商户标识：提交支付请求的子商户编号 
			bizContent.setOutUserId("M201801112326"); //用户标识
			bizContent.setPayMethod("01");//支付方式：固定取01  
			bizContent.setNotifyUrl("http://www.xx.com");//商户通知URL：商户接收支付成功通知消息URL，平台商户建档时需要维护  
			bizContent.setJumpUrl("http://www.xx.com");//商户跳转URL:支付完成后回调地址（跳转商户页面），不支持传参    
			bizContent.setGoodsId("3212");//主商品编号（长度单位：字节）     
			bizContent.setGoodsName("xx商品");//主商品名称（长度单位：字节）  
			bizContent.setOutOrderId("201804102908"); //平台对接方父订单ID 
			bizContent.setMac("a3-b4-c5");//PC mac:支付提交用户PC的Mac地址	        
			bizContent.setTrxIp("ip:port");//交易IP：支付提交用户IP地址      
			bizContent.setTrxIpCity("北京");//交易IP所在地：支付提交用户IP所在地  
			bizContent.setTrxChannel("02");//交易渠道:固定取03:H5 不送03校验不过 
			bizContent.setPayAmount("0.02");//支付金额(元)整数长度不能超过8位，小数位不能超过2位  
			bizContent.setVarNote("3212");//备注信息    
			bizContent.setSubMerRateOwn("300");//子商户服务费率（e支付本行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为非必输项（浮动费率必输）  
			bizContent.setSubMerRateOther("400");//子商户服务费率（e支付它行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为非必输项（浮动费率必输）
			bizContent.setSubMerRateWx("400");//子商户微信服务费率 非必输（浮动费率必输）
			bizContent.setSubMerRateZfb("400");//子商户支付宝服务费率 非必输（浮动费率必输）
			bizContent.setSubMerRateUnionPay("400");//子商户云闪付服务费率 非必输（浮动费率必输）
			bizContent.setWxProPayUrl("http://www.xx.com");//微信小程序Url,根据此URL在页面生成二维码，用户扫码后跳转微信小程序支付页面
			bizContent.setAliProPayUrl("http://www.xx.com");//支付宝生活号Url,根据此URL在页面生成二维码，用户扫码后跳转支付宝生活号支付页面
			bizContent.setVersion("1.0.1");//接口版本号
			bizContent.setOutBusinessCode("10000000000000000001");//外部业务编码
			bizContent.setPayType("02");//是否合并支付标识（01为单订单支付，02为合并支付）
			//子订单信息
			List<SubOrderInfo> subOrders = new ArrayList<SubOrderInfo>();
			SubOrderInfo sub1 = new SubOrderInfo();
			sub1.setOI("154529277181301");//外系统订单id
			sub1.setOV("1129102");//子商户标识
			sub1.setGI("3212");//商品id
			sub1.setGN("xx商品");//商品名称
			sub1.setPA("0.01");//交易金额
			sub1.setRW("400");//子商户微信费率
			sub1.setRZ("400");//子商户支付宝费率
			sub1.setROW("400");//子商户本行卡费率
			sub1.setROT("400");//子商户他行卡费率
			sub1.setRU("400");//子商户云闪付费率
			subOrders.add(sub1);
			
			SubOrderInfo sub2 = new SubOrderInfo();
			sub2.setOI("154529277181302");
			sub2.setOV("1129101");
			sub2.setGI("3212");
			sub2.setGN("xx商品");
			sub2.setPA("0.01");
			sub2.setRW("400");
			sub2.setRZ("400");
			sub2.setROW("400");
			sub2.setROT("400");
			sub1.setRU("400");//子商户云闪付费率
			subOrders.add(sub2);
			
			bizContent.setSubOrders(subOrders);
			
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


