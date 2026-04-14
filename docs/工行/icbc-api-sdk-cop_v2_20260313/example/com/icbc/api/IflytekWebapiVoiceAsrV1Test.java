package com.icbc.api;

import com.icbc.api.request.IflytekWebapiVoiceAsrRequestV1;
import com.icbc.api.response.IflytekWebapiVoiceAsrResponseV1;

import java.util.Random;

public class IflytekWebapiVoiceAsrV1Test {

	protected static final String MY_PRIVATE_KEY = "xxx";
	protected static final String APIGW_PUBLIC_KEY = "xxx";
	protected static final String APP_ID = "xxx";

	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		IflytekWebapiVoiceAsrRequestV1 request = new IflytekWebapiVoiceAsrRequestV1();
		request.setServiceUrl("https://ip:port/api/iflytek/webapi/voice/asr/V1");

		IflytekWebapiVoiceAsrRequestV1.IflytekWebapiVoiceAsrRequestV1Biz biz = new IflytekWebapiVoiceAsrRequestV1.IflytekWebapiVoiceAsrRequestV1Biz();
		biz.setCmd("cmd");
		biz.setAppid("appId");
		biz.setAuth_id("authId");
		biz.setSyncid("syncId");
		biz.setAuf("auf");
		biz.setAue("aue");
		biz.setSid("sadhaiuhkdajhuida-ashdadk123bjka");
		biz.setAudioStatus("1");
		biz.setData("ASDAJKAKJDJKDHJKAJDKBAJKDADAJKBKJADHAHDKDJKBZCCCUIYHWQQEBMNQEEQWEUQWENQWEQWEWQEQW==");
		biz.setUid("sfsidnjksnvjkxuinj");
		biz.setHotword("1");
		biz.setExtend_params("{\"params\":\"token=anhui,ability=ab_asr\"}");
		request.setBizContent(biz);

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(request.getBizContent());

		IflytekWebapiVoiceAsrResponseV1 response = new IflytekWebapiVoiceAsrResponseV1();

		try {
			response = client.execute(request,msgId);
			System.out.println("return_data:" + response.getReturnData());
			if (response.isSuccess()) {
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response.getMsgId());
				System.out.println("response:" + response.getReturnMsg());
			} else {
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}
}
