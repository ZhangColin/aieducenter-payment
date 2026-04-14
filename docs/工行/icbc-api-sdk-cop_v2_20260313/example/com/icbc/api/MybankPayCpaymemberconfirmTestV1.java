package com.icbc.api.test;

import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.MybankPayCpaymemberconfirmRequestV1;
import com.icbc.api.request.MybankPayCpaymemberconfirmRequestV1.MybankPayCpaymemberconfirmRequestV1Biz;
import com.icbc.api.response.MybankPayCpaymemberconfirmResponseV1;

public class MybankPayCpaymemberconfirmTestV1 {
	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "123"; 
	
	static Logger log = Logger.getLogger(MybankPayCpaymemberconfirmTestV1.class.getName());

	public static void main(String[] args) {

   DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		// 设置请求对象request
		MybankPayCpaymemberconfirmRequestV1 req = new MybankPayCpaymemberconfirmRequestV1();
		
		// 设置请求路径
		req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/memberconfirm/V1");

		MybankPayCpaymemberconfirmRequestV1Biz biz = new MybankPayCpaymemberconfirmRequestV1Biz();
		
        biz.setAgreeCode("AGREECODE20190215");//合作方协议编号
        biz.setMemberNo("001");//合作方会员号
        biz.setReturnUrl("http://www.XXX.cn");//返回页面地址
       
        
		req.setBizContent(biz);
		MybankPayCpaymemberconfirmResponseV1 response = new MybankPayCpaymemberconfirmResponseV1();

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		log.fine(msgId);

		try {
			response = client.execute(req, msgId);
			log.fine("response:" + response.toString());
			if (response.isSuccess()) {
				// 业务成功处理
				log.fine("success");//
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
