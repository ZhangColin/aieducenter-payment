package com.icbc.api;

import static org.junit.Assert.assertEquals;
import java.util.logging.Logger;
import org.junit.Test;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.CardbusinessEpayh5UiConsumptionRequestV2;
import com.icbc.api.request.CardbusinessEpayh5UiConsumptionRequestV2.CardbusinessEpayh5UiConsumptionRequestV2Biz;
import com.icbc.api.utils.IcbcEncrypt;

public class CardbusinessEpayh5UiConsumptionV2Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfV8piJL/5Pc/ZdCMBcX1mVrLQo6rRBdqLOnYkU9xnVp1EdhMkx1wcP1BDWTo0SqupMFwZlsTH5t6ywdlD4sXvkyfCkuSSShg+ZGGLRKmDPoLOEP1KZ/SFwnIGb6hj30OXyhsmArVYfYr7qAlo0GqfQrDUAC2ZbasESLHlVvqMy48ssp2QcOPtZoWW/diQY1HJR+RMS9Kjg9oZr/dU5UloZQsDQVXmYBuAqU4RcBfEqV56dFu/suJkGrw0LenDPxI2QLJ5c51rkhx9lC7xBZigfWOnDLH6xoT1ChdcY9bP5xt/GjP5NYuI8xI2sGGTUPoUKxdrH6aiI9jp728+K1y5AgMBAAECggEAbJQsktwU7GHti2UXo5r+AOPDWQVIhQfYgHlyeCTA8Qg9usvAcM/u6tio96UIU+W9YKpfDB2tGxYVTEhLjOJRojAjU0fAkZIuCR8aAO/njSO1yeKekS7KxMCMWK6t6afgH4ok+qy0ZwnZqJC/ylIQk86DUv2nLYEQdCu3OKy5b/qZ1qA7yaCiG/D4HBThgiOifV2Yq1TCtvC2iv5mcuhH4iTXexeOQcbZepZlQnyXiVAlTYRAeo+ng8ub01NJZ4njPe9boKeqrpmMAOLN/gRTjh6yZ+90+hniXLgznOVPg+GxUbff8pVDd01POGVsid0f5Gr/TvEnixJV9nM70SCp0QKBgQD1C/oCC0mC9T8yZrrzkKZ5gsWpxElGYFvU/S1LdDsfGioRLLBJ8k4PvQcJN+pB1Ea2b8s01HQKWarXGYKQmu+dGsULbv9UpaVwH3Of+gt35Wo2+Fuh0bhcS58Ct41IGQh5leI5ckNq9iB9/x6zWQFeAEpUnXqIwFYkNCZIPbgmRQKBgQDpU3YAvKXDCesL9W4JhoWhyGBJ94frOq8hiH3vbr1xUpqDkJ9aovMDWy5f77E5Vuva/mEDxIpQrFTSA4clKj6T8E6CBiEMStP2DWLQsyC3AxDKv3g5lXab3IH4KtxjNCwadp+TRmRHWG09FLdt14AeS4El14xdhlGx6FsYncst5QKBgFBGHR9gTTOeXZaIOsQhZbe2lEQZ7hsk49BxI85tBBUbQB6iMhn3S4UyWkS10YLBJG0NUFc9JcpiN2oBjFkMuGQR6ezl7rTvErQZSYploi4jtFjPoUzwY+GwUCXWtWyh7rnN1O8WtGksudYspgUAqkb991uivwpfX5i6kLPnrBS1AoGALe8WXhLFd14ufc41eX6YND9kZWtrwK1u6OUcFdTxSqv+a0Q/evJ1cQW0XYKsmyM3j4dgxgMdT8B9elLjejeU1j8K1aIrQ2Y/0ELWX0vEdwMNfTywiHWaQhjpJVgaxxTwUc1koPPMrhcEem/npKI2QMCQjkifA5J75tBdjr0R0NkCgYEA1eUVZW1zEXB79xf2GREbPi1UeQVfIvTqOQK8fa3O0Xdrdd//BFHy44eqSrg5eG0t78wbFtkwYHUIbQZOd0L9qp6yPIk2bqldKoqUxiXPjGX4QR1XgenbWjc+cLr//EN2zRqTLrd3K2e0V/Hx+6cL14/0DB73Ma7oyZ6rMKR2JYU=";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";
	// ENCRYPT_TYPE_AES:通过工具生成
	protected static final String ENCRYPT_AES_KEY = "ULgn+LpGG+JMNy+aFbGPZg==";
	private Logger logger = Logger.getLogger(CardbusinessEpayh5UiConsumptionV2Test.class.getName());


	public void test_cop() throws IcbcApiException {
		CardbusinessEpayh5UiConsumptionRequestV2 request = new CardbusinessEpayh5UiConsumptionRequestV2();

		// 4、需传入appid，私钥，字符集
		// 是否使用AES加密请求数据
		request.setNeedEncrypt(false);
		UiIcbcClient client ;
		if (request.isNeedEncrypt()) {
			client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, ENCRYPT_AES_KEY);
		} else {
			client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,  IcbcConstants.CHARSET_UTF8);
		}
		// 5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/cardbusiness/epayh5/ui/consumption/V2");
		CardbusinessEpayh5UiConsumptionRequestV2Biz bizContent = new CardbusinessEpayh5UiConsumptionRequestV2Biz();
		request.setBizContent(bizContent);
		// 6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setInterfaceName("xxx");
		bizContent.setInterfaceVersion("xxx");
		bizContent.setOrderDate("xxx");
		bizContent.setOrderId("xxx");
		bizContent.setCurType("xxx");
		bizContent.setTotalAmount("xxx");
		bizContent.setOrderAmount("xxx");
		bizContent.setInstallmentTimes("xxx");
		bizContent.setIsEcoupon("xxx");
		bizContent.setEcouponId("xxx");
		bizContent.setEcouponName("xxx");
		bizContent.setEcouponAmt("xxx");
		bizContent.setEcouponBankAmt("xxx");
		bizContent.setEcouponMerAmt("xxx");
		bizContent.setEcouponValDate("xxx");
		bizContent.setEcouponAreaCode("xxx");
		bizContent.setEcouponNetCode("xxx");
		bizContent.setEcouponAtrxCode("xxx");
		bizContent.setEcouponOpenFlag("xxx");
		bizContent.setEcouponActId("xxx");
		bizContent.setAmountByPoint("xxx");
		bizContent.setIsBulk("xxx");
		bizContent.setBulkId("xxx");
		bizContent.setBulkValidate("xxx");
		bizContent.setCardDiscountAmt("xxx");
		bizContent.setGoodsId("xxx");
		bizContent.setGoodsName("xxx");
		bizContent.setGoodsNum("xxx");
		bizContent.setGoodType("xxx");
		bizContent.setAllPointsFlag("xxx");
		bizContent.setGoodPoints("xxx");
		bizContent.setO2oTransType("xxx");
		bizContent.setEndTime("xxx");
		bizContent.setVerifyJoinFlag("xxx");
		bizContent.setLanguage("xxx");
		bizContent.setOrderSource("xxx");
		bizContent.setPosNo("xxx");
		bizContent.setQrToken("xxx");
		bizContent.setMobilePhone("xxx");
		bizContent.setEPayCardNum("xxx");
		bizContent.setCisNo("xxx");
		bizContent.setCustId("xxx");
		bizContent.setDeviceHardId("xxx");
		bizContent.setMerFlag("xxx");
		bizContent.setMerId("xxx");
		bizContent.setMerHint("xxx");
		bizContent.setMerVar("xxx");
		bizContent.setMerUrl("xxx");
		bizContent.setRemark1("xxx");
		bizContent.setRemark2("xxx");
		bizContent.setCreditType("xxx");
		bizContent.setNotifyType("xxx");
		bizContent.setResultType("xxx");
		bizContent.setRedirectUrl("xxx");
		bizContent.setIsTpFlag("xxx");
		bizContent.setTpIdTy("xxx");
		bizContent.setTpCstId("xxx");
		bizContent.setPayTypeCC("xxx");
		bizContent.setRetMode("xxx");
		bizContent.setSubAppId("xxx");
		bizContent.setSpQuotaFlag("xxx");
		bizContent.setSpSingleQuota("xxx");
		bizContent.setSpTodayQuota("xxx");
		bizContent.setSpTodayMaxTimes("xxx");
		bizContent.setMerType("xxx");
		bizContent.setQrNewProFlag("xxx");
		bizContent.setBackup1("xxx");
		bizContent.setBackup2("xxx");
		bizContent.setBackup3("xxx");
		bizContent.setBackup4("xxx");
		bizContent.setDigitalCurFlag("xxx");
		bizContent.setEcouponPayType("xxx");
		bizContent.setIcbcAppId("xxx");
		bizContent.setCarriageAmt("xxx");
		bizContent.setMerPrtclNo("xxx");
		bizContent.setMerAcct("xxx");
		bizContent.setAutoReferSec("xxx");
		bizContent.setWalletFag("xxx");
		bizContent.setExternalAppId("xxx");
		bizContent.setWalletCustId("xxx");
		bizContent.setH5Flag("xxx");
		bizContent.setCheckFlag("xxx");
		bizContent.setCustName("xxx");
		bizContent.setCustCertType("xxx");
		bizContent.setCustCertNo("xxx");
		bizContent.setAccessType("xxx");
		bizContent.setOrderInterval("xxx");
		bizContent.setBackShopUrl("xxx");
		bizContent.setOrderApdInf("xxx");
		
		try {
			boolean testFlag = true;
			logger.info(client.buildPostForm(request));
			assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}
