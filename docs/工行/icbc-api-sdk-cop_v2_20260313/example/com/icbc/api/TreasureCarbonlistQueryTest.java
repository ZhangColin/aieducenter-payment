package com.icbc.api;


import com.icbc.api.request.TreasureCarbonlistQueryRequestV1;
import com.icbc.api.response.TreasureCarbonlistQueryResponseV1;
import org.junit.Test;

public class TreasureCarbonlistQueryTest {
    protected static final String MY_PRIVATE_KEY = "yy";
    protected static final String APIGW_PUBLIC_KEY = "dd";
    protected static final String APP_ID = "aa";
    protected static final String ENCRYPT_KEY = "aa";

    //以上变量以实际为准

    public void TreasureCarbonlistQuery() throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES,ENCRYPT_KEY,null,null);
        TreasureCarbonlistQueryRequestV1 request = new TreasureCarbonlistQueryRequestV1();
        TreasureCarbonlistQueryRequestV1.TreasureCarbonlistQueryRequestV1Biz bizContent = new TreasureCarbonlistQueryRequestV1.TreasureCarbonlistQueryRequestV1Biz();
        request.setServiceUrl("ip:port/api/treasure/carbonlist/query/V1");
        bizContent.setUniqueid("");
        bizContent.setQueryMonth("202308");
        request.setBizContent(bizContent);
        TreasureCarbonlistQueryResponseV1 response = (TreasureCarbonlistQueryResponseV1) client.execute(request);
        if (response.isSuccess()) {
            //成功
            response.getListMoreRecord();
        } else {
            //失败
        }
    }


}
