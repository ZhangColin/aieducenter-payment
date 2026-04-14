package com.icbc.api;

import com.icbc.api.request.MybankPayCorporatewalletExchangeoutRequestV1;
import com.icbc.api.response.MybankPayCorporatewalletExchangeoutResponseV1;

public class MybankPayCorporatewalletExchangeoutTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankPayCorporatewalletExchangeoutRequestV1 request = new MybankPayCorporatewalletExchangeoutRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/corporatewallet/exchangeout/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankPayCorporatewalletExchangeoutRequestV1.MybankPayCorporatewalletExchangeoutRequestV1Biz bizContent = new MybankPayCorporatewalletExchangeoutRequestV1.MybankPayCorporatewalletExchangeoutRequestV1Biz();
        bizContent.setAgr_no("BOB01");
        bizContent.setBus_serialno("11xxxxxxxxxxxxxxxxxxxxxxx63");
        bizContent.setWork_date("2020-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("00:xx:00:xx:89:4A");
        bizContent.setIp("122.xx.xx.240");
        bizContent.setProtocol_id("21XXXXXXXXXXX63");
        bizContent.setAccount_id("21XXXXXXXXXXX63");
        bizContent.setWallet_id("00XXXXXXXXXXXX00");
        bizContent.setAmount("10");
        bizContent.setTrans_note("test1111");
        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankPayCorporatewalletExchangeoutResponseV1 response;
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
