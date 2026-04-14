package com.icbc.api;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.request.CustacqOrderInstallpayRequestV1;
import com.icbc.api.request.CustacqOrderInstallpayRequestV1.CustacqOrderInstallpayRequestV1Biz;

/**
 * 预获客接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class CustacqOrderInstallpayTestV1 {

	//1、appid
	protected static final String APP_ID = "10000000000000002156";

	//2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
	
	//3、AES密钥
	protected static final String MY_AES_KEY = "TKhSiK9Blwr6+aCq+O0MFg==";
	
	public void order(HttpServletRequest request1, HttpServletResponse response) throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和AES密钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, MY_AES_KEY);
		
		CustacqOrderInstallpayRequestV1 request = new CustacqOrderInstallpayRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/custacq/order/installpay/V1");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CustacqOrderInstallpayRequestV1Biz bizContent = new CustacqOrderInstallpayRequestV1Biz();
		bizContent.setMerId("020002040095");
		bizContent.setStoreCode("02000015087");
		bizContent.setCustId("9hvyM+yzA9Mbx+PrvkRNGUxFF9nT2IWJ");
		bizContent.setOutTradeNo("ZHL777O15002039");
		bizContent.setOrderAmt("7370");
		bizContent.setTradeDate("20180711");
		bizContent.setTradeTime("160346");
		bizContent.setAttach("接口测试20170601");// 该字段非必输项
		bizContent.setMerName("测试商户001");
		bizContent.setInstallmentTimes("3");
		bizContent.setPromoCode("TJ00013kjgr94oi40000");// 该字段非必输项
		bizContent.setCorpCode("HZDW000020461");// 该字段非必输项
		
		request.setBizContent(bizContent);

		System.out.println(client.buildPostForm(request,"msgId"));
		
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8 \">");
			out.write("</head>");
			out.write("<body>");
			out.write(client.buildPostForm(request,"msgId"));//6、msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			out.write("</body>");
			out.write("</html>");
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	
}

