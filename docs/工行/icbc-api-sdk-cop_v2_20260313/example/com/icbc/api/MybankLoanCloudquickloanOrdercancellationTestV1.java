package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankLoanCloudquickloanOrdercancellationRequestV1;
import com.icbc.api.response.MybankLoanCloudquickloanOrdercancellationResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kfzx-lihh
 * @Description: 订单取消
 * @Date: 2019/11/11
 */
public class MybankLoanCloudquickloanOrdercancellationTestV1 {
    /*public static void main(String[] args) {
        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQClDtj4WWl85bLkdgOc5yX9x/2G63SePhiIKjRjQkSaKk9T2fNOIHmmqPPFQHarUIHjd0bnUhcuNuBnSKFj4kkFa5UoKEdhq1C44pqSayI2k0MJZlr6XMObtWvxqO7kwsA1oX4Xbyyv87IYvji/EzRT9nIZSSYVQoNloHxvuRE//q5t9mUbKabnJM/cPa3BeoHPFpWwzEL0mPKfRMXqU2HGOjVnGn1RX2NOQpkfe0CPYDjMDR+PHNxNfGUA+1asWITuxEpNMYcgTOp8vsZjCteNFPItJuPQkAJVCQDxVY6wAVczUBRHR74XYmWcG0v1JOhwCiz1YYTOajZ/iLVSeK0VAgMBAAECggEBAILQNZz+H+Bgm1r9PPfJKg7rxT58tTwEsqSX7lUMXBpileQ6w92TV1aCaFBYOrSjZY3FJmiGYuC5lTfGFEJ5QK4PCXN+GLSLdbpRvXd1ePQP2f1FJa1uf4iUTCyGWm7q+jKG/svLzHrWhLufZpHownwYwWzQ0GHhCQouGvDgl49DGdN76fG2nVL+mqIihMIcBCk7tHOk1xRg5lyATPzDUTgZMbMNqhuBQ4pqZkXbM7yzWq3dJ4sALGNhnlUGMB/qU16SF+xH82ViRS1xc8OSGduG+qgRCl4FcbCI4MhGZRmYlBQoeaWPhuO3ZwNTNA4GjxepOgj+0IJlmaLpRz/fawECgYEA9yM5I9dsfZTIqGG+asVvnAT71XAlPurnrIJm29d2E2sSerQtVu8g0YN08Rladl1oiFglMt8+jcqQsG/l2InxsOMoBr2J02tuO0OEMuxEoH492s8OZ/LuZQVKwYEo5+E1ivYXgMlCE64UZK22oTVJ6QvSdKLRCp2EMwIGAQU4Z6UCgYEAqvodp47Zu7fBPrgKtjI8RcuurzjRtIVihLiLJKleIlreBoqhxgfQhlbeh28GTf2l9CX0FPHB64Y5FBfGT7b7YoB+ZyyvphOr3buXbZELldcF0y2J+K+3Q1eJpDxO485kvSxXmUdJj+X4ruIosmSP1pwbt12AhTm4bqinB0vltLECgYEAuabsNdCO34f1dQh4Ux0eSsYzqJMmZ61vfFg5jMy/FrdImoTfyn642Ks4HiY9ns1syX/t8dli1IFnUCO/Nnvp5EfAuc+IyhnaJQsDNH3oz+gF72m8GLqV+62OCu9MG2VDI7Da1xRZXw9Woosctt2hOEB5SMVG5AKQXQEYIYwaY0ECgYAUZ2bUkTKguUwhfPsiRRp2Rdlh5cj43xYjupY4WNH4DZoDc9XceSBEd+d0iCZbdlKwsTrQqQ04WztCj5fF+ZNEbQsoqueHTT+sZW+rzqxwREnYqs2VVPrs2Wx+ogfUHdRd0wXdL+myd4JW9Hxg1alothaEqm7k9z9wTUYpk8Ak8QKBgQCXYXM/zNK3ZGJB83M+BXjZhhj9jtYfbRipf+v9zkMkXQgamNQ3ovZVFKuGEo10B1iJGzdiIQJwxPI2tJ1dfPQwif+LPxX7TwmUh8jQMz869qkvGw+ohsfbOqQorEKMa3rKpBN6n7SLCMFQdTTIUJ6YpqDTTyOUr4gYgR9gbIQfMA==";
        //2、appid，华为在F-APIP平台登记的组织者信息，对应模块：app管理
        String APP_ID = "10000000000004096856";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);
        //5、设置请求对象
        MybankLoanCloudquickloanOrdercancellationRequestV1 request = new MybankLoanCloudquickloanOrdercancellationRequestV1();
        //6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
        request.setServiceUrl("http://ip:port/api/mybank/loan/cloudquickloan/ordercancellation/V1");
        //7、设置业务参数
        MybankLoanCloudquickloanOrdercancellationRequestV1.MybankLoanOrdercancellationRequestV1Biz bizContent = new MybankLoanCloudquickloanOrdercancellationRequestV1.MybankLoanOrdercancellationRequestV1Biz();

        //8、组装请求参数
        MybankLoanCloudquickloanOrdercancellationRequestV1.SubInput subInput = new MybankLoanCloudquickloanOrdercancellationRequestV1.SubInput();
        subInput.setOrderNo("1111");
        subInput.setRemark1("");
        subInput.setRemark2("");
        subInput.setRemark3("");

        List<MybankLoanCloudquickloanOrdercancellationRequestV1.SubInput> subInputs = new ArrayList<>();
        subInputs.add(subInput);

        //组装请求参数
        bizContent.setType("01");
        bizContent.setCIS("2222");
        bizContent.setTotalNum("1");
        bizContent.setSubInput(subInputs);
        bizContent.setCooperUnit("ST0340014");

        request.setBizContent(bizContent);

        MybankLoanCloudquickloanOrdercancellationResponseV1 response;
        try {
            response = client.execute(request, "1111111");
            System.out.println("response:" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }*/
}
