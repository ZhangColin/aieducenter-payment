package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BksAigcAnswerQueryRequestV1;
import com.icbc.api.request.BksAigcFeedbackSaveRequestV1;
import com.icbc.api.response.BksAigcAnswerQueryResponseV1;
import com.icbc.api.response.BksAigcFeedbackSaveResponseV1;

public class BksAigcFeedbackSaveV1Test {
    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY, (String) null, (String) null);
        client.setHeaderParam("X-Request-App","F-BKS"); //调用方应用简称
        BksAigcFeedbackSaveRequestV1 request = new BksAigcFeedbackSaveRequestV1();
        request.setServiceUrl("http://ip:port/api/bks/aigc/answer/query/V1");

        BksAigcFeedbackSaveRequestV1.BksAigcFeedbackSaveRequestV1Biz bizContent = new BksAigcFeedbackSaveRequestV1.BksAigcFeedbackSaveRequestV1Biz();
        bizContent.setUserId("001149245");
        bizContent.setChatId("ra202305111040363450000000020921");
        bizContent.setDialogueId("c9644315-6bfd-11ee-bdbd-fa163e3f2eb2");
        bizContent.setQuestion("问题");
        bizContent.setAnswer("答案");
        bizContent.setCommentType(2);
        bizContent.setLinkKnowledgeNameData("[1]个网-信用卡-基本规定,[2]手机银行-信用卡-基本规定,[3]个人信用卡查询-持卡信息查询");
        bizContent.setRightAnswerText("信用卡还款规定");
        bizContent.setProblemText("引用文档错误、不完整");

        request.setBizContent(bizContent);

        BksAigcFeedbackSaveResponseV1 response;
        try {
            System.out.println("request:"+ JSON.toJSONString(request));
            response = client.execute(request);
            System.out.println("response:" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
