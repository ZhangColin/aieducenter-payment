package com.icbc.api;

import com.icbc.api.request.CardbusinessFovaUnionpayConsumeRequestV1;
import com.icbc.api.request.CardbusinessFovaUnionpayConsumeRequestV1.CardbusinessFovaUnionpayConsumeRequestV1Biz;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * @ClassName CardbusinessFovaUnionpayConsumeV1Test.java
 * @Description 银联3DS下单接口
 **/
public class CardbusinessFovaUnionpayConsumeV1Test {


    // 1、appid
    protected static final String APP_ID = "xxx";

    // 2、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaUnionpayConsumeV1Test.class.getName());

	public void check_sign_rsa() throws IcbcApiException {
		UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		CardbusinessFovaUnionpayConsumeRequestV1 request = new CardbusinessFovaUnionpayConsumeRequestV1();
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/ui/cardbusiness/fova/unionpay/consume/V1");
		CardbusinessFovaUnionpayConsumeRequestV1Biz bizContent = new CardbusinessFovaUnionpayConsumeRequestV1Biz();
		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setClient_type("0");
		bizContent.setMer_order_id("abc123456789");
		bizContent.setTrx_currtype("CNY");
		bizContent.setTrx_amount("1");
		bizContent.setMer_no("020001021189");
		bizContent.setTrx_date("20231230");
		bizContent.setTrx_time("140832");
		bizContent.setReturn_url("http://www.test.com/return_url.html");
    bizContent.setQuit_url("xxx");
		bizContent.setNotify_url("http://www.test.com/notify");
		bizContent.setNotify_type("1");
		bizContent.setExpireTime("120");
		bizContent.setAttach("小卖铺");
		bizContent.setLanguage("");
		System.out.println(client.buildPostForm(request));

	}
}
