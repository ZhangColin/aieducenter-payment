package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.JftApiDclMarketinfocommitRequestV1;
import com.icbc.api.response.JftApiDclMarketinfocommitResponseV1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JftApcDclMarketinfocommitRequestV1Test {
	 protected static String APP_ID = "10000000000004095545";
	    protected static String APIGW_PUBLIC_KEY =
	            "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16"
	            +"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL"
	            +"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP"
	            +"3CiNgg0W6O3AGqwIDAQAB";
	    protected static String PRI_KEY =
	    		"-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAPaPye3IZxbwUR6w9PrRP8ZBwBBOD/8PLv4PSxDTOVWHF7zAeqJ7L/Lvg/BTZTz9wnPNmmTpJTqGkjYBBNJbmADW9Vou83n3zsc56n37mHqYx98nlLaYk2Jv6VM45WhB2uLBImB2obHeNz/dx7UD3m659nDuI8smu0aQPG6ans92l6KHtD5np4GNq9SWxiX8T8Xk9BH+TXaMZxZsDu0dDwDC4RE6tDztZs6O4fQerAGTfeV+gbrBe5hI4m6NiRWW+lCDRRyNWycXq6eVGrk6yH4vgGAu+s/BEqUdbqzNuyW15BFNIjScZ1VEb9xXvGm0bS4AG4ib5+bwn17zyYRmBAgMBAAECggEAUo9cjS+MNatnembqLEPCMfWC15rINaLjmrihVX+bv3qYh6WFhTdphEohaqbfhUfPv+BLkzHypF9LEXVHrLL+svRPpKWjMOTMkadXFvq3586zkFfGUxUgcXhCctdGLQYes1ATrajA7z1lepb45w+nU8GWhKegbHph/XpZ9KAIms33xFmiX+xHQw2OLPKI8YasgMOxHtPuBvy6f8DDSz9MXzjukilFUtDPyNnhbg02rXkUH80AL9+it2Ad6f4ghFhHsvHG4BK+6Fz0PUyg0zOKEmY1J7t0cB60Kr+10AHOUoDxcVq3dM8Ro1YAakocLmjixmCHCyxZciprkt2G7UXMAQKBgQC+AjI7hAt3sAcVj6Vqx3nToIogzLJbf/cHhjoVNZwXUpnsGXDoXFFleet06zsHBz7EhirVuz3bvvWHhuI/bmVJA63wBAUXx6muYh3S1w9PmsvD5PglcraUNdM4tnhu+d+GZ/U0JObHzI5K5h+whn8cIV1sOmhj8a9uOJOaJuSJmQKBgQCsx5+ke8LBWBvL5l/PRsZi4wtmNXWw+EAO1OiamsAyYMJi15HsHYsL/HWBXmaSriU4hDWW020Z/pNQF/k0kAYYMkAG8va3nVL7g1r8NKrCeics9Ohkn/Hgzln8TISdshQbxGOf96IPx1uVHD7YGwJPnyPgFMeXjK8WUwJadAmQKQKBgCEyTQhs53v/ux0fPa1MMJKyx683VCn8T3J3FwrBlQXWHqJLR5ixGF98T5Db0DJIJ8U6E53H2uTblhYZkMm4ReXdyLPYX/NppNGcvs0pWbl0uAj/pgMU6aU3SjHsX1o0OcNHwmVb1R4vNOUNE5hJx0do5OOVyMs9XIMLS25xQRhxAoGAdS3BsI0a7YOy/ltUeUAXxfMTxRLWqdoDn8t3oXr4hHIpA14OIiN7LPrgOofqaupeZfEMYykq4ilMgVJw+dv/tiNGVzAqcSbqo5dXeprBgLnVneDPR4dORNfOIZVHj6e5iYdsxY3txGPBUeRhHrFapONaMlwFbVvFVw+fFfeCkNkCgYEAmg79P/zMmH/7o9BzY9ZExl0dAaoPPAJVfrGfJ6lmXKJXC8buQd1cayweWpUyKo63kkHDObg7Kdy1868iSwSGaPgaUdex8VXLR2IpUyBRbTx6qIKohko+QSlcVjH81IiZLoPTOAtHjlHktVegTV7zFrUB9G8J82b//gOfv6Qkqpc=";
	    protected static String BASE_URL = "http://ip:port/api";
	
	public static void main(String[] args) {
		String URI = "/jft/api/dcl/marketinfocommit/v1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {


			JftApiDclMarketinfocommitRequestV1 request = new JftApiDclMarketinfocommitRequestV1();
			JftApiDclMarketinfocommitRequestV1.JftApiDclMarketinfocommitRequestV1Biz requestV1Biz = new JftApiDclMarketinfocommitRequestV1.JftApiDclMarketinfocommitRequestV1Biz();
			Map<String, Object> map = new HashMap<>();
			Map<String, Object> map1 = new HashMap<>();
			List<Map> mapList = new ArrayList<>();
			map1.put("dataMonth", "202204");
			map1.put("busiTargetId", "11");
			map1.put("busiTargetValue", "11");
			mapList.add(map1);
			map.put("appid", "123");
			map.put("admitType", "123");
			map.put("outVendorId", "123");
			map.put("list", mapList);
			requestV1Biz.setParams(map);
			JftApiDclMarketinfocommitResponseV1 response = (JftApiDclMarketinfocommitResponseV1) client.execute(request);
			if (response.isSuccess()) {
				String a = response.getReturn_code();
				System.out.println(a);
			} else {
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
