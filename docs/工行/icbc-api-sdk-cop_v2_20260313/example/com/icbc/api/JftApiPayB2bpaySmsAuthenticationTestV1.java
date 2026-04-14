package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiPayB2bpaySmsAuthenticationRequestV1;
import com.icbc.api.request.JftApiPayB2bpaySmsAuthenticationRequestV1.JftApiPayB2bpaySmsAuthenticationRequestV1Biz;
import com.icbc.api.response.JftApiPayB2bpaySmsAuthenticationResponseV1;


public class JftApiPayB2bpaySmsAuthenticationTestV1 {

    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    //2、appid
    protected static final String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    protected static String host = "[需替换]工行API网关地址";
    protected static String apiUrl= host+"api/jft/api/pay/b2bpay/smsauthentication/V1";
    protected static String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

    public static void main(String[] args) throws Exception {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");
        JftApiPayB2bpaySmsAuthenticationRequestV1 request = new JftApiPayB2bpaySmsAuthenticationRequestV1();
        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl(apiUrl);
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiPayB2bpaySmsAuthenticationRequestV1Biz bizContent = new JftApiPayB2bpaySmsAuthenticationRequestV1Biz();
        bizContent.setAppId("1201903150000001129");//平台商户标识
        bizContent.setOutOrderId("201904170065709801010100");//所查订单的ID
        bizContent.setMessageTypet("01");//消息类型 01-短信验证码验证
        bizContent.setSendCode("888888");//短信验证码 messageType=01 时必填
        request.setBizContent(bizContent);
        JftApiPayB2bpaySmsAuthenticationResponseV1 response;
        try {
            response = (JftApiPayB2bpaySmsAuthenticationResponseV1) client.execute(request, System.currentTimeMillis()+"");
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("return_code:" + response.getReturn_code());
                System.out.println("return_msg:" + response.getReturn_msg());
                System.out.println("status:" + response.getStatus());
            }
        } catch (IcbcApiException e) {
            System.out.println("ErrCode: " + e.getErrCode());
            System.out.println("ErrMsg: " + e.getErrMsg());
        }
    }



}
