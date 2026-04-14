package com.icbc.api.example;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.SalaryservicePayoffUdpBatchApplyResultRequestV1;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.response.SalaryservicePayoffUdpBatchApplyResultResponseV1;

public class SalaryservicePayoffUdpBatchApplyResultTest {
    protected static final String BASE_URL = "[需替换]工行API地址"; //https://ip:port/api/salaryservice/payoff/udpbatchapplyresult/V1
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        SalaryservicePayoffUdpBatchApplyResultRequestV1 request = new SalaryservicePayoffUdpBatchApplyResultRequestV1();
        SalaryservicePayoffUdpBatchApplyResultRequestV1.SalaryservicePayoffUdpBatchApplyResultRequestV1Biz bizContent = new SalaryservicePayoffUdpBatchApplyResultRequestV1.SalaryservicePayoffUdpBatchApplyResultRequestV1Biz();
        request.setServiceUrl(BASE_URL);
        StringBuffer sbf = new StringBuffer("");
        sbf.append("{");
        sbf.append("\"stru_id\": \"0200100000\",");
        sbf.append("\"seq_no\": \"20201214161000015876\",");
        sbf.append("\"apply_result\": \"0\",");
        sbf.append("\"apply_msg\": \"XXX\",");
        sbf.append("\"pay_account_no\": \"6222300000754385\",");
        sbf.append("\"pay_account_name\": \"张三\",");
        sbf.append("\"open_stru_name\": \"工商银行珠海分行\",");
        sbf.append("\"zone_no\": \"02302\",");
        sbf.append("\"br_no\": \"01022\",");
        sbf.append("\"pay_amout\": \"89323231.13\"");
        sbf.append("}");
        JSONObject publicInfo = JSONObject.parseObject("{\"app_id\": \"+"+APP_ID+"\"}");
        JSONObject privateInfo = JSONObject.parseObject(sbf.toString());
        bizContent.setPublicObject(publicInfo);
        bizContent.setPrivateObject(privateInfo);
        request.setBizContent(bizContent);
        request.setBizContent(bizContent);

        SalaryservicePayoffUdpBatchApplyResultResponseV1 response = client.execute(request);
        if (response.isSuccess()) {
            // 成功
        } else {
            // 失败
        }
    }
}
