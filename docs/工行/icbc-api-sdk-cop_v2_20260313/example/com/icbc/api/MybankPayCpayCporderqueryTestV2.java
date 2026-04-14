package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCporderqueryRequestV2;
import com.icbc.api.response.MybankPayCpayCporderqueryResponseV2;

import java.util.Random;

public class MybankPayCpayCporderqueryTestV2 {

	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123";


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		MybankPayCpayCporderqueryRequestV2 request = new MybankPayCpayCporderqueryRequestV2();
		request.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cporderquery/V2");

		MybankPayCpayCporderqueryRequestV2.QueryPayApplyRequestV2Biz bizContent = new MybankPayCpayCporderqueryRequestV2.QueryPayApplyRequestV2Biz();


		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(msgId);

		bizContent.setPartnerSeq("QQQDE1220120510246");
		bizContent.setAgreeCode("123");
		bizContent.setOrderCode("order0003");

		request.setBizContent(bizContent);

		MybankPayCpayCporderqueryResponseV2 response;
		try {
			response = client.execute(request,msgId);
			System.out.println(JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
				System.out.println(response.getReturnCode());
				System.out.println(response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
