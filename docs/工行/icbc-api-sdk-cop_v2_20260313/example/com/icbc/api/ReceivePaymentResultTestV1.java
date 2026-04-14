package com.icbc.api;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.ReceivePaymentResultRequestV1;
import com.icbc.api.response.ReceivePaymentResultResponseV1;

import java.util.Random;
import java.util.logging.Logger;

public class ReceivePaymentResultTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "11100000000000002369";

    static Logger log = Logger.getLogger(ReceivePaymentResultTestV1.class.getName());
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "SM2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        ReceivePaymentResultRequestV1 req = new ReceivePaymentResultRequestV1();

        req.setServiceUrl("xxx");
        ReceivePaymentResultRequestV1.ReceivePaymentResultRequestV1Biz bizContent = new ReceivePaymentResultRequestV1.ReceivePaymentResultRequestV1Biz();

        bizContent.setVersionid("01");
        bizContent.setAppsenderid("xxx");
        bizContent.setAppreceiverid("F-EIOP-BCS");
        bizContent.setOrigsenddate("20241219");
        bizContent.setOrigsendtime("120000");
        bizContent.setStructtype("JSON");
        bizContent.setMesgcharset("UTF-8");
        bizContent.setMesgtype("1");
        bizContent.setMesgstat("0");
        bizContent.setMsg_id("1001");
        bizContent.setMesgpriority("1");

        ReceivePaymentResultRequestV1.ChanCommV10Biz chanCommV10 = new ReceivePaymentResultRequestV1.ChanCommV10Biz();
        chanCommV10.setChantype("1");
        chanCommV10.setChanlno("1");
        chanCommV10.setMac("");
        chanCommV10.setIp("");
        chanCommV10.setTermid("");
        chanCommV10.setOapp("F-EIOP-BCS");
        chanCommV10.setSevlevel("1");
        chanCommV10.setSerialno("1001");
        chanCommV10.setMserialn("1002");
        chanCommV10.setOserialn("1003");
        chanCommV10.setPreflag("0");
        chanCommV10.setProdid("");
        chanCommV10.setCobprodid("");
        chanCommV10.setCino("0");
        chanCommV10.setTrxsqnb("");
        chanCommV10.setDisrecflag("");
        chanCommV10.setComrolflag("");
        chanCommV10.setPaperlessflag("");
        chanCommV10.setField1("");
        chanCommV10.setTermtype("");
        chanCommV10.setLaunbankzoneno("");
        chanCommV10.setFingerprinflag("");
        chanCommV10.setDistransinfo("");
        chanCommV10.setTrxnocheckflag("");
        chanCommV10.setAuthzoneno("");
        chanCommV10.setAuthbrno("");
        chanCommV10.setAuthtype("");
        chanCommV10.setAuthssi("");
        chanCommV10.setTimestamp("");
        chanCommV10.setIftrxsernb("");
        bizContent.setChanCommV10Biz(chanCommV10);

        ReceivePaymentResultRequestV1.InfoCommV10Biz infoCommV10 = new ReceivePaymentResultRequestV1.InfoCommV10Biz();
        infoCommV10.setTrxtype("1");
        infoCommV10.setTrxcode("59619");
        infoCommV10.setZoneno("1102");
        infoCommV10.setBrno("");
        infoCommV10.setTellerno("59");
        infoCommV10.setWorkdate("2024-12-19");
        infoCommV10.setWorktime("16:40:00");
        infoCommV10.setServiceface("53");
        infoCommV10.setAuthtellerno("");
        infoCommV10.setAuthcode("");
        infoCommV10.setAuthlevel("");
        infoCommV10.setActbrno("");
        infoCommV10.setAuthcardno("");
        infoCommV10.setAuthpass("");
        infoCommV10.setAuthdutyno("");
        infoCommV10.setAuthamount("");
        infoCommV10.setRelatedserialno("");
        infoCommV10.setZoneweb("");
        infoCommV10.setBrnoweb("");
        infoCommV10.setZonecrd("");
        infoCommV10.setBrnocrd("");
        infoCommV10.setRegflag("");
        infoCommV10.setIntelno("");
        infoCommV10.setAintelno("");
        infoCommV10.setApptype("");
        bizContent.setInfoCommV10Biz(infoCommV10);

        ReceivePaymentResultRequestV1.IcomBiz icom = new ReceivePaymentResultRequestV1.IcomBiz();
        icom.setConsumer("xxx");
        icom.setTrxDate("2024-12-19");
        icom.setTrxTime("16:40:00");
        icom.setSerialNo("111111");
        icom.setStatus("3");
        icom.setFailMessage("xxx");
        ReceivePaymentResultRequestV1.CustomerInfoBiz customerInfo = new ReceivePaymentResultRequestV1.CustomerInfoBiz();
        customerInfo.setSceneType("0");
        customerInfo.setCustomerId("111111");
        customerInfo.setRechargeNum("111111");
        customerInfo.setCustomerName("zhangsan");
        customerInfo.setCertificateType("0");
        customerInfo.setCertificateId("111111");
        customerInfo.setBackup1("");
        customerInfo.setBackup2("");
        customerInfo.setBackup3("");
        customerInfo.setBackup4("");
        customerInfo.setBackup5("");
        icom.setCustomerInfoBiz(customerInfo);
        bizContent.setIcomBiz(icom);

        req.setBizContent(bizContent);
        ReceivePaymentResultResponseV1 response;

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        try {
            response = client.execute(req,msgId);
            log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getStatus():"+response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():"+response.getReturnCode());
                log.fine("response.getReturnMsg():"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            log.info("IcbcApiException:"+e.getMessage());
        }
    }
}
