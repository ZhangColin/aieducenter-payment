package com.icbc.api;

import com.icbc.api.request.IflytekWebapiVoiceTtsRequestV1;
import com.icbc.api.response.IflytekWebapiVoiceTtsResponseV1;

import java.util.Random;

public class IflytekWebapiVoiceTtsV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "xxx";
    protected static final String APP_ID = "xxx";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        IflytekWebapiVoiceTtsRequestV1 request = new IflytekWebapiVoiceTtsRequestV1();
        request.setServiceUrl("https://ip:port/api/iflytek/webapi/voice/tts/V1");

        IflytekWebapiVoiceTtsRequestV1.IflytekWebapiVoiceTtsRequestV1Biz biz = new IflytekWebapiVoiceTtsRequestV1.IflytekWebapiVoiceTtsRequestV1Biz();
        biz.setCmd("cmd");
        biz.setVid("02i28811");
        biz.setAppid("appId");
        biz.setAuth_id("authId");
        biz.setSyncid("syncId");
        biz.setAuf("auf");
        biz.setAue("aue");
        biz.setSid("sadhaiuhkdajhuida-ashdadk123bjka");
        biz.setData("ASDAJKAKJDJKDHJKAJDKBAJKDADAJKBKJADHAHDKDJKBZCCCUIYHWQQEBMNQEEQWEUQWENQWEQWEWQEQW==");
        biz.setUid("sfsidnjksnvjkxuinj");
        biz.setExtend_params("{\"params\":\"token=anhui,ability=ab_asr\"}");
        request.setBizContent(biz);

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        System.out.println(request.getBizContent());

        IflytekWebapiVoiceTtsResponseV1 response = new IflytekWebapiVoiceTtsResponseV1();

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
