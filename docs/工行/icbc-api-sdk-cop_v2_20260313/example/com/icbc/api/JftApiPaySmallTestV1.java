package com.icbc.api;


import java.util.ArrayList;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiPaySmallRequestV1;
import com.icbc.api.request.JftApiPaySmallRequestV1.JftApiPaySmallRequestV1Biz;
import com.icbc.api.request.JftApiPaySmallRequestV1.SubOrderInfo;
import com.icbc.api.response.JftApiPaySmallResponseV1;

public class JftApiPaySmallTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000205510";
											
	
	public static void main(String[] args) {


		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");

		JftApiPaySmallRequestV1 request = new JftApiPaySmallRequestV1();
		request.setServiceUrl("http://ip:port/api/jft/api/pay/small/V1");
		JftApiPaySmallRequestV1Biz bizContent = new JftApiPaySmallRequestV1Biz();
		bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
		bizContent.setOutVendorId("201809121");//子商户标识：提交支付请求的子商户编号 
		bizContent.setOutUserId("M20180111222326");//用户标识
		bizContent.setNotifyUrl("com");//商户通知URL：商户接受支付成功通知消息URL
		bizContent.setGoodsId("3212");//商品编号（长度单位：字节）
		bizContent.setGoodsName("XX商品");//商品名称（长度单位：字节）
		bizContent.setOutOrderId("2099876345");//平台对接方ID：平台商户订单ID，确保唯一
		bizContent.setMac("a3-b4-c5");//手机Mac:支付提交用户手机Mac地址
		bizContent.setTrxIp("ip:port");//交易IP：支付提交用户IP地址
		bizContent.setTrxIpCity("北京");//交易IP所在地：支付提交用户IP所在地
		bizContent.setTrxChannel("06");//交易渠道:固定取06
		bizContent.setPayAmount("1.01");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
		bizContent.setVarNote("商户备注");//商户备注
		bizContent.setExternalAgreementNo("12312321312");//用户签约的协议号
		//以下内容为上送风控参数
		bizContent.setVersion("1.0.0");//版本号信息（走风险监控接口支付接口时，必须输入此值，固定输入1.0.0，以下字段信息为风险接口上送字段信息的选输项目）
		bizContent.setChannelType("2");//交易渠道：1-APP，2-其它
		bizContent.setDeviceId("A000005518F696-7D2FEEF966C631A1");//设备串号
		bizContent.setCpuId("E7493986AEm");//cpu编号
		bizContent.setMemoryCapacity("128");//内存容量
		bizContent.setSystemVersion("Windows7");//操作系统版本
		bizContent.setBrowserVersion("IE10");//浏览器版本
		bizContent.setBrowserLang("UTF-8");//浏览器语言
		bizContent.setScreenResolution("1024*768");//屏幕分辨率
		bizContent.setFactoryInfo("Intel");//厂商信息
		//以下内容为合并支付设置
		bizContent.setPayType("02");//支付类型-合并支付
		bizContent.setSubMerRateVipCard("100");//会员卡费率
		bizContent.setSubMerRateOwn("100");//本行卡费率
		bizContent.setSubMerRateOther("100");//它行卡费率
		bizContent.setOutBusinessCode("100");//外系统业务编号
		bizContent.setMultiFixedCommission("0.01");//定额佣金
		bizContent.setFixedCommissionOwn("1.00");//固定费用金额(e支付本行卡)
		bizContent.setFixedCommissionOther("1.00");//固定费用金额(e支付它行卡)
		
		bizContent.setIsreal("0");
		bizContent.setSubidno("123456");
		bizContent.setPromptText("1");
		bizContent.setExpiryDate("20200409202020");
		
		bizContent.setJD("1");		//是否优惠
		bizContent.setJDA("1.00");	//优惠金额
		bizContent.setCouponId("102321232123");	//优惠券编号
		bizContent.setBizType("100001");
		
		List<SubOrderInfo> subOrders = new ArrayList<SubOrderInfo>();
		SubOrderInfo sub1 = new SubOrderInfo();
		sub1.setOI("154529277181301");
		sub1.setOV("1129102");
		sub1.setPA("1.23");
		sub1.setRV("400");
		sub1.setROW("400");
		sub1.setROT("400");
		sub1.setJDA("1.00");
		sub1.setJD("1");
		sub1.setJDT("1");
		sub1.setIW("1"); //是否即时解冻
		sub1.setCOW("0.01");//定额佣金金额(e支付本行卡)
		sub1.setCOT("0.01");//定额佣金金额(e支付它行卡)
		sub1.setmFC("0.01");//多方分润定额佣金
		subOrders.add(sub1);
		
		SubOrderInfo sub2 = new SubOrderInfo();
		sub2.setOI("154529277181302");
		sub2.setOV("1129101");
		sub2.setPA("4.56");
		sub2.setRV("400");
		sub2.setROW("400");
		sub2.setROT("400");
		sub2.setJDA("1.00");
		sub2.setJD("1");
		sub2.setJDT("1");
		sub2.setIW("1"); //是否即时解冻
		sub2.setCOW("0.01");//定额佣金金额(e支付本行卡)
		sub2.setCOT("0.01");//定额佣金金额(e支付它行卡)
		sub2.setmFC("0.01");//多方分润定额佣金
		subOrders.add(sub2);
		
		bizContent.setSuborders(subOrders);
		
		//以下小额免密停机支付补帐设置
		bizContent.setRepairFlag("01"); //补账标志，区分该请求是支付请求或补账请求，缺省为支付。00：支付，01：补账
		bizContent.setOrigOutOrderId("201901011535460001");//补账时原交易订单号	201901011535460001
		
		request.setBizContent(bizContent);

		JftApiPaySmallResponseV1 response;
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

