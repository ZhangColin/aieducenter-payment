package com.icbc.api.response;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.MybankAccountCorpwalletHoldinfoqueryRequestV1;
import com.icbc.api.response.MybankAccountCorpwalletHoldinfoqueryResponseV1;

public class MybankAccountCorpwalletHoldinfoqueryV1Test {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "******";
        //2、appid
        String APP_ID = "1234XXXXXXXXXXXXXXXX";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "******";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        MybankAccountCorpwalletHoldinfoqueryRequestV1 request = new MybankAccountCorpwalletHoldinfoqueryRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/corpwallet/holdinfoquery/V1");

        MybankAccountCorpwalletHoldinfoqueryRequestV1.MybankAccountCorpwalletHoldinfoqueryRequestV1Biz bizContent = new MybankAccountCorpwalletHoldinfoqueryRequestV1.MybankAccountCorpwalletHoldinfoqueryRequestV1Biz();
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountCorpwalletHoldinfoqueryRequestV1.ChanCommV10 chanCommV10 = new MybankAccountCorpwalletHoldinfoqueryRequestV1.ChanCommV10();
        chanCommV10.setMac("00:XX:XX:XX:XX:00");
        chanCommV10.setIP("0.X.X.0");
        chanCommV10.setSerialNo("1234XXXXXXXXXXXXXXXXXXXXXXX");
        chanCommV10.setMserialn("1");
        chanCommV10.setOapp(); //固定
        chanCommV10.setChanType(101);
        bizContent.setChanCommV10(chanCommV10);

        MybankAccountCorpwalletHoldinfoqueryRequestV1.InfoCommV10 infoCommV10 = new MybankAccountCorpwalletHoldinfoqueryRequestV1.InfoCommV10();
        infoCommV10.setTrxType(3);
        infoCommV10.setTrxCode(0);
        infoCommV10.setZoneNo(0);
        infoCommV10.setBrNo(0);
        infoCommV10.setTellerNo(); //固定
        infoCommV10.setWorkDate("2022-05-11");
        infoCommV10.setWorkTime("20:56:00");
        bizContent.setInfoCommV10(infoCommV10);

        MybankAccountCorpwalletHoldinfoqueryRequestV1.PrivateMap privateMap = new MybankAccountCorpwalletHoldinfoqueryRequestV1.PrivateMap();
        privateMap.setWalletId("12XXXXXXXXXXXXXX");
        privateMap.setAgrNo("12XXXXXXXXXXXXXXXX");
        privateMap.setStatus(1);
        privateMap.setStartDate("2022-03-21");
        privateMap.setEndDate("9999-12-31");
        privateMap.setIndex(0);
        privateMap.setPageSize(10);
        bizContent.setPrivateMap(privateMap);

        request.setBizContent(bizContent);
        request.setNeedEncrypt(true);
        System.out.println("request:" + request.toString());
        MybankAccountCorpwalletHoldinfoqueryResponseV1 response;

        try {
            response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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
