package com.icbc.api;

import com.icbc.api.request.MybankPayCorporatewalletUnholdPayRequestV1;
import com.icbc.api.response.MybankPayCorporatewalletUnholdPayResponseV1;

public class MybankPayCorporatewalletUnholdPayTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankPayCorporatewalletUnholdPayRequestV1 request = new MybankPayCorporatewalletUnholdPayRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/corporatewallet/unholdpay/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankPayCorporatewalletUnholdPayRequestV1.CorporatewalletUnholdPayRequestV1Biz bizContent = new MybankPayCorporatewalletUnholdPayRequestV1.CorporatewalletUnholdPayRequestV1Biz();
        bizContent.setAgr_no("BOB01");
        bizContent.setBus_serialno("11xxxxxxxxxxxxxxxxxxxxxxx63");
        bizContent.setWork_date("2020-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("00:xx:00:xx:89:4A");
        bizContent.setIp("122.xx.xx.240");
        bizContent.setPayer_wallet_id("00xxxxxxxxxxxx91");
        bizContent.setPayer_wallet_name("珠xxxxx资");
        bizContent.setPayer_name("珠xxxxx资");
        bizContent.setPayer_telno("13xxxxxxxxx");
        bizContent.setPayer_wallet_type(1);
        bizContent.setPayer_wallet_level(1);
        bizContent.setPayee_wallet_id("00xxxxxxxxxxxx10");
        bizContent.setPayee_wallet_name("珠xxxxxxxxx公司");
        bizContent.setPayee_name("珠xxxxxxxxx公司");
        bizContent.setPayee_telno("13xxxxxxxxx");
        bizContent.setPayee_wallet_type(1);
        bizContent.setPayee_wallet_level(1);
        bizContent.setBusiscene(1);
        bizContent.setAmount(1000L);
        bizContent.setCurrtype(1);
        bizContent.setSummary("摘要");
        bizContent.setNotes("附言");
        bizContent.setRemark("备注");
        bizContent.setCustominfo("2");
        bizContent.setPayer_freeze_flag(2);
        bizContent.setPayee_freeze_flag(2);
        bizContent.setAgrno("00xxxx00");
        bizContent.setPayee_agrno("00xxxx00");
        bizContent.setBuss_code("C202");
        bizContent.setBuss_type("02044");
        bizContent.setRealcpmediatype("1");
        bizContent.setRealcpmediano("00xxxxxxxxxxxx10");
        bizContent.setRealcpmedianame("珠xxxxxxxxx公司");
        bizContent.setRealcpname("珠xxxxxxxxx公司");
        bizContent.setUsages("用途");

        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankPayCorporatewalletUnholdPayResponseV1 response;
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
