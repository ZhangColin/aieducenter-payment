package com.icbc.api;

import com.icbc.api.request.IcscApiSaasPageTokenRequestV1;
import com.icbc.api.response.IcscApiSaasPageTokenResponseV1;

/**
 * @author dccb-wbwangqb
 * @date 2021/10/28 - 16:01
 */
public class IcscApiSaasPageTokenTestV1 {


    protected static final String MY_PRIVATE_KEY = "[此处为私钥串]";

    protected static final String APIGW_PUBLIC_KEY = "[此处为网关公钥串]";

    protected static final String APP_ID = "[此处为APP_ID]";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        IcscApiSaasPageTokenRequestV1 request = new IcscApiSaasPageTokenRequestV1();
        request.setServiceUrl("https://apipcs4.dccnet.com.cn/api/icsc/api/saas/pageToken/V1");
        IcscApiSaasPageTokenRequestV1.IcscApiSaasPageTokenRequestV1Biz bizContent = new IcscApiSaasPageTokenRequestV1.IcscApiSaasPageTokenRequestV1Biz();
        //appId 平台商户标识
        bizContent.setAppId("10000000000000212516");
        //租户编号
        bizContent.setTenantId("0109");
        bizContent.setInterfaceType("02");
        bizContent.setUserId("2022020118004184");
        bizContent.setUserName("张三");
        bizContent.setMobile("18310251410");
        //accountFlag:1.一类卡小额免密签约；2.电子账户开户
        bizContent.setAccountFlag("2");
        //开户网点号
        bizContent.setAccountOrgNo("0020002030");
        //请求时间戳
        bizContent.setRequestTime(String.valueOf(System.currentTimeMillis()));
        request.setBizContent(bizContent);
        IcscApiSaasPageTokenResponseV1 response;
        try {
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            response = client.execute(request, String.valueOf(System.currentTimeMillis()));
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
                System.out.println("redirectUrl:"+response.getRedirectUrl());
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
