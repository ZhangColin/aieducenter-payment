package com.icbc.api;


import com.icbc.api.request.MybankPayDigitalWalletSweepcashRequestV1;

import com.icbc.api.response.MybankPayDigitalWalletSweepcashResponseV1;

public interface MybankPayDigitalWalletSweepcashV1Test {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "";
        //2、appid
        String APP_ID = "";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        MybankPayDigitalWalletSweepcashRequestV1 request = new MybankPayDigitalWalletSweepcashRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/digitalwallet/sweepcash/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankPayDigitalWalletSweepcashRequestV1.MybankPayDigitalWalletSweepcashV1Biz bizContent = new MybankPayDigitalWalletSweepcashRequestV1.MybankPayDigitalWalletSweepcashV1Biz();
		bizContent.setCooperatorNo("1234567890");
		bizContent.setSerialNo("002000000201234567891200001");
		bizContent.setRelatedSerialNo("");
		bizContent.setOriginalSerialNo("");
		bizContent.setWorkDate("2021-01-19");
		bizContent.setWorkTime("12:12:12");
		bizContent.setMac("xx:xx:xx:xx:xx:xx");
		bizContent.setIP("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");
		bizContent.setWalletId("0022000848230201");
        bizContent.setParentWalletId("0022000848230202");
        bizContent.setAmount(100L);
        bizContent.setCurrType("1");
        bizContent.setSubWalletName("母钱包");
        bizContent.setSubWalletName("子钱包");
        request.setBizContent(bizContent);

        request.setBizContent(bizContent);
        System.out.println("request:" + request);
        MybankPayDigitalWalletSweepcashResponseV1 response;
        try {
        	response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getTransok());
				System.out.println("response1:" + response);
			}else {
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("response2:"+response.getTransok());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
