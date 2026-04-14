package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankPayCpayCppreservationcancelRequestV2;
import com.icbc.api.request.MybankPayCpayCppreservationcancelRequestV2.MybankPayCpayCppreservationcancelRequestV2Biz;
import com.icbc.api.response.MybankPayCpayCppreservationcancelResponseV2;

import java.util.Random;
import java.util.logging.Logger;

public class MybankPayCpayCppreservationcancelTestV2 {
	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123";
	
	static Logger log = Logger.getLogger(MybankPayCpayCppreservationcancelTestV2.class.getName());
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		MybankPayCpayCppreservationcancelRequestV2 req = new MybankPayCpayCppreservationcancelRequestV2();

		// 设置请求路径
		req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cppreservationcancel/V2");

		MybankPayCpayCppreservationcancelRequestV2Biz biz = new MybankPayCpayCppreservationcancelRequestV2Biz();

		biz.setAgreeCode("AGREECODE20190217");//合作方协议编号
		biz.setOrderCode("20190217");//订单编号
		biz.setPartnerSeq("1");//合作方支付流水号
		biz.setPartnerSeqOrigin("12");//原合作方支付流水号
		biz.setPayAmount("1");//本次解保留金额
		biz.setOrderCurr("1");//交易币种
		biz.setSubmitTime("20190422170400");//交易平台提交时间
		biz.setOrderRemark("备注20190215");//订单备注
		biz.setPayeeAccno("020019900333");//收款账号
		biz.setPayeeCompanyName("华为旗舰店");//收款人户名
		biz.setReceiptRemark("回单补充信息备注");//回单补充信息备注
		biz.setPurpose("用途");//用途
		biz.setSummary("摘要");//摘要
		biz.setPayerAccno("020000030");//付款人账号
    biz.setPayerAccname("付款人户名");//付款人户名
    biz.setPayerBankCode("12345");//付款人行号
    biz.setOperType("302");//担保业务种类
		biz.setPayerMemberNo("302");//付款方会员编号
		biz.setPayerMemberName("302");//付款方会员名称
    biz.setCrmemberNo("111");//收款方会员编号
    biz.setCrmemberName("122");//收款方会员名称
    biz.setEnSummry("111");//英文备注
		biz.setReInterestFlag(1);//是否退息
		biz.setReInterestTypey(1);//退息方式

		req.setBizContent(biz);
		MybankPayCpayCppreservationcancelResponseV2 response = new MybankPayCpayCppreservationcancelResponseV2();

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		log.fine("msgId:"+msgId);

		try {
			response = client.execute(req, msgId);
			log.fine("response:"+response.toString());
			if (response.isSuccess()) {
				// 业务成功处理
				log.fine("success");
			} else {
				// 失败
				log.fine("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
