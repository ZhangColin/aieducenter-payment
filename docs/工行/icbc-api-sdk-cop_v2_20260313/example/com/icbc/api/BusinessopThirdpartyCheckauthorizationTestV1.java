package com.icbc.api;

import com.icbc.api.request.BusinessopThirdpartyCheckauthorizationRequestV1;
import com.icbc.api.request.BusinessopThirdpartyCheckauthorizationRequestV1.BusinessopThirdpartyCheckauthorizationRequestV1Biz;

import javax.management.remote.JMXServiceURL;

/**
 * @author
 */
public class BusinessopThirdpartyCheckauthorizationTestV1 {
    private static final String APP_ID = "[需替换]合作方自己申请";
    private static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    private static final String HOST = "[需替换]工行API网关地址";

    public static void main(String[] args) {
        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        BusinessopThirdpartyCheckauthorizationRequestV1 request = new BusinessopThirdpartyCheckauthorizationRequestV1();
        request.setServiceUrl(HOST);
        BusinessopThirdpartyCheckauthorizationRequestV1Biz biz = new BusinessopThirdpartyCheckauthorizationRequestV1Biz();
        biz.setOuterId("123");
        biz.setTargetUrl("https://icbc.com.cn");
        request.setBizContent(biz);
        // 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
        String s = null;
        try {
            s = client.buildPostForm(request);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
