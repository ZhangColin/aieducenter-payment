package com.icbc.api;

import com.icbc.api.request.CardbusinessFovaForeignpayConsumeRequestV1;
import com.icbc.api.request.CardbusinessFovaForeignpayConsumeRequestV1.CardbusinessFovaForeignpayConsumeRequestV1Biz;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * @ClassName ForeignpayOrderCreateV1Test.java
 * @Description 聚合下单接口
 * @author: kfzx-herq
 * @create: 2022-09-05 10:42
 **/
public class CardbusinessFovaForeignpayConsumeV1Test {


    // 1、appid
    protected static final String APP_ID = "xxx";

    // 2、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaForeignpayConsumeV1Test.class.getName());

	public void check_sign_rsa() throws IcbcApiException {
		UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		CardbusinessFovaForeignpayConsumeRequestV1 request = new CardbusinessFovaForeignpayConsumeRequestV1();
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/ui/cardbusiness/fova/foreignpay/consume/V1");
		CardbusinessFovaForeignpayConsumeRequestV1Biz bizContent = new CardbusinessFovaForeignpayConsumeRequestV1Biz();
		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setClientType("0");//0-PC浏览器；1-移动端浏览器
		bizContent.setTrxZoneno("00119");//例如澳门：00119 万象：06005 请与业务经理联系确认
		bizContent.setMerNo("011999019999");//例如：011999019999 12位或15位商户编号
		bizContent.setTrxCurrtype("MOP");//ISO标准币种：CNY-人民币；MOP-澳门币；USD-美元
		bizContent.setTrxAmount("10000");//不带小数点格式，单位为币种对应最小单位
		bizContent.setMerOrderId("OPay20220905183702");//商户系统的订单号
		bizContent.setTrxDate("20220914");//YYYYMMDD
		bizContent.setTrxTime("132055");//HHMMSS
		bizContent.setNotifyUrl("");//商户系统接收交易结果地址，端口必须为443或80，地址为空银行不主动发起通知
		bizContent.setNotifyType("2");//0-不通知；1-成功交易通知；2-成功/失败交易通知；
		bizContent.setReturnUrl("");//
		bizContent.setQuitUrl("http://www.XXX.XXX.XXX/page.html");//用于页面上显示回退按钮；PC页面用于回退到商户的页面
		bizContent.setExpireTime("");//建议大于60秒
		bizContent.setAttach("");//在查询API和交易结果通知时原样返回，该字段主要用于商户携带订单的自定义数据
		bizContent.setLanguage("Zh_CN");//默认为简体中文版本 Zh_CN:简体中文；Zh_TW:繁体中文；en_US:英文版；pt_BR:葡萄牙语；lo_LT：老挝语
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));

	}
}
