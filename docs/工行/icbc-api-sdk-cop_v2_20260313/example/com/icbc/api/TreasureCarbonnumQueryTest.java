package com.icbc.api;

import com.icbc.api.request.TreasureCarbonnumQueryRequestV1;
import com.icbc.api.response.TreasureCarbonnumQueryResponseV1;

public class TreasureCarbonnumQueryTest {
    protected static final String MY_PRIVATE_KEY = "xx";
    protected static final String APIGW_PUBLIC_KEY = "yy";
    protected static final String APP_ID = "";
    protected static final String ENCRYPT_KEY = "aa";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES,ENCRYPT_KEY,null,null);
        TreasureCarbonnumQueryRequestV1 request = new TreasureCarbonnumQueryRequestV1();
        TreasureCarbonnumQueryRequestV1.TreasureCarbonnumQueryRequestV1Biz bizContent = new TreasureCarbonnumQueryRequestV1.TreasureCarbonnumQueryRequestV1Biz();
        request.setServiceUrl("ip:port/api/treasure/carbonnum/query/V1");
        bizContent.setUniqueid("");
        request.setBizContent(bizContent);
        TreasureCarbonnumQueryResponseV1 response = (TreasureCarbonnumQueryResponseV1) client.execute(request);
        if (response.isSuccess()) {
            //成功

        } else {
            //失败
        }
    }
}
