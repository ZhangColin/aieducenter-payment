package com.icbc.api;


import com.icbc.api.request.TreasureCarboncreditsCaptureRequestV1;
import com.icbc.api.response.TreasureCarboncreditsCaptureResponseV1;
import org.junit.Test;

public class TreasureCarboncreditsCaptureTest {
    protected static final String MY_PRIVATE_KEY = "xx";
    protected static final String APIGW_PUBLIC_KEY = "yy";
    protected static final String APP_ID = "";
    protected static final String ENCRYPT_KEY = "aa";

    //以上变量以实际为准

    public void TreasureCarboncreditsCapture() throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES,ENCRYPT_KEY,null,null);
        TreasureCarboncreditsCaptureRequestV1 request = new TreasureCarboncreditsCaptureRequestV1();
        TreasureCarboncreditsCaptureRequestV1.TreasureCarboncreditsCaptureRequestV1Biz bizContent = new TreasureCarboncreditsCaptureRequestV1.TreasureCarboncreditsCaptureRequestV1Biz();
        request.setServiceUrl("ip:port/api/treasure/carboncredits/capture/V1");
        bizContent.setUniqueid("");
        bizContent.setTriggerId("");
        request.setBizContent(bizContent);
        TreasureCarboncreditsCaptureResponseV1 response = (TreasureCarboncreditsCaptureResponseV1) client.execute(request);
        if (response.isSuccess()) {
            //成功
            response.getCarbonNum();
        } else {
            //失败
        }
    }

}
