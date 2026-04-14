package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.OrderModExpressRequestV2;
import com.icbc.api.request.OrderModExpressRequestV2.Data;
import com.icbc.api.request.OrderModExpressRequestV2.LastResult;
import com.icbc.api.request.OrderModExpressRequestV2.OrderModExpressV2Biz;
import com.icbc.api.response.OrderModExpressResponseV2;

public class OrderModExpressV2test {
	//测试用例
		protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----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";
		
		protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJ h0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB"; 

		protected static final String APP_ID = "10000000000004094635";


		public void test_cop() throws IcbcApiException {

			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

			OrderModExpressRequestV2 request = new OrderModExpressRequestV2();
			//测试用例
			request.setServiceUrl("http://ip:port/api/order/mod/express/V2");
			
			Data data = new Data();
			data.setContext("[深圳市] 快件已在 [南山科技南] 签收,签收人: 本人或前台, 感谢使用中通快递,期待再次为您服务!");
			data.setTime("2018-03-21 13:45:24");
			data.setFtime("2018-03-21 13:45:24");
			
			List<Data> datas = new ArrayList<Data>(); 
			datas.add(data);
			
			LastResult lastResult = new LastResult();
			lastResult.setMessage("ok");
			lastResult.setNu("484162855791");
			lastResult.setIscheck("1");
			lastResult.setCondition("F00");
			lastResult.setCom("zhongtong");
			lastResult.setStatus("200");
			lastResult.setState("3");
			lastResult.setData(datas);
			
			OrderModExpressV2Biz bizContent = new OrderModExpressV2Biz();
			bizContent.setStatus("shutdown");
			bizContent.setBillstatus("check");
			bizContent.setMessage("");
			bizContent.setAutoCheck("0");
			bizContent.setComOld("");
			bizContent.setComNew("");
			bizContent.setLastResult(lastResult);
			System.out.println(JSONObject.toJSONString(bizContent));
			request.setBizContent(bizContent);
			String msg_id = "666653";
			OrderModExpressResponseV2 response = client.execute(request, msg_id);
			
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
			OrderModExpressV2test sas = new OrderModExpressV2test();
			try {
				sas.test_cop();
			//ip:port:16257
			} catch (IcbcApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
