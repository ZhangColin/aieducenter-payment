package com.icbc.api;

import com.icbc.api.request.GetWithdrawBalRequestV1;
import com.icbc.api.request.GetWithdrawBalRequestV1.GetWithdrawBalRequestV1Biz;

import com.icbc.api.response.GetWithdrawBalResponseV1;
import com.icbc.api.response.GetWithdrawBalResponseV1;

/**
 * 工银二维码退款接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class GetWithdrawBalV1Test {
	
	//1、网关公钥
		protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

		//2、appid
		protected static final String APP_ID = "20170301008";

		//3、密钥对认证方式，公钥在API平台登记，此处为私钥
		protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDB4/OxJgbvI1Btezi0bDURPhj/7bmjlU89vaxIikb3elLu1tVAQw+RVLnH8U26Sue17ciqlASCYczvkkLOGIwWDJLowiQom5JH1TislZVg+cTfcvC2hC/f0hMzLjCX/cGZG4zWsvl8/99h8XkVwDxR+5M1YHztVhOWkURahYARI6OQaouu8CYfgzDzLBldccViWyOf0+kXwBX6bO443/P0OcS+wLlxRIoD1p+7TicHdfZOIpGhYvEslYBV4IocTtoiw6mvRIE/nJWZKPiLTV7q75v51PGnptFnT+dzSfc0oAdhVzCJG3JFrUJm4Luz06NYSNNQ+jchoNi0EISIF1HLAgMBAAECggEBAImnzn8p8Whc2SAxiMvHu1F3lgY2AEOcoPrDrAqAaewQI8tAKX0LX4Ud8xx6PGYUAjhPDtXUavMqfnZkkBuEoAiTesXX+BYAhbWMM0WMmdaU3eqKz021wcuVeV4v3/45Afcy8SYJQUo226DM8Nu5dNn708Lksy1JnHScxDvEZm1Dc3JtD/cZ5ydjFA1hS+DxXaF0Fr0HUlnZPLX1+zxjqGMend+vegyq2xBHD2LfkdD8TOb169LdEXrtX+9rTqIuQJPJon8LZ2bVGhLCHyLqIIt2uwuSfcCuwRir2Zjq9+s9rPVHm1FbDKRRKeo8j/NwZBWir3K0JfX+LNpl2PTaatkCgYEA9SrBM/zAppITCuklMzEEwmKwKRhC7rSSrID3tAQlSmT4KbiQxNIq+LLX+WxWQnlsxgPP7fUcrUHuYHXoU1nsDEIPpIPqrxf4MryOuOPbhJ9qAzPkfwU1cyM0K16PK2Ja3W12t1YlYzMlorsu2tDnqw/kE9G+h3X3jbHNzmffzNUCgYEAynUsltzrfMAardBLCg5P9VJljGyKWgVy0kvhqgOzr+Yo0yiqhLHS0dIJYsLu3aWWzcbMvsVuEeo63980F1432hNJc5K40g9dYlQs3Avyg189Q/FKp+iu8nxxa6Y5uhk0FxptBdhyV6KxXDWJtpZjSDs9Woi/jDRI6/nglRD5dB8CgYAgjK2b9lmpg8lNcIjE1CQ6npmeyx7PzF+Q0YqRdt9wkhk0iNGHoBOeczgjYFPPfYmIfKcVg65r/k3ktqLCtjSVqAulnvIAjyEfs1MLjwzs/bE4faI/jvR6tdjOTT6a1vMTZN4XFzPdiMPdaBafbI7lcjWov/cYyvuz70BQwA/BhQKBgB++p7MwCKyS4pwGYP2+qMLqnImcK0VaPVrEEyo5Ul9uU1Bou/P6fffwWxBlSFbws+ioF0qCYnPkqj6YHO7dExzakVXR17D4P8g/Mpl+NhRrRov0taQGVAgpgw9CDSMk+In21sln2uXq2KyAqIs8oWyd/UzIzGALgrOdhp1ZAAQ5AoGBAIZoRKAu/U3HLSXTk6DhObRvB/3IE8trWZNAQR5Nemnn1XqLpIssy7wRSQbLohjPDMmrF6uf59xhabjefQG53jFxwlS8wDA0HsrEW2ctjipxUc7dI4V9IrlzoYXp6e+dogNCJcbwY/FeJh84OHSn8HGyHsZs9kEMrNP8djk2Oz7I";	
		
		public static void main(String[] args) throws Exception {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
				
		GetWithdrawBalRequestV1 request = new GetWithdrawBalRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/saes/servmer/getwithdrawbal/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		GetWithdrawBalRequestV1Biz bizContent = new GetWithdrawBalRequestV1Biz();
		bizContent.setServmerNo("0200000000201");
		bizContent.setShopCode("0200000000201");//该字段非必输项
		bizContent.setShopType("1");//该字段非必输项,
		bizContent.setApiId("1122334455");//该字段非必输项,
		
		request.setBizContent(bizContent);
		GetWithdrawBalResponseV1 response;
		try {
			response = client.execute(request, "23344544454439");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			
			
			
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
				
			} else {
				// 失败
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}

