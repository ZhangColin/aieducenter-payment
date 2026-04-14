package com.icbc.api;

import com.icbc.api.request.JftApiDclLoanapplyprogressRequestV1;
import com.icbc.api.response.JftApiDclLoanapplyprogressResponseV1;

import java.util.HashMap;
import java.util.Map;

public class JftApiDclLoanapplyprogressTestV1 {

    public static void main(String[] args) throws Exception {
        String APIGW_PUBLIC_KEY = "xxx"; //api网关公钥
        String MY_PRIVATE_KEY = "xxx"; //私钥
        String APP_ID = "xxx";   //appid
        String encryptKey = "";//加密秘钥
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", encryptKey, "", "");
        // 设置请求路径
        JftApiDclLoanapplyprogressRequestV1 request = new JftApiDclLoanapplyprogressRequestV1();
        JftApiDclLoanapplyprogressRequestV1.JftApiDclLoanapplyprogressRequestV1Biz loanApplyProgressRequestV1Biz
                = new JftApiDclLoanapplyprogressRequestV1.JftApiDclLoanapplyprogressRequestV1Biz();
        loanApplyProgressRequestV1Biz.setAppId(APP_ID);
        Map<String, Object> params = new HashMap<>();
        params.put("serialNo", "20220401152445123456");//融资申请上传序列号
        params.put("outVendorId", "33224355");//外部商户编号（融资申请人）
        loanApplyProgressRequestV1Biz.setParams(params);
        request.setBizContent(loanApplyProgressRequestV1Biz);
        JftApiDclLoanapplyprogressResponseV1 response = client.execute(request);
        if (response.isSuccess()) {
            //成功业务
        } else {

        }
    }

}
