package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BksAigcAnswerQueryRequestV1;
import com.icbc.api.response.BksAigcAnswerQueryResponseV1;

public class BksAigcAnswerQueryV1Test {
    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY, (String) null, (String) null);
        client.setHeaderParam("X-Request-App","F-BKS"); //调用方应用简称
        client.setHeaderParam("access_token","11113599fb1234501b660eef5ff877df"); //访问令牌（联系F-BKS应用获取）
        BksAigcAnswerQueryRequestV1 request = new BksAigcAnswerQueryRequestV1();
        request.setServiceUrl("http://ip:port/api/bks/aigc/answer/query/V1");

        BksAigcAnswerQueryRequestV1.BksAigcAnswerQueryRequestV1Biz bizContent = new BksAigcAnswerQueryRequestV1.BksAigcAnswerQueryRequestV1Biz();
        bizContent.setAppScene("1");
        bizContent.setTellerId("001149245");
        bizContent.setChatId("ra202305111040363450000000020921");
        bizContent.setDialogueId("c9644315-6bfd-11ee-bdbd-fa163e3f2eb2");
        bizContent.setQuestion("问题");

        request.setBizContent(bizContent);

        BksAigcAnswerQueryResponseV1 response;
        try {
            System.out.println("request:"+ JSON.toJSONString(request));
            response = client.execute(request);
            System.out.println("response:" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
