package com.icbc.api;


import com.icbc.api.request.TreasureTasklistQueryRequestV1;
import com.icbc.api.response.TreasureTasklistQueryResponseV1;
import org.junit.Test;


public class TreasureTaskListQueryTest {
    protected static final String MY_PRIVATE_KEY = "xx";
    protected static final String APIGW_PUBLIC_KEY = "ss";
    protected static final String APP_ID = "";
    protected static final String ENCRYPT_KEY = "aa";
    //以上变量以实际为准
    
    public void TreasureTaskListQuery() throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES,ENCRYPT_KEY,null,null);
        TreasureTasklistQueryRequestV1 request = new TreasureTasklistQueryRequestV1();
        TreasureTasklistQueryRequestV1.TreasureTaskListQueryRequestV1Biz bizContent = new TreasureTasklistQueryRequestV1.TreasureTaskListQueryRequestV1Biz();
        request.setServiceUrl("ip:port/api/treasure/tasklist/query/V1");
        bizContent.setUniqueid("");
        request.setBizContent(bizContent);
        TreasureTasklistQueryResponseV1 response = (TreasureTasklistQueryResponseV1) client.execute(request);
        if (response.isSuccess()) {
            //成功
            response.getListMoreRecord();
        } else {
            //失败
        }
    }





}
