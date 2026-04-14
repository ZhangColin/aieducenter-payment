package com.icbc.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.request.JftUiPayH5RequestV3;
import com.icbc.api.request.JftUiPayH5RequestV3.JftUiPayH5RequestV3Biz;
import com.icbc.api.request.JftUiPayH5RequestV3.SubOrderInfo;

public class JftUiPayH5TestServletV3 extends HttpServlet {

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
			String AES_Key ="5xGJdh7qb+B95SUoxDlatg==";
			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key);
			JftUiPayH5RequestV3 request = new JftUiPayH5RequestV3();
			
			request.setServiceUrl("[需替换]工行API网关地址/ui/jft/ui/pay/h5/V3");
			JftUiPayH5RequestV3Biz bizContent = new JftUiPayH5RequestV3Biz();
			
			bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
			bizContent.setOutVendorId("1224536");//子商户标识：提交支付请求的子商户编号 
			bizContent.setOutUserId("M201801112326");//用户标识
			bizContent.setNotifyUrl("http://xx.com");//商户通知URL：商户接受支付成功通知消息URL
			bizContent.setJumpUrl("www.xxx.com"); //商户跳转URL:支付完成后回调地址（跳转商户页面），不支持传参 
			bizContent.setOutOrderId("20190102_3");//平台对接方ID：平台商户订单ID，确保唯一
			bizContent.setGoodsId("3212");//商品编号（长度单位：字节）
			bizContent.setGoodsName("测试商品");//商品名称（长度单位：字节）
			bizContent.setPayType("02");//支付类型-合并支付
			bizContent.setMac("a3-b4-c5");//手机Mac:支付提交用户手机Mac地址
			bizContent.setTrxIp("xx.xx.xxx.xxx");//交易IP：支付提交用户IP地址
			bizContent.setTrxIpCity("北京");//交易IP所在地：支付提交用户IP所在地
			bizContent.setTrxChannel("03");//交易渠道:固定取03
			bizContent.setPayAmount("1.01");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
			bizContent.setSubMerRateOwn("100");//子商户服务费率（e支付本行卡）,商户档案中支付费率类型为区间支付费率，则该字段为必输项 
			bizContent.setSubMerRateOther("100");//子商户服务费率（e支付它行卡）,商户档案中支付费率类型为区间支付费率，则该字段为必输项 
			bizContent.setSubMerRateWx("400");//子商户服务费率（微信）,商户档案中支付费率类型为区间支付费率，则该字段为必输项 
			bizContent.setSubMerRateZfb("400");//子商户服务费率（支付宝）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			bizContent.setTpAppId("wx0f6flal9af93dbe6");//第三方应用ID；商户在微信公众号内或微信小程序内接入时必送，上送微信分配的公众账号ID；商户通过支付宝生活号接入时不送。
			bizContent.setTpOpenId("odg9h43-kX46sAmzGsk3tAvMPbU4");//第三方用户标识；商户在微信公众号内或微信小程序内接入时必送，上送用户在商户appid下的唯一标识；商户通过支付宝生活号接入时不送。
			bizContent.setUnionId("");//第三方用户标识；商户在支付宝生活号接入时必送，上送用户的唯一标识;商户在微信公众号内或微信小程序内选送，上送用户唯一标识。
			bizContent.setVarNote("商户备注");//商户备注
			bizContent.setOutBusinessCode("10000000000000000001");//外部业务编码
			bizContent.setCustomerMobile("15512345678");//客户上送手机号			
			
			//以下为子订单设置
			List<SubOrderInfo> subOrders = new ArrayList<SubOrderInfo>();
			SubOrderInfo sub1 = new SubOrderInfo();
			sub1.setOV("1129102"); //子商户标识
			sub1.setGI("good1"); //子商品编号（长度单位：字节）
			sub1.setGN("子订单商品1"); //子商品名称（长度单位：字节）
			sub1.setOI("154529277181301"); //外部订单id
			sub1.setPA("1.23"); //子订单金额
			sub1.setRW("400"); //子商户服务费率（微信/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub1.setRZ("400"); //子商户服务费率（支付宝/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub1.setROW("400"); //子商户服务费率（e支付本行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub1.setROT("400"); //子商户服务费率（e支付它行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub1.setVN("备注"); //商户备注     
			sub1.setCOW("0");//定额佣金金额（元）（本行卡支付），商户档案中支付费率类型为固定费用时，该字段为必输项  
			sub1.setCOT("0");//定额佣金金额（元）（他行卡支付），商户档案中支付费率类型为固定费用时，该字段为必输项
			sub1.setCW("0");//定额佣金金额（元）（微信支付），商户档案中支付费率类型为固定费用时，该字段为必输项  
			sub1.setCZ("0");//定额佣金金额（元）（支付宝支付），商户档案中支付费率类型为固定费用时，该字段为必输项
			sub1.setIW("0");//是否即时解冻 1是 0否 商户为担保模式有效，默认为0
			sub1.setIW("1"); //是否即时解冻
			sub1.setCOW("0.01");//定额佣金金额(e支付本行卡)
			sub1.setCOT("0.01");//定额佣金金额(e支付它行卡)
			sub1.setJD("0");		//是否优惠
			subOrders.add(sub1);
			
			SubOrderInfo sub2 = new SubOrderInfo();
			sub2.setOV("1129103"); //子商户标识
			sub2.setGI("good2"); //子商品编号（长度单位：字节）
			sub2.setGN("子订单商品2"); //子商品名称（长度单位：字节）
			sub2.setOI("154529277181302"); //外部订单id
			sub2.setPA("2"); //子订单金额
			sub2.setRW("400"); //子商户服务费率（微信/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub2.setRZ("400"); //子商户服务费率（支付宝/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub2.setROW("400"); //子商户服务费率（e支付本行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub2.setROT("400"); //子商户服务费率（e支付它行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
			sub2.setVN("备注"); //商户备注     
			sub2.setCOW("0");//定额佣金金额（元）（本行卡支付），商户档案中支付费率类型为固定费用时，该字段为必输项  
			sub2.setCOT("0");//定额佣金金额（元）（他行卡支付），商户档案中支付费率类型为固定费用时，该字段为必输项
			sub2.setCW("0");//定额佣金金额（元）（微信支付），商户档案中支付费率类型为固定费用时，该字段为必输项  
			sub2.setCZ("0");//定额佣金金额（元）（支付宝支付），商户档案中支付费率类型为固定费用时，该字段为必输项
			sub2.setIW("0");//是否即时解冻 1是 0否 商户为担保模式有效，默认为0
			sub2.setIW("1"); //是否即时解冻
			sub2.setCOW("0.01");//定额佣金金额(e支付本行卡)
			sub2.setCOT("0.01");//定额佣金金额(e支付它行卡)
			sub2.setJD("0");		//是否优惠
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

