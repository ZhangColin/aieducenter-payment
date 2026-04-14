package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankPayOverSeasCporderqueryRequestV1;
import com.icbc.api.request.MybankPayOverSeasCporderqueryRequestV1.QueryPayApplyRequestV1Biz;
import com.icbc.api.response.MybankPayOverSeasCporderqueryResponseV1;

public class MybankPayOverSeasCporderqueryTestV1 {
	
	protected static Logger log = Logger.getLogger(MybankPayOverSeasCporderqueryTestV1.class.getName());
	
	//1、网关公钥	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	//2、appid	
	protected static final String APP_ID = "123";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥	
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankPayOverSeasCporderqueryRequestV1 request = new MybankPayOverSeasCporderqueryRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/overseas/cporderquery/V1");
		QueryPayApplyRequestV1Biz bizContent = new QueryPayApplyRequestV1Biz();
		
		bizContent.setAgreeCode("123");
		bizContent.setOrderCode("123");
		bizContent.setPartnerSeq("123");
		client.setHeaderParam("X-Fova-Region", "00119");
		request.setBizContent(bizContent);

		try{
            MybankPayOverSeasCporderqueryResponseV1 response = client.execute(request);

            log.info("response:" + JSON.toJSONString(response));
            if (response.isSuccess()) {
				// 业务成功处理
				log.info("success");
			} else {
				// 失败
				log.info("error");
			}
        } catch(Exception e) {
		    e.printStackTrace();
        }
	}
}
