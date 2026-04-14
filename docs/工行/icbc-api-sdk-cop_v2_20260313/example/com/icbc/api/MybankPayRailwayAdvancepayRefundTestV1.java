package com.icbc.ndf.jft.deduction.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;

public class MybankPayRailwayAdvancepayRefundTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "";
    //2、appid
    protected static final String APP_ID = "";
    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY ="";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8",
                "json",APIGW_PUBLIC_KEY,"AES","nezfRV32iH9deJq4IrcCpw==", "", "");

        MybankPayRailwayAdvancepayRefundRequestV1 request = new MybankPayRailwayAdvancepayRefundRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("https://ip:port/api/mybank/pay/railway/advancepay/refund/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MybankPayRailwayAdvancepayRefundRequestV1.MybankPayRailwayAdvancepayRefundRequestV1Biz bizContent = new MybankPayRailwayAdvancepayRefundRequestV1.MybankPayRailwayAdvancepayRefundRequestV1Biz();
        bizContent.setAmount("");
        bizContent.setBusiType("");
        bizContent.setChannelSerno("");
        bizContent.setOriChannelSerno("");
        bizContent.setChanType("");
        bizContent.setPurpose("");
        bizContent.setWorkDate("");
        bizContent.setWorkTime("");
        request.setBizContent(bizContent);

        MybankPayRailwayAdvancepayRefundResponseV1 response;
        try {
            response = (MybankPayRailwayAdvancepayRefundResponseV1) client.execute(request, System.currentTimeMillis()+"");
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
            } else {
                //7.失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
