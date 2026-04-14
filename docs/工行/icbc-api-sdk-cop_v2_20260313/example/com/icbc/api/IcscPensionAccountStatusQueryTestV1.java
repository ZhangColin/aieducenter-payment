package com.icbc.api;

import com.icbc.api.request.IcscPensionAccountStatusQueryRequestV1;
import com.icbc.api.response.IcscPensionAccountStatusQueryResponseV1;

public class IcscPensionAccountStatusQueryTestV1 {

    protected static final String MY_PRIVATE_KEY = "此处为私钥串";
    protected static final String APIGW_PUBLIC_KEY = "此处为网关公钥串";
    protected static final String APP_ID = "此处为APP_ID";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,  MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, "json", APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");
        IcscPensionAccountStatusQueryRequestV1 request = new IcscPensionAccountStatusQueryRequestV1();
        request.setServiceUrl("https://{网关域名}/api/icsc/pension/accountStatusQuery/V1");
        IcscPensionAccountStatusQueryRequestV1.IcscPensionAccountStatusQueryRequestV1Biz bizContent = new IcscPensionAccountStatusQueryRequestV1.IcscPensionAccountStatusQueryRequestV1Biz();
        //appId
        bizContent.setAppId(APP_ID);
        bizContent.setCorpNo("JFT_HuiCanTongTest");
        bizContent.setCorpSerno("调用UI接口时的合作方交易单号");
        //请求时间戳
        bizContent.setRequestTime(String.valueOf(System.currentTimeMillis()));
        request.setBizContent(bizContent);
        IcscPensionAccountStatusQueryResponseV1 response;
        try {
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            response = client.execute(request, String.valueOf(System.currentTimeMillis()));
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("ReturnMsg: " + response.getReturnMsg());
                System.out.println("CipAccountStatus: " + response.getCipAccountStatus());//开户状态
                System.out.println("AccountLocation: " + response.getAccountLocation());//开户归属地
                System.out.println("CorpMediumId: " + response.getCorpMediumId());//合作方介质号（客户在我行的合作方介质号每个用户唯一）
                System.out.println("SecretKey: " + response.getSecretKey());//合作方SM2公钥加密的SM4秘钥串
                System.out.println("MediumId: " + response.getMediumId());//SM4加密的养老金资金账户号(合作方可通过SM2私钥解密获取SM4秘钥，解密该卡号)
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
