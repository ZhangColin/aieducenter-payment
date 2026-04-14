package com.icbc.api;


import com.icbc.api.request.PayLimitQueryRequestV1;
import com.icbc.api.response.PayLimitQueryResponseV1;

public class PayLimitQueryTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/pay/limit/query/V1
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        PayLimitQueryRequestV1 request = new PayLimitQueryRequestV1();
        request.setServiceUrl(BASE_URL);

        PayLimitQueryRequestV1.RequestBiz bizContent = new PayLimitQueryRequestV1.RequestBiz();

        //构建 chanCommV10 请求信息
        PayLimitQueryRequestV1.ChanCommV10 chanCommV10 = new PayLimitQueryRequestV1.ChanCommV10();
        //构建 infoCommV10 请求信息
        PayLimitQueryRequestV1.InfoCommV10 infoCommV10 = new PayLimitQueryRequestV1.InfoCommV10();
        //构建 inqCommV10 请求信息
        PayLimitQueryRequestV1.InqCommV10 inqCommV10 = new PayLimitQueryRequestV1.InqCommV10();
        //构建 privateArea 请求信息
        PayLimitQueryRequestV1.PayLimitInnerQueryLimitRecordPrivateArea privateArea = new PayLimitQueryRequestV1.PayLimitInnerQueryLimitRecordPrivateArea();

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setInqCommV10(inqCommV10);
        bizContent.setPrivateArea(privateArea);
        request.setBizContent(bizContent);
        PayLimitQueryResponseV1 response = client.execute(request);
        if (response.isSuccess()) {
            // 成功
        } else {
            // 失败
        }
    }
}
