package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.InvestmentFundtrusteeCustomerlogoutRequestV1;
import com.icbc.api.response.InvestmentFundtrusteeCustomerlogoutResponseV1;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2018/10/16.
 */
public class InvestmentFundtrusteeCustomerlogoutV1Test {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDddIu/Wz28MTAoFCIlcL9vM1dRy4oAYwzQwbkzKvHlho/pjEJhY7vnUwFHVRwhOXIlBogqLpkx60UAwvF3+cSeh5zQvKsFhpwr6WEsrS1jAilvEVyfu8vISveO5c1KjZgFj8TM4qP87sDGfi6UOvLKttfb6f+sATdt07KRCDTb+CL3W4rgyXSarlrM35CKjcm0LSnvXHVV+daGbnrx4K858gPWdaJvf5++Jd32RewbvpTPfcQmEjyAgPokla7EzXUlxtqv2ZJr7WfXxrJCf0FBJYqX6ysexMzQXPMn0XZtbU1QEsmvglgRQ2PIR3vD+Re50ND7AjyYWGgL3OAQyZ7ZAgMBAAECggEBAKzpShBgioR9l49DoSHAtgh6euyH0znYHPKGwDc+id9gQM/nTxFB0T6eZfovbyZV91UHk5v0BHOgsqEUlXvLDoWFd6DYH2TSJPB4agJ7OVv8HSP21251MHk0Z7UwoylaA2AcUFp1f/uG5AhYD8B3golbWJICVDoWPRRI3j01vigdU+NAS+EKMITXjn1XNovwo0PElxHLTW0w5WLlzzSLY3HvWfugctHFTLN/9moiUfDd3JMZ4cogS8rjcQSUZd3nn+yqALPsvt/XIYrOFLCwXEzcwPz7UdnR8ouiW5UydPQ9RUyQ1EaOylV1fTEq0EVo23QfvrHNa9pWvP4pRo0BlB0CgYEA+SWL3zKLcur5v6SPtwaU6NmeIBT9gu4uo+9+si78syONNRT3BU8ifPbcapCQZUPaqt2SDH/qo9kQiXll9nd7Fd0tTGU2EZGkBI2MUrGeRXEer+Yhc8X8D/Ri6Ac9X9wQ8J/Cplk3dO8pnrKZj0LlOPg+WFROadIS3C+iOm5lRM8CgYEA44wAMUUFs6/gDdU7OTqdXY/2U+sEuYVQWFtlntX1WX+vNxcNBvccHyW8skwlXBVKgn8g41a4IOpSKI7Igl1u9ZE4qLtdg8LrHM9zSwaD5V5oOuzhQBf0E1C6dNRQqMHTvfMa8E5l0Wq7kXjgI91MmROnFDEJA2iCtt15NDGVG9cCgYEA0Ku7zMLevO9FIlfH7rlaevPZVnF+Ku0mrzNl4AMurzM1j6HxisbO0e9IiRBvgAEm4jkVm9Z/RaAGE75TwewscRGAYR3b/TcIMrADOTd+ohauZxDOL9NA3HQJauC1bNY6ce687uqGYi/1oe9gYfbbWa3BOeXSjxrdpQFYVKwlsU0CgYBAY5B2YLKxWxZKs8kTc6uuGxu4OWvX14qZxhsmbCMGVWROcX5lNy3E/pe72GJGAVxZOwIicpqd7dCb23OPuEK6yIJA9OwMae6/81ALredK04EpE3jV+zE8+f4ZKy874cU6Hf3EfFrBfLNhAdRjMa8ILhB8XTfQuBUtC9TNWS55dwKBgQCD6N63uTky92jA/g+njQvnQu5piDTBzgV7n5xcko7vLM/7nXHBZrQhiLS2r0u7lT1gyHanEBbrFtDVx0wVgZluJkw48s6PwWbrTVCvK3IGq3jsLZpqv/pKjcg//37UsNT6x+CbJiSWZ8IAfRqaz9UmTpLIwr2Dv9AKl4l2bS99uQ==";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected String APP_ID = "1000000000000_4094636";


    public void test_cop() throws IcbcApiException {

        APP_ID = APP_ID.replace("_", "");

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        InvestmentFundtrusteeCustomerlogoutRequestV1 request = new InvestmentFundtrusteeCustomerlogoutRequestV1();
        InvestmentFundtrusteeCustomerlogoutRequestV1.InvestmentFundtrusteeCustomerlogoutRequestV1Biz bizContent = new InvestmentFundtrusteeCustomerlogoutRequestV1.InvestmentFundtrusteeCustomerlogoutRequestV1Biz();
        bizContent.setUserName("张三");
        bizContent.setRoleId("1");
        bizContent.setCertType("0");
        bizContent.setCertId("3548261983_07225157".replace("_", ""));
        bizContent.setMobilePhone("13913913900");
        bizContent.setToken("ddADrgffsXXXXXXXXXXfdfryTYJMFL");

        request.setBizContent(bizContent);

        request.setServiceUrl("http://122_64_61_81/api/investment/fundtrustee/customerlogout/V1".replaceAll("_", "."));
        InvestmentFundtrusteeCustomerlogoutResponseV1 response = client.execute(request);

        logger.info(JSONObject.toJSONString(response));
        if (response.isSuccess()) {
            // 业务成功处理
            logger.info("success.");
        } else {
            // 失败
            logger.info("error." + response.getReturnCode() + response.getReturnMsg());
        }

        Assert.assertNotNull(response);

    }

}
