package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankPayBaseMepOrdersendRequestV1;
import com.icbc.api.request.MybankPayBaseMepOrdersendRequestV1.MybankPayBaseMepOrdersendRequestV1Biz;
import com.icbc.api.response.MybankPayBaseMepOrdersendResponseV1;
import com.icbc.api.request.MybankPayBaseMepOrdersendRequestV1.MybankPayBaseMepOrdersendRequestV1Biz.PrivateReqInfo.SubOrderList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MybankPayBaseMepOrdersendTestV1 {
    //企业私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDP72MKfHNpNmqbll9ZSYCY1CE7MiExQxxu+3MQMpcJ5WTrg1XXF2wy5DhoAHZ/5W4fvwW0LzpuP2j/teeVWgRTDhX6BBpSM/fRZRSrsVvpROrhmeznan8jsImPwS1mpNJ36oiD7XgW261fOWb/wVeDef0avfXUqIzHtQMGiAK+3S/BT6oyKH1oi4SIbxDMzKYOSYirQ82j8uihdeaS3JQl6hMPmMzxGE5DDLas9P47uwmHL69CYCMbMBB3d9YNyTfOuOao9MysYv0gdgpk6gQpQAi+dPaN9Hpq2Ls+6AlD0t0EOKWM8w3sw2XQCCeKT/PByOfyiMlYWtI116ogfeTDAgMBAAECggEACHfaU7n/xUAnFVEJbwx0RUUWWV/QRD5UcRyicf1tc0S5WHIP0+OrT2NF/M5QBSNl2bsg01a9DXJdp1RUKxvwYyWGtZjnLYPaStfQALIHaTeaTTfEkyQeK2jsIUOfwrdhruqJRr4sDtpBdEhVLoNEOiGEskscMN3wZc20Yta1LeqnTiemfnIzHWvp64i+KCr6VpLtLhzYNon6/5p5f5cwZKnWlT38BNM4uRba46OdxU7DvrGV0EB3TDfcDVqQX9fJCyzhyEowGjyed5KMBj32dDYKqA4PgCe8HXiIe4XWWwr+Rx/5NAjqgMZZOxl8slFoVp4zAZIY4PJUMMcRi5GPKQKBgQDztYxlebNWL+sgvL39zXveOq+kF/rmPRP7SPM7wbqQBj+GLatTDyF5K1VF+PvYe5iFRyvXLFzeHyOZT7SkbrEGeH9ZqTkAlrjEhjsDLlGbCXNA8dCBsrn1tC4l7woHuCuanpk33pSTDJkqeuruiLaMR+blX/01AyuGqOglOa7fXQKBgQDaa/iJGXVKjf00LaeDIbyKrNNexMZtQ44dP25SwnzZpaOGWj91lbAW9QkRH4rmjJOYu21mJ0A4KX9xYUDGKAweQNe4brYZvGTOZ650MbRygd/hZlO83l8JWIKvx4ov4myHKE7sYq4J6onDfYWZUx4vXN1YoZgZ8IyDK/CLEzUynwKBgDtJVBEvjQAPgiyhnbqLbXG/ZxENQF0YSK8drQZBd6gnzPZQT9hR2vvGHeV6z5F3Z8Ascy7SPPlQK/HU4QYLY4dGcvyV3ScWg3ZTjTGV6AZGQ0JuUSHsH671GTDjONZt6DWiFXXw8MXhD7W010gV/AqeRz5PnqJx1fnZRzgfEzqFAoGAFK/LLzjxCemexJVaWyoT0FKEBmK+pFSUniKG0WHIL1jnvu7HOdy+e74jaLLDFgjUZc/Ckb0/8NHFVzityNa8JxVfGQGrT46vQXlCZZDblUjFqnRlk+q/t4PhhMJyTQy12KsxpmPGNfw3pgM6ZwYfQr8Z264aWMKWnEnKE3HzROMCgYBpW43pIEm0jBVc7to5gLYgt/y4xGGdEMpgciZgVzKk4h14TV08QZFTYLwO/Ft6JGONeBj+l8nKwzwc3LpoQ5ffOA9jml20b2WO08pTFTI9OgpIRM6OGMHIZI15S6IR2PIEMGUOXSbzvrZ5mrtiJsiCfL/iZdoNKJANRD4osuzuSQ==";
    //工行网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //加密方式
    protected static final String ENCRYPT_TYPE = "";
    //加密密钥
    protected static final String ENCRYPT_KEY = "";
    //请求url
    protected static final String REQ_URL = "https://apipcs3.dccnet.com.cn/api/mybank/pay/base/mep/ordersend/V1";
    //签名类型
    protected static final String SIGN_TYPE = "RSA2";
    //编码格式
    protected static final String CHARSET = "UTF-8";
    //报文类型
    protected static final String FORMAT = "json";
    //应用编号
    protected static final String APP_ID = "10000000000000202049";
    //平台编号
    protected static final String PLATFORM_ID = "100001";

    public static void main(String[] args) {
        MybankPayBaseMepOrdersendRequestV1Biz bizContent = new MybankPayBaseMepOrdersendRequestV1Biz();
        bizContent.getPublicReqInfo().setAppId(APP_ID);
        bizContent.getPublicReqInfo().setPlatformId(PLATFORM_ID);
        bizContent.getPublicReqInfo().setReqDate("20210315");
        bizContent.getPublicReqInfo().setReqTime("090920");
        bizContent.getPublicReqInfo().setReqSerno("A24723847824653391");
        bizContent.getPrivateReqInfo().setB2bOrderNo("B23433849834");
        bizContent.getPrivateReqInfo().setPayType("0");
        bizContent.getPrivateReqInfo().setCurrType("001");
        bizContent.getPrivateReqInfo().setTotalAmount("1000000");
        bizContent.getPrivateReqInfo().setPayerAccountNo("4000093209100160361");
        bizContent.getPrivateReqInfo().setPayerAccountName("究圳映桉住油鞍反绳覆指化僵侦柱");
        bizContent.getPrivateReqInfo().setRemark("办公电脑采购订金");
        bizContent.getPrivateReqInfo().setNotifyUrl("https://dev-api-business.tenpay.com/icbc_notify");

        List<SubOrderList> subOrderList = new ArrayList<SubOrderList>();
        SubOrderList sub1 = new SubOrderList();
        sub1.setSubOrderNo("S32439248928");
        sub1.setPayeeAccountNo("4000027219206725321");
        sub1.setPayeeAccountName("测试账户1");
        sub1.setPayeeBankFlag("0");
        sub1.setPayeeBankCode("102584000002");
        sub1.setPayeeBankName("工商银行");
        sub1.setAmount("500000");
        sub1.setGoodName("联想笔记本电脑");
        sub1.setGoodQuantity("2");
        sub1.setGoodUnit("台");
        sub1.setGoodPrice("500000");
        subOrderList.add(sub1);
        SubOrderList sub2 = new SubOrderList();
        sub2.setSubOrderNo("S32439248929");
        sub2.setPayeeAccountNo("4000027219206725569");
        sub2.setPayeeAccountName("测试账户2");
        sub2.setPayeeBankFlag("0");
        sub2.setPayeeBankCode("102584000002");
        sub2.setPayeeBankName("工商银行");
        sub2.setAmount("500000");
        sub2.setGoodName("戴尔笔记本电脑");
        sub2.setGoodQuantity("3");
        sub2.setGoodUnit("台");
        sub2.setGoodPrice("550000");
        subOrderList.add(sub2);
        bizContent.getPrivateReqInfo().setSubOrderList(subOrderList);

        MybankPayBaseMepOrdersendRequestV1 request = new MybankPayBaseMepOrdersendRequestV1();
        request.setServiceUrl(REQ_URL);
        request.setBizContent(bizContent);

        System.out.println("req=" + JSON.toJSONString(bizContent));

        MybankPayBaseMepOrdersendResponseV1 response = null;
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, CHARSET, FORMAT,
                APIGW_PUBLIC_KEY, ENCRYPT_TYPE, ENCRYPT_KEY, "", "");
        try {
            response = client.execute(request, UUID.randomUUID().toString());
            System.out.println("resp=" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败,return_code=" + response.getReturnCode() + ",return_msg=" + response.getReturnMsg());
        }
    }
}
