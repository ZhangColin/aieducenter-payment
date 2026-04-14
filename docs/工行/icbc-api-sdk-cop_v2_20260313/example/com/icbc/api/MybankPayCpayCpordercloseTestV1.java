package com.icbc.api;

import com.icbc.api.request.MybankPayCpayCpordercloseRequestV1;
import com.icbc.api.request.MybankPayCpayCpordercloseRequestV1.MybankPayCpayCpordercloseV1RequestV1Biz;
import com.icbc.api.response.MybankPayCpayCpordercloseResponseV1;

import java.util.Random;

public class MybankPayCpayCpordercloseTestV1 {

	/**
	 * @param args
	 */

	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123";


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		MybankPayCpayCpordercloseRequestV1 req = new MybankPayCpayCpordercloseRequestV1();

		// 设置请求路径
		req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cporderclose/V1");

		MybankPayCpayCpordercloseV1RequestV1Biz biz = new MybankPayCpayCpordercloseV1RequestV1Biz();
		biz.setAgreeCode("123");
		biz.setPartnerSeq("123");
		biz.setOrderCode("2019021314281026");
		req.setBizContent(biz);
		MybankPayCpayCpordercloseResponseV1 response;

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(msgId);

		try {
			response = client.execute(req, msgId);
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("success");//
			} else {
				// 失败
				System.out.println("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
