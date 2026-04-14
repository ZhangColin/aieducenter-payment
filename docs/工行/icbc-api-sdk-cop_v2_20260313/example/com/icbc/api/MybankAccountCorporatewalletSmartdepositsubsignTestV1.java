package com.icbc.api;

import com.icbc.api.request.MybankAccountCorporatewalletSmartdepositsubsignRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletSmartdepositsubsignResponseV1;

public class MybankAccountCorporatewalletSmartdepositsubsignTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankAccountCorporatewalletSmartdepositsubsignRequestV1 request = new MybankAccountCorporatewalletSmartdepositsubsignRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/corporatewallet/smartdepositsubsign/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountCorporatewalletSmartdepositsubsignRequestV1.MybankAccountCorporatewalletSmartdepositsubsignRequestV1Biz bizContent = new MybankAccountCorporatewalletSmartdepositsubsignRequestV1.MybankAccountCorporatewalletSmartdepositsubsignRequestV1Biz();
        bizContent.setAgr_no("BOB01");
        bizContent.setBus_serialno("11xxxxxxxxxxxxxxxxxxxxxxx63");
        bizContent.setWork_date("2020-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("00:xx:00:xx:89:4A");
        bizContent.setIp("122.xx.xx.240");
        bizContent.setAgree_no("10xxxxxxxxxxxxxx28");
        bizContent.setWallet_id("00xxxxxxxxxxxx14");
        bizContent.setCurrtype("1");
        bizContent.setCi_no("02xxxxxxxxxxx56");
        bizContent.setOrgan_no("02xxxx98");
        bizContent.setIs_auto_dep("1");
        bizContent.setIs_sc_control("0");
        bizContent.setCal_int_cl("1");
        bizContent.setRate_incm("2");
        bizContent.setRate_code("3");
        bizContent.setFloa_rate("4");
        bizContent.setInt_accno("02xxxxxxxxxxxxxxx18");
        bizContent.setBak_dec3("6");
        bizContent.setValue_day("2022-07-01");
        bizContent.setBak_dec4("7");
        bizContent.setDqend_date("2022-10-10");
        bizContent.setIs_lh_flag("8");
        bizContent.setLhmglh_type("9");
        bizContent.setLh_sav_term("10");
        bizContent.setLh_rate_code("11");
        bizContent.setRate_incm("1");
        bizContent.setLh_value_day("2022-07-01");
        bizContent.setLh_floa_rate("13");
        bizContent.setNote("备注");
        bizContent.setRemark1("1");
        bizContent.setRemark2("1");
        bizContent.setIs_inherit("1");
        bizContent.setIs_compay("0");
        bizContent.setAuto_type("5");
        bizContent.setZone_no("200");
        bizContent.setBr_no("200");
        bizContent.setDep_dead_line("001");
        bizContent.setRe_dep_term("12");
        bizContent.setInt_accno_type("1");
        bizContent.setEnd_date("2023-08-14");
        bizContent.setSub_agree_no("123");
        bizContent.setControl_scene("123");
        bizContent.setControl_scene_enddate("2023-08-14");
        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankAccountCorporatewalletSmartdepositsubsignResponseV1 response;
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
