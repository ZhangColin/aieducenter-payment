package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiUserEdpreceiveQueryRequestV1;
import com.icbc.api.request.JftApiUserEdpreceiveQueryRequestV1.JftApiUserEdpreceiveQueryRequestV1Biz;
import com.icbc.api.response.JftApiUserEdpreceiveQueryResponseV1;

public class JftApiUserEdpreceiveQueryV1Test {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    //2、appid
    protected static final String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公 钥上传至工行API开放平台";

    public static void main(String[] args) throws Exception {
        String host = "[需替换]工行API网关地址";
        String apiUrl = host + "/api/jft/api/user/edpreceive/query/V1";
        String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";
        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "utf-8", "json", APIGW_PUBLIC_KEY, "AES", encryptKey, "", "");
        JftApiUserEdpreceiveQueryRequestV1 request = new JftApiUserEdpreceiveQueryRequestV1();
        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl(apiUrl);
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiUserEdpreceiveQueryRequestV1.JftApiUserEdpreceiveQueryRequestV1Biz bizContent = new JftApiUserEdpreceiveQueryRequestV1.JftApiUserEdpreceiveQueryRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setAppIdSub("[需替换]");
        bizContent.setOutUserId("[需替换]");
        bizContent.setReceiverAccount("1239841923419123");
        bizContent.setBusinessType("0001");
        request.setBizContent(bizContent);
        JftApiUserEdpreceiveQueryResponseV1 response;
        try {
            response = (JftApiUserEdpreceiveQueryResponseV1) client.execute(request,
                    System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                // 失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
