package com.icbc.api;

import com.icbc.api.request.JftApiUserEntrustopenacctQueryRequestV1;
import com.icbc.api.request.JftApiUserEntrustopenacctQueryRequestV1.JftApiUserEntrustopenacctQueryRequestV1Biz;
import com.icbc.api.response.JftApiUserEntrustopenacctQueryResponseV1;

public class JftApiUserEntrustopenacctQueryTestV1 {

    // FIXME：此处替换工行API网关公钥，请向工行联系获取
    private static final String APIGW_PUBLIC_KEY = "[需替换]此处替换工行API网关公钥，请向工行联系获取";

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

        // 组装调用《聚富通委托代扣签约同步开户结果查询数据接口V1》上送报文
        JftApiUserEntrustopenacctQueryRequestV1 request = new JftApiUserEntrustopenacctQueryRequestV1();
        request.setServiceUrl("https://ip:port/api/jft/api/user/entrustopenacct/query/V1");

        JftApiUserEntrustopenacctQueryRequestV1Biz bizContent = new JftApiUserEntrustopenacctQueryRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setOutVendorId("SH000001");
        bizContent.setOutUserId("M201801112326");
        bizContent.setProjectId("PJ140014021000004309");
        bizContent.setBusiCode("888810222");
        request.setBizContent(bizContent);
        JftApiUserEntrustopenacctQueryResponseV1 response;
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
            e.printStackTrace();
        }
    }
}
