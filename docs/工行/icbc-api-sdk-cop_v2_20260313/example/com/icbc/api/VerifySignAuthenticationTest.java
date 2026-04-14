package com.icbc.api;

import com.icbc.api.request.VerifySignAuthenticationRequestV1;
import com.icbc.api.response.VerifySignAuthenticationResponseV1;

import static com.icbc.api.request.VerifySignAuthenticationRequestV1.*;

public class VerifySignAuthenticationTest {
    //todo
    protected static final String MY_PRIVATE_KEY ="-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCG2c59TYTxx7Invd/ORZqgDaEFO+vDF6+FwU6ZeCO0OlC2Pfo0hsPH0gxcKNM342GbVAFsJDCRDcaOOc9ipjfh+9EKh0cp7wqbOah+sd26FJl2T5QsLe/znfHn18sy6iShvYQa10j3y9x2ogYqlt0+zG3wFjbmRv/UBla5t5Ucfa4MS48/Ux+zX6Is5q1xBTLqmSgL6RwuamivROdOlejPGSq7OJCKi85DgZwmdO7S1+U0fE1tN+i01nWb/eI/Vb2rDYzZ5u6/dIKXoTk8cMJnCyBa5M1xKqgV6xUHWxvITFXo/z5dmxdIVUoDOLOIo1fuK3v5DNdnU/YhnTnLlxZhAgMBAAECggEAOeGPUhVN9QGEJK7JO56AqiZvcPGxVoH6Iu1AMXtxx9AoCLE9jgIyUriD+aLEVn7QzH0YRzSZbAl/qliB/ZPe9K7DoUNDdmpWIR50hj0OMOram/+4V+Y30ld/F8Z2P5pkss/HvkWd2k60iG8vOmSa/nN81MqUMe9Tq8w0uW5lIF7pauCjXSObpqbJ6GXGY+8Nbq+CRMYC2MNwZoIsYekg7i4iVTkRYU95Nb/K+YKerngEJ+UIBNBMzK3ODLu9zQfDpkA091aVpaARwN2Rj34YJT/PaJdyoQJRc22byatbiWWrB+wEIehYL3+/gSKmC7Nzv2MYDDcr6/jwOA2UbBJyYQKBgQDM8mZy1g+wgNf+EJ8gMCHI/CEwA2iFh/9bCDr6CXbGrN0zvzZD08gURSix13p1vvMAAIP7o7PV9aG77RIqjuJ/UMx+Dcgss32yn1s8Ru8+eLlWPXrIcwRXJMBu6qfeXQwER2sM7jBWl0sDHq7UpABv9IY95PQoSbB5GBU6ORrb3QKBgQCocVVDvvt1qA0Rq8M0H/L/8sFrlxHfvTQX4c4lXYisq17jYzcdNJBEWRbklNtGO1kWDk0HWHvG6Wi4ND8cURRtYmQxeYucRv5DKCFrAxZQQ4BllgHvEwk5+XTqvLujTW08TZbG9Xbtr7ZJlBZ7guaCrYU2qKAZi8jV55skRN4OVQKBgApXdPn4eUQzzDJizlTGeP32UwpkMaMDUMxcJvW1AoxV7reMv4U+q4ryTnJi6CgG4aYvYHmTMiDqyyV7ef4xxZYdEqSNt3sh94bK6cSp46qoVtnWMlrpCuAG17sBFkVvNosEu48kecX0znEy3CmYuPjtQtTet79MsJ37rB6LASL1AoGBAJGnHpGsLYi5vkNPgUYrcla8NWCxizUrSEZ2bWmGDc8xiKWbf2bzsuRHHTKfPVTnsMsPpq/5ZV7FKlZOZ1w3CDDl6bmWXpDOGsM7ilocm0jgdcBiDF4M2eeVu/HXLXGyXAjPFtgIHcS+MiRBt4cp0+Wf4hc3jOlKJGpDEbY1DsbZAoGAHndnAc4cj02t2pdv8ZCWnWWUBx4Qw8IW3WC+vWvlH/gi5aIMo1nKeE17lZfZqDIg9zENryM0m9BgRSgqGyIBVKG+EICVaKzG42XRjfrUb4EVYiG+0ij3KhpIikHzHyawR+lzGydI5OduSjG8m6eTqHAnn7p0Cln7SAsQ0uIwozs=";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAhtnOfU2E8ceyJ73fzkWaoA2hBTvrwxevhcFOmXgjtDpQtj36NIbDx9IMXCjTN+Nhm1QBbCQwkQ3GjjnPYqY34fvRCodHKe8KmzmofrHduhSZdk+ULC3v853x59fLMuokob2EGtdI98vcdqIGKpbdPsxt8BY25kb/1AZWubeVHH2uDEuPP1Mfs1+iLOatcQUy6pkoC+kcLmpor0TnTpXozxkquziQiovOQ4GcJnTu0tflNHxNbTfotNZ1m/3iP1W9qw2M2ebuv3SCl6E5PHDCZwsgWuTNcSqoFesVB1sbyExV6P8+XZsXSFVKAziziKNX7it7+QzXZ1P2IZ05y5cWYQIDAQAB";

    protected static final String APP_ID = "10000000000000018251";
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        VerifySignAuthenticationRequestV1 request = new VerifySignAuthenticationRequestV1();
        //todo
        request.setServiceUrl("http://ip:port/api/enterprise/verify/VerifySignAuthentication/V1");
        VerifySignAuthenticationRequestV1Biz bizContent = new VerifySignAuthenticationRequestV1Biz();

        bizContent.setFrom("F-xxxx");
        bizContent.setTo("F-EBANKC");
        bizContent.setMode("syn");
        bizContent.setApiCode("PI00217");
        bizContent.setChannelID("ip:port.2024040109492645264949584.6070");
        bizContent.setTranDate("20260101120000");
        bizContent.setTranTime("080000");
        bizContent.setLogon_id("brstj02.c.0200");
        bizContent.setCA_Type("ICBC20DNCA");
        bizContent.setSignstr("");
        bizContent.setXmlSignData("");
        bizContent.setKeyData("");
        bizContent.setTimestamp("20260101120000");
        bizContent.setSessionTimeStamp("20260101120000");
        request.setBizContent(bizContent);
        try {
            VerifySignAuthenticationResponseV1 response = client.execute(request, "msgId");
            if (response.isSuccess()) {
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
