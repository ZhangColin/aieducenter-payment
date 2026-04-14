package com.icbc.api;

import com.icbc.api.request.EsvsSealVerifyRequestV1;
import org.junit.Test;

/**
 * @author kfzx-luqy
 * @date 2024-6-6 10:50:34
 */
public class EsvsSealVerifyV1Test {

    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "yyy";
    protected static final String APP_ID = "110000000035782";


    public void check_seal_verify() throws IcbcApiException {

        UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2",MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        EsvsSealVerifyRequestV1 request = new EsvsSealVerifyRequestV1();
        request.setServiceUrl("https://ip:port/ui/esvs/seal/verify/V1");
        EsvsSealVerifyRequestV1.EsvsSealVerifyRequestV1Biz bizContent = new EsvsSealVerifyRequestV1.EsvsSealVerifyRequestV1Biz();
        bizContent.setSzone("00200");
        bizContent.setReq("0/#/mainSeal?tokenId=123&type=1&channel=0&apiflag=1");
        request.setBizContent(bizContent);
        System.out.println(client.buildPostForm(request));
    }

}
