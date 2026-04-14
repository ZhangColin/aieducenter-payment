package com.icbc.api;

import com.icbc.api.request.CallbackGovRequestV1;
import com.icbc.api.response.CallbackGovReponseV1;


public class CallbackGovV1Test {
    private static final String MY_PRIVATE_KEY = "xxxx";
    private static final String APIGW_PUBLIC_KEY = "xxxx";

    private static final String APPID = "yourAppId";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APPID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CallbackGovRequestV1 request = new CallbackGovRequestV1();
        request.setServiceUrl("https://ip:port/api/govdata/gov/callBckData/V1");
        CallbackGovRequestV1.NoStandardCallBackVo noStandardCallBackVo = new CallbackGovRequestV1.NoStandardCallBackVo();
        noStandardCallBackVo.setBizData(new CallbackGovRequestV1.NoStandardCallBackVo.BizData());
        noStandardCallBackVo.setCustCertType("0");
        noStandardCallBackVo.setCustCode("12313131");
        noStandardCallBackVo.setCustName("这是");
        noStandardCallBackVo.setGovType("1");
        noStandardCallBackVo.setServiceCode("1");
        noStandardCallBackVo.setServiceTransno("213");
        noStandardCallBackVo.setUuid("2323");
        request.setBizContent(noStandardCallBackVo);

        CallbackGovReponseV1 response;
        try {
            response = client.execute(request);
            if (response.isSuccess()) {
                // ҵ��ɹ�����
//				Assert.assertEquals(response.getPurchasePrice(), 100);
                System.out.println(response.getRetcode());
                System.out.println(response.getRetmsg());
                System.out.println(response.getMsgId());
            } else {
                // ʧ��
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
