package com.icbc.api;

import com.icbc.api.request.JftApiUserRireceiveDeleteRequestV1;
import com.icbc.api.response.JftApiUserRireceiveDeleteResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JftApiUserRireceiveDeleteTestV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(JftApiUserRireceiveDeleteTestV1.class);

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

        // 组装调用《融e聚反向开票自然人删除接口V1》上送报文
        JftApiUserRireceiveDeleteRequestV1 request = new JftApiUserRireceiveDeleteRequestV1();
        request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/user/rireceive/delete/V1");

        JftApiUserRireceiveDeleteRequestV1.JftApiUserRireceiveDeleteRequestV1Biz bizContent = new JftApiUserRireceiveDeleteRequestV1.JftApiUserRireceiveDeleteRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setAppIdSub("1239841923419123");
        bizContent.setOutUserId("12345678");
        bizContent.setBusinessType("0005");
        bizContent.setCallbackUrl("http://xx.xx.xx.xx:xxx/notify");
        bizContent.setBakField1("123");
        bizContent.setBakField2("234");
        request.setBizContent(bizContent);
        JftApiUserRireceiveDeleteResponseV1 response;
        try {
            response = (JftApiUserRireceiveDeleteResponseV1) client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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
