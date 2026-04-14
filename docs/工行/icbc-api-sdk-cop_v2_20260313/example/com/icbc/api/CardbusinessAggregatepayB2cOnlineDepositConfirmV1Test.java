package com.icbc.api;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import org.junit.Test;

import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineDepositConfirmResponseV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineDepositConfirmRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineDepositConfirmRequestV1.CardbusinessAggregatepayB2cOnlineDepositConfirmRequestV1Biz;

public class CardbusinessAggregatepayB2cOnlineDepositConfirmV1Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfV8piJL/5Pc/ZdCMBcX1mVrLQo6rRBdqLOnYkU9xnVp1EdhMkx1wcP1BDWTo0SqupMFwZlsTH5t6ywdlD4sXvkyfCkuSSShg+ZGGLRKmDPoLOEP1KZ/SFwnIGb6hj30OXyhsmArVYfYr7qAlo0GqfQrDUAC2ZbasESLHlVvqMy48ssp2QcOPtZoWW/diQY1HJR+RMS9Kjg9oZr/dU5UloZQsDQVXmYBuAqU4RcBfEqV56dFu/suJkGrw0LenDPxI2QLJ5c51rkhx9lC7xBZigfWOnDLH6xoT1ChdcY9bP5xt/GjP5NYuI8xI2sGGTUPoUKxdrH6aiI9jp728+K1y5AgMBAAECggEAbJQsktwU7GHti2UXo5r+AOPDWQVIhQfYgHlyeCTA8Qg9usvAcM/u6tio96UIU+W9YKpfDB2tGxYVTEhLjOJRojAjU0fAkZIuCR8aAO/njSO1yeKekS7KxMCMWK6t6afgH4ok+qy0ZwnZqJC/ylIQk86DUv2nLYEQdCu3OKy5b/qZ1qA7yaCiG/D4HBThgiOifV2Yq1TCtvC2iv5mcuhH4iTXexeOQcbZepZlQnyXiVAlTYRAeo+ng8ub01NJZ4njPe9boKeqrpmMAOLN/gRTjh6yZ+90+hniXLgznOVPg+GxUbff8pVDd01POGVsid0f5Gr/TvEnixJV9nM70SCp0QKBgQD1C/oCC0mC9T8yZrrzkKZ5gsWpxElGYFvU/S1LdDsfGioRLLBJ8k4PvQcJN+pB1Ea2b8s01HQKWarXGYKQmu+dGsULbv9UpaVwH3Of+gt35Wo2+Fuh0bhcS58Ct41IGQh5leI5ckNq9iB9/x6zWQFeAEpUnXqIwFYkNCZIPbgmRQKBgQDpU3YAvKXDCesL9W4JhoWhyGBJ94frOq8hiH3vbr1xUpqDkJ9aovMDWy5f77E5Vuva/mEDxIpQrFTSA4clKj6T8E6CBiEMStP2DWLQsyC3AxDKv3g5lXab3IH4KtxjNCwadp+TRmRHWG09FLdt14AeS4El14xdhlGx6FsYncst5QKBgFBGHR9gTTOeXZaIOsQhZbe2lEQZ7hsk49BxI85tBBUbQB6iMhn3S4UyWkS10YLBJG0NUFc9JcpiN2oBjFkMuGQR6ezl7rTvErQZSYploi4jtFjPoUzwY+GwUCXWtWyh7rnN1O8WtGksudYspgUAqkb991uivwpfX5i6kLPnrBS1AoGALe8WXhLFd14ufc41eX6YND9kZWtrwK1u6OUcFdTxSqv+a0Q/evJ1cQW0XYKsmyM3j4dgxgMdT8B9elLjejeU1j8K1aIrQ2Y/0ELWX0vEdwMNfTywiHWaQhjpJVgaxxTwUc1koPPMrhcEem/npKI2QMCQjkifA5J75tBdjr0R0NkCgYEA1eUVZW1zEXB79xf2GREbPi1UeQVfIvTqOQK8fa3O0Xdrdd//BFHy44eqSrg5eG0t78wbFtkwYHUIbQZOd0L9qp6yPIk2bqldKoqUxiXPjGX4QR1XgenbWjc+cLr//EN2zRqTLrd3K2e0V/Hx+6cL14/0DB73Ma7oyZ6rMKR2JYU=";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";

	private Logger logger = Logger.getLogger(CardbusinessAggregatepayB2cOnlineDepositConfirmV1Test.class.getName());


	public void test_cop() throws IcbcApiException {
		CardbusinessAggregatepayB2cOnlineDepositConfirmRequestV1 request = new CardbusinessAggregatepayB2cOnlineDepositConfirmRequestV1();
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
		request.setServiceUrl("https://ip:port/api/cardbusiness/aggregatepay/b2c/online/deposit/confirm/V1");
		CardbusinessAggregatepayB2cOnlineDepositConfirmRequestV1Biz bizContent = new CardbusinessAggregatepayB2cOnlineDepositConfirmRequestV1Biz();
		request.setBizContent(bizContent);
		// 6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setMer_id("xxx");
		bizContent.setOut_trade_no("xxx");
		bizContent.setOrig_date_time("xxx");
		bizContent.setMer_prtcl_no("xxx");
		bizContent.setO_order_id("xxx");
		bizContent.setO_out_trade_no("xxx");
		bizContent.setO_trx_amt("xxx");
		bizContent.setTotal_amt("xxx");
		bizContent.setAttach("xxx");
		bizContent.setOrder_apd_inf("xxx");

		CardbusinessAggregatepayB2cOnlineDepositConfirmResponseV1 response;
		try {
			boolean testFlag = true;
			response = client.execute(request, System.currentTimeMillis() + "");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				testFlag = false;
				logger.info("response:" + JSON.toJSONString(response));
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("ReturnMsg:" + response.getReturnMsg());
			}
			assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}
