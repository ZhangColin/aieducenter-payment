package com.icbc.api;

import com.icbc.api.request.MybankPayCpayCppreservationpayRequestV1;
import com.icbc.api.request.MybankPayCpayCppreservationpayRequestV1.MybankPayCpayCppreservationpayRequestV1Biz;
import com.icbc.api.response.MybankPayCpayCppreservationpayResponseV1;

import java.util.Random;

public class MybankPayCpayCppreservationpayTestV1 {

	/**
	 * @param args
	 */

	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123";


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		MybankPayCpayCppreservationpayRequestV1 req = new MybankPayCpayCppreservationpayRequestV1();

		// 设置请求路径
		req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cppreservationcancel/V1");

		MybankPayCpayCppreservationpayRequestV1Biz biz = new MybankPayCpayCppreservationpayRequestV1Biz();

		biz.setAgreeCode("123");
		biz.setOrderCode("order123");
		biz.setPartnerSeq("129QQQDE5220120510244");
		biz.setPartnerSeqOrigin("129QQQDE5220120510243");
		biz.setPayAmount("1111");
		biz.setOrderCurr("1");
		biz.setPayeeSysflag("1");
		biz.setPayeeAccno("123");
		biz.setPayeeCompanyName("varchar0932");
		biz.setPayeeBankCode("1dsf");
		biz.setSubmitTime("20140217");
		biz.setOrderRemark("1dsfsd");
		biz.setReceiptRemark("1dfdsf");
		bizContent.setPayerAccno("121");
		bizContent.setPayerAccnoName("香山湖公司");
		bizContent.setReInterestFlag(0);
		bizContent.setReInterestType(0);

		req.setBizContent(biz);
		MybankPayCpayCppreservationpayResponseV1 response = new MybankPayCpayCppreservationpayResponseV1();

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(req.getBizContent());

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
