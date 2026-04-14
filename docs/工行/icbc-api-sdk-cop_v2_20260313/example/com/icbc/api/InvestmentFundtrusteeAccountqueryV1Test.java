package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.InvestmentFundtrusteeAccountqueryRequestV1;
import com.icbc.api.response.InvestmentFundtrusteeAccountqueryResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.icbc.api.IcbcConstants.*;

public class InvestmentFundtrusteeAccountqueryV1Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentFundtrusteeAccountqueryV1Test.class);
    private static final String APP_ID = "";
    private static final String PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAh1/Lrxiv8E1LrJrFoZZ133Wfml7qftmHau7o5vyXaSJqj9tv05HEO9/P5gP8SxIVdf2hBVBvKTM4cPb50vn3OMT25mwocNKRmyxsS3VpkobQM26PSBaYocTEYwXRKsmTNOuLQ2EMZPBh0RRw3wG5NHI/adnHSS3CpjFnNe2PThOfQKRuwnnVFZAu8Q6xbpV6N2ef8NPhS7Ls1jtpp8rOiVRybp7txZCdcwoxtYWQkhiwTLNt/xQo/CJ76rzlt9nia3RY6TtIhF4dyToy+uE5JlJZYDaow8TB45UiLyhZIX9b1OpSVivhhjvk5/iAiGTXX/O2OFp+vDzPtWJm4UjAKwIDAQAB";
    private static final String PRIVATE_KEY = "";
    private static final String AES_KEY = "";
    private static final String API_GW_URL = "http://ip:port";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE_RSA2, PRIVATE_KEY, CHARSET_UTF8, FORMAT_JSON, PUBLIC_KEY, ENCRYPT_TYPE_AES, AES_KEY, null, null);
        InvestmentFundtrusteeAccountqueryRequestV1 request = new InvestmentFundtrusteeAccountqueryRequestV1();
        InvestmentFundtrusteeAccountqueryRequestV1.RequestBizContent bizContent = new InvestmentFundtrusteeAccountqueryRequestV1.RequestBizContent();
        bizContent.setFileType("3344");
        bizContent.setDeptCode("0323");
        bizContent.setXmlInfo("xvcxvxcvxvxc");
        request.setBizContent(bizContent);
        String apiResourceFullPath = "/api/investment/fundtrustee/accountquery/V1";
        request.setServiceUrl(API_GW_URL + apiResourceFullPath);

        InvestmentFundtrusteeAccountqueryResponseV1 response = null;
        try {
            response = client.execute(request);
        } catch (IcbcApiException e) {
            LOGGER.error("execute fail!", e);
        }
        if (response == null) {
            return;
        }
        LOGGER.info("response: {}", JSONObject.toJSONString(response));
        if (response.isSuccess()) {
            LOGGER.info("success!");
        } else {
            LOGGER.error("fail!");
        }
    }
}
