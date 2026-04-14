package com.icbc.api;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

import com.icbc.api.request.CardbusinessEpaypcConsumptionRequestV1;
import com.icbc.api.request.CardbusinessEpaypcConsumptionRequestV1.CardbusinessEpaypcConsumptionRequestV1Biz;

public class CardbusinessEpaypcConsumptionV1Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfV8piJL/5Pc/ZdCMBcX1mVrLQo6rRBdqLOnYkU9xnVp1EdhMkx1wcP1BDWTo0SqupMFwZlsTH5t6ywdlD4sXvkyfCkuSSShg+ZGGLRKmDPoLOEP1KZ/SFwnIGb6hj30OXyhsmArVYfYr7qAlo0GqfQrDUAC2ZbasESLHlVvqMy48ssp2QcOPtZoWW/diQY1HJR+RMS9Kjg9oZr/dU5UloZQsDQVXmYBuAqU4RcBfEqV56dFu/suJkGrw0LenDPxI2QLJ5c51rkhx9lC7xBZigfWOnDLH6xoT1ChdcY9bP5xt/GjP5NYuI8xI2sGGTUPoUKxdrH6aiI9jp728+K1y5AgMBAAECggEAbJQsktwU7GHti2UXo5r+AOPDWQVIhQfYgHlyeCTA8Qg9usvAcM/u6tio96UIU+W9YKpfDB2tGxYVTEhLjOJRojAjU0fAkZIuCR8aAO/njSO1yeKekS7KxMCMWK6t6afgH4ok+qy0ZwnZqJC/ylIQk86DUv2nLYEQdCu3OKy5b/qZ1qA7yaCiG/D4HBThgiOifV2Yq1TCtvC2iv5mcuhH4iTXexeOQcbZepZlQnyXiVAlTYRAeo+ng8ub01NJZ4njPe9boKeqrpmMAOLN/gRTjh6yZ+90+hniXLgznOVPg+GxUbff8pVDd01POGVsid0f5Gr/TvEnixJV9nM70SCp0QKBgQD1C/oCC0mC9T8yZrrzkKZ5gsWpxElGYFvU/S1LdDsfGioRLLBJ8k4PvQcJN+pB1Ea2b8s01HQKWarXGYKQmu+dGsULbv9UpaVwH3Of+gt35Wo2+Fuh0bhcS58Ct41IGQh5leI5ckNq9iB9/x6zWQFeAEpUnXqIwFYkNCZIPbgmRQKBgQDpU3YAvKXDCesL9W4JhoWhyGBJ94frOq8hiH3vbr1xUpqDkJ9aovMDWy5f77E5Vuva/mEDxIpQrFTSA4clKj6T8E6CBiEMStP2DWLQsyC3AxDKv3g5lXab3IH4KtxjNCwadp+TRmRHWG09FLdt14AeS4El14xdhlGx6FsYncst5QKBgFBGHR9gTTOeXZaIOsQhZbe2lEQZ7hsk49BxI85tBBUbQB6iMhn3S4UyWkS10YLBJG0NUFc9JcpiN2oBjFkMuGQR6ezl7rTvErQZSYploi4jtFjPoUzwY+GwUCXWtWyh7rnN1O8WtGksudYspgUAqkb991uivwpfX5i6kLPnrBS1AoGALe8WXhLFd14ufc41eX6YND9kZWtrwK1u6OUcFdTxSqv+a0Q/evJ1cQW0XYKsmyM3j4dgxgMdT8B9elLjejeU1j8K1aIrQ2Y/0ELWX0vEdwMNfTywiHWaQhjpJVgaxxTwUc1koPPMrhcEem/npKI2QMCQjkifA5J75tBdjr0R0NkCgYEA1eUVZW1zEXB79xf2GREbPi1UeQVfIvTqOQK8fa3O0Xdrdd//BFHy44eqSrg5eG0t78wbFtkwYHUIbQZOd0L9qp6yPIk2bqldKoqUxiXPjGX4QR1XgenbWjc+cLr//EN2zRqTLrd3K2e0V/Hx+6cL14/0DB73Ma7oyZ6rMKR2JYU=";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";

	private Logger logger = Logger.getLogger(CardbusinessEpaypcConsumptionV1Test.class.getName());


	public void test_cop() throws IcbcApiException {
		// 4、需传入appid，私钥，字符集
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
				IcbcConstants.CHARSET_UTF8);
		CardbusinessEpaypcConsumptionRequestV1 request = new CardbusinessEpaypcConsumptionRequestV1();
		// 5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/cardbusiness/epaypc/consumption/V1");
		CardbusinessEpaypcConsumptionRequestV1Biz bizContent = new CardbusinessEpaypcConsumptionRequestV1Biz();
		request.setBizContent(bizContent);
		// 6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setIcbc_flag("xxx");
		bizContent.setIcbc_appid("xxx");
		bizContent.setOrder_date("xxx");
		bizContent.setOut_trade_no("xxx");
		bizContent.setAmount("xxx");
		bizContent.setInstallment_times("xxx");
		bizContent.setCur_type("xxx");
		bizContent.setMer_id("xxx");
		bizContent.setMer_prtcl_no("xxx");
		bizContent.setMer_acct("xxx");
		bizContent.setMerchant_info("xxx");
		bizContent.setLanguage("xxx");
		bizContent.setGoods_id("xxx");
		bizContent.setGoods_name("xxx");
		bizContent.setGoods_num("xxx");
		bizContent.setCarriage_amt("xxx");
		bizContent.setMer_hint("xxx");
		bizContent.setExpire_time("xxx");
		bizContent.setMer_reference("xxx");
		bizContent.setMer_custom_ip("xxx");
		bizContent.setMer_var("xxx");
		bizContent.setMer_url("xxx");
		bizContent.setReturn_url("xxx");
		bizContent.setAuto_refer_sec("xxx");
		bizContent.setVerify_join_flag("xxx");
		bizContent.setNotify_type("xxx");
		bizContent.setResult_type("xxx");
		bizContent.setMer_custom_id("xxx");
		bizContent.setMer_custom_phone("xxx");
		bizContent.setMer_order_remark("xxx");
		bizContent.setOrder_flag_ztb("xxx");
		bizContent.setE_isMerFlag("xxx");
		bizContent.setE_fixedACFlag("xxx");
		bizContent.setO2o_mer_id("xxx");
		bizContent.setCredit_type("xxx");
		bizContent.setElife_mer_id("xxx");
		bizContent.setPay_expire("xxx");
		bizContent.setPage_linkage_flag("xxx");
		bizContent.setE_Name("xxx");
		bizContent.setE_TelNum("xxx");
		bizContent.setE_CredType("xxx");
		bizContent.setE_CredNum("xxx");
		bizContent.setE_CardNo("xxx");
		bizContent.setGoods_address("xxx");
		bizContent.setGoods_type("xxx");
		bizContent.setRemark1("xxx");
		bizContent.setOrder_interval("xxx");
		bizContent.setOrder_apd_inf("xxx");
		bizContent.setGoods_tag("xxx");
		bizContent.setWxpay_detail("xxx");
		bizContent.setAlipay_detail("xxx");
		try {
			boolean testFlag = true;
			logger.info(client.buildPostForm(request));
			assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}
