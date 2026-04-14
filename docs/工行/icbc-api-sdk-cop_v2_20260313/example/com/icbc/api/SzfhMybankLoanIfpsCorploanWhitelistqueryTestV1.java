package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.SzfhMybankLoanIfpsCorploanWhitelistqueryRequestV1;
import com.icbc.api.request.SzfhMybankLoanIfpsCorploanWhitelistqueryRequestV1.SzfhMybankLoanIfpsCorploanWhitelistqueryRequestV1Biz;
import com.icbc.api.response.SzfhMybankLoanIfpsCorploanWhitelistqueryResponseV1;

import java.util.UUID;

public class SzfhMybankLoanIfpsCorploanWhitelistqueryTestV1 {
    //企业私钥，企业通过工行SDK生成
    protected static final String MY_PRIVATE_KEY = "";
    //工行网关公钥，请联系工行获取
    protected static final String APIGW_PUBLIC_KEY = "";
    //加密方式
    protected static final String ENCRYPT_TYPE = "";
    //加密密钥
    protected static final String ENCRYPT_KEY = "";
    //请求url
    protected static final String REQ_URL = "https://apipcs3.dccnet.com.cn/api/szfh/mybank/loan/ifps/corploan/whitelistquery/V1";
    //签名类型
    protected static final String SIGN_TYPE = "RSA2";
    //编码格式
    protected static final String CHARSET = "UTF-8";
    //报文类型
    protected static final String FORMAT = "json";
    //应用编号
    protected static final String APP_ID = "10000000000000202049";

    public static void main(String[] args) {
        SzfhMybankLoanIfpsCorploanWhitelistqueryRequestV1Biz bizContent = new SzfhMybankLoanIfpsCorploanWhitelistqueryRequestV1Biz();
        bizContent.setApplyNo("A202108260923001230001");
        bizContent.setApplyDate("20210826");
        bizContent.setCorpCertCode("020090001926831");
        bizContent.setRepresentativeId("23424214");

        SzfhMybankLoanIfpsCorploanWhitelistqueryRequestV1 request = new SzfhMybankLoanIfpsCorploanWhitelistqueryRequestV1();
        request.setServiceUrl(REQ_URL);
        request.setBizContent(bizContent);

        System.out.println("req=" + JSON.toJSONString(bizContent));

        SzfhMybankLoanIfpsCorploanWhitelistqueryResponseV1 response = null;
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, CHARSET, FORMAT,
                APIGW_PUBLIC_KEY, ENCRYPT_TYPE, ENCRYPT_KEY, "", "");
        try {
            response = client.execute(request, UUID.randomUUID().toString());
            System.out.println("resp=" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败,return_code=" + response.getReturnCode() + ",return_msg=" + response.getReturnMsg());
        }
    }
}
