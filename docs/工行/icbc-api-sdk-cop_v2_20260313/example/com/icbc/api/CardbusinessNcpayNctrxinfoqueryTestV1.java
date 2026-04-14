package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayNctrxinfoqueryRequestV1;
import com.icbc.api.request.CardbusinessNcpayNctrxinfoqueryRequestV1.CardbusinessNcpayNctrxinfoqueryRequestV1Biz;
import com.icbc.api.response.CardbusinessNcpayNctrxinfoqueryResponseV1;

import java.text.SimpleDateFormat;


public class CardbusinessNcpayNctrxinfoqueryTestV1 {
    public static void main(String[] args) throws IcbcApiException {

        // 1、API平台申请的APPID
        String APP_ID = "XXX";


        // 2、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCpx55HRWBecD9PDGnjgG14gySvwgsWahRXl+6wvlsC/oT1Gr4vQyVIQ6JJDkWFopyKrbcfSm5YPanGLiM2iBZ4/Vsa5mUGmr7Ne4DAD2zEFhDPlFrMuyP7h5t89qtnL9Q4vgz89W35qDAiJ3lLjutRgk2LmyvYFpgTazdH/34cIkBIfhlTLLUGKEXL2fEc0A5oKkLPHtu1/YF6u8rlsu7Xe6WLOBeHk9KqBR7BH+5ctL75oZBBRbwKauFPRWE/tQcjXv+qxJJVq6TfbQCRPKpzdmt/YcsiWnJwL1CEeXndXsY6mX14fSH1jHmU2cnkjccudA/bBJAKPSzFfQ1FCp2NAgMBAAECggEBAKiAqiJqRJ72IJ8/3sggaoW1AvA9UevJFK9q50cbZhCusx7gj+kwZww1tBd1xqI/JcatapXvtVx2ts0udVxl7IfgTE+CYroPU1d1P+9eX8CtdbnPr96JzciSaku4NZ2P5rz6QsG8hI3H0x3St5u6GOsou/XW58jrg7uiKZeXh347DwtxFJEhgjZBGUDw1+GOWapz1WFXfkjxEiikYTW6YSLYGHR765YXG5wpanIqknz1iTBkXxedlzc4WbIfJMYNlilrYGUhEwaMuCHHz6HIVqSRQxmmJT+ZwZ4aCFeZnvBKe7dDRrhisqigJh0QqqnwO0aIVc0fBdKBFbb0sxrkaYECgYEA7ML1DPoGGMrpWOUiFzwpOkg3ZpL/9+KyReTzxAY/eMV9C4dUMXSE0yh46YueeYiqlinj88Ob7Q2AmzHh4M+J9BbmyR8pV8uM/W7X7Rzs9YkgcB2dQcU7WBfSaErwvIVTR62ABLoCgv9q74sWdPOTGxfIGQafzJNBEKmUdJxJx9kCgYEAt5NTbUk99cONlI7k98Ow8FVcAnx1qvmZfuVukRb6lufgbLhCKy3bz23hYkyUyVLl8Q6o+Vgg/TTuXQ8DCCPF5F/Cv/s8S+/ulF1sT7J/P9x3a/tCSCICfiRNM5jByG9zDZI4UgxzDV8AR2BvH19sCL355ENA/8ljkrvZkbfSRtUCgYEA5rMVOp7JoBDgDWm/rSpG9e52ZTGpz24KzgZI7OI1qMWukbQ8+q+R2XtfThpowf3EUuvgnZ8l6Xe1sAOMGJXB06EZIjKGA3FpIW2HYxlDYAFNJVIF8JdxuuYDdPJI3zl+q6JI0tI5LlIZbe52ZGEPH2At7wzAWwPFa6oB6Rp83gkCgYBo6kMgz14g6j0tLSmAzI+AG1i1dwoMyA/IrTkupLJcsnLZz8jJQrFAcG0CMABMuxr9VMKPlqVr8OdVufGiokdECNKVw/Em+R4iHu568em1xrtdtECiVwWTsA2LMn91nPRlSi7z/Xd3qdbP9DYt+OwWZvS9Vw574U4OG1vx4tvDbQKBgQCpIFfrj19CQU/+oWwtkdfdK/E/hMfK1ESFHfWAX+Pq4pIyEIz/Kb3IweKy9uEGbE/zNfi3m5ClCf9H3B4BYk5EKbSk3lVDKg3F4U+1ETNE4fwvKdBfCgpJMi12xdTtkuhfrVUjhaw7T0pFc8fJkP5P5Q+elY6iwYgn+JPm+2yb8A==";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessNcpayNctrxinfoqueryRequestV1 request = new CardbusinessNcpayNctrxinfoqueryRequestV1();

        // 4、API网关地址：根据测试环境和生产环境替换相应IP和端口
//        request.setServiceUrl("http://ip:port/api/cardbusiness/ncpay/nctrxinfoquery/V1");//功能
        request.setServiceUrl("http://gw.dccnet.com.cn/api/cardbusiness/ncpay/nctrxinfoquery/V1");//功能外网

        CardbusinessNcpayNctrxinfoqueryRequestV1Biz bizContent = new CardbusinessNcpayNctrxinfoqueryRequestV1Biz();

//        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
//        long dataNum = System.currentTimeMillis();


        //数据库account ip:port bcsspublic数据库    第条数据成功(用于迁移后接口的更新,带CCAP验证)

        bizContent.setQryTrxSerno("XXX");
        bizContent.setQryTrxDate("2025-06-30");
        bizContent.setQryMerchantNo("020001020483");
        bizContent.setTrxType("8");
        bizContent.setTrxSerno("XXX");
        bizContent.setTrxDate("2020-04-09");
        bizContent.setTrxTime("19:16:18");
        //交易卡号选输
        bizContent.setCardNo("");
        //交易币种选输
        bizContent.setTrxCurr("1");
        //交易金额选输
        bizContent.setTrxAmount("");
        //介质类型选输
        bizContent.setSignMedType("");
        //介质编号选输
        bizContent.setSignMedId("");
        bizContent.setNewMernoFlag("1");
        bizContent.setMerAgrId("XXX");
        bizContent.setoBankTrxSerno("XXX");

        request.setBizContent(bizContent);
        request.isNeedEncrypt();
        String msg_id = String.valueOf(System.currentTimeMillis());
        try {
            CardbusinessNcpayNctrxinfoqueryResponseV1 response = client.execute(request, msg_id);
//            System.out.println("sucessflg=>" + response.getReturnCode() + "|" + response.getReturnMsg());
            if ("8".equals(bizContent.getTrxType())) {
//                System.out.println("查询交易状态成功=>" + response.getTrxState());
//                System.out.println("银行交易检索号:" + response.getBankTrxSerno());
//                System.out.println("银行卡交易日期:" + response.getBankTrxDate());
//                System.out.println("银行授权号:" + response.getBankAuthorNo());
            } else {
//                System.out.println("银行交易检索号:" + response.getBankTrxSerno());
//                System.out.println("银行卡交易日期:" + response.getBankTrxDate());
//                System.out.println("查询交易状态成功=>" + response.getTrxState());
//                System.out.println("优惠金额=>" + response.getDiscountAmt());
//                System.out.println("扣帐金额=>" + response.getAccountAmt());
            }

        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}






