package com.icbc.api;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.icbc.api.request.BigdataStreamingdataPartnerdatarenterRequestV1;
import com.icbc.api.request.BigdataStreamingdataPartnerdatarenterRequestV1.BigdataStreamingdataPartnerdatarenterRequestV1Biz;
import com.icbc.api.response.BigdataStreamingdataPartnerdatarenterResponseV1;

public class BigdataStreamingdataPartnerdatarenterTestV1 {

	//1、appid
	private static final String APP_ID = new String("xxxx");
	//2、密钥对认证方式，公钥在API平台登记，此处为私钥
	private static final String MY_PRIVATE_KEY = new String("-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDoVP72jGjks5YToIy4RidV8nuhkJlRYHtWCtrpW1ApARDQ6WGl8YVMRftht0vIicnD3PZdQecFpjXGp3p3bSlsatxzVGDf4VVpu/B0/62FeiM9s5jBgTYxAp7Q0hImQx8IsSqjHpguNkd1J1rQT3Jx/xSfU25Cr6nVxTDTJ2pnskIkUSXCU8t/B2pIVDHTvWvF5VKzvS1kO68/mak5/HvOt1SE62PaT9OfRQYp728L2TRsQMGMiKiqzA0FcbDIYC4ki+OJzTlxccs9Ei3pILsgzDBOUpMOK5pT6Z4aFgWA3Wz2s7iGX9jP0mRCJ+AKcHytHReZVQ/hc2WHza0WKMmRAgMBAAECggEBAKAj/f4JOD/zvU1YC8b0rCZk1my908AwxbLAFrZkt3McBfBJ/YG5YUz6q9Rl2Hmo6Ab2ZmZPh6LwNv7v780j1DqjycJA9ek7U3lnDs5Gcd+gGWxtP6yZmLXdfYSoV9RBzcFbBO2cp/6qcUTCVKljKZqD3T4Y7tZOaL0th4L0FzbTatWxPLdOc9S+NWM1WrgFxa6oiKank03HO6TkJYde2ego7CWdS4VBz7ivQfX2IgXJC58Wgy0wabn7r0QwFSTayN2SjS6KJrYa6cyWOVRmv05wQE3elEs/h0UnnvCO3Znjv1gRf3kUOaFyxDIThbJhxfE0dP+jrXpwblGPiHCIgEUCgYEA+Sh+9FjO5IaGaWT0YY2Zgg+f0YdvJaW/0V6v/LiZpA6lnbC7/GLOAg77MkO5UziiEmY+VtHLfR+sJb4Kn+WKgWzWUKO4RutG+k9VSimp09Vm84Ufi1dvv2bQGHzGxslHf3Ic7sQq/R4fNZUseT+RdQg0xRlQeuhnSYYF9FjWIPsCgYEA7rY3ob+BUG3AGDYsDmxG7/L0H4ztd/772DAmwdVdX1jSPDfSWAsjjs1nDKAutSHIn0KPyXTtoUUDuxlSSGa/imJgmknDpr9w7KTEoxUtz5UR/q3yaED9S8KNPOp33x1QVxMMrYKw+ZthcSswo7s6M0rVE1zCT2cgJ8K0xsm0seMCgYEA8LrHrp3/h5zSURJRa8+7+YXPDEqmgOFcK5Yowyw4heIA8hLIDloIYMTIvw/gByA4tz5CDPA2g3YOhYjQCx1kbRgTGWaVrS+1LDNqXHVUuG7/QVnl2GNqfKuBc6dbBsWCZwiCG889GcH4tXSju6JHj6OWGLkUoSvaX5c2OZd7i/ECgYAdXIJZPdHtD6LKF5exqnXLO8n1O4NV+CIIBFwwCUtz19v8Fi2kMLKN9QvQzL8R/nbLyAchk2VLKc78q2+ogiLbJaCWOa58Cx4brWSWXpuE5eQRvFmNozjEoch3jqtx4Qwpuakcim+0M/13SwWvje7psjGlMQqhc6DL4zITJWo5WwKBgQD0War6I+Hnn81/SGtrTbH4/R74Sp4IGoNo+5jiCSFxfyJwJIx3g/UDkmBsMlS3RV2FctB36nC3o8MQkMD3Api7nZF8DiLkYUs7wwC93kkLz7HoANOr16pvNyfcgjjALb4shcWvStwr0JbymZd756yU9qgnoVh9qcjEORW6qZogOA==");
	//3、网关公钥
	private static final String APIGW_PUBLIC_KEY = new String("-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB");		
	
	
	public void test_cop() {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		BigdataStreamingdataPartnerdatarenterRequestV1 request = new BigdataStreamingdataPartnerdatarenterRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://xx.xx.xx.xx/api/bigdata/streamingdata/partnerdatarenter/V1");
		
		//5、对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		BigdataStreamingdataPartnerdatarenterRequestV1Biz bizContent= new BigdataStreamingdataPartnerdatarenterRequestV1Biz();
		bizContent.setBusinessKey("123");
		bizContent.setSenderApp("OAAS");
		bizContent.setBusinessType("OMS_METRIC");
		HashMap<String, String> reqmap = new HashMap<String, String>();
		reqmap.put("amount", "10000");
		reqmap.put("busiflag", "1");
		String data = JSON.toJSONString(reqmap);
		bizContent.setReqData(data);
		request.setBizContent(bizContent);
		
		BigdataStreamingdataPartnerdatarenterResponseV1 response;
		
		try {
			response = client.execute(request);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess() ) {
				//6、业务成功处理，根据接口文档用response.getxxx()获取同步返回的业务数据
				Assert.assertEquals(response.getBusinessKey(), "123");
				
			} else {
				// 失败
				
			}	
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
		
	}
}
