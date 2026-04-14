package com.icbc.api;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.icbc.api.request.BiometricsCustomMediumqueryV1Request;
import com.icbc.api.request.BiometricsCustomMediumqueryV1Request.BiometricsCustomMediumqueryV1RequestBiz;
import com.icbc.api.response.BiometricsCustomMediumqueryV1Response;
import com.icbc.api.response.BiometricsCustomMediumqueryV1Response.BiometricsCustomMediumqueryV1ResponseList;

/**
 * 鍒嗙粍瀹㈡埛鐢熺墿鐗瑰緛淇℃伅鏌ヨ
 * 
 * 璇峰晢鎴烽厤缃甋DK涓殑lib鏂囦欢澶逛腑鐨刯ar鍖呭悗锛屾牴鎹娉�1-6鎻愮ず杩涜鏁版嵁鏇挎崲
 */
public class BiometricsCustomMediumqueryV1Test {

	// 1銆佺綉鍏冲叕閽�
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	// 2銆乤ppid
	protected static final String APP_ID = "10000000000000003917";

	// 3銆佸瘑閽ュ璁よ瘉鏂瑰紡锛屽叕閽ュ湪API骞冲彴鐧昏锛屾澶勪负绉侀挜
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDH8nn5xSLmJVrXDm4480Yr9KanIITe4htlMjBB8AHnezhD6oEtB7g5AuuH0V3ZIRK9hvxJPMPPfjzdJaAyecX1Hbm2OBstgnCunXWhtUBeq27JAKvtWhomyuMo1xi+rbmOtLeNXydXWeq8Lh6hBwLvWjk9ZgH3f5iOEkv7NCR2uJef3getAJC2wrn3Sn0ck9UtvqXKVkMKdfOCnz+kmd81R5qJ+9wEILsp2Lv1pn1klE7+vjhS9ySPkfsDLZQGpj07k4TogELJIxzMPSRQ1mu5mlQuN1k3e3RwcXY4ZkpyJ406tajAA6NG/V4kNLIN0qthWZSaI8ejPupZRTsJucO5AgMBAAECggEAbthx3pH3YvdagNazLEdRo5Shvw08HAPlhBhqwGM56wZYju42Zqxc6N5BjK35BSJcrU1kF4XkV+d3/nGZeeIgaF7uUaKRDo29wk/wopFE+ja32B7TMWY+ETtw63czdKICuKZfC7SwrrBefaxjtomYivDNwsgGVxsZi8mA7eZeQKHiAjETf7YpkTyznRI2GC6wXzqrQTBOjnmzMyXH880kSQwglXHeV3YACkgeH8RnZpjVvVby3LWITmTQsUi6aYyLpxv9cRHzlCxNCt/0ny2mOS59FOIZbqBNWxK8xg9v6x5hsAr1CrAQmLqtzdMQlUwCaWx9OP6/HF6vnTnRWagE8QKBgQDrOz3RHJ1rzwF2j7IYb4h50JEVmc3k5joc1Mz9svCQ1UhlBHjVtNfRxxtbdPYtGLGEYoBcbltH40oqx4S4gUZdgL4e1vVOiv357hMcdawHYWNvY62cPsTS81ejUo04DhfHZKrV8BJrCzr3poS4+bjFv2/GfeTs6tI+GsRUvZQUVwKBgQDZmbtfANTxufegEBkBTwPpipcKV6CxWc+Ktzpgp2plzyAlMQ3AF7sP/xs6gsGuYiZnW3gAkD/x8+rsdYpkZaycpzEFz5hkvYo5uzVWEwLlRNGGtzWQGL86Kyr2Cc9VkJOy6D60G5Trn0/s19C8DnXH1NKBjPvb6Q7uZNMKETtebwKBgGjgIcBwlJLzWQCmMk4Is/0ziuvphc2PA8vE6JwXwn+gR8WaB9Gn7iz+K2lMLVGP191hGT+ciB/16yRFjhvAr0GS27DeP8JD39YlWenvYMJtniqUc1vc9F3k1mbfsEyUo+9nJ294uI13FVRCrpvV4pZvsoL5sC4cx52ey3fIihPDAoGAMXIJbbEWskb7yddelzmJPgxL/gGjdileS7sO/N+OAcxrFYhsA3Zg/2WMnmMP97w91canIhmlLimq/EhJRj8EViR3s0ZU7q9TNYMgv4paYhadNetztGarLWCZxoa8IklUBpdXiD2sPU5aLwtrVxBCG0J1AxMyN+phhjJK8kKwJukCgYAP03AqwmRaDYQ0zYx6vQcbetIjRTtqT4nWBHJzM60ilkMuLVQfMNXAGOZOxOYwCNKFU4Vr0r8n63P+p+I7e/jOi1O+y6wI5C2/6K6YP4z+kkpKXpgCQvOt7HjMpNzsN//3fZiPcArd7royTCHGvDB/az08SqV1H2qgdJ7AJoATtQ==";

	 // 鏌�2019骞�01鏈�23鏃� 13:14:01 鍒� 2019骞�01鏈�23鏃� 15:14:01 鍏氦铏硅啘鏀粯涓氬姟缁勪腑鍙戠敓鏂板銆佷慨鏀广�佸垹闄ょ殑瀹㈡埛淇℃伅
	public void testQueryByParPronoAndTranSeqno() throws Exception {

		// 绛惧悕绫诲瀷涓篟SA2鏃讹紝闇�浼犲叆appid锛岀閽ュ拰缃戝叧鍏挜锛岀鍚嶇被鍨嬩娇鐢ㄥ畾鍊糏cbcConstants.SIGN_TYPE_RSA2锛屽叾浠栧弬鏁颁娇鐢ㄧ己鐪佸��
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);
		BiometricsCustomMediumqueryV1Request request = new BiometricsCustomMediumqueryV1Request();

		// 4銆佹牴鎹祴璇曠幆澧冨拰鐢熶骇鐜鏇挎崲鐩稿簲ip鍜岀鍙�
		request.setServiceUrl("http://ip:port/api/biometrics/custom/mediumquery/V1");

		// 5銆佽瀵圭収鎺ュ彛鏂囨。鐢╞izContent.setxxx()鏂规硶瀵逛笟鍔′笂閫佹暟鎹繘琛岃祴鍊硷紝闈炲繀杈撳瓧娈靛彲涓嶄笂閫�
		BiometricsCustomMediumqueryV1RequestBiz bizContent = new BiometricsCustomMediumqueryV1RequestBiz();


     bizContent.setGroupId("90000000000000000191");//涓氬姟鍒嗙粍ID鍙�
     bizContent.setBioseqenceno("4000000000005");//鐢熺墿鐗瑰緛搴忓彿	
     request.setBizContent(bizContent);

		BiometricsCustomMediumqueryV1Response response = new BiometricsCustomMediumqueryV1Response();
		try {
			response = client.execute(request, UUID.randomUUID().toString().replaceAll("-", ""));// msgId娑堟伅閫氳鍞竴缂栧彿锛岃姹傛瘡娆¤皟鐢ㄧ嫭绔嬬敓鎴愶紝APP绾у敮涓�
			if (response.isSuccess()) {
				// 6銆佷笟鍔℃垚鍔熷鐞嗭紝璇锋牴鎹帴鍙ｆ枃妗ｇ敤response.getxxx()鑾峰彇鍚屾杩斿洖鐨勪笟鍔℃暟鎹�
				System.out.println("return_code:" + response.getReturnCode());
				System.out.println("return_msg:" + response.getReturnMsg());
				System.out.println("response.getItems: " + response.getItems());
				//System.out.println("response.getPoolStatus: " + response.getPoolStatus());

				if (!"0".equals(response.getItems())) {

					// 鏌ヨ鎴愬姛 鑾峰彇杩斿洖缁撴灉
					List<BiometricsCustomMediumqueryV1ResponseList> list = response.getList();
					// 鑾峰彇缁撴灉闆嗕腑瀛楁
					for (BiometricsCustomMediumqueryV1ResponseList detail : list) {
						// 鑾峰彇淇℃伅.....

						System.out.println("============================= begin =============================");

						System.out.println("mediumType         :    " + detail.getMediumType());
						System.out.println("mediumId            :    " + detail.getMediumId());
						System.out.println("=============================  end  =============================");

					}
				}
			} else {
				System.out.println("returnCode:" + response.getReturnCode());
				System.out.println("returnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
