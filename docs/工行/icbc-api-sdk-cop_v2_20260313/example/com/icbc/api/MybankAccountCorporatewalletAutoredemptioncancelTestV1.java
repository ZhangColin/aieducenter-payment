package com.icbc.api;

import com.icbc.api.request.MybankAccountCorporatewalletAutoredemptioncancelRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletAutoredemptioncancelResponseV1;

public class MybankAccountCorporatewalletAutoredemptioncancelTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        //2、appid
        String APP_ID = "xxxxxxxxxxxxxxxxxxxxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        MybankAccountCorporatewalletAutoredemptioncancelRequestV1 request = new MybankAccountCorporatewalletAutoredemptioncancelRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/corporatewallet/autoredemptioncancel/V1");

        MybankAccountCorporatewalletAutoredemptioncancelRequestV1.MybankAccountCorporatewalletAutoredemptioncancelRequestV1Biz bizContent = new MybankAccountCorporatewalletAutoredemptioncancelRequestV1.MybankAccountCorporatewalletAutoredemptioncancelRequestV1Biz();
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        bizContent.setAgr_no("PTxxxxxx13");
        bizContent.setWork_date("2021-05-25");
        bizContent.setWork_time("10:13:12");
        bizContent.setMac("xx:xx:xx:xx:xx:xx");
        bizContent.setBus_serialno("11xxxxx11");
        bizContent.setIp("xxx.xxx.xxx.xxx");

        bizContent.setWallet_id("00xxxxxxxxxxxx99");
        bizContent.setTran_date("2022-05-17");
        bizContent.setTran_time("15:42:16");
        bizContent.setChannel("3");
        bizContent.setPpseq_no("10xxxxxxxxxxxxxxxxxxxx03");
        bizContent.setBak1("1");

        request.setBizContent(bizContent);
        System.out.println("request:" + request.toString());
        MybankAccountCorporatewalletAutoredemptioncancelResponseV1 response;
        try {
            response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if ("0".equals(response.getResult_code())) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response1:" + response.getReturnMsg());
            }else {
                System.out.println("returnCode:"+response.getReturnCode());
                System.out.println("response2:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
