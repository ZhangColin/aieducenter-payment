package com.icbc.api;

import com.icbc.api.request.MybankAccountCorporatewalletSmartdepositsignRequestV1;
import com.icbc.api.request.MybankPayCorporatewalletCashsweepRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletSmartdepositsignResponseV1;
import com.icbc.api.response.MybankPayCorporatewalletCashsweepResponseV1;

public class MybankAccountCorporatewalletSmartdepositsignTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankAccountCorporatewalletSmartdepositsignRequestV1 request = new MybankAccountCorporatewalletSmartdepositsignRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/corporatewallet/smartdepositsign/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountCorporatewalletSmartdepositsignRequestV1.MybankAccountCorporatewalletSmartdepositsignRequestV1Biz bizContent = new MybankAccountCorporatewalletSmartdepositsignRequestV1.MybankAccountCorporatewalletSmartdepositsignRequestV1Biz();
        bizContent.setAgr_no("BOB01");
        bizContent.setBus_serialno("11xxxxxxxxxxxxxxxxxxxxxxx63");
        bizContent.setWork_date("2022-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("00:xx:00:xx:89:4A");
        bizContent.setIp("122.xx.xx.240");

        bizContent.setAgr_name("xxx");
        bizContent.setWallet_id("11xxxxxxxxxx11");
        bizContent.setCurr_type(1);
        bizContent.setCi_no("xxx");
        bizContent.setOrgan_no("xxx");
        bizContent.setMedia_type(1);
        bizContent.setAcc_no("");
        bizContent.setIs_auto_bind(1);
        bizContent.setMg_code(1);
        bizContent.setMgacc_type("xx")
        bizContent.setZone_no(1234);
        bizContent.setBr_no(123);
        bizContent.setCalintcl(123)
        bizContent.setDepdeadline("xxxx");
        bizContent.setRedepterm(123);
        bizContent.setRateincm(123);
        bizContent.setRate_code(123);
        bizContent.setFloa_rate(12l);
        bizContent.setInt_accno("12");
        bizContent.setIntaccno_type(123);
        bizContent.setBakdec3(123);
        bizContent.setValue_day("xxx");
        bizContent.setBakdec4(123);
        bizContent.setDq_end_date("xxx");
        bizContent.setIslh_flag(1);
        bizContent.setLhmglh_type(2)
        bizContent.setLhsav_term("xxxx");
        bizContent.setLh_rate_code(123);
        bizContent.setLg_rate_incm(123);
        bizContent.setLh_value_day("12");
        bizContent.setLhfloa_rate(10l);
        bizContent.setEffective_date("xxxx");
        bizContent.setEnd_date("20xx-xx-12");
        bizContent.setNote("xx");
        bizContent.setAgreeno("11202408221234567892");
        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankAccountCorporatewalletSmartdepositsignResponseV1 response;
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
