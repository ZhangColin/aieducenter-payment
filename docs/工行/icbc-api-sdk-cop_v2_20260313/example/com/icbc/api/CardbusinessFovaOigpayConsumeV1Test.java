package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaOigpayConsumeRequestV1;
import com.icbc.api.request.CardbusinessFovaOigpayConsumeRequestV1.CardbusinessFovaOigpayConsumeRequestV1Biz;
import com.icbc.api.response.CardbusinessFovaOigpayConsumeResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class CardbusinessFovaOigpayConsumeV1Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaOigpayConsumeV1Test.class.getName());


	public void test_cop() throws IcbcApiException {
		//given 1
		UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		CardbusinessFovaOigpayConsumeRequestV1 request = new CardbusinessFovaOigpayConsumeRequestV1();
		//when 2
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/cardbusiness/fova/oigpay/consume/V1");
		CardbusinessFovaOigpayConsumeRequestV1Biz bizContent = new CardbusinessFovaOigpayConsumeRequestV1Biz();
		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setAppid("xxx");//工行网关APPID
		bizContent.setTrxZoneno("00119");//例如澳门：00119 万象：06005 请与业务经理联系确认
		bizContent.setMerNo("011999019999");//例如：011999019999 12位或15位商户编号
		bizContent.setTrxCurrtype("MOP");//ISO标准币种：CNY-人民币；MOP-澳门币；USD-美元；LAK-老挝基普
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
		bizContent.setChannel("xxx");//WechatApp -微信APP支付
		Map<String, Object> channelExt = new HashMap<>();
		channelExt.put("body", "1 King Size Bed");
		channelExt.put("wx_appid", "123456789");
		bizContent.setChannelExt(channelExt);//支付渠道信息
		bizContent.setMerchantTid("00000001");//商户设备ID
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));

		//then 3
		CardbusinessFovaOigpayConsumeResponseV1 response;
		client.setHeaderParam("X‐Fova‐Region", "00119"); //交易地区号 澳门送00119，工银亚洲送00110
		try {
			boolean testFlag = true;
			response = client.execute(request, System.currentTimeMillis()+"");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			logger.info("response:" + response.getReturnCode());
			if (0 == response.getReturnCode()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				testFlag = false;
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("ReturnMsg:" + response.getReturnMsg());
			}
//			Assert.assertEquals(true, testFlag);
			assertTrue(testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}
