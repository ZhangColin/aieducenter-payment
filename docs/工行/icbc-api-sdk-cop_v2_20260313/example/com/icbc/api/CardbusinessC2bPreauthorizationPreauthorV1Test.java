package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessC2bPreauthorizationPreauthorRequestV1;
import com.icbc.api.request.CardbusinessC2bPreauthorizationPreauthorRequestV1.CardbusinessC2bPreauthorizationPreauthorRequestV1Biz;
import com.icbc.api.response.CardbusinessC2bPreauthorizationPreauthorResponseV1;
import com.icbc.api.response.CardbusinessNcpayPreAuthorizatioPreAuthorResponseV1;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class CardbusinessC2bPreauthorizationPreauthorV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfV8piJL/5Pc/ZdCMBcX1mVrLQo6rRBdqLOnYkU9xnVp1EdhMkx1wcP1BDWTo0SqupMFwZlsTH5t6ywdlD4sXvkyfCkuSSShg+ZGGLRKmDPoLOEP1KZ/SFwnIGb6hj30OXyhsmArVYfYr7qAlo0GqfQrDUAC2ZbasESLHlVvqMy48ssp2QcOPtZoWW/diQY1HJR+RMS9Kjg9oZr/dU5UloZQsDQVXmYBuAqU4RcBfEqV56dFu/suJkGrw0LenDPxI2QLJ5c51rkhx9lC7xBZigfWOnDLH6xoT1ChdcY9bP5xt/GjP5NYuI8xI2sGGTUPoUKxdrH6aiI9jp728+K1y5AgMBAAECggEAbJQsktwU7GHti2UXo5r+AOPDWQVIhQfYgHlyeCTA8Qg9usvAcM/u6tio96UIU+W9YKpfDB2tGxYVTEhLjOJRojAjU0fAkZIuCR8aAO/njSO1yeKekS7KxMCMWK6t6afgH4ok+qy0ZwnZqJC/ylIQk86DUv2nLYEQdCu3OKy5b/qZ1qA7yaCiG/D4HBThgiOifV2Yq1TCtvC2iv5mcuhH4iTXexeOQcbZepZlQnyXiVAlTYRAeo+ng8ub01NJZ4njPe9boKeqrpmMAOLN/gRTjh6yZ+90+hniXLgznOVPg+GxUbff8pVDd01POGVsid0f5Gr/TvEnixJV9nM70SCp0QKBgQD1C/oCC0mC9T8yZrrzkKZ5gsWpxElGYFvU/S1LdDsfGioRLLBJ8k4PvQcJN+pB1Ea2b8s01HQKWarXGYKQmu+dGsULbv9UpaVwH3Of+gt35Wo2+Fuh0bhcS58Ct41IGQh5leI5ckNq9iB9/x6zWQFeAEpUnXqIwFYkNCZIPbgmRQKBgQDpU3YAvKXDCesL9W4JhoWhyGBJ94frOq8hiH3vbr1xUpqDkJ9aovMDWy5f77E5Vuva/mEDxIpQrFTSA4clKj6T8E6CBiEMStP2DWLQsyC3AxDKv3g5lXab3IH4KtxjNCwadp+TRmRHWG09FLdt14AeS4El14xdhlGx6FsYncst5QKBgFBGHR9gTTOeXZaIOsQhZbe2lEQZ7hsk49BxI85tBBUbQB6iMhn3S4UyWkS10YLBJG0NUFc9JcpiN2oBjFkMuGQR6ezl7rTvErQZSYploi4jtFjPoUzwY+GwUCXWtWyh7rnN1O8WtGksudYspgUAqkb991uivwpfX5i6kLPnrBS1AoGALe8WXhLFd14ufc41eX6YND9kZWtrwK1u6OUcFdTxSqv+a0Q/evJ1cQW0XYKsmyM3j4dgxgMdT8B9elLjejeU1j8K1aIrQ2Y/0ELWX0vEdwMNfTywiHWaQhjpJVgaxxTwUc1koPPMrhcEem/npKI2QMCQjkifA5J75tBdjr0R0NkCgYEA1eUVZW1zEXB79xf2GREbPi1UeQVfIvTqOQK8fa3O0Xdrdd//BFHy44eqSrg5eG0t78wbFtkwYHUIbQZOd0L9qp6yPIk2bqldKoqUxiXPjGX4QR1XgenbWjc+cLr//EN2zRqTLrd3K2e0V/Hx+6cL14/0DB73Ma7oyZ6rMKR2JYU=";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";
	
	private Logger logger = Logger.getLogger(CardbusinessC2bPreauthorizationPreauthorV1Test.class.getName());
	

	public void test_cop() throws IcbcApiException {
		
		//4、签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessC2bPreauthorizationPreauthorRequestV1 request = new CardbusinessC2bPreauthorizationPreauthorRequestV1();
		//4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
		request.setServiceUrl("http://ip:port/api/cardbusiness/c2b/preauthorization/preauthor/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessC2bPreauthorizationPreauthorRequestV1Biz bizContent = new CardbusinessC2bPreauthorizationPreauthorRequestV1Biz();
		bizContent.setQr_no("1");
		bizContent.setOut_trade_no("20200311095409005");
		bizContent.setOrig_date_time("20250501T11:24:31");
		bizContent.setMer_prtcl_no("0200010401350201");
		bizContent.setMer_id("020001040135");
		bizContent.setTrx_curr_type("001");
		bizContent.setTotal_fee("1000");
		bizContent.setBody("商品描述");
		bizContent.setAttach("附加数据");
		bizContent.setOrder_apd_inf("1");
		bizContent.setLocation("12345123451234512345");
		bizContent.setNetwork_license("12345");
		bizContent.setDevice_type("xx");
		bizContent.setSerial_num("xxxxx");
		bizContent.setSecret_text("1234512345");
		bizContent.setApp_version("12345123");
		bizContent.setMobile_countrycd("xxx");
		bizContent.setMobile_network_num("xx");
		bizContent.setIcc_id("12345123451234512345");
		bizContent.setLocation_cd1("xxxx");
		bizContent.setLbs_num1("xxx");
		bizContent.setLbs_signal1("xxx");
		bizContent.setLocation_cd2("xxx");
		bizContent.setLbs_num2("xxx");
		bizContent.setLbs_signal2("xxx");
		bizContent.setLocation_cd3("xxx");
		bizContent.setLbs_num3("xxx");
		bizContent.setLbs_signal3("xxx");
		bizContent.setTelecom_system_id("xxx");
		bizContent.setTelecom_network_id("xxx");
		bizContent.setTelecom_lbs("xxx");
		bizContent.setTelecom_lbs_signal("xxx");


		request.setBizContent(bizContent);
		CardbusinessC2bPreauthorizationPreauthorResponseV1 response;
		try {
			boolean testFlag = true;
			response = client.execute(request, "2323***4345345");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				testFlag = false;
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
				logger.info("ReturnMsg:"+response.getReturnMsg());
			}
			assertTrue(testFlag);
		} catch (IcbcApiException e) {
			logger.info(e.getErrMsg());
		}
	}
}
