package com.icbc.api;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;
import org.junit.Test;

import com.icbc.api.request.CardbusinessDecppayConsumeRequestV1;
import com.icbc.api.request.CardbusinessDecppayConsumeRequestV1.CardbusinessDecppayConsumeRequestV1Biz;

public class CardbusinessDecppayConsumeV1Test {
	

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfV8piJL/5Pc/ZdCMBcX1mVrLQo6rRBdqLOnYkU9xnVp1EdhMkx1wcP1BDWTo0SqupMFwZlsTH5t6ywdlD4sXvkyfCkuSSShg+ZGGLRKmDPoLOEP1KZ/SFwnIGb6hj30OXyhsmArVYfYr7qAlo0GqfQrDUAC2ZbasESLHlVvqMy48ssp2QcOPtZoWW/diQY1HJR+RMS9Kjg9oZr/dU5UloZQsDQVXmYBuAqU4RcBfEqV56dFu/suJkGrw0LenDPxI2QLJ5c51rkhx9lC7xBZigfWOnDLH6xoT1ChdcY9bP5xt/GjP5NYuI8xI2sGGTUPoUKxdrH6aiI9jp728+K1y5AgMBAAECggEAbJQsktwU7GHti2UXo5r+AOPDWQVIhQfYgHlyeCTA8Qg9usvAcM/u6tio96UIU+W9YKpfDB2tGxYVTEhLjOJRojAjU0fAkZIuCR8aAO/njSO1yeKekS7KxMCMWK6t6afgH4ok+qy0ZwnZqJC/ylIQk86DUv2nLYEQdCu3OKy5b/qZ1qA7yaCiG/D4HBThgiOifV2Yq1TCtvC2iv5mcuhH4iTXexeOQcbZepZlQnyXiVAlTYRAeo+ng8ub01NJZ4njPe9boKeqrpmMAOLN/gRTjh6yZ+90+hniXLgznOVPg+GxUbff8pVDd01POGVsid0f5Gr/TvEnixJV9nM70SCp0QKBgQD1C/oCC0mC9T8yZrrzkKZ5gsWpxElGYFvU/S1LdDsfGioRLLBJ8k4PvQcJN+pB1Ea2b8s01HQKWarXGYKQmu+dGsULbv9UpaVwH3Of+gt35Wo2+Fuh0bhcS58Ct41IGQh5leI5ckNq9iB9/x6zWQFeAEpUnXqIwFYkNCZIPbgmRQKBgQDpU3YAvKXDCesL9W4JhoWhyGBJ94frOq8hiH3vbr1xUpqDkJ9aovMDWy5f77E5Vuva/mEDxIpQrFTSA4clKj6T8E6CBiEMStP2DWLQsyC3AxDKv3g5lXab3IH4KtxjNCwadp+TRmRHWG09FLdt14AeS4El14xdhlGx6FsYncst5QKBgFBGHR9gTTOeXZaIOsQhZbe2lEQZ7hsk49BxI85tBBUbQB6iMhn3S4UyWkS10YLBJG0NUFc9JcpiN2oBjFkMuGQR6ezl7rTvErQZSYploi4jtFjPoUzwY+GwUCXWtWyh7rnN1O8WtGksudYspgUAqkb991uivwpfX5i6kLPnrBS1AoGALe8WXhLFd14ufc41eX6YND9kZWtrwK1u6OUcFdTxSqv+a0Q/evJ1cQW0XYKsmyM3j4dgxgMdT8B9elLjejeU1j8K1aIrQ2Y/0ELWX0vEdwMNfTywiHWaQhjpJVgaxxTwUc1koPPMrhcEem/npKI2QMCQjkifA5J75tBdjr0R0NkCgYEA1eUVZW1zEXB79xf2GREbPi1UeQVfIvTqOQK8fa3O0Xdrdd//BFHy44eqSrg5eG0t78wbFtkwYHUIbQZOd0L9qp6yPIk2bqldKoqUxiXPjGX4QR1XgenbWjc+cLr//EN2zRqTLrd3K2e0V/Hx+6cL14/0DB73Ma7oyZ6rMKR2JYU=";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";

	private Logger logger = Logger.getLogger(CardbusinessDecppayConsumeV1Test.class.getName());


	public void test_cop() throws IcbcApiException {
		CardbusinessDecppayConsumeRequestV1 request = new CardbusinessDecppayConsumeRequestV1();
		// 4、需传入appid，私钥，字符集
		UiIcbcClient client;
		//request.setNeedEncrypt(true);//设置是否需要对biz_content进行AES解密，默认不需要
		if(request.isNeedEncrypt()){//若需要进行AES解密
			String encryptKey = "xxxx";//AES密钥
			//4、配置签名类型为RSA2，加密类型为AES，AES密钥为encryptKey
			client = new UiIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY, 
					IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES,encryptKey);
		}else{
			//4、签名类型为RSA2时，需传入appid，私钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
			client = new UiIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		}
		// 5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/cardbusiness/decppay/consume/V1");
		CardbusinessDecppayConsumeRequestV1Biz bizContent = new CardbusinessDecppayConsumeRequestV1Biz();
		request.setBizContent(bizContent);
		// 6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setIcbc_appid("xxx");
		bizContent.setAttach("xxx");
		bizContent.setBackup("xxx");
		bizContent.setBody("xxx");
		bizContent.setChannel_flag("xxx");
		bizContent.setExpire_time("xxx");
		bizContent.setFeeType("xxx");
		bizContent.setMer_url("xxx");
		bizContent.setMerId("xxx");
		bizContent.setMerPrtclNo("xxx");
		bizContent.setNotify_type("xxx");
		bizContent.setOut_trade_no("xxx");
		bizContent.setResult_type("xxx");
		bizContent.setReturn_url("xxx");
		bizContent.setTotal_fee("xxx");
		bizContent.setLanguage("xxx");
		bizContent.setSceneId("xxx");
		bizContent.setMrchntIdTp("xxx");
		bizContent.setMrchntIdNo("xxx");
		try {
			boolean testFlag = true;
			logger.info(client.buildPostForm(request));
			assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}
