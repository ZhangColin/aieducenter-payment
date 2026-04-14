package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayNcagreementupdateRequestV1;
import com.icbc.api.request.CardbusinessNcpayNcagreementupdateRequestV1.CardbusinessNcpayUpdateAgreementRequestV1Biz;
import com.icbc.api.response.CardbusinessNcpayNcagreementupdateResponseV1;

import java.util.logging.Logger;

/**
 * @author kfzx-wangming
 * @Description:无卡支付协议修改注销服务
 * @date: 2019/10/8 9:39
 */
public class CardbusinessNcpayNcagreementupdateTestV1 {
    public static void main(String[] args) throws IcbcApiException {

        //1、API平台申请的APPID
        String APP_ID = "XXX";


        //2、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        //3、密钥对认证方式，公钥在API平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCpx55HRWBecD9PDGnjgG14gySvwgsWahRXl+6wvlsC/oT1Gr4vQyVIQ6JJDkWFopyKrbcfSm5YPanGLiM2iBZ4/Vsa5mUGmr7Ne4DAD2zEFhDPlFrMuyP7h5t89qtnL9Q4vgz89W35qDAiJ3lLjutRgk2LmyvYFpgTazdH/34cIkBIfhlTLLUGKEXL2fEc0A5oKkLPHtu1/YF6u8rlsu7Xe6WLOBeHk9KqBR7BH+5ctL75oZBBRbwKauFPRWE/tQcjXv+qxJJVq6TfbQCRPKpzdmt/YcsiWnJwL1CEeXndXsY6mX14fSH1jHmU2cnkjccudA/bBJAKPSzFfQ1FCp2NAgMBAAECggEBAKiAqiJqRJ72IJ8/3sggaoW1AvA9UevJFK9q50cbZhCusx7gj+kwZww1tBd1xqI/JcatapXvtVx2ts0udVxl7IfgTE+CYroPU1d1P+9eX8CtdbnPr96JzciSaku4NZ2P5rz6QsG8hI3H0x3St5u6GOsou/XW58jrg7uiKZeXh347DwtxFJEhgjZBGUDw1+GOWapz1WFXfkjxEiikYTW6YSLYGHR765YXG5wpanIqknz1iTBkXxedlzc4WbIfJMYNlilrYGUhEwaMuCHHz6HIVqSRQxmmJT+ZwZ4aCFeZnvBKe7dDRrhisqigJh0QqqnwO0aIVc0fBdKBFbb0sxrkaYECgYEA7ML1DPoGGMrpWOUiFzwpOkg3ZpL/9+KyReTzxAY/eMV9C4dUMXSE0yh46YueeYiqlinj88Ob7Q2AmzHh4M+J9BbmyR8pV8uM/W7X7Rzs9YkgcB2dQcU7WBfSaErwvIVTR62ABLoCgv9q74sWdPOTGxfIGQafzJNBEKmUdJxJx9kCgYEAt5NTbUk99cONlI7k98Ow8FVcAnx1qvmZfuVukRb6lufgbLhCKy3bz23hYkyUyVLl8Q6o+Vgg/TTuXQ8DCCPF5F/Cv/s8S+/ulF1sT7J/P9x3a/tCSCICfiRNM5jByG9zDZI4UgxzDV8AR2BvH19sCL355ENA/8ljkrvZkbfSRtUCgYEA5rMVOp7JoBDgDWm/rSpG9e52ZTGpz24KzgZI7OI1qMWukbQ8+q+R2XtfThpowf3EUuvgnZ8l6Xe1sAOMGJXB06EZIjKGA3FpIW2HYxlDYAFNJVIF8JdxuuYDdPJI3zl+q6JI0tI5LlIZbe52ZGEPH2At7wzAWwPFa6oB6Rp83gkCgYBo6kMgz14g6j0tLSmAzI+AG1i1dwoMyA/IrTkupLJcsnLZz8jJQrFAcG0CMABMuxr9VMKPlqVr8OdVufGiokdECNKVw/Em+R4iHu568em1xrtdtECiVwWTsA2LMn91nPRlSi7z/Xd3qdbP9DYt+OwWZvS9Vw574U4OG1vx4tvDbQKBgQCpIFfrj19CQU/+oWwtkdfdK/E/hMfK1ESFHfWAX+Pq4pIyEIz/Kb3IweKy9uEGbE/zNfi3m5ClCf9H3B4BYk5EKbSk3lVDKg3F4U+1ETNE4fwvKdBfCgpJMi12xdTtkuhfrVUjhaw7T0pFc8fJkP5P5Q+elY6iwYgn+JPm+2yb8A==";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessNcpayNcagreementupdateRequestV1 request = new CardbusinessNcpayNcagreementupdateRequestV1();

        //4、API网关地址：根据测试环境和生产环境替换相应IP和端口
        request.setServiceUrl("http://XXX:XXX/api/cardbusiness/ncpay/ncagreementupdate/V1");


        //5、请参照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值updateAgreement
        CardbusinessNcpayUpdateAgreementRequestV1Biz bizContent = new CardbusinessNcpayUpdateAgreementRequestV1Biz();
        //交易流水号(16)
        bizContent.setTrxSerno("XXX");
        //交易日期(10)
        bizContent.setTrxDate("2020-04-26");
        //交易时间(8)
        bizContent.setTrxTime("11:58:37");
        //介质类型(3)
        bizContent.setSignMedtype(3);
        //绑定介质编号(50)
        bizContent.setSignMedid("湘K999999");
        //绑定商圈(13)
        bizContent.setMerGroupNo("4600000000001");
        //签约介质属性
        bizContent.setSignMedInfo("5");
        //操作标识(3) 1-解约  2-换卡
        bizContent.setOperFlag("1");
        //银行卡号
        bizContent.setCardno("XXX");
        //创建日期
        bizContent.setCreateDate("2020-08-25");
        //换卡
        //交易渠道 1-智慧加油   4-智慧停车
        bizContent.setSceneType(1);
        //新卡号
        bizContent.setNewCardno("XXX");
        //手机号
        bizContent.setMobileNo("15804198643");

        //签约要素包含必输，否则选输
        bizContent.setCustName("黄可欣");
        bizContent.setIdNo("XXX");
        bizContent.setExpiredDate("202601");
        bizContent.setCardCvv2("908");
        bizContent.setVerifyCode("123456");
        bizContent.setVerifyCodeNo("XXX");

        //新平台
        //服务商编号
        bizContent.setSerId("020000001485");
        //服务上协议编号
        bizContent.setSerAgrId("XXX");
        //新旧平台标志 0-商户，2-服务商
        bizContent.setNewMerFlag("0");


        request.setBizContent(bizContent);
        CardbusinessNcpayNcagreementupdateResponseV1 response;
        try {
            String msg_id = String.valueOf(System.currentTimeMillis());
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            response = client.execute(request, msg_id);

            Logger log = Logger.getLogger(CardbusinessNcpayCarinfocollectTestV1.class.getName());
            if (response.isSuccess()) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                log.info("ReturnCode:" + response.getReturnCode() + "\n      Response:" + response.getReturnMsg());
            } else {
                // 失败
                log.info("ReturnCode:" + response.getReturnCode() + "\n      Response:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}


