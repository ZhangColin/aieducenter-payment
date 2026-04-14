package com.icbc.api;

import com.icbc.api.request.MybankAccountEaccountAgreementProjectqueryRequestV1;
import com.icbc.api.response.MybankAccountEaccountAgreementProjectqueryResponseV1;

import java.util.logging.Logger;


public class MybankAccountEaccountAgreementProjectqueryV1Test {
    
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "";//公钥
    // 2、appid 通行证第三方测试1 (行外)
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥 行外访问
    protected static final String MY_PRIVATE_KEY ="";//私钥
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(MybankAccountEaccountAgreementProjectqueryV1Test.class.getName());
        
        // 签名类型为RSA2时，需传入appid，私钥和AES密钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                APIGW_PUBLIC_KEY);
    
        MybankAccountEaccountAgreementProjectqueryRequestV1 request = new MybankAccountEaccountAgreementProjectqueryRequestV1();
    
        //APIGW（行外网关）：
        request.setServiceUrl("https://ip:port/api/mybank/account/eaccount/agreement/projectquery/V1");
    
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MybankAccountEaccountAgreementProjectqueryRequestV1.MybankAccountEaccountAgreementProjectqueryRequestV1Biz bizContent = new MybankAccountEaccountAgreementProjectqueryRequestV1.MybankAccountEaccountAgreementProjectqueryRequestV1Biz();
        bizContent.setCorpAppId("xxx");
    
        request.setBizContent(bizContent);
    
        MybankAccountEaccountAgreementProjectqueryResponseV1 response = new MybankAccountEaccountAgreementProjectqueryResponseV1();
        try {
            response = client.execute(request);
            if (response.isSuccess()) {
                logger.fine("ReturnCode:" + response.getReturnCode());
            } else {
                logger.fine("returnCode:" + response.getReturnCode());
            }
            logger.fine("returnMsg:" + response.getReturnMsg());
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
