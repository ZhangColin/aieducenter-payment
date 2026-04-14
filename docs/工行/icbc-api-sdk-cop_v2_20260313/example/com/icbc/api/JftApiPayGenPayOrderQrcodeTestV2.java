package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.JftApiPayGenPayOrderQrcodeRequestV2;
import com.icbc.api.request.JftApiPayGenPayOrderQrcodeRequestV2.JftApiPayGenPayOrderQrcodeRequestV2Biz;
import com.icbc.api.request.JftApiPayGenPayOrderQrcodeRequestV2.SubOrderInfo;
import com.icbc.api.response.JftApiPayGenPayOrderQrcodeResponseV2;

public class JftApiPayGenPayOrderQrcodeTestV2 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000092502";
											
	
	public static void main(String[] args) {

		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", 
				"json",APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");
								
		JftApiPayGenPayOrderQrcodeRequestV2 request = new JftApiPayGenPayOrderQrcodeRequestV2();
		request.setServiceUrl("http://ip:port/api/jft/api/pay/gen/pay/order/qrcode/V2");
		JftApiPayGenPayOrderQrcodeRequestV2Biz bizContent = new JftApiPayGenPayOrderQrcodeRequestV2Biz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setOutVendorId("1224536");//子商户标识：提交支付请求的子商户编号 
		bizContent.setOutUserId("M201801112326");//用户标识
		bizContent.setNotifyUrl("http://xx.com");//商户通知URL：商户接受支付成功通知消息URL
		bizContent.setOutOrderId("20190102_3");//平台对接方ID：平台商户订单ID，确保唯一
		bizContent.setGoodsId("3212");//商品编号（长度单位：字节）
		bizContent.setGoodsName("xx商品");//商品名称（长度单位：字节）
		bizContent.setMac("a3-b4-c5");//手机Mac:支付提交用户手机Mac地址
		bizContent.setTrxIp("ip:port");//交易IP：支付提交用户IP地址
		bizContent.setTrxIpCity("北京");//交易IP所在地：支付提交用户IP所在地
		bizContent.setTrxChannel("03");//交易渠道:固定取06
		bizContent.setPayAmount("0.02");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
		bizContent.setSubMerRateWx("400");//子商户服务费率（微信）,商户档案中支付费率类型为区间支付费率，则该字段为必输项 
		bizContent.setSubMerRateZfb("400");//子商户服务费率（支付宝）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
		bizContent.setSubMerRateOwn("400");//子商户服务费率（e支付本行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
		bizContent.setSubMerRateOther("400");//子商户服务费率（e支付它行卡/十万分比）,商户档案中支付费率类型为区间支付费率，则该字段为必输项
		bizContent.setOutBusinessCode("101010"); //外系统业务编号
		bizContent.setPayExpire("1000");//二维码有效期，单位：秒，必须小于30分钟
		bizContent.setPayType("02"); //合并支付02,单订单01
		bizContent.setVersion("1.0.0"); //版本号
		//子订单信息
		List<SubOrderInfo> suborders = new ArrayList<SubOrderInfo>();
		SubOrderInfo sub1 = new SubOrderInfo();
		sub1.setOI("154529277181301");//外系统订单id
		sub1.setOV("1129102");//子商户标识
		sub1.setGI("3212");//商品id
		sub1.setGN("xx商品");//商品名称
		sub1.setPA("0.01");//交易金额
		sub1.setVN("备注1");//备注信息
		sub1.setRW("400");//子商户微信费率
		sub1.setRZ("400");//子商户支付宝费率
		sub1.setROW("400");//子商户本行卡费率
		sub1.setROT("400");//子商户他行卡费率
		suborders.add(sub1);
		
		SubOrderInfo sub2 = new SubOrderInfo();
		sub2.setOI("154529277181302");
		sub2.setOV("1129101");
		sub2.setGI("3212");
		sub2.setGN("xx商品");
		sub2.setPA("0.01");
		sub2.setVN("备注2");
		sub2.setRW("400");
		sub2.setRZ("400");
		sub2.setROW("400");
		sub2.setROT("400");
		suborders.add(sub2);
		
		bizContent.setSubOrders(suborders);
		request.setBizContent(bizContent);

		JftApiPayGenPayOrderQrcodeResponseV2 response;
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

