package com.icbc.api.example;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.SalaryservicePayoffQuerysalarybatchinfoRequestV1;
import com.icbc.api.response.SalaryservicePayoffQuerysalarybatchinfoResponseV1;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;



public class SalaryservicePayoffQuerysalarybatchinfoTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/salaryservice/payoff/querysalarybatchinfo/V1
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        SalaryservicePayoffQuerysalarybatchinfoRequestV1 request = new SalaryservicePayoffQuerysalarybatchinfoRequestV1();
        SalaryservicePayoffQuerysalarybatchinfoRequestV1.SalaryServicePayoffQuerySalaryBatchInfoRequestV1Biz bizContent = new SalaryservicePayoffQuerysalarybatchinfoRequestV1.SalaryServicePayoffQuerySalaryBatchInfoRequestV1Biz();

        request.setServiceUrl(BASE_URL);
        JSONObject privateObject = new JSONObject();
        JSONObject publicObject = new JSONObject();
        privateObject.put("stru_id", "0010100025");
        privateObject.put("year_month", "202406");
        publicObject.put("app_id", APP_ID);
        bizContent.setPrivateObject(privateObject);
        bizContent.setPublicObject(publicObject);
        request.setBizContent(bizContent);

        SalaryservicePayoffQuerysalarybatchinfoResponseV1 response = client.execute(request);
        if (response.isSuccess()) {
            //System.out.println("success:" + response.getReturnCode() + "##"
            //		+ response.getReturnMsg());
            // 成功
        } else {
            //System.out.println("error:" + response.getReturnCode() + "##"
            //		+ response.getReturnMsg());
            // 失败
        }
    }
}
