package com.icbc.api;

import com.icbc.api.request.MybankCardbusinessShopMerRegistrationAddRequestV1;
import com.icbc.api.request.MybankCardbusinessShopMerRegistrationAddRequestV1Biz;
import com.icbc.api.response.MybankCardbusinessShopMerRegistrationAddResponseV1;

public class MybankCardbusinessShopMerRegistrationAddTest {

    public static void main(String[] args) throws Exception{

        String MY_PRIVATE_KEY = "密钥";
        String APP_ID = "10000000000004097556";
        String APIGW_PUBLIC_KEY = "公钥";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        MybankCardbusinessShopMerRegistrationAddRequestV1 request = new MybankCardbusinessShopMerRegistrationAddRequestV1();

        request.setServiceUrl("http://ip:port/api/mybank/cardbusiness/shop/MerRegistrationAdd/V1");
        MybankCardbusinessShopMerRegistrationAddRequestV1Biz bizRes = new MybankCardbusinessShopMerRegistrationAddRequestV1Biz();


        bizRes.setChannelFlag("1");
        bizRes.setEmpowerPhone("13502641529");
        bizRes.setApplyType("10000");
        bizRes.setRgiNo("200");
        bizRes.setDominationBankNo("620");
        bizRes.setMcc("1");
        bizRes.setCrftType1("101");
        bizRes.setCrftNo1("1350264151");
        bizRes.setChnsName("1");
        bizRes.setEnglName("1");
        bizRes.setShortName("1");
        bizRes.setLidrange("1");
        bizRes.setMeraddr("1");
        bizRes.setPostCode("560236");
        bizRes.setMerconat("1");
        bizRes.setMertel("13502641529");
        bizRes.setEmail("2561569@136.com");
        bizRes.setMersertel("13502641529");
        bizRes.setCrftType2("1");
        bizRes.setCrftName2("1");
        bizRes.setCrftNo2("1");
        bizRes.setCrftExpiraDate("2020-01-12");
        bizRes.setCrftType3("1");
        bizRes.setCrftNo3("1");
        bizRes.setEmpowerLicenceExpire("2020-01-16");
        bizRes.setCrftName3("1");
        bizRes.setChgType("1");
        bizRes.setAnxinaccflag("1");
        bizRes.setAccntName1("1");
        bizRes.setAccntNo1("1");
        bizRes.setDepositBankNo1("1");
        bizRes.setDepositBankName1("1");
        bizRes.setAccntType2("1");
        bizRes.setAccntNo2("1");
        bizRes.setNotes("1");
        bizRes.setJtmerNo("1");
        bizRes.setWxodsNo("1");
        bizRes.setPersonalInfo1("1");
        bizRes.setPersonalInfo2("1");
        bizRes.setPersonalInfo3("1");
        bizRes.setPersonalInfo4("1");
        bizRes.setPersonalInfo5("1");
        bizRes.setAccntType1("1");
        request.setBizContent( bizRes);
        MybankCardbusinessShopMerRegistrationAddResponseV1 response = new MybankCardbusinessShopMerRegistrationAddResponseV1();
        try{
            response = client.execute(request, "msgId");
            // 判断调用是否成功，进行后续业务处理
            if (response.isSuccess()) {
                // TODO 业务成功处理
                System.out.println(" success:"+response.getReturnCode()+response.getReturnMsg());
            } else {
                // TODO 失败
                System.out.println(" fail:"+response.getReturnCode()+response.getReturnMsg());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
