package com.icbc.api;

import com.icbc.api.request.AggregatePaymentRequestV3;
import com.icbc.api.request.AggregatePaymentRequestV3.AggregatePaymentRequestV3Biz;


public class MedicaloqrcodepayresultRequsetTestV1 {
    //1.工行侧网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //2.appid
    protected static final String APP_ID = "10000000000000002156";

    //合作方私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";

    public static void main(String[] args) throws Exception {
        //aes秘钥
        String priKey = "TKhSiK9Blwr6+aCq+O0MFg==";
        //初始化客户端
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", priKey, (String)null, (String)null, (String)null, (String)null, (String)null);
        //构建请求
        MedicaloqrcodepayresultRequsetV1 requset = new MedicaloqrcodepayresultRequsetV1();
        requset.setServiceUrl("http://ip:port/api/mybank/account/medicaloqrcodepay/querypayresult/V1");
        //构建请求参数
        MedicaloqrcodepayresultRequsetV1.MedicaloqrcodepayResultRequsetV1Biz bizContext = new MedicaloqrcodepayresultRequsetV1.MedicaloqrcodepayResultRequsetV1Biz();
        bizContext.setTrxCode("queryPayResult");
        MedicaloqrcodepayresultRequsetV1.ReqData reqData = new MedicaloqrcodepayresultRequsetV1.ReqData();
        reqData.setChnId("xxx");
        reqData.setFixmedinsCode("xxx");
        reqData.setPayCert("xxx");
        bizContext.setReqData(reqData);
        requset.setBizContent(bizContext);
        try {
            System.out.println("request:"+ JSON.toJSONString(requset));
            MedicaloqrcodepayResponseV1 response1 = client.execute(requset);
            System.out.println("response:" + JSON.toJSONString(response1));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

		
	}

	
}
