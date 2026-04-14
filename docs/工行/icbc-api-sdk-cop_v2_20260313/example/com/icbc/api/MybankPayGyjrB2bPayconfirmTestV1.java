package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayGyjrB2bPayconfirmRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bPayconfirmResponseV1;

import java.util.Random;
import java.util.logging.Logger;

public class MybankPayGyjrB2bPayconfirmTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1lOvR6gsDBrlMiDz+lR8w98PMZzOGlXo64LDmfLthpii4K5+a5zImro08bmfmdH1cmtNckjO10w1FYfTYJsUE6DTcqOuA3cVNeEATyH1Xynqp/9ZSblOFjTx1tqRLw0lJvFlyw9krY421mJ0bJwiP6fp4VKuloYvT5/tMek4oXX7HuxPPsdEHOePStjoOpB5lo9SwJVCdTx8yWuEJnkrClzrfYs/AF60peAb1GNrPC+roXf0aY0FXIIUz1GGWb99JDejup7N1lJc4Ti8DZI3lF2YSwf4LxrPVf/CAtMlG7ECeMOTeCpI5jzLwHOOryF/fGVTcp5FSTiSwhPz6JczDAgMBAAECggEAYgPxB25+YzPN8jHYOtPj8lrLphpPBebKA94ln0TVIPDAKiniDYue1qKCOoyeKRfYU5R/YwjIQO2bzEgEEmlbEDsHzdSgyv7q8dZnQEaI4kq5y+n9lqFXFFAYwrHEHeFcPiEdZ4WwRGuIou2uYGnBhW2Xdjy+Krn48HnYfH/IkKB9ENyXVBwIOKioMZKVgWPhqLrC9vUJuv1oRLdgIFLpxrFS9E+hg1mY6M2mQRofiJZc6h8As7RIw4RfQIicjQLe3j7PGqW3hwj4dLL2r/iQiiDOTsat1R8y5KXQ9OwgCrwKh745Q/iRcs8+lecgLPETILL1l7YkWRdlvq8vf7KJAQKBgQDwvQbejH8jom/XUoxRYw1yB5KbxxX6PiVtIY1X0MJakILtAMRewDhOM9MmlKdHJPwWUei+o5aM8OTVdRwF53Z4C4qnIYWSh446DHv4rGm13cJ7+huLnUIwchVnWDNnXDxJq/UP0r0vgyiVxYdxA0aZhk/ZzMscZeipdYBfoFJ9GwKBgQDBF9VhBie0To1fGM5Fnduor9ZRrQaIY/ljx6Enypwu/0kIPgG+Fy4/VTwZHsp+sAo7ofNBVs6VMmHgG4XIBG1zCtDaGwWDmhPMdQe8+eT09JCZKnHnwIPCsdrsE5HJudrEUifo8XgSKOgqu9ouFZEsdMNMcHTboUhthiBqh4uxeQKBgQChdfZMfyiqisC+RvQrhea3qxKOfllkLBhH+G/nDPG9ch0Nviz2a6+X67RbtOn4knqssaULb4aC4vJnpBL2dsaDPyJwc9A1dctTbZocL+76dlCBfD7LRd7lxS5L0OExGrCorLdBNJOEcVBgG0p65rqML4QQzQUQ99yzA0blXA7XLwKBgQCH0c3ja0LuTvqd04Lz8O+bHePwm3Ym5bt79eVjad6mKCckxwjOvttm7PtS6BejOQxZmbbTVM4Gn+O8kV9uq76Y1xjE8ZDVWsFmZUNGJ7hmnMhBFFv+mamUfynwOGupqzM4OFmePosoquFzfaMgG0d8L71XvRpVioK5l0ih5inacQKBgCYunQNafcPXrupq5rzjpI2GlYEZKwOLnQHt+St/fWLzZ746SGurrCjC7uQjnxEPrha0ZctfeHLuM5adSEMho2izcrYV0GZrB6FZ+ykVWI3BZtpbqqkeEOBCHELcVBMUk/NP3JKEV2BfR6sdXLPXTEysXHcDJNuyjk6Ek6h8yOc0";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16" +
			"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL" +
			"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP" +
			"3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000004097878";

	static Logger log = Logger.getLogger(MybankPayGyjrB2bPayconfirmTestV1.class.getName());
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		MybankPayGyjrB2bPayconfirmRequestV1 req = new MybankPayGyjrB2bPayconfirmRequestV1();

		req.setServiceUrl("http://ip:port/api/mybank/pay/gyjr/b2b/payconfirm/V1");

		MybankPayGyjrB2bPayconfirmRequestV1.MybankPayGyjrB2bPayconfirmRequestV1Biz bizContent = new MybankPayGyjrB2bPayconfirmRequestV1.MybankPayGyjrB2bPayconfirmRequestV1Biz();

		MybankPayGyjrB2bPayconfirmRequestV1.TransInfoBiz transInfo = new MybankPayGyjrB2bPayconfirmRequestV1.TransInfoBiz();

		transInfo.setProtocolCode("GYJR20211204102832000000002");
		transInfo.setBusinessNo("181814");
		transInfo.setPlatVendorid("871848");
		transInfo.setOrganizationCode("156145");
		transInfo.setAgreeCode("0");
		transInfo.setOrderCode("0");
		transInfo.setPartnerSeq("0");
		transInfo.setPartnerSeqOrigin("0");
		transInfo.setPayAmount("1");
		transInfo.setOrderCurr("1");
		transInfo.setPayeeSysflag("1");
		transInfo.setPayeeAccno("0");
		transInfo.setPayeeCompanyName("1");
		transInfo.setPayeeBankCode("1");
		transInfo.setSubmitTime("1");
		transInfo.setOrderRemark("1");
		transInfo.setReceiptRemark("1");
		transInfo.setPurpose("1");
		transInfo.setSummary("1");
		transInfo.setOperType("302");
		transInfo.setPayerMemberNo("1234567");
		transInfo.setPayerMemberName("PayerMemberName");
		transInfo.setNote("note");
		transInfo.setCrmemberNo("1234567");
		transInfo.setCrmemberName("CrmemberName");
		transInfo.setTradeName("TradeName");
		transInfo.setTradeNum("TradeNum");
		transInfo.setTradeUnit("TradeUnit");
		transInfo.setRealpayerAccno("121");
		transInfo.setRealpayerAccnoName("121");
		transInfo.setRealpayerBankName("102");
		transInfo.setRealpayerBankNo("102");
		transInfo.setFundDealDirect("1");

		bizContent.setTransInfoBiz(transInfo);
		req.setBizContent(bizContent);

		MybankPayGyjrB2bPayconfirmResponseV1 response;

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
