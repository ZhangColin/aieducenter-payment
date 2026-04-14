/**
 * 
 */
package com.icbc.api.request;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssRealcardConsumRequestV1.BcssRealcardConsumRequestBizV1;
import com.icbc.api.response.BcssRealcardConsumResponseV1;

public class BcssRealcardConsumTestV1 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDVav+T9xfXb4TSPKd37OucaWIaegvw6xBbFdZ1tJqGDlEpEGbHGSVGCxhKyu+JYkeqk+/zBsRZx9nPoJl0qiAxLZ9SCkQEMgdXmxuw4359kxt5W5RUdmBA1FR0u1N6luHblpjvhJph/S2WQiJ+0FTZsjmFNPcDwj/IJmPBTkKN2lt7kq6sIeJwwbgoHWebQ6f9jA9Cb8Dji0mfgsEK34fuNsjp1m8GkbDcSSH3fNhYJD1dWmp/9eIvqMofwBAYyZTNvWhHvqM2w0Yk1aqiXt+aTOWjOzT0qXNfMPzhkkiVK3A0vTmH6moK/FiBse6Ca6cL389xT5xQtdDiYkz894JpAgMBAAECggEBAKPZ/lcPwlI44HS49KpyT4JKtnM1eK2DC4+i+IBuJKT/oPgyC3aYq7FXG1Nw8u87cWJZKkQf0ehMM8Hgv3B+nTCkoklOcGH4NwLfpybrwlWO9+z4jq06JTQg0Ka+m5zm8oiw2sIpwI2y36kZJXrPYBWza84FmBBetrFoJ/Zlq1tT/y+SMR7xL8Fyto78WXA4BreQ/mNBWqPNC37o6PbFuuTmn1df0xUMRdlPTNnLf+dHiPsbrweRQjphz9BoYM0Hjlwq9P7BAsi/GFyo1t6AyHkaMtOW4wvqv1IOzb8GJRzafU5y2fP4fFcHuiyeEx780j3LLoFU5DhkQhD607UZasECgYEA7JuZFnjhepzuROTrifOEdx67zO1dPkSqvFTZ8KSyPelP4ej8rNPP5KjxwHTtMp4Bux3hghSnVo13Ygcg5ZAbD6k4oLMxVry6XnAK2P/rAzYg1X7JlYB/bNOFKUxmmnrTTHrwWVzV3sOZznbxYAUTwD55XIh22mQl4/wuTde3Lu0CgYEA5ujXVvKqOBHYK/aEL1z36mVGy8OPrpLfFxdPkutSgNpMfsYUgCIc5WMzsaXuFuhGNCqmQp7LWe/0leVV1X+qC7+CESRNy2tu68HkdsoUF6+FCiZji/Fgh8sI2oiYEiKReo9ywfkLTY46TGVa8YjNLilMvll5bHuCA0y/yWsSNe0CgYEAs3qAUNSXLXw3HZmMthM762QrzxJLBb3uyXeP7oPwFRiTGOm0KaFudKRW5ceMWjYTWA4djp/+I6Ch64E+ygRbErEpnHTxOztkH9jb0amnasM3ett3LGnRvwpgW2hx+EKtX7lt8kuCjD1RwZTmGhEf31dElhWKGrWXLG6R7Uhx0y0CgYBYFzgfuY/quBQKIY+oKgWCbISns8GazflB6+i1mgTtl1pbeuHTSDBR6piMC0tbfd1ahXLMM5xMgViD3uvx+TQ8KyFJvNnY4vF8C4pHLyyfJJzSlHahqsIyy+hixn2NXZa2dQdIrOP9AGeJQZmCT7hHBgZ7D9Nc6XleHOeFxc06uQKBgC19XpS1P3NaIFxyqVsymzWu6umW7tf9Dso00c8Y3ATECMZIdMUYh1IlbWsJN8LrIJXN5bwiNhh9/QygV0jDMQOppeVKsVtrHPpZ6gyCrOMqh3sMKx6n9y6Ti9qQBNyAXjFXGA4t6AVXFLir7K5OMX2lTYyhj4E1WLXaV/2kSrsG";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
   
    // APP_ID
    protected static final String APP_ID = "*******************";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        BcssRealcardConsumTestV1 a = new BcssRealcardConsumTestV1();
        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
    	BcssRealcardConsumRequestV1 request = new BcssRealcardConsumRequestV1();
        request.setServiceUrl("http://**********:****/api/bcss/realcard/consum/V1");
        BcssRealcardConsumRequestBizV1 bizContent = new BcssRealcardConsumRequestBizV1();
                    
        bizContent.setCorpId("0000000322");
        bizContent.setCorgNo("000006");
        bizContent.setOrdersrc("3");
        bizContent.setManufacturerId("LandiCmp01");
        bizContent.setTrademode("1");
        bizContent.setClientTransNo("*******************");
        bizContent.setPayAmt(new BigDecimal(1000));
        bizContent.setTimeStamp("2019-06-05 13:47:49");
        bizContent.setPayType("2");
        bizContent.setTotalAmt(new BigDecimal(1000));
        bizContent.setClientType("3");
        bizContent.setMerNo("123456");
        bizContent.setImeiNo("*********");
        bizContent.setGoodsType("001");
        bizContent.setIndustry("001");
        bizContent.setDataSrc("1");
        List<GoodsInfoDto> goods = new ArrayList<GoodsInfoDto>();
        GoodsInfoDto good1 = new GoodsInfoDto();
        good1.setGoodsId("***********");
        try {
            good1.setGoodsName(URLEncoder.encode("北方大馍","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("BEI FAGN DA MO");
        }
        good1.setGoodsNum(new BigDecimal(1));
        good1.setGoodsAmt(new BigDecimal(500));
        good1.setGoodstype("001");
        try {
            good1.setGoodsName(URLEncoder.encode("特大","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("TE DA");
        }
        good1.setGroupNo(null);
        good1.setPeriodid(null);
        good1.setTypeid(null);
        good1.setGoodsback(null);
        good1.setDistributeDate(null);
        goods.add(good1);
        GoodsInfoDto good2 = new GoodsInfoDto();
        good2.setGoodsId("****************");
        try {
            good2.setGoodsName(URLEncoder.encode("糯米发糕","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good2.setGoodsName("LAO MAIN MAN TOU");
        }
        good2.setGoodsNum(new BigDecimal(1));
        good2.setGoodsAmt(new BigDecimal(500));
        good2.setGoodstype("001");
        try {
            good2.setGoodsName(URLEncoder.encode("特大","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good2.setGoodsName("TE DA");
        }
        good2.setGroupNo(null);
        good2.setPeriodid(null);
        good2.setTypeid(null);
        good2.setGoodsback(null);
        good2.setDistributeDate(null);
        goods.add(good2);
        bizContent.setGoods(goods);
        /*实体卡相关数据*/
        bizContent.setCardNo("*****************");
        bizContent.setTerminalNo("AAAAAAAAAAAAAAAAA");
        bizContent.setTradeTag("AAAAAAAAAAAAAAAAA");
        bizContent.setTerminalTradeNo("AAAAAAAAAAAAAAAAA");
        bizContent.setTradeDate("AAAAAAAAAAAAAAAAA");
        bizContent.setTradeTime("AAAAAAAAAAAAAAAAA");
        bizContent.setTAC("AAAAAAAAAAAAAAAAA");
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        client.execute(request);
    }
    
}
