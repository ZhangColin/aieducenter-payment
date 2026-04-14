package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.InvestmentDepositDpsellRequestV1;
import com.icbc.api.response.InvestmentDepositDpsellResponseV1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class InvestmentDepositDpsellTestV1 {

    private static final Log LOGGER = LogFactory.getLog("com.icbc");

    private static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOMjCEUP9IiTauLI0WaeMsARmyrqNhlykBY7L5UsvRJJmmli/RpvFetsN5QAWaX//6YsKWdDMz88ccTrB5BZaC4wRIKizWySDP2ou+F/rjqpAvYaYILG+EB9ap3h+i0D21m6asL2pVqcLPIPBzmBthPe1gxzdasWELC4ds7Jy8pSxTWhzV/IMAQIoOP+Aft9z56PSLD3TqX0dSpa/4JUmFkPAwo31Sp18xwPhMgh0SHxg8m/U+mYGlctQmsp951ObHJcJqrWJuvRpnC5djCe5khli8zD5OaDNRs/lQYFX9cgw+xP7clOoFe+JZMBzNlOASfV0uEvEUx7SNprAEwFKFAgMBAAECggEAK4hyEhA6F5rqgqhhCVuhkPdFRs7wdhAxHZvIT0xp2b26zKVuPmE9xCFv9s8C6CA73gweRrGMe3tGmgP9Qw/S5H9b8Dv5PkiVIw1leu0cglbVjav3iMkR2voLXjYCuXXHbrIYdN6PT2qJmaAb/Ue0GjE7W0WiTMHBVABTlaVa1uXFJLBNdjAi5yPslmhGbD417htfIThGc7sUaQ/FTF+cQ9uqmmNpER6/aJ+BAxrSpWvFCsXQTPv73FfpaAmXGNIGRpYP6jOORwce+yIHtoMD1VMZhLmi2BxW63aDmWK5x/FOMtNcWUIHeNDVKaUy9gmM1+pbblNL5DwU6L1MjknggQKBgQDSyh4h+D+7WAW0RpAzkNt9E4IJu3ROtVq+rwOfkmai87izsKvVxq4UlSbk9sOsygQqKP9FxzxHDrPsQDvJT9ORSv8MWpUcnCSJ01JCoRK3vKcJo6lLpMglknzrg6hAm2fM0lzwtYD0n2I30ZV8GO0CxA/OEH2B3RaGKHBLXMOMMwKBgQCssco+JtpD48Dpc0/D+i7SeSq6rQLCBaLZBSlvK7RVRmnQBUzxmB91y/pPUh9VvU490INvH9UnfziBAs1n5OMhudmqOBAz7UUyO4UIgIB45aPqwyaGpY19HIr4XZUiT+8Ek5Uup7YKsiQaePo2M63zvmmjNx5JWrQgc63AX9duZwKBgQCi67GKk/O+e3Q9OGTwVkAvVkql9wsYYre2tlLYnTFTNYlG6YCHBL88DWQ4iDoJbUL2yyUy66E9UrHiImuTV22w2QR0qfaiXFUpafokoZZ0CQ4aNzL80moYEDcZWXNMLFF4UAehzMfVSvOBLS6a8jEOeDe3D6vSGe3014Cj3xkNXQKBgCLZSc+a9YanqiEx9NG+Zb5xVw8OpLTUgFU89myJrD1dFTM2Lp2+LfMOc3eYQdyCdAyDTFKbYN80jAl0sFCb3r3KjEMORy7DXahSXHknvUUoShiwwtB/DftGTEDsWZDwAnKw+XTNwS2NXz28VfmkGiFKtnSKReFVnW3DCjf83zNXAoGAHoj6Rr4vKMs+ngpH9/6QbrHe3htp4dtp7Nlc1WJm8dUBN/1z6wNK7hXQOy6DW2T2PYReKZSeapaH5l/oCp/UNUAs3Xx3mbvJE13gLRZyp/bVMGdzmQNCd6FMfikaHPVvHBy17ckDiW9+8upwsnigut6JdzKH4GW5krxGPYwApSk=";

    private static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


    public void test_cop() throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(Const.APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        InvestmentDepositDpsellRequestV1 request = new InvestmentDepositDpsellRequestV1();
        request.setServiceUrl(Const.URL_PREFIX + "/investment/deposit/dpsell/V1");
        InvestmentDepositDpsellRequestV1.InvestmentDepositDpsellRequestV1Biz bizContent =
                new InvestmentDepositDpsellRequestV1.InvestmentDepositDpsellRequestV1Biz();

        int serialNo = new Random().nextInt(999999999);
        LOGGER.info(serialNo);
        bizContent.setSerialno(String.valueOf(serialNo));
        bizContent.setZoneno("00200");
        bizContent.setBrno("00012");
        bizContent.setWorkdate(Const.WORK_DATE);
        bizContent.setWorktime("15:40:20");
        bizContent.setOperflag("2");
        // PMD检查
        //		bizContent.setCardno("622202" +
        //         "0200008791614");
        bizContent.setAccno("020" +
                "01234567678444");
        bizContent.setPrice("1000");
        bizContent.setCashcnt("10000");
        bizContent.setTimestamp("2019-07-25 10:18:09");


        request.setBizContent(bizContent);
        InvestmentDepositDpsellResponseV1 response;
        try {
            response = client.execute(request);
            LOGGER.info(JSONObject.toJSONString(response));
            // 业务处理成功
            if (response.isSuccess()) {
                assertEquals(response.getReturnCode(), 0);
                //				Assert.assertEquals(response.getReturnCode(), 0);
            } else {
                // //失败
                LOGGER.error(response.getReturnCode() + ":"
                        + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
