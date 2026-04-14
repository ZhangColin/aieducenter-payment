package com.icbc.api;

import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiDclLoanapplyRequestV1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JftUiDclLoanapplyTestV1 {

    public static void main(String[] args) {
        String APP_ID = "XXXXXXXXX";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        String buildPostForm = "";
        // 设置请求路径
        JftUiDclLoanapplyRequestV1 request = new JftUiDclLoanapplyRequestV1();
        JftUiDclLoanapplyRequestV1.JftUiDclLoanapplyRequestV1Biz loanApplyRequestV1Biz = new JftUiDclLoanapplyRequestV1.JftUiDclLoanapplyRequestV1Biz();
        loanApplyRequestV1Biz.setAppId(APP_ID);
        Map<String,Object> params = new HashMap<>();
        List<String> tradeList = new ArrayList<>();
        tradeList.add("11");
        tradeList.add("22");
        params.put("serialNo","20220401152445123456");
        params.put("outVendorId","653983022");
        params.put("counterpartType","1");
        params.put("counterpartNo","41856334185");
        params.put("counterpartName","张丹");
        params.put("secretFlag","1");
        params.put("notifyUrl","XXXXX");
        params.put("tradeinfoList",tradeList);
        params.put("relations","01");
        params.put("loanAmt","100.22");
        params.put("evalType","2");
        loanApplyRequestV1Biz.setParams(params);
        request.setBizContent(loanApplyRequestV1Biz);
        try {
            buildPostForm = client.buildPostForm(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(buildPostForm);
    }

}
