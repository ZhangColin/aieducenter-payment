package com.icbc.api;

import com.icbc.api.request.MybankAccountDigitalwalletProgressqueryRequestV1;
import com.icbc.api.response.MybankAccountDigitalwalletProgressqueryResponseV1;

public interface MybankAccountDigitalwalletProgressqueryTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----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";
        //2、appid
        String APP_ID = "10000000000004097526";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        MybankAccountDigitalwalletProgressqueryRequestV1 request = new MybankAccountDigitalwalletProgressqueryRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/progressquery/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountDigitalwalletProgressqueryRequestV1.DigitalwalletProgressqueryRequestV1Biz bizContent = new MybankAccountDigitalwalletProgressqueryRequestV1.DigitalwalletProgressqueryRequestV1Biz();
        bizContent.setCooperatorNo("PT00000000");
        bizContent.setAgentCooperatorNo("1122334455");
        bizContent.setSerialNo("1020201028500193711001");
		bizContent.setRelatedSerialNo("");
		bizContent.setOriginalSerialNo("");
		bizContent.setWorkDate("2021-01-19");
		bizContent.setWorkTime("12:12:12");
		bizContent.setMac("xx:xx:xx:xx:xx:xx");
		bizContent.setIP("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");
		bizContent.setApplyno("COR0102020000174003");
		bizContent.setApplySerialNo("CORP00001");

        request.setBizContent(bizContent);
        request.setNeedEncrypt(true);
        System.out.println("request:" + request);
        MybankAccountDigitalwalletProgressqueryResponseV1 response;
        try {
        	response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response2:"+response.getWalletId());
                System.out.println("response3:"+response.getStatus());
                System.out.println("response4:"+response.getErrno());
                System.out.println("response5:"+response.getErrname());
			}else {
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnCode:"+response.getReturnMsg());
				
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
