package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.GenPreOrderMergePayRequestV1;
import com.icbc.api.request.GenPreOrderMergePayRequestV1.GenPreOrderMergePayRequestV1Biz;
import com.icbc.api.request.GenPreOrderMergePayRequestV1.SubOrderInfo;
import com.icbc.api.response.GenPreOrderMergePayResponseV1;

public class GenPreOrderMergePayTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000205510";


	public static void main(String[] args) {


		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");

		GenPreOrderMergePayRequestV1 request = new GenPreOrderMergePayRequestV1();
		request.setServiceUrl("http://ip:port/api/jft/api/pay/mergepay/gen/order/V1");
		GenPreOrderMergePayRequestV1Biz bizContent = new GenPreOrderMergePayRequestV1Biz();
		
		bizContent.setAppId("10000000000000205510");//平台商户标识
		
		bizContent.setOutUserId("sh2");//用户标识
		bizContent.setNotifyUrl("http://www.xx.com");//商户通知URL
		bizContent.setGoodsId("3212");//商品编号
		bizContent.setGoodsName("xx商品");//商品名称
		bizContent.setOutOrderId("2018082923908");//平台对接方订单ID
		bizContent.setMac("a3-b4-c5");//手机Mac
		bizContent.setTrxIp("ip:port");//交易IP
		bizContent.setTrxIpCity("北京");//交易IP所在地
		bizContent.setTrxChannel("02");//交易渠道
		bizContent.setPayAmount("1.03");//支付金额(元)
		
		bizContent.setVarNote("备注");//商户备注
		bizContent.setVersion("1.0.1");
		bizContent.setCustomerMobile("15512345678");
		List<SubOrderInfo> suborders = new ArrayList<SubOrderInfo>();
		SubOrderInfo suborder = new SubOrderInfo();
		suborder.setGoodsId("3212");//商品编号
		suborder.setGoodsName("xx商品");//商品名称
		suborder.setSubMerRateWx("600");//子商户服务费率（微信/十万分比）
		suborder.setSubMerRateZfb("600");//子商户服务费率（支付宝/十万分比）
		suborder.setSubMerRateOwn("600");//子商户服务费率（e支付本行卡/十万分比）
		suborder.setSubMerRateOther("600");//子商户服务费率（e支付它行卡/十万分比） 
		suborder.setRU("600");//子商户服务费率（云闪付/十万分比） 
		suborder.setOutVendorId("10000000000000045006");//子商户标识号
		suborder.setPayAmount("1.03");//支付金额(元)
		suborder.setVarNote("备注");//商户备注
		suborder.setOutOrderId("2018082923909");//平台对接方订单ID
		suborder.setJDA("1.00");		//优惠金额
		suborder.setJD("1");			// 是否优惠
		suborder.setJDT("1");     //优惠类型
		suborder.setIW("1"); //是否即时解冻
		suborder.setCOW("0.01");//定额佣金金额(e支付本行卡)
		suborder.setCOT("0.01");//定额佣金金额(e支付它行卡)
		suborder.setCW("0.01");//定额佣金金额(微信支付)
		suborder.setCZ("0.01");//定额佣金金额(支付宝)
		suborder.setCU("0.01");//定额佣金金额(云闪付)
		suborders.add(suborder);
		bizContent.setSuborders(suborders);
		bizContent.setOutBusinessCode("10000000000000000001");//外部业务编码
		
		request.setBizContent(bizContent);

		GenPreOrderMergePayResponseV1 response;
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

