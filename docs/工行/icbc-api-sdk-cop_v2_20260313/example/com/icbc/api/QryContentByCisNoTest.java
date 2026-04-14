package com.icbc.api;

import com.icbc.api.request.QryContentByCisNoRequestV1;
import com.icbc.api.response.QryContentByCisNoResponseV1;

public class QryContentByCisNoTest {
    protected static final String MY_PRIVATE_KEY = "xx";
    protected static final String APIGW_PUBLIC_KEY = "yy";
    protected static final String APP_ID = "";
    protected static final String ENCRYPT_KEY = "aa";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES,ENCRYPT_KEY,null,null);
        QryContentByCisNoRequestV1 request = new QryContentByCisNoRequestV1();
        QryContentByCisNoRequestV1.QryContentByCisNoRequestV1Biz bizContent = new QryContentByCisNoRequestV1.QryContentByCisNoRequestV1Biz();
        request.setServiceUrl("ip:port/api/outer/fundClue/qryContentByCisNo/V1");
        bizContent.setCisNo("020000215713737");
        bizContent.setAreaCode("0200");
        bizContent.setChannelId("F-EINFO");
        bizContent.setEventId("030306114050105362159213502");
        bizContent.setIsLimitArea("1");
        bizContent.setIsRelateZhProd("0");
        request.setBizContent(bizContent);
        QryContentByCisNoResponseV1 response = (QryContentByCisNoResponseV1) client.execute(request);
        if (response.isSuccess()) {
            //成功

        } else {
            //失败
        }
    }
}
