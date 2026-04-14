package com.icbc.api;

import com.icbc.api.request.MybankAccountDigitalwalletModifyprogressqueryRequestV1;
import com.icbc.api.request.MybankAccountDigitalwalletModifyprogressqueryResponseV1;

public interface MybankAccountDigitalwalletModifyprogressqueryTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "";
        //2、appid
        String APP_ID = "";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        MybankAccountDigitalwalletModifyprogressqueryRequestV1 request = new MybankAccountDigitalwalletModifyprogressqueryRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/modifyprogressquery/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountDigitalwalletModifyprogressqueryRequestV1.MybankAccountDigitalwalletModifyprogressqueryV1Biz  bizContent = new MybankAccountDigitalwalletModifyprogressqueryRequestV1.MybankAccountDigitalwalletModifyprogressqueryV1Biz();
        bizContent.setCooperatorNo("PT00000000");
        bizContent.setAgentCooperatorNo("1122334455");
        bizContent.setSerialNo("1020201028500193711001");
		bizContent.setRelatedSerialNo("");
		bizContent.setOriginalSerialNo("");
		bizContent.setWorkDate("2021-01-19");
		bizContent.setWorkTime("12:12:12");
		bizContent.setMac("xx:xx:xx:xx:xx:xx");
		bizContent.setIP("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");
		bizContent.setApplyNo("COR0102020000174003");
		bizContent.setWalletId("123456789");
		bizContent.setModifyType("1");
		bizContent.setOperateType("1");
        bizContent.setApplySerialNo("123xxxxxxx");

        request.setBizContent(bizContent);
        request.setNeedEncrypt(true);
        System.out.println("request:" + request);
        MybankAccountDigitalwalletModifyprogressqueryResponseV1 response;
        try {
        	response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response3:"+response.getStatus());
                System.out.println("ErrNo:"+response.getErrNo());
                System.out.println("ErrName:"+response.getErrName());
			}else {
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnCode:"+response.getReturnMsg());
				
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
