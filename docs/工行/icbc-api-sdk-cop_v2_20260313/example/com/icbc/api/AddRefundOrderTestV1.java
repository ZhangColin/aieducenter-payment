package com.icbc.api;

import com.icbc.api.request.AddRefundOrderRequestV1;
import com.icbc.api.request.AddRefundOrderRequestV1.AddRefundOrderRequestV1Biz;
import com.icbc.api.response.AddRefundOrderResponseV1;

public class AddRefundOrderTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDN+/pxt5PKhjYAPc+G/rdx+XOUBEFtY7Fy6it3AunbV7Qeq36QxljKV/12dqNc4XQM/NLypGfFXuuvkP3hhvfS8AO4r1FCRbPjkEZqUe4RaUBnKbEaYl4Dq8G7hycmQWqmuBzdT5ANmBfu6AkHSirkEqCr0ghH3xiPYKgbPNWgI+PbSwiwOvx7UcuGHawxPRLEVerRlGy8o5FwPw4DDJnoTgkCqueTTQ9bTTAGNnl96PbTU3fHiPVdd0Q0eiK7cuSShjq8o0aPUzhCYshJYIKoL6AQksPSkSZFsq0A2O9lH86xbWXGj5LzjqVblp/Jd9MCMRA4y64YRGuGmFqvQvlTAgMBAAECggEBAJVeeAomeUGPeXFLYUYfR5+aqLlZLKjKdk9CiCpRLowkWTU0Xx9U9eYlUGOrPGOKCcGxRLc3iRiS8I3rsm7slKGd8tTmLSLFsOEAKk/HAcAWduFYVlkvo8U9s8rtAvK21UP4ltXVcDyrYg+Yq5NhsSCacXCnaeHUYdb23HCD+NWaY9S8WvT3kUTRpxfq8JzBvwst+yyJUOzVAXOMjtXD+YB7QHJCvyQ3m2bqHI15X2PyvbTWNxFfpxXozEfqT/Ec/DJT4gTyP+sz7YNzzI5UjyfKofUR8rcZ7Fqmdn0Ap11f4jvLM2FNTPqdeiiUYS9UEa3sfKPFXf+DAX0Ig+j8yeECgYEA7T9vWpvhiQUVUxSPyP4alyfs+ApBsbLe3fsrSJx7cRG2C3Tk0Ga5rSu9tfqCjd9AjrmLLbbScVdCl9hDsUDTujW9KOkRkgPx8BJHbzh50ni/GYXoJjKlYz7a6xccfhapdcgfdOm+yVn2IMdyTWX8779aJg0/ttNp1NqhhxtMF7UCgYEA3kPyAR1SJ/WFK6nCao9HTsnITX/e4tLru1yyXaOowefez3poSfHH2kDw1DjgY4WQzcMDVCFEmB6C65NF2aPsH9Rkdvw0kw11ynbnOcSu8CgPk342en9Wo05zK38KOa7IDkCw/zXNEbJckee7CJz7dAvXAjcVyuYgt/uy57zmYecCgYEAm4DUsAM0VOcsDwSPKiLDswfMXtf6L0saoU0cFb/beUUumVvJDoAGe+cLtde48ty+J86d1xzZ2shjMbviwq/HF2pZzhLWHWrQjZatk8zkrHvQZh5LB55yoRZ1z1map1qbQEQnui3ri3aXyOxxkyGiJFK79+z/Mgpod8fF0BSoT3ECgYEAmZF2hwCcQBGqRmXPd1KxoSVX6Pwdori6uUSt1Hb8ZaVwVAguQFD2lbzi6K63kuu5BgHkrd8ihrwmFuA1lc9lmQRlubQ+GxBkv1PyJP7ZJAN5551/xJlw4fqVJotrJDm37ekSofKpUr3aqdDjcGsObbq0fspSm1Lvbk9Q6D6jKycCgYEA5DV937sWji2NBQI6bBB1x0848ng1S+q1OFQIWsM+jwUJpH/yxTx+a5sS51m4GNdFxNqUyGBxFslf+319b4b5XrLuHFvS2ZE1Xo85qjvjlIBu84DBjTjT5I9lazlutpNzzH+hADbUazdxbePVa/tOyP2zBCpDlGpuhd4cWmRPjtw=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000004434";

	
	public static void main(String[] args) {

		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", 
				"json",APIGW_PUBLIC_KEY,"AES","G242nUlMmiT1tcToX29xkg==", "", "");
		
		AddRefundOrderRequestV1 request = new AddRefundOrderRequestV1();
		//request.setServiceUrl("http://ip:port/api/cfjs/api/pay/add/refund/order/V1");
		request.setServiceUrl("http://ip:port/api/jft/api/pay/add/refund/order/V1");
		
		AddRefundOrderRequestV1Biz bizContent = new AddRefundOrderRequestV1Biz();
		bizContent.setAppId("sh1");//平台商户标识
		bizContent.setOutVendorId("sh2");//子商户标识
		bizContent.setOutUserId("01541");//用户标识
		bizContent.setOutOrderId("1");//原平台订单ID
		bizContent.setOutRefundId("1233");//平台退款单ID
		bizContent.setOrderCreateDate("2018-03-30");//原交易日期
		bizContent.setPayAmount("0.01");//订单总金额
		bizContent.setRefundAmount("0.01");//退款金额
		bizContent.setNotifyUrl("www.baidu.com");//退款通知URL
		bizContent.setFixedCommissionOwn("0.02");//定额佣金退款金额（本行卡）
		bizContent.setFixedCommissionOther("0.02");//定额佣金退款金额（他行卡）
		bizContent.setFixedCommissionWx("0.02");//定额佣金退款金额（微信）
		bizContent.setFixedCommissionZfb("0.02");//定额佣金退款金额（支付宝）
		request.setBizContent(bizContent);

		AddRefundOrderResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			System.out.println(response);
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

