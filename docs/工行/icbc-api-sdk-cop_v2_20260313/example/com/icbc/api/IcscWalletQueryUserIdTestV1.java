package com.icbc.api;

import com.icbc.api.request.IcscWalletQueryUserIdRequestV1;
import com.icbc.api.response.IcscWalletQueryUserIdResponseV1;

/**
 * @author dccb-wbwangqb
 * @date 2021/10/28 - 16:01
 */
public class IcscWalletQueryUserIdTestV1 {

    protected static final String MY_PRIVATE_KEY = "[此处为私钥串]";
    protected static final String APIGW_PUBLIC_KEY = "[此处为网关公钥串]";
    protected static final String APP_ID = "[APP_ID]";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
                "json", APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");
        IcscWalletQueryUserIdRequestV1 request = new IcscWalletQueryUserIdRequestV1();
        request.setServiceUrl("https://apipcs4.dccnet.com.cn/api/icsc/wallet/queryUserId/V1");
        IcscWalletQueryUserIdRequestV1.IcscWalletQueryUserIdRequestV1Biz bizContent = new IcscWalletQueryUserIdRequestV1.IcscWalletQueryUserIdRequestV1Biz();
        //appId
        bizContent.setAppId("10000000000000212516");
        //平台商户标识
        bizContent.setTenantId("0109");
        bizContent.setMobileType("2");
        bizContent.setMobile("18310251410");
        //请求时间戳
        bizContent.setRequestTime(String.valueOf(System.currentTimeMillis()));
        request.setBizContent(bizContent);
        IcscWalletQueryUserIdResponseV1 response;
        try {
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            response = client.execute(request, String.valueOf(System.currentTimeMillis()));
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("ReturnMsg: " + response.getReturnMsg());
                System.out.println("result: " + response.getResult());
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
