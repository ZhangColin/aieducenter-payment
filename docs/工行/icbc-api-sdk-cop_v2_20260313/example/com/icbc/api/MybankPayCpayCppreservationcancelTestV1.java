package com.icbc.api;

import com.icbc.api.request.MybankPayCpayCppreservationcancelRequestV1;
import com.icbc.api.request.MybankPayCpayCppreservationcancelRequestV1.MybankPayCpayCppreservationcancelRequestV1Biz;
import com.icbc.api.response.MybankPayCpayCppreservationcancelResponseV1;

import java.util.Random;

public class MybankPayCpayCppreservationcancelTestV1 {
	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123";


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		MybankPayCpayCppreservationcancelRequestV1 req = new MybankPayCpayCppreservationcancelRequestV1();

		// 设置请求路径
		req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cppreservationcancel/V1");

		MybankPayCpayCppreservationcancelRequestV1Biz biz = new MybankPayCpayCppreservationcancelRequestV1Biz();

		biz.setAgreeCode("AGREECODE20190217");//合作方协议编号
		biz.setOrderCode("20190217");//订单编号
		biz.setPartnerSeq("1");//合作方支付流水号
		biz.setPartnerSeqOrigin("12");//原合作方支付流水号
		biz.setPayAmount("1");//本次解保留金额
		biz.setOrderCurr("1");//交易币种
		biz.setSubmitTime("20190422170400");//交易平台提交时间
		biz.setOrderRemark("备注20190215");//订单备注

		req.setBizContent(biz);
		MybankPayCpayCppreservationcancelResponseV1 response = new MybankPayCpayCppreservationcancelResponseV1();

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(msgId);

		try {
			response = client.execute(req, msgId);
			System.out.println("response:" + response.toString());
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
