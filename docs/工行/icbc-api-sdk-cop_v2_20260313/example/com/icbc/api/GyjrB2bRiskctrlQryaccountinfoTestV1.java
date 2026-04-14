package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.GyjrB2bRiskctrlQryaccountinfoRequestV1;

import java.util.ArrayList;
import java.util.List;

public class GyjrB2bRiskctrlQryaccountinfoTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1lOvR6gsDBrlMiDz+lR8w98PMZzOGlXo64LDmfLthpii4K5+a5zImro08bmfmdH1cmtNckjO10w1FYfTYJsUE6DTcqOuA3cVNeEATyH1Xynqp/9ZSblOFjTx1tqRLw0lJvFlyw9krY421mJ0bJwiP6fp4VKuloYvT5/tMek4oXX7HuxPPsdEHOePStjoOpB5lo9SwJVCdTx8yWuEJnkrClzrfYs/AF60peAb1GNrPC+roXf0aY0FXIIUz1GGWb99JDejup7N1lJc4Ti8DZI3lF2YSwf4LxrPVf/CAtMlG7ECeMOTeCpI5jzLwHOOryF/fGVTcp5FSTiSwhPz6JczDAgMBAAECggEAYgPxB25+YzPN8jHYOtPj8lrLphpPBebKA94ln0TVIPDAKiniDYue1qKCOoyeKRfYU5R/YwjIQO2bzEgEEmlbEDsHzdSgyv7q8dZnQEaI4kq5y+n9lqFXFFAYwrHEHeFcPiEdZ4WwRGuIou2uYGnBhW2Xdjy+Krn48HnYfH/IkKB9ENyXVBwIOKioMZKVgWPhqLrC9vUJuv1oRLdgIFLpxrFS9E+hg1mY6M2mQRofiJZc6h8As7RIw4RfQIicjQLe3j7PGqW3hwj4dLL2r/iQiiDOTsat1R8y5KXQ9OwgCrwKh745Q/iRcs8+lecgLPETILL1l7YkWRdlvq8vf7KJAQKBgQDwvQbejH8jom/XUoxRYw1yB5KbxxX6PiVtIY1X0MJakILtAMRewDhOM9MmlKdHJPwWUei+o5aM8OTVdRwF53Z4C4qnIYWSh446DHv4rGm13cJ7+huLnUIwchVnWDNnXDxJq/UP0r0vgyiVxYdxA0aZhk/ZzMscZeipdYBfoFJ9GwKBgQDBF9VhBie0To1fGM5Fnduor9ZRrQaIY/ljx6Enypwu/0kIPgG+Fy4/VTwZHsp+sAo7ofNBVs6VMmHgG4XIBG1zCtDaGwWDmhPMdQe8+eT09JCZKnHnwIPCsdrsE5HJudrEUifo8XgSKOgqu9ouFZEsdMNMcHTboUhthiBqh4uxeQKBgQChdfZMfyiqisC+RvQrhea3qxKOfllkLBhH+G/nDPG9ch0Nviz2a6+X67RbtOn4knqssaULb4aC4vJnpBL2dsaDPyJwc9A1dctTbZocL+76dlCBfD7LRd7lxS5L0OExGrCorLdBNJOEcVBgG0p65rqML4QQzQUQ99yzA0blXA7XLwKBgQCH0c3ja0LuTvqd04Lz8O+bHePwm3Ym5bt79eVjad6mKCckxwjOvttm7PtS6BejOQxZmbbTVM4Gn+O8kV9uq76Y1xjE8ZDVWsFmZUNGJ7hmnMhBFFv+mamUfynwOGupqzM4OFmePosoquFzfaMgG0d8L71XvRpVioK5l0ih5inacQKBgCYunQNafcPXrupq5rzjpI2GlYEZKwOLnQHt+St/fWLzZ746SGurrCjC7uQjnxEPrha0ZctfeHLuM5adSEMho2izcrYV0GZrB6FZ+ykVWI3BZtpbqqkeEOBCHELcVBMUk/NP3JKEV2BfR6sdXLPXTEysXHcDJNuyjk6Ek6h8yOc0";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16" +
            "bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL" +
            "T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP" +
            "3CiNgg0W6O3AGqwIDAQAB";

    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000123456";

    public static void main(String[] args) throws IcbcApiException {
        // 构造client对象
        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        // 设置请求对象request
        GyjrB2bRiskctrlQryaccountinfoRequestV1 request = new GyjrB2bRiskctrlQryaccountinfoRequestV1();
        // 设置请求路径
        request.setServiceUrl("http://ip:port/ui/gyjr/b2b/riskctrl/qryaccountinfo/V1");

        GyjrB2bRiskctrlQryaccountinfoRequestV1.GyjrB2bRiskctrlQryaccountinfoRequestV1Biz bizContent = new GyjrB2bRiskctrlQryaccountinfoRequestV1.GyjrB2bRiskctrlQryaccountinfoRequestV1Biz();
        GyjrB2bRiskctrlQryaccountinfoRequestV1.Trans_Info transInfo = new GyjrB2bRiskctrlQryaccountinfoRequestV1.Trans_Info();

        // 公司列表
        List<GyjrB2bRiskctrlQryaccountinfoRequestV1.Company> companys = new ArrayList();

        // 公司1-start
        GyjrB2bRiskctrlQryaccountinfoRequestV1.Company company1 = new GyjrB2bRiskctrlQryaccountinfoRequestV1.Company();
        // 公司1账号列表
        List<GyjrB2bRiskctrlQryaccountinfoRequestV1.Account> accounts1 = new ArrayList();
        // 公司1账号1
        GyjrB2bRiskctrlQryaccountinfoRequestV1.Account account1_1 = new GyjrB2bRiskctrlQryaccountinfoRequestV1.Account();
        account1_1.setAccountCode("0200003309002630579");
        account1_1.setAccountType("1");
        accounts1.add(account1_1);
        // 公司1账号2
        GyjrB2bRiskctrlQryaccountinfoRequestV1.Account account1_2 = new GyjrB2bRiskctrlQryaccountinfoRequestV1.Account();
        account1_2.setAccountCode("0200000319201904497");
        account1_2.setAccountType("1");
        accounts1.add(account1_2);

        company1.setCompanyName("XX公司");
        company1.setOrganizationCode("912300008888123456");
        company1.setAccount(accounts1);
        // 公司1-end

        // 公司2-start
        GyjrB2bRiskctrlQryaccountinfoRequestV1.Company company2 = new GyjrB2bRiskctrlQryaccountinfoRequestV1.Company();
        // 公司2账号列表
        List<GyjrB2bRiskctrlQryaccountinfoRequestV1.Account> accounts2 = new ArrayList();
        // 公司2账号1
        GyjrB2bRiskctrlQryaccountinfoRequestV1.Account account2_1 = new GyjrB2bRiskctrlQryaccountinfoRequestV1.Account();
        account2_1.setAccountCode("0200000209213359619");
        account2_1.setAccountType("1");
        accounts2.add(account2_1);
        // 公司2账号2
        GyjrB2bRiskctrlQryaccountinfoRequestV1.Account account2_2 = new GyjrB2bRiskctrlQryaccountinfoRequestV1.Account();
        account2_2.setAccountCode("0200041709212092812");
        account2_2.setAccountType("1");
        accounts2.add(account2_2);

        company2.setCompanyName("XX公司");
        company2.setOrganizationCode("912300008888234567");
        company2.setAccount(accounts2);
        // 公司2-end

        companys.add(company1);
        companys.add(company2);

        transInfo.setProtocolCode("GYJR20220705100505000012345");
        transInfo.setCompany(companys);
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        try{
            String s = client.buildPostForm(request);
            System.out.println(s);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
