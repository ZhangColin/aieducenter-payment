package com.icbc.api;

import com.icbc.api.request.MybankPayCreditcardconsumeCreditcardconsumeRequestV3;
import com.icbc.api.response.MybankPayCreditcardconsumeCreditcardconsumeResponseV3;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import java.util.logging.Logger;

public class MybankPayCreditcardconsumeCreditcardconsumeTestV3 {

    private static final String MY_PRIVATE_KEY = "****";
    private static final String APP_ID = "1234";
    private static final String APIGW_PUBLIC_KEY = "****";
    private static final String API_SERVICE_URL = "http://ip:port/api";

    public static void main(String[] args) {

        Logger log = Logger.getLogger(MybankPayCreditcardconsumeCreditcardconsumeTestV3.class.getName());

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);
        MybankPayCreditcardconsumeCreditcardconsumeRequestV3 request = new MybankPayCreditcardconsumeCreditcardconsumeRequestV3();
        MybankPayCreditcardconsumeCreditcardconsumeRequestV3.MybankPayCreditcardconsumeCreditcardconsumeRequestV3Biz bizContent = new MybankPayCreditcardconsumeCreditcardconsumeRequestV3.MybankPayCreditcardconsumeCreditcardconsumeRequestV3Biz();
        //访问工行API平台URL
        request.setServiceUrl(API_SERVICE_URL + "/mybank/pay/creditcardconsume/creditcardconsume/V3");

        MybankPayCreditcardconsumeCreditcardconsumeRequestV3.ChanCommV10 chanCommV10 = new MybankPayCreditcardconsumeCreditcardconsumeRequestV3.ChanCommV10();
        chanCommV10.setChannelType("101");
        chanCommV10.setOapp("F-SOCT");
        chanCommV10.setSerialno("150202005162835750018916647");
        chanCommV10.setMserialn("150202005162835750018916647");
        chanCommV10.setPreflag(1);
        MybankPayCreditcardconsumeCreditcardconsumeRequestV3.InfoCommV10 infoCommV10 = new MybankPayCreditcardconsumeCreditcardconsumeRequestV3.InfoCommV10();
        infoCommV10.setTrxtype("1");
        infoCommV10.setTrxcode("1325");
        infoCommV10.setZoneno("00200");
        infoCommV10.setBrno("00035");
        infoCommV10.setTellerno("93188");
        infoCommV10.setWorkdate("2025-11-01");
        infoCommV10.setWorktime("11:12:13");
        infoCommV10.setRevtranf("1");
        MybankPayCreditcardconsumeCreditcardconsumeRequestV3.TranComm tranComV10 = new MybankPayCreditcardconsumeCreditcardconsumeRequestV3.TranComm();
        tranComV10.setTrxServFace("1");
        tranComV10.setTrxOperationType("1");
        tranComV10.setTrxDateToAccount("1");
        tranComV10.setChannelAccessType("1");
        tranComV10.setAppName("F-SOCT");
        MybankPayCreditcardconsumeCreditcardconsumeRequestV3.MediChkComm mediChkComm = new MybankPayCreditcardconsumeCreditcardconsumeRequestV3.MediChkComm();
        mediChkComm.setCardinputmode("1");
        mediChkComm.setCardPinChkFlag("");
        mediChkComm.setCardPin("");
        MybankPayCreditcardconsumeCreditcardconsumeRequestV3.MediDataComm mediDataComm = new MybankPayCreditcardconsumeCreditcardconsumeRequestV3.MediDataComm();
        mediDataComm.setTrack1("");
        mediDataComm.setTrack2("");
        MybankPayCreditcardconsumeCreditcardconsumeRequestV3.ProdAuthComm prodAuthComm = new MybankPayCreditcardconsumeCreditcardconsumeRequestV3.ProdAuthComm();
        prodAuthComm.setCardno("65852235445455");
        prodAuthComm.setPassNet("0");
        prodAuthComm.setSceneCode("1");
        prodAuthComm.setDbcrFlag("1");
        prodAuthComm.setBusiCcy("1");
        prodAuthComm.setBusiAmt("1");
        bizContent.setChannelCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setTranComm(tranComV10);
        bizContent.setMediChkComm(mediChkComm);
        bizContent.setMediDataComm(mediDataComm);
        bizContent.setProdAuthComm(prodAuthComm);
        bizContent.setChkNameFlag("0");
        bizContent.setPreTrxSerno("");
        bizContent.setChkAccumulateFlag("0");
        bizContent.setChkAccAuthFlag("0");
        request.setBizContent(bizContent);

        try {
            MybankPayCreditcardconsumeCreditcardconsumeResponseV3 response = client.execute(request);
        } catch (Exception e) {
            log.error("MybankPayCreditcardconsumeCreditcardconsume.Exception: ", e);
        }
    }
}