package com.icbc.api;

import com.icbc.api.request.MybankAccountCorporatewalletSmartdepositcancelRequestV1;
import com.icbc.api.request.MybankPayCorporatewalletComtransferRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletSmartdepositcancelResponseV1;
import com.icbc.api.response.MybankPayCorporatewalletComtransferResponseV1;

public class MybankPayCorporatewalletComtransferTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankPayCorporatewalletComtransferRequestV1 request = new MybankPayCorporatewalletComtransferRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/corporatewallet/comtransfer/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankPayCorporatewalletComtransferRequestV1.MybankPayCorporatewalletComtransferRequestV1Biz bizContent = new MybankPayCorporatewalletComtransferRequestV1.MybankPayCorporatewalletComtransferRequestV1Biz();
        bizContent.setAgr_no("BOB01");
        bizContent.setBus_serialno("11xxxxxxxxxxxxxxxxxxxxxxx63");
        bizContent.setWork_date("2022-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("00:xx:00:xx:89:4A");
        bizContent.setIp("122.xx.xx.240");
        bizContent.setAccount_type("100");
        bizContent.setBill_type("100");
        bizContent.setPayer_wallet_id("00xxxxxxxxxxxx00");
        bizContent.setPayer_wallet_name("13xxxxxxxxx");
        bizContent.setPayer_name("00xxxxxx01");
        bizContent.setPayer_telno("9");
        bizContent.setPayer_email("1");
        bizContent.setPayer_wallet_type("xxxxxxxxx公司");
        bizContent.setPayer_wallet_level("00xxxxxxxxxxxx02");
        bizContent.setPayee_wallet_id("13xxxxxxxxx");
        bizContent.setPayee_wallet_name("00xxxxxx01");
        bizContent.setPayee_name("9");
        bizContent.setPayee_telno("1");
        bizContent.setPayee_wallet_type("xxxxxxxxx公司");
        bizContent.setPayee_wallet_level("BOB01");
        bizContent.setBusiscene("11xxxxxxxxxxxxxxxxxxxxxxx63");
        bizContent.setAmount("2022-12-12");
        bizContent.setCurrtype("12:12:12");
        bizContent.setSummary("00:xx:00:xx:89:4A");
        bizContent.setNotes("122.xx.xx.240");
        bizContent.setBak1("100");
        bizContent.setRemark("100");
        bizContent.setCustominfo("00xxxxxxxxxxxx00");
        bizContent.setPayer_freeze_flag("13xxxxxxxxx");
        bizContent.setPayee_freeze_flag("00xxxxxx01");
        bizContent.setAgrno("9");
        bizContent.setPayee_telno("1");
        bizContent.setBuss_code("xxxxxxxxx公司");
        bizContent.setBuss_type("00xxxxxxxxxxxx02");
        bizContent.setRealcpmediatype("13xxxxxxxxx");
        bizContent.setRealcpmediano("00xxxxxx01");
        bizContent.setRealcpmedianame("9");
        bizContent.setRealcpname("1");
        bizContent.setUsages("xxxxxxxxx公司");
        bizContent.setBusiness_type("1");
        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankPayCorporatewalletComtransferResponseV1 response;
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
