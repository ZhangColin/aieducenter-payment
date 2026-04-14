package com.icbc.api;

import com.icbc.api.request.ThirdpartyOrderPayRequestV6;
import com.icbc.api.request.ThirdpartyOrderPayRequestV6.ThirdpartyOrderPayRequestV6Biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * 第三方买单支付接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-5提示进行数据替换
 */
public class ThirdpartyOrderPayTestV6 {

	static Logger LOGGER = Logger.getLogger(ThirdpartyOrderPayTestV6.class.getName());

	//1、appid
	protected static final String APP_ID = "10000000000000002156";

	//2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
	
	//3、AES密钥
	protected static final String MY_AES_KEY = "TKhSiK9Blwr6+aCq+O0MFg==";
	
	public void order(HttpServletRequest request1, HttpServletResponse response) throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和AES密钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, MY_AES_KEY);
		
		ThirdpartyOrderPayRequestV6 request = new ThirdpartyOrderPayRequestV6();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/ui/thirdparty/order/pay/V6");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		ThirdpartyOrderPayRequestV6Biz bizContent = new ThirdpartyOrderPayRequestV6Biz();
		bizContent.setMerId("020002040095");
		bizContent.setStoreCode("02000015087");
		bizContent.setCustId("9hvyM+yzA9Mbx+PrvkRNGUxFF9nT2IWJ");
		bizContent.setOutTradeNo("ZHL777O15002039");
		bizContent.setOrderAmt("7370");
		bizContent.setTradeDate("20170112");
		bizContent.setTradeTime("160346");
		bizContent.setAttach("接口测试20170601");// 该字段非必输项
		bizContent.setPayExpire("1200");
		bizContent.setNotifyUrl("ip:port");// 该字段非必输项
		bizContent.setNotifyFlag("1");
		bizContent.setAutoSubmitFlag("0");
		bizContent.setGoodsName("商品001");// 该字段非必输项
		bizContent.setAllPointsFlag("0");// 该字段非必输项
		bizContent.setGoodPoints("");// 该字段非必输项
		bizContent.setInstallmentFlag("0");// 该字段非必输项
		bizContent.setInstallmentTimes("");// 该字段非必输项
		bizContent.setCardType("111");// 该字段非必输项
		bizContent.setIntgMerId("020053010901");// 该字段非必输项
		bizContent.setOnlinePrtclNo("0200089901470201");// 该字段非必输项
		bizContent.setPayMode("100");// 该字段非必输项
		bizContent.setSceneCode("2");// 该字段非必输项

		request.setBizContent(bizContent);

		LOGGER.info(client.buildPostForm(request,"msgId"));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8 \">");
			out.write("</head>");
			out.write("<body>");
			out.write(client.buildPostForm(request,"msgId"));
			out.write("</body>");
			out.write("</html>");
		} catch (IcbcApiException e) {
			LOGGER.info("IcbcApiException:" + e);
		} finally {
			out.flush();
			out.close();
		}
	}

	
}

