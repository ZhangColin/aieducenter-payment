package com.icbc.api;

import com.icbc.api.request.EbankAutopagreeSacRequestV1;
import com.icbc.api.response.EbankAutopagreeSacResponseV1;

/**
 * @ClassName
 * @Descirption 网上银行自动还款注册调整注销
 * @Author kfzx-wangd2
 */
public class EbankAutopagreeSacV1Test {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
    protected static final String appId = "123";

    protected static final String privateKey = "actualprivateKeyValue";
    protected static final String apigwPublicKey = "actualpublicKeyValue";
    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
        EbankAutopagreeSacRequestV1 request = new EbankAutopagreeSacRequestV1();

        request.setServiceUrl("http://ip:port/api/ebank/autopagree/sac/V1");

        //biz_content
        EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz param = new EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz();
        //req_json
        EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson reqJson = new EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson();
        //public
        EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.PubReqInfo pubReqInfo = new EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.PubReqInfo();
        pubReqInfo.setProduct_id("101");
        pubReqInfo.setProject_id("202");
        pubReqInfo.setZoneno("200");
        pubReqInfo.setBrno("620");
        pubReqInfo.setTellerno("12");
        pubReqInfo.setServface("10");
        reqJson.setPubReqInfo(pubReqInfo);
        //channel
        EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.Channel channel = new EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.Channel();
        channel.setChan_type("404");
        channel.setChan_serialno("040402005624216001000003000");
        channel.setOapp("F-APIP");
        reqJson.setChannel(channel);
        //ctrlicom
        EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.Ctrlicom ctrlicom= new EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.Ctrlicom();
        ctrlicom.setCkusr_f("0");
        ctrlicom.setCkpin_f("0");
        reqJson.setCtrlicom(ctrlicom);
        //commcitr
        EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.Commcitr commcitr = new EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.Commcitr();
        commcitr.setIftrxsernb("123456789");
        commcitr.setTrxcode("59233");
        commcitr.setAuthtlno("");
        commcitr.setAuthcode("");
        commcitr.setVagen_level("");
        commcitr.setCardno("");
        commcitr.setAuthpwd("");
        commcitr.setDutyno("");
        commcitr.setMdcardno("4518110021554328");
        commcitr.setMdcardno1("");
        commcitr.setCurrtype("1");
        commcitr.setCurrtype1("");
        commcitr.setCashexf1("");
        commcitr.setAccpin("");
        commcitr.setLgldoctp("");
        commcitr.setIdcode("");
        commcitr.setTermid("231121131111111");
        commcitr.setNotes1("");
        reqJson.setCommcitr(commcitr);
        //icom0786
        EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.Icom0786 icom0786 = new EbankAutopagreeSacRequestV1.EbankAutopagreeSacV1Biz.ReqJson.Icom0786();
        icom0786.setOperflag("3");
        icom0786.setCkidc_f("0");
        icom0786.setN_ddtrxtype("");
        icom0786.setN_ddamttype("");
        icom0786.setN_paydays("");
        icom0786.setN_intimpayf("");
        icom0786.setN_partpayf("");
        icom0786.setN_bankno("");
        icom0786.setN_conntel("");
        icom0786.setN_notes("");
        icom0786.setN_lowtramt("");
        icom0786.setN_tranamt("");
        icom0786.setN_filler("");
        icom0786.setO_incardno("");
        icom0786.setO_ddtrxtype("");
        icom0786.setO_ddamttype("");
        icom0786.setO_paydays("");
        icom0786.setO_outcardno("");
        icom0786.setO_outcurrtp("");
        icom0786.setO_cashexf("");
        icom0786.setO_partpayf("");
        icom0786.setO_conntel("");
        icom0786.setO_notes("");
        icom0786.setO_lowtramt("");
        icom0786.setO_tranamt("");
        icom0786.setO_filler("");
        icom0786.setInaccno("02000000210686412");
        icom0786.setOtherpayf("");
        icom0786.setBksernum("");
        icom0786.setChantype("");
        icom0786.setVerno("");
        icom0786.setOtherddflag("");
        icom0786.setFiller("");
        icom0786.setOutacctype("");
        reqJson.setIcom0786(icom0786);
        param.setReqJson(reqJson);
        request.setBizContent(param);
        EbankAutopagreeSacResponseV1 response = new EbankAutopagreeSacResponseV1();
        try {
            response = client.execute(request);
        } catch (Exception e) {
        }
        if (response.isSuccess()) {
        } else {
        }
    }


}
