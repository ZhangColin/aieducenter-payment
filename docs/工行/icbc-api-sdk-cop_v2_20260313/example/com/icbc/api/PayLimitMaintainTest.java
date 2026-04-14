package com.icbc.api;


import com.icbc.api.request.PayLimitMaintainRequestV1;
import com.icbc.api.response.PayLimitMaintainResponseV1;

import java.util.ArrayList;

public class PayLimitMaintainTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/pay/limit/maintain/V1
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        PayLimitMaintainRequestV1 request = new PayLimitMaintainRequestV1();
        request.setServiceUrl(BASE_URL);

        PayLimitMaintainRequestV1.RequestBiz bizContent = new PayLimitMaintainRequestV1.RequestBiz();

        //构建 chanCommV10 请求信息
        PayLimitMaintainRequestV1.ChanCommV10 chanCommV10 = new PayLimitMaintainRequestV1.ChanCommV10();
        //构建 infoCommV10 请求信息
        PayLimitMaintainRequestV1.InfoCommV10 infoCommV10 = new PayLimitMaintainRequestV1.InfoCommV10();

        //构建 privateArea 请求信息
        PayLimitMaintainRequestV1.PayLimitInnerMaintainLimitRecordPrivateArea privateArea = new PayLimitMaintainRequestV1.PayLimitInnerMaintainLimitRecordPrivateArea();

        //构建 remitList 列表
        ArrayList<PayLimitMaintainRequestV1.Remit> remitList = new ArrayList<>();
        PayLimitMaintainRequestV1.Remit remit = new PayLimitMaintainRequestV1.Remit();
        remitList.add(remit);

        privateArea.setRemitList(remitList);

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setPrivateArea(privateArea);
        request.setBizContent(bizContent);
        PayLimitMaintainResponseV1 response = client.execute(request);
        if (response.isSuccess()) {
            // 成功
        } else {
            // 失败
        }
    }
}
