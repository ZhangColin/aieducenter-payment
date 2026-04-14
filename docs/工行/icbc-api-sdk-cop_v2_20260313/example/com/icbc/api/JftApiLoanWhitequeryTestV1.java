package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftApiLoanWhitequeryRequestV1;
import com.icbc.api.response.JftApiLoanWhitequeryResponseV1;


public class JftApiLoanWhitequeryTestV1 {
    protected static final String MY_PRIVATE_KEY = "";

    protected static final String APIGW_PUBLIC_KEY = "";

    protected static final String APP_ID = "";

    protected static final String HOST = "";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String apiUrl = HOST + "/api/jft/api/loan/whitequery/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", "5xGJdh7qb+B95SUoxDlatg==", "", "");
        JftApiLoanWhitequeryRequestV1 request = new JftApiLoanWhitequeryRequestV1();
        request.setServiceUrl(apiUrl);
        JftApiLoanWhitequeryRequestV1.JftApiLoanWhitequeryRequestV1Biz bizContent = new JftApiLoanWhitequeryRequestV1.JftApiLoanWhitequeryRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setWhiteType("1");
        bizContent.setSerialNo("1111");
        bizContent.setBatchCode("222");
        bizContent.setBak("");
        request.setBizContent(bizContent);
        JftApiLoanWhitequeryResponseV1 response;
        try {
            response = (JftApiLoanWhitequeryResponseV1) client.execute(request, System.currentTimeMillis() + "");

            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
