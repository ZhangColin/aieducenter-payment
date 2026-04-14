package com.icbc.api.example;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.SalaryservicePayoffQuerySalaryPayResultRequestV1;
import com.icbc.api.response.SalaryservicePayoffQuerySalaryPayResultResponseV1;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

public class SalaryservicePayoffQuerySalaryPayResultTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/salaryservice/payoff/querysalarypayresult/V1
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args)  throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        SalaryservicePayoffQuerySalaryPayResultRequestV1 request = new SalaryservicePayoffQuerySalaryPayResultRequestV1();
        SalaryservicePayoffQuerySalaryPayResultRequestV1.SalaryservicePayoffQuerySalaryPayResultRequestV1Biz bizContent = new SalaryservicePayoffQuerySalaryPayResultRequestV1.SalaryservicePayoffQuerySalaryPayResultRequestV1Biz();
        request.setServiceUrl(BASE_URL);
        StringBuffer sbf = new StringBuffer("");
        sbf.append("{");
        sbf.append("\"year_month\": \"202406\",");
        sbf.append("\"stru_id\": \"001010000\"");
        sbf.append("\"seq_no\": \"\"");
        sbf.append("}");
        JSONObject publicInfo = JSONObject.parseObject("{\"app_id\": \""+APP_ID+"\"}");
        JSONObject privateInfo = JSONObject.parseObject(sbf.toString());
        bizContent.setPublicObject(publicInfo);
        bizContent.setPrivateObject(privateInfo);
        request.setBizContent(bizContent);

        SalaryservicePayoffQuerySalaryPayResultResponseV1 response = client.execute(request);
        if (response.isSuccess()) {
            // 成功
        } else {
            // 失败
        }
    }
}
