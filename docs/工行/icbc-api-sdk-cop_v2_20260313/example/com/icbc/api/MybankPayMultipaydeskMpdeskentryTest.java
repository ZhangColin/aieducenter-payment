package com.icbc.api;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

import com.alibaba.fastjson.JSON;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.MybankPayMultipaydeskMpdeskentryRequestV1;
import com.icbc.api.request.MybankPayMultipaydeskMpdeskentryRequestV1.MybankPayMultipaydeskMpdeskentryRequestV1Biz;

public class MybankPayMultipaydeskMpdeskentryTest {
	protected static final String MY_PRIVATE_KEY = "Al84Gc7IXK8AfR1qLPdW96blEjRN8pVdJ9crarCngQPIkIvx1abQXlx5PCmgs/7Bqu3vJpdRlK2EnFJ5FMN6p9Ga6r1ILcPK4ldVahY4J02/ZQjw7poXMZ+NwZYdXpVyrqfvMLz/JbDPih/gU9sN39hdJrYAeqpXmmtvl3657dDkz45i7UBwKLZQ/+couNOcXgrGvuqbVGRkK0k1lfmj9U0gbbER+7VS47sukjXxNLx/V+8W1I9Xw8hZberSYx8RAaFDb68ZNcKdMRgoM5g7AgEmMWxkemN46f/0xjZuDMdH00KEfTZDzq6EnyZG++92wU9n8BPxqPxtK0M5h7OXqRrq3+H9JBoYlRVB1SHyIcIo8RduvZdleVhRkFsAuKN4m9lpomFxMNSz4HRuOEY0RkRaInl0XColI10a81cxXG9Nngmu2o6flGCe6VZUDiNSp/HzD+WroaIMsiN3kJl0yg5dPmO6KyTnc4D27WxRkOfCfasiLbaXCfc7rldTmplZnkc0RbNMijG5ToQAp8bZbq5JpYwDl9+c33tYhU89CZrTujFEeTnL4kLBWv1F4n3/TEl83M5zyAxLLzy3MymdQNvEdk+D5wlhbM3dJdLlqusyVnaswIJ0SDXWca+24s0SvUiQcNOwbkeEYbo29FeWYoFNpKfTARx7vE+rqvfx6MwEx9vAlqReD3YdE0vSHFWU1cZ0dULyHKvZ5Hy7/dY2VQCcwKWDBKarMdPafUCDLfXjJn2gq0WHVROXmB5N9WIq9bBsmOJ6TrvjV6qTBJPbdfnIEOtETezC5qCKZKhcFrEw";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----DDzCCAfegAwIBAgIKbaHKEE0tAABStDANBgkqhkiG9w0BAQUFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xNjA5MTMwODQ2MDdaFw0yMjAzMDEwODQ2MDdaMEUxGTAXBgNVBAMTEGx1aHV6eTAwMS55LjAyMDAxDTALBgNVBAsTBDAyMDAxGTAXBgNVBAoTEGNvcmJhbms0My5jb20uY24wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAIYomiojXul1Ri/9CnknLHZ1txuq1o0vF/DpeiRspzldFpB0RKxTqfcC1gs82nN76vYo50xIgtP0tLJ1/rDqc/t/AMpnCpDPJXKymOq8j9iESfrgEpypbQ/6Hhozq1tFxIi+/TARf3TlZB6OnTMu5EEaYXOKaDfEv90PkYpq2OMzAgMBAAGjgZIwgY8wHwYDVR0jBBgwFoAUnoARo7tXznf98Sdn/uThfyLyUfMwTQYDVR0fBEYwRDBCoECgPqQ8MDoxDjAMBgNVBAMTBWNybDIyMQ0wCwYDVQQLEwRjY3JsMRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBSCIdS+x+AlXsCCvB36nJmsvuVt4jANBgkqhkiG9w0BAQUFAAOCAQEACBF+MaxsHRH52Im+amFM8xBrvO57xN8rQ94nyozd25gaGh3CgtgxY1SgIGwk05rmNyeDi0QSByMFZ7aFhgxCxEeR1i0VT/gNzgoyXYmocBsFnVKo+ihHGE7OdXz9DrGCOhk+EAqnFBOrnalNSaZs7bklpnc9AzuLTLqRZxkAiQpPj+UNOot/y6/lb/rJsjC43idECycAGc3M6TmZQH5wNeccxXtuv3DbLTme9mbh+8eEWYtwBgqQCDwHxDPoHcBSzRaCb/N6u3dEBLDFs1I/SIyyUSUwUKneS3iUYIyE1hPZASQSZAOIUvUvG3BRr/mwrpePcTpPhZubSHlCYAG68A==";

	protected static final String APP_ID = "10000000000000004447";
	
	protected static final String PASSWORD = "12345678";
	

	public static void main(String args[]) throws IcbcApiException {
		// 构造client对象
		String castr = APIGW_PUBLIC_KEY;
		// 去除签名数据及证书数据中的空格 added for Safari
		Pattern p = Pattern.compile("\\s*|\t");
		Matcher m2 = p.matcher(castr);
		castr = m2.replaceAll("");
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		// 设置请求对象request
		MybankPayMultipaydeskMpdeskentryRequestV1 request1 = new MybankPayMultipaydeskMpdeskentryRequestV1();
		// 设置请求路径
		request1.setServiceUrl("https://ip:port/ui/paymentproof/digitalui/pay/V1");

		MybankPayMultipaydeskMpdeskentryRequestV1Biz bizContent = new MybankPayMultipaydeskMpdeskentryRequestV1Biz();
		bizContent.setApiName("MULTIPAY");
		bizContent.setApiVersion("001.001.001.001");
		bizContent.setMerchantCode("1800137");
		bizContent.setMerchantName("测试商户");
		bizContent.setJoinProd("004");
		bizContent.setBusyType("004");
		bizContent.setOperType("002");
		bizContent.setTranSerialno("2345");
		bizContent.setTranOrderno("123456");
		bizContent.setGoodsName("");
		bizContent.setGoodsQuantity("");
	    bizContent.setOrderAmt("100");
		bizContent.setPayAmt("100");
		bizContent.setPayCur("001");
		bizContent.setTranTime("20210925084322");
		bizContent.setShopRemark("商城备注");
		bizContent.setRecvName("");
		bizContent.setRecvMemno("");
		bizContent.setPayMemno("2018092102");
		bizContent.setRecvAccount("");
		bizContent.setNotifyUrl("http://IP:PORT/NotifyServlet");
	    bizContent.setMergeFlag("1");
	    bizContent.setOrderCount("2");
	   
	    //设置合并支付订单信息
	    LinkedList<HashMap<String,Object>> orderlist=new LinkedList<HashMap<String,Object>>();
		HashMap<String,Object> ordermap1 =new LinkedHashMap<String,Object>();
		HashMap<String,Object> ordermap2 =new LinkedHashMap<String,Object>();
		    

		ordermap1.put("list_orderamt", "50");
		ordermap1.put("list_payamt", "50");
		ordermap1.put("list_paycur", "001");
		ordermap1.put("list_recvname", "测试收款商户1");
		ordermap1.put("list_recvmemno", "00001");
		ordermap1.put("list_recvaccount", "");
	    ordermap1.put("list_goodsname", "");
	    ordermap1.put("list_goodsquantity", "");


		ordermap2.put("list_orderamt", "10000");
		ordermap2.put("list_payamt", "10000");
		ordermap2.put("list_paycur", "001");
		ordermap2.put("list_recvname", "测试收款商户2");
		ordermap2.put("list_recvmemno", "00002");
		ordermap2.put("list_recvaccount", "");
	    ordermap2.put("list_goodsname", "");
	    ordermap2.put("list_goodsquantity", "");
			
		orderlist.add(ordermap1);
		orderlist.add(ordermap2);	

	    bizContent.setOrderList(JSON.toJSONString(orderlist));
		// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
		System.out.println(client.buildPostForm(request1));
		// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
	}	
	

}
