package com.icbc.api;

import static org.junit.Assert.assertEquals;
import java.util.logging.Logger;
import org.junit.Test;
import com.icbc.api.request.CardbusinessEpayh5UiConsumptionRequestV1;
import com.icbc.api.request.CardbusinessEpayh5UiConsumptionRequestV1.CardbusinessEpayh5UiConsumptionRequestV1Biz;
import com.icbc.api.utils.IcbcEncrypt;

public class CardbusinessEpayh5UiConsumptionV1Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAjFBNI73xr0rQIzLCIUHgV11a2ewftKrkq9ELrBmFfrJYwu5WYbBpj8u8FtOqTiOuuV3DVAIJwvnvojI++2hRjbxvNAZJVhDQkuxj/4KJ0F5L/RE9TiipR+tsaAhLvOR65T/nQGbqBe9kIeWg+yGYMGKAjLd2nGNxy2zQLneUyAtFgNFs9rEkzrMPDuV6f7peOaoIHLXAoMhUyFZHZ9e7Pn/2ByP7evzrtAB4nmz/Y2QKgc9E9/c6NTGm96+locBTYSrFT7uips26xuIUSB6W99i4/DA7AcxVfh9qtfC5zwLgq1Us0sfaYNDY4j29UUKYDyUaUIKRFXG6SIBdGBqTCwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCMUE0jvfGvStAjMsIhQeBXXVrZ7B+0quSr0QusGYV+sljC7lZhsGmPy7wW06pOI665XcNUAgnC+e+iMj77aFGNvG80BklWENCS7GP/gonQXkv9ET1OKKlH62xoCEu85HrlP+dAZuoF72Qh5aD7IZgwYoCMt3acY3HLbNAud5TIC0WA0Wz2sSTOsw8O5Xp/ul45qggctcCgyFTIVkdn17s+f/YHI/t6/Ou0AHiebP9jZAqBz0T39zo1Mab3r6WhwFNhKsVPu6KmzbrG4hRIHpb32Lj8MDsBzFV+H2q18LnPAuCrVSzSx9pg0NjiPb1RQpgPJRpQgpEVcbpIgF0YGpMLAgMBAAECggEANyqvWZkpt/jbIqunRfonoVHUEZBT2u3B4IfdZfnW0bx/R4z7q4b3yhCZUARCllxTKxBhZtsySVWJUSUePCyEvAxVUIgUN2F/D/UfT+tcEe+xQruPdfZosB0wzypL1OWkKBRa8KhF5LV9khI9m99WfdeMoUbUvKIgsJGEvHBJr+ifjSJnQjqxoilNDLh3hiC3u567bytwLy1A8TWJrTmejD8AcZbsgxCWaE48UeLey2EHFkRkckULutMWIiTEvS88gGEoQL9JOJl3icyW+1oNt37uLhppBNKMg4RziPMCO+aGdrippJHAuTRL6KxvX1B6TkiGy5Crk+wG4nC3nlzfSQKBgQDfTm0vcs0/Qq8/Ay3MXx6SAK2sOev86dtBsUEgpWPR8b/IVdjNeNDrAHDfiQeyC2ac6No2t4emDne+mMe+i9hsWOp1kLCKwhzn92aTolxTaC7uL/qscAc8C6frBno1frrQOugR9grtY0E96me3Q6QYQZKJYe/SyRX/M/URnK29bwKBgQCg20pLrS5sAFOo4YwkSmTCGGTMDh3kZSzMNTssrGI/spIr9TydHSKPQzKPBJ0AgKL2rid4O9S6l4CaGVnATI9A/8oyGcGVS+F0cYOfK0HzRPvjzeHkN+aHKSfHPp0acfRJfpeu+9I+Zltss0KLlanE/nml3U0dutTkb5B4cq/uJQKBgQCd3lgaf1LTMlWqepjaLzTxi0E8zQYlGz9CxgmBBuv29K3c97P6LzujqAAkSEAEg0LcE1KR9FYV84x8Vo6AC2XJ02KOEuh8cJb291SxcstKvBDRm2HgnqK+tI7V/RJQ13NC2yJN8F/xS4ZIO3tveIZ+D32FWpOttTOz5AuRk+7QSQKBgQCLc5FFai4Ek4PztRW5oIUZf+xiuxpPFydvIfbbE9dZQYZXdZPG4atEaA08z9MMzHh7QyAUrEoQOafT8IZOoBDRWPAB3zJkLBSYzpA9PVM/g16cb7CWGaBRLOy/Xxj0BDa0QHtfyBDM6A/6fK4ENQupLLRphyGD1KUcJVwqD8NVwQKBgQC460Q4eKey/GMVQrXhNbmYOAv4hv3Ia3OuUgJAtmwUtpeyxMU6+WkLFF4bFmkbGvPNQYJ2DCLAZS47Mo/I2i8nRqSwmKypYxd/+d4NMXF8NuCSrvbbu++IHZh8ejGcZMNYYBQsvs9+ZoFU15HAekQDHp5fqnegFwxLOK+JUre8NQ==";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";

	private Logger logger = Logger.getLogger(CardbusinessEpayh5UiConsumptionV1Test.class.getName());


	public void test_cop() throws IcbcApiException {
		// 4、需传入appid，私钥，字符集
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		CardbusinessEpayh5UiConsumptionRequestV1 request = new CardbusinessEpayh5UiConsumptionRequestV1();
		// 5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/cardbusiness/epayh5/ui/consumption/V1");
		CardbusinessEpayh5UiConsumptionRequestV1Biz bizContent = new CardbusinessEpayh5UiConsumptionRequestV1Biz();
		request.setBizContent(bizContent);
		// 6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setIcbc_appid("xxx");
		bizContent.setOrder_date("xxx");
		bizContent.setOrder_id("xxx");
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
		bizContent.setMerURL("xxx");
		bizContent.setReturn_url("xxx");
		bizContent.setMer_var("xxx");
		bizContent.setAuto_refer_sec("xxx");
		bizContent.setWallet_flag("xxx");
		bizContent.setExternal_app_id("xxx");
		bizContent.setCust_id("xxx");
		bizContent.setMobile("xxx");
		bizContent.setCredit_type("xxx");
		bizContent.setNotify_type("xxx");
		bizContent.setResult_type("xxx");
		bizContent.setH5_flag("xxx");
		bizContent.setRemark1("xxx");
		bizContent.setRemark2("xxx");
		bizContent.setBackup1("xxx");
		bizContent.setCheck_flag("xxx");
		bizContent.setCust_name("xxx");
		bizContent.setCust_cert_type("xxx");
		bizContent.setCust_cert_no("xxx");
		bizContent.setAllocate_cardno(IcbcEncrypt.encryptContent("allocate_cardno", "AES", "aes_key", "GBK"));
		bizContent.setBack_shop_url("xxx");
        bizContent.setAccess_type("xxx");
		bizContent.setOrder_apd_inf("xxx");
		bizContent.setOrder_interval("xxx");
		bizContent.setBack_shop_url_sdk("xxx");
		try {
			boolean testFlag = true;
			logger.info(client.buildPostForm(request));
			assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}
