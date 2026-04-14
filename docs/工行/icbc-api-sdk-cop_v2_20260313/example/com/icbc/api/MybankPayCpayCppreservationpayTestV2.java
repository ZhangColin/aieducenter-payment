package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankPayCpayCppreservationpayRequestV2;
import com.icbc.api.request.MybankPayCpayCppreservationpayRequestV2.MybankPayCpayCppreservationpayRequestV2Biz;
import com.icbc.api.response.MybankPayCpayCppreservationpayResponseV2;

import java.util.Random;
import java.util.logging.Logger;

public class MybankPayCpayCppreservationpayTestV2{

	/**
	 * @param args
	 */

	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123";
	
	static Logger log = Logger.getLogger(MybankPayCpayCppreservationpayTestV2.class.getName());
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		MybankPayCpayCppreservationpayRequestV2 req = new MybankPayCpayCppreservationpayRequestV2();

		// 设置请求路径
		req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cppreservationpay/V2");

		MybankPayCpayCppreservationpayRequestV2Biz biz = new MybankPayCpayCppreservationpayRequestV2Biz();

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
		biz.setPurpose("purpose");
		biz.setSummary("summary");
		biz.setOperType("302");
		biz.setPayerMemberNo("1234567");
		biz.setPayerMemberName("PayerMemberName");
		biz.setNote("note");
		biz.setCrmemberNo("1234567");
		biz.setCrmemberName("CrmemberName");
		biz.setTradeName("TradeName");
		biz.setTradeNum("TradeNum");
		biz.setTradeUnit("TradeUnit");
		biz.setRealpayerAccno("121");
		biz.setRealpayerAccnoName("121");
		biz.setRealpayerBankName("102");
		biz.setRealpayerBankNo("102");
    	biz.setFundDealDirect("3");
		bizContent.setFundDealDirect("1");
		bizContent.setIsAuthorizedMemFlag("1");
		bizContent.setAuthorizedAmount("10");
		bizContent.setAuthorizedBusiCode("abc");
		bizContent.setPayerAccno("121");
		bizContent.setPayerAccnoName("香山湖公司");
		bizContent.setReInterestFlag(0);
		bizContent.setReInterestType(0);

		req.setBizContent(biz);
		MybankPayCpayCppreservationpayResponseV2 response = new MybankPayCpayCppreservationpayResponseV2();

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		log.fine("req.getBizContent():"+req.getBizContent());

		try {
			response = client.execute(req, msgId);
			log.fine("response:" + response.toString());
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("success");//
				log.fine("success");
			} else {
				// 失败
				System.out.println("error");
				log.fine("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
