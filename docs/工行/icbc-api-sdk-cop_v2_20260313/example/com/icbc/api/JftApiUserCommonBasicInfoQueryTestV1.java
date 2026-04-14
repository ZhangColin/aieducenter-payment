package com.icbc.api;

import com.icbc.api.request.JftApiUserCommonBasicInfoQueryRequestV1;
import com.icbc.api.response.JftApiUserCommonBasicInfoQueryResponseV1;

public class JftApiUserCommonBasicInfoQueryTestV1 {

    // FIXME：此处替换工行API网关公钥，请向工行联系获取
    private static final String APIGW_PUBLIC_KEY = "[需替换]此处替换工行API网关公钥，请向工行联系获取";
    
    // FIXME：此处替换工行API网关域名，请向工行联系获取
    private static final String APIGW_URL = "[需替换]此处替换工行API网关域名，请向工行联系获取";
    
    // FIXME: 此处替换合作方 APPID
    private static final String APP_ID = "[需替换]此处替换合作方 APPID";

    // FIXME：此处替换合作方 APPID 对应私钥
    private static final String MY_PRIVATE_KEY = "[需替换]此处替换合作方 APPID 对应私钥";

    // FIXME：此处替换合作方 AES 加密秘钥
    private static final String AES_Key = "[需替换]此处替换合作方 AES 加密秘钥";

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
                AES_Key,
                "", "");

        // 组装调用《聚富通通用客户信息查询数据接口V1》上送报文
        JftApiUserCommonBasicInfoQueryRequestV1 request = new JftApiUserCommonBasicInfoQueryRequestV1();
        request.setServiceUrl(APIGW_URL+"/api/jft/api/user/common/basicinfo/query/V1");

        JftApiUserCommonBasicInfoQueryRequestV1.JftApiUserCommonBasicInfoQueryRequestV1Biz bizContent = new JftApiUserCommonBasicInfoQueryRequestV1.JftApiUserCommonBasicInfoQueryRequestV1Biz();
        bizContent.setCustName("张楚岚");
        bizContent.setIdNo("350100200406020071");
        bizContent.setIdType("0");
        request.setBizContent(bizContent);
        JftApiUserCommonBasicInfoQueryResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            // log.error("");
        }
    }
}
