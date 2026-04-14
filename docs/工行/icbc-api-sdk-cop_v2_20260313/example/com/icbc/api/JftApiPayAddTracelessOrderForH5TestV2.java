package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.JftApiPayAddTracelessOrderForH5RequestV2;
import com.icbc.api.request.JftApiPayAddTracelessOrderForH5RequestV2.JftApiPayAddTracelessOrderForH5V2Biz;
import com.icbc.api.request.JftApiPayAddTracelessOrderForH5RequestV2.SubOrderInfo;
import com.icbc.api.response.JftApiPayAddTracelessOrderForH5ResponseV2;

public class JftApiPayAddTracelessOrderForH5TestV2 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000205510";
											
	
	public static void main(String[] args) {


		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");

		JftApiPayAddTracelessOrderForH5RequestV2 request = new JftApiPayAddTracelessOrderForH5RequestV2();
		request.setServiceUrl("http://ip:port/api/jft/api/pay/add/h5/traceless/order/V2");
		JftApiPayAddTracelessOrderForH5V2Biz bizContent = new JftApiPayAddTracelessOrderForH5V2Biz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setOutVendorId("1224536");//子商户标识：提交支付请求的子商户编号 
		bizContent.setOutUserId("M201801112326");//用户标识
		bizContent.setNotifyUrl("http://xx.com");//商户通知URL：商户接受支付成功通知消息URL
		bizContent.setOutOrderId("20190102_3");//平台对接方ID：平台商户订单ID，确保唯一
		bizContent.setGoodsId("3212");//商品编号（长度单位：字节）
		bizContent.setGoodsName("测试商品");//商品名称（长度单位：字节）
		bizContent.setMac("a3-b4-c5");//手机Mac:支付提交用户手机Mac地址
		bizContent.setTrxIp("ip:port");//交易IP：支付提交用户IP地址
		bizContent.setTrxIpCity("北京");//交易IP所在地：支付提交用户IP所在地
		bizContent.setTrxChannel("03");//交易渠道:固定取03
		bizContent.setPayAmount("1.01");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
		bizContent.setSubMerRateWx("400");//子商户服务费率（微信）,商户档案中支付费率类型为区间支付费率，则该字段为必输项 
		bizContent.setSubMerRateZfb("400");//子商户服务费率（支付宝）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
		bizContent.setTpAppId("wx0f6flal9af93dbe6");//第三方应用ID；商户在微信公众号内或微信小程序内接入时必送，上送微信分配的公众账号ID；商户通过支付宝生活号接入时不送。
		bizContent.setTpOpenId("odg9h43-kX46sAmzGsk3tAvMPbU4");//第三方用户标识；商户在微信公众号内或微信小程序内接入时必送，上送用户在商户appid下的唯一标识；商户通过支付宝生活号接入时不送。
		bizContent.setUnionId("");//第三方用户标识；商户在支付宝生活号接入时必送，上送用户的唯一标识;商户在微信公众号内或微信小程序内选送，上送用户唯一标识。
		bizContent.setPayMode("01");//支付模式：微信小程序: 01，支付宝生活号: 02
		bizContent.setOutBusinessCode("10000000000000000001");//外部业务编码     
		bizContent.setFixedCommissionWx("0");//定额佣金金额（元）（微信支付），商户档案中支付费率类型为固定费用时，该字段为必输项  
		bizContent.setFixedCommissionZfb("0");//定额佣金金额（元）（支付宝支付），商户档案中支付费率类型为固定费用时，该字段为必输项
		bizContent.setAutoConfirm("0");//是否即时解冻 1是 0否 商户为担保模式有效，默认为0
		bizContent.setJDA("1.00");	//优惠金额
		bizContent.setJD("1");		//是否优惠
		bizContent.setJDT("1");		//聚富通优惠类型(1立减  2满减  3优惠券)
		
		List<SubOrderInfo> subOrders = new ArrayList<SubOrderInfo>();
		SubOrderInfo sub1 = new SubOrderInfo();
		sub1.setOV("1129102"); //子商户标识
		sub1.setGI("good1"); //子商品编号（长度单位：字节）
		sub1.setGN("子订单商品1"); //子商品名称（长度单位：字节）
		sub1.setOI("154529277181301"); //外部订单id
		sub1.setPA("1.23"); //子订单金额
		sub1.setRW("400"); //子商户服务费率（微信/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
		sub1.setRZ("400"); //子商户服务费率（支付宝/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
		sub1.setVN("备注"); //商户备注     
		sub1.setCW("0");//定额佣金金额（元）（微信支付），商户档案中支付费率类型为固定费用时，该字段为必输项  
		sub1.setCZ("0");//定额佣金金额（元）（支付宝支付），商户档案中支付费率类型为固定费用时，该字段为必输项
		sub1.setIW("0");//是否即时解冻 1是 0否 商户为担保模式有效，默认为0
		sub1.setJDA("1.00");
		sub1.setJD("1");
		sub1.setJDT("1");
		subOrders.add(sub1);
		
		SubOrderInfo sub2 = new SubOrderInfo();
		sub2.setOV("1129103"); //子商户标识
		sub2.setGI("good2"); //子商品编号（长度单位：字节）
		sub2.setGN("子订单商品2"); //子商品名称（长度单位：字节）
		sub2.setOI("154529277181302"); //外部订单id
		sub2.setPA("2"); //子订单金额
		sub2.setRW("400"); //子商户服务费率（微信/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
		sub2.setRZ("400"); //子商户服务费率（支付宝/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
		sub2.setVN("备注"); //商户备注   
		sub2.setCW("0");//定额佣金金额（元）（微信支付），商户档案中支付费率类型为固定费用时，该字段为必输项  
		sub2.setCZ("0");//定额佣金金额（元）（支付宝支付），商户档案中支付费率类型为固定费用时，该字段为必输项
		sub2.setIW("0");//是否即时解冻 1是 0否 商户为担保模式有效，默认为0
		sub2.setJDA("1.00");
		sub2.setJD("1");
		sub2.setJDT("1");
		subOrders.add(sub2);
		
		bizContent.setSubOrders(subOrders);
		
		request.setBizContent(bizContent);
		

		JftApiPayAddTracelessOrderForH5ResponseV2 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}	
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

