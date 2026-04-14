package com.icbc.api;

import com.icbc.api.request.JftApiVendorOpenAccountOtherRequestV1;
import com.icbc.api.request.JftApiVendorOpenAccountOtherRequestV1.JftApiVendorOpenAccountOtherRequestV1Biz;
import com.icbc.api.response.JftApiVendorOpenAccountOtherResponseV1;

/**
  * @Author dccb-wblit
  * @Description 异地往来户预约开户API接口V1-测试类
  * @Date 2021/1/18 16:35
  * @Version 1.0.0
  */
public class JftApiVendorOpenAccountOtherTestV1 {

	// 1、网关公钥（不是APP的公钥）
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5"
			+ "BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lT"
			+ "W3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、appid
	protected static final String APP_ID = "10000000000001474554";
	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gp...";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", "5xGJdh7qb+B95SUoxDlatg==", "", "");

		JftApiVendorOpenAccountOtherRequestV1 request = new JftApiVendorOpenAccountOtherRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/jft/api/vendor/open/account/other/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiVendorOpenAccountOtherRequestV1Biz bizContent = new JftApiVendorOpenAccountOtherRequestV1Biz();
		bizContent.setAppId(APP_ID);
		bizContent.setUpstreamEventSerialno("202101181351");//事件编号
		bizContent.setTrxID("20210118135135");//交易流水号
		bizContent.setChannelType("1");//渠道种类
		bizContent.setTerminalId("01");//渠道终端编号
		bizContent.setChannelTerminalType("1");//渠道终端类型
		bizContent.setStartApp("app");//发起应用简称
		bizContent.setServface("servface");//服务界面
		bizContent.setZoneNo("200");//地区号
		bizContent.setBrNo("620");//网点号
		bizContent.setOperTeller("12");//操作柜员
		bizContent.setTrxType("3");//交易类型
		bizContent.setTrxCode("1");//交易代码
		bizContent.setTrxDate("2018-06-12");//交易日期
		bizContent.setCheckWorkDateFlag("1");//核对交易日期标志
		bizContent.setOffsiteZoneNo("200");//主办行地区号
		bizContent.setOffsiteBrNo("620");//主办行网点号
		bizContent.setVerifyCode("91500103578");// 资质代码
		request.setBizContent(bizContent);

		JftApiVendorOpenAccountOtherResponseV1 response;
		try {
			response = (JftApiVendorOpenAccountOtherResponseV1) client.execute(request, System.currentTimeMillis() + "");
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				// 失败
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

}
