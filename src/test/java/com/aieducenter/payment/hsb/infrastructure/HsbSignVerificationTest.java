package com.aieducenter.payment.hsb.infrastructure;

import com.ccb.mktpay.sign.RSASignUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HSB 签名验签验证测试
 * 使用实际回调数据验证签名拼接串和密钥是否正确
 */
class HsbSignVerificationTest {

    // 与 application-test.yml 中配置的密钥一致
    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu";
    // 我们自己私钥对应的公钥
    private static final String OUR_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vbHtcG6udUl8OPW2YMyHY/0oYletfG/7zQ87RYSv4ltZw9czMaO9mA/ch0V/I1Vp6wcg8C9fNdOxytp2Gx7vLdwXMXfJN/7aWYKLGwzqFxMQ7iRtNnMEseI2wiuseVlr7nBjvrY6DGgdaTxLqyukfAFcpHBMwenqNGxko8emVA4d6VBEjF0ypF6EDv3yaH6tQYm1lKuSqQHXjS/soxVFGYNOTx9iu10RafJ8HvbQ0Nx671MdR4cjT/yBcVP10kfw+7mbGLmDnoYgV++pkM2A/D6HJ0tHoY6XMoaO0f45hrg+DugRnVsQrYaza60XeoNN9P0730YnLYrhCQDEkKn/wIDAQAB";
    // 建行平台上的公钥
    private static final String PLATFORM_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmXNmDoEY7PKwUa0hPO5xy8LyGaYOx0YeTkACpa6TrI4V04+/iQgHo9b8DQE4EhqM/ggiW1kIKkubimlE8dosRr9R8nLzX4guk4GTDOxLcknavVScXy9yrDdzAfQHn+5mzxS0Q2nsU4CTe1/WbHDwVsj6fZi/qnqVsc9udP5NI1j2IRTDSMHx9dY1lIZcLd7nt63Vs3IVlToGwC5r1LmZQ3b2qAdMfobs46LIuC+kWITakl7UY8VAPqaVafEwJpjoUCAhCgqF0tyTgHorksNUzstm36yRij2PCkaxrBFvbYeA2JuMZx1rGpuKnzn4+owWqSgTmgalukNbrwkpba2HHQIDAQAB";
    // 建行平台下载的公钥
    private static final String DOWNLOADED_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqZoHnVaKwUy0oNzlL9fWKRRVVYYzazPM9TsDiuKQ3WcX6LzyXdWyNMVkT2LNy7fU14lgZa0LoWRtSjZ7IpFmlxgxo23ikazGh0Q132ban1TDWlGbEy4loi73hT88bP3S6WByK1Rs1IwXy+GkcVoWRWeP6jfLRf+vYNpjS7SFeDtTWEwCxBetCFjI1cfkwzM/lwJyrTMzn9Cqlt02LMUwfk/RKep66VhU4BMgNPz8YR98nq5F3fnjK0qZq4uAFcp9xMEBcTYNUELi6cbaB2fWe/A6+vT3aikb9HV/axlDr6wdsdNrLhCsD2wQgZfDT7UyxMxJtD+/IJlSnhPV228IpwIDAQAB";

    // 建行实际回调的原始报文（来自日志）
    private static final String RAW_BODY = "{\"Ordr_Amt\":1,\"Main_Ordr_No\":\"HCY0000000012\",\"Sign_Inf\":\"S2RXF8SooN5fDE0SbRVGKXOmmBpCgS05wqm6vTtYqDIcrKtm5RusYCVdkho/LZa32tOVOzj+AUJcsmgiCD5mVq+5krgNAgZ3fpvXrqb+jBDVnzq0/EIblui/pwxUk82Gn44l3OYEkCw0DOaMMz+blI9zQH2+lVfMOYSaQW3BE8FUkXR7HpjRgpgLaEIy/JW2FgVhKX+BIA7lcYq49qoMvvNgaTEFzEf0fGEOzht3YFWOHRHro7BIzBev715NTbxoyLDob1O9Zw0MCwbeO0zWkf43GVWArau0zSgyejFRSov/xYlw2vKX00OSdiJtX1nDwuV9SY3yc+3Ro12aV8c8AA==\",\"Pay_Time\":\"20260529143801\",\"Py_Trn_No\":\"10500009399003626052900098017H\",\"Txnamt\":1,\"Ordr_Stcd\":\"2\"}";
    private static final String BANK_SIGN_INF = "S2RXF8SooN5fDE0SbRVGKXOmmBpCgS05wqm6vTtYqDIcrKtm5RusYCVdkho/LZa32tOVOzj+AUJcsmgiCD5mVq+5krgNAgZ3fpvXrqb+jBDVnzq0/EIblui/pwxUk82Gn44l3OYEkCw0DOaMMz+blI9zQH2+lVfMOYSaQW3BE8FUkXR7HpjRgpgLaEIy/JW2FgVhKX+BIA7lcYq49qoMvvNgaTEFzEf0fGEOzht3YFWOHRHro7BIzBev715NTbxoyLDob1O9Zw0MCwbeO0zWkf43GVWArau0zSgyejFRSov/xYlw2vKX00OSdiJtX1nDwuV9SY3yc+3Ro12aV8c8AA==";

    @Test
    void verifyKeyPair_internally() {
        // Step 1: 验证我们自己的密钥对是否匹配
        String testData = "test-sign-verification";
        String signature = RSASignUtil.sign(PRIVATE_KEY, testData);
        assertTrue(RSASignUtil.verifySign(OUR_PUBLIC_KEY, testData, signature),
            "我们的私钥签名、我们的公钥验签应通过");
    }

    @Test
    void verifyOurSignStringConstruction() {
        // Step 2: 用我们的拼接逻辑构造签名串，自己签名自己验签
        String signStr = HsbSplicingUtil.createSign(RAW_BODY, true);
        System.out.println("Sign string: " + signStr);

        String ourSignature = RSASignUtil.sign(PRIVATE_KEY, signStr);
        assertTrue(RSASignUtil.verifySign(OUR_PUBLIC_KEY, signStr, ourSignature),
            "用 HsbSplicingUtil 构造的签名串应能自验签");
    }

    @Test
    void verifyBankSignature_allCombinations() {
        // Step 3: 用两个公钥 + 多种签名串变体，全面排查
        String signStr = HsbSplicingUtil.createSign(RAW_BODY, true);
        System.out.println("Sign string: " + signStr);
        System.out.println();

        String[] signStrings = {
            signStr,
            signStr + "&",
            "Ordr_Amt=1&Main_Ordr_No=HCY0000000012&Pay_Time=20260529143801&Py_Trn_No=10500009399003626052900098017H&Txnamt=1&Ordr_Stcd=2",
            "Main_Ordr_No=HCY0000000012&Ordr_Stcd=2&Pay_Time=20260529143801&Py_Trn_No=10500009399003626052900098017H",
            "Main_Ordr_No=HCY0000000012&Ordr_Amt=1.0&Ordr_Stcd=2&Pay_Time=20260529143801&Py_Trn_No=10500009399003626052900098017H&Txnamt=1.0",
            "Main_Ordr_No=HCY0000000012&Ordr_Amt=1.00&Ordr_Stcd=2&Pay_Time=20260529143801&Py_Trn_No=10500009399003626052900098017H&Txnamt=1.00",
            HsbSplicingUtil.createSign(RAW_BODY, false),
            RAW_BODY,
        };

        String[] labels = {
            "sorted key=value&",
            "sorted key=value& (trailing &)",
            "original JSON order",
            "exclude numeric fields",
            "numeric as 1.0",
            "numeric as 1.00",
            "isNotification=false",
            "raw body",
        };

        String[][] keyPairs = {
            {"我们的公钥", OUR_PUBLIC_KEY},
            {"建行平台公钥", PLATFORM_PUBLIC_KEY},
            {"建行平台下载公钥", DOWNLOADED_PUBLIC_KEY},
        };

        boolean anyPassed = false;
        for (String[] keyPair : keyPairs) {
            System.out.println("--- " + keyPair[0] + " ---");
            for (int i = 0; i < signStrings.length; i++) {
                boolean result = RSASignUtil.verifySign(keyPair[1], signStrings[i], BANK_SIGN_INF);
                if (result) anyPassed = true;
                System.out.println("  " + (result ? "✅" : "❌") + " " + labels[i]);
            }
            System.out.println();
        }

        if (!anyPassed) {
            System.out.println("!!! 两个公钥 × 8种签名串，全部验签失败 !!!");
            System.out.println("结论：建行回调签名所用的私钥，与这两个公钥都不匹配。");
        }
    }

    @Test
    void tryAlternativeSignStrings() {
        // 兼容保留
    }

    private void tryVerify(String label, String signStr) {
        boolean result = RSASignUtil.verifySign(PLATFORM_PUBLIC_KEY, signStr, BANK_SIGN_INF);
        System.out.println(label + " -> " + result + " | signStr=" + signStr);
    }
}
