package com.icbc.api;

import com.icbc.api.request.JftApiUserRiOrderConfirmDeauthRequestV1;
import com.icbc.api.response.JftApiUserRiOrderConfirmDeauthResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftApiUserRiOrderConfirmDeauthTestV1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(JftApiUserRiOrderConfirmDeauthTestV1.class);

    // 此处替换工行API网关公钥，请向工行联系获取
    private static final String APIGW_PUBLIC_KEY = "[需替换]此处替换工行API网关公钥，请向工行联系获取";

    // 此处替换合作方 APPID
    private static final String APP_ID = "[需替换]此处替换合作方 APPID";

    // 此处替换合作方 APPID 对应私钥
    private static final String MY_PRIVATE_KEY = "[需替换]此处替换合作方 APPID 对应私钥";

    // 此处替换合作方 AES 加密秘钥
    private static final String AES_KEY = "[需替换]此处替换合作方 AES 加密秘钥";

    public static void main(String[] args) {
        // 声明工行开放平台 API 数据接口请求 Client 实体类，传入配置项
        DefaultIcbcClient client = new DefaultIcbcClient(
                APP_ID,
                IcbcConstants.SIGN_TYPE_RSA2,
                MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,
                APIGW_PUBLIC_KEY,
                IcbcConstants.ENCRYPT_TYPE_AES,
                AES_KEY,
                "", "");

        // 组装调用《聚富通通用客户信息查询数据接口V1》上送报文
        JftApiUserRiOrderConfirmDeauthRequestV1 request = new JftApiUserRiOrderConfirmDeauthRequestV1();
        request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/user/riOrderConfirm/deauth/V1");

        JftApiUserRiOrderConfirmDeauthRequestV1.JftApiUserRiOrderConfirmDeauthRequestV1Biz bizContent = new JftApiUserRiOrderConfirmDeauthRequestV1.JftApiUserRiOrderConfirmDeauthRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutUserId("10001112");
        bizContent.setTransNo("1000000012345");
        request.setBizContent(bizContent);
        JftApiUserRiOrderConfirmDeauthResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                LOGGER.info("ReturnCode:" + response.getReturnCode());
                LOGGER.info("response:" + response);
            } else {
                //失败
                LOGGER.error("ReturnCode:" + response.getReturnCode());
                LOGGER.error("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            LOGGER.error("IcbcApiException e=", e);
        }
    }
}