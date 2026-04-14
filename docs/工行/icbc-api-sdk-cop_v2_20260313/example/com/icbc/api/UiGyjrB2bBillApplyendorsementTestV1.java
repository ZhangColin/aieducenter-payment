package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.UiGyjrB2bBillApplyendorsementRequestV1;
//import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class UiGyjrB2bBillApplyendorsementTestV1 {
    protected static final String APP_ID = "10000000000004097933";
    // 应用私钥(变量值需替换)
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaQJXO4meBA/FtTExiJCFIIaoOcsvMgZnOn9g5KZ9J01LN9iDHjI0g5FoDvw+9D54nQigR+TepMXH7NCTPA8TTvWIQWX3kyDPNqOOxtuHBjeTODh2b9MTYvUuNcJo75zN9a6BVJQZ9pMDPfUmwQGK5QtpLShuqf/+Uv5XNUCM4/7UD9HLkiJrQlENlnpaD1A416X0WCEWJuMvjEN/QIxIP+ULcKSMt++vgUSo0FJ2+eDudYyh12aotUlrHZqtHB6qGiMC9stc/idZQzu6EfUl0DJTSx1E8QkZ7shRsW8UnH4ed8xMYX54LksYTlNWvYBRh+hM1WqvtnbQ9ZQSmLiRdAgMBAAECggEAPWcIO1qUqR7QtBlzLn4DYZtE/otj5PySJVYr1FVlOxYMGECpu8WPSFCeJG+h7DiTVD1Zhg4WwhsUrFKNdHqjl0YEC04Ud0XamTtpqAyMwWeidQPZdYNx+HUh5iO0wbO/I2X8ZOhgNROoTPAqrSZ0JhsemmndqXlaW/QHxbroyn8d23ZqKUCNEWv/CPgmTY4SgJVGFcbQRCdr2kkFYvjcwSpW8Ce4zo/KhB2B/pXAIKKq8rvGivqqoE+Yh/ihLGkLXaqeJYaWoIEpr41tplnVI57+fAGxDBwmJqJnzZbsk8fncf/1DRyD7XRrlTcYafU5nnwFwcTZea/lSJrEfztXAQKBgQD1zAZYTozbsld//Pr30FzdYyV+pka1EI4WJZLtR5cSrB4Pec1p6z89tmGjYaTBtYwuON1YyP9VhXIZJpMvsdJCEwEXKM9aNRh7ycLQ8dw3yWm/HighfWW0KYr5O+OGUT3PBebjRhnTrRYnfPc34M07Ax0YN850nVNNpz0TnMzQpQKBgQCgp8F1au/Um9UiWWred+q/UgWiywH02aaWotTaoQ6hUqxBLSVjyYWPbjvFqcZN9ZUxNz0avJ7FH0IQQPY/opxoJmF46HLLjZ/OZEdpwzpaJnldQG9UCSdI5I5uo31nyatxh5FWjWjfEx64FgApYDiI8lDjdgZqCpDoMOLO8k0/WQKBgQCKPmIjMKrQthG4jpSo5KwWaDkibmq+U71Wf3OPuQAjJGMT2THLLOC42Oc5eSWtPWrGZH05/nJ9qzxtIOEPpjfXqdCnMfDNj6dhmCzWT/HH/qRXNaLkkx6soeDkJObnGLJmya7+jOLhxyv6sbJp3yoO0sV+ZYTc2c+MkU8sxLf9cQKBgFjXrGJUesBmVN0j+beBNS4MG9XE0hYnbrqK3Y7x1fdckqmQX8bpIJUK7r8GfgWKs7B9zarFeD+mY5A9fHAAauIlp22HjkQG08L9LYjNT62e7SqmgWpFjZX/3sxMWQNWQRsNT1A/7yi9mBVRBVHcRidlaVoAYNevdCcTMbojwJgxAoGBAI3MbwoNpUiN8rvl4nosYpG98B9NFJLk6YcobfZWquS8JLeGNm1iTH87l9YlX47FBho6FPuYuzNSKjJwFtKqoaPL2UMiD/Xwx73xXEQHNhb5QW8tPVGXA57zKH1GJjZRyq8ksbuMLv1yIbtXfH5x122i+jUVScXgLMjqn3Wwlq0j";
    // API网关公钥(变量值需替换)
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
//
    public void test_request() {

        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,"UTF-8");
            UiGyjrB2bBillApplyendorsementRequestV1 request = new UiGyjrB2bBillApplyendorsementRequestV1();
            // 根据实际环境替换相应的域名
            request.setServiceUrl("https://IP:PORT/ui/gyjr/b2b/bill/applyendorsement/V1");
            UiGyjrB2bBillApplyendorsementRequestV1.UiGyjrB2bBillApplyendorsementRequestV1Biz  biz = new UiGyjrB2bBillApplyendorsementRequestV1.UiGyjrB2bBillApplyendorsementRequestV1Biz();
            UiGyjrB2bBillApplyendorsementRequestV1.UiGyjrB2bBillApplyendorsementRequestV1Biz.Bill  bill = new UiGyjrB2bBillApplyendorsementRequestV1.UiGyjrB2bBillApplyendorsementRequestV1Biz.Bill();
            List<UiGyjrB2bBillApplyendorsementRequestV1.UiGyjrB2bBillApplyendorsementRequestV1Biz.Bill> billList = new ArrayList<>();
            bill.setBnedMtMrk("0200099809212066358");
            bill.setRangeBgn("000000000001");
            bill.setRangeEnd("000000099999");
            bill.setPackNo("966555094775202211257991811008");
            bill.setTransAmount("99999");
            bill.setEndrseeAcctid("0200099809212066358");
            bill.setEndrseeName("鑺掑悷绫界洴渚紶閰颁剑姝诲棧");
            bill.setEndrseeIsIcbc("1");
            bill.setEndrseeAcctsvcr("102100009980");
            bill.setPreName("前一手背书人名称");
            bill.setEndrseeBankname("被背书人开户行行名");
            bill.setPackType("票据种类");
            bill.setAccBankname("承兑人/行全称");
            bill.setHolderName("持票人全称");
            bill.setDueDate("到期日");
            bill.setBillAmt("票据包金额");
            bill.setPayRemark("");
            bill.setIsPhone("0");
            bill.setMobileSuccess("");
            bill.setMobileFail("");
            bill.setEndrseeIsChain("0");
            bill.setEndrseeMCode("");
            bill.setEndrseeDistTp("DT02");
            bill.setEndrseeAcctid("0200000609027330386");

            billList.add(bill);
            biz.setBillList(billList);
            biz.setProtocol_code("GYJR20220119160206728375706");
            biz.setPlat_vendor_id("14");
            biz.setPlat_batSerial_no("12345678");
            biz.setTrade_name("Applyendorsement");
            biz.setTrade_version("V1");
            biz.setTotalCount("100");
            biz.setTotalAmt("1");
            biz.setVer("1");
            biz.setApplyType("0");
            biz.setCallBackUrl("https://www.xxxxxxx.com/notify.do");

            request.setBizContent(biz);
            String s =  client.buildPostForm(request);
            System.out.println(s);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
