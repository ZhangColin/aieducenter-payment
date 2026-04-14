package com.icbc.api.demo;

import java.util.UUID;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.*;
import com.icbc.api.request.MybankPayDigitalwalletAccountexchangeRequestV1.MybankPayDigitalwalletAccountexchangeRequestV1Biz;
import com.icbc.api.response.*;

public class MybankPayDigitalwalletAccountexchangeV1Test {
	//1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "";
	//2、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "";
	//3、appid
	protected static final String APP_ID = "";
	
	public static void main(String[] args) {
		String msgId = UUID.randomUUID().toString();
		//4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

		MybankPayDigitalwalletAccountexchangeRequestV1 request = new MybankPayDigitalwalletAccountexchangeRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/pay/digitalwallet/accountexchange/V1");
		
		MybankPayDigitalwalletAccountexchangeRequestV1Biz bizContent= new MybankPayDigitalwalletAccountexchangeRequestV1Biz();
		
		bizContent.setCooperatorNo("1234567890");
		bizContent.setAgentCooperatorNo("1122334455");
		bizContent.setSerialNo("002000000201234567891200002");
		bizContent.setRelatedSerialNo("");
		bizContent.setOriginalSerialNo("");
		bizContent.setWorkDate("2020-12-12");
		bizContent.setWorkTime("12:12:12");
		bizContent.setMac("xx:xx:xx:xx:xx:xx");
		bizContent.setIP("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");
		bizContent.setWalletId("123");
		bizContent.setWalletName("钱包");
		bizContent.setAmount(9999999999999999L);
		bizContent.setCurrType("1");
		bizContent.setOperType(1);
		bizContent.setRemark("备注");
		bizContent.setAccno("1211022019200042686");
		bizContent.setProtocolId("12333333");
		bizContent.setReverseFlag(1);
		request.setBizContent(bizContent);
		request.setNeedEncrypt(true);

		MybankPayDigitalwalletAccountexchangeResponseV1 response = new MybankPayDigitalwalletAccountexchangeResponseV1();

		
		System.out.println(request.getBizContent());

		try {
			response = client.execute(request, msgId);
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
