package com.icbc.api;

import java.util.HashMap;
import java.util.Map;

import com.icbc.api.request.MessageTemplateSendRequestV1Biz;
import com.icbc.api.response.MessageTemplateSendResponseV1;
import com.icbc.omcp.api.request.MessageSendRequestV1;

public class TemplateMessageTest {

	private static final String MY_PRIVATE_KEY = "xxxx";
	private static final String APIGW_PUBLIC_KEY = "xxxx";
	
	private static final String URL = "https://ip:port/api/message/templateSend/V1";//api地址
	private static final String APPID = "yourAppId";
	private static final String TEMPLATE_ID = "xxxx";//短信模板Id
	
	public static void main(String[] args) {
		MessageTemplateSendRequestV1Biz bizContent = new MessageTemplateSendRequestV1Biz();
		bizContent.setMobile("12345678901");
		bizContent.setTemplateId(TEMPLATE_ID);
		Map<String,String> params = new HashMap<String,String>();
		params.put("verification_code","123456");
		bizContent.setTemplateParams(params);

		MessageTemplateSendRequestV1 request = new MessageSendRequestV1();		
		request.setServiceUrl(URL);
		request.setBizContent(bizContent);
		
		DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MessageTemplateSendResponseV1 response;
		try {
			response = client.execute(request, "msgId1233664");
			// 业务成功处理
			System.out.println(response.getReturnCode());
			System.out.println(response.getReturnMsg());
			System.out.println(response.getMsgId());
		} catch (Exception e) {
			//TODO handle exception
		}
	}
}
