package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcResponse;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.NfpdChainTradeinfoUploadRequestV1;
import com.icbc.api.request.NfpdChainTradeinfoUploadRequestV1.NfpdChainTradeinfoUploadRequestV1Biz;

import java.util.ArrayList;
import java.util.List;

// 网络融资产业链贸易信息上传服务（数据）
public class MallNfpChainNfptradeinfouploadTestV1 {

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String APP_ID = "10000000000000092015";
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXY3LeSkYCtpk2mmyeLKYA2OrFyp36vW7OOkfU0w6OkAnGnOFY5qn92ig49Psn2sw96yRbdbq2466SM9Vy13RCYLg2WnGDNmx2+QZr1j5gSmCusk76fVvFv5c0/WCdg/9IQt/NPLR3Pg9vyVGTrw1zZwP5MRtizOTTBAMMgfbPOD8JrS2+my1CwX7P9vrBxvQZMLGMHJsMneaAuoG9Kfo7UpEUnIBiKLq/9DbDMQQ1fSS0l0xA64IJLuLG+nwiwzgyWX7AblQtCtdPTHmBCPk4o8Y5hpNAyQ4dtcWk1F90i/YxexyDwbDBnPJ9uP+HFp895Je8XoPy3VqMEQToEdF/AgMBAAECggEAYoeJVYAFvPJXGaC8HN52VIrZkk3jMDRmj7aC1dNf4A2DL08fo5rM/LkmAJwBctkGoS4Pha9EDJB3KBSwUYRE2IbWnhmTU5DyWySMt1bDVixSbKEEoidAuZ2OriBS53HnjoJn4s4lro4nwdvHSlVebcOYZHE/n7g3Hde4nEVh+09hb6JXXFC3EK/7p80zt0wjp1yv9/iLPgXSvyXRW9qM8ED5JbDpsO4nDvF5ZwXWjQ3iauQjsKjVgYcNg31PNq6tl39I3sivx2AP8IYoYz/J5IukKrc6Yeb9KLyjrhMzeoJr3gzpp5T/VPb2vgeH+z8ytU91inMiS7WqmD+xNq33QQKBgQDSph1K/fq8+ZyckERklNudVdtsIKHtNL5kFvPFZUSF94oUgg6y6IkWO1wtDUIUAxi9B88/l34TBeYqmvCfDe/MN7npAjNbDhh6m6VyPZqgUkCdLCUqPpoI3vEP7N8iLPCXZoVs6nvrOqh0tbsLUplQpDc3KOzQqYhhGGyoqZNfUQKBgQC3+zRUH0Y8TUdtkD+VWB74+FzfmgalNZQZMjVbhkpmPWLCj69IxtHIFwWtKQQdeAvYii8PgHrstkQOSEcpXTItIJXcHpwRCypyZpO1i/ScvflhdHJdWKYsaho7k/peGWTP09mD7UHv3SzEC0taU6GzvUE7ZsZbZCwQf8OPkbIPzwKBgQCROH4HXjMHJtpmQTs1UD5bKH6d+51Gh88d94jVNCxaYz5pRwRVKOShVAkG8hX07/GVGBYz0//Ro4Z7Jtft/1CrLFvZnG/FPWF43Wbg7EF2d14ePPneNsxAg1oTTYzC5dJzvfUMEled4pGR7Ftnwej6ZEvAllE0xTvFHG6ElGuKEQKBgDuIBzipHfrpLpGfOVPDvdHl23dmyXuvyIoWmvlQjMZkWo6GG7u6yaqq+GU9fl1iuMKKhAvKEPyDRaYxcQVLaK79ekBNj0ZcjJGT63u1G6OUPRtxqIfX5X09n9tjWIIWSW6OKDXuEYXh1VhiJzHFGe+k73SfHtP3p+J21PqhtDI1AoGBAMQZUXTDb6wSA76b2Lc5/pqYYvoO1GQYJvWm8o4ZTlno7iBdFTAOEz7ekKFmyuW/NiZq58hOA5PQevRmXKggLix5qSlvjLJc8y93gC6kqW/Znuaf2fYHR7fl7jR1neltETtEWvrW0Ei7YugeBb4sim1lagbPLI87Ab8hwSJahwHh";
	protected static final String ENCRYPT_KEY = "mQbJILokBccRHUkS+XBk7A==";

	public static void main(String[] args) {
		test();
	}

	public static void test() {
		System.out.println("请求测试开始");

		String serviceUrl = "http://ip:port/api/mall/nfp/chain/nfptradeinfoupload/V1";

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");

		NfpdChainTradeinfoUploadRequestV1Biz bizContent = new NfpdChainTradeinfoUploadRequestV1Biz();
		bizContent.setAppid("10000000000000082009");
		bizContent.setOutVendorId("10001006");
		bizContent.setOutCoreVendorId("10001006");
		bizContent.setOutCustomVendorId("10001068");
		bizContent.setWithDrawalsWay("1");
		bizContent.setTradeId("2019062700000002");
		bizContent.setTradeName("贸易信息上传测试-201907");
		bizContent.setActType("1");
		bizContent.setOrderList(generatorOrderDataTest());

		NfpdChainTradeinfoUploadRequestV1 request = new NfpdChainTradeinfoUploadRequestV1();
		request.setServiceUrl(serviceUrl);
		request.setBizContent(bizContent);
		IcbcResponse response;
		try {
			System.out.println("请求参数: " + JSONObject.toJSONString(bizContent));
			response = client.execute(request, System.currentTimeMillis() + "");
			System.out.println("返回信息: " + JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				System.out.println("业务处理成功！");
				System.out.println(response.getReturnCode());
			} else {
				System.out.println("业务处理失败！");
			}
		} catch (IcbcApiException e) {
			System.out.println("请求测试异常" + e);
			e.printStackTrace();
		}
		System.out.println("请求测试结束");
	}

	private static List<NfpdChainTradeinfoUploadRequestV1Biz.Order> generatorOrderDataTest() {
		List<NfpdChainTradeinfoUploadRequestV1Biz.Order> orderList = new ArrayList<NfpdChainTradeinfoUploadRequestV1Biz.Order>();
		NfpdChainTradeinfoUploadRequestV1Biz.Order order = new NfpdChainTradeinfoUploadRequestV1Biz.Order();
		order.setOrderElement1("2019082108461012");
		order.setOrderElement2("10000000");
		order.setOrderElement3("人民币");
		order.setOrderElement4("");
		order.setOrderElement5("");
		order.setOrderElement6("");
		order.setOrderElement7("商品");
		order.setOrderElement8("");
		order.setOrderElement9("");
		order.setOrderElement10("");
		order.setOrderElement11("70");
		order.setOrderElement12("转让");
		order.setOrderElement13("是");
		order.setOrderElement14("现金");
		order.setOrderElement15("20191229");
		order.setOrderElement16("432啊");
		order.setOrderElement17("工行");
		order.setOrderElement18("0200003419006599821");
		order.setOrderElement19("457额");
		order.setOrderElement20("建行");
		order.setOrderElement21("0200227909020179358");
		order.setOrderElement22("");
		order.setOrderElement23("");
		order.setOrderElement24("");
		order.setOrderElement25("");
		order.setOrderElement26("");
		order.setOrderElement27("已开票");
		order.setOrderElement28("发票");
		order.setOrderElement29("81625152-27");
		order.setOrderElement30("20190816-34");
		order.setOrderElement31("10000000");
		order.setOrderElement32("");
		order.setOrderElement33("");
		order.setOrderElement34("");
		order.setOrderElement35("");
		order.setOrderElement36("");
		order.setOrderElement37("");
		order.setOrderElement38("");
		order.setOrderElement39("");
		order.setOrderElement40("");
		order.setOrderElement41("");
		order.setOrderElement42("");
		order.setOrderElement43("");
		order.setOrderElement44("");
		order.setOrderElement45("");
		order.setOrderElement46("10000000");
		orderList.add(order);

		return orderList;
	}

}
