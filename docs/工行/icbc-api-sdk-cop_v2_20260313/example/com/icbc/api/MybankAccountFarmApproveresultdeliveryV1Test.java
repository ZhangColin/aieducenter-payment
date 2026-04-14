package com.icbc.api;

import java.util.Random;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankAccountFarmApproveresultdeliveryRequestV1;
import com.icbc.api.request.MybankAccountFarmApproveresultdeliveryRequestV1.MybankAccountFarmApproveresultdeliveryV1Biz;
import com.icbc.api.response.MybankAccountFarmApproveresultdeliveryResponseV1;
public class MybankAccountFarmApproveresultdeliveryV1Test {

	/*protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTwp6dfC4QDiD6zKp1HaFtJQ0h8ifCEZDPMEgD/fbUQF2PxI/toBbW8rGaeNJOry5Akxj/wAHzGR3yxAnHNlk5DANxknjkyGtiJCSxjaMahOXPwiPWUnKXF4VrgDCh4wgKB0lWnZtajr0+ZgI3IrkggPy/CLQI+UGrf0DsH7/zjgTeMrmP/Cs12qh9FImm51OfYZlWzJKEJv5k60pAPfeSXWyOnxA7lAxDZMXnT1VE2AcgGLowyxxFXISVcZqoB5b5mJfBjNFdhn7EO4YMlaZ51RO75BYE4hImggreg1ZwBUboeCaI42cTZsbrmbkRRyPnQTt45eqB0Op7LMmL7XnlAgMBAAECggEAMRQLqzN7+Uk4GxWv6IaW/7G20vejzQoYlzjqMqlAOu/GI+KeC8eIzox+AVjwQcJeEoTJYOldvGkzUVdcCBc9O32KB0cncJZBVK+8oFzzN/O8gW1yidWIyja90h8aCCtomCwjZ+mhmEqhAQDQ649U/j7ZtHonknN4JIZ4AfXpw/N+5GxSiiVOs9Fw11Lo3re0VDxfj67+a1uSQSWD/UEUFnd7+Se9nlePIbK017zXO4Gl2i41ZWSsW4WMhbVpsT6iP7Jv6Sg1XhtFG7yXpfoLD9N1T2qyrAucgm01Jwpe+mEDPU9whdrY1tDnfKvbGSxlVQV5UIv7ZONfnoTDlqngAQKBgQDE3jFxo00v+puYkWpyJMoRMfTm2DwHwIektBF1Tm6qcZ9y1ZstiNOw3ujMtYXR/lRSD0kfxmXfheBQWnP9gmm1CGUSJacTEpShBAeOWyD7qnRg05TlyiChJ9mqWYSVzGM8GkMNfNNQtv84oXMZqbQXCalKHTjhWrImozvzCo4vAQKBgQDAJGCZayII9E5XyoDJfohKSLlUjUIxc6DiPnLYNHR+zyFMagkrKi0NehoM1D+xd1VO8MZjVaw/2bZAtIXyfv3melG6KL0F0NFGki0nXPMCMPWSajzpZlYWCh2cBi1AuXXZ5ut0z1byfumZO1csF/xw2/YLoWxatmxUnXaxfYtu5QKBgQCh4BHH3v8Y9Hf8e5WLRbThbrJxHlw+A5GFXIr+af+VJth1FC8Ewf1O8ft6V8gxY27yCp+WAQzDPtGzQK0fRpykvsJcqZk3lY+g1i9gXo5AYWhQNam5SPqLiLjx2amrguQWB4IbFQis1cwGRLrf3yq1xrvstRqQMoNKQe6JaaSAAQKBgGE9R8H87QGZGljlvB29Mp5dhaWbw6chPIejUR3IFjji/JgehbtZoelTJQHfHMjkesDzq4M0JXM+7DR859kcmcRdnSTYXyklT/QmIEHukXFHc9CAgYkhaGnI/skqexZphZIcgobgM8GPpwsMdAZ1sSUwvKmQLTUPY23DLwGOZtVdAoGAc6wHRlu69M/5oLXtnu9wr0IaIm0KhxBv65lOZrJOXDSlC5A7hJL9PovwoYACBmgiA9l4xP2lXNDa+kLv+WN7o7J+FVmCRWTrI7wJ4pVd+abFKU20FbS0yrfwWM1noircOqFzEZMYUFf4zhL9FWd9svb1nfI1LN831dIDJ1MPsts=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000004096941";
	*/
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		// 签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		//DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		MybankAccountFarmApproveresultdeliveryRequestV1 req = new MybankAccountFarmApproveresultdeliveryRequestV1();
		
		// 设置请求路径
		//req.setServiceUrl("http://ip:port/api/mybank/account/farm/approveresultdelivery/V1");

		req.setServiceUrl("http://ip:port/api/farm/pay/approve/callback/V1");

		MybankAccountFarmApproveresultdeliveryV1Biz bizContent = new MybankAccountFarmApproveresultdeliveryV1Biz();
        bizContent.setApplicationDate("20200304");
		bizContent.setBatchNum("111222333");
		bizContent.setApprovalStatus("5");
		bizContent.setBusinessType("CONS0001");
		bizContent.setCorpCode("inspur");
		req.setBizContent(bizContent);
		/*
		MybankAccountFarmApproveresultdeliveryResponseV1 response;

		Random rand = new Random();

		try {
			response = client.execute(req, String.valueOf(System.currentTimeMillis()));
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("success");//
				System.out.println(response.getReturnCode());
				System.out.println(response.getMsgId());
			} else {
				// 失败
				System.out.println(response.getReturnCode());
				System.out.println(response.getMsgId());
				System.out.println("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}*/
		
	}

}
