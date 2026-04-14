package com.icbc.api;

import com.icbc.api.request.MybankPayCpayCpamountdepositRequestV1;
import com.icbc.api.request.MybankPayCpayCpamountdepositRequestV1.MybankPayCpayCpamountdepositRequestV1Biz;
import com.icbc.api.response.MybankPayCpayCpamountdepositResponseV1;

import java.util.Random;

public class MybankPayCpayCpamountdepositTestV1 {

	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123"; 

	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		MybankPayCpayCpamountdepositRequestV1 req = new MybankPayCpayCpamountdepositRequestV1();

		// 设置请求路径
		req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cpamountdeposit/V1");

		MybankPayCpayCpamountdepositRequestV1Biz biz = new MybankPayCpayCpamountdepositRequestV1Biz();
		
        biz.setAgreeCode("12345");//合作方编号
        biz.setOrderCode("1234");//订单编号
        biz.setPartnerSeq("123456");//交易流水号
        biz.setPayReserveCurr("1");//保留币种 1-人民币
        biz.setPayReserveAmount("6000000");//保留金额
		
		req.setBizContent(biz);
		MybankPayCpayCpamountdepositResponseV1 response = new MybankPayCpayCpamountdepositResponseV1();

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
				//失败
				System.out.println("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
