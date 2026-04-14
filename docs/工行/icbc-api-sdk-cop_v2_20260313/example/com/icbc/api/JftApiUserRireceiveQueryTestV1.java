package com.icbc.api;

import cas.web.TimerListener;
import com.icbc.api.request.JftApiUserRireceiveQueryRequestV1;
import com.icbc.api.response.JftApiUserRireceiveQueryResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftApiUserRireceiveQueryTestV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(JftApiUserRireceiveQueryTestV1.class);

    private static final String APIGW_PUBLIC_KEY = "[需替换]此处替换工行API网关公钥，请向工行联系获取";

    private static final String APP_ID = "[需替换]此处替换合作方 APPID";

    private static final String MY_PRIVATE_KEY = "[需替换]此处替换合作方 APPID 对应私钥";

    private static final String AES_Key = "[需替换]此处替换合作方 AES 加密秘钥";

    public static void main(String[] args) throws Exception {
        // 声明工行开放平台 API 数据接口请求 Client 实体类，传入配置项
        DefaultIcbcClient client = new DefaultIcbcClient(
                APP_ID,
                IcbcConstants.SIGN_TYPE_RSA2,
                MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,
                APIGW_PUBLIC_KEY,
                IcbcConstants.ENCRYPT_TYPE_AES,
                AES_Key,
                "", "");

        // 组装调用《融e聚反向开票自然人查询接口V1》上送报文
        JftApiUserRireceiveQueryRequestV1 request = new JftApiUserRireceiveQueryRequestV1();
        request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/user/rireceive/query/V1");

        JftApiUserRireceiveQueryRequestV1.JftApiUserRireceiveQueryRequestV1Biz bizContent = new JftApiUserRireceiveQueryRequestV1.JftApiUserRireceiveQueryRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setAppIdSub("1239841923419123");
        bizContent.setReceiverAccount("64440202000000005");
        bizContent.setOutUserId("12345678");
        bizContent.setBusinessType("0005");
        bizContent.setBakField1("123");
        bizContent.setBakField2("234");
        request.setBizContent(bizContent);
        JftApiUserRireceiveQueryResponseV1 response;
        try {
            response = (JftApiUserRireceiveQueryResponseV1) client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                // 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                LOGGER.info("ReturnCode:"+response.getReturnCode());
            } else {
                // 失败
                LOGGER.error("ReturnCode:"+response.getReturnCode());
            }
        } catch (IcbcApiException e) {
            // 异常
            LOGGER.error("IcbcApiException e=", e);
        }
    }
}
