package com.icbc.api;

import java.util.UUID;

import org.junit.Test;

import com.icbc.api.request.FinanceTppfinanceQueryinstrdetailRequestV1;
import com.icbc.api.request.FinanceTppfinanceQueryinstrdetailRequestV1.FinanceTppfinanceQueryinstrdetailRequestV1Biz;
import com.icbc.api.response.FinanceTppfinanceQueryinstrdetailResponseV1;

public class FinanceTppfinanceQueryinstrdetailV1Test {

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

		FinanceTppfinanceQueryinstrdetailRequestV1 request = new FinanceTppfinanceQueryinstrdetailRequestV1();
		request.setServiceUrl("https://ip:port/api/finance/tppfinance/queryinstrdetail/V1");

		// 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
		FinanceTppfinanceQueryinstrdetailRequestV1Biz bizContent = new FinanceTppfinanceQueryinstrdetailRequestV1Biz();
		bizContent.setClient_id("ICBCTEST80001"); // 客户ID
		bizContent.setPre_msg_id(""); // 原交易唯一编号
		bizContent.setStatus(""); // 指令状态（000-待处理；001-成功；002-失败）
		bizContent.setType("002"); // 指令类型(001-开户；002-购买；003-赎回；004-提现；005-销户；006-修改手机号)
		bizContent.setBegin_time("20230520"); // 查询开始时间
		bizContent.setEnd_time("20230520"); // 查询截止时间
		bizContent.setBegnum("1"); // 查询开始位置
		bizContent.setFetnum("10"); // 查询条数（每次查询最多100条）
		request.setBizContent(bizContent);

		FinanceTppfinanceQueryinstrdetailResponseV1 response = new FinanceTppfinanceQueryinstrdetailResponseV1();
		String msgId = UUID.randomUUID().toString().replaceAll("-", "");
		response = client.execute(request, msgId); // msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

		if (response.isSuccess()) {

			System.out.println("return_code             :       " + response.getReturnCode());
			System.out.println("return_msg              :       " + response.getReturnMsg());
			System.out.println("msg_id                  :       " + response.getMsgId());

			// 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
			System.out.println("totalcount              :       " + response.getTotalCount()); // 符合查询条件的总记录条数
			System.out.println("rows                    :       " + response.getRows()); // 本次返回记录条数
			System.out.println("nextflag                :       " + response.getNextFlag()); // 本次返回记录条数
			System.out.println("rowset                  :       " + response.getRowset().toString()); // 记录列表

		} else {

			System.out.println("return_code             :       " + response.getReturnCode());
			System.out.println("return_msg              :       " + response.getReturnMsg());
			System.out.println("msg_id                  :       " + response.getMsgId());

		}

	}
}
