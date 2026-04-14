package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankPayOverseasCppayreservationRequestV1;
import com.icbc.api.request.MybankPayOverseasCppayreservationRequestV1.MybankPayOverseasCppayreservationRequestV1Biz;
import com.icbc.api.response.MybankPayOverseasCppayreservationResponseV1;

import java.util.Random;

public class MybankPayOverseasCppayreservationTestV1 {
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	//2、appid
	protected static final String APP_ID = "123";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "xxxx";
			


	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		// 设置请求对象request
		MybankPayOverseasCppayreservationRequestV1 req = new MybankPayOverseasCppayreservationRequestV1();

		req.setServiceUrl("http://IP:PORT/api/mybank/pay/overseas/cppayreservation/V1");

		MybankPayOverseasCppayreservationRequestV1Biz bizContent = new MybankPayOverseasCppayreservationRequestV1Biz();

		bizContent.setAgreeCode("88");
		bizContent.setOrderCode("88");
		bizContent.setPartnerSeq("88");
		bizContent.setPartnerSeqOrigin("88");
		bizContent.setPayAmount("23333");
		bizContent.setOrderCurr("13");
		bizContent.setPayeeSysflag("1");
		bizContent.setPayeeAccno("88");
		bizContent.setPayeeCompanyName("1");
		bizContent.setSubmitTime("20220228139000");
		bizContent.setReceiptRemark("哈哈");
		bizContent.setPurpose("哈哈");
		bizContent.setSummary("哈哈");

		req.setBizContent(bizContent);
		MybankPayOverseasCppayreservationResponseV1 response = new MybankPayOverseasCppayreservationResponseV1();

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		client.setHeaderParam("X-Fova-Region", "00119");
		try {
			response = client.execute(req, msgId);
			if (response.isSuccess()) {
				// 业务成功处理
				
			} else {
				// 失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
