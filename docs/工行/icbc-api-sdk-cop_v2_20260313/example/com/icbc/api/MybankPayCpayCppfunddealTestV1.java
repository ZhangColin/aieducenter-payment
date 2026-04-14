package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankPayCpayCppfunddealV1;
import com.icbc.api.request.MybankPayCpayCppfunddealV1.MybankPayCpayCppfunddealV1Biz;
import com.icbc.api.response.MybankPayCpayCppfunddealResponseV1;

import java.util.Random;
import java.util.logging.Logger;

public class MybankPayCpayCppfunddealTestV1{

	/**
	 * @param args
	 */

	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123";
	
	static Logger log = Logger.getLogger(MybankPayCpayCppfunddealTestV1.class.getName());
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

	
		MybankPayCpayCppfunddealV1 req = new MybankPayCpayCppfunddealV1();

	
		req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cppfunddeal/V1");

		MybankPayCpayCppfunddealV1Biz biz = new MybankPayCpayCppfunddealV1Biz();

		bizContent.setAgreeCode("0");
		bizContent.setOrderCode("0");
		bizContent.setPartnerSeq("0");
		bizContent.setPayAmount("1");
		bizContent.setOrderCurr("1");
		bizContent.setFundDealflag("1");
		bizContent.setOperType("0");
		bizContent.setPayerMemberNo("1");
		bizContent.setSubmitTime("1");
		bizContent.setOrderRemark("1");
		bizContent.setReceiptRemark("1");
		bizContent.setPurpose("1");
		bizContent.setSummary("1");
		MybankPayCpayCppfunddealResponseV1 response = new MybankPayCpayCppfunddealResponseV1();

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		log.fine("req.getBizContent():"+req.getBizContent());

		try {
			response = client.execute(req, msgId);
			log.fine("response:" + response.toString());
			if (response.isSuccess()) {
				
				System.out.println("success");//
				log.fine("success");
			} else {
				
				System.out.println("error");
				log.fine("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
