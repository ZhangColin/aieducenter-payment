package com.icbc.api;

import com.icbc.api.request.EiopCouponGetRequestV1;

public class EiopCouponGetTest {
    protected static final String sign_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCc9yEvrEMR7SKt9ABvxwx9WF6DtU+R2BSWJH0UtoeGQoGNBivPM9R/A3JJekKLLUK31R7vczosELhssRwBOzD0KTFzjZUTTRX0yyC+CyWPM3p2VMBIN5Cz7xU9UorALb86YWIgQu80WtTX19q2+DZMER37lUG3bPbK6LxLMItmSBJHa1EUgNZTdGZoloZjduH/+XABanu6C2TWEhVkTsMcwHaHqIDF/2O9BeyPckZzdSC5A+ipyGqTFd32YIuXswcrgau6t1IHz9Gn+9xeaAs9eQ1bBit6VcaEETa+RyXqTkuhRS9Ke1r0qBEuH6tZZliLCO3urv9BXs14j7anE51xAgMBAAECggEAMESKbveA9G1Agc+LptPF0nGvNr81hbq1senskMcEa9PdtLkWFbRxftFXfqLa/aNgZdlilu2PEOffXoUkAHjsN9jG/SqnkfTdcJZaLjaNSTjiSqsLKen0VHCLznyocCphKJmzrAoJNsdLtkgJWMSBJekJmEiwmbAnQARLiq+V3lCFidh39JEBM9+wdnjz9yHimeVj+q5q3Tf9sv4NJPQnrtidMYEGSBgggnvCmovtVx/c6WZ+4JMJcUBe3kJv9aIhxtiieXHWrmYbmreaEWI8LB6e16pW4SqkcUry5vF53iYOvAIl8ErVPCfN/H9ZTM52yUGPJfDYgowhLZGOcvGJVQKBgQDbnQ2GIM7VpeFtgt59yZ4RT1XWfPoJw+8WMzODQywegmZU4pgtfiwZfusi6CwP+X5tVIS7fP1r7CMNN1cesfM+16Sp/kudNWLJAus6MrYDU8Y11s/Wtgkp82rw1vse+CnPpb4KR38v+eJCVZX9QQzzoaw71bMx3kUw+TKMdzeZIwKBgQC2+NgmHqGhs1eyVML9lib947Eat4MjBJf4Xx2gOsDMORhx0tEK5fum46T3yUKhzUqnO4znmcjFgty2AvE2BTZ/due5UPNr9J/lVUnjSRk/OqtFfm4dcIe8SOlkbgSwwxEZ7bivrssqmUCq+2CdSYzbxoOMaiebzZEl++fhIGz6WwKBgCi3wsUmmhLEsbozNHtQAW2mLv0YqOdcm5ybG6bvsmNpu/1rEdoFdbm4bXJIZ7sCgC1XKVD1yKrU2l7d7tozFi2Fn/MgwfZA6rLoRqLAujx9sNdUn65oi+RqTF6hDWhdjKsCSOzw+qTziL7RzYBqKjOVuzBQ2+uYEW4WFggzJmzPAoGBAKlLacMriGWzKKtKHfjAW2eCqzWLInzeHTaj2yeL9SOAmIuIgf7ReJyb+OGDiHFpH2/Ie8E22ztUr56A7moLPLeHbzRaIEB84Q0ycuEnm4KTaDEun/gBTLX7yGcyHIjolAQnnkB/PGA32EvbtzMRAJHBBap/9hIEjA5XG9+DPxINAoGBAIr/h4mQRr66RP5rR4JUaG5iSuCImB9iNl52BFhTLcxLt06ybpp31kmW5q5V0O8midbK27jrdk/6vMy0coEeZaE9aSTVnp9uQlenMrlFLSGBCpebLaWZhfHLuiriJDwUDyB4X3cKc9JcbVNf7N3ZVysyff9u6a44NYPXC6n0btJq";

    protected static final String encryptPriKey = "wts8/tRJCA5jJulOqPb+jw==";

    protected static final String APP_ID = "10000000000000017880";


    public void test_cop() throws IcbcApiException {

        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, sign_PRIVATE_KEY,"UTF-8","AES",encryptPriKey);

        // 设置请求对象request
        EiopCouponGetRequestV1 request = new EiopCouponGetRequestV1();
        // 设置请求路径
        request.setServiceUrl("http://ip:port/ui/eiop/coupon/get/V1");

        EiopCouponGetRequestV1.EiopCouponGetRequestV1Biz bizContent = new EiopCouponGetRequestV1.EiopCouponGetRequestV1Biz();

        bizContent.setCustMobile("18668736829");
        bizContent.setLaunchChannel("702");
        bizContent.setLaunchScene("001");
        bizContent.setCino("020000234674406");
        bizContent.setUserId("A20200920000491810");
        bizContent.setDeviceId("B9F1225B-3D27-4E89-8242-2E0B3FA0E03C");
        bizContent.setMac("E8:6A:64:0E:B5:B5");
        bizContent.setTimeStamp("20210531162918300");
        bizContent.setTellerno("000998889");
        bizContent.setZoneno("00200");
        bizContent.setBrno("00021");
        bizContent.setEntranceFlag("12");
        bizContent.setJdServerIp("ip:port");
        bizContent.setIsApp("0");
        bizContent.setReturnUrl("http://xxx.xx.xx ");
        bizContent.setProvince("北京");
        bizContent.setCity("北京");
        bizContent.setBakfield1("");
        bizContent.setBakfield2("");

        request.setBizContent(bizContent);

        // 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
        System.out.println(client.buildPostForm(request));
    }
}
