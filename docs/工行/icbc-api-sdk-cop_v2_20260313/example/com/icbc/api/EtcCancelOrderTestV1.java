package com.icbc.api;

import static org.junit.Assert.assertEquals;

import com.icbc.api.request.EtcOrderCancelRequestV1;
import com.icbc.api.request.EtcOrderCancelRequestV1.EtcCancelOrderRequestV1Biz;
import com.icbc.api.response.EtcOrderCancelResponseV1;

import org.junit.Test;

public class EtcCancelOrderTestV1{

	public void test_cop() {
			DefaultIcbcClient client = new DefaultIcbcClient("","", ""); 
			EtcOrderCancelRequestV1 request = new EtcOrderCancelRequestV1();
			request.setServiceUrl("https://domain:port/api/ETC/order/cancel/invoke/V1");
			EtcCancelOrderRequestV1Biz bizContent = new EtcCancelOrderRequestV1Biz();

			bizContent.setTrx_serno("45371237437433737524012534537123743743373752401253");
			bizContent.setTrxdate("96453215756423778372245329645321575642377837224532");
			bizContent.setTrxtime("48234610634061249178515418362457393426185237856738");
			bizContent.setOrderId("41775315920473842314564863154230340037561203423064");
			bizContent.setUserId("41775315920473842314564863154230340037561203423064"); 
			bizContent.setContractId("41775315920473842314564863154230340037561203423064"); 
			bizContent.setIssuerId("41775315920473842314564863154230340037561203423064");
			bizContent.setCancelTime("2018‐09‐19 14:11:12");
			                      
			request.setBizContent(bizContent);
			EtcOrderCancelResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				assertEquals(response.getReturn_code(), "0");
			} else {
				// 失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
