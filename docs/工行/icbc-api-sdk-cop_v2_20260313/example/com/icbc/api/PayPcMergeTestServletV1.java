package com.icbc.api.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.PcMergePayRequestV1;
import com.icbc.api.request.PcMergePayRequestV1.PcMergePayRequestV1Biz;
import com.icbc.api.request.PcMergePayRequestV1.SubOrderInfo;

public class PayPcMergeTestServletV1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		try {
			String APP_ID = "10000000000000004434";
			String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDN+/pxt5PKhjYAPc+G/rdx+XOUBEFtY7Fy6it3AunbV7Qeq36QxljKV/12dqNc4XQM/NLypGfFXuuvkP3hhvfS8AO4r1FCRbPjkEZqUe4RaUBnKbEaYl4Dq8G7hycmQWqmuBzdT5ANmBfu6AkHSirkEqCr0ghH3xiPYKgbPNWgI+PbSwiwOvx7UcuGHawxPRLEVerRlGy8o5FwPw4DDJnoTgkCqueTTQ9bTTAGNnl96PbTU3fHiPVdd0Q0eiK7cuSShjq8o0aPUzhCYshJYIKoL6AQksPSkSZFsq0A2O9lH86xbWXGj5LzjqVblp/Jd9MCMRA4y64YRGuGmFqvQvlTAgMBAAECggEBAJVeeAomeUGPeXFLYUYfR5+aqLlZLKjKdk9CiCpRLowkWTU0Xx9U9eYlUGOrPGOKCcGxRLc3iRiS8I3rsm7slKGd8tTmLSLFsOEAKk/HAcAWduFYVlkvo8U9s8rtAvK21UP4ltXVcDyrYg+Yq5NhsSCacXCnaeHUYdb23HCD+NWaY9S8WvT3kUTRpxfq8JzBvwst+yyJUOzVAXOMjtXD+YB7QHJCvyQ3m2bqHI15X2PyvbTWNxFfpxXozEfqT/Ec/DJT4gTyP+sz7YNzzI5UjyfKofUR8rcZ7Fqmdn0Ap11f4jvLM2FNTPqdeiiUYS9UEa3sfKPFXf+DAX0Ig+j8yeECgYEA7T9vWpvhiQUVUxSPyP4alyfs+ApBsbLe3fsrSJx7cRG2C3Tk0Ga5rSu9tfqCjd9AjrmLLbbScVdCl9hDsUDTujW9KOkRkgPx8BJHbzh50ni/GYXoJjKlYz7a6xccfhapdcgfdOm+yVn2IMdyTWX8779aJg0/ttNp1NqhhxtMF7UCgYEA3kPyAR1SJ/WFK6nCao9HTsnITX/e4tLru1yyXaOowefez3poSfHH2kDw1DjgY4WQzcMDVCFEmB6C65NF2aPsH9Rkdvw0kw11ynbnOcSu8CgPk342en9Wo05zK38KOa7IDkCw/zXNEbJckee7CJz7dAvXAjcVyuYgt/uy57zmYecCgYEAm4DUsAM0VOcsDwSPKiLDswfMXtf6L0saoU0cFb/beUUumVvJDoAGe+cLtde48ty+J86d1xzZ2shjMbviwq/HF2pZzhLWHWrQjZatk8zkrHvQZh5LB55yoRZ1z1map1qbQEQnui3ri3aXyOxxkyGiJFK79+z/Mgpod8fF0BSoT3ECgYEAmZF2hwCcQBGqRmXPd1KxoSVX6Pwdori6uUSt1Hb8ZaVwVAguQFD2lbzi6K63kuu5BgHkrd8ihrwmFuA1lc9lmQRlubQ+GxBkv1PyJP7ZJAN5551/xJlw4fqVJotrJDm37ekSofKpUr3aqdDjcGsObbq0fspSm1Lvbk9Q6D6jKycCgYEA5DV937sWji2NBQI6bBB1x0848ng1S+q1OFQIWsM+jwUJpH/yxTx+a5sS51m4GNdFxNqUyGBxFslf+319b4b5XrLuHFvS2ZE1Xo85qjvjlIBu84DBjTjT5I9lazlutpNzzH+hADbUazdxbePVa/tOyP2zBCpDlGpuhd4cWmRPjtw=";
			UiIcbcClient client =new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8);
			
			PcMergePayRequestV1 request = new PcMergePayRequestV1();
			
			request.setServiceUrl("http://ip:port/ui/jft/ui/pay/pay/pcMegerPay/V1");
			PcMergePayRequestV1Biz bizContent = new PcMergePayRequestV1Biz();
			
			bizContent.setPayMethod("01");
			bizContent.setAppId("123455");
			bizContent.setOutUserId("sh2");  
			bizContent.setNotifyUrl("com");  
			bizContent.setJumpUrl("com");    
			bizContent.setGoodsId("3212");    
			bizContent.setGoodsName("申威sw商品");  
			bizContent.setOutOrderId("2018021315091823"); 
			bizContent.setMac("a3-b4-c5");        
			bizContent.setTrxIp("ip:port");
			bizContent.setTrxIpCity("北京");  
			bizContent.setTrxChannel("02"); 
			bizContent.setPayAmount("0.02");  
			bizContent.setVarNote("3212");    
			List<SubOrderInfo> suborders = new ArrayList<SubOrderInfo>();
			SubOrderInfo suborder = new SubOrderInfo();
			suborder.setGoodsId("3212");//商品编号
			suborder.setGoodsName("xx商品");//商品名称
			suborder.setSubMerRateOwn("600");//子商户服务费率（e支付本行卡/十万分比）
			suborder.setSubMerRateOther("600");//子商户服务费率（e支付它行卡/十万分比） 
			suborder.setOutVendorId("11111");//子商户标识号
			suborder.setPayAmount("0.02");//支付金额(元)
			suborder.setVarNote("备注");//商户备注
			suborder.setOutOrderId("2018040123909");//平台对接方订单ID
			suborder.setIW("1");
			suborder.setCOT("0.01");
			suborder.setCOW("0.01");
			suborder.setCW("0.01");
			suborder.setCZ("0.01");
			suborders.add(suborder);
			bizContent.setSuborders(suborders);
			bizContent.setOutBusinessCode("10000000000000000001");
			
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


