/**
 * TODO
 *
 * @author kfzx-weiwf
 * 
 */
package com.icbc.api;

import java.net.URLEncoder;
import java.net.URLDecoder;

import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.EaccountManageRequestV1;
import com.icbc.api.request.EaccountManageRequestV1.EaccountManageRequestBizV1;

/**
 * TODO
 *
 *
 * @author kfzx-weiwf
 */
public class EaccountManageTestV1 {

	protected static String APP_ID = "";//appid
	protected static String MY_PRIVATE_KEY = "密钥";
	private static String AES_KEY = ""; //AES_KEY;

	public static void main(String[] args) throws IcbcApiException {

		// public UiIcbcClient(String appId, String signType, String privateKey,
		// String charset, String encryptType, String encryptKey)
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA,
				MY_PRIVATE_KEY,
				IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY);
		EaccountManageRequestV1 request = new EaccountManageRequestV1();
		request.setServiceUrl("https://ip:port/ui/eaccount/manage/V1");///
		EaccountManageRequestBizV1 bizContent = new EaccountManageRequestBizV1();
		bizContent.setMobileNo("");// 选输，手机号
		bizContent.setIdcode("");// 选输，身份证号
		bizContent.setName(URLEncoder.encode("姓名"));// 选输，用户姓名
		bizContent.setUserId("");// 必输，需控制不能为空，用户唯一标识,送身份证号
		bizContent.setBacAddr(URLEncoder.encode(""));// 选输，回调地址
		bizContent.setEpayflag("0");// 选输，工银e支付标志，0-短信、1-静态密码，集成客户端SDK的方式能送0-短信或1-静态密码，非APP方式只能送0-短信，不送默认为0-短信

		bizContent.setCorpAppid(APP_ID);// 外公司合作方送APP_ID的值，行内应用如e支付等送外公司合作方的APPID
		long systime = System.currentTimeMillis(); // 现时间戳
		String orderTimeStamp = new Long(systime).toString();

		System.out.println("orderTimeStamp:" + orderTimeStamp);
		bizContent.setOrderTimeStamp(orderTimeStamp);// 生成请求的时间,必输

		bizContent.setZoneno("00200");// 地区号号，选输，5位数字，上送值联系对接分行提供
		bizContent.setBrno("00260");// 网点号，选输，5位数字，上送值联系对接分行提供
		bizContent.setDeviceId("123456789012");
		bizContent.setDevicephoneNumber("13412345678");
		bizContent.setLbsInfo("+37.12/-121.23");
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));// 实际调用时的相关返回结果及异常处理，请自行添加
	}
}
