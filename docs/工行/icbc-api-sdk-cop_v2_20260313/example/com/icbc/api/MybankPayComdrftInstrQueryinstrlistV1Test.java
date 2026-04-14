package com.icbc.api;

import com.icbc.api.request.MybankPayComdrftInstrQueryinstrlistRequestV1;
import com.icbc.api.response.MybankPayComdrftInstrQueryinstrlistResponseV1;

import java.util.ArrayList;
import java.util.List;

public class MybankPayComdrftInstrQueryinstrlistV1Test {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankPayComdrftInstrQueryinstrlistRequestV1 request = new MybankPayComdrftInstrQueryinstrlistRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/pay/comdrft/instr/queryinstrlist/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankPayComdrftInstrQueryinstrlistRequestV1.MybankPayComdrftInstrQueryinstrlistRequestBizV1 bizContent = new MybankPayComdrftInstrQueryinstrlistRequestV1.MybankPayComdrftInstrQueryinstrlistRequestBizV1();
        bizContent.setCdTp("AC01");
        bizContent.setAcctId("12xxxxxxxxxxx58");
        bizContent.setBatSerialNo("KYA300xxxxxxxxx48");
        bizContent.setBeginDate("20220101");
        bizContent.setBusinessType("1");
        bizContent.setCdAmtBgn("100");
        bizContent.setCdAmtEnd("1000");
        bizContent.setBusiSeq("1234567");
        bizContent.setEndDate("20240101");
        bizContent.setGroupId("12xxxxxxxxxxx58");
        bizContent.setInstrState("NES_IS_01");
        bizContent.setOperationType("apply");
        List<String> packNoList = new ArrayList<>();
        packNoList.add("61100029xxxxxxxxxxxxx0000055");
        bizContent.setPackNoList(packNoList);
        bizContent.setPageIndex("1");
        bizContent.setPageSize("10");
        bizContent.setTeller("zhangsan");
        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankPayComdrftInstrQueryinstrlistResponseV1 response;
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
