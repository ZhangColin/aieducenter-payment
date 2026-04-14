package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.OrderModExpressRequestV1;
import com.icbc.api.request.OrderModExpressRequestV1.Body;
import com.icbc.api.request.OrderModExpressRequestV1.OrderModExpressV1Biz;
import com.icbc.api.request.OrderModExpressRequestV1.WaybillRoute;
import com.icbc.api.response.OrderModExpressResponseV1;

public class OrderModExpressV1test {
	//测试用例
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCA0zbAbGLBTlPBC8sMMXpnWsRHbEqKXkfgCXToeIhIyS9iAGrOMNV+FkP7TCfkC57BNubARWP6n7pvRY6T/HC5tb/Q0DkZGiL2ko0zcR7s1cLxiJVraad6yQz+KfSUxWe7R1ibdSL13fq2jo79Gq+hRIo46S95JynwcJDAzExJqAnZnb7dLSCVVHim34teeAEynX5riI3OSgQ39yKICRupNduEU4nRuT12n64f8jVdq3s4cnDvgeT3CWIdyCxDzNJsrYmVxTGCloKfEp7s3hJDWEE7hbrPZApvqA0yjZKWo3BUHWjABsWtq6mgfJlbosT9JfbXAqCxp6nR8YSF5HGtAgMBAAECggEASzUDXcrozFlh4+1ymKe6AeSnedrqmWT5KP3cVmsnhPaOxvPfZohz2qw8+9Q15ZgzEjFu/QiFmabuPE+rMRHK7WABn3IwI0ty5eggrst3dmRE7qUCNjb0I2aEQ01TEBc+u8KnI/p6cncpCrKnH3QY3FkbKKZZpK0MeuX8W9HpJ4YyHyfSB/eVVkkAmKY8qkOY/nGhMLAwtqcS0kHLwFtRZ90T1e8LPSKW605TdTIK5OxUkumbOIENyGUpcUxRyC3IH0SUW7eCZCtE36hzUKxggCnzEWlbh4HGPccSmRLd94pCla8DR2NZZzvqmgttNZtpceWHqqhDMjU6loyUnHEqgQKBgQC9eNkKoIVUfraGjSpk77ycTNqI3hsd1OIAoKeMjZkz14B/+4AbvHTzY8R9SsL36x0BSVdj+KFpeU8UJfULZmWKIaBqJI6rYGtXaTcMwz2UWaa20KGKjfp9ChQCuttBYshlFa8lK6hbVwM5l0pN5zqdXOU/TK/DlWzmJmZO9Y4wRQKBgQCuDva+yJFwur8WWtwOeaZqekJAnFNwn9Pq59BIG09SWKn1P4awZ1coZDzqT+pSTcLKVr++V/nJO0d5QZM/j2N62AOLMW9G3W1L19pR5OX9oNMpZsROVS6voZoIl9SiqCUPQkYqKgssXKvMMuiJmCjj9Qx9TNHms701O3Xv8XPWSQKBgFnxr17LGuAkkvNGujRWVjwAg9FrVoYP1AWmPkOLyImCqN6YfpUsHnyYBOPRhMJ6GzfMIC82DTHrw212I2B5CITNN8CLgCq8x9gj+10UpfvxnyGz4qvKgHmxOhSdrY3ZnfXWmGFV20MZCE/jkQNwXDPtsGofNmTrX1nWKsUa7pfdAoGAGlCA1U8VTemtwf9yjW2uzyI0qff4VWqUa0sonf4i+9wTtdQo1LqK3rxoyTPTxSWkcVq0Gl7KkLW5lIdpb46KB6RoxL5wiFIpl/HGO+NTMgkCY5v5ya3vJPZVhz8NEppBIBFw8ibxEX/hKTn5FfQ/GleNz9OxWCOMU2Pt7JygWqECgYBacQoLEkREoaWKjD/CbhMYJcwjN/JTuoTTeWGOYR8NnbO7FvAOyAsorquPSlnDsF45VXLHDFrdtPQcdgUf6Afhz48Vp6QMu/7ElvjWHTXzzb58qPOgQoSio3YvZXUeZMCB6BLPXDZDwZ0aG3Y0Rm3nwdsVMla0KFnj1A8kp6EUFQ==";
	
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJ h0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB"; 

	protected static final String APP_ID = "10000000000001054554";


	public void test_cop() throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		OrderModExpressRequestV1 request = new OrderModExpressRequestV1();
		//测试用例
		request.setServiceUrl("http://ip:port/api/order/mod/express/V1");
		
		WaybillRoute waybillRoute = new WaybillRoute();
		waybillRoute.setId("111111");
		waybillRoute.setOrderid("2018122709523335800200001100008");
		waybillRoute.setMailno("123456");
		waybillRoute.setAccepttime("2018-12-27 16:50:21");
		waybillRoute.setAcceptAddress("123");
		waybillRoute.setOpCode("80D");
		waybillRoute.setRemark("客户已签收");
		
		List<WaybillRoute> waybillRoutes = new ArrayList<>(); 
		waybillRoutes.add(waybillRoute);
		
		Body body = new Body();
		body.setWaybillRoutes(waybillRoutes);
		
		OrderModExpressV1Biz bizContent = new OrderModExpressV1Biz();
		bizContent.setBody(body);
	    
		request.setBizContent(bizContent);

		String msg_Id = "1234567890";
		
		OrderModExpressResponseV1 response = client.execute(request, msg_Id);

		System.out.println(JSONObject.toJSONString(response));
		if (response.isSuccess() && response.getReturnCode() == 0) {
			// 业务成功处理
			System.out.println(response.toString());//更新成功的话 返回的list是空
		} else {
			// 失败
			System.out.println(response.getList().toString());//更新失败返回更新失败的orderid
		}
	}
	
	
	public static void main(String[] args) {
		OrderModExpressV1test sas = new OrderModExpressV1test();
		try {
			sas.test_cop();
		//ip:port:16257
		} catch (IcbcApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
