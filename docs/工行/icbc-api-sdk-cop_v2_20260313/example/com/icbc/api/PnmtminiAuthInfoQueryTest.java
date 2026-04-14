package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.PnmtminiAuthInfoQueryRequestV1;
import com.icbc.api.response.PnmtminiAuthInfoQueryResponseV1;

public class PnmtminiAuthInfoQueryTest {
    protected static String APP_ID = "APP_ID";
    protected static String APIGW_PUBLIC_KEY = "PUB_KEY";
    protected static String PRI_KEY = "PRI_KEY";
    protected static String BASE_URL = "HOST";

    public static void main(String[] args) throws Exception {
        String URI = "https://ip:port/api/pnmtmini/rongegou/auth/info/query/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {

            PnmtminiAuthInfoQueryRequestV1 request = new PnmtminiAuthInfoQueryRequestV1();
            PnmtminiAuthInfoQueryRequestV1.PnmtminiAuthInfoQueryRequestV1Biz bizContent = new PnmtminiAuthInfoQueryRequestV1.PnmtminiAuthInfoQueryRequestV1Biz();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setCode("1234567890");

            request.setServiceUrl(URI);
            request.setBizContent(bizContent);
            System.out.println(JSONObject.toJSONString(bizContent));
            PnmtminiAuthInfoQueryResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println("success");
                System.out.println(JSONObject.toJSONString(response));
            } else {
                System.out.println("error");
                System.out.println(JSONObject.toJSONString(response));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
