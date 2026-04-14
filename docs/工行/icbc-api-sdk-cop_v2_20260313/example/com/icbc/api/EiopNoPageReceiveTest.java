package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.response.EiopNoPageResponseV1;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.EiopNoPageRequestV1;
import com.icbc.api.request.EiopNoPageRequestV1.EiopNoPageRequestV1Biz;
import com.icbc.api.request.EiopNoPageRequestV1.RecipientInfo;
public class EiopNoPageReceiveTest {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQSNADGDFHDSZDSAEPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	//2、appid 通行证第三方测试1 (行外)
	protected static final String APP_ID = "10000000000000011239";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥 行外访问
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEQEFSCBKcwggSjAgEAAoIBAQCO/Q2D4B77lM9GbN5n7NTkqIHloL5RJh8hh4Hbt8dWGj19pTmtDucMn7alRQk99DHW230So/OgH6OiX5GVGNKfH6I9ONEuJ69Oq7lXg9KvUg4LrsI6HhiMdqVhUx0/5YXCaNBP3yj0GJH2VEHtD9iJkqbT5K42SM0alwEvqZ3GQ30ADFNT73xif+/qK/FHqM9CaS1m1EzgXhITeHZ1sgCoXWz3oJjtv5+K3XFXg44h3sVDwUKGMOMMg+EyZMU8pg+pDxoAnD9W3vKAhWN2myVedzP2Ua8xJMPQwB3IaBHhTtD07j7Lskg995D+3HLEOg4w2F3n/V9a0o2U6A/4r93bAgMBAAECggEALof88PBrRLWdai6dhbZVZX/Mnbxckq3ULaYaRTUeIk/xuMSe60lRORN0bX30zvt7iI7uubUu3+nL51gwWRO13uB9fGzT7bAuZWyOqQMV+cW7GmQpgp4uoO8vTGeuug9oIubW+1cEzcJ9JWsv9QRWFBbsQUJGyxWP4VpIoYkR9eWdARrnDN4Oc3tQo6Mn+cE6rykm7eLxtGq8BK5B+ktUYx12nEMtVwOvkNbdY+ryIa/Zqh8+vNj7MCEOZXDFMhNCEFH0SlNppcF6JN6tj47RIgumAH7hiAcVJREg3oz3ScI3QmDWG0wZfjDMNwlTzpRX7rLr9THRzxIGcikFz+p6wQKBgQDYK+wQsPsNUZ8ZYjSRcUJdrDuyJGEWe0AqOpJ8f4Q9jc/XIPmrgBbQ9lWwcT4NLzsQ4Ls0zCG/pPbikrJ2x5HjtjhhD6hqd6T5jK4RpokDdQU4LY3ib7H4aaLFr9/p7az2DVYfndWeuEiopCouponResultQuery4YblBcqZzPOy4KshdgCl7WyKj0iTewKBgQCpVVVfy8QB9YZjw/+jMMjX+OvCydDVSzhIxWOlVHN4e/a6UhNDrwS8O4G2hN7cUsa2/e5DpUvdKId0ZZXTjgE3ZJ7NcKA9MWOsjsLfUeip8y7Jeb8tmfw+yo+VMqQ/D9Wmd0mpIm6dsjfwsuRfvD+46wTjx7FrASmPi7yUrlYhIQKBgEsG44XfhyLWQ1vyA8C/CIOudYGcsr20xU5iydjAAUvD3nESFtV5PXIFLTRx+VFmJR/0f8URXF1BihaVR6+GEJJx+htl7dQV9oJOW1Pw8vjqMoeO+AGxNpm7QC1Qn2x3e2JiupN6mlvfIT49Qi5W1jyPL73T0FFqvUrZnqRwmoXlAoGAVOM4d1uZGTpScrUQ9GhCZzKcTfwKOsMIpkJ936XkdSP/3gvDKI0WnJ5gOnWFm4HLqHTH+xVkYKwuq4U5hOKNbY19ppOtYL/freOrgXR4ZKpKbCoiUuP89iVpsWu/tvVjL55hSO6/8UIklP+5SN0APN1kiCPVp0Tf5twwX4C+IMECgYEAzdo0jQjqz/ijnQDAFHw/pgAnbUIxp0nXnjoPjBRM9vonD0z/3BrvvRyldglo6urWRx1v+/ONkbXGSEpmWESOaRKRCiIZpcf294fAgKHDubMKpSJqu5WMfJx6Ffx5s+WmTHGcboAXEabMM87iE1nUF6eRnRTeT6SWtL5DKEgGYUs=";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		EiopNoPageRequestV1 request = new EiopNoPageRequestV1();
		//4、根据测试环境和生产环境替换相应ip和端口
		//行外流程
		request.setServiceUrl("http://ip:port/api/eiop/coupon/noPage/receive/V1");
		
		//5、请对照接口文档用bizContent方法对业务上送数据进行赋值

		RecipientInfo  recipientInfo=new RecipientInfo();
		recipientInfo.setInputMobile("15203728620");
		recipientInfo.setAuthCode("");
		recipientInfo.setAppId("");
		recipientInfo.setType("2");
		recipientInfo.setJsCode("");
		recipientInfo.setOpenId("wxd05c3a50bc9c5679");
		recipientInfo.setAppId("ocW7F5JPr5N_qJKvgX_dr6c5nsJk");

		EiopNoPageRequestV1Biz bizContent = new  EiopNoPageRequestV1Biz();
		bizContent.setUserUniqueType("152036668888");
		bizContent.setUserId("3");
		bizContent.setCino("020000234674406");
		bizContent.setCustMobile("152036668888");
		bizContent.setChannel("315");
		bizContent.setTellerno("888899992");
		bizContent.setBrno("00001");
		bizContent.setTimeStamp("20230713085537300");
		bizContent.setClientIp("0200");
		bizContent.setMac("0");
		bizContent.setEntranceFlag("34");
		bizContent.setActivityId("22181");
		bizContent.setCouponId("7694787");
		bizContent.setPeriodStage("0");
		bizContent.setRecipientType("2");
		bizContent.setRecipientInfo(recipientInfo);
		request.setBizContent(bizContent);

		EiopNoPageResponseV1 response = new EiopNoPageResponseV1();
		try {
			response = client.execute(request, "msgId");
			// 业务成功处理
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("appStatV10:" + response.getAppStatV10());
				System.out.println("result:" + response.getResult());
			} else {
				// 失败
				int returnCode = response.getReturnCode();
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
