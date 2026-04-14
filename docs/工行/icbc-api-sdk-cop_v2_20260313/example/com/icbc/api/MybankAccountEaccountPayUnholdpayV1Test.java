package com.icbc.api;

import com.icbc.api.request.MybankAccountEaccountPayUnholdpayRequestV1;
import com.icbc.api.response.MybankAccountEaccountPayUnholdpayResponseV1;
import java.util.logging.Logger;


public class MybankAccountEaccountPayUnholdpayV1Test {

    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "";//公钥
    // 2、appid 通行证第三方测试1 (行外)，app编号
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥 行外访问
    protected static final String MY_PRIVATE_KEY = "";//私钥
    public static void main(String[] args) {
        
        Logger logger = Logger.getLogger(MybankAccountEaccountPayUnholdpayV1Test.class.getName());
    
        // 签名类型为RSA2时，需传入appid，私钥和AES密钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
//        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY,
//                APIGW_PUBLIC_KEY);
    
				DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
																 IcbcConstants.SIGN_TYPE_RSA2, 
																 MY_PRIVATE_KEY,
																 IcbcConstants.CHARSET_UTF8, 
																 IcbcConstants.FORMAT_JSON,
																 APIGW_PUBLIC_KEY, 
																 IcbcConstants.ENCRYPT_TYPE_AES,
																 AES_KEY, 
																 null, null);//最后两个参数是ca、password   
    
        MybankAccountEaccountPayUnholdpayRequestV1 request = new MybankAccountEaccountPayUnholdpayRequestV1();
        //APIGW（行外网关）：
        request.setServiceUrl("https://ip:port/api/mybank/account/eaccount/pay/unholdpay/V1");
    
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MybankAccountEaccountPayUnholdpayRequestV1.MybankAccountEaccountPayUnholdpayRequestV1Biz bizContent = new MybankAccountEaccountPayUnholdpayRequestV1.MybankAccountEaccountPayUnholdpayRequestV1Biz();
				bizContent.setCorpNo("10000000000004098107");
				bizContent.setTrxAccDate("2022-01-10");
				bizContent.setTrxAccTime("19:30:40");
				bizContent.setZoneNo("101");
				bizContent.setBrNo("1");
				bizContent.setUnholdEventNo("041002002259003101040195125");
				bizContent.setHoldEventNo("041002002259003101040195018");
				bizContent.setTransferEventNo("041002002259003101040195448");
				bizContent.setHoldType("257");
				bizContent.setHoldRemarks("保留");
				bizContent.setHoldDate("2023-04-02");
				bizContent.setHour24Flag("1");
				bizContent.setAmount("10000");
				bizContent.setDebitMediumId("6214760200609712895");
				bizContent.setDebitAccType("1");
				bizContent.setDebitSummary("借方注释");
				bizContent.setCreditAccountNo("0200041729213691985");
				bizContent.setCreditAccType("3");
				bizContent.setEnterType("0");
				bizContent.setAgentNo("111");				
        request.setBizContent(bizContent);
    
        MybankAccountEaccountPayUnholdpayResponseV1 response = new MybankAccountEaccountPayUnholdpayResponseV1();
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
