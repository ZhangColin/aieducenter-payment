package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.CouponApplyRequestV1;
import com.icbc.api.request.CouponApplyRequestV1Biz;
import com.icbc.api.response.CouponApplyResponseV1;

public class PaymentCouponApplyTest {

private static final String MY_PRIVATE_KEY = "xxxx";
private static final String APIGW_PUBLIC_KEY = "xxxx";
private static final String URL = "https://ip:port/api/payment/coupon/apply/V1";//api��ַ
private static final String APPID = "yourAppId";
	
public static void main(String[] args) {
		CouponApplyRequestV1Biz bizContent = new CouponApplyRequestV1Biz();
		bizContent.setAct_id("201511050022");
		bizContent.setUser_mobile_no("12345678901");
		bizContent.setShare_terminal_type("mobile");
		bizContent.setShare_terminal("wechat");

		CouponApplyRequestV1 request = new CouponApplyRequestV1();		
		request.setServiceUrl(URL);
		request.setBizContent(bizContent);
		
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CouponApplyResponseV1 response;
		try {
			response = client.execute(request, "msgId1233664");
			// ҵ��ɹ�����
			System.out.println(response.getReturnCode());
			System.out.println(response.getReturnMsg());
			System.out.println(response.getMsgId());
		} catch (Exception e) {
			//TODO handle exception
		}
}

}
