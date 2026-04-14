package com.icbc.api;

import com.icbc.api.request.GyjrB2bBillApplyDischargeRequestV1;
import com.icbc.api.request.GyjrB2bBillApplyRecourseRequestV1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GyjrB2bBillApplyDischargeTestV1 {
    protected static final String APP_ID = "10000000000004097933";
    // 应用私钥(变量值需替换)
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaQJXO4meBA/FtTExiJCFIIaoOcsvMgZnOn9g5KZ9J01LN9iDHjI0g5FoDvw+9D54nQigR+TepMXH7NCTPA8TTvWIQWX3kyDPNqOOxtuHBjeTODh2b9MTYvUuNcJo75zN9a6BVJQZ9pMDPfUmwQGK5QtpLShuqf/+Uv5XNUCM4/7UD9HLkiJrQlENlnpaD1A416X0WCEWJuMvjEN/QIxIP+ULcKSMt++vgUSo0FJ2+eDudYyh12aotUlrHZqtHB6qGiMC9stc/idZQzu6EfUl0DJTSx1E8QkZ7shRsW8UnH4ed8xMYX54LksYTlNWvYBRh+hM1WqvtnbQ9ZQSmLiRdAgMBAAECggEAPWcIO1qUqR7QtBlzLn4DYZtE/otj5PySJVYr1FVlOxYMGECpu8WPSFCeJG+h7DiTVD1Zhg4WwhsUrFKNdHqjl0YEC04Ud0XamTtpqAyMwWeidQPZdYNx+HUh5iO0wbO/I2X8ZOhgNROoTPAqrSZ0JhsemmndqXlaW/QHxbroyn8d23ZqKUCNEWv/CPgmTY4SgJVGFcbQRCdr2kkFYvjcwSpW8Ce4zo/KhB2B/pXAIKKq8rvGivqqoE+Yh/ihLGkLXaqeJYaWoIEpr41tplnVI57+fAGxDBwmJqJnzZbsk8fncf/1DRyD7XRrlTcYafU5nnwFwcTZea/lSJrEfztXAQKBgQD1zAZYTozbsld//Pr30FzdYyV+pka1EI4WJZLtR5cSrB4Pec1p6z89tmGjYaTBtYwuON1YyP9VhXIZJpMvsdJCEwEXKM9aNRh7ycLQ8dw3yWm/HighfWW0KYr5O+OGUT3PBebjRhnTrRYnfPc34M07Ax0YN850nVNNpz0TnMzQpQKBgQCgp8F1au/Um9UiWWred+q/UgWiywH02aaWotTaoQ6hUqxBLSVjyYWPbjvFqcZN9ZUxNz0avJ7FH0IQQPY/opxoJmF46HLLjZ/OZEdpwzpaJnldQG9UCSdI5I5uo31nyatxh5FWjWjfEx64FgApYDiI8lDjdgZqCpDoMOLO8k0/WQKBgQCKPmIjMKrQthG4jpSo5KwWaDkibmq+U71Wf3OPuQAjJGMT2THLLOC42Oc5eSWtPWrGZH05/nJ9qzxtIOEPpjfXqdCnMfDNj6dhmCzWT/HH/qRXNaLkkx6soeDkJObnGLJmya7+jOLhxyv6sbJp3yoO0sV+ZYTc2c+MkU8sxLf9cQKBgFjXrGJUesBmVN0j+beBNS4MG9XE0hYnbrqK3Y7x1fdckqmQX8bpIJUK7r8GfgWKs7B9zarFeD+mY5A9fHAAauIlp22HjkQG08L9LYjNT62e7SqmgWpFjZX/3sxMWQNWQRsNT1A/7yi9mBVRBVHcRidlaVoAYNevdCcTMbojwJgxAoGBAI3MbwoNpUiN8rvl4nosYpG98B9NFJLk6YcobfZWquS8JLeGNm1iTH87l9YlX47FBho6FPuYuzNSKjJwFtKqoaPL2UMiD/Xwx73xXEQHNhb5QW8tPVGXA57zKH1GJjZRyq8ksbuMLv1yIbtXfH5x122i+jUVScXgLMjqn3Wwlq0j";
    // API网关公钥(变量值需替换)
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


    public void test_request() {

        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,"UTF-8");
            GyjrB2bBillApplyDischargeRequestV1 request = new GyjrB2bBillApplyDischargeRequestV1();
            // 根据实际环境替换相应的域名
            request.setServiceUrl("https://ip:port/ui/gyjr/b2b/bill/applydischarge/V1");
            GyjrB2bBillApplyDischargeRequestV1.GyjrB2bBillApplyDischargeRequestV1Biz  biz = new GyjrB2bBillApplyDischargeRequestV1.GyjrB2bBillApplyDischargeRequestV1Biz();
            GyjrB2bBillApplyDischargeRequestV1.GyjrB2bBillApplyDischargeRequestV1Biz.Bill  bill = new GyjrB2bBillApplyDischargeRequestV1.GyjrB2bBillApplyDischargeRequestV1Biz.Bill();
            List<GyjrB2bBillApplyDischargeRequestV1.GyjrB2bBillApplyDischargeRequestV1Biz.Bill> billList = new ArrayList<>();
            billList.add(bill);
            biz.setBillList(billList);
            biz.setProtocol_code("1");
            biz.setPlat_vendor_id("1");
            biz.setPlat_batSerial_no("1");
            biz.setTrade_name("applyDischarge");
            biz.setTrade_version("V1");
            biz.setTotalAmt("11");
            biz.setTotalCount("11");
            biz.setCallBackUrl("11");
            biz.setCondition("0");
            request.setBizContent(biz);
            String s =  client.buildPostForm(request);
            System.out.println(s);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
