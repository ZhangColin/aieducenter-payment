package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQueryissuancebillRequestV1;
import com.icbc.api.response.GyjrB2bBillQueryissuancebillResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bBillQueryissuancebillRequestTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1lOvR6gsDBrlMiDz+lR8w98PMZzOGlXo64LDmfLthpii4K5+a5zImro08bmfmdH1cmtNckjO10w1FYfTYJsUE6DTcqOuA3cVNeEATyH1Xynqp/9ZSblOFjTx1tqRLw0lJvFlyw9krY421mJ0bJwiP6fp4VKuloYvT5/tMek4oXX7HuxPPsdEHOePStjoOpB5lo9SwJVCdTx8yWuEJnkrClzrfYs/AF60peAb1GNrPC+roXf0aY0FXIIUz1GGWb99JDejup7N1lJc4Ti8DZI3lF2YSwf4LxrPVf/CAtMlG7ECeMOTeCpI5jzLwHOOryF/fGVTcp5FSTiSwhPz6JczDAgMBAAECggEAYgPxB25+YzPN8jHYOtPj8lrLphpPBebKA94ln0TVIPDAKiniDYue1qKCOoyeKRfYU5R/YwjIQO2bzEgEEmlbEDsHzdSgyv7q8dZnQEaI4kq5y+n9lqFXFFAYwrHEHeFcPiEdZ4WwRGuIou2uYGnBhW2Xdjy+Krn48HnYfH/IkKB9ENyXVBwIOKioMZKVgWPhqLrC9vUJuv1oRLdgIFLpxrFS9E+hg1mY6M2mQRofiJZc6h8As7RIw4RfQIicjQLe3j7PGqW3hwj4dLL2r/iQiiDOTsat1R8y5KXQ9OwgCrwKh745Q/iRcs8+lecgLPETILL1l7YkWRdlvq8vf7KJAQKBgQDwvQbejH8jom/XUoxRYw1yB5KbxxX6PiVtIY1X0MJakILtAMRewDhOM9MmlKdHJPwWUei+o5aM8OTVdRwF53Z4C4qnIYWSh446DHv4rGm13cJ7+huLnUIwchVnWDNnXDxJq/UP0r0vgyiVxYdxA0aZhk/ZzMscZeipdYBfoFJ9GwKBgQDBF9VhBie0To1fGM5Fnduor9ZRrQaIY/ljx6Enypwu/0kIPgG+Fy4/VTwZHsp+sAo7ofNBVs6VMmHgG4XIBG1zCtDaGwWDmhPMdQe8+eT09JCZKnHnwIPCsdrsE5HJudrEUifo8XgSKOgqu9ouFZEsdMNMcHTboUhthiBqh4uxeQKBgQChdfZMfyiqisC+RvQrhea3qxKOfllkLBhH+G/nDPG9ch0Nviz2a6+X67RbtOn4knqssaULb4aC4vJnpBL2dsaDPyJwc9A1dctTbZocL+76dlCBfD7LRd7lxS5L0OExGrCorLdBNJOEcVBgG0p65rqML4QQzQUQ99yzA0blXA7XLwKBgQCH0c3ja0LuTvqd04Lz8O+bHePwm3Ym5bt79eVjad6mKCckxwjOvttm7PtS6BejOQxZmbbTVM4Gn+O8kV9uq76Y1xjE8ZDVWsFmZUNGJ7hmnMhBFFv+mamUfynwOGupqzM4OFmePosoquFzfaMgG0d8L71XvRpVioK5l0ih5inacQKBgCYunQNafcPXrupq5rzjpI2GlYEZKwOLnQHt+St/fWLzZ746SGurrCjC7uQjnxEPrha0ZctfeHLuM5adSEMho2izcrYV0GZrB6FZ+ykVWI3BZtpbqqkeEOBCHELcVBMUk/NP3JKEV2BfR6sdXLPXTEysXHcDJNuyjk6Ek6h8yOc0";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16" +
			"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL" +
			"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP" +
			"3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000004097878";

	static Logger log = Logger.getLogger(GyjrB2bBillQueryissuancebillRequestTestV1.class.getName());
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		GyjrB2bBillQueryissuancebillRequestV1 req = new GyjrB2bBillQueryissuancebillRequestV1();

		req.setServiceUrl("http://ip:port/api/gyjr/b2b/bill/queryissuancebill/V1");

		GyjrB2bBillQueryissuancebillRequestV1.GyjrB2bBillQueryissuancebillRequestV1Biz bizContent = new GyjrB2bBillQueryissuancebillRequestV1.GyjrB2bBillQueryissuancebillRequestV1Biz();

		GyjrB2bBillQueryissuancebillRequestV1.TransinfoBiz transInfo = new GyjrB2bBillQueryissuancebillRequestV1.TransinfoBiz();

		transInfo.setProtocolCode("GYJR20211204102832000000002");
		transInfo.setPlatVendorId("64854126126589");
		transInfo.setPageIndex("1");
		transInfo.setPageSize("10");

		transInfo.setCdTp("AC01");
		transInfo.setPackAmtBgn("0");
		transInfo.setPackAmtEnd("10000");

		transInfo.setDueDateBgn("20220101");
		transInfo.setDueDateEnd("20251231");
		transInfo.setIsBillPay("0");
		transInfo.setUkeyId("81367173515");

		List<String> packNoList = new ArrayList<>();
		packNoList.add("16151545454");
		packNoList.add("89781525455");

		transInfo.setPackNoList(packNoList);

		List<String> drwrAcctIds = new ArrayList<>();
		drwrAcctIds.add("612561505");
		drwrAcctIds.add("0311545");
		transInfo.setDrwrAcctIds(drwrAcctIds);


		bizContent.setTransInfoBiz(transInfo);
		req.setBizContent(bizContent);

		GyjrB2bBillQueryissuancebillResponseV1 response;

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		try {
			response = client.execute(req,msgId);
			log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				log.fine("response.getReturnCode():"+response.getReturnCode());
			} else {
				//失败
				log.fine("response.getReturnCode():"+response.getReturnCode());
				log.fine("response.getReturnMsg():"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
