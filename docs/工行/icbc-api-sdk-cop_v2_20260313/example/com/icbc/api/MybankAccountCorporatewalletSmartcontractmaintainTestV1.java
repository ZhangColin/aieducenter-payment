package com.icbc.api;

import com.icbc.api.request.MybankAccountCorporatewalletSmartcontractmaintainRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletSmartcontractmaintainResponseV1;

public class MybankAccountCorporatewalletSmartcontractmaintainTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankAccountCorporatewalletSmartcontractmaintainRequestV1 request = new MybankAccountCorporatewalletSmartcontractmaintainRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/corporatewallet/smartcontractmaintain/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountCorporatewalletSmartcontractmaintainRequestV1.MybankAccountCorporatewalletSmartcontractmaintainRequestV1Biz bizContent = new MybankAccountCorporatewalletSmartcontractmaintainRequestV1.MybankAccountCorporatewalletSmartcontractmaintainRequestV1Biz();
        bizContent.setAgr_no("BOB01");
        bizContent.setBus_serialno("11xxxxxxxxxxxxxxxxxxxxxxx63");
        bizContent.setWork_date("2022-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("00:xx:00:xx:89:4A");
        bizContent.setIp("122.xx.xx.240");
        bizContent.setWallet_id("00xxxxxxxxxxxx91");
        bizContent.setContract_id("13xxxxxxxxxxxxxx30");
        bizContent.setAgr_name("智能合约协议维护");
        bizContent.setCurrtype("1");
        bizContent.setSign_date("2022-08-20");
        bizContent.setSign_time("14:45:30");
        bizContent.setAgr_mode("2");
        bizContent.setMange_mode("1");
        bizContent.setEnd_date("2025-01-01");
        bizContent.setContract_type("[1]");
        bizContent.setCheck_type("1");
        bizContent.setOp_type("1");
        bizContent.setNote("备注");
        bizContent.setRpcheckflag("2");
        bizContent.setDirect_pay_id("M2022");
        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankAccountCorporatewalletSmartcontractmaintainResponseV1 response;
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
