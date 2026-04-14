package com.icbc.api;

import com.icbc.api.request.JftApiVendorStatusModifyRequestV1;
import com.icbc.api.request.JftApiVendorStatusModifyRequestV1.JftApiVendorStatusModifyRequestV1Biz;
import com.icbc.api.response.JftApiVendorStatusModifyResponseV1;

/**
 * 聚富通子商户状态修改接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class JftApiVendorStatusModifyTestV1 {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//2、appid
	protected static final String APP_ID = "10000000000000006464";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDzVCVhKg+ABxCI8mfMU/jDx5oQ7r30KKuKorsHWzQcdsMiYAX2s+KoOrPJktWu58iP6vHrZ/fWNtUjNO5HRA+IWUqM3qjXDLRMI5h7F+kU5+wFfe7T8+rUUTxZ4HOYh//GdXU3EZmGURqbQozVTytRVrkg7ZGlfAySg9E7z4nNJffLPK8wjT8t1Q8NZVvC+eNVEJ64SHusQDGFp4QB4w0+Wqaa0ni6BqLdgxnDQLxa1A8IBlHTA8qsQUxCiJaZxyHHDcpUie97p7B6zZ2U8AGLK52wiDyF+wO9G1tTtCmYgEwaD/DwwFv9OhEzHfPLfbYyvY6XGfD4QT2YGGBvHKApAgMBAAECggEBAOiOx5rxxBasqdT5ONNEfkE/h7QfOVUdebXqia4iMM/zgdRcydZbXolCcYp519hulnVWKxi+KVSG23XmpMVf+yhpjqLMO7os3jTW4GjlFVNxxVYKYjQYr5t0eKW9qz1g3cgjgn+7RmvlH/S7yjYkkFYQ+KbL56Otx6ZQba0oUZ/KiMCHs9F1ywRHUTg38kgmcv5pHpDO4pc+8IZxKuT8L/exUgBiAbACg3CoW1dTIMxovUBDc7+9+crt5yvcGaEF028hZrd36vI+AfL2FgApTNtZvVKFAAzoiwqEKeZAGWMe0OCZH/rkosXbfxNDjlHECvtTrCD5z1HxhfdqQi6qPrECgYEA/cG3lWPnX2ILPzO4jDfx4D5kuS0j6ggyp61Hp0mYMRPBU7V5JSrKUSH0uqP0OqINj1DRVL27l04TQVlS9lubrLWcvUlWrIejjkgG0dmZM5Kz8vX0G0nhpeIxwRxZ11XvpLt0YicJ1ZQBlFYHeoTfSR2rGhANbp+OaPMVmB/iATsCgYEA9XrUOcnctHKp3GhkXFGpZBBcSsw8gWXsMEhrbnDi4VEPvrDBigYzyaKoh3BT8duGnmn1Mm5sRn3p1BI23MKYH5g7IFTSoemQy27sGVhNnNMVlwclKZ0btGQk+mq2HoU7rLfHtmZtLd/FKYIQup135/nbgqZhgOBJ+OKcXpBojesCgYEAk5NvkwAQ9VXtuIn50AJRNtrSdScddePiocWSoGWajyAwFOC7XbQvCVQ4IbtsNBU+0ZRsyV22lV4gsKakKwZH2P+d8i+OqrQPmJWm8bAc+vNcl/YWSzCCfJi52tcEIMmnnyQtoU+W1pFHAd+uZcCUos8QTQ384dMFXV/RtTEFnTUCgYBRQyMEKlXcimS+qPQoN4RKsrvKqweQkmv4o9Ckk8wXaJ08tAitp1X3dtgxVbcERnau9EaONk2znDUO1iTBOc3gD6XrEwlTPh2HqqWP0lKHCr2H7H6Z4v9r3Qb8utylB9f0WjZLpGaaHAyJrBvbmws5iNXozylR3229bU70YvuplwKBgCnhdHz6EfCa4TgXchunLTFYcBgvU1Gr0dQkUiGsQHpyHeFjTCD4CG+oCx0rqTHnPisDGnTBYYrCnvzuNh2/k7VmxJQBL0+ZQU30rvcoAkPKkVhw8X6N6H5m3HTRJlm7d7c17+IC8AkzbFnPuWFl7yjuK/JB7Jbp/ViaNpLWyCZ9";
	
	public static void main(String[] args) throws Exception {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES","ZGoePcBvwS7NvOBqbJGkYw==", "", "");
		
		JftApiVendorStatusModifyRequestV1 request = new JftApiVendorStatusModifyRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/jft/api/vendor/status/modify/V1"); 
											   
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiVendorStatusModifyRequestV1Biz bizContent = new JftApiVendorStatusModifyRequestV1Biz();
		bizContent.setAppId("10000000000000004443");
		bizContent.setOutVendorId("10000000000000003952");
		bizContent.setOperation("1"); 
		request.setBizContent(bizContent);

		JftApiVendorStatusModifyResponseV1 response;
		try {
			response =  (JftApiVendorStatusModifyResponseV1) client.execute(request, System.currentTimeMillis()+"");
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

