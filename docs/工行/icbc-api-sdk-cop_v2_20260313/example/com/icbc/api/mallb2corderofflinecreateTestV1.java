package com.icbc.api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.mallb2corderofflinecreateRequestV1;
import com.icbc.api.request.mallb2corderofflinecreateRequestV1.mallb2CorderofflinecreateRequestV1Biz;
import com.icbc.api.request.mallb2corderofflinecreateRequestV1.mallb2CorderofflinecreateRequestV1Biz.ThirdPartySubmitRequestOrderParam;
import com.icbc.api.request.mallb2corderofflinecreateRequestV1.mallb2CorderofflinecreateRequestV1Biz.ThirdPartySubmitRequestOrderParam.ThirdPartySubmitRequestOrders;
import com.icbc.api.request.mallb2corderofflinecreateRequestV1.mallb2CorderofflinecreateRequestV1Biz.ThirdPartySubmitRequestOrderParam.ThirdPartySubmitRequestOrders.StoreVO;
import com.icbc.api.request.mallb2corderofflinecreateRequestV1.mallb2CorderofflinecreateRequestV1Biz.ThirdPartySubmitRequestOrderParam.ThirdPartySubmitRequestOrders.StoreVO.Prods;
import com.icbc.api.response.mallb2CorderofflinecreateResponseV1;
public class mallb2corderofflinecreateTestV1 {
	
		private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
		
		protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRVgmyC7tV4L4dC5B/Hx/EIJFQwdFN6aB8Ia3AxhGgC/hAKfMohR7qDk0ZusNw1Af0ov7APl3tpz0U25+4aLGvy03Bu/eZHFK1feFxakZJtpGER6wm2EAd83cLoq15diIaIzOo8zgFuTbQu2TlIc4tDtr5ZffEe18eTD8asirZ8rtgqYmHq51cDmvBrHoWKHewkrU0fVjFdjiPglu0xg4PT4SHNYe29vWEkDnKg07YKF+t5u3EOyL3GrCQEFjJx87cWUVmGNehRtyT7qjNyyNiFXx4yngL7/WFc1ieMnPVzzOeHlLcRl99q22diO/Lv+i+E8oTzlzg+A233kV+jZtjAgMBAAECggEAT9xjP37K1gnE7djo8bF8lxz+PM4D4Tg7g3pOK9uq27M4Gqnyf5WGwz5Qh47kzh70sCzQp3qdynVXTxrPzVlaKisg8xACohO6LxiYxOWM8fxsQa/QnRe/Q7YA+FfP0ypi2tH1FSVw51Q7Fr+iVXYaARBkqx6o40KXpeJn2peEjHXM5SlZE0JKdeShfzvFzJVBClYLhGU7Zmtka8kFmBNCM3CdReH0lwyuSxcxfn4xq3wUqI6+0PU1qKrBsbRrYd3RIDvCrQ1kFQHiwwGgQ6I557KgIM1N+d6KvQQSDGlw36Q2JBqcbGJDGOUsIbfQaGKVqayttUXffLApcvnnjeAt4QKBgQDJ1ZEjJzr9DFjdqwVAvbRZFpxjEsONhUxQZFteJFGloLMqKPKDMzsQyruWBTPx+f0y/da0oBGCq1Uy6Fzbfdonq9WiZHoUy2mSMmNJdfNqN/fTM6uSAnP+FYy+EQ2hTIBEX4EY51cAQhcYuC4i3QL66QXcitKmBFiyTheK8SMzTwKBgQC4Vu38yxqgbH3pggO+ebqu/0EI5PR07K/t3IT1T3JCVqYEDOLrFio9cdKd32pf9JAeH9+3tRhuzkj1FCMWxbTut6GAT1UbQEhSR5SedbXKGRukwL7pK7dc5W5UmQ/MwaFa1xsE2O+aaXFvQ1UnwJ+npyTLQdBCoUC1166ZDr1hrQKBgQCcWzhOdUu7UN9fCefN2ahnA58vGRApCESwWq51Zclid/WUCZiljwCI56UeePVe1X0TDZZQ41ftVHFyOJQG7Z4c8X9UiGYSyeoE+cf01s01UhpIoVSejgvxoBwh+fAHLs6HKT1HX6AEcS6XVcdX9sMiQXbq/RhmyCM2Lk0Ks/fC/QKBgQCfvfUOE5NHYbzbFWfZL/OuolPhOUb96/2oymgNjIMfKadD60bZIaQJCxHQ7UJpVK1u7YAY2j4KB4kBmCmwGjPoigjR/+1oaBRD+PO5PZgDQi7KXp0RwmhrgAZUGS+v6DvDVJR9ZuB7ltRN7rNkd2/jANzJpoaqtsI00mrjB/rlIQKBgCp3oFsq4n6i91B0m5zhq6wBSQEoHq0MFh2p1F+UJgRdwUcmaesTOTJqIMHm/ASLIgHPsPzZldw4qNTOFxYFbp5ARqeIIgS5Bq4xn0vN+2cKssI2lmAZ3ksg6Aw/OPvnxKCobt9LsaiJY0Fz5mcoUsmZfPb8v5+2Mgkt8sFTDu8D";

		protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

		protected static final String APP_ID = "10000000000000005530";

		
		public void test_cop() throws Exception {
//			公私钥与AES
			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8", 
					"json",APIGW_PUBLIC_KEY,"AES","G242nUlMmiT1tcToX29xkg==", "", "");
			mallb2corderofflinecreateRequestV1 request = new mallb2corderofflinecreateRequestV1();
//			APIP路由入口
			request.setServiceUrl("http://ip:port/api/MalluserInfo/ForOuter/Service/offline/create/V1");
//			组织参数  参数类型必须为
//			{
//			    "thirdPartySubmitRequestOrders": [
//			        {
//			            "orderProdType": "19",
//			            "appId": "10000000000000005530",
//			            "platformId": "019",
//			            "remark1": null,
//			            "remark2": null,
//			            "remark3": null,
//			            "orderMerchantMemo": "饿了么商品",
//			            "outUserId": "0001",
//			            "thirdPartyParentOrderId": "00002",
//			            "thirdPartyOrderId": "0001",
//			            "parentOrderPrice": "19.00",
//			            "orderPrice": "18.00",
//			            "orderPayAmout": "12",
//			            "orderInvalidTime": "30",
//			            "payUse": "0",
//			            "noticeUrl": "http://ip:port:8010/mobile/order/createOrderforOuter.jhtml",
//			            "payBackUrl": "http://ip:port:8010/mobile/order/createOrderforOuter.jhtml",
//			            "payFailUrl": "http://ip:port:8010/mobile/order/createOrderforOuter.jhtml",
//			            "storeVO": {
//			                "mercId": "10001068",
//			                "storeId": "010077",
//			                "storeName": "test1直营店",
//			                "prods": [
//			                    {
//			                        "prodId": "9000002468",
//			                        "prodName": "重庆水煮鱼",
//			                        "skuId": "90000000000000003072"
//			                    }
//			                ]
//			            }
//			        }
//			    ]
//			}
			mallb2CorderofflinecreateRequestV1Biz bizContent = new mallb2CorderofflinecreateRequestV1Biz();
			ThirdPartySubmitRequestOrders  thirdPartySubmitRequestOrders = new ThirdPartySubmitRequestOrders();
			
			StoreVO store = new StoreVO();
			store.setMercId("10001068");
			store.setStoreId("010077");
			store.setStoreName("test1直营店");
			Prods prod = new Prods();
			prod.setProdName("重庆水煮鱼");
			prod.setSkuId("90000000000000003072");
			prod.setProdId("9000002468");
			List<Prods> prods= new ArrayList<Prods>();
			prods.add(prod);
			store.setProds(prods);
			
			thirdPartySubmitRequestOrders.setAppId("10000000000000005530");
			
			thirdPartySubmitRequestOrders.setOrderProdType("19");
			
			thirdPartySubmitRequestOrders.setPlatformId("019");
			
			thirdPartySubmitRequestOrders.setOutUserId("201901150000007609");
			
			thirdPartySubmitRequestOrders.setParentOrderPrice("19.00");
			
			thirdPartySubmitRequestOrders.setOrderPrice("18.00");
			
			thirdPartySubmitRequestOrders.setPayUse("25");
			
			thirdPartySubmitRequestOrders.setOrderInvalidTime("30");
			
			thirdPartySubmitRequestOrders.setPayUse("0");
			
			thirdPartySubmitRequestOrders.setStoreVO(store);
			
			thirdPartySubmitRequestOrders.setOrderMerchantMemo("饿了么商品");
			
			thirdPartySubmitRequestOrders.setOrderPayAmout("12");
			
			thirdPartySubmitRequestOrders.setNoticeUrl("http://ip:port:8010/mobile/order/createOrderforOuter.jhtml");
			
			thirdPartySubmitRequestOrders.setPayBackUrl("http://ip:port:8010/mobile/order/createOrderforOuter.jhtml");
			
			thirdPartySubmitRequestOrders.setPayFailUrl("http://ip:port:8010/mobile/order/createOrderforOuter.jhtml");
			
			thirdPartySubmitRequestOrders.setThirdPartyOrderId("0002");
			
			thirdPartySubmitRequestOrders.setThirdPartyParentOrderId("00002");
			
			
			List<ThirdPartySubmitRequestOrders> list = new ArrayList<ThirdPartySubmitRequestOrders>();
			
			ThirdPartySubmitRequestOrderParam str = new ThirdPartySubmitRequestOrderParam();
			
			list.add(thirdPartySubmitRequestOrders);
			
			str.setThirdPartySubmitRequestOrders(list);
//			将bean转为String类型json串的工具，可替换任意该工具
			String jsonStr = OBJECT_MAPPER.writeValueAsString(str);
			
			bizContent.setRequestOrdersStr(jsonStr);
			
			request.setBizContent(bizContent);
			
			mallb2CorderofflinecreateResponseV1 response;
			try {
				response = client.execute(request, System.currentTimeMillis()+"");
				System.out.println(JSONObject.toJSONString(response));
				if (response.isSuccess()) {
					// 业务成功处理
					System.out.println(response.getReturnCode());
				} else {
					// 失败
				}
			} catch (IcbcApiException e) {
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println(e);
			}
		}		

}
