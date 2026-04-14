package com.icbc.api;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberOfflineTradeinfoRegisterRequestV1;
import com.icbc.api.request.BcssMemberOfflineTradeinfoRegisterRequestV1.BcssMemberOfflineTradeInfoRegisterRequestBizV1;
import com.icbc.api.response.BcssMemberOfflineTradeinfoRegisterResponseV1;


public class BcssMemberOfflineTradeinfoRegisterTestV1 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDME5evAleNhLF2To1o/aqeZKKQADOAdpsZ4A+6G1uFrtF0MD6OJ8Y2wAvdqaZD0yrIp8xVjw25VdyoAO4m/+Va792GQWQbv9+pjfGr+gohzT9zVdoVatlFamXu/BtMjPTtJw5eMLkBRTptatJ0oWa3uBuDRH2pV/w8ku3Y0DOTBoFas2x537fcvzcRtO7YFBGr4PwjEACnJ/oEK/KljvFlVgpmEz2e678uMK3vM5QkcGv7e91emkTcnzlgk1XaGc+a71W4q3ISmdN8v3Ad4hk7rbxQ9gqFUbx47ctWl8fYnrArSvXj6RGrV70F+B5DaXA4Kp4lLMRl5LR9M+WUldTbAgMBAAECggEBAJggitQlNMsD/Z4plHSNlCCa+CvMWpgK0jgJtO+Q8TrIRM8OH7Ospq+tnarRimXJBn2pyEKHbz2XafO7LczYuUp+wG4SVOPytnIYo+0FaYQlEoWRnA6XJfv/ApmSSW9ZLEOU97rzKxZtWs6x8MyMfJu7nJbP+bTBKACnrrIPEkMoI4LK/Llgh7zhUmEE4f3MbVpedl4a9zsLfCz+LZxYwvCqYXrGPAqtEsk+kvvlF9pEjCeBeJ+0oDEc9a3jQVxEetCJabcOXcal4dCfTYMvdrmqWun9599Ybo0N0BDS65YTI5HULV+0oMiDpgi8SDZisG4LOSW0gNp7Wu4wrIWsbUECgYEA5TCwsWVNDjbTzD0XWb2inSnMvqCiZ0NANkRIUp5WYoarcNwmnjpBJ7deSRdbz23cZTD5D1cShCzCCuTJrKoH3NPy4gCgj/kh4BQnvA1t76Pakef7cwkuDoiABull9+3dp6OZ71v+oF+V2aw+7Fpq5vPMkEMVtIPlcn8P41V/LzECgYEA4/LZyYJ7lFWgDCvouGBH9Tt5kcx+x5uf9Vbi2K5znicmrJXyVH809vKYsE8DS0WcKC5DdjWoWL7i9mZHB3BTOVBxfl/0uUNm0uAXr9vxXAdEBiLiCY4vSBWxyddqslN/+VwwgsubgMpIW1NsVAJctQHyF2TyoUJR0D4yn/txucsCgYAWfIZ5gTTkNiDlowDNfzubK+S4t033z7NXH6/w2zQiio/7jL4FPDX0TZtklHQfj+nVy85D4QkiCronE3duB2iqE+l6nxEhzxGxyIh1fOPSZUMmmZokGTx5lI7rHq8wehgLQJPz1hTPa1Fqf+nUirJ+YmEprktM4Lv8VdcbOOI3oQKBgQCPRAUJl47gLejHfZlouGrBgL3pmuNbxT4FzrQIh6x3DBhMhHmoDcNWKic5OI/TcU6yGlGgLB1AmcjAIIwn6rsD3MFYMir3fZtUki+RAeVyd8aTLL1wUAL7hVcWiuOubLiUGxMKQbncekZ7z7TYIBvwT+a+OV4daDdPcKgU0M4rfQKBgBHaqffhJvq5nhab8YIBve8GKCtVRO+0AYIXYwO6Ooe4Epmf+rqyqMTg7LJhaNcPbt/KGVvbGI2G896NP4NHBJWd3F+ChYehlqyCuwuZIr1X6q/JcORZ5sax6BERMT5BgSLvOqEd33+jpF1HUZEwlDqAYrC9WieG+kEVNpNvfQez";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
   
    // APP_ID
    protected static final String APP_ID = "*******************";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
    	BcssMemberOfflineTradeinfoRegisterTestV1 a = new BcssMemberOfflineTradeinfoRegisterTestV1();
        a.testCop();
    }

    public static void testCop() throws IcbcApiException {
    	BcssMemberOfflineTradeinfoRegisterRequestV1 request = new BcssMemberOfflineTradeinfoRegisterRequestV1();
        request.setServiceUrl("http://**********:****/api/bcss/member/offline/tradeinfo/register/V1");
        BcssMemberOfflineTradeInfoRegisterRequestBizV1 bizContent = new BcssMemberOfflineTradeInfoRegisterRequestBizV1();
        
        bizContent.setCorpId("0000000322");
        bizContent.setCorgNo("000006");
        bizContent.setOrdersrc("3");
        bizContent.setManufacturerId("LandiCmp01");
        bizContent.setImeiNo("00021064032");
        bizContent.setTimeStamp("2019-06-05 13:47:49");
        bizContent.setClientTransNo("*******************");
        bizContent.setTrxTime("2019-06-04 13:47:49");
        bizContent.setClientType("3");
        bizContent.setMerNo("123");
        bizContent.setMemAccno("8*************8");
        
        bizContent.setFeatureType("001");
        bizContent.setFeature("*******");
        bizContent.setFeatureCorgNo("000002");
      
        bizContent.setTotalAmt(new BigDecimal(1000));
        bizContent.setPayAmt(new BigDecimal(1000));
      
        bizContent.setPayType("2");
        bizContent.setDataSrc("1");
        bizContent.setOrdersrc("1");
        bizContent.setIndustry("001");
        bizContent.setTradeMode("1");
      
        bizContent.setMerNo("123456");
      
        bizContent.setMsgFlag("1");
        bizContent.setOptionType("1");
       
       
        List<BcssMemberOfflineTradeinfoRegisterRequestV1.ProductInfoDto> procustList = new ArrayList<BcssMemberOfflineTradeinfoRegisterRequestV1.ProductInfoDto>();
        BcssMemberOfflineTradeinfoRegisterRequestV1.ProductInfoDto prod = new BcssMemberOfflineTradeinfoRegisterRequestV1.ProductInfoDto();
        prod.setProductId("*********");
        try {
            prod.setProductName(URLEncoder.encode("北方大馍","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            prod.setProductName("BEI FAGN DA MO");
        }
        prod.setProductNum(new BigDecimal(1));
        prod.setProductAmt(new BigDecimal(500));
        prod.setProductType("001");
        try {
            prod.setProductName(URLEncoder.encode("特大号","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            prod.setProductName("TE DA");
        }
        prod.setGroupNo(null);
        prod.setPeriodid(null);
        prod.setProductRemark(null);
        prod.setDistributeDate(null);
        procustList.add(prod);
       
        bizContent.setProductList(procustList);
        
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        client.execute(request);
    }
    
}
