package com.icbc.api;

import com.icbc.api.request.MybankPayComdrftRegisterQuerycontractlistRequestV1;
import com.icbc.api.response.MybankPayComdrftRegisterQuerycontractlistResponseV1;

public class MybankPayComdrftRegisterQuerycontractlistV1Test {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankPayComdrftRegisterQuerycontractlistRequestV1 request = new MybankPayComdrftRegisterQuerycontractlistRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/comdrft/register/querycontractlist/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankPayComdrftRegisterQuerycontractlistRequestV1.MybankPayComdrftRegisterQuerycontractlistRequestBizV1 bizContent = new MybankPayComdrftRegisterQuerycontractlistRequestV1.MybankPayComdrftRegisterQuerycontractlistRequestBizV1();

        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankPayComdrftRegisterQuerycontractlistResponseV1 response;
        try {
            response = client.execute(request, String.valueOf(System.currentTimeMillis())); //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response1:" + response.getReturnMsg());
            } else {
                System.out.println("returnCode:" + response.getReturnCode());
                System.out.println("response2:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
