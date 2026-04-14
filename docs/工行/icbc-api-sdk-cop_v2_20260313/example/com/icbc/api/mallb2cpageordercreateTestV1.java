package com.icbc.api;

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
import com.icbc.api.request.mallb2cpageordercreateRequestV1;
import com.icbc.api.request.mallb2cpageordercreateRequestV1.Prods;
import com.icbc.api.request.mallb2cpageordercreateRequestV1.StoreVO;
import com.icbc.api.request.mallb2cpageordercreateRequestV1.ThirdPartySubmitRequestOrders;
import com.icbc.api.request.mallb2cpageordercreateRequestV1.mallb2CpageordercreateRequestV1Biz;

public class mallb2cpageordercreateTestV1 extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6698148563681809215L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String APP_ID = "appid";
			String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRVgmyC7tV4L4dC5B/Hx/EIJFQwdFN6aB8Ia3AxhGgC/hAKfMohR7qDk0ZusNw1Af0ov7APl3tpz0U25+4aLGvy03Bu/eZHFK1feFxakZJtpGER6wm2EAd83cLoq15diIaIzOo8zgFuTbQu2TlIc4tDtr5ZffEe18eTD8asirZ8rtgqYmHq51cDmvBrHoWKHewkrU0fVjFdjiPglu0xg4PT4SHNYe29vWEkDnKg07YKF+t5u3EOyL3GrCQEFjJx87cWUVmGNehRtyT7qjNyyNiFXx4yngL7/WFc1ieMnPVzzOeHlLcRl99q22diO/Lv+i+E8oTzlzg+A233kV+jZtjAgMBAAECggEAT9xjP37K1gnE7djo8bF8lxz+PM4D4Tg7g3pOK9uq27M4Gqnyf5WGwz5Qh47kzh70sCzQp3qdynVXTxrPzVlaKisg8xACohO6LxiYxOWM8fxsQa/QnRe/Q7YA+FfP0ypi2tH1FSVw51Q7Fr+iVXYaARBkqx6o40KXpeJn2peEjHXM5SlZE0JKdeShfzvFzJVBClYLhGU7Zmtka8kFmBNCM3CdReH0lwyuSxcxfn4xq3wUqI6+0PU1qKrBsbRrYd3RIDvCrQ1kFQHiwwGgQ6I557KgIM1N+d6KvQQSDGlw36Q2JBqcbGJDGOUsIbfQaGKVqayttUXffLApcvnnjeAt4QKBgQDJ1ZEjJzr9DFjdqwVAvbRZFpxjEsONhUxQZFteJFGloLMqKPKDMzsQyruWBTPx+f0y/da0oBGCq1Uy6Fzbfdonq9WiZHoUy2mSMmNJdfNqN/fTM6uSAnP+FYy+EQ2hTIBEX4EY51cAQhcYuC4i3QL66QXcitKmBFiyTheK8SMzTwKBgQC4Vu38yxqgbH3pggO+ebqu/0EI5PR07K/t3IT1T3JCVqYEDOLrFio9cdKd32pf9JAeH9+3tRhuzkj1FCMWxbTut6GAT1UbQEhSR5SedbXKGRukwL7pK7dc5W5UmQ/MwaFa1xsE2O+aaXFvQ1UnwJ+npyTLQdBCoUC1166ZDr1hrQKBgQCcWzhOdUu7UN9fCefN2ahnA58vGRApCESwWq51Zclid/WUCZiljwCI56UeePVe1X0TDZZQ41ftVHFyOJQG7Z4c8X9UiGYSyeoE+cf01s01UhpIoVSejgvxoBwh+fAHLs6HKT1HX6AEcS6XVcdX9sMiQXbq/RhmyCM2Lk0Ks/fC/QKBgQCfvfUOE5NHYbzbFWfZL/OuolPhOUb96/2oymgNjIMfKadD60bZIaQJCxHQ7UJpVK1u7YAY2j4KB4kBmCmwGjPoigjR/+1oaBRD+PO5PZgDQi7KXp0RwmhrgAZUGS+v6DvDVJR9ZuB7ltRN7rNkd2/jANzJpoaqtsI00mrjB/rlIQKBgCp3oFsq4n6i91B0m5zhq6wBSQEoHq0MFh2p1F+UJgRdwUcmaesTOTJqIMHm/ASLIgHPsPzZldw4qNTOFxYFbp5ARqeIIgS5Bq4xn0vN+2cKssI2lmAZ3ksg6Aw/OPvnxKCobt9LsaiJY0Fz5mcoUsmZfPb8v5+2Mgkt8sFTDu8D";
			UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
			
			mallb2cpageordercreateRequestV1 request = new mallb2cpageordercreateRequestV1();
			
			//request.setServiceUrl("http://ip:port/ui/cfjs/ui/pay/kf/pay/pc/V1");
//			request.setServiceUrl("http://ip:port/ui/mallc/createorder/action/neworder/V1");
			//根据测试环境和生产环境替换相应ip和端口
			request.setServiceUrl("http://ip:port/ui/mall/b2C/page/order/create/V1");
			mallb2CpageordercreateRequestV1Biz bizContent = new mallb2CpageordercreateRequestV1Biz();
			
			ThirdPartySubmitRequestOrders  thirdPartySubmitRequestOrders = new ThirdPartySubmitRequestOrders();
			
			StoreVO store = new StoreVO();
			store.setMercId("xxx");
			store.setStoreId("xxx");
			store.setStoreName("xxx");
			Prods prod = new Prods();
			prod.setProdName("xxx");
			prod.setSkuId("xxx");
			prod.setProdId("xxx");
			List<Prods> prods= new ArrayList<Prods>();
			prods.add(prod);
			store.setProds(prods);
			
			thirdPartySubmitRequestOrders.setAppId("xxx");
			
			thirdPartySubmitRequestOrders.setOrderProdType("xxx");
			
			thirdPartySubmitRequestOrders.setPlatformId("xxx");
			
			thirdPartySubmitRequestOrders.setOutUserId("xxx");
			
			thirdPartySubmitRequestOrders.setParentOrderPrice("xxx");
			
			thirdPartySubmitRequestOrders.setOrderPrice("xxx");
			
			thirdPartySubmitRequestOrders.setPayUse("xxx");
			
			thirdPartySubmitRequestOrders.setOrderInvalidTime("xxx");
			
			thirdPartySubmitRequestOrders.setPayUse("xxx");
			
			thirdPartySubmitRequestOrders.setStoreVO(store);
			
			thirdPartySubmitRequestOrders.setOrderMerchantMemo("xxx");
			
			thirdPartySubmitRequestOrders.setOrderPayAmout("xxx");
			
			thirdPartySubmitRequestOrders.setNoticeUrl("xxx");
			
			thirdPartySubmitRequestOrders.setPayBackUrl("xxx");
			
			thirdPartySubmitRequestOrders.setPayFailUrl("xxx");
			
			thirdPartySubmitRequestOrders.setThirdPartyOrderId("xxx");
			
			thirdPartySubmitRequestOrders.setThirdPartyParentOrderId("xxx");

			thirdPartySubmitRequestOrders.setFulfilPromotionId("xxx");

			thirdPartySubmitRequestOrders.setFulfilPrice("xxx");
			
			List<ThirdPartySubmitRequestOrders> list = new ArrayList<ThirdPartySubmitRequestOrders>();
			
			list.add(thirdPartySubmitRequestOrders);
			bizContent.setThirdPartySubmitRequestOrders(list);
			
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
