package com.icbc.api;


import com.icbc.api.request.CreditCardQueryBadDebtRepayRequestV1;
import com.icbc.api.request.CreditCardQueryBadDebtRepayRequestV1.CreditCardQueryBadDebtRepayRequestV1Biz;
import com.icbc.api.response.CreditCardQueryBadDebtRepayResponseV1;

public class CreditCardQueryBadDebtRepayV1Test {

    private static final String APP_ID = "10000xxxxxxx9"; //20年2月
    // 调用方私钥
    private static final String MY_PRIVATE_KEY =
            "-----请使用正确的APP私钥-----";
    // API网关公钥
    private static final String APIGW_PUBLIC_KEY =
            "-----请使用正确的网关公钥-----";

    private static final String API_SERVICE_URL = "API_SERVICE_URL";

    // AES加密密钥
    private static final String AES_KEY = "AES_KEY";
    static Logger log = Logger.getLogger(CreditCardCommonRepayV1Test.class.getName());

    public static void main(String[] args) {

        // 签名类型为SHA256WithRSA时，传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, null, null);

        CreditCardQueryBadDebtRepayRequestV1 request = new CreditCardQueryBadDebtRepayRequestV1();

        //访问工行API平台URL
        request.setServiceUrl(API_SERVICE_URL + "/mybank/pay/creditcard/transfer/QueryBadDebtRepay/V1");

        CreditCardQueryBadDebtRepayRequestV1Biz bizContent = new CreditCardQueryBadDebtRepayRequestV1Biz();

        //APPID
        bizContent.setCert_type("1111111");
        bizContent.setCert_no("11111111");
        bizContent.setCustname("1111111");
        bizContent.setPalm_global_user_id("1111111");
        bizContent.setNonce("1111111");
        bizContent.setTimestamp("1111111");
        bizContent.setLang_code("111");
        bizContent.setIp("111");

        request.setBizContent(bizContent);

        CreditCardQueryBadDebtRepayResponseV1 response;

        try {
            response = client.execute(request);

        } catch (IcbcApiException e) {
            log.error("IcbcApiException occurred for APP_ID: {} and request: {}", APP_ID, request.toString());
        } catch (Exception e) {
            log.error("RuntimeException: ", e);
            throw new RuntimeException(e);
        }


    }
}
