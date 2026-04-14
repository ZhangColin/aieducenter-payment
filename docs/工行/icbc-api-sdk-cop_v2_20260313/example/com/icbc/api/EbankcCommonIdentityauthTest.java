package com.icbc.api;

import com.icbc.api.request.EbankcCommonIdentityauthRequestV1;

public class EbankcCommonIdentityauthTest {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIyANF5Z7e/J2DLCH7785Xw301UbvCjTGbxcD8TwLHf/CuoKVKdKNCcUncnBwIpmRu5acyMuwkeRTnjB6q5C5OnXayyoNxjDK6SzW9LfvADAnn0/dxxdtdW5TyspPyWC7IfELTa84Utqyr2fY7mj0oxl3XntKbP+gzWYeP6JUlXfAgMBAAECgYAEaGE8NIYKZ8KolP5592UbqISwvtYAUd8U0a6M9xvuB8pH3VsahHFuaG8qzTFFJkBDagMCr4zYwe48S5kZLnAP+hPIBTYGTy98EmrGZvEH3sDWyauDHGGlLCgPOOMegf4vMSvUUR3A4k+FUZwWUOnDhvT/Slftc6LK81MerbgZgQJBAL5nzYsh2TrbyahXRb+zyw3wvskjrhADOyIDVAkiC4PDU/g3QJVd3g3jnKHQQKr6tUrCmPAOWSJ9RkbOmF+wAacCQQC85zgkTJeJlS17uL63guEOO39lGw2xtmUTKI5Kp6LSv3ecxHgHYU94vlWkv0+CS9UGHxTeUymbPuOZoDBErmEJAkEAkb36wuih0j+x170VkmsYng8MugEplvAiLyLFDEiW78BvYatJMAutkIwMtH8nQLiuVA8n6XfSGmgxhrlUQMoEOQJAVchmXU2Oz0K4OePmb612iCEKtB459MbQVYfoPgysSyn5pm4t16QgE8PTXpImCBRps3Lv5ho73a0MbForMTVDCQJAYxstWDQSAB6VAkes79ct9KsDQ4dCz2CJ+yxdmzeOTcq73nK4MuRhRAM3ZARcHyjiIsxEOAlgUySJ/0BEqjdNAw==";
    protected static final String APP_ID = "10000000000000018252";

    public static void main(String[] args) {
        // 构造client对象
        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

        // 设置请求对象request
        EbankcCommonIdentityauthRequestV1 request = new EbankcCommonIdentityauthRequestV1();
        // 设置请求路径
        request.setServiceUrl("https://ip:port/ui/ebankc/common/identityauth/V1/");
        // 设置通知消息路径
        request.setNotifyUrl("http://ip:port/index.jsp");

        EbankcCommonIdentityauthRequestV1.EbankcCommonIdentityauthRequestV1Biz bizContent = new EbankcCommonIdentityauthRequestV1.EbankcCommonIdentityauthRequestV1Biz();
        bizContent.setApiName("JSON");
        bizContent.setApiVersion("001.001.001.005");
        bizContent.setSerialNo("hg10000000000000002136201701220120210001");
        bizContent.setAutoTurnFlag("1");
        bizContent.setPlatformName("XXXXX");
        request.setBizContent(bizContent);

        // 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
        try {
            System.out.println(client.buildPostForm(request));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
