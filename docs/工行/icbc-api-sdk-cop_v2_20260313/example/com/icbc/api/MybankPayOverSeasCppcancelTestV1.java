package com.icbc.api.test.V1;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankPayOverSeasCppcancelRequestV1;
import com.icbc.api.request.MybankPayOverSeasCppcancelRequestV1.MybankPayOverSeasCppcancelRequestV1Biz;
import com.icbc.api.response.MybankPayOverSeasCppcancelResponseV1;
import java.util.Random;
import java.util.logging.Logger;

public class MybankPayOverSeasCppcancelTestV1 {
	
	protected static Logger log = Logger.getLogger(MybankPayOverSeasCppcancelTestV1.class.getName());
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "xxxx";
	//2、appid
	protected static final String APP_ID = "123";			
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		client.setHeaderParam("X-Fova-Region", "00119");

		// 设置请求对象request
		MybankPayOverSeasCppcancelRequestV1 req = new MybankPayOverSeasCppcancelRequestV1();

		// 设置请求路径
	    req.setServiceUrl("http://IP:PORT/api/mybank/pay/overseas/cppaycancel/V1");

		MybankPayOverSeasCppcancelRequestV1Biz biz = new MybankPayOverSeasCppcancelRequestV1Biz();

		biz.setAgreeCode("123");//合作方协议编号
		biz.setOrderCode("123");//订单编号
		biz.setPartnerSeq("123");//合作方支付流水号
		biz.setPartnerSeqOrigin("123");//原合作方支付流水号
		biz.setPayAmount("23333");//本次解保留金额
		biz.setPayCurr("13");//交易币种
		biz.setSubmitTime("20220228145109");//交易平台提交时间
		biz.setPayeeAccno("123");//收款人账号
		biz.setPayeeCompanyName("1");//收款人户名
		biz.setReceiptRemark("回单补充信息备注");//回单补充信息备注
		biz.setPurpose("用途");//用途
		biz.setSummary("摘要");//摘要

		req.setBizContent(biz);
		MybankPayOverSeasCppcancelResponseV1 response = new MybankPayOverSeasCppcancelResponseV1();

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		log.info(msgId);

		try {
			response = client.execute(req, msgId);
			log.info("response:" + JSON.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				log.info("success");
			} else {
				// 失败
				log.info("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
