package com.icbc.api;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;

import com.icbc.api.internal.util.codec.Base64;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CertEsecureEsecuritywebQuicklogonRequestV1;
import com.icbc.api.request.CertEsecureEsecuritywebQuicklogonRequestV1.CertEsecureEsecuritywebQuicklogonRequestV1Biz;

public class CertEsecureEsecuritywebQuicklogonRequestV1Test {
	private final static String SIGN_TYPE = IcbcConstants.SIGN_TYPE_RSA2;

	public static void main(String[] args) {
		String APP_ID = "xxxxx";
		// API平台密钥
		String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCQ61AmaaqgbEX80C9HlaheVCFOyN3AP7g0cGkkvl9JNQq5tNQzig+O/1fo5akOoD2X2lSSUcZqnbgVyoAL6SER27R6SHtwBxY51uz560ldEwrH/EURbMyJM9j9S0fP142+zuJbqagEDKAmx2EcYt7uKT/LlKgHndrmHkMiRZN/WlQOJIL9HNyThwLoadKI6gndsX7vSU6OSr3NIeiV78IKpizlQTq30UP08oojh0KEUvHxudp+71Bbcldcd7rgNRIcXPApBzHYI+2Ql5M/eCaVph5xcvc1EgixaeQhAB8kJodTtxGMc61aAVkAg6qa/mW8le9Gbxy3WuC/6ara/e2jAgMBAAECggEAZcy3B9TQZFLKszY3EC0kEec9Yo+dnk/kgQm6AnKsksts1lkde3rvRP8/7L1vjXOo8MQRBpvlKr3SM5SFcFh6v4W7eO7zrxV7WnQNEd5jMbuPpj4i8oOgBn1iX0QDlRARZUkFH2jFOtr3oWYLZJpbiad2RfcONhOAUohNloSTgTCOusmEGTkCK1AIyJFr6v8bngM4nO1Uc60NWJ/fjanjoLUBWml67fN7aZpE5sClggWGuVsXfmYG7P8xQkdG3CObRB5hNTi56brALN2TU1zJKLqFh3c1OmVsrHmd76iQDVfVKNg8+UosH3Jt+0e6FzlH7OF9k1yH30wnoXpKp47XeQKBgQDLZtaYQAthapZfkv0D+v6YwH7E6qWJegiwDhaFAZ8mwyU1NRXM9kvh1EtLOi/S9puqanNJdUjpUqRnkoZJFGKQEKmKy8MXMYCHG6AgRqAI8MfN2u80tQ6WSfSCl7TrrgqQGrXwTv1kmnqh+Y/q3v5AkHJ/6sOwLreodqJsywCuxQKBgQC2ZPDqBGDxI/TY0x/gdpTDW1ciiXx5uyODkx5zQfadi3azul0Iw83gcHwxEKtt6j2wiFmS8qdHfmQrfe+XjpACpowXPl7zXJq52oBqLOAe0IFe/vPWmUn6nuXEVSApl3DkZVJDGiJCT5izmnVEjCdi9Pqk9uiJBbtgStMZmI7xRwKBgDtvK1mFrU2j5Zi7zK7zEp3z8Zb8sa5AOHWpSC9JUAX1k9O+VUZ02at9axrqP7s3UDk1rcZTPSDggyW4jQChskNCeygRbkpCrrnL+rgzquOx59BhHvCZz09fANGAHt+XFYMSKwOzMRvKf/cAg2swCb839h0mrlO28veI4uFDWEm5AoGANdD4yIO8OspaLzO0Hx297RphTl50rNJLu0HIZUecRYXl40fktXZYo5Vs/2kku8y2oQTKMTKJqu2fDroc3IIC+dU8Votfpry3JolTBIDzLARnd6kQN0yJaHpD3zciw0Lc23cN26C1ZQ1pR+0WFsCnymnwHoZ/vBZF4DaTrOS3oHcCgYARFoF7rVmM6RAdjvRY8ybyH2PvXpNoqJDVENOidDH+iDk4vR7THoHuEdBswscvyrMwSYfNxPl//IEr7UQXZjPsCfSSGAhQYYyxxS7r7o2a4/md++fRbBiGflue7Eepv40qwQ+dcqVslfLfA+8vY9M/nV3eVdoQM9FeJbuIhatDUA==";
		// 加密密钥
		String PUB = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAkOtQJmmqoGxF/NAvR5WoXlQhTsjdwD+4NHBpJL5fSTUKubTUM4oPjv9X6OWpDqA9l9pUklHGap24FcqAC+khEdu0ekh7cAcWOdbs+etJXRMKx/xFEWzMiTPY/UtHz9eNvs7iW6moBAygJsdhHGLe7ik/y5SoB53a5h5DIkWTf1pUDiSC/Rzck4cC6GnSiOoJ3bF+70lOjkq9zSHole/CCqYs5UE6t9FD9PKKI4dChFLx8bnafu9QW3JXXHe64DUSHFzwKQcx2CPtkJeTP3gmlaYecXL3NRIIsWnkIQAfJCaHU7cRjHOtWgFZAIOqmv5lvJXvRm8ct1rgv+mq2v3towIDAQAB";
		UiIcbcClient client = new UiIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

		CertEsecureEsecuritywebQuicklogonRequestV1 request = new CertEsecureEsecuritywebQuicklogonRequestV1();
		request.setServiceUrl("https://ip:port/ui/cert/esecure/esecurityweb/quicklogon/V1");
		CertEsecureEsecuritywebQuicklogonRequestV1Biz bizContent = new CertEsecureEsecuritywebQuicklogonRequestV1Biz();

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = format.format(new Date());

		JSONObject logonDataJson = new JSONObject();
		logonDataJson.put("userId", "ybjh1");// 用户ID
		logonDataJson.put("password", "pass1212");// 明文密码
		logonDataJson.put("time", time);// 时间戳 yyyyMMddHHmmssSSS

		String auth = logonDataJson.toJSONString();

		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Cipher cipher = Cipher.getInstance("RSA");
			// 加密要素信息
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(PUB));// 公钥编码标准，使用该类转换
			PublicKey pub = keyFactory.generatePublic(x509KeySpec);
			cipher = Cipher.getInstance("RSA");

			cipher.init(Cipher.ENCRYPT_MODE, pub);
			String outStr = Base64.encodeBase64String(cipher.doFinal(auth.getBytes("UTF-8")));// RSA加密

			bizContent.setLogonData(outStr);
			bizContent.setAppName("GYJ");// 请求来源，应用简称
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		request.setBizContent(bizContent);

		try {
			client.buildPostForm(request);
//			System.out.println(client.buildPostForm(request));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
