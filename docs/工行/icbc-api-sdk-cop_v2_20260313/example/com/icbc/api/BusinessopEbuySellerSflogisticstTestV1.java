package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BusinessopEbuySellerSflogisticstRequestV1;
import com.icbc.api.request.BusinessopEbuySellerSflogisticstRequestV1.LastResultDTORequestV1;
import com.icbc.api.response.BusinessopEbuySellerSflogisticstResponseV1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 军E采物流信息同步
public class BusinessopEbuySellerSflogisticstTestV1 {

	// 客户私钥
	private static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXY3LeSkYCtpk2mmyeLKYA2OrFyp36vW7OOkfU0w6OkAnGnOFY5qn92ig49Psn2sw96yRbdbq2466SM9Vy13RCYLg2WnGDNmx2+QZr1j5gSmCusk76fVvFv5c0/WCdg/9IQt/NPLR3Pg9vyVGTrw1zZwP5MRtizOTTBAMMgfbPOD8JrS2+my1CwX7P9vrBxvQZMLGMHJsMneaAuoG9Kfo7UpEUnIBiKLq/9DbDMQQ1fSS0l0xA64IJLuLG+nwiwzgyWX7AblQtCtdPTHmBCPk4o8Y5hpNAyQ4dtcWk1F90i/YxexyDwbDBnPJ9uP+HFp895Je8XoPy3VqMEQToEdF/AgMBAAECggEAYoeJVYAFvPJXGaC8HN52VIrZkk3jMDRmj7aC1dNf4A2DL08fo5rM/LkmAJwBctkGoS4Pha9EDJB3KBSwUYRE2IbWnhmTU5DyWySMt1bDVixSbKEEoidAuZ2OriBS53HnjoJn4s4lro4nwdvHSlVebcOYZHE/n7g3Hde4nEVh+09hb6JXXFC3EK/7p80zt0wjp1yv9/iLPgXSvyXRW9qM8ED5JbDpsO4nDvF5ZwXWjQ3iauQjsKjVgYcNg31PNq6tl39I3sivx2AP8IYoYz/J5IukKrc6Yeb9KLyjrhMzeoJr3gzpp5T/VPb2vgeH+z8ytU91inMiS7WqmD+xNq33QQKBgQDSph1K/fq8+ZyckERklNudVdtsIKHtNL5kFvPFZUSF94oUgg6y6IkWO1wtDUIUAxi9B88/l34TBeYqmvCfDe/MN7npAjNbDhh6m6VyPZqgUkCdLCUqPpoI3vEP7N8iLPCXZoVs6nvrOqh0tbsLUplQpDc3KOzQqYhhGGyoqZNfUQKBgQC3+zRUH0Y8TUdtkD+VWB74+FzfmgalNZQZMjVbhkpmPWLCj69IxtHIFwWtKQQdeAvYii8PgHrstkQOSEcpXTItIJXcHpwRCypyZpO1i/ScvflhdHJdWKYsaho7k/peGWTP09mD7UHv3SzEC0taU6GzvUE7ZsZbZCwQf8OPkbIPzwKBgQCROH4HXjMHJtpmQTs1UD5bKH6d+51Gh88d94jVNCxaYz5pRwRVKOShVAkG8hX07/GVGBYz0//Ro4Z7Jtft/1CrLFvZnG/FPWF43Wbg7EF2d14ePPneNsxAg1oTTYzC5dJzvfUMEled4pGR7Ftnwej6ZEvAllE0xTvFHG6ElGuKEQKBgDuIBzipHfrpLpGfOVPDvdHl23dmyXuvyIoWmvlQjMZkWo6GG7u6yaqq+GU9fl1iuMKKhAvKEPyDRaYxcQVLaK79ekBNj0ZcjJGT63u1G6OUPRtxqIfX5X09n9tjWIIWSW6OKDXuEYXh1VhiJzHFGe+k73SfHtP3p+J21PqhtDI1AoGBAMQZUXTDb6wSA76b2Lc5/pqYYvoO1GQYJvWm8o4ZTlno7iBdFTAOEz7ekKFmyuW/NiZq58hOA5PQevRmXKggLix5qSlvjLJc8y93gC6kqW/Znuaf2fYHR7fl7jR1neltETtEWvrW0Ei7YugeBb4sim1lagbPLI87Ab8hwSJahwHh";
	// API网关公钥
	private static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 签名秘钥
	private static final String ENCRYPT_KEY = "mQbJILokBccRHUkS+XBk7A==";
	// APPID
	private static final String APP_ID = "1000XXXXX0241012";
	// 服务地址
	private static final String API_URL = "http://IP/api/businessop/ebuy/seller/sflogisticst/V1";
	// 外网服务地址
	//protected static final String API_URL = "https://apipcs.dccnet.com.cn/api/businessop/ebuy/seller/sflogisticst/V1";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
		BusinessopEbuySellerSflogisticstRequestV1 request = new BusinessopEbuySellerSflogisticstRequestV1();
		request.setServiceUrl(API_URL);
        LastResultDTORequestV1 biz = new LastResultDTORequestV1();
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("barScanTm", "2020-10-18 08:31:33");
        map.put("distName", "1111");
        map.put("erpOrder", "1111");
        map.put("opCode", "1111");
        map.put("outsideName", "1111");
        map.put("owsRemark", "1111");
        map.put("requestTime", "2020-10-18 08:31:33");
        map.put("routeId", "1111");
        map.put("sfOrderNo", "1111");
        map.put("waybillNo", "1111");
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("barScanTm", "2020-10-18 08:31:33");
        map2.put("distName", "111122");
        map2.put("erpOrder", "111122");
        map2.put("opCode", "111122");
        map2.put("outsideName", "111122");
        map2.put("owsRemark", "111122");
        map2.put("requestTime", "2020-10-18 08:31:33");
        map2.put("routeId", "111122");
        map2.put("sfOrderNo", "111122");
        map2.put("waybillNo", "111122");
        list.add(map);
		list.add(map2);
        BusinessopEbuySellerSflogisticstResponseV1 response;
		biz.setLastResult(list);
		request.setBizContent(biz);
		try {
			//System.out.println("Request: " + JSONObject.toJSONString(biz));
			response = client.execute(request, System.currentTimeMillis() + "");
			//System.out.println("Response: " + JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				//System.out.println("execute success");
			} else {
				//System.out.println("execute failue");
			}
		} catch (IcbcApiException e) {
			//System.out.println("execute exception" + e);
			e.printStackTrace();
		}
	}
}
