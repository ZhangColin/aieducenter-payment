package com.icbc.api;

import com.icbc.api.request.TreasureCarbontriggerQueryRequestV1;
import com.icbc.api.response.TreasureCarbontriggerQueryResponseV1;

public class TreasureCarbontriggerQueryTest {
    protected static final String MY_PRIVATE_KEY = "xx";
    protected static final String APIGW_PUBLIC_KEY = "yy";
    protected static final String APP_ID = "";
    protected static final String ENCRYPT_KEY = "aa";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES,ENCRYPT_KEY,null,null);
        TreasureCarbontriggerQueryRequestV1 request = new TreasureCarbontriggerQueryRequestV1();
        TreasureCarbontriggerQueryRequestV1.TreasureCarbontriggerQueryRequestV1Biz bizContent = new TreasureCarbontriggerQueryRequestV1.TreasureCarbontriggerQueryRequestV1Biz();
        request.setServiceUrl("ip:port/api/treasure/carbontrigger/query/V1");
        request.setBizContent(bizContent);
        TreasureCarbontriggerQueryResponseV1 response = (TreasureCarbontriggerQueryResponseV1) client.execute(request);
        if (response.isSuccess()) {
            //成功

        } else {
            //失败
        }
    }
}
