package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankCardbusinessShopMerRegistrationModifyRequestV1;
import com.icbc.api.response.MybankCardbusinessShopMerRegistrationModifyResponseV1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybankCardbusinessShopMerRegistrationModifyTest {

    public static void main(String[] args) throws Exception{

        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCzYUaZG/h8xTBwy"
                +"4FiUFwKrTnyBqbQNNa4zfWzMWK+cQ/eEHuPxKcWrv5lwcav+NXE1I3IMUuXIX7zee"
                +"vaIgNs0GnCgcxBk4il/NMoVxlfrUSU2I/cBSMLGcOoD95IeA1kSGO1gQGnKq+g76r"
                +"A0dt87+Jdh4Zjfc9wlryDnDhRRb4XF5t7KBehe8ESMR4K9oLcD2MXfxoXyJE7HiWY"
                +"lhHA3M2A1EDaIZKOcW817kZbJSL+3IeidfItGIVt4cG/GYWWbGnkwmoP2YvHRZcTq"
                +"ncNfcn2VieyWQB6IGyId1eSLDqvAQAHwe63dTO3ca5THlnJyM+4yTbYABBkTZJGhR"
                +"SLAgMBAAECggEAGFiom1lXhZ/B7l1Ap1Ze17ghOD883t99EUIrCfI3A8AWtj4odx/"
                +"qycJglvERBYtRgFCVGcnPB6ktMOCoaUfajSdQY2z4iyH6FwVUp5ICyKCcwVMPW356"
                +"/iO2EZZtxgo9TxIgLl9fGRBLHB3LYDZbgv6QC7+8Nr65q/9ZgMdwQV9qr5bfKyk1Q"
                +"fOdS2aOihSMqm6rB0L4UyMzLXe66QOwevXr1aje2t63yRDyzILoHhpx7BFTmAD43X"
                +"IVBYusbRS6rYI6NSt79Fh73sDFRqDTaNeJUFm4VqrN09vZv3TRasvgWoziYN3Qu4y"
                +"qrSbhxdyNUa/a5aCdfbrSXg5PDMflGQKBgQDfhLFWDssJCiRysmCpOZGjkyd7fivY"
                +"hOZedota7rw/rXjyAi+2Im2oZSq+v0gs4wSMKAEX5mpFrqXd+yEfkfpzczErcw8FA"
                +"UaWMXP47Gw8tl6FAbWuHUh5TzTYuiOa0ZJ3Nde9lr2/68s13DSULtp+nqtKa0URcR"
                +"xQBZcIFsl8vQKBgQDNco1ysZGvGAVj/N9HpLl+S8Y39I78q7wOMqGwXZ+kPSrZhVt"
                +"lgyI13AJIWio2GvMWQ2izEhxmHUI0G51g6OhlOCDrDnJZD3t/Sq8cBsasfgF6M8UI"
                +"5Yep8aDJ9iSDO1b9OQ+PlGGzhPfkpnCQNJpV4WAZ8E+lx8/6BS/nJNr+5wKBgGA3K"
                +"2sjFQ1AG25wEKHn0SR7rMxBBLjAhLX3C4fKKqiykNSEUmtxqIiLe/n+NyBzXLrEYa"
                +"wbGHYEdwv2FU0Utp3J4UmVzspVwT/X69ckVndP/aH9mWpo2pC1oscEYV08h3Lfb44"
                +"B/I5H/wKhVtTO0mkc/hkb0fBvI0PjFB98R6F1AoGBAMsWQdzuyyRainmcrtsTaon+"
                +"PaNyjSYYXX1rb4n4AqdID+OWgBKI3nh/U4JSHwf8++82E3lwncITii4YXio+Efx8z"
                +"42Maly/gUUDfrYlvJ8msC8sncl9m+3SfS8nslBHiGYo0ckM1Mew2ZudLAhg92+Nqo"
                +"H5m8I1EtID7qZ/ODxhAoGBAIcf/IMADuxiCtIs6/MqXJIN7i/fSAR6+mVXzgmu7tE"
                +"759oVqLRiB6m0r7TYPeAtJ958fujjT2uPm8AGHs3xBe+75aLLmGVSl01C4fgX81JD"
                +"DMvGiKacYbLc8nOr6ZEiNfKdTK+0UjbpFOE4CS6d9l/BUUnhjLoNX5jhXVsfNel4";
        String APP_ID = "10000000000004097556";
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        MybankCardbusinessShopMerRegistrationModifyRequestV1 request = new MybankCardbusinessShopMerRegistrationModifyRequestV1();

        request.setServiceUrl("http://ip:port:16257/api/mybank/cardbusiness/shop/ATSPrtiAndProdAgrAdd/V1");
        //	request.setServiceUrl("https://ip:port/api/mybank/customer/shoponline/csocustopen/V1");
        MybankCardbusinessShopMerRegistrationModifyRequestV1.MybankCardbusinessShopMerRegistrationModifyRequestV1Biz bizRes = new MybankCardbusinessShopMerRegistrationModifyRequestV1.MybankCardbusinessShopMerRegistrationModifyRequestV1Biz();

        List merPrtiPermitList = new ArrayList();
        Map merPrtiPermit = new HashMap<>();
        merPrtiPermit.put("rgiNo","200");
        merPrtiPermit.put("brnoStruId","998");
        merPrtiPermit.put("dominationBankNo","00998");
        merPrtiPermit.put("userid","020000014");
        merPrtiPermit.put("crftType1","100");
        merPrtiPermit.put("crftNo1","12592225-2");
        merPrtiPermit.put("crftName2","张三花");
        merPrtiPermit.put("crftType2","0");
        merPrtiPermit.put("crftNo2","441522184902097778");
        merPrtiPermit.put("registCapital","0");
        merPrtiPermit.put("crftType3","");
        merPrtiPermit.put("lidaddr","广东省-广州市-天河区 凤凰");
        merPrtiPermit.put("meraddr","广东省-广州市-天河区 凤凰");
        merPrtiPermit.put("chnsName","API建档-032206");
        merPrtiPermit.put("englName","ffwww");
        merPrtiPermit.put("shortName","sf");
        merPrtiPermit.put("postCode","715704");
        merPrtiPermit.put("merconat","张梅");
        merPrtiPermit.put("mertel","13123456545");
        merPrtiPermit.put("email","6465@qq.com");
        merPrtiPermit.put("domain_name","http://url");
        merPrtiPermit.put("ebankConFlag","2");
        merPrtiPermit.put("shopHomeConFlag","1");
        merPrtiPermit.put("mcssAdminFlag","1");
        merPrtiPermit.put("mcssAdminMobile","13234567865");
        merPrtiPermit.put("mallPostcode","");
        merPrtiPermit.put("salesAccManager","张华华");
        merPrtiPermit.put("managerContactTel","13234546754");
        merPrtiPermit.put("mcc","0104");
        merPrtiPermit.put("cpuMcc","8878");
        merPrtiPermit.put("merRiskRating","3");
        merPrtiPermit.put("creditSwitch","1");
        merPrtiPermit.put("accntType1","1");
        merPrtiPermit.put("accntNo1","4518110021553445");
        merPrtiPermit.put("depositBankNo1","");
        merPrtiPermit.put("depositBankName1","");
        merPrtiPermit.put("accntName1","");
        merPrtiPermit.put("accntType2","3");
        merPrtiPermit.put("accntNo2","6222020200099950905");
        merPrtiPermit.put("depositBankNo2","");
        merPrtiPermit.put("depositBankName2","");
        merPrtiPermit.put("accntName2","");
        merPrtiPermit.put("accntType3","3");
        merPrtiPermit.put("accntNo3","");
        merPrtiPermit.put("depositBankNo3","");
        merPrtiPermit.put("depositBankName3","");
        merPrtiPermit.put("accntName3","");
        merPrtiPermit.put("isriskcheck","1");
        merPrtiPermit.put("checkStatus","0");
        merPrtiPermit.put("lastModDate","2030-03-20");
        merPrtiPermit.put("createUser","020000014");
        merPrtiPermit.put("createStruId","0020001242");
        merPrtiPermit.put("createDate","2029-03-18");
        merPrtiPermit.put("lastoperUser","020000014");
        merPrtiPermit.put("lastoperStruId","0020001242");
        merPrtiPermit.put("lastoperDate","2029-10-01");
        merPrtiPermit.put("recStatus","0");
        merPrtiPermit.put("operation","0");
        merPrtiPermit.put("notes","ip:port");
        merPrtiPermit.put("merlogo",0);
        merPrtiPermit.put("logoaddr","");
        merPrtiPermit.put("apprTellerName","");
        merPrtiPermit.put("permitTellerName","");
        merPrtiPermit.put("relationSrc","9");
        merPrtiPermit.put("channelType","102");
        merPrtiPermitList.add(merPrtiPermit);

        List prodAgrPermitList = new ArrayList();
        Map prodAgrPermit = new HashMap();
        prodAgrPermit.put("prodId","100000020802");
        prodAgrPermit.put("zoneno","200");
        prodAgrPermit.put("lastModDate","2029-03-18");
        prodAgrPermit.put("createUser","020000014");
        prodAgrPermit.put("createStruId","0020001242");
        prodAgrPermit.put("createTimestamp","2029-03-20");
        prodAgrPermit.put("lastoperUser","020000014");
        prodAgrPermit.put("lastoperStruId","0020001242");
        prodAgrPermit.put("lastoperDate","2021-03-18");
        prodAgrPermit.put("prodAgrName","结算协议名称");
        prodAgrPermit.put("balanceLevel","3");
        prodAgrPermit.put("checkoutFlag","2");
        prodAgrPermit.put("accountCycle","1");
        prodAgrPermit.put("thgcflag","1");
        prodAgrPermit.put("chgtype","1");
        prodAgrPermit.put("chgcycle","1");
        prodAgrPermit.put("chgdate","30");
        prodAgrPermit.put("sendAccVerify","1");
        prodAgrPermit.put("email","6545@qq.com");
        prodAgrPermit.put("acccountVerify","1");
        prodAgrPermit.put("transAuth","[\"1\",\"17\",\"18\",\"25\",\"8\"]");
        prodAgrPermit.put("receiveAccessMethod","[\"5\",\"4\",\"6\",\"7\",\"2\"]");
        prodAgrPermit.put("icbcDecardFreesign","1000000000");
        prodAgrPermit.put("icbcCrcardSign","1000000000");
        prodAgrPermit.put("icbcDecardSimsign","1000000000");
        prodAgrPermit.put("icbcCrcardSimsign","1110000000");
        prodAgrPermit.put("cupInFlag","2");
        prodAgrPermit.put("cupDecardFreesign","1111100000");
        prodAgrPermit.put("cupCrcardFreesign","1111111000");
        prodAgrPermit.put("weixinFlag","1");
        prodAgrPermit.put("alipayFlag","1");
        prodAgrPermit.put("transFreeQuota","0");
        prodAgrPermit.put("transFreeQuotasum","0");
        prodAgrPermit.put("speQuotasMin","0");
        prodAgrPermit.put("speQuotasMax","0");
        prodAgrPermit.put("speSumquotasMin","0");
        prodAgrPermit.put("speSumquotasMax","0");
        prodAgrPermit.put("prodLimitFlag","2");
        prodAgrPermit.put("notes","{\"fdPhoto\":\"\",\"sqPhoto\":\"\"}");
        prodAgrPermit.put("mergepayflag","0");
        prodAgrPermit.put("dinnerconnect","0");
        prodAgrPermit.put("cupMcc","8878");
        prodAgrPermit.put("balanceType","1");
        prodAgrPermit.put("relationSrc","5");
        prodAgrPermit.put("channelType","102");
        prodAgrPermit.put("returnTime","365");
        prodAgrPermit.put("cardFreeVerifyLimit","9999999999999");
        prodAgrPermitList.add(prodAgrPermit);

        List agrAccntPermitList = new ArrayList();
        Map<String,String> agrAccntPermit = new HashMap();
        agrAccntPermit.put("trnscCardType","0");
        agrAccntPermit.put("accntPps","1");
        agrAccntPermit.put("accntType","1");
        agrAccntPermit.put("accntNo","4518110021553445");
        agrAccntPermit.put("recordStatus","0");
        agrAccntPermit.put("depositBankNo","");
        agrAccntPermit.put("depositBankName","");
        agrAccntPermit.put("accntName","");
        agrAccntPermit.put("operation","0");
        agrAccntPermit.put("prodId","100000020802");
        agrAccntPermitList.add(agrAccntPermit);

        List agrWeixinPermitList = new ArrayList();
        Map<String,String> agrWeixinPermit = new HashMap<>();
        agrWeixinPermit.put("wxName","张梅");
        agrWeixinPermit.put("crftType","1");
        agrWeixinPermit.put("crftNo","45632");
        agrWeixinPermit.put("wxTel","13456543476");
        agrWeixinPermit.put("wxmcc","7896ww");
        agrWeixinPermit.put("subAppid1","002");
        agrWeixinPermit.put("wxratechannel","002");
        agrWeixinPermit.put("wxqdsno","004");
        agrWeixinPermit.put("wxsercode","444");
        agrWeixinPermit.put("wxName2","张梅");
        agrWeixinPermit.put("crftType2","1");
        agrWeixinPermit.put("crftNo2","12324");
        agrWeixinPermit.put("wxTel2","13123456545");
        agrWeixinPermit.put("wxmcc2","756");
        agrWeixinPermit.put("wxratechannel2","001");
        agrWeixinPermit.put("wxqdsno2","");
        agrWeixinPermit.put("wxsercode2","");
        agrWeixinPermit.put("serid","0");
        agrWeixinPermit.put("seragrtype","0");
        agrWeixinPermit.put("seragrid","0");
        agrWeixinPermit.put("selfflag","0");
        agrWeixinPermit.put("chncode1","");
        agrWeixinPermit.put("subAppid2","002");
        agrWeixinPermit.put("opertype1","0");
        agrWeixinPermit.put("merchantname1","");
        agrWeixinPermit.put("merchantshortname1","");
        agrWeixinPermit.put("merchantremark1","");
        agrWeixinPermit.put("backup1","");
        agrWeixinPermit.put("recstatus1","0");
        agrWeixinPermit.put("chncode2","");
        agrWeixinPermit.put("opertype2","0");
        agrWeixinPermit.put("merchantname2","API建档-032206");
        agrWeixinPermit.put("merchantshortname2","forShortName");
        agrWeixinPermit.put("merchantremark2","");
        agrWeixinPermit.put("backup2","");
        agrWeixinPermit.put("recstatus2","0");
        agrWeixinPermitList.add(agrWeixinPermit);

        List agrAlipayPermitList = new ArrayList();
        Map<String,String> agrAlipayPermit = new HashMap<>();
        agrAlipayPermit.put("selfflag","0");
        agrAlipayPermit.put("serid","0");
        agrAlipayPermit.put("seragrtype","0");
        agrAlipayPermit.put("seragrid","0");
        agrAlipayPermit.put("zfbSubmchid1","");
        agrAlipayPermit.put("zfbMcc1","");
        agrAlipayPermit.put("zfbRateChanl1","002");
        agrAlipayPermit.put("zfbSourceId1","1");
        agrAlipayPermit.put("zfbMerCusttype1","1");
        agrAlipayPermit.put("zfbMerCustnum1","45679");
        agrAlipayPermit.put("zfbMerCustSort1","23");
        agrAlipayPermit.put("zfbMerCustcode1","555");
        agrAlipayPermit.put("zfbMerProvcode1","002");
        agrAlipayPermit.put("zfbMerCitycode1","008");
        agrAlipayPermit.put("zfbMerAreacode1","567");
        agrAlipayPermit.put("zfbMerCard1","434");
        agrAlipayPermit.put("zfbMerCardName1","张华1");
        agrAlipayPermit.put("zfbMerSiteCode1","956");
        agrAlipayPermit.put("zfbMerSiteAddr1","452");
        agrAlipayPermit.put("zfbLifeCode1","765");
        agrAlipayPermit.put("zfbopertype1","1");
        agrAlipayPermit.put("zfbchncode1","");
        agrAlipayPermit.put("zfbchannelid1","");
        agrAlipayPermit.put("zfbmerchantname1","");
        agrAlipayPermit.put("zfbmerchantshortname1","");
        agrAlipayPermit.put("zfbservicephone1","132322456789");
        agrAlipayPermit.put("zfbcontact1","梨花");
        agrAlipayPermit.put("zfbcontacttype1","0");
        agrAlipayPermit.put("zfbmerchantremark1","备注aa");
        agrAlipayPermit.put("zfbsubappid1","002");
        agrAlipayPermit.put("zfbaddress1","22");
        agrAlipayPermit.put("zfbbackup1","");
        agrAlipayPermit.put("recstatus1","0");
        agrAlipayPermit.put("zfbSubmchid2","");
        agrAlipayPermit.put("zfbMcc2","售卖零食");
        agrAlipayPermit.put("zfbRateChanl2","001");
        agrAlipayPermit.put("zfbSourceId2","2088731984846534");
        agrAlipayPermit.put("zfbMerCusttype2","1");
        agrAlipayPermit.put("zfbMerCustnum2","12");
        agrAlipayPermit.put("zfbMerCustSort2","1");
        agrAlipayPermit.put("zfbMerCustcode2","61058119950316311x");
        agrAlipayPermit.put("zfbMerProvcode2","0200");
        agrAlipayPermit.put("zfbMerCitycode2","300");
        agrAlipayPermit.put("zfbMerAreacode2","0100");
        agrAlipayPermit.put("zfbMerCard2","610581199102244565");
        agrAlipayPermit.put("zfbMerCardName2","李留");
        agrAlipayPermit.put("zfbMerSiteCode2","");
        agrAlipayPermit.put("zfbMerSiteAddr2","");
        agrAlipayPermit.put("zfbLifeCode2","icbcicbc01@126.com");
        agrAlipayPermit.put("zfbopertype2","1");
        agrAlipayPermit.put("zfbservicephone2","13123456545");
        agrAlipayPermit.put("zfbmobile2","13123456545");
        agrAlipayPermit.put("zfbcontact2","张梅");
        agrAlipayPermit.put("zfbcontacttype2","0");
        agrAlipayPermit.put("zfbmerchantremark2","");
        agrAlipayPermit.put("zfbmerchantname2","");
        agrAlipayPermit.put("zfbmerchantshortname2","");
        agrAlipayPermit.put("zfbaddress2","");
        agrAlipayPermit.put("zfbbackup2","");
        agrAlipayPermit.put("recstatus2","0");
        agrAlipayPermitList.add(agrAlipayPermit);

        List agrFeePermitList = new ArrayList();
        Map<String,String> agrFeePermit = new HashMap<>();
        agrFeePermit.put("paymentMode","2");
        agrFeePermit.put("trnscCardType","1");
        agrFeePermit.put("feeType","1");
        agrFeePermit.put("trxSection1","0");
        agrFeePermit.put("comRate1","66");
        agrFeePermit.put("trxSection2","0");
        agrFeePermit.put("trxSection3","0");
        agrFeePermit.put("trxSection4","0");
        agrFeePermit.put("trxSection5","0");
        agrFeePermit.put("lowSigAmt","0");
        agrFeePermit.put("rdurbtTrxamt","9999999999999");
        agrFeePermit.put("rdurbtTrxnum","999999999");
        agrFeePermit.put("minComAmt","0");
        agrFeePermit.put("maxComAmt","0");
        agrFeePermit.put("sigComAmt","0");
        agrFeePermit.put("fixedComAmt","0");
        agrFeePermit.put("lowComRate","66");
        agrFeePermit.put("sigLowComamt","0");
        agrFeePermit.put("prodId","100000020802");
        agrFeePermitList.add(agrFeePermit);

        List saesProdComTransAreaList = new ArrayList();
        Map<String,String> saesProdComTransArea = new HashMap<>();
        saesProdComTransArea.put("prodOutId",null);
        saesProdComTransArea.put("prodAgrId",null);
        saesProdComTransArea.put("newNoCardWlistFlag",null);
        saesProdComTransArea.put("ylmerno",null);
        saesProdComTransArea.put("fdPhoto","");
        saesProdComTransArea.put("sqPhoto","");
        saesProdComTransArea.put("prodId",null);
        saesProdComTransArea.put("ewalletNewNocardFlag",null);
        saesProdComTransAreaList.add(saesProdComTransArea);




        List agrForeignPermitList = new ArrayList();

        bizRes.setMerPrtiPermitList(JSON.toJSONString(merPrtiPermitList));
        bizRes.setProdAgrPermitList(JSON.toJSONString(prodAgrPermitList));
        bizRes.setAgrAccntPermitList(JSON.toJSONString(agrAccntPermitList));
        bizRes.setAgrWeixinPermitList(JSON.toJSONString(agrWeixinPermitList));
        bizRes.setAgrAlipayPermitList(JSON.toJSONString(agrAlipayPermitList));
        bizRes.setAgrFeePermitList(JSON.toJSONString(agrFeePermitList));
        bizRes.setSaesProdComTransAreaList(JSON.toJSONString(saesProdComTransAreaList));
        bizRes.setAgrForeignPermitList(JSON.toJSONString(agrForeignPermitList));
        bizRes.setRemark("备注信息");
        bizRes.setSnId("11");
        bizRes.setPrtiId("11");
        request.setBizContent( bizRes);
        MybankCardbusinessShopMerRegistrationModifyResponseV1 response = new MybankCardbusinessShopMerRegistrationModifyResponseV1();
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
