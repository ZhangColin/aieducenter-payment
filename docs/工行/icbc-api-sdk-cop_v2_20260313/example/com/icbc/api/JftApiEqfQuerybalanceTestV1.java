package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiEqfQuerybalanceRequestV1;
import com.icbc.api.request.JftApiEqfQuerybalanceRequestV1.JftApiEqfQuerybalanceRequestV1Biz;
import com.icbc.api.response.JftApiEqfQuerybalanceResponseV1;

/**
 * 聚富通钱包e企付余额查询接口
 * 
 */
public class JftApiEqfQuerybalanceTestV1 {

	// 1网关公钥（不是APP的公钥）
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5"
			+ "BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lT"
			+ "W3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2 appid
	protected static final String APP_ID = "10000000000000005452";
	// 3密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDI/Pm9FdqP7cTo+B9hQt...";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", "yuzLR7IMjAOZzlNZ83iXIw==", "", "");

		JftApiEqfQuerybalanceRequestV1 request = new JftApiEqfQuerybalanceRequestV1();

		// 4根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/jft/api/eqf/querybalance/V1");

		// 5请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiEqfQuerybalanceRequestV1Biz bizContent = new JftApiEqfQuerybalanceRequestV1Biz();
		bizContent.setAppId("10000000000000045006");
		bizContent.setOutVendorId("999900001");
		bizContent.setTranSeqno("100012345");
		
		request.setBizContent(bizContent);

		JftApiEqfQuerybalanceResponseV1 response;
		try {
			response = (JftApiEqfQuerybalanceResponseV1) client.execute(request, System.currentTimeMillis() + "");
			if (response.isSuccess()) {
				// 6业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
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
