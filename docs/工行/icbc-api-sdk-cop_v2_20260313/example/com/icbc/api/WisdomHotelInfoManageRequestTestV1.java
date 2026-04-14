package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.WisdomHotelInfoManageRequestV1;
import com.icbc.api.request.WisdomHotelInfoManageRequestV1.WisdomHotelInfoManageRequestV1Biz;
import com.icbc.api.response.WisdomHotelInfoManageResponseV1;

public class WisdomHotelInfoManageRequestTestV1 {

    public static void main(String[] args) {
        // 企业交易秘钥
        String corpPayKeyAppId = "1000000BCSSS0000004097433";
        String corpPayKeyRsaPriKey = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC0aR3Z4DrMGtQh5sHf+9PnexePqgNlUqKSL17o35o5ut2oV4jwm9UWj+BWpoLmm/eavqPt8xM3F2omheout+P/wpTZKtMSsuCul1mgObjcIABX26TDOJNXimSxlaLTz07Y/NSU34xJOsU6hFPBU4zGu8WDq7OXkazOmC71Wq3UOt/8ZPMrsXaIySpsI/aj/+B6ojQ+3qvYArtYaLMohr0I7tZgpZABAUbtgALKjnBraqiySKo0sYuVdaEYrvWZt4hI2IzDxRJ120ItZepNCS56iGwwbbW04jRWJNnS22W5CLVdFnpQd7vL+gTW5zeZLZF7Vz4Crezh1fdEfmuYaADJAgMBAAECggEBAKVeiU9Se3Ll0Lc2l1Yhts7DhQhaNfylb4GCTIQ3ml36jP2nJ04t+XhR77hdrzWFl8RPpWIS4vUn0FR5gfzK8QJasSgTWiEUuGExOKVm461a5BWkrvWHoX+nyr8BQ5zVtRevQYkpFKV3apxF+aQHWs48BkO4msl/baB93OVn9ljHA9+p/nrq7/F1JaaEKvObs1k+iDiz+ypsIGJVcJmM/mFOMIkEsZ8L67XpPrpy0tiBx9J6cfrkNm11QincQMGs4qP5rxGugP5UjhCIdctJVmxUom97BTPZ9rlSDZAN7hnrqkYoDAB5bDzu+xQKCm1fi3xXtMnHtUmeEjMSL5N8rjUCgYEA+9liZPDGu4HaOGmHVhWJOsnnxk0XuxUFtVLmmN1l4Pgi4R1nBvpQvY7IIlpo6ATb9anm9Ci0jJ57hE2XiK0FaqxE2lRvfBHCYmJX5He7qpCsmZsOtk9feWi5fWM+oqlLKiHOjuAz8Xzsk+JFtfiR7UCIkknXO40Cc+svuw6qwE8CgYEAt2JQmqCORRFNGjKJh4v8GZgoNAi+4fBC2C7cxTEUZA1vrSUM+uKwx9GG7B6+5eVoAgkGJrb3dGVC3+eez2UcFr85xWJXx3z9iysVxM3YOk5s1moAArQ4MtyBqoo7UpAx+9ijmKTtJ9NT9c6kvjt+9C4LGRly1l3TL4vUcjyQD2cCgYAt9eX29dnOrkvcr6V7GEMK733UuozyH0wDYzWiAAVvoNj8rBj5NQU3Eq+DVWx/qyNZGcCEHkmUv/dala5pyumKJhYKog4WXkdTHQ9dKlydK/nTrnBo5ONPjMUNFOeBV9PXxIOln1TbZSjndAu0wcAfw/8oc0FMJNelv+3d/Tc8EQKBgQCCJhRuqWRPQGgoH5XbzU2uHH7r7wkJ8Sp/o0Kr8Jk4KREJITKFHD26ksne6jMxlg6q4xyqfVBCG1vK7OeY6Xl162Fk5NbHjb2j94ALnMvJFb/IrJI1WRJJhpXAIzrW7d+LjcUNoH9vtFvyKCZjnHYNdmAKADWwTgaDyAmOycy0IQKBgGzI4tMj3crQOSmje959Fmy2gZuVz4ZVrP8RCbFhN+rOYCQ3Jx0ZajrfpeJWj3/nhKCGO9rjLj3L9/QGdbCS2+DgDjLsOEHF+JQ7PcdmIcK1cD32vU8M+AMkoJCDKMDL5Ummq4a7uwgx07agl1nUzhPKfedzV0wf52jRj5r4nkl0";

        // API gateway 公钥
        String apigwPublicKey = "-----请使用正确的网关公钥-----"
                + "D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aH"
                + "axsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        // 构造请求
        WisdomHotelInfoManageRequestV1Biz biz = new WisdomHotelInfoManageRequestV1Biz();
        biz.setFunction("CHECKIN");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CORPID", "2000000192");
        jsonObject.put("CITYCODE", "130200");
        biz.setArgs(jsonObject);
        biz.setDeviceid("SKFJS211921YR2J91LL1M12912");
        // 请求IP地址，请根据测试环境和生产环境替换相应ip和端口
        String serviceUrl = "serviceUrl";
        WisdomHotelInfoManageRequestV1 requestV1 = new WisdomHotelInfoManageRequestV1();
        requestV1.setBizContent(biz);
        requestV1.setServiceUrl(serviceUrl);
        requestV1.isNeedEncrypt();

        DefaultIcbcClient client = new DefaultIcbcClient(corpPayKeyAppId, "RSA2", corpPayKeyRsaPriKey, apigwPublicKey);
        try {
            WisdomHotelInfoManageResponseV1 responseV1 = client.execute(requestV1);
            if (responseV1.isSuccess()) {
                // LoggerCommon.info("智慧酒店PMS系统管理", "处理成功!");
            } else {
                // LoggerCommon.info("智慧酒店PMS系统管理", "处理失败!");
            }
        } catch (Exception e) {
            // LoggerCommon.info("智慧酒店PMS系统管理处理异常：", e);
        }

    }
}
