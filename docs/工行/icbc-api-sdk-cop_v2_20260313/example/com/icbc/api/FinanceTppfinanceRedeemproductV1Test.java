package com.icbc.api;

import java.util.UUID;

import org.junit.Test;

import com.icbc.api.request.FinanceTppfinanceRedeemproductRequestV1;
import com.icbc.api.request.FinanceTppfinanceRedeemproductRequestV1.FinanceTppfinanceRedeemproductRequestV1Biz;
import com.icbc.api.response.FinanceTppfinanceRedeemproductResponseV1;

public class FinanceTppfinanceRedeemproductV1Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	// 2、appid
	protected static final String APP_ID = "10000000000000003916";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDH8nn5xSLmJVrXDm4480Yr9KanIITe4htlMjBB8AHnezhD6oEtB7g5AuuH0V3ZIRK9hvxJPMPPfjzdJaAyecX1Hbm2OBstgnCunXWhtUBeq27JAKvtWhomyuMo1xi+rbmOtLeNXydXWeq8Lh6hBwLvWjk9ZgH3f5iOEkv7NCR2uJef3getAJC2wrn3Sn0ck9UtvqXKVkMKdfOCnz+kmd81R5qJ+9wEILsp2Lv1pn1klE7+vjhS9ySPkfsDLZQGpj07k4TogELJIxzMPSRQ1mu5mlQuN1k3e3RwcXY4ZkpyJ406tajAA6NG/V4kNLIN0qthWZSaI8ejPupZRTsJucO5AgMBAAECggEAbthx3pH3YvdagNazLEdRo5Shvw08HAPlhBhqwGM56wZYju42Zqxc6N5BjK35BSJcrU1kF4XkV+d3/nGZeeIgaF7uUaKRDo29wk/wopFE+ja32B7TMWY+ETtw63czdKICuKZfC7SwrrBefaxjtomYivDNwsgGVxsZi8mA7eZeQKHiAjETf7YpkTyznRI2GC6wXzqrQTBOjnmzMyXH880kSQwglXHeV3YACkgeH8RnZpjVvVby3LWITmTQsUi6aYyLpxv9cRHzlCxNCt/0ny2mOS59FOIZbqBNWxK8xg9v6x5hsAr1CrAQmLqtzdMQlUwCaWx9OP6/HF6vnTnRWagE8QKBgQDrOz3RHJ1rzwF2j7IYb4h50JEVmc3k5joc1Mz9svCQ1UhlBHjVtNfRxxtbdPYtGLGEYoBcbltH40oqx4S4gUZdgL4e1vVOiv357hMcdawHYWNvY62cPsTS81ejUo04DhfHZKrV8BJrCzr3poS4+bjFv2/GfeTs6tI+GsRUvZQUVwKBgQDZmbtfANTxufegEBkBTwPpipcKV6CxWc+Ktzpgp2plzyAlMQ3AF7sP/xs6gsGuYiZnW3gAkD/x8+rsdYpkZaycpzEFz5hkvYo5uzVWEwLlRNGGtzWQGL86Kyr2Cc9VkJOy6D60G5Trn0/s19C8DnXH1NKBjPvb6Q7uZNMKETtebwKBgGjgIcBwlJLzWQCmMk4Is/0ziuvphc2PA8vE6JwXwn+gR8WaB9Gn7iz+K2lMLVGP191hGT+ciB/16yRFjhvAr0GS27DeP8JD39YlWenvYMJtniqUc1vc9F3k1mbfsEyUo+9nJ294uI13FVRCrpvV4pZvsoL5sC4cx52ey3fIihPDAoGAMXIJbbEWskb7yddelzmJPgxL/gGjdileS7sO/N+OAcxrFYhsA3Zg/2WMnmMP97w91canIhmlLimq/EhJRj8EViR3s0ZU7q9TNYMgv4paYhadNetztGarLWCZxoa8IklUBpdXiD2sPU5aLwtrVxBCG0J1AxMyN+phhjJK8kKwJukCgYAP03AqwmRaDYQ0zYx6vQcbetIjRTtqT4nWBHJzM60ilkMuLVQfMNXAGOZOxOYwCNKFU4Vr0r8n63P+p+I7e/jOi1O+y6wI5C2/6K6YP4z+kkpKXpgCQvOt7HjMpNzsN//3fZiPcArd7royTCHGvDB/az08SqV1H2qgdJ7AJoATtQ==";

	
	public void test() throws IcbcApiException {

		// 签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);

		FinanceTppfinanceRedeemproductRequestV1 request = new FinanceTppfinanceRedeemproductRequestV1();
		request.setServiceUrl("https://ip:port/api/finance/tppfinance/redeemproduct/V1");

		FinanceTppfinanceRedeemproductRequestV1Biz bizContent = new FinanceTppfinanceRedeemproductRequestV1Biz();
		bizContent.setOperation("1"); // 操作标志（1-赎回提现 2-提现）
		bizContent.setProdcode("1701ELT"); // 产品代码
		bizContent.setCurrType("CNY"); // 币种（国际标准）
		bizContent.setRedemFunds("1000000"); // 赎回份额（操作标志1必输）
		bizContent.setDrawFunds(""); // 提现金额（操作标志2必输，最小单位分）
		bizContent.setApplNum(""); // 原购买申请单编号（有则输）
		bizContent.setClient_id("ICBCTEST80001"); // 客户ID
		bizContent.setPhoneno("18688888888"); // 手机号
		bizContent.setSms_no("443253"); // 短信编号
		bizContent.setSms_identify_no("0ac63bcfcf1de3be"); // 验证码
		bizContent.setSms_id("ICBCTEST8000120210615161443000007"); // 短信认证唯一编号，认证短信发送后需记录下，后和短信编号、短信验证码一同上送
		bizContent.setSms_key(
				"0bb0cc1ac3a554de73550d2cd35a84a51af5a5c0d320a82e30a380626b269b5ff56aabfc346e39a9aa82f113e1a661b84e252356217bd0bebfe679bc71d52a6a710e6056df4882a50dd8122d5afc7d14b0acc610a7ef2600e04b11cd14be15c717a7fab85776151cc1da889f05c2c06b215a202d89291631cf6b5e8d05c8e0812da8096fc4cf8d653eb8adfe4a5ffeb7e2adbe4b2380772956cb189f269f7d3a4864d8093b9accd1b69d17c8ce062a85517bd672cb9e3f00a188165d10c76385347d0c75d90223757704cfe62fa97924aa67a57e18547ad50d7fa3894fd9c3f6e64325d71be07c85704aacd8defd5c5d01dba5796a9acd571894c998ebc34098"); // 验证码密钥（第三方公司随机生成192位对称密钥，使用该密钥加密验证码，并使用工行公钥加密后在本字段上送）
		request.setBizContent(bizContent);

		FinanceTppfinanceRedeemproductResponseV1 response = client.execute(request);// 请求通讯唯一编号
		String msgId = UUID.randomUUID().toString().replaceAll("-", "");
		response = client.execute(request, msgId); // msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

		if (response.isSuccess()) {

			System.out.println("return_code             :       " + response.getReturnCode());
			System.out.println("return_msg              :       " + response.getReturnMsg());
			System.out.println("msg_id                  :       " + response.getMsgId());

			// 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
			System.out.println("totalAmt                :       " + response.getTotalAmt()); // 返还金额（单位分）
			System.out.println("principal               :       " + response.getPrincipal()); // 本金（单位分）
			System.out.println("profit                  :       " + response.getProfit()); // 收益（单位分）
			System.out.println("poundage                :       " + response.getPoundage()); // 手续费（单位分）
			System.out.println("isarrived               :       " + response.getIsarrived()); // 是否实时到账（1-已成交2-已登记
																								// 3-已预约；对于“1-已成交”是实时兑付资金）

		} else {

			System.out.println("return_code             :       " + response.getReturnCode());
			System.out.println("return_msg              :       " + response.getReturnMsg());
			System.out.println("msg_id                  :       " + response.getMsgId());

		}

	}
}
