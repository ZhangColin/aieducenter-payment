package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.CloseOrderRequestV1;
import com.icbc.api.request.CloseOrderRequestV1.CloseOrderRequestV1Biz;
import com.icbc.api.response.CloseOrderResponseV1;


public class CloseOrderTestV1 {
	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDnzm9o79y/t2yEEEv/Bkw7WLKztC0wN5FR+I0KmdqMIf5IbD0gAE9+z+SaTDrD34XfZ3gb6vvZGmIrgX6/wbT0EnZo6fr2kFLysCIikh6ElbmuT9xhFOvmpNOHSHoJGgTs0BVK+RDWHK73ImHCmFPV6WCeAqQpUJBWkyJMVSyvL4OzQ5U8ZnPgDWlyVYchB2C7wvDsZOvBqmhPCWO6QSt6XFDBmQ+c5KrkZh0Kh5AMLh4o7jbmSzIgXqCXfR9s9YNS8a86MWGenRTJCxyBodxVxOjSdjRBfYgv/a0od+oVbw9eP7bToGtpMpylZ1cHFbjDxU2E2nIrCD1PXDCy1M3AgMBAAECggEACZcFVvriTReiwYGLrlpY34a8RviQnkrbJwMYySI5zLPihFVfFkFpVcHDnQBLDyVe04lUgnDAfz26rrm3N9VzAuXPK6tP5dBOJ56LLqecpkBF0qO2qqewjT/e8bcPrGFSr/TEgDAlfn6oRWXJI2yvMF+7cGkco/NWg3p3zxsoB6m7iptgprTVThEjmI8wg1l9pQasfC00ULrDLN5YVESzqP710COrc2Zj1+tl7GsyNrDJ61NYIzKgOdQjQac4WOFhx5hxwDMYCQ1PtjpwiK6/SOJw3ZoeEhHzjbqSyYpB3469/tucue49CZL+1SCdBqddBHwbYFFVUvzOcRVRbr6zwQKBgQDgIHt8Ptm/Vzkb9TICxWfDMm79BupbC2lxV2gk0KqMcz0+YBcs0vo3DMNbbxSRVQfgOGeoGe+8LV887DfKdUEud/tJnN7a736m0h6neLl38n5SlvSv1B1WEU8Fqi3/BakqX+qW2Tp6vAbfZx3Hhl/QQPLVDmYynAwUgTrhbqW1YQKBgQCWVwc/8oWmvTMe9cNIsjLDXgwQ8Yy0HMY9FGbxwJnvv3AOQ4S11aKuHQzdwpkiqiCb+/jBwZE85iv+VbweVw70FvAFbWj1dx6As61ko484XjYbYg9fxRjKHBZBSvI4WwPEjP9j54q6IIaunenqI8aD9fQn5jszZ3wa7zl8L4W3lwKBgAvrDkX8HZ4kKEBJWKB3GN5zZx2DaaDvyWqiiEI06mzPj/kqKPFUQsP8pMLO9FwKFoIg3bo9rtbl3sOhmBHnDFFJRg6TDfK8e5KpZ8HmFI7BUWBY8KgM8oZqIzac5xdBmM5NDW96G7b9Gqx0kkvS5HNxkLgtrNeSHr5UZOop9RvhAoGAKn0qJV6Le1ZZjlRy/qauRhzcWCWMWJMRS83qUkTWvLVkHAPlC+b29TJsongPA4cFdFBAO7JTM9zxbg4Y/pIoPcAF0eLcGROzYThmttyq64lkzVRnd++iU+ygNY/S6S5/4BrhSwDzaisrJUqoA25c+oqWmHgvHxYOMyaNHpXnur0CgYBIMMsnbRLO3rERjf6Nt+/a1OqjqgLGBLw5OAI+xPM9U9e1Yd3UDQh1TFK6McVLMBkr1nC97LnhbCC9GaiKm0ClO1PZL4q9cWhASpOhyFgkIokfEHEbLEsrWMCbWoizmXat9TvC3nFwZLVfUGTjh6TXUMLXyJqbIlFHOBRvwImMOw==";

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	
	protected static final String AES_KEY = "[需替换]应用方加密串，与工行API开放平台一致";

	protected static final String APP_ID = "[需替换]APP的编号";
	
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_KEY, "", "");
		
		CloseOrderRequestV1 request = new CloseOrderRequestV1();
		request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/close/order/V1");
		
		CloseOrderRequestV1Biz bizContent = new CloseOrderRequestV1Biz();
		bizContent.setAppId(APP_ID);
		bizContent.setOutVendorId("020001020461");
		bizContent.setOutOrderId("20180720100002");
		request.setBizContent(bizContent);
		
		CloseOrderResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}	
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	
	}

}
