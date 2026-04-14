package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftApiLoanQryLoanLimitBusisRequestV1;
import com.icbc.api.request.JftApiLoanQryLoanLimitBusisRequestV1.JftApiLoanQryLoanLimitBusisRequestV1Biz;
import com.icbc.api.response.JftApiLoanQryLoanLimitBusisResponseV1;
import org.junit.Test;

public class JftApiLoanQryLoanLimitBusisTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCavjp2dLfcL0U+AUGslw28gWqEfWXQVRuWo5YjR5Ynw8ykqI12wD+7sOwXzmRJx9YkkOCHl926Reav8V7df+ot5PodmAlioCCUSoxbgSHU3lHqok81agzYapF4LK9tvMtfea6tYjaZ+OVgCorYrzRMDRHzJL1TlsIYHpajSqgXIKQSK/942wsuVXk5//40gQLf7wM8h5sBGcfjFLxVT+hHK5/l7DaSOm8zEenOqR59lwvMskhhbpdQ0cRKLA7n7+t37eJXPZGALm4Jew8dgI7vpJl2+iqWjMXcmhtDD6ksYQzu7M3GxfRktX2XBhInh4imB4sQXbaxIXfu2ckTcIyJAgMBAAECggEAHlQ8hy0XQDuvmZiLc7VjhtfitbcSNoQH1T2Y1q9YOdEBkiBLIcRKeZISpBPayi81xJL0vv7AAZtjvIZVbpp8nqQrdZNv+3mhCQ+XdPPZWqx8Z72KQtDXVa+FdYVkmOCRB9SErQz74K/M3UcTsaadXG6PXxWLWg/JWQrdwYGHcoiugsMr6zskUk5trh761F40/Z1yhc9W7IWBKI+6DIbx8uC6WE2nnNhHs3UYwGon6u1YdXdkgML6Jzb8W+ATRITkVXa9T7FioU8xV8AdZzUfudjepxw/zaCF4OZ2HLBV9ZhbfUsWhNyTJGeW1mC3DyOaQO1VSace1xkLPqMsB6DHgQKBgQD1Z4KblWIdmMlIngqpGmw4MVQSpVV3fkrho9WpNIe10Ack5JCUFKgPTyZOMrhrkJ7F0msN55AplLpqwixZ9spQx1SOCB5VD5QktTJlqPQIPqNS81uKpV9xbVQJN3/aOU9k2B1wC0VMiifMWX266bxu3AbFXWgayPxx7MqJQeBROQKBgQChbKBJ3zng8I18T+R+HFo9irPjq78Rjo7uQfBwYAos9+BbBGMmZZQJrbwhba9IQk324SZI7BPnASpkLTWYMPB+cSkWoyH64F+PUwTlqDyWp+eyBD/W+Jq8LnRtYYzs8ga7uh/Tt/kctA3geZcj2FEfKNuZQW4s33ADUugxeRkl0QKBgHBIdif9x7DHEYHaXgJVhqKHX3JQl7Re4qAvODl9QYLctggZVFWMxgCLNHmfF2aP+zWEO1tXUkzUMh9azLQ1CQjAy11ZNZex0xSgm3iMP2vuTQhNNNtRI0Rgw4ycCjGs+zim8YEshv6oT+FeKPr2qzMGFUsEm/d1cpwlvd3jeGFxAoGAI4uG1B1qYkS18vyopLhbipejOuvTm+sXQBGW/ze2peZDKeeSaZ8OdP8joPzFDxVCk61KP950F3bjPcHSvltwaXdDg++SCOslP6sZNfTQup/oXgOfhxehAvT7wCYt4PL+VpIKdhVZrl9beuVrSYJVgpX9nQaSWyEsSh8Ub+hpiDECgYEAtU+5G4hd0hwEEy2ImtcvvnroZHG2tNYgVa0GFUU7ak0tJ7491/yZ0DtOixiwIHWklUgTpAh1UcV9LBvwSlo5r5yJKOGtxh6MvUlI11tOFQVKUS+P5Xt2H5Zb3+T4wKvjbvRzu31N/GgYTS6aNOEJffUUZVrnzMy/CDSgXxmV/e4=";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "1000xxx00108514";

    @SuppressWarnings("unchecked")

    public void test_cop() {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES","eyiSiHvFqYFLu/AZDCt+4w==", "", "");
        JftApiLoanQryLoanLimitBusisRequestV1 request = new JftApiLoanQryLoanLimitBusisRequestV1();
        request.setServiceUrl("http://gateway-cfjs-app.sdc.cs.icbc:9080/api/jft/api/loan/qryloanlimitbusis/V1");
        JftApiLoanQryLoanLimitBusisRequestV1Biz bizContent = new JftApiLoanQryLoanLimitBusisRequestV1Biz();
        bizContent.setAppId("100xxx00108514");
        bizContent.setOutSubVendorId("215xxx46");
        bizContent.setVersion("1.0.0");//设置接口访问版本号

        request.setBizContent(bizContent);
        JftApiLoanQryLoanLimitBusisResponseV1 response;
        try {
            response = (JftApiLoanQryLoanLimitBusisResponseV1) client.execute(request, System.currentTimeMillis()+"");

            //System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                //System.out.println(response.getReturnCode());
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
