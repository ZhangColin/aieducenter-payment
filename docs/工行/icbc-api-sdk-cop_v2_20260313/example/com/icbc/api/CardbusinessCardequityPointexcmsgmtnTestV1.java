package com.icbc.api.test;


import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.CardbusinessCardequityPointexcmsgmtnRequestV1;
import com.icbc.api.request.CardbusinessCardequityPointexcmsgmtnRequestV1.CardbusinessCardequityPointexcmsgmtnRequestBizV1;
import com.icbc.api.response.CardbusinessCardequityPointexcmsgmtnResponseV1;


public  class CardbusinessCardequityPointexcmsgmtnTestV1 {


	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //重新生成	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCttyY70CuUSlB/AcW3rwqreM7wYrTFj4UcCrktktGh+L03A04YwzTjRI/U7eD8JAmt14Ln17MOM04uoozcit5zxsKNahITYWuRmi+9WEjvmWPB5a2unFt7I6AsH9BPNjUyQ4Q2ZsOhzKK8d8X63WXtmBe1SI7rcY/kFi9DJ+wg8L4vp5o9eHXr8zbGdXiwx6mPPuak+XPuYAPmQKBYZtMJJQMqxxbUTje2X3WNil0JB2zS3S9joBw6wiFla/YlpK8p9JL7lbPFLpMYvbSiuF8BacCXGoO6/XnMZz7SHr/RKB/f33W+2qyO52JvvJMYy4kP9WdHQ06HHmUfM0cTqHyBAgMBAAECggEAbTedPjXfcFhYy8FdgeL3nl9SHCiy5xMY6UyEqHaKfn9Y12GolEEG22yb0Y/NyDkQLNA2aXVgCimPmhpw3aghvEDWyPpLawvxCZYbNbxv/tQ9uAajcAi2K3F2nsdRvLukLnnsgheSIAP8vOnA+7x+/SaGoV+BG95GTWz1Qg+zkfFBMhTR42stCgBpHWCU2CEFInuLkAz7fXCGvckLEpO7d+YqjwBM7bgmi2th6K571GWGBABTYOva+mzl96Fzvt27PZaemYzwIrQKieEHvqzQEz+LgwaD65sDpfcUDmwaMlIMZ/SUBIt/QC498vlvHIBR5z+aeu1IpY/bRq8oSl57AQKBgQDVC7yKiAPasKAogkDpyVvu+9ZSmxK4msxsP++xEVc7CGzrDALGe1jKRxPIU7o5wP5HZtXf6FU64xhF/XeI4fGEk865rF0Xkqk80+ydgKuJND/r1gvRi8CoTO8m+xBnyoFkXmFjQPHvo1Fkp9uNx03gUU4T6ygUUn2RVO4ayW13CQKBgQDQvWQwotwP7eBxqcPEKEXYuW2C1/hngrh4uC+gsrPAyYNEvz42kD9ioHc1g3jHrxz852EOA4kikIzm7yDQ9/EiUKZ04bORMK5CrG1fzU0vRKENuUQiYL/RUWBr56f5jHPkrE5oBnlA3H3SbnDNHbPyHEL2coyUbOPSrkvwaYB/uQKBgHEQqn4m00zTohv9jK/GUokRlFwAur3IQadNg0rIti+5N/zCQhLOUYAAmsodTV7rQsfoFqefKOR4s2Apta+qob/HpdW6WSlyh2il3BR2vhKx7R6fWWRbM+Ld+aoJYlqDCYqX4D2JsRyJ4191CjtDQ9xBqhs+GOY4JTXlCfyjW/npAoGAE9lupZFXC8y7AxP5obWSrsEzQLX40R8FG7WoEcfsf/CpoufsqpOc82v+6f/OhG6KeyIlgIelirvkKjGjFTv+mhzBN2we/zd52q062VlUdaI5Dm0HpZ09oFH0sU4eEwTgIGaiLhIvhZNyA4itAVRJwEQfuf+6joKsR2R7pCjkJ0ECgYAfkmxzAIiJ/6RABRp8fDNGER+gZz11l0YP4NBJZygTP0qRyub91sfxLYkMVtXtbTaBLcx1G9I5saV25zSr9g4UcGBFht3CqzyW7jUzheWCorZPP4DiJaYZvtgMkQgbY5Gr/tRR9AVgDDSKKfYDrfUQuPnsyX9LKdoMgB0EbrTOnA==";

	//APP_ID：工行API接口的ID
	protected static final String APP_ID = "11000000000000003719";

	
	public static void main(String[] args) throws Exception{
		CardbusinessCardequityPointexcmsgmtnTestV1 a=new CardbusinessCardequityPointexcmsgmtnTestV1();
		a.test_cop();
	}

	
	
	public void test_cop() throws IcbcApiException {
		CardbusinessCardequityPointexcmsgmtnRequestV1 request = new CardbusinessCardequityPointexcmsgmtnRequestV1();
		request.setServiceUrl("http://ip:port/api/cardbusiness/cardequity/pointexcmsgmtn/V1");
		CardbusinessCardequityPointexcmsgmtnRequestBizV1 bizContent = new CardbusinessCardequityPointexcmsgmtnRequestBizV1();

		//操作类型（1-兑换积分，2-退货）
		bizContent.setOperType("1");
		//活动ID
		bizContent.setActivityId("3");
		//工行订单号
		bizContent.setOrderId("020001049032000722809200084735");
		//合作方订单号
		bizContent.setOutOrderId("1");
		//商户编号
		bizContent.setMerNo("020001049032");
		//持卡人会员号码（兑换积分场景必输）
		bizContent.setProgramAccountId("1");
		//权益合作商代码（兑换积分场景必输）
		bizContent.setProgramCode("EMIRATES");
		//持卡人名（拼音/英文）
		bizContent.setFirstName("");
		//持卡人姓（拼音/英文）
		bizContent.setLastName("2");
		
		request.setBizContent(bizContent);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessCardequityPointexcmsgmtnResponseV1 response = client.execute(request);
		
		System.out.println(JSONObject.toJSONString(response));


	}

																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																														
	
}	
