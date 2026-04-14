package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiVendorInfoQueryRequestV1;
import com.icbc.api.request.JftApiVendorInfoQueryRequestV1.JftApiVendorInfoQueryRequestV1Biz;
import com.icbc.api.response.JftApiVendorInfoQueryResponseV1;

/**
 * 聚富通子商户查询接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class JftApiVendorInfoQueryTestV1 {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//2、appid
	protected static final String APP_ID = "10000000000000004207";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCw0uMBzYmj2AxTWCDxg2LzByUWe/wLD+l0OFg6dinYwJDxj+GVjY1U0RA4Vpjj8kD3Pmhc6fd1X0Tsxnl+38S7un3BGvDgsPQUI6l/IbMszG29+rsHq9+5YNn/CIfKVaC/di6SWpWR5DBKj5Nf0KWrD78pG6ildIVIMaJ+Dsf9qtuZdS5TRZ0I/HRHU+z5YS417aQpjOmQP5n+BPuzb2duwzugwoHEo9R78WxR0XcqycEJmguA9KlXs1S6MrzrMsVgFxdc665SpNKRAmyqB7wjSHnNZCPWPLHdAXnAED9EngPgUBEKmQWhCEQZt1rPqqbsYSXB51ojZFlcpY++0S8RAgMBAAECggEAfGUP0F/hecNvAF8aZe2ReflHzSaSLNIX4WwWHaKtU9A858PZ5lU73TRqncfGLW5q58UetGbgcNEB8zS5mlkf1RWgjG5AkUHLpDNmdYqmu1hhx+oRE5eFf6u0EOfWZ7CPssIYh1t0p7sJghq5igmF0u70A55yqVtsvFo2v0n0xe317j5+aUZ4iahR834CPD5iXnbQ7pfNWr+Ch+ll7dRtsT8oqP9tIFzfAkCX/W8KIQqT/ZGKAf79O4PjgPogzwdJ5Aa1OJOFj4zzuK5h1mgZ4twjQh/AhrecsylgVqwsem9mp5T/4W45/BjtPA87wDlPdc7arPGhYpGqrEQIywjPQQKBgQDlqIq7OGE0sdpfcdWXXVVLcROX76TKEDd7/sfLMBej6S5d5/OgZ7qTAKJTxawH2NoU/X1Cimf1cXsXo/Cbak/FTIr9NXvRQQGhBpaQcupfuzXUP1kNIskE8sxgZIuGRzBvhChOn9C2F0/a8aDDxkVC6wJiMgOXBKzYPIo54NRW2QKBgQDFGvZ+uOViyBQE2tL8BJM+CAlXlUI2nivyOVdaneHKoYAiJ/g1C+ZJ5g2it36pPPocVHylfvsuL4pTysMvLEbKglySLopD37Na/38CEAZu0ajs9YgUD5h1b2PqUlZV+T9mBiupCiPNniYOFAvobzffZhu0ArE/tl+F487LFLim+QKBgHadk3HZT9OAnLI5QEFakmhRsfYCb1FyCMZBNH4Mi9k74Zfc6mcJzEKKzEyKRy0SYqj+uAh7JfCOOS5fnTEYOrVDsHHRgu70LVw24+Mdh1IEhmvmgB8ZvUuR/981bx7ee/TNBGh/gLPb3IHP1OHo8PL8IJIJFPOWFZPlyNrV9bYZAoGAbDq78yVvv2gBphDKHVwdxcf/6TmqG/eKRf06fCWJYAyCIT81SVt5XkaWYizDvgYfiALUVsN9BQVRvWTu/C4hyPz/+7zWayKgh20XvngyMBOr4EGKMQnHO2SoxnfjtF2idFEbFDzfH/6hAsX7K+w2D9Mvjw6TaVqo5QoL+PMt2XECgYAZTXzs78P7A68HXq3EyQ6ZdHRzoXwVHcxnC3ovItLN/9VeSt+6A5hBZRZN+QasvxD/++ny/9+LGM5YLVE6vi1aF6cOYAanYu3qot7tNaPhIbSFnUfyfu5/mO4cCnOJi4zveWU3MdBoJjow4i0yx6bCAKX9woNldEI8Co71T9efIA==";
	
	public static void main(String[] args) throws Exception {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES","nezfRV32iH9deJq4IrcCpw==", "", "");
		
		JftApiVendorInfoQueryRequestV1 request = new JftApiVendorInfoQueryRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/jft/api/vendor/info/query/V1");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiVendorInfoQueryRequestV1Biz bizContent = new JftApiVendorInfoQueryRequestV1Biz();
		bizContent.setAppId("10000000000000004207");
		bizContent.setOutVendorId("201803310123456");
		request.setBizContent(bizContent);

		JftApiVendorInfoQueryResponseV1 response;
		try {
			response = (JftApiVendorInfoQueryResponseV1) client.execute(request, System.currentTimeMillis()+"");
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

